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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/contents/base/fonts/style.css">
<script src="${pageContext.request.contextPath}/css/bootstrap4/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid" id="scope-anl-ai" v-clock>
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
								<li><a href="/analysis/earthquake/damage">지진</a></li>
								<li class="on"><a href="/analysis/ai/damage">전염병</a></li>
							</ul>
						</div>
					</div>
					</div>
					</div>
					<br>
						<div class="table-basic">
						<table>
						<thead>
							<tr class="table-primary">
								<th scope="col" class="tbwid_30">발생일</th>
								<th scope="col" class="tbwid_30">종료일</th>
								<th scope="col" class="tbwid_20">발생지역(개)</th>
							</tr>
						</thead>
					</table>
					</div>
					<div class="table-wrap">
						<div class="table-basic">
						<table  style="border-top:0px;">
							<thead>
								<tr class="table-primary">
									<th scope="col" class="tbwid_30">발생일</th>
									<th scope="col" class="tbwid_30">종료일</th>
									<th scope="col" class="tbwid_20">발생지역(개)</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="tbwid_30">2017-06-05</td>
									<td class="tbwid_30">2017-06-23</td>
									<td class="tbwid_20">{{viewModel.selected.dongCount}}</td>
								</tr>
							</tbody>
						</table>
						</div>
					</div>
				</div>
			</div>
			<div class="col-9">
				<div class="d-flex justify-content-between">
					<div class="result-area">
						<div class="row">
							<div class="col-12">
								<em>고병원성 조류인플루엔자:
									{{viewModel.selected.damageBegDate.dateFormat()}} ~
									{{viewModel.selected.damageEndDate.dateFormat()}}
								</em> 
								<em>(발생지역 :{{viewModel.selected.dongCount}} 개 읍면동)
								</em>
							</div>
						</div>
					</div>
				</div>
				<div class="row" style="height: 670px;">
					<div class="col-6">
						<!-- full size map -->
						<div class="card">
							<div id="map" class="full-map" style="height:670px;"></div>
						</div>
					</div>
					<div class="col-6">
						<div style="padding-bottom: 15px;">
							<!-- 일별 발생농장 횟수 -->
							<h4><i class="icon-chart"></i> 일별 발생농장 횟수</h4>
							<div class="card" style="overflow-x: scroll;">
								<div class="chart card-body">
									<canvas class="chart" id="chart-daily" style="width:780px;height:160px;"></canvas>
								</div>
							</div>
						</div>
						<div style="padding-bottom: 15px;">
							<!-- 일별 살처분 횟수 -->
							<h4><i class="icon-chart"></i> 일별 살처분 횟수</h4>
							<div class="card" style="overflow-x: scroll;">
								<div class="chart card-body">
									<canvas class="chart" id="chart-pw" style="width:780px;height:160px;"></canvas>
								</div>
							</div>
						</div>
						<div>
							<!-- 대응현황 -->
							<h4><i class="icon-content"></i> 대응현황 주요 키워드</h4>
							<div>
								<table class="table table-sm table-default">
									<thead>
										<tr>
											<th>전파</th>
											<th>점검</th>
											<th>지시</th>
											<th>파견</th>
											<th>회의</th>
											<th>대피</th>
										</tr>
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
													<span>&nbsp;</span>
												</div>
											</td>
											<td class="cell">
												<div class="cell-item"
													v-for="item in viewModel.eventAction.lvl2">
													<span v-on:click="fnOnClickManualTitle(item)">{{item.title}}</span>
												</div>
												<div class="cell-item"
													v-if="viewModel.eventAction.size>viewModel.eventAction.lvl2.length"
													v-for="item in viewModel.eventAction.size-viewModel.eventAction.lvl2.length">
													<span>&nbsp;</span>
												</div>
											</td>
											<td class="cell">
												<div class="cell-item"
													v-for="item in viewModel.eventAction.lvl3">
													<span v-on:click="fnOnClickManualTitle(item)">{{item.title}}</span>
												</div>
												<div class="cell-item"
													v-if="viewModel.eventAction.size>viewModel.eventAction.lvl3.length"
													v-for="item in viewModel.eventAction.size-viewModel.eventAction.lvl3.length">
													<span>&nbsp;</span>
												</div>
											</td>
											<td class="cell">
												<div class="cell-item"
													v-for="item in viewModel.eventAction.lvl4">
													<span v-on:click="fnOnClickManualTitle(item)">{{item.title}}</span>
												</div>
												<div class="cell-item"
													v-if="viewModel.eventAction.size>viewModel.eventAction.lvl4.length"
													v-for="item in viewModel.eventAction.size-viewModel.eventAction.lvl4.length">
													<span>&nbsp;</span>
												</div>
											</td>
											<td class="cell">
												<div class="cell-item"
													v-for="item in viewModel.eventAction.lvl5">
													<span v-on:click="fnOnClickManualTitle(item)">{{item.title}}</span>
												</div>
												<div class="cell-item"
													v-if="viewModel.eventAction.size>viewModel.eventAction.lvl5.length"
													v-for="item in viewModel.eventAction.size-viewModel.eventAction.lvl5.length">
													<span>&nbsp;</span>
												</div>
											</td>
											<td class="cell">
												<div class="cell-item"
													v-for="item in viewModel.eventAction.lvl6">
													<span v-on:click="fnOnClickManualTitle(item)">{{item.title}}</span>
												</div>
												<div class="cell-item"
													v-if="viewModel.eventAction.size>viewModel.eventAction.lvl6.length"
													v-for="item in viewModel.eventAction.size-viewModel.eventAction.lvl6.length">
													<span>&nbsp;</span>
												</div>
											</td>
										</tr>
										<tr v-if="viewModel.eventAction.isAction==false">
											<td colspan="6">대응현황 내용이 없습니다.</td>
										</tr>

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-12">
						<div style="padding-top: 15px;">
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
		</div>

		<!-- Modal -->
		<div class="modal" id="contentViewModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="z-index: 9999;">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 v-if="modal.title=='대응현황'" class="modal-title" id="exampleModalLabel">{{modal.evtDay.dateFormat()}} {{modal.title}}</h5>
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

		<div id="popup" class="overlay">
			<div id="popup-content"></div>
		</div>


	</div>

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
	<script src="${pageContext.request.contextPath}/contents/advis/js/model/vue/analisysAi-controller.min.js"></script>
</body>
</html>