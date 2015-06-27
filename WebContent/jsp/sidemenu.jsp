<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">

$(function() {
	$('#cssmenu > ul > li > a').click(function() {
	  $('#cssmenu li').removeClass('active');
	  $(this).closest('li').addClass('active');	
	  var checkElement = $(this).next();
	  if((checkElement.is('ul')) && (checkElement.is(':visible'))) {
	    $(this).closest('li').removeClass('active');
	    checkElement.slideUp('normal');
	  }
	  if((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
	    $('#cssmenu ul ul:visible').slideUp('normal');
	    checkElement.slideDown('normal');
	  }
	  if($(this).closest('li').find('ul').children().length == 0) {
	    return true;
	  } else {
	    return false;	
	  }		
	});
	
	//Submit form by passing inputs
	$('#cssmenu > ul > li > ul > li > a').on("click",function(e){
		
		e.preventDefault();
		
		//create key value pair out of a href link
		var listOfAttr = $(this).attr('href');
		var destination = listOfAttr;
		
		//remove ? marks
		listOfAttr = listOfAttr.replace('?','');
		var keyValuePairs = listOfAttr.split('&');
		
		var flagChecked= "false";
		var errors = 0;
	 
		$.each( keyValuePairs, function( index, value ){
		    var keyValue = value;
		    
		    //split with =
		    var s = keyValue.split('=');			    
		    
		    //set custom flag true, if not saved, need to validate from, to and as on date 
		    if(flagChecked=='false' && s[0]=='saved' ){  //custom flag not checked yet
		    	
		    	if( s[1]=='true' ){
		    		flagChecked = true;
		    	}
		    	else{
		    	//validate from, to and as on date cannot be blank
		    		 if( !$('input[name=fromdate]').val() || !$('input[name=todate]').val() || !$('input[name=asondate]').val()){
		    			 alert(" from, to and as on date are required");
		    			 errors++;
		    			 return false;
		    		 }
		    	
		    	}
		    }
		    var input = $("<input>").attr("type", "hidden").attr("name", s[0]).val(s[1]);
	        $('#sidemenu').append($(input));
		});
		
		
		//TODO put validation : for custom section, from, to and as on date are mandatory fields			
		   if(errors === 0){
				$('#sidemenu').attr('action',destination);
				$('#sidemenu').submit();
		   }
	});
	
	setSysdate('input[name=asondate]');
	
	});
</script>




	<style type="text/css">

		/* Styles added for side menu */
 	@import url(http://fonts.googleapis.com/css?family=Open+Sans:400,600,300);
@charset "UTF-8";
/* Base Styles */
#cssmenu,
#cssmenu ul,
#cssmenu li,
#cssmenu a {
  margin: 0;
  padding: 0;
  border: 0;
  list-style: none;
  font-weight: normal;
  text-decoration: none;
  line-height: 1;
  font-family: 'Open Sans', sans-serif;
  font-size: 14px;
  position: relative;
}
#cssmenu a {
  line-height: 1.3;
}
#cssmenu {
  width: 250px;
  background: #fff;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  border-radius: 4px;
  padding: 3px;
  -moz-box-shadow: 0 0 5px rgba(0, 0, 0, 0.6);
  -webkit-box-shadow: 0 0 5px rgba(0, 0, 0, 0.6);
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.6);
}
#cssmenu > ul > li {
  margin: 0 0 2px 0;
}
#cssmenu > ul > li:last-child {
  margin: 0;
}
#cssmenu > ul > li > a {
  font-size: 15px;
  display: block;
  color: #ffffff;
  text-shadow: 0 1px 1px #000;
  background: #565656;
  background: -moz-linear-gradient(#565656 0%, #323232 100%);
  background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #565656), color-stop(100%, #323232));
  background: -webkit-linear-gradient(#565656 0%, #323232 100%);
  background: linear-gradient(#565656 0%, #323232 100%);
  border: 1px solid #000;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  border-radius: 4px;
}
#cssmenu > ul > li > a > span {
  display: block;
  border: 1px solid #666666;
  padding: 6px 10px;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  border-radius: 4px;
  font-weight: bold;
}
#cssmenu > ul > li > a:hover {
  text-decoration: none;
}
#cssmenu > ul > li.active {
  border-bottom: none;
}
#cssmenu > ul > li.active > a {
  background: #97be10;
  background: -moz-linear-gradient(#97be10 0%, #79980d 100%);
  background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #97be10), color-stop(100%, #79980d));
  background: -webkit-linear-gradient(#97be10 0%, #79980d 100%);
  background: linear-gradient(#97be10 0%, #79980d 100%);
  color: #fff;
  text-shadow: 0 1px 1px #000;
  border: 1px solid #79980d;
}
#cssmenu > ul > li.active > a span {
  border: 1px solid #97be10;
}
#cssmenu > ul > li.has-sub > a span {
  background: url(/Locker_Financial_Society/css/images/icon_plus.png) 98% center no-repeat;
}
#cssmenu > ul > li.has-sub.active > a span {
  background: url(/Locker_Financial_Society/css/images/icon_minus.png) 98% center no-repeat;
}
/* Sub menu */
#cssmenu ul ul {
  padding: 5px 12px;
  display: block;
}
#cssmenu ul ul li {
  padding: 3px 0;
}
#cssmenu ul ul a {
  display: block;
  color: #595959;
  font-size: 13px;
  font-weight: bold;
}
#cssmenu ul ul a:hover {
  color: #79980d;
}
</style>
</head>
<body>
	<form name="sidemenu" id="sidemenu" >
	<div id='cssmenu' style="float:left">
	
		<ul>
			<li class='has-sub active' id="saved"><a href='#'
				title="System will generate report for current year"><span>Saved
						Report</span></a>
				<ul>
					<li><a href='?reload=true&saved=true&id=bill_1'><span>Current Year Outstanding</span></a></li>
					<li><a href='?reload=true&saved=true&id=bill_2'><span>Total Outstanding</span></a></li>
					<li class='last'><a href='?reload=true&saved=true&id=bill_3'><span>Current year
								Bill Raised</span></a></li>
					
				</ul></li>
			<li class='has-sub active' id="custom"><a href='#'><span>Custom Report</span></a>
				<ul>
					<li><span>Enter Period</span><br>
					<input type="date" placeholder="From Date" name="fromdate" value="${param.fromdate }">TO<input
						type="date" placeholder="To Date" name="todate" value="${param.todate }"></li>
					<li><span>As On Date</span><br>
					<input type="date" placeholder="As on Date" name="asondate" value="${param.todate }"></li>
					<li><a href='?reload=true&saved=false&id=bill_4'><span>Outstanding Bill</span></a></li>
					<li><a href='?reload=true&saved=fals&id=bill_5'><span>Bill Raised</span></a></li>

				</ul></li>
		</ul>
		
	</div></form>
</body>
</html>