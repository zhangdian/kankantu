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
				<h2 class="content-title">
					ESPs>sb异常列表
					<c:if test="${abnormalInfo_list ne null}">
						(${fn:length(abnormalInfo_list)})
					</c:if>
				</h2>
				<table class="check-table">
					<tbody>
						<tr class="check-table-title">
							<th class="ck">邮件id</th>
							<th>from</th>
							<th>to</th>
							<th>已处理(删除)</th>
						</tr>
						<c:if
							test="${abnormalInfo_list ne null and fn:length(abnormalInfo_list) ne 0}">
							<c:set var="abnormalInfo_list_len"
								value="${fn:length(abnormalInfo_list)}" />
							<c:forEach var="abnormalInfo" items="${abnormalInfo_list}"
								begin="0" end="${abnormalInfo_list_len-1}" step="1">
								<tr>
									<td class="ck"><a
										href="getAbnormalSBInfo.do?id=${abnormalInfo.id }">${abnormalInfo.id}</a></td>
									<td>${abnormalInfo.fromAddress}</td>
									<td>${abnormalInfo.toAddress}</td>
									<td><a
										href="deleteAbnormalSBInfo.do?id=${abnormalInfo.id }">删除</a></td>
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
