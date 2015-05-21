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
			<span itemprop="title">Create New or Edit Customer KYC
				Documents</span>
		</div>
		<form id="frm_custDetails" autocomplete="on"
			enctype="multipart/form-data"
			action="/Locker_Financial_Society/rest/upload/customerdetails?CUSTOMERID=${customer.CUSTOMERID }"
			method="POST">
			<table>

				<tr>
					<td>Customer Id:</td>
					<td><input name="CUSTOMERID" value="${customer.CUSTOMERID}"
						type="text" /></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><input name="FIRSTNAME" value="${customer.FIRSTNAME}"
						type="text" /></td>
					<td><input name="LASTNAME" value="${customer.LASTNAME}"
						type="text" /></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td class="tdAddress" colspan=5><input name="ADDRESS1"
						value="${customer.ADDRESS1}" type="text" /> <input
						value="${customer.ADDRESS2}" name="ADDRESS2" type="text" /></td>
					<td>City:</td>
					<td><input value="${customer.CITY}" name="CITY" type="text" /></td>
					<td>STATE:</td>
					<td><input value="${customer.STATE}" name="STATE" type="text" /></td>
					<td>COUNTRY:</td>
					<td><input name="COUNTRY" value="${customer.COUNTRY}"
						type="text" /></td>
					<td>PINCODE:</td>
					<td><input name="PINCODE" value="${customer.PINCODE}"
						type="text" /></td>
					<td>LANDMARK:</td>
					<td><input name="LANDMARK" value="${customer.LANDMARK}"
						type="text" /></td>
				</tr>
				<tr>
					<td>Email Id:</td>
					<td><input type="email" name="EMAILID"
						value="${customer.EMAILID}" style="width: 392px;" />
				</tr>
				<tr>
					<td>Mobile number:</td>
					<td><input type="text" name="MOBILENUM"
						value="${customer.MOBILENUM}" /></td>
				</tr>
				<tr>
					<td>Phone Number:</td>
					<td><input type="text" name="PHONENUM"
						value="${customer.PHONENUM}" /></td>
				</tr>
				<tr>
					<td>Photo upload:</td>
					<td><img
						src="/Locker_Financial_Society/fileservlet/${customer.PHOTOPATH }"></td>
					<td><input type="file" name="PHOTO_FILE"></td>
				</tr>
				<tr>
					<td>Passport Upload:</td>
					<td><img
						src="/Locker_Financial_Society/fileservlet/${customer.PPPATH }"></td>
					<td><input type="file" name="PP_FILE"></td>
				</tr>
				<tr>
					<td>Aadhar Upload:</td>
					<td><img
						src="/Locker_Financial_Society/fileservlet/${customer.AADHARPATH }"></td>
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

			</table>
			<input type="submit" name="submitNewBooking" id="submitNewBooking"
				value="" class="update"> <input type="reset"
				name="cleartext" value="" class="clear">

		</form>
	</div>

</body>
</html>