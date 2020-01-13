<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>재난상황관리 표준화 기술</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basic.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/disaster.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">
	<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
	<script src="${pageContext.request.contextPath}/contents/advis/js/jquery-stringFormat.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/advis/js/polyfill.min.js"></script>
	<script	src="${pageContext.request.contextPath}/contents/advis/js/extends.js"></script>
	<style type="text/css">
	.links line {
	  stroke: #999;
	  stroke-opacity: 0.6;
	}
	.nodes .title {
	  fill:#ffffff;
	  font-weight:bold;
	}
	.nodes circle{
		stroke:#999;
		stroke-width:1px;
		stroke-opacity: 0.6;
	}
	svg{
		border:1px solid #e8e6e6;
		background-color:#f8f9fb;
	}
</style>
</head>
<body>
	<div class="wrap" id="scope-action" v-cloak>
		<div id="container">
	
			<div class="result-info text-center">
				<span> {{evtTitle}} 대처사항 보기 </span>
			</div>
			
			<div class="table-basic" v-show="evtCtgId=='NTY'">
				<table id="table-nty">
					<tr><th rowspan="2">문서생성일시</th><th colspan="2">기상현황</th><th rowspan="2">비상단계</th><th colspan="6">대처사항</th><th rowspan="2">피해상황</th></tr>
					<tr><th>기상특보</th><th>태풍현황</th><th>전파</th><th>점검</th><th>지시</th><th>파견</th><th>회의</th><th>대피</th></tr>
					<tr v-for="item,index in listDisEvtAction">
						<td>{{item.year}}.{{item.month}}.{{item.day}} {{item.hour}}시</td>
						<td class="text-left" v-html="item.dis_news"></td>
						<td class="text-left" v-html="item.dis_nty"></td>
						<td>{{item.sys_title}}</td>
						<td class="text-left">{{item.dis_act_lv1}}</td>
						<td class="text-left">{{item.dis_act_lv2}}</td>
						<td class="text-left">{{item.dis_act_lv3}}</td>
						<td class="text-left">{{item.dis_act_lv4}}</td>
						<td class="text-left">{{item.dis_act_lv5}}</td>
						<td class="text-left">{{item.dis_act_lv6}}</td>
						<td class="text-left" v-html="item.dis_dmg"></td>
					</tr>
				
				</table>
			</div>
			
			<div class="table-basic" v-show="evtCtgId!='NTY'">
				<table id="table-nhr">
					<tr><th rowspan="2">문서생성일시</th><th colspan="1">기상현황</th><th rowspan="2">비상단계</th><th colspan="6">대처사항</th><th rowspan="2">피해상황</th></tr>
					<tr><th>기상특보</th><th>전파</th><th>점검</th><th>지시</th><th>파견</th><th>회의</th><th>대피</th></tr>
					
					<tr v-for="item,index in listDisEvtAction">
						<td>{{item.year}}.{{item.month}}.{{item.day}} {{item.hour}}시</td>
						<td class="text-left" v-html="item.dis_news"></td>
						<td>{{item.sys_title}}</td>
						<td class="text-left">{{item.dis_act_lv1}}</td>
						<td class="text-left">{{item.dis_act_lv2}}</td>
						<td class="text-left">{{item.dis_act_lv3}}</td>
						<td class="text-left">{{item.dis_act_lv4}}</td>
						<td class="text-left">{{item.dis_act_lv5}}</td>
						<td class="text-left">{{item.dis_act_lv6}}</td>
						<td class="text-left" v-html="item.dis_dmg"></td>
					</tr>
				
				</table>
			</div>

		</div>
	</div>
	
	<script type="text/javascript">
	function pageLoad(obj){
		obj.fnListDisEventAction("${vm.paramEvtGrp}");
	}
	</script>
	<script src="${pageContext.request.contextPath}/contents/advis/js/model/vue/disEventAction-controller.min.js"></script>
</body>
</html>