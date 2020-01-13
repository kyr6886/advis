<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>지진 상황보고 작성</title>
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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/custom.css">
<!-- <link rel="stylesheet" type="text/css" -->
<%-- 	href="${pageContext.request.contextPath}/contents/base/fonts/style.css"> --%>
<script
	src="${pageContext.request.contextPath}/css/bootstrap4/js/bootstrap.min.js"></script>
	<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
<style type="text/css">
.bgWhite {
	background: white;
}

body {
	height: 100vh;
}

td textarea {
	width: 100%;
	border: solid 1px #ededed;
	height: 40px !important;
}

.card-body {
	padding: 10px !important;
}

.row.border-b {
	border-bottom: solid 1px #ededed;
}

.row .area {
	border: 2px dotted #8bc34a;
	border-radius: 5px;
	background: #fff;
}

.bgGray {
	background: #ededed;
}
.font-btn{
	background-color:transparent;
	border:none;
}
.font-btn i{
 color:#339AF0;
}
.table-sm td{
	font-size:14px;
}
td[scope='row']{
background-color: rgba(0,0,0,.03);
padding-top:15px;
}
tr.act_date{
 border-top:3px solid #dddddd;
}
i.blue{
     color: #2793db;
}
.left-pan{
	max-height:850px;
	overflow-y:auto;
}

.left-pan::-webkit-scrollbar {
	width: 6px;
	background-color: #f0f0f0;
}
.right-pan{
	max-height:850px;
	overflow-y:auto;
	overflow-x:hidden;
}

.right-pan::-webkit-scrollbar {
	width: 6px;
	background-color: #f0f0f0;
}

.left-pan::-webkit-scrollbar-thumb {
	background-color: #cdcdcd;
}
.map{
    border: 1px solid rgba(0,0,0,.125);
}
#rain-map{
	width:100%;
	min-width:300px;
	height:250px;
}
</style>
</head>
<body>
	<div class="container-fluid " id="scope-report-neq" v-cloak>
	
		<div id="loading-layer"><div class="loading-content">{{message}}</div>
	 		<img id="loading-image" src="/images/ajax-loader.gif" alt="Loading..." />
	 	</div>
	 	
		<div class="row border-b">
			<div class="col">
				<div class="logo">
					<a href="/" class="simple-text logo-normal"
						style="background: url(/contents/advis/images/bg-login.png) no-repeat; background-size: cover; background-position: 60%;">
						<img src="/contents/advis/images/logo-login.png"
						style="height: 40px;">
					</a>
				</div>
			</div>
		</div>
		<br>
		<div class="row">
		
			<div class="col-8 left-pan" >
				<div v-show="!isWrite||isUsed">
					<h4><i class="fas fa-chevron-right blue"></i> 기본정보 </h4>
					<div class="card card-sm" style="display:block;">
						
						<div class="card-body">
							<h6 class="card-title"><i class="fas fa-caret-square-right blue"></i> 관련 재난 선택</h6>
							<p class="card-text">
							<div class="form-group">
								<select class="form-control form-control-sm" v-model="summary.selectedEventGroup" id="dis_title" v-on:change="fnChangeEventGrp">
									<option v-for="item in summary.eventList" v-bind:value="item.evt_group" v-key="item.evt_group">{{item.title}}</option>
								</select>
							</div>
							</p>
	
							<h6 class="card-title"><i class="fas fa-caret-square-right blue"></i> 작성일시</h6>
							<p class="card-text">
							<div class="form-group">
								<input type="text" class="form-control form-control-sm" v-model="summary.createDateTime"
									placeholder="ex) 201911190300" />
							</div>
							</p>
							<h6 class="card-title"><i class="fas fa-caret-square-right blue"></i> 피해지역 입력</h6>
							<p class="card-text">
							<div class="form-group">
								<input type="text" class="form-control form-control-sm" v-model="summary.area"
									placeholder="ex) 서울,충남,제주,수원시,강남구" />
							</div>
							</p>
							<h6 class="card-title"><i class="fas fa-caret-square-right blue"></i> 대응단계</h6>
							<p class="card-text">
							<div class="form-group">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" id="DVL_0000" value="DVL_0000" v-model="summary.paramStatus"> 
									<label class="form-check-label" for="DVL_0000">비상근무실시</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" id="DVL_0001" value="DVL_0001"  v-model="summary.paramStatus"> 
									<label class="form-check-label" for="DVL_0001">1단계</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" id="DVL_0002" value="DVL_0002"  v-model="summary.paramStatus"> 
									<label class="form-check-label" for="DVL_0002">2단계</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" id="DVL_0003" value="DVL_0003"  v-model="summary.paramStatus"> 
									<label class="form-check-label" for="DVL_0003">3단계</label>
								</div>
							</div>
							</p>
							<h6 class="card-title"><i class="fas fa-caret-square-right blue"></i> 관련기관 선택</h6>
							<p class="card-text">
							<div class="form-group">
								<div class="form-check form-check-inline" v-for="item in sysCodes">
									<input class="form-check-input" type="checkbox" v-model="item.checked"
										v-bind:id="item.sys_code"> <label
										class="form-check-label" v-bind:for="item.sys_code" >{{item.sys_title}}</label>
								</div>
								<div class="form-check form-check-inline">
									<label class="my-1 mr-2 ml-2">직접입력 : </label>
									<input type="text" class="form-control form-control-sm" v-model="inputOrg" style="width:350px;" placeholder="국회,국토교통부,여성가족부 " />
								</div>
							</div>
							</p>
