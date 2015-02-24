<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/Locker_Financial_Society/css/jquery-ui.css" />

<link rel="stylesheet" type="text/css"
	href="/Locker_Financial_Society/css/jquery-ui.structure.css" />
<link rel="stylesheet" type="text/css" href="/Locker_Financial_Society/css/jquery-ui.theme.css" />

<!--  load jquery prior to the jquery ui which is using it -->
<script src="/Locker_Financial_Society/js/jquery-1.11.1.js" type="text/javascript"></script>
<script src="/Locker_Financial_Society/js/jquery-ui.js" type="text/javascript"></script>
<title>Key Details</title>
<script type="text/javascript">
	
//equivalent of $(document).ready(function(){...
$(function(){
	
	//generic function to populate form data 
	//TODO need to be moved to the generic js file
	function populateForm($form, data)
	{
	   // resetForm($form);
	    $.each(data, function(key, value) {
	        var $ctrl = $form.find('[name="'+key.toUpperCase()+'"]');
	        if ($ctrl.is('select')){
	            $('option', $ctrl).each(function() {
	                if (this.value == value)
	                    this.selected = true;
	            });
	        } else if ($ctrl.is('textarea')) {
	            $ctrl.val(value);
	        } else {
	            switch($ctrl.attr("type")) {
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
	
	function getDetails(keynum){
		//get ajax call
		$.get('/Locker_Financial_Society/rest/lockerservice/keydetails/'+keynum // rest api
									// key for which record is to be fetched		
			)
			.done(function(data){	// data is returned from the server
				
				//populate fields with the recieved data
				populateForm($('form[id=frm_keyDetails]'),data);
			})            
			.fail(function(error){
				//log error to console
				console.log(error);
			})
	}
	
	
	//call the rest api to get details for the given key num
	$('#getDetails').on('click',function(){
		
		var keynum = $('input[name="KNO"]').val();
		
			getDetails(keynum);
		})


});

</script>
</head>
<body>
<jsp:include page="/jsp/mainmenu.jsp"></jsp:include>

<div style="clear: both;"></div>

	<div id='BookingDetails' >
		<form id="frm_keyDetails">
			<table>
				<tr>
					<td>Key no</td><td> <input name="KNO" size="6" type="text" onkeydown="if (event.keyCode == 13) document.getElementById('getDetails').click()"/><input type="button" id='getDetails' value="Go" ></td>
					<td>Locker no</td><td> <input name="LNO" size="6" type="text" /></td>
					<td>R box </td><td><input name="RBOX" maxlength="1" size="1" type="text" /></td>
					<td>Lease date </td><td><input type="date" name="LSDT" /></td>
				</tr>
				<tr>
					<td>Booking no </td><td><input name="LSNO" size="6" type="text" /></td>
					<td>Locker rent </td><td><input name="LOKR" type="number" step="0.01"/></td>
					<td>Old Rent </td><td><input type="number" name="oldrent" step="0.01"/>
					<td>S deposit </td><td><input name="LSDA" type="number" step="0.01"/></td>
					
				</tr>
				<tr>
					<td>Old Booking no </td><td><input name="LSNO2" size="6" type="text" /></td>
					<td>Rent due date </td><td><input type="date" name="LRDD" /></td>
					<td>Folio no      </td><td><input name="FLON" size="6" type="text" /></td>
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
					<td>1st</td>
					<!-- Ist Name -->
					<td><select name="PNM1" >
							<option value="MR">Mr</option>
							<option value="MRS">Mrs</option>
					</select>
					<input name="PNM2" type="text" />
					<input name="PNM3" type="text"/></td>
					
					<!--  KYC doc -->
					<td class="tdMiddle"><input name="KYC1" type="text" maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC12" type="text" maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC13" type="text" maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC14" type="text" maxlength="1" size="1" /></td>
				</tr>
				<tr>
				<!-- 2nd Name -->
					<td>2nd</td>
					<td><input name="PNM4" size="40" type="text" /></td>
									
					<!--  KYC doc -->
					<td class="tdMiddle"><input name="KYC2" type="text" maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC22" type="text" maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC23" type="text" maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC24" type="text" maxlength="1" size="1" /></td>
				</tr>
				<tr>
				<!-- 3rd Name -->
					<td>3rd</td>
					<td><input name="PNM5" size="40" type="text" /></td>
					
									
					<!--  KYC doc -->
					<td class="tdMiddle"><input name="KYC3" type="text" maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC32" type="text" maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC33" type="text" maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC34" type="text" maxlength="1" size="1" /></td>
				</tr>
				<tr>
				<!-- Deputy Name -->
					<td>Deputy</td>
					<td ><input type="text" size="40" /></td>
					
									
					<!--  KYC doc -->
					<td class="tdMiddle"><input name="KYC4" type="text" maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC42" type="text" maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC43" type="text" maxlength="1" size="1" /></td>
					<td class="tdMiddle"><input name="KYC44" type="text" maxlength="1" size="1" /></td>
				</tr>
				
				<tr>
					<td>E-mail </td>
					 <td><input type="email" name="EMAILID"
						style="width: 392px;" />
					</td>
					
					<td>Phones </td>
					 <td><input type="text" name="PHN" /></td>
					 <td></td>
					 <td></td>
				</tr>
				
				<tr>
					<td>Address</td>
					<td class="tdAddress" colspan=5>
						<input name="PAD1" type="text" />
						<input name="PAD2" type="text" />
						<input name="PAD3" type="text" />
						<input name="PAD4" type="text" />
					</td>
				</tr>
			</table>
			<table>

				<tr>
					<td>Outstanding:</td>
					 <td> <input type="number" name="POA" step="0.01"></td>
					<td>Advance Rent</td>
					 <td><input type="number" name="PCRA" step="0.01"></td>
					<td>Receipt No.</td>
					<td><input type="text" name="LRNO"></td>
					 <td>Receipt Date</td>
					<td><input type="date" name="LRND"></td>
					
				</tr>
				<tr>
					<td>Remark: </td>
					 <td> <input type="text" name="REMARKS"></td>
					<td>Service tax</td>
					 <td> <input type="number" name="PCRAST" step="0.01"></td>
					<td>Release</td>
					<td><input type="text" name="RELS" size="1"/></td>
					<td>Release Date</td>
					<td><input type="date" name="RELSD"/></td>

				</tr>
				<tr>
					<td>Late fee:</td>
					 <td>  <input type="number" name="latefee" step="0.01"></td>
					<td>Area code</td>
					 <td> <input type="number" name="areacode"></td>
				</tr>
			</table>
			<input type="submit" name="submitNewBooking" value="Update">
			<input type="reset" name="cleartext" value="Clear">
		</form>
	</div>

</body>
</html>