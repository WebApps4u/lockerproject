CREATE DATABASE  IF NOT EXISTS `kplok_dev_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `kplok_dev_db`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: kplok_dev_db
-- ------------------------------------------------------
-- Server version	5.5.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping events for database 'kplok_dev_db'
--

--
-- Dumping routines for database 'kplok_dev_db'
--
/*!50003 DROP PROCEDURE IF EXISTS `auto_bill_generation` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `auto_bill_generation`(
IN dueMonth varchar(2),   -- month should in digits, 01, 02, 03 etc
IN dueYear varchar(4)
)
BEGIN

DECLARE finished INTEGER DEFAULT 0;
declare batch_size int default 10;

-- TODO this will be moved to configurable section
declare const_service_tax double default 14;

-- all the variables of the table

-- all party record variables
declare v_party_kno varchar(255);
declare v_party_lokr varchar(255);
declare v_party_lpa varchar(255);
declare v_party_lrdd date;
declare v_party_lrnd varchar(255);
declare v_party_lrno varchar(255);
declare v_party_lsdt date;
declare v_party_lsno varchar(255);
declare v_party_olokr varchar(255);
declare v_party_poa varchar(255);
declare v_party_pcra varchar(255);
declare v_party_pcrast varchar(255);
declare v_party_emailflag varchar(10);
declare v_party_emailid   varchar(100);

-- Bill record variable
declare v_bill_kno varchar(10);
declare v_bill_lsno varchar(10);
declare v_bill_bfdt date;
declare v_bill_btdt date;
declare v_bill_lamt double;
declare v_bill_lout double;
declare v_bill_lstxr double;
declare v_bill_lstxa double;
declare v_bill_lcp double;
declare v_bill_ladv double;
declare v_bill_lpyba double;
declare v_bill_lrno varchar(255);
declare v_bill_lrctn varchar(10);
declare v_bill_lrctd date;
declare v_bill_bflag varchar(1);
declare v_bill_emailflag varchar(10);

 


-- batch count
declare v_batch_count int default 0;


declare cur_party cursor for select * from party_view;  -- view will be dynamically created for active and having due date this month-year

DECLARE CONTINUE HANDLER FOR NOT FOUND SET finished = TRUE;
-- DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK;
insert into lok_log_debug values(null,'auto_bill_gen',54,'Proc Called',sysdate());
commit;
START transaction;

drop view if exists party_view;
insert into lok_log_debug values(null,'auto_bill_gen',55,'drop view',sysdate());
set @queryParty = concat('CREATE VIEW party_view as select t.KNO,t.LOKR,t.lpa,t.lrdd,t.LRND,t.LRNO,t.LSDT,t.LSNO,t.OLOKR,t.POA,t.PCRA,t.PCRAST,t.SENDEMAIL1,t.EMAILID 
						from partyrecord t where ifnull(upper(t.RELS),''X'') <> ''R'' and ifnull(upper(t.STPBL),''X'') <> ''Y'' and year(t.LRDD) = ',
                        dueYear,' and month(t.LRDD) = ',dueMonth);
                        
insert into lok_log_debug values(null,'auto_bill_gen',59,@queryParty,sysdate());
-- select '1';
PREPARE stmt from @queryParty; 
EXECUTE stmt; 
DEALLOCATE PREPARE stmt; 
insert into lok_log_debug values(null,'auto_bill_gen',65,@queryParty,sysdate());
OPEN cur_party; 
gen_bill: LOOP
FETCH cur_party INTO v_party_kno,v_party_lokr,v_party_lpa,v_party_lrdd,v_party_lrnd,v_party_lrno,v_party_lsdt,v_party_lsno,v_party_olokr,v_party_poa,v_party_pcra,v_party_pcrast,v_party_emailflag,v_party_emailid;
	IF finished THEN
		LEAVE gen_bill;
    END IF;
	-- Bill generation for each key
    -- set the kno from key kno
    set v_bill_kno = v_party_kno;
    
    -- set the lsno from key lsno
    set v_bill_lsno = v_party_lsno;
    
    -- set the bfdt to the due date
    set v_bill_bfdt = v_party_lrdd;
    
    -- set the btdt to the due date+1 year
    set v_bill_btdt = DATE_ADD(v_party_lrdd, INTERVAL 1 YEAR);
    
    -- copy the btdt to the key lrdd
    set v_party_lrdd = DATE_SUB(  v_bill_btdt,INTERVAL 1 DAY);
    
    -- set the lrno of bill with key lrno
    set v_bill_lrno = v_party_lrno;
    
    -- set the locker rent to lokr
    set v_bill_lamt = v_party_lokr;
    
    -- add the outstanding amount to the lout
    set v_bill_lout = v_party_poa;
    
    -- clear the lout from key details
    set v_party_poa = 0;
    
    -- take the service tax, from the constant
    set v_bill_lstxr = const_service_tax;
    
    -- calculate the total current year payment lcp
    set v_bill_lcp = v_bill_lamt + v_bill_lout;
    
    -- calculate the service tax value
    set v_bill_lstxa = ((v_bill_lcp*10.3)/100);
    
    -- get the advance payment from the key alongwith service tax
    -- pcra+pcrast into ladv
    set v_bill_ladv = ifnull(v_party_pcra,0) + ifnull(v_party_pcrast,0);
    
    -- calculate the payable amount lpyba = lcp - ladv
    set v_bill_lpyba = v_bill_lcp+v_bill_lstxa - v_bill_ladv;
    
    -- email flag, if email is to be sent
    set v_bill_emailflag = v_party_emailflag;
    
    insert into lok_log_debug values(null,'auto_bill_gen',116,concat('key no -',v_party_kno),sysdate());
    -- if overpaid, or balanced out
    if v_bill_lpyba <= 0.0 then
	insert into lok_log_debug values(null,'auto_bill_gen',119,concat('lpyba - ',v_bill_lpyba),sysdate());	
        -- set the bflag to T
        set v_bill_bflag = '*';
        
        -- extra paid, so set the key with the adv ladv as ABS(lpyba)
        set v_party_pcra = ABS(v_bill_lpyba);
        
        -- already paid, so no payable amount
        set v_bill_lpyba = 0.0;
        
	-- else (underpaid)
    else 
    insert into lok_log_debug values(null,'auto_bill_gen',131,concat('lpyba + ',v_bill_lpyba),sysdate());	
        -- set the bflag to false
        set v_bill_bflag = '';
        
	end if;
	
    -- Generate new bill number
    set @bill_seq = 0;
	set @bill_init = "";
	set @new_bno = "";
    
	select t.last_seq,t.object_intial into @bill_seq,@bill_init from lok_master_seq t where t.object_type = 'billrecord';
	set @bill_seq = @bill_seq+1;


	set @new_bno = concat(@bill_init, @bill_seq);
-- update the last seq
	update lok_master_seq set last_seq = @bill_seq where object_type = 'billrecord';
    
    -- insert into bill table
    insert into billrecord (
			`BNO`,
			`BDT`,
			`BFDT`,
			`BTDT`,
			`KNO`,
			`LSNO`,
			`LAMT`,
			`LOUT`,
			`LCP`,
			`LADV`,
			`LSTXR`,
			`LSTXA`,
			`LRNO`,
			`LPYBA`,
			`BFLG`,
            `REMDA1`,
            `REMDA2`,
            `REMDA3`,
            `REMDA4`,
            `REMDA5`,
            `REMDA6`,
            `SENDEMAIL`
			)
			VALUES(
            @new_bno,
			sysdate(),          -- BDT
			v_bill_bfdt,       -- BFDT
			v_bill_BTDT,       -- BTDT
			v_bill_KNO,        -- KNO
			v_bill_LSNO,       -- LSNO
			v_bill_LAMT,       -- LAMT
			v_bill_LOUT,       -- LOUT
			v_bill_LCP,        -- LCP
			v_bill_LADV,       -- LADV
			v_bill_LSTXR,      -- LSTXR
			v_bill_LSTXA,      -- LSTXA
			v_bill_LRNO,       -- LRNO
			v_bill_LPYBA,      -- LPYBA
			v_bill_BFLAG,      -- BFLG
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            v_bill_emailflag
			);
    
    -- update the key details with lrdd, pcra, poa
    update partyrecord set  lrdd = v_party_lrdd,
							pcra = v_party_pcra,
							poa  = v_party_poa,
                            pcrast = 0
						where kno = v_party_kno and lsno = v_party_lsno;
    
    -- Make an entry to the emailoutbound table, if email is to be sent
    
    insert into lok_log_debug values(null,'auto_bill_gen',180,concat('email flag ',v_bill_emailflag),sysdate());	
    if v_bill_emailflag = 'Y' then
    insert into emailoutbound values (@new_bno,'billrecord');
    insert into lok_log_debug values(null,'insert i log',180,concat('bno ',@new_bno),sysdate());	
    end if;
    
    -- increment batch count by 1
    set v_batch_count  = v_batch_count+1;
    insert into lok_log_debug values(null,'auto_bill_gen',180,concat('About to commit ',v_batch_count),sysdate());	
    if v_batch_count > batch_size
    then 
       commit;
		-- reset batch_count to 0
        set v_batch_count = 0;
	end if;

end LOOP gen_bill; 
close cur_party;
-- commit the leftover items
insert into lok_log_debug values(null,'auto_bill_gen',191,concat('Done for the month ',dueMonth),sysdate());	
commit ;
drop view if exists party_view;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `auto_rem_generation` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `auto_rem_generation`(
IN fromDate varchar(30),   -- month should in digits, 01, 02, 03 etc
IN toDate varchar(30)
)
BEGIN

DECLARE finished INTEGER DEFAULT 0;
declare batch_size int default 10;

-- Bill record variable
declare v_bill_bno varchar(10);
declare v_bill_bfdt date;
declare v_bill_lamt double;
declare v_bill_lout double;
declare v_bill_lpyba double;
declare v_bill_bflag varchar(1);
declare v_bill_remn double;
declare v_bill_premd date;
declare v_bill_remd1 date;
declare v_bill_rmda1 double;
declare v_bill_remd2 date;
declare v_bill_rmda2 double;

declare v_bill_remd3 date;
declare v_bill_rmda3 double;

declare v_bill_remd4 date;
declare v_bill_rmda4 double;

declare v_bill_remd5 date;
declare v_bill_rmda5 double;

declare v_bill_remd6 date;
declare v_bill_rmda6 double;

-- amount to be recharged
declare v_bill_rem_amount double;

-- batch count
declare v_batch_count int default 0;


declare cur_unpaidbill cursor for select * from unpaidbill_view;  -- view will be dynamically created for unpaid and having fromdate in the given raise, with less than 6 reminders

DECLARE CONTINUE HANDLER FOR NOT FOUND SET finished = TRUE;

START transaction;

drop view if exists unpaidbill_view;
/*
set @queryUnpaidBills = concat('CREATE VIEW unpaidbill_view as select t.BNO,t.BFDT,t.LAMT,t.LOUT,t.LPYBA,t.PREMD,t.REMN,t.REMD1,t.RMDA1,t.REMD2,t.RMDA2,t.REMD3,t.RMDA3,t.REMD4,t.RMDA4,t.REMD5,t.RMDA5,t.REMD6,t.RMDA6 
						from billrecord t where ifnull(t.REMN,-1) < 6 and ifnull(t.BFLG,''X'') <> ''*'' and date(t.BFDT) > date(''',
                        fromDate,''') and date(t.BFDT) <= date( ''',toDate,''')'); */

set @queryUnpaidBills = concat('CREATE VIEW unpaidbill_view as select t.BNO,t.BFDT,t.LAMT,t.LOUT,t.LPYBA,t.REMN 
						from billrecord t where ifnull(t.REMN,-1) < 6 and ifnull(t.BFLG,''X'') <> ''*'' and date(t.BFDT) > date(''',
                        fromDate,''') and date(t.BFDT) <= date( ''',toDate,''')');

PREPARE stmt from @queryUnpaidBills; 
EXECUTE stmt; 
DEALLOCATE PREPARE stmt; 

OPEN cur_unpaidbill; 
gen_rem: LOOP
-- FETCH cur_unpaidbill INTO v_bill_bno,v_bill_bfdt,v_bill_lamt,v_bill_lout,v_bill_lpyba,v_bill_premd,v_bill_remn,v_bill_remd1,v_bill_rmda1,v_bill_remd2,v_bill_rmda2,v_bill_remd3,v_bill_rmda3,v_bill_remd4,v_bill_rmda4,v_bill_remd5,v_bill_rmda5,v_bill_remd6,v_bill_rmda6;
FETCH cur_unpaidbill INTO v_bill_bno,v_bill_bfdt,v_bill_lamt,v_bill_lout,v_bill_lpyba,v_bill_remn;
	
    -- to support old data, 0 the null values, required for comparison
    set v_bill_lpyba = ifnull(v_bill_lpyba,0);
    
    -- select if(v_bill_lpyba is null, 0,v_bill_lpyba) as v_bill_lpyba;
    
   -- select if(v_bill_lamt is null, 0,v_bill_lamt) as v_bill_lamt;
    -- select if(v_bill_lout is null, 0,v_bill_lout) as v_bill_lout;
    
   -- select v_bill_bno;
    IF finished THEN
		LEAVE gen_rem;
    END IF;
    
    -- if no reminder is sent, send a prereminder
   
    if ifnull(v_bill_remn,-1) = -1 then 
      
		  set  v_bill_remn = 0;
		  
		  -- send prereminder
		  
		UPDATE billrecord 
			SET 
				remn = v_bill_remn,
				premd = SYSDATE()
		WHERE
				bno = v_bill_bno;
	
	else 
      	  -- send reminder
		  set  v_bill_remn = v_bill_remn+1;
        
         -- Update the bill details with reminder date and amount
		  -- based on number of reminder, the corresponding field is updated
          -- TODO, update total amount to be payable by adding pyba and v_bill_rem_amount
		  if v_bill_remn =1 then
		    
            -- get date from reminder 1 column
            SELECT reminder1
		 INTO v_bill_rem_amount FROM latefeeslab t
		WHERE t.fromamount <= v_bill_lpyba
        AND t.toamount >= v_bill_lpyba
		LIMIT 1;
        
			update billrecord
			 set  remn = v_bill_remn,
				  remd1 = sysdate(),
				  rmda1 = v_bill_rem_amount
			where bno = v_bill_bno; 
			
		  elseif v_bill_remn = 2 then
			  
               -- get date from reminder 2 column
            SELECT reminder2
		 INTO v_bill_rem_amount FROM latefeeslab t
		WHERE t.fromamount <= v_bill_lpyba
        AND t.toamount >= v_bill_lpyba
		LIMIT 1;
			update billrecord
			  set  remn = v_bill_remn,
				  remd2 = sysdate(),
				  rmda2 = v_bill_rem_amount
			where bno = v_bill_bno; 
			
		  elseif v_bill_remn = 3 then
			
             -- get date from reminder 3 column
            SELECT reminder3
		 INTO v_bill_rem_amount FROM latefeeslab t
		WHERE t.fromamount <= v_bill_lpyba
        AND t.toamount >= v_bill_lpyba
		LIMIT 1;
			update billrecord
			  set  remn = v_bill_remn,
				  remd3 = sysdate(),
				  rmda3 = v_bill_rem_amount
			where bno = v_bill_bno; 
			
		  elseif v_bill_remn = 4 then
			 
              -- get date from reminder 4 column
            SELECT reminder4
		 INTO v_bill_rem_amount FROM latefeeslab t
		WHERE t.fromamount <= v_bill_lpyba
        AND t.toamount >= v_bill_lpyba
		LIMIT 1;
			 update billrecord
			  set  remn = v_bill_remn,
				  remd4 = sysdate(),
				  rmda4 = v_bill_rem_amount
			where bno = v_bill_bno; 
			
		  elseif v_bill_remn = 5 then
			 
             
              -- get date from reminder 5 column
            SELECT reminder5
		 INTO v_bill_rem_amount FROM latefeeslab t
		WHERE t.fromamount <= v_bill_lpyba
        AND t.toamount >= v_bill_lpyba
		LIMIT 1;
			 update billrecord
			  set  remn = v_bill_remn,
				  remd5 = sysdate(),
				  rmda5 = v_bill_rem_amount
			where bno = v_bill_bno;
			
		  elseif v_bill_remn = 6 then
			 
              -- get date from reminder 6 column
            SELECT reminder6
		 INTO v_bill_rem_amount FROM latefeeslab t
		WHERE t.fromamount <= v_bill_lpyba
        AND t.toamount >= v_bill_lpyba
		LIMIT 1;
			 update billrecord
			  set  remn = v_bill_remn,
				  remd6 = sysdate(),
				  rmda6 = v_bill_rem_amount
			where bno = v_bill_bno;
			
		  end if;
        
    end if;
    
   -- insert into emailoutbound values (v_bill_bno,'reminder');
    
	If v_batch_count > batch_size
    then 
       -- commit;
		-- reset batch_count to 0
        set v_batch_count = 0;
	end if;

end LOOP gen_rem; 
close cur_unpaidbill;
    
drop view if exists unpaidbill_view;
-- commit;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-02 12:28:55
