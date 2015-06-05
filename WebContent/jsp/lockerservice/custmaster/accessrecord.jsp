<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Access Record</title>
</head>
<body>
	<jsp:include page="/jsp/mainmenu.jsp"></jsp:include>
	<div style="clear: both;"></div>
	<div id="msg" class="nomsg"></div>

	<div style="clear: both;"></div>

	<div class="main-content" id="accessdetails">
		<form id="frm_accessdetails">
	
		<table >
		<tr>
		<td>Date <input type="date" name="ATD"> </td>
		<td>Key Num <input type="text" name="AKNO"
			onkeydown="javascript:clickOnEnter(event,'getDetails')" />
					
					
					<input
						type="button" id='getDetails' value="Get Details">
		</td>
		<td>Booking Num <input type="text" name="ALSNO"></td>
		<td>Locker Num <input type="text" name="ALNO"></td>

		</tr>
		
		</table>
		<table id="accessrecords">
		<tr>
		<th>Visited By</th>
		<th>Time In</th>
		<th>Time Out</th>
		<th>Attended By</th>
		<th>Remarks</th>
				<th><span style="font:normal 12px agency, arial; color:blue; text-decoration:underline; cursor:pointer;" onclick="addMoreRows(this.form);">
Add Data
</span></th>
		</tr>
		
		</table>
	</form> 
	</div>
	
	<script type="text/javascript">
	     var namesOfCustomers = [];	
	
	    //equivalent of $(document).ready(function(){...
		$(function() {
			
			
			
			//set Bill date as sysdate if it is not already set
			if($('input[name=ATD]').val()==""){
				$('input[name=ATD]').val($.datepicker.formatDate('yy-mm-dd',new Date()));
			}
			
			function getDetails(keynum) {
				//get ajax call
				$.get(
						'/Locker_Financial_Society/rest/lockerservice/keydetails/'
								+ keynum // rest api
				// key for which record is to be fetched		
				).done(function(data) { // data is returned from the server

					//populate fields with the recieved data
//					populateForm($('form[id=frm_accessdetails]'), data);
				//fill booking number
				
				$('input[name=ALSNO]').val(data.LSNO);
				$('input[name=ALNO]').val(data.LNO);
				
				namesOfCustomers.push(data.PNM2+data.PNM3);
				namesOfCustomers.push(data.PNM4);
				namesOfCustomers.push(data.PNM5);
				
				}).fail(function(error) {
					//log error to console
					console.log(error);
				})
			}

			//call the rest api to get details for the given key num
			$('#getDetails').on('click', function() {

				var keynum = $('input[name="AKNO"]').val();

				getDetails(keynum);
			})
			
		});
		

		var rowCount = 0;
		
		function addMoreRows(frm) {
		rowCount ++;
		var recRow = '<tr id="rowCount'+rowCount+'" ><td><input name="" id="automplete-'+rowCount+'" type="text" size="17%"  maxlength="120"  /></td><td><input name="" type="time"  maxlength="120" style="margin: 4px 5px 0 5px;"/> </td><td><input name="" type="time" maxlength="120" style="margin: 4px 10px 0 0px;"/><span class="add-on"> </td><td><input name="" type="text"></td><td><input name="" type="text"></td><td> <a href="javascript:void(0);" onclick="removeRow('+rowCount+');">Delete</a></td></tr>';
		$('#accessrecords').append(recRow);
		
		//add autocomplete to input text
		$('#automplete-'+rowCount).autocomplete({source:namesOfCustomers,minLength:0}).bind('focus', function(){ $(this).autocomplete("search"); });
		}

		function removeRow(removeNum) {
		$('#rowCount'+removeNum).remove();
		}

	</script>
</body>
</html>