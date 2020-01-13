<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="../inc/top.jsp"%>

<link href="${pageContext.request.contextPath}/contents/advis/css/viewer.css" rel="stylesheet" />
<div id="scope-search" v-cloak>
	<div class="contents-title">
		<h2>
			검색결과 <span>{{searchList.length}}개의 결과가 검색되었습니다.</span>
		</h2>
		<ul class="breadcrumb">
			<li>재난안전데이터 정보제공시스템</li>
			<li class="current">검색결과</li>
		</ul>
	</div>
	
	<ul class="tab">
<!-- 		<li v-bind:class="{'on':selectedTabCode=='20'}"><a v-on:click="fnOnClickTab('20')">현재상황</a></li> -->
		<li v-bind:class="{'on':selectedTabCode=='30'}"><a v-on:click="fnOnClickTab('30')">피해상황</a></li>
		<li v-bind:class="{'on':selectedTabCode=='50'}"><a v-on:click="fnOnClickTab('50')">주요 대처상황</a></li>
		<li v-bind:class="{'on':selectedTabCode=='60'}"><a v-on:click="fnOnClickTab('60')">향후 조치계획</a></li>
		<li v-bind:class="{'on':selectedTabCode=='news'}"><a v-on:click="fnOnClickNewsTab('news')">관련 재난 뉴스</a></li>
	</ul>
	
	<ul class="tab-result" v-show="selectedTabCode!='news'">
		<li  v-for="item in searchListViewData">
			<h4>{{item.title}}({{item.evt_id}})</h4>
			<cite><a v-bind:href="'/advis/code/codeView/'+item.ctg_id+'/'+item.evt_id">/advis/code/codeView/{{item.ctg_id}}/{{item.evt_id}}</a></cite>
			
			<div class="result-area-code">
				<div v-for="item2 in depth2" v-if="item2.ctg_id==selectedTabCode">
					<h4 class="page-header">{{item2.title}}</h4>
					
					<div class="result-set" v-for="item3 in depth3"  v-if="item3.parent_ctg_id==item2.ctg_id">
						<h5>{{item3.title}}</h5>
	
						<div class="code-table">
							<div  v-for="viewItem in item.detail" v-bind:class="{'item':viewItem.ctg_id.endsWith('10')}">
								<div v-for="item4 in depth4">
									<table v-if="viewItem.ctg_id.substring(3,17)==item4.ctg_id && item4.parent_ctg_id==item3.ctg_id">
										<tr>
											<th>{{viewItem.contentsTitle}}</th>
											<td v-html="viewItem.contents"></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</li>
	</ul>
	
	<ul class="tab-result" v-show="selectedTabCode=='news'">
		<li  v-for="item in viewModel.listNews">
			<h4><a v-bind:href='item.url' target="_blank">{{item.printTitle}}</a></h4>
			작성일자 : {{item.datetime.dateFormat()}}
			<br>
			<cite><a v-bind:href='item.url' target="_blank">{{item.url}}</a></cite>
		</li>
	</ul>
	
	<ul class="tab-result">
		<li><div class="description"></div></li>
		<li>
			<h4>관련이미지</h4>
			<div class="description" v-if="eventImgList.length>0">
				<div class="image" v-for="item in eventImgList">
					<img class="img" v-bind:src="'${pageContext.request.contextPath}/contents/advis/images/eventImg/'+item.file_new_name"/>
				</div>
			</div>
			<div class="description" v-if="eventImgList.length==0">
				해당 내용이 없습니다.
			</div>
		</li>
		<li></li>
<!-- 		<li> -->
<!-- 			<h4>관련 통계 (지역별)</h4> -->
<!-- 			<div class="description"> -->
<!-- 				<div class="graph" style="background: #eee;"></div> -->
<!-- 			</div> -->
<!-- 		</li> -->
	</ul>
	
</div>

<script type="text/javascript">
	var vueObj = null;
	function pageLoad(obj) {
		vueObj = obj;
		obj.fnGetList("${vm.paramTitle}");
	}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/contents/advis/js/model/vue/search-controller.min.js"></script>


<%@include file="../inc/bottom.jsp"%>