<!-- 							<h6 class="card-title"><i class="fas fa-caret-square-right blue"></i> 누적여진 횟수</h6> -->
<!-- 							<p class="card-text"> -->
<!-- 							<div class="form-group"> -->
<!-- 								<div class="form-check form-check-inline"> -->
<!-- 									<input class="form-check-input" type="radio" -->
<!-- 										id="rain_150" value="150"  v-model="summary.checkedAftershocks"> <label -->
<!-- 										class="form-check-label" for="aftershocks_150">150회 이상</label> -->
<!-- 								</div> -->
								
<!-- 								<div class="form-check form-check-inline"> -->
<!-- 									<input class="form-check-input" type="radio" -->
<!-- 										id="rain_100" value="100"  v-model="summary.checkedAftershocks"> <label -->
<!-- 										class="form-check-label" for="aftershocks_100">100회 이상</label> -->
<!-- 								</div> -->
	
<!-- 								<div class="form-check form-check-inline"> -->
<!-- 									<input class="form-check-input" type="radio" -->
<!-- 										id="rain_50" value="50" v-model="summary.checkedAftershocks"> <label -->
<!-- 										class="form-check-label" for="aftershocks_50">50회 이상</label> -->
<!-- 								</div> -->
								
<!-- 								<div class="form-check form-check-inline"> -->
<!-- 									<input class="form-check-input" type="radio" -->
<!-- 										id="rain_10" value="10"  v-model="summary.checkedAftershocks"> <label -->
<!-- 										class="form-check-label" for="aftershocks_10">10회 이상</label> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							</p> -->
							<h6 class="card-title"><i class="fas fa-caret-square-right blue"></i> 인명 피해</h6>
							<p class="card-text">
							<div class="form-inline">
								<label class="my-1 mr-2">사망</label> <input type="text" v-model="summary.death"
									class="form-control form-control-sm">
								<label class="my-1 mr-2 ml-2">실종</label> <input type="text"
									class="form-control form-control-sm" v-model="summary.missing">
								<label class="my-1 mr-2 ml-2">부상</label> <input type="text"
									class="form-control form-control-sm" v-model="summary.injury">
							</div>
							</p>
							<a v-if="!isWrite" href="#" class="btn btn-primary" v-on:click="fnOnClickBtnWrite">작성하기</a>
						</div>
					</div>
				</div>
				
				<div v-show="isWrite||isUsed">
					<div class="row"> 
						<div class="col">
							<h4><i class="fas fa-chevron-right blue"></i> 상세정보  </h4>
						</div>
						<div class="col text-right" v-show="!isUsed">
							<button class="btn btn-info btn-sm" v-on:click="fnOnClickBtnSummary" >기본정보</button>
							<button class="btn btn-primary btn-sm" v-on:click="fnOnClickBtnSave">저장</button>
						</div>
					</div>
					<div class="card card-sm" >
						
						<div class="card-body">
							<div v-for="item2 in depth2">
								<h5><i class="fas fa-caret-square-right blue"></i> {{item2.title}}</h5>
								<div v-for="item3 in depth3"  v-if="item3.parent_ctg_id==item2.ctg_id">
									<h6><i class="far fa-square blue"></i> {{item3.title}} <button class="font-btn" type="button" v-if="item3.print_id=='50000'" v-on:click="fnOnClickBtnAddItem(item3)"><i class="fas fa-plus-circle"></i></button></h6></h6>
									<div>
										<table class="table table-bordered table-sm">
											<colgroup>
												<col style="width:150px"/>
												<col/>
											</colgroup>
											<tr v-for="item4 in depth4" v-if="item3.print_id!='50000'&&item4.parent_ctg_id==item3.ctg_id" v-bind:class="item4.ctg_id">
												<td scope="row">{{item4.title}}</td>
												<td class="text-left"><textarea class=" form-control-sm" v-model="item4.value"></textarea></td>
											</tr>
											<tr v-for="actions in addedListActions"  v-if="item3.print_id=='50000'" >
												<td>
													<table style="width:100%">
													<colgroup>
														<col style="width:150px"/>
														<col/>
													</colgroup>
														<tr v-for="action in actions.listActions"  v-bind:class="action.ctg_id">
														<td scope="row">{{action.title}}<button class="font-btn" type="button" v-if="action.ctg_id=='act_date'" v-on:click="fnOnClickBtnDeleteItem(actions)"><i class="fas fa-minus-circle"></i></button></td>
														<td class="text-left"><textarea class=" form-control-sm" v-model="action.value"></textarea></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
						
					</div>
					<br>
					<div class="text-right" v-show="!isUsed"><button type="button" class="btn btn-primary btn-sm" v-on:click="fnOnClickBtnSave">저장</button></div>
				</div>
			</div>
			
			<div class="col">
				<div class="right-pan">
					<h4 ><i class="fas fa-chevron-right blue"></i> 이전 지진 피해사례</h4>
					<div class="card card-sm" >
						<div class="card-body">
						
							<div class="form-group">
								<h6><i class="fas fa-caret-square-right blue"></i> 지진 정보</h6>
								<div class="row">
									
									<div class="col"> 
										<div id="map" class="full-map"></div>
									</div>
