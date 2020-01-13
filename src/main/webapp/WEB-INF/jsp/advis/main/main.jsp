<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>재난상황관리 표준화 기술</title>
<meta name="viewport"
	content="width=device-width,maximum-scale=1,minimum-scale=1,user-scalable=no">
<meta name="author" content="재난상황관리 표준화 기술">
<meta name="subject" content="재난상황관리 표준화 기술">
<meta name="description" lang="ko" content="">
<meta name="robots" content="all">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/contents/advis/css/basic.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/contents/advis/css/disaster.css">

<script src="${pageContext.request.contextPath}/contents/base/js/jquery.min.js"></script>
<script type="text/javascript" src="https://d3js.org/d3.v4.min.js"></script>

<script
	src="${pageContext.request.contextPath}/contents/base/js/Chart.js/dist/Chart.bundle.js"></script>


<script type="text/javascript">
	function resizeContent() {
		var windowHeight = $(window).height();
		var topHeight = $('header').height();
		var bottomHeight = $('footer').height() + 40;
		var containerHeight = $('section').css(
				{
					'min-height' : (windowHeight - topHeight - bottomHeight)
							- 113 + 'px'
				});
	}
	$(document).ready(function() {
		resizeContent();
	});

	$(window).resize(function() {
		resizeContent();
	});
