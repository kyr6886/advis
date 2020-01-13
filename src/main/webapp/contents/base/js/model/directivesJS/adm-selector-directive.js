noaaAdmin.directive('admSelector',['$http',function($http){
	return{
		restrict:'E,C',
		scope:{binding:'=',callbackClick:'='},
		templateUrl:_context+'/contents/base/directives/adm-selector.html',
		replace: true,
		link:function(scope,element,attrs){
			
			scope.listSido=[];
			scope.listGungu=[];
			scope.listDong=[];
			scope.selectedSido="";
			scope.selectedGungu="";
			scope.selectedDong="";
			scope.layerClass=null;
			scope.btnClass=null;
			scope.fieldText=null;
			scope.searchText=null;
			scope.isInit=true;
			scope.isUseSido=true;
			scope.isUseGungu=true;
			scope.isUseDong=true;
			if(scope.binding!=undefined && scope.binding!=null){
				scope.isUseGungu=scope.binding.isUseGungu;
				scope.isUseDong=scope.binding.isUseDong;
				scope.layerClass=scope.binding.layerClass;
				scope.btnClass=scope.binding.btnClass;
				scope.fieldText=scope.binding.fieldText;
				if(scope.binding.dong!=undefined && scope.binding.dong!=null && scope.binding.dong!=""){
					
					scope.selectedSido=scope.binding.dong.substring(0,2);
					scope.selectedGungu=scope.binding.dong.substring(0,5);
					scope.selectedDong=scope.binding.dong;
				}
			}
			var fnSetSync=function(){
				scope.binding.info={sido:scope.selectedSido
						,gungu:scope.selectedGungu
						,dong:scope.selectedDong
						,searchText:scope.searchText
						};
			}
			
			scope.fnListSido=function(isEvt){
				$http.post(_context+"/api/meta/adm/sido/list",{}).success(function(data){
					commonComplete(data);
					scope.listSido=data.listAdm;
					fnSetSync();
					if(isEvt)
					scope.fnListGungu(isEvt)
				});
			};
			scope.fnListGungu=function(isEvt){
				$http.post(_context+"/api/meta/adm/gungu/list",{paramSido:scope.selectedSido}).success(function(data){
					//commonComplete(data);
					scope.listGungu=data.listAdm;
					fnSetSync();
					if(isEvt)
					scope.fnListDong(isEvt);
				});
			};
			scope.fnListDong=function(isEvt){
				$http.post(_context+"/api/meta/adm/dong/list",{paramGungu:scope.selectedGungu}).success(function(data){
					//commonComplete(data);
					scope.listDong=data.listAdm;
					fnSetSync();
				});
			}
			
			scope.fnChangeSido=function(){
				scope.listGungu=[];
				scope.listDong=[];
				
				scope.fnListGungu(false);
				
				scope.selectedGungu="";
				scope.selectedDong="";
			}
			scope.fnChangeGungu=function(){
				scope.listDong=[];
				
				scope.fnListDong();
				
				scope.selectedDong="";
			}
			scope.fnChangeDong=function(){
				fnSetSync();
			}
			
			scope.fnOnClickSearch=function(){
				 scope.callbackClick({sido:scope.selectedSido
					 ,gungu:scope.selectedGungu
					 ,dong:scope.selectedDong
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
		 					scope.selectedDong=scope.binding.dong;
		 					scope.fnListSido(true);
		 				}else{
		 					scope.listGungu=[];
		 					scope.listDong=[];
		 					scope.selectedSido="";
		 					scope.selectedGungu="";
		 					scope.selectedDong="";
		 					scope.fnListSido(false);
		 				}
		    			 
		    		 }
		    			 
		    	 
		     });
			
			
		}
	};
	
}]);