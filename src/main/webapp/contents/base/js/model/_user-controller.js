noaaAdmin.controller('userCtrl',['$scope', '$http', '$routeParams', '$location',function($scope, $http, $routeParams, $location){
	var totalCount=0;
	$scope.list=[];
	$scope.listRoles=[];
	$scope.listUserTypeCodes=[];
	$scope.detailUser={};
	$scope.isShowUserCreate=false;
	$scope.isEnableUpdate=false;
	$scope.checkedItem=[];
	$scope.isInitSystem=false;
	$scope.resultMessage="";
	$scope.findOption="user_name";
	$scope.findText="";
	
	$scope.listSido=[];
	$scope.listGungu=[];
	$scope.listDong=[];
	$scope.selectedSido="";
	$scope.selectedGungu="";
	$scope.selectedDong="";
	$scope.isShowMessage=false;
	var isCheckedID=false;
	
	//페이징 관련
	$scope.pager={
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
	}
	
	$scope.fnInitAccount=function(){
		$http.post(_context+'/api/admin/user/init/check',{
			
		 }).success(function(data){
			 console.log(data);
			 $scope.isInitSystem=data.resultCount>0;
			 if($scope.resultMessage!=""){
				 commonAlert($scope.resultMessage,"alert");
			 }
		});
	}
	
	$scope.fnOnClickCheckID=function(){
		if($scope.detailUser.user_id == null || $scope.detailUser.user_id.isEmpty()){
			commonAlert("아이디를 입력하세요.","msg");
			return false;
		}else{
			$http.post(_context+"/api/account/detail",{paramUserID:$scope.detailUser.user_id})
			.success(function(data){
				commonComplete(data);
				if(data.detail==null){ 
					commonAlert("사용 가능한 아이디 입니다.","msg");
					isCheckedID=true;
				}
				else {
					commonAlert("이미 사용중인 아이디 입니다.","msg");
					isCheckedID=false;
				}
			});
		}
	}
	
	$scope.fnOnChangeID=function(){
		commonComplete("change");
		isCheckedID=false;
	}
	//목록(admin page)
	$scope.fnGetList=function(){
		
		$http.post(_context+'/api/admin/user/manage',{
			 pageNo : $scope.pager.currentPage,
			 pageSize : $scope.pager.rowSize,
		 }).success(function(data){
			 commonComplete(data);
			$scope.list=data.list;
			$scope.listUserTypeCodes=data.listUserTypeCodes;
			$scope.listRoles=data.listRoles;
			$scope.listSido=data.listAdm;
			$scope.pager={totalCount:data.totalCount,rowSize:10,blockSize:10,currentPage:$scope.pager.currentPage};
		});
	
	}
	
	$scope.fnList=function(){
		$http.post(_context+'/api/account/list',{
			 pageNo : $scope.pager.currentPage,
			 pageSize : $scope.pager.rowSize,	
			 paramText:$scope.findText, 
			 paramOption:$scope.findOption,
		 }).success(function(data){
			 commonComplete(data);
			$scope.list=data.list;
			$scope.pager={totalCount:data.totalCount,rowSize:10,blockSize:10,currentPage:$scope.pager.currentPage};
		});
	}
	
	
	$scope.fnChangeUserType=function(paramItem,paramType){
		$http.post(_context+"/api/admin/user/type/update",{
			paramUserID:paramItem.user_id,
			paramUserType:paramItem.user_type_code
		}).success(function(data){
			if(data.resultCount<=0){
				noaaAdminAlert("error");	
			}
		});
	}
	
	$scope.fnClickDelete=function(paramItem){
		if(confirm("삭제하시겠습니까?")){
			$http.post(_context+"/api/account/delete",{paramUserID:paramItem.user_id})
			.success(function(){
				$scope.fnGetList();
			});
		}
	}
	
	//사용자 검색 팝업창에서 체크박스 선택 완료 버튼 클릭
	$scope.fnOnClickChoice=function(){
		var rs=[];
		if($scope.checkedItem!=null && $scope.checkedItem.length>0){
			for(var i=0;i<$scope.list.length;i++){
				for(var k=0;k<$scope.checkedItem.length;k++){
					if($scope.list[i].user_id==$scope.checkedItem[k]){
					rs.push($scope.list[i]);
					}
				}
			}
		};
		
		if(window.opener!=null){
		window.opener.fnCallbackCheckedUsers(rs);
		window.close();
		}
	}
	
	
	$scope.fnOnClickUpdateUser=function(paramItem){
		$scope.isEnableUpdate=true;
		$scope.isShowUserCreate=true;
		$scope.checkedItem=[];
		
		$http.post(_context+"/api/account/detail",{paramUserID:paramItem.user_id})
		.success(function(data){
			$scope.detailUser=data.detail;
			commonComplete(data);
			if(data.detail.user_roles!=null&&data.detail.user_roles!="")
				$scope.checkedItem=data.detail.user_roles.split(",");
		});
	}
	$scope.fnClickUpdate=function(){
		$http.post(_context+"/api/account/update",{detail:$scope.detailUser})
		.success(function(data){
		
		});
	}
	$scope.fnClickBtnShowCreateForm=function(){
		$scope.isShowUserCreate=true;
		$scope.isEnableUpdate=false;
		$scope.detailUser={};
	}
	
	$scope.fnClickHideForm=function(){
		$scope.isShowUserCreate=false;
		$scope.isEnableUpdate=false;
		
		$scope.detailUser={};
	}
	
	
	$scope.fnClickBtnCreate=function(){
		if(!$scope.isEnableUpdate && !isCheckedID){
			commonAlert("아이디 중복체크를 하세요.","alert");
			return false;
		}
		$scope.detailUser.user_roles = null;
		for(var i=0;i<$scope.checkedItem.length;i++){
			if(i==0) $scope.detailUser.user_roles=$scope.checkedItem[i];
			else $scope.detailUser.user_roles+=","+$scope.checkedItem[i];
			
		}
		if(!$scope.isEnableUpdate && ($scope.detailUser.user_pwd == null || $scope.detailUser.user_pwd.isEmpty())){
			commonAlert("비밀번호를 입력하세요.", "alert");
			return false;
		}
		if($scope.detailUser.user_name == null || $scope.detailUser.user_name.isEmpty()){
			commonAlert("사용자 이름을 입력하세요.", "alert");
			return false;
		}
		if($scope.detailUser.user_email == null || $scope.detailUser.user_email.isEmpty()){
			commonAlert("사용자 이메일을 입력하세요.", "alert");
			return false;
		}
		if($scope.detailUser.user_type_code == null || $scope.detailUser.user_type_code.isEmpty()){
			commonAlert("사용자 구분을 선택하세요.", "alert");
			return false;
		}
		if($scope.detailUser.user_roles == null || $scope.detailUser.user_roles.isEmpty()){
			commonAlert("사용자 권한을 선택하세요.", "alert");
			return false;
		}
		if(!$scope.isEnableUpdate ){
			$http.post(_context+"/api/account/create",{detail:$scope.detailUser})
			.success(function(data){
				commonComplete(data);
				if(data.resultCount>0){
					commonAlert("등록을 성공했습니다.","alert");
				}
				$scope.fnGetList();
			});
		}else{
			$http.post(_context+"/api/account/update",{detail:$scope.detailUser})
			.success(function(data){
				commonComplete(data);
				if(data.resultCount>0){
					commonAlert("수정을 성공했습니다.","alert");
				}
				$scope.fnGetList();
			});
		}
	}
	
	var fnCreateAdmin=function(){
		$http.post(_context+"/api/account/admin/create",{detail:$scope.detailUser})
		.success(function(data){
			commonComplete(data);
			$scope.fnInitAccount();
		});
	}
	$scope.fnOnCreateAdmin=function(){
		if($scope.detailUser.user_id==undefined){
			$scope.detailUser.user_id="";
		}
		if($scope.detailUser.user_pwd==undefined){
			$scope.detailUser.user_pwd="";
		}
		
		
		if($scope.detailUser.user_id.isEmpty()){
			commonAlert("아이디를 입력하세요.");
			return false;
		}
		if($scope.detailUser.user_pwd.isEmpty()){
			commonAlert("비밀번호를 입력하세요.");
			return false;
		}
		fnCreateAdmin();
		
	}
	
	$scope.fnOnPagingClick = function(num){
		$scope.pager.currentPage = num;
		$scope.fnGetList();
	}
	
	$scope.fnListSido=function(){
		$http.post(_context+"/api/meta/adm/sido/list",{}).success(function(data){
			commonComplete(data);
			$scope.listSido=data.listAdm;
			
		});
	};
	$scope.fnListGungu=function(){
		$http.post(_context+"/api/meta/adm/gungu/list",{paramSido:$scope.selectedSido}).success(function(data){
			commonComplete(data);
			$scope.listGungu=data.listAdm;
			
		});
	};
	$scope.fnListDong=function(){
		$http.post(_context+"/api/meta/adm/dong/list",{paramGungu:$scope.selectedGungu}).success(function(data){
			commonComplete(data);
			$scope.listDong=data.listAdm;
			
		});
	}
	
	$scope.fnChangeSido=function(){
		$scope.listGungu=[];
		$scope.listDong=[];
		
		$scope.fnListGungu();
		
		$scope.selectedGungu="";
		$scope.selectedDong="";
	}
	$scope.fnChangeGungu=function(){
		$scope.listDong=[];
		
		$scope.fnListDong();
		
		$scope.selectedDong="";
	}
	
	$scope.fnOnClickFindID=function(){
		$scope.isShowMessage=false;
		$http.post(_context+"/api/account/find/id",{detailUser:$scope.detailUser}).success(function(data){
			commonComplete(data);
			$scope.isShowMessage=true;
			if(data.resultMessage!=null){
				
				$scope.resultMessage=$.stringFormat("사용중인 아이디 입니다.[{0}]",data.resultMessage);
			}else{
				$scope.resultMessage="입력 정보를 확인하세요.";
			}
		});
	}
	
	$scope.fnOnClickFindPwd=function(){
		$scope.isShowMessage=false;
		$http.post(_context+"/api/account/find/pwd",{detailUser:$scope.detailUser}).success(function(data){
			commonComplete(data);
			$scope.isShowMessage=true;
			if(data.resultMessage!=null){
				
				$scope.resultMessage=$.stringFormat("사용중인 아이디 입니다.[{0}]",data.resultMessage);
			}else{
				$scope.resultMessage="입력 정보를 확인하세요.";
			}
		});
	}
	
	//checkbox toggle 
	$scope.toggleSelection = function toggleSelection(paramValue) {
		
	    var idx = $scope.checkedItem.indexOf(paramValue);
	    // is currently selected
	    if (idx > -1) {
	      $scope.checkedItem.splice(idx, 1);
	    }
	    // is newly selected
	    else {
	      $scope.checkedItem.push(paramValue);
	    }
	  };

	  
	  
	  
	  
}]);