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
				<td>Customer Id:</td>
				<td>${it.CUSTOMERID==""?"NEW":it.CUSTOMERID}</td>
				</tr>
				<tr>
				<td>Name:</td>
				<td><input name="FIRSTNAME" type="text" value=${it.FIRSTNAME }/> </td><td><input name="LASTNAME" type="text" /></td>
				</tr>
				<tr>
				<td>Address:</td>
				<td class="tdAddress" colspan=5><input name="ADDRESS1" type="text" />
						<input name="ADDRESS2" type="text" /> 
						</td>
						<td>City: </td>
						<td><input name="CITY" type="text" /></td>
						<td>STATE: </td>
						<td><input name="STATE" type="text" /></td>
						<td>COUNTRY: </td>
						<td><input name="COUNTRY" type="text" /></td>
						<td>PINCODE: </td>
						<td><input name="PINCODE" type="text" /></td>
							<td>LANDMARK: </td>
						<td><input name="LANDMARK" type="text" /></td>
				</tr>
				<tr>
				<td>Email Id:</td>
				<td><input type="email" name="EMAILID" style="width: 392px;" />
				</tr>
				<tr>
				<td>Mobile number:</td>
				<td><input type="text" name="MOBILENUM" /></td>
				</tr>
				<tr>
				<td>Phone Number:</td>
				<td><input type="text" name="PHONENUM" /></td>
				</tr>
				<tr>
				<td>Photo upload:</td>
				<td><input type="file" name="PHOTO_FILE"> </td>
				</tr>
				<tr>
				<td>Passport Upload:</td>
				<td><input type="file" name="PP_FILE"></td>
				</tr>
				<tr>
				<td>Aadhar Upload:</td>
				<td><input type="file" name="AADHAR_FILE"></td>
				</tr>
				<tr>
				<td>D/L:</td>
				<td><input type="file" name="DL_FILE"></td>
				</tr>
				<tr>
				<td>Sign Upload:</td>
				<td><input type="file" name="SIGN_FILE"></td>
				
				</tr>
				<tr><td>
<input type="submit" name="submitNewBooking" id="submitNewBooking"
					value="" class="update">
					</td><td> <input type="reset"
					name="cleartext" value="" class="clear"></td></tr>
		</table>
		</form>
		</div>

</body>
</html>