<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link	href="/contents/base/css/bootstrap.min.css"	rel="stylesheet">
<script type="text/javascript" src="http://map.vworld.kr/js/vworldMapInit.js.do?apiKey=C026F532-62FC-3086-A520-937A6AA26B74"></script> 
<script	src="/contents/base/js/angular.min.js"></script>
	<script	src="/contents/base/js/angular-route.min.js"></script>
	<script	src="/contents/base/js/angular-sanitize.min.js"></script>
	<script	src="/contents/base/js/model/noaa-admin.js?1=1"></script>
	<script	src="/contents/base/js/model/test-controller.js"></script>
	<script	src="/contents/base/js/model/directivesJS/vwolrd/pos-selector-directive.js"></script>
	<script	src="/contents/base/js/model/directivesJS/pager-directive.js"></script>
	<script	type="text/javascript" src="/contents/base/js/map/map.geoserver.js"></script>
	<script type="text/javascript" src="/contents/base/js/map/sld/adm.style.js"></script>
<title>Insert title here</title>
</head>
<body ng-app="noaaAdmin" ng-controller="testCtrl">
<pos-selector binding="mapData" callback-func="fnCallBackPosition" map-width="400" map-height="400"></pos-selector>
	
</body>
</html>