noaaAdmin.controller('manageDisManualCtrl',['$scope', '$http', '$routeParams', '$location',function($scope, $http, $routeParams, $location){

	$scope.listlargeCode = {};
	$scope.selectedCtgId = "";
	$scope.listManual = {};	
	$scope.manualTitle = "";
	$scope.manualContents = "";
	$scope.listSysCodes = {};
	$scope.manualCode = "";
	
	$scope.fnInit = function(){
		$scope.fnList(1);
		$scope.fnGetManualList();
		$scope.fnListSysCodes();
	}
	
	$scope.fnList=function(item,paramCtg){
		$scope.listlargeCode={};
		
		$http.post(_context+"/api/std/manageDisCodelist",{paramDepth:item,paramCtgId:paramCtg})
		.success(function(data){
			$scope.listlargeCode = data.list;
		});
	}
	
	$scope.fnCodeSysList=function(paramCode){
		$http.post(_context+"/api/admin/code/sys/list",{paramCodeGrp: paramCode})
		.success(function(data){
			$scope.listCodeSys = data.listCodeSys;
			$scope.selectedManualCode = $scope.listCodeSys[0].sys_code;
		});
	}
	
	$scope.fnGetManualList=function(){
		$http.post(_context+"/disaster/manual/list",{paramCtgId: $scope.selectedCtgId})
		.success(function(data){
			$scope.listManual = data.resultManualList; 
		});
	}
	
	$scope.fnClickSave=function(){
		if($scope.selectedCtgId == ""){
			alert("재해 코드를 선택하세요.");
			return;
		}else if($scope.manualTitle=="" || $scope.manualContents=="" || $scope.manualCode==""){
			alert("모든 입력사항을 입력 해주세요.");
			return;
		}
		
		//중복체크
		var tempList = [];
		for(var i=0; i<$scope.listManual.length; i++){
			var data = {title:'', sys_code:''};
			if($scope.listManual[i].ctg_id == $scope.selectedCtgId){
				data.title = $scope.listManual[i].manual_title;
				data.sys_code = $scope.listManual[i].sys_code;
				tempList.push(data);
			}
		}
		var cnt = 0;
		for(var i=0; i<tempList.length; i++){
			if(tempList[i].title == $scope.manualTitle){
				cnt++;
			}
		}
		
		if(cnt > 0){
			alert('이미 중복된 매뉴얼이 있습니다.');
			return;
		}else{
			$http.post(_context+"/disaster/manual/insert",{
				paramCtgId: $scope.selectedCtgId
				, paramGrpCode: 'MNA_CODE'
				, paramSysCode: $scope.manualCode
				, paramTitle: $scope.manualTitle
				, paramContents: $scope.manualContents
				})
			.success(function(data){
				
				if(data.resultCount == 1){
					alert("저장되었습니다.");
					$scope.fnGetManualList();
				}
				
			});
		}
		
		$scope.manualTitle = "";
		$scope.manualContents = "";
		
	}
	
	$scope.fnClickManualDelete=function(paramItem){
		$http.post(_context+"/disaster/manual/delete",{paramSeq: paramItem.seq})
		.success(function(data){
			$scope.fnGetManualList();
		});
	}
	
	$scope.fnChangeCtgId=function(paramItem){
		$scope.selectedCtgId = paramItem.print_id;
		$scope.fnGetManualList();
	}
	
	$scope.fnListSysCodes=function(){
		
		$http.post(_context+'/api/admin/code/sys/list',{
			paramCodeGrp:'MNA_CODE'
		 }).success(function(data){
			 commonComplete(data);
			 $scope.listSysCodes=data.listCodeSys;
			 $scope.manualCode = $scope.listSysCodes[0].sys_code;
		});
	}
	
	
	
	
}]);