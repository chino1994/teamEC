<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 3秒後にHomeActionに自動遷移 -->
<meta http-equiv="refresh" content="3; URL='HomeAction">
<link rel="stylesheet" href="./css/button.css">
<link rel="stylesheet" href="./css/detail.css">
<link rel="stylesheet" href="./css/error.css">
<link rel="stylesheet" href="./css/header.css">
<link rel="stylesheet" href="./css/list.css">
<link rel="stylesheet" href="./css/table1.css">
<link rel="stylesheet" href="./css/table2.css">
<link rel="stylesheet" href="./css/title.css">

<title>決済完了</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="contents">
		<h1>決済完了画面</h1>
		<div class="success">決済が完了しました。</div>
	</div>
</body>
</html>