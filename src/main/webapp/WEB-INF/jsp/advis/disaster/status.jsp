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

	<div class="contents-wrap" id="scope-disasterDme" v-cloak>
		<ul class="tab">
			<li><a href="/advis/disaster/rain">호우</a></li>
			<li class="on"><a href="/advis/disaster/status">태풍</a></li>
			<li><a href="/advis/disaster/snow">대설</a></li>
			<li><a href="/advis/disaster/earthquake">지진</a></li>
			<li><a href="/advis/disaster/society">사회재난</a></li>
		</ul>


<!-- 		<div class="text-right"> -->
<!-- 			<button class="btn btn-default" v-on:click="fnOnClickShowInfo()">재난업무 -->
<!-- 				단계별 우선순위 보기</button> -->
<!-- 		</div> -->
		<div class="table-basic tm20" v-if="viewModel.showInfo">

			<table>
				<tr>
					<th>중요도</th>
					<th>예방단계</th>
					<th>대비단계</th>
					<th>대응단계</th>
					<th>복구단계</th>
				</tr>
				<tr>
					<td>1 순위</td>
					<td><a
						href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/contents/prevent/prevent02.html?menuSeq=126"
						target="_blank">국민행동요령</a></td>
					<td><a
						href="http://www.weather.go.kr/weather/typoon/prediction.jsp"
						target="_blank">예상 진로</a></td>
					<td><a
						href="http://www.weather.go.kr/weather/typoon/prediction.jsp"
						target="_blank">예상 진로</a></td>
					<td><a
						href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/sfc/ppb/ppbeaiList.jsp?emgPage=Y&menuSeq=116"
						target="_blank">복구 인원 및 물자 현황</a></td>
				</tr>
				<tr>
					<td>2 순위</td>
					<td><a
						href="https://www.weather.go.kr/weather/warning/standard.jsp"
						target="_blank">기상특보 기준</a></td>
					<td><a
						href="http://www.weather.go.kr/weather/typoon/detail.jsp"
						target="_blank">태풍 위치</a></td>
					<td><a
						href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/contents/civil_defense/SDIJKM1402.html?menuSeq=57"
						target="_blank">대피소 현황</a></td>
					<td><a
						href="https://www.mois.go.kr/frt/bbs/type001/commonSelectBoardList.do?bbsId=BBSMSTR_000000000336"
						target="_blank">향후 조치 계획</a></td>
				</tr>
				<tr>
					<td>3 순위</td>
					<td><a
						href="http://www.weather.go.kr/weather/typoon/prediction.jsp"
						target="_blank">예상 진로</a></td>
					<td><a
						href="http://www.weather.go.kr/weather/typoon/report.jsp"
						target="_blank">모니터링 현황</a></td>
					<td><a
						href="http://www.khoa.go.kr/koofs/kor/observation/obs_real.do"
						target="_blank">조위정보</a></td>
					<td><a
						href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/contents/insurance/SDIJKM2101.html?menuSeq=303"
						target="_blank">보험 및 보상 기준</a></td>
				</tr>
				<tr>
					<td>4 순위</td>
					<td><a
						href="http://www.weather.go.kr/weather/typoon/report.jsp"
						target="_blank">특별 관심 지구</a></td>
					<td><a
						href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/sfc/fcl/riskUserList.html?menuSeq=314"
						target="_blank">재해위험지구 현황</a></td>
					<td><a
						href="http://typ.kma.go.kr/TYPHOON/contents/contents_04_4_1.jsp"
						target="_blank">국민행동요령</a></td>
					<td><a
						href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/contents/civil_defense/SDIJKM1402.html?menuSeq=57"
						target="_blank">대피소 현황</a></td>
				</tr>
				<tr>
					<td>5 순위</td>
					<td><a
						href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/csc/bbs_conf.jsp?menuSeq=593&bbs_no=9&viewtype=read&bbs_ordr=142"
						target="_blank">재난대응 매뉴얼</a></td>
					<td><a
						href="http://www.weather.go.kr/weather/typoon/report.jsp"
						target="_blank">기상개황</a></td>
					<td><a
						href="http://www.weather.go.kr/weather/observation/aws_table_popup.jsp"
						target="_blank">시간당강우량</a></td>
					<td><a
						href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/sfc/tot/toteaiList.jsp?emgPage=Y&menuSeq=111"
						target="_blank">피해현황</a></td>
				</tr>
				<tr>
					<td>6 순위</td>
					<td><a
						href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/sfc/fcl/riskUserList.html?menuSeq=314"
						target="_blank">재해위험지구 현황</a></td>
					<td><a
						href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/contents/civil_defense/SDIJKM1301.html?menuSeq=53"
						target="_blank">대국민전파체계</a></td>
					<td><a
						href="https://www.mois.go.kr/frt/bbs/type001/commonSelectBoardList.do?bbsId=BBSMSTR_000000000336"
						target="_blank">통제상황</a></td>
					<td><a
						href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/sfc/emd/medicalUserList.html?menuSeq=320"
						target="_blank">긴급 의료 기관</a></td>
				</tr>
				<tr>
					<td>7 순위</td>
					<td><a
						href="http://www.weather.go.kr/weather/typoon/detail.jsp"
						target="_blank">태풍 위치</a></td>
					<td><a
						href="http://www.weather.go.kr/weather/typoon/report.jsp"
						target="_blank">특별 관심 지구</a></td>
					<td><a
						href="http://www.weather.go.kr/weather/observation/aws_table_popup.jsp"
						target="_blank">누적강우량</a></td>
					<td><a
						href="https://www.mois.go.kr/frt/bbs/type001/commonSelectBoardList.do?bbsId=BBSMSTR_000000000336"
						target="_blank">통제상황</a></td>
				</tr>
				<tr>
					<td>8 순위</td>
					<td><a
						href="http://www.law.go.kr/%EB%B2%95%EB%A0%B9/%EC%9E%AC%EB%82%9C%EB%B0%8F%EC%95%88%EC%A0%84%EA%B4%80%EB%A6%AC%EA%B8%B0%EB%B3%B8%EB%B2%95"
						target="_blank">재난 및 안전관리 기본법</a></td>
					<td><a
						href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/contents/prevent/prevent02.html?menuSeq=126"
						target="_blank">국민행동요령</a></td>
					<td><a
						href="http://www.weather.go.kr/weather/typoon/detail.jsp"
						target="_blank">태풍 위치</a></td>
					<td><a
						href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/sfc/dis/disasterNewsList.jsp?emgPage=Y&menuSeq=619"
						target="_blank">복합재난발생 확률</a></td>
				</tr>
				<tr>
					<td>9 순위</td>
					<td><a
						href="http://www.law.go.kr/LSW/admRulLsInfoP.do?chrClsCd=&admRulSeq=2100000178060#AJAX"
						target="_blank">비상근무 기준 및 현황</a></td>
					<td><a
						href="http://www.weather.go.kr/weather/observation/aws_table_popup.jsp"
						target="_blank">시간당강우량</a></td>
					<td><a
						href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/sfc/emd/medicalUserList.html?menuSeq=320"
						target="_blank">긴급 의료 기관</a></td>
					<td><a href="https://www.safetyreport.go.kr/#main"
						target="_blank">신고 및 제보 현황</a></td>
				</tr>
				<tr>
					<td>10 순위</td>
					<td><a
						href="http://www.law.go.kr/admRulLsInfoP.do?admRulSeq=2100000118982"
						target="_blank">시설 안전기준</a></td>
					<td><a
						href="http://www.khoa.go.kr/kcom/cnt/selectContentsPage.do?cntId=31105000"
						target="_blank">조위 피해지역</a></td>
					<td><a
						href="http://www.law.go.kr/LSW/admRulLsInfoP.do?chrClsCd=&admRulSeq=2100000178060#AJAX"
						target="_blank">비상근무 기준 및 현황</a></td>
					<td><a
						href="https://www.mois.go.kr/frt/bbs/type001/commonSelectBoardList.do?bbsId=BBSMSTR_000000000336"
						target="_blank">기관별 대처상황</a></td>
				</tr>
			</table>
		</div>
		<div class="search">
			<select v-model="viewModel.selectTopDmeCode"
				class="select-box-width-120" v-on:change='fnChangeTyphoonNameList()'
				disabled="disabled">
				<option v-for="item in viewModel.listTopDmeCode"
					v-bind:value='item.code'>{{item.code_name_dme}}</option>
			</select> <select v-model="viewModel.stDate" class="select-box-width-120"
				v-on:change='fnOnChangeYear()'>
				<option v-for="item in viewModel.listYearsSelect"
					v-bind:value="item">{{item}}년</option>
			</select> <select v-model="viewModel.selectedMonth"
				class="select-box-width-120" v-on:change='fnOnChangeMonth()'>
				<option value="">월 선택</option>
				<option v-for="item in viewModel.listMonth"
					v-bind:value="item.beg_month">{{item.beg_month}}월</option>
			</select> <select v-model="viewModel.selectedTyphoonName"
				class="select-box-width-120" v-on:change='fnOnChangeTyphoon()'>
				<option value="">태풍선택</option>
				<option v-for="item in viewModel.listTyphoons"
					v-bind:value="item.typ_name">{{item.typ_name}}</option>
			</select> <a href="#" class="btn btn-purple" v-on:click="fnOnClickBtnSearch()">조회</a>
		</div>


		<br>

		<!-- <div class="result-info" v-show="viewModel.listTopComDme">
			<i class="bl-map"></i> <span>조회 기간 : <em><b>{{ viewModel.selectedParamStDate.dateFormat()}} ~ {{ viewModel.selectedParamEndDate.dateFormat() }}</b></em></span> 	
			<span>최대 피해 지역  : <em><b>{{viewModel.selectedSidoName}} {{viewModel.selectedGunguName}}</b></em></span> 	
			<span v-show="viewModel.viewType=='person'">최대 인명피해 : <em><b>{{ viewModel.selectedMaxComDme }}</b></em> 명</span>
			<span v-show="viewModel.viewType!='person'">최대 피해금액 : <em><b>{{ viewModel.selectedMaxComDme.format() }}</b></em>(천원)</span>
		</div> -->




		<section class="main-divide">
			<div class="route-area">
				<h2>태풍경로</h2>
				<div class="map-wrap">
					<div id="map" style="width: 680px; height: 400px;">

						<a class="overlay" id="overlay-label"
							style="display: none; color: white; font-weight: bold;"
							target="_blank" href="http://en.wikipedia.org/wiki/Vienna"></a>
					</div>
					<div class="map-info table-basic"
						v-show="viewModel.typInfo.time!=''">
						<table>
							<tr>
								<td>시간</td>
								<td>{{viewModel.typInfo.time}}</td>
							</tr>
							<tr>
								<td>중심기압(hPa)</td>
								<td>{{viewModel.typInfo.press}}</td>
							</tr>
							<tr>
								<td>최대 풍속(m/s)</td>
								<td>{{viewModel.typInfo.wind}}</td>
							</tr>
							<tr>
								<td>이동속도(m/s)</td>
								<td>{{viewModel.typInfo.speed}}</td>
							</tr>
						</table>
					</div>
				</div>

			</div>

			<div class="dis-right-box">
				<canvas id="typ-chart"
					style="border: solid 1px #dddddd; width: 680px; height: 420px; margin-top: 35px;"></canvas>
			</div>

			<!-- 			<div class="dis-right-box" v-show="eventImgList.length>0"> -->
			<!-- 			<h2>재해이미지</h2> -->
			<!-- 				<div class="image"> -->
			<%-- 					<img  v-for="item in eventImgList" class="dis-thumb" v-bind:src="'${pageContext.request.contextPath}/contents/advis/images/eventImg/'+item.file_new_name"/> --%>
			<!-- 				</div> -->
			<!-- 			</div> -->
			<!-- 			<div class="dis-right-box" v-show="eventImgList.length==0"> -->
			<!-- 			<h2>재해이미지</h2> -->
			<!-- 				<div style="text-align:center; padding-top:50px;"> -->
			<!-- 					<h4>해당 내용이 없습니다.</h4> -->
			<!-- 				</div> -->
			<!-- 			</div> -->
		</section>
		<br>
