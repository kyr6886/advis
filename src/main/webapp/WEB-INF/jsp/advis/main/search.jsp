<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>재난상황관리 표준화 기술</title>
	<meta name="viewport" content="width=device-width,maximum-scale=1,minimum-scale=1,user-scalable=no">
	<meta name="author" content="재난상황관리 표준화 기술">
	<meta name="subject" content="재난상황관리 표준화 기술">
	<meta name="description" lang="ko" content="">
	<meta name="robots" content="all">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/contents/advis/css/basic.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/contents/advis/css/disaster.css">
	
	<script src="${pageContext.request.contextPath}/contents/base/js/jquery.min.js"></script>
	<script type="text/javascript" src="https://d3js.org/d3.v4.min.js"></script>
	<script type="text/javascript">
	function resizeContent() {
		var windowHeight = $(window).height();
		var topHeight = $('header').height();
		var bottomHeight = $('footer').height() +40;
		var containerHeight = $('section').css({'min-height':(windowHeight-topHeight-bottomHeight)-113+'px'});
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
		<a href="#container">본문 내용 바로가기</a>
		<a href="#gnb">대메뉴 바로가기</a>
	</p>
	<div class="wrap">
		<header class="clearfix">
			<div class="header-wrap">
				<h1><a href="/advis/manage/main"><img src="${pageContext.request.contextPath}/contents/advis/images/logo-1.png" alt="재난상황관리 표준화 기술"/></a></h1>
				<div class="search-wrap">
					<div class="search-box">
						<input type="text" value="태풍"><a href="#" class="btn-search"></a>
					</div>
					<div>
						<span>연관검색어 : </span>
						<ul>
							<li><a href="#">피해</a></li>
							<li><a href="#">지역</a></li>
							<li><a href="#">기상현황</a></li>
							<li><a href="#">태풍</a></li>
						</ul>
					</div>
				</div>
				<div class="head-service">
					<ul>
						<li>10.24 (수) <strong>서울시 14&#8451;</strong></li>

						<li><a href="/advis/logout?returnURI=/"><img src="${pageContext.request.contextPath}/contents/advis/images/ic-logout.png" alt="로그아웃"/></a></li>
					</ul>
					<ul class="user-info">
						<li><a href="/system/manage/default"><c:out value="${userName }" /></a></li>
						<li>
							<select>
								<option>대응</option>
								<option>복구</option>
								<option>예방/대비</option>
							</select>
						</li>
					</ul>
				</div>
			</div>
			<div class="nav-wrap">
				<nav id="gnb">
					
					<ul class="clearfix">
						<li><a href="#">호우</a></li>
						<li><a href="/advis/manage/matrix" >태풍</a></li>
						<li><a href="#">지진</a></li>
						<li><a href="#">감염</a></li>
						<li><a href="#" id="mnu-search" class="active">정보검색</a>
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
	    			<div>
	    				<select>
	    					<option value="2017">2017</option>
	    					<option value="2016" selected="selected">2016</option>
	    					<option value="2015">2015</option>
	    					<option value="2014">2014</option>
	    					<option value="2013">2013</option>
	    					<option value="2012">2012</option>
	    				</select>
	    				<select>
	    					<option>01</option>
	    					<option>02</option>
	    					<option>03</option>
	    					<option>04</option>
	    					<option>05</option>
	    					<option>06</option>
	    					<option>07</option>
	    					<option>08</option>
	    					<option>09</option>
	    					<option selected="selected">10</option>
	    					<option>11</option>
	    					<option>12</option>
	    				</select>
	    			</div>
	    			<ul>
	    				<li><a href="#" style="font-weight:bold;color:blue;padding:0px;">2016-10-05 태풍</a></li>
	    				<li><a href="#">2016-10-04 태풍</a></li>
	    				<li><a href="#">2016-10-03 태풍</a></li>
	    		
	    				
	    			</ul>
	    		</section>
	    		
	    		<section style="padding:0px;">
	    		
	    			<div class="chart" id="chart1" style="">
	    				<img src="/contents/advis/images/ex-chart5.png" style="width:540px;"/>
	    			</div>
	    		</section>
	    		
	    		<section>
	    			<div>
	    				<a href="#" id="btn-category-1" class="btn btn-default btn-icon btn-category active"><i class="intergrated"></i>통합검색</a>
	    				<a href="#" id="btn-category-2" class="btn btn-default btn-category btn-icon"><i class="weather"></i>기상현황</a>
	    				<a href="#" id="btn-category-3" class="btn btn-default btn-category btn-icon"><i class="damaged"></i>통제 및 피해상황</a>
	    				<a href="#" id="btn-category-4" class="btn btn-default btn-category btn-icon"><i class="handle"></i>주요 대처상황</a>
	    				<a href="#" id="btn-category-5" class="btn btn-default btn-category btn-icon"><i class="plans"></i>향후계획</a>
	    				<a href="#" id="btn-category-6" class="btn btn-default btn-category btn-icon"><i class="plans"></i>기타자료</a>
	    			</div>
	    			<div class="list-result" id="category-1" style="display:block">
	    				<p>검색결과 : 157건</p>
		    			<ul>
		    				<li>
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">태풍 차바 대처상황 보고
		    						<span>
			    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a>
		    						</span>
		    					</h3>
		    					<ul>
		    						<li>2016.10.05(목) 10:00</li>
		    						<li>피해현황: 재산피해 00억 ~ 00억 / 인명피해 00명</li>
		    						<li>태풍명: 제18호 태풍 차바 / 기상개황: 기상특보</li>
		    						<li>풍랑 : (10.2.저녁)- 남해동부먼바다, 제주도남쪽먼바다 / 기상상황 누적강수량 서산 18.1, 수원 11.3</li>
		    					</ul>
		    				</li>
		    				<li>
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">태풍 차바 대처상황 보고
		    						<span>
			    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a>
		    						</span>
		    					</h3>
		    					<ul>
		    						<li>2016.10.05(목) 04:00</li>
		    						<li>피해현황: 재산피해 00억 ~ 00억 / 인명피해 00명</li>
		    						<li>태풍명: 제18호 태풍 차바 / 기상개황: 기상특보</li>
		    						<li>풍랑 : (10.2.저녁)- 남해동부먼바다, 제주도남쪽먼바다 / 기상상황 누적강수량 서산 18.1, 수원 11.3</li>
		    					</ul>
		    				</li>
		    				<li>
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">태풍 차바 대처상황 보고
		    						<span>
			    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a>
		    						</span>
		    					</h3>
		    					<ul>
		    						<li>2016.10.04(수) 10:00</li>
		    						<li>피해현황: 재산피해 00억 ~ 00억 / 인명피해 00명</li>
		    						<li>태풍명: 제18호 태풍 차바 / 기상개황: 기상특보</li>
		    						<li>풍랑 : (10.2.저녁)- 남해동부먼바다, 제주도남쪽먼바다 / 기상상황 누적강수량 서산 18.1, 수원 11.3</li>
		    					</ul>
		    				</li>
		    				<li>
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">태풍 차바 대처상황 보고
		    						<span>
			    					<!-- 	<a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a>
		    						</span>
		    					</h3>
		    					<ul>
		    							<li>2016.10.04(수) 04:00</li>
		    						<li>피해현황: 재산피해 00억 ~ 00억 / 인명피해 00명</li>
		    						<li>태풍명: 제18호 태풍 차바 / 기상개황: 기상특보</li>
		    						<li>풍랑 : (10.2.저녁)- 남해동부먼바다, 제주도남쪽먼바다 / 기상상황 누적강수량 서산 18.1, 수원 11.3</li>
		    					</ul>
		    				</li>
		    			</ul>
						<div class="pagination">
						    <ul>
						        <li><a href="#" title="맨처음으로 가기" class="first"><span>맨처음으로 가기</span></a></li>
						        <li><a href="#" title="이전페이지로 가기" class="prev"><span>이전페이지로 가기</span></a></li>
						        <li><a class="active" href="#">1</a></li>
						        <li><a href="#">2</a></li>
						        <li><a href="#">3</a></li>
						        <li><a href="#">4</a></li>
						        <li><a href="#">5</a></li>
						        <li><a href="#">6</a></li>
						        <li><a href="#">7</a></li>
						        <li><a href="#">8</a></li>
						        <li><a href="#">9</a></li>
						        <li><a href="#">10</a></li>
						        <li><a href="#" title="다음페이지로 가기" class="next"><span>다음페이지로 가기</span></a></li>
						        <li><a href="#" title="맨마지막으로 가기" class="last"><span>맨마지막으로 가기</span></a></li>
						    </ul>
						 </div>
	    			</div>
	    			<div class="list-result" id="category-2" style="display:none">
	    				<p>검색결과 : 157건</p>
		    			<ul>
		    				<li style="word-break:break-all;width:800px;">
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">태풍 차바 대처상황 보고
		    						<span>
			    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a> 
		    						</span>
		    					</h3>
		    					<ul>
		    						<li>2016.10.05(목) 10:00</li>
		    						<li style=""> - 호우경보 : 강원도(화천군, 철원군), 경기도(포천시)
   - 호우주의보 : 강원도(양구군산간, 양구군평지, 양양군산간, 인제군산간, 고성군산간, 속초시산간, 인제군평지, 춘천시), 경기도(가평군, 남양주시, 의정부시, 양주시, 연천군, 동두천시)
    * 호우 예비특보(10월 05일 오전) : 서울, 충남(당진시, 서산시, 태안군), 강원(평창군산간, 강릉시산간, 홍천군산간, 평창군평지, 홍천군평지, 횡성군, 원주시), 경기(양평군, 하남시, 구리시, 파주시, 고양시
   								 </li>
		    					
		    					</ul>
		    				</li>
		    				<li style="word-break:break-all;width:800px;">
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">태풍 차바 대처상황 보고
		    						<span>
			    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a>
		    						</span>
		    					</h3>
		    					<ul>
		    						<li>2016.10.05(목) 04:00</li>
		    						<li style=""> - 호우경보 : 강원도(화천군, 철원군), 경기도(포천시)
   - 호우주의보 : 강원도(양구군산간, 양구군평지, 양양군산간, 인제군산간, 고성군산간, 속초시산간, 인제군평지, 춘천시)
    
   								 </li>
		    					</ul>
		    				</li>
		    				<li style="word-break:break-all;width:800px;">
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">태풍 차바 대처상황 보고
		    						<span>
			    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a>
		    						</span>
		    					</h3>
		    					<ul>
		    						<li>2016.10.04(수) 10:00</li>
		    						<li style=""> - 호우경보 : 강원도(화천군, 철원군), 경기도(포천시)
   - 호우주의보 : 경기도(가평군, 남양주시, 의정부시, 양주시, 연천군, 동두천시)
    * 호우 예비특보(10월 04일 오전) : 서울, 충남(당진시, 서산시, 태안군)
   								 </li>
		    					</ul>
		    				</li>
		    				<li style="word-break:break-all;width:800px;">
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">태풍 차바 대처상황 보고
		    						<span>
			    					<!-- 	<a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a>
		    						</span>
		    					</h3>
		    					<ul>
		    							<li>2016.10.04(수) 04:00</li>
		    						<li style="">
   									 * 호우 예비특보(10월 04일 새벽) : 서울, 충남(당진시, 서산시, 태안군)
   								 </li>
		    					</ul>
		    				</li>
		    			</ul>
						<div class="pagination">
						    <ul>
						        <li><a href="#" title="맨처음으로 가기" class="first"><span>맨처음으로 가기</span></a></li>
						        <li><a href="#" title="이전페이지로 가기" class="prev"><span>이전페이지로 가기</span></a></li>
						        <li><a class="active" href="#">1</a></li>
						        <li><a href="#">2</a></li>
						        <li><a href="#">3</a></li>
						        <li><a href="#">4</a></li>
						        <li><a href="#">5</a></li>
						        <li><a href="#">6</a></li>
						        <li><a href="#">7</a></li>
						        <li><a href="#">8</a></li>
						        <li><a href="#">9</a></li>
						        <li><a href="#">10</a></li>
						        <li><a href="#" title="다음페이지로 가기" class="next"><span>다음페이지로 가기</span></a></li>
						        <li><a href="#" title="맨마지막으로 가기" class="last"><span>맨마지막으로 가기</span></a></li>
						    </ul>
						 </div>
	    			</div>
	    			
	    			
	    			
	    			<div class="list-result" id="category-4" style="display:none">
	    				<p>검색결과 : 2건</p>
		    			<ul>
		    				<li style="word-break:break-all;width:800px;">
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">태풍 차바 대처상황 보고 (중앙대책본부)
		    						<span>
			    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a> 
		    						</span>
		    					</h3>
		    					<ul>
		    						<li>2016.10.05(목) 10:00</li>
		    						<li style=""> 보강근무(7.23, 06:20), 초기 상황판단회의(7.23, 14:00), 비상 1단계(7.23, 16:00)
  - 집중호우대비 취약지역 예방활동 강화 및 선제적상황관리 지시(6회)
     ․시‧도 및 시‧군‧구, 기상상황에 따라 단계별 비상근무 철저 이행
     ․재해위험지구, 해안가 저지대 등 취약시설 및 배수문‧배수장 사전 점검
     ․인명피해 우려지역 예찰활동 강화, 위험징후 발견시 주민사전대피 조치		 </li>
		    					
		    					</ul>
		    				</li>
		    				<li style="word-break:break-all;width:800px;">
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">태풍 차바 대처상황 보고
		    					(지역대책본부․유관기관)
		    						<span>
			    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a>
		    						</span>
		    					</h3>
		    					<ul>
		    						<li>2016.10.05(목) 10:00</li>
		    						<li style=""> 특보지역 비상근무 4개 시‧도, 497명(서울 214, 경기 151, 강원 53, 충남 79) 
     ․급경사지 등 인명피해 우려지역 281개소, 공사장 등 취약지역 605개소 예찰활동 및 점검
     ․배수펌프장 등 방재시설(869개소), 침수우려 하상도로 및 하천내 주차장(72개소) 사전 점검
     ․SMS 문자발송(60,573명/40회), 전광판(40개소), 자동음성통보(19회/1,064개소), 자막방송(18회) 등 홍보	 </li>
		    					
		    					</ul>
		    				</li>
		    				
		    			</ul>
					
	    			</div>
	    			
	    			
	    			
	    			
	    			<div class="list-result" id="category-3" style="display:none">
	    				<p>검색결과 : 2건</p>
		    			<ul>
		    				<li style="word-break:break-all;width:800px;">
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">태풍 차바 대처상황 보고 
		    						<span>
			    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a> 
		    						</span>
		    					</h3>
		    					<ul>
		    						<li>2016.10.05(목) 10:00</li>
		    						<li style=""> 탐방로 : 2개 공원 49개소(북한산도봉 37, 설악산 12) 출입통제	 </li>
		    					
		    					</ul>
		    				</li>
		    			</ul>
	    			</div>
	    			
	    				<div class="list-result" id="category-5" style="display:none">
	    				<p>검색결과 : 2건</p>
		    			<ul>
		    				<li style="word-break:break-all;width:800px;">
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">태풍 차바 대처상황 보고 
		    						<span>
			    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a> 
		    						</span>
		    					</h3>
		    					<ul>
		    						<li>2016.10.05(목) 10:00</li>
		    						<li style=""> 호우특보지역 강수 지속 모니터링, 산사태․급경사지 등 취약지역 집중관리	 </li>
		    					
		    					</ul>
		    				</li>
		    			</ul>
	    			</div>
	    			<div  class="list-result" id="category-6" style="display:none;">
	    				<p>검색결과 : 4건</p>
	    				<ul>
	    					<li>
	    					<h3>태풍 경로 <span id="exImgTxt" style="position:relative;left:10px;">2016-10-05 10시</span> 
		    						<span>
			    					<!-- 	<a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						
		    						</span>
		    					</h3>
		    					<ul>
		    						<li><img id="exImg" src="/contents/advis/images/ex/18-05-10.png" style="width:600px;height:450px;margin-left:30px;"/></li>
		    					</ul>
	    					</li>
	    					
	    			
	    					
	    				</ul>
	    				<div class="pagination">
						    <ul>
						        <li><a href="#" title="맨처음으로 가기" id="btn-img-pre" class="first"><span>맨처음으로 가기</span></a></li>
						       
						        <li><a href="#" title="맨마지막으로 가기" id="btn-img-next" class="last"><span>맨마지막으로 가기</span></a></li>
						    </ul>
						 </div>
	    			</div>
	    			
	    			<div id="category-3"></div>
	    		</section>
		
				<footer>
					<div>Advanced Disaster visualized Intelligent Interactive and Information System</div>
					<address>주소. 우)44538 울산광역시 중구 종가로 365 국립재난안전연구원 / TEL : 052-928-8000
						<span class="copy">Copyright &copy; 2018 by National Disaster Management Institute. All Rights Reserved.</span>
					</address>
				</footer>
	    	</div>
		</div>
	</div>
	<div class="popup" style="top: 1rem; width:655px;left: 50rem; display:none;">
			<div class="popup-header">
				데이터 등록 현황<a href="#" class="btn-close"></a>
			</div>
			<div class="popup-contents">
				<div>
					<img src="/contents/advis/images/ex/ex-chart2.png"/>
				</div>
				<div>
					<img src="/contents/advis/images/ex/ex-chart3.png"/>
				</div>
			</div>
		</div>
	<script type="text/javascript">
	var exImgTxt=["2016-10-05 10시","2016-10-05 01시","2016-10-04 04시","2016-10-03 22시"];
	var exImg=["18-05-10","18-05-01","18-04-04(2)","18-03-22"];
	var currentIndex=0;
	$(function(){
		
		$("#mnu-search").click(function(){
			$(".sub").toggle();
		});
		
		$("#btn-img-pre").click(function(){
			if(currentIndex>0){
				currentIndex--;
				var imgPath="/contents/advis/images/ex/"+exImg[currentIndex]+".png";
				$("#exImg").attr("src",imgPath);
				$("#exImgTxt").text(exImgTxt[currentIndex]);
			}
		});
		$("#btn-img-next").click(function(){
			if(currentIndex>=0 && currentIndex<exImg.length-1){
				currentIndex++;
				var imgPath="/contents/advis/images/ex/"+exImg[currentIndex]+".png";
				$("#exImg").attr("src",imgPath);
				$("#exImgTxt").text(exImgTxt[currentIndex]);
			}
		});
		
		$("#btn-category-1").click(function(){
			$(".btn-category").removeClass('active');
			$(this).addClass('active');
			$(".list-result").hide();
			$("#category-1").show();
			
		});
		$("#btn-category-2").click(function(){
			$(".btn-category").removeClass('active');
			$(this).addClass('active');
			$(".list-result").hide();
			$("#category-2").show();
		});
		$("#btn-category-3").click(function(){
			$(".btn-category").removeClass('active');
			$(this).addClass('active');
			$(".list-result").hide();
			$("#category-3").show();
		});
		$("#btn-category-4").click(function(){
			$(".btn-category").removeClass('active');
			$(this).addClass('active');
			$(".list-result").hide();
			$("#category-4").show();
		});
		$("#btn-category-5").click(function(){
			$(".btn-category").removeClass('active');
			$(this).addClass('active');
			$(".list-result").hide();
			$("#category-5").show();
		});
		$("#btn-category-6").click(function(){
			$(".btn-category").removeClass('active');
			$(this).addClass('active');
			$(".list-result").hide();
			$("#category-6").show();
		});
		
		$(".btn-close").click(function(){
			$(".popup").toggle();
		});
	});
	
	chartColors = {
			
			orange : 'rgb(255, 159, 64)',
			
			green : 'rgb(75, 192, 192)',
			blue : 'rgb(54, 162, 235)',
			
			grey : 'rgb(201, 203, 207)'
		};
	
	dataset = {
			"children" : [ {
				Name : "태풍",
				Count : 75
			}, {
				Name : "호우",
				Count : 68
			}, {
				Name : "지진",
				Count : 35
			}, {
				Name : "대설",
				Count : 10
			}, {
				Name : "풍랑",
				Count : 3
			}, {
				Name : "강풍",
				Count : 2
			} 
			, {
				Name : "경기도",
				Count : 15
			}
			, {
				Name : "울산",
				Count :20
			}
			, {
				Name : "포항지진",
				Count : 21
			}
			, {
				Name : "포항",
				Count : 13
			}
			, {
				Name : "침수피해",
				Count : 2
			}
			]

		};
	
	function createBubbleChart() {
		var diameter = 540;
		var color = d3.scaleOrdinal(d3.schemeCategory20b);

		var bubble = d3.pack(dataset).size([ diameter, diameter ]).padding(
				1.5);

		var svg = d3.select("#chart1").append("svg")
				.attr("width", diameter).attr("height", diameter).attr(
						"class", "bubble");

		var nodes = d3.hierarchy(dataset).sum(function(d) {
			return d.Count;
		});

		var node = svg.selectAll(".node").data(bubble(nodes).descendants())
				.enter().filter(function(d) {
					return !d.children
				}).append("g").attr("class", "node").attr("transform",
						function(d) {
							return "translate(" + d.x + "," + d.y + ")";
						});

		node.append("title").text(function(d) {

			return d.data.Name + ": " + d.data.Count;
		});

		node.append("circle").attr("r", function(d) {
			return d.r;
		}).style("fill", function(d, i) {
			return color(i);
		})
		
		;
		node.append("text").attr("dy", ".2em").style("text-anchor",
				"middle").text(function(d) {
			return d.data.Name.substring(0, d.r / 3);
		}).attr("font-family", "sans-serif").attr("font-size", function(d) {
			return d.r / 5;
		}).attr("fill", "white")
		 .on("click", function(d,i){
			 $(".popup").toggle();
		 })
		;

		node.append("text").attr("dy", "1.3em").style("text-anchor",
				"middle").text(function(d) {
			return d.data.Count;
		}).attr("font-family", "Gill Sans", "Gill Sans MT").attr(
				"font-size", function(d) {
					return d.r / 5;
				}).attr("fill", "white");
	}
//	createBubbleChart();
	</script>
</body>
</html>