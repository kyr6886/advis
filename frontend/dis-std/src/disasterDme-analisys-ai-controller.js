import Vue from 'vue'
import Axios from 'axios'
import utilMap from '../commons/util-openlayers'
import Chart from "chart.js"
var vue = new Vue({

    el: '#scope-anl-ai',
    data: function() {
        return {
            map:[]
            ,chartObj:[]
            ,modal:{title:'',contents:[],evtDay:'',damageName:''}
            
            ,viewModel:{
                listEventLocations:[]
                ,listDamages:[]
                ,listHab:[]
                ,selected:{
                    damageBegDate:''
                    ,damageEndDate:''
                    ,dongCount:0
                    ,farmCount:0
                    ,headCount:0
                }
                ,listActionManual: []
                ,eventAction: ''
                ,eventList: []
                ,keyListView: []
                ,orgList: []
                ,eventList: []
                ,listActionManual: []
                ,listDailyCnt: []
            }
            
        }
    }
    ,mounted: function() {
        this.fnListEventLocations();
        this.fnGetListDisasterManual();
    }
    ,methods: {
        fnListEventLocations:function(){
            let vm=this;
            Axios.post("/api/advis/dis/event/location/list",{
                paramDisType: "VAI"
                ,paramEndDate: "20170720"
                ,paramStartDate: "20170605"
            }).then(function(response){
                let _listEventLocations=response.data.listEventLocation;
                let firstAccur={lon:0,lat:0};
                let firstAccurText="";
                if(_listEventLocations!=null){
                    for(let i=0;i<_listEventLocations.length;i++){
                        _listEventLocations[i].text="";
                       if(i==0) {
                           _listEventLocations[i].number=1;
                           firstAccurText=_listEventLocations[i].evt_date.dateFormat()+" 최초 발생";
                           firstAccur.lon=parseFloat(_listEventLocations[i].evt_lon);
                           firstAccur.lat=parseFloat(_listEventLocations[i].evt_lat);
                        }
                       else{
                          if(_listEventLocations[i-1].evt_date==_listEventLocations[i].evt_date){
                            _listEventLocations[i].number=_listEventLocations[i-1].number;
                          }else{
                            _listEventLocations[i].number=_listEventLocations[i-1].number+1;
                          }
                       }

                    }
                    vm.viewModel.listEventLocations=_listEventLocations;
                    vm.viewModel.selected.damageBegDate=vm.viewModel.listEventLocations[0].evt_date;
                    vm.viewModel.selected.damageEndDate=vm.viewModel.listEventLocations[vm.viewModel.listEventLocations.length-1].evt_date;
                 
               
                    vm.fnAddrCount();
                    vm.map.push(vm.fnCreateBaseMap("map",7,[127,36]));
                    let param=vm.fnGetMovePoint();
                    vm.fnCreateFirstMoveLine(param);
                    vm.fnCreateSecondMoveLine(param);
                    vm.fnPutAiPosition();
                    utilMap.fnCreatePopup(vm.map[0],{id:'popup',contentId:'popup-content'},firstAccurText,firstAccur);
                    vm.fnFindBirdRoot();

                    vm.fnGetListEvent(); //event list
                }

            }).catch(function(ex){
                console.log(ex);
            });
        }
        ,fnFindBirdRoot:function(){
            let vm=this;
            Axios.post("/api/vworld/find/bird/root",{})
            .then(function(response){
                
                if(response.data.response!=null && response.data.response!=""){
                    let responseObj=JSON.parse(response.data.response);
                    let listFindResult=responseObj[0].response.result.items;
                    listFindResult.forEach(item => {
                        if(item.category.indexOf("자연환경")>0){
                            vm.viewModel.listHab.push(item);
                        }   
                        
                    });
                    vm.fnCreateHabPosiotion();
                }
            })
            .catch(function(ex){
                console.log(ex);
            });
        }
        ,
        fnCreateHabPosiotion:function(){
            let list=this.viewModel.listHab;
            let birdFeatures=[];
            let circleFeatures=[];
            list.forEach(item => {
                let point={lon:parseFloat(item.point.x),lat:parseFloat(item.point.y)};
                let iconFeature=utilMap.fnCreatePoint(point);
                iconFeature.imagePath="/contents/advis/images/ic-bird.png";
                
                birdFeatures.push(iconFeature);
                
                let circleFeature=utilMap.fnCreateCircle(point,100000);
                circleFeature.imagePath=null;
                circleFeatures.push(circleFeature);
            });
             // Source and vector layer
             let birdVectorSource = new ol.source.Vector({
                projection: 'EPSG:4326',
                features: birdFeatures
            });
            
            let birdVVectorLayer = new ol.layer.Vector({
                name: "ai-hab",
                source:birdVectorSource,
                style:utilMap.fnStyleIcon
            });
            // Source and vector layer
            let circleVectorSource = new ol.source.Vector({
                projection: 'EPSG:4326',
                features: circleFeatures
            });
            
            let circleVectorLayer = new ol.layer.Vector({
                name: "ai-hab-circle",
                source:circleVectorSource,
                style: new ol.style.Style({
                    fill: new ol.style.Fill({
                        color: 'rgba(255, 100, 50, 0.1)'
                    }),
                    stroke: new ol.style.Stroke({
                        width: 2,
                        color: 'rgba(255, 100, 50, 1)'
                    })})
            });
            this.map[0].addLayer(circleVectorLayer);
            this.map[0].addLayer(birdVVectorLayer);
        }
        ,fnCreateFirstMoveLine:function(paramPositions){
            let lineFeatures=[];
            let paramSecPositions={lon:paramPositions[1].lon,lat:paramPositions[1].lat};
            lineFeatures.push(utilMap.fnCreateLine(
                paramPositions[0]
                ,paramSecPositions
               ,"rgba(102,153,255,0.5)"
               ,20
               ));
        
            let lineVectorSource = new ol.source.Vector({
               projection: 'EPSG:4326',
               features: lineFeatures
           });
           let lineVectorLayer = new ol.layer.Vector({
               name: "ai-move-line",
               source: lineVectorSource,
               style: utilMap.fnStyleArrow
           });
           this.map[0].addLayer(lineVectorLayer);
        }
        ,fnCreateSecondMoveLine:function(paramPositions){
            let lineFeatures=[];
            let paramSecPositions={lon:paramPositions[2].lon,lat:paramPositions[3].lat};
            lineFeatures.push(utilMap.fnCreateLine(
                paramPositions[2]
                ,paramSecPositions
               ,"rgba(102,153,255,0.5)"
               ,20
               ));
        
            let lineVectorSource = new ol.source.Vector({
               projection: 'EPSG:4326',
               features: lineFeatures
           });
           let lineVectorLayer = new ol.layer.Vector({
               name: "ai-move-line",
               source: lineVectorSource,
               style: utilMap.fnStyleArrow
           });
           this.map[0].addLayer(lineVectorLayer);
        }
        ,fnOnChangeSelectedDme:function(paramItem){

        }
        //읍면동 수
        ,fnAddrCount:function(){
            let addr=[];
            for(let i=0;i<this.viewModel.listEventLocations.length;i++){
                let item=this.viewModel.listEventLocations[i];
                if(addr.indexOf(item.addr)<0){
                    addr.push(item.addr);
                }
            }
            this.viewModel.selected.dongCount=addr.length;
        },
        //농장수
        fnAddrFarmCount:function(){

        }
        //살처분수
        ,fnAddrHeadCount:function(){
         
        }
        ,fnCreateBaseMap:function(paramTargetId,paramZoom,paramCenterPosition){
            let raster=new ol.layer.Tile({
                source: new ol.source.XYZ({
                    //Vworld Tile 변경
                    url: 'http://xdworld.vworld.kr:8080/2d/gray/201802/{z}/{x}/{y}.png'
                })
              
            });
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
        ,fnPutAiPosition:function(){
            let vm=this;
            let features=[];
            let heatMapPoint=[];
            this.viewModel.listEventLocations.sort(function(a,b){return b.text-a.text;});
            for(let i=0;i<this.viewModel.listEventLocations.length;i++){
                let item=this.viewModel.listEventLocations[i];
                let pos={lon:parseFloat(item.evt_lon),lat:parseFloat(item.evt_lat)};
                heatMapPoint.push(pos);
                let _feature=utilMap.fnCreatePoint(pos,5);
                _feature.text=item.text;
                features.push(_feature);
                
            }
        
            let style =function(feature,resolution){
                
                let _text=feature.text.toString();
                _text="";
                
                return  new ol.style.Style({
            
                image: new ol.style.Circle({
                    fill: new ol.style.Fill({
                        color: 'rgba(255, 100, 50, 0.8)'
                    }),
                    stroke: new ol.style.Stroke({
                        width: 2,
                        color: 'rgba(255, 100, 50, 0.8)'
                    }),
                    radius: 3
                }),
                text: new ol.style.Text({
                    font: '16px helvetica,sans-serif',
                    text:_text,
                    fill: new ol.style.Fill({color: 'black'}),
                    stroke: new ol.style.Stroke({color: 'white', width:2})
                })
            });
            };

              // Source and vector layer
              let vectorSource = new ol.source.Vector({
                projection: 'EPSG:4326',
                features: features
            });
            let vectorLayer = new ol.layer.Vector({
                name: "ai-location",
                source: vectorSource,
                style: style
            });
            this.map[0].addLayer(utilMap.fnCreateHeatMap(heatMapPoint));
            this.map[0].addLayer(vectorLayer);
            
        },
        fnGetMovePoint:function(){
            let rs=[];
            let temp=[];
            for(let i=0;i<this.viewModel.listEventLocations.length;i++){
                let item=this.viewModel.listEventLocations[i];
                if(temp.indexOf(item.number)<0){
                    temp.push(item.number);
                    rs.push({lon:parseFloat(item.evt_lon),lat:parseFloat(item.evt_lat)});
                }
            }
            return rs;
        }

        ,fnGetListEvent: function(){
            let vm = this.viewModel;
            let _this = this;

            Axios.post("/api/advis/dis/event/groupid/info",{
                paramCtgId: "VAI"
                ,paramStartDate: "20170605"
                ,paramEndDate: "20170720"
                ,paramTitle:null
            })
            .then(function(response){
                
                vm.eventList = response.data.eventList;
                vm.listDailyCnt = [];
                let today_fm = 0;
                let today_di = 0;
                let ydCnt_fm = 0;
                let ydCnt_di = 0;

                for (let i = 0; i < vm.eventList.length; i++) {
                    let tempData = {evt_date:'', fmCnt:0, diCnt:0};
                    tempData.evt_date = vm.eventList[i].evt_date;
                    
                    for (let j = 0; j < vm.eventList[i].eventItemList.length; j++) {
                        if (vm.eventList[i].eventItemList[j].ctg_id.indexOf('40020') > -1) { //살처분실적_누계
                            let arr = vm.eventList[i].eventItemList[j].contents.split('/');

                            if(arr.length>0){
                                let regExp =  /[^0-9]/gi;
                                if(arr[0]!=null){ //발생농장횟수
                                    if(regExp.test(arr[0])){
                                        tempData.fmCnt = arr[0].replace(regExp, '');
                                        today_fm = tempData.fmCnt;
                                    }
                                }
                                if(arr[1]!=null){ //살처분횟수
                                    if(regExp.test(arr[1])){
                                        tempData.diCnt = arr[1].replace(regExp, '');
                                        today_di = tempData.diCnt;
                                    }
                                }
                            }

                            if(vm.listDailyCnt.length==0){
                                ydCnt_fm = tempData.fmCnt;
                                ydCnt_di = tempData.diCnt;
                                vm.listDailyCnt.push(tempData);
                                break;
                            }else{
                                if(tempData.fmCnt!=null){
                                    if( (tempData.fmCnt - ydCnt_fm) >= 0){
                                        tempData.fmCnt = today_fm - ydCnt_fm;
                                        ydCnt_fm = today_fm;
                                    }
                                }
                                if(tempData.diCnt!=null){
                                    if( (tempData.diCnt - ydCnt_di) >= 0){
                                        tempData.diCnt = today_di - ydCnt_di;
                                        ydCnt_di = today_di;
                                    }
                                }

                                vm.listDailyCnt.push(tempData);
                                break;
                            }
                        }
                    }
                    
                    
                }

                //chart
                let dailyChart={labels:[],data:[]};
                let pwChart={labels:[],data:[]};
                
                for(let i=0;i<vm.listDailyCnt.length;i++){
                    let item=vm.listDailyCnt[i];
                    if(dailyChart.labels.indexOf(item.evt_date.substring(4,8))==-1){
                        dailyChart.labels.push(item.evt_date.substring(4,8));
                    }
                    if(pwChart.labels.indexOf(item.evt_date.substring(4,8))==-1){
                        pwChart.labels.push(item.evt_date.substring(4,8));
                    }
                }
                for(let i=0;i<dailyChart.labels.length;i++){
                    for(let k=0;k<vm.listDailyCnt.length;k++){
                        let item=vm.listDailyCnt[k];
                        if(dailyChart.labels[i]==item.evt_date.substring(4,8)){
                            dailyChart.data.push(item.fmCnt);
                        }
                    }  
                }
                for(let i=0;i<pwChart.labels.length;i++){
                    for(let k=0;k<vm.listDailyCnt.length;k++){
                        let item=vm.listDailyCnt[k];
                        if(pwChart.labels[i]==item.evt_date.substring(4,8)){
                            pwChart.data.push(item.diCnt);
                        }
                    }  
                }
                let _chartOption = _this.fnCreateDefaultChartOption("일별 발생농장 횟수", "bar", dailyChart.labels, false);
                let _pwChartOption = _this.fnCreateDefaultChartOption("일별 살처분 횟수", "bar", pwChart.labels, false);
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
                _this.fnCreateChart("chart-daily",_chartOption);
                _this.fnCreateChart("chart-pw",_pwChartOption);
                //end chart

                
                _this.fnGetActionKeyword(response.data.resultList);
                _this.fnListEventTimeLine(); //대응현황 timeLine

            }).catch(function(ex){
                console.log(ex);
           });
        }

        ,fnGetListDisasterManual:function(){
            let vm=this;
            Axios.post("/disaster/manual/list/group", {
                paramCtgId: "VAI"
            })
            .then(function(response){
                vm.viewModel.listActionManual=response.data.resultManualList;
            })
            .catch(function(ex){
                $("#loading-layer").hide();
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
        }

        ,fnOnClickManualTitle:function(paramObj){
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
                //일자별 시간 리스트
                if (tempList.length > 0) {
                    let keyList = [];
                    for (let i = 0; i < tempList.length; i++) {
                        if (tempList[i].contentsTitle == '일시') {
                            let item = { day: '', printDay: '', detail: [] };
                            let arr = [];
                            let temp = '';
                            let regExp = /[\{\}\[\]\/?,;|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/gi;

                            if(tempList[i].contents != null){
                                if(regExp.test(tempList[i].contents)){
                                    temp = tempList[i].contents.replace(regExp, '');
                                }else{
                                    temp = tempList[i].contents;
                                }
                                temp = temp.replace('  ', ' ');
                                arr = temp.split(' ');
                                item.day = arr[0];
                                item.printDay = _this.fnConvertDay(arr[0]);
                                if(item.printDay!=null){
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
                        }
                    }
                    keyList = keyList.sort(function ascending(a, b) { return (a.printDay < b.printDay) ? -1 : (a.printDay == b.printDay) ? 0 : 1; });

                    for (let i = 0; i < keyList.length; i++) {
                        for (let j = 0; j < tempList.length; j++) {

                            if (tempList[j].contentsTitle == '일시') {
                                let arr = [];
                                let temp = '';
                                let regExp = /[\{\}\[\]\/?,;|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/gi;

                                if(tempList[j].contents != null){
                                    if(regExp.test(tempList[j].contents)){
                                        temp = tempList[j].contents.replace(regExp, '');
                                    }else{
                                        temp = tempList[j].contents;
                                    }
                                    temp = temp.replace('  ', ' ');
                                    arr = temp.split(' ');
    
                                    if (keyList[i].printDay == _this.fnConvertDay(arr[0])) { //같은날짜
                                        let cont = { hour: '', printHour: '', printDay:'', org: '', content: '', contentId: '' };
                                        cont.printDay = _this.fnConvertDay(arr[0]);
                                        cont.hour = arr[1] != null ? arr[1] : '00:00';
                                        cont.printHour = keyList[i].printDay + ' ' + cont.hour.replace("∼", "");

                                        if(cont.printDay!=null){
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
                        }
                    }
                    for (let i = 0; i < keyList.length; i++) {
                        keyList[i].detail = keyList[i].detail.sort(function ascending(a, b) { return (a.hour < b.hour) ? -1 : (a.hour == b.hour) ? 0 : 1; });
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
                                        if (keyList[i].detail[j].org != '' && keyList[i].detail[j].org != null) {
                                        //같은날짜,기관명 중복체크
                                            if (list[k].detail.length == 0) {
                                                list[k].detail.push(keyList[i].detail[j]);
                                            } else {
                                                let boo = true;
                                                for (let m = 0; m < list[k].detail.length; m++) {
                                                    if (keyList[i].detail[j].org == list[k].detail[m].org) {
                                                        list[k].detail[m].content = keyList[i].detail[j].content;
                                                        boo = false;
                                                    }
                                                }
                                                if (boo) list[k].detail.push(keyList[i].detail[j]);
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
                    }
                    for (let i = 0; i < list.length; i++) {
                        if (list[i].detail.length > 0) vm.keyListView.push(list[i]);
                    }
                }
            }
            console.log(vm.keyListView);
        }

        ,fnClickEventLink: function(paramObj) {
            this.modal={title:'',contents:[],evtDay:'',damageName:''};
            this.modal.title="대처사항";
            this.modal.evtDay=paramObj.printDay;
            if(paramObj.content!=''){
                paramObj.content = paramObj.content.replace(/\r\n/gi, "<br>").replace(/\n/gi, "<br>");
            }
            this.modal.contents.push({contentTitle:'',content:paramObj.content});
            $('#contentViewModal').modal('show');
        }

        ,fnConvertDay: function(paramVal) {
            paramVal = paramVal.replace(/\,/, "").trim();

            let arr = paramVal.split('~');
            if(arr.length>0) paramVal = arr[0];

            arr = paramVal.split('.');
            if(arr.length > 1){
                if (arr[0].length == 1) arr[0] = '0' + arr[0];
                if (arr[1].length == 1) arr[1] = '0' + arr[1];
                paramVal = arr[0] + '.' + arr[1];
                if(arr[1] > 31) paramVal = null;
            }
            return paramVal;
        }

        ,fnCreateChart:function(paramChartId,paramChartOption){
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
        }
        
        ,fnCreateDefaultChartOption: function(strChartTitle, strChartType, xData, isLegendView) {
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
                            label: function(args, data) {
                                return Intl.NumberFormat().format(args.yLabel);
                            }
                        }
                    }
                }
            };
            return _chartOption;
        }


    }
});