<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link	href="${pageContext.request.contextPath}/contents/base/css/bootstrap.min.css"	rel="stylesheet">
<link	href="${pageContext.request.contextPath}/contents/base/fonts/css/font-awesome.min.css"	rel="stylesheet">
<link type="text/css" rel="stylesheet"	href="${pageContext.request.contextPath}/contents/base/css/shCoreDefault.css" />
<link type="text/css" rel="stylesheet"	href="${pageContext.request.contextPath}/contents/base/css/animate.min.css" />
<link type="text/css" rel="stylesheet"	href="${pageContext.request.contextPath}/contents/base/css/custom.css" />
<script	src="${pageContext.request.contextPath}/contents/base/js/jquery.min.js"></script>

<title></title>
</head>
<body style="background: #F7F7F7;" ng-app="noaaAdmin" ng-controller="userCtrl">
	<div id="wrapper" ng-init="fnInitAccount()">
		<div>
			<h1 class="text-center">
				<i class="fa fa-cog"></i>
			</h1>
		</div>
		<div>
			<h4 class="text-center">관리자 로그인</h4>
		</div>
		<div>
			<div class="x_panel">
				<div class="panel-body">
					<form class="form-horizontal m-t-20" method="post"	action="<c:url value="/account/login"/>" >

						<div class="form-group ">

							<div class="col-xs-12">
								<label>사용자 아이디</label> <input class="form-control" kr-input
								ng-model="detailUser.user_id"	name="loginInfo.id" type="text" required="required">
							</div>
						</div>

						<div class="form-group">

							<div class="col-xs-12">
								<label>사용자 비밀번호</label> <input class="form-control" 
								ng-model="detailUser.user_pwd"
									name="loginInfo.password" type="password" value=""
									required="required" placeholder="">
							</div>
						</div>

						<div class="form-group">
							<div class="col-xs-12 ng-hide"  ng-show="!isInitSystem">
								<label>이메일</label> <input class="form-control"
								ng-model="detailUser.user_email"
									name="detailUser.user_email" type="email" value=""
									 placeholder="">
							</div>
						</div>

						<div class="form-group ng-hide" ng-show="isInitSystem">
							<div class="col-xs-12">
								<div class="checkbox checkbox-primary">
									<label for="checkbox-signup"> <input
										id="checkbox-signup" name="rememberMeYn" type="checkbox"
										value="Y"> 로그인 상태 유지
									</label>
								</div>

							</div>
						</div>

						<div class="form-group text-center m-t-40 ng-hide" ng-show="isInitSystem"  ng-cloak>
							<div class="col-xs-12">
								<button
									class="btn btn-success btn-block waves-effect waves-light"
									type="submit">
									<span class="btn-label"><i
										class="glyphicon glyphicon-off"></i></span>로그인
								</button>
							</div>
						</div>

						<div class="form-group m-t-20 m-b-0">
							<div class="col-sm-6">
								<a data-toggle="modal" data-target="#find_id" class="text-dark"><i
									class="fa fa-lock m-r-5"></i> 아이디 찾기</a>
							</div>
							<div class="col-sm-6 text-right">
								<a data-toggle="modal" data-target="#find_pw" class="text-dark "><i
									class="fa fa-lock m-r-5"></i> 비밀번호 찾기</a>
							</div>
						</div>
					</form>
					<!-- <div class="form-group m-t-20 m-b-0 ng-hide" ng-show="findID!=null">
						<hr>
						아이디 : <span class="">{{findID}}</span>
					</div>
					<div class="form-group m-t-20 m-b-0 ng-hide" ng-show="findPwd!=null">
						<hr>
						비밀번호 : {{findPwd}}
					</div> -->
				</div>
			</div>
			<div>
				<div class="col-sm-12 text-center">
					<p>
						관리자 계정이 없습니다. <a href="" ng-click="fnOnCreateAdmin()"
							class="text-primary m-l-5"><b>계정생성</b></a>
					</p>
				</div>
			</div>

		</div>
	</div>
	<input type="hidden" ng-model="resultMessage" ng-init="resultMessage='${vm.resultMessage}'">
	<script	src="${pageContext.request.contextPath}/contents/base/js/angular.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/angular-route.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/angular-sanitize.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/noaa-admin.js?1=1"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/user-controller.js"></script>

</body>
</html>