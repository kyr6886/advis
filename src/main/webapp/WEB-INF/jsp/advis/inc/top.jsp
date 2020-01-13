<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.noaa.base.global.SysKeyword" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>재난상황관리 표준화 기술</title>
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
	<meta name="author" content="재난상황관리 표준화 기술">
	<meta name="subject" content="재난상황관리 표준화 기술">
	<meta name="description" lang="ko" content="">
	<meta name="robots" content="all">
	<meta name="format-detection" content="telephone=no">
	


	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basic.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/disaster.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">
	<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
	<script src="${pageContext.request.contextPath}/contents/advis/js/jquery-stringFormat.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/advis/js/polyfill.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/advis/js/extends.js"></script>
	<script type="text/javascript">
	function resizeContent() {
		var windowHeight = $(window).height();
		var topHeight = $('header').height();
		var bottomHeight = $('footer').height() +40;
		var containerHeight = $('').css({'min-height':(windowHeight-topHeight-bottomHeight)-113+'px'});
	}
	$(document).ready(function() {
		resizeContent();
	});
	 
	$(window).resize(function() {
		resizeContent();
	});

	</script>

</head>
<body>
	<p id="skipnav">
		<a href="#container">본문 내용 바로가기</a>
		<a href="#gnb">대메뉴 바로가기</a>
	</p>
	<div class="wrap">
		<header class="clearfix">
			<div class="header-wrap">
				<h1><a href="/"><img src="${pageContext.request.contextPath}/contents/advis/images/logo-login.png" style="width:300px" alt="재난상황관리 표준화 기술"/></a></h1>
				
				<div class="search-wrap">
						<nav id="gnb">
					<ul>
						<li><a href="/analysis/damage" target="_blank">재난분석</a></li>
						<li><a href="/advis/disaster/rain">피해이력</a></li>
						<li><a href="/report/list">업무지원</a></li>
					
					</ul>
				</nav>
				</div>
				

				<div class="head-service">
					<ul class="user-info">
						
						<li><a href="javascript:window.open('/history/accident/checkArea', '관심 지역 설정', 'width=500, height=350, menubar=no, status=no, toolbar=no')">
						<% if(session.getAttribute(SysKeyword.SESSION_USER_NAME) != null){ %>
							<%=session.getAttribute(SysKeyword.SESSION_USER_NAME)%>(
							<% if("ROLE_001".equals(session.getAttribute(SysKeyword.SESSION_USER_ROLE))){ %>
								관리자
							<% } else if("ROLE_002".equals(session.getAttribute(SysKeyword.SESSION_USER_ROLE))){ %>
								일반사용자
							<% } else{ %>
								지자체담당자
							<% } %>
							) 시스템 접속중
						<% } else {%>
							김병식(관리자) 시스템 접속중
						<% } %>
						</a></li>
						<li>
							<% if(session.getAttribute(SysKeyword.SESSION_USER_ID) != null){ %>
								<a href="/advis/logout"><i class="icon-lock"></i><span class="hidden">로그아웃</span></a>
							<% } else{ %>
								<a href="/advis/login/login"><i class="icon-lock-open-circle"></i><span class="hidden">로그인</span></a>
							<% } %>
						</li>
						<li><a href=""><i class="icon-account"></i><span class="hidden">정보수정</span></a></li>
					</ul>
				</div>
			</div>
			<div class="nav-wrap">
				<div class="issue"> 
					<c:choose>
						<c:when test="${monthDisaster.codeName == '태풍'}">
							<div class="icon typhoon"></div>
							<dl> 
								<a href="/analysis/typhoon/path">
									<dt>태풍특보발생</dt>
								</a>
							</dl>
						</c:when>
						<c:otherwise>
							<div class="icon rain"></div>
							<dl>
								<dt><c:out value="${toMonth}"/>월 이슈재난</dt>
								<dd>[ <c:out value="${monthDisaster.codeName}"/> ] 평균 <em><c:out value="${monthDisaster.count}"/></em>건 발생!</dd>
							</dl>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="bg-gnb"></div>
				<nav id="gnb">
					<ul>
						<li><a href="/advis/code/codeView/NHR">호우</a></li>
						<li><a href="/advis/code/codeView/NTY">태풍</a></li>
						<li><a href="/advis/code/codeView/NEQ">지진</a></li>
						<li><a href="/advis/code/codeView/NFR">화재</a></li>
						<li><a href="/advis/code/codeView/NSN">대설</a></li>
						<li><a href="">강풍</a></li>
						<li><a href="">풍랑</a></li>
						<li><a href="/advis/code/codeView/VAI">감염병/전염병</a></li>
					</ul>
				</nav>
			</div>
		</header>
		  	<div id="container">