noaaAdmin.directive('sitelink',['$http',function($http){
	return{
		restrict:'E,C',
		templateUrl:_context+'/contents/base/directives/site-link.html',
		replace: true,
		link:function(scope,element,attrs){
			scope.website=[];
			scope.dynamicSelect="";
			
			scope.fnInit = function(){
				$http.post("/api/partner/site/list",{})
				.success(function(data){
					scope.website = data.list;
				});
			}
			
			scope.fnOnClickGoSite = function(){
				window.open(scope.dynamicSelect);
				scope.dynamicSelect="";
			}
			
			scope.fnInit();
		}
	};
}]);