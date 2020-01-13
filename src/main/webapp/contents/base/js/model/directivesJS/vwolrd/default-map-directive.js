

noaaAdmin.directive('defaultMap',['$http',function($http, $window){
	return{
		restrict:'E,C',
		scope:{binding:'=',callbackFunc:'=',mapWidth:'=',mapHeight:'='},
		templateUrl:'/contents/base/directives/vworld/default-map.html?1=1',
		replace: true,
		link:function(scope,element,attrs){
			var pos;
			var _bounds=scope.binding;
			//scope.binding.mapBound.split(',');
			var _mapMinZoom=scope.binding.mapMinZoom;
			var _mapMaxZoom=scope.binding.mapMaxZoom;
			var _apiMap;
			var _mControl;//마커이벤트변수
			var fnSetPointObject=function(){
				var pointOptions = {persist:true};//포인트옵션
				if (_mControl == null) {//마커컨트롤이 정의 되어 있지 않으면
					
					_mControl =	new OpenLayers.Control.Measure(
								OpenLayers.Handler.Point,
								{handlerOptions:pointOptions});//포인트 객체 생성
					_mControl.events.on({"measure":fnOnClickPoint});//객체를 클릭이벤트 등록
			    	_apiMap.addControl(_mControl);//나의 map에 객체 추가
				}        
				_mControl.activate();//마커컨트롤 활성화

			};
			var fnOnClickPoint=function(event){
				var temp = event.geometry;//마커 클릭이벤트시 나오는 좌표	
				 pos = new OpenLayers.LonLat(temp.x, temp.y);//좌표값 셋팅
				
			};
			
			scope.fnOnClickChoice=function(){
				window.opener.fnCallBackPosition(pos);
				window.open('','_self').close();
			};
			
			scope.fnOnClickInit=function(){
				_apiMap.init();
				fnSetPointObject();
			}
			
			vworld.init(_bounds.mapID,"base",function(){
				
				vworldMap.push({"id":_bounds.mapID,"map":{}});
				for(var i=0;i<vworldMap.length;i++){
					if(vworldMap[i].id==_bounds.mapID){
						vworldMap[i].map=this.vmap;
						break;
					}
				}
				vworldMap["map"]=this.vmap;
				_apiMap.se
				tBaseLayer(_apiMap.vworldBaseMap);
				_apiMap.setControlsType({"simpleMap":_bounds.isSimpleMap}); //간단한 화면
				if(_bounds.isZoomBar)
				//_apiMap.addVWORLDControl();//panzoombar등록
				_apiMap.setCenterAndZoom(_bounds.center.x, _bounds.center.y, _bounds.center.level);//화면중심점과 레벨로 이동 (초기 화면중심점과 레벨)  

			});
		}
	};
	
}]);