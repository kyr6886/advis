noaaAdmin.directive('simpleCalendar',['$http',function($http){
	return{
		restrict:'E,C',
		scope:{binding:'=',callbackClick:'='},
		templateUrl:_context+'/contents/base/directives/simple-calendar.html',
		replace: true,
		link:function(scope,element,attrs){
			scope.nowYear;
			scope.nowMonth;
			scope.maxRow=0;
			var now = new Date(); // 날짜 객체 생성
			
			
			scope.days=[];
			scope.week=[1,2,3,4,5];
			scope.weekOfDay =[0,1,2,3,4,5,6];
			
			scope.fnSetDay=function(year,month){
				scope.days=[];
				var lastDate=new Date(year,month,0);
				var row=1;
				for(var i=1;i<=lastDate.getDate();i++){
					var _date=new Date(now.getFullYear(),now.getMonth(),i);
				
					if(_date.getDay()%7==0 && i>1) row++;
					scope.days.push({date:_date.getDate(),day:_date.getDay(),week:row,hasSchedule:false});
					scope.maxRow=row;
				}
				scope.nowYear=now.getFullYear();
				scope.nowMonth=now.getMonth()+1;
				
			}
			
			
			scope.nextMonth=function(){
				now=new Date(now.setMonth(now.getMonth()+1));
				scope.fnSetDay(now.getFullYear(),now.getMonth()+1);
				 scope.callbackClick(now);
			}
			scope.prevMonth=function(){
				var _m=now.getMonth()-1;
				now=new Date(now.setMonth(now.getMonth()-1));
				if(_m==now.getMonth()-1){
					now=new Date(now.setMonth(now.getMonth()-1));
				}
				scope.fnSetDay(now.getFullYear(),now.getMonth()+1);
				scope.callbackClick(now);
			}
			
			var fnSetUpdateDay=function(data){
				data.sort(fnSort);
				
				for(var i=0;i<data.length;i++){
					if(data[i].s_date!=null && data[i].s_date!=""){
						
						var spDate=data[i].s_date.split("-");
						var _targetDay=parseInt(spDate[2]);
						
						for(var k=1;k<scope.days.length;k++){
							if(_targetDay==parseInt(scope.days[k].date)){
								scope.days[k].hasSchedule=true;
								break;
							}
						}
					}
				}
				
			}
			
			var fnSort=function(first, second)
			{
			    if (first.s_date < second.s_date){
			    	return -1;
			    }
			    if (first.s_date > second.s_date)
			        return 1;
			   
			    return 0;
			}
			
			var y = now.getFullYear(); // 현재 연도
			var m = now.getMonth(); // 현재 월
			var d = now.getDate(); // 현재 일
			scope.fnSetDay(y,m+1);
			
			scope.$watch('binding',function(newVal,oldVal){
			    	 
		    		 if(newVal!=oldVal){
		    			 
		    		 	 fnSetUpdateDay(newVal);
		    		 }
		    	 
		     });
		}
	};
	
}]);