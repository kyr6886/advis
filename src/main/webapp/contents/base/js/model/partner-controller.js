noaaAdmin.controller('partnerCtrl',['$scope', '$http', '$routeParams', '$location',function($scope, $http, $routeParams, $location){
	$scope.isShowCreate=false;
	$scope.isEnableUpdate=false;
	$scope.siteList=[];
	
	$scope.detail={
		site_url : '',
		siteUrlBefore : '',
		site_title : '',
		site_thumb : '',
		file_grp_id:'',
		file_org_name:''
	};
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
	};
	


	$scope.fnClickBtnShowCreateForm=function(){
		$scope.detail.site_url = '';
		$scope.detail.site_title = '';
		$scope.detail.site_thumb = '';
		$scope.isShowCreate=true;
		$scope.isEnableUpdate = false;
	}
	$scope.fnClickHideForm=function(){
		$scope.isShowCreate=false;
	}
	//관련사이트 리스트 초기 실행
	$scope.fnSiteInit=function(){
		$http.post(_context+"/api/partner/site/list",{
		}).success(function(data){
			commonComplete(data);
			$scope.siteList = data.list;
			//$scope.pager={totalCount:data.totalCount,rowSize:10,blockSize:10,currentPage:$scope.pager.currentPage};
		})
	};
	//선택한 사이트 정보 불러오기
	$scope.fnOnClickUpdate = function(item){
			$scope.isShowCreate=true;
			$scope.isEnableUpdate = true;
			$scope.detail=item;
			$scope.detail.siteUrlBefore=item.site_url;
	};
	//관련사이트 등록 함수.
	$scope.fnOnCreateAction=function(){
		//urlValidation
		var urlRe = /^(http[s]?:\/\/)/;
		if($scope.detail.site_url == null || $scope.detail.site_url.isEmpty()){
			commonAlert('사이트 URL입력을 해주세요.','alert');
			return false;
		}
//		if(!urlRe.test($scope.site.siteUrl)){
//			commonAlert('URL형식이 잘못되었습니다.','alert');
//			return false;
//		}
		//제목 Validation
		if($scope.detail.site_title == null || $scope.detail.site_title.isEmpty()){
			commonAlert('사이트 제목을 해주세요.','alert');
			return false;
		}
		
		//submit
		document.forms["site"].action = '/api/partner/site/create';
		document.forms["site"].submit();
	}
	$scope.fnOnClickDeleteFile=function(paramUrl,paramImgCode){
		if(!confirm("삭제하시겠습니까?")) return false;
		$http.post(_context+"/api/partner/site/image/delete",{
			paramSiteUrl : paramUrl,
			paramFileGrpId : paramImgCode,
		}).success(function(data){
			$scope.detail.file_grp_id = null;
			$scope.detail.fileInfo=null;
		})
	};
	//관련사이트 삭제
	$scope.fnOnDropction=function(param){
		if(!confirm("삭제하시겠습니까?")) return false;
		$http.post(_context+"/api/partner/site/drop",{
			paramSiteUrl : param
		}).success(function(data){
			$scope.fnSiteInit();
		})
	};
	//사이트 사용유무
	$scope.fnClickUseYn = function(paramItem,useYn) {
		paramItem.use_yn = useYn;
		$http.post(_context+"/api/partner/site/useyn/update",{detail:paramItem})
		.success(function(data){
			$scope.fnSiteInit();
		});
	}
	$scope.fnOnUpdateAction=function(){
		//url Validation
		var urlRe = /^(http[s]?:\/\/)/;
		if($scope.detail.site_url.isEmpty()){
			commonAlert('사이트 URL입력을 해주세요.','alert');
			return false;
		}
		//제목 Validation
		if($scope.detail.site_title.isEmpty()){
			commonAlert('사이트 제목을 해주세요.','alert');
			return false;
		}
		//등록 확인후 submit
		document.forms["site"].action = '/api/partner/site/update';
		document.forms["site"].submit();
	}

	if($routeParams.messageCode!=undefined){
		commonAlert("이미지만 업로드 가능 합니다.", "alert");
	}
}]);