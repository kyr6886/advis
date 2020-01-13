<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>대응현황 분석</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
<script
	src="${pageContext.request.contextPath}/contents/advis/js/jquery-stringFormat.js"></script>
<script
	src="${pageContext.request.contextPath}/contents/advis/js/polyfill.min.js"></script>
<script
	src="${pageContext.request.contextPath}/contents/advis/js/extends.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap4/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/anl.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/openlayers3/css/ol.css">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/openlayers3/css/ol3gm.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
<script	src="${pageContext.request.contextPath}/css/bootstrap4/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/contents/base/fonts/style.css">
<style type="text/css">
.fa-check-circle{
	height:17px;
	padding-left:0px !important;
}
</style>
</head>
<body>

	<div id="loading-layer">
		<img id="loading-image" src="/images/ajax-loader.gif" alt="Loading..." />
	</div>


	<div class="container-fluid" id="scope-anl-rain" v-clock>

		<div class="row"></div>
		<div class="row flex-xl-nowrap">
			<div class="col-3">
				<div class="bd-sidebar">

					<div class="logo">
						<a href="#" class="simple-text logo-normal"
							style="background: url(/images/bg-login.png) no-repeat; background-size: cover; background-position: 60%;">
							<img src="/contents/advis/images/logo-login.png" style="height: 40px;">
						</a>
					</div>
					
					<div style="margin-top:15px;">
					<div class="row">
					<div class="col">
						<div class="tab">
							<ul>
								<li><a href="/analysis/damage">태풍</a></li>
								<li class="on"><a href="/analysis/rain/damage">호우</a></li>
								<li><a href="/analysis/earthquake/damage">지진</a></li>
								<li><a href="/analysis/ai/damage">전염병</a></li>
							</ul>
						</div>
					</div>
					</div>
					</div>
					<div class="row">
					<div class="col" style="padding-top:5px;">
					<div class="search">
					<div class="form-group">
						<select class="form-control form-control-sm" v-model="viewModel.selected.sido">
							<option value="">전국</option>
							<option v-for="item in viewModel.listSido" v-bind:value="item.code">{{item.sido}}</option>
							
						</select>
					</div>
					<div class="form-group">
					
						<select class="form-control form-control-sm"
							v-model="viewModel.selected.rain">
							<option value="">강우량(mm)</option>
							<option value="500-99999">500 이상</option>
							<option value="300-500">300 ~500 미만</option>
							<option value="200-300">200~300 미만</option>
							<option value="100-200">100~200 미만</option>
							<option value="1-100">1~100 미만</option>
						</select>
					</div>
					<div class="form-group">
						<select class="form-control form-control-sm"
							v-model="viewModel.selected.damageMoney">
							<option value="">피해액(억원)</option>
							<option value="1000-99999">1000 이상~</option>
							<option value="500-1000">500~1000 미만</option>
							<option value="100-500">100~500 미만</option>
							<option value="0-100">0~100 미만</option>
						</select>
					</div>
				
					<div class="form-group">
						<select class="form-control form-control-sm"
							v-model="viewModel.selected.damagePerson">
							<option value="">인명피해(명)</option>
							<option value="11-999">11명 이상</option>
							<option value="6-10">6~10 명</option>
							<option value="1-5">1~5 명</option>
						</select>
					</div>
					<div>
						<button type="button" class="btn btn-info btn-sm btn-block"
							v-on:click="fnOnClickBtnSearch()">검색</button>
					</div>
					</div>
					</div>
					</div>
					<div class="table-basic">
					<small class="text-right">(* 각 피해현황은 재해연보 기준 입니다.)</small>
					<table>
						<thead>
							<tr class="table-primary">
									<th scope="col" class="tbwid_30" style="font-size:13px !important">발생일</th>
									<th scope="col" class="tbwid_20" style="font-size:13px !important">강우량(mm)</th>
									<th scope="col" class="tbwid_30" style="font-size:13px !important">피해액(억원)</th>
									<th scope="col" class="tbwid_20" style="font-size:13px !important">인명피해(명)</th>
								</tr>
						</thead>
					</table>
					</div>
					<div class="table-wrap" style="max-height:450px;">
						<table class="table table-sm table-hover" cellpadding=0
							cellspacing=0 border=0>
							<thead>
								<tr class="table-primary">
									<th scope="col" class="tbwid_30">발생일</th>
									<th scope="col" class="tbwid_20">강우량(mm)</th>
									<th scope="col" class="tbwid_30">피해액(억원)</th>
									<th scope="col" class="tbwid_20">인명피해(명)</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="4"
										v-show="viewModel.listDamages.length==0"><small
										class="text-muted">{{viewModel.message.blank}}</small></td>
								</tr>
								<tr v-for="item in viewModel.listDamages">
									<td class="tbwid_30">
										<div
											 v-bind:beg_date="item.beg_date"
											v-bind:end_date="item.end_date">
											<div draggable="true" ondragstart="dragWord(event)">{{item.beg_date.dateFormat(".")}}</div>
										</div>
									</td>
									<td  class="tbwid_20">{{item.rn_day}}</td>
									<td class="tbwid_30">{{item.total_damage==null?'-':(item.total_damage/100000).toFixed(2)}}</td>
									<td class="tbwid_20">{{item.com_dme}}</td>
								</tr>
							</tbody>

						</table>
					</div>
				</div>
			</div>
			<div class="col">
				<div class="row drop-layer">
				
					<div class="col" >

						<div class="drop-area">
							<div  class="damage-summary" v-if="viewModel.rain.orgInfo!=null">
							<div class="d-flex justify-content-between" >
								<div class="p-2">
									<h4>
										<i class="icon-calendar"></i> 발생일:
											 ({{viewModel.rain.orgInfo.beg_date.dateFormat()}}~{{viewModel.rain.orgInfo.end_date.dateFormat()}})
										
									</h4>
								</div>
							</div>
							
							<div class="row p-2">
										<div class="col ">
										<small class="text-right">(* 피해현황, 인명피해:사망,실종)</small>
									<table class="table table-sm table-default ">
										<thead>
											<tr>
												<th>피해액(억원)</th>
												<th>인명피해(명)</th>
												<th>최대 강우</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>{{(viewModel.rain.orgInfo.total_damage/100000).toFixed().format()}}
												<span v-if="viewModel.rain.targetInfo!=null">
												<i class="fas fa-caret-up" style="color:red;font-size: 21px;" v-if="viewModel.rain.orgInfo.total_damage>viewModel.rain.targetInfo.total_damage"></i>
												<i class="fas fa-caret-down" style="color:red;font-size: 21px;" v-if="viewModel.rain.orgInfo.total_damage<viewModel.rain.targetInfo.total_damage"></i>
												</span>
												</td>
												<td>{{viewModel.rain.orgInfo.com_dme}}
												<span v-if="viewModel.rain.targetInfo!=null">
												<i class="fas fa-caret-up" style="color:red;font-size: 21px;" v-if="viewModel.rain.orgInfo.com_dme>viewModel.rain.targetInfo.com_dme"></i>
												<i class="fas fa-caret-down" style="color:red;font-size: 21px;" v-if="viewModel.rain.orgInfo.com_dme<viewModel.rain.targetInfo.com_dme"></i>
												</span>
												</td>
												<td style="font-size:13px;">{{viewModel.rain.orgInfo.obs_name}} ({{viewModel.rain.orgInfo.rn_day_max}} mm)
												<span v-if="viewModel.rain.targetInfo!=null">
												<i class="fas fa-caret-up" style="color:red;font-size:21px;" v-if="viewModel.rain.orgInfo.rn_day>viewModel.rain.targetInfo.rn_day"></i>
												<i class="fas fa-caret-down" style="color:red;font-size: 21px;" v-if="viewModel.rain.orgInfo.rn_day<viewModel.rain.targetInfo.rn_day"></i>
												</span>
												</td>
											</tr>
										</tbody>
									</table>
									
								</div>
							</div>
								
								<div class="row p-2">
										<div class="col ">
										<small class="text-right">(* 피해지역)</small>
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
											<tr v-for="(item,index) in viewModel.rain.listOrgYearDme" v-key="item.beg_date" v-if="index<5">
												<td>{{item.sido}}</td>
												<td>{{item.sigungu}}</td>
												<td>{{item.com_dme}}</td>
												<td>{{(item.total_damage/100000).toFixed(2).format()}}</td>
											</tr>
											<tr v-for="index in 5" v-key="index" v-if="viewModel.rain.listOrgYearDme.length-index<0">
												<td>-</td>
												<td>-</td>
												<td>-</td>
												<td>-</td>
											</tr>
										</tbody>
									</table>
									
								</div>
							</div>	
								
								
								<div class="p-2">
								<svg class="graph" id="orgChart" width="663"  height="300"  >
		            			<g></g></svg>
		            				<div id="orgChart-legend" >
								<table>
									<tr><td><div class="legend-symbol level5"></div></td><td>100~(억원)</td></tr>
									<tr><td><div class="legend-symbol level4"></div></td><td>20~100(억원)</td></tr>
									<tr><td><div class="legend-symbol level3"></div></td><td>10~20(억원)</td></tr>
									<tr><td><div class="legend-symbol level2"></div></td><td>1~10(억원)</td></tr>
									<tr><td><div class="legend-symbol level1"></div></td><td>피해없음</td></tr>
								</table>
							
							</div>
								</div>
								
							</div>
							
							<div class="row ">

								
								<div class="org-data-layer result-layer"
									v-for="item,index in viewModel.rain.listOrgTimely"
									:key="item.eventTime">

									<div class="result-area">
										<em>{{item.eventTime.dateFormat()}} </em>
									</div>

									<div class="row">
											<div class="p-3" style="width:100%" >
											<div v-bind:id="'org-map'+index" class="report-map"></div>
											</div>
			
									</div>
									<div class="row">
									<div class="p-3 fix-height fix-height-250" v-html="item.damageTxt" >
									
									</div>
									</div>
									<div class="p-1 list-750">


										<div style="padding-top: 15px;">
											<h5><button type="button" v-on:click="fnOnClickViewContent(true,'기상특보',item.eventTime)"><i class="icon-news"></i>기상 특보</button><sup><i class="icon-browser"></i></sup></h5>
										</div>
										<table class="table table-sm table-default">
											<thead>
												<tr>
													<th>호우주의보</th>
													<th>호우경보</th>
													<th>강풍주의보</th>
													<th>강풍경보</th>
													<th>풍랑주의보</th>
													<th>풍랑경보</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>&nbsp;<h5 class="far fa-check-circle text-info"
															v-if="item.kmaRs.rain_at"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kmaRs.rain_alert"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kmaRs.typhoon_at"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kmaRs.typhoon_alert"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kmaRs.wind_at"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kmaRs.wind_alert"></h5></td>
												</tr>
											</tbody>
										</table>


										<div style="padding-top: 15px;">
											<h5><i class="icon-control"></i><button type="button" v-on:click="fnOnClickViewContent(true,'통제상황',item.eventTime)">통제 현황</button><sup><i class="icon-browser"></i></sup></h5>
										</div>
										<table class="table table-sm table-default">
											<thead>
												<tr>
													<th>여객선</th>
													<th>국립공원</th>
													<th>도로</th>
													<th>항공</th>
													<th>항로</th>
													<th>기타</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>&nbsp;<h5 class="far fa-check-circle  text-info" v-if="item.control.ship"></h5></td>
													<td><h5 class="far fa-check-circle  text-info" v-if="item.control.park"></h5></td>
													<td><h5 class="far fa-check-circle  text-info" v-if="item.control.road"></h5></td>
													<td><h5 class="far fa-check-circle  text-info" v-if="item.control.flight"></h5></td>
													<td><h5 class="far fa-check-circle  text-info" v-if="item.control.sea"></h5></td>
													<td><h5 class="far fa-check-circle  text-info" v-if="item.control.etc"></h5></td>
												</tr>
											</tbody>
										</table>
										<div style="padding-top: 15px;">
											<h5 v-on:click="fnOnClickListManual(item.actions,item.eventTime)"><i class="icon-content"></i>대응현황 주요 키워드<sup><i class="icon-browser"></i></sup></h5>
										</div>
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
												<tr v-if="item.actions.isAction">
													<td class="cell"><div class="cell-item"
															v-for="val in item.actions.lvl1"><span v-on:click="fnOnClickManualTitle(val,item.eventTime)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl1.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl1.length">&nbsp;</div>
													</td>
													<td class="cell"><div class="cell-item"
															v-for="val in item.actions.lvl2"><span v-on:click="fnOnClickManualTitle(val,item.eventTime)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl2.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl2.length">&nbsp;</div>
													</td>
													<td class="cell"><div class="cell-item"
															v-for="val in item.actions.lvl3"><span v-on:click="fnOnClickManualTitle(val,item.eventTime)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl3.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl3.length">&nbsp;</div>
													</td>
													<td class="cell"><div class="cell-item"
															v-for="val in item.actions.lvl4"><span v-on:click="fnOnClickManualTitle(val,item.eventTime)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl4.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl4.length">&nbsp;</div>
													</td>
													<td class="cell"><div class="cell-item"
															v-for="val in item.actions.lvl5"><span v-on:click="fnOnClickManualTitle(val,item.eventTime)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl5.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl5.length">&nbsp;</div>
													</td>
													<td class="cell"><div class="cell-item"
															v-for="val in item.actions.lvl6"><span v-on:click="fnOnClickManualTitle(val,item.eventTime)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl6.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl6.length">&nbsp;</div>
													</td>
												</tr>

											</tbody>
										</table>
									</div>
								</div>
							
							</div> 
						</div>
						<div id="org-data" class="dropable-area" draggable="false" ondragover="event.preventDefault()" ondrop="dropWord(event)"></div>
					</div>
					<div class="col" >

						<div class="drop-area">
							<div  class="damage-summary"  v-if="viewModel.rain.targetInfo!=null">
							<div class="d-flex justify-content-between" >
								<div class="p-2">
									<h4>
										<i class="icon-calendar"></i> 발생일:
											 ({{viewModel.rain.targetInfo.beg_date.dateFormat()}}~{{viewModel.rain.targetInfo.end_date.dateFormat()}})
										
									</h4>
								</div>
							</div>
							
							<div class="row p-2">
										<div class="col ">
										<small class="text-right">(* 피해현황, 인명피해:사망,실종)</small>
									<table class="table table-sm table-default ">
										<thead>
											<tr>
												<th>피해액(억원)</th>
												<th>인명피해(명)</th>
												<th>최대 강우</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>{{(viewModel.rain.targetInfo.total_damage/100000).toFixed().format()}}</td>
												<td>{{viewModel.rain.targetInfo.com_dme}}</td>
												<td style="font-size:13px;">{{viewModel.rain.targetInfo.obs_name}} ({{viewModel.rain.targetInfo.rn_day}} mm)</td>
											</tr>
										</tbody>
									</table>
									
								</div>
							</div>
								
								<div class="row p-2">
										<div class="col ">
										<small class="text-right">(* 피해지역)</small>
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
											<tr v-for="(item,index) in viewModel.rain.listTargetYearDme" v-key="item.beg_date" v-if="index<5">
												<td>{{item.sido}}</td>
												<td>{{item.sigungu}}</td>
												<td>{{item.com_dme}}</td>
												<td>{{(item.total_damage/100000).toFixed(2).format()}}</td>
											</tr>
											<tr v-for="index in 5" v-key="index" v-if="viewModel.rain.listTargetYearDme.length-index<0">
												<td>-</td>
												<td>-</td>
												<td>-</td>
												<td>-</td>
											</tr>
										</tbody>
									</table>
									
								</div>
							</div>	
								
								
								<div class="p-2">
								<svg class="graph" id="targetChart" width="663"  height="300" >
		            			<g></g></svg>
		            			<div id="targetChart-legend" >
								<table>
									<tr><td><div class="legend-symbol level5"></div></td><td>100~(억원)</td></tr>
									<tr><td><div class="legend-symbol level4"></div></td><td>20~100(억원)</td></tr>
									<tr><td><div class="legend-symbol level3"></div></td><td>10~20(억원)</td></tr>
									<tr><td><div class="legend-symbol level2"></div></td><td>1~10(억원)</td></tr>
									<tr><td><div class="legend-symbol level1"></div></td><td>피해없음</td></tr>
								</table>
							
							</div>
								</div>
								
							</div>
							
							<div class="row ">

								
								<div class="target-data-layer result-layer"
									v-for="item,index in viewModel.rain.listTargetTimely"
									:key="item.eventTime">

									<div class="result-area">
										<em>{{item.eventTime.dateFormat()}} </em>
									</div>

									<div class="row">
											<div class="p-3" style="width:100%" >
											<div v-bind:id="'target-map'+index" class="report-map"></div>
											</div>
			
									</div>
									<div class="row">
									<div class="p-3 fix-height fix-height-250" v-html="item.damageTxt" >
									
									</div>
									</div>
									<div class="p-1 list-750">


										<div style="padding-top: 15px;">
											<h5><button type="button" v-on:click="fnOnClickViewContent(false,'기상특보',item.eventTime)"><i class="icon-news"></i>기상 특보</button><sup><i class="icon-browser"></i></sup></h5>
										</div>
										<table class="table table-sm table-default">
											<thead>
												<tr>
													<th>호우주의보</th>
													<th>호우경보</th>
													<th>강풍주의보</th>
													<th>강풍경보</th>
													<th>풍랑주의보</th>
													<th>풍랑경보</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>&nbsp;<h5 class="far fa-check-circle text-info" v-if="item.kmaRs.rain_at"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kmaRs.rain_alert"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kmaRs.typhoon_at"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kmaRs.typhoon_alert"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kmaRs.wind_at"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kmaRs.wind_alert"></h5></td>
												</tr>
											</tbody>
										</table>


										<div style="padding-top: 15px;">
											<h5><i class="icon-control"></i><button type="button" v-on:click="fnOnClickViewContent(false,'통제상황',item.eventTime)">통제 현황</button><sup><i class="icon-browser"></i></sup></h5>
										</div>
										<table class="table table-sm table-default">
											<thead>
												<tr>
													<th>여객선</th>
													<th>국립공원</th>
													<th>도로</th>
													<th>항공</th>
													<th>항로</th>
													<th>기타</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>&nbsp;<h5 class="far fa-check-circle  text-info" v-if="item.control.ship"></h5></td>
													<td><h5 class="far fa-check-circle  text-info" v-if="item.control.park"></h5></td>
													<td><h5 class="far fa-check-circle  text-info" v-if="item.control.road"></h5></td>
													<td><h5 class="far fa-check-circle  text-info" v-if="item.control.flight"></h5></td>
													<td><h5 class="far fa-check-circle  text-info" v-if="item.control.sea"></h5></td>
													<td><h5 class="far fa-check-circle  text-info" v-if="item.control.etc"></h5></td>
												</tr>
											</tbody>
										</table>
										<div style="padding-top: 15px;">
											<h5 v-on:click="fnOnClickListManual(item.actions,item.eventTime)"><i class="icon-content"></i>대응현황 주요 키워드<sup><i class="icon-browser"></i></sup></h5>
										</div>
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
												<tr v-if="item.actions.isAction">
													<td class="cell"><div class="cell-item"
															v-for="val in item.actions.lvl1"><span v-on:click="fnOnClickManualTitle(val,item.damageDay,viewModel.rain.targetInfo.typ_name)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl1.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl1.length">&nbsp;</div>
													</td>
													<td class="cell"><div class="cell-item"
															v-for="val in item.actions.lvl2"><span v-on:click="fnOnClickManualTitle(val,item.damageDay,viewModel.rain.targetInfo.typ_name)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl2.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl2.length">&nbsp;</div>
													</td>
													<td class="cell"><div class="cell-item"
															v-for="val in item.actions.lvl3"><span v-on:click="fnOnClickManualTitle(val,item.damageDay,viewModel.rain.targetInfo.typ_name)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl3.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl3.length">&nbsp;</div>
													</td>
													<td class="cell"><div class="cell-item"
															v-for="val in item.actions.lvl4"><span v-on:click="fnOnClickManualTitle(val,item.damageDay,viewModel.rain.targetInfo.typ_name)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl4.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl4.length">&nbsp;</div>
													</td>
													<td class="cell"><div class="cell-item"
															v-for="val in item.actions.lvl5"><span v-on:click="fnOnClickManualTitle(val,item.damageDay,viewModel.rain.targetInfo.typ_name)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl5.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl5.length">&nbsp;</div>
													</td>
													<td class="cell"><div class="cell-item"
															v-for="val in item.actions.lvl6"><span v-on:click="fnOnClickManualTitle(val,item.damageDay,viewModel.rain.targetInfo.typ_name)"><span v-on:click="fnOnClickManualTitle(val)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl6.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl6.length">&nbsp;</div>
													</td>
												</tr>

											</tbody>
										</table>
									</div>
								</div>
							
							</div> 
						</div>
						<div id="target-data" class="dropable-area" draggable="false" ondragover="event.preventDefault()" ondrop="dropWord(event)"></div>
					</div>
			</div>

		</div>

	</div>
	<!-- tooltop -->
								<div id="orgChart-tooltip" v-on:click="fnHideTooltip('orgChart-tooltip')" style="display:none;width:230px;height:60px;background-color:#ffffff;position:absolute;z-index:999;border:solid 1px #ddd;">
									<table  class="table table-sm table-default">
										<tr><th>구분</th><th>피해금액(억원)</th></tr>
										<tr><td>{{viewModel.selectedOrgNode.name}}</td><td>{{(viewModel.selectedOrgNode.value/100000).toFixed(2).format()}}</td></tr>
									</table>
								</div>

	<div id="targetChart-tooltip" v-on:click="fnHideTooltip('targetChart-tooltip')"  style="display:none;width:230px;height:60px;background-color:#ffffff;position:absolute;z-index:999;border:solid 1px #ddd;">
		<table class="table table-sm table-default" >
			<tr><th>구분</th><th>피해금액(억원)</th></tr>
			<tr><td>{{viewModel.selectedTargetNode.name}}</td><td>{{(viewModel.selectedTargetNode.value/100000).toFixed(2).format()}}</td></tr>
		</table>
	</div>
	<!-- Modal -->
