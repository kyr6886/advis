noaaAdmin.directive('sitemarquee',['$http',function($http){
	return{
		restrict:'E,C',
		templateUrl:_context+'/contents/base/directives/site-marquee.html',
		replace: true,
		link:function(scope,element,attrs){
			scope.siteMarquee=[];
			
			scope.fnInitMarquee = function(){
				$http.post("/api/partner/site/list",{})
				.success(function(data){
					scope.siteMarquee = data.list;
				});
			}
			
			scope.fnOnClickGoMarquee = function(paramUrl){
				console.log(paramUrl);
				window.open(paramUrl);
			}
			
			scope.fnInitMarquee();
		}
	};
}]);