noaaAdmin.controller('ocrCtrl',['$scope', '$http', '$routeParams', '$location',function($scope, $http, $routeParams, $location){
	$scope.isCreateViewOn = false;
	
	$scope.ctg_id = "";
	$scope.evt_id = "";
	$scope.title = "";
	$scope.file_org_name = "";
	
	$scope.listlargeCode = [];
	$scope.mainDataList = [];
	$scope.ocrInfo={title:'',list:[]}
	$scope.pager = {
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
	
	$scope.fnInit = function(){		
		$scope.isCreateViewOn = false;
		
		$http.post(_context+"/api/std/ocr/file/list",{
			pageNo : $scope.pager.currentPage-1,
			pageSize : $scope.pager.rowSize
		}).success(function(data){
			$scope.mainDataList = data.ocrList;
			
			$scope.pager = {
				totalCount : data.totalCount,
				rowSize : 10,
				blockSize : 10,
				pagerNums : [],
				currentPage : $scope.pager.currentPage,
				endPagerNum : 0,
				startBlockNum : 1,
				endBlockNum : 1,
				preBlockNum : "",
				nextBlockNum : ""		
			};
		});
		
		$http.post(_context+"/api/std/manageDisCodelist",{paramDepth:1,paramCtgId:null})
		.success(function(data){
			for(var i=0; i<data.list.length; i++) {
				if (data.list[i].depth == 1) {
					$scope.listlargeCode = data.list;
					break;
				}
			}
		});
	}
	
	$scope.fnClickOpenCreateView = function(){
		$scope.isCreateViewOn = true;
	}
	
	$scope.fnClickCloseCreateView = function(){
		$scope.isCreateViewOn = false;
	}
	
	$scope.fnClickSave = function(){
		if($scope.ctg_id == "" || $scope.title == ""){
			alert("빈칸이 존재합니다.");
			return;
		}
		
		if($scope.evt_id.length != 10 || isNaN(Number($scope.evt_id))){
			alert("발생일의 형식이 잘못되었습니다.\nYYYYMMDDHH");
			return;
		}
		
		var file = document.getElementById("selFile").files[0];

		if(file == undefined){
			alert("파일을 선택하세요.");
			return;
		}
		
		var ext = file.name.substring(file.name.indexOf(".")+1);
		
		if(ext != "png" && ext != "jpeg" && ext != "jpg" && ext != "gif" && ext != "bmp"){
			alert("이미지 파일만 업로드 가능합니다.");
			return;
		}
		
		if(file.size > 10485760){
			alert("10MB 넘는 이미지는 업로드가 불가능합니다.");
			return;
		}

		if(confirm("저장하시겠습니까?")){
			$http.post(_context+"/api/std/ocr/file/checkValidation",{
				paramCtgId:$scope.ctg_id,
				paramEvtId:$scope.evt_id
			}).success(function(data){
				if(data.resultCount == 0)
					document.forms["submitFile"].submit();
				else
					alert("재난구분과 발생일이 겹칩니다.");
			});
		}
	}
	
	$scope.fnOnPagingClick = function(num){
		if(num > 0){
			$scope.pager.currentPage = num;
			$scope.fnInit();
		}
	}
	
	$scope.fnOnClickDetail=function(obj){
		$scope.ocrInfo.title=obj.title;
		$http.post(_context+"/api/std/ocr/read",{
			paramCtgId:obj.ctg_id,
			paramEvtId:obj.evt_id
		}).success(function(data){
			if(data.listSbjRptOct!=null && data.listSbjRptOct.length>0){
				$('#myModal').modal('show');
				$scope.ocrInfo.list=data.listSbjRptOct;
			}
		});
	}
	
	$scope.fnClickDelete = function(ctg_id, evt_id){
		if(confirm("삭제하시겠습니까?")){
			$http.post(_context+"/api/std/ocr/file/delete",{
				paramCtgId:ctg_id,
				paramEvtId:evt_id
			}).success(function(data){
				if(data.resultCount > 0){
					$scope.pager.currentPage = 1;
					$scope.fnInit();
				}
				else
					alert("삭제를 실패했습니다.")
			});
		}
	}
}]);