import Vue from 'vue'
import Axios from 'axios'

var vue=new Vue({
  el: '#scope-code'
  ,data:function(){
    return {
      category_name: ''
      , eventName: ''
       , depth2: []
       , depth3: []
       , depth4: []
       , eventItemList: []
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
    fnGetList: function(paramCtgId, paramEvtId){
        let vm = this;
        vm.categoryList = [];
        vm.detailList = [];

        Axios.post("/advis/code/tree/category/list", {})
          .then(function (response) {
            vm.categoryList = response.data.list;

            Axios.post("/advis/code/tree/event/list", {})
              .then(function (response) {
                vm.detailList = response.data.resultList;

                if(paramEvtId==''){
                  for(var i=0; i<vm.detailList.length; i++){
                    if(vm.detailList[i].ctg_id == paramCtgId){
                      paramEvtId = vm.detailList[i].evt_id;
                      break;
                    }
                  }
                }
                let category = paramEvtId.split("-");     
                vm.category_name = category[0];

                vm.fnCreateTree(vm.categoryList, vm.detailList, paramEvtId);
                if(paramEvtId!=''){
                  vm.fnGetDetail(paramEvtId);
                }

              }).catch(function (e){} );

        }).catch(function (e){} );
    }
    , fnGetDetail: function (paramCode) {
      let vm = this;
      let category = paramCode.split("-");     
      vm.category_name = category[0];
      vm.fnGetDepthList(vm.category_name);
      
      Axios.post("/advis/code/tree/eventItem/list", {
        paramEvtId: paramCode
      }).then(function (response) {
        vm.eventItemList = response.data.eventItemList;
      }).catch(function (e) {
        console.log(e);
      });
    }
    , fnGetDepthList: function(paramCategoryName){
      let vm = this;
      let list = [];
      vm.depth1 = [];
      vm.depth2 = [];
      vm.depth3 = [];
      vm.depth4 = [];

      for(var i=0; i<vm.categoryList.length; i++){
        if(vm.categoryList[i].ctg_group==paramCategoryName){
          list.push(vm.categoryList[i]);
        }
      }

      for(var i=0; i<list.length; i++){
        if(list[i].depth == 1) {
          vm.depth1.push(list[i]);
        }else if(list[i].depth == 2){
          vm.depth2.push(list[i]);
        }else if(list[i].depth == 3){
          vm.depth3.push(list[i]);
        }else{
          if(list[i].depth == 4) vm.depth4.push(list[i]);
        }
      }
    }
    , fnCreateTree: function (paramList, paramDetailList, paramCode) {
      let vm = this;

      let setting = {
          data: {
            simpleData: {
              enable: true
            }
          },
          callback: {
            onClick: this.fnOnclickNode
          }
      };

      let zNodes = [];
      let level_s = {
        id: 'S'
        , name: '사회재난'
        , pId: 0
        , isParent: true
        , t: "id=" + 'S'
      };
      zNodes.push(level_s);

      let level_n = {
        id: 'N'
        , name: '자연재난'
        , pId: 0
        , isParent: true
        , t: "id=" + 'N'
      };
      zNodes.push(level_n);

      for (let i = 0; i < paramList.length; i++) {
        let target = paramList[i];
        if(target.depth==1){
          let item = {
            id: target.ctg_id
            , name: target.title
            , pId: target.parent_ctg_id.substring(0,1) == null ? 0 : target.parent_ctg_id.substring(0,1)
            , isParent: true
            , t: "id=" + target.ctg_id
          };
          zNodes.push(item);
        }
      }

      for (let i = 0; i < paramDetailList.length; i++) {
        let target = paramDetailList[i];
        let item = {
          id: target.evt_id
          , name: target.title + '(' + target.evt_id + ')'
          , pId: target.ctg_id == null ? 0 : target.ctg_id
          , isParent: target.ctg_id == null
          , t: "id=" + target.evt_id
        };
        zNodes.push(item);
      }

      this.fnNodeOpen(zNodes, paramCode);
      $.fn.zTree.init($("#tree"), setting, zNodes);

      let zTree = $.fn.zTree.getZTreeObj("tree");
      let rsNode = zTree.getNodeByParam("id", paramCode);

      this.previosNode = rsNode.tId;
      if (rsNode != null) {
        $("a", "#" + rsNode.tId).addClass("tree-init-selectedNode");
        vm.eventName = rsNode.name;
      }

    }
    , fnOnclickNode: function (event, treeId, treeNode, clickFlag) {
      let vm = this;
      vm.eventName = treeNode.name;
      $(".tree-init-selectedNode").removeClass("tree-init-selectedNode");
      this.previosNode = treeNode.tId;
      this.fnGetDetail(treeNode.id);
    }
    , fnNodeOpen: function (nodes, openId) {
      for (let k = 0; k < nodes.length; k++) {
        if (nodes[k].id == openId) {
          nodes[k].open = true;
          if (nodes[k].pId != 0) {
            this.fnNodeOpen(nodes, nodes[k].pId);
          } else {
            break;
          }
        }
      }
    }

  }
})
