noaaAdmin.controller('metaCtrl',['$scope', '$http', '$routeParams', '$location',function($scope, $http, $routeParams, $location){
	
	//변수 선언
	$scope.listMetas=[];
	$scope.selectedMetaId="";
	
	//메타 리스트 상태 가져오기
	$scope.fnGetListMetaCode=function(){
		$http.post(_context+'/api/admin/meta/list',{
		 }).success(function(data){
			 commonComplete(data);
			$scope.listMetas=data.listMeta;
		});
	}
	
	//선택 meta 생성
	$scope.fnOnCreate=function(paramItem){
		$http.post(_context+'/api/admin/meta/create',{
			paramMetaId: paramItem.meta_table
		 }).success(function(data){
			$scope.fnGetListMetaCode();
		});
	}	
	
	//상세 메타 리스트 가져오기
	
	
	//메타 리스트 등록 - 종류별
	
	
	//메타 리스트 삭제
	
	
	
	
}]);