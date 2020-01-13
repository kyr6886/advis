 var editor_object = [];
noaaAdmin.controller('bbsCtrl',['$scope', '$http', '$routeParams', '$location',function($scope, $http, $routeParams, $location){
	$scope.listCategory=[];
	$scope.listBBS=[];
	$scope.listBBSContents=[];
	$scope.isShowBoardCreate=false;
	$scope.isEnableUpdate=false;
	$scope.categoryTitle;
	$scope.selectedBoard={};
	$scope.bbs_html;
	$scope.listRoles=[];
	$scope.readRoleItem=[];
	$scope.writeRoleItem=[];
	$scope.fnListCategory=function(){
		$http.post(_context+"/api/admin/board/category/list",{id:selectedBoard.bbs_id}).success(function(data){
			commonComplete(data);
		});
	}
	
	$scope.fnMngBBS=function(){
		$http.post(_context+"/api/admin/board/mng",{}).success(function(data){
			commonComplete(data);
			$scope.listRoles=data.listRoles;
			$scope.listBBS=data.listMaster;
		});
	}
	
	$scope.fnListBBS=function(){
		$http.post(_context+"/api/admin/board/list",{}).success(function(data){
			commonComplete(data);
			$scope.listBBS=data.listMaster;
		});
	}
	
	$scope.fnDetailBBS=function(item){
		$scope.readRoleItem=[];
		$scope.writeRoleItem=[];
		$http.post(_context+"/api/admin/board/detail",{id:item.bbs_id}).success(function(data){
			commonComplete(data);
			$scope.selectedBoard=data.detailMaster;
			if($scope.selectedBoard.bbs_info_html==null)
				$scope.selectedBoard.bbs_info_html="";
		
			editor_object.getById["smartEditor"].exec("SET_IR", [$scope.selectedBoard.bbs_info_html]);
			var tempCategory=[];
			if(data.detailMaster!=null && data.detailMaster.listCategory!=null){
				for(var i=0;i<data.detailMaster.listCategory.length;i++){
					tempCategory.push(data.detailMaster.listCategory[i].category_title);
				}
				
				if(data.detailMaster.read_role_id!=null) $scope.readRoleItem= data.detailMaster.read_role_id.split(',');
				if(data.detailMaster.write_role_id!=null) $scope.writeRoleItem= data.detailMaster.write_role_id.split(',');
			}
			$scope.categoryTitle=tempCategory.join(",");
			
		});
	}
	
	
	$scope.fnListBBSContents=function(){
		$http.post("",{}).success(function(data){
			commonComplete(data);
		});
	}
	
	$scope.fnClickUpdate=function(item){
		$scope.isShowBoardCreate=true;
		$scope.isEnableUpdate=true;
		$scope.fnDetailBBS(item);
	}
	$scope.fnClickBtnShowCreateForm=function(){
		$scope.isShowBoardCreate=true;
		$scope.isEnableUpdate=false;
		$scope.selectedBoard={};
		$scope.selectedBoard.use_yn="Y";
		$scope.categoryTitle=null;
		
	}
	$scope.fnClickHideForm=function()
	{
		$scope.isShowBoardCreate=false;
		
	}
	
	$scope.fnOnCreateAction=function(){
		
		for(var i=0;i<$scope.readRoleItem.length;i++){
			if(i==0) $scope.selectedBoard.read_role_id=$scope.readRoleItem[i];
			else $scope.selectedBoard.read_role_id+=","+$scope.readRoleItem[i];
			
		}
		
		for(var i=0;i<$scope.writeRoleItem.length;i++){
			if(i==0) $scope.selectedBoard.write_role_id=$scope.writeRoleItem[i];
			else $scope.selectedBoard.write_role_id+=","+$scope.writeRoleItem[i];
			
		}
		
		
		editor_object.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);
		$scope.selectedBoard.bbs_info_html=$("#smartEditor").val();//.replace(/</g,"&lt;").replace(/>/g,"&gt;");
	
		
		if($scope.selectedBoard.bbs_id == null || $scope.selectedBoard.bbs_id.isEmpty()){
			commonAlert("아이디를 입력하세요.","alert");
			return false;
		}
		
		if($scope.selectedBoard.bbs_title == null || $scope.selectedBoard.bbs_title.isEmpty()){
			commonAlert("제목을 입력하세요.","alert");
			return false;
		}
		
		$http.post(_context+"/api/admin/board/create",{detailMaster:$scope.selectedBoard,paramCategorys:$scope.categoryTitle}).success(function(data){
			commonComplete(data);
			$scope.listBBS=data.listMaster;
			$scope.isShowBoardCreate=false;
			$scope.categoryTitle=null;
			$scope.selectedBoard={};
			editor_object.getById["smartEditor"].exec("SET_IR", [""]);
			
		});
	}
	
	$scope.fnUpdateAction=function(){
		editor_object.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);
		$scope.selectedBoard.bbs_info_html=$("#smartEditor").val();//.replace(/</g,"&lt;").replace(/>/g,"&gt;");
		$scope.selectedBoard.read_role_id=$scope.readRoleItem.join(",");
		$scope.selectedBoard.write_role_id=$scope.writeRoleItem.join(",");
		if($scope.selectedBoard.bbs_title == null || $scope.selectedBoard.bbs_title.isEmpty()){
			commonAlert("제목을 입력하세요.","alert");
			return false;
		}
		$http.post(_context+"/api/admin/board/update",{detailMaster:$scope.selectedBoard,paramCategorys:$scope.categoryTitle}).success(function(data){
			commonComplete(data);
			$scope.listBBS=data.listMaster;
			$scope.isShowBoardCreate=false;
			$scope.categoryTitle=null;
			$scope.selectedBoard={};
			editor_object.getById["smartEditor"].exec("SET_IR", [""]);
		});
	}
	//checkbox toggle 
	$scope.toggleReadSelection = function(paramValue) {
		
	    var idx = $scope.readRoleItem.indexOf(paramValue);
	    // is currently selected
	    if (idx > -1) {
	      $scope.readRoleItem.splice(idx, 1);
	    }
	    // is newly selected
	    else {
	      $scope.readRoleItem.push(paramValue);
	    }
	  };
	  
	//checkbox toggle 
		$scope.toggleWriteSelection =  function(paramValue) {
			
		    var idx = $scope.writeRoleItem.indexOf(paramValue);
		    // is currently selected
		    if (idx > -1) {
		      $scope.writeRoleItem.splice(idx, 1);
		    }
		    // is newly selected
		    else {
		      $scope.writeRoleItem.push(paramValue);
		    }
		  };
	  
}]);