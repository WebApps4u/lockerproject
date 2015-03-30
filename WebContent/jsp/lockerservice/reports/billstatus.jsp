<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bill Status Reports</title>
</head>
<body>
	<jsp:include page="/jsp/mainmenu.jsp"></jsp:include>
	<div style="clear: both;"></div>

	<div id="msg" class="nomsg"></div>

	<div style="clear: both;"></div>

<div class="main_content">
	<!--  Report links starts here -->
	
	
	<form name="frm_reportoptions">
	<div id="reportOptions">

		<div class="table">

			<div class="heading">
				<div class="cell">Saved Reports</div>
			</div>
			<div class="row saved_search">
				<div class="cell"><a href="#" name="currentyear">Current Year outstanding</a></div>
				<div class="cell"><a href="#" name="outstandingbills">Outstanding bills</a></div>
			</div>
			<div class="heading">
				<div class="cell">Customer Reports</div>
			</div>
			<div class="row custom_search">
				<div class="cell"><input type="date" name='from_date' value="" placeholder="select from date"/></div>
				<div class="cell"><input type="date" name='to_date' value="" placeholder="select to date(optional)"/></div>
				<div class="cell"><a href="#" name="asondate">Outstanding As On date</a></div>
				<div class="cell"><a href="#" name="inyear">Outstanding During the year</a></div>
				<div class="cell"><a href="#" name="inperiod">Outstanding During the period</a></div>
				<div class="cell"><a href="#" name="allbills">All bill raised</a></div>
			</div>
			<div class="heading">
				<div class="cell">Download Reports</div>
			</div>
			<div class="row saved_search">
				<div class="cell"><a href="#" name="currentyear_d">Current Year outstanding</a></div>
				<div class="cell"><a href="#" name="outstandingbills_d">Outstanding bills</a></div>
			</div>
			<div class="row custom_search">
				<div class="cell"><input type="date" name='from_date_d' value="" placeholder="select from date"/></div>
				<div class="cell"><input type="date" name='to_date_d' value="" placeholder="select to date(optional)"/></div>
				<div class="cell"><a href="#" name="asondate_d">Outstanding As On date</a></div>
				<div class="cell"><a href="#" name="inyear_d">Outstanding During the year</a></div>
				<div class="cell"><a href="#" name="inperiod_d">Outstanding During the period</a></div>
				<div class="cell"><a href="#" name="allbills_d">All bill raised</a></div>
			</div>

		</div>

	</div>
</form>
	<!--  Report starts below  -->
	<div id="billReport">
		<a href="#" onclick="printData(document.getElementById('billRecords'))">Click to Print</a>
		<table id="billRecords">
			<thead>
				<tr>

					<th data-dynatable-column="BNO">Bill No.</th>
					<th data-dynatable-column="LAMT">Locker Rent</th>
					<th data-dynatable-column="LOUT">Prev Outstanding</th>
					<th data-dynatable-column="LADV">Adv Payment</th>
					<th data-dynatable-column="BFDT">From</th>
					<th data-dynatable-column="BTDT">To</th>
				</tr>
			</thead>
		</table>

	</div>
</div>
	<!--  Script to load different results based on the report asked -->
	<script type="text/javascript">
		//Create dynamic table with all the bill details
		function populateBillDetails(billDetails) {

			var dynatable = $('#billRecords').dynatable({
				dataset : {
					records : billDetails
				}
			}).data('dynatable');

			//to clear old table data and use new data
			dynatable.settings.dataset.originalRecords = billDetails;
			dynatable.process();
		}
		
		//jquery function to invoke the request for the report
		function getReport(type,reqParam){
			$.get(
					'/Locker_Financial_Society/rest/reports/bills/'
							+ type+"?"+reqParam // rest api
			// option which is clicked	alongwith values	
			).done(function(data) { // data is returned from the server

				populateBillDetails(data.bills);
				
				//populateParticulars(data);
			}).fail(function(error) {
				//log error to console
				console.log(error);
			})

		}
		
		//Function to send a download request, for xls
		//jquery function to invoke the request for the report
		//This must not be a ajax call, since Ajax is not currently supported for Downloads
		function getDownload(type,reqParam){
			var link = document.createElement('a');
			link.href = "/Locker_Financial_Society/rest/downloads/billreport/"+ type+"?"+reqParam;
			link.id = "dummyLink";
			window.location = link.href;
		}

		
		
		// saved search capturing
		$('form[name="frm_reportoptions"] .saved_search a').on('click',function(){
			
			
			//create request parameter
			//remove additional _d from the name
			var reqName = this.name.contains('_d')==true?this.name.slice(0,this.name.indexOf('_')):this.name;
			var reqParam = 'name='+reqName;
		//if the link ends with _d, its a download request
		if(this.name.contains('_d')){
			getDownload("saved",reqParam)
		}
		else{
			getReport("saved",reqParam);
		}
		})
		
		// custom search capturing
		$('form[name="frm_reportoptions"] .custom_search a').on('click',function(){
			
			//use native JS wherever possible
			var fromDate = document.getElementsByName('from_date')[0].value;
			var toDate = document.getElementsByName('to_date')[0].value;
			
			//create request parameter
			var reqName = this.name.contains('_d')==true?this.name.slice(0,this.name.indexOf('_')):this.name;
			
			var reqParam = 'name='+reqName+"&from-date="+fromDate+"&to-date="+toDate;
			
			if(this.name.contains('_d')){
				getDownload("custom",reqParam)
			}
			else{
			getReport("custom",reqParam);
			}
		})
		
	</script>

	<div style="clear: both;"></div>
	<jsp:include page="/jsp/Footer.jsp"></jsp:include>
</body>
</html>