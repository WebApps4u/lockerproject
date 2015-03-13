CREATE DEFINER=`root`@`localhost` PROCEDURE `auto_bill_generation`(
IN dueMonth varchar(2),   -- month should in digits, 01, 02, 03 etc
IN dueYear varchar(4)
)
BEGIN

DECLARE finished INTEGER DEFAULT 0;
declare batch_size int default 10;

-- TODO this will be moved to configurable section
declare const_service_tax double default 12.6;

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
set @queryParty = concat('CREATE VIEW party_view as select t.KNO,t.LOKR,t.lpa,t.lrdd,t.LRND,t.LRNO,t.LSDT,t.LSNO,t.OLOKR,t.POA,t.PCRA,t.PCRAST 
						from partyrecord t where upper(t.RELS) <> ''R'' and upper(t.STPBL) <> ''Y'' and year(t.LRDD) = ',
                        dueYear,' and month(t.LRDD) = ',dueMonth);
                        
insert into lok_log_debug values(null,'auto_bill_gen',59,@queryParty,sysdate());
-- select '1';
PREPARE stmt from @queryParty; 
EXECUTE stmt; 
DEALLOCATE PREPARE stmt; 
insert into lok_log_debug values(null,'auto_bill_gen',65,@queryParty,sysdate());
OPEN cur_party; 
gen_bill: LOOP
FETCH cur_party INTO v_party_kno,v_party_lokr,v_party_lpa,v_party_lrdd,v_party_lrnd,v_party_lrno,v_party_lsdt,v_party_lsno,v_party_olokr,v_party_poa,v_party_pcra,v_party_pcrast;
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
    set v_party_lrdd = v_bill_btdt;
    
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
    set v_bill_ladv = v_party_pcra + v_party_pcrast;
    
    -- calculate the payable amount lpyba = lcp - ladv
    set v_bill_lpyba = v_bill_lcp - v_bill_ladv;
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

    -- insert into bill table
    insert into billrecord (
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
            `REMDA6`
			)
			VALUES(
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
            0.0
			);
    
    -- update the key details with lrdd, pcra, poa
    update partyrecord set  lrdd = v_party_lrdd,
							pcra = v_party_pcra,
							poa  = v_party_poa,
                            pcrast = 0
						where kno = v_party_kno and lsno = v_party_lsno;
    
    
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

END