<div class="modal" id="contentViewModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">{{modal.evtDay.dateFormat()}} {{modal.title}}</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        	<div v-for="item in modal.contents">
        		<h6>{{item.contentTitle}}</h6>
        		<p v-html="item.content"></p>
        	</div>
        	
        	<div v-for="item in modal.manual_contents">
        		<div v-for="sub in item.list">
        			<h6>{{sub.manual_contents}}</h6>
        		</div>
        	</div>
        	
      </div>
     
    </div>
  </div>
</div>

	<script
		src="${pageContext.request.contextPath}/openlayers3/build/ol.js"></script>
	<script
		src="${pageContext.request.contextPath}/openlayers3/build/ol3gm.js"></script>
	<script type="text/javascript">

		
		var vueObj = null;
		function dragWord(dragEvent) {
			console.log("dragEvent");
			console.log($(dragEvent.target.parentNode));
			$("#org-data").show();
			$("#target-data").show();
			dragEvent.dataTransfer.setData("text", dragEvent.target.textContent
					+ "|" + dragEvent.target.parentNode.getAttribute("beg_date")
					+ "|" + dragEvent.target.parentNode.getAttribute("end_date")
			);
		}

		function dropWord(dropEvent) {
			$("#org-data").hide();
			$("#target-data").hide();

			var dropData = dropEvent.dataTransfer.getData("text");
			var isValid = false;
			if (dropEvent.target.id == "org-data") {
				$(".org-data-layer").find(".report-map").html("");
				isValid = true;
			}
			if (dropEvent.target.id == "target-data") {
				$(".target-data-layer").find(".report-map").html("");
				isValid = true;
			}

			if (!isValid)
				return false;

			var dropItems = dropData.split("|");
			var dataBegDate = dropItems[1];
			var dataEndDate = dropItems[2];
			//dropEvent.target.textContent = dropItems[0];
			vueObj.fnOnDropDamageInfo(dropEvent.target.id, dataBegDate,dataEndDate);
			dropEvent.preventDefault();
		}

		function pageLoad(obj) {
			vueObj = obj;
			
		}
	
	</script>
	<script
		src="${pageContext.request.contextPath}/contents/advis/js/model/vue/analisysRain-controller.min.js"></script>
</body>
</html>