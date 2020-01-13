<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>대응현황 분석</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
<script src="${pageContext.request.contextPath}/contents/advis/js/jquery-stringFormat.js"></script>
<script src="${pageContext.request.contextPath}/contents/advis/js/polyfill.min.js"></script>
<script src="${pageContext.request.contextPath}/contents/advis/js/extends.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap4/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/anl.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/openlayers3/css/ol.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/openlayers3/css/ol3gm.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/contents/base/fonts/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">
<script src="${pageContext.request.contextPath}/css/bootstrap4/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container-fluid" id="scope-anl-earthquake"  v-clock>
		<div id="loading-layer"><div class="loading-content">{{viewModel.message}}</div></div>
		<div class="row">
			<div class="col-3">
				<div class="bd-sidebar">

					<div class="logo">
						<a href="#" class="simple-text logo-normal"
							style="background: url(/contents/advis/images/bg-login.png) no-repeat; background-size: cover; background-position: 60%;">
							<img src="/contents/advis/images/logo-login.png"
							style="height: 40px;">
						</a>
					</div>
					<div style="margin-top:15px;">
					<div class="row">
					<div class="col">
						<div class="tab">
							<ul>
								<li><a href="/analysis/damage">태풍</a></li>
								<li><a href="/analysis/rain/damage">호우</a></li>
								<li  class="on"><a href="/analysis/earthquake/damage">지진</a></li>
								<li><a href="/analysis/ai/damage">전염병</a></li>
							</ul>
						</div>
					</div>
					</div>
					</div>
					<br>
					<div class="table-basic">
					<small class="text-right">(* 각 피해현황은 재해연보 기준 입니다.)</small>
					<table >
						<thead>
							<tr class="table-primary">
								<th scope="col" class="tbwid_30">발생일</th>
								<th scope="col" class="tbwid_30">피해액(억원)</th>
								<th scope="col" class="tbwid_20">인명피해(명)</th>
							</tr>
						</thead>
					</table>
					</div>
					<div class="table-wrap">
					<div class="table-basic">
						<table class="table-hover"  style="border-top:0px;">
							<thead>
								<tr class="table-primary">
									<th scope="col" class="tbwid_30">발생일</th>
									<th scope="col" class="tbwid_30">피해액(억원)</th>
									<th scope="col" class="tbwid_20">인명피해(명)</th>
								</tr>
							</thead>
							<tbody>
								<tr v-for="item in viewModel.listDamages">
									<td class="tbwid_30" v-on:click="fnOnChangeSelectedDme(item)">
										<div v-bind:beg_date="item.beg_date"
											v-bind:end_date="item.end_date">
											<div>{{item.beg_date.dateFormat(".")}}</div>
										</div>
									</td>
									<td class="tbwid_30">{{item.total_damage==null?'-':(item.total_damage/100000).toFixed(2)}}</td>
									<td class="tbwid_20">{{item.com_dme}}</td>
								</tr>
							</tbody>
						</table>
						</div>
					</div>
				</div>
			</div>
			<div class="col">
				<div class="d-flex justify-content-between">
					<div class="result-area">
						<div class="row" >
							<div class="col-7">
								<em>지진 발생일 : {{viewModel.selected.damageBegDate.dateFormat()}} </em>
								<em v-show="viewModel.selected.damageAfterCount>0">(본진 : {{viewModel.selected.damageOccurScale}}, 누적여진 : {{viewModel.selected.damageAfterCount}}회)</em>
								<em v-show="viewModel.selected.damageAfterCount==0">(본진 : {{viewModel.selected.damageOccurScale}})</em>
							</div>
							
						</div>
					</div>
				</div>
				<div class="row" style="height:470px;">
					<div class="col-7">
						<!-- full size map -->
						<div class="card">
							<div id="map" class="full-map"></div>
						</div>
						
						<div class="card overlay-map">
							<div id="zoom-map" class="zoom-map"></div>
							<div id="zoom-map-legend" class="legend-table">
							  <table>
							  	<tr><th class="lvl lvl1">2</th><th class="lvl lvl2">3</th><th class="lvl lvl3">4</th></tr>
							  	<tr><td>2~3 미만</td><td>3~4 미만</td><td>4 이상~</td></tr>
							  </table>
							</div>
						</div>
						
					</div>
					<div class="col">
						
						<h4><i class="icon-damage"></i>피해현황<small>(인명피해: 사망, 실종)</small></h4>
						<table class="table table-sm table-default ">
							<tbody>
								<tr>
									<th class="tbwid_30">인명피해(명)</th>
									<td>{{viewModel.selected.damageEq.com_dme}}</td>
								</tr>
								<tr>
									<th>재산 피해액(억원)</th>
									<td>{{(viewModel.selected.damageEq.com_total/100000).toFixed(2).format()}}</td>
								</tr>
								<tr>
									<th>사유시설 피해액(억원)</th>
									<td>{{(viewModel.selected.damageEq.pri_total/100000).toFixed(2).format()}}</td>
								</tr>
								<tr>
									<th>공공시설 피해액(억원)</th>
									<td>{{(viewModel.selected.damageEq.pub_total/100000).toFixed(2).format()}}</td>
								</tr>
								<tr>
									<th class="last-th">총 피해액(억원)</th>
									<td>{{(viewModel.selected.damageEq.total_damage/100000).toFixed(2).format()}}</td>
								</tr>
							</tbody>
						</table>
						
						<table class="table table-sm table-default ">
							<thead>
								<tr>
									<th>시도</th>
									<th>시군구</th>
									<th>인명 피해(명)</th>
									<th>재산 피해(억원)</th>
								</tr>
							</thead>
							<tbody>
									<tr v-for="(item,index) in viewModel.listOrgYearDme"
										v-key="item.beg_date" v-if="index<3">
										<td>{{item.sido}}</td>
										<td>{{item.sigungu}}</td>
										<td>{{item.com_dme}}</td>
										<td>{{(item.total_damage/100000).toFixed(2).format()}}</td>
									</tr>
									<tr v-for="index in 5" v-key="index"
										v-if="viewModel.listOrgYearDme.length-index<0">
										<td>-</td>
										<td>-</td>
										<td>-</td>
										<td>-</td>
									</tr>
								</tbody>
						</table>
					</div>
				</div>
				<br>
				<div class="row">

					<div class="col-7">

						<!-- 일별 지진 횟수 -->
						<h4>
							<i class="icon-chart"></i> 일별 발생횟수<small>(진도 2.0 이상 )</small>
						</h4>
						<div class="card">
							<div class="chart card-body">

								<canvas class="chart" id="chart-daily" style="width:780px;height:200px;"></canvas>
							</div>
						</div>
					</div>
					<div class="col">
						<!-- 일별 지진 크기 -->
						<h4>
							<i class="icon-chart"></i> 규모별 발생횟수<small>( 진도 2.0 이상 <em style="color:red;">총 {{viewModel.eventInfo.locations.length}} 회 발생</em> )</small>
						</h4>
						<div class="card">
							<div class="chart card-body">
								<canvas class="chart" id="chart-pw" style="width:540px;height:200px;"></canvas>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row"  style="padding-top: 15px;">
					<div class="col-7" >
						<!-- 대응현황 -->
						<h4><i class="icon-content"></i> 대응현황 주요 키워드</h4>
						<div>
							<table class="table table-sm table-default">
								<thead>
								<tr><th>전파</th> <th>점검</th> <th>지시</th> <th>파견</th> <th>회의</th> <th>대피</th></tr>
								</thead> 
								<tbody>
									<tr v-if="viewModel.eventAction.isAction">
										<td class="cell">
											<div class="cell-item"
												v-for="item in viewModel.eventAction.lvl1">
												<span v-on:click="fnOnClickManualTitle(item)">{{item.title}}</span>
											</div>
											<div class="cell-item"
												v-if="viewModel.eventAction.size>viewModel.eventAction.lvl1.length"
												v-for="item in viewModel.eventAction.size-viewModel.eventAction.lvl1.length">
												<span>&nbsp;</span></div>
										</td>
										<td class="cell">
											<div class="cell-item"
												v-for="item in viewModel.eventAction.lvl2">
												<span v-on:click="fnOnClickManualTitle(item)">{{item.title}}</span>
											</div>
											<div class="cell-item"
												v-if="viewModel.eventAction.size>viewModel.eventAction.lvl2.length"
												v-for="item in viewModel.eventAction.size-viewModel.eventAction.lvl2.length">
												<span>&nbsp;</span></div>
										</td>
										<td class="cell">
											<div class="cell-item"
												v-for="item in viewModel.eventAction.lvl3">
												<span v-on:click="fnOnClickManualTitle(item)">{{item.title}}</span>
											</div>
											<div class="cell-item"
												v-if="viewModel.eventAction.size>viewModel.eventAction.lvl3.length"
												v-for="item in viewModel.eventAction.size-viewModel.eventAction.lvl3.length">
												<span>&nbsp;</span></div>
										</td>
										<td class="cell">
											<div class="cell-item"
												v-for="item in viewModel.eventAction.lvl4">
												<span v-on:click="fnOnClickManualTitle(item)">{{item.title}}</span>
											</div>
											<div class="cell-item"
												v-if="viewModel.eventAction.size>viewModel.eventAction.lvl4.length"
												v-for="item in viewModel.eventAction.size-viewModel.eventAction.lvl4.length">
												<span>&nbsp;</span></div>
										</td>
										<td class="cell">
											<div class="cell-item"
												v-for="item in viewModel.eventAction.lvl5">
												<span v-on:click="fnOnClickManualTitle(item)">{{item.title}}</span>
											</div>
											<div class="cell-item"
												v-if="viewModel.eventAction.size>viewModel.eventAction.lvl5.length"
												v-for="item in viewModel.eventAction.size-viewModel.eventAction.lvl5.length">
												<span>&nbsp;</span></div>
										</td>
										<td class="cell">
											<div class="cell-item"
												v-for="item in viewModel.eventAction.lvl6">
												<span v-on:click="fnOnClickManualTitle(item)">{{item.title}}</span>
											</div>
											<div class="cell-item"
												v-if="viewModel.eventAction.size>viewModel.eventAction.lvl6.length"
												v-for="item in viewModel.eventAction.size-viewModel.eventAction.lvl6.length">
												<span>&nbsp;</span></div>
										</td>
									</tr>
									<tr v-if="viewModel.eventAction.isAction==false">
										<td colspan="6">대응현황 내용이 없습니다.</td>
									</tr>
	
								</tbody>
							</table>
						</div>
					</div>
					<div class="col">
						<h4><i class="icon-content"></i> 대응 내용</h4>
						<table class="table table-sm table-default">
							<thead>
								<tr><th>대응 주요 키워드 내용</th></tr>
							</thead>
							
							<tbody v-if="viewModel.eventAction.size>0">
								<tr v-if="viewModel.eventAction.lvl1.length!==0" v-for="item in viewModel.eventAction.lvl1">
								<td>{{item.manual_contents}}</td>
								</tr>
								<tr v-if="viewModel.eventAction.lvl2.length!==0" v-for="item in viewModel.eventAction.lvl2">
									<td>{{item.manual_contents}}</td>
								</tr>
								<tr v-if="viewModel.eventAction.lvl3.length!==0" v-for="item in viewModel.eventAction.lvl3">
									<td>{{item.manual_contents}}</td>
								</tr>
								<tr v-if="viewModel.eventAction.lvl4.length!==0" v-for="item in viewModel.eventAction.lvl4">
									<td>{{item.manual_contents}}</td>
								</tr>
								<tr v-if="viewModel.eventAction.lvl5.length!==0" v-for="item in viewModel.eventAction.lvl5">
									<td>{{item.manual_contents}}</td>
								</tr>
								<tr v-if="viewModel.eventAction.lvl6.length!==0" v-for="item in viewModel.eventAction.lvl6">
									<td>{{item.manual_contents}}</td>
								</tr>
							</tbody>
							<tbody v-if="viewModel.eventAction.size==0">
								<tr>
								<td>대응 내용이 없습니다.</td>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
				
				<div class="row">
				
					<div class="col-12 "  style="padding-top: 15px;">
						<!-- 대응현황 챠트 -->
						<h4><i class="icon-content"></i> 대응현황</h4>
						<div class="report-row-nolimit">
							<div v-show="viewModel.keyListView!=null && viewModel.keyListView.length>0">
								<div class="time-table result-info-half">
									<table>
											<tr>
												<th></th>
												<th v-for="item in viewModel.keyListView">
													<div v-if="item.detail.length>0">
														<div v-if="item.date.substring(6)=='00:00'">{{item.date.substring(0,6)}}</div>
														<div v-if="item.date.substring(6)!='00:00'">{{item.date}}</div>
													</div>
												</th>
											</tr>
											<tr v-for="keyItem in viewModel.orgList">
												<td class='noBorder text-right'>{{keyItem}}</td>
												<td v-for="item in viewModel.keyListView">
													<div v-for="dItem in item.detail">
														<div v-if="keyItem == dItem.org" class="action-color"v-on:click="fnClickEventLink(dItem)">&nbsp;</div>
													</div>
												</td>
											</tr>
										</table>
								</div>
							</div>
							<div v-show="viewModel.keyListView===null||viewModel.keyListView.length==0">
								<div class="time-table result-info">대응현황 내용이 없습니다.</div>
							</div>
						</div>
					</div>
					</div>
					
			</div>

		</div>


		<!-- Modal -->
		<div class="modal" id="contentViewModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="z-index: 9999;">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 v-if="modal.title=='대응현황'" class="modal-title" id="exampleModalLabel">{{modal.evtDay.dateFormat()}}
							{{modal.title}}</h5>
						<h5 v-if="modal.title=='대처사항'" class="modal-title" id="exampleModalLabel">{{modal.evtDay}} {{modal.title}}</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div v-for="item in modal.contents">
