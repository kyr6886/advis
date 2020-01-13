<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>관심 지역 설정</title>
<meta name="viewport" content="width=device-width,maximum-scale=1,minimum-scale=1,user-scalable=no">
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
<style>
	.contents-title {margin-top: 0; padding-left: 2rem; background-color: #e0e0e0;}
	.contents {padding: 0 2rem;}
	.contents li {display: inline-block; width: calc(100% / 3); margin: .5rem 0;}
	.contents div:last-child {margin-top: 2rem;}
</style>
</head>
<body>
<div id="container" style="width:100%">
	<div class="contents-title">
		<h2>관심 지역 설정</h2>
	</div>
	<div class="contents">
		<ul>
			<!-- <input type="checkbox" @click="fnCheckAll($event.path[0].checked)"> -->
			<li v-for="item, index in viewModel.allList">
				<label>
					<input type="checkbox" v-model="item.checked">&nbsp;{{item.law_sido}}&emsp;
				</label>
			</li>
		</ul>
		<div style="text-align:right">
			<button class="btn btn-blue" @click="fnSaveCheckedArea()">저장</button>
		</div>
	</div>
</div>
<script src="${pageContext.request.contextPath}/contents/advis/js/model/vue/checkArea-controller.min.js"></script>
</body>
</html>