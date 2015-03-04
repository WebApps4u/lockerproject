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
<!-- IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
<script type="text/javascript">

//Load the datepicker, it will be required on all the pages
$(function() {
    $( "input[type='date']" ).datepicker({ dateFormat: 'yy-mm-dd' }).val();;
  });
</script>
</head>

<body>
	<div id="wrapper">

		<nav id="nav">
			<ul id="navigation">
				<li><a href="#" class="first">Home</a></li>
				<li><a href="#">Locker &raquo;</a>
					<ul>
						<li><a
							href="/Locker_Financial_Society/jsp/lockerservice/custmaster/billdetails.jsp">Bill
								Editing</a></li>
						<li><a href="#">Daily Statement</a></li>
						<li><a href="#">Reports &raquo;</a>
							<ul>
								<li><a href="#">Report I</a></li>
								<li><a href="#">Report II</a></li>
							</ul></li>
						<li><a href="#">Due Letters</a></li>
					</ul></li>
				<li><a href="#">Locker Service &raquo;</a>
					<ul>
						<li><a href="#">Customer Master</a></li>
						<li><a href="#">Locker Rent</a></li>
						<li><a href="#">Delete Receipt</a></li>
						<li><a href="#">Calligraphy</a></li>
						<li><a href="#">Reminder &raquo;</a>
							<ul>
								<li><a href="#">Reminder </a></li>
								<li><a href="#">Reminder Prt</a></li>
							</ul></li>
						<li><a href="#">Help </a></li>
					</ul></li>
				<li><a href="#">Search</a></li>
				<li><a href="#" class="last">Reports</a></li>
				<!-- 		<li><a href="#" class="last">Data-transfer</a></li> -->
			</ul>
		</nav>

	</div>
	<!--end wrapper-->

</body>
</html>