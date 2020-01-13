<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="../inc/top.jsp"%>
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
			<li><a href="/advis/disaster/status">태풍</a></li>
			<li class="on"><a href="/advis/disaster/snow">대설</a></li>
			<li><a href="/advis/disaster/earthquake">지진</a></li>
			<li><a href="/advis/disaster/society">사회재난</a></li>
		</ul>
<!-- 		<div class="text-right"> -->
<!-- 			<button class="btn btn-default" v-on:click="fnOnClickShowInfo()">재난업무 단계별 우선순위 보기</button> -->
<!-- 		</div> -->
		<div class="table-basic tm20" v-if="viewModel.showInfo">
			<table>
				<tr><th>중요도</th><th>예방단계</th><th>대비단계</th><th>대응단계</th><th>복구단계</th></tr>
				<tr><td>1 순위</td><td><a href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/contents/prevent/prevent02.html?menuSeq=126" target="_blank">국민행동요령</a></td>
					<td><a href="http://www.weather.go.kr/weather/typoon/prediction.jsp" target="_blank">예상 진로</a></td>
					<td><a href="http://www.weather.go.kr/weather/typoon/prediction.jsp" target="_blank">예상 진로</a></td>
					<td><a href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/sfc/ppb/ppbeaiList.jsp?emgPage=Y&menuSeq=116" target="_blank">복구 인원 및 물자 현황</a></td></tr>
				<tr><td>2 순위</td><td><a href="https://www.weather.go.kr/weather/warning/standard.jsp" target="_blank">기상특보 기준</a></td>
					<td><a href="http://www.weather.go.kr/weather/typoon/detail.jsp" target="_blank">태풍 위치</a></td>
					<td><a href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/contents/civil_defense/SDIJKM1402.html?menuSeq=57" target="_blank">대피소 현황</a></td>
					<td><a href="https://www.mois.go.kr/frt/bbs/type001/commonSelectBoardList.do?bbsId=BBSMSTR_000000000336" target="_blank">향후 조치 계획</a></td></tr>
				<tr><td>3 순위</td><td><a href="http://www.weather.go.kr/weather/typoon/prediction.jsp" target="_blank">예상 진로</a></td>
					<td><a href="http://www.weather.go.kr/weather/typoon/report.jsp" target="_blank">모니터링 현황</a></td>
					<td><a href="http://www.khoa.go.kr/koofs/kor/observation/obs_real.do" target="_blank">조위정보</a></td>
					<td><a href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/contents/insurance/SDIJKM2101.html?menuSeq=303" target="_blank">보험 및 보상 기준</a></td></tr>
				<tr><td>4 순위</td>
					<td><a href="http://www.weather.go.kr/weather/typoon/report.jsp" target="_blank">특별 관심 지구</a></td>
					<td><a href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/sfc/fcl/riskUserList.html?menuSeq=314" target="_blank">재해위험지구 현황</a></td>
					<td><a href="http://typ.kma.go.kr/TYPHOON/contents/contents_04_4_1.jsp" target="_blank">국민행동요령</a></td>
					<td><a href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/contents/civil_defense/SDIJKM1402.html?menuSeq=57" target="_blank">대피소 현황</a></td></tr>
				<tr><td>5 순위</td><td><a href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/csc/bbs_conf.jsp?menuSeq=593&bbs_no=9&viewtype=read&bbs_ordr=142" target="_blank">재난대응 매뉴얼</a></td>
					<td><a href="http://www.weather.go.kr/weather/typoon/report.jsp" target="_blank">기상개황</a></td>
					<td><a href="http://www.weather.go.kr/weather/observation/aws_table_popup.jsp" target="_blank">시간당강우량</a></td>
					<td><a href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/sfc/tot/toteaiList.jsp?emgPage=Y&menuSeq=111" target="_blank">피해현황</a></td></tr>
				<tr><td>6 순위</td><td><a href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/sfc/fcl/riskUserList.html?menuSeq=314" target="_blank">재해위험지구 현황</a></td>
					<td><a href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/contents/civil_defense/SDIJKM1301.html?menuSeq=53" target="_blank">대국민전파체계</a></td>
					<td><a href="https://www.mois.go.kr/frt/bbs/type001/commonSelectBoardList.do?bbsId=BBSMSTR_000000000336" target="_blank">통제상황</a></td>
					<td><a href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/sfc/emd/medicalUserList.html?menuSeq=320" target="_blank">긴급 의료 기관</a></td></tr>
				<tr><td>7 순위</td><td><a href="http://www.weather.go.kr/weather/typoon/detail.jsp" target="_blank">태풍 위치</a></td>
					<td><a href="http://www.weather.go.kr/weather/typoon/report.jsp" target="_blank">특별 관심 지구</a></td>
					<td><a href="http://www.weather.go.kr/weather/observation/aws_table_popup.jsp" target="_blank">누적강우량</a></td>
					<td><a href="https://www.mois.go.kr/frt/bbs/type001/commonSelectBoardList.do?bbsId=BBSMSTR_000000000336" target="_blank">통제상황</a></td></tr>
				<tr><td>8 순위</td><td><a href="http://www.law.go.kr/%EB%B2%95%EB%A0%B9/%EC%9E%AC%EB%82%9C%EB%B0%8F%EC%95%88%EC%A0%84%EA%B4%80%EB%A6%AC%EA%B8%B0%EB%B3%B8%EB%B2%95" target="_blank">재난 및 안전관리 기본법</a></td>
					<td><a href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/contents/prevent/prevent02.html?menuSeq=126" target="_blank">국민행동요령</a></td>
					<td><a href="http://www.weather.go.kr/weather/typoon/detail.jsp" target="_blank">태풍 위치</a></td>
					<td><a href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/sfc/dis/disasterNewsList.jsp?emgPage=Y&menuSeq=619" target="_blank">복합재난발생 확률</a></td></tr>
				<tr><td>9 순위</td><td><a href="http://www.law.go.kr/LSW/admRulLsInfoP.do?chrClsCd=&admRulSeq=2100000178060#AJAX" target="_blank">비상근무 기준 및 현황</a></td>
					<td><a href="http://www.weather.go.kr/weather/observation/aws_table_popup.jsp" target="_blank">시간당강우량</a></td>
					<td><a href="http://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/sfc/emd/medicalUserList.html?menuSeq=320" target="_blank">긴급 의료 기관</a></td>
					<td><a href="https://www.safetyreport.go.kr/#main" target="_blank">신고 및 제보 현황</a></td></tr>
				<tr><td>10 순위</td><td><a href="http://www.law.go.kr/admRulLsInfoP.do?admRulSeq=2100000118982" target="_blank">시설 안전기준</a></td>
					<td><a href="http://www.khoa.go.kr/kcom/cnt/selectContentsPage.do?cntId=31105000" target="_blank">조위 피해지역</a></td>
					<td><a href="http://www.law.go.kr/LSW/admRulLsInfoP.do?chrClsCd=&admRulSeq=2100000178060#AJAX" target="_blank">비상근무 기준 및 현황</a></td>
					<td><a href="https://www.mois.go.kr/frt/bbs/type001/commonSelectBoardList.do?bbsId=BBSMSTR_000000000336" target="_blank">기관별 대처상황</a></td>
					</tr>
			</table>
		</div>
		<div class="search">
			<select  v-model="viewModel.selectTopDmeCode" class="select-box-width-120" v-on:change='fnChangeTyphoonNameList()' disabled="disabled">
				<option v-for="item in viewModel.listTopDmeCode" v-bind:value='item.code' >{{item.code_name_dme}}</option>
			</select> 
			<select v-model="viewModel.stDate" class="select-box-width-120" v-on:change='fnChangeTyphoonNameList()'>
					<option v-for="item in viewModel.listYearsSelect" v-bind:value="item">{{item}}년  </option>
			</select>
			<select v-model="viewModel.selectDmeDate"  class="select-box-width-120"  v-if="viewModel.selectTopDmeCode == 'HZD002' || viewModel.selectTopDmeCode == 'HZD007'" v-on:change='fnChangeSidoList()'>
				<option value="">전체</option>				
				<option v-for="item in viewModel.listDmeDateCode" v-bind:value="item.typ_seq + '_' +item.beg_date + '_' + item.end_date">{{item.typ_name}}</option>
			</select>
			<select v-model="viewModel.selectDmeDate"  class="select-box-width-120"  v-else  v-on:change='fnChangeSidoList()'>
				<option value="">전체</option>
				<option v-for="item in viewModel.listDmeDateCode" v-bind:value="item.beg_date + '_' + item.end_date">{{item.beg_month}}월 {{item.beg_day}}일</option>
			</select>
			<select v-model="viewModel.selectDmeSido"  class="select-box-width-120">
				<option value="">전체</option>
				<option v-for="item in viewModel.listDmeSidoCode" v-bind:value="item.sido_code">{{item.sido}}</option>
			</select>
			<a href="#" class="btn btn-purple" v-on:click="fnOnClickBtnSearch()">조회</a>
		</div>
		<div>
			<div class="table-basic" >
				<table>
					<caption></caption>
					<thead>
						<tr v-if="viewModel.viewType=='person'">						
							<th>재난시작일</th>
							<th>재난종료일</th>
							<th>재해명</th>
							<th>인명피해(명)</th>						
							<th>피해액(천원)</th>
						</tr>
						<tr v-if="viewModel.viewType!='person'">
							<th>재난시작일</th>
							<th>재난종료일</th>
							<th>재해명</th>
							<th>피해액(천원)</th>
							<th>인명피해(명)</th>	
						</tr>
					</thead>
					<tbody >
						<tr v-if="viewModel.viewType=='person'" v-for="item in viewModel.listWholeDme" v-bind:class="{'selected':viewModel.selectedParamStDate==item.beg_date&&viewModel.selectedParamEndDate==item.end_date}">
							<td><span><a href="#" v-on:click="fnOnClickGunguDme(item, true)"><u>{{item.beg_date.substring(0,4)}} - {{item.beg_date.substring(4,6)}} - {{item.beg_date.substring(6,8)}}</u></a></span></td>
							<td>{{item.end_date.substring(0,4)}} - {{item.end_date.substring(4,6)}} - {{item.end_date.substring(6,8)}}</td>
							<td><span v-if="item.typ_name == null">{{item.damage_name}}</span> 
								<span v-else>{{item.damage_name}}({{item.typ_name}})</span>
							</td>	
							<td >{{item.com_dme}}</td>
							<td >{{item.total_damage.format()}}</td> 
						</tr>
						<tr v-if="viewModel.viewType!='person'" v-for="item in viewModel.listWholeDme" v-bind:class="{'selected':viewModel.selectedParamStDate==item.beg_date&&viewModel.selectedParamEndDate==item.end_date}">
							<td>{{item.sido_code}}<span><a href="#" v-on:click="fnOnClickGunguDme(item, false)"><u>{{item.beg_date.substring(0,4)}} - {{item.beg_date.substring(4,6)}} - {{item.beg_date.substring(6,8)}}</u></a></span></td>
							<td>{{item.end_date.substring(0,4)}} - {{item.end_date.substring(4,6)}} - {{item.end_date.substring(6,8)}}</td>
							<td><span v-if="item.typ_name == null">{{item.damage_name}}</span> 
								<span v-else>{{item.damage_name}}({{item.typ_name}})</span>
							</td>		
							<td >{{item.total_damage.format()}}</td>
							<td >{{item.com_dme}}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- <div class="text-right" v-if="viewModel.viewType!='person'"> <small>※ 호우는 재난시작일이 검색조건이며 태풍은 해당년도에 발생한 태풍을 조회 가능합니다.</small></div>	 -->
		</div><br>
		<div class="table-basic">
			<table>
				<caption></caption>
				<thead>
					<tr v-if="viewModel.viewType=='person'">
						<th>재해명</th>
						<th>시도</th>
						<th>시군구</th>
						<th>해당 재난<br> 인명피해수</th>
						<th>해당 재난 피해액<br>(천원)</th>
						<th>1985년 부터 <br>총 인명피해수</th>
						<!-- <th>최대일강우<br>(mm)</th>
						<th>최대시강우<br>(mm)</th> -->
					</tr>
					<tr v-if="viewModel.viewType!='person'">
						<th>재해명</th>
						<th>시도</th>
						<th>시군구</th>
						<th>해당 재난<br> 피해액(천원)</th>
						<th>1985년 부터<br>총 피해액</th>
						<th>해당 재난<br> 인명피해수</th>
						<!-- <th>최대일강우<br>(mm)</th> -->
						<th>피해시설</th>
					</tr>
				</thead>
				<tbody  id="mytable">
					<tr v-bind:class="{'selected':selectedSigunguCode==item.sigungu_code}"  v-if="viewModel.viewType=='person'"  v-for="item in viewModel.listTopComDme">
						<td><span v-if="item.typ_name == null">{{item.damage_name}}</span> 
							<span v-else>{{item.damage_name}}({{item.typ_name}})</span>
						</td>		
						<td>{{item.sido}}</td>
						<td>
							<span v-if="item.sigungu != null"><a v-on:click="fnGetDamageList(item.sigungu_code, this)"><u>{{item.sigungu}}</u></a></span>
							<span v-else>{{item.sido}}</span>
						</td>
						<td>{{item.com_dme}}</td>
						<td>{{item.total_damage.format()}}</td>
						<td >{{item.total_com_dme}}</td>
						<!-- <td >{{item.rn_day}}</td>
						<td >{{item.rn_60m_max}}</td> -->
					</tr>
					<tr v-bind:class="{'selected':viewModel.selectedDamageGungu.sigungu_code==item.sigungu_code}" v-if="viewModel.viewType!='person'" v-for="item in viewModel.listTopComDme">
						<td><span v-if="item.typ_name == null">{{item.damage_name}}</span> 
							<span v-else>{{item.damage_name}}({{item.typ_name}})</span>
						</td>	
						<td>{{item.sido}}</td>
						<td>
							<span v-if="item.sigungu != null"><a v-on:click="fnGetDamageList(item)"><u>{{item.sigungu}}</u></a></span>
							<span v-else>{{item.sido}}</span>
						</td>
						<td >{{item.total_damage.format()}}</td>
						<td >{{item.all_total_damage.format()}}</td>
						<td >{{item.com_dme}}</td>
						<!-- <td >{{item.rn_day}}</td> -->
						<td>
							<span v-if="item.pri_name != '' && item.pub_name != ''">{{item.pri_name}},{{item.pub_name}}</span>
							<span v-if="item.pri_name != '' && item.pub_name == ''">{{item.pri_name}}</span>
							<span v-if="item.pri_name == '' && item.pub_name != ''">{{item.pub_name}}</span>
							<span v-if="item.pri_name == '' && item.pub_name == ''">-</span>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="text-right" v-if="viewModel.viewType=='person'"> <small>※ 총 인명 피해수는 1985년 부터 조회기간년도까지의 인명피해수입니다.</small></div>	
		<div class="text-right" v-if="viewModel.viewType!='person'"> <small>※ 총 시설 피해액은 1985년 부터 조회기간년도까지의 재난피해액입니다.</small></div>
		<div class="section-title"><ul><li>{{viewModel.selectedDamageGungu.sigungu}}의 대설 피해현황</li></ul></div>
		<div class="table-basic">
			<table>
				<tr><th>재해 시작일</th><th>재해 종료일</th><th>인명피해(명)</th><th>시설피해액(천원)</th><!-- <th>최대 일강우(mm)</th><th>최대 시강우(mm)</th> --></tr>
				<tr  v-for="item in viewModel.listDamage">
				<td>{{item.beg_date.dateFormat()}}</td>
				<td>{{item.end_date.dateFormat()}}</td>
				<td>{{item.com_dme}}</td>
				<td>{{item.total_damage.format()}}</td>
				<!-- <td>{{item.rn_day}}</td>
				<td>{{item.rn_60m_max}}</td> -->
				</tr>
			</table>
		</div>
		<br>
<!-- 		<div class="section-title"><ul><li>대응 현황</li></ul></div> -->
<!-- 		<div class="table-basic" v-if="keyListView!=null&&keyListView.length>0"> -->
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
<!-- 		<div class="table-basic" v-if="keyListView==null||keyListView.length==0"> -->
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
</div>
<script type="text/javascript">
function pageLoad(obj){	
	obj.fnOnInit('HZD008');
}
</script>
<script	src="${pageContext.request.contextPath}/contents/advis/js/model/vue/disasterDmeSnow-controller.min.js?v=20190403"></script>
<%@include file="../inc/bottom.jsp"%>