import Vue from 'vue'
import Axios from 'axios'
import utilMap from '../commons/util-openlayers'
import Chart from "chart.js"

var vue=new Vue({
  el: '#scope-report-neq'
  ,data:function(){
    return {
      sysCodes:[]
      ,isWrite:false
      ,inputOrg:""
      ,map:null
      ,currentTypMap: {}
      ,message: ''
      ,isUsed:false
      ,chartObj:[]

      ,link: null
      ,node: null
      ,t:null
      ,map:[]
      ,raster:new ol.layer.Tile({source : new ol.source.OSM()})
      ,chartObj:[]
      ,modal:{title:'',contents:[],evtDay:'',damageName:''}

      ,summary:{
         enventList:[]
        ,checkedKmaReport:false
        ,checkedAftershocks:''
        ,category_name: ''
        ,death:0
        ,missing:0
        ,injury:0
        ,area:''
        ,disEvent:''
        ,createDateTime:''
        ,selectedEvent:{evt_group:''}
        ,selectedEventGroup:''
        ,selectedOrg:''
        ,paramStatus:''
      }
      ,viewModel: {
        listNews:[]
        ,listEventActionKeyword:{size:0}
        ,listDamages:[]
        ,listOrgYearDme: []

        ,selected:{
            damageEq:'',
            damageBegDate: '',
            damageEndDate: '',
            damageOccurScale: 0,
            damageAfterCount: 0,
        },
        eventInfo:{
            locations:[],
            firstEq:null,
            listDailyEventInfo:[]
        },

      }  
      , eventName: ''
       , depth2: []
       , depth3: []
       , depth4: []
       , addedListActions:[]
       , eventItemList: []

       ,listDisEventType: []
       ,selectedDisEventType: 'NEQ'
       ,listDisEventIsCs_y: []
    };
  }
  , mounted: function () {
    try {
      pageLoad(this);
      this.map.push(this.fnCreateBaseMap("map",7,[127,36]));
  
    } catch (e) {
      console.log(e);
    }
  }
  , methods:{

    fnListDisEvents:function(paramCtgId){
        let vm=this;
        Axios.post("/api/std/event/list",{
            paramCtgId:paramCtgId
            ,paramIsRoot:'Y'
            ,paramIsCs:'N'
        }).then(function(response){
            vm.summary.eventList=response.data.eventList;
            if(vm.summary.eventList!=null && vm.summary.eventList.length>0){
            vm.summary.selectedEvent=vm.summary.eventList[0];
            vm.summary.selectedEventGroup=vm.summary.selectedEvent.evt_group;
            vm.fnChangeEventGrp();
            }
        }).catch(function (e){
            console.log(e);
        });
        
        }
    ,fnListCategories: function(paramCtgId, paramEvtId){
        let vm = this;
        vm.categoryList = [];
        vm.detailList = [];

        Axios.post("/advis/code/tree/category/list", {paramCtgId:paramCtgId})
        .then(function (response) {
            vm.sysCodes=response.data.codes;
            for(let i=0;i<vm.sysCodes.length;i++){
            vm.sysCodes[i].checked=false;
            }
            vm.categoryList = response.data.list;
            vm.fnSetDepthList(paramEvtId);
        }).catch(function (e){
        console.log(e);
        } );
    }
    
    , fnSetDepthList: function(paramEvtId){
        let vm = this;
        let list = this.categoryList;

        vm.depth1 = [];
        vm.depth2 = [];
        vm.depth3 = [];
        vm.depth4 = [];

        for(var i=0; i<list.length; i++){
            list[i].value="";
            if(list[i].depth == 1) {
                vm.depth1.push(list[i]);
            }else if(list[i].depth == 2){
                vm.depth2.push(list[i]);
            }else if(list[i].depth == 3){
                if(list[i].print_id=='50000'){
                    list[i].listActions=[
                        {no:1,grp:0,title:"일시",ctg_id:"act_date",value:''}
                    ,{no:2,grp:0,title:"기관명",ctg_id:"dis_org",value:''}
                    ,{no:3,grp:0,title:"전파",ctg_id:"act_lvl1",value:''}
                    ,{no:4,grp:0,title:"점검",ctg_id:"act_lvl2",value:''}
                    ,{no:5,grp:0,title:"지시",ctg_id:"act_lvl3",value:''}
                    ,{no:6,grp:0,title:"파견",ctg_id:"act_lvl4",value:''}
                    ,{no:7,grp:0,title:"회의",ctg_id:"act_lvl5",value:''}
                    ,{no:8,grp:0,title:"대피",ctg_id:"act_lvl6",value:''}
                    ];
                }else{
                    list[i].listActions=[];
                }
                vm.depth3.push(list[i]);
                }else{
                if(list[i].depth == 4) {
                    vm.depth4.push(list[i]);
                }
            }
        }
        if(paramEvtId!=''&&paramEvtId!=undefined) vm.fnViewSelectedEvtId(paramEvtId);

    }

    ,fnOnClickBtnAddItem:function(item,title,date,lvl1,lvl2,lvl3,lvl4,lvl5,lvl6) {
      
        if(item.depth==3){
          
          let temp={depth:3,grp:0,listActions:[]};
          if(this.addedListActions.length>0){
           temp.grp= this.addedListActions[this.addedListActions.length-1].grp+1;
          }
          temp.listActions=[
            {no:1,title:"일시",ctg_id:"act_date",value:date!=undefined?date:''}
           ,{no:2,title:"기관명",ctg_id:"dis_org",value:title!=undefined?title:''}
           ,{no:3,title:"전파",ctg_id:"act_lvl1",value:lvl1!=undefined?lvl1:''}
           ,{no:4,title:"점검",ctg_id:"act_lvl2",value:lvl2!=undefined?lvl2:''}
           ,{no:5,title:"지시",ctg_id:"act_lvl3",value:lvl3!=undefined?lvl3:''}
           ,{no:6,title:"파견",ctg_id:"act_lvl4",value:lvl4!=undefined?lvl4:''}
           ,{no:7,title:"회의",ctg_id:"act_lvl5",value:lvl5!=undefined?lvl5:''}
           ,{no:8,title:"대피",ctg_id:"act_lvl6",value:lvl6!=undefined?lvl6:''}
          ];
          this.addedListActions.push(temp);
        }
        
      }
    ,fnOnClickBtnDeleteItem:function(item) {
        
        let temp=[];
        if(item.depth==3){
          for(let i=0;i<this.addedListActions.length;i++){
              
              if(this.addedListActions[i].grp!=item.grp){
                temp.push(this.addedListActions[i]);
              }
          }
        }
        this.addedListActions=temp;
    }
    ,fnOnClickBtnSummary:function(){
        this.isWrite=false;
    }
    ,fnOnClickBtnWrite:function(){
        this.summary.selectedOrg="";
        let rsArr=[];
        let target=[];
        this.addedListActions=[];
        this.isUsed=false;
  
        if(this.fnCheckDate(this.summary.createDateTime)==false){
          alert('날짜형식 오류입니다.');
          return false;
        }else{
          this.isWrite=true;
        }
  
        for(let i=0; i<this.depth4.length; i++){
          this.depth4[i].value = '';
        }
  
        for(let i=0;i<this.sysCodes.length;i++){
          let item=this.sysCodes[i];
          if(item.checked){
            rsArr.push(item.sys_title);
          }
        }
        if(rsArr.length>0){
          this.summary.selectedOrg=rsArr.join();
        }
        if(this.inputOrg!=""){
          this.summary.selectedOrg+=","+this.inputOrg;
        }
        
        let rsSplit=this.summary.selectedOrg.split(",");
        for(let i=0;i<rsSplit.length;i++){
          if(rsSplit[i]=="") continue;
          let isUse=false;
          for(let k=0;k<this.addedListActions.length;k++){
            let action=this.addedListActions[k];
            for(let z=0;z<action.listActions.length;z++){
              if(action.listActions[z].value==rsSplit[i]){
                isUse=true;
                break;
              }
            }
          }
          if(!isUse) {
            target.push({item:null,title:rsSplit[i]});
          }
        }
       
        for(let i=0;i<target.length;i++){
            this.fnOnClickBtnAddItem({depth:3},target[i].title);
        }
        
        this.fnGetListEarthquakeDamage();
    }
  
    ,fnCreateMap:function(){
        let raster=new ol.layer.Tile({
          source : new ol.source.OSM()
        });
        this.map = new ol.Map({
          layers : [ raster],
          target : 'rain-map',
          view : new ol.View({
              center : ol.proj.fromLonLat([ 128, 32 ]),
              zoom : 4
          })
      });
  
    }

    ,fnViewSelectedEvtId: function(paramItem){
        let vm = this;
        if(paramItem==null||paramItem==''||paramItem==undefined) return false;
        this.isWrite=true;
        this.isUsed=true;
        $("#loading-layer").show();
  
  
        Axios.post("/api/advis/dis/event/report/detail", {
          paramEvtId: paramItem
        }).
        then(function(response) {
          vm.summary.selectedEvent = response.data.resultEventInfo;
          let eventItemList = response.data.eventItemList;
          let eventActionList = response.data.resultList;

          vm.summary.selectedEventGroup = vm.summary.selectedEvent.evt_group;
          vm.summary.createDateTime = vm.summary.selectedEvent.evt_date;
          vm.summary.area = vm.summary.selectedEvent.area_codes;
          vm.summary.paramStatus = vm.summary.selectedEvent.dis_level_code;
          vm.summary.death = vm.summary.selectedEvent.com_1;
          vm.summary.missing = vm.summary.selectedEvent.com_2;
          vm.summary.injury = vm.summary.selectedEvent.com_3;
  
          for(let i=0; i<eventItemList.length; i++){
            
            for(let j=0; j<vm.depth4.length; j++){
              if(eventItemList[i].ctg_id==vm.depth4[j].ctg_id){
                vm.depth4[j].value = eventItemList[i].contents.replace(/<br>/g, '\n');
              }
            }
          }

          //관련기관 setting
          for(let i=0;i<vm.sysCodes.length;i++){
            for(let j=0; j<eventActionList.length; j++){
              if(vm.sysCodes[i].sys_title == eventActionList[j].dis_org){
                vm.sysCodes[i].checked=true;
                eventActionList[j].use_yn = 'Y';
              }
            }
          }
          for(let i=0; i<eventActionList.length; i++){
            if(eventActionList[i].use_yn != 'Y'){
              if(vm.inputOrg==''){
                vm.inputOrg = eventActionList[i].dis_org;
              }else{
                vm.inputOrg = ',' + eventActionList[i].dis_org;
              }
            }
            vm.fnOnClickBtnAddItem({depth:3},eventActionList[i].dis_org,eventActionList[i].dis_act_date,eventActionList[i].dis_act_lv1,eventActionList[i].dis_act_lv2,
              eventActionList[i].dis_act_lv3,eventActionList[i].dis_act_lv4,eventActionList[i].dis_act_lv5,eventActionList[i].dis_act_lv6);
          }
  
          vm.fnChangeEventGrp();
          vm.fnGetListEarthquakeDamage();
  
        }).catch(function(ex) {
          $("#loading-layer").hide();
            console.log(ex);
        });
  
    }

    ,fnChangeEventGrp: function(){
        let vm = this;
        for(let i=0; i<vm.summary.eventList.length; i++){
          if(vm.summary.eventList[i].evt_group == vm.summary.selectedEventGroup){
            vm.summary.selectedEvent = vm.summary.eventList[i];
          }
        }
    }

    ,fnGetDaumNews: function() {
        let vm = this;
        vm.viewModel.listNews = [];
        
        let paramQueryObj = { text: vm.viewModel.selected.damageBegDate.substring(0,4) + '년 지진', sort: 'accuracy', size: 50, page: 1 };
        Axios.post("/api/kakao/get/search", {
            paramUrl: 'https://dapi.kakao.com/v2/search/web',
            paramKey: 'dd615544fe7d753ebfabf05ad473f6bf',
            paramQuery: paramQueryObj.text + ' 재난 재해 news 피해 대응 복구',
            paramSort: paramQueryObj.sort,
            paramSize: paramQueryObj.size,
            paramPage: paramQueryObj.page
        })
        .then(function(response) {
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

            let list=[];
            for(let i=0; i<vm.viewModel.listNews.length; i++){
            if(vm.viewModel.listNews[i].datetime.substring(0,4) == vm.summary.createDateTime.substring(0,4)){
                list.push(vm.viewModel.listNews[i]);
            }
            }

            let news = [];
            for(let i=0; i<10; i++){
            if(list.length-1 > i){
                news.push(list[i]);
            }else{
                news.push(vm.viewModel.listNews[i]);
            }
            }  
            vm.viewModel.listNews = news;

        })
        .catch(function(ex) {
            $("#loading-layer").hide();
            console.log(ex);
        });
        
    }

    ,fnCheckDate: function(paramDate){
        let dateRegex = /^[0-9]{10,12}$/;
        let yearRegex = /^(19|20)\d{2}$/;
        let monthRegex = /^(0[1-9]|1[012])$/;
        let dayRegex = /^(0[1-9]|[12][0-9]|3[0-1])$/;
        let hourRegex = /^(0[1-9]|1[0-9]|2[0-3])$/;
  
        let year = paramDate.substring(0,4);
        let month = paramDate.substring(4,6);
        let day = paramDate.substring(6,8);
        let hour = paramDate.substring(8,10);
  
        if(dateRegex.test(paramDate)&&yearRegex.test(year)&&monthRegex.test(month)&&dayRegex.test(day)&&hourRegex.test(hour)){
          return true;
        }else{
          return false;
        }
    }

    ,fnOnClickBtnSave: function(){
        let vm = this;
        let paramList=[];
        let actionList = [];
  
        for(let i=0; i<vm.depth4.length; i++){
          if(vm.depth4[i].value!=''){
            paramList.push($.stringFormat("{0}|{1}",vm.depth4[i].ctg_id, vm.depth4[i].value));
          }
        }
  
        for(let i=0; i<vm.addedListActions.length; i++){
          let actionData = vm.addedListActions[i].listActions;
          
          paramList.push($.stringFormat("{0}|{1}", 'NEQ50500005000010', actionData[0].value));
          paramList.push($.stringFormat("{0}|{1}", 'NEQ50500005000020', actionData[1].value));
          paramList.push($.stringFormat("{0}|{1}", 'NEQ50500005000050', actionData[2].value+'\r\n'+actionData[3].value+'\r\n'+actionData[4].value
          +'\r\n'+actionData[5].value+'\r\n'+actionData[6].value+'\r\n'+actionData[7].value));
  
          actionList.push($.stringFormat("{0}|{1}|{2}|{3}|{4}|{5}|{6}|{7}", actionData[0].value, actionData[1].value, actionData[2].value, actionData[3].value, 
          actionData[4].value, actionData[5].value, actionData[6].value, actionData[7].value));
        }
  
        // for(let i=0; i<vm.viewModel.listObsRn.length; i++){
        //   let obsData = vm.viewModel.listObsRn[i].list;
  
        //   if(obsData[0].value!='')paramList.push($.stringFormat("{0}|{1}", 'NEQ10102001020010', obsData[0].value));
        //   if(obsData[1].value!='')paramList.push($.stringFormat("{0}|{1}", 'NEQ10102001020020', obsData[1].value));
        //   if(obsData[2].value!='')paramList.push($.stringFormat("{0}|{1}", 'NEQ10102001020030', obsData[2].value));
        // }
        
        if(paramList.length>0){
          Axios.post("/api/advis/dis/event/report/create",{
            paramEvtGrp: vm.summary.selectedEventGroup
            ,paramTitle: $("#dis_title option:selected").text()
            ,paramDisType: vm.selectedDisEventType
            ,paramDisDateStr: vm.summary.createDateTime
            ,paramIsRoot:'N'
            ,paramIsCs:'Y'
            ,paramCom_1: vm.summary.death
            ,paramCom_2: vm.summary.missing
            ,paramCom_3: vm.summary.injury
            ,paramAreaCodes: vm.summary.area
            ,paramStatus: vm.summary.paramStatus
            ,paramEvents: paramList
            ,paramActionList: actionList
          }).then(function(response){
            
            if(response.data.resultCount>0){
              alert("저장되었습니다.");
            }
  
          }).catch(function (e){
            console.log(e);
          });
        }
  
    }


    ,fnGetListEarthquakeDamage: function(){
        let vm = this;
        let item = '';

        vm.viewModel.selected={damageEq: '',damageBegDate: '',damageEndDate: '',damageOccurScale: 0,damageAfterCount: 0};
        
        Axios.post("/api/statistics/earthquake/damage/list",{
             paramDamageCode:vm.selectedDisEventType
        })
        .then(function(response){

            vm.viewModel.listDamages=response.data.listDamages;
            if(response.data.listDamages!=null){
              //이전 현황 
              for(let i=0; i<vm.viewModel.listDamages.length; i++){
                  if(item.beg_date<=vm.summary.createDateTime.substring(0,8) && item.end_date>=vm.summary.createDateTime.substring(0,8)){
                      if(item==''){
                        if(i==0){
                          vm.fnOnChangeSelectedDme(vm.viewModel.listDamages[i]); 
                        }else{
                          vm.fnOnChangeSelectedDme(vm.viewModel.listDamages[i-1]); 
                        }
                      }
                  }
              }

              if(item=='') vm.fnOnChangeSelectedDme(vm.viewModel.listDamages[0]); 
            }
            
            $("#loading-layer").hide();
            
        })
        .catch(function(ex){
            console.log(ex);
            $("#loading-layer").hide();
        });
    }

    ,fnOnChangeSelectedDme: function(paramSelectedItem){
        let vm = this;
        vm.viewModel.selected.damageEq = paramSelectedItem;
        vm.viewModel.selected.damageBegDate = paramSelectedItem.beg_date;
        vm.viewModel.selected.damageEndDate = paramSelectedItem.end_date;
        
        vm.fnGetDaumNews();
        vm.fnGetEarthquakeInfo();
        vm.fnListSigunguDamages();
        // vm.fnGetListDisasterManual();
        // vm.fnGetListEvent();

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
          //setTimeout(function(){ vm.fnListStatistics();},0);

      })
      .catch(function(ex){
          console.log(ex);
          $("#loading-layer").hide();
      });
    }

    ,fnGetEarthquakeInfo:function(){
        let vm=this;
        vm.viewModel.eventInfo.firstEq='';

        Axios.post("/api/advis/dis/event/location/list",{
            paramDisType: vm.selectedDisEventType
           ,paramStartDate: vm.viewModel.selected.damageBegDate
           ,paramEndDate: vm.viewModel.selected.damageEndDate
        }).then(function(response){

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
    }

    ,fnMapLayerInit:function(){
      for(let i=0;i<this.map.length;i++){
         let _map=this.map[i];
         let _layers=_map.getLayers();
         let removeTarget=[];
          _layers.forEach(function(layer){
              if(layer.get('name')!=undefined){
                 if(layer.get('name').indexOf("eq-")>-1){
                     removeTarget.push(layer);
                 }
              }
          });
          removeTarget.forEach(function(layer){
              _map.removeLayer(layer);
          });
      }
    }

    ,fnCreateBaseMap: function(paramTargetId,paramZoom,paramCenterPosition){
            
      let vm=this;
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

    //반경표시
    ,fnCreateEqRadius:function(){
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
      let item=this.viewModel.eventInfo.firstEq;
      let featureInfo=utilMap.fnCreatePoint({lon:parseFloat(item.evt_lon),lat:parseFloat(item.evt_lat)});
      featureInfo.pw=item.evt_power;
      featureInfo.contents=item.contents;
      features.push(featureInfo);

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
      //this.map[1].getView().setCenter(ol.proj.fromLonLat([parseFloat(this.viewModel.eventInfo.firstEq.evt_lon),parseFloat(this.viewModel.eventInfo.firstEq.evt_lat)]));
      //this.map[1].getView().setZoom(12);
      //this.map[1].addLayer(vectorLayer);
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


  }
})