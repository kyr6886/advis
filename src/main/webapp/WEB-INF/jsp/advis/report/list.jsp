<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="../inc/top.jsp"%>

<div id="scope-report" v-cloak>

	<div id="container">
		<div class="contents-title">
			<h2> 관련 재난 리스트 </h2>
			<ul class="breadcrumb">
				<li>재난안전데이터 정보제공시스템</li>
				<li class="current">관련 재난 리스트 보기</li>
			</ul>
		</div>
		
		<section class="main-divide">
			<div class="ol-wrap">
				<div class="text-left select-area-code">
					<select v-model="selectedDisEventType" id="dis_title" v-on:change="fnGetListDisEvent">
						<option v-for="item in listDisEventType" v-bind:value="item.ctg_id" v-key="item.ctg_id">{{item.title}}</option>
					</select>
				</div>
				<div class="text-right">
					<a href="#" target="_blank" class="btn btn-blue" v-on:click="fnOnClickWritePage('')" target="_blank">작성하기</a>
				</div>
			</div>
			<div class="table-basic">
				<table>
					<thead>
						<th>태풍명</th>
						<th>작성일시</th>
						<th>대응단계</th>
						<th>사망</th>
						<th>실종</th>
						<th>부상</th>
						<th>보기</th>
					</thead>
					<tbody v-if="listDisEventIsCs_y.length!=0">
						<tr v-for="item in listDisEventIsCs_y">
							<td>{{item.title}}</td>
							<td>{{fnPrintDateTimeByNum(item.evt_date)}}</td>
							<td>{{item.dis_level_code}}</td>
							<td>{{item.com_1}}</td>
							<td>{{item.com_2}}</td>
							<td>{{item.com_3}}</td>
							<td><a class="btn btn-small btn-gray" v-on:click="fnOnClickWritePage(item.evt_id)" target="_blank">보기</a></td>
						</tr>
					</tbody>
					<tbody v-if="listDisEventIsCs_y.length==0">
						<tr>
							<td colspan="6"> 조회 데이터가 없습니다. </td>
						</tr>
					</tbody>
				</table>
			</div>
										
		</section>
		
		
	</div>
	
</div>

<script type="text/javascript">
	function pageLoad(obj) {
		obj.fnGetListDisType();
	}
	
	function fnPrintDateTimeByNum(_date){
		if(_date!=null && _date!=""){
	        var year=_date.substring(0,4);
	        var month=_date.substring(4,6);
	        var day=_date.substring(6,8);
	        var hh=_date.substring(8,10);
	        var min=_date.substring(10,12);
	        return year+"-"+month+"-"+day+" "+hh+":"+min;
	      }
		return "";
	}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/contents/advis/js/model/vue/report-controller.min.js"></script>

<%@include file="../inc/bottom.jsp"%>

