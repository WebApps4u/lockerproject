<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Customer Details</title>
</head>
<body>
	<jsp:include page="/jsp/mainmenu.jsp"></jsp:include>
	<div style="clear: both;"></div>
	<div id="msg" class="nomsg"></div>

	<div style="clear: both;"></div>
	<div id='CustomerDetails' class="main_content">
	<!--  Page Name -->
	    <div class="breadcrums">
	    <span itemprop="title">Create New or Edit Customer KYC Documents</span>
	    </div>
		<form id="frm_custDetails" autocomplete="on" enctype="multipart/form-data" action="/Locker_Financial_Society/rest/upload/customerdetails?CUSTOMERID=${it.CUSTOMERID}" method="POST">
					<table>

				<tr>
				<td class="displayTextLbl">Customer Id:</td>
				<td><input name="CUSTOMERID" value="${it.CUSTOMERID }" placeholder="NEW"
				onkeydown="clickOnEnter(event,'getDetails')" />
							<input type="button" id='getDetails' value="Go">
				</td>
				</tr>
				<tr>
				<td class="displayTextLbl">Name:</td>
				<td><input name="FIRSTNAME" type="text" value="${it.FIRSTNAME }" /> <input name="LASTNAME" value="${it.LASTNAME }" type="text" /></td>
				</tr>
				<tr>
				<td class="displayTextLbl">Address:</td>
				<td class="tdAddress" colspan=5><input name="ADDRESS1" type="text" value="${it.ADDRESS1 }" />
						<input name="ADDRESS2" type="text" value="${it.ADDRESS2 }" /> 
						</td>
						</tr>
						<tr>
						<td class="displayTextLbl">City: </td>
						<td><input name="CITY" type="text" value="${it.CITY }" /></td>
						</tr>
						<tr>
						<td class="displayTextLbl">STATE: </td>
						<td><input name="STATE" type="text" value="${it.STATE }" /></td>
						</tr>
						<tr>
						<td class="displayTextLbl">COUNTRY: </td>
						<td><input name="COUNTRY" type="text" value="${it.COUNTRY }"  /></td>
						</tr>
						<tr>
						<td class="displayTextLbl">PINCODE: </td>
						
						<td><input name="PINCODE" type="text" value="${it.PINCODE }" /></td>
						</tr>
						<tr>
							<td class="displayTextLbl">LANDMARK: </td>
						
						<td><input name="LANDMARK" type="text" value="${it.LANDMARK }" /></td>
				</tr>
				<tr>
				<td class="displayTextLbl">Email Id:</td>
				<td><input type="email" name="EMAILID" value="${it.EMAILID }" />
				</tr>
				<tr>
				<td class="displayTextLbl">Mobile number:</td>
				<td><input type="text" name="MOBILENUM" value="${it.MOBILENUM }" /></td>
				</tr>
				<tr>
				<td class="displayTextLbl">Phone Number:</td>
				<td><input type="text" name="PHONENUM" value="${it.PHONENUM }" /></td>
				</tr>
				<tr>
				<td class="displayTextLbl">Photo upload:</td>
				<td><img
						src="/Locker_Financial_Society/fileservlet/${it.PHOTOPATH }">
					<input type="file" name="PHOTO_FILE"></td>
				</tr>
				<tr>
				<td class="displayTextLbl">Passport Upload:</td>
				<td><img
						src="/Locker_Financial_Society/fileservlet/${it.PPPATH }">
					<input type="file" name="PP_FILE"></td>
				</tr>
				<tr>
				<td class="displayTextLbl">Aadhar Upload:</td>
				<td><img
						src="/Locker_Financial_Society/fileservlet/${it.AADHARPATH }">
					<input type="file" name="AADHAR_FILE"></td>
				</tr>
				<tr>
				<td class="displayTextLbl">D/L:</td>
				<td>
				<img
						src="/Locker_Financial_Society/fileservlet/${it.DLICENSEPATH }">
					<input type="file" name="DL_FILE"></td>
				
				</tr>
				<tr>
				<td class="displayTextLbl">Sign Upload:</td>
				<td>
				<img
						src="/Locker_Financial_Society/fileservlet/${it.SIGNPATH }">
					<input type="file" name="SIGN_FILE"></td>
				
				</tr>				
		</table>
		<div class="floating_buttons">
<input type="button" name="submitNewKYC" id="submitNewKYC"
					value="" class="update">
		 <input type="reset"
					name="cleartext" value="" class="clear">
</div>

		</form>
		</div>
		
		<script>
		//on click of get details, request the URL, by providing given customer id
		$(function(){
			
			$('#getDetails').on(
					'click',function(){
						var custId = $("input[name=CUSTOMERID]").val();
						
						document.location.href = "/Locker_Financial_Society/rest/upload/customerdetails/" + custId;
					})
					
			$('#submitNewKYC').on(
					'click',function(){
						$('#frm_custDetails').submit();
					})
		});
		
		
		</script>

</body>
</html>