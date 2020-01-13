var _context='';
var commonComplete=function(data){
	commonLog(data);
	commonCheckException(data);
}
var commonAlert=function(msg,type){
	alert(msg);
};

var commonLog=function(data){
	//console.log(data);
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

var fnPrintDateTimeByNum=function(_date,exp){
	if(_date!=null && _date!=""){
		var year=_date.substring(0,4);
		var month=_date.substring(4,6);
		var day=_date.substring(6,8);
		var hh=_date.substring(8,10);
		var min=_date.substring(10,12);
		if(exp != null){
			return year+exp+month+exp+day+" "+hh+":"+min;
		}else{
			return year+"-"+month+"-"+day+" "+hh+":"+min;
		}
	}
	return "";
}
var fnPrintTimeByNum=function(_date){
	if(_date!=null && _date!=""){
	var hh=_date.substring(8,10);
	var min=_date.substring(10,12);
	return hh+":"+min;
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

var fnConvertDateTimeStr=function(_date,exp){	
	if(_date==null){
		return '';
	}
	
	var _date = new Date(_date);
	
	var month = _date.getMonth() + 1;
	var day = _date.getDate();
	var year = _date.getFullYear();
	var hh=_date.getHours();
	var min=_date.getMinutes();
	var ss=_date.getSeconds();
	day=day<10?"0"+day:day;
	hh=hh<10?"0"+hh:hh;
	min=min<10?"0"+min:min;
	ss=ss<10?"0"+ss:ss;
	var _month = month < 10 ? "0"+ month: month;
	if(exp == null){
		return year+ "/"+ _month+ "/" + day+" "+hh+":"+min+":"+ss;
	}else{
		return year+ exp+ _month+ exp + day+" "+hh+":"+min+":"+ss;
	}
}
var fnConvertYYYYMMDDHH24MI = function(_date,exp){
	if(_date==null){
		return '';
	}
	
	var _date = new Date(_date);
	
	var month = _date.getMonth() + 1;
	var day = _date.getDate();
	var year = _date.getFullYear();
	var hh=_date.getHours();
	var min=_date.getMinutes();
	day=day<10?"0"+day:day;
	hh=hh<10?"0"+hh:hh;
	min=min<10?"0"+min:min;
	var _month = month < 10 ? "0"+ month: month;
	if(exp == null){
		return year+ "/"+ _month+ "/" + day+" "+hh+":"+min;
	}else{
		return year+ exp+ _month+ exp + day+" "+hh+":"+min;
	}
};
var fnConvertTimeStr=function(_date,exp){	
	if(_date==null){
		return '';
	}
	
	var _date = new Date(_date);
	
	var hh=_date.getHours();
	var min=_date.getMinutes();
	var ss=_date.getSeconds();
	hh=hh<10?"0"+hh:hh;
	min=min<10?"0"+min:min;
	ss=ss<10?"0"+ss:ss;
	if(exp == null){
		return hh+":"+min+":"+ss;
	}else{
		return hh+exp+min+exp+ss;
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
var fnConvertStrDateTime=function(str){
	var year;
	var month;
	var day;
	var hour;
	var minute;
	year = str.substring(0,4);
	month = str.substring(4,6);
	day = str.substring(6,8);
	hour = str.substring(8,10);
	minute = str.substring(10,12);
	return new Date(year,month,day,hour,minute);
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

var baseModule=angular.module('baseModule', ['ngRoute','ngSanitize']);
baseModule.run(['$rootScope','$http',function($rootScope,$http){
	
	$rootScope.menuTitle="";
	$rootScope.menuDesc="";
	
	$rootScope.fnOnFileDownload=function(seq){
		window.location='/file/download/'+seq;
	}
	$rootScope.fnOnFileGroupDownload=function(grp){
		window.location='/file/download/group/'+grp;;
	}
	$rootScope.fnOnExcelDownloadload=function(paramPage,paramfileType,paramStartDate,paramEndDate,param1,param2,param3,param4){
		$http.post("/api/"+paramPage+"/"+paramfileType,{paramStartDate:paramStartDate,paramEndDate:paramEndDate,exportParam1:param1,exportParam2:param2,exportParam3:param3,exportParam4:param4})
		.success(function(data){
			window.location='/file/download/'+data.exportKey;
		});
	}
	$rootScope.fnOnExcelDownloadSurvey=function(paramfileType,paramEqupId,paramStartDate,paramEndDate){
		console.log("fnOnExcelDownloadSurvey"+paramfileType+"|"+paramEqupId+"|"+paramStartDate+"|"+paramEndDate);
		$http.post("/api/survey/"+paramfileType,{paramEqupID:paramEqupId,paramStartDate:paramStartDate,paramEndDate:paramEndDate})
		.success(function(data){
			console.log(data);
			window.location='/file/download/'+data.exportKey;
		});
	}
	$rootScope.fnOnExcelDownloadRseeStrc=function(paramfileType,paramStrcId, paramEqupId,paramStartDate){
		$http.post("/api/rsee/strc/info/"+paramfileType,{paramStrcId:paramStrcId,paramEqupId:paramEqupId,paramStartDate:paramStartDate})
		.success(function(data){
			console.log(data);
			window.location='/file/download/'+data.exportKey;
		});
	}
	
}]);

baseModule.directive('onFinishRender', function ($timeout) {
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
