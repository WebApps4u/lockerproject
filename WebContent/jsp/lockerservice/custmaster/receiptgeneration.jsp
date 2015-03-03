<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Receipt</title>
</head>
<body>
	<jsp:include page="/jsp/mainmenu.jsp"></jsp:include>
	<div style="clear: both;"></div>


	<!--  Key details -->

	<div class="sep_section"></div>
	<div id="ReceiptDetails">

		<form name="frm_receiptDetails">

			<div id="keyDetails">
				<table>
					<tr>
						<td>Receipt Number</td>
						<td>NEW</td>
						<td></td>

						<td>Date</td>
						<td><input type="date" name='RCTD' /></td>
					</tr>

				</table>
				<table>
					<tr>
						<td>Key no</td>
						<td><input name="KNO" size="6" type="text"
							onkeydown="if (event.keyCode == 13) document.getElementById('getDetails').click()" />
							<input type="button" id='getDetails' value="Go"></td>
						<td>Locker no</td>
						<td><input readonly="readonly" name="LNO" size="6"
							type="text" /></td>
						<td>Locker rent</td>
						<td><input readonly="readonly" name="LOKR" type="number"
							step="0.01" /></td>
					</tr>
					<tr>
						<td>Lease date</td>
						<td><input readonly="readonly" type="date" name="LSDT" /></td>
						<td>Booking no</td>
						<td><input readonly="readonly" name="LSNO" size="6"
							type="text" /></td>

					</tr>
				</table>
				<table>
					<tr>
						<th></th>
						<th>Name</th>

					</tr>
					<tr>
						<td>1st</td>
						<!-- Ist Name -->
						<td><select readonly="readonly" name="PNM1">
								<option value="MR">Mr</option>
								<option value="MRS">Mrs</option>
						</select> <input readonly="readonly" name="PNM2" type="text" /> <input
							readonly="readonly" name="PNM3" type="text" /></td>

					</tr>
					<tr>
						<!-- 2nd Name -->
						<td>2nd</td>
						<td><input readonly="readonly" name="PNM4" size="40"
							type="text" /></td>

					</tr>
					<tr>
						<!-- 3rd Name -->
						<td>3rd</td>
						<td><input readonly="readonly" name="PNM5" size="40"
							type="text" /></td>

					</tr>


					<tr>
						<td>E-mail</td>
						<td><input readonly="readonly" type="email" name="EMAILID"
							style="width: 200px;" /></td>
					</tr>
					<tr>
						<td>Phones</td>
						<td><input readonly="readonly" type="text" name="PHN" /></td>

					</tr>

					<tr>
						<td>Address</td>
						<td class="tdAddress" colspan=5><input readonly="readonly"
							name="PAD1" type="text" /> <input readonly="readonly"
							name="PAD2" type="text" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input readonly="readonly" name="PAD3" type="text" /> <input
							name="PAD4" type="text" readonly="readonly" /></td>
					</tr>

				</table>

				<!--  Bill Details -->
				<div id="billDetails">
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

			<!-- Particulars details -->
			<div id="particulars">
				<table>
					<tr>
						<th>Fees Details</th>
						<th>Amount</th>
					</tr>
					<tr>
						<td>Locker Rent</td>
						<td><input type="number" step="0.01" name="RRNT" /></td>
					</tr>
					<tr>
						<td>Less at credit(-)</td>
						<td><input type="number" step="0.01" name="RADVP" /></td>
					</tr>
					<tr>
						<td>Add previous due</td>
						<td><input type="number" step="0.01" name="RPOUT" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td>Interest</td>
						<td><input type="number" step="0.01" name="RINT" /></td>
					</tr>
					<tr>
						<td>Misc charges</td>
						<td><input type="number" step="0.01" name="RINC" /></td>
					</tr>
					<tr>
						<td>Bank charges</td>
						<td><input type="number" step="0.01" name="RBC" /></td>
					</tr>
					<tr>
						<td>Application Fees</td>
						<td><input type="number" step="0.01" name="RAPF" /></td>
					</tr>
					<tr>
						<td>Legal Fees</td>
						<td><input type="number" step="0.01" name="RGLF" /></td>
					</tr>
					<tr>
						<td>Advances</td>
						<td><input type="number" step="0.01" name="RDAV" /></td>
					</tr>
					<tr>
						<td>Access charges</td>
						<td><input type="number" step="0.01" name="RACH" /></td>
					</tr>
					<tr>
						<td>Suspense</td>
						<td><input type="number" step="0.01" name="RSUS" /></td>
					</tr>
					<tr>
						<td>Breaking charges</td>
						<td><input type="number" step="0.01" name="BRKCH" /></td>
					</tr>

					<tr>
						<td>Service charges@<input type="number" step="0.01"
							name="STAXR" />%
						</td>
						<td><input type="number" step="0.01" name="STAXA" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td>Total</td>
						<td><input type="number" step="0.01" name="RGTOT" /></td>
					</tr>

				</table>
			</div>

			<div class="sep_section"></div>

			<!--  Payment section -->
			<div id="paymentmethod">
			    <label>Payment Methods</label>
				<section class="tabs">
					<input id="tab-1" type="radio" name="radio-set"
						class="tab-selector-1" checked="checked" value="CASH" /> <label for="tab-1"
						class="tab-label-1">Cash</label> <input id="tab-2"
						type="radio" name="radio-set" class="tab-selector-2" value="Draft" /> <label
						for="tab-2" class="tab-label-2">Draft</label> <input
						id="tab-3" type="radio" name="radio-set" class="tab-selector-3" value="Cheque"/>
					<label for="tab-3" class="tab-label-3">Cheque</label> <input
						id="tab-4" type="radio" name="radio-set" class="tab-selector-4" value="Online Both"/>
					<label for="tab-4" class="tab-label-4">Online Both</label>

					<div class="clear-shadow"></div>

					<div class="content">
						<div class="content-1">
							<p>Amount Paid</p>
							<input name="RAMT" type="number" step="0.01">
						</div>
						<div class="content-2">
							<p>Some content</p>
						</div>
						<div class="content-3">
							<p>Some content</p>
						</div>
						<div class="content-4">
							<p>Some content</p>
						</div>
					</div>
				</section>

			</div>

		</form>
	</div>

	<script type="text/javascript">
		//equivalent of $(document).ready(function(){...
		$(function() {

			//change date format of all the date fields
			//this will be invoked after each section is populated from the server
			function changeDateFormat($form) {
			}

			//populate key details section
			function populateForm($form, data) {
				// resetForm($form);
				$.each(data, function(key, value) {
					var $ctrl = $form
							.find('[name="' + key.toUpperCase() + '"]');
					if ($ctrl.is('select')) {
						$('option', $ctrl).each(function() {
							if (this.value == value)
								this.selected = true;
						});
					} else if ($ctrl.is('textarea')) {
						$ctrl.val(value);
					} else {
						switch ($ctrl.attr("type")) {
						case "text":
						case "hidden":
						case "email":
						case "number":
						case "date":
							$ctrl.val(value);
							break;
						case "checkbox":
							if (value == '1')
								$ctrl.prop('checked', true);
							else
								$ctrl.prop('checked', false);
							break;
						}
					}
				});
			}

			//Create dynamic table with all the bill details
			function populateBillDetails(billDetails) {

				$('#billRecords').dynatable({

					dataset : {
						records : billDetails
					}

				});

				$('#billRecords tbody').on('click', 'tr', function() {

					$(this).toggleClass('selected');
					$('td', this).toggleClass('selected');
				});
			}

			function getDetails(keynum) {
				//get ajax call
				$.get(
						'/Locker_Financial_Society/rest/lockerservice/unpaidbills/'
								+ keynum // rest api
				// key for which record is to be fetched		
				).done(function(data) { // data is returned from the server

					//populate fields with the recieved data
					populateForm($('div[id=keyDetails]'), data);

					//populateKeyDetails(data);
					populateBillDetails(data.bills);
					//populateParticulars(data);
				}).fail(function(error) {
					//log error to console
					console.log(error);
				})
			}

			//call the rest api to get details for the given key num
			$('#getDetails').on('click', function() {

				var keynum = $('input[name="KNO"]').val();
				//clearform();
				getDetails(keynum);
			})

		});
	</script>

</body>
</html>