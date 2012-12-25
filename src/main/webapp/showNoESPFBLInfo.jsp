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
					ESPs>新添加，没有ESP的fbl信息(hellO_id=0)
					<c:if test="${fblInfo_list ne null}">
					(${ fn:length(fblInfo_list)}个)
					</c:if>

				</h2>
				<table class="check-table">
					<tbody>
						<tr class="check-table-title">
							<th class="ck">邮件id</th>
							<th>选择ESP</th>
							<th>标记为可识</th>
							<th>删除</th>
						</tr>
						<c:if
							test="${fblInfo_list ne null and fn:length(fblInfo_list) ne 0}">
							<c:set var="fblInfo_list_len" value="${fn:length(fblInfo_list)}" />
							<c:forEach var="fblInfo" items="${fblInfo_list}" begin="0"
								end="${fblInfo_list_len-1}" step="1">
								<form action="updateFBLInfo.do" method="post">
									<input type="hidden" name="id" id="id" value="${fblInfo.id }" />
									<tr>
										<td class="ck">${fblInfo.emailId}</td>
										<td><c:if
												test="${esp_list ne null and fn:length(esp_list) ne 0 }">
												<c:set var="esp_list_len" value="${fn:length(esp_list)}" />
												<select id="esp_id" name="esp_id" style="width: 150px">
													<c:forEach var="esp" items="${esp_list}" begin="0"
														end="${esp_list_len-1}" step="1">
														<option value=${esp.helloId }>${esp.helloEsp }</option>
													</c:forEach>
												</select>
											</c:if></td>
										<td><input type="submit" id="submit" name="submit"
											value="修改" /></td>
										<td><a href="deleteFBLInfo.do?id=${fblInfo.id }">删除</a></td>
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
