noaaAdmin.directive('lawSelector',['$http',function($http){
	return{
		restrict:'E,C',
		scope:{binding:'=',callbackClick:'='},
		templateUrl:_context+'/contents/base/directives/law-selector.html?3=1',
		replace: true,
		link:function(scope,element,attrs){
			scope.listSido=[];
			scope.listGungu=[];
			scope.listDong=[];
			scope.listRi=[];
			scope.selectedSido="";
			scope.selectedGungu="";
			scope.selectedDong="";
			scope.selectedRi="";
			scope.layerClass=null;
			scope.btnClass=null;
			scope.fieldText=null;
			scope.searchText=null;
			
			scope.isUseGungu=true;
			scope.isUseDong=true;
			scope.isUseRi=true;
			if(scope.binding!=undefined && scope.binding!=null){
				scope.isUseGungu=scope.binding.isUseGungu;
				scope.isUseDong=scope.binding.isUseDong;
				scope.isUseRi=scope.binding.isUseRi;
				
				scope.layerClass=scope.binding.layerClass;
				scope.btnClass=scope.binding.btnClass;
				scope.fieldText=scope.binding.fieldText;
				if(scope.binding.dong!=undefined && scope.binding.dong!=null && scope.binding.dong!=""){
					
					scope.selectedSido=scope.binding.dong.substring(0,2);
					scope.selectedGungu=scope.binding.dong.substring(0,5);
					scope.selectedDong=scope.binding.dong.substring(0,8);
					scope.selectedRi=scope.binding.ri;
				}
			}
			var fnSetSync=function(){
				scope.binding.info={
					sido:scope.selectedSido
					,gungu:scope.selectedGungu
					,dong:scope.selectedDong
					,ri:scope.selectedRi
					,searchText:scope.searchText
				};
			}
			
			scope.fnListSido=function(isEvt){
				$http.post(_context+"/api/meta/law/sido/list",{}).success(function(data){
					commonComplete(data);
					scope.listSido=data.listLaw;
					fnSetSync();
					if(isEvt)
						scope.fnListGungu(isEvt)
				});
			};
			scope.fnListGungu=function(isEvt){
				$http.post(_context+"/api/meta/law/gungu/list",{paramSido:scope.selectedSido}).success(function(data){
					//commonComplete(data);
					scope.listGungu=data.listLaw;
					fnSetSync();
					if(isEvt)
						scope.fnListDong(isEvt)
				});
			};
			scope.fnListDong=function(isEvt){
				$http.post(_context+"/api/meta/law/dong/list",{paramGungu:scope.selectedGungu}).success(function(data){
					//commonComplete(data);
					scope.listDong=data.listLaw;
					fnSetSync();
					if(isEvt)
						scope.fnListRi(isEvt)
				});
			}
			scope.fnListRi=function(){
				$http.post(_context+"/api/meta/law/ri/list",{paramDong:scope.selectedDong}).success(function(data){
					commonComplete(data);
					scope.listRi=data.listLaw;
					fnSetSync();
				});
			}
			
			scope.fnChangeSido=function(){
				scope.listGungu=[];
				scope.listDong=[];
				scope.listRi=[];
				scope.selectedGungu="";
				scope.selectedDong="";
				scope.selectedRi="";
				
				scope.fnListGungu();
				
			}
			scope.fnChangeGungu=function(){
				scope.listDong=[];
				scope.listRi=[];
				
				scope.fnListDong();				
				
				scope.selectedDong="";
				scope.selectedRi="";
			}
			scope.fnChangeDong=function(){
				scope.listRi=[];
				
				scope.fnListRi();				
				
				scope.selectedRi="";
			}
			scope.fnChangeRi=function(){
				fnSetSync();
			}
			
			scope.fnOnClickSearch=function(){
				 scope.callbackClick({sido:scope.selectedSido
					 ,gungu:scope.selectedGungu
					 ,dong:scope.selectedDong
					 ,ri:scope.selectedRi
					 ,searchText:scope.searchText});
			}
			
			if(scope.binding.dong!=undefined && scope.binding.dong!=null && scope.binding.dong!=""){
				scope.fnListSido(true);
			}else{
				scope.fnListSido(false);
			}
			
			scope.$watch('binding',function(newVal,oldVal){
		    	 
	    		 if(newVal!=oldVal){
	    			 if(scope.binding.dong!=undefined && scope.binding.dong!=null && scope.binding.dong!=""){
	 					scope.selectedSido=scope.binding.dong.substring(0,2);
	 					scope.selectedGungu=scope.binding.dong.substring(0,5);
	 					scope.selectedDong=scope.binding.dong.substring(0,8);
	 					scope.selectedRi=scope.binding.dong;
	 					scope.fnListSido(true);
	 				}else{
	 					scope.listGungu=[];
	 					scope.listDong=[];
	 					scope.listRi=[];
	 					scope.selectedSido="";
	 					scope.selectedGungu="";
	 					scope.selectedDong="";
	 					scope.selectedRi="";
	 					scope.fnListSido(false);
	 				}
	    			 
	    		 }
	    			 
	    	 
	     });
		}
	};
	
}]);