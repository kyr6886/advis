import Vue from 'vue'
import Axios from 'axios'

var vue=new Vue({
  el: '#scope-report'
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
        ,checkedRain:''
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
        detailKmaInform: [],
        maxDamageTyphoon: { typ_name: '' },
        maxDamageTyphoon_typName: '',
        maxDamageTyphoon_personDme: '',
        maxDamageTyphoon_moneyDme: {totalPub:0, totalPri:0},
        maxDamageTyphoon_sidoDme: '',
        maxDamageTyphoon_dmeFac: '',
        currentTyphoonInfo: { beg_date: '', end_date: '', typ_name: '', year: '', month: '' },
        listCurrentTyphoonPosition: [],
        listForecastTyphoonPath: [],
        listDamagesByKmaReport: [],
        listSimilarTyphoons: [],
        listTyphoonPosition: [],
        listTyphoonList:[],
        listNews:[],
        listStnAsos: [],
        listObsRn:[],
        listAllSimilarTyphoon:[],
        listActionManual:[],
        listEventActionKeyword:{size:0}
      }  
      , eventName: ''
       , depth2: []
       , depth3: []
       , depth4: []
       , addedListActions:[]
       , eventItemList: []

       ,listDisEventType: []
       ,selectedDisEventType: 'NTY'
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
      
      this.fnGetTyphoonList();
      
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
        
        paramList.push($.stringFormat("{0}|{1}", 'NTY50500005000010', actionData[0].value));
        paramList.push($.stringFormat("{0}|{1}", 'NTY50500005000020', actionData[1].value));
        paramList.push($.stringFormat("{0}|{1}", 'NTY50500005000050', actionData[2].value+'\r\n'+actionData[3].value+'\r\n'+actionData[4].value
        +'\r\n'+actionData[5].value+'\r\n'+actionData[6].value+'\r\n'+actionData[7].value));

        actionList.push($.stringFormat("{0}|{1}|{2}|{3}|{4}|{5}|{6}|{7}", actionData[0].value, actionData[1].value, actionData[2].value, actionData[3].value, 
        actionData[4].value, actionData[5].value, actionData[6].value, actionData[7].value));
      }

      for(let i=0; i<vm.viewModel.listObsRn.length; i++){
        let obsData = vm.viewModel.listObsRn[i].list;

        if(obsData[0].value!='')paramList.push($.stringFormat("{0}|{1}", 'NTY10102001020010', obsData[0].value));
        if(obsData[1].value!='')paramList.push($.stringFormat("{0}|{1}", 'NTY10102001020020', obsData[1].value));
        if(obsData[2].value!='')paramList.push($.stringFormat("{0}|{1}", 'NTY10102001020030', obsData[2].value));
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

    ,fnCheckDate: function(paramDate){
      let dateRegex = /^[0-9]{10,12}$/;
      let yearRegex = /^(19|20)\d{2}$/;
      let monthRegex = /^(0[1-9]|1[012])$/;
      let dayRegex = /^(0[1-9]|[12][0-9]|3[0-1])$/;
      let hourRegex = /^(0[1-9]|1[0-9]|2[0-3])$/;
      //let minRegex = /^(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9])$/;

      let year = paramDate.substring(0,4);
      let month = paramDate.substring(4,6);
      let day = paramDate.substring(6,8);
      let hour = paramDate.substring(8,10);
      // let min = paramDate.substring(10,12);

      if(dateRegex.test(paramDate)&&yearRegex.test(year)&&monthRegex.test(month)&&dayRegex.test(day)&&hourRegex.test(hour)){
        return true;
      }else{
        return false;
      }
    }

    ,fnChangeEventGrp: function(){
      let vm = this;
      for(let i=0; i<vm.summary.eventList.length; i++){
        if(vm.summary.eventList[i].evt_group == vm.summary.selectedEventGroup){
          vm.summary.selectedEvent = vm.summary.eventList[i];
        }
      }
    }

    ,fnGetTyphoonList: function(){
      let vm = this;
      let listTyphoonStrengthName = ["약", "중", "강", "매우 강"];
      
      $("#loading-layer").show();
      vm.message = '태풍 정보를 조회 중입니다.';
      vm.viewModel.listTyphoonList = [];
      vm.summary.selectedTypInfo = '';
      vm.summary.selectedTypName = '';
      vm.summary.selectedTypPw = '';

      //년,월 기간내 태풍list
      Axios.post("/api/period/typhoon/info.do",{
        paramStDate: vm.summary.createDateTime
      }).then(function(response){
        vm.viewModel.listTyphoonList = response.data.typhoonList;
        
        if (response.data.typhoonList != null && response.data.typhoonList.length > 0){
          let tempTypList = [];
          for(let i=0; i<vm.viewModel.listTyphoonList.length; i++){
            if(vm.summary.selectedEvent.title.indexOf(vm.viewModel.listTyphoonList[i].typ_name) > -1){
              tempTypList.push(vm.viewModel.listTyphoonList[i]); //해당 태풍 list
            }
          }

          if(tempTypList.length>0){

            for(let i=0; i<tempTypList.length; i++){
              //작성일자의 태풍정보
              if(tempTypList[i].tm_fc == vm.summary.createDateTime){
                vm.summary.selectedTypInfo = tempTypList[i];
                vm.summary.selectedTypInfo.typ_ws = tempTypList[i].typ_ws;
                if(i==tempTypList.length-1) vm.summary.selectedTypInfo.typ_ws = tempTypList[i-1].typ_ws;
              }
            }
            if(vm.summary.selectedTypInfo == ''){
              vm.summary.selectedTypInfo = tempTypList[tempTypList.length-1];
              vm.summary.selectedTypInfo.typ_ws = tempTypList[tempTypList.length-2].typ_ws;
            }
  
            //typoon 강도
            if(vm.summary.selectedTypInfo.typ_ws != null){
              if(vm.summary.selectedTypInfo.typ_ws > 0 && vm.summary.selectedTypInfo.typ_ws < 25){
                vm.summary.selectedTypPw = listTyphoonStrengthName[0];
              }else if(vm.summary.selectedTypInfo.typ_ws >= 25 && vm.summary.selectedTypInfo.typ_ws < 33){
                vm.summary.selectedTypPw = listTyphoonStrengthName[1];
              }else if(vm.summary.selectedTypInfo.typ_ws >= 33 && vm.summary.selectedTypInfo.typ_ws < 44){
                vm.summary.selectedTypPw = listTyphoonStrengthName[2];
              }else if(vm.summary.selectedTypInfo.typ_ws >= 44){
                vm.summary.selectedTypPw = listTyphoonStrengthName[3];
              }else{
                vm.summary.selectedTypPw = "-";
              }
            }else{
              vm.summary.selectedTypPw = "-";
            }
          }          
        }

        vm.fnGetDetailKmaReport();
        vm.fnGetTyphoonInfo(); 
        vm.fnGetObsRainList();

      }).catch(function (e){
        $("#loading-layer").hide();
        console.log(e);
      });
    }

    ,fnGetDetailKmaReport: function(){
      let vm = this;
      vm.summary.selectedDetailKmaInform={stat_tm_ef:''};

      //기상특보 상세
      if(vm.summary.selectedTypInfo!=''){
        
        Axios.post("/api/kma/report/detail.do",{
          paramDummyDate: vm.summary.createDateTime
        }).then(function(response){
          if(response.data.detailKmaInform==null || response.data.detailKmaInform.length==0) return false;
          vm.summary.selectedDetailKmaInform = response.data.detailKmaInform;

          if(!vm.isUsed){
            let data = vm.summary.selectedDetailKmaInform.stat_tm_ef;
            let arr = data.split('\n');

            for(let i=0; i<arr.length; i++){

              if(arr[i].indexOf('대설')>-1){
                for(let j=0; j<vm.depth4.length; j++){
                  if(vm.depth4[j].ctg_id == 'NTY10101001010010'){
                    if(vm.depth4[j].value==''){
                      vm.depth4[j].value = arr[i];
                    }else{
                      vm.depth4[j].value += '\n' + arr[i];
                    }
                  }
                }
              }else if(arr[i].indexOf('강풍')>-1){
                for(let j=0; j<vm.depth4.length; j++){
                  if(vm.depth4[j].ctg_id == 'NTY10101001010020'){
                    if(vm.depth4[j].value==''){
                      vm.depth4[j].value = arr[i];
                    }else{
                      vm.depth4[j].value += '\n' + arr[i];    
                    }
                  }
                }
              }else if(arr[i].indexOf('풍랑')>-1){
                for(let j=0; j<vm.depth4.length; j++){
                  if(vm.depth4[j].ctg_id == 'NTY10101001010030'){
                    if(vm.depth4[j].value==''){
                      vm.depth4[j].value = arr[i];
                    }else{
                      vm.depth4[j].value += '\n' + arr[i];    
                    }
                  }
                }
              }else if(arr[i].indexOf('태풍')>-1){
                for(let j=0; j<vm.depth4.length; j++){
                  if(vm.depth4[j].ctg_id == 'NTY10101001010040'){
                    if(vm.depth4[j].value==''){
                      vm.depth4[j].value = arr[i];
                    }else{
                      vm.depth4[j].value += '\n' + arr[i];    
                    }
                  }
                }
              }else if(arr[i].indexOf('낙뢰')>-1){
                for(let j=0; j<vm.depth4.length; j++){
                  if(vm.depth4[j].ctg_id == 'NTY10101001010050'){
                    if(vm.depth4[j].value==''){
                      vm.depth4[j].value = arr[i];
                    }else{
                      vm.depth4[j].value += '\n' + arr[i];    
                    }
                  }
                }
              }else if(arr[i].indexOf('예비특보')>-1){
                for(let j=0; j<vm.depth4.length; j++){
                  if(vm.depth4[j].ctg_id == 'NTY10101001010060'){
                    if(vm.depth4[j].value==''){
                      vm.depth4[j].value = arr[i];
                    }else{
                      vm.depth4[j].value += '\n' + arr[i];    
                    }
                  }
                }
              }else{
              }
            }
          }

        }).catch(function (e){
          $("#loading-layer").hide();
          console.log(e);
        });
      }
    }

    ,fnGetTyphoonInfo: function() {
      let vm = this;
      this.fnLayerRemove();
      
      //태풍정보reset
      vm.viewModel.listCurrentTyphoonPosition=[];
      vm.viewModel.currentTyphoonInfo='';

      vm.viewModel.listSimilarTyphoons = [];
      vm.viewModel.maxDamageTyphoon='';
      vm.viewModel.maxDamageTyphoon_typName='';
      vm.viewModel.maxDamageTyphoon_personDme='';
      vm.viewModel.maxDamageTyphoon_moneyDme={totalPub:0, totalPri:0};
      vm.viewModel.maxDamageTyphoon_sidoDme='';

      vm.viewModel.listEventActionKeyword={size:0};
      vm.viewModel.maxDamageTyphoon_dmeFac='';
      vm.viewModel.listNews = [];

      if(vm.summary.selectedTypInfo!=''){

        Axios.post("/api/typhoon/cast/inform/list.do", {
          paramYear: vm.summary.createDateTime.substring(0,4) //2017
          ,paramMonth: vm.summary.createDateTime.substring(4,6) //09 
          ,paramTyphoonName: vm.summary.selectedTypInfo.typ_name //탈림
        })
        .then(function(response) {
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
          }

          vm.fnListSimilarTyphoon(); //현재태풍 경로로 유사태풍 찾기
          
        })
        .catch(function(ex) {
          $("#loading-layer").hide();
            console.log(ex);
        });
      }else{
        $("#loading-layer").hide();
      }

      
    }

    ,fnListSimilarTyphoon: function() {
      let vm = this;
      let lastIndex = 0;
      vm.message = '유사태풍 정보를 조회 중입니다.';

      //유사태풍
      if(vm.viewModel.listCurrentTyphoonPosition.length>0){

        lastIndex = vm.viewModel.listCurrentTyphoonPosition.length-1;
        Axios.post('/api/typhoon/list/similar/forcast.do', {
          paramYear: vm.summary.createDateTime.substring(0,4),
          paramMonth: vm.summary.createDateTime.substring(4,6),
          paramSeq: vm.viewModel.listCurrentTyphoonPosition[lastIndex-2].typ_seq,
          paramTimeSeq: vm.viewModel.listCurrentTyphoonPosition[lastIndex-2].tm_seq
        }).
        then(function(response) {

          if(response.data.similarTyphoonList.length>0){

            response.data.similarTyphoonList.sort(function(a, b) {
                return b.countPosition - a.countPosition;
            });
            vm.viewModel.listSimilarTyphoons = response.data.similarTyphoonList;

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
            }
            
            vm.viewModel.listSimilarTyphoons.sort(function(a, b) { return b.sumDamage - a.sumDamage; });
            vm.viewModel.maxDamageTyphoon = vm.viewModel.listSimilarTyphoons[0];
            vm.viewModel.maxDamageTyphoon_typName = vm.viewModel.maxDamageTyphoon.typ_name;
            vm.viewModel.maxDamageTyphoon_personDme = new String(vm.viewModel.maxDamageTyphoon.detailSummary.com_total);
            vm.viewModel.maxDamageTyphoon_moneyDme = {totalPub:vm.viewModel.maxDamageTyphoon.detailSummary.pub_total, totalPri:vm.viewModel.maxDamageTyphoon.detailSummary.pri_total};
            vm.viewModel.maxDamageTyphoon_sidoDme = vm.viewModel.maxDamageTyphoon.detailSummary.damage_sido;

            vm.fnGetDamageFacility();//주요피해시설
            vm.fnGetListDisasterManual();//대응현황키워드
            vm.fnGetDaumNews();
            $("#loading-layer").hide();

          }
        }).catch(function(ex) {
            $("#loading-layer").hide();
        });

      }else{
        $("#loading-layer").hide();
      }

    }

    ,fnGetListDisasterManual:function(){
      let vm=this;

      Axios.post("/disaster/manual/list/group", {
          paramCtgId:vm.selectedDisEventType
      }).then(function(response){
        
        vm.viewModel.listActionManual=response.data.resultManualList;
        vm.fnGetSimilarEventActionKeyword(); //대응사례 키워드내용

      }).catch(function(ex){
          $("#loading-layer").hide();
      });
    }

    ,fnGetSimilarEventActionKeyword: function(){
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

      if(vm.viewModel.maxDamageTyphoon!=''){

        Axios.post("/api/advis/dis/event/groupid/info",{
          paramCtgId:vm.selectedDisEventType
          ,paramStartDate:vm.viewModel.maxDamageTyphoon.beg_date
          ,paramEndDate:vm.viewModel.maxDamageTyphoon.end_date
          ,paramTitle:vm.viewModel.maxDamageTyphoon.typ_name
        }).then(function(response) {
  
          let paramListAction = response.data.resultList;
  
          if(paramListAction!=null){
              for(let i=0;i<paramListAction.length;i++){
                  let item=paramListAction[i];
                  item.month=item.month.length==1?"0"+item.month:item.month;
                  item.day=item.day.length==1?"0"+item.day:item.day;
                  item.hour=item.hour.length==1?"0"+item.hour:item.hour;
  
                  for(let k=0;k<vm.viewModel.listActionManual.length;k++){
                      let action= vm.viewModel.listActionManual[k];
                      
                      if(item.dis_act_lv1!=null&&item.dis_act_lv1.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                          if(rs.lvl1.indexOf(action.manual_title)==-1 && rs.lvl1.indexOf(action.manual_contents)==-1) {

                            let boo = false;
                            for(let d=0; d<rs.lvl1.length; d++){
                              if(rs.lvl1[d].manual_contents==action.manual_contents) boo = true; 
                            }
                            if(!boo){
                              rs.lvl1.push({title:action.manual_title,content:item.dis_act_lv1,manual_contents:action.manual_contents,evtDay:item.year+item.month+item.day+item.hour});
                              rs.isAction=true;
                            }
                          }
                      }
                      if(item.dis_act_lv2!=null&&item.dis_act_lv2.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/\s*/gi, ""))>-1 ){
                          if(rs.lvl2.indexOf(action.manual_title)==-1 && rs.lvl2.indexOf(action.manual_contents)==-1) {

                            let boo = false;
                            for(let d=0; d<rs.lvl2.length; d++){
                              if(rs.lvl2[d].manual_contents==action.manual_contents) boo = true; 
                            }
                            if(!boo){
                              rs.lvl2.push({title:action.manual_title,content:item.dis_act_lv2,manual_contents:action.manual_contents,evtDay:item.year+item.month+item.day+item.hour});
                              rs.isAction=true;
                            }
                          }
                      }
                      if(item.dis_act_lv3!=null&&item.dis_act_lv3.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                          if(rs.lvl3.indexOf(action.manual_title)==-1 && rs.lvl3.indexOf(action.manual_contents)==-1) {

                            let boo = false;
                            for(let d=0; d<rs.lvl3.length; d++){
                              if(rs.lvl3[d].manual_contents==action.manual_contents) boo = true; 
                            }
                            if(!boo){
                              rs.lvl3.push({title:action.manual_title,content:item.dis_act_lv3,manual_contents:action.manual_contents,evtDay:item.year+item.month+item.day+item.hour});
                              rs.isAction=true;
                            }
 
                          }
                      }
                      if(item.dis_act_lv4!=null&&item.dis_act_lv4.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                          if(rs.lvl4.indexOf(action.manual_title)==-1 && rs.lvl4.indexOf(action.manual_contents)==-1) {

                            let boo = false;
                            for(let d=0; d<rs.lvl4.length; d++){
                              if(rs.lvl4[d].manual_contents==action.manual_contents) boo = true; 
                            }
                            if(!boo){
                              rs.lvl4.push({title:action.manual_title,content:item.dis_act_lv4,manual_contents:action.manual_contents,evtDay:item.year+item.month+item.day+item.hour});
                              rs.isAction=true;
                            }

                          }
                      }
                      if(item.dis_act_lv5!=null&&item.dis_act_lv5.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                          if(rs.lvl5.indexOf(action.manual_title)==-1 && rs.lvl5.indexOf(action.manual_contents)==-1) {

                            let boo = false;
                            for(let d=0; d<rs.lvl5.length; d++){
                              if(rs.lvl5[d].manual_contents==action.manual_contents) boo = true; 
                            }
                            if(!boo){
                              rs.lvl5.push({title:action.manual_title,content:item.dis_act_lv5,manual_contents:action.manual_contents,evtDay:item.year+item.month+item.day+item.hour});
                              rs.isAction=true;
                            }
                          }
                      }
                      if(item.dis_act_lv6!=null&&item.dis_act_lv6.replace(/(\s*)/gi, "").indexOf(action.manual_title.replace(/(\s*)/gi, ""))>-1 ){
                          if(rs.lvl6.indexOf(action.manual_title)==-1 && rs.lvl6.indexOf(action.manual_contents)==-1) {

                            let boo = false;
                            for(let d=0; d<rs.lvl6.length; d++){
                              if(rs.lvl6[d].manual_contents==action.manual_contents) boo = true; 
                            }
                            if(!boo){
                              rs.lvl6.push({title:action.manual_title,content:item.dis_act_lv6,manual_contents:action.manual_contents,evtDay:item.year+item.month+item.day+item.hour});
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
          }
          vm.viewModel.listEventActionKeyword = rs;
  
        }).catch(function(ex) {
          $("#loading-layer").hide();
        });
      }
    }

    ,fnGetDamageFacility: function(){
      let vm = this;
      
      if(vm.viewModel.maxDamageTyphoon!=''){

        Axios.post('/advis/disaster/status/eventItem/list', { 
          paramDisType: 'HZD002', 
          paramStDate: vm.viewModel.maxDamageTyphoon.beg_date, 
          paramEndDate: vm.viewModel.maxDamageTyphoon.end_date, 
          paramTitle: vm.viewModel.maxDamageTyphoon.typ_name 
        }).then(function(response) {
  
          //주요피해시설
          for(let i=0; i<response.data.listStatistics.length; i++){
            if(vm.viewModel.maxDamageTyphoon_dmeFac==''){
              vm.viewModel.maxDamageTyphoon_dmeFac = response.data.listStatistics[i].damageName;
            }else{
              vm.viewModel.maxDamageTyphoon_dmeFac += ', ' + response.data.listStatistics[i].damageName;
            }
          }
          
  
        }).catch(function(ex) {
          $("#loading-layer").hide();
        });

      }
    }

    ,fnDrawCurrentTyphoon: function() {
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

      this.currentTypMap.addLayer(lineLayer);
      this.currentTypMap.addLayer(pointLayer);
      this.currentTypMap.addLayer(windLayer);
    },

    fnLayerRemove:function(){
        let removeTarget=[];
        this.currentTypMap.getLayers().forEach(function(layer) {
            if (layer.get('name') != undefined) {
                if (layer.get('name').indexOf("current") > -1) {
                 removeTarget.push(layer);
                } 
            }
        });

        for(let i=0;i<removeTarget.length;i++){
          let layer=removeTarget[i];
          this.currentTypMap.removeLayer(layer);
        }
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

    fnGetDaumNews: function() {
      let vm = this;
      vm.viewModel.listNews = [];
      
      if(vm.viewModel.maxDamageTyphoon!=''){

        let paramQueryObj = { text: vm.viewModel.maxDamageTyphoon.beg_date.substring(0,4) + '년 태풍 ' + vm.viewModel.maxDamageTyphoon.typ_name, sort: 'accuracy', size: 50, page: 1 };
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
      
    },

    fnGetObsRainList:function(){
      let vm = this;
      let data = {stn_id:'', list:[]};
      if(!vm.isUsed) vm.viewModel.listObsRn = [];

      vm.viewModel.listStnAsos = [];
      if(vm.summary.checkedRain=='') vm.summary.checkedRain = 10;

      //현재 태풍정보가 있을시 강수량 가져오기
      if(vm.summary.selectedTypInfo!=''){

        Axios.post("/api/period/rain/obs/list.do", {
          paramStDate: vm.summary.createDateTime.substring(0,8),
          paramRnDay: vm.summary.checkedRain
        }).then(function(response) {

          vm.viewModel.listStnAsos = response.data.listSidoAsos;

          if(!vm.isUsed){

            if(response.data.listSidoAsos==null||response.data.listSidoAsos.length==0){
                data.list.push({ctg_id:'NTY10102001020010', ctg_nm:'지역', value: ''});
                data.list.push({ctg_id:'NTY10102001020020', ctg_nm:'강우량', value: ''});
                data.list.push({ctg_id:'NTY10102001020030', ctg_nm:'누적강우량', value: ''});
                vm.viewModel.listObsRn.push(data);
            }else{
              
                for(let i=0; i<vm.viewModel.listStnAsos.length; i++){
                  if(i<10){
                    let data = {stn_id:'', list:[]};
                    data.stn_id = vm.viewModel.listStnAsos[i].stn_id;
                    data.list.push({ctg_id:'NTY10102001020010', ctg_nm:'지역', value: vm.viewModel.listStnAsos[i].obs_name});
                    data.list.push({ctg_id:'NTY10102001020020', ctg_nm:'강우량', value: vm.viewModel.listStnAsos[i].rn_day/10});
                    data.list.push({ctg_id:'NTY10102001020030', ctg_nm:'누적강우량', value: vm.viewModel.listStnAsos[i].rn_60m_max/10});
                    vm.viewModel.listObsRn.push(data);
                  }
                }
              
            }
          }
         
        }).catch(function(ex) {
          $("#loading-layer").hide();
          console.log(ex);
        });

      }else{
        data.list.push({ctg_id:'NTY10102001020010', ctg_nm:'지역', value: ''});
        data.list.push({ctg_id:'NTY10102001020020', ctg_nm:'강우량', value: ''});
        data.list.push({ctg_id:'NTY10102001020030', ctg_nm:'누적강우량', value: ''});
        vm.viewModel.listObsRn.push(data);
      }
    }

    ,fnGetListDisType: function(){
      let vm = this;
      Axios.post("/api/std/manageDisCodelist", {
        paramDepth: 1
      }).
      then(function(response) {
          vm.listDisEventType = response.data.list;
          vm.fnGetListDisEvent();
      }).catch(function(ex) {
        $("#loading-layer").hide();
          console.log(ex);
      });
    }
    
    ,fnGetListDisEvent: function(){
      let vm = this;
      vm.listDisEventIsCs_y = [];
      
      Axios.post("/api/advis/dis/event/report/list", {
        paramCtgId: vm.selectedDisEventType
        ,paramIsRoot: 'N'
        ,paramIsCs: 'Y'
      }).
      then(function(response) {
          vm.listDisEventIsCs_y = response.data.resultList;
          for(let i=0; i<vm.listDisEventIsCs_y.length; i++){
            switch(vm.listDisEventIsCs_y[i].dis_level_code){
              case 'DVL_0000': vm.listDisEventIsCs_y[i].dis_level_code = '비상근무실시'; break;
              case 'DVL_0001': vm.listDisEventIsCs_y[i].dis_level_code = '1단계'; break;
              case 'DVL_0002': vm.listDisEventIsCs_y[i].dis_level_code = '2단계'; break;
              case 'DVL_0003': vm.listDisEventIsCs_y[i].dis_level_code = '3단계'; break;
              default: break;
            }
          }
      }).catch(function(ex) {
        $("#loading-layer").hide();
          console.log(ex);
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

        vm.summary.selectedTypName = vm.summary.selectedEvent.title; 
        vm.summary.selectedEventGroup = vm.summary.selectedEvent.evt_group;
        vm.summary.createDateTime = vm.summary.selectedEvent.evt_date;
        vm.summary.area = vm.summary.selectedEvent.area_codes;
        vm.summary.paramStatus = vm.summary.selectedEvent.dis_level_code;
        vm.summary.death = vm.summary.selectedEvent.com_1;
        vm.summary.missing = vm.summary.selectedEvent.com_2;
        vm.summary.injury = vm.summary.selectedEvent.com_3;

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

        //강수량 setting
        vm.viewModel.listObsRn = [];
        for(let i=0; i<rainItem.length; i++){

          if(rainItem[i].ctg_id=='NTY10102001020010'){
            let data = {stn_id:'', list:[]};

            if(rainItem[i].contents!='') data.list.push({ctg_id:'NTY10102001020010', ctg_nm:'지역', value: rainItem[i].contents});
            if(rainItem[i+1].contents!='') data.list.push({ctg_id:'NTY10102001020020', ctg_nm:'강우량', value: rainItem[i+1].contents});
            if(rainItem[i+2].contents!='') data.list.push({ctg_id:'NTY10102001020030', ctg_nm:'누적강우량', value: rainItem[i+2].contents});
            vm.viewModel.listObsRn.push(data);
            i = i+2;
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
        vm.fnGetTyphoonList();


      }).catch(function(ex) {
        $("#loading-layer").hide();
          console.log(ex);
      });

    }

    ,fnOnClickWritePage: function(paramEvtId){
      let vm = this;

      if(paramEvtId!=null&&paramEvtId!=''&&paramEvtId!=undefined){
        if(vm.selectedDisEventType=='NTY'){
          window.open("/report/typ/write?paramEvtId="+paramEvtId, "_blank");
        }else if(vm.selectedDisEventType=='NHR'){
          window.open("/report/nhr/write?paramEvtId="+paramEvtId, "_blank");
        }else if(vm.selectedDisEventType=='NEQ'){
          window.open("/report/neq/write?paramEvtId="+paramEvtId, "_blank");
        }else if(vm.selectedDisEventType=='VAI'){
          window.open("/report/vai/write?paramEvtId="+paramEvtId, "_blank");
        }else{
          
        }
      }else{
        if(vm.selectedDisEventType=='NTY'){
          window.open("/report/typ/write", "_blank");
        }else if(vm.selectedDisEventType=='NHR'){
          window.open("/report/nhr/write", "_blank");
        }else if(vm.selectedDisEventType=='NEQ'){
          window.open("/report/neq/write", "_blank");
        }else if(vm.selectedDisEventType=='VAI'){
          window.open("/report/vai/write", "_blank");
        }else{
          
        }
      }

    }



  }
})
