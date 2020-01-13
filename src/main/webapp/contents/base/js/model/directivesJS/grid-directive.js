noaaAdmin.directive('gridBasic',['$http',function($http){
	return{
		restrict:'E,C',
		scope:{binding:'='},
		templateUrl:_context+'/contents/base/directives/grid-basic.html?3=1',
		replace: true,
		link:function(scope,element,attrs){
			scope.dataSet=scope.binding;
			
			if(scope.dataSet.column.length==0){
			  if(scope.dataSet.data.length>0){
				  var dataColumns=Object.keys(scope.dataSet.data[0]);
				  console.log(dataColumns);
				  scope.dataSet.column=[];
				  for(var i=0;i<dataColumns.length;i++){
					 scope.dataSet.column.push({column:dataColumns[i],width:150});
				  }
			  }	
			}
			console.log(scope.dataSet);
		}
	};
	
}]);