noaaAdmin.controller('manageStatusCtrl',['$scope', '$http', '$routeParams', '$location','Upload',function($scope, $http, $routeParams, $location , Upload){
	$scope.list=[];
	$scope.searchList=[];
	$scope.listLevels=[];
	$scope.listLevel1=[];
	$scope.listLevel2=[];
	$scope.listLevel3=[];
	$scope.listLevel4=[];
	$scope.listLevel5=[];
	$scope.fileList=[];
	$scope.disTitle='';
	$scope.disType='NTY';
	$scope.disDate='';
	$scope.file_grp_id = "";
	$scope.file=null;
	$scope.selectedFile=[];
	$scope.isCreate=false;
	$scope.selectedCategory='NTY';
	var n=0;
	$scope.isUpdate=false;
	$scope.eventItemList=[];
	$scope.selectedEvtId = "";
	$scope.listEventGroup=[];
	$scope.selectedEventGroup='';
	
	$scope.fnInit = function(){
		$scope.fnGetSearchList();
	}
	
	$scope.fnClickAddFile=function(){
		$scope.fn
	}
	
	$scope.fnClickCloseCreate=function(){
		$scope.isCreate=false;
		$scope.isUpdate=false;
	}
	
	$scope.fnClickBtnCreate=function(){
		$scope.fnGetList();
		$scope.isCreate=true;
		$scope.isUpdate=false;
		$scope.selectedEvtId = "";
		$scope.disTitle = "";
		$scope.disDate = "";
	}
	
	//목록
	fnListEvents=function(){
		$http.post("/api/std/event/list",{paramCtgId:$scope.selectedCategory})
		.success(function(response){
			$scope.listEventGroup=response.eventList;
			if($scope.listEventGroup!=null && $scope.listEventGroup.length>0){
				$scope.selectedEventGroup=$scope.listEventGroup[0].evt_id;
			}
			if($scope.isUpdate){
				fnGetEventItem();
				
			}
		});
	}

	
	$scope.fnOnClickDeleteFile=function(item){		
		$scope.fileList.splice(item.index,1)
	}
	$scope.fnOnFileSelect=function($files){
		if($files[0] !=undefined){
			$scope.fileList.push({index:n++,fileInfo:$files[0]  });
		}
		
	}
	
	$scope.fnSave=function() {
		console.log("save");
		var paramList=[];
		for(var i=0;i<$scope.listLevel3.length;i++){
			if($scope.listLevel3[i].value!=''){
				paramList.push($.stringFormat("{0}|{1}",$scope.listLevel3[i].code,$scope.listLevel3[i].value));
			}
		}
		
		if(paramList.length>0){
			$http.post("/api/std/eventItem/Create",{
				 paramEvtGrp:$scope.selectedEventGroup
				,paramEvents:paramList
				,paramTitle:$scope.disTitle
				,paramDisType:$scope.selectedCategory
				,paramDisDateStr:$scope.disDate
				,paramFileId:$scope.file_grp_id}
			).success(function(data){
				alert("저장되었습니다.");
			});
		}
	}
	

	
	$scope.formatDate = function(date){
        var dateOut = new Date(date);
        return dateOut;
  };
	
	$scope.fnClickUpdateSave=function(){
		
		console.log("fnClickUpdateSave");
		$scope.fnSave();
		
	}
	
	$scope.fnClickDeleteSave=function(paramEvtId){
		
		$http.post("/api/std/event/all/delete",{paramEvtId:paramEvtId}).success(function(data){
			$scope.fnGetSearchList();
			if(data.resultCount>0){
				alert('삭제되었습니다.');
				return;
			}
		});
	}
	
	$scope.fnGetList=function(){
		$http.post("/api/std/tree/category/list",{paramCtgId:$scope.selectedCategory}).success(function(data){
			$scope.list=[];
			$scope.listLevel1=[];
			$scope.listLevel2=[];
			$scope.listLevel3=[];
			$scope.listLevel4=[];
			$scope.listLevel5=[];
			data.list[0].parent_ctg_id=null;
			for(var i=0;i<data.list.length;i++){
				if(data.list[i].depth>1){
					$scope.list.push({
						 code:data.list[i].ctg_id
						,title:data.list[i].title
						,level:data.list[i].depth-1
						,parent:data.list[i].parent_ctg_id
						,itemGroup:1
						,value:''	
					}
						
					);
				}
			}
			
			for(var i=0;i<$scope.list.length;i++){
				if($scope.list[i].level==1) $scope.listLevel1.push($scope.list[i]);
				if($scope.list[i].level==2) $scope.listLevel2.push($scope.list[i]);
				if($scope.list[i].level==3) $scope.listLevel3.push($scope.list[i]);
				if($scope.list[i].level==4) $scope.listLevel4.push($scope.list[i]);
				if($scope.list[i].level==5) $scope.listLevel5.push($scope.list[i]);
			}
			fnListEvents();
		});
	}
	
	$scope.fnHasCount=function(paramParentCode){
		var rs=0;
		for(var i=0;i<$scope.list.length;i++){
			if($scope.list[i].parent==paramParentCode) rs++;
		}
		return rs;
	}
	
	$scope.fnGetSearchList=function(){
		$http.post("/api/std/tree/event/searchList",{paramCtgId:$scope.selectedCategory}).success(function(data){
			
			$scope.searchList = [];
			
			for (var i=0; i<data.resultList.length; i++) {
				var tempData = {division:"", evt_id:"", print_id:"", title:"", evt_date:"", evt_date_print:""};
				tempData.division = data.resultList[i].division;
				tempData.evt_id = data.resultList[i].evt_id;
				tempData.print_id = data.resultList[i].evt_id.split("-")[0] + data.resultList[i].evt_id.split("-")[1];
				tempData.title = data.resultList[i].title;
				tempData.evt_date = data.resultList[i].evt_date;
				tempData.evt_date_print = fnPrintDateTimeByNum(data.resultList[i].evt_date);
				
				$scope.searchList.push(tempData);
			}

		});
	}
	
	$scope.fnClickAddItems=function(paramParentCode,paramFindLevel){
		var maxItemGroup=1;
		for(var i=0;i<$scope.list.length;i++){
			if($scope.list[i].parent==paramParentCode){
				if($scope.list[i].itemGroup>=maxItemGroup){
					maxItemGroup=$scope.list[i].itemGroup;
				}
			}
		}
		
		for(var i=0;i<$scope.list.length;i++){
			if($scope.list[i].parent==paramParentCode){
				var obj={
						 code:$scope.list[i].code
							,title:$scope.list[i].title
							,level:$scope.list[i].level-1
							,parent:$scope.list[i].parent
							,itemGroup:maxItemGroup+1
							,value:''	
						};
				$scope.listLevel3.push(obj);
			
			}
		}
	}
	
	$scope.fnOnClickEvtDetail = function(paramItem){
		
		$scope.selectedEvtId = paramItem.evt_id;
		$scope.disTitle = paramItem.title;
		$scope.disDate = paramItem.evt_date;
		$scope.isUpdate = true;
		$scope.isCreate = false;
		$scope.fnGetList();
		
		
	}
	
	fnGetEventItem=function(){
		$http.post("/advis/code/tree/eventItem/list",{paramEvtId:$scope.selectedEvtId}).success(function(data){
			$scope.eventItemList = data.eventItemList; 
			
			var levelAddCntList = [];
			for(var i=0; i<$scope.eventItemList.length; i++){
				var boo = false;
				
				if(levelAddCntList.length == 0){
					var temp = {ctg_id:$scope.eventItemList[0].ctg_id, cnt:1};
					levelAddCntList.push(temp);
					boo = true;
				}else{
					for(var j=0; j<levelAddCntList.length; j++){
						if(levelAddCntList[j].ctg_id == $scope.eventItemList[i].ctg_id){
							levelAddCntList[j].cnt++;
							boo = true;
						}
					}
				}
				if(boo==false){
					var temp = {ctg_id:$scope.eventItemList[i].ctg_id, cnt:1};
					levelAddCntList.push(temp);
				}
			}
			
			for(var i=0; i<levelAddCntList.length; i++){
				if(levelAddCntList[i].cnt > 1){
					for(var j=0; j<levelAddCntList[i].cnt; j++){
						$scope.fnClickAddItems(levelAddCntList[i].ctg_id.substring(0,10), 3);
					}
				}
			}
			
			for(var i=0; i<$scope.eventItemList.length; i++){
				for(var j=0; i<$scope.listLevel3.length; j++){
					if($scope.eventItemList[i].ctg_id == $scope.listLevel3[j].code){
						if($scope.listLevel3[j].value == ""){
							$scope.listLevel3[j].value = $scope.eventItemList[i].contents;
							break;
						}
					}
				}
			}
		});
		
	}
	
}]);
