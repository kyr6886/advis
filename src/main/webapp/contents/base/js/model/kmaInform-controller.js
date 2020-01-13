noaaAdmin.controller('kmaInformCtrl',['$scope', '$http', '$routeParams', '$location',function($scope, $http, $routeParams, $location){
	$scope.isCreateViewOn = false;
	$scope.mainDataList = [];	
	$scope.nowTime = "";
	
	$scope.pager = {
		totalCount : 0,
		rowSize : 10,
		blockSize : 10,
		pagerNums : [],
		currentPage : 1,
		endPagerNum : 0,
		startBlockNum : 1,
		endBlockNum : 1,
		preBlockNum : "",
		nextBlockNum : ""		
	};
	
	$scope.cd_stn = "";
	$scope.dt_tm_fc = "";
	$scope.nm_man_fc = "";
	$scope.etc_ttl = "";
	$scope.sect_area_txt = "";
	$scope.dt_tm_ef_txt = "";
	
	$scope.fnInit = function(){
		let date = new Date();
		$scope.nowTime = "예시 : " + date.getFullYear() + 
						(date.getMonth()>8?"":"0") + (date.getMonth()+1) +
						(date.getDate()>9?date.getDate():"0"+date.getDate()) +
						(date.getHours()>9?date.getHours():"0"+date.getHours()) +
						(date.getMinutes()>9?date.getMinutes():"0"+date.getMinutes());
		
		$scope.isCreateViewOn = false;		
		$scope.pager.pagerNums = [];
		
		$scope.cd_stn = "";
		$scope.dt_tm_fc = "";
		$scope.nm_man_fc = "";
		$scope.etc_ttl = "";
		$scope.sect_area_txt = "";
		$scope.dt_tm_ef_txt = "";
		
		$http.post("/data/kmaInform/list",{
			pageNo : $scope.pager.currentPage-1,
			pageSize : $scope.pager.rowSize
		}).success(function(data){
			$scope.mainDataList = data.informList;
			
			$scope.pager = {
				totalCount : data.totalCount,
				rowSize : 10,
				blockSize : 30,
				pagerNums : [],
				currentPage : $scope.pager.currentPage,
				endPagerNum : 0,
				startBlockNum : 1,
				endBlockNum : 1,
				preBlockNum : "",
				nextBlockNum : ""		
			};
		});
	}
	
	$scope.fnClickOpenCreateView = function(){
		$scope.isCreateViewOn = true;
	}
	
	$scope.fnClickCloseCreateView = function(){
		$scope.isCreateViewOn = false;
	}
	
	$scope.fnClickSave = function(){
		if($scope.dt_tm_fc == "" || $scope.nm_man_fc == "" ||
			$scope.etc_ttl == "" || $scope.sect_area_txt == "" ||
			$scope.dt_tm_ef_txt == "" || $scope.cd_stn == ""){
			alert("빈칸을 채워주세요.");
			return;
		}
		
		if(isNaN(Number($scope.cd_stn))){
			alert("통신소 번호는 숫자입니다.");
			return;
		}
		
		if(isNaN(Number($scope.dt_tm_fc)) || ($scope.dt_tm_fc).length != 12){
			alert("시간 형식이 잘못됐습니다.\n" + $scope.nowTime);
			return;
		}
		
		if(confirm("저장하시겠습니까?")){
			$http.post("/data/kmaInform/create",{
				cd_stn : $scope.cd_stn,
				dt_tm_fc : $scope.dt_tm_fc,
				nm_man_fc : $scope.nm_man_fc,
				etc_ttl : $scope.etc_ttl,
				sect_area_txt : $scope.sect_area_txt,
				dt_tm_ef_txt : $scope.dt_tm_ef_txt
			}).success(function(data){
				if(data.resultCount > 0){
					$scope.pager.currentPage = 1;
					$scope.fnInit();
				}
				else
					alert("통신소 번호와 예보시간이 겹칩니다.");
			});
		}
	}
	
	$scope.fnOnPagingClick = function(num){
		if(num > 0){
			$scope.pager.currentPage = num;
			$scope.fnInit();
		}
	}
	
	$scope.fnClickDelete = function(delDate){
		if(confirm("삭제하시겠습니까?")){
			$http.post("/data/kmaInform/delete",{
				dt_regt : delDate
			}).success(function(data){
				if(data.resultCount > 0)
					$scope.fnInit();
				else
					alert("삭제에 실패하였습니다.");
			});
		}
	}
}]);