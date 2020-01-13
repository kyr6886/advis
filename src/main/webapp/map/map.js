function createGoogleMap(paramMapID){
	
	var googleLayer = new olgm.layer.Google({ mapTypeId: google.maps.MapTypeId.SATELLITE});
	var map = new ol.Map({
	  interactions: olgm.interaction.defaults(),
	  layers: [
	    googleLayer
	  ],
	  target: paramMapID
	});
	var olGM = new olgm.OLGoogleMaps({map: map});
	olGM.activate();
	return map;
}

function createVWorldMap(paramMapID){
	try{
		var map = new ol.Map({
			  target: paramMapID,
			  layers: [
			    new ol.layer.Tile({
			        type : 'base',
			      source: new ol.source.XYZ({
			    	  url: 'http://xdworld.vworld.kr:8080/2d/Base/201802/{z}/{x}/{y}.png',
			        attributions: ['<a href="http://www.vworld.kr/po_main.do" target="_blank">공간정보 오픈플랫폼</a>',
			                        '<a href="https://www.molit.go.kr/" target="_blank">국토교통부</a>']
			      })
			    })
			  ],
			  view: new ol.View({
			    center: ol.proj.fromLonLat([128, 37]),
			    zoom: 7,
			    minZoom: 6
		
			  })
			});
    
    return map;
	}catch(ex){
		console.log(ex);
		return null;
	}


}