</script>
<style type="text/css">
.sub{
	
	width:116px;
	position:absolute;
	z-index:999;
	display:none;
	border-top:solid 1px #dddddd !important;
}
.sub li{
    font-size:14px;
    line-height:27px;
	display:block !important;
	width:116px;
	clear:both;
	background:#ffffff;
	border-bottom:solid 1px #dddddd !important;
}
.sub-item:hover{
 background-color:#2c96ec;
 color:white;
 cursor:pointer;
}
.sub-item a{
	padding:0px !important;
	font-size:14px !important;
}
</style>
</head>
<body>
	<p id="skipnav">
		<a href="#container">본문 내용 바로가기 </a> <a href="#gnb">대메뉴 바로가기 </a>
	</p>
	<div class="wrap">
		<header class="clearfix">
			<div class="header-wrap">
				<h1>
					
				</h1>
				<div class="search-wrap">
				<!-- 	<div class="search-box">
						<input type="text"><a href="/advis/manage/search" id="btn-search"
							class="btn-search"></a>
					</div> -->
					<div style="text-align:center">
					<a href="#"><img
						src="${pageContext.request.contextPath}/contents/advis/images/logo-1.png"
						alt="재난상황관리 표준화 기술" /></a>
						<!-- <span>연관검색어 : </span>
						<ul>
							<li><a href="#">피해</a></li>
							<li><a href="#">지역</a></li>
							<li><a href="#">기상현황</a></li>
							<li><a href="#">태풍</a></li>
						</ul> -->
					</div>
				</div>
				<div class="head-service">
					<ul>
						<li>10.24 (수) <strong>서울시 14&#8451;</strong></li>
					
						<li><a href="/advis/logout?returnURI=/"><img
								src="${pageContext.request.contextPath}/contents/advis/images/ic-logout.png"
								alt="로그아웃" /></a></li>

					</ul>
					<ul class="user-info">
						<li><a href="/system/manage/default"><c:out value="${userName }" /></a></li>
						<li><select>
								<option>대응</option>
								<option>복구</option>
								<option>예방/대비</option>
						</select></li>
					</ul>
				</div>
			</div>
			<div class="nav-wrap">
				<nav id="gnb">
					
					<ul class="clearfix">
						<li><a href="#">호우</a></li>
						<li><a href="/advis/manage/matrix">태풍</a></li>
						<li><a href="#">지진</a></li>
						<li><a href="#">감염</a></li>
						<li><a href="#" id="mnu-search">정보검색</a>
							<ul class="sub">
								<li class="sub-item">유형별 피해현황</li>
								<li class="sub-item"><a href="/advis/manage/search">재난대응 검색</a></li>
							</ul>
						</li>
						
					</ul>
				</nav>
			</div>
		</header>
		
		
		

		<div id="body-container" class="clearfix">
			<div id="container" class="main-contents">
				<section class="summary">
					<p class="section-title">재난현장정보(태풍)</p>
					<p class="summary-title">
						태풍 <span>차바</span>
					</p>
					<div class="table-basic">
						<table>
							<tr>
								<th>강풍반경</th>
								<th>최대풍속</th>
							</tr>
							<tr>
								<td>280<sub>km</sub></td>
								<td>40<sub>m/s</sub></td>
							</tr>
							<tr>
								<th>강도</th>
								<th>크기</th>
							</tr>
							<tr>
								<td>강</td>
								<td>소형</td>
							</tr>
						</table>
					</div>

					<div class="info-box">
						<p>강우량 (시간당)</p>
						<ul>
							<li><label>제주</label><span>171.5<sub>mm</sub></span></li>
							<li><label>서귀포</label><span>141.0<sub>mm</sub></span></li>
						</ul>
					</div>
					<div class="info-box">
						<p>
							특보현황<a href="#" id="btn_detail" class="btn btn-default btn-small">상세보기</a>
						</p>
						<ul>
							<li><label>예비 특보</label><span>태풍, 강풍, 풍랑, 호우, 안개, 해무</span></li>
							<li><label>특보</label><span>풍랑</span></li>
						</ul>
					</div>
				</section>

				<section>
					<p class="section-title">재난유형별 피해현황</p>
					<ul class="section-tab">
						<li class="on"><a id="btn-m" href="#">피해액</a></li>
						<li><a id="btn-r" href="#">복구비</a></li>
						<!-- 	<li><a id="btn-s" href="#">특보현황</a></li> -->
					</ul>

					<!-- 1.카테고리 -->
					<div>
						<div class="btn-group">
							<a href="#" id="btn_dis_people" class="btn btn-gray">인명</a> <a
								href="#" id="btn_dis_money" class="btn btn-default">재산</a>
						</div>
						<div class="chart-wrap">
							<div id="chart1" class="chart1"></div>
							<div>
								<div class="chart" style="width: 500px; height: 290px;">
									<canvas id="chart2"
										style="display: none; position: relative; top: 0px;"></canvas>
									<canvas id="chart3"
										style="display: block; position: relative; top: 0px;"></canvas>
									<canvas id="chart4"
										style="display: none; position: relative; top: 0px;"></canvas>
								</div>
								<div class="table-basic" id="category-1" style="display: none;">
									<p>단위 : 명, 억원</p>
									<table>
										<thead>
											<tr>
												<th></th>
												<th>2008</th>
												<th>2009</th>
												<th>2010</th>
												<th>2011</th>
												<th>2012</th>
												<th>2013</th>
												<th>2014</th>
												<th>2015</th>
												<th>2016</th>
												<th>2017</th>


											</tr>
										</thead>
										<tbody>
											<tr>
												<th>인명피해</th>
												<td>14</td>
												<td>0</td>
												<td>3</td>
												<td>8</td>
												<td>8</td>
												<td>4</td>
												<td>2</td>
												<td>0</td>
												<td>10</td>
												<td>0</td>
											</tr>
											<tr>
												<th>재산피해</th>
												<td>911</td>
												<td>0</td>
												<td>176</td>
												<td>209</td>
												<td>957</td>
												<td>1</td>
												<td>5</td>
												<td>13</td>
												<td>221</td>
												<td>0</td>
											</tr>
										</tbody>
									</table>
								</div>

								<div class="table-basic" id="category-2" style="display: none;">
									<p>단위 :억원</p>
									<table>
										<thead>
											<tr>

												<th>2008</th>
												<th>2009</th>
												<th>2010</th>
												<th>2011</th>
												<th>2012</th>
												<th>2013</th>
												<th>2014</th>
												<th>2015</th>
												<th>2016</th>
												<th>2017</th>


											</tr>
										</thead>
										<tbody>

											<tr>

												<td>1.3</td>
												<td>0</td>
												<td>191</td>
												<td>443</td>
												<td>1,844</td>
												<td>4.1</td>
												<td>10</td>
												<td>28</td>
												<td>522</td>
												<td>0</td>
											</tr>
										</tbody>
									</table>
								</div>

							</div>
						</div>
					</div>



				</section>

				<section class="issue">
					<div class="ol-wrap">
						<p>최근 이슈정보</p>
						<ol>
							<li>호우피해</li>
							<li>지진</li>
							<li>실종원인</li>
							<li>침수</li>
							<li>포항지진</li>
							<li>포항</li>
							<li>침수피해</li>
							<li>사망</li>
							<li>부상</li>
							<li>부상원인</li>
						</ol>
					</div>
					<div class="system-db">
						<ul>
							<li><strong>27<sub>mb</sub></strong></li>
							<li>베타 테스트 구축</li>
							<li>2018-10-31 기준</li>
						</ul>
					</div>
				</section>
				<footer>
					<div>Advanced Disaster visualized Intelligent Interactive and
						Information System</div>
					<address>
						주소. 우)44538 울산광역시 중구 종가로 365 국립재난안전연구원 / TEL : 052-928-8000 <span
							class="copy">Copyright &copy; 2018 by National Disaster
							Management Institute. All Rights Reserved.</span>
					</address>
				</footer>
			</div>
		</div>


		<div class="popup" style="top: 10rem; left: 50rem; display: none;">
			<div class="popup-header">
				기상현황 및 대처상황 보고 내용<a href="#" class="btn-close"></a>
			</div>
			<div class="popup-contents">
				<div class="table-basic">
					<table>
						<colgroup>
							<col style="width: 15%">
							<col style="width: 20%">
							<col style="width: 20%">
							<col style="width: 45%">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">중분류</th>
								<th scope="col">소분류</th>
								<th scope="col">세분류</th>
								<th scope="col">보고서 내용</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td style="width: 132px;" rowspan="2">기상현황</td>
								<td style="width: 132px;" rowspan="2">누적강우량</td>
								<td style="width: 133px;">지역</td>
								<td style="width: 303px;">춘천</td>
							</tr>
							<tr>
								<td style="width: 133px;">강우량</td>
								<td style="width: 303px;">39.8</td>
							</tr>
							<tr>
								<td style="width: 132px;" rowspan="2">통제 및 피해상황</td>
								<td style="width: 132px;">피해상황</td>
								<td style="width: 133px;">도로</td>
								<td style="width: 303px;">충남 천안 : 도로 일부(L=25m) 유실 * 안전선 설치
									및 응급복구 완료(7.24 12:00)</td>
							</tr>
							<tr>
								<td style="width: 132px;">통제상황</td>
								<td style="width: 133px;">달리 분류되지 않은 분류</td>
								<td style="width: 303px;">탐방로 : 2개 공원 49개소(북한산도봉 37, 설악산
									12) 출입통제</td>
							</tr>
							<tr>
								<td style="width: 132px;" rowspan="2">주요 대처상황</td>
								<td style="width: 132px;" rowspan="2">기관명</td>
								<td style="width: 133px;">일시</td>
								<td style="width: 303px;">7.23</td>
							</tr>
							<tr>
								<td style="width: 133px;">대처사항</td>
								<td style="width: 303px;">- 보강근무(7.23, 06:20), 초기
									상황판단회의(7.23, 14:00), 비상 1단계(7.23, 16:00)<br> - 집중호우대비 취약지역
									예방활동 강화 및 선제적상황관리 지시(6회)<br> ․시‧도 및 시‧군‧구, 기상상황에 따라 단계별
									비상근무 철저 이행<br> ․재해위험지구, 해안가 저지대 등 취약시설 및 배수문‧배수장 사전 점검<br>
									․인명피해 우려지역 예찰활동 강화, 위험징후 발견시 주민사전대피 조치(군·경찰 협조)<br> ․인명피해
									우려되는 하천변 유원지, 산간계곡 등 주민·야영객 안전관리 강화<br> ․노후저수지 등 이상 징후시
									하류지역 거주주민 사전대피 조치<br> - 지하차도 등 침수우려 취약도로 관리 철저 지시(7.23)
								</td>
							</tr>
							<tr>
								<td style="width: 132px;">향후 조치계획</td>
								<td style="width: 132px;"></td>
								<td style="width: 132px;"></td>
								<td style="width: 303px;">호우특보지역 강수 지속 모니터링, 산사태․급경사지 등
									취약지역 집중관리</td>
							</tr>
						</tbody>

					</table>
				</div>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		chartColors = {
			red : 'rgb(255, 99, 132)',
			orange : 'rgb(255, 159, 64)',
			yellow : 'rgb(255, 205, 86)',
			green : 'rgb(75, 192, 192)',
			blue : 'rgb(54, 162, 235)',
			purple : 'rgb(153, 102, 255)',
			grey : 'rgb(201, 203, 207)'
		};

		restore = {
			item : [ {
				Name : "호우",
				Money : 3860,
				People : 120
			}, {
				Name : "태풍",
				Money : 3046,
				People : 120
			}, {
				Name : "지진",
				Money : 195,
				People : 120
			}, {
				Name : "대설",
				Money : 124,
				People : 120
			}, {
				Name : "풍랑",
				Money : 36,
				People : 120
			}, {
				Name : "강풍",
				Money : 18,
				People : 120
			} ]

		};

		dataset = {
			"children" : [ {
				Name : "태풍",
				Money : 1587,
				People : 120
			}, {
				Name : "호우",
				Money : 1494,
				People : 130
			}, {
				Name : "지진",
				Money : 96,
				People : 20
			}, {
				Name : "대설",
				Money : 226,
				People : 10
			}, {
				Name : "풍랑",
				Money : 42,
				People : 3
			}, {
				Name : "강풍",
				Money : 39,
				People : 2
			} ]

		};

		$(function() {

			$("#mnu-search").click(function(){
				$(".sub").toggle();
			});
			
			$("#btn_dis_people").click(function() {
				$("#chart1").html("");
				$("#btn_dis_money").removeClass("btn-gray");
				$("#btn_dis_money").addClass("btn-default");
				$(this).removeClass("btn-gray");
				$(this).addClass("btn-gray");
				createBubbleChartPeople(dataset);
			});

			$("#btn_dis_money").click(function() {
				$("#chart1").html("");
				$("#btn_dis_people").removeClass("btn-gray");
				$("#btn_dis_people").addClass("btn-default");
				$(this).removeClass("btn-default");
				$(this).addClass("btn-gray");
				createBubbleChartMoney(dataset);
			});

			$("#btn_detail").click(function() {
				$(".popup").toggle();
			});

			$("#btn-m").click(function() {

				$("#btn-r").parent().removeClass("on");
				$("#btn-m").parent().removeClass("on");
				$(this).parent().addClass("on")
				$("#chart3").hide();
				$("#chart2").show();
				$("#category-2").hide();
				$("#category-1").show();

			});

			$("#btn-r").click(function() {

				$("#btn-r").parent().removeClass("on");
				$("#btn-m").parent().removeClass("on");
				$(this).parent().addClass("on")
				$("#chart2").hide();
				$("#chart3").show();
				$("#category-1").hide();
				$("#category-2").show();
			});

			$(".btn-close").click(function(){
				$(".popup").toggle();
			});
			
			$("#btn-m").click();
			$("#btn_dis_people").click();
		});

		function createBubbleChartPeople(dataset) {
			var diameter = 400;
			var color = d3.scaleOrdinal(d3.schemeCategory10);

			var bubble = d3.pack(dataset).size([ diameter, diameter ]).padding(
					1.5);

			var svg = d3.select("#chart1").append("svg")
					.attr("width", diameter).attr("height", diameter).attr(
							"class", "bubble");

			var nodes = d3.hierarchy(dataset).sum(function(d) {
				return d.People;
			});

			var node = svg.selectAll(".node").data(bubble(nodes).descendants())
					.enter().filter(function(d) {
						return !d.children
					}).append("g").attr("class", "node").attr("transform",
							function(d) {
								return "translate(" + d.x + "," + d.y + ")";
							});

			node.append("title").text(function(d) {

				return d.data.Name + ": " + d.data.People;
			});

			node.append("circle").attr("r", function(d) {
				return d.r;
			}).style("fill", function(d, i) {
				return color(i);
			});
			node.append("text").attr("dy", ".2em").style("text-anchor",
					"middle").text(function(d) {
				return d.data.Name.substring(0, d.r / 3);
			}).attr("font-family", "sans-serif").attr("font-size", function(d) {
				return d.r / 5;
			}).attr("fill", "white");

			node.append("text").attr("dy", "1.3em").style("text-anchor",
					"middle").text(function(d) {
				return d.data.People;
			}).attr("font-family", "Gill Sans", "Gill Sans MT").attr(
					"font-size", function(d) {
						return d.r / 5;
					}).attr("fill", "white");
		}

		function createBubbleChartMoney() {
			var diameter = 400;
			var color = d3.scaleOrdinal(d3.schemeCategory10);

			var bubble = d3.pack(dataset).size([ diameter, diameter ]).padding(
					1.5);

			var svg = d3.select("#chart1").append("svg")
					.attr("width", diameter).attr("height", diameter).attr(
							"class", "bubble");

			var nodes = d3.hierarchy(dataset).sum(function(d) {
				return d.Money;
			});

			var node = svg.selectAll(".node").data(bubble(nodes).descendants())
					.enter().filter(function(d) {
						return !d.children
					}).append("g").attr("class", "node").attr("transform",
							function(d) {
								return "translate(" + d.x + "," + d.y + ")";
							});

			node.append("title").text(function(d) {

				return d.data.Name + ": " + d.data.Money;
			});

			node.append("circle").attr("r", function(d) {
				return d.r;
			}).style("fill", function(d, i) {
				return color(i);
			});
			node.append("text").attr("dy", ".2em").style("text-anchor",
					"middle").text(function(d) {
				return d.data.Name.substring(0, d.r / 3);
			}).attr("font-family", "sans-serif").attr("font-size", function(d) {
				return d.r / 5;
			}).attr("fill", "white");

			node.append("text").attr("dy", "1.3em").style("text-anchor",
					"middle").text(function(d) {
				return d.data.Money;
			}).attr("font-family", "Gill Sans", "Gill Sans MT").attr(
					"font-size", function(d) {
						return d.r / 5;
					}).attr("fill", "white");
		}

		var chartData = {
			labels : [ '2008', '2009', '2010', '2011', '2012', '2013', '2014',
					'2015', '2016', '2017' ],
			datasets : [ {
				type : 'line',
				label : '재산피해',
				borderColor : chartColors.blue,
				borderWidth : 2,
				fill : false,
				yAxisID : 'y-axis-1',
				data : [

				911, 0, 176, 209, 957, 1, 5, 13, 221, 0

				]
			}, {
				type : 'bar',
				label : '인명피해',
				backgroundColor : chartColors.red,
				yAxisID : 'y-axis-2',
				data : [

				14, 0, 3, 8, 8, 4, 2, 0, 10, 0

				],
				borderColor : 'white',
				borderWidth : 2
			} ]

		};
		window.onload = function() {
			var ctx2 = document.getElementById('chart3').getContext('2d');
			window.char2 = new Chart(ctx2,
					{
						type : 'bar'

						,
						data : {
							labels : [ '2008', '2009', '2010', '2011', '2012',
									'2013', '2014', '2015', '2016', '2017' ],
							datasets : [ {

								label : '복구비용',
								backgroundColor : chartColors.red,
								data : [ 1.3, 0, 191.6, 443.6, 1.8, 4.1, 10,
										28, 522, 0 ]
							} ]

						}//end data
						,
						options : {

							scales : {
								xAxes : [ {
									display : true,
									scaleLabel : {
										display : true,
										labelString : '년도'
									}
								} ],
								yAxes : [ {
									display : true,
									scaleLabel : {
										display : true,
										labelString : '금액(억원)'
									}
								} ]
							}
						}
					});

			var ctx3 = document.getElementById('chart4').getContext('2d');
			//	window.char3=new Chart(ctx3,{});

			var ctx = document.getElementById('chart2').getContext('2d');
			window.myMixedChart = new Chart(ctx, {
				type : 'bar',
				data : chartData,
				options : {
					responsive : true,
					title : {
						display : false,
						text : 'Chart.js Combo Bar Line Chart'
					},
					tooltips : {
						mode : 'index',
						intersect : true
					},
					scales : {
						yAxes : [ {
							type : 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
							display : true,
							position : 'left',
							id : 'y-axis-1',
							scaleLabel : {
								display : true,
								labelString : '피해금액(억원)'
							}
						}, {
							type : 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
							display : true,
							position : 'right',
							id : 'y-axis-2',
							gridLines : {
								drawOnChartArea : false
							},
							scaleLabel : {
								display : true,
								labelString : '인명피해(명)'
							}
						} ],
					}
				}
			});
		};
	</script>
</body>
</html>