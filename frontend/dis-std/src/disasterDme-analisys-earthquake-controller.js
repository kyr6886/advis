import Vue from 'vue'
import Axios from 'axios'
import utilMap from '../commons/util-openlayers'
import Chart from "chart.js"
var vue = new Vue({
    el: '#scope-anl-earthquake',
    data: function() {
        return {

            link: null,
            node: null,
            t:null,  
            map:[],
            raster:new ol.layer.Tile({source : new ol.source.OSM()}),
            chartObj:[],
            modal:{title:'',contents:[],evtDay:'',damageName:''},

            viewModel:{
                listDamages:[]
                ,listActionManual:[]
                ,listOrgYearDme:[]

                ,selected:{
                    damageType:'NEQ',
                    damageMoney:'',
                    damagePerson:'',
                    damageEq:'',
                    damageBegDate: '',
                    damageEndDate: '',
                    damageOccurScale: 0,
                    damageAfterCount: 0,
                },
                eventInfo:{
                    locations:[],
                    firstEq:null
                },
                eventList:[],
                eventAfterList: [],
                keyListView:[],
                orgList: [],
                orgInfo:null,
                eventAction: '',

                selectedOrgNode:{
                    name:'',
                    value:0
                },
            },
        }
    }
    ,mounted: function() {
       try {
            console.log('loading');
            this.fnGetListEarthquakeDamage();
            this.map.push(this.fnCreateBaseMap("map",7,[126.5,36]));
            this.map.push(this.fnCreateBaseMap("zoom-map",12,[128,36]));
           
        } catch (e) {
            console.log(e);
        }
    }
    , methods: {

        fnMapLayerInit:function(){
             for(let i=0;i<this.map.length;i++){
                let _map=this.map[i];
                let _layers=_map.getLayers();
                let removeTarget=[];
                 _layers.forEach(function(layer){
                     if(layer.get('name')!=undefined){
                        //  console.log(layer.get('name'));
                        if(layer.get('name').indexOf("eq-")>-1){
                            removeTarget.push(layer);
                        }

                     }
                 });

                 removeTarget.forEach(function(layer){
                     _map.removeLayer(layer);
                 });


             }

        },
        //재해 경위도 정보
        fnGetEarthquakeInfo:function(){
            let vm=this;
           Axios.post("/api/advis/dis/event/location/list",{paramDisType:'NEQ',paramStartDate:vm.viewModel.selected.damageBegDate,paramEndDate:vm.viewModel.selected.damageEndDate})
           .then(function(response){
                if(response.data.listEventLocation!=null){
                   
                        vm.viewModel.eventInfo.firstEq=response.data.listEventLocation[0];
                        if(vm.viewModel.selected.damageOccurScale==0) vm.viewModel.selected.damageOccurScale = vm.viewModel.eventInfo.firstEq.evt_power;
                        vm.viewModel.eventInfo.locations=response.data.listEventLocation;
                        let dailyChart={labels:[],data:[]};
                        let pwChart={labels:[],data:[]};
                        for(let i=20;i<60;i++){
                            pwChart.labels.push(parseFloat(i/10));
                        }
                        for(let i=0;i<response.data.listEventLocation.length;i++){
                            let item=response.data.listEventLocation[i];
                            if(dailyChart.labels.indexOf(item.evt_date.substring(4,8))==-1){
                                dailyChart.labels.push(item.evt_date.substring(4,8));
                            }
                         
                        }
                        for(let i=0;i<dailyChart.labels.length;i++){
                            let count=0;
                            for(let k=0;k<response.data.listEventLocation.length;k++){
                                let item=response.data.listEventLocation[k];
                                if(dailyChart.labels[i]==item.evt_date.substring(4,8)){
                                  count++;
                                }
                            }  
                            dailyChart.data.push(count);
                        }
                        for(let i=0;i<pwChart.labels.length;i++){
                            let count=0;
                            for(let k=0;k<response.data.listEventLocation.length;k++){
                                let item=response.data.listEventLocation[k];
                                if(pwChart.labels[i]==parseFloat(item.evt_power)){
                                  count++;
                                }
                            }  
                            pwChart.data.push(count);
                        }
                        vm.fnMapLayerInit();
                        
                        vm.fnAddDamageSigunguLayer(vm.map[0]);
                        vm.fnCreateEqRadius();
                        vm.fnCreateAllEq();
                    
                        vm.fnCreateFirstEq();
                     
                        let _chartOption = vm.fnCreateDefaultChartOption("일별 발생횟수(진도 2이상)", "bar", dailyChart.labels, false);
                        let _pwChartOption=vm.fnCreateDefaultChartOption("규모별 발생횟수(진도 2이상)", "bar", pwChart.labels, false);
                        _chartOption.data.datasets.push({
                            label: "",
                            data: dailyChart.data,
                            backgroundColor: 'rgba(0, 102, 255,1)',
                            borderColor: 'rgba(0, 102, 255,1)'
                        });
                        _pwChartOption.data.datasets.push({
                            label: "",
                            data: pwChart.data,
                            backgroundColor: 'rgba(0, 102, 255,1)',
                            borderColor: 'rgba(0, 102, 255,1)'
                        });
                        vm.fnCreateChart("chart-daily",_chartOption);
                        vm.fnCreateChart("chart-pw",_pwChartOption);
                      
                }
                
           })
           .catch(function(ex){
                $("#loading-layer").hide();
                console.log(ex);
           });
        },
        //반경표시
        fnCreateEqRadius:function(){
            let features=[];
            let item=this.viewModel.eventInfo.firstEq;
            features.push(utilMap.fnCreateCircle({lon:parseFloat(item.evt_lon),lat:parseFloat(item.evt_lat)},400000));
            features.push(utilMap.fnCreateCircle({lon:parseFloat(item.evt_lon),lat:parseFloat(item.evt_lat)},200000));
            features.push(utilMap.fnCreateCircle({lon:parseFloat(item.evt_lon),lat:parseFloat(item.evt_lat)},100000));
            
            let style = new ol.style.Style({
                fill: new ol.style.Fill({
                    color: 'rgba(255, 100, 50, 0.1)'
                }),
                stroke: new ol.style.Stroke({
                    width: 2,
                    color: 'rgba(255, 100, 50, 1)'
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

              // Source and vector layer
              let vectorSource = new ol.source.Vector({
                projection: 'EPSG:4326',
                features: features
            });
            let vectorLayer = new ol.layer.Vector({
                name: "eq-radius",
                source: vectorSource,
                style: style
            });
          
             let radiusRuler=utilMap.fnGetRadiusRuler({lon:parseFloat(item.evt_lon),lat:parseFloat(item.evt_lat)},100000,300);
             let convertLonLat=ol.proj.fromLonLat([parseFloat(item.evt_lon),parseFloat(item.evt_lat)]);
             
             let lineFeatures=[];
             lineFeatures.push(utilMap.fnCreateLineLonLat(
                 {lon:convertLonLat[0],lat:convertLonLat[1]}
                ,{lon:radiusRuler.lon,lat:radiusRuler.lat}
                ,"#ff3300"
                ,"100km"
                ));
          


             let lineVectorSource = new ol.source.Vector({
                projection: 'EPSG:4326',
                features: lineFeatures
            });
            let lineVectorLayer = new ol.layer.Vector({
                name: "eq-line",
                source: lineVectorSource,
                style: utilMap.fnStyleArrow
            });
            this.map[0].addLayer(vectorLayer);
            this.map[0].addLayer(lineVectorLayer);
        },
        //본진 표시
        fnCreateFirstEq:function(){
            let features=[];
            // for(let i=0;i<response.data.listEventLocation.length;i++){
                 let item=this.viewModel.eventInfo.firstEq;
                 let featureInfo=utilMap.fnCreatePoint({lon:parseFloat(item.evt_lon),lat:parseFloat(item.evt_lat)});
                 featureInfo.pw=item.evt_power;
                 featureInfo.contents=item.contents;
                 features.push(featureInfo);

            
           //  }
             // Source and vector layer
             let vectorSource = new ol.source.Vector({
                 projection: 'EPSG:4326',
                 features: features
             });
             let vectorLayer = new ol.layer.Vector({
                 name: "eq-point",
                 source: vectorSource,
                 style:function(feature) {
                     if(feature.pw==undefined) feature.pw=0;
                     let _pw=parseFloat(feature.pw);
                     let _text=feature.contents;
                     let _color="";
                     if(_pw>=2&&_pw<3) _color="rgb(255, 204, 0,1)";
                     else if(_pw>=3&&_pw<4) _color="rgb(255, 102, 153,1)";
                     else if(_pw>=4) _color="rgba(255, 0, 0,1)";
                     else _color="rgb(255, 102, 153,0.1)";
                     let _style = new ol.style.Style({
                            image: new ol.style.Circle({
                                radius: 5,
                                stroke: new ol.style.Stroke({
                                    color: 'white',
                                    width: 1
                                }),
                                fill: new ol.style.Fill({
                                    color: _color
                                }),
                        }),
                         text: new ol.style.Text({
                            font: 'bold 18px Arial, Verdana, Helvetica, sans-serif',
                            text: _text,
                            fill: new ol.style.Fill({color: 'black'}),
                            stroke: new ol.style.Stroke({color: 'white', width: '2'}),
                            offsetX: 0,
                            offsetY: 0,
                            rotation: 0
                          })
                       
                    });
                     return _style;
                   }
             });
             this.map[0].addLayer(vectorLayer);
            
        },
        //본진 및 여진 모두 표시
        fnCreateAllEq:function(){
            let features=[];
             let tempList=this.viewModel.eventInfo.locations;
             tempList.sort(function(a,b){return a.evt_power-b.evt_power;});
             for(let i=0;i<tempList.length;i++){
                 let item=tempList[i];
                 let featureInfo=utilMap.fnCreatePoint({lon:parseFloat(item.evt_lon),lat:parseFloat(item.evt_lat)});
                 featureInfo.pw=item.evt_power;
                 featureInfo.contents=item.contents;
                 featureInfo.isRoot=(i==tempList.length-1);
                 features.push(featureInfo);
                
             }
             
             // Source and vector layer
             let vectorSource = new ol.source.Vector({
                 projection: 'EPSG:4326',
                 features: features
             });
             let vectorLayer = new ol.layer.Vector({
                name: "eq-points",
                source: vectorSource,
                style:function(feature) {
                    if(feature.pw==undefined) feature.pw=0;
                    let _pw=parseFloat(feature.pw);
                    let _size=feature.isRoot? 10:5;
                    let _color="";
                    if(_pw>=2&&_pw<3) _color="rgb(255, 204, 0,1)";
                    else if(_pw>=3&&_pw<4) _color="rgb(255, 102, 153,1)";
                    else if(_pw>=4) _color="rgba(255, 0, 0,1)";
                    else _color="rgb(255, 102, 153,0.1)";
                    let _style = new ol.style.Style({
                           image: new ol.style.Circle({
                               radius: _size,
                               stroke: new ol.style.Stroke({
                                   color: 'white',
                                   width: 1
                               }),
                               fill: new ol.style.Fill({
                                   color: _color
                               }),
                       })
                      
                   });
                    return _style;
                  }
            });
             this.map[1].getView().setCenter(ol.proj.fromLonLat([parseFloat(this.viewModel.eventInfo.firstEq.evt_lon),parseFloat(this.viewModel.eventInfo.firstEq.evt_lat)]));
             this.map[1].getView().setZoom(12);
             this.map[1].addLayer(vectorLayer);
        },
        fnCreateChart:function(paramChartId,paramChartOption){
            let _isUse = false;
            let _useChart = null;
            try {
                for (let i = 0; i < this.chartObj.length; i++) {
                    if (this.chartObj[i].id == paramChartId) {
                        _isUse = true;
                        _useChart = this.chartObj[i].obj;
                        break;
                    }
                }

                if (_isUse) {
                    _useChart.data = paramChartOption.data;
                    _useChart.options = paramChartOption.options;
                    _useChart.update();
                } else {
                    const _ctx = document.getElementById(paramChartId);
                    const _chart = new Chart(_ctx, {
                        type: paramChartOption.type,
                        data: paramChartOption.data,
                        options: paramChartOption.options
                    });

                    this.chartObj.push({ id: paramChartId, obj: _chart });
                }

             
            } catch (ex) {
                console.log(ex);
            }
        },
        fnCreateDefaultChartOption: function(strChartTitle, strChartType, xData, isLegendView) {
            var _chartOption = {
                type: strChartType,
                data: {
                    labels: xData,
                    datasets: []
                },
                options: {
                    layout: {
                        padding: {
                            left: 30,
                            right: 30,
                            top: 30,
                            bottom: 30
                        }
                    },
                    legend: {
                        display: isLegendView
                    },
                    responsive: false,
                    lineTension: 1,
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true,
                                padding: 5,
                                fontSize: 12

                            }
                        }],
                        xAxes: []
                    },
                    tooltips: {
                        callbacks: {
                            label: function(args, data) {
                                return Intl.NumberFormat().format(args.yLabel);
                            }
                        }
                    }
                }
            };
            return _chartOption;
        },
        fnGetListEarthquakeDamage: function(){
            let vm = this;
            
            Axios.post("/api/statistics/earthquake/damage/list",{
                 paramDamageCode:vm.viewModel.selected.damageType
            })
            .then(function(response){
                vm.viewModel.listDamages=response.data.listDamages;
                if(vm.viewModel.selected.damageEq == '') vm.fnOnChangeSelectedDme(vm.viewModel.listDamages[0]);

            })
            .catch(function(ex){
                console.log(ex);
            });
        }

        ,fnGetListDisasterManual:function(){
            let vm=this;
            Axios.post("/disaster/manual/list/group", {
                paramCtgId:vm.viewModel.selected.damageType
            })
            .then(function(response){
                vm.viewModel.listActionManual=response.data.resultManualList;
            })
            .catch(function(ex){
                $("#loading-layer").hide();
            });
        }

        ,fnOnChangeSelectedDme: function(paramSelectedItem){
            let vm = this;
            $("#loading-layer").show();
            vm.viewModel.selected.damageEq = paramSelectedItem;
            vm.viewModel.selected.damageBegDate = paramSelectedItem.beg_date;
            vm.viewModel.selected.damageEndDate = paramSelectedItem.end_date;
            
            vm.fnGetEarthquakeInfo();
            vm.fnGetListDisasterManual();
            vm.fnListSigunguDamages();
            vm.fnGetListEvent();

        }

        ,fnListSigunguDamages:function(){
            let vm = this;
            
            Axios.post("/api/yeardme/list",{paramDamageCode:'HZD017', paramStartDate:vm.viewModel.selected.damageBegDate, paramEndDate:vm.viewModel.selected.damageEndDate})
            .then(function(response){
                
                if(response.data.listYearDme!=null && response.data.listYearDme.length>0){
                    for(let i=0;i< response.data.listYearDme.length;i++){
                        let item=response.data.listYearDme[i];
                        item.total_damage=parseFloat(item.com_total)+parseFloat(item.pri_total)+parseFloat(item.pub_total);
                    }
                    response.data.listYearDme.sort(function(a,b){return b.total_damage-a.total_damage;});
                }

                vm.viewModel.listOrgYearDme=response.data.listYearDme;
                setTimeout(function(){ vm.fnListStatistics();},0);
                
                // console.log('시군구별 피해 : ');
                // console.log(vm.viewModel.listOrgYearDme);

            })
            .catch(function(ex){
                console.log(ex);
                $("#loading-layer").hide();
            });
        }

        ,fnListStatistics:function (){
            let vm=this;

            Axios.post("/api/analysis/earthquake/damage/build/list",{paramStartDate:vm.viewModel.selected.damageBegDate,paramEndDate:vm.viewModel.selected.damageEndDate})
            .then(function(response){
              
                let damagesGroup=[
                    { group: 0, id: '재산피해', value: response.data.listStatistics[0].sum, rate: 0,range:0 }
                    ,{ group: 1, id: '공공시설', value: response.data.listStatistics[0].damagePublic, rate: 0,range:0 }
                    ,{ group: 1, id: '사유시설', value: response.data.listStatistics[0].damagePrivate, rate: 0,range:0 }
                ];

                
                let defaultLink=[
                    {  target: '재산피해', value: 0, source: "공공시설", rate:0,range:0}
                    ,{  target: '재산피해', value: 0, source: "사유시설", rate:0,range:0}
                ];

                for(let i=0;i<response.data.listStatistics.length;i++){
                    let item=response.data.listStatistics[i];
                    if(item.parentYn=="Y"){
                        let _value=item.damageTotal;
                        if(item.damageName!="공공시설"&&item.damageName!="사유시설"){
                        damagesGroup.push({ group: 1, id:item.damageName, value:_value, rate: 0,range:0 });
                        defaultLink.push({  target: '재산피해', value: _value, source: item.damageName, rate:0,range:0});
                        }
                    }
                }

                let listTemp=[];
                for(let i=0;i<response.data.listStatistics.length;i++){
                    let item=response.data.listStatistics[i];
                  
                        let damage=parseInt(item.damageTotal/100000);
                        if(damage>=0&& damage<=10) item.range=1;
                        else if(damage>10&& damage<=20) item.range=2;
                        else if(damage>20&& damage<=100) item.range=3;
                        else if(damage>100) item.range=4;

                    if(item.parentYn=="N"){
                        listTemp.push(item);
                    }
                }

                for(let i=0;i<damagesGroup.length;i++){
                    let item=damagesGroup[i];
                    let damage=parseInt(item.value/100000);
                    if(damage>=0&& damage<=10) item.range=1;
                    else if(damage>10&& damage<=20) item.range=2;
                    else if(damage>20&& damage<=100) item.range=3;
                    else if(damage>100) item.range=4;
                }
               
            })
            .catch(function(ex){
                console.log(ex);
            });
        }

       

        ,fnFindDamageBuildGroup:function(paramListDamages){
       
            let damageGroup=[
                 {id:'재산피해',item:['농경지','농작물','건물','선박']}
                ,{id:'공공시설',item:['도로','하천','소하천','상하수도','항만','어항','학교','철도','수리','사방임도','통신','군사','소규모','기타']}
                ,{id:'사유시설',item:['축대','가축','축사','양식장','어망,어구','비닐하우스']}
            ];
            for(let i=0;i<paramListDamages.length;i++){
                let item=paramListDamages[i];
                item.target='';
                for(let k=0;k<damageGroup.length;k++){
                    for(let z=0;z<damageGroup[k].item.length;z++){
                        if(damageGroup[k].item[z]==item.damageName){
                            item.target=damageGroup[k].id;
                            break; 
                        }
                    }
                }
            }
        }

        ,fnShowToolTip:function(tooltipId,event){

            let ttid = "#"+tooltipId;
            let xOffset = 20;
            let yOffset = 10;

            let toolTipW = $(ttid).width();
            let toolTipeH = $(ttid).height();
            let windowY = $(window).scrollTop();
            let windowX = $(window).scrollLeft();
            let curX = event.pageX;
            let curY = event.pageY;
            let ttleft = ((curX) < $(window).width() / 2) ? curX - toolTipW - xOffset*2 : curX + xOffset;
            if (ttleft < windowX + xOffset){
            ttleft = windowX + xOffset;
            } 
            let tttop = ((curY - windowY + yOffset*2 + toolTipeH) > $(window).height()) ? curY - toolTipeH - yOffset*2 : curY + yOffset;
            if (tttop < windowY + yOffset){
            tttop = curY + yOffset;
            } 
            $(ttid).css('top', tttop + 'px').css('left', ttleft + 'px');
            $(ttid).show();
        }

        ,fnHideTooltip:function(tooltipId){
            let ttid = "#"+tooltipId;
            $(ttid).hide();

        }

        ,fnCreateBaseMap: function(paramTargetId,paramZoom,paramCenterPosition){
            
            let vm=this;
            // let raster=new ol.layer.Tile({
            //     source : new ol.source.OSM()
              
            // });
            let raster=new ol.layer.Tile({
                source: new ol.source.XYZ({
                    //Vworld Tile 변경
                    url: 'http://xdworld.vworld.kr:8080/2d/gray/201802/{z}/{x}/{y}.png'
                })
              
            });
            //  raster.on('postcompose', function(event) {
            //      console.log("postc");
            //      vm.fnChangeGrayScale(event.context);
            //     });

      

            let _map = new ol.Map({
                layers : [ raster],
                target : paramTargetId,
                overlays:[],
                view : new ol.View({
                    center : ol.proj.fromLonLat([ paramCenterPosition[0], paramCenterPosition[1] ]),
                    zoom :paramZoom
                })
            });
            return _map;
        }

        ,fnAddDamageSigunguLayer:function(paramMap){
            let params = $.stringFormat('dme_code:{0};stDate:{1};endDate:{2};', 'HZD017',this.viewModel.selected.damageBegDate,this.viewModel.selected.damageEndDate);
            let wmsSource = new ol.source.TileWMS({
                url: 'http://61.105.196.70:7000/geoserver/common/wms',
                params: {
                    'LAYERS': 'view_damage_sigungu',
                    'viewparams':'sigungu_code:'+params,
                    ratio:1
                },
                
                serverType: 'geoserver'
            
            });
            
            let wmsLayer = new ol.layer.Tile({
                name:'eq-sigungu'
                ,source: wmsSource
            });
            
            paramMap.addLayer(wmsLayer);
        }

        ,fnGetListEvent: function(){
            let vm = this.viewModel;
            let _this = this;

            vm.selected.damageOccurScale = 0;
            vm.selected.damageAfterCount = 0;
            
            Axios.post("/api/advis/dis/event/groupid/info",{
                paramCtgId:vm.selected.damageType
                ,paramStartDate:vm.selected.damageEq.beg_date
                ,paramEndDate:vm.selected.damageEq.end_date
                ,paramTitle:null}
            )
            .then(function(response){
                
                vm.eventList = response.data.eventList;
                if(vm.eventList != null){
                    for (let i = 0; i < vm.eventList.length; i++) { //본진
                        for (let j = 0; j < vm.eventList[i].eventItemList.length; j++) {
                            if (vm.eventList[i].eventItemList[j].ctg_id.indexOf('2001020010') > -1) {
                                vm.selected.damageOccurScale = vm.eventList[i].eventItemList[j].contents;
                                break;
                            }
                        }
                    }
                    vm.eventAfterList = []; //여진
                    for (let i = 0; i < vm.eventList.length; i++) {
                        for (let j = 0; j < vm.eventList[i].eventItemList.length; j++) {
                            let data = {evt_id:'', evt_date:'', cnt:0};
                            if(vm.eventList[i].eventItemList[j].ctg_id.indexOf('3001030040') > -1) {
                                data.evt_id = vm.eventList[i].eventItemList[j].evt_id;
                                data.evt_date = data.evt_id.replace('NEQ-','');
                                data.evt_date = data.evt_date.replace('-1','');
                                data.cnt = /\d{1,4}/.exec(vm.eventList[i].eventItemList[j].contents)[0];
                                vm.eventAfterList.push(data);
                            }
                        }
                    }
                    if(vm.eventAfterList.length>0) vm.selected.damageAfterCount = vm.eventAfterList[vm.eventAfterList.length-1].cnt;
                }

                setTimeout(function(){ 
                    _this.fnListEventTimeLine(); //대응현황 timeLine
                    _this.fnGetActionKeyword(response.data.resultList);//대응현황 table
                },0);
                $("#loading-layer").hide();
                
            }).catch(function(ex){
                $("#loading-layer").hide();
                console.log(ex);
           });
        }

        ,fnGetActionKeyword: function(paramListAction){
            let vm = this;

            let rs={
                 isAction:false
                 ,content:''
                 ,size:0
                 ,lvl1:[]
                ,lvl2:[]
                ,lvl3:[]
                ,lvl4:[]
                ,lvl5:[]
                ,lvl6:[]
            }

            if(paramListAction!=null){
                for(let i=0;i<paramListAction.length;i++){
                    let item=paramListAction[i];
                    item.month=item.month.length==1?"0"+item.month:item.month;
                    item.day=item.day.length==1?"0"+item.day:item.day;
                    item.hour=item.hour.length==1?"0"+item.hour:item.hour;

                    for(let k=0;k<vm.viewModel.listActionManual.length;k++){
                        let action= vm.viewModel.listActionManual[k];
                        
                        if(item.dis_act_lv1!=null&&item.dis_act_lv1.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                            if(rs.lvl1.indexOf(action.manual_title)==-1) {
                                rs.lvl1.push({title:action.manual_title,content:item.dis_act_lv1,manual_contents:action.manual_contents,evtDay:item.year+item.month+item.day+item.hour});
                                rs.isAction=true;
                            }
                        }
                        if(item.dis_act_lv2!=null&&item.dis_act_lv2.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/\s*/gi, ""))>-1 ){
                            if(rs.lvl2.indexOf(action.manual_title)==-1) {
                                rs.lvl2.push({title:action.manual_title,content:item.dis_act_lv2,manual_contents:action.manual_contents,evtDay:item.year+item.month+item.day+item.hour});
                                rs.isAction=true;
                            }
                        }
                        if(item.dis_act_lv3!=null&&item.dis_act_lv3.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                            if(rs.lvl3.indexOf(action.manual_title)==-1) {
                                rs.lvl3.push({title:action.manual_title,content:item.dis_act_lv3,manual_contents:action.manual_contents,evtDay:item.year+item.month+item.day+item.hour});
                                rs.isAction=true;
                            }
                        }
                        if(item.dis_act_lv4!=null&&item.dis_act_lv4.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                            if(rs.lvl4.indexOf(action.manual_title)==-1) {
                                rs.lvl4.push({title:action.manual_title,content:item.dis_act_lv4,manual_contents:action.manual_contents,evtDay:item.year+item.month+item.day+item.hour});
                                rs.isAction=true;}
                        }
                        if(item.dis_act_lv5!=null&&item.dis_act_lv5.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                            if(rs.lvl5.indexOf(action.manual_title)==-1) {
                                rs.lvl5.push({title:action.manual_title,content:item.dis_act_lv5,manual_contents:action.manual_contents,evtDay:item.year+item.month+item.day+item.hour});
                                rs.isAction=true;}
                        }
                        if(item.dis_act_lv6!=null&&item.dis_act_lv6.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                            if(rs.lvl6.indexOf(action.manual_title)==-1) {
                                rs.lvl6.push({title:action.manual_title,content:item.dis_act_lv6,manual_contents:action.manual_contents,evtDay:item.year+item.month+item.day+item.hour});
                                rs.isAction=true;
                            }
                        }
                    }
                }
                if(rs.isAction){
                    rs.size= rs.lvl1.length;
                    if(rs.size<=rs.lvl2.length)  rs.size=rs.lvl2.length;
                    if(rs.size<=rs.lvl3.length)  rs.size=rs.lvl3.length;
                    if(rs.size<=rs.lvl4.length)  rs.size=rs.lvl4.length;
                    if(rs.size<=rs.lvl5.length)  rs.size=rs.lvl5.length;
                    if(rs.size<=rs.lvl6.length)  rs.size=rs.lvl6.length;
                }
            }
            
            vm.viewModel.eventAction = rs;
            // console.log('event action : ');
            // console.log(vm.viewModel.eventAction);
        }

        ,fnOnClickManualTitle:function(paramObj){
            // console.log(paramObj);
            this.modal={title:'',contents:[],evtDay:'',damageName:''};
            this.modal.title="대응현황";
            this.modal.evtDay=paramObj.evtDay;
            if(paramObj.content!=''){
                paramObj.content = paramObj.content.replace(/\r\n/gi, "<br>").replace(/\n/gi, "<br>");
            }
            this.modal.contents.push({contentTitle:'',content:paramObj.content});
            $('#contentViewModal').modal('show');
        }

        ,fnOnClickListManual:function(paramActions){
            this.modal={title:'',contents:[],evtDay:'',damageName:''};
            if(paramActions.isAction){
                this.modal.damageName="";
                this.modal.title="대응현황";
                this.modal.contents=[];
                this.modal.manual_contents=[];
                if(paramActions.lvl1!=null && paramActions.lvl1.length>0){
                    this.modal.manual_contents.push({lvl:"lvl1",list:paramActions.lvl1});
                }
                if(paramActions.lvl2!=null && paramActions.lvl2.length>0){
                    this.modal.manual_contents.push({lvl:"lvl2",list:paramActions.lvl2});
                }
                if(paramActions.lvl3!=null && paramActions.lvl3.length>0){
                    this.modal.manual_contents.push({lvl:"lvl3",list:paramActions.lvl3});
                }
                if(paramActions.lvl4!=null && paramActions.lvl4.length>0){
                    this.modal.manual_contents.push({lvl:"lvl4",list:paramActions.lvl4});
                }
                if(paramActions.lvl5!=null && paramActions.lvl5.length>0){
                    this.modal.manual_contents.push({lvl:"lvl5",list:paramActions.lvl5});
                }
                if(paramActions.lvl6!=null && paramActions.lvl6.length>0){
                    this.modal.manual_contents.push({lvl:"lvl6",list:paramActions.lvl6});
                }
                $('#contentViewModal').modal('show');
            }
        }

        ,fnListEventTimeLine: function(){
            let vm = this.viewModel;
            let _this = this;
            let tempList = [];
            vm.keyListView = [];
            vm.orgList = [];

            if(vm.eventList != null){
                //대처상황(50000)리스트
                for (let i = 0; i < vm.eventList.length; i++) {
                    for (let j = 0; j < vm.eventList[i].eventItemList.length; j++) {
                        if (vm.eventList[i].eventItemList[j].ctg_id.indexOf('50000') > -1) {
                            tempList.push(vm.eventList[i].eventItemList[j]);
                        }
                    }
                }
                
                if (tempList.length > 0) {
                    let keyList = [];
                    for (let i = 0; i < tempList.length; i++) {
                        if (tempList[i].contentsTitle == '일시') {
                            let item = { day: '', printDay: '', detail: [] };
                            let arr = [];
                            let temp = '';
                            let regExp = /[\{\}\[\]\/?,;|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/gi;

                            if(regExp.test(tempList[i].contents)){
                                temp = tempList[i].contents.replace(regExp, '');
                            }else{
                                temp = tempList[i].contents;
                            }
                            temp = temp.replace('  ', ' ');
                            arr = temp.split(' ');
                            item.day = arr[0];
                            item.printDay = _this.fnConvertDay(arr[0]);

                            if (keyList.length == 0) {
                                keyList.push(item);
                            } else {
                                let boo = true;
                                for (let k = 0; k < keyList.length; k++) { //중복체크
                                    if (keyList[k].printDay == item.printDay) {
                                        boo = false;
                                    }
                                }
                                if (boo == true) keyList.push(item);
                            }
                        }
                    }
                    keyList = keyList.sort(function ascending(a, b) { return (a.printDay < b.printDay) ? -1 : (a.printDay == b.printDay) ? 0 : 1; });

                    for (let i = 0; i < keyList.length; i++) {
                        for (let j = 0; j < tempList.length; j++) {

                            if (tempList[j].contentsTitle == '일시') {
                                let arr = [];
                                let temp = '';
                                let regExp = /[\{\}\[\]\/?,;|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/gi;

                                if(regExp.test(tempList[j].contents)){
                                    temp = tempList[j].contents.replace(regExp, '');
                                }else{
                                    temp = tempList[j].contents;
                                }
                                temp = temp.replace('  ', ' ');
                                arr = temp.split(' ');

                                if (keyList[i].printDay == _this.fnConvertDay(arr[0])) { //같은날짜
                                    let cont = {printDay:'', org: '', content: '', contentId: '' };
                                    cont.printDay = _this.fnConvertDay(arr[0]);

                                    for (let k = j - 1; k < tempList.length; k++) {
                                        if (tempList[k].contentsTitle == '일시') {
                                            k++;
                                            if (tempList[k].contentsTitle == '대처사항') {
                                                cont.content = tempList[k].contents;
                                                cont.contentId = tempList[k].evt_id;
                                            }
                                            break;
                                        } else if (tempList[k].contentsTitle == '기관명') {
                                            cont.org = tempList[k].contents;
                                        } else {
                                            if (tempList[k].contentsTitle == '대처사항') {
                                                cont.content = tempList[k].contents;
                                                cont.contentId = tempList[k].evt_id;
                                            }
                                        }
                                    }
                                    if (cont.hour != '') {
                                        keyList[i].detail.push(cont);
                                    }
                                }
                            }
                        }
                    }

                    for (let i = 0; i < keyList.length; i++) {
                        keyList[i].detail = keyList[i].detail.sort(function ascending(a, b) { return (a.printDay < b.printDay) ? -1 : (a.printDay == b.printDay) ? 0 : 1; });
                    }
                    //날짜+시간 중복체크
                    let list = [];
                    for (let i = 0; i < keyList.length; i++) {
                        if (keyList[i].detail != null && keyList[i].detail.length > 0) {
                            let col = { date: '', detail: [] };
                            col.date = keyList[i].printDay;
                            list.push(col);
                        }
                    }

                    for (let k = 0; k < list.length; k++) {
                        for (let i = 0; i < keyList.length; i++) {
                            if (keyList[i].detail.length > 0) {
                                for (let j = 0; j < keyList[i].detail.length; j++) {

                                    if (list[k].date == keyList[i].detail[j].printDay) {
                                        //같은날짜,기관명 중복체크
                                        if (list[k].detail.length == 0) {
                                            if (keyList[i].detail[j].org != '' && keyList[i].detail[j].org != null) list[k].detail.push(keyList[i].detail[j]);
                                        } else {
                                            if (keyList[i].detail[j].org != '' && keyList[i].detail[j].org != null) {
                                                let boo = true;
                                                for (let m = 0; m < list[k].detail.length; m++) {
                                                    if (keyList[i].detail[j].org == list[k].detail[m].org) {
                                                        list[k].detail[m].content = keyList[i].detail[j].content;
                                                        boo = false;
                                                    }
                                                }
                                                if (boo) list[k].detail.push(keyList[i].detail[j]);
                                            }
                                        }
                                        //기관명 리스트
                                        let boo = true;
                                        if (vm.orgList.length == 0) {
                                            vm.orgList.push(keyList[i].detail[j].org);
                                        } else {
                                            for (let m = 0; m < vm.orgList.length; m++) {
                                                if (vm.orgList[m].indexOf(keyList[i].detail[j].org) > -1) {
                                                    boo = false;
                                                    break;
                                                }
                                            }
                                            if (boo) {
                                                vm.orgList.push(keyList[i].detail[j].org);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    for (let i = 0; i < list.length; i++) {
                        if (list[i].detail.length > 0) vm.keyListView.push(list[i]);
                    }
                }
            }
        }

        ,fnClickEventLink: function(paramObj) {
            // console.log(paramObj);
            this.modal={title:'',contents:[],evtDay:'',damageName:''};
            this.modal.title="대처사항";
            this.modal.evtDay=paramObj.printDay;
            if(paramObj.content!=''){
                paramObj.content = paramObj.content.replace(/\r\n/gi, "<br>").replace(/\n/gi, "<br>");
            }
            this.modal.contents.push({contentTitle:'',content:paramObj.content});
            $('#contentViewModal').modal('show');
            // let addr = '/advis/code/codeView/' + paramId.substring(1, 3) + '/' + paramId;
            // window.open(addr, '_blank');
        }

        ,fnConvertDay: function(paramVal) {
            paramVal = paramVal.replace(/\,/, "").trim();
            let arr = paramVal.split('.');
            if(arr.length > 1){
                if (arr[0].length == 1) arr[0] = '0' + arr[0];
                if (arr[1].length == 1) arr[1] = '0' + arr[1];
                paramVal = arr[0] + '.' + arr[1];
            }
            return paramVal;
        }

    }
});