<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link	href="${pageContext.request.contextPath}/contents/base/css/bootstrap.min.css"	rel="stylesheet">
<script type="text/javascript" src="http://map.vworld.kr/js/vworldMapInit.js.do?apiKey=C026F532-62FC-3086-A520-937A6AA26B74"></script> 
<script	src="${pageContext.request.contextPath}/contents/base/js/angular.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/angular-route.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/angular-sanitize.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/noaa-admin.js?1=1"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/test-controller.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/directivesJS/vwolrd/pos-selector-directive.js"></script>
<title>Insert title here</title>


</head>
<body>
<div ng-app="noaaAdmin" ng-controller="testCtrl">
<input type="text" ng-model="userName"/>
<button type="button" onclick='window.open("/map/select/position","","width:600,height=600")'>지도 위치선택</button>
</div>
</body>
</html>