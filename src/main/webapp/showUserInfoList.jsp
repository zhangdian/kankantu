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
				<h2 class="content-title">用户信息>用户列表</h2>
				<table class="check-table">
					<tbody>
						<tr class="check-table-title">
							<th class="ck">用户id</th>
							<th>用户名称</th>
							<th>category详情</th>
						</tr>
						<c:if test="${userinfo_list ne null and fn:length(userinfo_list) ne 0}">
							<c:set var="userinfo_list_len" value="${fn:length(userinfo_list)}" />
							<c:forEach var="userInfo" items="${userinfo_list}" begin="0"
								end="${userinfo_list_len-1}" step="1">
								<tr>
									<td class="ck">${userInfo.userId}</td>
									<td>${userInfo.userName}</td>
									<td><a href="getUserCategoryList.do?user_id=${userInfo.userId}">查看</a></td>
								</tr>
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
