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
</form>
</div>
<script type="text/javascript">

$(function() {
	
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