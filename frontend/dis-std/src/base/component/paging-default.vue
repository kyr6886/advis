<template>
   <div class="btn-group btn-group-sm" role="group" >
     <button type="button"  class="btn btn-default btn-sm" v-if="pass.startBlock>1" v-on:click="onClickPagerNo(pass.startBlock-1)">&lt;</button>
     
     <button type="button" v-on:click="onClickPagerNo(item)" v-bind:class="{'btn-info':item==pass.pageNo}" class="btn btn-default  btn-sm" v-for="item in pass.list">{{item}}</button>

     <button type="button"  class="btn btn-default  btn-sm"  v-on:click="onClickPagerNo(pass.nextBlock)">&gt;</button>
   </div>
</template>
<script>
  export default{
  props:['pass']
  ,methods:{
  onClickPagerNo:function(n){
  this.pass.pageNo=n==0?1:n;
  this.pass.callBack(n);
  },
  createPaging:function(pager,vm,cbType){
  pager.callBackType=cbType;
  let _list=[];
  let _endPagerNo=parseInt((pager.totalSize-1)/pager.rowSize)+1;

  let _startBlock=parseInt((pager.pageNo-1)/pager.blockSize)*pager.blockSize+1;
  let index=0;
  let _endBlock=0;
  let _nextBlock=0;
  for(let i=_startBlock;i<=_endPagerNo;i++){
        if(index<pager.blockSize){
          _list.push(i);
          _endBlock=i;
            if(i<_endPagerNo){
              _nextBlock=i+1;
            }else{
              _nextBlock=0;
            }
          index++;
        }else{
          break;
        }
      }//end for
      pager.list=_list;
      pager.model=vm;
      pager.startBlock=_startBlock;
      pager.nextBlock=_nextBlock;
      return pager;
    }
  }
}
</script>
