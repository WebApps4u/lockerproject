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
						<td><input name="EMAILTEMPLATEID" type=email readonly="readonly"
							value="${it.EMAILTEMPLATEID }" ></td>
					</tr>

					<tr>
						<td class="displayTextLbl">To</td>
						<td><input name="TOLIST" type=email value="${it.TOLIST }" ></td>
					</tr>
					<tr>
						<td class="displayTextLbl">CC list</td>
						<td><input name="CCLIST" type=email value="${it.CCLIST }" ></td>
					</tr>
					<tr>
						<td class="displayTextLbl">From</td>
						<td><input name="FROMEMAIL" type=email value="${it.FROMEMAIL }" ></td>
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
			</div>
			<div class="fixed_buttons">
				<input type="button" name="updateTemplate" id="updateTemplate"
					value="" class="update" > <input
					type="reset" name="cleartext" value="" class="clear">
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
									CKEDITOR.instances[instance].updateElement();
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