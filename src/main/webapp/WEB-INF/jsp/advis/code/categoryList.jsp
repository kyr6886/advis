<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="../inc/top.jsp"%>
<link href="${pageContext.request.contextPath}/contents/advis/css/viewer.css" rel="stylesheet" />

<div id="scope-category" v-cloak>

	<div id="loading-layer"><div class="loading-content"></div>
 		<img id="loading-image" src="/images/ajax-loader.gif" alt="Loading..." />
 	</div>
	 	
	<div id="container">
		<div class="contents-title">
		<h2>카테고리 리스트</h2>
			<ul class="breadcrumb">
				<li>재난안전데이터 정보제공시스템</li>
				<li class="current">카테고리 리스트</li>
			</ul>
		</div>
	
	
		<section class="main-divide">
			<div class="select-area-code">
				<div class='panel-left'>
					<div class="section-title"><ul><l>대분류</li></ul></div>
					<div class="table-basic table-category">
						<table>
							<tr v-for="item in viewModel.listDepth1">
								<td><input type="radio" name="item.ctg_id" :id="item.ctg_id" :value="item.ctg_id" v-model="viewModel.selectedDepth1" v-on:change="fnOnChangeCtgId(2, item.ctg_id, item.title)" ></td>
								<td><a class="click-item" >{{item.title}}</td>
							</tr>
						</table>
					</div>
				
					<div class="section-title"><ul><l>중분류</li></ul></div>
					<div class="table-basic table-category">
						<table>
							<tr v-for="item in viewModel.listDepth2">
								<td><input type="checkbox" :id="item.ctg_id" :value="item.ctg_id" v-model="viewModel.selectedDepth2" v-on:change="fnOnChangeCtgId(3, item.ctg_id, item.title)" ></td>
								<td><a v-on:click="fnOnClickDisCodeList(3, item.ctg_id)" class="click-item">{{item.title}}</td>
							</tr>
						</table>
					</div>
				
					<div class="section-title"><ul><l>소분류</li></ul></div>
					<div class="table-basic table-category">
						<table>
							<tr v-for="item in viewModel.listDepth3">
								<td><input type="checkbox" :id="item.ctg_id" :value="item.ctg_id" v-model="viewModel.selectedDepth3" v-on:change="fnOnChangeCtgId(4, item.ctg_id, item.title)" ></td>
								<td><a v-on:click="fnOnClickDisCodeList(4, item.ctg_id)" class="click-item">{{item.title}}</td>
							</tr>
						</table>
					</div>
				
					<div class="section-title"><ul><l>세분류</li></ul></div>
					<div class="table-basic table-category">
						<table>
							<tr v-for="item in viewModel.listDepth4">
								<td><input type="checkbox" :id="item.ctg_id" :value="item.ctg_id" v-model="viewModel.selectedDepth4" v-on:change="fnOnChangeCtgId(5, item.ctg_id, item.title)" ></td>
								<td><a v-on:click="fnOnClickDisCodeList(5, item.ctg_id)" class="click-item">{{item.title}}</td>
							</tr>
						</table>
					</div>
				
					<a class="btn btn-blue-category" v-on:click="fnOnClickSearch()">
						<i class="ic-search mr05"></i>검색
					</a>
				</div>
			</div>
			
			<div class="result-area-code">
				<div class="section-title"><ul><l>선택 카테고리</li></ul></div>
				<div class="table-basic">
					<div class="category-selected-table">
						<table>
							<tr>
								<th>대분류</th>
								<td class="text-left">
									{{viewModel.selectedDepth1_nm}}
								</td>
							</tr>
							<tr>
								<th>중분류</th>
								<td class="text-left">
									<span v-for="item,index in viewModel.selectedDepth2_nm">
										<span v-if="index!=0"> , </span> {{item.title}}
									</span>
								</td>
							</tr>
							<tr>
								<th>소분류</th>
								<td class="text-left">
									<span v-for="item,index in viewModel.selectedDepth3_nm">
										<span v-if="index!=0"> , </span> {{item.title}}
									</span>
								</td>
							</tr>
							<tr>
								<th>세분류</th>
								<td class="text-left">
									<span v-for="item,index in viewModel.selectedDepth4_nm">
										<span v-if="index!=0"> , </span> {{item.title}}
									</span>
								</td>
							</tr>
						</table>
					</div>
				</div>
			
				<div v-show="viewModel.eventList.length>0">
					<ul class="tab-result">
						<li  v-for="item,index in viewModel.eventList">
							
							<h4>{{item.title}}({{item.evt_id}})</h4>
							<cite><a v-bind:href="'/advis/code/codeView/'+item.ctg_id+'/'+item.evt_id">/advis/code/codeView/{{item.ctg_id}}/{{item.evt_id}}</a></cite>
							
							<div v-for="item2 in viewModel.categoryDepth2">
								<h5>{{item2.title}}</h5>
								
								<div class="result-set" v-for="item3 in viewModel.categoryDepth3"  v-if="item3.parent_ctg_id==item2.ctg_id">
									{{item3.title}}
				
									<div class="code-table">
										<div  v-for="viewItem in item.detail" v-bind:class="{'item':viewItem.ctg_id.endsWith('10')}">
											<div v-for="item4 in viewModel.categoryDepth4">
												<table v-if="viewItem.ctg_id==item4.ctg_id && item4.parent_ctg_id==item3.ctg_id">
													<tr>
														<th>{{viewItem.contentsTitle}}</th>
														<td v-html="viewItem.contents"></td>
													</tr>
												</table>
											</div>
											
										</div>
									</div>
								</div>
							</div>
							
						</li>
					</ul>
				</div>
				
				<br>
				
				<div v-show="viewModel.eventList.length==0">
					<table>
						<tr>
							<td>해당 내용이 없습니다.</td>
						</tr>
					</table>
				</div>
				
				
				
			</div>
		</section>

	</div>
	<input type="hidden" name="toMonth" value="${toMonth}"/>
	<input type="hidden" name="monthDisaster" value="${monthDisaster}"/>
</div>
	
		


<script type="text/javascript">
	var vueObj = null;
	function pageLoad(obj) {
		vueObj = obj;
		obj.fnGetInit("${vm.paramCtgId}", "${vm.paramDepth}");
	}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/contents/advis/js/model/vue/category-controller.min.js"></script>


<%@include file="../inc/bottom.jsp"%>