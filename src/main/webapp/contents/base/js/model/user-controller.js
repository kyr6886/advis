noaaAdmin.controller('userCtrl',['$scope', '$http', '$routeParams', '$location',function($scope, $http, $routeParams, $location){
	var totalCount=0;
	$scope.list=[];
	$scope.listRoles=[];
	$scope.listUserTypeCodes=[];
	$scope.listUserStatusCodes=[];
	$scope.detailUser={};
	$scope.pwd1="";
	$scope.pwd2="";
	$scope.phone1="";
	$scope.phone2="";
	$scope.phone3="";
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
	var regId = /^[A-Za-z0-9]{4,12}$/;
	var regPass = /^[A-Za-z0-9]{8,16}$/;
	var regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	var regPhone = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-[0-9]{3,4}-[0-9]{4}$/;
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
			if (!regId.test($scope.detailUser.user_id)) {
				commonAlert("아이디는 영문,숫자를 포함한 4자이상12자 이하입니다.","msg");
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
	}
	
	$scope.fnOnChangeID=function(){
		commonComplete("change");
		isCheckedID=false;
	}
	//목록(admin page)
	$scope.fnGetList=function(){
		$http.post(_context+'/api/admin/user/manage',{pageNo : $scope.pager.currentPage,pageSize : $scope.pager.rowSize})
		.success(function(data){
			commonComplete(data);
			$scope.list=data.list;
			$scope.listUserTypeCodes=data.listUserTypeCodes;
			$scope.listUserStatusCodes=data.listUserStatusCodes;
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
	
	
	$scope.fnChangeUserStat=function(paramItem){
		$http.post(_context+"/api/admin/user/type/update",{
			paramUserID:paramItem.user_id,
			paramUserType:paramItem.user_type_code,
			paramUserStatus:paramItem.user_status_code
		}).success(function(data){
			if(data.resultCount<=0){
				noaaAdminAlert("error");	
			}
		});
	}
	
	$scope.fnClickDelete=function(paramItem){
		$http.post(_context+"/api/account/delete",{paramUserID:paramItem.user_id})
		.success(function(){
			$scope.fnGetList();
		});
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
			if(data.detail.user_phone!=null&&data.detail.user_phone!=""){
				$scope.phone1=data.detail.user_phone.split("-")[0];
				$scope.phone2=data.detail.user_phone.split("-")[1];
				$scope.phone3=data.detail.user_phone.split("-")[2];
			}
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
		$scope.phone1="";
		$scope.phone2="";
		$scope.phone3="";
	}
	
	$scope.fnClickHideForm=function(){
		$scope.isShowUserCreate=false;
		$scope.isEnableUpdate=false;
		
		$scope.detailUser={};
		$scope.phone1="";
		$scope.phone2="";
		$scope.phone3="";
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
		if(!$scope.isEnableUpdate && ($scope.pwd1 == null || $scope.pwd1.isEmpty()||$scope.pwd2 == null || $scope.pwd2.isEmpty())){
			commonAlert("비밀번호를 입력하세요.", "alert");
			return false;
		}
		if(!$scope.isEnableUpdate && ($scope.pwd1 != $scope.pwd2 )){
			commonAlert("비밀번호와 비밀번호확인 이 다릅니다.", "alert");
			return false;
		}
		if (!regPass.test($scope.pwd1)) {
			commonAlert("비밀번호는 영문,숫자를 포함한 8자이상16자 이하입니다.", "alert");
			return false;
		}else{
			$scope.detailUser.user_pwd = $scope.pwd1;
		}
		if($scope.detailUser.user_name == null || $scope.detailUser.user_name.isEmpty()){
			commonAlert("사용자 이름을 입력하세요.", "alert");
			return false;
		}
		if($scope.detailUser.user_email == null || $scope.detailUser.user_email.isEmpty()){
			commonAlert("사용자 이메일을 입력하세요.", "alert");
			return false;
		}		
		if(!regEmail.test($scope.detailUser.user_email)){
			commonAlert("이메일 형식을 확인해주세요.", "alert");
			return false;
		}
		if($scope.phone1 != null || !$scope.phone1.isEmpty()){			
			if(!regPhone.test($scope.phone1+"-"+$scope.phone2+"-"+$scope.phone3)){
				commonAlert("연락처 형식을 확인해주세요.", "alert");
				return false;
			}else{
				$scope.detailUser.user_phone = $scope.phone1+"-"+$scope.phone2+"-"+$scope.phone3;
			}
		}
		if($scope.detailUser.user_type_code == null || $scope.detailUser.user_type_code.isEmpty()){
			commonAlert("사용자 유형을 선택하세요.", "alert");
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
				if(data.resultCount==0){
					commonAlert("비밀번호는 영문,숫자를 포함한 8자이상16자 이하입니다.", "alert");
				}else{
					$scope.fnGetList();
				}
				
			});
		}else{
			$http.post(_context+"/api/account/update",{detail:$scope.detailUser})
			.success(function(data){
				commonComplete(data);
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
		$http.post("/api/meta/adm/sido/list",{}).success(function(data){
			commonComplete(data);
			$scope.listSido=data.listAdm;
			
		});
	};
	$scope.fnListGungu=function(){
		$http.post("/api/meta/adm/gungu/list",{paramSido:$scope.selectedSido}).success(function(data){
			commonComplete(data);
			$scope.listGungu=data.listAdm;
			
		});
	};
	$scope.fnListDong=function(){
		$http.post("/api/meta/adm/dong/list",{paramGungu:$scope.selectedGungu}).success(function(data){
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
		$http.post("/api/account/find/id",{detailUser:$scope.detailUser}).success(function(data){
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
		$http.post("/api/account/find/pwd",{detailUser:$scope.detailUser}).success(function(data){
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

	  
	$scope.fnOnClickBtnLogin=function(){

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