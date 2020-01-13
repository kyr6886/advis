noaaAdmin.controller('indexCtrl',['$scope', '$http', '$routeParams', '$location',function($scope, $http, $routeParams, $location){
	$scope.avg_use_time;
	$scope.cur_user_cnt;
	$scope.user_tot_cnt;
	$scope.listBbs=[];
	$scope.listMenuLog=[];
	$scope.fnMenuLog=function(){
	
		$http.post(_context+"/api/system/manage/default/load",{
			paramAvgTime:5
			,pageNo:1
			,pageSize:10
		}).success(function(data){
			commonComplete(data);
			$scope.listBbs= data.listBbsContent;
			$scope.listMenuLog=data.listMenuLog;
			$scope.avg_use_time = data.avg_use_time;
			$scope.cur_user_cnt = data.cur_user_cnt;
			$scope.user_tot_cnt = data.user_tot_cnt;
			
			for(var i=0;i<$scope.listMenuLog.length;i++){
				$scope.listMenuLog[i].conn_day=fnPrintDateByNum($scope.listMenuLog[i].conn_day);
			}
			
			for(var i=0;i<$scope.listBbs.length;i++){
				$scope.listBbs[i].create_date=fnConvertDateStr($scope.listBbs[i].create_date);
			}
			
			// Line chart
		      var ctx = document.getElementById("visitLine").getContext("2d");;
		      var chartLabels=[];
		      var chartData=[];
		      
		      for(var i=0;i<data.listVisitMenuLog.length;i++){
		    	  chartLabels.push(data.listVisitMenuLog[i].conn_day);
		    	  chartData.push(data.listVisitMenuLog[i].visitCount);
		      }
		      
		      var lineChart = new Chart(ctx, {
		        type: 'line',
		        options:{responsive:true,maintainAspectRatio : false},
		        data: {
		          labels: chartLabels,
		          datasets: [{
		            label: "접속자 수(명)",
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
	}
}]);