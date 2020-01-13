import Vue from 'vue'
import Axios from 'axios'

var vue=new Vue({
  el: '#scope-report-rain'
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
      }
      ,viewModel: {
        detailKmaInform: [],
        listNews:[],
        listStnAsos: [],
        listObsRn:[],
        listActionManual:[],
        listEventActionKeyword:{size:0},
        listSimilarRain: [],
      }  
      , eventName: ''
       , depth2: []
       , depth3: []
       , depth4: []
       , addedListActions:[]
       , eventItemList: []

       ,listDisEventType: []
       ,selectedDisEventType: 'NHR'
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
      
      //this.fnGetTyphoonList();
      this.fnGetDetailKmaReport();
      this.fnGetObsRainList();

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
        
        paramList.push($.stringFormat("{0}|{1}", 'NHR50500005000010', actionData[0].value));
        paramList.push($.stringFormat("{0}|{1}", 'NHR50500005000020', actionData[1].value));
        paramList.push($.stringFormat("{0}|{1}", 'NHR50500005000050', actionData[2].value+'\r\n'+actionData[3].value+'\r\n'+actionData[4].value
        +'\r\n'+actionData[5].value+'\r\n'+actionData[6].value+'\r\n'+actionData[7].value));

        actionList.push($.stringFormat("{0}|{1}|{2}|{3}|{4}|{5}|{6}|{7}", actionData[0].value, actionData[1].value, actionData[2].value, actionData[3].value, 
        actionData[4].value, actionData[5].value, actionData[6].value, actionData[7].value));
      }

      for(let i=0; i<vm.viewModel.listObsRn.length; i++){
        let obsData = vm.viewModel.listObsRn[i].list;

        if(obsData[0].value!='')paramList.push($.stringFormat("{0}|{1}", 'NHR10103001030010', obsData[0].value));
        if(obsData[1].value!='')paramList.push($.stringFormat("{0}|{1}", 'NHR10103001030020', obsData[1].value));
        if(obsData[2].value!='')paramList.push($.stringFormat("{0}|{1}", 'NHR10103001030030', obsData[2].value));
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

    ,fnGetDetailKmaReport: function(){
      let vm = this;
      vm.summary.selectedDetailKmaInform={stat_tm_ef:''};

      //기상특보 상세
        
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
                  if(vm.depth4[j].ctg_id == 'NHR10101001010010'){
                    if(vm.depth4[j].value==''){
                      vm.depth4[j].value = arr[i];
                    }else{
                      vm.depth4[j].value += '\n' + arr[i];
                    }
                  }
                }
              }else if(arr[i].indexOf('강풍')>-1){
                for(let j=0; j<vm.depth4.length; j++){
                  if(vm.depth4[j].ctg_id == 'NHR10101001010020'){
                    if(vm.depth4[j].value==''){
                      vm.depth4[j].value = arr[i];
                    }else{
                      vm.depth4[j].value += '\n' + arr[i];    
                    }
                  }
                }
              }else if(arr[i].indexOf('풍랑')>-1){
                for(let j=0; j<vm.depth4.length; j++){
                  if(vm.depth4[j].ctg_id == 'NHR10101001010030'){
                    if(vm.depth4[j].value==''){
                      vm.depth4[j].value = arr[i];
                    }else{
                      vm.depth4[j].value += '\n' + arr[i];    
                    }
                  }
                }
              }else if(arr[i].indexOf('호우')>-1){
                for(let j=0; j<vm.depth4.length; j++){
                  if(vm.depth4[j].ctg_id == 'NHR10101001010040'){
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

      if(vm.viewModel.listSimilarRain.length>0){

        Axios.post("/api/advis/dis/event/groupid/info",{
          paramCtgId:vm.selectedDisEventType
          ,paramStartDate:vm.viewModel.listSimilarRain[0].beg_date
          ,paramEndDate:vm.viewModel.listSimilarRain[0].end_date
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

    ,fnGetDaumNews: function() {
      let vm = this;
      vm.viewModel.listNews = [];
      
      if(vm.viewModel.listSimilarRain.length>0){

        let paramQueryObj = { text: vm.viewModel.listSimilarRain[0].beg_date.substring(0,4) + '년 호우 ', sort: 'accuracy', size: 50, page: 1 };
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
      vm.viewModel.listSimilarRain = [];
      if(vm.summary.checkedRain=='') vm.summary.checkedRain = 10;

        Axios.post("/api/period/rain/obs/list.do", {
          paramStDate: vm.summary.createDateTime.substring(0,8),
          paramRnDay: vm.summary.checkedRain
        }).then(function(response) {

          vm.viewModel.listStnAsos = response.data.listSidoAsos;

          if(!vm.isUsed){
            if(response.data.listSidoAsos==null||response.data.listSidoAsos.length==0){
                data.list.push({ctg_id:'NHR10103001030010', ctg_nm:'지역', value: ''});
                data.list.push({ctg_id:'NHR10103001030020', ctg_nm:'강우량', value: ''});
                data.list.push({ctg_id:'NHR10103001030030', ctg_nm:'누적강우량', value: ''});
                vm.viewModel.listObsRn.push(data);
            }else{
              
              for(let i=0; i<vm.viewModel.listStnAsos.length; i++){
                if(i<10){
                  let data = {stn_id:'', list:[]};
                  data.stn_id = vm.viewModel.listStnAsos[i].stn_id;
                  data.list.push({ctg_id:'NHR10103001030010', ctg_nm:'지역', value: vm.viewModel.listStnAsos[i].obs_name});
                  data.list.push({ctg_id:'NHR10103001030020', ctg_nm:'강우량', value: vm.viewModel.listStnAsos[i].rn_day/10});
                  data.list.push({ctg_id:'NHR10103001030030', ctg_nm:'누적강우량', value: vm.viewModel.listStnAsos[i].rn_60m_max/10});
                  vm.viewModel.listObsRn.push(data);
                }
              }
            }
          }

          let listObsId = [];
          for(let i=0; i<vm.viewModel.listStnAsos.length; i++){
            if(i==0){
              listObsId.push(vm.viewModel.listStnAsos[i].stn_id);
            }else{
              if(listObsId.indexOf(vm.viewModel.listStnAsos[i].stn_id)>-1){
                return;
              }else{
                listObsId.push(vm.viewModel.listStnAsos[i].stn_id);
              }
            }
          }

          if(listObsId.length>0){
            vm.fnListSimilarRain(listObsId);
          }else{
            $("#loading-layer").hide();
          }

         
        }).catch(function(ex) {
          $("#loading-layer").hide();
          console.log(ex);
        });

    }

    ,fnListSimilarRain: function(listObsId){
      let vm = this;

      Axios.post("/api/statistics/sido/rain/damage/list", {
        paramListObsId: listObsId
        ,paramRnDay: vm.summary.checkedRain
      }).then(function(response) {

        //vm.viewModel.listSimilarRain = response.data.listSidoDmeRain;
        for(let i=0; i<response.data.listSidoDmeRain.length; i++){
          vm.viewModel.listSimilarRain.push(response.data.listSidoDmeRain[i]);
        }

        vm.fnGetListDisasterManual();//대응현황키워드
        vm.fnGetDaumNews();

        $("#loading-layer").hide();

      }).catch(function(ex) {
        $("#loading-layer").hide();
          console.log(ex);
      });
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
          
          if(eventItemList[i].ctg_id.indexOf('10300')>-1){
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

          if(rainItem[i].ctg_id=='NHR10103001030010'){
            let data = {stn_id:'', list:[]};

            if(rainItem[i].contents!='') data.list.push({ctg_id:'NHR10103001030010', ctg_nm:'지역', value: rainItem[i].contents});
            if(rainItem[i+1].contents!='') data.list.push({ctg_id:'NHR10103001030020', ctg_nm:'강우량', value: rainItem[i+1].contents});
            if(rainItem[i+2].contents!='') data.list.push({ctg_id:'NHR10103001030030', ctg_nm:'누적강우량', value: rainItem[i+2].contents});
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
        vm.fnGetDetailKmaReport();
        vm.fnGetObsRainList();


      }).catch(function(ex) {
        $("#loading-layer").hide();
          console.log(ex);
      });

    }


  }
})
