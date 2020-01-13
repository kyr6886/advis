<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="../inc/top.jsp"%>
<div id="loading">
	<img id="loading-image" src="/images/ajax-loader.gif" alt="Loading..." />
</div>
<div id="container">
	<div class="contents-title">
		<h2>재해대응 피해이력</h2>
		<ul class="breadcrumb">
			<li>재난안전데이터 정보제공시스템</li>
			<li class="current">재해대응 피해이력</li>
		</ul>
	</div>

	<div class="contents-wrap" id="scope-socDme" v-cloak>
	
		<ul class="tab">
			<li><a href="/advis/disaster/rain">호우</a></li>
			<li><a href="/advis/disaster/status">태풍</a></li>
			<li><a href="/advis/disaster/snow">대설</a></li>
			<li><a href="/advis/disaster/earthquake">지진</a></li>
			<li class="on" ><a href="/advis/disaster/society">사회재난</a></li>
		</ul>
	
		<div class="search">
			<select  v-model="viewModel.selectTopDmeCode" class="select-box-width-120" >
				<option v-for="item in viewModel.listTopDmeCode" v-bind:value='item.ctg_id' >{{item.title}}</option>
			</select> 
			<select  v-model="viewModel.selectedSidoCode" class="select-box-width-120" v-on:change='fnOnClickChangeGunguList()'>
				<option v-for="item in viewModel.listDmeSidoCode" v-bind:value='item.code' >{{item.sido}}</option>
			</select>
			<select  v-model="viewModel.selectedGunguCode" class="select-box-width-120" v-on:change='fnGetDmeInfoList()'>
				<option v-for="item in viewModel.listDmeGunguCode" v-bind:value='item.code' >{{item.sigungu}}</option>
			</select>
			<a href="#" class="btn btn-purple" v-on:click="fnOnClickBtnSearch()">조회</a>
		</div>
		
		<br>
		<section class="main-divide">
			<div class="route-area">
				<div class="map-wrap">
					<div id="map" style="width:680px; height:400px;"></div>
				</div>
			</div>
			<div class="dis-right-box-soc">
				발생 월 :
				<select  v-model="viewModel.selectedMonth" class="select-box-width-120" v-on:change="fnGetDmeInfoList()">
					<option v-for="item in viewModel.listMonth" v-bind:value="item.month">{{item.monthNm}}</option>
				</select>
				<br/>
				<div class="table-basic table-soc">
					<table v-show="viewModel.listDmeSociety.length>0">
						<tbody id="mytable">
							<tr>
								<th class="min-th">시도/시군구</th>
								<th>사고명</th>
								<th class="min-th-sm">발생일</th>
								<th class="min-th-sm">사망(명)</th>
								<th class="min-th-sm">부상(명)</th>
							</tr>
							<tr v-bind:class="{'selected':viewModel.selectedDiInfId==item.di_inf_id}" v-for="item,index in viewModel.listDmeSociety" v-on:click="fnOnClickDmeDetailInfo(index)">
								<td class="action-color">{{item.sido}} / {{item.sigungu}}</td>
								<td class="action-color">{{item.di_inf_name}}</td>
								<td class="action-color">{{item.obz_dt.substring(0,4)}}.{{item.obz_dt.substring(4,6)}}.{{item.obz_dt.substring(6,8)}}</td>
								<td>{{item.hm_dmg_killed}}</td>
								<td>{{item.hm_dmg_injser}}</td>
							</tr>
						</tbody>
					</table>
					<table v-show="viewModel.listDmeSociety.length==0">
						<tr>
							<th>시도/시군구</th>
							<th>사고명</th>
							<th>사망(명)</th>
							<th>부상(명)</th>
						</tr>
						<tr><td colspan="4">해당 내용이 없습니다.</td></tr>
					</table>
