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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">
	
	<script	src="${pageContext.request.contextPath}/contents/base/js/angular.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/angular-route.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/angular-sanitize.min.js"></script>
	<script src="${pageContext.request.contextPath}/contents/base/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/contents/advis/js/model/base-module.js"></script>
	<script src="${pageContext.request.contextPath}/contents/advis/js/model/login-controller.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/utils/rsa/jsbn.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/utils/rsa/rsa.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/utils/rsa/prng4.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/utils/rsa/rng.js"></script>
	<script type="text/javascript">
	function resizeContent() {
		var windowHeight = $(window).height();
		var topHeight = $('header').height();
		var bottomHeight = $('footer').height() +40;
		var containerHeight = $('#login-container').css({'min-height':(windowHeight-topHeight-bottomHeight)+'px'});
	}
	$(document).ready(function() {
		resizeContent();
	});
	 
	$(window).resize(function() {
		resizeContent();
	});

	</script>

</head>
<body ng-app="baseModule"  ng-controller="loginCtrl" ng-init="fnInit()" >
	<div class="wrap">
	    <div id="login-container">
	    	<div class="login-wrap">
		    	<div class="bg-wrap"></div>
	    		<h1><img src="${pageContext.request.contextPath}/contents/advis/images/logo-login.png"></h1>
	    		<div class="login-box">
	    			<div class="login-info">
						<div class="login-id"><label for="login-id" title="아이디">아이디</label>
							<input ng-model="detailUser.user_id"	name="loginInfo.id" type="text" required="required" placeholder=""></div>
						<div class="login-pw"><label for="login-pw" title="비밀번호">비밀번호</label>
							<input ng-model="detailUser.user_pwd"  name="loginInfo.password" type="password" value="" required="required" placeholder="">
						</div>
						<a class="btn-login" ng-click="fnOnClickBtnLogin()">로그인</a>
						</div>
		    		<div>
		    			<!-- <a href="" id="btn_join" class="btn btn-default-right">회원가입</a>-->
		    			<!-- <a href="" class="btn btn-default">아이디 / 비밀번호 찾기</a> -->
		    		</div>
	    		</div>
	    	</div>
		</div>
		<form class="form-horizontal m-t-20" method="post" name="mainForm"	action="<c:url value="/advis/login/login"/>" >
			<input type="hidden" id="keyM" name="loginInfo.keyModule" value="${vm.keyModule }" />
			<input type="hidden" id="keyEx" name="loginInfo.keyExponent" value="${vm.keyExponent }"/>
			<input type="hidden" id="encUserID" name="loginInfo.id" value=""/>
			<input type="hidden" id="encUserPwd" name="loginInfo.password" value=""/>
		</form>
		<footer>
			<div>Advanced Disaster visualized Intelligent Interactive and Information System</div>
			<address>주소. 우)44538 울산광역시 중구 종가로 365 국립재난안전연구원 / TEL : 052-928-8000
				<span class="copy">Copyright &copy; 2018 by National Disaster Management Institute. All Rights Reserved.</span>
			</address>
		</footer>
		
	</div>
	<div class="popup" style="top: 10rem; left: 10rem;display:none;">
		<div class="popup-header">회원가입<a href="" class="btn-close"></a></div>
		<div class="popup-contents">
			<div class="popup-infobox">각 항목에 해당하는 정보를 입력해주세요.</div>
			<div class="table-basic write-form">
				<table>
					<tbody>
						<tr>
							<th>소속 기관</th>
							<td><input type="text"></td>
						</tr>
						<tr>
							<th>소속 시군구</th>
							<td>
								<select>
									<option>전체</option>
									<option>전체</option>
									<option>전체</option>
								</select>
								<select>
									<option>전체</option>
									<option>전체</option>
									<option>전체</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>성명</th>
							<td><input type="text"></td>
						</tr>
						<tr>
							<th>아이디</th>
							<td><input type="text"></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="password"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<input type="checkbox" name="duty" id="a"><label for="a">예방/대비</label>
				<input type="checkbox" name="duty" id="b"><label for="b">대응</label>
				<input type="checkbox" name="duty" id="c"><label for="c">복구</label>
			</div>
			<div class="btn-group">
				<a href="" class="btn btn-gray">확인</a>
				<a href="" class="btn btn-default">취소</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$("#btn_join").click(function(){
				$(".popup").toggle();
				
			});
			
			$(".popup .btn-close").click(function(){$(".popup").hide();})
		});
	</script>
</body>
</html>