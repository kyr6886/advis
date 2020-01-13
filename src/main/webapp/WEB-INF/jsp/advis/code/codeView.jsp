<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="../inc/top.jsp"%>

<script src="${pageContext.request.contextPath}/contents/advis/js/model/zTree.js"></script>
<link href="${pageContext.request.contextPath}/contents/advis/css/zTree.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/contents/advis/css/viewer.css" rel="stylesheet" />

<div id="scope-code" v-cloak>
	<div id="container">
		<div class="contents-title">
			<h2>코드검색</h2>
			<ul class="breadcrumb">
				<li>재난안전데이터 정보제공시스템</li>
				<li class="current">재해현황 리포트 보기</li>
			</ul>
		</div>

		<section class="main-divide">
			<div class="select-area-code">
				<ul id="tree" class="ztree"></ul>
			</div>
			<div class="result-area-code">
				
					<h3 class="result-title" v-if="eventName!=''">{{eventName}}</h3>
				
				
					<div v-for="item2 in depth2">
						<h4 class="page-header">{{item2.title}} </h4>

						<div class="result-set" v-for="item3 in depth3"  v-if="item3.parent_ctg_id==item2.ctg_id">
							<div>
								<h5>{{item3.title}} </h5>

								<div class="code-table" v-if="category_name!='NHR'">
									<div  v-for="viewItem in eventItemList" v-bind:class="{'item':viewItem.ctg_id.endsWith('10')}">
										<div v-for="item4 in depth4">
											<table
												v-if="viewItem.ctg_id==item4.ctg_id && item4.parent_ctg_id==item3.ctg_id">
												<tr>
													<th>{{item4.title}}</th>
													<td v-html="viewItem.contents"></td>
												</tr>
											</table>
										</div>
									</div>
								</div>
								
								<div class="code-table" v-if="category_name=='NHR'">
									<div  v-for="viewItem in eventItemList" v-bind:class="{'item':viewItem.ctg_id.endsWith('20')}">
										<div v-for="item4 in depth4">
											<table
												v-if="viewItem.ctg_id==item4.ctg_id && item4.parent_ctg_id==item3.ctg_id">
												<tr>
													<th>{{item4.title}}</th>
													<td v-html="viewItem.contents"></td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				
			</div>
		</section>
	</div>
</div>
<script type="text/javascript">
	var vueObj = null;
	function pageLoad(obj) {
		vueObj = obj;
		obj.fnGetList("${vm.paramCtgId}", "${vm.paramEvtId}");
	}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/contents/advis/js/model/vue/codeView-controller.min.js"></script>



<%@include file="../inc/bottom.jsp"%>
