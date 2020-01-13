<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
system error <br>
<c:out value="${reason}"></c:out><br>
<c:out value="${statusCode}"></c:out><br>
<c:out value="${message}"></c:out>