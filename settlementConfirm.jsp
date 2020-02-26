<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/button.css">
<link rel="stylesheet" href="./css/detail.css">
<link rel="stylesheet" href="./css/error.css">
<link rel="stylesheet" href="./css/header.css">
<link rel="stylesheet" href="./css/list.css">
<link rel="stylesheet" href="./css/table1.css">
<link rel="stylesheet" href="./css/table2.css">
<link rel="stylesheet" href="./css/title.css">
<title>決済確認</title>
</head>
<body>

	<jsp:include page="header.jsp" />

	<script type="text/javascript">
		function setAction(url) {
			document.getElementById("form").action = url;
		}
	</script>
	<h1>決済確認画面</h1>
	<div id="main">
		<s:if
			test="destinationInfoDTOList != null && destinationInfoDTOList.size()>0">

			<div class="info">宛先情報を選択してください。</div>
			<br>
			<s:form id="form">

				<table class="horizontal-list-table">
					<thead>
						<tr>
							<th><s:label value="#" /></th>
							<th><s:label value="姓" /></th>
							<th><s:label value="名" /></th>
							<th><s:label value="ふりがな" /></th>
							<th><s:label value="住所" /></th>
							<th><s:label value="電話番号" /></th>
							<th><s:label value="メールアドレス" /></th>
						</tr>
					</thead>

					<s:iterator value="destinationInfoDTOList" status="st">
						<tbody>
							<tr>
								<td><s:if test="#st.index == 0">
								<input type="radio" name="id" checked="checked"
											value="<s:property value='id'/>" />
								</s:if> <s:else>
								<input type="radio" name="id" value="<s:property value='id'/>" />
								</s:else></td>
								<td><s:property value="familyName" /><br></td>
								<td><s:property value="firstName" /><br></td>
								<td><s:property value="familyNameKana" /><span>　</span>	<s:property value="firstNameKana" /><br></td>
								<td><s:property value="userAddress" /></td>
								<td><s:property value="telNumber" /></td>
								<td><s:property value="email" /></td>
							</tr>
						</tbody>
					</s:iterator>
				</table>

				<div class="submit_btn_box">
					<s:submit value="決済" class="submit_btn"
						onClick="setAction('SettlementCompleteAction')" />
				</div>
				<div class="submit_btn_box">
					<s:submit value="削除" class="submit_btn"
						onClick="setAction('DeleteDestinationAction')" />
				</div>
			</s:form>
		</s:if>

		<s:else>
			<div class="info">宛先情報がありません。</div>
		</s:else>

		<div class="submit_btn_box">
			<s:form action="CreateDestinationAction">
				<s:submit value="新規宛先登録" class="submit_btn" />
			</s:form>
		</div>

	</div>
</body>
</html>