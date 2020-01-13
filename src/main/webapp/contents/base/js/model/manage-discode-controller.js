noaaAdmin.controller('managedisCtrl',['$scope', '$http', '$routeParams', '$location',function($scope, $http, $routeParams, $location){
	$scope.upd={};
	$scope.listlargeCode={};
	$scope.listmediumCode={};
	$scope.listsmallCode={};
	$scope.listsmallerCode={};
	$scope.listdetailedCode={};
	
	$scope.listcreatLargeItem=[];
	$scope.listcreatMediumItem=[];
	$scope.listcreatSmallItem=[];
	$scope.listcreatSmallerItem=[];
	$scope.listcreatDetailedItem=[];
	
	$scope.selectedGrp=null;
	var regexp = /^[0-9]*$/
	
	$scope.param={
			ctg_id : "",
			print_id : "",
			title : "",
			depth : 0,
			parent_ctg_id : "",
			use_yn : "",
			ctg_group : "",
				
	};
	
	$scope.fnInit = function(){
		$scope.fnList(1);

	}
	
	$scope.fnList=function(item,paramCtg){
		$scope.listlargeCode={};
		$scope.listmediumCode={};
		$scope.listsmallCode={};
		$scope.listsmallerCode={};
		$scope.listdetailedCode={};
		
		$http.post(_context+"/api/std/manageDisCodelist",{paramDepth:item,paramCtgId:paramCtg})
		.success(function(data){
			for(var i=0; i<data.list.length; i++) {
				if (data.list[i].depth == 1) {
					$scope.listlargeCode = data.list;
				} else if (data.list[i].depth == 2 && item == 2) {
					$scope.listmediumCode = data.list;
				} else if (data.list[i].depth == 3  && item == 3) {
					$scope.listsmallCode[i] = data.list[i];
				} else if (data.list[i].depth == 4  && item == 4) {
					$scope.listsmallerCode = data.list;
				} else if (data.list[i].depth == 5  && item == 5) {
					$scope.listdetailedCode = data.list;
				} 
			}
		});
	}
	
	$scope.fnOnClickDetailList=function(item) {
		
		$scope.listcreatItem = item;
		
		var paramDepth = item.depth+1;
		$http.post(_context+"/api/std/manageDisCodelist",{paramCtgId :item.ctg_id,paramDepth:paramDepth})
		.success(function(data){
			if (paramDepth == 2) {
				$scope.listcreatLargeItem.selectedDepth = "large";
				$scope.listmediumCode = data.list;
				$scope.listsmallCode = [];
				$scope.listsmallerCode = [];
				$scope.listdetailedCode = [];
			} else if (paramDepth == 3) {
				$scope.listcreatMediumItem.selectedDepth = "medium";
				$scope.listsmallCode = data.list;
				$scope.listsmallerCode = [];
				$scope.listdetailedCode = [];
			} else if (paramDepth == 4) {
				$scope.listcreatSmallItem.selectedDepth = "small";
				$scope.listsmallerCode = data.list;
				$scope.listdetailedCode = [];
			}  else if (paramDepth == 5) {
				$scope.listcreatDetailedItem.selectedDepth = "smaller";
				$scope.listdetailedCode = data.list;
			}
		});	
	}
	
	$scope.createCode=function(item){
		if (item == 1) {
			$scope.param.print_id = $scope.ctg_largeid;
			$scope.param.title = $scope.ctg_largetitle;
			$scope.param.ctg_id = $scope.ctg_largeid;
		} else if (item == 2) {
			$scope.param.print_id = $scope.ctg_mediumid;
			$scope.param.title = $scope.ctg_mediumtitle;
			$scope.param.ctg_id = $scope.listcreatLargeItem.ctg_group + $scope.ctg_mediumid;
			$scope.param.ctg_group = $scope.listcreatLargeItem.ctg_group
			$scope.param.parent_ctg_id = $scope.listcreatLargeItem.ctg_id;
		} else if (item == 3) {
			$scope.param.print_id = $scope.ctg_smallid;
			$scope.param.title = $scope.ctg_smalltitle;
			
			$scope.param.ctg_id = $scope.listcreatMediumItem.ctg_id + $scope.ctg_smallid;
			$scope.param.ctg_group = $scope.listcreatMediumItem.ctg_group
			$scope.param.parent_ctg_id = $scope.listcreatMediumItem.ctg_id;
			
		} else if (item == 4) {
			$scope.param.print_id = $scope.ctg_smallerid;
			$scope.param.title = $scope.ctg_smallertitle;
			$scope.param.ctg_id =   $scope.listcreatSmallItem.ctg_id + $scope.ctg_smallerid;
			$scope.param.ctg_group = $scope.listcreatSmallItem.ctg_group
			$scope.param.parent_ctg_id = $scope.listcreatSmallItem.ctg_id;
		} else if (item == 5) {
			$scope.param.print_id = $scope.ctg_detailedid;
			$scope.param.title = $scope.ctg_detailedtitle;
			$scope.param.ctg_id =   $scope.listcreatSmallerItem.ctg_id + $scope.ctg_detailedid;
			$scope.param.ctg_group = $scope.listcreatSmallerItem.ctg_group
			$scope.param.parent_ctg_id = $scope.listcreatSmallerItem.ctg_id;
		} 
		
		$scope.param.depth = item;
		$scope.param.use_yn = 'Y';
		
		$http.post(_context+"/api/std/category/discodeCreate",{param:$scope.param})
		.success(function(data){
			if (item ==1) {
				$scope.fnList(1);	
			} else if (item == 2) {
				$scope.fnList(1);	
				$scope.fnList(2,$scope.listcreatLargeItem.print_id);
			} else if (item == 3) {
				$scope.fnList(1);	
				$scope.fnList(2,$scope.listcreatLargeItem.print_id);
				$scope.fnList(3, $scope.listcreatMediumItem.ctg_id);
			} else if (item == 4) {
				$scope.fnList(1);	
				$scope.fnList(2,$scope.listcreatLargeItem.print_id);
				$scope.fnList(3, $scope.listcreatMediumItem.ctg_id  );
				$scope.fnList(4,$scope.listcreatSmallItem.ctg_id);
			} else if (item == 5) {
				$scope.fnList(1);	
				$scope.fnList(2,$scope.listcreatLargeItem.print_id);
				$scope.fnList(3, $scope.listcreatMediumItem.ctg_id  );
				$scope.fnList(4,$scope.listcreatSmallItem.ctg_id);
				$scope.fnList(5,  $scope.listcreatSmallerItem.ctg_id);
				
			}
		});
		
	}
	
	//그룹코드 선택
	$scope.fnSelectedGrp=function(paramItem){
		
		if (paramItem.depth == 1) {
			$scope.listcreatLargeItem=paramItem;
			for(var i=0;i<$scope.listlargeCode.length;i++){
				$scope.listlargeCode[i].selected=false;
			}	
		}  if (paramItem.depth == 2) {
			$scope.listcreatMediumItem=paramItem;
			for(var i=0;i<$scope.listmediumCode.length;i++){
				$scope.listmediumCode[i].selected=false;
			}	
		}  if (paramItem.depth == 3) {
			$scope.listcreatSmallItem=paramItem;
			for(var i=0;i<$scope.listsmallCode.length;i++){
				$scope.listsmallCode[i].selected=false;
			}	
		}  if (paramItem.depth == 4) {
			$scope.listcreatSmallerItem=paramItem;
			for(var i=0;i<$scope.listsmallerCode.length;i++){
				$scope.listsmallerCode[i].selected=false;
			}
		}	if (paramItem.depth == 5) {
				$scope.listcreatDetailedItem=paramItem;
				for(var i=0;i<$scope.listdetailedCode.length;i++){
					$scope.listdetailedCode[i].selected=false;
			}
			
		}
		paramItem.selected=true;
		$scope.selectedGrp=paramItem;
		
		$scope.fnOnClickDetailList(paramItem);
		$scope.fnUpdateCode(paramItem);
	}
	
	$scope.fnValuedationCheck=function(depth){
		var valcheckid = null;
		var valchecktitle = null;
		var valcheckDepth = null;
		
		if (depth == 1)			valcheckid = $scope.ctg_largeid, valchecktitle = $scope.ctg_largetitle;
		else if (depth == 2)	valcheckid = $scope.ctg_mediumid, valchecktitle = $scope.ctg_mediumtitle;
		else if (depth == 3)	valcheckid = $scope.ctg_smallid, valchecktitle = $scope.ctg_smalltitle;
		else if (depth == 4)	valcheckid = $scope.ctg_smallerid, valchecktitle = $scope.ctg_smallertitle;
		else if (depth == 5)	valcheckid = $scope.ctg_detailedid, valchecktitle = $scope.ctg_detailedtitle;
		
		
		if ( depth == 2 && $scope.listcreatItem.selectedDepth != undefined && $scope.listcreatItem.selectedDepth != "large" && $scope.listcreatItem.selectedDepth != "medium" && $scope.listcreatItem.selectedDepth != "small"  && $scope.listcreatItem.selectedDepth != "smaller") {
			alert("분류를 선택 후 추가해주세요.");
			return;
		}
		if ( depth == 3 && $scope.listcreatItem.selectedDepth != undefined && $scope.listcreatItem.selectedDepth != "small" &&  $scope.listcreatItem.selectedDepth != "medium"  && $scope.listcreatItem.selectedDepth != "smaller") {
			alert("대분류를 선택 후 추가해주세요.");
			return;
		}
		if ( depth == 4 && $scope.listcreatItem.selectedDepth != undefined && $scope.listcreatItem.selectedDepth != "small"  && $scope.listcreatItem.selectedDepth != "smaller") {
			alert("중분류를 선택 후 추가해주세요.");
			return;
		}
		if ( depth == 5 && $scope.listcreatItem.selectedDepth != undefined && $scope.listcreatItem.selectedDepth != "smaller") {
			alert("소분류를 선택 후 추가해주세요.");
			return;
		}
		
		if(valcheckid == null || valcheckid == ""){
			commonAlert("코드를 입력하세요.","");
			return;
		}

		if(valchecktitle == null || valchecktitle == ""){
			commonAlert("제목을 입력하세요.","");
			return;
		}
		
		$scope.createCode(depth);
	}
	$scope.fnUpdateCode=function(item){
		$scope.upd = {};

		if (item.depth == 1) {
			$scope.upd.largeId = item.print_id;
			$scope.upd.largeTitle = item.title;
		} else if ( item.depth ==2 ) {
			$scope.upd.mediumId = item.print_id;
			$scope.upd.mediumTitle = item.title;
			$scope.upd.largeId = $scope.listcreatLargeItem.print_id;
			$scope.upd.largeTitle = $scope.listcreatLargeItem.title;
		} else if ( item.depth ==3 ) {
			$scope.upd.smallId = item.print_id;
			$scope.upd.smallTitle = item.title;
			$scope.upd.mediumId = $scope.listcreatMediumItem.print_id;
			$scope.upd.mediumTitle = $scope.listcreatMediumItem.title;
			$scope.upd.largeId = $scope.listcreatLargeItem.print_id;
			$scope.upd.largeTitle = $scope.listcreatLargeItem.title;
		} else if ( item.depth == 4 ) {
			$scope.upd.smallerId = item.print_id;
			$scope.upd.smallerTitle = item.title;
			$scope.upd.smallId = $scope.listcreatSmallItem.print_id;
			$scope.upd.smallTitle = $scope.listcreatSmallItem.title;
			$scope.upd.mediumId = $scope.listcreatMediumItem.print_id;
			$scope.upd.mediumTitle = $scope.listcreatMediumItem.title;
			$scope.upd.largeId = $scope.listcreatLargeItem.print_id;
			$scope.upd.largeTitle = $scope.listcreatLargeItem.title;
		} else if ( item.depth == 5 ) {
			$scope.upd.detailedId = item.print_id;
			$scope.upd.detailedTitle = item.title;
			$scope.upd.smallerId = $scope.listcreatSmallerItem.print_id;
			$scope.upd.smallerTitle = $scope.listcreatSmallerItem.title;
			$scope.upd.smallId = $scope.listcreatSmallItem.print_id;
			$scope.upd.smallTitle = $scope.listcreatSmallItem.title;
			$scope.upd.mediumId = $scope.listcreatMediumItem.print_id;
			$scope.upd.mediumTitle = $scope.listcreatMediumItem.title;
			$scope.upd.largeId = $scope.listcreatLargeItem.print_id;
			$scope.upd.largeTitle = $scope.listcreatLargeItem.title;
		}
		
	}
	
	$scope.fnUpdateCodeAction=function(){
		
		if (($scope.upd.largeTitle == undefined || $scope.upd.largeTitle == "") && ($scope.upd.largeId != "" && $scope.upd.largeId != undefined)) {
			alert("수정할 대분류 제목을 입력해주세요.");
			return;
		}
		if (($scope.upd.mediumTitle == undefined || $scope.upd.mediumTitle == "") && ($scope.upd.mediumId != "" && $scope.upd.mediumId != undefined)) {
			alert("수정할 중분류 제목을 입력해주세요.");
			return;
		}
		if (($scope.upd.smallTitle == undefined || $scope.upd.smallTitle == "") && ($scope.upd.smallId != "" && $scope.upd.smallId != undefined)) {
			alert("수정할 소분류 제목을 입력해주세요.");
			return;
		}
		if (($scope.upd.detailedTitle == undefined || $scope.upd.detailedTitle == "") && ($scope.upd.detailedId != "" && $scope.upd.detailedId != undefined)) {
			alert("수정할 세분류 제목을 입력해주세요.");
			return;
		}
		
		if(confirm("저장 하시겠습니까?")){
			$http.post(_context+"/api/std/category/discodeUpdate",{param:$scope.upd})
			.success(function(data){
				if (item.depth ==1) {
					$scope.fnList(1);
				} else if (item.depth == 2) {
					$scope.fnList(1);
					$scope.fnList(2,$scope.listcreatLargeItem.ctg_id);
				} else if (item.depth == 3) {
					$scope.fnList(1);
					$scope.fnList(2,$scope.listcreatLargeItem.ctg_id);
					$scope.fnList(3,$scope.listcreatMediumItem.ctg_id);
				} else if (item.depth == 4) {
					$scope.fnList(1);
					$scope.fnList(2,$scope.listcreatLargeItem.ctg_id);
					$scope.fnList(3,$scope.listcreatMediumItem.ctg_id);
					$scope.fnList(4,$scope.listcreatSmallItem.ctg_id);
				}  else if (item.depth == 5) {
					$scope.fnList(1);
					$scope.fnList(2,$scope.listcreatLargeItem.ctg_id);
					$scope.fnList(3,$scope.listcreatMediumItem.ctg_id);
					$scope.fnList(4,$scope.listcreatSmallItem.ctg_id);
					$scope.fnList(5, $scope.listcreatSmallerItem.ctg_id);
				}
				
			});
		}
	}
	
	$scope.fnDeleteAction=function(depth){

		if (depth == 1 && $scope.listcreatLargeItem == "") {
			alert("분류를 선택해주세요.");
			return;
		}  
		if (depth == 2 && $scope.listcreatMediumItem == "") {
			alert("대분류를 선택해주세요.");
			return;
		}  
		if (depth == 3 && $scope.listcreatSmallItem == "") {
			alert("중분류를 선택해주세요.");
			return;
		} 
		if (depth == 4 && $scope.listcreatSmallerItem == "") {
			alert("소분류를 선택해주세요.");
			return;
		}
		if (depth == 5 && $scope.listcreatDetailedItem == "") {
			alert("세분류를 선택해주세요.");
			return;
		}
		var deleteParam = [];
		
		if (depth == 1) {
			deleteParam = $scope.listcreatLargeItem;
		} else if (depth == 2) {
			deleteParam = $scope.listcreatMediumItem 
		} else if (depth == 3) {
			deleteParam = $scope.listcreatSmallItem
		} else if (depth == 4) {
			deleteParam = $scope.listcreatSmallerItem;
		} else if (depth == 5) {
			deleteParam = $scope.listcreatDetailedItem;
		}
		
		if(confirm("삭제 하시겠습니까?")){
			$http.post(_context+"/api/std/category/discodeDelete",{param:deleteParam})
			.success(function(data){
				if (depth ==1) {
					$scope.fnList(1);
					$scope.listmediumCode = [];
					$scope.listsmallCode = [];
					$scope.listsmallerCode = [];
				} else if (depth == 2) {
					$scope.fnList(1);	
					$scope.fnList(2,$scope.listcreatLargeItem.ctg_id);
					$scope.listsmallCode = [];
					$scope.listsmallerCode = [];
				} else if (depth == 3) {
					$scope.fnList(1);	
					$scope.fnList(2,$scope.listcreatLargeItem.ctg_id);
					$scope.fnList(3,$scope.listcreatMediumItem.ctg_id );
					$scope.listsmallerCode = [];
					
				} else if (depth == 4) {
					$scope.fnList(1);	
					$scope.fnList(2,$scope.listcreatLargeItem.ctg_id);
					$scope.fnList(3,$scope.listcreatMediumItem.ctg_id );
					$scope.fnList(4,$scope.listcreatSmallItem.ctg_id);
					$scope.listdetailedCode = [];
				} else if (depth == 5) {
					$scope.fnList(1);	
					$scope.fnList(2,$scope.listcreatLargeItem.ctg_id);
					$scope.fnList(3,$scope.listcreatMediumItem.ctg_id );
					$scope.fnList(4,$scope.listcreatSmallItem.ctg_id);
					$scope.fnList(5,$scope.listcreatSmallerItem.ctg_id);
				}
			});
		}
	}
	
}]);