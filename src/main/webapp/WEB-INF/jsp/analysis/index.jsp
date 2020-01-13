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
	
<script	src="${pageContext.request.contextPath}/contents/advis/js/extends.js"></script>

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
	
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/contents/base/fonts/style.css">
	
<script	src="${pageContext.request.contextPath}/css/bootstrap4/js/bootstrap.min.js"></script>
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


	<div class="container-fluid" id="scope-anl" v-clock>

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
							<li class="on"><a href="">태풍</a></li>
							<li><a href="/analysis/rain/damage">호우</a></li>
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
								<th scope="col" class="tbwid_20">재난명</th>
								<th scope="col" class="tbwid_20">발생일</th>
								<th scope="col" class="tbwid_30">피해액(억원)</th>
								<th scope="col" class="tbwid_30">인명피해(명)</th>
							</tr>
						</thead>
					</table>
					</div>
					<div class="table-wrap" style="max-height:540px;">
					<div class="table-basic">
						<table class="table-hover">
							<thead>
								<tr class="table-primary">
									<th scope="col" class="tbwid_20">재난명</th>
									<th scope="col" class="tbwid_20">발생일</th>
									<th scope="col" class="tbwid_30">피해액(억원)</th>
									<th scope="col" class="tbwid_30">인명피해(명)</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="4"
										v-show="viewModel.typhoon.listTyphoons.length==0"><small
										class="text-muted">{{viewModel.message.blank}}</small></td>
								</tr>
								<tr v-for="item in viewModel.typhoon.listTyphoons">
									<td class="tbwid_20">
										<div v-bind:year="item.beg_date.substring(0,4)"
											v-bind:month="item.beg_date.substring(4,6)"
											v-bind:typnm="item.typ_name" v-bind:seq="item.typ_seq">
											<div draggable="true" ondragstart="dragWord(event)">{{item.typ_name}}</div>
										</div>
									</td>
									<td class="tbwid_20">{{item.beg_date.dateFormat(".")}}</td>
									<td class="tbwid_30">{{item.property_dme==null?'-':(item.property_dme/100).toFixed(2)}}</td>
									<td class="tbwid_30">{{item.com_dme}}</td>
								</tr>
							</tbody>

						</table>
						</div>
					</div>
				</div>
			</div>
			<div class="col">
				<div class="row drop-layer">
				
					<div class="col" >

						<div class="drop-area" >
							
							<div class="damage-summary"  v-if="viewModel.typhoon.orgTyphoonInfo!=null">
							<div class="d-flex justify-content-between" >
								<div class="p-2">
									<h4><i class="icon-typhpoon"></i>
										태풍명:
											{{viewModel.typhoon.orgTyphoonInfo.typ_name}} <small>({{viewModel.typhoon.orgTyphoonInfo.beg_date.dateFormat()}}~{{viewModel.typhoon.orgTyphoonInfo.end_date.dateFormat()}})</small>
										
									</h4>
								</div>
								<div class="p-2">
								
								</div>
							</div>
							
							<div class="row p-2">
								<div class="col ">
									<small class="text-right">(* 위도 30도 기준)</small>
									<table class="table table-sm table-default ">
										<thead>
											<tr>
												<th>중심기압(hPa)</th>
												<th>평균 이동속도(m/s)</th>
												<th>최대풍속(m/s)</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>{{viewModel.typhoon.orgTyphoonInfo.ps}}</td>
												<td>{{viewModel.typhoon.orgTyphoonInfo.sp}}</td>
												<td>{{viewModel.typhoon.orgTyphoonInfo.ws}}</td>
											</tr>
										</tbody>
									</table>
									
								</div>
							</div>
							<div class="row p-2">
								<canvas id="orgTypChart"  width="663" height="255"></canvas>
							</div>
							
							<div class="row p-2">
										<div class="col ">
										<small class="text-right">(* 피해현황)</small>
									<table class="table table-sm table-default ">
										<thead>
											<tr>
												<th>피해액(억원)</th>
												<th>인명피해(명)</th>
												<th>피해지역</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>{{(viewModel.typhoon.orgDropItem.property_dme/100).format()}}</td>
												<td>{{viewModel.typhoon.orgDropItem.com_dme}}</td>
												<td style="font-size:13px;">{{viewModel.typhoon.orgDropItem.com_area}}</td>
											</tr>
										</tbody>
									</table>
									
								</div>
							</div>
								
								<div class="p-2">
									<svg class="graph" id="orgChart" width="663" height="300"><g></g></svg>
		            			<div id="orgChart-legend" >
									<table>
										<tr><td><div class="legend-symbol level5"></div></td><td>1000~(억원)</td></tr>
										<tr><td><div class="legend-symbol level4"></div></td><td>500~1000(억원)</td></tr>
										<tr><td><div class="legend-symbol level3"></div></td><td>100~500(억원)</td></tr>
										<tr><td><div class="legend-symbol level2"></div></td><td>1~100(억원)</td></tr>
										<tr><td><div class="legend-symbol level1"></div></td><td>피해없음</td></tr>
									</table>
							
								</div>
								</div>
								
							</div>
							
							<div class="row " style="padding-top:20px;">

								
								<div class="org-data-layer result-layer"
									v-for="item,index in viewModel.typhoon.listPrintOrgDailys"
									:key="item.tm_fc">

									<div class="result-area">
										<div class="row">
										<div class="col-7">
										<em>{{item.damageDay.dateFormat()}} </em><em
											v-if="item.asos!=null">최대 강우량 {{item.asos.obs_name}}
											{{item.asos.rn_day}}(mm)</em>
										</div>
										<div class="col-5 text-right">		
											<!-- <span >
												<i style="font-size:24px;color:#C0392B;" class="fas fa-user-slash" title="사망"></i> 
												<span style="font-size:16px" v-if="item.com_1!=null">12{{item.com_1 }} 명</span>
												<span style="font-size:16px" v-if="item.com_1==null">-</span>
												<i  style="font-size:24px;color:#D35400;" class="fas fa-user-check" title="실종"></i>
												<span  style="font-size:16px"  v-if="item.com_2!=null">11{{item.com_2 }} 명</span>
												<span  style="font-size:16px"  v-if="item.com_2==null">-</span>
												<i  style="font-size:24px;color:#F39C12;" class="fas fa-user-injured" title="부상"></i>
												<span  style="font-size:16px"  v-if="item.com_3!=null">20{{item.com_3 }} 명</span>
												<span  style="font-size:16px"  v-if="item.com_3==null">-</span>
											</span> -->
										</div>
										</div>
									</div>

									<div class="row">
										<div class="col" style="padding-right: 0px;">
											<div v-bind:id="'org-map'+index" class="report-map"></div>

										</div>

										<div class="col">
											<table class="table table-sm table-default ">
												<thead>
													<tr>
														<th>구분</th>
														<th>내용</th>
													</tr>
												</thead>
												<tr>
													<td class="text-left">강도</td>
													<td>{{item.typ_power}}</td>
												</tr>
												<tr>
													<td class="text-left">크기</td>
													<td>{{item.typ_size}}</td>
												</tr>
												<tr>
													<td class="text-left">최대 풍속(m&#47;s)</td>
													<td>{{item.typ_ws}}</td>
												</tr>
												<tr>
													<td class="text-left">최대 중심기압(hPa)</td>
													<td>{{item.typ_ps}}</td>
												</tr>
												<tr>
													<td class="text-left">최대 이동속도(m&#47;s)</td>
													<td>{{item.typ_sp}}</td>
												</tr>


											</table>

										</div>

									</div>
									<div class="p-1 list-750">


										<div style="padding-top: 15px;">
											<h5><button type="button" v-on:click="fnOnClickViewContent(true,'기상특보',item.damageDay,viewModel.typhoon.orgTyphoonInfo.typ_name)"><i class="icon-news"></i>기상 특보</button><sup><i class="icon-browser"></i></sup></h5>
										</div>
										<table class="table table-sm table-default">
											<thead>
												<tr>
													<th>호우주의보</th>
													<th>호우경보</th>
													<th>태풍주의보</th>
													<th>태풍경보</th>
													<th>풍랑주의보</th>
													<th>풍랑경보</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>&nbsp;<h5 class="far fa-check-circle text-info"
															v-if="item.kma.rain_at"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kma.rain_alert"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kma.typhoon_at"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kma.typhoon_alert"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kma.wind_at"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kma.wind_alert"></h5></td>
												</tr>
											</tbody>
										</table>


										<div style="padding-top: 15px;">
											<h5><i class="icon-control"></i><button type="button" v-on:click="fnOnClickViewContent(true,'통제상황',item.damageDay,viewModel.typhoon.orgTyphoonInfo.typ_name)">통제 현황</button><sup style="font-size:12px;color:blue;"><i class="far fa-window-maximize"></i></sup></h5>
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
											<h5 v-on:click="fnOnClickListManual(item.actions,item.damageDay,viewModel.typhoon.orgTyphoonInfo.typ_name)"><i class="icon-content"></i>대응현황 주요 키워드</h5>
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
															v-for="val in item.actions.lvl1"><span v-on:click="fnOnClickManualTitle(val,item.damageDay,viewModel.typhoon.orgTyphoonInfo.typ_name)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl1.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl1.length">&nbsp;</div>
													</td>
													<td class="cell"><div class="cell-item"
															v-for="val in item.actions.lvl2"><span v-on:click="fnOnClickManualTitle(val,item.damageDay,viewModel.typhoon.orgTyphoonInfo.typ_name)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl2.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl2.length">&nbsp;</div>
													</td>
													<td class="cell"><div class="cell-item"
															v-for="val in item.actions.lvl3"><span v-on:click="fnOnClickManualTitle(val,item.damageDay,viewModel.typhoon.orgTyphoonInfo.typ_name)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl3.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl3.length">&nbsp;</div>
													</td>
													<td class="cell"><div class="cell-item"
															v-for="val in item.actions.lvl4"><span v-on:click="fnOnClickManualTitle(val,item.damageDay,viewModel.typhoon.orgTyphoonInfo.typ_name)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl4.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl4.length">&nbsp;</div>
													</td>
													<td class="cell"><div class="cell-item"
															v-for="val in item.actions.lvl5"><span v-on:click="fnOnClickManualTitle(val,item.damageDay,viewModel.typhoon.orgTyphoonInfo.typ_name)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl5.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl5.length">&nbsp;</div>
													</td>
													<td class="cell"><div class="cell-item"
															v-for="val in item.actions.lvl6"><span v-on:click="fnOnClickManualTitle(val,item.damageDay,viewModel.typhoon.orgTyphoonInfo.typ_name)"><span v-on:click="fnOnClickManualTitle(val)">{{val.title}}</span></div>
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
					<div class="col">
				<div class="drop-area">
							<div class="damage-summary"  v-if="viewModel.typhoon.targetTyphoonInfo!=null">
							<div class="d-flex justify-content-between" >
								<div class="p-2">
									<h4>
										<i class="icon-typhpoon"></i>태풍명:
											{{viewModel.typhoon.targetTyphoonInfo.typ_name}} <small>({{viewModel.typhoon.targetTyphoonInfo.beg_date.dateFormat()}}~{{viewModel.typhoon.targetTyphoonInfo.end_date.dateFormat()}})</small>
										
									</h4>
								</div>
								<div class="p-2">
								<!-- 	<button type="button" class="btn btn-primary btn-sm"
										v-on:click="fnOnClickDetaiInfo(true)">상세 보기</button> -->
								</div>
							</div>
							
							<div class="row p-2">
								<div class="col ">
									<small class="text-right">(* 위도 30도 기준)</small>
									<table class="table table-sm table-default ">
										<thead>
											<tr>
												<th>중심기압(hPa)</th>
												<th>평균 이동속도(m/s)</th>
												<th>최대풍속(m/s)</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>{{viewModel.typhoon.targetTyphoonInfo.ps}}</td>
												<td>{{viewModel.typhoon.targetTyphoonInfo.sp}}</td>
												<td>{{viewModel.typhoon.targetTyphoonInfo.ws}}</td>
											</tr>
										</tbody>
									</table>
									
								</div>
							</div>
							<div class="row p-2">
								<canvas id="targetTypChart"   width="663" height="255"></canvas>
							</div>
							
							<div class="row p-2">
										<div class="col ">
										<small class="text-right">(* 피해현황)</small>
									<table class="table table-sm table-default ">
										<thead>
											<tr>
												<th>피해액(억원)</th>
												<th>인명피해(명)</th>
												<th>피해지역</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>{{(viewModel.typhoon.targetDropItem.property_dme/100).format()}}</td>
												<td>{{viewModel.typhoon.targetDropItem.com_dme}}</td>
												<td style="font-size:13px;">{{viewModel.typhoon.targetDropItem.com_area}}</td>
											</tr>
										</tbody>
									</table>
									
								</div>
							</div>
								
								<div class="p-2">
								<svg class="graph" id="targetChart" width="663" height="300"  >
		            			<g></g></svg>
		            			<div id="targetChart-legend" >
								<table>
									<tr><td><div class="legend-symbol level5"></div></td><td>1000~(억원)</td></tr>
									<tr><td><div class="legend-symbol level4"></div></td><td>500~1000(억원)</td></tr>
									<tr><td><div class="legend-symbol level3"></div></td><td>100~500(억원)</td></tr>
									<tr><td><div class="legend-symbol level2"></div></td><td>1~100(억원)</td></tr>
									<tr><td><div class="legend-symbol level1"></div></td><td>피해없음</td></tr>
								</table>
							
							 	</div>
								</div>
								
							</div>
							
							<div class="row"  style="padding-top:20px;">

								
								<div class="target-data-layer result-layer"
									v-for="item,index in viewModel.typhoon.listPrintTargetDailys"
									:key="item.tm_fc">

									<div class="result-area">
											<div class="row">
											<div class="col-7">
												<em>{{item.damageDay.dateFormat()}} </em><em
												v-if="item.asos!=null">최대 강우량 {{item.asos.obs_name}}
												{{item.asos.rn_day}}(mm)</em>
											</div>
											<div class="col-5 text-right">		
											<!-- <span >
												<i style="font-size:24px;color:#C0392B;" class="fas fa-user-slash" title="사망"></i> 
												<span style="font-size:16px" v-if="item.com_1!=null">12{{item.com_1 }} 명</span>
												<span style="font-size:16px" v-if="item.com_1==null">-</span>
												<i  style="font-size:24px;color:#D35400;" class="fas fa-user-check" title="실종"></i>
												<span  style="font-size:16px"  v-if="item.com_2!=null">11{{item.com_2 }} 명</span>
												<span  style="font-size:16px"  v-if="item.com_2==null">-</span>
												<i  style="font-size:24px;color:#F39C12;" class="fas fa-user-injured" title="부상"></i>
												<span  style="font-size:16px"  v-if="item.com_3!=null">20{{item.com_3 }} 명</span>
												<span  style="font-size:16px"  v-if="item.com_3==null">-</span>
											</span> -->
										</div>
										</div>
									</div>

									<div class="row">
										<div class="col" style="padding-right: 0px;">
											<div v-bind:id="'target-map'+index" class="report-map"></div>

										</div>

										<div class="col">
											<table class="table table-sm table-default ">
												<thead>
													<tr>
														<th>구분</th>
														<th>내용</th>
													</tr>
												</thead>
												<tr>
													<td class="text-left">강도</td>
													<td>{{item.typ_power}}</td>
												</tr>
												<tr>
													<td class="text-left">크기</td>
													<td>{{item.typ_size}}</td>
												</tr>
												<tr>
													<td class="text-left">최대 풍속(m&#47;s)</td>
													<td>{{item.typ_ws}}</td>
												</tr>
												<tr>
													<td class="text-left">최대 중심기압(hPa)</td>
													<td>{{item.typ_ps}}</td>
												</tr>
												<tr>
													<td class="text-left">최대 이동속도(m&#47;s)</td>
													<td>{{item.typ_sp}}</td>
												</tr>


											</table>

										</div>

									</div>
									<div class="p-1 list-750">


										<div style="padding-top: 15px;">
											<h5><button type="button" v-on:click="fnOnClickViewContent(false,'기상특보',item.damageDay,viewModel.typhoon.targetTyphoonInfo.typ_name)"><i class="icon-news"></i>기상 특보</button><sup><i class="icon-browser"></i></sup></h5>
										</div>
										<table class="table table-sm table-default">
											<thead>
												<tr>
													<th>호우주의보</th>
													<th>호우경보</th>
													<th>태풍주의보</th>
													<th>태풍경보</th>
													<th>풍랑주의보</th>
													<th>풍랑경보</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>&nbsp;<h5 class="far fa-check-circle text-info"
															v-if="item.kma.rain_at"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kma.rain_alert"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kma.typhoon_at"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kma.typhoon_alert"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kma.wind_at"></h5></td>
													<td><h5 class="far fa-check-circle text-info"
															v-if="item.kma.wind_alert"></h5></td>
												</tr>
											</tbody>
										</table>


										<div style="padding-top: 15px;">
											<h5><i class="icon-control"></i><button type="button" v-on:click="fnOnClickViewContent(false,'통제상황',item.damageDay,viewModel.typhoon.targetTyphoonInfo.typ_name)">통제 현황</button><sup style="font-size:12px;color:blue;"><i class="far fa-window-maximize"></i></sup></h5>
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
											<h5 v-on:click="fnOnClickListManual(item.actions,item.damageDay,viewModel.typhoon.targetTyphoonInfo.typ_name)"><i class="icon-content"></i>대응현황 주요 키워드</h5>
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
													<td class="cell">
													
														<div class="cell-item"
															v-for="val in item.actions.lvl1"><span v-on:click="fnOnClickManualTitle(val,item.damageDay,viewModel.typhoon.targetTyphoonInfo.typ_name)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl1.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl1.length">&nbsp;</div>
													</td>
													<td class="cell">
													
													<div class="cell-item"
															v-for="val in item.actions.lvl2"><span v-on:click="fnOnClickManualTitle(val,item.damageDay,viewModel.typhoon.targetTyphoonInfo.typ_name)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl2.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl2.length">&nbsp;</div>
													</td>
													<td class="cell">
													
													<div class="cell-item"
															v-for="val in item.actions.lvl3"><span v-on:click="fnOnClickManualTitle(val,item.damageDay,viewModel.typhoon.targetTyphoonInfo.typ_name)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl3.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl3.length">&nbsp;</div>
													</td>
													<td class="cell">
													
													<div class="cell-item"
															v-for="val in item.actions.lvl4"><span v-on:click="fnOnClickManualTitle(val,item.damageDay,viewModel.typhoon.targetTyphoonInfo.typ_name)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl4.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl4.length">&nbsp;</div>
													</td>
													<td class="cell">
													
													<div class="cell-item"
															v-for="val in item.actions.lvl5"><span v-on:click="fnOnClickManualTitle(val,item.damageDay,viewModel.typhoon.targetTyphoonInfo.typ_name)">{{val.title}}</span></div>
														<div class="cell-item"
															v-if="item.actions.lvl5.length<item.actions.size"
															v-for="index in item.actions.size-item.actions.lvl5.length">&nbsp;</div>
													</td>
													<td class="cell">
													
													<div class="cell-item"
															v-for="val in item.actions.lvl6"><span v-on:click="fnOnClickManualTitle(val,item.damageDay,viewModel.typhoon.targetTyphoonInfo.typ_name)"><span v-on:click="fnOnClickManualTitle(val)">{{val.title}}</span></div>
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
					
						<div id="target-data" class="dropable-area" draggable="false"
									ondragover="event.preventDefault()" ondrop="dropWord(event)"></div>
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
        <h5 class="modal-title" id="exampleModalLabel">{{modal.damageName}} : {{modal.evtDay.dateFormat()}} {{modal.title}}</h5>
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





	<script src="${pageContext.request.contextPath}/openlayers3/build/ol.js"></script>
	<script src="${pageContext.request.contextPath}/openlayers3/build/ol3gm.js"></script>
	<script type="text/javascript">

		
		var vueObj = null;
		function dragWord(dragEvent) {
			console.log("dragEvent");
			console.log($(dragEvent.target.parentNode));
			$("#org-data").show();
			$("#target-data").show();
			dragEvent.dataTransfer.setData("text", dragEvent.target.textContent
					+ "|" + dragEvent.target.parentNode.getAttribute("year")
					+ "|" + dragEvent.target.parentNode.getAttribute("month")
					+ "|" + dragEvent.target.parentNode.getAttribute("typnm")
					+ "|" + dragEvent.target.parentNode.getAttribute("seq")

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
			var dataYear = dropItems[1];
			var dataMonth = dropItems[2];
			var dataName = dropItems[3];
			var dataSeq = dropItems[4];
			console.log(dropEvent.target);
			//dropEvent.target.textContent = dropItems[0];
			vueObj.fnOnDropDamageInfo(dropEvent.target.id, dataYear, dataMonth,
					null, dataName, dataSeq);

			dropEvent.preventDefault();
		}

		function pageLoad(obj) {
			vueObj = obj;
			/* var raster1 = new ol.layer.Tile({
				source : new ol.source.OSM()
			});

			var map1 = new ol.Map({
				layers : [ raster1 ],
				target : 'map1',
				view : new ol.View({
					center : ol.proj.fromLonLat([ 128, 28 ]),
					zoom : 6
				})
			});

			var raster2 = new ol.layer.Tile({
				source : new ol.source.OSM()
			});

			var map2 = new ol.Map({
				layers : [ raster2 ],
				target : 'map2',
				view : new ol.View({
					center : ol.proj.fromLonLat([ 128, 28 ]),
					zoom : 6
				})
			}); */
		}
	
	</script>
	<script
		src="${pageContext.request.contextPath}/contents/advis/js/model/vue/analisys-controller.min.js"></script>
</body>
</html>