baseModule.controller('loginCtrl',['$scope', '$http', '$routeParams', '$location','$window', '$interval',function($scope, $http, $routeParams, $location,$window, $interval){
	$scope.detailUser={};
	$scope.fnInit = function(){
		
	}
	
	$scope.fnOnClickBtnLogin=function(){
		console.log($("#keyEx").val())
		console.log($("#keyM").val())
		console.log($scope.detailUser.user_id)
		
		   var keyEx=$("#keyEx").val();
		   var keyM=$("#keyM").val();
		   var rsa = new RSAKey();
		   rsa.setPublic(keyM,keyEx);
		   var encID=rsa.encrypt($scope.detailUser.user_id);
		   var encPwd=rsa.encrypt($scope.detailUser.user_pwd);
		   $("#encUserID").val(encID);
		   $("#encUserPwd").val(encPwd);
		   document.forms["mainForm"].submit();
	}  
}]);
	
