<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script	src="${pageContext.request.contextPath}/contents/base/js/jquery.min.js"></script>
<script	src="${pageContext.request.contextPath}/contents/base/js/html5/html2canvas.min.js"></script>
<script	src="${pageContext.request.contextPath}/contents/base/js/angular.min.js"></script>
<script	src="${pageContext.request.contextPath}/contents/base/js/angular-route.min.js"></script>
<script	src="${pageContext.request.contextPath}/contents/base/js/angular-sanitize.min.js"></script>
<script src="${pageContext.request.contextPath}/contents/base/js/model/directivesJS/ng-file-upload-shim.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/contents/base/js/model/directivesJS/ng-file-upload.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/contents/base/js/model/noaa-admin.js?1=1"></script>
<script	src="${pageContext.request.contextPath}/contents/base/js/model/image-controller.js?1=1"></script>
<script type="text/javascript">

</script>
</head>
<body  ng-app="noaaAdmin" ng-controller="imageCtrl">
웹페이지를 이미지로 생성 및 저장
<div name="targetImage" style="width:300px;height:300px;background-color: #dddddd;">
<h1>Test</h1>
<h1>Target Html 1</h1>
</div>

<div name="targetImage" style="width:300px;height:300px;background-color: #dddddd;">
<h1>Test</h1>
<h1>Target Html 2</h1>
</div>

<div name="targetImage" style="width:300px;height:300px;background-color: #dddddd;">
<h1>Test</h1>
<h1>Target Html 3</h1>
</div>
<div>

<button id="btn" ng-click="fnCreateImage('targetImage',300,300)">이미지 생성</button></div>

</body>
</html>