<!-- 									<div class="col"> -->
<!-- 										<table  class="table table-bordered table-sm"> -->
											
<!-- 										</table> -->
<!-- 									</div> -->
								</div>
							</div>
							
							<div class="form-group">
								<h6><i class="fas fa-caret-square-right blue"></i>피해현황</h6>
								<div class="table-basic" style="max-height:425px; overflow-y: auto;">
									<table class="table table-sm table-default ">
										<tbody>
											<tr>
												<th class="tbwid_30">인명피해(명)</th>
												<td>{{viewModel.selected.damageEq.com_dme}}</td>
											</tr>
											<tr>
												<th>재산 피해액(억원)</th>
												<td>{{(viewModel.selected.damageEq.com_total/100000).toFixed().format()}}</td>
											</tr>
											<tr>
												<th>사유시설 피해액(억원)</th>
												<td>{{(viewModel.selected.damageEq.pri_total/100000).toFixed().format()}}</td>
											</tr>
											<tr>
												<th>공공시설 피해액(억원)</th>
												<td>{{(viewModel.selected.damageEq.pub_total/100000).toFixed().format()}}</td>
											</tr>
											<tr>
												<th class="last-th">총 피해액(억원)</th>
												<td>{{(viewModel.selected.damageEq.total_damage/100000).toFixed().format()}}</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="table-basic" style="max-height:425px; overflow-y: auto;">
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
													<td>{{(item.total_damage/100000).toFixed().format()}}</td>
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
							
							<div class="form-group">
								<h6><i class="fas fa-caret-square-right blue"></i>일별 발생횟수 <small>(진도 2.0 이상)</small> </h6>
								<div class="card">
									<div class="chart card-body">
										<canvas class="chart" id="chart-daily" style="width:540px;height:200px;"></canvas>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<h6><i class="fas fa-caret-square-right blue"></i>규모별 발생횟수 <small>(진도 2.0 이상)</small> </h6>
								<div class="card">
									<div class="chart card-body">
										<canvas class="chart" id="chart-pw" style="width:540px;height:200px;"></canvas>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<h6><i class="fas fa-caret-square-right blue"></i>지진 관련 뉴스</h6>
								<div class="table-basic">
									<table>
										<thead>
											<tr>
												<th scope="col">제목</th>
												<th scope="col">작성일</th>
											</tr>
										</thead>
										<tbody v-if="viewModel.listNews.length>0">
											<tr v-for="item,index in viewModel.listNews" v-if="index<9">
												<td class="text-left-width"><a v-bind:href='item.url'target="_blank" v-html="item.printTitle"></a></td>
												<td>{{item.datetime.dateFormat()}}</td>
											</tr>
										</tbody>
										<tbody v-if="viewModel.listNews.length==0">
											<tr>
												<td colspan="2">내용이 없습니다.</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							
							
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	<script type="text/javascript">
	var vueObj = null;
		function pageLoad(obj) {
			vueObj = obj;
			obj.fnListDisEvents('NEQ');
			obj.fnListCategories('NEQ',"${vm.paramEvtId}");
			
			
		}
	</script>
	<script src="${pageContext.request.contextPath}/openlayers3/build/ol.js"></script>
	<script src="${pageContext.request.contextPath}/openlayers3/build/ol3gm.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/advis/js/model/vue/reportEarthquake-controller.min.js"></script>
</body>
</html>