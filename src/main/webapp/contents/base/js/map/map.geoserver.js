var geoServerHost = "http://61.105.196.69";
var fnGetSidoWMSLayer = function(param){
	var paramObj;
	//레이어 전체
	if(param==null){
		paramObj={
				layers:"noaa.adm:TL_SCCO_CTPRVN",
			    transparent : 'true', // 지도의 바탕 색상
				bgColor : '0xFFFFFF', // 지도의 바탕 색상
				//exceptions : 'BLANK', // 서버상에 에러가 발생하면 빈 이미지로 반환 -INIMAGE,XML,BLANK
				isBaseLayer : false, // 이 레이어를 지도의 기본 레이어로 할 것인지 여부
				singleTile : false,
				opacity : 0.5,
				cql_filter : "1=1",
				sld:''
		}
	}else{
		//레이어 전체 or 필터
		paramObj={
				layers:param.layers==null || param.layers==undefined?"noaa.adm:TL_SCCO_CTPRVN":param.layers,
			    transparent : param.transparent == null || param.transparent==undefined ? 'true':param.transparent, // 지도의 바탕 색상
				bgColor : param.bgColor == null || param.bgColor==undefined  ? '0xFFFFFF':param.bgColor, // 지도의 바탕 색상
				//exceptions : param.exceptions == null || param.exceptions==undefined  ? 'BLANK': param.exceptions, // 서버상에 에러가 발생하면 빈 이미지로 반환 -INIMAGE,XML,BLANK
				isBaseLayer :  param.isBaseLayer == null || param.isBaseLayer==undefined  ? false:param.isBaseLayer, // 이 레이어를 지도의 기본 레이어로 할 것인지 여부
				singleTile :  param.singleTile == null || param.singleTile==undefined  ? false:param.singleTile,
				opacity : param.opacity == null || param.opacity==undefined  ? 0.5:param.opacity,
				cql_filter : param.cql_filter == null || param.cql_filter==undefined  ? "1=1" : param.cql_filter,
				sld: param.sld == null || param.sld==undefined  ? "" : param.sld
		}
	}
	
	var layer = new OpenLayers.Layer.WMS('시도', geoServerHost+"/geoserver/wms", {
		  layers : paramObj.layers, // WMS서버에서 제공하는 레이어
		  transparent : paramObj.transparent, // 지도의 바탕 색상
		  bgColor : paramObj.bgColor, // 지도의 바탕 색상
		 // exceptions : paramObj.exceptions, // 서버상에 에러가 발생하면 빈 이미지로 반환 -INIMAGE,XML,BLANK
		  cql_filter : paramObj.cql_filter,
		  sld_body:paramObj.sld
	  }, {
		  isBaseLayer : paramObj.isBaseLayer, // 이 레이어를 지도의 기본 레이어로 할 것인지 여부
		  singleTile : paramObj.singleTile,
		  opacity : paramObj.opacity
		  
	  });
	return layer;
};


var fnGetGunguWMSLayer = function(param){
	var paramObj;
	//레이어 전체
	if(param==null){
		paramObj={
				layers:"noaa.adm:TL_SCCO_SIG",
			    transparent : 'true', // 지도의 바탕 색상
				bgColor : '0xFFFFFF', // 지도의 바탕 색상
				//exceptions : 'BLANK', // 서버상에 에러가 발생하면 빈 이미지로 반환 -INIMAGE,XML,BLANK
				isBaseLayer : false, // 이 레이어를 지도의 기본 레이어로 할 것인지 여부
				singleTile : false,
				opacity : 0.5,
				cql_filter : "1=1",
				sld:''
		}
	}else{
		//레이어 전체 or 필터
		paramObj={
				layers:param.layers==null || param.layers==undefined?"noaa.adm:TL_SCCO_SIG":param.layers,
			    transparent : param.transparent == null || param.transparent==undefined ? 'true':param.transparent, // 지도의 바탕 색상
				bgColor : param.bgColor == null || param.bgColor==undefined  ? '0xFFFFFF':param.bgColor, // 지도의 바탕 색상
				//exceptions : param.exceptions == null || param.exceptions==undefined  ? 'BLANK': param.exceptions, // 서버상에 에러가 발생하면 빈 이미지로 반환 -INIMAGE,XML,BLANK
				isBaseLayer :  param.isBaseLayer == null || param.isBaseLayer==undefined  ? false:param.isBaseLayer, // 이 레이어를 지도의 기본 레이어로 할 것인지 여부
				singleTile :  param.singleTile == null || param.singleTile==undefined  ? false:param.singleTile,
				opacity : param.opacity == null || param.opacity==undefined  ? 0.5:param.opacity,
				cql_filter : param.cql_filter == null || param.cql_filter==undefined  ? "1=1" : param.cql_filter,
				sld: param.sld == null || param.sld==undefined  ? "" : param.sld,
		}
	}
	var layer = new OpenLayers.Layer.WMS('시군구', geoServerHost+"/geoserver/wms", {
		  layers : paramObj.layers, // WMS서버에서 제공하는 레이어
		  transparent : paramObj.transparent, // 지도의 바탕 색상
		  bgColor : paramObj.bgColor, // 지도의 바탕 색상
		  //exceptions : paramObj.exceptions, // 서버상에 에러가 발생하면 빈 이미지로 반환 -INIMAGE,XML,BLANK
		  cql_filter : paramObj.cql_filter,
		  sld_body:paramObj.sld
	  }, {
		  isBaseLayer : paramObj.isBaseLayer, // 이 레이어를 지도의 기본 레이어로 할 것인지 여부
		  singleTile : paramObj.singleTile,
		  opacity : paramObj.opacity
	  });
	return layer;
};


