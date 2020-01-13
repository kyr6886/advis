import Vue from 'vue'
import Axios from 'axios'

var vue = new Vue({
    el: '#scope-anl-earthquake'
    ,data: function() {
        return{
            

        }
    }
    , mounted: function() {
        try {
            this.fnCreateBaseMap();
        } catch (e) {
            console.log(e);
        }
    },
    methods: {
        fnChangeGrayScale:function(context){
            
                let canvas = context.canvas;
                let width = canvas.width;
                let height = canvas.height;
                let imageData = context.getImageData(0, 0, width, height);
                let data = imageData.data;
                for(let i=0; i<data.length; i += 4){
                    let r = data[i];
                    let g = data[i + 1];
                    let b = data[i + 2];
                  // CIE luminance for the RGB
                  let v = 0.2126 * r + 0.7152 * g + 0.0722 * b;
                  // Show white color instead of black color while loading new tiles:
                  if(v === 0.0)
                   v=255.0;  
                  data[i+0] = v; // Red
                  data[i+1] = v; // Green
                  data[i+2] = v; // Blue
                  data[i+3] = 255; // Alpha
                 }
                context.putImageData(imageData,0,0);
                
             }
        ,fnCreateBaseMap:function(){
           console.log(1)
            let vm=this;
            let raster=new ol.layer.Tile({
                source : new ol.source.OSM()
              
            });
            //  raster.on('postcompose', function(event) {
            //      console.log("postc");
            //      vm.fnChangeGrayScale(event.context);
            //     });
            let _map = new ol.Map({
                layers : [ raster],
                target : 'map',
                overlays:[],
                view : new ol.View({
                    center : ol.proj.fromLonLat([ 130, 36 ]),
                    zoom :8
                })
            });

            this.fnTest(_map);

          
        }
        ,fnTest:function(paramMap){

            let style = new ol.style.Style({
                fill: new ol.style.Fill({
                    color: 'rgba(255, 100, 50, 0.1)'
                }),
                stroke: new ol.style.Stroke({
                    width: 1,
                    color: 'rgba(255, 100, 50, 0.8)'
                }),
                image: new ol.style.Circle({
                    fill: new ol.style.Fill({
                        color: 'rgba(255, 100, 50, 0.5)'
                    }),
                    stroke: new ol.style.Stroke({
                        width: 1,
                        color: 'rgba(255, 100, 50, 0.8)'
                    }),
                    radius: 7
                }),
            });
            let features=[];
            features.push(this.fnCreateCircle({lon:130,lat:36},400000));
            features.push(this.fnCreateCircle({lon:130,lat:36},200000));

            features.push(this.fnCreateCircle({lon:130,lat:36},100000));
            features.push(this.fnCreatePoint({lon:130,lat:36}));
            let cecter=ol.proj.fromLonLat([130, 36])

            let paramEdge={x:cecter[0],y:cecter[1],radius:100000}

            features.push(this.fnCreateCircleRuler(paramEdge));
            // Source and vector layer
            let vectorSource = new ol.source.Vector({
                projection: 'EPSG:4326',
                features: features
            });
            let vectorLayer = new ol.layer.Vector({
                name: "current-wind",
                source: vectorSource,
                style: style
            });
       
           // this.fnCreatePopupLabel(paramMap,{lon:130,lat:36},"aaa");
            this.fnAddSigunguLayer(paramMap);
            this.fnCreatePopup(paramMap,{id:'popup',contentId:'popup-content'},'test',cecter);
            paramMap.addLayer(vectorLayer);

        }
        ,fnCreatePoint:function(position){
       

           return  new ol.Feature({ geometry: new ol.geom.Point(ol.proj.fromLonLat([position.lon, position.lat]))});
        }
        ,fnCreatePopup:function(paramMap,paramPopupInfo,paramText,paramPosition){
            let container = document.getElementById(paramPopupInfo.id);
            let content = document.getElementById(paramPopupInfo.contentId);
            let overlay = new ol.Overlay({
                element: container
              });
              paramMap.addOverlay(overlay);
              content.innerHTML =paramText;
              overlay.setPosition(paramPosition);

             container.style.display = 'block';

            //let closer = document.getElementById('popup-closer');
        }
        ,fnCreateCircleRuler:function(paramRadiusPoint){
            if(paramRadiusPoint!=undefined ){
                let x = paramRadiusPoint.x+ paramRadiusPoint.radius*Math.sin(300*Math.PI/180);
                let y = paramRadiusPoint.y + paramRadiusPoint.radius*Math.cos(300*Math.PI/180);
             
             
                return new ol.Feature({ geometry: new ol.geom.Point([x,y])});
                
            }
        }
       
        ,fnCreateCircle:function(position,paramRadius){
            let center=ol.proj.fromLonLat([position.lon, position.lat]);
       
            return  new ol.Feature({ geometry: new ol.geom.Circle(ol.proj.fromLonLat([position.lon, position.lat]),paramRadius)});
        }
        ,fnCreatePopupLabel:function(paramMap,position,paramText){
            let pixel = paramMap.getPixelFromCoordinate(ol.proj.fromLonLat([position.lon, position.lat]));
            let view = paramMap.getView();
            let width = ol.extent.getWidth(view.getProjection().getExtent()) / view.getResolution();
            let pixelX = ((pixel[0] % width) + width) % width;
            let pixelY = pixel[1];
            console.log(pixelX);
            console.log(pixelY);

        }
        ,fnAddSigunguLayer:function(paramMap){
            let paramPosition="45720";
            let wmsSource = new ol.source.TileWMS({
                url: 'http://61.105.196.70:8080/geoserver/common/wms',
                params: {
                    'LAYERS': 'view_kma_gungu_lv1',
                    'viewparams':'sigungu_code:'+paramPosition,
                    ratio:1
                },
                
                serverType: 'geoserver'
            
            });
            
            let wmsLayer = new ol.layer.Tile({
                source: wmsSource
            });
            
            paramMap.addLayer(wmsLayer);
        }
    }

});