<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Security Deposit Receipt</title>
</head>
<body>
	<jsp:include page="/jsp/mainmenu.jsp"></jsp:include>
	<div style="clear: both;"></div>

	<div id="msg" class="nomsg"></div>

	<div style="clear: both;"></div>

	<!--  Key details -->

	<div class="sep_section"></div>
	<div id="SDReceiptDetails" class="main_content">
<!--  Page Name -->
	    <div class="breadcrums">
	    <span itemprop="title">
	    ${(param.id==''||param.id==null) ?'Create New SD Receipt':(param.id=='EXISTING'?'Modify Existing SD Receipt':param.id) }
	    </span>
	    </div>
		<form id="frm_sdreceiptDetails">

			<div id="keyDetails">
				<table>
					<tr>
						<td class="displayTextLbl">SD Receipt Number</td>
						<td><input name="SRCN" placeholder="NEW"  size=6
						onkeydown="clickOnEnter(event,'getDetails')" />
							<input type="button" id='getDetails' value="Go"></td>

						<td class="displayTextLbl">Date</td>
						<td><input type="date" name='SRCD' value="" /></td>
					</tr>

				</table>
				<table>
					<tr>
						<td class="displayTextLbl">Key no</td>
						<td><input name="SKNO" size="6" type="text"
							onkeydown="clickOnEnter(event,'getKeyDetails')" />
							<input type="button" id='getKeyDetails' value="Go"></td>
						<td class="displayTextLbl">Locker no</td>
						<td><input readonly="readonly" name="SLSNO" size="6"
							type="text" /></td>

					</tr>
					</table>
				<table id="contactDetails">
					<tr>
						<th>Contact Details</th>

					</tr>
					<tr>
						<!-- Ist Name -->
						<td><select readonly="readonly" name="PNM1">
								<option value="MR">Mr</option>
								<option value="MRS">Mrs</option>
						</select> <input readonly="readonly" name="PNM2" type="text" /> <input
							readonly="readonly" name="PNM3" type="text" /></td>

					</tr>
					<tr>

						<td><input readonly="readonly" name="PNM4" size="40"
							type="text" /></td>

					</tr>
					<tr>

						<td><input readonly="readonly" name="PNM5" size="40"
							type="text" /></td>

					</tr>


					<tr>
						<td><input readonly="readonly" type="email" name="EMAILID"
							 /></td>
					</tr>
					<tr>
						<td><input readonly="readonly" type="text" name="PHN" /></td>

					</tr>

					<tr>
						<td class="tdAddress" colspan=5><input readonly="readonly"
							name="PAD1" type="text" /> <input readonly="readonly"
							name="PAD2" type="text" />
	                    <br>

						<input readonly="readonly" name="PAD3" type="text" /> <input
							name="PAD4" type="text" readonly="readonly" /></td>
					</tr>

				</table>

			<!-- Particulars details -->
			<div id="particulars">
				<table>
					<tr>
						<td class="displayTextLbl">SD Amount</td>
						<td><input type="number" required value="0" step="0.01" name="SRCMT"
							 /></td>
					</tr>
					<tr></tr>
					<tr></tr>
						<tr>
						<td class="displayTextLbl">Amount Paid</td>
						<td><input name="SCAMT" type="number" required value="0" step="0.01" value="0">
					</tr>
					
				</table>


				<!--  Payment section -->
				<div id="paymentmethod">
					<label>Payment Methods</label>
					<section class="tabs">
						<input id="tab-1" type="radio" name="SPTYP" class="tab-selector-1"
							checked="checked" value="CASH" /> <label for="tab-1"
							class="tab-label-1">Cash</label> <input id="tab-2" type="radio"
							name="SPTYP" class="tab-selector-2" value="Draft" /> <label
							for="tab-2" class="tab-label-2">Draft</label> <input id="tab-3"
							type="radio" name="SPTYP" class="tab-selector-3" value="Cheque" />
						<label for="tab-3" class="tab-label-3">Cheque</label> <input
							id="tab-4" type="radio" name="SPTYP" class="tab-selector-4"
							value="Online Both" /> <label for="tab-4" class="tab-label-4">Online
							Both</label>

						<div class="clear-shadow"></div>

						<div class="content">
							<div class="content-1">
								<p>Amount Paid</p>
								
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



			</div>
			<div class="floating_buttons">
				<input type="button" name="submitNewSDRcpt" id="submitNewSDRcpt"
					value="" class="send"> <input type="reset" name="cleartext"
					value="" class="clear">
			</div>

			<div class="sep_section"></div>

		</form>
	</div>

	<div style="clear: both;"></div>
	<jsp:include page="/jsp/Footer.jsp"></jsp:include>
	
	<script type="text/javascript">
		//equivalent of $(document).ready(function(){...
		$(function() {
			
			//set Bill date as sysdate if it is not already set
			if($('input[name=SRCD]').val()==""){
				$('input[name=SRCD]').val($.datepicker.formatDate('yy-mm-dd',new Date()));
			}
			
			function disableElements(el) {
				for (var i = 0; i < el.length; i++) {
					el[i].disabled = true;

					disableElements(el[i].children);
				}
			}

			//generic function to populate form data 
			//TODO need to be moved to the generic js file
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
						case "date":
						case "email":
						case "number":
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

			function getDetails(sdnum) {
				//get ajax call
				$.get(
						'/Locker_Financial_Society/rest/lockerservice/sdreceipt/'
								+ sdnum // rest api
				// billnum for which record is to be fetched		
				).done(function(data) { // data is returned from the server

					//populate fields with the recieved data
					populateForm($('form[id=frm_sdreceiptDetails]'), data);

					//disable the contact details
					disableElements($('#contactDetails'));

				}).fail(function(error) {
					//log error to console
					console.log(error);
				})
			}

			//call the rest api to get details for the given key num
			$('#getDetails').on('click', function() {

				var sdnum = $('input[name="SRCN"]').val();

				getDetails(sdnum);
			})
			
			function getKeyDetails(keynum) {
				//get ajax call
				$.get(
						'/Locker_Financial_Society/rest/lockerservice/keydetails/'
								+ keynum // rest api
				// key for which record is to be fetched		
				).done(function(data) { // data is returned from the server

					//populate fields with the recieved data
					populateForm($('form[id=frm_sdreceiptDetails]'), data);
					disableElements($('#contactDetails'));
					populateParticulars(data.LSDA,data.LSNO);
					
				}).fail(function(error) {
					//log error to console
					console.log(error);
				})
			}

			//call the rest api to get details for the given key num
			$('#getKeyDetails').on('click', function() {

				var keynum = $('input[name="SKNO"]').val();

				getKeyDetails(keynum);
			})


			//call the rest api to new booking number available
			//Depreciated
		/* 	$('#getNewBillNo').on('click', function() {
				//get ajax call
				$.get('/Locker_Financial_Society/rest/autogen/billnumber/'

				).done(function(data) { // data is returned from the server

					//populate Booking number field
					$('input[name="BNO"]').val(data);

				}).fail(function(error) {
					//log error to console
					console.log(error);
				})

			}) */

			$('#submitNewSDRcpt')
					.on(
							'click',
							function(e) {

								e.preventDefault();
								$
										.ajax({
											type : "POST",
											url : "/Locker_Financial_Society/rest/lockerservice/sdreceipt/",
											contentType : "application/json; charset=utf-8",
											dataType : "json",
											success : function(data) {

												//check the status of the response
												if (data.status == "SUCCESS") {
													console
															.log("successfully updated");
													//remove all classes
													$('#msg').removeClass();
													$('#msg').addClass(
															"successmsg");
													$('#msg').html(
															data.successMsg);
													
													$('input[name=SRCN]').val(data.obj);
												} else {
													console
															.log("error in updated updated"
																	+ data.errMsg);
													$('#msg').removeClass();
													$('#msg')
															.addClass("errmsg");
													$('#msg').html(data.errMsg);
												}

											},
											error : function(response,
													ajaxOptions, thrownError) {
												console.log(thrownError)
											},
											data : ConvertFormToJSON($('form[id=frm_sdreceiptDetails]'))
										});

							})
							

		});
		
		
		//populate the particulars section with the data
		//need to get
	// security Deposit
	    function populateParticulars(sdAmount,lockernum){
			
			//replace the value 
			//Security Deposit
			$('input[name="SRCMT"]').val(sdAmount);   //Security Deposit
			$('input[name="SLSNO"]').val(lockernum);   //Locker#
		}
		
		</script>


</body>
</html>