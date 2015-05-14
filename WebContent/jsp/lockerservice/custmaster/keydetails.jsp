<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Key Details</title>
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
	    ${(param.id==''||param.id==null) ?'Create New Booking':(param.id=='EXISTING'?'Modify Existing Booking':param.id) }
	    </span>
	    </div>
		<form id="frm_keyDetails">
			<table>
				<tr>
					<td>Key no</td>
					<!--  	onkeydown="if (event.keyCode == 13) document.getElementById('getDetails').click()" />-->
					<td><input name="KNO" size="6" type="text"
					
							onkeydown="javascript:clickOnEnter(event,'getDetails')" />
					<!--  Show Get details, only if the page is  -->
					<c:if test="${param.id=='EXISTING' }">	
						<input
						type="button" id='getDetails' value="Get Details">
					</c:if>	
						</td>
					
					<td>Locker no</td>
					<td><input name="LNO" size="6" type="text" /></td>
					<td>R box</td>
					<td><input name="RBOX" maxlength="1" size="1" type="text" />
					</td>
					<td>Lease date</td>
					<td><input type="date" name="LSDT" /></td>
				</tr>
				<tr>
					<td>Booking no</td>
					<td><input name="LSNO" size="6" type="text"
						readonly="readonly" placeholder="NEW"/>
						<!--  
						 <input type="button" id='getNewBookingNo'
						value="Create New"></td>
						-->
					<td>Locker rent</td>
					<td><input name="LOKR" type="number" step="0.01" /></td>
					<td>Old Rent</td>
					<td><input type="number" name="OLOKR" step="0.01" />
					<td>S deposit</td>
					<td><input name="LSDA" type="number" step="0.01" /></td>

				</tr>
				<tr>
					<td>Old Booking no</td>
					<td><input name="LSNO2" size="6" type="text" /></td>
					<td>Rent due date</td>
					<td><input type="date" name="LRDD" /></td>
					<td>Folio no</td>
					<td><input name="FLON" size="6" type="text" /></td>
				</tr>

			</table>
			<div class="sep_section"></div>
			<table>

				<tr>
					<th></th>
					<th>Name</th>
					<th>PP/Aadhar/DL</th>
					<th>Electricity bill</th>
					<th>PAN</th>
					<th>Photograph</th>
				</tr>
				<tr>
					<td>Link Existing Customer <input name="FIRSTCUSTOMER" type="text"></td>
					<!-- Ist Name -->
					<td><select name="PNM1">
							<option value="MR">Mr</option>
							<option value="MRS">Mrs</option>
					</select> <input name="PNM2" type="text" /> <input name="PNM3" type="text" /></td>

					<!--  KYC doc -->
					<td class="tdMiddle"><input name="KYC11" type="text"
						maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC12" type="text"
						maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC13" type="text"
						maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC14" type="text"
						maxlength="1" size="1" /></td>
				</tr>
				<tr>
					<!-- 2nd Name -->
					<td>2nd</td>
					<td><input name="PNM4" size="40" type="text" /></td>

					<!--  KYC doc -->
					<td class="tdMiddle"><input name="KYC21" type="text"
						maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC22" type="text"
						maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC23" type="text"
						maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC24" type="text"
						maxlength="1" size="1" /></td>
				</tr>
				<tr>
					<!-- 3rd Name -->
					<td>3rd</td>
					<td><input name="PNM5" size="40" type="text" /></td>


					<!--  KYC doc -->
					<td class="tdMiddle"><input name="KYC31" type="text"
						maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC32" type="text"
						maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC33" type="text"
						maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC34" type="text"
						maxlength="1" size="1" /></td>
				</tr>
				<tr>
					<!-- Deputy Name -->
					<td>Deputy</td>
					<td><input type="text" size="40" /></td>


					<!--  KYC doc -->
					<td class="tdMiddle"><input name="KYC41" type="text"
						maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC42" type="text"
						maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC43" type="text"
						maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC44" type="text"
						maxlength="1" size="1" /></td>
				</tr>

				<tr>
					<td>E-mail</td>
					<td><input type="email" name="EMAILID" style="width: 392px;" />
					</td>

					<td>Phones</td>
					<td><input type="text" name="PHN" /></td>
					<td></td>
					<td></td>
				</tr>

				<tr>
					<td>Address</td>
					<td class="tdAddress" colspan=5><input name="PAD1" type="text" />
						<input name="PAD2" type="text" /> <input name="PAD3" type="text" />
						<input name="PAD4" type="text" /></td>
				</tr>
			</table>
			<table>

				<tr>
					<td>Outstanding:</td>
					<td><input type="number" name="POA" step="0.01"></td>
					<td>Advance Rent</td>
					<td><input type="number" name="PCRA" step="0.01"></td>  <!--  TODO, this is temporary done, it is PCRA -->
					<td>Receipt No.</td>
					<td><input type="text" name="LRNO"></td>
					<td>Receipt Date</td>
					<td><input type="date" name="LRND"></td>

				</tr>
				<tr>
					<td>Remark:</td>
					<td><input type="text" name="REMARKS"></td>
					<td>Service tax</td>
					<td><input type="number" name="PCRAST" step="0.01"></td>
					<td>Release</td>
					<td><input type="text" name="RELS" size="1" /></td>
					<td>Release Date</td>
					<td><input type="date" name="RELSD" /></td>

				</tr>
				<!-- <tr>
					<td>Late fee:</td>
					 <td>   <input type="number" name="latefee" step="0.01"></td>
					<td>Area code</td>
					 <td> <input type="number" name="areacode"></td>
				</tr> -->
			</table>
			<input type="hidden" name="LPA" value="0"> <input
				type="hidden" name="PUCD" value="0">
			<div class="floating_buttons">
				<input type="button" name="submitNewBooking" id="submitNewBooking"
					value="" class="update"> <input type="reset"
					name="cleartext" value="" class="clear">
			</div>
		</form>
	</div>
	<script type="text/javascript">
		//equivalent of $(document).ready(function(){...
		$(function() {

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

			function getDetails(keynum) {
				//get ajax call
				$.get(
						'/Locker_Financial_Society/rest/lockerservice/keydetails/'
								+ keynum // rest api
				// key for which record is to be fetched		
				).done(function(data) { // data is returned from the server

					//populate fields with the recieved data
					populateForm($('form[id=frm_keyDetails]'), data);
				}).fail(function(error) {
					//log error to console
					console.log(error);
				})
			}

			//call the rest api to get details for the given key num
			$('#getDetails').on('click', function() {

				var keynum = $('input[name="KNO"]').val();

				getDetails(keynum);
			})

			//call the rest api to new booking number available
			$('#getNewBookingNo').on('click', function() {
				//get ajax call
				$.get('/Locker_Financial_Society/rest/autogen/bookingnumber/'

				).done(function(data) { // data is returned from the server

					//populate Booking number field
					$('input[name="LSNO"]').val(data);

				}).fail(function(error) {
					//log error to console
					console.log(error);
				})

			})

			//call the rest api to get details for the given key num
			$('#submitNewBooking')
					.on(
							'click',
							function(e) {

								e.preventDefault();
								$
										.ajax({
											type : "POST",
											url : "/Locker_Financial_Society/rest/lockerservice/keydetails/",
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
													
													//TODO, must be dynamic
													$('input[name=LSNO]').val(data.obj);
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
											data : ConvertFormToJSON($('form[id=frm_keyDetails]'))
										});

							})

		});
	</script>
	<div style="clear: both;"></div>
	<jsp:include page="/jsp/Footer.jsp"></jsp:include>


</body>
</html>