var fnGetDongWMSLayer = function(param){
	//레이어 전체
	if(param==null){
		paramObj={
				layers:"noaa.adm:TL_SCCO_EMD",
			    transparent : 'true', // 지도의 바탕 색상
				bgColor : '0xFFFFFF', // 지도의 바탕 색상
				//exceptions : 'BLANK', // 서버상에 에러가 발생하면 빈 이미지로 반환 -INIMAGE,XML,BLANK
				isBaseLayer : false, // 이 레이어를 지도의 기본 레이어로 할 것인지 여부
				singleTile : false,
				opacity : 0.5,
				cql_filter : "1=1",
				sld:''
		}
	}else{
		//레이어 전체 or 필터
		paramObj={
				layers:param.layers==null || param.layers==undefined?"noaa.adm:TL_SCCO_EMD":param.layers,
			    transparent : param.transparent == null || param.transparent==undefined ? 'true':param.transparent, // 지도의 바탕 색상
				bgColor : param.bgColor == null || param.bgColor==undefined  ? '0xFFFFFF':param.bgColor, // 지도의 바탕 색상
				//exceptions : param.exceptions == null || param.exceptions==undefined  ? 'BLANK': param.exceptions, // 서버상에 에러가 발생하면 빈 이미지로 반환 -INIMAGE,XML,BLANK
				isBaseLayer :  param.isBaseLayer == null || param.isBaseLayer==undefined  ? false:param.isBaseLayer, // 이 레이어를 지도의 기본 레이어로 할 것인지 여부
				singleTile :  param.singleTile == null || param.singleTile==undefined  ? false:param.singleTile,
				opacity : param.opacity == null || param.opacity==undefined  ? 0.5:param.opacity,
				cql_filter : param.cql_filter == null || param.cql_filter==undefined  ? "1=1" : param.cql_filter,
				sld: param.sld == null || param.sld==undefined  ? "" : param.sld
		}
	}
	var layer = new OpenLayers.Layer.WMS('읍면동', geoServerHost+"/geoserver/wms", {
		  layers : paramObj.layers, // WMS서버에서 제공하는 레이어
		  transparent : paramObj.transparent, // 지도의 바탕 색상
		  bgColor : paramObj.bgColor, // 지도의 바탕 색상
		  //exceptions : paramObj.exceptions, // 서버상에 에러가 발생하면 빈 이미지로 반환 -INIMAGE,XML,BLANK
		  cql_filter : paramObj.cql_filter,
		  sld_body:paramObj.sld
	  }, {
		  isBaseLayer : paramObj.isBaseLayer, // 이 레이어를 지도의 기본 레이어로 할 것인지 여부
		  singleTile : paramObj.singleTile,
		  opacity : paramObj.opacity
	  });
	return layer;
};
var fnGetLiWMSLayer = function(param){
	//레이어 전체
	if(param==null){
		paramObj={
				layers:"noaa.adm:TL_SCCO_LI",
			    transparent : 'true', // 지도의 바탕 색상
				bgColor : '0xFFFFFF', // 지도의 바탕 색상
				//exceptions : 'BLANK', // 서버상에 에러가 발생하면 빈 이미지로 반환 -INIMAGE,XML,BLANK
				isBaseLayer : false, // 이 레이어를 지도의 기본 레이어로 할 것인지 여부
				singleTile : false,
				opacity : 0.5,
				cql_filter : "1=1",
				sld:''
		}
	}else{
		//레이어 전체 or 필터
		paramObj={
				layers:param.layers==null || param.layers==undefined?"noaa.adm:TL_SCCO_LI":param.layers,
			    transparent : param.transparent == null || param.transparent==undefined ? 'true':param.transparent, // 지도의 바탕 색상
				bgColor : param.bgColor == null || param.bgColor==undefined  ? '0xFFFFFF':param.bgColor, // 지도의 바탕 색상
				//exceptions : param.exceptions == null || param.exceptions==undefined  ? 'BLANK': param.exceptions, // 서버상에 에러가 발생하면 빈 이미지로 반환 -INIMAGE,XML,BLANK
				isBaseLayer :  param.isBaseLayer == null || param.isBaseLayer==undefined  ? false:param.isBaseLayer, // 이 레이어를 지도의 기본 레이어로 할 것인지 여부
				singleTile :  param.singleTile == null || param.singleTile==undefined  ? false:param.singleTile,
				opacity : param.opacity == null || param.opacity==undefined  ? 0.5:param.opacity,
				cql_filter : param.cql_filter == null || param.cql_filter==undefined  ? "1=1" : param.cql_filter,
				sld: param.sld == null || param.sld==undefined  ? "" : param.sld,
		}
	}
	var layer = new OpenLayers.Layer.WMS('리', geoServerHost+"/geoserver/wms", {
		  layers : paramObj.layers, // WMS서버에서 제공하는 레이어
		  transparent : paramObj.transparent, // 지도의 바탕 색상
		  bgColor : paramObj.bgColor, // 지도의 바탕 색상
		  //exceptions : paramObj.exceptions, // 서버상에 에러가 발생하면 빈 이미지로 반환 -INIMAGE,XML,BLANK
		  cql_filter : paramObj.cql_filter,
		  sld_body : paramObj.sld
	  }, {
		  isBaseLayer : paramObj.isBaseLayer, // 이 레이어를 지도의 기본 레이어로 할 것인지 여부
		  singleTile : paramObj.singleTile,
		  opacity : paramObj.opacity
	  });
	return layer;
};