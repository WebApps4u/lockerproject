/**
 * This file is only meant to be used for receipt generations
 * As it contains many calculations and validations, this has been separated out
 */
//equivalent of $(document).ready(function(){...
$(function() {


	//set Bill date as sysdate if it is not already set
	if($('input[name=RCTD]').val()==""){
		$('input[name=RCTD]').val($.datepicker.formatDate('yy-mm-dd',new Date()));
	}
	
	//populate key details section
	function populateForm($form, data) {
		// resetForm($form);
		$.each(data, function(key, value) {
			var $ctrl = $form.find('[name="' + key.toUpperCase() + '"]');
			if ($ctrl.is('select')) {
				$('option', $ctrl).each(function() {
					if (this.value == value)
						this.selected = true;
				});
			} else if ($ctrl.is('textarea')) {
				$ctrl.val(value);
			} else {
				switch ($ctrl.attr("type")) {
				case "text":
				case "hidden":
				case "email":
				case "number":
				case "date":
					$ctrl.val(value);
					break;
				case "checkbox":
					if (value == '1')
						$ctrl.prop('checked', true);
					else
						$ctrl.prop('checked', false);
					break;
				}
			}
		});
	}


	//Create dynamic table with all the bill details
	function populateBillDetails(billDetails) {

		
		var dynatable = $('#billRecords').dynatable({
            dataset: {
                records: billDetails
            }
        }).data('dynatable');

		//to clear old table data and use new data
        dynatable.settings.dataset.originalRecords = billDetails;
        dynatable.process();

		$('#billRecords tbody').on('click', 'tr', function() {

			$(this).toggleClass('selected');
			$('td', this).toggleClass('selected');
			
			//updateParticulars with bills
			updateParticularsWithBills();
		});
	}
	
	//Update the particulars with selected bills
	function updateParticularsWithBills(){
		
		//check if no bill is selected
		
		var allBills = $('#billRecords tbody tr.selected');
		
		//if size of selected bills is 0, no bills has been selected
		//update the particulars with Key details
		if (allBills == null || allBills.size() == 0){
			
			//get locker rent, outstanding, advance
			//and set to the particulars
			
			populateParticulars($('input[name="LOKR"]').val()    //locker rent
								,$('input[name="POA"]').val() //outstanding balance
								,$('input[name="LPA"]').val()); //advance
			
			calculateTotal();
		}
		else{
			//sum of the selected bills locker rent, advances, outstanding
			//and update the particulars
			var lokr = 0;
			var outstanding = 0;
			var advance = 0;
			var bills = "";
			$.each(allBills,function(key,value){
				
				if(bills || bills != ""){
				bills = bills + " ";       //append space only when required
				}
				bills = bills + value.children[0].textContent;  //   "B-1234 B-2123"  //white space separated multiple bills
				
				lokr += parseFloat(value.children[1].textContent) || 0;
				outstanding += parseFloat(value.children[2].textContent) || 0;
				advance += parseFloat(value.children[3].textContent) || 0;
				
			});
			
			//update particulars
			populateParticulars(lokr, outstanding, advance);
			
			//calculate total
			calculateTotal();
			
			//add the selected bill id to be submit to server
			$('#frm_receiptDetails').append('<input type="hidden" name="RBNO" value="'+bills+'" />');
		}
	}
	
    // Get all the key details with unpaid bills
	function getDetails(keynum) {
		//get ajax call
		$.get(
				'/Locker_Financial_Society/rest/lockerservice/unpaidbills/'
						+ keynum // rest api
		// key for which record is to be fetched		
		).done(function(data) { // data is returned from the server

			//populate fields with the recieved data
			populateForm($('div[id=keyDetails]'), data);

			//populateKeyDetails(data);
			//populateBillDetails(data.bills);
			populateBillDetails(data.bills);
			
			//populate particulars
			populateParticulars(data.LOKR,data.POA, data.LPA);
			
			//populateParticulars(data);
		}).fail(function(error) {
			//log error to console
			console.log(error);
		})
	}

	//call the rest api to get details for the given key num
	$('#getDetails').on('click', function() {

		var keynum = $('input[name="RKNO"]').val();
		//clearform();
		getDetails(keynum);
	})
	
	//Submit receipt data to create new receipt
	$('#submitNewReceipt').on(
				'click',
				function(e) {
                    
					e.preventDefault();
					$.ajax({
				           type: "POST",
				           url: "/Locker_Financial_Society/rest/lockerservice/receipt/",
				           contentType: "application/json; charset=utf-8",
				           dataType: "json",
				           success: function (data) {
				        	   
				        	   //check the status of the response
				        	   if(data.status == "SUCCESS"){
									console.log("successfully updated");
									//remove all classes
									$('#msg').removeClass();
									$('#msg').addClass("successmsg");
									$('#msg').html(data.successMsg);
									
									$('input[name=RCTN]').val(data.obj);
				        	   }
				        	   else{
				        		   console.log("error in updated updated"+data.errMsg);
				        		   $('#msg').removeClass();
									$('#msg').addClass("errmsg");
									$('#msg').html(data.errMsg);
				        	   }
				        	   
				        	   
				           },
				           error : function(response, ajaxOptions, thrownError){
				        	   console.log(thrownError)
				           },
				           data: ConvertFormToJSON($('form[id=frm_receiptDetails]'))
				       });

				})

	
	//populate the particulars section with the data
	//need to get
	//1st - Locker rent
	//2nd - Outstanding
	//3rd - Advance
    function populateParticulars(rrnt, rpout, radvp){
		
		//replace the value 
		//locker rent 
		$('input[name="RRNT"]').val(rrnt);   //locker rent
		$('input[name="RPOUT"]').val(rpout); //outstanding balance
		$('input[name="RADVP"]').val(radvp); //advance
		
		//calculate total
		calculateTotal();
	}
	
	//calculate all the sections
	function calculateTotal(){
		
		// get all the particulars amounts
		var lockerRent = parseFloat($('input[name="RRNT"]').val()) || 0;    //adding pipe to treat empty string as 0
		var advancePay = parseFloat($('input[name="RADVP"]').val()) || 0;
		var outstanding = parseFloat($('input[name="RPOUT"]').val()) || 0;
		
		var interest = parseFloat($('input[name="RINT"]').val()) || 0;
		var misc = parseFloat($('input[name="RINC"]').val()) || 0;
		var bankCharges = parseFloat($('input[name="RBC"]').val()) || 0;
		
		var appFee = parseFloat($('input[name="RAPF"]').val()) || 0;
		var legalFee = parseFloat($('input[name="RGLF"]').val()) || 0;
		var advances = parseFloat($('input[name="RDAV"]').val()) || 0;
		
		var accessCharges = parseFloat($('input[name="RACH"]').val()) || 0;
		var suspense = parseFloat($('input[name="RSUS"]').val()) || 0;
		var breakingCharges = parseFloat($('input[name="BRKCH"]').val()) || 0;
		
		var serviceTax = parseFloat($('input[name="STAXR"]').val()) || 0;
		
		var totalAmount = lockerRent;
						 	
		//only this place is required to round off
		var serviceTaxAmount = roundOff(((totalAmount* serviceTax)/100));
		
		
		//set the tax calculated
		$('input[name="STAXA"]').val(serviceTaxAmount);
		
		totalAmount = subtract(totalAmount+interest+misc+bankCharges+appFee+legalFee+advances+accessCharges+suspense+breakingCharges+serviceTaxAmount+outstanding,advancePay);
		//totalAmount = totalAmount+serviceTaxAmount;
		//set the total
		$('input[name="RGTOT"]').val(totalAmount);
		
		//TODO
		//get the amount paid
		var amountPaid = parseFloat($('input[name="RAMT"]').val()) || 0;
		
		//set the outstanding or advance amount for the key
		//if ramt > totalAmount, advance, else outstanding
		if(totalAmount > amountPaid){
			$('input[name="RCOUT"]').val(subtract(totalAmount,amountPaid));
			$('input[name="RADV"]').val(0);
		}
		else if(totalAmount < amountPaid){
			$('input[name="RADV"]').val(subtract(amountPaid,totalAmount));
			$('input[name="RCOUT"]').val(0);
		}
		else{
			//Clear outstanding and advance
			$('input[name="RADV"]').val(0);
			$('input[name="RCOUT"]').val(0);
		}
	}
	
	//on change of any input inside particulars, invoke calculateTotal
	$('#particulars input').on('change', function(){
		
		//recalculate total
		calculateTotal();
	})
});
