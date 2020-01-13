 var editor_object = [];
noaaAdmin.controller('bbsContentCtrl',['$scope', '$http', '$routeParams', '$location','$timeout','$sce',function($scope, $http, $routeParams, $location,$timeout,$sce){	
	//
	$scope.bbsID;
	$scope.listBBS=[];
	$scope.listContents=[];
	$scope.listCategories=[];
	$scope.detailContent={};
	$scope.pager={totalCount:0,rowSize:10,blockSize:10,currentPage:1};
	$scope.param={searchTitle:"",searchContent:""};
	$scope.fnListBBS=function(){
		
		if($routeParams.bbsID!=undefined){
			$scope.bbsID=$routeParams.bbsID;
		}
		if($routeParams.pageNo!=undefined){
			
			$scope.pager.currentPage=$routeParams.pageNo;
		}
		getListBBS(true);
	}
	
	var fnSetListCategories=function(targetItem){
		$scope.listCategories=[];
		if(targetItem.category_seqs!=null && targetItem.category_seqs!='' && targetItem.category_titles!=null){
			var seqs=targetItem.category_seqs.split(',');
			var titles=targetItem.category_titles.split(',');
			
			for(var i=0;i<seqs.length;i++){
				$scope.listCategories.push({category_seq:seqs[i],category_title:titles[i]});
			}
		
		}
	}
	
	
	
	var getListBBS=function(isCallContent){
		
		$http.post(_context+"/api/admin/board/list",{}).success(function(data){
			commonComplete(data);
			$scope.listBBS=data.listMaster;
			
			if($scope.bbsID==null){
				$scope.bbsID=$scope.listBBS[0].bbs_id;
				fnSetListCategories($scope.listBBS[0]);
			}else{
			
				for(var i=0;i<$scope.listBBS.length;i++){
					if($scope.listBBS[i].bbs_id==$scope.bbsID){
						fnSetListCategories($scope.listBBS[i]);
						break;
					}
				}
			}
			
			
			if(isCallContent)
				fnListContent();
		});
	}
	
	
	var fnListContent=function(paramID,paramPage){
		
		paramPage=paramPage==null||paramPage==0?$scope.pager.currentPage:paramPage;
	
		paramID=paramID==null?$scope.bbsID:paramID;
		$http.post(_context+"/api/admin/board/content/list",{
			id:paramID,pageNo:paramPage
			,pageSize:$scope.pager.rowSize
			,searchTitle:$scope.param.searchTitle
			,searchContent:$scope.param.searchContent
			}).success(function(data){
			console.log(data);
			$scope.listContents=data.listContent;
			for(var i=0;i<$scope.listContents.length;i++){
				$scope.listContents[i].create_date=fnConvertDateStr($scope.listContents[i].create_date);
			}
			$scope.pager={totalCount:data.totalCount,rowSize:10,blockSize:10,currentPage:paramPage}
			
		
			
		});
	}
	
	var fnDetailContent=function(paramID,paramSeq){
		
		$http.post(_context+"/api/admin/board/content/detail",{id:paramID,paramSeq:paramSeq}).success(function(data){
			commonComplete(data);
			$scope.detailContent=data.detailContent;
			$scope.detailContent.create_date=fnConvertDateStr(data.detailContent.create_date);
			$scope.detailContent.bbs_cont=$sce.trustAsHtml($scope.detailContent.bbs_cont);
			 $timeout( function(){
			   
			   if(editor_object.length>0){
				   editor_object.getById["smartEditor"].exec("SET_IR", [$scope.detailContent.bbs_cont]);
			   }
		     }, 1000 );
			
		});
	}
	
	var fnDeleteContent=function(paramSeq){
		$http.post(_context+"/api/admin/board/content/delete",{id:$scope.bbsID,paramSeq:paramSeq}).success(function(data){
			commonComplete(data);
			fnListContent();
		});
	}
	
	$scope.fnOnChangeBBS=function(){
		$scope.pager.currentPage=1;
		fnListContent($scope.bbsID);
	}
	
	$scope.fnOnSelectCategories=function(){
		getListBBS(false);
	}
	
	
	$scope.fnOnPagingClick=function(num){
		fnListContent($scope.bbsID,num);
	}
	
	$scope.fnOnDeleteContent=function(paramSeq){
		fnDeleteContent(paramSeq);
	}
	 
	$scope.fnOnFileDelete=function(paramSeq){
		if(confirm("파일을 삭제하시겠습니까?")){
			$http.post(_context+"/api/admin/board/file/delete",{id:paramSeq}).success(function(data){
				$scope.detailContent.listFiles=data.listAttachFiles;
				if(data.listAttachFiles==null || data.listAttachFiles.length==0){
					$scope.detailContent.file_grp_id=null;
					
				}
				
			});
		}
	}
	$scope.fnOnList=function()
	{
		var url=$.stringFormat("/bbsContentList/{0}/{1}",$routeParams.bbsID,$routeParams.pageNo); 
		
		$location.path(url);
		
	}
	

	
	
	$scope.fnDeleteContent=function(paramContentSeq){
		$http.post(_context+"/api/admin/board/content/delete",{id:$scope.bbsID,paramSeq:paramContentSeq}).success(function(data){
			commonComplete(data);
			$scope.listContents=data.listContent;
		});
	}
	
	$scope.fnClickMoveCreate=function(){
		
		$location.path("/bbsContentWrite/"+$scope.bbsID);
	}
	
	$scope.fnClickMoveUpdate=function(){
		
		var url=$.stringFormat("/bbsContentUpdate/{0}/{1}/{2}",$scope.bbsID,$scope.detailContent.bbs_seq,$routeParams.pageNo); 
		
		$location.path(url);
	}
	
	$scope.fnContentWriteInit=function(){
		$scope.bbsID=$routeParams.bbsID; 
		getListBBS(false);
	}
	
	$scope.fnContentDetailInit=function(){
		$scope.bbsID=$routeParams.bbsID;
		fnDetailContent($routeParams.bbsID,$routeParams.seq);
	}
	
	
	$scope.fnOnSubmit=function(){
		if($scope.detailContent.bbs_cont_title == null || $scope.detailContent.bbs_cont_title.isEmpty()){
			commonAlert("제목을 입력하세요.","alert");
			return false;
		}
		editor_object.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);
		var bbs_cont = document.getElementById("smartEditor").value;
		if(bbs_cont == null || bbs_cont.isEmpty() || bbs_cont == '<p>&nbsp;</p>'){
			commonAlert("내용을 입력하세요.","alert");
			return false;
		}else{
			
		}
		document.forms["mainForm"].action=_context+"/admin/board/content/create";
		document.forms["mainForm"].submit();
		
		//$scope.selectedBoard.bbs_info_html=$("#smartEditor").val();//.replace(/</g,"&lt;").replace(/>/g,"&gt;");
	}
	
	$scope.fnOnUpdateSubmit=function(){
		if($scope.detailContent.bbs_cont_title == null || $scope.detailContent.bbs_cont_title.isEmpty()){
			commonAlert("제목을 입력하세요.","alert");
			return false;
		}
		editor_object.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);
		var bbs_cont = document.getElementById("smartEditor").value;
		if(bbs_cont == null || bbs_cont.isEmpty() || bbs_cont == '<p>&nbsp;</p>'){
			commonAlert("내용을 입력하세요.","alert");
			return false;
		}else{
			
		}
		document.forms["mainForm"].action=_context+"/admin/board/content/update";
		document.forms["mainForm"].submit();
		
		//$scope.selectedBoard.bbs_info_html=$("#smartEditor").val();//.replace(/</g,"&lt;").replace(/>/g,"&gt;");
	}
	
	$scope.fnOnClickSearch=function(){
		fnListContent();
	}
	
	//일반 페이지에서 리스트 호출시  사용
	$scope.fnListContentByClient=function(paramID,paramPage){
		$scope.bbsID=paramID;
		fnListContent(paramID,paramPage);
	}
	//일반 페이지에서 상세 호출시  사용
	$scope.fnContentDetailInitByClient=function(paramID,paramSeq){
		$scope.bbsID=paramID
		fnDetailContent(paramID,paramSeq);
	}
	
	
	
	
}]);