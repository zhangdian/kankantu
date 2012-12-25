<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jstl/fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>SB-FBL后台管理系统</title>
<link rel="stylesheet" href="style/style.css" />
</head>
<body>
	<div class="wrap">
		<div class="hd-bg">
			<h1 class="hd-logo">SB-FBL后台管理系统</h1>
		</div>
		<div class="content">
			<jsp:include page="content_left.jsp"></jsp:include>
			<div class="content-right check-info">
				<h2 class="content-title">ESPs>未处理的ESP列表</h2>
				<table class="check-table">
					<tbody>
						<tr class="check-table-title">

							<th class="ck">esp id</th>
							<th>esp名称</th>
							<th>已处理，加入到可识的ESP列表(状态变为1)</th>
							<th>删除</th>
						</tr>
						<c:if test="${esp_list ne null and fn:length(esp_list) ne 0}">
							<c:set var="esp_list_len" value="${fn:length(esp_list)}" />
							<c:forEach var="esp" items="${esp_list}" begin="0"
								end="${esp_list_len-1}" step="1">
								<form method="post" action="updateESP.do">
									<tr>
										<td class="ck">${esp.helloId}<input type="hidden"
											name="hello_id" id="hello_id" value="${esp.helloId}" /></td>
										<td><input type="text" name="hello_esp" id="hello_esp"
											value="${esp.helloEsp}" /><input type="hidden" name="status"
											id="status" value="1" /></td>
										<td><input type="submit" name="submit" id="submit"
											value="加入" /></td>
										<td><a
											href="deleteUnHandleESP.do?hello_id=${esp.helloId}">删除</a>
									</tr>
								</form>

							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<!-- <div class="check-tool">
					<a href="#" class="select-all">全部选中</a> <span class="check-all"><a
						href="#" class="check-btn pass">通过选中项</a> <a href="#"
						class="check-btn reject">否决选中项</a></span> <span class="page-nav"><a
						href="#">&laquo;上一页</a> | <a href="#">下一页&raquo;</a></span>
				</div> -->
			</div>
		</div>
	</div>
</body>
</html>
