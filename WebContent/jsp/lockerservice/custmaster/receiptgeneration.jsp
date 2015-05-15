<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Receipt</title>
</head>
<body>
	<jsp:include page="/jsp/mainmenu.jsp"></jsp:include>
	<div style="clear: both;"></div>

	<div id="msg" class="nomsg"></div>

	<div style="clear: both;"></div>

	<!--  Key details -->

	<div class="sep_section"></div>
	<div id="ReceiptDetails" class="main_content">
<!--  Page Name -->
	    <div class="breadcrums">
	    <span itemprop="title">
	    ${(param.id==''||param.id==null) ?'Create New Receipt':(param.id=='EXISTING'?'Modify Existing Receipt':param.id) }
	    </span>
	    </div>
		<form id="frm_receiptDetails">

			<div id="keyDetails">
				<table>
					<tr>
						<td class="displayTextLbl">Receipt Number</td>
						<td><input name="RCTN" placeholder="NEW" readonly="" /></td>
						<td></td>

						<td class="displayTextLbl">Date</td>
						<td><input type="date" name='RCTD' value="" /></td>
					</tr>

				</table>
				<table>
					<tr>
						<td class="displayTextLbl">Key no</td>
						<td><input name="RKNO" size="6" type="text"
							onkeydown="if (event.keyCode == 13) document.getElementById('getDetails').click()" />
							<input type="button" id='getDetails' value="Go"></td>
						<td class="displayTextLbl">Locker no</td>
						<td><input readonly="readonly" name="LNO" size="6"
							type="text" /></td>
						<td class="displayTextLbl">Locker rent</td>
						<td><input readonly="readonly" name="LOKR" type="number"
							step="0.01" /></td>
					</tr>
					<tr>
						<td class="displayTextLbl">Lease date</td>
						<td><input readonly="readonly" type="date" name="LSDT" /></td>
						<td class="displayTextLbl">Booking no</td>
						<td><input readonly="readonly" name="LSNO" size="6"
							type="text" /></td>

						<td class="displayTextLbl">Outstanding</td>
						<td><input readonly="readonly" name="POA" type="number"
							step="0.01" /></td>

					</tr>
					<tr>
						<td class="displayTextLbl">Advance</td>
						<td><input readonly="readonly" type="date" name="LPA" /></td>
						<td></td>
						<td></td>
						<td></td>

					</tr>
				</table>
				<table>
					<tr>
						<th>Contact Details</th>

					</tr>
					<tr>
						<!-- Ist Name -->
						<td><select readonly="readonly" name="PNM1">
								<option value="MR">Mr</option>
								<option value="MRS">Mrs</option>
						</select> <input readonly="readonly" name="PNM2" type="text" /> <input
							readonly="readonly" name="PNM3" type="text" /></td>

					</tr>
					<tr>

						<td><input readonly="readonly" name="PNM4" size="40"
							type="text" /></td>

					</tr>
					<tr>

						<td><input readonly="readonly" name="PNM5" size="40"
							type="text" /></td>

					</tr>


					<tr>
						<td><input readonly="readonly" type="email" name="EMAILID"
							style="width: 200px;" /></td>
					</tr>
					<tr>
						<td><input readonly="readonly" type="text" name="PHN" /></td>

					</tr>

					<tr>
						<td class="tdAddress" colspan=5><input readonly="readonly"
							name="PAD1" type="text" /> <input readonly="readonly"
							name="PAD2" type="text" />
	                    <br>

						<input readonly="readonly" name="PAD3" type="text" /> <input
							name="PAD4" type="text" readonly="readonly" /></td>
					</tr>

				</table>

				<!--  Bill Details -->
				<div id="billDetails">
					<table id="billRecords">
						<thead>
							<tr>

								<th data-dynatable-column="BNO">Bill No.</th>
								<th data-dynatable-column="LAMT">Locker Rent</th>
								<th data-dynatable-column="LOUT">Prev Outstanding</th>
								<th data-dynatable-column="LADV">Adv Payment</th>
								<th data-dynatable-column="BFDT">From</th>
								<th data-dynatable-column="BTDT">To</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>

			<!-- Particulars details -->
			<div id="particulars">
				<table>
					<tr>
						<th>Fees Details</th>
						<th>Amount</th>
					</tr>
					<tr>
						<td class="displayTextLbl">Locker Rent</td>
						<td><input type="number" step="0.01" name="RRNT" /></td>
					</tr>
					
					<tr>
						<td class="displayTextLbl">Service tax@<input type="number" step="0.01"
							name="STAXR" value="14" />%
						</td>
						<td><input type="number" step="0.01" name="STAXA"
							readonly="readonly" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td class="displayTextLbl">Interest</td>
						<td><input type="number" step="0.01" name="RINT" /></td>
					</tr>
					<tr>
						<td class="displayTextLbl">Misc charges</td>
						<td><input type="number" step="0.01" name="RINC" /></td>
					</tr>
					<tr>
						<td class="displayTextLbl">Bank charges</td>
						<td><input type="number" step="0.01" name="RBC" /></td>
					</tr>
					<tr>
						<td class="displayTextLbl">Application Fees</td>
						<td><input type="number" step="0.01" name="RAPF" /></td>
					</tr>
					<tr>
						<td class="displayTextLbl">Legal Fees</td>
						<td><input type="number" step="0.01" name="RGLF" /></td>
					</tr>
					<tr>
						<td class="displayTextLbl">Advances</td>
						<td><input type="number" step="0.01" name="RDAV" /></td>
					</tr>
					<tr>
						<td class="displayTextLbl">Access charges</td>
						<td><input type="number" step="0.01" name="RACH" /></td>
					</tr>
					<tr>
						<td class="displayTextLbl">Suspense</td>
						<td><input type="number" step="0.01" name="RSUS" /></td>
					</tr>
					<tr>
						<td class="displayTextLbl">Breaking charges</td>
						<td><input type="number" step="0.01" name="BRKCH" /></td>
					</tr>

					
					<tr></tr>
										<tr>
						<td class="displayTextLbl">Less at credit(-)</td>
						<td><input type="number" step="0.01" name="RADVP" /></td>
					</tr>
					<tr>
						<td class="displayTextLbl">Add previous due</td>
						<td><input type="number" step="0.01" name="RPOUT" /></td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td class="displayTextLbl">Total</td>
						<td><input type="number" step="0.01" name="RGTOT"
							readonly="readonly" /></td>
					</tr>
					<tr></tr>
					<tr></tr>
						<tr>
						<td class="displayTextLbl">Amount Paid</td>
						<td><input name="RAMT" type="number" step="0.01" value="0">
					</tr>
					<tr></tr>
					
					<tr>
							<td class="displayTextLbl">Advance for Future</td>
							<td><input type="number" step="0.01" name="RADV" value="0" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td class="displayTextLbl">New Outstanding</td>
							<td><input type="number" step="0.01" name="RCOUT" value="0" readonly="readonly"></td>
						</tr>

				</table>


				<!--  Payment section -->
				<div id="paymentmethod">
					<label>Payment Methods</label>
					<section class="tabs">
						<input id="tab-1" type="radio" name="RTYP" class="tab-selector-1"
							checked="checked" value="CASH" /> <label for="tab-1"
							class="tab-label-1">Cash</label> <input id="tab-2" type="radio"
							name="RTYP" class="tab-selector-2" value="Draft" /> <label
							for="tab-2" class="tab-label-2">Draft</label> <input id="tab-3"
							type="radio" name="RTYP" class="tab-selector-3" value="Cheque" />
						<label for="tab-3" class="tab-label-3">Cheque</label> <input
							id="tab-4" type="radio" name="RTYP" class="tab-selector-4"
							value="Online Both" /> <label for="tab-4" class="tab-label-4">Online
							Both</label>

						<div class="clear-shadow"></div>

						<div class="content">
							<div class="content-1">
								<p>Amount Paid</p>
								
							</div>
							<div class="content-2">
								<p>Some content</p>
							</div>
							<div class="content-3">
								<p>Some content</p>
							</div>
							<div class="content-4">
								<p>Some content</p>
							</div>
						</div>
					</section>

				</div>

				<div id="futurebalance">

					<table>

						
					</table>
				</div>


			</div>
			<div class="floating_buttons">
				<input type="button" name="submitNewReceipt" id="submitNewReceipt"
					value="" class="send"> <input type="reset" name="cleartext"
					value="" class="clear">
			</div>

			<div class="sep_section"></div>

		</form>
	</div>

	<!--  Load the required js file -->
	<script src="/Locker_Financial_Society/js/lok_receiptJS.js"
		type="text/javascript"></script>

	<div style="clear: both;"></div>
	<jsp:include page="/jsp/Footer.jsp"></jsp:include>

</body>
</html>