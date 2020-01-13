<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="../advis/inc/top.jsp"%>

<div id="scope-main" v-cloak>

	<div class="contents-title">
		<h2>관심지역 이달의 예방/대비 재해</h2>

	</div>

	<sction class="main-divide">
		<div id="rank">
			<ul class="list-inline">
				<li v-for="(item,index) in viewModel.listDamageRank" v-if="index<5"><span class="label-number label-info">{{index+1}}</span>
				<span v-on:click="fnOnClickDamageType(item)">{{item.name}}</span>
				</li>
				
			</ul>
		</div>
	<div class='panel-left content-issue'>
		<div class='panel-left'>
			<div id="content-issue-txt">
				<div class="section-title"><ul><li>피해사례({{viewModel.selectedDamageTypes.name}})</li></ul></div>
				<div class="table-basic">
					<table v-if="viewModel.selectedDamageTypes.isSocDisYn=='N'">
						<tr><th>피해 시설</th><th>피해액</th></tr>
						<tr v-for="(item,index) in viewModel.listDamageTypes" v-if="index<5"><td>{{item.damage_name}}</td><td>{{(item.damage_money/100000).toFixed(2)}}(억원)</td></tr>
					</table>
					<table v-if="viewModel.selectedDamageTypes.isSocDisYn=='Y'" style="height:250px;">
						<tr><th style="width:150px;">사고명</th><td>{{viewModel.selectedDamageTypes.info}}</td></tr>
						<tr><th>인명피해</th><td>{{viewModel.selectedDamageTypes.damage}}(명)</td></tr>
						<tr><th>개요</th><td class="text-left">{{viewModel.selectedDamageTypes.description}}</td></tr>
					</table>
				</div>
			</div>
		</div>
		<div class="panel-left">
			<canvas id="chart-radar" style="width: 350px; height: 310px;margin-top:20px;"></canvas>
		</div>
	</div>
	
	<form method="get" name="categorySearchForm" action="/advis/search/category/list" id="categorySearchForm">
		<div class='panel-right  content-keyword'>
		<div class="section-title"><ul><li>재해 카테고리별 검색</li></ul></div>
			<div>
				대분류 : 
				<select class="main-select bg-blue" v-on:change="fnDisCodeList(2)"v-model="selectedLargeCode">
				<option value="">대분류 전체</option>
				<option v-for="item in listLargeCode" v-bind:value="item.ctg_id"> {{item.title}}</option>
				</select>
			</div>
			<div>
				중분류 : 
				<select class="main-select" v-on:change="fnDisCodeList(3)" v-model="selectedMediumCode">
				<option value="">중분류 전체</option>
				<option v-for="item in listMediumCode" v-bind:value="item.ctg_id"> {{item.title}}</option>
				</select>
			</div>
			<div>
				소분류 : 
				<select class="main-select" v-on:change="fnDisCodeList(4)" v-model="selectedSmallCode">
				<option value="">소분류 전체</option>
				<option v-for="item in listSmallCode" v-bind:value="item.ctg_id"> {{item.title}}</option>
				</select> 
			</div>
			<div>
				세분류 : 
				<select class="main-select" v-on:change="fnDisCodeList(5)" v-model="selectedDetailCode" >
				<option value="">세분류 전체</option>
				<option v-for="item in listDetailCode" v-bind:value="item.ctg_id"> {{item.title}}</option>
				</select>
			</div>
			<div> 
				<a class="btn btn-blue-main" v-on:click="fnOnClickCategory()">
					<i class="ic-search mr05"></i>검색
				</a>
			</div>
			
			<input type="hidden" id="paramCtgId" name="paramCtgId">
			<input type="hidden" id="paramDepth" name="paramDepth">
			<input type="hidden" name="toMonth" value="${toMonth}"/>
			<input type="hidden" name="monthDisaster" value="${monthDisaster}"/>
	<!-- 	<div> -->
	<!-- 	<ul class='list-block'> -->
	<!-- 	<li><span class="label-number label-info">1</span> <span><a href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/contents/prevent/prevent01.html?menuSeq=126" target="_blank">국민행동요령</a></span></li> -->
	<!-- 	<li><span class="label-number label-info">2</span> <span><a href="" target="_blank">특별관심지구</a></span></li> -->
	<!-- 	<li><span class="label-number label-info">3</span> <span>재난대응 매뉴얼</span></li> -->
	<!-- 	<li><span class="label-number label-info">4</span> <span><a href="javascript:window.open('/advis/damage/station/list','재해위험지구','width=1450px height=800px;')" >재해위험지구 현황</a></span></li> -->
	<!-- 	<li><span class="label-number label-info">5</span> <span>재난 및 안전관리 기본법</span></li> -->
	<!-- 	<li><span class="label-number label-info">6</span> <span>비상근무 기준 및 현황</span></li> -->
	<!-- 	<li><span class="label-number label-info">7</span> <span>시설안전 기준</span></li></ul> -->
	<!-- 	</div> -->
		</div>
		
		
	</form>
	</sction>


	<div class="contents-title">
		<h2>
			피해 현황 <span>{{viewModel.stDate}} ~ {{viewModel.endDate}}년 까지의
				재산, 인명 피해 통계 내용입니다.</span>
		</h2>
		<!-- <ul class="breadcrumb">
		<li>재난안전데이터 정보제공시스템</li>
		<li class="current">피해현황</li>
	</ul> -->
	</div>

	<section class="main-divide">
		<div class="section-title">
			<ul>
				<li>{{viewModel.selectedDamage.damageType}}</li>
				<li>{{viewModel.selectedDamage.damageName}}</li>
			</ul>
		</div>
		<div class="select-area">
			<svg class="graph" id="treeMap" width="920" height="300"
				viewBox="0 0 920 300">
            <g>
            
            </g>
       </svg>

		</div>
		<div class="result-area">
			<div class="table-basic">
				<table>
					<caption>피해현황 자연재난</caption>
					<thead>
						<tr>
							<th scope="col">재해유형</th>
							<th scope="col">시설피해(억원)</th>
							<th scope="col">인명피해(명)</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="item,index in viewModel.listDamages" v-if="index<6">
							<td>{{item.codeName}}</td>
							<td>{{(parseInt(item.damageTotal/100000)).format()}}</td>
							<td>{{item.damagePerson}}</td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
	</section>


	<div class='container-mid'>
		<div class='panel-left content-graph'>
			<div class="section-title">
				<ul>
					<li>시군구별 피해현황</li>
				</ul>
			</div>
			<section class="graph1">
				<canvas id="chart1" style="width: 918px; height: 420px;"></canvas>
			</section>
		</div>
		<div class='panel-right content-news'>
			<div class="section-title">
				<ul>
					<li>관련 재난 뉴스</li>
				</ul>
			</div>
			<div class="table-basic">
				<table>
					<tr>
						<th>제목</th>
						<th>작성일</th>
					</tr>
					<tr v-for="item,index in viewModel.listNews" v-if="index<9">
						<td class="text-left-width"><a v-bind:href='item.url'target="_blank" v-html="item.printTitle"></a></td>
						<td>{{item.datetime.dateFormat()}}</td>
					</tr>
				</table>
			</div>

		</div>
	</div>

	<div class="section-title">
		<ul>
			<li>코드 등록/조회 현황</li>
		</ul>
	</div>

	<section class="graph2">
		<canvas id="chart2" style="width: 1418px; height: 400px;"></canvas>
	</section>
	
	<input type="hidden" name="toMonth" value="${toMonth}"/>
	<input type="hidden" name="monthDisaster" value="${monthDisaster}"/>
</div>
<script type="text/javascript">
	function pageLoad(obj) {
		obj.fnlistCheckedArea();
	
		var param = {
			text : '${toMonth}월 ${monthDisaster.codeName}',
			sort : 'accuracy',
			size : 50,
			page : 1
		};
		
		obj.fnGetDaumNews(param);
		//tb_accident_soc
		obj.fnGetAccidentSoc();
		obj.fnDisCodeList(2);
	}
</script>
<script	src="${pageContext.request.contextPath}/contents/advis/js/model/vue/main-controller.min.js"></script>
<%@include file="../advis/inc/bottom.jsp"%>
