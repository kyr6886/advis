noaaAdmin.controller('codeCtrl',['$scope', '$http', '$routeParams', '$location',function($scope, $http, $routeParams, $location){
	$scope.listGrps=[];
	$scope.listSysCodes=[];
	$scope.inputGrpCode="";
	$scope.inputGrpCodeTxt="";
	$scope.inputSysCode="";
	$scope.inputSysCodeTxt="";
	$scope.selectedGrp=null;
	$scope.fnGetListGrpCode=function(){
		$http.post(_context+'/api/admin/code/grp/list',{
		 }).success(function(data){
			 commonComplete(data);
			$scope.listGrps=data.listCodeGrp;
			for(var i=0;i<$scope.listGrps.length;i++){
				$scope.listGrps[i].selected=false;
			}
		/*	if($scope.listGrps!=null && $scope.listGrps.length>0){
				$scope.fnGetListSysCodes($scope.listGrps[0].grp_code);
			}*/
		});
	}
	
	$scope.fnGetListSysCodes=function(paramGrpCode){
		if(!paramGrpCode.isEmpty()){
			$http.post(_context+'/api/admin/code/sys/list',{
				paramCodeGrp:$scope.selectedGrp.grp_code
			 }).success(function(data){
				 commonComplete(data);
				 $scope.listSysCodes=data.listCodeSys;
			});
		}
	}
	
	//그룹코드 추가 버튼 클릭
	$scope.fnClickAddGrpCode=function(){
		if($scope.inputGrpCode.isEmpty()||$scope.inputGrpCodeTxt.isEmpty()){
			commonAlert("모든 필드는 필수 입력 항목 입니다.","");
			return false;
		}
		fnCreateGrpCode();
		
	}
	//시스템코드 추가
	$scope.fnClickAddSysCode=function(){
		if($scope.selectedGrp==null){
			commonAlert("그룹코드를 선택하세요.","");
			return false;
		}
		if($scope.inputSysCode == null || $scope.inputSysCode.isEmpty()){
			commonAlert("코드를 입력하세요.","");
			return false;
		}
		if($scope.inputSysCodeTxt == null || $scope.inputSysCodeTxt.isEmpty()){
			commonAlert("코드명을 입력하세요.","");
			return false;
		}
		fnCreateSysCode();
		
	}
	
	//그룹코드 추가
	var fnCreateGrpCode=function(){
		$http.post(_context+'/api/admin/code/grp/create',{
			paramCodeGrp: $scope.inputGrpCode,
			paramCodeGrpTxt:$scope.inputGrpCodeTxt
		}).success(function(data){
			commonComplete(data);
			/*commonlog(data);
			commonCheckException(data);*/
			$scope.inputGrpCode="";
			$scope.inputGrpCodeTxt="";
			$scope.listGrps=data.listCodeGrp;
		});
	}
	//시스템코드 추가
	var fnCreateSysCode=function(){
		$http.post(_context+'/api/admin/code/sys/create',{
			paramCodeGrp:$scope.selectedGrp.grp_code,
			paramSysCode: $scope.inputSysCode,
			paramSysCodeTxt:$scope.inputSysCodeTxt
		}).success(function(data){
			commonComplete(data);
			/*commonlog(data);
			commonCheckException(data);*/
			$scope.inputSysCode="";
			$scope.inputSysCodeTxt="";
			$scope.listSysCodes=data.listCodeSys;
		});
	}
	$scope.fnDelteGrpCode=function(paramItem){
		$http.post(_context+'/api/admin/code/grp/drop',{
			paramCodeGrp:paramItem.grp_code
		}).success(function(data){
			$scope.listGrps=data.listCodeGrp;
		});
	}
	
	$scope.fnDeleteSysCode=function(paramItem){
		$http.post(_context+'/api/admin/code/sys/drop',{
			paramSysCode:paramItem.sys_code,
			paramCodeGrp:paramItem.grp_code
		}).success(function(data){
			
			$scope.listSysCodes=data.listCodeSys;
		});
	}
	
	//그룹코드 선택
	$scope.fnSelectedGrp=function(paramItem){
		for(var i=0;i<$scope.listGrps.length;i++){
			$scope.listGrps[i].selected=false;
		}
		paramItem.selected=true;
		$scope.selectedGrp=paramItem;
		
		
		
		$scope.fnGetListSysCodes(paramItem.grp_code);
	}
}]);