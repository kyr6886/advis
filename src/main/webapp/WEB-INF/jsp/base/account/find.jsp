<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../inc/popup_header.jsp"%>
<div class="col-sm-12 col-xs-12 col-md-12" role="main" ng-controller="userCtrl" ng-init="fnGetList()">


	<div class="clearfix"></div>

	<div class="row">

		<div class="col-sm-12 col-xs-12 col-md-12">
			<div class="x_panel" style="height: 600px;">
				<div class="x_title">
					<div class="col-sm-11 col-xs-11 col-md-11">
						<h2>사용자 검색</h2>
					</div>
					<div class="col-sm-1 col-xs-1 col-md-1">

						<a href="" onclick="javascript:window.close();" class="pull-right"><i
							class="fa fa-close"></i></a>

					</div>
					<div class="clearfix"></div>
					<div class="row" style="margin-top: 10px;">
						<div class="row">
							<div class="form">
								<div class="col-md-3 col-sm-3 col-xs-3 form-group">
									<select class="form-control">
										<option value="name">이름</option>
										<option value="id">아이디</option>
									</select>
								</div>
								<div  class="col-md-7 col-sm-7 col-xs-7 form-group">
									<input type="text" placeholder="아이디 또는 이름을 입력하세요." class="form-control" />
								</div>
								<div class="col-md-2 col-sm-2 col-xs-2 form-group">
									<button type="button" class="btn btn-primary btn_sm "> 검색 </button>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="table-responsive">
								<table class="table table-striped jambo_table bulk_action" style="bordere:none;">
									<thead>
										<tr class="headings">
											<th class="column-title">선택</th>
											<th class="column-title">아이디</th>
											<th class="column-title">이름</th>
											<th class="column-title">부서</th>
											<th class="column-title">전화번호</th>
											<th class="column-title">이메일</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="item in list">
											<td><input type="checkbox" ng-click="toggleSelection(item.user_id)"  class="form-group"/></td>
											<td>{{item.user_id}}</td>
											<td>{{item.user_name}}</td>
											<td>부서 출력예정</td>
											<td>{{item.user_phone}}</td>
											<td>{{item.user_email}}</td>
										</tr>
									</tbody>
								</table>
								<div class="row">
									<pager binding="pager" callback-click='fnOnPagingClick'></pager>
								</div>
							</div>
						</div>
					</div>
					

				</div>
			</div>
		</div>
		<div class="row" style="padding-right:13px;">
		<button type="button" class="btn btn-success btn-sm  pull-right" ng-click="fnOnClickChoice()">선택 완료</button>
		</div>
	</div>
</div>

<%@include file="../inc/popup_footer.jsp"%>