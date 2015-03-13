<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Auto Bill</title>
</head>
<body>
	<jsp:include page="/jsp/mainmenu.jsp"></jsp:include>
	<div style="clear: both;"></div>

	<form id="frm_billgen">

		<div id="selectMonth">

			<table>
				<tr>
					<td><select name="dueMonth" id="dueMonth">
							<option value="01">JAN</option>
							<option value="02">FEB</option>
							<option value="03">MAR</option>
							<option value="04">APR</option>
							<option value="05">MAY</option>
							<option value="06">JUN</option>
							<option value="07">JUL</option>
							<option value="08">AUG</option>
							<option value="09">SEP</option>
							<option value="10">OCT</option>
							<option value="11">NOV</option>
							<option value="12">DEC</option>
					</select></td>
					<td><select name="dueYear" id="dueYear"></select></td>
				</tr>
				<tr>
					<td><input type="button" id='startAutoBill'
						value="Start Auto Bill"></td>
				</tr>
				
			</table>
			<a href="#" id="searchbills">Click here to see generated bills for this month-year</a>
						
				<table id="billRecords">
						<thead>
							<tr>

								<th data-dynatable-column="BNO">Bill No.</th>
								<th data-dynatable-column="LAMT">Locker Rent</th>
								<th data-dynatable-column="LOUT">Prev Outstanding</th>
								<th data-dynatable-column="LADV">Adv Payment</th>
								<th data-dynatable-column="BFDT">From</th>
								<th data-dynatable-column="BTDT">To</th>
								<th data-dynatable-column="LPYBA">Pay Amt</th>
							</tr>
						</thead>
					</table>
		</div>

	</form>

	<script>
	$(function() {	
		//inserts current and next year in the drop down
	    function insertYears() {
			var now = new Date();
			var thisYear = now.getFullYear();
			var nextYear = parseInt(now.getFullYear()) + 1;
			var new_opt_one = document.createElement("option");
			var new_opt_two = document.createElement("option");
			new_opt_one.value = thisYear;
			new_opt_one.innerHTML = thisYear;
			new_opt_two.value = nextYear;
			new_opt_two.innerHTML = nextYear;
			document.getElementById("dueYear").appendChild(new_opt_one);
			document.getElementById("dueYear").appendChild(new_opt_two);

		}
		
		//insert the years
	    insertYears();
		
	  //Create dynamic table with all the bill details
		function populateBillDetails(billDetails) {

			$('#billRecords').dynatable({

				dataset : {
					records : billDetails
				}

			});

		}
	  
		function getDetails(dueMonth,dueYear) {
			//get ajax call
			$.get(
					'/Locker_Financial_Society/rest/lockerservice/bills?dueMonth='
							+ dueMonth // rest api
							+'&dueYear='
							+dueYear
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

			var dueMonth = $('#dueMonth').val();
			var dueYear =  $('#dueYear').val();
			//clearform();
			getDetails(dueMonth,dueYear);
		})
		
		//invokes the bills generation package
		$('#startAutoBill').on('click', function() {

			var dueMonth = $('#dueMonth').val();
			var dueYear =  $('#dueYear').val();
			//clearform();
			$.get(
					'/Locker_Financial_Society/rest/autogen/bills?dueMonth='
							+ dueMonth // rest api
							+'&dueYear='
							+dueYear
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