<!-- 		<div v-show="keyListView!=null && keyListView.length>0"> -->
<!-- 			<div class="time-table result-info" > -->
<!-- 				<table> -->
<!-- 					<tr> -->
<!-- 						<th></th> -->
<!-- 						<th v-for="item in keyListView"> -->
<!-- 							<div v-if="item.detail.length>0"> -->
<!-- 								<div v-if="item.date.substring(6)=='00:00'">{{item.date.substring(0,6)}}</div> -->
<!-- 								<div v-if="item.date.substring(6)!='00:00'">{{item.date}}</div> -->
<!-- 							</div> -->
<!-- 						</th> -->
<!-- 					</tr> -->
<!-- 					<tr v-for="keyItem in orgList"> -->
<!-- 						<td class='noBorder text-right'> -->
<!-- 							{{keyItem}} -->
<!-- 						</td> -->
<!-- 						<td v-for="item in keyListView"> -->
<!-- 							<div v-for="dItem in item.detail"> -->
<!-- 								<div v-if="keyItem == dItem.org" class="action-color" v-on:click="fnClickEventLink(dItem.contentId)"> &nbsp; </div> -->
<!-- 							</div> -->
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 				</table> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div v-show="keyListView===null||keyListView.length==0"> -->
<!-- 			<div class="time-table result-info" > -->
<!-- 				해당 내용이 없습니다. -->
<!-- 			</div> -->
<!-- 		</div> -->
			<!-- 			<div class="time-table result-info" > -->
			<!-- 				<table> -->
			<!-- 					<tr class="time-content"> -->
			<!-- 						<td v-for="item,index in keyListView" > -->
			<!-- 							<div class="content" v-for="subItem in item.detail"> -->
			<!-- 								<a v-bind:href="'/advis/code/codeView/'+subItem.contentId.substring(1,3)+'/'+subItem.contentId" v-bind:title="subItem.content">{{subItem.content.substring(1,15)}}...<br></a> -->
			<!-- 							</div> -->
			<!-- 						</td> -->
			<!-- 					</tr> -->
			<!-- 					<tr class="time-line"><td v-for="item,index in keyListView"><i>{{item.date}}</i></td></tr> -->
			<!-- 					<tr class="time-cloumn"><td v-for="item,index in keyListView" ><i>{{item.date}}</i></td></tr> -->
			<!-- 				</table> -->
			<!-- 			</div> -->
		
		<br>
		<h2>재해연보</h2>
		<div class="table-basic">
			<table>
				<caption></caption>
				<thead>
					<tr v-if="viewModel.viewType=='person'">


						<th>시도</th>
						<th>시군구</th>
						<th>인명피해(명)</th>
						<th>피해액(천원)</th>
						<th>최대일강우(mm)</th>
						<th>최대시강우(mm)</th>
					</tr>

					<tr v-if="viewModel.viewType!='person'">

						<th>시도</th>
						<th>시군구</th>
						<th>피해액(천원)</th>

						<th>인명피해(명))</th>
						<th>최대일강우(mm)</th>

					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="7" v-if="viewModel.listTopComDme.length==0">재해
							이력이 없습니다.</td>
					</tr>
					<tr v-if="viewModel.viewType=='person'"
						v-for="item in viewModel.listTopComDme">


						<td>{{item.sido}}</td>
						<td><span v-if="item.sigungu != null">{{item.sigungu}}</span>
							<span v-else>{{item.sido}}</span></td>
						<td>{{item.com_dme}}</td>
						<td>{{item.total_damage.format()}}</td>
						<td>{{item.total_com_dme}}</td>
						<td>{{item.rn_day}}</td>
						<td>{{item.rn_60m_max}}</td>
					</tr>
					<tr v-if="viewModel.viewType!='person'"
						v-for="item in viewModel.listTopComDme">


						<td>{{item.sido}}</td>
						<td><span v-if="item.sigungu != null">{{item.sigungu}}</span>
							<span v-else>{{item.sido}}</span></td>

						<td>{{item.total_damage.format()}}</td>

						<!-- <td><span><a href="#" v-on:click="fnOnClickPopupOpen(item)"><u>{{item.beg_date.substring(0,4)}} -{{item.beg_date.substring(4,6)}} - {{item.beg_date.substring(6,8)}}</u></a></span></td>
						<td>{{item.end_date.substring(0,4)}} -{{item.end_date.substring(4,6)}} - {{item.end_date.substring(6,8)}}</td> -->
						<td>{{item.com_dme}}</td>
						<td>{{item.rn_day}}</td>
						<!-- <td>
							<span v-if="item.pri_name != '' && item.pub_name != ''">{{item.pri_name}},{{item.pub_name}}</span>
							<span v-if="item.pri_name != '' && item.pub_name == ''">{{item.pri_name}}</span>
							<span v-if="item.pri_name == '' && item.pub_name != ''">{{item.pub_name}}</span>
							<span v-if="item.pri_name == '' && item.pub_name == ''">-</span>
						</td> -->
					</tr>
				</tbody>
			</table>
		</div>

		<br>
		<h2>관련재난뉴스</h2>
		<div class="image">
			<div class="table-basic">
				<table>
					<tr>
						<th>제목</th>
						<th>작성일</th>
					</tr>
					<tr>
						<td colspan="2" v-if="viewModel.listNews.length==0">관련 뉴스가
							없습니다.</td>
					</tr>
					<tr v-for="item,index in viewModel.listNews" v-if="index<9">
						<td class="text-left"><a v-bind:href='item.url'
							target="_blank">{{item.printTitle}}...</a></td>
						<td>{{item.datetime.dateFormat()}}</td>
					</tr>
				</table>
			</div>
			<br>
			<%-- 	<div v-show="eventImgList.length>0">
						<a v-for="item in eventImgList" v-bind:href="'${pageContext.request.contextPath}/contents/advis/images/eventImg/'+item.file_new_name" target="_blank"><img class="dis-thumb" v-bind:src="'${pageContext.request.contextPath}/contents/advis/images/eventImg/'+item.file_new_name"/></a>
					</div> --%>
		</div>
		<!-- 				<div style="text-align:center; padding-top:50px;" v-show="eventImgList.length==0"> -->
		<!-- 					<h4>해당 내용이 없습니다.</h4> -->
		<!-- 				</div> -->

	</div>

	<!-- 			<div class="dis-right-box" v-show="eventImgList.length>0"> -->
	<!-- 			<h2>재해이미지</h2> -->
	<!-- 				<div class="image"> -->
	<%-- 					<img  v-for="item in eventImgList" class="dis-thumb" v-bind:src="'${pageContext.request.contextPath}/contents/advis/images/eventImg/'+item.file_new_name"/> --%>
	<!-- 				</div> -->
	<!-- 			</div> -->
	<!-- 			<div class="dis-right-box" v-show="eventImgList.length==0"> -->
	<!-- 			<h2>재해이미지</h2> -->
	<!-- 				<div style="text-align:center; padding-top:50px;"> -->
	<!-- 					<h4>해당 내용이 없습니다.</h4> -->
	<!-- 				</div> -->
	<!-- 			</div> -->

	

</div>

<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCO6oQoC5ZRaX-nKSXeLChM_KWwu6-v4BM&v=3.21&region=KR"></script>



<script src="${pageContext.request.contextPath}/openlayers3/build/ol.js"></script>
<script
	src="${pageContext.request.contextPath}/openlayers3/build/ol3gm.js"></script>
<script src="${pageContext.request.contextPath}/map/map.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/openlayers3/css/ol.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/openlayers3/css/ol3gm.css">



<script type="text/javascript">
	function pageLoad(obj) {
		//var map=createVWorldMap('map');
	  var raster = new ol.layer.Tile({
			        source: new ol.source.OSM()
			      });

			      var map = new ol.Map({
			        layers: [raster],
			        target: 'map',
			        view: new ol.View({
			          center: ol.proj.fromLonLat([ 128, 28 ]),
			          zoom:4
			        })
			      });
			      obj.map = map; 
		obj.fnOnInit();

	}
</script>
<script
	src="${pageContext.request.contextPath}/contents/advis/js/model/vue/disasterDme-controller.min.js?v=20190403"></script>


<%@include file="../inc/bottom.jsp"%>
