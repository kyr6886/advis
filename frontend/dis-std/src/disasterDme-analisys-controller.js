import Vue from 'vue'
import Axios from 'axios'
import * as d3 from 'd3'
import Chart from "chart.js"
import annotation from 'chartjs-plugin-annotation'

var vue = new Vue({
    el: '#scope-anl',
    data: function() {
        return {
            link: null,
            node: null,
          
          t:null,  
          raster:new ol.layer.Tile({
            source : new ol.source.OSM()
        }),
           chartObj:[]
          ,modal:{title:'',contents:[],evtDay:'',damageName:'',manual_contents:[]}
          ,viewModel:{
              listActionManual:[],
              selected:{
                  damageType:'NTY',
                  damageMoney:'',
                  damagePerson:''
              },
              selectedOrgNode:{
                name:'',
                value:0
              },
              selectedTargetNode:{
                name:'',
                value:0
              },
              typhoon:{
                listPosition:[],
                listOrgDme:[],
                listTargetDme:[],
                listTyphoons:[],
                orgDropItem:{com_dme:0,property_dme:0},
                orgTyphoonInfo:null,
                targetDropItem:{com_dme:0,property_dme:0},
                targetTyphoonInfo:null,
                listTyphoonOrgDailys:[],
                listTyphoonTargetDailys:[],
                listPrintOrgDailys:[],
                listPrintTargetDailys:[]
              },
              asos:{
                  org:null,
                  target:null
              },
              message:{
                  blank:'조회 조건(피해액,인명피해)을 선택 하세요.'
              }
              
          }
        }
    },
    mounted: function() {

        try {
            
            console.log('loading');
            pageLoad(this);
    
        } catch (e) {

        }
    },
    
    methods: {
        fnOnClickBtnSearch:function(){
           if(this.viewModel.selected.damageType=='NTY'){
               this.fnGetListTyphoonInfos();
               this.fnGetListDisasterManual();
           }
        },
        fnGetListDisasterManual:function(){
            let vm=this;
            Axios.post("/disaster/manual/list/group", {
                paramCtgId:vm.viewModel.selected.damageType
            })
            .then(function(response){
                vm.viewModel.listActionManual=response.data.resultManualList;
            
            })
            .catch(function(ex){

            });
        }
        ,fnGetListTyphoonInfos:function(){
            let vm=this;
            let _money=vm.viewModel.selected.damageMoney.split("-");
            let _person=vm.viewModel.selected.damagePerson.split("-");
            let _paramStDamge=_money[0]==''?0:parseInt(_money[0]);
            let _paramEndDamge=_money[0]==''?0:parseInt(_money[1]);;
            let _paramStDamgePerson=_person[0]==''?0:parseInt(_person[0]);
            let _paramEndDamgePerson=_person[0]==''?0:parseInt(_person[1]);
            
            Axios.post("/api/analysis/typhoon/damage/list"
            ,{paramStDamage:_paramStDamge
                ,paramEndDamage:_paramEndDamge
                ,paramStDamagePerson:_paramStDamgePerson
                ,paramEndDamagePerson:_paramEndDamgePerson
            })
            .then(function(response){
                
                vm.viewModel.typhoon.listTyphoons=response.data.listTyphoonDamageInfo;
            })
            .catch(function(ex){
                console.log(ex);
            });
        },
        fnGetListRainInfos:function(){
            Axios.post()
            .then(function(response){
             
            })
            .catch(function(ex){
                console.log(ex);
            });
        },
        fnGetListEarthQuakeInfos:function(){
            Axios.post()
            .then(function(response){
             
            })
            .catch(function(ex){
                console.log(ex);
            });
        },
        fnGetListHazardInfos:function(){
            Axios.post()
            .then(function(response){
             
            })
            .catch(function(ex){
                console.log(ex);
            });
        },
        fnGetListAsos:function(paramIsOrg,paramBegDate,paramEndDate){
            let vm=this;
            let url=$.stringFormat("/api/analysis/asos/{0}/{1}",paramBegDate,paramEndDate);
            Axios.post(url,{})
            .then(function(response){
                if(response.data.listAsos!=null){
                for(let i=0;i<response.data.listAsos.length;i++){
                    let item=response.data.listAsos[i];
                    item.rn_day=item.rn_day==null?0:(parseFloat(item.rn_day)/10).toFixed(1);
                }

                if(paramIsOrg){
                    vm.viewModel.asos.org=response.data.listAsos;

                }
                else{
                    vm.viewModel.asos.target=response.data.listAsos;
                }
                vm.fnSetAsosToTyphoon(paramIsOrg,response.data.listAsos);
            }
            })
            .catch(function(ex){
                console.log(ex);
            });
        },
        fnSetAsosToTyphoon:function(paramIsOrg,paramListAsos){
            let list=paramIsOrg?this.viewModel.typhoon.listTyphoonOrgDailys:this.viewModel.typhoon.listTyphoonTargetDailys;
            for(let i=0;i<list.length;i++){
                let item=list[i];
                for(let k=0;k<paramListAsos.length;k++){
                    if(item.damageDay==paramListAsos[k].tm){
                        item.asos=paramListAsos[k];
                        break;
                    }
                }
            }
            
        },
    
        fnOnDropDamageInfo:function(paramDivObj,paramYear,paramMonth,paramDay,paramName,paramSeq){
            $("#loading-layer").show();
            
            if(this.viewModel.selected.damageType=='NTY'){
                if(paramDivObj=="org-data"){
                    this.viewModel.typhoon.orgDropItem= this.fnGetDropItem(paramYear,paramName);
                 
                }else if(paramDivObj=="target-data"){
                
                    this.viewModel.typhoon.targetDropItem= this.fnGetDropItem(paramYear,paramName);
                 
                }
                if(paramYear!=null && paramMonth!=null && paramName!=null&& paramSeq!=null){

                    this.fnGetTyphoonPositionAndDamageInfo(paramDivObj,paramYear,paramMonth,paramName,paramSeq);
                }
            }
        },
    
        fnListStatistics:function (paramTargetId,paramDropItem){
            let vm=this;
            let begDate=paramDropItem.beg_date;
            let endDate=paramDropItem.end_date;
            
            Axios.post("/api/analysis/typhoon/damage/build/list",{paramStartDate:begDate,paramEndDate:endDate,paramDamageName:paramDropItem.typ_name})
            .then(function(response){

                if(response.data.listStatistics==null || response.data.listStatistics.length==0) return false;
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
                        if(damage>=0&& damage<=100) item.range=1;
                        else if(damage>100&& damage<=500) item.range=2;
                        else if(damage>500&& damage<=1000) item.range=3;
                        else if(damage>1000) item.range=4;

                    if(item.parentYn=="N"){
                        listTemp.push(item);
                    }
                    
                }


                for(let i=0;i<damagesGroup.length;i++){
                    let item=damagesGroup[i];
                    let damage=parseInt(item.value/100000);
                    if(damage>=0&& damage<=100) item.range=1;
                    else if(damage>100&& damage<=500) item.range=2;
                    else if(damage>500&& damage<=1000) item.range=3;
                    else if(damage>1000) item.range=4;
                    
                }
                vm.fnCreateBubbleChart(damagesGroup,defaultLink,listTemp,paramTargetId);
            })
            .catch(function(ex){
                console.log(ex);
            });
        },
        fnGetDropItem:function(paramYear,paramName){
            let rs=null;
            for(let i=0;i<this.viewModel.typhoon.listTyphoons.length;i++){
                let item=this.viewModel.typhoon.listTyphoons[i];
                if(item.beg_date.substring(0,4)==paramYear && item.typ_name==paramName){
                    rs=item;
                    break;
                }
            }
           return rs;
        },
        fnGetTyphoonPositionAndDamageInfo:function(paramDivObj,paramDataYear,paramDataMonth,paramDataName,paramDataSeq){
            let vm=this;
            
            Axios.post("/api/typhoon/cast/inform/list.do",{paramYear:paramDataYear, paramMonth:paramDataMonth, paramSeq:paramDataSeq,paramTyphoonName:paramDataName})
            .then(function(response){
                if(response.data.typhoonInfo!=null){
                    if(paramDivObj=="org-data"){
                        vm.fnSetDropOrgItem(response);
                    
                    }else if(paramDivObj=="target-data"){
                    
                        vm.fnSetDropTargetItem(response);
                    
                    }
                    vm.fnGetListAsos(paramDivObj=="org-data",response.data.typhoonInfo.beg_date,response.data.typhoonInfo.end_date);
                }else{
                    $("#loading-layer").hide();
                    alert("태풍 상세 정보를 찾을 수 없습니다.");
                }
            })
            .catch(function(ex){
                console.log(ex);
                $("#loading-layer").hide();
            });
        },
        fnGetTyphoonPositionByDay:function(response,paramDay){
            let rs=[];
            for(let i=0;i<response.data.typhoonList.length;i++){
                let item=response.data.typhoonList[i];
                if(item.tm_fc.substring(0,8)<=paramDay){
                    rs.push(item);
                }
            }
            return rs;
        },
        fnSetDropOrgItem:function(response){
            $("#loading-layer").show();
            let vm=this;
            this.viewModel.typhoon.orgTyphoonInfo=response.data.typhoonInfo;
            this.viewModel.typhoon.listTyphoonOrgDailys=[];
            this.viewModel.typhoon.listPosition=response.data.typhoonList;
            let max_ws=0;
            let sumSpeed=0;
            let cnt=0;

            for(let i=0;i<response.data.typhoonList.length;i++){

                let item=response.data.typhoonList[i];
                if(max_ws<=item.typ_ws){
                    max_ws=item.typ_ws;
                    
                }
                

                if(item.typ_lat>=30){
                    if(cnt==0){
                        this.viewModel.typhoon.orgTyphoonInfo.ps=item.typ_ps;
                    }
                    if(item.typ_sp!=null && item.typ_sp!=''&& parseInt(item.typ_sp)>0){
                        sumSpeed+=parseInt(item.typ_sp);
                        cnt++;
                    }
                }
                
                item.typ_ws_kor = vm.fnChangeTyphoonPower(item.typ_ws); // 강도
    

                if(i==0){
                    vm.viewModel.typhoon.listTyphoonOrgDailys.push({
                        damageDay:item.tm_fc.substring(0,8)
                        ,listPosition:[]
                        ,map:null
                        ,asos:null
                        ,kma:{rain_at:false,rain_alert:false,typhoon_at:false,typhoon_alert:false,wind_at:false,wind_alert:false}
                        ,control:{ship:false,park:false,road:false,flight:false,sea:false,etc:false}
                        ,actions:{}
                    });
                }else{
                    let arrLength=vm.viewModel.typhoon.listTyphoonOrgDailys.length;
                    let lastItem=vm.viewModel.typhoon.listTyphoonOrgDailys[arrLength-1];
                    let day=item.tm_fc.substring(0,8);
                    if(day!=lastItem.damageDay){
                       
                        vm.viewModel.typhoon.listTyphoonOrgDailys.push({
                              damageDay:item.tm_fc.substring(0,8)
                             ,listPosition:[]
                             ,map:null
                             ,asos:null
                             ,kma:{rain_at:false,rain_alert:false,typhoon_at:false,typhoon_alert:false,wind_at:false,wind_alert:false}
                             ,control:{ship:false,park:false,road:false,flight:false,sea:false,etc:false}
                             ,actions:{}
                        });
                    }
                    
                }
            }
           
            for(let i=0;i<vm.viewModel.typhoon.listTyphoonOrgDailys.length;i++){
                let item=vm.viewModel.typhoon.listTyphoonOrgDailys[i];
                let dailyLength=vm.viewModel.typhoon.listTyphoonOrgDailys.length;
              
                item.listPosition=vm.fnGetTyphoonPositionByDay(response,item.damageDay);
                item.typ_size=vm.fnChangeTyphoonSize(item.listPosition[item.listPosition.length-1].typ_15);
                item.typ_power=vm.fnChangeTyphoonPower(item.listPosition[item.listPosition.length-1].typ_ws);
                item.typ_sp=item.listPosition[item.listPosition.length-1].typ_sp;
                item.typ_ws=item.listPosition[item.listPosition.length-1].typ_ws;
                item.typ_ps=item.listPosition[item.listPosition.length-1].typ_ps;
            }
           
            vm.viewModel.typhoon.orgTyphoonInfo.ws=max_ws;
            vm.viewModel.typhoon.orgTyphoonInfo.sp=parseInt(sumSpeed/cnt);
            vm.viewModel.typhoon.listTyphoonOrgDailys.sort(function(a,b){return a.damageDay-b.damageDay;});
            vm.viewModel.typhoon.listPrintOrgDailys=vm.viewModel.typhoon.listTyphoonOrgDailys;
            vm.fnGetEventGroupId(true);
           
            
            setTimeout(function(){ vm.fnCreateTyphoonChart("orgTypChart");vm.fnCreatOrgMap();vm.fnListStatistics("orgChart",vm.viewModel.typhoon.orgDropItem);},0);
            $("#loading-layer").hide();
        },
        fnSetDropTargetItem:function(response){
            $("#loading-layer").show();
            let vm=this;
            this.viewModel.typhoon.targetTyphoonInfo=response.data.typhoonInfo;
            this.viewModel.typhoon.listTyphoonTargetDailys=[];
            this.viewModel.typhoon.listPosition=response.data.typhoonList;
            let max_ws=0;
            let sumSpeed=0;
            let cnt=0;

            for(let i=0;i<response.data.typhoonList.length;i++){

                let item=response.data.typhoonList[i];
                if(max_ws<=item.typ_ws){
                    max_ws=item.typ_ws;
                    
                }
                

                if(item.typ_lat>=30){
                    if(cnt==0){
                        this.viewModel.typhoon.targetTyphoonInfo.ps=item.typ_ps;
                    }
                    if(item.typ_sp!=null && item.typ_sp!=''&& parseInt(item.typ_sp)>0){
                        sumSpeed+=parseInt(item.typ_sp);
                        cnt++;
                    }
                }
                
                item.typ_ws_kor = vm.fnChangeTyphoonPower(item.typ_ws); // 강도
    

                if(i==0){
                    vm.viewModel.typhoon.listTyphoonTargetDailys.push({
                        damageDay:item.tm_fc.substring(0,8)
                        ,listPosition:[]
                        ,map:null
                        ,asos:null
                        ,kma:{rain_at:false,rain_alert:false,typhoon_at:false,typhoon_alert:false,wind_at:false,wind_alert:false}
                        ,control:{ship:false,park:false,road:false,flight:false,sea:false,etc:false}
                        ,actions:{}
                    });
                }else{
                    let arrLength=vm.viewModel.typhoon.listTyphoonTargetDailys.length;
                    let lastItem=vm.viewModel.typhoon.listTyphoonTargetDailys[arrLength-1];
                    let day=item.tm_fc.substring(0,8);
                    if(day!=lastItem.damageDay){
                       
                        vm.viewModel.typhoon.listTyphoonTargetDailys.push({
                              damageDay:item.tm_fc.substring(0,8)
                             ,listPosition:[]
                             ,map:null
                             ,asos:null
                             ,kma:{rain_at:false,rain_alert:false,typhoon_at:false,typhoon_alert:false,wind_at:false,wind_alert:false}
                             ,control:{ship:false,park:false,road:false,flight:false,sea:false,etc:false}
                             ,actions:{}
                        });
                    }
                    
                }
            }
           
            for(let i=0;i<vm.viewModel.typhoon.listTyphoonTargetDailys.length;i++){
                let item=vm.viewModel.typhoon.listTyphoonTargetDailys[i];
                let dailyLength=vm.viewModel.typhoon.listTyphoonTargetDailys.length;
              
                item.listPosition=vm.fnGetTyphoonPositionByDay(response,item.damageDay);
                item.typ_size=vm.fnChangeTyphoonSize(item.listPosition[item.listPosition.length-1].typ_15);
                item.typ_power=vm.fnChangeTyphoonPower(item.listPosition[item.listPosition.length-1].typ_ws);
                item.typ_sp=item.listPosition[item.listPosition.length-1].typ_sp;
                item.typ_ws=item.listPosition[item.listPosition.length-1].typ_ws;
                item.typ_ps=item.listPosition[item.listPosition.length-1].typ_ps;
            }
           
            vm.viewModel.typhoon.targetTyphoonInfo.ws=max_ws;
            vm.viewModel.typhoon.targetTyphoonInfo.sp=parseInt(sumSpeed/cnt);
            vm.viewModel.typhoon.listTyphoonTargetDailys.sort(function(a,b){return a.damageDay-b.damageDay;});
            vm.viewModel.typhoon.listPrintTargetDailys=vm.viewModel.typhoon.listTyphoonTargetDailys;
            vm.fnGetEventGroupId(false);
           
            
            setTimeout(function(){ vm.fnCreateTyphoonChart("targetTypChart");vm.fnCreatTargetMap();vm.fnListStatistics("targetChart",vm.viewModel.typhoon.targetDropItem);},0);
            $("#loading-layer").hide();
          
        }
        ,fnCreatOrgMap:function(){
            let vm=this;
                for(let i=0;i<vm.viewModel.typhoon.listTyphoonOrgDailys.length;i++){
                    let item=vm.viewModel.typhoon.listTyphoonOrgDailys[i];
                    let _map = new ol.Map({
                        layers : [ vm.raster],
                        target : 'org-map'+i,
                        view : new ol.View({
                            center : ol.proj.fromLonLat([ 128, 29 ]),
                            zoom : 3
                        })
                    });
                    vm.fnSetTyphoonMap(_map,item.listPosition);
                }
        }
        ,fnCreatTargetMap:function(){
            let vm=this;
                for(let i=0;i<vm.viewModel.typhoon.listTyphoonTargetDailys.length;i++){
                    let item=vm.viewModel.typhoon.listTyphoonTargetDailys[i];
                    let _map = new ol.Map({
                        layers : [ vm.raster],
                        target : 'target-map'+i,
                        view : new ol.View({
                            center : ol.proj.fromLonLat([ 128, 29 ]),
                            zoom : 3
                        })
                    });
                    vm.fnSetTyphoonMap(_map,item.listPosition);
                }
        },
        fnSetTyphoonMap: function (paramMap,paramPosition) {
        

            let curLinePoint = [];
            let colors = ["#FDB91A", "#F57E20", "#F64E05", "#D00C00"];
            let windColors = ["#ffffff", "#ffffff", "#F64E05", "#D00C00"];
            let pointFeatures=[];
            let windFeatures=[];
            let line=[];
            for (let i = 0; i < paramPosition.length; i++) {
                let item = paramPosition[i];
                if (item.typ_lat != null && item.typ_lat != "" && item.typ_lon != null && item.typ_lon != "") {
                    let point = new ol.Feature({ geometry: new ol.geom.Point(ol.proj.fromLonLat([parseFloat(item.typ_lon), parseFloat(item.typ_lat)])),typ_pw:item.typ_ws_kor });
                    let wind = new ol.Feature({ geometry: new ol.geom.Polygon.circular(new ol.Sphere(6378137), [parseFloat(item.typ_lon), parseFloat(item.typ_lat)], item.typ_15 * 1000, 40).transform('EPSG:4326', 'EPSG:3857') });
    
                    pointFeatures.push(point);
                    windFeatures.push(wind);
                    line.push([parseFloat(item.typ_lon), parseFloat(item.typ_lat)]);
                }

            }
            let lineFeature = new ol.Feature(new ol.geom.LineString(line).transform('EPSG:4326', 'EPSG:3857'));

            let pointSource = new ol.source.Vector({ features: pointFeatures });
            let lineSource = new ol.source.Vector({ features: [lineFeature] });
            let windSource = new ol.source.Vector({ features: windFeatures });

            let windStyle = new ol.style.Style({
                fill: new ol.style.Fill({
                    color: 'rgba(255,255,255, 0.2)'
                }),
                stroke: new ol.style.Stroke({
                    color: 'rgba(255,255,255, 0.2)',
                    width: 0
                })
            });
            let pointStyle = new ol.style.Style({
                image: new ol.style.Circle({
                    radius: 5,
                    stroke: new ol.style.Stroke({
                        color: 'white',
                        width: 2
                    }),
                    fill: new ol.style.Fill({
                        color: 'gray'
                    })
                }),
                //    text:new ol.style.Text({
                //     font: '12px Calibri,sans-serif',
                //     overflow: true,
                //     fill: new ol.style.Fill({
                //         color: '(\s*)000'
                //     }),
                //     stroke: new ol.style.Stroke({
                //         color: '(\s*)fff',
                //         width: 3
                //     })

                // })
            });
            let textStyle = new ol.style.Style({
                text: new ol.style.Text({
                    font: '12px Calibri,sans-serif',
                    overflow: true,
                    fill: new ol.style.Fill({
                        color: '#000'
                    }),
                    stroke: new ol.style.Stroke({
                        color: '#fff',
                        width: 3
                    })
                })
            });
            let st = [pointStyle, textStyle];
            let lineStyle = new ol.style.Style({
                stroke: new ol.style.Stroke({
                    color: 'white',
                    width: 2
                })
            });

            let pointLayer = new ol.layer.Vector({
                name: "current-point",
                source: pointSource,
               // style: pointStyle
                  style:function(feature) {
                    
                     let _pw=feature.get('typ_pw');
                     let _color="";
                     if(_pw==""||_pw=="-"||_pw=='약') _color=colors[0];
                     else if(_pw==""||_pw=="-"||_pw=='중') _color=colors[1];
                     else if(_pw==""||_pw=="-"||_pw=='강') _color=colors[2];
                     else if(_pw==""||_pw=="-"||_pw=='매우 강') _color=colors[3];
                     let _style = new ol.style.Style({
                        image: new ol.style.Circle({
                            radius: 5,
                            stroke: new ol.style.Stroke({
                                color: 'white',
                                width: 2
                            }),
                            fill: new ol.style.Fill({
                                color: _color
                            })
                        }),
                       
                    });
                     return _style;
                   }

            });
            let lineLayer = new ol.layer.Vector({
                name: "current-line",
                source: lineSource,
                style: lineStyle
            });
            let windLayer = new ol.layer.Vector({
                name: "current-wind",
                source: windSource,
                style: windStyle
            });
            //  windLayer.setStyle(this.fnGetWindStyle);
            paramMap.addLayer(lineLayer);
            paramMap.addLayer(pointLayer);
            paramMap.addLayer(windLayer);
  

        }
        ,fnChangeTyphoonPower: function (typ_ws) {
            let vm = this;
            var result = "";
            let listTyphoonStrengthName = ["약", "중", "강", "매우 강"]
            if (typ_ws != null) {
                if (typ_ws > 0 && typ_ws < 25) {
                    result = listTyphoonStrengthName[0];
                } else if (typ_ws >= 25 && typ_ws < 33) {
                    result = listTyphoonStrengthName[1];
                } else if (typ_ws >= 33 && typ_ws < 44) {
                    result = listTyphoonStrengthName[2];
                } else if (typ_ws >= 44) {
                    result = listTyphoonStrengthName[3];
                } else {
                    result = "-";
                }
            } else {
                result = "-";
            }
            return result;
        },
        fnChangeTyphoonSize: function (typ_15) {
            let vm = this;
            let result = "";
            let listTyphoonSizeName = ["소형", "중형", "대형", "초대형"];

            if (typ_15 != null && typ_15 != undefined) {
                if (typ_15 > 0 && typ_15 < 300) {
                    result = listTyphoonSizeName[0];
                } else if (typ_15 >= 300 && typ_15 <= 500) {
                    result = listTyphoonSizeName[1];
                } else if (typ_15 >= 500 && typ_15 <= 800) {
                    result = listTyphoonSizeName[2];
                } else if (typ_15 >= 800) {
                    result = listTyphoonSizeName[3];
                } else {
                    result = "-";
                }
            } else {
                result = "-";
            }
            return result;
        },
        fnGetEventGroupId:function(paramIsOrg){
            let param="";
            
            if(this.viewModel.selected.damageType=="NTY"){
                let paramObj=paramIsOrg?this.viewModel.typhoon.orgTyphoonInfo:this.viewModel.typhoon.targetTyphoonInfo;
                let paramObjDaily=paramIsOrg?this.viewModel.typhoon.listPrintOrgDailys:this.viewModel.typhoon.listPrintTargetDailys;
               
                let vm=this;
                Axios.post("/api/advis/dis/event/groupid/info",{paramCtgId:vm.viewModel.selected.damageType
                    ,paramStartDate:paramObj.beg_date
                    ,paramEndDate:paramObj.end_date
                    ,paramTitle:paramObj.typ_name}
                    )
                    .then(function(response){
                        if(response.data.eventList!=null &&response.data.eventList.length>0){
                            for(let i=0;i<paramObjDaily.length;i++){
                                let item=paramObjDaily[i];
                                item.eventItemList=[];
                                for(let k=0;k<response.data.eventList.length;k++){
                                    
                                    let evt=response.data.eventList[k];
                                    if(item.damageDay==evt.evt_date.substring(0,8)){
                                        item.kma.rain_at=vm.fnIsContainEventCodeTxt(evt.eventItemList,"NTY10101001010070","주의보",item.kma.rain_at,item.eventItemList);
                                        item.kma.rain_alert=vm.fnIsContainEventCodeTxt(evt.eventItemList,"NTY10101001010070","경보",item.kma.rain_alert,item.eventItemList);
                                        item.kma.typhoon_at=vm.fnIsContainEventCodeTxt(evt.eventItemList,"NTY10101001010040","주의보",item.kma.typhoon_at,item.eventItemList);
                                        item.kma.typhoon_alert=vm.fnIsContainEventCodeTxt(evt.eventItemList,"NTY10101001010040","경보",item.kma.typhoon_alert,item.eventItemList);
                                        item.kma.wind_at=vm.fnIsContainEventCodeTxt(evt.eventItemList,"NTY10101001010030","주의보",item.kma.wind_at,item.eventItemList);
                                        item.kma.wind_alert=vm.fnIsContainEventCodeTxt(evt.eventItemList,"NTY10101001010030","경보",item.kma.wind_alert,item.eventItemList);
                                        item.control.ship=vm.fnIsContainControlTxt(evt.eventItemList,"NTY30302003020010","여객선",item.control.ship,item.eventItemList);
                                        item.control.park=vm.fnIsContainControlTxt(evt.eventItemList,"NTY30302003020020","국립공원",item.control.park,item.eventItemList);
                                        item.control.road=vm.fnIsContainControlTxt(evt.eventItemList,"NTY30302003020030","도로",item.control.road,item.eventItemList);
                                        item.control.flight=vm.fnIsContainControlTxt(evt.eventItemList,"NTY30302003020040","항공",item.control.flight,item.eventItemList);
                                        item.control.sea=vm.fnIsContainControlTxt(evt.eventItemList,"NTY30302003020050","항로",item.control.sea,item.eventItemList);
                                        item.control.etc=vm.fnIsContainControlTxt(evt.eventItemList,"NTY30302003020060","기타",item.control.etc,item.eventItemList);
                                    }
                                }
                                // 대응현황
                                item.actions=vm.fnGetActionKeyword(response.data.resultList,item.damageDay);
                            }
                        }

                        
                    })
                    .catch(function(ex){
                        console.log(ex);
                    });

            }
        },
        fnIsContainEventCodeTxt:function(paramListCode,paramCodeTxt,paramSearchTxt,paramIsBool,paramListEvent){
            //let rs=false;
            if(paramIsBool=='undefined') paramIsBool = false;

            for(let i=0;i<paramListCode.length;i++){
                let item=paramListCode[i];
           
                if(item.ctg_id==paramCodeTxt && item.contents!=null){
                    item.contents=item.contents.replace(" ","");
                    if(item.contents.indexOf(paramSearchTxt)>-1 && item.contents.indexOf(paramSearchTxt+"해제")==-1){
                        paramIsBool = true;
                        
                        let index = -1;
                        for(let j=0; j<paramListEvent.length; j++){
                            if(paramListEvent[j].contents.replace(" ","") == item.contents.replace(" ","")){
                                index = j;
                            }
                        }
                        if(index==-1) paramListEvent.push(item);

                        break;
                    }
                }
            }
            return paramIsBool;
        },
        fnIsContainControlTxt:function(paramListCode,paramCodeTxt,paramSearchTxt,paramIsBool,paramListEvent){
            //let rs=false;
            if(paramIsBool=='undefined') paramIsBool = false;

            for(let i=0;i<paramListCode.length;i++){
                let item=paramListCode[i];
                if(item.ctg_id==paramCodeTxt && item.contents!=null){
                    
                    paramIsBool = true;
                    
                    let index = -1;
                    for(let j=0; j<paramListEvent.length; j++){
                        if(paramListEvent[j].contents.replace(" ","") == item.contents.replace(" ","")){
                            index = j;
                        }
                    }
                    if(index==-1) paramListEvent.push(item);
                }
               
            }
            return paramIsBool;
        },
        fnGetActionKeyword:function(paramListAction,paramActionDay){
            let vm=this;
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

            for(let i=0;i<paramListAction.length;i++){
                let item=paramListAction[i];
                item.month=item.month.length==1?"0"+item.month:item.month;
                item.day=item.day.length==1?"0"+item.day:item.day;
                item.hour=item.hour.length==1?"0"+item.hour:item.hour;
                if((item.year+item.month+item.day)==paramActionDay){
                    for(let k=0;k<vm.viewModel.listActionManual.length;k++){
                       let action= vm.viewModel.listActionManual[k];
                        if(item.dis_act_lv1!=null&&item.dis_act_lv1.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                            if(rs.lvl1.indexOf(action.manual_title)==-1) {
                                rs.lvl1.push({title:action.manual_title,content:item.dis_act_lv1,manual_contents:action.manual_contents});
                                rs.isAction=true;
                                
                            }
                        }
                        if(item.dis_act_lv2!=null&&item.dis_act_lv2.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                            if(rs.lvl2.indexOf(action.manual_title)==-1) {
                                rs.lvl2.push({title:action.manual_title,content:item.dis_act_lv2,manual_contents:action.manual_contents});
                                
                                rs.isAction=true;
                            }
                        }
                        if(item.dis_act_lv3!=null&&item.dis_act_lv3.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                            if(rs.lvl3.indexOf(action.manual_title)==-1) {
                                rs.lvl3.push({title:action.manual_title,content:item.dis_act_lv3,manual_contents:action.manual_contents});
                                rs.isAction=true;
                            }
                        }
                        if(item.dis_act_lv4!=null&&item.dis_act_lv4.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                            if(rs.lvl4.indexOf(action.manual_title)==-1) {
                                rs.lvl4.push({title:action.manual_title,content:item.dis_act_lv4,manual_contents:action.manual_contents});
                                rs.isAction=true;}
                        }
                        if(item.dis_act_lv5!=null&&item.dis_act_lv5.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                            if(rs.lvl5.indexOf(action.manual_title)==-1) {
                                rs.lvl5.push({title:action.manual_title,content:item.dis_act_lv5,manual_contents:action.manual_contents});
                                rs.isAction=true;}
                        }
                        if(item.dis_act_lv6!=null&&item.dis_act_lv6.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                            if(rs.lvl6.indexOf(action.manual_title)==-1) {
                                rs.lvl6.push({title:action.manual_title,content:item.dis_act_lv6,manual_contents:action.manual_contents});
                                
                                rs.isAction=true;
                            }
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
            return rs;
        }
        ,fnOnClickDetaiInfo:function(paramIsOrg){
            let param="";
            
            if(this.viewModel.selected.damageType=="NTY"){
                let paramObj=paramIsOrg?this.viewModel.typhoon.orgTyphoonInfo:this.viewModel.typhoon.targetTyphoonInfo;
                let vm=this;
                Axios.post("/api/advis/dis/event/groupid/info",{paramCtgId:vm.viewModel.selected.damageType
                    ,paramStartDate:paramObj.beg_date
                    ,paramEndDate:paramObj.end_date
                    ,paramTitle:paramObj.typ_name}
                    )
                    .then(function(response){
                        if(response.data.resultEventInfo!=null &&response.data.resultEventInfo.evt_group!=null){
                            window.open('/advis/dis/eventActionView/'+response.data.resultEventInfo.evt_group);     
                        }else{
                            alert("대응현황 자료가 없습니다.");
                            return false;
                        }
                    })
                    .catch(function(ex){
                        console.log(ex);
                    });

            }
        },
        fnCreateChart: function(chartID, chartInfo) {
            let _isUse = false;
            let _useChart = null;
            try {
                for (let i = 0; i < this.chartObj.length; i++) {
                    if (this.chartObj[i].id == chartID) {
                        _isUse = true;
                        _useChart = this.chartObj[i].obj;
                        break;
                    }
                }

                if (_isUse) {
                    _useChart.data = chartInfo.data;
                    _useChart.options = chartInfo.options;
                    _useChart.update();
                } else {
                    const _ctx = document.getElementById(chartID);
                    const _chart = new Chart(_ctx, {
                        type: chartInfo.type,
                        data: chartInfo.data,
                        options: chartInfo.options
                    });

                    this.chartObj.push({ id: chartID, obj: _chart });
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
                            right: 30
                          
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
                            label: function(args) {
                                return Intl.NumberFormat().format(args.yLabel);
                            }
                        }
                    }
                }
            };
            return _chartOption;
        },
        fnCreateTyphoonChart: function(paramId) {
            let vm = this;
            let damageChart = { labels: [], typ_ps: [], typ_ws: [] };

            for (let i = 0; i < vm.viewModel.typhoon.listPosition.length; i++) {
                let item =  vm.viewModel.typhoon.listPosition[i];
                if (item.typ_ps != '' && item.typ_ws != '') {
                    damageChart.labels.push(item.tm_fc.substring(4, 8));
                    damageChart.typ_ps.push(parseFloat(item.typ_ps));
                    damageChart.typ_ws.push(parseFloat(item.typ_ws));
                }
            }

            let chartTitle = "태풍정보";
            let _chartOption = vm.fnCreateDefaultChartOption(chartTitle, "line", damageChart.labels, true);
            _chartOption.options.scales.yAxes = [];
            _chartOption.options.scales.yAxes.push({ display: true, position: 'left', id: 'y-axis-1', scaleLabel: { display: true, labelString: 'hPa' } });
            _chartOption.options.scales.yAxes.push({ display: true, position: 'right', id: 'y-axis-2', scaleLabel: { display: true, labelString: 'm/s' } });

            _chartOption.data.datasets.push({
                label: "중심기압(hPa)",
                fill: false,
                data: damageChart.typ_ps,
                backgroundColor: 'rgba(42,110,154,1)',
                borderColor: 'rgba(42,110,154,1)',
                yAxisID: 'y-axis-1'
            });

            _chartOption.data.datasets.push({
                label: "최대풍속(m/s)",
                fill: false,
                data: damageChart.typ_ws,
                backgroundColor: 'rgba(84,212,221,1)',
                borderColor: 'rgba(84,212,221,1)',
                yAxisID: 'y-axis-2'
            });

            if (vm.viewModel.typhoon.listPosition.length > 0) {
                _chartOption.options.annotation = {
                    annotations: [],
                    drawTime: "afterDraw"
                };

                Axios.post("/api/damage/history/typhoon/dmeList.do", {
                    paramStDate: vm.viewModel.typhoon.listPosition[0].tm_fc.substring(0, 8),
                    paramEndDate: vm.viewModel.typhoon.listPosition[vm.viewModel.typhoon.listPosition.length - 1].tm_fc.substring(0, 8),
                    paramDamageName: vm.viewModel.typhoon.listPosition[0].typ_name
                }).
                then(function(response) {
                    let listDamage = response.data.listDamage;

                    listDamage.forEach(function(element) {
                        let stDate = element.beg_month + element.beg_day;
                        let end_date_class = new Date(parseInt(element.end_date.substring(0, 4)), parseInt(element.end_month) - 1, parseInt(element.end_day) + 1);
                        let endDate = end_date_class.getMonth() > 8 ? "" : "0" + (end_date_class.getMonth() + 1) + (end_date_class.getDate() > 9 ? "" : "0") + end_date_class.getDate();

                        _chartOption.options.annotation.annotations.push({
                            type: 'box',
                            mode: 'vertical',
                            xScaleID: 'x-axis-0',
                            id: 'box0',
                            borderColor: 'rgba(235,97,89,0.3)',
                            borderWidth: 4,
                            backgroundColor: 'rgba(235,97,89,0.3)',
                            xMin: damageChart.labels.indexOf(stDate) > -1 ? stDate : damageChart.labels[0],
                            xMax: damageChart.labels.indexOf(endDate) < 0 ? undefined : endDate
                        });
                    });

                    vm.fnCreateChart(paramId, _chartOption);
                }).catch(function(ex) {
                    console.log(ex);
                });
            } else {
                vm.fnCreateChart(paramId, _chartOption);
            }
        },

        fnCreateBubbleChart: function(defaultGrp,defaultLink,paramList,paramTargetId) {
            this.node=null;
            this.link=null;
            let vm=this;
            $("#"+paramTargetId).html("<g></g>");

            // {
            //     "nodes": [
            //       {"id": "Myriel", "group": 1},
            //       {"id": "Napoleon", "group": 1},
            //       {"id": "Mlle.Baptistine", "group": 1},
            //       ...
            //     ],
            //     "links": [
            //       {"source": "Napoleon", "target": "Myriel", "value": 1},
            //       {"source": "Mlle.Baptistine", "target": "Myriel", "value": 8},
            //       ...
            //     ]
            //   }
            var colores_g = ["rgba(248,187,208,1)", "rgba(178,223,219,1)", "rgba(187,222,251,1)", "rgba(178,235,242,1)", "rgba(248,187,208,1)", "rgba(197,202,233,1)"];
            let colors=["#3498DB","#F1C40F","#F39C12","#D35400","#C0392B"];
            let width = 663;
            let height = 300;
            let sum = 0;
            let bubbleNode = {
                nodes: defaultGrp,
                links:defaultLink

            };
            let index = 0;
            for (let i = 0; i < paramList.length; i++) {
                let item = paramList[i];
                if (item.damageName != '기타') {
                  
                        bubbleNode.nodes.push({  group: 2, value: item.damageTotal, id: item.damageName ,range:item.range});
                        sum += item.damageTotal;
                      //  index++;
                  
                }
            }


            index = 0;
            this.fnFindDamageBuildGroup(paramList);
            for (let i = 0; i < paramList.length; i++) {
                let item = paramList[i];
                
                if (item.damageName != '기타') {
                      
                       if(item.target=='') item.target='재산피해';

                        bubbleNode.links.push({  target: item.target, value: item.damageTotal, source: item.damageName, rate: (item.damageTotal / sum) * 100 + 125 });
                    //    index++;
                    
                }
            }
            
            for (let i = 1; i < bubbleNode.nodes.length; i++) {
                
                let item = bubbleNode.nodes[i];
                let rate = (item.value / sum) * 100;
                
                item.rate = rate + 25;

            }


            //let c10 = d3.scaleOrdinal(d3.schemeCategory20);
            let c10 = d3.scaleOrdinal(d3.schemePaired);

            let margin = { top: 20, right: 0, bottom: 0, left: 0 };


            let forceLayout = d3.forceSimulation()
            .force("link", d3.forceLink().id(function (d) {
                return d.id;
              }))
              .force("charge", d3.forceManyBody().strength(-80))
              .force("center", d3.forceCenter(width / 2, (height / 2)+20));
              
    
            
            this.link = d3.select("#"+paramTargetId+" g")
                .attr("class", "links")
                .selectAll("line")
                .data(bubbleNode.links)
                .enter().append("line")
                .attr("stroke-width", function(d) { return 1; })

            this.node = d3.select("#"+paramTargetId)
                .append("g")
                .attr("class", "nodes")
                .selectAll("g")
                .data(bubbleNode.nodes)
                .enter()
                .append("g");
              

            this.node.append("circle")
                .attr("r", function(d) {  return d.group==0?15:10; })
                .attr("fill", function(d) { 
                    return colors[d.range]; 
                });

            this.node.append("text")
                .text(function(d) {

                    return d.group<2?d.id:'';
                })
                .attr("class", "title")
                .attr('x',10)
                .attr('y', 3);
            this.node.on("click",function(d){
                if(paramTargetId.indexOf("org")>-1){
                    vm.viewModel.selectedOrgNode.name=d.id;
                    vm.viewModel.selectedOrgNode.value=d.value;
                }else{
                    vm.viewModel.selectedTargetNode.name=d.id;
                    vm.viewModel.selectedTargetNode.value=d.value;
                }
                vm.fnShowToolTip(paramTargetId+"-tooltip",d3.event);
            });
            
            forceLayout.nodes(bubbleNode.nodes)
                .on("tick", this.ticked);
            forceLayout.force("link").links(bubbleNode.links);

        },
        ticked: function() {
            this.link.attr("x1", function(d) { 
                return d.source.x; })
                .attr("y1", function(d) { return d.source.y; })
                .attr("x2", function(d) { return d.target.x; })
                .attr("y2", function(d) { return d.target.y; });

            this.node.attr("transform", function(d) {
                return "translate(" + d.x + "," + d.y + ")";
            });
        }
        ,fnOnClickViewContent:function(paramIsOrg,paramTitle,paramDay,paramName){
            this.fnFindCategoryId(paramIsOrg,paramTitle,paramDay,paramName);
        }
        ,fnFindCategoryId:function(paramIsOrg,paramTitle,paramDay,paramName){
            let vm=this;
            let paramObj=paramIsOrg?this.viewModel.typhoon.orgTyphoonInfo:this.viewModel.typhoon.targetTyphoonInfo;
            let paramObjDaily=paramIsOrg?this.viewModel.typhoon.listPrintOrgDailys:this.viewModel.typhoon.listPrintTargetDailys;

            Axios.post("/advis/code/search",{paramCtgId:vm.viewModel.selected.damageType,paramTitle:paramTitle})
            .then(function(response){
                vm.modal.manual_contents=[];
                if(response.data.list!=null && response.data.list.length>0){
                    for(let i=0;i<paramObjDaily.length;i++){
                        let item=paramObjDaily[i];
                        if(item.damageDay==paramDay){
                            let listContents=vm.fnGetContent(item.damageDay,item.eventItemList,response.data.list[0].ctg_id);
                            
                            vm.modal.title=paramTitle;
                            vm.modal.evtDay=item.damageDay;
                            vm.modal.contents=listContents;
                            vm.modal.damageName=paramName;
                            //vm.modal.contents=
                            $('#contentViewModal').modal('show');
                            break;
                        }
                    }
                }

            })
            .catch(function(ex){
                
                console.log(ex);
            })

        },
        fnGetContent:function(paramDay,paramEventItemList,paramCtgId){
            let rs=[];
            for(let i=0;i<paramEventItemList.length;i++){
                let item=paramEventItemList[i];
                if(item.ctg_id.startsWith(paramCtgId)){
                    rs.push({content:item.contents,contentTitle:item.contentsTitle,evtDay:paramDay})
                }

            }
            return rs;
        }
        ,fnOnClickManualTitle:function(paramObj,paramDay,paramName){
            this.modal.manual_contents=[];
            this.modal.contents=[];
            this.modal.evtDay=paramDay;
            this.modal.title="대응현황";
            this.modal.damageName=paramName;
            if(paramObj.content!=''){
                paramObj.content = paramObj.content.replace(/\r\n/gi, "<br>").replace(/\n/gi, "<br>");
            }
            this.modal.contents.push({contentTitle:'',content:paramObj.content});
            $('#contentViewModal').modal('show');
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
        },
        fnShowToolTip:function(tooltipId,event){

            var ttid = "#"+tooltipId;
            var xOffset = 20;
            var yOffset = 10;

            var toolTipW = $(ttid).width();
            var toolTipeH = $(ttid).height();
            var windowY = $(window).scrollTop();
            var windowX = $(window).scrollLeft();
            var curX = event.pageX;
            var curY = event.pageY;
            var ttleft = ((curX) < $(window).width() / 2) ? curX - toolTipW - xOffset*2 : curX + xOffset;
            if (ttleft < windowX + xOffset){
            ttleft = windowX + xOffset;
            } 
            var tttop = ((curY - windowY + yOffset*2 + toolTipeH) > $(window).height()) ? curY - toolTipeH - yOffset*2 : curY + yOffset;
            if (tttop < windowY + yOffset){
            tttop = curY + yOffset;
            } 
            $(ttid).css('top', tttop + 'px').css('left', ttleft + 'px');
            $(ttid).show();
        }
        ,fnHideTooltip:function(tooltipId){
            var ttid = "#"+tooltipId;
            $(ttid).hide();

        }
        ,fnOnClickListManual:function(paramActions,paramDamageDay,paramDamageName){
            
            if(paramActions.isAction){
            this.modal.damageName=paramDamageName;
            this.modal.evtDay=paramDamageDay;
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

    }
}); 