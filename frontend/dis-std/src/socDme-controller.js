import Vue from 'vue';
import Axios from 'axios';
import UtilDate from '../commons/util-date';
import Chart from "chart.js"
import { now } from 'd3';

var vue = new Vue({
    el: '#scope-socDme',
    data: function () {
        return {
            map:null,
            maxFeatureCount:0,
            chartObj:[],
            soceityChartData: { labels:[], ctg_scb:[], ctg_sco:[], ctg_sep:[], ctg_set:[], ctg_sta:[] },

            viewModel:{

                listTopDmeCode:[],
                selectTopDmeCode:'',
                listDmeSidoCode:[],
                listDmeGunguCode:[],
                selectedSidoCode:'',
                selectedSidoNm:'',
                selectedGunguCode:'',
                selectedMonth:new Date().getMonth()+1,
                listAllDmeSociety:[],
                listAllDmeSocietyGroup:[],
                listDmeSociety:[],
                listMonth:[],
                selectedDiInfId:'',
                selectedDmeDetail:'',
                layerData:[],
                keyListView:[],
                listSearchCountByMonthly:[],
                

            },
            
        }
       

    },
    mounted: function () {
        try {
            pageLoad(this);
            console.log(this);
        } catch (e) {

        }
    },
    methods: {
        fnOnInit:function(paramDmeCode){
            //$("#loading").show();
            let vm =this;
            vm.fnGetDmeCtgList('S',1);
            vm.fnGetAreaList();
            
        }, //end fnOnInit

        fnGetDmeCtgList: function(paramCtgId, paramDepth){
            let vm = this;
            Axios.post("/advis/disaster/society/ctgList",{
                paramCtgId : paramCtgId,
                paramDepth : paramDepth
            })
            .then(function(response){
               //vm.viewModel.listTopDmeCode = response.data.resultList;
               for(let i=0; i<response.data.resultList.length; i++){
                   if(response.data.resultList[i].title != '교통사고'){
                        vm.viewModel.listTopDmeCode.push(response.data.resultList[i]);
                   }
               }
               vm.viewModel.selectTopDmeCode = vm.viewModel.listTopDmeCode[0].ctg_id;
               //$("#loading").hide();
            })
            .catch(function(ex){
                console.log(ex);
            });
        }, //end fnGetDmeCtgList

        fnGetAreaList: function(){
            let vm = this;
            vm.viewModel.listDmeSidoCode = [{code:'', sido:'전체'}];
            Axios.post("/advis/disaster/society/areaSidoCodeList",{})
            .then(function(response){
                //vm.viewModel.listDmeSidoCode = response.data.resultSidoList;
                for(let i=0; i<response.data.resultSidoList.length; i++){
                   let data = {code:'', sido:''};
                   data.code = response.data.resultSidoList[i].code;
                   data.sido = response.data.resultSidoList[i].sido;
                   vm.viewModel.listDmeSidoCode.push(data);
                }
                vm.viewModel.selectedSidoCode = vm.viewModel.listDmeSidoCode[0].code;
                vm.fnOnClickChangeGunguList();
            })
            .catch(function(ex){
                console.log(ex);
            });
        }, //end fnGetAreaList

        fnOnClickChangeGunguList: function(paramCode, paramCodeNm){
            let vm = this;
            vm.viewModel.listDmeGunguCode = [{code:'', sigungu:'전체'}];

            if(vm.viewModel.selectedSidoCode==''){
                vm.viewModel.selectedSidoNm = '';
                vm.viewModel.selectedGunguCode = vm.viewModel.listDmeGunguCode[0].code;
                vm.fnOnClickBtnSearch();
                return;
            }

            for(let i=0; i<vm.viewModel.listDmeSidoCode.length; i++){
                if(vm.viewModel.listDmeSidoCode[i].code == vm.viewModel.selectedSidoCode){
                    vm.viewModel.selectedSidoNm = vm.viewModel.listDmeSidoCode[i].sido;
                }
            }

            Axios.post("/advis/disaster/society/areaSigunguCodeList",{
                paramSidoCode : vm.viewModel.selectedSidoCode
            })
            .then(function(response){
                //vm.viewModel.listDmeGunguCode = response.data.resultSigunguList;
                for(let i=0; i<response.data.resultSigunguList.length; i++){
                    let data = {code:'', sigungu:''};
                    data.code = response.data.resultSigunguList[i].code;
                    data.sigungu = response.data.resultSigunguList[i].sigungu;
                    vm.viewModel.listDmeGunguCode.push(data);
                 }
                vm.viewModel.selectedGunguCode = vm.viewModel.listDmeGunguCode[0].code;
                vm.fnOnClickBtnSearch();

            })
            .catch(function(ex){
                console.log(ex);
            });
        }, //end fnOnClickChangeGunguList

        fnOnClickBtnSearch:function(){
            //$("#loading").show();
            let vm = this;
            vm.fnCreateMonthList();
            

        }, //end fnOnClickBtnSearch

        fnGetDmeInfoList: function(){

            let vm  = this;
            let paramAreaCode = '';
            vm.viewModel.listDmeSociety = [];
            vm.viewModel.selectedDiInfId = '';
            
            if(vm.viewModel.selectedGunguCode != ''){
                paramAreaCode = vm.viewModel.selectedGunguCode.substring(0,5);
            }else{
                paramAreaCode = vm.viewModel.selectedSidoCode.substring(0,2);
            }

            let now = new Date();
            if(vm.viewModel.selectedMonth=='undefined'){
                vm.viewModel.selectedMonth = now.getMonth()+1;
            }
            
            if(String(vm.viewModel.selectedMonth).length == 1){
                vm.viewModel.selectedMonth = '0' + vm.viewModel.selectedMonth;
            }
            
            Axios.post("/advis/disaster/society/dmeInfoList",{
                paramCtgId : vm.viewModel.selectTopDmeCode
                ,paramSigunguCode : paramAreaCode
                ,paramMonth : vm.viewModel.selectedMonth
               })
                .then(function(response){
                    if(response.data.resultAccidentSocList.length>0) {
                        vm.viewModel.listDmeSociety = response.data.resultAccidentSocList;
                        vm.viewModel.selectedDiInfId = vm.viewModel.listDmeSociety[0].di_inf_id;

                        for(let i=0; i<vm.viewModel.listDmeSociety.length; i++){
                            if(vm.viewModel.listDmeSociety[i].obz_dt != null) vm.viewModel.listDmeSociety[i].obz_dt = vm.viewModel.listDmeSociety[i].obz_dt;
                            if(vm.viewModel.listDmeSociety[i].dis_content != null) vm.viewModel.listDmeSociety[i].dis_content = vm.viewModel.listDmeSociety[i].dis_content.replace(/\r\n/gi, "<br>").replace(/\n/gi, "<br>");
                            if(vm.viewModel.listDmeSociety[i].action_content != null) vm.viewModel.listDmeSociety[i].action_content = vm.viewModel.listDmeSociety[i].action_content.replace(/\r\n/gi, "<br>").replace(/\n/gi, "<br>");
                        }
                    }
                    vm.fnOnClickDmeDetailInfo(0);
                    vm.fnCreateChartData();
                    vm.fnLayerView();
                })
                .catch(function(ex){
                    console.log(ex);
                });
        }, //end fnGetDmeInfoList

        fnOnClickDmeDetailInfo: function(paramIndex){
            let vm = this;
            vm.viewModel.selectedDmeDetail = '';
            
            if(vm.viewModel.listDmeSociety.length>0){
                vm.viewModel.selectedDiInfId = vm.viewModel.listDmeSociety[paramIndex].di_inf_id;
                vm.viewModel.selectedDmeDetail = vm.viewModel.listDmeSociety[paramIndex];
                vm.fnGetDisEventItemList(vm.viewModel.listDmeSociety[paramIndex].ctg_id, vm.viewModel.listDmeSociety[paramIndex].obz_dt, vm.viewModel.listDmeSociety[paramIndex].obz_dt);
            }
            vm.fnZoomLayer();
        
        }, //end fnOnClickDmeDetailInfo

        fnCreateMonthList:function(){
            let vm = this;
            vm.viewModel.listAllDmeSocietyGroup = [];
            vm.viewModel.listMonth = [{month:'', monthNm:'전체'}];
            vm.viewModel.listDmeSociety = [];
            vm.viewModel.selectedDiInfId = '';

            Axios.post("/advis/disaster/society/dmeInfoList",{
                paramCtgId : vm.viewModel.selectTopDmeCode
               })
                .then(function(response){
                    vm.viewModel.listAllDmeSocietyGroup = response.data.resultAccidentSocList;
                    
                    if(vm.viewModel.listAllDmeSocietyGroup.length>0) {                           
                        for(let i=0; i<vm.viewModel.listAllDmeSocietyGroup.length; i++){
                            let data = {month:'', monthNm:''};
                            data.month = vm.fnConvertMonth(vm.viewModel.listAllDmeSocietyGroup[i].obz_dt);
                            data.monthNm = data.month + '월';

                            if(data.month != null){
                                let boo = true;
                                for(let k=0; k<vm.viewModel.listMonth.length; k++){
                                    if(vm.viewModel.listMonth[k].month == data.month) boo = false;
                                }
                                if(boo) vm.viewModel.listMonth.push(data);
                            }
                        }
                        vm.viewModel.listMonth = vm.viewModel.listMonth.sort(function ascending(a,b){return(a.month<b.month)?-1:(a.month==b.month)?0:1;});
                    }else{
                        vm.viewModel.selectedMonth = '';
                    }

                    vm.fnGetDmeInfoList();

                }).catch(function(ex){
                    console.log(ex);
                });
        }, //end fnCreateMonthList

        fnConvertMonth:function(paramVal){
            if(paramVal!=''&&paramVal!=null){
                paramVal=paramVal.replace(/\,/, "").trim();
                paramVal=paramVal.substring(4,6);
            }
            
            return paramVal;
        }, //end fnConvertMonth

        fnConvertDay: function(paramVal) {
            paramVal = paramVal.replace(/\,/, "").trim();
            let arr = paramVal.split('.');
            if(arr.length>1){
                if (arr[1].length == 1) arr[1] = '0' + arr[1];
                paramVal = arr[0] + '.' + arr[1];
            }
            return paramVal;
        },

        fnOnPagingClick: function(num){
            let vm = this;
            vm.fnGetDmeInfoList(num);
        },

        fnGetDisEventItemList: function(paramCode, paramStartDate, paramEndDate){
            let vm = this;
            vm.keyListView = [];

            Axios.post('/advis/disaster/society/eventItem/list',{ paramCtgId: paramCode, paramStDate:paramStartDate.substring(0,6), paramEndDate:paramEndDate.substring(0,6)})
            .then(function (response) {
                let eventList = response.data.resultList;
                let tempList = [];
                
                //대처상황(50000)리스트
                for(var i=0; i<eventList.length; i++){
                    for(var j=0; j<eventList[i].eventItemList.length; j++){
                        if(eventList[i].eventItemList[j].ctg_id.indexOf('50000') > -1){
                            tempList.push(eventList[i].eventItemList[j]);
                        }
                    }
                }
                //일자별 시간 리스트
                if(tempList.length>0){
                    let keyList = [];
                    for(let i=0; i<tempList.length; i++){
                        if(tempList[i].contentsTitle == '일시' ){
                            let item = {day:'', printDay:'', detail:[] };
                            let arr = [];
                            let temp = '';
                            temp = tempList[i].contents.replace('/','');
                            temp = temp.replace('  ',' ');
                            arr = temp.split(' ');
                            item.day = arr[0];
                            item.printDay = vm.fnConvertDay(arr[0]);
                            
                            if(keyList.length==0){
                                keyList.push(item);
                            }else{
                                let boo = true;
                                for(let k=0; k<keyList.length; k++){ //중복체크
                                    if(keyList[k].printDay == item.printDay){
                                        boo = false;
                                    }
                                }
                                if(boo == true) keyList.push(item);
                            }
                        }
                    }
                    keyList = keyList.sort(function ascending(a,b){return(a.printDay<b.printDay)?-1:(a.printDay==b.printDay)?0:1;});

                    for(let i=0; i<keyList.length; i++){
                        for(let j=0; j<tempList.length; j++){
                            
                            if(tempList[j].contentsTitle == '일시' ){
                                let arr = [];
                                let temp = '';
                                temp = tempList[j].contents.replace('/','');
                                temp = temp.replace('  ',' ');
                                arr = temp.split(' ');
                                if(keyList[i].printDay == vm.fnConvertDay(arr[0])){ //같은날짜
                                    let cont = {hour:'', printHour:'', org:'', content:'', contentId:''};
                                    cont.hour = arr.length==1?'':arr[1];
                                    cont.printHour = keyList[i].printDay + ' ' + cont.hour.replace("∼","");

                                    for(let k=j+1; k<tempList.length; k++){
                                        if(tempList[k].contentsTitle == '일시' ){
                                            break;
                                        }else if(tempList[k].contentsTitle == '기관명'){
                                            cont.org = tempList[k].contents;
                                        }else{
                                            if(tempList[k].contentsTitle == '대처사항'){
                                                cont.content = tempList[k].contents.replace(/\r\n/gi, "<br>").replace(/\n/gi, "<br>");
                                                cont.contentId = tempList[k].evt_id;
                                            }
                                        }
                                    }
                                    keyList[i].detail.push(cont);
                                }
                            }
                        }
                    }
                    vm.keyListView = keyList;
                }
            })
            .catch(function (ex) { 
                $("#loading").hide();
                console.log(ex);
            });
        },

        fnClickEventLink: function(paramId){
            let addr = '/advis/code/codeView/'+paramId.substring(1,3)+'/'+paramId;
            window.open(addr,'_blank');
        },

        fnLayerView: function(){
            let vm = this;
            vm.fnRemoveAllLayer();
            vm.viewModel.layerData = [];
            vm.viewModel.listAllDmeSociety = [];
            let paramAreaCode = '';

            if(vm.viewModel.selectedGunguCode != ''){
                paramAreaCode = vm.viewModel.selectedGunguCode.substring(0,5);
            }else{
                paramAreaCode = vm.viewModel.selectedSidoCode.substring(0,2);
            }

            if(String(vm.viewModel.selectedMonth).length == 1){
                vm.viewModel.selectedMonth = '0' + vm.viewModel.selectedMonth;
            }
            
            Axios.post("/advis/disaster/society/allList",{
                paramCtgId : vm.viewModel.selectTopDmeCode
                ,paramSigunguCode : paramAreaCode
                ,paramMonth : vm.viewModel.selectedMonth
            })
            .then(function(response){
                vm.viewModel.listAllDmeSociety = response.data.resultAccidentSocList;

                if(vm.viewModel.listAllDmeSociety.length!=0){
                    //layer set data 
                    for(let i=0; i<vm.viewModel.listAllDmeSociety.length; i++){
                        vm.viewModel.layerData.push([vm.viewModel.listAllDmeSociety[i].cord_lon , vm.viewModel.listAllDmeSociety[i].cord_lat]);
                    }
    
                    let coordianteArray=[];
                    for(var i = 0;i < vm.viewModel.layerData.length ; i++){
                        if(vm.viewModel.layerData[i][0]!='' &&vm.viewModel.layerData[i][1]!=''){
                            let _feature = new ol.Feature({
                            geometry:new ol.geom.Point([parseFloat(vm.viewModel.layerData[i][0]),parseFloat(vm.viewModel.layerData[i][1])]).transform('EPSG:4326', 'EPSG:3857')
                            });
                            coordianteArray.push(_feature);
                        }
                    };
    
                    let clusterSource=new ol.source.Cluster({
                        distance:40,
                        source:new ol.source.Vector({features:coordianteArray})
                    });
                    var styleCache = {};
                      //layer view
                      let clusterLayer = new ol.layer.Vector({
                        name:'clusterMap',
                         source: clusterSource,
                         style: function(feature, resolution) {
                            if(feature==undefined) return null;
                            var size = feature.get('features').length;
                            var style = styleCache[size];
                           
                            if (!style) {
                              style = [new ol.style.Style({
                                image: new ol.style.Circle({
                                  radius: 10,
                                  stroke: new ol.style.Stroke({
                                    color: '#fff'
                                  }),
                                  fill: new ol.style.Fill({
                                    color: '#3399CC'
                                  })
                                }),
                                text: new ol.style.Text({
                                  text: size.toString(),
                                  fill: new ol.style.Fill({
                                    color: '#fff'
                                  })
                                })
                              })];
                              styleCache[size] = style;
                            }
                            return style;
                          }
                    });
                    vm.map.addLayer(clusterLayer);
                }

            }).catch(function (ex) { 
                $("#loading").hide();
                console.log(ex);
            });
            
        },
     
        fnRemoveAllLayer: function(){
            let vm = this;
            try{
                let removeTarget=[];
                vm.map.getLayers().forEach(function(layer){
                    
                    if(layer!=undefined&&layer.get('name')=='clusterMap'){
                        removeTarget.push(layer);
                    }
                });
                for(let i=0;i<removeTarget.length;i++){
                    vm.map.removeLayer(removeTarget[i]);
                }
            }catch(ex){
                //console.log(ex);
            }
        },

        fnRemoveMarkerLayer: function(){
            let vm = this;
            try{
                let removeTarget=[];
                vm.map.getLayers().forEach(function(layer){
                    
                    if(layer!=undefined&&layer.get('name')==='marker'){
                        removeTarget.push(layer);
                    }
                });
                for(let i=0;i<removeTarget.length;i++){
                    vm.map.removeLayer(removeTarget[i]);
                }
            }catch(ex){
                console.log(ex);
            }
        },

        fnZoomLayer: function(){
            let vm = this;
            vm.fnRemoveMarkerLayer();

            if(vm.viewModel.selectedDmeDetail.cord_lon!=null&&vm.viewModel.selectedDmeDetail.cord_lat!=null) {
                vm.map.getView().setCenter(ol.proj.fromLonLat([ 128,36 ]));
                vm.map.getView().setZoom(6);

                var iconFeature = new ol.Feature({
                    geometry:new ol.geom.Point([parseFloat(vm.viewModel.selectedDmeDetail.cord_lon),parseFloat(vm.viewModel.selectedDmeDetail.cord_lat)]).transform('EPSG:4326', 'EPSG:3857'),
                    population: 4000,
                    rainfall: 500
                });
                var iconStyle = new ol.style.Style({
                    image: new ol.style.Icon(/** @type {olx.style.IconOptions} */({
                        anchor: [0.5, 46],
                        anchorXUnits: 'fraction',
                        anchorYUnits: 'pixels',
                        src: '/contents/advis/images/icons8-marker-48.png'
                    }))
                });
                iconFeature.setStyle(iconStyle);
                var vectorSource = new ol.source.Vector({
                    features: [iconFeature]
                });
                var vectorLayer = new ol.layer.Vector({
                    name: 'marker',
                    source: vectorSource
                });
                vm.map.addLayer(vectorLayer);
                
            }
        },

        fnCreateChartData: function(){
            let vm = this;
            let paramAreaCode = '';
            vm.soceityChartData = { labels:[], ctg_scb:[], ctg_sco:[], ctg_sep:[], ctg_set:[] };

            if(vm.viewModel.selectedSidoCode != ''){
                paramAreaCode = vm.viewModel.selectedSidoCode.substring(0,2);
            }

            Axios.post("/advis/disaster/society/count/monthly",{
                paramSigunguCode: paramAreaCode
            })
            .then(function(response){
                let groupCountList = response.data.resultAccidentSocList;

                for(let i=0; i<groupCountList.length; i++){
                    
                    let month = groupCountList[i].obz_dt;
                    if(vm.soceityChartData.labels.length==0){
                        vm.soceityChartData.labels.push(month + '월');
                    }else{
                        if(vm.soceityChartData.labels.indexOf(month + '월') < 0) vm.soceityChartData.labels.push(month + '월');
                    }

                    if(groupCountList[i].ctg_id == 'SCB') vm.soceityChartData.ctg_scb.push(groupCountList[i].cnt);
                    if(groupCountList[i].ctg_id == 'SCO') vm.soceityChartData.ctg_sco.push(groupCountList[i].cnt);
                    if(groupCountList[i].ctg_id == 'SEP') vm.soceityChartData.ctg_sep.push(groupCountList[i].cnt);
                    if(groupCountList[i].ctg_id == 'SET') vm.soceityChartData.ctg_set.push(groupCountList[i].cnt);
                }

                let chartTitle="사회재난";
                let _chartOption=vm.fnCreateDefaultChartOption(chartTitle,"bar",vm.soceityChartData.labels,true);

                _chartOption.options.scales.yAxes=[];
                _chartOption.options.scales.yAxes.push({display: true,position: 'left',id: 'y-axis-1',scaleLabel: {display:true,labelString:'건'}});
                

                _chartOption.data.datasets.push( {
                    label: "화생방사고"
                    , data: vm.soceityChartData.ctg_scb
                    , backgroundColor: 'rgba(54, 162, 235,0.5)'
                    , borderColor: 'rgba(54, 162, 235,0.5)'
                    , yAxisID: 'y-axis-1',
                });

                _chartOption.data.datasets.push( {
                    label: "붕괴"
                    , data: vm.soceityChartData.ctg_sco
                    , backgroundColor: 'rgba(255, 99, 132,0.5)'
                    , borderColor: 'rgba(255, 99, 132,0.5)'
                    , yAxisID: 'y-axis-1',
                });

                _chartOption.data.datasets.push( {
                    label: "환경오염사고"
                    , data: vm.soceityChartData.ctg_sep
                    , backgroundColor: 'rgba(255, 159, 64,0.5)'
                    , borderColor: 'rgba(255, 159, 64,0.5)'
                    , yAxisID: 'y-axis-1',
                });

                _chartOption.data.datasets.push( {
                    label: "폭발"
                    , data: vm.soceityChartData.ctg_set
                    , backgroundColor: 'rgba(75, 192, 192,0.5)'
                    , borderColor: 'rgba(75, 192, 192,0.5)'
                    , yAxisID: 'y-axis-1',
                });

                vm.fnCreateChart("soc-chart",_chartOption);


            }).catch(function (ex) { 
                $("#loading").hide();
                console.log(ex);
            });
        },

        fnCreateDefaultChartOption:function (strChartTitle, strChartType, xData, isLegendView) {
            var _chartOption = {
                type: strChartType,
                data: {
                    labels: xData
                    , datasets: []
                }
                , options: {
                    layout: {
                        padding: {
                            left: 30,
                            right: 30,
                            top:30,
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
                        }]
                        ,xAxes:[]
                    },
                    tooltips : {
                        callbacks : {
                             label : function(args, data){
                                 return  Intl.NumberFormat().format(args.yLabel);
                             }
                        }
                    }
                }
            };
            return _chartOption;
        },

        fnCreateChart: function (chartID, chartInfo) {
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
                    _useChart.update();
                } else {

                    const _ctx = document.getElementById(chartID);
                    const _chart = new Chart(_ctx, {
                        type: chartInfo.type
                        , data: chartInfo.data
                        , options: chartInfo.options
                        }
                    );

                    this.chartObj.push({ id: chartID, obj: _chart });
                }
            } catch (ex) {
                console.log(ex);
            }
        },

       
    }
});