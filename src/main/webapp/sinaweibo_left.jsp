<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jstl/fn"%>
<%
String flag = request.getParameter("flag");
%>
<div class="well sidebar-nav">
	<ul class="nav nav-list">
		<li class="nav-header">账户信息</li>
		<li <%if("authorize_status".equals(flag)) {%> class="active" <% }%>><a href="sinaWeiboAuthorizeStatus.do">授权状态</a></li>
		<li <%if("authorize_history".equals(flag)) {%> class="active" <% }%>><a href="listSinaWeiboAuthorize.do">授权历史</a></li>
		<li class="nav-header">微博信息</li>
		<li <%if("tag".equals(flag)) {%> class="active" <% }%>><a href="listSinaWeiboTag.do">我的tags</a></li>
		<li <%if("main".equals(flag)) {%> class="active" <% }%>><a href="#">Link</a></li>
		<li <%if("main".equals(flag)) {%> class="active" <% }%>><a href="#">Link</a></li>
	</ul>
</div>
<!--/.well -->