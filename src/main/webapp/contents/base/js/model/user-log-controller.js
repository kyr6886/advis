noaaAdmin.controller('userLogCtrl',['$scope', '$http', '$routeParams', '$location',function($scope, $http, $routeParams, $location){
	var totalCount=0;
	$scope.logList=[];
	$scope.userId;
	$scope.dateS='';
	$scope.dateE='';
	$scope.userName;
	$scope.conn_cnt_tot;
	$scope.avg_use_time;
	//페이징 관련
	$scope.pager={totalCount:0,rowSize:10,blockSize:10,currentPage:1,userId:'',userName:'',dateS:'',dateE:''};
	$scope.fnInit = function(){
		$scope.fnListLog(null,null);
	}
	$scope.fnListLog=function(paramID,paramPage,paramName,paramStartDate,paramEndDate){
		paramPage=paramPage==null ? $scope.pager.currentPage : paramPage;
		$http.post(_context+"/api/account/log/list",{
			id:paramID,
			pageNo:paramPage,
			pageSize:$scope.pager.rowSize,
			paramStartDate: paramStartDate,
			paramEndDate: paramEndDate,
			paramUserName : paramName
			}).success(function(data){
				commonComplete(data)
				var result;
				for(var i=0;i<data.listLoginLog.length;i++){
					data.listLoginLog[i].update_date = fnConvertDateStr(data.listLoginLog[i].update_date,'-');
				}
				$scope.logList = data.listLoginLog;
				if(paramStartDate == undefined || paramStartDate == null ){
					$scope.dateS = fnPrintDateByNum(data.paramStartDate);
				}
				if(paramEndDate == undefined || paramEndDate == null ){
					$scope.dateE = fnPrintDateByNum(data.paramEndDate);
				}
				$scope.pager={
						totalCount:data.resultCount
						,rowSize:10
						,blockSize:10
						,currentPage:paramPage
						,userId:paramID
						,userName:paramName
						,dateS:$scope.dateS
						,dateE:$scope.dateE
				};
		});
	}
	//검색클릭
	$scope.fnOnSearchClick=function(){
		if($scope.userId == '') $scope.userId = null;
		if($scope.userName == '') $scope.userName = null;
		if($scope.dateS == '') $scope.dateS = null;
		if($scope.dateE == '') $scope.dateE = null;
		if($scope.dateS !=null && $scope.dateE !=null && (fnConvertStrDate($scope.dateE,'-')-fnConvertStrDate($scope.dateS,'-')<0)){
			commonAlert("종료일자가 시작일자보다 작습니다.","alert");
			return false;
		}else{
			$scope.fnListLog($scope.userId,1,$scope.userName,$scope.dateS,$scope.dateE);
		}
		//$scope.searchObj={userId:$scope.userId,userName:$scope.userName,dateS:startDate,dateE:endDate};
	}
	//페이지 클릭
	$scope.fnOnPagingClick=function(num){
		$scope.fnListLog($scope.pager.userId,num,$scope.pager.userName,$scope.pager.dateS,$scope.pager.dateE);
	}
	
	//상세페이지 이벤트
	$scope.fnLogDetailInit = function(){
		$scope.userId=$routeParams.userId;
		$scope.fnLogDetail();
	}
	//상세ajax
	$scope.fnLogDetail=function(paramStartDate,paramEndDate,paramPage){
		paramPage=paramPage==null ? $scope.pager.currentPage : paramPage;
		paramStartDate=paramStartDate==null ? $routeParams.dateS : paramStartDate;
		paramEndDate=paramEndDate==null ? $routeParams.dateE : paramEndDate;
		
		$http.post(_context+"/api/account/log/detail",{
			pageNo:paramPage,
			pageSize:$scope.pager.rowSize,
			paramStartDate: paramStartDate,
			paramEndDate: paramEndDate,
			paramUserID: $scope.userId
			}).success(function(data){
				commonComplete(data)
				var result;
				for(var i=0;i<data.listLoginLog.length;i++){
					data.listLoginLog[i].update_date = fnConvertDateStr(data.listLoginLog[i].update_date);
				}
				for(var i=0;i<data.chartLoginLog.length;i++){
					data.chartLoginLog[i].update_date = fnConvertDateStr(data.chartLoginLog[i].update_date);
				}
				$scope.dateS = paramStartDate;
				$scope.dateE = paramEndDate;
				$scope.logDetailList = data.listLoginLog;
				$scope.conn_cnt_tot = data.conn_cnt_tot;
				$scope.avg_use_time = data.avg_use_time
				$scope.pager={
						totalCount:data.resultCount
						,rowSize:10
						,blockSize:10
						,currentPage:paramPage
						,userId:$scope.userId
						,dateS:paramStartDate
						,dateE:paramEndDate
				};
				
				var ctx = document.getElementById("visitLine").getContext("2d");
			    var chartLabels=[];
			    var chartData=[];
			    for(var i=data.chartLoginLog.length-1;i>=0;i--){
			    	  chartLabels.push(data.chartLoginLog[i].update_date);
			    	  chartData.push(data.chartLoginLog[i].hit_count);
			      }
			      var lineChart = new Chart(ctx, {
			        type: 'line',
			        options:{responsive:false,maintainAspectRatio : false},
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
	//페이지 클릭(상세보기페이지)
	$scope.fnOnDetailPagingClick = function(num){
		$scope.fnLogDetail($scope.pager.dateS,$scope.pager.dateE,num);
	}
	//검색(상세보기 페이지)
	$scope.fnOnDetailSearchClick = function(){
		if($scope.dateS == '') $scope.dateS = null;
		if($scope.dateE == '') $scope.dateE = null;
		if($scope.dateS !=null && $scope.dateE !=null && fnConvertStrDate($scope.dateE,'-')-fnConvertStrDate($scope.dateS,'-')<0){
			commonAlert("종료일자가 시작일자보다 작습니다.","alert");
			return false;
		}else{
			$scope.fnListLog($scope.userId,1,$scope.userName,$scope.dateS,$scope.dateE);
		}
		$scope.fnLogDetail($scope.dateS,$scope.dateE);
	};
	$scope.fnOnBackList = function(){
		$location.path('/userLogList');
	}
}]);