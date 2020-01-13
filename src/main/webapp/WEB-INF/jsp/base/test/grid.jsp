<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script src="${pageContext.request.contextPath}/contents/base/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/contents/base/js/jquery-stringFormat.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/angular.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/angular-route.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/angular-sanitize.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/noaa-admin.js?1=1"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/directivesJS/grid-directive.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/test-controller.js"></script>
	<style type="text/css">

.fixed{
	table-layout:fixed;
}
.fixed td { overflow: hidden; }
table.hovertable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}
table.hovertable th {
	background-color:#c3dde0;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
table.hovertable tr {
	background-color:#d4e3e5;
}
table.hovertable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

</style>
</head>
<body ng-app="noaaAdmin" ng-controller="testCtrl">
	<grid-basic binding="dataSet"></grid-basic>
</body>
</html>