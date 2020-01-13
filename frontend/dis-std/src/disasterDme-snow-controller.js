import Vue from 'vue'
import Axios from 'axios'
import Chart from "chart.js"
import 'chartjs-plugin-datalabels'

var vue = new Vue({
    el: '#scope-disasterDme',
    data: function() {
        return {
            chartObj: [],
            viewModel: {
                selectTopDmeCode: '',
                selectedGunguCode: '',
                selectedSidoName: '',
                selectedGunguName: '',
                selectedMaxComDme: '',
                selectedParamStDate: '',
                selectedParamEndDate: '',
                selectTyphoonSeq: '',
                selectDmeGungu: '',
                selectDmeDate: '',
                viewType: 'person',
                selectedDamage: {},
                selectedDamageGungu: {},
                stDate: new Date().getFullYear() - 12,
                endDate: new Date().getFullYear() - 1,
                listWholeDme: [],
                listTopComDme: [],
                listTopTotDme: [],
                listYears: [],
                listDamage: [],
                listYearsSelect: [],
                listPubAndPri: [],
                listComDmeChart: [],
                listTotalDmeChart: [],
                listSelectItem: [],
                listTopDmeCode: [{ code: "HZD008", code_name_dme: "대설" }],
                listTyphoonName: [],
                listDmeDateCode: [],
                listDmeSidoCode: [],
                listDmeGunguCode: [],
                listPubFacilityName: ["도로", "하천", "학교", "소규모시설물"],
                listPriFacilityName: ["건물", "선박", "농경지", "축대담장", "비닐하우스"],
            },
            isGunguTopDme: false,
            isGunguTopDmeView: false,
            isPubList: true,
            isPriList: false,
            isPicList: false,
            popupShow: false,

            keyListView: [],
            eventImgList: [],
            orgList: [],
            orgDateList: [],
            selectedSigunguCode: ''
        }
    },
    mounted: function() {
        try {
            let endYear = new Date().getFullYear();
            for (let i = 1985; i < endYear; i++) {
                this.viewModel.listYearsSelect.push(i);
            }
            pageLoad(this);

        } catch (e) {

        }
    },
    methods: {
        fnOnInit: function(paramDmeCode) {
            let vm = this;
            vm.fnGetCodeList(paramDmeCode);
        },
        fnGetCodeList: function(paramDmeCode) {
            let vm = this;
            vm.viewModel.selectTopDmeCode = paramDmeCode;
            vm.viewModel.selectDmeDate = "";
            vm.viewModel.selectDmeSido = "";
            vm.viewModel.selectDmeGungu = "";

            Axios.post('/api/damage/history/code/snowList.do', { paramYear: vm.viewModel.stDate })
                .then(function(response) {
                    if (response.data.listDmeDateCode != undefined && response.data.listDmeDateCode != null && response.data.listDmeDateCode.length > 0) {
                        vm.viewModel.listDmeDateCode = response.data.listDmeDateCode;
                        vm.viewModel.selectDmeDate = vm.viewModel.listDmeDateCode[0].beg_date + '_' + vm.viewModel.listDmeDateCode[0].end_date;
                        vm.viewModel.listDmeSidoCode = response.data.listDmeGunguCode;
                        vm.viewModel.selectDmeSido = vm.viewModel.listDmeSidoCode[0].sido_code;

                        vm.fnGetWholeList();
                    }
                })
        },
        fnChangeTyphoonNameList: function() {
            let vm = this;

            vm.viewModel.selectDmeDate = "";
            vm.viewModel.selectDmeSido = "";
            vm.viewModel.selectDmeGungu = "";

            Axios.post('/api/damage/history/date/code.do', { paramYear: vm.viewModel.stDate, paramDmeCode: vm.viewModel.selectTopDmeCode })
                .then(function(response) {
                    vm.viewModel.listDmeDateCode = response.data.listDmeDateCode;
                })
        },
        fnChangeSidoList: function() {
            let vm = this;
            vm.viewModel.selectDmeSido = "";
            vm.viewModel.selectDmeGungu = "";
            let param = {};

            let start_date = vm.fnGetSplitOptionValueModel(vm.viewModel.selectDmeDate, 0);
            param = { paramYear: vm.viewModel.stDate, paramStDate: start_date, paramDmeCode: vm.viewModel.selectTopDmeCode };

            Axios.post('/api/damage/history/sido/code.do', param)
                .then(function(response) {
                    vm.viewModel.listDmeSidoCode = response.data.listDmeSidoCode;
                    vm.viewModel.selectDmeSido = vm.viewModel.listDmeSidoCode[0].sido_code;
                })
        },
        fnGetWholeList: function() {
            $("#loading").show();

            let vm = this;
            vm.viewModel.listWholeDme = [];
            vm.viewModel.listTopComDme = [];

            let start_date = vm.viewModel.stDate + vm.fnGetSplitOptionValueModel(vm.viewModel.selectDmeDate, 0);
            let end_date = vm.viewModel.endDate + vm.fnGetSplitOptionValueModel(vm.viewModel.selectDmeDate, 1);

            Axios.post('/api/damage/history/whole/list.do', { paramStDate: start_date, paramEndDate: end_date, paramDmeCode: vm.viewModel.selectTopDmeCode, paramGungu: vm.viewModel.selectDmeSido })
                .then(function(response) {
                    if (response.data.listDmeGungu.length > 0) {
                        response.data.listDmeGungu.sort(function(a, b) { return b.total_damage - a.total_damage; });
                        for (var i = 0; i < response.data.listDmeGungu.length; i++) {
                            response.data.listDmeGungu[i].seq = (i + 1);
                        }

                        for (var i = 0; i < response.data.listDmeGungu.length; i++) {
                            for (var k = 0; k < response.data.typhoonNameList.length; k++) {
                                response.data.listDmeGungu[i].typ_name = null;
                            }
                        }

                        vm.viewModel.listWholeDme = response.data.listDmeGungu;

                        vm.fnOnClickGunguDme(vm.viewModel.listWholeDme[0]);

                        vm.fnGetDisEventItemList(vm.viewModel.selectTopDmeCode, vm.viewModel.listWholeDme[0].beg_date, vm.viewModel.listWholeDme[0].end_date); //재해리포트
                    }

                    $("#loading").hide();
                })
        },
        fnOnClickGunguDme: function(item) {
            $("#loading").show();
            let vm = this;
            vm.viewModel.selectedDamage = item;
            vm.viewModel.listTopComDme = [];

            let stDate = item.beg_date;
            let endDate = item.end_date;
            vm.viewModel.selectedParamStDate = item.beg_date;
            vm.viewModel.selectedParamEndDate = item.end_date;

            let _chart_10years = { labels: [], values: [] };
            let chart_label_name;

            Axios.post('/api/damage/history/topDme/list.do', { paramStDate: stDate, paramEndDate: endDate, paramDmeCode: vm.viewModel.selectTopDmeCode, paramGungu: vm.viewModel.selectDmeSido })
                .then(function(response) {
                    if (response.data.listDmeGungu.length > 0) {
                        for (var i = 0; i < response.data.listDmeGungu.length; i++) {
                            response.data.listDmeGungu[i].seq = (i + 1);

                            //pba_tot, pbr_tot, pbh_tot, pbn_tot
                            response.data.listDmeGungu[i].pri_name = vm.fnGetPubDamageFacilityName(response.data.listDmeGungu[i].pba_tot, response.data.listDmeGungu[i].pbr_tot, response.data.listDmeGungu[i].pbh_tot, response.data.listDmeGungu[i].pbn_tot);
                            //cob_tot, cos_tot, cof_tot, pra_tot, prf_tot
                            response.data.listDmeGungu[i].pub_name = vm.fnGetPriDamamgeFacilityName(response.data.listDmeGungu[i].cob_tot, response.data.listDmeGungu[i].cos_tot, response.data.listDmeGungu[i].cof_tot, response.data.listDmeGungu[i].pra_tot, response.data.listDmeGungu[i].prf_tot);
                        }

                        // vm.fnOnCreateTotChart("chart-10years", _chart_10years, chart_label_name, vm.viewModel.selectTopDmeCode);

                        if (response.data.typhoonNameList.length > 0) {
                            for (var i = 0; i < response.data.listDmeGungu.length; i++) {
                                for (var k = 0; k < response.data.typhoonNameList.length; k++) {
                                    response.data.listDmeGungu[i].typ_name = null;
                                }
                            }
                        }

                        vm.viewModel.selectedSidoName = response.data.listDmeGungu[0].sido;
                        vm.viewModel.selectedGunguName = response.data.listDmeGungu[0].sigungu;

                        vm.viewModel.viewType = vm.viewModel.selectTopDmeCode == 'COM_DME' ? 'person' : 'damage';
                        vm.viewModel.selectedMaxComDme = (vm.viewModel.viewType == 'person') ? response.data.listDmeGungu[0].com_dme : response.data.listDmeGungu[0].total_damage;
                        vm.viewModel.listTopComDme = response.data.listDmeGungu;

                        if (vm.viewModel.listTopComDme.length > 0) {
                            vm.fnGetDamageList(vm.viewModel.listTopComDme[0]); //재해차트
                        }
                    }

                    $("#loading").hide();
                })
                .catch(function(ex) {
                    $("#loading").hide();
                    console.log(ex);
                });

        },
        fnOnClickBtnSearch: function() {
            let vm = this;
            if (vm.viewModel.selectDmeDate == '') {
                alert("대설 또는 날짜 전체를 선택하실 수 없습니다.");
                return false;
            } else if (vm.viewModel.selectDmeSido == '') {
                alert("시도 전체를 선택하실 수 없습니다.");
                return false;
            } else {
                vm.popupShow = false;
                vm.fnGetWholeList();
            }
        },
        fnGetPubDamageFacilityName: function(pba_tot, pbr_tot, pbh_tot, pbn_tot) {
            let vm = this;
            let facilityNm = vm.viewModel.listPubFacilityName;
            let result = "";
            /*   listPubFacilityName:["도로", "하천", "학교", "소규모시설물"],
             */
            (pba_tot > 0) ? result = facilityNm[0]: result = result;
            (pbr_tot > 0 && result != "") ? result = result + "," + facilityNm[1]: result = result;
            (pbr_tot > 0 && result == "") ? result = facilityNm[1]: result = result;
            (pbh_tot > 0 && result != "") ? result = result + "," + facilityNm[2]: result = result;
            (pbh_tot > 0 && result == "") ? result = facilityNm[2]: result = result;
            (pbn_tot > 0 && result != "") ? result = result + "," + facilityNm[3]: result = result;
            (pbn_tot > 0 && result == "") ? result = facilityNm[3]: result = result;

            return result;
        },
        fnGetPriDamamgeFacilityName: function(cob_tot, cos_tot, cof_tot, pra_tot, prf_tot) {
            let vm = this;
            let facilityNm = vm.viewModel.listPriFacilityName;
            let result = "";
            /*             listPriFacilityName:["건물", "선박", "농경지", "축대담장", "비닐하우스"]
             */
            (cob_tot > 0) ? result = facilityNm[0]: result = result;
            (cos_tot > 0 && result != "") ? result = result + "," + facilityNm[1]: result = result;
            (cos_tot > 0 && result == "") ? result = facilityNm[1]: result = result;
            (cof_tot > 0 && result != "") ? result = result + "," + facilityNm[2]: result = result;
            (cof_tot > 0 && result == "") ? result = facilityNm[2]: result = result;
            (pra_tot > 0 && result != "") ? result = result + "," + facilityNm[3]: result = result;
            (prf_tot > 0 && result == "") ? result = facilityNm[4]: result = result;

            return result;
        },
        fnOnClickDetailDamageOpenWindow: function(item) {
            let vm = this;
            let dataType = (vm.viewModel.viewType == 'person') ? vm.viewModel.viewType : 'public';
            let url = "/data/damage/" + dataType + "/" + vm.viewModel.selectDmeSido + "/" + item.beg_date + "/" + item.end_date + "/" + vm.viewModel.selectTopDmeCode + "/" + "dmeTopDetail.do";
            let openWindowName = "topDmeDetail";
            let openWindowOption = 'width=710, height=560, resizable = no, scrollbars=no, toolbar=no';
            window.open(url, openWindowName, openWindowOption);
        },
        fnOnClickPopupClose: function() {
            let vm = this;
            vm.popupShow = false;
        },
        fnGetSplitOptionValueModel: function(model, number) {
            let data = [];
            data = model.split("_");
            return data[number];
        },
        fnGetDisEventItemList: function(paramCode, paramStartDate, paramEndDate) {
            let vm = this;
            vm.keyListView = [];

            Axios.post('/advis/disaster/status/eventItem/list', { paramDisType: paramCode, paramStDate: paramStartDate.substring(0, 6), paramEndDate: paramEndDate.substring(0, 6), paramTitle: vm.viewModel.selectedTyphoonName })
                .then(function(response) {
                    let eventList = response.data.resultList;
                    let tempList = [];

                    //대처상황(50000)리스트
                    for (var i = 0; i < eventList.length; i++) {
                        if (eventList[i] != null) {
                            for (var j = 0; j < eventList[i].eventItemList.length; j++) {
                                if (eventList[i].eventItemList[j].ctg_id.indexOf('50000') > -1) {
                                    tempList.push(eventList[i].eventItemList[j]);
                                }
                            }
                        }
                    }
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
                                // temp = tempList[i].contents.replace('/', '');
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
                                    // temp = tempList[j].contents.replace('/', '');
                                    temp = temp.replace('  ', ' ');
                                    arr = temp.split(' ');
                                    if (keyList[i].printDay == vm.fnConvertDay(arr[0])) { //같은날짜
                                        let cont = { hour: '', printHour: '', org: '', content: '', contentId: '' };
                                        cont.hour = arr.length == 1 ? '' : arr[1];
                                        cont.printHour = keyList[i].printDay + ' ' + cont.hour.replace("∼", "");

                                        for (let k = j + 1; k < tempList.length; k++) {
                                            if (tempList[k].contentsTitle == '일시') {
                                                break;
                                            } else if (tempList[k].contentsTitle == '기관명') {
                                                cont.org = tempList[k].contents;
                                            } else {
                                                if (tempList[k].contentsTitle == '대처사항') {
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
                .catch(function(ex) {
                    $("#loading").hide();
                    console.log(ex);
                });
        },

        fnConvertDay: function(paramVal) {
            paramVal = paramVal.replace(/\,/, "").trim();
            let arr = paramVal.split('.');
            if (arr[1].length == 1) arr[1] = '0' + arr[1];
            paramVal = arr[0] + '.' + arr[1];
            return paramVal;
        },
        fnGetDamageList: function(paramObj) {
            let vm = this;
            vm.viewModel.selectedDamageGungu = paramObj;
            Axios.post("/api/damage/snow/area/detail.do", {
                paramStDate: String(vm.viewModel.stDate),
                paramEndDate: String(vm.viewModel.endDate),
                paramGungu: paramObj.sigungu_code,
                paramDmeCode: vm.viewModel.selectTopDmeCode
            }).then(function(response) {
                vm.viewModel.listDamage = response.data.listDamage;
            }).catch(function(ex) {
                console.log("ex");
            });
        },
        fnCreateAreaChart: function(paramStDate, paramEndDate) {
            let vm = this;
            let damageChart = { labels: [], values: [] };
            let pubDamageChart = { labels: [], values: [] };
            let priDamageChart = { labels: [], values: [] };

            for (let i = 0; i < vm.listDamage.length; i++) {
                let item = vm.listDamage[i];
                damageChart.labels.push(item.beg_date);
                damageChart.values.push(item.com_dme);

                pubDamageChart.labels.push(item.beg_date);
                pubDamageChart.values.push(item.pub_total);

                priDamageChart.labels.push(item.beg_date);
                priDamageChart.values.push(item.pri_total + item.com_total);
            }

            let chartTitle = $.stringFormat("{0}년~{1}년 {2} 인명 피해현황", paramStDate, paramEndDate, vm.viewModel.selectedDamage.damage_name);
            let _chartOption = vm.fnCreateDefaultChartOption(chartTitle, "bar", damageChart.labels, true);
            _chartOption.options.scales.yAxes = [];
            _chartOption.options.scales.yAxes.push({
                display: true,
                position: 'left',
                id: 'y-axis-1',
                scaleLabel: {
                    display: true,
                    labelString: '명'
                }
            });
            _chartOption.data.datasets.push({
                label: "사망실종 피해(명)",
                data: damageChart.values,
                backgroundColor: 'rgba(84,212,221,1)',
                borderColor: 'rgba(84,212,221,1)',
                yAxisID: 'y-axis-1',
            });
            vm.fnCreateChart("personChart", _chartOption);

            let chartTitle_pub = $.stringFormat("{0}년~{1}년 {2} 공공시설 피해현황", paramStDate, paramEndDate, vm.viewModel.selectedDamage.damage_name);
            let _chartOption_pub = vm.fnCreateDefaultChartOption(chartTitle_pub, "bar", pubDamageChart.labels, true);
            _chartOption_pub.options.scales.yAxes = [];
            _chartOption_pub.options.scales.yAxes.push({
                display: true,
                position: 'left',
                id: 'y-axis-2',
                scaleLabel: {
                    display: true,
                    labelString: '천원'
                }
            });
            _chartOption_pub.data.datasets.push({
                label: "공공시설 피해(천원)",
                data: pubDamageChart.values,
                backgroundColor: 'rgba(84,212,221,1)',
                borderColor: 'rgba(84,212,221,1)',
                yAxisID: 'y-axis-2',
            });
            vm.fnCreateChart("pubChart", _chartOption_pub);

            let chartTitle_pri = $.stringFormat("{0}년~{1}년 {2} 사유시설 피해현황", paramStDate, paramEndDate, vm.viewModel.selectedDamage.damage_name);
            let _chartOption_pri = vm.fnCreateDefaultChartOption(chartTitle_pri, "bar", priDamageChart.labels, true);
            _chartOption_pri.options.scales.yAxes = [];
            _chartOption_pri.options.scales.yAxes.push({
                display: true,
                position: 'left',
                id: 'y-axis-3',
                scaleLabel: {
                    display: true,
                    labelString: '천원'
                }
            });
            _chartOption_pri.data.datasets.push({
                label: "사유시설 피해(천원)",
                data: priDamageChart.values,
                backgroundColor: 'rgba(84,212,221,1)',
                borderColor: 'rgba(84,212,221,1)',
                yAxisID: 'y-axis-3',
            });
            vm.fnCreateChart("priChart", _chartOption_pri);
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
                            right: 30,
                            top: 30,
                            bottom: 30
                        }
                    },
                    plugins: {
                        datalabels: {
                            anchor: function(context) {
                                let value = context.dataset.data[context.dataIndex];
                                return value < 50 ? 'end' : 'end';
                            },
                            align: function(context) {
                                let value = context.dataset.data[context.dataIndex];
                                return value < 50 ? 'end' : 'center';
                            },
                            color: function(context) {
                                let value = context.dataset.data[context.dataIndex];
                                return value < 50 ? context.dataset.backgroundColor : 'black';
                            },
                            font: {
                                weight: 'bold'
                            },
                            formatter: function(value) {
                                if (typeof(value) == 'number') {
                                    return Math.round(value);
                                } else {
                                    return value.title;
                                }
                            },
                            offset: 2,
                            padding: 0
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
                                fontSize: 12,
                                callback: function(label, index, labels) {
                                    return Intl.NumberFormat('hi', { style: 'currency', currency: 'INR', minimumFractionDigits: 2 })
                                        .format(label).replace(/^(\D+)/, '$1 ');
                                }
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
        fnClickEventLink: function(paramId) {
            let addr = '/advis/code/codeView/' + paramId.substring(1, 3) + '/' + paramId;
            window.open(addr, '_blank');
        }
    }
});