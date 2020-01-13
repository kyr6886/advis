import Vue from 'vue'
import Axios from 'axios'

var vue=new Vue({
  el: '#scope-report-vai'
  ,data:function(){
    return {
      sysCodes:[]
      ,isWrite:false
      ,inputOrg:""
      ,map:null
      ,currentTypMap: {}
      ,message: ''
      ,isUsed:false

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
        ,selectedDetailKmaInform:{stat_tm_ef:''}
        ,selectedTypInfo: { typ_name:'', typ_lat:'', typ_lon:'', typ_ws:'', typ_ps:'', typ_sp:'' }
        ,selectedTypPw:''
      }
      ,viewModel: {
        listNews:[]
        ,listEventActionKeyword:{size:0}
      }  
      , eventName: ''
       , depth2: []
       , depth3: []
       , depth4: []
       , addedListActions:[]
       , eventItemList: []

       ,listDisEventType: []
       ,selectedDisEventType: 'VAI'
       ,listDisEventIsCs_y: []
    };
  }
  , mounted: function () {
    try {
      pageLoad(this);
  
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
  
          let rainItem = [];
          for(let i=0; i<eventItemList.length; i++){
            
            if(eventItemList[i].ctg_id.indexOf('10200')>-1){
              rainItem.push(eventItemList[i]);
            }
  
            for(let j=0; j<vm.depth4.length; j++){
              if(eventItemList[i].ctg_id==vm.depth4[j].ctg_id){
                vm.depth4[j].value = eventItemList[i].contents;
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
        
        let paramQueryObj = { text: '지진', sort: 'accuracy', size: 50, page: 1 };
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
          
          paramList.push($.stringFormat("{0}|{1}", 'VAI50500005000010', actionData[0].value));
          paramList.push($.stringFormat("{0}|{1}", 'VAI50500005000020', actionData[1].value));
          paramList.push($.stringFormat("{0}|{1}", 'VAI50500005000050', actionData[2].value+'\r\n'+actionData[3].value+'\r\n'+actionData[4].value
          +'\r\n'+actionData[5].value+'\r\n'+actionData[6].value+'\r\n'+actionData[7].value));
  
          actionList.push($.stringFormat("{0}|{1}|{2}|{3}|{4}|{5}|{6}|{7}", actionData[0].value, actionData[1].value, actionData[2].value, actionData[3].value, 
          actionData[4].value, actionData[5].value, actionData[6].value, actionData[7].value));
        }
  
        for(let i=0; i<vm.viewModel.listObsRn.length; i++){
          let obsData = vm.viewModel.listObsRn[i].list;
  
          if(obsData[0].value!='')paramList.push($.stringFormat("{0}|{1}", 'VAI10102001020010', obsData[0].value));
          if(obsData[1].value!='')paramList.push($.stringFormat("{0}|{1}", 'VAI10102001020020', obsData[1].value));
          if(obsData[2].value!='')paramList.push($.stringFormat("{0}|{1}", 'VAI10102001020030', obsData[2].value));
        }
        
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


  }
})