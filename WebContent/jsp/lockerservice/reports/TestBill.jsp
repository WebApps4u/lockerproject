<%@page import="net.sf.jasperreports.engine.*"%>
<%@page import="net.sf.jasperreports.engine.util.*"%>
<%@page import="net.sf.jasperreports.engine.export.*"%>
<%@page import="net.sf.jasperreports.j2ee.servlets.*"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import ="com.lok.controller.ReportGenController" %>

<%
	JasperPrint jasperPrint = (JasperPrint)session.getAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE);
	
	if (jasperPrint == null)
	{
		jasperPrint = ReportGenController.getReportTest("select * from billrecord where lamt is not null and lstxa is not null limit 500");
		session.setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
	}
	HtmlExporter exporter = new HtmlExporter();
	
	int pageIndex = 0;
	int lastPageIndex = 0;
	if (jasperPrint.getPages() != null)
	{
		lastPageIndex = jasperPrint.getPages().size() - 1;
	}

	String pageStr = request.getParameter("page");
	try
	{
		pageIndex = Integer.parseInt(pageStr);
	}
	catch(Exception e)
	{
	}
	
	if (pageIndex < 0)
	{
		pageIndex = 0;
	}

	if (pageIndex > lastPageIndex)
	{
		pageIndex = lastPageIndex;
	}
	
	StringBuffer sbuffer = new StringBuffer();

	exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
	exporter.setParameter(JRExporterParameter.PAGE_INDEX, Integer.valueOf(pageIndex));

	exporter.exportReport();
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



<title>Test Bill Report </title>
</head>
<body>
	<!--<jsp:include page="/jsp/mainmenu.jsp"></jsp:include>-->
	<div style="clear: both;"></div>

	<div id='cssmenu' style="float:left">
		<ul>
			<li class='active'><a href='#'
				title="System will generate report for current year"><span>Saved
						Report</span></a>
				<ul>
					<li><a href='#'><span>Current Year Outstanding</span></a></li>
					<li><a href='#'><span>Total Outstanding</span></a></li>
					<li class='last'><a href='#'><span>Current year
								Bill Raised</span></a></li>
				</ul></li>
			<li class='has-sub'><a href='#'><span>Custom Report</span></a>
				<ul>
					<li><span>Enter Period</span><br>
					<input type="date" placeholder="From Date" name="fromdate">TO<input
						type="date" placeholder="To Date" name="todate"></li>
					<li><span>As On Date</span><br>
					<input type="date" placeholder="As on Date" name="asondate"></li>
					<li><a href='#'><span>Outstanding Bill</span></a></li>
					<li><a href='#'><span>Bill Raised</span></a></li>

				</ul></li>
		</ul>
	</div>
	<div id="report_content" style="float:left">

<table width="100%" cellpadding="0" cellspacing="0" border="0">
<tr>
  <td width="50%">&nbsp;</td>
  <td align="left">
    <hr size="1" color="#000000">
    <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td><a href="TestBill.jsp?reload=true"><img src="../images/reload.GIF" border="0"></a></td>
        <td>&nbsp;&nbsp;&nbsp;</td>
<%
	if (pageIndex > 0)
	{
%>
        <td><a href="TestBill.jsp?page=0"><img src="../images/first.GIF" border="0"></a></td>
        <td><a href="TestBill.jsp?page=<%=pageIndex - 1%>"><img src="../images/previous.GIF" border="0"></a></td>
<%
	}
	else
	{
%>
        <td><img src="../images/first_grey.GIF" border="0"></td>
        <td><img src="../images/previous_grey.GIF" border="0"></td>
<%
	}

	if (pageIndex < lastPageIndex)
	{
%>
        <td><a href="TestBill.jsp?page=<%=pageIndex + 1%>"><img src="../images/next.GIF" border="0"></a></td>
        <td><a href="TestBill.jsp?page=<%=lastPageIndex%>"><img src="../images/last.GIF" border="0"></a></td>
<%
	}
	else
	{
%>
        <td><img src="../images/next_grey.GIF" border="0"></td>
        <td><img src="../images/last_grey.GIF" border="0"></td>
<%
	}
%>
        <td width="100%">&nbsp;</td>
      </tr>
    </table>
    <hr size="1" color="#000000">
  </td>
  <td width="50%">&nbsp;</td>
</tr>
<tr>
  <td width="50%">&nbsp;</td>
  <td align="center">

<%=sbuffer.toString()%>

  </td>
  <td width="50%">&nbsp;</td>
</tr>
</table>
</div>
	<!--  To load required javascript for side menu -->
	<jsp:include page="/jsp/sidemenu.jsp"></jsp:include>


</body>
</html>