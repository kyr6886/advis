<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>재난상황관리 표준화 기술</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basic.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/disaster.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">
	<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
	<script src="${pageContext.request.contextPath}/contents/advis/js/jquery-stringFormat.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/advis/js/polyfill.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/advis/js/extends.js"></script>
	<style type="text/css">
	.links line {
	  stroke: #999;
	  stroke-opacity: 0.6;
	}
	#manualChart text{
		font-size:11px;
		
	}
	
	.nodes .title {
	  fill:#ffffff;
	
	}
	.nodes circle{
		stroke:#999;
		stroke-width:1px;
		stroke-opacity: 0.6;
	}
	svg{
		border:1px solid #e8e6e6;
		background-color:#f8f9fb;
	}
</style>
</head>
<body>
	 <div class="wrap" id="scope-report" v-cloak>
	 	<div id="loading-layer"><div class="loading-content">{{viewModel.message}}</div>
	 	<img id="loading-image" src="/images/ajax-loader.gif" alt="Loading..." />
	 	</div>
	  	<div id="container">
			<div class="result-info text-center">
				<span> {{viewModel.currentTyphoonInfo.year}} 년 {{viewModel.currentTyphoonInfo.month}}월 15호 태풍 <em> <b>{{viewModel.currentTyphoonInfo.typ_name}}</b></em> 유사 피해 분석 </span>
			</div>
			<div>
				<a href="/report/typ/write" target="_blank" class="btn btn-blue" style="float: right;">리포트 작성</a>
			</div>
			
			<div class="report-row">
				<div class="section-title"><ul><li>특보 발령 지역<small> (*최근 10년간 특보 지역 태풍 피해 현황) </small></li></ul></div>
				<div>
				<div class="column-50">
					<div>
						<div id="map1" class="report-map"></div>
				
					</div>
				</div>
				<div class="table-basic column-50 report-table">
					<table>
						<tr><th>시도</th><th>시군구</th><th>인명피해(명)</th><th>시설 피해액(억원)</th></tr>
						<tr v-for="item in viewModel.listDamagesByKmaReport">
							<td>{{item.sido}}</td>
							<td>{{item.sigungu}}</td>
							<td class="text-right">{{item.com_dme}}</td>
							<td class="text-right">{{(parseFloat(item.total_damage/100000)).toFixed(2)}}</td>
						</tr>
					
					</table>
				</div>
				</div>
			</div>
			
			<div class="report-row">
				<div class="section-title"><ul><li>유사경로 태풍<small> (*현재 태풍의 예측경로 기준) </small></li></ul></div>
				<div>
					<div class="column-50">
						<div id="map2" class="report-map"></div>
					</div>
					<div class="table-basic column-50 report-table">
						<table>
							<tr><th>태풍명</th><th>최대 중심기압(hPa)</th><th>평균 이동속도(m/s)</th><th>최대 풍속(m/s)	</th></tr>
