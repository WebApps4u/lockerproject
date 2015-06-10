<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Auto Reminder</title>
</head>
<body>
	<jsp:include page="/jsp/mainmenu.jsp"></jsp:include>
	<div style="clear: both;"></div>

<div id='Auto_rem_gen' class="main_content">
<form id="frm_billgen">

<table>
				<tr>
					<td>From Date<input type="date" name="fromDate" id="fromDate"></td>
					<td>To Date<input type="date" name="toDate" id="toDate"></td>
					</tr>
					<tr>
					<td><input type="button" id='startAutoRem'
						value="Start Auto Reminders"></td>
				</tr>
					</table>
					
					<a href="#" id="searchbills">Click here to see generated reminders for this duration</a>
						
				<table id="billRecords">
						<thead>
							<tr>

								<th data-dynatable-column="BNO">Bill#</th>
					<th data-dynatable-column="KNO">Key#</th>
					<th data-dynatable-column="LAMT">Locker Rent</th>
					<th data-dynatable-column="LOUT">Prev Outstanding</th>
					<th data-dynatable-column="LADV">Adv Payment</th>
					<th data-dynatable-column="LPYBA">Payable Amount</th>
					<th data-dynatable-column="BFDT">From</th>
					<th data-dynatable-column="BTDT">To</th>
					<th data-dynatable-column="BFLG">Status</th>
					<th data-dynatable-column="PREMD">PreReminder Date</th>
					<th data-dynatable-column="REMD1">Reminder 1 Date</th>
					<th data-dynatable-column="REMD2">Reminder 2 Date</th>
					<th data-dynatable-column="REMD3">Reminder 3 Date</th>
					<th data-dynatable-column="REMD4">Reminder 4 Date</th>
					<th data-dynatable-column="REMD5">Reminder 5 Date</th>
					<th data-dynatable-column="REMD6">Reminder 6 Date</th>		
							</tr>
						</thead>
					</table>
					
</form>
</div>
<script type="text/javascript">

$(function() {
	
	
	//Create dynamic table with all the bill details
	function populateBillDetails(billDetails) {

		
		var dynatable = $('#billRecords').dynatable({
            dataset: {
                records: billDetails
            }
        }).data('dynatable');

		//to clear old table data and use new data
        dynatable.settings.dataset.originalRecords = billDetails;
        dynatable.process();

	}
  
	function getDetails(fromDate,toDate) {
		//get ajax call
		$.get(
				'/Locker_Financial_Society/rest/lockerservice/reminders?fromDate='
						+ fromDate // rest api
						+'&toDate='
						+toDate
		// billnum for which record is to be fetched		
		).done(function(data) { // data is returned from the server

			populateBillDetails(data.bills);
			
		}).fail(function(error) {
			//log error to console
			console.log(error);
		})
	}
	//call the rest api to get details for the given key num
	$('#searchbills').on('click', function() {

		var fromDate = $('#fromDate').val();
		var toDate =  $('#toDate').val();
		//clearform();
		getDetails(fromDate,toDate);
	})
	
	//invokes the bills generation package
	$('#startAutoRem').on('click', function() {

		var fromDate = $('#fromDate').val();
		var toDate =  $('#toDate').val();
		//clearform();
		$.get(
				'/Locker_Financial_Society/rest/autogen/reminders?fromDate='
						+ fromDate // rest api
						+'&toDate='
						+toDate
		// billnum for which record is to be fetched		
		).done(function() { // data is returned from the server

			console.log("job done");
			
		}).fail(function(error) {
			//log error to console
			console.log(error);
		})
	})
	
});
</script>
</body>
</html>