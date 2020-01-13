import Vue from 'vue'
import Axios from 'axios'
import * as d3 from 'd3'
import Chart from "chart.js"
import annotation from 'chartjs-plugin-annotation'

var vue = new Vue({
    el: '#scope-report',
    data: function() {
        return {
            chartObj: [],
            link: null,
            node: null,
            currentTypMap: {},
            smTypMap: {},
            viewModel: {
                message: '',
                detailKmaInform: [],
                maxDamageTyphoon: { beg_date: '', end_date: '', typ_name: '' },
                currentTyphoonInfo: { beg_date: '', end_date: '', typ_name: '', year: '', month: '' },
                listCurrentTyphoonPosition: [],
                listForecastTyphoonPath: [],
                listDamagesByKmaReport: [],
                listSimilarTyphoons: [],
                listTyphoonPosition: []
            },
            damageList: [],
            keyListView: [],
            orgList: [],
            orgDateList: [],
            actionSituation: [],

            manual_link: null,
            manual_node: null,
        }
    },
    mounted: function() {

        try {

            pageLoad(this);

        } catch (e) {

        }
    },
    methods: {

        fnListInform: function() {
            let vm = this;
            //  setTimeout(function(){  vm.viewModel.message="특보 현황을 조회 중 입니다."; }, 2000);

            vm.viewModel.message = "특보 현황을 조회 중 입니다.";
            Axios.post('/api/kma/report/current.do', { paramTyphoonYn: "Y", paramDummyDate: '201909221600'}).
            then(function(response) {
                    vm.viewModel.detailKmaInform = response.data.detailKmaInform;

                    if (vm.viewModel.detailKmaInform !== null && vm.viewModel.detailKmaInform.length !== 0) {
                        if (vm.viewModel.detailKmaInform.reportSido != null || vm.viewModel.detailKmaInform.reportGungu != null) {
                            vm.fnDamageSummary();
                        }
                        vm.fnListTyphoon();
                    }
                })
                .catch(function(ex) {
                    $("#loading-layer").hide();
                    console.log(ex);
                });
        },
        fnDamageSummary: function() {
            let vm = this;
            //  setTimeout(function(){ vm.viewModel.message="과거 피해내역을 조회 중 입니다."; }, 2000);

            vm.viewModel.message = "과거 피해내역을 조회 중 입니다.";
            Axios.post("/api/damage/typhoon/area/kma/summary.do", {
                listParamSido: vm.viewModel.detailKmaInform.reportSido,
                listParamGungu: vm.viewModel.detailKmaInform.reportGungu,
                paramEndDate: '2017',
                paramStDate: '2009'
            }).then(function(response) {
                response.data.listDamage.sort(function(a, b) { return b.total_damage - a.total_damage; });
                vm.viewModel.listDamagesByKmaReport = response.data.listDamage;

            }).catch(function(ex) {
                $("#loading-layer").hide();
                console.log(ex);
            });
        },
        fnListTyphoon: function() {
            let vm = this;
            //setTimeout(function(){ vm.viewModel.message="현재 태풍경로를 조회 중 입니다."; }, 2000);
            vm.viewModel.message = "현재 태풍경로를 조회 중 입니다.";
            let now = new Date();
            let now_str = now.getFullYear() +
                (now.getMonth() > 8 ? now.getMonth + 1 : "0" + (now.getMonth() + 1)) +
                (now.getDate() > 9 ? now.getDate() : "0" + now.getDate());

            Axios.post('/api/typhoon/current/inform/list.do', {}).
            then(function(response) {
                if (response.data.typhoonList == null || response.data.typhoonList.length == 0) return false;
                vm.viewModel.listCurrentTyphoonPosition = response.data.typhoonList;
                vm.viewModel.currentTyphoonInfo = response.data.typhoonInfo;
                vm.viewModel.currentTyphoonInfo.year = vm.viewModel.currentTyphoonInfo.beg_date.substring(0, 4);
                vm.viewModel.currentTyphoonInfo.month = vm.viewModel.currentTyphoonInfo.beg_date.substring(4, 6);
                if (vm.viewModel.listCurrentTyphoonPosition !== null && vm.viewModel.listCurrentTyphoonPosition.length != 0) {
                    let len = vm.viewModel.listCurrentTyphoonPosition.length;
                    let lastPosition = vm.viewModel.listCurrentTyphoonPosition[len - 1];
                    vm.viewModel.listForecastTyphoonPath = [];
                    vm.viewModel.listForecastTyphoonPath.push({ typ_lon: lastPosition.ft1_lon, typ_lat: lastPosition.ft1_lat });
                    vm.viewModel.listForecastTyphoonPath.push({ typ_lon: lastPosition.ft2_lon, typ_lat: lastPosition.ft2_lat });
                    vm.viewModel.listForecastTyphoonPath.push({ typ_lon: lastPosition.ft3_lon, typ_lat: lastPosition.ft3_lat });
                    vm.viewModel.listForecastTyphoonPath.push({ typ_lon: lastPosition.ft4_lon, typ_lat: lastPosition.ft4_lat });
                    vm.viewModel.listForecastTyphoonPath.push({ typ_lon: lastPosition.ft5_lon, typ_lat: lastPosition.ft5_lat });

                    vm.fnDrawCurrentTyphoon();
                    vm.fnDrawForecastPath();
                    vm.fnListSimilarTyphoon(now);
                }
            });
        },
        fnListSimilarTyphoon: function(now) {
            let vm = this;
            let lastIndex = vm.viewModel.listCurrentTyphoonPosition.length - 1;
            //  setTimeout(function(){ vm.viewModel.message="유사경로 태풍을 조회 중 입니다."; }, 2000);
            vm.viewModel.message = "유사경로 태풍을 조회 중 입니다.";
            Axios.post('/api/typhoon/list/similar/forcast.do', {
                // paramYear: now.getFullYear(),
                // paramMonth: (now.getMonth() > 8 ? now.getMonth() + 1 : "0" + (now.getMonth() + 1)),
                paramYear: '2019',
                paramMonth: '09',
                paramSeq: vm.viewModel.listCurrentTyphoonPosition[lastIndex - 2].typ_seq,
                paramTimeSeq: vm.viewModel.listCurrentTyphoonPosition[lastIndex - 8].tm_seq
            }).
            then(function(response) {
                response.data.similarTyphoonList.sort(function(a, b) {
                    return b.countPosition - a.countPosition;
                });
                vm.viewModel.listSimilarTyphoons = response.data.similarTyphoonList;

                let maxDamageTyphoon = {};
                for (let i = 0; i < vm.viewModel.listSimilarTyphoons.length; i++) {
                    let item = vm.viewModel.listSimilarTyphoons[i];

                    let maxPs = 0;
                    let maxWs = 0;
                    let avgSp = 0;
                    let total = 0;

                    for (let k = 0; k < item.listPosition.length; k++) {
                        let sub = item.listPosition[k];
                        if (maxPs <= parseFloat(sub.typ_ps)) { maxPs = parseFloat(sub.typ_ps); }
                        if (maxWs <= parseFloat(sub.typ_ws)) { maxWs = parseFloat(sub.typ_ws); }
                        avgSp += parseFloat(sub.typ_sp);
                    }
                    item.maxPs = maxPs;
                    item.maxWs = maxWs;
                    item.maxSp = parseInt(avgSp / item.listPosition.length);

                    for (let k = 0; k < item.moneyDamage.length; k++) {
                        let sub = item.moneyDamage[k];
                        total += sub.total_damage;

                    }

                    item.sumDamage = total;

                    vm.fnDrawSimilarTyphoon(item.listPosition, item.typ_name);
                }
                vm.viewModel.listSimilarTyphoons.sort(function(a, b) { return b.sumDamage - a.sumDamage; });
                //vm.viewModel.maxDamageTyphoon = vm.viewModel.listSimilarTyphoons[0];

                for(let i=0; i<vm.viewModel.listSimilarTyphoons.length; i++){
                    if(vm.viewModel.listSimilarTyphoons[i].typ_name == '차바'){
                        vm.viewModel.maxDamageTyphoon = vm.viewModel.listSimilarTyphoons[i];
                        break;
                    }
                }

                vm.fnShowLayer(vm.viewModel.maxDamageTyphoon.typ_name);

                vm.fnGetDisEventItemList('HZD002', vm.viewModel.maxDamageTyphoon.beg_date, vm.viewModel.maxDamageTyphoon.end_date, vm.viewModel.maxDamageTyphoon.typ_name);
                vm.fnGetDisLastEventItemList('HZD002', vm.viewModel.maxDamageTyphoon.beg_year, vm.viewModel.maxDamageTyphoon.beg_date, vm.viewModel.maxDamageTyphoon.end_date, vm.viewModel.maxDamageTyphoon.typ_name);

                // vm.viewModel.message="피해현황을 분석 중 입니다.";  
                //  setTimeout(function(){  $("#loading-layer").hide();}, 3000);

                vm.fnGetTyphoonInfo();
            }).catch(function(ex) {
                $("#loading-layer").hide();

            });
        },
        fnDrawCurrentTyphoon: function() {
            let pointFeatures = [];
            let windFeatrues = [];
            let line = [];
            for (let i = 0; i < this.viewModel.listCurrentTyphoonPosition.length; i++) {
                let item = this.viewModel.listCurrentTyphoonPosition[i];
                let point = new ol.Feature({ geometry: new ol.geom.Point(ol.proj.fromLonLat([parseFloat(item.typ_lon), parseFloat(item.typ_lat)])) });
                let wind = new ol.Feature({ geometry: new ol.geom.Polygon.circular(new ol.Sphere(6378137), [parseFloat(item.typ_lon), parseFloat(item.typ_lat)], item.typ_15 * 1000, 40).transform('EPSG:4326', 'EPSG:3857') });

                pointFeatures.push(point);
                windFeatrues.push(wind);
                line.push([parseFloat(item.typ_lon), parseFloat(item.typ_lat)]);
            }
            let lineFeature = new ol.Feature(new ol.geom.LineString(line).transform('EPSG:4326', 'EPSG:3857'));

            let pointSource = new ol.source.Vector({ features: pointFeatures });
            let lineSource = new ol.source.Vector({ features: [lineFeature] });
            let windSource = new ol.source.Vector({ features: windFeatrues });

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
                //         color: '#000'
                //     }),
                //     stroke: new ol.style.Stroke({
                //         color: '#fff',
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

            var pointLayer = new ol.layer.Vector({
                name: "current-point",
                source: pointSource,
                style: pointStyle
                    // style:function(feature) {

                //     pointStyle.getText().setText(feature.get('description'));

                //     return pointStyle;
                //   }

            });
            var lineLayer = new ol.layer.Vector({
                name: "current-line",
                source: lineSource,
                style: lineStyle
            });
            var windLayer = new ol.layer.Vector({
                name: "current-wind",
                source: windSource,
                style: windStyle
            });
            //  windLayer.setStyle(this.fnGetWindStyle);
            this.currentTypMap.addLayer(lineLayer);
            this.currentTypMap.addLayer(pointLayer);
            this.currentTypMap.addLayer(windLayer);
            var overText = new ol.Overlay({
                position: ol.proj.fromLonLat(line[line.length - 1]),
                element: document.getElementById('overlay-label'),
                offset: [10, 8]
            });
            this.currentTypMap.addOverlay(overText);
        },
        fnDrawForecastPath: function() {
            let line = [];
            for (let i = 0; i < this.viewModel.listForecastTyphoonPath.length; i++) {
                let item = this.viewModel.listForecastTyphoonPath[i];
                line.push([parseFloat(item.typ_lon), parseFloat(item.typ_lat)]);
            }
            let lineFeature = new ol.Feature(new ol.geom.LineString(line).transform('EPSG:4326', 'EPSG:3857'));
            let lineSource = new ol.source.Vector({ features: [lineFeature] });
            let lineStyle = new ol.style.Style({
                stroke: new ol.style.Stroke({
                    color: 'yellow',
                    width: 5
                })
            });

            var lineLayer = new ol.layer.Vector({
                name: "forecast-line",
                source: lineSource,
                style: lineStyle
            });
            this.currentTypMap.addLayer(lineLayer);
        },
        fnDrawSimilarTyphoon: function(paramListPosition, paramName) {
            let pointFeatures = [];
            let windFeatrues = [];
            let line = [];
            for (let i = 0; i < paramListPosition.length; i++) {
                let item = paramListPosition[i];
                let point = new ol.Feature({ geometry: new ol.geom.Point(ol.proj.fromLonLat([parseFloat(item.typ_lon), parseFloat(item.typ_lat)])) });
                let wind = new ol.Feature({ geometry: new ol.geom.Polygon.circular(new ol.Sphere(6378137), [parseFloat(item.typ_lon), parseFloat(item.typ_lat)], item.typ_15 * 1000, 40).transform('EPSG:4326', 'EPSG:3857') });

                pointFeatures.push(point);
                windFeatrues.push(wind);
                line.push([parseFloat(item.typ_lon), parseFloat(item.typ_lat)]);
            }
            let lineFeature = new ol.Feature(new ol.geom.LineString(line).transform('EPSG:4326', 'EPSG:3857'));

            let pointSource = new ol.source.Vector({ features: pointFeatures });
            let lineSource = new ol.source.Vector({ features: [lineFeature] });
            let windSource = new ol.source.Vector({ features: windFeatrues });

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
                //         color: '#000'
                //     }),
                //     stroke: new ol.style.Stroke({
                //         color: '#fff',
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

            var pointLayer = new ol.layer.Vector({
                name: paramName + "-point",
                source: pointSource,
                style: pointStyle
                    // style:function(feature) {

                //     pointStyle.getText().setText(feature.get('description'));

                //     return pointStyle;
                //   }

            });
            var lineLayer = new ol.layer.Vector({
                name: paramName + "-line",
                source: lineSource,
                style: lineStyle
            });
            var windLayer = new ol.layer.Vector({
                name: paramName + "-wind",
                source: windSource,
                style: windStyle
            });

            lineLayer.setVisible(false);
            windLayer.setVisible(false);
            pointLayer.setVisible(false);


            //  windLayer.setStyle(this.fnGetWindStyle);
            this.smTypMap.addLayer(lineLayer);
            this.smTypMap.addLayer(pointLayer);
            this.smTypMap.addLayer(windLayer);



            var overText = new ol.Overlay({
                position: ol.proj.fromLonLat(line[line.length - 1]),
                element: document.getElementById('overlay-label'),
                offset: [10, 8]
            });
            this.smTypMap.addOverlay(overText);
        },
        fnShowLayer: function(paramLayerName) {
            this.smTypMap.getLayers().forEach(function(layer) {
                if (layer.get('name') != undefined) {
                    if (layer.get('name').indexOf(paramLayerName) > -1) {
                        layer.setVisible(true);
                    } else {
                        layer.setVisible(false);
                    }
                }
            });
        },

        fnGetDisEventItemList: function(paramCode, paramStartDate, paramEndDate, paramTitle) {
            let vm = this;

            vm.viewModel.message = "재난 피해상황 및 대처상황을 조회 중 입니다.";
            Axios.post('/advis/disaster/status/eventItem/list', { paramDisType: paramCode, paramStDate: paramStartDate, paramEndDate: paramEndDate, paramTitle: paramTitle })
                .then(function(response) {
                    let eventList = response.data.resultList;
                    let tempList = [];
                    vm.damageList = [];
                    vm.keyListView = [];

                    //피해상황(30100)리스트
                    let damageLastList = eventList[eventList.length - 1]; //마지막report
                    for (var j = 0; j < damageLastList.eventItemList.length; j++) {
                        if (damageLastList.eventItemList[j].ctg_id.indexOf('30100') > -1) {
                            vm.damageList.push(damageLastList.eventItemList[j]);
                        }
                    }
                    for (var i = 0; i < vm.damageList.length; i++) {
                        vm.damageList[i].contents = vm.damageList[i].contents.replace(/\r\n/gi, "<br>").replace(/\n/gi, "<br>");
                    }

                    //대처상황(50000)리스트
                    for (var i = 0; i < eventList.length; i++) {
                        for (var j = 0; j < eventList[i].eventItemList.length; j++) {
                            if (eventList[i].eventItemList[j].ctg_id.indexOf('50000') > -1) {
                                tempList.push(eventList[i].eventItemList[j]);
                            }
                        }
                    }
                    vm.actionSituation = tempList;

                    //일자별 시간 리스트
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

                                //temp = tempList[i].contents.replace('/', '');
                                temp = temp.replace('  ', ' ');
                                arr = temp.split(' ');
                                item.day = arr[0];
                                item.printDay = vm.fnConvertDay(arr[0]);

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
                            console.log(keyList[i]);

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

                                    if (keyList[i].printDay == vm.fnConvertDay(arr[0])) { //같은날짜
                                        let cont = {printDay:'', org: '', content: '', contentId: '' };
                                        cont.printDay = vm.fnConvertDay(arr[0]);

                                        for (let k = j + 1; k < tempList.length; k++) {
                                            if (tempList[k].contentsTitle == '일시') {
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

                        //[날짜+시간, [기관,내용,문서아이디]] 만들기
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
                        //$(".time-table table").width(vm.keyListView.length*100);
                    }
                    vm.fnCreateBubbleChart(response.data.listStatistics);
                    vm.fnCreateManualChart();
                });
        },

        fnClickEventLink: function(paramId) {
            let addr = '/advis/code/codeView/' + paramId.substring(1, 3) + '/' + paramId;
            window.open(addr, '_blank');
        },

        fnConvertDay: function(paramVal) {
            paramVal = paramVal.replace(/\,/, "").trim();
            let arr = paramVal.split('.');
            if(arr.length > 1){
                if (arr[0].length == 1) arr[0] = '0' + arr[0];
                if (arr[1].length == 1) arr[1] = '0' + arr[1];
                paramVal = arr[0] + '.' + arr[1];
            }
            return paramVal;
        },
        fnCreateBubbleChart: function(paramList) {

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
            let width = 700;
            let height = 450;
            let sum = 0;
            let bubbleNode = {
                nodes: [{ id: 0, group: 1, name: '주요 피해', value: 100, rate: 50 }],
                links: []

            };
            let index = 0;
            for (let i = 0; i < paramList.length; i++) {
                let item = paramList[i];
                if (item.damageName != '기타') {
                    if (index < 5) {
                        bubbleNode.nodes.push({ id: i + 1, group: 2, value: item.damageTotal, name: item.damageName });
                        sum += item.damageTotal;
                        index++;
                    }
                }
            }

            index = 0;
            for (let i = 0; i < paramList.length; i++) {
                let item = paramList[i];

                if (item.damageName != '기타') {
                    if (index < 5) {
                        bubbleNode.links.push({ source: i + 1, target: 0, value: item.damageTotal, name: item.damageName, rate: (item.damageTotal / sum) * 100 + 125 });
                        index++;
                    }
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

            var x = d3.scaleLinear().domain([0, width]).range([0, width]);

            var y = d3.scaleLinear()
                .domain([0, height - margin.top - margin.bottom])
                .range([0, height - margin.top - margin.bottom]);
            let forceLayout = d3.forceSimulation()
                .force("link", d3.forceLink().id(function(d) { return d.id }).distance(function(d) { return d.rate; }).strength(1))
                .force("charge", d3.forceManyBody().strength(-300))
                .force("center", d3.forceCenter(width / 2, height / 2))

            this.link = d3.select("#damageMap g")
                .attr("class", "links")
                .selectAll("line")
                .data(bubbleNode.links)
                .enter().append("line")
                .attr("stroke-width", function(d) { return 1; })

            this.node = d3.select("#damageMap")
                .append("g")
                .attr("class", "nodes")
                .selectAll("g")
                .data(bubbleNode.nodes)
                .enter()
                .append("g");


            this.node.append("circle")
                .attr("r", function(d) { return d.rate; })
                .attr("fill", function(d) { return c10(d.group) });

            this.node.append("text")
                .text(function(d) {
                    return d.name;
                })
                .attr("class", "title")
                .attr("text-anchor", "middle");


            forceLayout.nodes(bubbleNode.nodes)
                .on("tick", this.ticked);
            forceLayout.force("link").links(bubbleNode.links);

        },
        ticked: function() {
            this.link.attr("x1", function(d) { return d.source.x; })
                .attr("y1", function(d) { return d.source.y; })
                .attr("x2", function(d) { return d.target.x; })
                .attr("y2", function(d) { return d.target.y; });

            this.node.attr("transform", function(d) {
                return "translate(" + d.x + "," + d.y + ")";
            });
        },
        fnGetTyphoonInfo: function() {
            let vm = this;

            Axios.post("/api/typhoon/cast/inform/list.do", {
                    paramYear: vm.viewModel.maxDamageTyphoon.beg_date.substring(0, 4),
                    paramMonth: vm.viewModel.maxDamageTyphoon.beg_date.substring(4, 6),
                    paramSeq: vm.viewModel.maxDamageTyphoon.typ_seq
                })
                .then(function(response) {
                    vm.viewModel.listTyphoonPosition = response.data.typhoonList;
                    vm.fnCreateTyphoonChart();
                })
                .catch(function(ex) {
                    console.log(ex);
                });
        },
        fnCreateTyphoonChart: function() {
            let vm = this;
            let damageChart = { labels: [], typ_ps: [], typ_ws: [] };

            for (let i = 0; i < vm.viewModel.listTyphoonPosition.length; i++) {
                let item = vm.viewModel.listTyphoonPosition[i];
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

            if (vm.viewModel.listTyphoonPosition.length > 0) {
                _chartOption.options.annotation = {
                    annotations: [],
                    drawTime: "afterDraw"
                };

                Axios.post("/api/damage/history/typhoon/dmeList.do", {
                    paramStDate: vm.viewModel.listTyphoonPosition[0].tm_fc.substring(0, 8),
                    paramEndDate: vm.viewModel.listTyphoonPosition[vm.viewModel.listTyphoonPosition.length - 1].tm_fc.substring(0, 8),
                    paramDamageName: vm.viewModel.maxDamageTyphoon.typ_name
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

                    vm.fnCreateChart("typ-chart", _chartOption);
                }).catch(function(ex) {
                    console.log(ex);
                });
            } else {
                vm.fnCreateChart("typ-chart", _chartOption);
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
                            label: function(args) {
                                return Intl.NumberFormat().format(args.yLabel);
                            }
                        }
                    }
                }
            };
            return _chartOption;
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

                $("#loading-layer").hide();
            } catch (ex) {
                console.log(ex);
            }
        },

        fnCreateManualChart: function(){
            let vm = this;

            Axios.post("/disaster/manual/list/group", {
                paramCtgId: 'NTY'
            }).
            then(function(response) {
                if(response.data.resultManualList!=null && response.data.resultManualList.length>0){
                let manualList = response.data.resultManualList;//.sort(function(a,b){return b.total_cnt-a.total_cnt;});
             
            

                for(let i=0; i<manualList.length; i++){
                    for(let j=0; j<vm.actionSituation.length; j++){
                        let tempContents = vm.actionSituation[j].contents;
                        if(tempContents != null && tempContents != ''){
                            if(tempContents.indexOf(manualList[i].manual_title) > 0){
                                manualList[i].total_cnt = manualList[i].total_cnt + 1;
                            }
                        }
                    }
                }
                
                
                manualList = manualList.sort(function(a,b){return b.total_cnt-a.total_cnt;});
                let bubbleList=[];
                for(let i=0;i<manualList.length;i++){
                    if(manualList[i].total_cnt>5){
                        bubbleList.push(manualList[i]);
                    }
                }
                manualList=bubbleList;
                let parentelement = d3.select("#manualChart").node();
                let width = parentelement.getBoundingClientRect().width;
                let height = parentelement.getBoundingClientRect().height;
                let sum = 0;

                let bubbleNode = {
                    nodes: [{ id: 0, group: 1, name: '대응', value: 100, rate: 50 }],
                    links: []
                };

                for (let i = 0; i < manualList.length; i++) {
                    let item = manualList[i];
                    bubbleNode.nodes.push({ id: i + 1, group: 2, value: item.total_cnt, name: item.manual_title });
                    sum += item.total_cnt;
                }

                for (let i = 0; i < manualList.length; i++) {
                    let item = manualList[i];
                    bubbleNode.links.push({ source: i + 1, target: 0, value: item.total_cnt, name: item.manual_title, rate: (item.total_cnt / sum) * 100 + 125 });
                }

                for (let i = 1; i < bubbleNode.nodes.length; i++) {
                    let item = bubbleNode.nodes[i];
                    let rate = (item.value / sum) * 100;
                    item.rate = rate + 25;
                }

                let c10 = d3.scaleOrdinal(d3.schemePaired);

                let margin = { top: 20, right: 0, bottom: 0, left: 0 };

                let x = d3.scaleLinear().domain([0, width]).range([0, width]);

                let y = d3.scaleLinear()
                    .domain([0, height - margin.top - margin.bottom])
                    .range([0, height - margin.top - margin.bottom]);
                let forceLayout = d3.forceSimulation()
                    .force("link", d3.forceLink().id(function(d) { return d.id }).distance(function(d) { return d.rate; }).strength(1))
                    .force("charge", d3.forceManyBody().strength(-300))
                    .force("center", d3.forceCenter(width / 2, height / 2));
                    
                    vm.manual_link = d3.select("#manualChart g")
                    .attr("class", "links")
                    .selectAll("line")
                    .data(bubbleNode.links)
                    .enter().append("line")
                    .attr("stroke-width", function(d) { return 1; });

                    vm.manual_node = d3.select("#manualChart")
                    .append("g")
                    .attr("class", "nodes")
                    .selectAll("g")
                    .data(bubbleNode.nodes)
                    .enter()
                    .append("g");


                    vm.manual_node.append("circle")
                    .attr("r", function(d) { return d.rate; })
                    .attr("fill", function(d) { return c10(d.group) });

                    vm.manual_node.append("text")
                    .text(function(d) {
                        return d.name;
                    })
                    .attr("class", "title")
                    .attr("text-anchor", "middle");


                forceLayout.nodes(bubbleNode.nodes)
                    .on("tick", vm.manual_ticked);
                forceLayout.force("link").links(bubbleNode.links);
                }
            }).catch(function(ex) {
                console.log(ex);
            });
        },

        manual_ticked: function() {
            let vm = this;
            vm.manual_link.attr("x1", function(d) { return d.source.x; })
                .attr("y1", function(d) { return d.source.y; })
                .attr("x2", function(d) { return d.target.x; })
                .attr("y2", function(d) { return d.target.y; });

                vm.manual_node.attr("transform", function(d) {
                return "translate(" + d.x + "," + d.y + ")";
            });
        },

        fnGetDisLastEventItemList: function(paramCode, paramBegYear, paramStDt, paramEndDt, paramTitle) {
            Axios.post('/api/std/event/responseStatusList', { 
                paramDisType: paramCode
                , paramYear: paramBegYear
                , paramStDate: paramStDt
                , paramEndDate: paramEndDt
                , paramTitle: paramTitle 
            })
                .then(function(response) {
                    
            });

        },

    }
});