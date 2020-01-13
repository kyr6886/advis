noaaAdmin.controller('kmaTyphoonCtrl',['$scope', '$http', '$routeParams', '$location',function($scope, $http, $routeParams, $location){
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
	
	$scope.stn_id = "";
	$scope.tm_fc = "";
	$scope.typ_seq = "";
	$scope.typ_name = "";
	$scope.typ_en = "";
	$scope.typ_lat = "";
	$scope.typ_lon = "";
	$scope.typ_sp = "";
	$scope.typ_ps = "";
	$scope.typ_15 = "";
	$scope.ft1_lat = "";
	$scope.ft1_lon = "";
	$scope.ft1_sp = "";
	$scope.ft1_ps = "";
	$scope.ft2_lat = "";
	$scope.ft2_lon = "";
	$scope.ft2_sp = "";
	$scope.ft2_ps = "";
	$scope.ft3_lat = "";
	$scope.ft3_lon = "";
	$scope.ft3_sp = "";
	$scope.ft3_ps = "";
	
	$scope.fnInit = function(){
		let date = new Date();
		$scope.nowTime = "예시 : " + date.getFullYear() + 
						(date.getMonth()>8?"":"0") + (date.getMonth()+1) +
						(date.getDate()>9?date.getDate():"0"+date.getDate()) +
						(date.getHours()>9?date.getHours():"0"+date.getHours()) +
						(date.getMinutes()>9?date.getMinutes():"0"+date.getMinutes());
		
		$scope.isCreateViewOn = false;
		
		$scope.stn_id = "";
		$scope.tm_fc = "";
		$scope.typ_seq = "";
		$scope.typ_name = "";
		$scope.typ_en = "";
		$scope.typ_lat = "";
		$scope.typ_lon = "";
		$scope.typ_sp = "";
		$scope.typ_ps = "";
		$scope.typ_15 = "";
		$scope.ft1_lat = "";
		$scope.ft1_lon = "";
		$scope.ft1_sp = "";
		$scope.ft1_ps = "";
		$scope.ft2_lat = "";
		$scope.ft2_lon = "";
		$scope.ft2_sp = "";
		$scope.ft2_ps = "";
		$scope.ft3_lat = "";
		$scope.ft3_lon = "";
		$scope.ft3_sp = "";
		$scope.ft3_ps = "";
		
		$http.post("/data/kmaTyphoon/list",{
			pageNo : $scope.pager.currentPage-1,
			pageSize : $scope.pager.rowSize
		}).success(function(data){
			$scope.mainDataList = data.typhoonList;
			
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
		if($scope.stn_id == "" || 
			$scope.tm_fc == "" ||
			$scope.typ_seq == "" ||
			$scope.typ_name == "" ||
			$scope.typ_en == "" ||
			$scope.typ_lat == "" ||
			$scope.typ_lon == "" ||
			$scope.typ_sp == "" ||
			$scope.typ_ps == "" ||
			$scope.typ_15 == "" ||
			$scope.ft1_lat == "" ||
			$scope.ft1_lon == "" ||
			$scope.ft1_sp == "" ||
			$scope.ft1_ps == "" ||
			$scope.ft2_lat == "" ||
			$scope.ft2_lon == "" ||
			$scope.ft2_sp == "" ||
			$scope.ft2_ps == "" ||
			$scope.ft3_lat == "" ||
			$scope.ft3_lon == "" ||
			$scope.ft3_sp == "" ||
			$scope.ft3_ps == ""){
			alert("빈칸을 채워주세요.");
			return;
		}
		
		if(isNaN(Number($scope.stn_id)) ||
			isNaN(Number($scope.typ_lat)) ||
			isNaN(Number($scope.typ_lon)) ||
			isNaN(Number($scope.typ_sp)) ||
			isNaN(Number($scope.typ_ps)) ||
			isNaN(Number($scope.typ_15)) ||
			isNaN(Number($scope.ft1_lat)) ||
			isNaN(Number($scope.ft1_lon)) ||
			isNaN(Number($scope.ft1_sp)) ||
			isNaN(Number($scope.ft1_ps)) ||
			isNaN(Number($scope.ft2_lat)) ||
			isNaN(Number($scope.ft2_lon)) ||
			isNaN(Number($scope.ft2_sp)) ||
			isNaN(Number($scope.ft2_ps)) ||
			isNaN(Number($scope.ft3_lat)) ||
			isNaN(Number($scope.ft3_lon)) ||
			isNaN(Number($scope.ft3_sp)) ||
			isNaN(Number($scope.ft3_ps))){
			alert("숫자형이 아닙니다.");
			return;
		}
		
		if(isNaN(Number($scope.tm_fc)) || ($scope.tm_fc).length != 12){
			alert("시간 형식이 잘못됐습니다.\n" + $scope.nowTime);
			return;
		}
		
		if(confirm("저장하시겠습니까?")){
			$http.post("/data/kmaTyphoon/create",{
				stn_id : $scope.stn_id,
				tm_fc : $scope.tm_fc,
				typ_seq : $scope.typ_seq,
				typ_name : $scope.typ_name,
				typ_en : $scope.typ_en,
				typ_lat : $scope.typ_lat,
				typ_lon : $scope.typ_lon,
				typ_ws : $scope.typ_sp,
				typ_ps : $scope.typ_ps,
				typ_15 : $scope.typ_15,
				ft1_lat : $scope.ft1_lat,
				ft1_lon : $scope.ft1_lon,
				ft1_ws : $scope.ft1_sp,
				ft1_ps : $scope.ft1_ps,
				ft2_lat : $scope.ft2_lat,
				ft2_lon : $scope.ft2_lon,
				ft2_ws : $scope.ft2_sp,
				ft2_ps : $scope.ft2_ps,
				ft3_lat : $scope.ft3_lat,
				ft3_lon : $scope.ft3_lon,
				ft3_ws : $scope.ft3_sp,
				ft3_ps : $scope.ft3_ps
			}).success(function(data){
				if(data.resultCount > 0){
					$scope.pager.currentPage = 1;
					$scope.fnInit();
				}
				else
					alert("예보시간과 태풍번호가 겹칩니다.");
			});
		}
	}
	
	$scope.fnOnPagingClick=function(num){
		if(num > 0){
			$scope.pager.currentPage = num;
			$scope.fnInit();
		}
	}
	
	$scope.fnClickDelete = function(delDate){
		if(confirm("삭제하시겠습니까?")){
			$http.post("/data/kmaTyphoon/delete",{
				tm_fc : delDate
			}).success(function(data){
				if(data.resultCount > 0)
					$scope.fnInit();
				else
					alert("삭제에 실패하였습니다.");
			});
		}
	}
}]);