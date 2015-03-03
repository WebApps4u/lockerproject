<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<div id='BookingDetails'>
		<form id="frm_billDetails" autocomplete="on">
			<table>
				<tr>
					<td>Bill no</td>
					<td><input name="BNO" size="6" type="text"
						onkeydown="if (event.keyCode == 13) document.getElementById('getDetails').click()" /><input
						type="button" id='getDetails' value="Go"></td>
					<td>Bill Date</td>
					<td><input type="date" name="BDT"></td>
					
					<td>Rent</td>
					<td><input type="date" name="BFDT"></td>
					<td>TO</td>
					<td><input type="date" name="BTDT"></td>
				</tr>
				<tr>
					<td>Key no</td>
					<td><input name="KNO" size="6" type="text" /></td>
					<td>Locker no</td>
					<td><input name="LNO" size="6" type="text" /></td>
					<td>Booking no</td>
					<td><input name="LSNO" size="6" type="text" /></td>
					<td>Lease date</td>
					<td><input type="date" name="LSDT" /></td>
				</tr>
			</table>

			<!--  Contact details -->
			<div class="sep_section"></div>
			<table id="contactDetails">

				<tr>
					<th></th>
					<th>Name</th>
					<th>PP/Aadhar/DL</th>
					<th>Electricity bill</th>
					<th>PAN</th>
					<th>Photograph</th>
				</tr>
				<tr>
					<td>1st</td>
					<!-- Ist Name -->
					<td><select name="PNM1">
							<option value="MR">Mr</option>
							<option value="MRS">Mrs</option>
					</select> <input name="PNM2" type="text" /> <input name="PNM3" type="text" /></td>

					<!--  KYC doc -->
					<td class="tdMiddle"><input name="KYC1" type="text"
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
					<td class="tdMiddle"><input name="KYC2" type="text"
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
					<td class="tdMiddle"><input name="KYC3" type="text"
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
					<td class="tdMiddle"><input name="KYC4" type="text"
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
			<!--  End of Contact Details -->

			<!--  Particular Section -->
			<table>
				<tr>
					<th>Particulars</th>
					<th>Amount</th>
				</tr>
				<tr>
					<td>Locker Rent</td>
					<td><input type="number" step="0.01" name="LAMT"></td>
				</tr>
				<tr>
					<td>OutStanding Amount</td>
					<td><input type="number" step="0.01" name="LOUT"></td>
				</tr>
				<tr>

					<td>Service Tax</td>
					<td><input type="number" step="0.01" name="LSTXR"></td>
				</tr>
				<tr>

					<td>Current Year Payment</td>
					<td><input type="number" step="0.01" name="LCP"></td>

				</tr>
				<tr>
					<td>Amount Paid</td>
					<td><input type="number" step="0.01" name="LADV"></td>
				</tr>
				<tr>
					<!--  This is auto calculated, both frontend and backend -->
					<td>Amount Payable</td>
					<td><input type="number" step="0.01" name="LPYBA"
						readonly="readonly"></td>
				</tr>
			</table>
			<input type="button" name="submitNewBill" id="submitNewBill" value="Update">
			<input type="reset" name="cleartext" value="Clear">
		</form>
	</div>
<script type="text/javascript">
	//equivalent of $(document).ready(function(){...
	$(function() {

		function ConvertFormToJSON(form){
		    var array = $(form).serializeArray();
		    var json = {};
		    
		    $.each(array, function() {
		        json[this.name.toLowerCase()] = this.value || '';
		    });
		    json=JSON.stringify(json);
		    return json;
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
				var $ctrl = $form.find('[name="' + key.toUpperCase() + '"]');
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
		
		$('#submitNewBill').on(
				'click',
				function(e) {
                    
					e.preventDefault();
					$.ajax({
				           type: "POST",
				           url: "/Locker_Financial_Society/rest/lockerservice/billdetails/",
				           contentType: "application/json; charset=utf-8",
				           dataType: "json",
				           success: function (data) {
				        	   
				        	   //check the status of the response
				        	   if(data.status == "SUCCESS"){
									console.log("successfully updated");
									//remove all classes
									$('#msg').removeClass();
									$('#msg').addClass("successmsg");
									$('#msg').html(data.successMsg);
				        	   }
				        	   else{
				        		   console.log("error in updated updated"+data.errMsg);
				        		   $('#msg').removeClass();
									$('#msg').addClass("errmsg");
									$('#msg').html(data.errMsg);
				        	   }
				        	   
				        	   
				           },
				           error : function(response, ajaxOptions, thrownError){
				        	   console.log(thrownError)
				           },
				           data: ConvertFormToJSON($('form[id=frm_billDetails]'))
				       });

				})

	});
</script>

</body>
</html>