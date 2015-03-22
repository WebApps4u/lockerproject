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
			<div class="row">
				<div class="cell"><a href="#" name="currentyear">Current Year outstanding</a></div>
				<div class="cell"><a href="#" name="outstandingbills">Outstanding bills</a></div>
			</div>
			<div class="heading">
				<div class="cell">Customer Reports</div>
			</div>
			<div class="row">
				<div class="cell"><a href="#">Outstanding on date</a></div>
			</div>
			<div class="row">
				<div class="cell"><a href="#">Outstanding Yearly</a></div>
			</div>

		</div>

	</div>
</form>
	<!--  Report starts below  -->
	<div id="billReport">

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
		function getReport(reqParam){
			$.get(
					'/Locker_Financial_Society/rest/reports/bills?'
							+ reqParam // rest api
			// option which is clicked	alongwith values	
			).done(function(data) { // data is returned from the server

				populateBillDetails(data);
				
				//populateParticulars(data);
			}).fail(function(error) {
				//log error to console
				console.log(error);
			})

		}
		
		
		// saved search capturing
		$('form[name="frm_reportoptions"] a').on('click',function(){
			
			//create request parameter
			var reqParam = 'type=saved&name='+this.name;
			getReport(reqParam);
		})
		
	</script>

	<div style="clear: both;"></div>
	<jsp:include page="/jsp/Footer.jsp"></jsp:include>
</body>
</html>