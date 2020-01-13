import Vue from 'vue'
import Axios from 'axios'

var vue=new Vue({
  el: '#scope-search'
  ,data:function(){
    return {
      viewModel:{
        listNews: []
      },
      searchList: []
      ,searchListViewData: []
      ,eventImgList: []
      ,depth2: []
      ,depth3: []
      ,depth4: []
      ,searchTxt: ''
      ,selectedTabCode: ''
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
    fnGetList: function(paramTxt){
      let vm = this;
      vm.searchTxt = paramTxt;
      Axios.post("/advis/search/list", {paramDepth: 1, paramTitle: vm.searchTxt})
        .then(function (response) {
          vm.searchList = response.data.resultList;
          vm.eventImgList = response.data.eventImgList;
          if(vm.selectedTabCode == '') vm.selectedTabCode = '30';
          vm.fnOnClickTab(vm.selectedTabCode);
        }).catch(function (e){} );
    },

    fnOnClickTab: function(paramCtgId){
      let vm = this;
      let list = [];
      vm.searchListViewData = [];
      vm.selectedTabCode = paramCtgId;
      vm.fnGetDepthList('NEQ'); //지진 카테고리 만들기

      for(var i=0; i<vm.searchList.length; i++){
        let col = { evt_id:'', ctg_id:'', title:'', detail:[] };
        col.evt_id = vm.searchList[i].evt_id;
        col.ctg_id = vm.searchList[i].ctg_id;
        col.title = vm.searchList[i].title;
        
        if(vm.searchList[i].eventItemList != null){
          for(var j=0; j<vm.searchList[i].eventItemList.length; j++){
            if(vm.selectedTabCode == '20'){
              if(vm.searchList[i].eventItemList[j].ctg_id.indexOf('20000') > -1){
                col.detail.push(vm.searchList[i].eventItemList[j]);
              }
            }else if(vm.selectedTabCode == '30'){
              if(vm.searchList[i].eventItemList[j].ctg_id.indexOf('30100') > -1){
                col.detail.push(vm.searchList[i].eventItemList[j]);
              }
              if(vm.searchList[i].eventItemList[j].ctg_id.indexOf('30200') > -1){
                col.detail.push(vm.searchList[i].eventItemList[j]);
              }
            }else if(vm.selectedTabCode == '50'){
              if(vm.searchList[i].eventItemList[j].ctg_id.indexOf('50000') > -1){
                col.detail.push(vm.searchList[i].eventItemList[j]);
              }
            }else {
              if(vm.selectedTabCode == '60'){
                if(vm.searchList[i].eventItemList[j].ctg_id.indexOf('60000') > -1){
                  col.detail.push(vm.searchList[i].eventItemList[j]);
                }
              }
            }
          }
        }
        
        list.push(col);
      }
      
      vm.searchListViewData = list;
    },

    fnGetDepthList: function(paramCategoryName){
      let vm = this;
      let list = [];
      vm.categoryList = [];
      vm.depth2 = [];
      vm.depth3 = [];
      vm.depth4 = [];

      Axios.post("/advis/code/tree/category/list", {})
      .then(function (response) {
        vm.categoryList = response.data.list;

        for(var i=0; i<vm.categoryList.length; i++){
          if(vm.categoryList[i].ctg_group==paramCategoryName){
            list.push(vm.categoryList[i]);
          }
        }
  
        for(var i=0; i<list.length; i++){
          if(list[i].depth == 2){
            list[i].ctg_id = list[i].ctg_id.replace('NEQ','');
            vm.depth2.push(list[i]);
          }else if(list[i].depth == 3){
            list[i].parent_ctg_id = list[i].parent_ctg_id.replace('NEQ','');
            vm.depth3.push(list[i]);
          }else{
            if(list[i].depth == 4){
              list[i].ctg_id = list[i].ctg_id.replace('NEQ','');
              vm.depth4.push(list[i]);
            }
          }
        }
      }).catch(function (e){} );
    },

    fnOnClickNewsTab: function(paramTab){
      let vm = this;
      vm.selectedTabCode = paramTab;
      let  param={text:vm.searchTxt , sort:'accuracy' , size:50 , page:1};
      vm.fnGetDaumNews(param);
    },

    fnGetDaumNews:function(paramQueryObj){
      let vm=this;
      Axios.post("/api/kakao/get/search",{paramUrl:'https://dapi.kakao.com/v2/search/web'
      ,paramKey:'dd615544fe7d753ebfabf05ad473f6bf'
      ,paramQuery:paramQueryObj.text
      ,paramSort:paramQueryObj.sort
      ,paramSize:paramQueryObj.size
      ,paramPage:paramQueryObj.page
      })
      .then(function(response){
          vm.viewModel.listNews=[];
          for(let i=0;i<response.data.documents.length;i++){
              let item=response.data.documents[i];
              if(item.title.indexOf('위키')<0){

                  item.title=item.title.replace(/<[^>]+>/g, ' ');
                  item.printTitle=item.title;
                  item.datetime=item.datetime.substring(0,10).replace(/-/g, '');
                  vm.viewModel.listNews.push(item);
              }
          }
          vm.viewModel.listNews.sort(function(a,b){return b.datetime-a.datetime;});
      })
      .catch(function(ex){
          console.log(ex);
      });
    },


  }
})
