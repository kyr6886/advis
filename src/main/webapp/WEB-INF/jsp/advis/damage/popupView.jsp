<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<div class="wrap">
  	<div id="container">

<div id="scope-dmeStation" v-cloak>
	<div class="search">
		<select v-model="viewModel.paramArea">
			<option value="">전체</option>
			<option v-for="item in viewModel.listAreaCode" :value="item.code.substring(0,2)">{{item.sido}}</option>
		</select>
		<select v-model="viewModel.paramType">
			<option value="">전체</option>
			<option v-for="item in viewModel.listTypeCode">{{item.dis_dme_typ}}</option>
		</select>
		<button class="btn btn-purple" v-on:click="fnListDmeStation()">조회</button>
	</div>
	<div class="table-basic scroll-y-600">
		<table>
			<tr>
				<th>재해지구명</th>
				<th>주소</th>
				<th>재해위험유형</th>
				<th>시설명</th>
				<th>시설물유형</th>
			</tr>
			<tr v-for="item in viewModel.listDmeStation">
				<td>{{item.dis_dme_nm}}</td>
				<td class="text-left">{{item.dis_dme_area}} {{item.dis_dme_area_detail}}</td>
				<td>{{item.dis_dme_typ}}</td>
				<td>{{item.dis_facility_nm}}</td>
				<td>{{item.dis_facility_ty}}</td>
			</tr>
		</table>
	</div>
</div>
<script src="${pageContext.request.contextPath}/contents/advis/js/model/vue/disDmeStation-controller.min.js"></script>
</div>
</div>
</body>
</html>