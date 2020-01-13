noaaAdmin.controller('manageEventCtrl',['$scope', '$http', '$routeParams', '$location','Upload',function($scope, $http, $routeParams, $location , Upload){
	$scope.disaster={type:'NTY',title:'',date:''}
	$scope.listDisaster=[];
	//생성
	$scope.fnOnClickCreate=function(){
		
		fnCreate();
	}
	
	//삭제
	$scope.fnOnClickDelete=function(paramObj){
		if(confirm("해당 재해를 삭제 하시겠습니까?")){
			fnDelete(paramObj);
		}
		
	}
	
	$scope.fnChangeDisasterType=function(){
		fnList();
	}
	
	fnDelete=function(paramItem){
		$http.post("/api/std/event/group/delete",{paramEvtGrp:paramItem.evt_group})
		.success(function(response){
			fnList();
		});
	}

	fnCreate=function(){
		$http.post("/api/std/event/create"
				,{	 paramTitle:$scope.disaster.title
					,paramCtgId:$scope.disaster.type
					,paramDisDateStr:$scope.disaster.date
					,paramIsRoot:'Y'
					,paramIsCs:'N'
				}
		).success(function(response){
			if(response.resultCount>0){
				fnList();
			}
		});
	}
	//목록
	fnList=function(){
		$http.post("/api/std/event/list",{paramCtgId:$scope.disaster.type, paramIsRoot:'Y', paramIsCs:'N'})
		.success(function(response){
			$scope.listDisaster=response.eventList;
			
		});
	}
	fnList();
}]);
