<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Bill Details</title>

<script src="/Locker_Financial_Society/ckeditor/ckeditor.js"
	type="text/javascript"></script>
</head>
<body>
	<jsp:include page="/jsp/mainmenu.jsp"></jsp:include>
	<div style="clear: both;"></div>
	<div id="msg" class="nomsg"></div>

	<div style="clear: both;"></div>
	<div id='TemplateDetails' class="main_content">
		<div class="breadcrums">
			<span itemprop="title"> Edit Bill Template </span>
		</div>
		<form id="frm_billTemplate" autocomplete="on">
			<div>

				<table id="billTempalte">
					<tr>
						<td class="displayTextLbl">Email Template Id</td>
						<td><input name="EMAILTEMPLATEID" type=email
							readonly="readonly" value="${it.EMAILTEMPLATEID }"></td>
					</tr>

					<tr>
						<td class="displayTextLbl">To</td>
						<td><input name="TOLIST" type=email value="${it.TOLIST }"></td>
					</tr>
					<tr>
						<td class="displayTextLbl">CC list</td>
						<td><input name="CCLIST" type=email value="${it.CCLIST }"></td>
					</tr>
					<tr>
						<td class="displayTextLbl">From</td>
						<td><input name="FROMEMAIL" type=email
							value="${it.FROMEMAIL }"></td>
					</tr>
					<tr>
						<td class="displayTextLbl">Subject</td>
						<td><input name="SUBJECT" type=email value="${it.SUBJECT }"></td>
					</tr>
					<tr>
						<td class="displayTextLbl">Body</td>
						<td style="width: 800px"><textarea id="BODY" name="BODY">
						${it.BODY }
						</textarea></td>
					</tr>

				</table>

				<div class="sep_section"></div>

				<table>
					<tr>
						<th>Field name</th>
						<th>Variable</th>
					</tr>
					<tr>
						<td class="displayTextLbl">Bill Number</td>
						<td class="displayTextLbl">
							<%
								out.println("${BNO}");
							%>
						</td>
					</tr>
					<tr>
						<td class="displayTextLbl">Locker Rent</td>
						<td class="displayTextLbl">
							<%
								out.println("${LAMT}");
							%>
						</td>
					</tr>
					<tr>
						<td class="displayTextLbl">Service tax Amount</td>
						<td class="displayTextLbl">
							<%
								out.println("${LSTXA}");
							%>
						</td>
					</tr>
					<tr>
						<td class="displayTextLbl">Advance Paid</td>
						<td class="displayTextLbl">
							<%
								out.println("${LADV}");
							%>
						</td>
					</tr>
					<tr>
						<td class="displayTextLbl">Payable Amount</td>
						<td class="displayTextLbl">
							<%
								out.println("${LPYBA}");
							%>
						</td>
					</tr>
					<tr>
						<td class="displayTextLbl">From Date</td>
						<td class="displayTextLbl">
							<%
								out.println("${BFDT}");
							%>
						</td>
					</tr>
					<tr>
						<td class="displayTextLbl">To Date</td>
						<td class="displayTextLbl">
							<%
								out.println("${BTDT}");
							%>
						</td>
					</tr>
					<tr>
						<td class="displayTextLbl">Key Number</td>
						<td class="displayTextLbl">
							<%
								out.println("${KNO}");
							%>
						</td>
					</tr>
					<tr>
						<td class="displayTextLbl">Outstanding Amount</td>
						<td class="displayTextLbl">
							<%
								out.println("${LOUT}");
							%>
						</td>
					</tr>
					<tr>
						<td class="displayTextLbl">Current Year Payment</td>
						<td class="displayTextLbl">
							<%
								out.println("${LCP}");
							%>
						</td>
					</tr>
					<tr>
						<td class="displayTextLbl">Salutation (Mr./Ms.)</td>
						<td class="displayTextLbl">
							<%
								out.println("${PNM1}");
							%>
						</td>
					</tr>
					<tr>
						<td class="displayTextLbl">First Name</td>
						<td class="displayTextLbl">
							<%
								out.println("${PNM2}");
							%>
						</td>
					</tr>
					<tr>
						<td class="displayTextLbl">Second Name</td>
						<td class="displayTextLbl">
							<%
								out.println("${PNM3}");
							%>
						</td>
					</tr>
					<tr>
						<td class="displayTextLbl">Phone Number</td>
						<td class="displayTextLbl">
							<%
								out.println("${PHN}");
							%>
						</td>
					</tr>
					<tr>
						<td class="displayTextLbl">Email Id</td>
						<td class="displayTextLbl">
							<%
								out.println("${EMAILID}");
							%>
						</td>
					</tr>
					<tr>
						<td class="displayTextLbl">Booking Number</td>
						<td class="displayTextLbl">
							<%
								out.println("${LSNO}");
							%>
						</td>
					</tr>
					<tr>
						<td class="displayTextLbl">Locker Number</td>
						<td class="displayTextLbl">
							<%
								out.println("${LNO}");
							%>
						</td>
					</tr>
				</table>

			</div>
			<div class="sep_section"></div>
			<div class="fixed_buttons">
				<input type="button" name="updateTemplate" id="updateTemplate"
					value="" class="update"> <input type="reset"
					name="cleartext" value="" class="clear">
			</div>
		</form>

	</div>
	<script>
		// Replace the <textarea id="editor1"> with a CKEditor
		// instance, using default configuration.
		CKEDITOR.replace('BODY');

		$(function() {
			$('#updateTemplate')
					.on(
							'click',
							function(e) {

								e.preventDefault();

								//Reassign all the values from ckeditor back to text area before submission
								for (instance in CKEDITOR.instances) {
									CKEDITOR.instances[instance]
											.updateElement();
								}

								$
										.ajax({
											type : "POST",
											url : "/Locker_Financial_Society/rest/adminservice/billtemplate/",
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
											data : ConvertFormToJSON($('form[id=frm_billTemplate]'))
										});

							})
		});
	</script>
</body>
</html>