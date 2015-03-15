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