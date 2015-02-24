<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="../css/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
	href="../css/jquery-ui.structure.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery-ui.theme.css" />

<!--  load jquery prior to the jquery ui which is using it -->
<script src="../js/jquery-1.11.1.js" type="text/javascript"></script>
<script src="../js/jquery-ui.js" type="text/javascript"></script>

</head>
<body>
<div id = 'mainmenu'>
		<div style="float: left;">
			<accordion id="accUprav" class="accUpr">
			<h3>
				<a href="#">Menu 1</a>
			</h3>
			<div>
				<ul id="mnuUpr">
					<li><a href="#">Tools</a>
						<ul>
							<li><a href="#">3.1jQuery</a>
								<ul>
									<li><a href="#">3.1.1 Download</a></li>
									<li><a href="#">3.1.2 Tutorial</a></li>
								</ul></li>
							<li><a href="#">3.2 Mootools</a></li>
							<li><a href="#">3.3 Prototype</a></li>
						</ul></li>
					<li><a href="#">Special</a></li>
				</ul>
			</div>
			</accordion>
		</div>
		<div style="float: left;">
			<accordion id="accOptions" class="accOpt">
			<h3>
				<a href="#">Menu 2</a>
			</h3>
			<div>
				<ul id="mnuOpt">

					<li><a href="#">Tools</a>
						<ul>
							<li><a href="#">3.1jQuery</a>
								<ul>
									<li><a href="#">3.1.1 Download</a></li>
									<li><a href="#">3.1.2 Tutorial</a></li>
								</ul></li>
							<li><a href="#">3.2 Mootools</a></li>
							<li><a href="#">3.3 Prototype</a></li>
						</ul></li>
					<li><a href="#">Special</a></li>
				</ul>
			</div>
			</accordion>
		</div>
	</div>
	<script>
		$("accordion ul").css({
			width : "145px"
		});
		$("accordion").accordion({
			collapsible : true,
			active : false
		});
		$("accordion div")
				.removeClass(
						"ui-accordion-content ui-corner-all ui-corner-bottom ui-widget-content");
		$("#mnuUpr").menu({
			position : {
				my : "left top",
				at : "right+3 top"
			}
		});
		$("#mnuUpr").hover(function() {
		}, function() {
			$("#accUprav").accordion("option", "active", "none");
		});
	</script>
</body>
</html>
