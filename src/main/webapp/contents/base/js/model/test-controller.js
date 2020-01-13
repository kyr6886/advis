noaaAdmin.controller('testCtrl',['$scope', '$http', '$routeParams', '$location','Upload',function($scope, $http, $routeParams, $location , Upload){

	$scope.resultList = [];
	$scope.list = [];
	$scope.yearList = [];
	$scope.monthList = [];
	$scope.categoryList = [];
	
	$scope.fnInit = function(){
		$http.post(_context+"/api/std/eventItem/searchItem",{paramTitle:"태풍"})
		.success(function(data){
			$scope.resultList = data.setResultSearchItem;
			var yearArr = new Array();
			for(var i=0; i<$scope.resultList.length; i++) {
				yearArr.push($scope.resultList[i].evt_date.substring(0,4));
			}
			$scope.yearList = uniqueFunction(yearArr);
		});
	}
	
	$scope.fnOnChangeYear = function() {
		$scope.selectMonth =  "";
		$scope.selectEvtId =  "";
		$http.post(_context+"/api/std/eventItem/searchItem",{paramTitle:"태풍",paramYear:$scope.selectYear })
		.success(function(data){
			
			$scope.resultList = data.setResultSearchItem;
			var monthArr = new Array();
			
			for(var i=0; i<$scope.resultList.length; i++) {
				monthArr.push($scope.resultList[i].evt_date.substring(4,6));
			}
			$scope.monthList = uniqueFunction(monthArr);
		});		
		
	}
	
	$scope.fnChangeMonth = function () {
		$scope.selectEvtId =  "";
		var paramYear = $scope.selectYear;
		var paramMonth = $scope.selectMonth;
		
		$http.post(_context+"/api/std/eventItem/searchItem",{paramTitle:"태풍",paramYear:paramYear,paramMonth:paramMonth})
		.success(function(data){
			$scope.resultList = data.setResultSearchItem;
			
		});
	}
	
	$scope.fnChangeEvtId=function () {
		
		$http.post(_context+"/api/std/advisSearch/searchResult",{paramEvtId:$scope.selectEvtId})
		.success(function(data){
			$scope.categoryList =  data.setResultSearchItem;
		});
		
	}
	
	function uniqueFunction (param) {
		return param.slice().sort(function(a,b){return a - b}).reduce(function(a,b){if (a.slice(-1)[0] !== b) a.push(b);return a;},[]);
	}
	
}]);


