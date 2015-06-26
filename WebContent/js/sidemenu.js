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
			
			$.each( keyValuePairs, function( index, value ){
			    var keyValue = value;
			    
			    //split with =
			    var s = keyValue.split('=');			    
			    var input = $("<input>").attr("type", "hidden").attr("name", s[0]).val(s[1]);
		        $('#sidemenu').append($(input));
			});
			
			
			//TODO put validation : for custom section, from, to and as on date are mandatory fields			
			
			$('#sidemenu').attr('action',destination);
			$('#sidemenu').submit();
		});
		
		});