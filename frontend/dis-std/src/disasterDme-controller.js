import Vue from 'vue';
import Axios from 'axios';
import Chart from "chart.js"
import UtilDate from '../commons/util-date';
import annotation from 'chartjs-plugin-annotation';

var box_id_index = 0;

var vue = new Vue({
    el: '#scope-disasterDme',
    data: function() {
        return {
            chartObj: [],
            map: null,
            viewModel: {
                typInfo: { time: '', press: '', wind: '', speed: '' },
                selectTopDmeCode: 'HZD002',
                selectedGunguCode: '',
                selectedSidoName: '',
                selectedGunguName: '',
                selectedMaxComDme: '',
                selectedParamStDate: '',
                selectedParamEndDate: '',
                selectTyphoonSeq: '',
                selectedTyphoonName: '',
                selectedTyphoon: '',
                selectDmeGungu: '',
                selectDmeDate: '',
                viewType: 'person',
                selectedDamage: {},
                stDate: new Date().getFullYear() - 12,
                endDate: new Date().getFullYear() - 1,
                listWholeDme: [],
                listTopComDme: [],
                listTopTotDme: [],
                listYears: [],
                listYearsSelect: [],
                listPubAndPri: [],
                listComDmeChart: [],
                listTotalDmeChart: [],
                listSelectItem: [],
                listMonth: [],
                listTopDmeCode: [{ code: "HZD002", code_name_dme: "태풍" }, { code: "HZD006", code_name_dme: "호우" }],
                listTyphoons: [],
                listTyphoonPosition: [],
                listDmeDateCode: [],
                listDmeSidoCode: [],
                listDmeGunguCode: [],
                listPubFacilityName: ["도로", "하천", "학교", "소규모시설물"],
                listPriFacilityName: ["건물", "선박", "농경지", "축대담장", "비닐하우스"],
                listNews: [],
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
            $("#loading").show();
            let vm = this;
            vm.viewModel.stDate = 2016;
            vm.viewModel.selectedTyphoonName = "";
            vm.viewModel.selectedMonth = "";

            this.fnGetTypoonMonthly(true);

        },
        fnGetTypoonMonthly: function(isSetDefault) {
            let vm = this;

            Axios.post("/api/typhoon/code/month/list.do", { paramYear: vm.viewModel.stDate, paramMonth: '09', })
                .then(function(response) {

                    vm.viewModel.listMonth = response.data.typMonthList;

                    if (isSetDefault) {
                        vm.viewModel.listTyphoons = response.data.typNameList;
                        if (vm.viewModel.listMonth != null && vm.viewModel.listMonth.length > 0) {

                            vm.viewModel.selectedMonth = "09"; //vm.viewModel.listMonth[0].beg_month;
                        }

                        if (vm.viewModel.listTyphoons != null && vm.viewModel.listTyphoons.length > 0) {
                            //vm.viewModel.selectedTyphoonName=vm.viewModel.listTyphoons[0].typ_name;
                            //vm.viewModel.selectTyphoonSeq=vm.viewModel.listTyphoons[0].seq;
                            vm.viewModel.selectedTyphoonName = "차바";
                            vm.viewModel.selectTyphoonSeq = 18;

                        }

                        vm.fnGetTyphoonInfo();
                    }
                })
                .catch(function(ex) {

                });
        },
        fnGetCodeList: function(paramDmeCode) {
            let vm = this;
            vm.viewModel.selectTopDmeCode = paramDmeCode;
            vm.viewModel.selectDmeDate = "";
            vm.viewModel.selectDmeSido = "";
            vm.viewModel.selectDmeGungu = "";
            Axios.post('/api/damage/history/code/list.do', { paramYear: vm.viewModel.stDate })
                .then(function(response) {
                    if (response.data.listCastTyphoonName != null && response.data.listCastTyphoonName.length > 0) {
                        vm.viewModel.listTyphoons = response.data.listCastTyphoonName;
                        vm.viewModel.selectedTyphoon = vm.viewModel.listTyphoons[0];
                        vm.viewModel.selectedTyphoonName = vm.viewModel.listTyphoons[0].typ_name;
                        vm.fnGetTyphoonInfo();
                    }
                    if (vm.viewModel.selectTopDmeCode == "HZD002" || vm.viewModel.selectTopDmeCode == "HZD007") {
                        vm.viewModel.listDmeDateCode = response.data.listCastTyphoonName;
                        vm.viewModel.listDmeSidoCode = response.data.listDmeSidoCode;
                        vm.viewModel.selectDmeSido = vm.viewModel.listDmeSidoCode[0].sido_code;
                    } else {
                        vm.viewModel.listDmeDateCode = response.data.listDmeDateCode;

                        vm.viewModel.listDmeSidoCode = response.data.listDmeGunguCode;
                        vm.viewModel.selectDmeSido = vm.viewModel.listDmeSidoCode[0].sido_code;
                    }

                    vm.fnGetWholeList();

                })

        },
        fnGetTyphoonInfo: function() {
            let vm = this;

            Axios.post("/api/typhoon/cast/inform/list.do", {
                    paramYear: vm.viewModel.stDate,
                    paramMonth: vm.viewModel.selectedMonth,
                    paramSeq: vm.viewModel.selectTyphoonSeq
                })
                .then(function(response) {
                    vm.viewModel.listTyphoonPosition = response.data.typhoonList;
                    vm.viewModel.selectedTyphoon = response.data.typhoonInfo;
                    vm.fnGetWholeList();
                    vm.fnCreateTyphoonPosition();
                    vm.fnCreateTyphoonChart();
                })
                .catch(function(ex) {
                    console.log(ex);
                });
        },
        fnOnChangeYear: function() {
            this.viewModel.selectedTyphoonName = "";
            this.viewModel.selectedMonth = "";
            this.viewModel.listMonth = [];
            this.viewModel.listTyphoons = [];
            this.fnGetTypoonMonthly(false);

        },
        fnOnChangeMonth: function() {
            this.viewModel.selectedTyphoonName = "";
            this.viewModel.listTyphoons = [];
            let vm = this;
            Axios.post("/api/typhoon/code/name/list.do", {
                    paramYear: vm.viewModel.stDate,
                    paramMonth: vm.viewModel.selectedMonth
                })
                .then(function(response) {
                    vm.viewModel.listTyphoons = response.data.typNameList;

                })
                .catch(function(ex) {
                    console.log(ex);
                });

        },
        fnOnChangeTyphoon: function() {
            for (let i = 0; i < this.viewModel.listTyphoons.length; i++) {
                let item = this.viewModel.listTyphoons[i];
                if (item.typ_name == this.viewModel.selectedTyphoonName) {
                    this.viewModel.selectTyphoonSeq = item.seq;
                    break;
                }
            }

        },
        fnOnClickBtnSearch: function() {
            $("#loading").show();
            this.viewModel.typInfo = { time: '', press: '', wind: '', speed: '' };
            this.viewModel.selectedParamStDate = '';
            this.viewModel.selectedParamEndDate = '';
            this.viewModel.selectedSidoName = '';
            this.viewModel.selectedGunguName = '';
            this.viewModel.selectedMaxComDme = 0;

            this.viewModel.listNews = [];
            this.eventImgList = [];
            this.keyListView = [];
            this.orgDateList = [];
            this.orgList = [];
            this.fnGetTyphoonInfo();
        },

        fnGetWholeList: function() {
            if (this.viewModel.selectedTyphoon == null || this.viewModel.selectedTyphoonName == '') return false;


            let vm = this;
            vm.viewModel.listWholeDme = [];
            vm.viewModel.listTopComDme = [];

            let stDate = vm.viewModel.selectedTyphoon.beg_date;
            let endDate = vm.viewModel.selectedTyphoon.end_date;
            vm.viewModel.selectDmeSido = null;

            Axios.post('/api/damage/history/whole/list.do', { paramStDate: stDate, paramEndDate: endDate, paramDmeCode: vm.viewModel.selectTopDmeCode, paramGungu: vm.viewModel.selectDmeSido })
                .then(function(response) {


                    if (response.data.listDmeGungu.length > 0) {
                        for (var i = 0; i < response.data.listDmeGungu.length; i++) {
                            response.data.listDmeGungu[i].seq = (i + 1);
                        }


                        for (var i = 0; i < response.data.listDmeGungu.length; i++) {
                            for (var k = 0; k < response.data.typhoonNameList.length; k++) {
                                if (response.data.listDmeGungu[i].damage_code == "HZD002" || response.data.listDmeGungu[i].damage_code == "HZD007") {
                                    if (parseInt(response.data.listDmeGungu[i].beg_date) >= parseInt(response.data.typhoonNameList[k].beg_date) &&
                                        parseInt(response.data.typhoonNameList[k].beg_date) <= parseInt(response.data.listDmeGungu[i].end_date) &&
                                        (Math.abs(parseInt(response.data.typhoonNameList[k].end_date) - parseInt(response.data.listDmeGungu[i].end_date)) >= 0)
                                    ) {
                                        response.data.listDmeGungu[i].typ_name = response.data.typhoonNameList[k].typ_name;
                                    }
                                } else {
                                    response.data.listDmeGungu[i].typ_name = null;
                                }
                            }
                        }

                        vm.viewModel.listWholeDme = response.data.listDmeGungu;

                        vm.fnOnClickGunguDme(vm.viewModel.listWholeDme[0]);

                        vm.fnGetDisEventItemList(vm.viewModel.selectTopDmeCode, vm.viewModel.listWholeDme[0].beg_date, vm.viewModel.listWholeDme[0].end_date); //재해리포트

                        //재해뉴스
                        let param = { text: vm.viewModel.selectedTyphoon.beg_date.substring(0, 4) + '년 태풍 ' + vm.viewModel.selectedTyphoon.typ_name, sort: 'accuracy', size: 50, page: 1 };
                        vm.fnGetDaumNews(param);

                        //재해이미지
                        vm.eventImgList = [];
                        if (response.data.eventImgList.length > 5) {
                            for (let i = 0; i < 7; i++) {
                                vm.eventImgList.push(response.data.eventImgList[i]);
                            }
                        }
                    }
                    $("#loading").hide();

                }).catch(function(ex) {
                    $("#loading").hide();

                });
        },
        fnOnClickGunguDme: function(item) {
            //$("#loading").show();
            let vm = this;
            vm.viewModel.selectedDamage = item;
            //   vm.isGunguTopDmeView = true;
            vm.viewModel.listTopComDme = [];

            let stDate = item.beg_date;
            let endDate = item.end_date;
            vm.viewModel.selectedParamStDate = item.beg_date;
            vm.viewModel.selectedParamEndDate = item.end_date;

            // vm.isGunguTopDme = (isClickValue) ? true : false; 

            let _chart_10years = { labels: [], values: [] };
            let chart_label_name;


            Axios.post('/api/damage/history/topDme/list.do', { paramStDate: stDate, paramEndDate: endDate, paramDmeCode: 'COM_DME', paramGungu: vm.viewModel.selectDmeSido })
                .then(function(response) {


                    if (response.data.listDmeGungu.length > 0) {

                        response.data.listDmeGungu.sort(function(a, b) { return b.total_damage - a.total_damage; });

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
                                    if (response.data.listDmeGungu[i].damage_code == "HZD002" || response.data.listDmeGungu[i].damage_code == "HZD007") {
                                        if (parseInt(response.data.listDmeGungu[i].beg_date) >= parseInt(response.data.typhoonNameList[k].beg_date) &&
                                            parseInt(response.data.typhoonNameList[k].beg_date) <= parseInt(response.data.listDmeGungu[i].end_date) &&
                                            (Math.abs(parseInt(response.data.typhoonNameList[k].end_date) - parseInt(response.data.listDmeGungu[i].end_date)) >= 0)) {
                                            response.data.listDmeGungu[i].typ_name = response.data.typhoonNameList[k].typ_name;
                                        }
                                    } else {
                                        response.data.listDmeGungu[i].typ_name = null;
                                    }
                                }
                            }
                        }

                        vm.viewModel.selectedSidoName = response.data.listDmeGungu[0].sido;
                        vm.viewModel.selectedGunguName = response.data.listDmeGungu[0].sigungu;

                        vm.viewModel.viewType = vm.viewModel.selectTopDmeCode == 'COM_DME' ? 'person' : 'damage';
                        vm.viewModel.selectedMaxComDme = (vm.viewModel.viewType == 'person') ? response.data.listDmeGungu[0].com_dme : response.data.listDmeGungu[0].total_damage;
                        vm.viewModel.listTopComDme = response.data.listDmeGungu;

                    }

                    $("#loading").hide();

                })
                .catch(function(ex) {
                    $("#loading").hide();
                    console.log(ex);
                });

        },
        // fnOnClickBtnSearch :function(){

        //    let vm =this;

        //     if(vm.viewModel.selectedTyphoonName!=''){

        //        vm.popupShow = false;
        //        vm.fnGetWholeList();
        //     }
        // },
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
            vm.orgList = [];
            Axios.post('/advis/disaster/status/eventItem/list', { paramDisType: paramCode, paramStDate: paramStartDate.substring(0, 6), paramEndDate: paramEndDate.substring(0, 6), paramTitle: vm.viewModel.selectedTyphoonName })
                .then(function(response) {
                    let eventList = response.data.resultList;
                    let tempList = [];

                    //대처상황(50000)리스트
                    for (var i = 0; i < eventList.length; i++) {
                        for (var j = 0; j < eventList[i].eventItemList.length; j++) {
                            if (eventList[i].eventItemList[j].ctg_id.indexOf('50000') > -1) {
                                tempList.push(eventList[i].eventItemList[j]);
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
                                var regExp = /[\{\}\[\]\/?,;|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/gi;

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
                                        cont.hour = arr[1] != null ? arr[1] : '00:00';
                                        cont.printHour = keyList[i].printDay + ' ' + cont.hour.replace("∼", "");

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
                            keyList[i].detail = keyList[i].detail.sort(function ascending(a, b) { return (a.hour < b.hour) ? -1 : (a.hour == b.hour) ? 0 : 1; });
                        }

                        //날짜+시간 중복체크
                        let list = [];
                        for (let i = 0; i < keyList.length; i++) {
                            if (keyList[i].detail != null && keyList[i].detail.length > 0) {
                                for (let j = 0; j < keyList[i].detail.length; j++) {
                                    let col = { date: '', detail: [] };
                                    if (j == 0) {
                                        col.date = keyList[i].detail[j].printHour;
                                        list.push(col);
                                    } else {
                                        let boo = true;
                                        for (let k = 0; k < list.length; k++) {
                                            if (list[k].date.trim() == keyList[i].detail[j].printHour.trim()) boo = false;
                                        }
                                        if (boo) {
                                            col.date = keyList[i].detail[j].printHour;
                                            list.push(col);
                                        }
                                    }
                                }
                            }
                        }
                        //[날짜+시간, [기관,내용,문서아이디]] 만들기
                        for (let k = 0; k < list.length; k++) {
                            for (let i = 0; i < keyList.length; i++) {
                                if (keyList[i].detail.length > 0) {
                                    for (let j = 0; j < keyList[i].detail.length; j++) {

                                        if (list[k].date == keyList[i].detail[j].printHour) {
                                            //같은날짜,기관명 중복체크
                                            if (list[k].detail.length == 0) {
                                                if (keyList[i].detail[j].org != '' && keyList[i].detail[j].org != null) list[k].detail.push(keyList[i].detail[j]);
                                            } else {
                                                if (keyList[i].detail[j].org != '' && keyList[i].detail[j].org != null) {
                                                    let boo = true;
                                                    for (let m = 0; m < list[k].detail.length; m++) {
                                                        if (keyList[i].detail[j].org == list[k].detail[m].org) {
                                                            list[k].detail[m] = keyList[i].detail[j];
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
                })
                .catch(function(ex) {
                    $("#loading").hide();
                    console.log(ex);
                });
        },
        fnConvertDay: function(paramVal) {
            paramVal = paramVal.replace(/\,/, "").trim();
            let arr = paramVal.split('.');
            if(arr.length>1){
                if (arr[1].length == 1) arr[1] = '0' + arr[1];
                paramVal = arr[0] + '.' + arr[1];
            }
            return paramVal;
        },


        fnCreateTyphoonPosition: function() {
            this.fnRemoveAllLayer();
            let pointFeatures = [];
            let windFeatrues = [];
            let line = [];
            for (let i = 0; i < this.viewModel.listTyphoonPosition.length; i++) {
                let item = this.viewModel.listTyphoonPosition[i];
                let point = new ol.Feature({
                    type: 'clickable',
                    geometry: new ol.geom.Point(ol.proj.fromLonLat([parseFloat(item.typ_lon), parseFloat(item.typ_lat)])),
                    description: item.typ_tm,
                    typWindSpeed: item.typ_ws,
                    typTime: item.typ_tm,
                    typPress: item.typ_ps,
                    typSpeed: item.typ_sp

                });
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
                name: "point",
                source: pointSource,
                style: pointStyle
                    // style:function(feature) {

                //     pointStyle.getText().setText(feature.get('description'));

                //     return pointStyle;
                //   }

            });
            var lineLayer = new ol.layer.Vector({
                name: "line",
                source: lineSource,
                style: lineStyle
            });

            var windLayer = new ol.layer.Vector({
                name: "wind",
                source: windSource,
                style: windStyle
            });
            //  windLayer.setStyle(this.fnGetWindStyle);
            this.map.addLayer(lineLayer);
            this.map.addLayer(pointLayer);
            this.map.addLayer(windLayer);


            var overText = new ol.Overlay({
                position: ol.proj.fromLonLat(line[line.length - 1]),
                element: document.getElementById('overlay-label'),
                offset: [10, 8]
            });
            this.map.addOverlay(overText);
            this.map.on("click", this.fnOnMapClick);
            this.map.on("moveend", this.fnOnMoveEnd);
            this.map.on("movestart", this.fnOnMoveStart);

            $("#overlay-label").html(String(pointFeatures[pointFeatures.length - 1].get('description')).substring(0, 8).dateFormat());
            $("#overlay-label").show();
        },
        fnOnMoveStart: function(e) {
            console.log('start');
        },
        fnOnMoveEnd: function(e) {
            console.log('end');
        },
        fnOnMapClick: function(e) {
            let vm = this;
            this.map.forEachFeatureAtPixel(e.pixel, function(feature, layer) {

                if (feature.get('type') != undefined && feature.get('type') != null && feature.get('type') == 'clickable') {
                    vm.viewModel.typInfo = {
                        time: feature.get('typTime').substring(0, 8).dateFormat() + " " + feature.get('typTime').substring(8, 10),
                        press: feature.get('typPress'),
                        speed: feature.get('typSpeed'),
                        wind: feature.get("typWindSpeed")
                    };

                };
            })
        },
        fnRemoveAllLayer: function() {

            let removeTarget = [];
            this.map.getLayers().forEach(function(layer) {
                if (layer.get('name') == 'wind' || layer.get('name') == 'line' || layer.get('name') == 'point') {
                    removeTarget.push(layer);
                }
            });
            for (let i = 0; i < removeTarget.length; i++) {
                this.map.removeLayer(removeTarget[i]);
            }
        },
        fnGetWindStyle: function(feature, resolution) {
            let _radius = 0;
            if (feature != undefined && feature.get("r") != undefined) {
                _radius = feature.get("r");
            }

            let windStyle = [new ol.style.Style({
                image: new ol.style.Circle({
                    radius: 10,
                    stroke: new ol.style.Stroke({
                        color: 'rgba(255,255,255, 0.3)',
                        width: 1
                    }),
                    fill: new ol.style.Fill({
                        color: 'rgba(255,255,255, 0.3)'
                    })
                })
            })];
            return windStyle;

        },
        fnClickEventLink: function(paramId) {
            let addr = '/advis/code/codeView/' + paramId.substring(1, 3) + '/' + paramId;
            window.open(addr, '_blank');
        },
        fnGetDaumNews: function(paramQueryObj) {
            let vm = this;
            Axios.post("/api/kakao/get/search", {
                    paramUrl: 'https://dapi.kakao.com/v2/search/web',
                    paramKey: 'dd615544fe7d753ebfabf05ad473f6bf',
                    paramQuery: paramQueryObj.text,
                    paramSort: paramQueryObj.sort,
                    paramSize: paramQueryObj.size,
                    paramPage: paramQueryObj.page
                })
                .then(function(response) {
                    vm.viewModel.listNews = [];
                    for (let i = 0; i < response.data.documents.length; i++) {
                        let item = response.data.documents[i];
                        if (item.title.indexOf('위키') < 0) {

                            item.title = item.title.replace(/<[^>]+>/g, ' ');
                            item.printTitle = item.title.substring(0, 50);
                            item.datetime = item.datetime.substring(0, 10).replace(/-/g, '');
                            if (vm.viewModel.listNews.length < 6) {
                                vm.viewModel.listNews.push(item);
                            }
                        }


                    }
                    vm.viewModel.listNews.sort(function(a, b) { return b.datetime - a.datetime; });
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
                    paramDamageName: vm.viewModel.selectedTyphoonName
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
                            id: 'box' + box_id_index++,
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
                    // plugins: {
                    //     datalabels: {
                    //         anchor: function(context) {
                    //             let value = context.dataset.data[context.dataIndex];

                    //             return value < 50 ? 'end' : 'end';
                    //         },
                    //         align: function(context) {
                    //             let value = context.dataset.data[context.dataIndex];
                    //             return value < 50 ? 'end' : 'center';
                    //         },
                    //         color: function(context) {
                    //             let value = context.dataset.data[context.dataIndex];
                    //             return value < 50 ? context.dataset.backgroundColor : 'black';
                    //         },
                    //         font: {
                    //             weight: 'bold'
                    //         },
                    //         formatter: function(value) {
                    //            if(typeof(value)=='number'){
                    //             return Math.round(value);
                    //            }else{
                    //                return value.title;
                    //            }

                    //         },
                    //         offset: 2,
                    //         padding: 0
                    //     }
                    // },
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
        }
    }
});