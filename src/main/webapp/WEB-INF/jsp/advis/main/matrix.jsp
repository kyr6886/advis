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
						<li><a href="/advis/manage/matrix" class="active">태풍</a></li>
						<li><a href="#">지진</a></li>
						<li><a href="#">감염</a></li>
						<li><a href="#" id="mnu-search" >정보검색</a>
							<ul class="sub">
								<li class="sub-item">유형별 피해현황</li>
								<li class="sub-item"><a href="/advis/manage/search">재난대응 검색</a></li>
							</ul>
						</li>
						
					</ul>
				</nav>
			</div>
		</header>
	<div class="nav-wrap" style="background:transparent;border:none;box-shadow:none">
				<nav id="gnb" style="text-align:left;width:1650px; margin-left:15px;border-bottom:solid 1px #e0e0e0; ">
					
					<ul class="clearfix" style="border-top:solid 1px #e0e0e0;">
						<li><a href="#" class="active">예방단계</a></li>
						<li><a href="#">대비단계</a></li>
						<li><a href="#">대응단계</a></li>
						<li><a href="#">복구단계</a></li>
						
						
					</ul>
				</nav>
			</div>
	    <div id="body-container" class="clearfix">
	    	<div id="container" style="margin-top:5px !important;" class="main-contents">
	    		<section class="summary" >
	    		<div style="width:200px;">
	    			<div style="width:200px; max-height:600px;overflow-y:auto;">
		    			<ul>
		    				<li><a href="#cat-1"> 국민행동요령</a></li>
		    				<li><a href="#cat-2"> 기상특보 기준</a></li>
		    				<li><a href="#cat-3"> 예상 진로</a></li>
		    				<li><a href="#cat-4"> 특별관심지구</a></li>
		    				<li><a href="#cat-5"> 재난대응 매뉴얼</a></li>
		    				<li><a href="#cat-6"> 재해위험지구 현황</a></li>
		    				<li><a href="#cat-7"> 태풍 위치</a></li>
		    				<li><a href="#cat-8"> 재난 및 안전관리기본법</a></li>
		    				<li><a href="#cat-9"> 비상근무 기준 및 현황</a></li>
		    				<li><a href="#cat-10"> 시설 안전 기준</a></li>
		    			</ul>
		    			<div style="text-align:right;padding-right:10px;"><a href="#" id="btn-category-more" style="font-size:12px;color:blue">더보기</a></div>
		    			<ul id="category-more-list" style="display:none" >
		    				<li><a href="#"> 대피소현황</a></li>
		    				<li><a href="#"> 대국민전파 체계</a></li>
		    				<li><a href="#"> 비상 연락망</a></li>
		    				<li><a href="#"> 기상개황</a></li>
		    				<li><a href="#"> 풍속</a></li>
		    				<li><a href="#"> 조위정보</a></li>
		    				<li><a href="#"> 전국수문현황</a></li>
		    				<li><a href="#"> 자연재해대책법</a></li>
		    				<li><a href="#"> 위성사진</a></li>
		    				<li><a href="#"> 모니터링현황</a></li>
		    				<li><a href="#"> 과거피해이력</a></li>
		    				<li><a href="#"> 조회피해지역</a></li>
		    			</ul>
	    			</div>
	    			</div>
	    		</section>
	    		
	    		
	    		
	    		<section style="border:none;">
	    			<!-- <div>
	    				<a href="#" id="btn-category-1" class="btn btn-default btn-icon btn-category active"><i class="intergrated"></i>통합검색</a>
	    				<a href="#" id="btn-category-2" class="btn btn-default btn-category btn-icon"><i class="weather"></i>기상현황</a>
	    				<a href="#" id="btn-category-3" class="btn btn-default btn-category btn-icon"><i class="damaged"></i>통제 및 피해상황</a>
	    				<a href="#" id="btn-category-4" class="btn btn-default btn-category btn-icon"><i class="handle"></i>주요 대처상황</a>
	    				<a href="#" id="btn-category-5" class="btn btn-default btn-category btn-icon"><i class="plans"></i>향후계획</a>
	    				<a href="#" id="btn-category-6" class="btn btn-default btn-category btn-icon"><i class="plans"></i>기타자료</a>
	    			</div> -->
	    			<div class="list-result" id="category-1" style="display:block">
	    				
		    			<ul>
		    				<li>
		    					<h3 style="margin-left:-30px;"><img src="/contents/advis/images/icon-bullet.png" style="width:30px;"/> <a  name="cat-1"> 국민행동요령</a></h3>
		    				</li>
		    			
		    				<li>
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">	태풍과 호우
		    						<span>
			    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a>
		    						</span>
		    					</h3>
		    					<ul>
		    						<li>작성일 : 2016.10.05</li>
		    						<li>발행기관 : 행정안전부</li>
		    					</ul>
		    				</li>
		    				
		    				
		    				<li>
		    					<h3 style="margin-left:-30px;border-top:solid 1px #e0e0e0;padding-top:10px;"><img src="/contents/advis/images/icon-bullet.png" style="width:30px;"/><a  name="cat-2">  기상특보 기준</a></h3>
		    				</li>
		    				<li>
		    				
		    					<ul>
		    						<li>작성일 : 2014.09.02</li>
		    						<li>주의보 : · 태풍으로 인하여 강풍, 풍랑, 호우, 폭풍해일 현상 등이 주의보 기준에 도달할 것으로 예상될 때</li>
		    						<li>경보:태풍으로 인하여 다음중 어느 하나에 해당하는 경우<br>
										① 강풍(또는 풍랑) 경보 기준에 도달할 것으로 예상 될 때<br>
										② 총 강우량이 200mm이상 예상될 때<br>
										③ 폭풍해일 경보 기준에 도달할 것으로 예상 될 때
									</li>
		    					</ul>
		    					
		    				</li>
		    				
		    				
		    				<li>
		    					<h3 style="margin-left:-30px;border-top:solid 1px #e0e0e0;padding-top:10px;"><img src="/contents/advis/images/icon-bullet.png" style="width:30px;"/> 예상 진로(현재태풍,2016-10-05 09시 기준)</h3>
		    				</li>
		    				<li>
		    				
		    					<ul>
		    						<li>작성일 : 2016.10.05 09시</li>
		    						<li><img id="exImg" src="/contents/advis/images/ex/18-05-10.png" style="width:600px;height:450px;margin-left:30px;"/></li>
		    					
		    					</ul>
		    						<ul>
		    						<li>작성일 : 2016.10.05 01시</li>
		    						<li><img id="exImg" src="/contents/advis/images/ex/18-05-01.png" style="width:600px;height:450px;margin-left:30px;"/></li>
		    					
		    					</ul>
		    				</li>
		    				
		    				
		    				
		    				<li>
		    					<h3 style="margin-left:-30px; border-top:solid 1px #e0e0e0;padding-top:10px;"><img src="/contents/advis/images/icon-bullet.png" style="width:30px;"/> <a  name="cat-3"> 특별 관심 지구</a></h3>
		    				</li>
		    				<li>
		    				
		    					
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">	태풍 차바 대처상황 보고
		    						<span>
			    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a>
		    						</span>
		    					</h3>
		    						<ul>
		    						<li>2016.10.05 10:00</li>
		    						<li>발행기관 : 행정안전부</li>
		    						</ul>
		    					
		    					
		    				</li>
		    				<li>
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">	태풍 차바 대처상황 보고
		    						<span>
			    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a>
		    						</span>
		    					</h3>
		    					<ul>
		    						<li>2016.10.05 04:00</li>
		    						<li>발행기관 : 행정안전부</li>
		    					</ul>
		    				</li>
		    				<li>
		    					<h3 style="margin-left:-30px; border-top:solid 1px #e0e0e0;padding-top:10px;"><img src="/contents/advis/images/icon-bullet.png" style="width:30px;"/><a  name="cat-4"> 재난 대응 메뉴얼</a></h3>
		    				</li>
		    				<li>
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">	태풍,호우 표준 메뉴얼
		    						<span>
			    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a>
		    						</span>
		    					</h3>
		    					<ul>
		    						<li>2014.01.01 </li>
		    						<li>발행기관 : 행정안전부</li>
		    					</ul>
		    				</li>
		    				<li>
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">	위기대응 메뉴얼
		    						<span>
			    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a>
		    						</span>
		    					</h3>
		    					<ul>
		    						<li>2014.01.01 </li>
		    						<li>발행기관 : 행정안전부</li>
		    					</ul>
		    				</li>
		    				
		    				<li>
		    					<h3 style="margin-left:-30px; border-top:solid 1px #e0e0e0;padding-top:10px;"><img src="/contents/advis/images/icon-bullet.png" style="width:30px;"/> <a  name="cat-5">재해위험지구 현황</a></h3>
		    				</li>
		    				<li>
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">	재해위험지구 유형별 지정 현황
		    						<span>
			    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a>
		    						</span>
		    					</h3>
		    					<ul>
		    						<li>2014.01.01 </li>
		    						<li>발행기관 : 행정안전부</li>
		    					</ul>
		    				</li>
		    				<li>
		    					<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">	자연재해위험개선지구 관리지침
		    						<span>
			    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
			    						<a href="#" class="btn btn-default btn-small">원문다운</a>
		    						</span>
		    					</h3>
		    					<ul>
		    						<li>2014.01.01 </li>
		    						<li>발행기관 : 행정안전부</li>
		    					</ul>
		    				</li>
		    				<li>
		    					<h3 style="margin-left:-30px; border-top:solid 1px #e0e0e0;padding-top:10px;"><img src="/contents/advis/images/icon-bullet.png" style="width:30px;"/> <a  name="cat-6">태풍 위치</a></h3>
		    				</li>
		    				<li>
		    				<ul>
		    						<li>작성일 : 2016.10.05 09시</li>
		    						<li><img id="exImg" src="/contents/advis/images/ex/18-05-10.png" style="width:600px;height:450px;margin-left:30px;"/></li>
		    					
		    					</ul>
		    				</li>
		    				
		    				<li>
		    					<h3 style="margin-left:-30px; border-top:solid 1px #e0e0e0;padding-top:10px;"><img src="/contents/advis/images/icon-bullet.png" style="width:30px;"/><a  name="cat-7"> 재난 및 안전관리 기본법</a></h3>
		    				</li>
		    				<li>
		    					<ul>
		    						<li>
			    						<h3 style="background-image: url('/contents/advis/images/popup.jpg'); background-size:21px; background-repeat-x:no-repeat;">	자연재해위험개선지구 관리지침
			    						<span>
				    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
				    						<a href="#" class="btn btn-default btn-small">정보보기</a>
			    						</span>
			    						</h3>
			    						<ul>
				    						<li>2014.01.01 </li>
				    						<li>발행기관 : 국가법령정보센터</li>
			    						</ul>
		    						</li>
		    					</ul>
		    				</li>
		    				
		    				<li>
		    					<h3 style="margin-left:-30px; border-top:solid 1px #e0e0e0;padding-top:10px;"><img src="/contents/advis/images/icon-bullet.png" style="width:30px;"/><a  name="cat-8"> 비상근무 기준 및 현황</a></h3>
		    				</li>
		    				<li>
		    					<ul>
		    						<li>
			    						<h3 style="background-image: url('/contents/advis/images/ic-hwp.png'); background-repeat-x:no-repeat;">	단계별 비상근무 시행기준
			    						<span>
				    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
				    						<a href="#" class="btn btn-default btn-small">원문보기</a>
			    						</span>
			    						</h3>
			    						<ul>
		    						<li>2014.01.01 </li>
		    						<li>발행기관 : 행정안전부</li>
		    					</ul>
			    						</li>
			    						</ul>
		    				</li>
		    				
		    				
		    				
		    				<li>
		    					<h3 style="margin-left:-30px; border-top:solid 1px #e0e0e0;padding-top:10px;"><img src="/contents/advis/images/icon-bullet.png" style="width:30px;"/><a  name="cat-9"> 시설안전기준</a></h3>
		    				</li>
		    				<li>
		    					<ul>
		    						<li>
			    						<h3 style="background-image: url('/contents/advis/images/popup.jpg'); background-size:21px; background-repeat-x:no-repeat;">	소규모 공공시설 안전관리 등에 관한 법률
			    						<span>
				    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
				    						<a href="#" class="btn btn-default btn-small">정보보기</a>
			    						</span>
			    						</h3>
			    						<ul>
				    						<li>2014.01.01 </li>
				    						<li>발행기관 : 국가법령정보센터</li>
			    						</ul>
		    						</li>
		    					</ul>
		    				</li>
		    				
		    				
		    					<li>
		    					<h3 style="margin-left:-30px; border-top:solid 1px #e0e0e0;padding-top:10px;"><img src="/contents/advis/images/icon-bullet.png" style="width:30px;"/> 대피소현황</h3>
		    				</li>
		    				<li>
		    					<ul>
		    						<li>
			    						<h3 style="background-image: url('/contents/advis/images/ic-hwp.png');background-repeat-x:no-repeat;">	<a  name="cat-10">안전시설정보</a> 
			    						<span>
				    						<!-- <a href="#" class="btn btn-default btn-small">상세보기</a> -->
				    						<a href="#" class="btn btn-default btn-small">원문보기</a>
			    						</span>
			    						</h3>
			    						<ul>
				    						<li>2014.01.01 </li>
				    						<li>발행기관 : 국가법령정보센터</li>
			    						</ul>
		    						</li>
		    					</ul>
		    				</li>
		    				
		    			</ul>
					
	    			</div>
	    			
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
		
		$("#btn-category-more").click(function(){
			$(this).hide();
			$("#category-more-list").show();
			
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