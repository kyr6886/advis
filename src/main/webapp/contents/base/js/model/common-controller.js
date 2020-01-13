noaaAdmin.controller('commCtrl',['$scope', '$http', '$routeParams', '$location',function($scope, $http, $routeParams, $location){
	$scope.commonUserDetail=null;
	
	$scope.fnCommonOnClickMyInfo=function(){
		$http.post(_context+"/api/account/myInfo",{}).success(function(data){
			$scope.commonUserDetail=data.detailUser;
		});
	}
	
	$scope.fnCommonOnClickMyInfoUpdate=function(){
		if($scope.commonUserDetail.user_email.isEmpty()){
			commonAlert("이메일을 입력하세요","msg");
			return false;
		}
		$http.post(_context+"/api/account/update",{detail:$scope.commonUserDetail}).success(function(data){
			if(data.resultCount>0){
				commonAlert("저장 하였습니다.","msg");
			}else{
				commonAlert("입력 정보를 확인하세요.","msg");
			}
			$scope.commonUserDetail=data.detail;
		});
	}
	
}]);