noaaAdmin.controller('menuLogCtrl',['$scope', '$http', '$routeParams', '$location',function($scope, $http, $routeParams, $location){
	var totalCount=0;
	$scope.menuLogList=[];
	$scope.dateS='';
	$scope.dateE='';
	//페이징 관련
	$scope.pager={totalCount:0,rowSize:10,blockSize:10,currentPage:1,dateS:'',dateE:''};
	$scope.fnInit = function(){
		$scope.fnListLog();
	}
	$scope.fnListLog=function(paramPage,paramStartDate,paramEndDate){
		paramPage=paramPage==null ? $scope.pager.currentPage : paramPage;
		$http.post(_context+"/api/admin/menu/log/list",{
			pageNo:paramPage,
			pageSize:$scope.pager.rowSize,
			paramStartDate: paramStartDate,
			paramEndDate: paramEndDate
			}).success(function(data){
				commonComplete(data)
				var result;
				for(var i=0;i<data.listMenuLog.length;i++){
					data.listMenuLog[i].conn_day = fnPrintDateByNum(data.listMenuLog[i].conn_day);
				}
				$scope.menuLogList = data.listMenuLog;
				if(paramStartDate == undefined || paramStartDate == null ){
					$scope.dateS = fnPrintDateByNum(data.paramStartDate);
				}
				if(paramEndDate == undefined || paramEndDate == null ){
					$scope.dateE = fnPrintDateByNum(data.paramEndDate);
				}
				$scope.pager={
						totalCount:data.totalCount
						,rowSize:10
						,blockSize:10
						,currentPage:paramPage
						,dateS:$scope.dateS
						,dateE:$scope.dateE
				};
		});
	}
	//검색클릭
	$scope.fnOnSearchClick=function(){
		if($scope.dateS == '') $scope.dateS = null;
		if($scope.dateE == '') $scope.dateE = null;
		if($scope.dateS !=null && $scope.dateE !=null && (fnConvertStrDate($scope.dateE,'-')-fnConvertStrDate($scope.dateS,'-')<0)){
			commonAlert("종료일자가 시작일자보다 작습니다.","alert");
			return false;
		}else{
			$scope.fnListLog(1,$scope.dateS,$scope.dateE);
		}
	}
	//페이지 클릭
	$scope.fnOnPagingClick=function(num){
		$scope.fnListLog(num,$scope.pager.dateS,$scope.pager.dateE);
	}
	//상세페이지 이벤트
	$scope.fnLogDetailInit = function(){
		$scope.userId=$routeParams.userId;
		$scope.fnLogDetail();
	}
	//상세ajax
	$scope.fnLogDetail=function(paramStartDate,paramEndDate){
		paramStartDate=paramStartDate==null ? $routeParams.dateS : paramStartDate;
		paramEndDate=paramEndDate==null ? $routeParams.dateE : paramEndDate;
		$http.post(_context+"/api/account/log/detail",{
			paramStartDate: paramStartDate,
			paramEndDate: paramEndDate,
			paramUserID: $scope.userId
			}).success(function(data){
				commonComplete(data)
				var result;
				for(var i=0;i<data.listLoginLog.length;i++){
					data.listLoginLog[i].update_date = fnConvertDateStr(data.listLoginLog[i].update_date);
				}
				$scope.dateS = paramStartDate;
				$scope.dateE = paramEndDate;
				$scope.logDetailList = data.listLoginLog;
				var ctx = document.getElementById("visitLine").getContext("2d");
			    var chartLabels=[];
			    var chartData=[];
			    for(var i=data.listLoginLog.length-1;i>=0;i--){
			    	  chartLabels.push(data.listLoginLog[i].update_date);
			    	  chartData.push(data.listLoginLog[i].hit_count);
			      }
			      var lineChart = new Chart(ctx, {
			        type: 'line',
			        options:{responsive:true,maintainAspectRatio : false},
			        data: {
			          labels: chartLabels,
			          datasets: [{
			            label: "접속 횟수",
			            backgroundColor: "rgba(38, 185, 154, 0.31)",
			            borderColor: "rgba(38, 185, 154, 0.7)",
			            pointBorderColor: "rgba(38, 185, 154, 0.7)",
			            pointBackgroundColor: "rgba(38, 185, 154, 0.7)",
			            pointHoverBackgroundColor: "#fff",
			            pointHoverBorderColor: "rgba(220,220,220,1)",
			            pointBorderWidth: 1,
			            data: chartData
			          }]
			        },
			      });
		});
	};
	$scope.fnOnDetailSearchClick = function(){
		if($scope.dateS == '') $scope.dateS = null;
		if($scope.dateE == '') $scope.dateE = null;
		$scope.fnLogDetail($scope.dateS,$scope.dateE);
	};
	$scope.fnOnBackList = function(){
		$location.path('/userLogList');
	}
}]);