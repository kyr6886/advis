import Vue from 'vue'
import Axios from 'axios'

var vue=new Vue({
  el: '#scope-action'
  ,data:function(){
    return {
        listDisEvtAction: []
        ,listDisEvt: []
        ,listDisEvtItem_action: []
        ,evtCtgId: ''
        ,evtGrpId: ''
        ,evtTitle: '재해'
        ,categoryList: []
        ,categoryId_news: ''
        ,categoryId_dmg: ''
        ,categoryId_nty: ''
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

    fnListDisEventAction: function(paramEvtGrpId){
        let vm = this;
        vm.evtCtgId = paramEvtGrpId.substring(0,3);
        vm.evtGrpId = paramEvtGrpId;

        Axios.post("/api/advis/dis/eventActionList", {paramCtgId: vm.evtCtgId, paramEvtGrp: vm.evtGrpId})
          .then(function (response) {
            
            vm.listDisEvtAction = response.data.resultList;
            vm.evtTitle = vm.listDisEvtAction[0].title;
            vm.listDisEvt = response.data.eventList;
            if(vm.listDisEvtAction==null||vm.listDisEvtAction.length==0) return false;
            Axios.post("/advis/code/tree/category/list", {paramCtgId: vm.evtCtgId}).then(function (response) {
              vm.categoryList = response.data.list;
        
              for(let i=0; i<vm.categoryList.length; i++){
                if(vm.categoryList[i].depth == 3){
                  if(vm.categoryList[i].title == '기상특보'){
                    vm.categoryId_news = vm.categoryList[i].print_id;
                  }
                  if(vm.categoryList[i].title == '피해상황'){
                    vm.categoryId_dmg = vm.categoryList[i].print_id;
                  }
                  if(vm.categoryList[i].title == '태풍현황'){
                    vm.categoryId_nty = vm.categoryList[i].print_id;
                  }
                }
              }

              let _date = '';
              for(let i=0; i<vm.listDisEvtAction.length; i++){
                _date = vm.listDisEvtAction[i].year + vm.listDisEvtAction[i].month + vm.listDisEvtAction[i].day + vm.listDisEvtAction[i].hour + '00';
                vm.listDisEvtAction[i].dis_news = '';
                vm.listDisEvtAction[i].dis_dmg = '';
                vm.listDisEvtAction[i].dis_nty = '';

                if(vm.evtCtgId == 'NTY'){
                  for(let j=0; j<vm.listDisEvt.length; j++){
                    if(_date == vm.listDisEvt[j].evt_date){
                      
                      for(let k=0; k<vm.listDisEvt[j].eventItemList.length; k++){
                        if(vm.listDisEvt[j].eventItemList[k].ctg_id.indexOf(vm.categoryId_news)>-1){ 
                          vm.listDisEvtAction[i].dis_news += vm.listDisEvt[j].eventItemList[k].contentsTitle + ':' + '<br>' + vm.listDisEvt[j].eventItemList[k].contents + '<br>';
                        }else if(vm.listDisEvt[j].eventItemList[k].ctg_id.indexOf(vm.categoryId_nty)>-1){
                          vm.listDisEvtAction[i].dis_nty += vm.listDisEvt[j].eventItemList[k].contentsTitle + ':' + '<br>' + vm.listDisEvt[j].eventItemList[k].contents + '<br>';
                        }else{
                          if(vm.listDisEvt[j].eventItemList[k].ctg_id.indexOf(vm.categoryId_dmg)>-1){
                            vm.listDisEvtAction[i].dis_dmg += vm.listDisEvt[j].eventItemList[k].contentsTitle + ':' + '<br>' + vm.listDisEvt[j].eventItemList[k].contents + '<br>';
                          }
                        }
                      }
                    }
                  }

                }else{
                  for(let j=0; j<vm.listDisEvt.length; j++){
                    if(_date == vm.listDisEvt[j].evt_date){
                      
                      for(let k=0; k<vm.listDisEvt[j].eventItemList.length; k++){
                        if(vm.listDisEvt[j].eventItemList[k].ctg_id.indexOf(vm.categoryId_news)>-1){ 
                          vm.listDisEvtAction[i].dis_news += vm.listDisEvt[j].eventItemList[k].contentsTitle + ':' + '<br>' + vm.listDisEvt[j].eventItemList[k].contents + '<br>';
                        }else{
                          if(vm.listDisEvt[j].eventItemList[k].ctg_id.indexOf(vm.categoryId_dmg)>-1){
                            vm.listDisEvtAction[i].dis_dmg += vm.listDisEvt[j].eventItemList[k].contentsTitle + ':' + '<br>' + vm.listDisEvt[j].eventItemList[k].contents + '<br>';
                          }
                        }
                      }
                    }
                  }
                }

              }

              if(vm.evtCtgId == 'NTY'){
                window.setTimeout(function(){
                  $("#table-nty").rowspan(0);
                  $("#table-nty").rowspan(1);
                  $("#table-nty").rowspan(2);
                  $("#table-nty").rowspan(3);
                  $("#table-nty").rowspan(10);
                }, 0);  
              }else{
                window.setTimeout(function(){
                  $("#table-nhr").rowspan(0);
                  $("#table-nhr").rowspan(1);
                  $("#table-nhr").rowspan(2);
                  $("#table-nhr").rowspan(9);
                }, 0);  
              }
               

            }).catch(function (e){
              console.log(ex);
            } );
        }).catch(function (e){
          console.log(ex);
        } );
    },
    fnConvertDay: function(paramVal) {
      let arr = paramVal.split('.');
      if(arr.length>1){
          if (arr[0].length == 1) arr[0] = '0' + arr[0];
          if (arr[1].length == 1) arr[1] = '0' + arr[1];
          paramVal = arr[0] + '.' + arr[1];
      }
      return paramVal;
  } ,

  }
});