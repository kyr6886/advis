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
<link	href="${pageContext.request.contextPath}/contents/base/css/basic.css"	rel="stylesheet">
 
	
<style type="text/css">
.x_title {
	height: 40px;
}

.m-right-10 {
	margin-right: 10px;
}
</style>

<title></title>
</head>


<body class="nav-md" ng-app="noaaAdmin">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="/system/manage/default#/" class="site_title"><i class="fa fa-paw"></i><span style="color:#dddddd;">Advis Admin</span></a>
					</div>
					<div class="clearfix"></div>

					<!-- menu prile quick info -->
					<!--    <div class="profile">
              <div class="profile_pic">
                <img src="images/img.jpg" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>Welcome,</span>
                <h2>John Doe</h2>
              </div>
            </div> -->
					<!-- /menu prile quick info -->

					<br />

					<!-- sidebar menu -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">
						<div class="menu_section">
							<ul class="nav side-menu">
								<li><a><i class="fa fa-home"></i> Home <span class="fa"></span></a>
									<!-- <ul class="nav child_menu">
                      <li><a href="index.html">Dashboard</a>54
                      </li>
                      <li><a href="index2.html">Dashboard2</a>
                      </li>
                      <li><a href="index3.html">Dashboard3</a>
                      </li>
                    </ul> --></li>
								<li><a><i class="fa fa-edit"></i> 코드 <span class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="/system/manage/default#/codeList">코드관리</a></li>
										<li><a href="/system/manage/default#/metaList">메타관리</a></li>
									</ul></li>
								<li><a><i class="fa fa-users"></i> 사용자 관리 <span class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="/system/manage/default#/userList">사용자 목록</a></li>
										<li><a href="/system/manage/default#/userLogList">사용자 접속현황</a></li>
										<!-- 	<li><a href="form_advanced.html">권한관리</a></li> -->
									</ul></li>
								<li><a><i class="fa fa-sitemap"></i> 메뉴관리 <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="/system/manage/default#/menuList">메뉴 목록</a></li>
										<li><a href="/system/manage/default#/menuRoleMng">권한 관리</a></li>
										<li><a href="/system/manage/default#/menuLogList">메뉴별 접속현황</a></li>
									</ul></li>
								<li><a><i class="fa fa-table"></i> 게시판 관리 <span class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="/system/manage/default#/bbsList">게시판 </a></li>
										<li><a href="/system/manage/default#/bbsContentList">게시물</a></li>
									</ul></li>
								<li>
									<a><i class="fa fa-external-link"></i> 관련 사이트 관리 <span class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="#/partnerList">관련 사이트</a></li>
									</ul>
								</li>
								
								<li>
									<a><i class="fa fa-external-link"></i> 표준화 관리 <span class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="#/dis/manage/code">코드 관리</a></li>
										<li><a href="#/dis/manage/event">재해 등록</a></li>
										<li><a href="#/dis/manage/status">재해 현황</a></li>
										<!-- <li><a href="#/dis/manage/test">Test</a></li> -->
										<li><a href="#/dis/manage/ocr">OCR 관리</a></li>
										<li><a href="#/dis/manage/manual">메뉴얼 관리</a></li>
									</ul>
								</li>
								<li>
									<a><i class="fa fa-database"></i> 데이터 관리 <span class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="#/data/kmaTyphoon">태풍</a></li>
										<li><a href="#/data/kmaInform">특보</a></li>
									</ul>
								</li>
							</ul>
						</div>
					</div>
					<!-- /sidebar menu -->
					<!-- /menu footer buttons -->
					<div class="sidebar-footer hidden-small">
						<a data-toggle="tooltip" data-placement="top" title="Lock">
	                <span class="glyphicon glyphicon-square" aria-hidden="true"></span>
	              </a>
	              <a data-toggle="tooltip" data-placement="top" title="Logout">
	                <span class="glyphicon glyphicon-square" aria-hidden="true"></span>
	              </a>
	              <a data-toggle="tooltip" data-placement="top" title="Settings">
	                <span class="glyphicon glyphicon-square" aria-hidden="true"></span>
	              </a>
	              <a data-toggle="tooltip" data-placement="top" title="Winsplus" href="/wins/v1/dashboard/">
	                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
	              </a>
              
            </div>
					<!-- /menu footer buttons -->
				</div>
			</div>

			<!-- top navigation -->
			<div class="top_nav">
				<div class="nav_menu">
					<nav class="" role="navigation">
						<div class="nav toggle"><a id="menu_toggle"><i class="fa fa-bars"></i></a></div>
						<ul class="nav navbar-nav navbar-right">
							<li class="">
								<a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> <!-- <img src="images/img.jpg" alt=""> -->
									<c:out value="${userName }" /> <span class=" fa fa-angle-down"></span>
								</a>
								<ul class="dropdown-menu dropdown-usermenu pull-right">
									<li>
										<!-- <a href="/account/logout?returnURI=/account/login"><i class="fa fa-sign-out pull-right"></i> Log Out</a> -->
										<a href="/account/logout?returnURI=/"><i class="fa fa-sign-out pull-right"></i> Log Out</a>
									</li>
								</ul>
							</li>
						</ul>
					</nav>
				</div>
			</div>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">

				<ng-view></ng-view>

			</div>
			<!-- /page content -->

			<!-- footer content -->
			<footer>
				<div class="pull-right">
					
				</div>
				<div class="clearfix"></div>
			</footer>
			<!-- /footer content -->
		</div>
		
	</div>
	<input type="hidden" id="prgID" value="${vm.pg}"/>
	<input type="hidden" id="hidResultMessage" value="${vm.resultMessage}">
	<script	src="${pageContext.request.contextPath}/contents/base/js/jquery.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/jquery-stringFormat.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/bootstrap.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/custom.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/fastclick.js"></script>
	<script src="${pageContext.request.contextPath}/contents/base/js/Chart.js/dist/Chart.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/angular.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/angular-route.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/angular-sanitize.min.js"></script>
	<script src="${pageContext.request.contextPath}/contents/base/js/model/directivesJS/ng-file-upload-shim.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/contents/base/js/model/directivesJS/ng-file-upload.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/contents/base/js/model/directivesJS/ng-file-upload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/contents/base/js/model/directivesJS/ng-file-upload-shim.min.js" type="text/javascript"></script>
	
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/noaa-admin.js?1=1"></script>
	
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/manage-status-controller.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/manage-discode-controller.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/manage-disOCR-controller.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/test-controller.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/user-controller.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/user-log-controller.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/code-controller.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/menu-controller.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/menu-log-controller.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/bbs-controller.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/meta-controller.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/bbs-content-controller.js?1=1"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/partner-controller.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/index-controller.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/contents/base/editor/js/HuskyEZCreator.js"	charset="utf-8"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/directivesJS/editor-directive.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/directivesJS/pager-directive.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/moment/moment.min.js"></script>
	<script src="${pageContext.request.contextPath}/contents/base/js/datepicker/daterangepicker.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/manage-status-controller.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/kmaTyphoon-controller.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/kmaInform-controller.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/manage-disManual-controller.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/base/js/model/manage-event-controller.js"></script>
	<script type="text/javascript">
	
		$(function() {
			/* $(".side-menu a").click(function() {
				$(".side-menu li").removeClass("current-page");
				$(".side-menu li").removeClass("active");
				$(this).parent().addClass("current-page active");
			}); */
			if ($("#hidResultMessage").val() != '')
				commonAlert($("#hidResultMessage").val(), "alert");
		});
		
			var durTime=1000*60;			
			function fnCreateMenuLog(){				
				if($("#prgID").val()!=""){
					$.ajax({
						  type: "POST",
						  url: "/api/admin/menu/history/create",
						  dataType: 'json',
						  contentType: 'application/json; charset=utf-8',
						  data:JSON.stringify({ pg: $("#prgID").val(),paramDurMils:durTime })
						})
						  .done(function( msg ) {
						    console.log(msg);
						  });
				}
			}
		//	fnCreateMenuLog();
		//	window.setInterval("fnCreateMenuLog()",durTime);
	</script>
	
</body>
</html>