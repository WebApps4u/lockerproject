<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html>
<head>
<title>Bill Details</title>
</head>
<body>
	<jsp:include page="/jsp/mainmenu.jsp"></jsp:include>
	<div style="clear: both;"></div>
	<div id="msg" class="nomsg"></div>

	<div style="clear: both;"></div>
	<div id='BookingDetails' class="main_content">
	<!--  Page Name -->
	    <div class="breadcrums">
	    <span itemprop="title">
	    ${(param.id==''||param.id==null) ?'Create New Bill':(param.id=='EXISTING'?'Modify Existing Bill':param.id) }
	    </span>
	    </div>
		<form id="frm_billDetails" autocomplete="on">
		<div >
		
			<table id ="billDetails">
				<tr>
					<td class="displayTextLbl">Bill no</td>
					<td><input name="BNO" size="6" placeholder="NEW" type="text" ${param.id!='EXISTING'?"readonly":'' }
						onkeydown="javascript:clickOnEnter(event,'getDetails')" />
						<c:if test="${param.id=='EXISTING' }">	
						<input
						type="button" id='getDetails' value="Get Details">
					</c:if>	</td>
					<!-- 	<input
						type="button" id='getNewBillNo' value="Create New"></td> -->
					<td class="displayTextLbl">Bill Date</td>
					<td><input type="date" name="BDT"></td>

					<td class="displayTextLbl">Rent</td>
					<td><input type="date" name="BFDT"  required="required" id="from" onchange="setToDate()"></td>
					<td class="displayTextLbl">TO</td>
					<td><input type="date" name="BTDT" id="to" readonly="readonly"></td>
					<td class="displayTextLbl">Bill Status</td>
					<td ><input type=text name="BFLG"></td>
				</tr>
				<tr>
					<td class="displayTextLbl">Key no</td>
					<td><input name="KNO" size="6" type="text" 
					onkeydown="javascript:clickOnEnter(event,'getKeyDetails')"
					/>
					<input
						type="button" id='getKeyDetails' value="Get Details"></td>
					<td>Locker no</td>
					<td><input disabled="disabled" name="LNO" size="6" type="text" /></td>
					<td>Booking no</td>
					<td><input name="LSNO" size="6" type="text" /></td>
					<td>Lease date</td>
					<td><input disabled="disabled" type="date" name="LSDT" /></td>
					<td class="displayTextLbl">Receipt#</td>
					<td ><input type=text name="LRCTN" readonly=""></td>
				</tr>
			</table>

			<!--  Contact details -->
			<div class="sep_section"></div>
			<table id="contactDetails">

				<tr>
					<th>Contact Details</th>
				</tr>
				<tr>

					<td><select name="PNM1">
							<option value="MR">Mr</option>
							<option value="MRS">Mrs</option>
					</select> <input name="PNM2" type="text" /> <input name="PNM3" type="text" /></td>

				</tr>
				<tr>

					<td><input name="PNM4" size="40" type="text" /></td>

					
				</tr>
				<tr>

					<td><input name="PNM5" size="40" type="text" /></td>


					
				</tr>
				<tr>

					<td><input type="email" name="EMAILID" />
					
					</td>
				</tr>
				<tr>
	
					<td><input type="text" name="PHN" /></td>

				</tr>

				<tr>
	
					<td class="tdAddress" ><input name="PAD1" type="text" />
						<input name="PAD2" type="text" /> <br><input name="PAD3" type="text" />
						<input name="PAD4" type="text" /></td>
				</tr>
			</table>
			<!--  End of Contact Details -->
			<div class="sep_section"></div>
			<!--  Particular Section -->
			<div id="particulars">
			<table>
				<tr>
					<th>Particulars</th>
					<th>Amount</th>
				</tr>
				<tr>
					<td class="displayTextLbl">Locker Rent</td>
					<td><input type="number" step="0.01" name="LAMT"></td>
				</tr>
				
				<tr>

					<td class="displayTextLbl">Service Tax<input type="number" step="0.01" name="LSTXR" value="14">%</td>
					<td><input type="number" step="0.01" name="LSTXA" readonly="readonly"></td>
				</tr>
				<tr>

					<td class="displayTextLbl">Current Year Payment</td>
					<td><input type="number" step="0.01" name="LCP"></td>

				</tr>
				<tr>
					<td class="displayTextLbl">OutStanding Amount</td>
					<td><input type="number" step="0.01" name="LOUT"></td>
				</tr>
				<tr>
					<td class="displayTextLbl">Amount Paid</td>
					<td><input type="number" step="0.01" name="LADV"></td>
				</tr>
				<tr>
					<!--  This is auto calculated, both frontend and backend -->
					<td class="displayTextLbl">Amount Payable</td>
					<td><input type="number" step="0.01" name="LPYBA"
						readonly="readonly"></td>
				</tr>
				<tr>
				<td class="displayTextLbl">Send Email (Y/N)</td>
				<td><input type="text" size=1 name="SENDEMAIL" pattern="[Y,N]{1}"></td></tr>
			</table>
			</div>
			<div class="fixed_buttons">
				<input type="button" name="submitNewBill" id="submitNewBill"
					value="" class="update"> <input type="reset"
					name="cleartext" value="" class="clear">
			</div>
		</div>
		</form>
	</div>
	<script type="text/javascript">
		//equivalent of $(document).ready(function(){...
		$(function() {
			
			//set Bill date as sysdate if it is not already set
			if($('input[name=BDT]').val()==""){
				$('input[name=BDT]').val($.datepicker.formatDate('yy-mm-dd',new Date()));
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

			function getDetails(billnum) {
				//get ajax call
				$.get(
						'/Locker_Financial_Society/rest/lockerservice/billdetails/'
								+ billnum // rest api
				// billnum for which record is to be fetched		
				).done(function(data) { // data is returned from the server

					//populate fields with the recieved data
					populateForm($('form[id=frm_billDetails]'), data);

					//disable the contact details
					disableElements($('#contactDetails'));

				}).fail(function(error) {
					//log error to console
					console.log(error);
				})
			}

			//call the rest api to get details for the given key num
			$('#getDetails').on('click', function() {

				var billnum = $('input[name="BNO"]').val();

				getDetails(billnum);
			})
			
			function getKeyDetails(keynum) {
				//get ajax call
				$.get(
						'/Locker_Financial_Society/rest/lockerservice/keydetails/'
								+ keynum // rest api
				// key for which record is to be fetched		
				).done(function(data) { // data is returned from the server

					//populate fields with the recieved data
					populateForm($('form[id=frm_billDetails]'), data);
					disableElements($('#contactDetails'));
					populateParticulars(data.LOKR,data.POA, data.LPA);
					
					//fill bill from date. This should be the 
					$('#from').val(data.LRDD);
					setToDate();
				}).fail(function(error) {
					//log error to console
					console.log(error);
				})
			}

			//call the rest api to get details for the given key num
			$('#getKeyDetails').on('click', function() {

				var keynum = $('input[name="KNO"]').val();

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

			$('#submitNewBill')
					.on(
							'click',
							function(e) {

								e.preventDefault();
								$
										.ajax({
											type : "POST",
											url : "/Locker_Financial_Society/rest/lockerservice/billdetails/",
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
													
													$('input[name=BNO]').val(data.obj);
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
											data : ConvertFormToJSON($('form[id=frm_billDetails]'))
										});

							})
							
				//on change of any input inside particulars, invoke calculateTotal
			$('#particulars input').on('change', function(){
				
				//recalculate total
				calculateTotal();
			})

		});
		
		
		//populate the particulars section with the data
		//need to get
		//1st - Locker rent
		//2nd - Outstanding
		//3rd - Advance
	    function populateParticulars(rrnt, rpout, radvp){
			
			//replace the value 
			//locker rent 
			$('input[name="LAMT"]').val(rrnt);   //locker rent
			$('input[name="LOUT"]').val(rpout); //outstanding balance
			$('input[name="LADV"]').val(radvp); //advance
			
			//calculate total
			calculateTotal();
		}
		
		//calculate all the sections
		function calculateTotal(){
			
			// get all the particulars amounts
			var lockerRent = parseFloat($('input[name="LAMT"]').val()) || 0;    //adding pipe to treat empty string as 0
			var advancePay = parseFloat($('input[name="LADV"]').val()) || 0;
			var outstanding = parseFloat($('input[name="LOUT"]').val()) || 0;
			
			var serviceTax = parseFloat($('input[name="LSTXR"]').val()) || 0;
			
			
			var currentYrAmount = lockerRent;
							 	
			//only this place is required to round off
			var serviceTaxAmount = roundOff(((currentYrAmount* serviceTax)/100));
			
			//set the tax calculated
			$('input[name="LSTXA"]').val(serviceTaxAmount);
			
			currentYrAmount = serviceTaxAmount+currentYrAmount;
			//set the total
			$('input[name="LCP"]').val(currentYrAmount);
			
			//TODO
			//get the amount paid
			var amountPaid = parseFloat($('input[name="LADV"]').val()) || 0;
			
			//set the outstanding or advance amount for the key
			//if ramt > totalAmount, advance, else outstanding
			$('input[name="LPYBA"]').val(subtract(currentYrAmount+outstanding,amountPaid));
		}
		
		//Sets To date, which should 1 day less than the From date
		function setToDate(){
			var fromDate = $('#from').val();
			
			//if no from date, then do nothing
			if(!fromDate){
				return;
			}
			
			var t = new Date(fromDate);
			t.setFullYear(t.getFullYear()+1);
			t.setDate(t.getDate()-1);
			
			$('#to').val($.datepicker.formatDate('yy-mm-dd',t));
		}
	</script>

	<div style="clear: both;"></div>
	<jsp:include page="/jsp/Footer.jsp"></jsp:include>
</body>
</html>