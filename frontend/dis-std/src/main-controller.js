import Vue from 'vue'
import Axios from 'axios'
import * as d3 from 'd3'
import Chart from "chart.js"
import 'chartjs-plugin-datalabels'
import Global from './global'

var vue = new Vue({
    el: '#scope-main',
    data: function() {
        return {
            init: false,
            root: null,
            node: null,
            chartColors: ['rgba(255, 99, 132,0.5)',
                'rgba(255, 159, 64,0.5)',
                'rgba(255, 205, 86,0.5)',
                'rgba(75, 192, 192,0.5)',
                'rgba(54, 162, 235,0.5)',
                'rgba(153, 102, 255,0.5)',
                'rgba(201, 203, 207,0.5)'
            ]

            ,
            chartObj: [],
            viewModel: {
                listDamageRate:[],
                listDamageRank:[],
                listDamageTypes:[],
                listNews: [],
                stDate: '',
                endDate: '',
                selectedDamageTypes:{},
                selectedDamage: { damageCode: '', damageName: '' },
                printDataSet: null,
                dataSet: null,
                listDamages: [],
                listAreaDamages: [],
                listSido: [],
                listLog: []
            },

            listLargeCode: [],
            listMediumCode: [],
            listSmallCode: [],
            listDetailCode: [],
            listSelectedSidoCode:[],

            selectedLargeCode: '',
            selectedMediumCode: '',
            selectedSmallCode: '',
            selectedDetailCode: '',
            selectedSearchCtgId: '',
            selectedSearchDepth: 0,
            
        };
    },
    mounted: function() {
        this.viewModel.listSido.push({ code: '11', name: '서울', checked: true });
        this.viewModel.listSido.push({ code: '26', name: '부산', checked: true });
        this.viewModel.listSido.push({ code: '27', name: '대구', checked: true });
        this.viewModel.listSido.push({ code: '28', name: '인천', checked: true });
        this.viewModel.listSido.push({ code: '29', name: '광주', checked: true });
        this.viewModel.listSido.push({ code: '30', name: '대전', checked: true });
        this.viewModel.listSido.push({ code: '31', name: '울산', checked: true });
        this.viewModel.listSido.push({ code: '36', name: '세종', checked: true });
        this.viewModel.listSido.push({ code: '41', name: '경기도', checked: true });
        this.viewModel.listSido.push({ code: '42', name: '강원도', checked: true });
        this.viewModel.listSido.push({ code: '43', name: '충북', checked: true });
        this.viewModel.listSido.push({ code: '44', name: '충남', checked: true });
        this.viewModel.listSido.push({ code: '45', name: '전북', checked: true });
        this.viewModel.listSido.push({ code: '46', name: '전남', checked: true });
        this.viewModel.listSido.push({ code: '47', name: '경북', checked: true });
        this.viewModel.listSido.push({ code: '48', name: '경남', checked: true });
        this.viewModel.listSido.push({ code: '50', name: '제주', checked: true });
        pageLoad(this);

    },
    methods: {
        color: function(n) {
            var colores_g = ["rgba(248,187,208,1)", "rgba(178,223,219,1)", "rgba(187,222,251,1)", "rgba(178,235,242,1)", "rgba(248,187,208,1)", "rgba(197,202,233,1)"];
            if (n == 1) {
                n = 2;
            } else if (n > 1 && n <= 1.5) {
                n = 3;
            } else if (n >= 1.5 && n < 1.7) {
                _n = 4;
            } else {
                n = 5;
            }
            return colores_g[n];
        }

        ,fnlistCheckedArea: function() {
            let vm = this;

            Axios.post("/history/accident/listCheckedAreaView", {}).
            then(function(response) {

                for(let i=0; i<response.data.listSelectedSidoCodes.length; i++){
                    vm.listSelectedSidoCode.push(response.data.listSelectedSidoCodes[i].law_code);
                }

                vm.fnGetStatisticsSummary();

            }).
            catch(function(ex) {
                console.log(ex);
            });
        }

        ,
        fnGetStatisticsSummary: function() {
            let vm = this;
            let now = new Date();

            let preMonth = (now.getMonth() - 1) + 1;
            let nextMonth = (now.getMonth() + 1) + 1;
            let preYear = (now.getFullYear() - 10);;
            let currentYear = now.getFullYear();
            let currentMonth = now.getMonth() + 1;

            preMonth = preMonth < 10 ? ("0" + preMonth) : preMonth.toString();
            nextMonth = nextMonth < 10 ? ("0" + nextMonth) : nextMonth.toString();
            currentMonth = currentMonth < 10 ? ("0" + currentMonth) : currentMonth.toString();
            let paramMonths = [preMonth, currentMonth, nextMonth];

            vm.viewModel.stDate = preYear;
            vm.viewModel.endDate = currentYear;
            
            Axios.post("/api/statistics/summary", { paramMonth: currentMonth, paramStartDate: preYear, paramEndDate: currentYear, paramListSelectedSidoCodes: vm.listSelectedSidoCode })
                .then(function(response) {
                    vm.viewModel.listDamages = response.data.listStatDisasterMonthly;
                    vm.viewModel.listDamages.sort(function(a, b) { return b.damageTotal - a.damageTotal; });
                    vm.viewModel.selectedDamage.damageCode = vm.viewModel.listDamages[0].code;
                    vm.viewModel.selectedDamage.damageName = vm.viewModel.listDamages[0].codeName;
                    vm.viewModel.selectedDamage.damageType = vm.viewModel.selectedDamage.damageCode.startsWith("N") ? "자연재난" : "사회재난";
                    vm.viewModel.listDamageRate=response.data.listMontlyRate;
                    vm.viewModel.listLog = response.data.listLog;

                    
                    vm.viewModel.listDamageRate.sort(function(a, b) { return b.count - a.count; })
                    let tempRank=[];
                    for(let i=0;i<vm.viewModel.listDamageRate.length;i++){
                        let item=vm.viewModel.listDamageRate[i];
                        if(tempRank.indexOf(item.damage_code)<0){
                            tempRank.push(item.damage_code);
                            vm.viewModel.listDamageRank.push({code:item.damage_code,name:item.damage_name,begMonth:item.beg_month,sido:item.sido_code});
                        }
                    }
                    
                    vm.viewModel.selectedDamageTypes= vm.viewModel.listDamageRank[0];
                    let isSocDisYn='Y';
                    if(vm.viewModel.selectedDamageTypes.code.indexOf('HZD')>-1){
                        isSocDisYn='N';
                    }
        
                    vm.viewModel.selectedDamageTypes.isSocDisYn=isSocDisYn;


                    let data = [];
                    if (response.data.listStatDisasterMonthly != null && response.data.listStatDisasterMonthly.length > 0) {
                        let sum = 0;
                        for (let i = 0; i < response.data.listStatDisasterMonthly.length; i++) {
                            let item = response.data.listStatDisasterMonthly[i];
                            sum += item.damageTotal;

                            //if(item.count==0)item.count=0.5;
                            //  data.push({name:item.codeName,value:item.count});    
                        }
                        for (let i = 0; i < response.data.listStatDisasterMonthly.length; i++) {
                            let item = response.data.listStatDisasterMonthly[i];
                            let _value = item.damageTotal / (sum / 2);
                            if (_value == 0) {
                                _value = 1;
                            } else if (_value > 0 && _value <= 0.2) {
                                _value = 1.5;
                            } else if (_value > 0.2 && _value < 0.5) {
                                _value = 1.7;
                            } else {
                                _value = 2;
                            }

                            if(data.indexOf(item.code)<0){
                                data.push({ name: item.codeName, value: _value, code: item.code });
                            }
                            
                        }

                        data.sort(function(a, b) { return b.value - a.value; })
                        vm.viewModel.dataSet = { name: '재해', children: data };
                       vm.fnListDamageMonthlySido( vm.viewModel.listDamageRank[0].code);
                       vm.fnCreateDisRateChart();
                       vm.fnCreateTreeMap();
                       vm.fnListDamageArea();
                    }

                })
                .catch(function(ex) {
                    console.log(ex);
                });
        },
        fnListDamageMonthlySido:function(_paramDamageCode){
            let vm=this;
            let targetItem=vm.viewModel.selectedDamageTypes;
            
            vm.viewModel.listDamageTypes=[];
            let isSocDisYn='Y';
            if(targetItem.code.indexOf('HZD')>-1){
                isSocDisYn='N';
            }

            targetItem.isSocDisYn=isSocDisYn;
                Axios.post("/api/statistics/damage/sido/monthly/list", {paramMonth:targetItem.begMonth,paramDamageName:targetItem.name, paramDamageCode:targetItem.code , paramSidoCode: targetItem.sido ,paramSocDisYn:isSocDisYn })
                .then(function(response) {
                    if(targetItem.isSocDisYn=='N'){
                        for(let i=0;i<response.data.lisMontlySidoDamage.length;i++){
                            let item=response.data.lisMontlySidoDamage[i];
                            if(item.damage_name!='기타'){
                                vm.viewModel.listDamageTypes.push(item);
                            }
                        }
                    }else{
                        vm.viewModel.selectedDamageTypes={
                            name:vm.viewModel.selectedDamageTypes.name,
                            code:response.data.listSocSidoDamage[0].ctg_id,
                            beg_month:response.data.listSocSidoDamage[0].obz_dt.substring(4,6),
                            sido:response.data.listSocSidoDamage[0].addr_code.substring(0,2),
                            description:response.data.listSocSidoDamage[0].dis_content,
                            info:response.data.listSocSidoDamage[0].di_inf_name,
                            damage:(response.data.listSocSidoDamage[0].hm_dmg_injser+response.data.listSocSidoDamage[0].hm_dmg_killed),
                            isSocDisYn:'Y'
                        }
                    }
                })
                .catch(function(ex) {
                    console.log(ex);
                });
            

        },
        fnCreateTreeMap: function() {
            let c10 = d3.scaleOrdinal(d3.schemePaired);
            let vm = this;
            d3.select("#treeMap g").selectAll("*").remove();
            this.root = null;
            let width = 400;
            let height = 200;
            let margin = { top: 20, right: 0, bottom: 0, left: 0 };

            var x = d3.scaleLinear().domain([0, width]).range([0, width]);

            var y = d3.scaleLinear()
                .domain([0, height - margin.top - margin.bottom])
                .range([0, height - margin.top - margin.bottom]);
            if (this.viewModel.printDataSet == null) {
                this.viewModel.printDataSet = this.viewModel.dataSet;
            }
            this.root = d3.hierarchy(this.viewModel.printDataSet);

            let treeMapLayout = d3.treemap();
            treeMapLayout.size([920, 300]);

            treeMapLayout.tile(d3.treemapSquarify.ratio(2));

            this.root.sum(d => d.value);
            treeMapLayout(this.root);

            var treemapNodes = d3.select("#treeMap g")
                .selectAll("g")
                .data(this.root.descendants())
                .enter()
                .append('g').attr('class', 'node')
                .attr('transform', d => 'translate(' + [d.x0, d.y0] + ')');

            treemapNodes.append('rect')
                .classed('the-node', true)
                .attr("width", d => d.x1 - d.x0)
                .attr("height", d => d.y1 - d.y0)
                .style("fill", function(d, i) { return vm.color(d.value); })
                .style('stroke', "#ffffff")
                .style('stroke-width', "0.5px")
                .on('click', function(d) {
                    
                    vm.viewModel.selectedDamage.damageCode = d.data.code;
                    vm.viewModel.selectedDamage.damageName = d.data.name;
                    vm.viewModel.selectedDamage.damageType = vm.viewModel.selectedDamage.damageCode.startsWith("N") ? "자연재난" : "사회재난";
                    vm.fnListDamageArea();
                    let param = { text: vm.viewModel.selectedDamage.damageName, sort: 'accuracy', size: 50, page: 1 };
                    if(vm.viewModel.selectedDamage.damageName=='교통사고'){
                        param.text = '교통사고 블랙아이스 빙판길';
                    }else{
                        param.text = param.text + '재난 재해 news 피해 대응 복구';
                    }

                    vm.fnGetDaumNews(param);

                });

            treemapNodes.append('text')
                .attr('class', 'label')
                .attr('dx', d => 10)
                .attr('dy', d => 20)
                .text(d => d.data.name);

        },
        fnListDamageArea: function() {
            let vm = this;
            Axios.post("/api/statistics/damage/area", {
                    paramStartDate: vm.viewModel.stDate,
                    paramEndDate: vm.viewModel.endDate,
                    paramDamageCode: vm.viewModel.selectedDamage.damageCode
                })
                .then(function(response) {
                    vm.viewModel.listAreaDamages = response.data.listStatSido;

                    vm.fnCreateAreaChart();
                    if (!vm.init) {
                        vm.fnCreateYearBubbleChart();
                        vm.init = true;
                    }
                })
                .catch(function(ex) {
                    console.log(ex);

                })
        },
        fnCreateAreaChart: function() {
            let vm = this;
            let damageChart = { labels: [], price: [], person: [] };

            for (let i = 0; i < vm.viewModel.listAreaDamages.length; i++) {
                let item = vm.viewModel.listAreaDamages[i];
                damageChart.labels.push(item.codeName);
                damageChart.price.push(parseInt(item.damageTotal / 100000));
                damageChart.person.push(item.damagePerson);
            }
            let chartTitle = $.stringFormat("{0}년~{1}년 시도별 {2} 피해현황", vm.viewModel.stDate, vm.viewModel.endDate, vm.viewModel.selectedDamage.damageName);
            let _chartOption = vm.fnCreateDefaultChartOption(chartTitle, "bar", damageChart.labels, true);
            _chartOption.options.scales.yAxes = [];
            _chartOption.options.scales.yAxes.push({ display: true, position: 'left', id: 'y-axis-1', scaleLabel: { display: true, labelString: '억원' } });
            _chartOption.options.scales.yAxes.push({ display: true, position: 'right', id: 'y-axis-2', scaleLabel: { display: true, labelString: '명' } });

            _chartOption.data.datasets.push({
                label: "피해액(억원)",
                data: damageChart.price,
                backgroundColor: 'rgba(42,110,154,1)',
                borderColor: 'rgba(42,110,154,1)',
                yAxisID: 'y-axis-1',
            });

            _chartOption.data.datasets.push({
                label: "인명피해(명)",
                data: damageChart.person,
                backgroundColor: 'rgba(84,212,221,1)',
                borderColor: 'rgba(84,212,221,1)',
                yAxisID: 'y-axis-2',
            });
            vm.fnCreateChart("chart1", _chartOption);
        },
        fnRandomNumber: function() {
            return Math.floor((Math.random() * 30) + 10);
        },
        fnCreateDisRateChart:function(){
            let damageChart = { labels: [], rate: []};
            for(let i=0;i<this.viewModel.listDamageRate.length;i++){
                let item=this.viewModel.listDamageRate[i];
                if(damageChart.labels.indexOf(item.damage_name)<0){
                    damageChart.labels.push(item.damage_name);
                    damageChart.rate.push(item.rate);
                }else{                    
                    let index = damageChart.labels.indexOf(item.damage_name);
                    damageChart.rate[index] += item.rate;
                }
            }
           // let _chartOption = this.fnCreateDefaultChartOption("재해/사고 발생현황", "radar", damageChart.labels, true);
          
          
		let _chartOption = {
            type:'radar',
            data:{
                labels:damageChart.labels,
                datasets:[]
            },
            options:{
                legend: false,
			tooltips: true
            }
			
		};
        _chartOption.data.datasets.push({
                label: "사고내역",
                data: damageChart.rate,
                legend:false,
                backgroundColor: 'rgba(84,212,221,0.2)',
                borderColor: 'rgba(84,212,221,1)'
            });
            
            this.fnCreateChart("chart-radar",_chartOption);
        }
        ,fnCreateYearBubbleChart: function() {
            this.viewModel.listLog.sort(function(a, b) { b.recordCount - a.recordCount });
            let _data = [];
            let colorIndex = 0;
            for (let i = 0; i < this.viewModel.listLog.length; i++) {
                let item = this.viewModel.listLog[i];

                let searchCnt = 0;
                let codeCnt = 0;
                codeCnt = item.recordCount==0? this.fnRandomNumber():item.recordCount;
                searchCnt = codeCnt;

                _data.push({
                        label: item.title,
                        data: [{
                            y: codeCnt,
                            x: searchCnt,
                            r: codeCnt>50?50+(codeCnt/10):codeCnt,
                            title: item.title
                        }],
                        backgroundColor: this.chartColors[colorIndex]
                    }

                );
                if (i > 5 && i % 6 == 0) {
                    colorIndex = -1;
                }
                colorIndex++;
            }
            let _chartOption = this.fnCreateDefaultChartOption("", "bubble", [], true);
            _chartOption.data.datasets = _data;
            _chartOption.options.legend.position = "bottom";
            _chartOption.options.scales.yAxes[0].scaleLabel = { display: true, labelString: '코드등록 수량(개)' };
            _chartOption.options.scales.yAxes[0].ticks = {max: 200};
            _chartOption.options.scales.xAxes.push({ display: true, position: 'left', scaleLabel: { display: true, labelString: '검색빈도' } });
            this.fnCreateChart("chart2", _chartOption);
        }
        ,
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
                            item.printTitle = item.title.substring(0, 30);
                            item.datetime = item.datetime.substring(0, 10).replace(/-/g, '');
                            vm.viewModel.listNews.push(item);
                        }


                    }
                    vm.viewModel.listNews.sort(function(a, b) { return b.datetime - a.datetime; });
                })
                .catch(function(ex) {
                    console.log(ex);
                });
        },
        fnGetAccidentSoc: function() {
            // let vm = this;

            Axios.post("/history/accident/list", {
                paramList: ["46900", "46830", "41390", "27260"]
            }).
            then(function(response) {
               // console.log("결과!!!", response.data);
            }).
            catch(function(ex) {
                console.log(ex);
            });
        }
        ,fnOnClickDamageType:function(param){
            this.viewModel.selectedDamageTypes=param;
            this.fnListDamageMonthlySido();
        }

        ,fnDisCodeList:function(paramDepth){
            let vm = this;
            let paramCtg = null;

            if(paramDepth == 2) { if(vm.selectedLargeCode!='') paramCtg = vm.selectedLargeCode; }
            else if(paramDepth == 3) { if(vm.selectedMediumCode!='') paramCtg = vm.selectedMediumCode; }
            else if(paramDepth == 4) { if(vm.selectedSmallCode!='') paramCtg = vm.selectedSmallCode; }
            else { 
                if(vm.selectedDetailCode!=''){
                    paramCtg = vm.selectedDetailCode; 
                }
            }

            if(paramCtg == null){
                paramDepth = paramDepth - 1;

                if(paramDepth == 2){
                    paramCtg = vm.selectedLargeCode;
                }else if(paramDepth == 3){
                    paramCtg = vm.selectedMediumCode;
                }else if(paramDepth == 4){
                    paramCtg = vm.selectedSmallCode;
                }
            }

            vm.selectedSearchCtgId = paramCtg;
            vm.selectedSearchDepth = paramDepth == null ? 1 : paramDepth;
            
            Axios.post("/api/std/manageDisCodelist", {
                paramDepth:vm.selectedSearchDepth
                ,paramCtgId:vm.selectedSearchCtgId
            }).
            then(function(response) {
                let list = response.data.list;
                if(list.length > 0){
                    if (list[0].depth == 1) {
                        vm.listLargeCode = list;
                        vm.listMediumCode = [];
                        vm.listSmallCode = [];
                        vm.listDetailCode = [];
                        vm.selectedMediumCode = '';
                        vm.selectedSmallCode = '';
                        vm.selectedDetailCode = '';
                    } else if (list[0].depth == 2 && paramDepth == 2) {
                        vm.listMediumCode = list;
                        vm.listSmallCode = [];
                        vm.listDetailCode = [];
                        vm.selectedMediumCode = '';
                        vm.selectedSmallCode = '';
                        vm.selectedDetailCode = '';
                    } else if (list[0].depth == 3  && paramDepth == 3) {
                        vm.listSmallCode = list;
                        vm.listDetailCode = [];
                        vm.selectedSmallCode = '';
                        vm.selectedDetailCode = '';
                    } else if (list[0].depth == 4  && paramDepth == 4) {
                        vm.listDetailCode = list;
                        vm.selectedDetailCode = '';
                    } 
                }
            }).
            catch(function(ex) {
                console.log(ex);
            });
        }

        ,fnOnClickCategory:function(){
            let vm = this;
            if(vm.selectedSearchCtgId==''||vm.selectedSearchCtgId==null||vm.selectedSearchDepth<4){
                alert('소분류까지 필수 선택사항 입니다.');
                return;
            }else{
                $('#paramCtgId').val(vm.selectedSearchCtgId);
                $('#paramDepth').val(vm.selectedSearchDepth);
                $('#categorySearchForm').submit();
                //window.open('/advis/search/category/list/'+vm.selectedSearchCtgId+'/'+vm.selectedSearchDepth ,'_blank');
            }
        }



    }
});