<!-- 							<h4>{{item.contentTitle}}</h4> -->
							<p class="small-font" v-html="item.content"></p>
						</div>

						<div v-for="item in modal.manual_contents">
							<div v-for="sub in item.list">
								<h4>{{sub.manual_contents}}</h4>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- tooltop -->
		<div id="orgChart-tooltip"
			v-on:click="fnHideTooltip('orgChart-tooltip')"
			style="display: none; width: 230px; height: 60px; background-color: #ffffff; position: absolute; z-index: 999; border: solid 1px #ddd;">
			<table class="table table-sm table-default">
				<tr>
					<th>구분</th>
					<th>피해금액(억원)</th>
				</tr>
				<tr>
					<td>{{viewModel.selectedOrgNode.name}}</td>
					<td>{{(viewModel.selectedOrgNode.value/100000).toFixed(2).format()}}</td>
				</tr>
			</table>
		</div>

	</div>
	
<!-- <script type="text/javascript" src="http://map.vworld.kr/js/vworldMapInit.js.do?version=2.0&apiKey=A0D9DD95-A3F1-37ED-B898-E6716A7B6E84"></script> -->
	
	<script src="${pageContext.request.contextPath}/openlayers3/build/ol.js"></script>
	<script src="${pageContext.request.contextPath}/openlayers3/build/ol3gm.js"></script>
	<script src="${pageContext.request.contextPath}/map/map.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/openlayers3/css/ol.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/openlayers3/css/ol3gm.css">
	<script type="text/javascript">
		var vueObj = null;
		function pageLoad(obj) {
			vueObj = obj;
		}
	</script>
	<script	src="${pageContext.request.contextPath}/contents/advis/js/model/vue/analisysEarthquake-controller.min.js"></script>
</body>
</html>