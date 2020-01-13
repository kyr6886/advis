noaaAdmin.directive('vworld',['$http',function($http){
	return{
		restrict:'E,C',
		scope:{key:'=',callbackClick:'='},
		templateUrl:_context+'/contents/base/directives/map-vworld.html?1=1',
		replace: true,
		link:function(scope,element,attrs){
			
			
		}
	};
}]);