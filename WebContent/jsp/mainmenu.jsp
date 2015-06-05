<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<link rel="stylesheet" href="/Locker_Financial_Society/css/style.css">
<link rel="stylesheet" type="text/css"
	href="/Locker_Financial_Society/css/jquery-ui.css" />

<link rel="stylesheet" type="text/css"
	href="/Locker_Financial_Society/css/jquery-ui.structure.css" />
<link rel="stylesheet" type="text/css"
	href="/Locker_Financial_Society/css/jquery-ui.theme.css" />


<!--  load jquery prior to the jquery ui which is using it -->
<script src="/Locker_Financial_Society/js/jquery-1.11.1.js"
	type="text/javascript"></script>
<script src="/Locker_Financial_Society/js/jquery-ui.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="/Locker_Financial_Society/css/jquery.dynatable.css">
<script src="/Locker_Financial_Society/js/jquery.dynatable.js"
	type="text/javascript"></script>
<script src="/Locker_Financial_Society/js/jquery.dataTables.min.js"
	type="text/javascript"></script>
	<script src="/Locker_Financial_Society/js/jquery.blockUI.js"
	type="text/javascript"></script>
<script src="/Locker_Financial_Society/js/lok_jsutil.js"
	type="text/javascript"></script>

<!-- IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
<script type="text/javascript">
	//Load the datepicker, it will be required on all the pages
	 $(function() {
		$("input[type='date']").datepicker({
			dateFormat : 'yy-mm-dd',
			changeYear : true
		}).val();
		
		
	}); 
</script>
</head>

<body>
	<div id="logo">
		<img src="/Locker_Financial_Society/css/images/logo.png"
			alt="The Delhi Safe Deposit Co Ltd.">
	</div>
	<div id="wrapper">

		<nav id="nav">
			<ul id="navigation">
				<li><a href="/Locker_Financial_Society/index.jsp" class="first">Home</a></li>
				<li><a href="#">New Entry &raquo;</a>
					<ul>
						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/custmaster/keydetails.jsp">New
								Booking</a></li>
						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/custmaster/CustomerDetails.jsp">New
								Customer(KYC)</a></li>
						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/custmaster/billdetails.jsp">New
								Manual Bill</a></li>

						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/custmaster/receiptgeneration.jsp">New
								Receipt</a></li>
						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/custmaster/sdreceipt.jsp">New
								SD Receipt</a></li>

						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/custmaster/accessrecord.jsp">New
								Access Record</a></li>
						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/custmaster/debitnote.jsp">New
								Debit Note</a></li>
						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/custmaster/creditnote.jsp">New
								Credit Note</a></li>

						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/reports/AutoBillGeneration.jsp">Auto
								Bill Generation</a></li>
						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/reports/AutoRemGeneration.jsp">Auto
								Reminder Generation</a></li>

						<li><a href="#">Due Letters</a></li>
					</ul></li>
				<li><a href="#">Existing Entry &raquo;</a>
					<ul>

					<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/custmaster/keydetails.jsp?id=EXISTING">
								Booking</a></li>
						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/custmaster/CustomerDetails.jsp?id=EXISTING">
								Customer(KYC)</a></li>
						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/custmaster/billdetails.jsp?id=EXISTING">
								Manual Bill</a></li>

						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/custmaster/receiptgeneration.jsp?id=EXISTING">
								Receipt</a></li>
						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/custmaster/sdreceipt.jsp?id=EXISTING">
								SD Receipt</a></li>

						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/custmaster/accessrecord.jsp?id=EXISTING">
								Access Record</a></li>
						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/custmaster/debitnote.jsp?id=EXISTING">
								Debit Note</a></li>
						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/custmaster/creditnote.jsp?id=EXISTING">
								Credit Note</a></li>
					</ul></li>

				<li><a href="#" class="last">Reports &raquo;</a>
					<ul>
						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/reports/billstatus.jsp">Bill
								Status &raquo;</a>

							<ul>
								<!-- Bill status has all the below options, this is just a shortcut to display results directly   -->
								<li><a href="#">Oustanding on Date </a></li>
								<li><a href="#">Current Year position</a></li>
								<li><a href="#">Outstanding yearly</a></li>
								<li><a href="#">Outstanding bills</a></li>
							</ul></li>
						<li><a href="#">Receipt register &raquo;</a>

							<ul>
								<li><a href="#">Daily </a></li>
								<li><a href="#">Monthly</a></li>
							</ul></li>
					</ul></li>
				<li><a href="#">Admin Menu</a>
					<ul>
					<li><a href="/Locker_Financial_Society/rest/adminservice/billtemplate/1001">Bill Email Template</a>
					</ul>	</li>
				<li><a href="#">Search</a></li>
				<!-- 		<li><a href="#" class="last">Data-transfer</a></li> -->
			</ul>
		</nav>

	</div>
	<!--end wrapper-->

</body>
</html>