noaaAdmin.controller('menuCtrl',['$scope', '$http', '$routeParams', '$location',function($scope, $http, $routeParams, $location){
	
	$scope.listMenu=[];
	$scope.detailMenu={};
	$scope.listCodes=[];
	$scope.isShowRoleCreate=false;
	$scope.listRoles=[];
	
	$scope.fnPageInit=function(){
		$http.post(_context+"/api/admin/menu",{}).success(function(data){
			commonComplete(data);
			$scope.listMenu=data.listMenu;
			
		});
	}
	$scope.fnPageRoleInit=function(){
		$http.post(_context+"/api/admin/menu/role",{}).success(function(data){
			commonComplete(data);
			$scope.listMenu=data.listMenu;
			$scope.listRoles=data.listRole;
		
			
			
			for(var i=0;i<$scope.listMenu.length;i++){
				$scope.listMenu[i].menuRoles=[];
				
				for(var k=0;k<$scope.listRoles.length;k++){
					var temp={role_id:'',write_yn:"N",read_yn:"N"};
					
					if($scope.listMenu[i].menu_role==null || $scope.listMenu[i].menu_role==''){
						temp.role_id=$scope.listRoles[k].role_id;
					}else{
						var mRoles=$scope.listMenu[i].menu_role.split(",");
						var _index=mRoles.indexOf($scope.listRoles[k].role_id);
						if(_index>-1){
							console.log($scope.listMenu[i]);
							temp.role_id=$scope.listRoles[k].role_id;
							temp.write_yn=$scope.listMenu[i].write_yn==null?"":$scope.listMenu[i].write_yn.split(',')[_index];
							temp.read_yn="Y";
						}else{
							temp.role_id=$scope.listRoles[k].role_id;
						
						}
					}
					$scope.listMenu[i].menuRoles.push(temp);
				}
			
				
			}
			
			
			for(var i=0;i<$scope.listMenu.length;i++){
				$scope.listMenu[i].isComplete=true;
			}
		});
	}
	
	$scope.fnClickDelete=function(item){
		$http.post(_context+"/api/admin/menu/drop",{id:item.menu_id}).success(function(data){
			commonComplete(data);
			$scope.listMenu=data.listMenu;
		
		});
		
	}
	
	$scope.fnCheckedRole=function($event,item,roleID){
		var checkbox = $event.target;
		var roles=[];
		if(item.menu_role!=null)
			roles=item.menu_role.split(',');
		
		if(roles.indexOf(roleID)==-1){
			roles.push(roleID);
		}
		else{
			roles.splice(roles.indexOf(roleID), 1);
		}
		
		item.menu_role=roles.join(',');
	}
	

	
	$scope.fnCreateRoleAction=function(item){
		item.isComplete=false;
		var _roles=[];
		var _write=[];
		for(var i=0;i<item.menuRoles.length;i++){
			if(item.menuRoles[i].read_yn=='Y'){
				_roles.push(item.menuRoles[i].role_id);
				if(item.menuRoles[i].write_yn=='Y'){
					_write.push("Y");
				}else{
					_write.push("N");
				}
			}
			
		}
		
		$http.post(_context+"/api/admin/menu/role/create",{paramMenuID:item.menu_id,paramRoles:_roles.join(','),paramWriteRoles:_write.join(',')}).success(
		function(data){
			commonComplete(data);
			item.isComplete=true;
		}
		);
	}
	
	$scope.fnOnClickCreateAction=function(item){
		if($scope.detailMenu.menu_id==undefined || $scope.detailMenu.menu_id.isEmpty()){
			commonAlert("입력항목을 확인하세요.", "alert");
			return false;
		}else if($scope.detailMenu.menu_title==undefined || $scope.detailMenu.menu_title.isEmpty()){
			commonAlert("메뉴 이름을 확인하세요.", "alert");
			return false;
		}else if($scope.detailMenu.menu_uri==undefined || $scope.detailMenu.menu_uri.isEmpty()){
			commonAlert("메뉴 URL을 확인하세요.", "alert");
			return false;
		}else if($scope.detailMenu.menu_type_code==undefined || $scope.detailMenu.menu_type_code.isEmpty()){
			commonAlert("메뉴 타입을 확인하세요.", "alert");
			return false;
		}
		$http.post(_context+"/api/admin/menu/create",{detailMenu:$scope.detailMenu}).success(
		function(data){
			commonComplete(data);
			
			$scope.listMenu=data.listMenu;
			$scope.detailMenu={};
				
		}
		);
	}
	
	
}]);