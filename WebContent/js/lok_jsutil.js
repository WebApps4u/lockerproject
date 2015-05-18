
/**
 * lok_jsutil.js
 * Utility javascript files
 * methods here can be reused everywhere
 */

// round offs any number, float or integer to 2 decimals 
function roundOff(amount){
	
	var decimalPlace = 100;
	
	return Math.round(amount*decimalPlace)/100;
}

// subtract two numbers, one may be float or int
function subtract(a,b){
	var decimalPlace = 100;
	return roundOff((a*decimalPlace - b*decimalPlace)/decimalPlace);
}

//Convert the form data to the json to sending to server
function ConvertFormToJSON(form){
var array = $(form).serializeArray();
var json = {};

$.each(array, function() {
    json[this.name.toLowerCase()] = this.value || '';
});
json=JSON.stringify(json);
return json;
}

//Function to print 
function printData(divToPrint)
{
   //var divToPrint=document.getElementById("printTable");
   newWin= window.open("");
   newWin.document.write(divToPrint.outerHTML);
   newWin.print();
   newWin.close();
}

//Function to click on the passing argument (should be id of button)
//on pressing enter on a text field
function clickOnEnter(e,idOfButton){
	
	if(e.keyCode==13){
	document.getElementById(idOfButton).click();
	}
	return;
}
