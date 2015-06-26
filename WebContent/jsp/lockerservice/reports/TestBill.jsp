<%@page import="net.sf.jasperreports.engine.*"%>
<%@page import="net.sf.jasperreports.engine.util.*"%>
<%@page import="net.sf.jasperreports.engine.export.*"%>
<%@page import="net.sf.jasperreports.j2ee.servlets.*"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import ="com.lok.reporting.BillReportGenerator" %>

<%
	String reload = request.getParameter("reload");
	JasperPrint jasperPrint = (JasperPrint)session.getAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE);
	
	if ((reload!=null && reload.equalsIgnoreCase("true")) || jasperPrint == null)
	{
		jasperPrint = new BillReportGenerator().getReportTest(request);
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
	<jsp:include page="/jsp/mainmenu.jsp"></jsp:include>
	<div style="clear: both;"></div>
	<!--  To load required javascript for side menu -->
	<jsp:include page="/jsp/sidemenu.jsp">
		<jsp:param value="${param.queryVal }" name="queryVal"/>
		
	</jsp:include>
	

	<div id="report_content" style="float:left">

<table width="100%" cellpadding="0" cellspacing="0" border="0">
<tr>
  <td width="50%">&nbsp;</td>
  <td align="left">
    <hr size="1" color="#000000">
    <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td><a href="?reload=true"><img src="/Locker_Financial_Society/css/images/reload.GIF" border="0"></a></td>
        <td>&nbsp;&nbsp;&nbsp;</td>
<%
	if (pageIndex > 0)
	{
%>
        <td><a href="?page=0"><img src="/Locker_Financial_Society/css/images/first.GIF" border="0"></a></td>
        <td><a href="?page=<%=pageIndex - 1%>"><img src="/Locker_Financial_Society/css/images/previous.GIF" border="0"></a></td>
<%
	}
	else
	{
%>
        <td><img src="/Locker_Financial_Society/css/images/first_grey.GIF" border="0"></td>
        <td><img src="/Locker_Financial_Society/css/images/previous_grey.GIF" border="0"></td>
<%
	}

	if (pageIndex < lastPageIndex)
	{
%>
        <td><a href="?page=<%=pageIndex + 1%>"><img src="/Locker_Financial_Society/css/images/next.GIF" border="0"></a></td>
        <td><a href="?page=<%=lastPageIndex%>"><img src="/Locker_Financial_Society/css/images/last.GIF" border="0"></a></td>
<%
	}
	else
	{
%>
        <td><img src="/Locker_Financial_Society/css/images/next_grey.GIF" border="0"></td>
        <td><img src="/Locker_Financial_Society/css/images/last_grey.GIF" border="0"></td>
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
	

</body>
</html>