<!-- 					<pager v-bind:pass="viewModel.pager"></pager> -->
				</div>
			</div>
		</section>
		<br>

		<div class='container-mid'>
			<div class="section-title">
				<ul>
					<li>{{viewModel.selectedSidoNm}} 월별 사회재난</li>
				</ul>
			</div>
			<section class="graph1">
				<canvas id="soc-chart" style="width: 1418px; height: 420px;"></canvas>
			</section>
		</div>
		
		<div class="section-title"><ul><li>상세내용</li></ul></div>
		<div class="table-basic">
			<table v-show="viewModel.selectedDmeDetail.di_inf_name!=null && viewModel.selectedDmeDetail.dis_content!=null">
				<tr>
					<th class="min-th">구분</th>
					<th>내용</th>
				</tr>
				<tr>
					<td v-if="viewModel.selectedDmeDetail.dis_content!=null">
						사고내용
					</td>
					<td class="text-left" v-if="viewModel.selectedDmeDetail.dis_content!=null" v-html="viewModel.selectedDmeDetail.dis_content">
					</td>
				</tr>
				<tr>
					<td v-if="viewModel.selectedDmeDetail.action_content!=null">
						조치내용
					</td>
					<td class="text-left" v-if="viewModel.selectedDmeDetail.action_content!=null" v-html="viewModel.selectedDmeDetail.action_content">
					</td>
				</tr>
			</table>
			<table v-show="viewModel.selectedDmeDetail.di_inf_name==null && viewModel.selectedDmeDetail.dis_content==null">
				<tr>	
					<td colspan="2">해당 내용이 없습니다.</td>
				</tr>
			</table>
		</div>
<!-- 		<br> -->
<!-- 		<div class="section-title"><ul><li>대응 현황</li></ul></div> -->
<!-- 		<div class="table-basic" v-if="viewModel.keyListView!=null&&viewModel.keyListView.length>0"> -->
<!-- 			<table> -->
<!-- 				<tr> -->
<!-- 					<th>일시</th> -->
<!-- 					<th>기관명</th> -->
<!-- 					<th>내용</th> -->
<!-- 				</tr> -->
<!-- 				<template v-for="key,index in keyListView"> -->
<!-- 					<tr v-for="item in key.detail"> -->
<!-- 						<td>{{item.printHour}}</td> -->
<!-- 						<td>{{item.org}}</td> -->
<!-- 						<td class="action-color" v-on:click="fnClickEventLink(item.contentId)" v-html="item.content"></td> -->
<!-- 					</tr> -->
<!-- 				</template> -->
<!-- 			</table> -->
<!-- 		</div> -->
<!-- 		<div class="table-basic" v-if="viewModel.keyListView==null||viewModel.keyListView.length==0"> -->
<!-- 			<table> -->
<!-- 				<tr> -->
<!-- 					<th>일시</th> -->
<!-- 					<th>기관명</th> -->
<!-- 					<th>내용</th> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td colspan="3">해당 내용이 없습니다.</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 		</div> -->
		
		
	
	</div>
	
	

	<script type="text/javascript"
		src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCO6oQoC5ZRaX-nKSXeLChM_KWwu6-v4BM&v=3.37&region=KR"></script>

	<script
		src="${pageContext.request.contextPath}/openlayers3/build/ol.js"></script>
	<script
		src="${pageContext.request.contextPath}/openlayers3/build/ol3gm.js"></script>
	<script src="${pageContext.request.contextPath}/map/map.js"></script>

	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/openlayers3/css/ol.css">
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/openlayers3/css/ol3gm.css">
		
		
				
	<script type="text/javascript">
		function pageLoad(obj) {
			
		//	var map = createGoogleMap('map');
		/* 	map.getView().setCenter(ol.proj.fromLonLat([ 128, 36 ]));
			map.getView().setZoom(6);
			obj.map = map; */
			
			  var raster = new ol.layer.Tile({
			        source: new ol.source.OSM()
			      });

			      var map = new ol.Map({
			        layers: [raster],
			        target: 'map',
			        view: new ol.View({
			          center: ol.proj.fromLonLat([ 128, 36 ]),
			          zoom:6
			        })
			      });
			      obj.map = map; 
			
			obj.fnOnInit();
			
		}
	</script>
	<script
		src="${pageContext.request.contextPath}/contents/advis/js/model/vue/socDme-controller.min.js?v=20190624"></script>

	<%@include file="../inc/bottom.jsp"%>