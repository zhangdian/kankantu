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
				<h2 class="content-title">ESPs>ESP列表</h2>
				<table class="check-table">
					<tbody>
						<tr class="check-table-title">

							<th class="ck">esp id</th>
							<th>esp名称</th>
							<th>esp状态</th>
							<th>sb信息 <c:if test="${total_sb ne null}">
							${total_sb }
							</c:if>
							</th>
							<th>fbl信息<c:if test="${total_fbl ne null}">
							${total_fbl }
							</c:if></th>
						</tr>
						<c:if test="${esp_list ne null and fn:length(esp_list) ne 0}">
							<c:set var="esp_list_len" value="${fn:length(esp_list)}" />
							<c:forEach var="esp" items="${esp_list}" begin="0"
								end="${esp_list_len-1}" step="1">
								<tr>
									<td class="ck">${esp.helloId}</td>
									<td>${esp.helloEsp}</td>
									<td>${esp.status}</td>
									<td><a href="getAllHelloSBInfo.do?hello_id=${esp.helloId}">查看(${esp.totalSB
											})</a></td>
									<td><a
										href="getAllHelloFBLInfo.do?hello_id=${esp.helloId}">查看(${esp.totalFBL
											})</a></td>
								</tr>

							</c:forEach>
							<form action="addESP.do" mwthod="post">
								<tr>
									<td class="ck">添加</td>
									<td><input type="text" id="esp_name" name="esp_name" /></td>
									<td><input type="text" id="status" name="status" /></td>
									<td><input type="submit" id="submit" name="submit" value="添加" /></td>
								</tr>
							</form>
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
