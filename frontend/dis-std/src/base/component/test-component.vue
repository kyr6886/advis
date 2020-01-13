<template>
  <div class="table-responsive content-box">
      <table class="table table-condensed table-hover th-info">
          <thead>
              <tr>
                  <th>사이트 명</th>
                  <th>URL</th>
                  <th>배너 이미지</th>
                  <th>사용여부</th>
                  <th>삭제</th>
              </tr>
          </thead>
          <tbody>
              <tr v-for="(item , index ) in list">
                  <td><a v-bind:href="'/PartnerSite/Update/'+item.seq">{{item.siteTitle}} </a></td>
                  <td><a v-bind:href="'/PartnerSite/Update/'+item.seq">{{item.siteUrl}} </a></td>
                  <td><img v-show="item.base64String != null" v-bind:src="'data:image/png;base64,' + item.base64String" style="width:125px;height:25px;" /></td>
                  <td>
                      <div class="row">
                          <div class="btn-group">
                              <a href="#" v-on:click="onClickUseYn(item , 'Y')" v-bind:class="{'btn-info btn-sm':item.useYn=='Y'}"><span class="glyphicon glyphicon-pencil"></span>사용</a>
                              <a href="#" v-on:click="onClickUseYn(item , 'N')" v-bind:class="{'btn-danger btn-sm':item.useYn=='N'}"><span class="glyphicon glyphicon-remove"></span> 미사용</a>
                          </div>
                      </div>

                  </td>
                  <td>
                      <div class="btn-group">
                          <a href="#" v-on:click="onClickDeleteSite(item.siteUrl)" class="btn-danger btn-sm"><span class="glyphicon glypicon-remove"></span>삭제</a>
                      </div>
                  </td>
              </tr>
          </tbody>
      </table>
  </div>
</template>
<script>
  export default{
    props : ['list']
  }
</script>
