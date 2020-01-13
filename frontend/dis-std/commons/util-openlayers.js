export default {
    fnCreatePoint:function(position){
        return  new ol.Feature({ geometry: new ol.geom.Point(ol.proj.fromLonLat([position.lon, position.lat]))});
     }
     ,fnCreatePopup:function(paramMap,paramPopupInfo,paramText,paramPosition){
         let center=ol.proj.fromLonLat([parseFloat(paramPosition.lon), parseFloat(paramPosition.lat)]);
         let container = document.getElementById(paramPopupInfo.id);
         let content = document.getElementById(paramPopupInfo.contentId);
         let overlay = new ol.Overlay({
             element: container
             ,offset:[10,10]
           });
           paramMap.addOverlay(overlay);
           content.innerHTML =paramText;
           overlay.setPosition(center);

          container.style.display = 'block';

         //let closer = document.getElementById('popup-closer');
     }
     ,fnGetRadiusRuler:function(paramCenterPoint,paramRadius,degree){
            let xy=ol.proj.fromLonLat([paramCenterPoint.lon,paramCenterPoint.lat]);
             let x = xy[0]+ paramRadius*Math.sin(degree*Math.PI/180);
             let y = xy[1] + paramRadius*Math.cos(degree*Math.PI/180);
          
          
             return { lon:x,lat:y};
             
         
     }
    
     ,fnCreateCircle:function(position={lon:126.5,lat:36},paramRadius=100000){
      
         let center=ol.proj.fromLonLat([position.lon, position.lat]);
    
         return  new ol.Feature({ geometry: new ol.geom.Circle(ol.proj.fromLonLat([position.lon, position.lat]),paramRadius)});
     }

     ,fnCreatePoint:function(position={lon:126.5,lat:36}){
      
        let center=ol.proj.fromLonLat([position.lon, position.lat]);
   
        return  new ol.Feature({ geometry: new ol.geom.Point(ol.proj.fromLonLat([position.lon, position.lat]))});
    }

    
     ,fnCreateLine:function(paramStartPosition,paramEndPosition,paramColor,paramWidth=2){
        let line=[];
        line.push([parseFloat(paramStartPosition.lon),parseFloat(paramStartPosition.lat)]);
        line.push([parseFloat(paramEndPosition.lon),parseFloat(paramEndPosition.lat)]);
        let lineFeature = new ol.Feature(new ol.geom.LineString(line).transform('EPSG:4326', 'EPSG:3857'));
        lineFeature.userColor=paramColor;
        lineFeature.userWidth=paramWidth;
        return lineFeature;
     }
     ,fnCreateHeatMap:function(listParamPositions=[{lon:126.5,lat:36},{lon:126.6,lat:36.5}]){
        let features=[];
        listParamPositions.forEach(element => {
            features.push(this.fnCreatePoint(element));
        });
         // Source and vector layer
         let vectorSource = new ol.source.Vector({
            projection: 'EPSG:4326',
            features: features
        });
        let vectorLayer = new ol.layer.Heatmap({
            name: "layer-heatMap",
            source: vectorSource,
            blur: parseInt(14),
            radius: parseInt(8)
        });
        return vectorLayer;
     }
     ,fnCreateLineLonLat:function(paramStartPosition,paramEndPosition,paramColor,paramText){
        let line=[];
        line.push([paramStartPosition.lon,paramStartPosition.lat]);
        line.push([paramEndPosition.lon,paramEndPosition.lat]);
        // console.log(line);
        let lineFeature = new ol.Feature(new ol.geom.LineString(line));
        lineFeature.userColor=paramColor;
        lineFeature.userText=paramText;
        return lineFeature;
     }
     ,fnStyleIcon:function(feature){
        
        return   new ol.style.Style({
           
            image: new ol.style.Icon({
                anchor: [0.5, 46],
                anchorXUnits: 'fraction',
                anchorYUnits: 'pixels',
                opacity: 0.95,
                src: feature.imagePath,
                scale: 0.07
            }),
            text: new ol.style.Text({
                font: '16px helvetica,sans-serif',
                text:"",
                fill: new ol.style.Fill({color: 'black'}),
                stroke: new ol.style.Stroke({color: 'white', width:2})
            })
        });
     }
     ,fnStyleArrow:function(feature){
        let _color=feature.userColor;
        let _text=feature.userText;
        let _width=feature.userWidth;
        let geometry = feature.getGeometry();
        let styles = [
            // linestring
            new ol.style.Style({
                stroke: new ol.style.Stroke({
                    color: _color,
                    width: _width
                })
            })
        ];
    
        geometry.forEachSegment(function (start, end) {
            let dx = end[0] - start[0];
            let dy = end[1] - start[1];
            let rotation = Math.atan2(dy, dx);
    
            styles.push(new ol.style.Style({
              geometry: new ol.geom.Point(end),
              image: new ol.style.RegularShape({
                fill: new ol.style.Fill({color: _color}),
                points: 3,
                radius: _width*2,
                rotation: -rotation,
                angle: Math.PI / 2 // rotate 90°
              }),
              text: new ol.style.Text({
                 font: '16px helvetica,sans-serif',
                 text: _text,
                 fill: new ol.style.Fill({color: 'black'}),
                 stroke: new ol.style.Stroke({color: 'white', width: _width}),
                 offsetX: 0,
                 offsetY: -36,
                 rotation: 0
               })
            }));
        });
    
        return styles;
     }
}