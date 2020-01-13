noaaAdmin.directive('pager',['$http',function($http){
	return{
		restrict:'E,C',
		scope:{binding:'=',callbackClick:'='},
		templateUrl:_context+'/contents/base/directives/pager.html',
		replace: true,
		link:function(scope,element,attrs){
			
			
			scope.pager={
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
			}
			
			//페이징 
			setPagerNums = function (value) {
		
				scope.pager.totalCount=value.totalCount==0?1:value.totalCount;
				scope.pager.rowSize=value.rowSize;
				scope.pager.blockSize=value.blockSize;
				scope.pager.currentPage=value.currentPage;
				scope.pager.endPagerNum = parseInt((scope.pager.totalCount - 1) / scope.pager.rowSize + 1);
				 var startBlock = parseInt(((scope.pager.currentPage - 1) / scope.pager.blockSize)) * scope.pager.blockSize + 1;
		         scope.pager.preBlockNum = startBlock == 1 ? "" : startBlock - 1;
		         scope.pager.startRowNum=(scope.pager.currentPage-1)*scope.pager.rowSize+1;
		         scope.pager.startBlockNum = startBlock;
		         var index = 0;
		         scope.pager.pagerNums = [];
		         for (var i = startBlock; i <= scope.pager.endPagerNum; i++) {
		             if (index < scope.pager.blockSize) {
		             	scope.pager.pagerNums.push(i);
		             	scope.pager.endBlockNum = i;
		                 if (i < scope.pager.endPagerNum) {
		                 	scope.pager.nextBlockNum = i + 1;
		                 } else {
		                 	scope.pager.nextBlockNum = "";
		                 }
		                 index++;
		             } else {
		                 break;
		             }
		         }
		    
		     };
		    
		     scope.fnOnClickPapger=function(item){
		    	 scope.pager.currentPage=item;
		    	 scope.callbackClick(item);
		     }
		 	scope.fnClickPreBlock=function(n){
		 		if(n>0)
		 		scope.fnOnClickPapger(n);
			}
			scope.fnClickNextBlock=function(n){
				if(n<scope.pager.endPagerNum)
				scope.fnOnClickPapger(n);
			}
		     
		     
		     scope.$watch('binding',function(newVal,oldVal){
		    	 
		    		 //if(newVal!=oldVal)
		    		 	 setPagerNums(newVal);
		    	 
		     });
			
			
		
		}
		
		
	};
	
}]);