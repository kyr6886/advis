var _context="/ndmi";
var urlMapping=function(url){
	return _context+"/contents/base/templates/"+url;
};

var vwolrdKey=""
var vworldMap=[];
var commonComplete=function(data){
	if(location.href.split("/")[2].split(":")[0] == 'localhost'){
		commonLog(data);
	}
	commonCheckException(data);
}
var commonAlert=function(msg,type){
	alert(msg);
};

var commonLog=function(data){
	console.log(data);
};
var fnNumberFix=function(arr,n){
	var rs=[];
	for(var i=0;i<arr.length;i++){
		rs.push( parseFloat((arr[i]).toFixed(n)));
	}
	return rs;
}

var fnPrintDate=function(_date){
	
	var _month=_date.getMonth();
	_month="0"+(_month+1);
	var _day="0"+_date.getDate();
	
	return _date.getFullYear()+"-"+_month.substring(_month.length-2,_month.length)+"-"+_day.substring(_day.length-2,_day.length);
	
}
var fnPrintDateByNum=function(_date){
	if(_date!=null && _date!=""){
	var year=_date.substring(0,4);
	var month=_date.substring(4,6);
	var day=_date.substring(6,8);
	return year+"-"+month+"-"+day;
	}
	return "";
}
var fnConvertDateStr=function(_date,exp){
	var _date = new Date(_date);
	var month = _date.getMonth() + 1;
	var day = _date.getDate();
	var year = _date.getFullYear();
	var _month = month < 10 ? "0"+ month: month;
	var _day = day < 10 ? "0"+ day: day;
	if(exp == null){
		return year+ "/"+ _month+ "/" + _day;
	}else{
		return year+ exp+ _month+ exp + _day;
	}
}
var fnConvertStrDate=function(str,exp){
	var year;
	var month;
	var day;
	if(exp == null){
		year = str.substring(0,4);
		month = str.substring(4,6);
		day = str.substring(6,8);  
	}else{
		str=str.split(exp);
		year = str[0];
		month = str[1];
		day = str[2];  
	}
	return new Date(year,month,day);
}

var fnConvertCarrigeReturn=function(str){
	var re = /\r\n/g;
	return  str.replace(re, "<br/>"); 
}

var commonCheckException=function(data){
	if(data.hasOwnProperty("error")){
		var form = document.createElement("form");
	    form.setAttribute("method", "post");
	    form.setAttribute("action", "/error");
		var params={'resultMessage':data.error};
	    for(var key in params) {
	        if(params.hasOwnProperty(key)) {
	            var hiddenField = document.createElement("input");
	            hiddenField.setAttribute("type", "hidden");
	            hiddenField.setAttribute("name", key);
	            hiddenField.setAttribute("value", params[key]);
	            form.appendChild(hiddenField);
	         }
	    }

	    document.body.appendChild(form);
	    form.submit();
		
		
		
	}else{
		if(data.resultCount>9000){
			alert(data.resultMessage);
			return false;
		}
	}
};

var cloneObject=function(obj) {
    if (null == obj || "object" != typeof obj) return obj;
    var copy = obj.constructor();
    for (var attr in obj) {
        if (obj.hasOwnProperty(attr)) copy[attr] = obj[attr];
    }
    return copy;
}


String.prototype.isEmpty = function() {
	var rs=(this == null || this === ""|| !/\S/.test(this));
	return rs;
};
Number.prototype.format=function(p){
	var rs="";
	switch (p) {
	case "00":
		rs=this<10?"0"+this:this;
		break;

	default:
		break;
	}
	return rs;
}



$.fn.rowspan = function(colIdx, isStats) {       
	return this.each(function(){      
		var that;     
		$('tr', this).each(function(row) {      
			$('td:eq('+colIdx+')', this).filter(':visible').each(function(col) {
				
				if ($(this).html() == $(that).html()
					&& (!isStats 
							|| isStats && $(this).prev().html() == $(that).prev().html()
							)
					) {            
					rowspan = $(that).attr("rowspan") || 1;
					rowspan = Number(rowspan)+1;

					$(that).attr("rowspan",rowspan);
					
					// do your action for the colspan cell here            
					$(this).hide();
					
					//$(this).remove(); 
					// do your action for the old cell here
					
				} else {            
					that = this;         
				}          
				
				// set the that if not already set
				that = (that == null) ? this : that;      
			});     
		});    
	});  
}; 



var noaaAdmin=angular.module('noaaAdmin', ['ngRoute','ngSanitize','ngFileUpload']);

noaaAdmin.directive('onFinishRender', function ($timeout) {
    return {
        restrict: 'A',
        link: function (scope, element, attr) {
        	
            if (scope.$last === true) {
                $timeout(function () {
                    scope.$emit(attr.onFinishRender);
                });
            }
        }
    }
});


noaaAdmin.run(['$rootScope','$http',function($rootScope,$http){
	$rootScope.fnOnFileDownload=function(seq){
		window.location=_context+'/file/download/'+seq;
	}
	
	$rootScope.fnOnExcelDownloadload=function(paramPage){
		$http.post(_context+"/api/export/excel",{paramPage:paramPage})
		.success(function(data){
			window.location=_context+'/file/download/'+data.downSeq;
		});
	}
}]);

// kr-input
noaaAdmin.directive('krInput', ['$parse', function($parse) {
    return {
        priority : 2,
        restrict : 'A',
        compile : function(element) {
            element.on('compositionstart', function(e) {
                e.stopImmediatePropagation();
            });
        },
    };
}]);
