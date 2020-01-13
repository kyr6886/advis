var _context="";
var urlMapping=function(url){
	return _context+"/contents/base/templates/"+url;
};

var vwolrdKey=""
var vworldMap=[];
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

var fnPrintDateTimeByNum=function(_date){
	if(_date!=null && _date!=""){
	var year=_date.substring(0,4);
	var month=_date.substring(4,6);
	var day=_date.substring(6,8);
	var hh=_date.substring(8,10);
	var min=_date.substring(10,12);
	return year+"-"+month+"-"+day+" "+hh+":"+min;
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
var commonCheckException=function(data){
	if(data.hasOwnProperty("error")){
		var form = document.createElement("form");
	    form.setAttribute("method", "post");
	    form.setAttribute("action", "/error");
		var params={'resultMessage':data.error,'statusCode':data.statusCode,'reason':data.reason};
	    for(var key in params) {
	        if(params.hasOwnProperty(key)) {
	            var hiddenField = document.createElement("input");
	            hiddenField.setAttribute("type", "hidden");
	            hiddenField.setAttribute("name", key);
	            hiddenField.setAttribute("value", params[key]);
	            form.appendChild(hiddenField);
	         }
	    }
	    console.log(data);
	    //document.body.appendChild(form);
	   //form.submit();
		
		
		
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
Number.prototype.toDate = function() {
	console.log(this);
	return fnConvertDateTimeStr(this,"-");
	
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



var noaaAdmin=angular.module('noaaAdmin', ['ngRoute','ngSanitize','ngFileUpload']);
noaaAdmin.run(['$rootScope','$http',function($rootScope,$http){
	
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

noaaAdmin.config(function($routeProvider){
    $routeProvider
    .when('/',{templateUrl:urlMapping('/index.html'),controller:'indexCtrl'})
    .when('/userList',{templateUrl:urlMapping('user/userList.html'),controller:'userCtrl'})
    .when('/userLogList',{templateUrl:urlMapping('user/userLogList.html'),controller:'userLogCtrl'})
    .when('/userLog/Detail/:userId/:dateS/:dateE',{templateUrl:urlMapping('user/userLogDetail.html'),controller:'userLogCtrl'})
    .when('/codeList',{templateUrl:urlMapping('code/codeList.html'),controller:'codeCtrl'})
    .when('/menuList',{templateUrl:urlMapping('menu/menuList.html'),controller:'menuCtrl'})
    .when('/menuRoleMng',{templateUrl:urlMapping('menu/menuRoleMng.html'),controller:'menuCtrl'})
    .when('/menuLogList',{templateUrl:urlMapping('menu/menuLogList.html'),controller:'menuLogCtrl'})
    .when('/bbsList',{templateUrl:urlMapping('bbs/bbsList.html'),controller:'bbsCtrl'})
    .when('/bbsContentList/:bbsID?/:pageNo?',{templateUrl:urlMapping('bbs/bbsContentList.html'),controller:'bbsContentCtrl'})
    .when('/bbsContent/Detail/:bbsID/:seq/:pageNo?',{templateUrl:urlMapping('bbs/bbsContentDetail.html'),controller:'bbsContentCtrl'})
    .when('/bbsContentWrite/:bbsID',{templateUrl:urlMapping('bbs/bbsContentWrite.html'),controller:'bbsContentCtrl'})
    .when('/bbsContentUpdate/:bbsID/:seq/:pageNo?',{templateUrl:urlMapping('bbs/bbsContentUpdate.html'),controller:'bbsContentCtrl'})
    .when('/metaList',{templateUrl:urlMapping('code/metaList.html'),controller:'metaCtrl'})
    .when('/partnerList/:messageCode?',{templateUrl:urlMapping('partner/partnerList.html'),controller:'partnerCtrl'})
    .when('/dis/manage/code',{templateUrl:urlMapping('dis/manageDisCode.html'),controller:'managedisCtrl'})
    .when('/dis/manage/ocr',{templateUrl:urlMapping('dis/manageDisOCR.html'),controller:'ocrCtrl'})
    .when('/dis/manage/status',{templateUrl:urlMapping('dis/manageDisStatus.html'),controller:'manageStatusCtrl'})
    .when('/dis/manage/test',{templateUrl:urlMapping('dis/test.html'),controller:'testCtrl'})
    .when('/data/kmaTyphoon',{templateUrl:urlMapping('data/kmaTyphoon.html'),controller:'kmaTyphoonCtrl'})
    .when('/data/kmaInform',{templateUrl:urlMapping('data/kmaInform.html'),controller:'kmaInformCtrl'})
    .when('/dis/manage/manual',{templateUrl:urlMapping('dis/manageDisManual.html'),controller:'manageDisManualCtrl'})
    .when('/dis/manage/event',{templateUrl:urlMapping('dis/manageEvent.html'),controller:'manageEventCtrl'})
});