<!-- 							<tr v-for="item,index in viewModel.listSimilarTyphoons" v-if="index == 0"> -->
							<tr>
								<td><span v-on:click='fnShowLayer(viewModel.maxDamageTyphoon.typ_name)'>{{viewModel.maxDamageTyphoon.typ_name}}</span></td>
								<td>{{viewModel.maxDamageTyphoon.maxPs}}</td>
								<td>{{viewModel.maxDamageTyphoon.maxSp}}</td>
								<td>{{viewModel.maxDamageTyphoon.maxWs}}</td>
							</tr>
						</table>
						<canvas id="typ-chart" style="border: solid 1px #dddddd; width:706px; height:255px; margin-top:10px;"></canvas>
					</div>
				</div>
			</div>
		
			<div class="report-row-nolimit">
				<div class="section-title"><ul><li>{{viewModel.maxDamageTyphoon.typ_name}} 피해 내용<small> </small></li></ul></div>
				<div class="column-50">
					<svg class="graph" id="damageMap" width="700" height="450"  >
		            	<g></g></svg>
            	</div>
				<div class="table-basic column-50">
					<table>
						<tr><th>시도</th><th>시군구</th><th>인명피해(명)</th><th>사유시설 피해액(억원)</th><th>공공시설 피해액(억원)</th><th>총 시설 피해액(억원)</th></tr>
						<tr v-for="item in viewModel.maxDamageTyphoon.moneyDamage">
						<td>{{item.sido}}</td>
						<td>{{item.sigungu}}</td>
						<td>{{item.com_dme}}</td>
						<td>{{(parseFloat(item.pri_total/100000)).toFixed(2)}}</td>
						<td>{{(parseFloat(item.pub_total/100000)).toFixed(2)}}</td>
						<td>{{(parseFloat(item.total_damage/100000)).toFixed(2)}}</td>
						</tr>
					</table>
				</div>
			</div>
			
			<div class="report-row-nolimit">
				<div class="section-title"><ul><li>피해상황<small> </small></li></ul></div>
				<div class="table-basic ">
					<table>
						<tr>
							<th>구분</th>
							<th>내용</th>
						</tr>
						<tr v-for="item in damageList">
							<td>{{item.contentsTitle}}</td>
							<td class="action-color" v-on:click="fnClickEventLink(item.evt_id)" v-html="item.contents"></td>
						</tr>
					</table>
				</div>
			</div>
			
			<div class="report-row-nolimit">
				<div class="section-title">대응활동</div>
			
				<div class="column-50">
					<svg class="chart" id="manualChart" width="700" height="450" style="overflow: visible;">
		            	<g></g>
	            	</svg>
            	</div>
            	
<!--             	<div class="section-title">대응 현황</div> -->
				<div v-show="keyListView!=null && keyListView.length>0">
					<div class="time-table result-info-half">
						<table>
							<tr>
								<th></th>
								<th v-for="item in keyListView">
									<div v-if="item.detail.length>0">
										<div v-if="item.date.substring(6)=='00:00'">{{item.date.substring(0,6)}}</div>
										<div v-if="item.date.substring(6)!='00:00'">{{item.date}}</div>
									</div>
								</th>
							</tr>
							<tr v-for="keyItem in orgList">
								<td class='noBorder text-right'>
									{{keyItem}}
								</td>
								<td v-for="item in keyListView">
									<div v-for="dItem in item.detail">
										<div v-if="keyItem == dItem.org" class="action-color" v-on:click="fnClickEventLink(dItem.contentId)"> &nbsp; </div>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div v-show="keyListView===null||keyListView.length==0">
					<div class="time-table result-info" >
						해당 내용이 없습니다.
					</div>
				</div>
			</div>
			
	 	</div>
	 </div>
	 
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCO6oQoC5ZRaX-nKSXeLChM_KWwu6-v4BM&v=3.21&region=KR"></script>
<script	src="${pageContext.request.contextPath}/openlayers3/build/ol.js"></script>
<script	src="${pageContext.request.contextPath}/openlayers3/build/ol3gm.js"></script>
<script	src="${pageContext.request.contextPath}/map/map.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/openlayers3/css/ol.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/openlayers3/css/ol3gm.css">
	 <script type="text/javascript">
	 $("#loading-layer").show();
	 	function pageLoad(obj){
	 		
	 		  var raster1 = new ol.layer.Tile({
			        source: new ol.source.OSM()
			      });

			      var map1 = new ol.Map({
			        layers: [raster1],
			        target: 'map1',
			        view: new ol.View({
			          center: ol.proj.fromLonLat([ 128, 28 ]),
			          zoom:4
			        })
			      });
			       
	 		
			      var raster2 = new ol.layer.Tile({
				        source: new ol.source.OSM()
				      });

				      var map2 = new ol.Map({
				        layers: [raster2],
				        target: 'map2',
				        view: new ol.View({
				          center: ol.proj.fromLonLat([ 128, 28 ]),
				          zoom:4
				        })
				      });
				      
	 	
	 	    
	 	    obj.currentTypMap=map1;
	 	    obj.smTypMap=map2;
	 	    
	 	   obj.fnListInform();
	 	}
	 </script>
	 <script src="${pageContext.request.contextPath}/contents/advis/js/model/vue/similarPathTyphoon-controller.min.js"></script>
	<script src="${pageContext.request.contextPath}/contents/advis/js/model/galaxy.js"></script>
	<script src="${pageContext.request.contextPath}/contents/advis/js/model/main-d3.js"></script>

</body>
</html>

