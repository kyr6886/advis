

noaaAdmin.directive('posSelector',['$http',function($http, $window){
	return{
		restrict:'E,C',
		scope:{binding:'=',callbackFunc:'=',mapWidth:'=',mapHeight:'='},
		templateUrl:'/contents/base/directives/vworld/select-pos.html?1=1',
		replace: true,
		link:function(scope,element,attrs){
			var pos;
			var _bounds=scope.binding;
			//scope.binding.mapBound.split(',');
			var _mapMinZoom=scope.binding.mapMinZoom;
			var _mapMaxZoom=scope.binding.mapMaxZoom;
			var _apiMap;
			var _mControl;//마커이벤트변수
			scope.pager={
					totalCount : 1,
					rowSize : 5,
					blockSize : 10,
					currentPage : 1
			}
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
				// scope.callbackFunc(pos);
			};
			scope.fnOnClickSearchArea=function(page){
				page = page == null ? 1: page;
				$http.post("/api/vwolrd/search/poi",{paramArea:scope.searchKeyWord,paramKey:'C026F532-62FC-3086-A520-937A6AA26B74',pageUnit:5,pageIndex:page}).success(function(data){
					try{
						if(data!=null && data.LIST!=null && data.LIST.length>0){
							scope.listPoi = data.LIST;
							if(data.LIST.length == 1){
								_apiMap.setCenter(new OpenLayers.LonLat(data.LIST[0].xpos, data.LIST[0].ypos).transform(_apiMap.displayProjection,_apiMap.projection),18);
							}
							scope.pager={
									totalCount : data.paginationInfo.totalRecordCount,
									rowSize : 5,
									blockSize : 10,
									currentPage : page
							}
							/*var tmpArr =['27290119','27290121','27290106','27290105'];
							var codes = "";
							for(var i=0;i<tmpArr.length;i++){
								if(i == tmpArr.length-1) codes+=tmpArr[i];
								else codes+=tmpArr[i]+"','";
							}
							//var tmpPolyColor = "#E04050";
							//var tmpStrokeColor = "#E08040";
							var tmpPolyColor = "#ffc0cb";
							var tmpStrokeColor = "#7f60e5";
							var _obj = {
									//layers : "noaa.adm:TL_SCCO_CTPRVN"
									//layers : "noaa.adm:TL_SCCO_SIG"
									//layers : "noaa.adm:TL_SCCO_EMD"
									layers : "noaa.adm:TL_SCCO_LI"
									//,cql_filter : "CTPRVN_CD LIKE '11%'"
									//,cql_filter : "SIG_CD LIKE '11%'"
									//,cql_filter : "EMD_CD IN('"+codes+"')"
									,cql_filter : "LI_CD LIKE '437603%'"
									,sld:fnGetAdmSld("noaa.adm:TL_SCCO_LI",tmpPolyColor,tmpStrokeColor)
							};
							var layer = fnGetLiWMSLayer(_obj);
							_apiMap.addLayer(layer);*/
						}else{
							commonAlert("입력하신 정보를 찾을 수 없습니다.");
						}
					}catch(ex){
						commonAlert("입력하신 정보를 찾을 수 없습니다.");
					}
				});
			};
			scope.fnOnPagingClick = function(num){
				scope.fnOnClickSearchArea(num);
			};
			scope.fnOnClickMovePoi = function(item){
				_apiMap.setCenter(new OpenLayers.LonLat(item.xpos, item.ypos).transform(_apiMap.displayProjection,_apiMap.projection),17);
				 pos = new OpenLayers.LonLat(item.xpos, item.ypos).transform(_apiMap.displayProjection,_apiMap.projection);//좌표값 셋팅
			}
			scope.fnOnClickChoice=function(){
				if(pos == null){
					commonAlert('지점을 선택하세요.','alert');
					return false;
				}
				window.opener.fnCallBackPosition(pos);
				window.open('','_self').close();
			};
			
			scope.fnOnClickInit=function(){
				_apiMap.init();
				fnSetPointObject();
			}
			
			vworld.init("vMap","base",function(){
				_apiMap=this.vmap;
				_apiMap.setBaseLayer(_apiMap.vworldBaseMap);
				_apiMap.setControlsType({"simpleMap":_bounds.isSimpleMap}); //간단한 화면
				if(_bounds.isZoomBar)
				//_apiMap.addVWORLDControl();//panzoombar등록
				_apiMap.setCenterAndZoom(_bounds.center.x, _bounds.center.y, _bounds.center.level);//화면중심점과 레벨로 이동 (초기 화면중심점과 레벨)  
				fnSetPointObject();
			});
		}
	};
	
}]);