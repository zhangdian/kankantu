<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jstl/fn"%>
<%
String flag = request.getParameter("flag");
%>
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#">看看图</a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
            <c:if test="${kankantu_user eq null}">
            	<a href="login.jsp" class="navbar-link">登陆</a>
            	<a href="signup.jsp" class="navbar-link">注册</a>
            </c:if>
            <c:if test="${kankantu_user ne null}">
            	<a href="account.jsp" class="navbar-link">${kankantu_user }</a>
            	<a href="signout.do" class="navbar-link">退出</a>
            </c:if>
            </p>
            <ul class="nav">
            <li <%if("main".equals(flag)) {%> class="active" <% }%>><a href="main.jsp">首页</a></li>
            <li <%if("sinaweibo".equals(flag)) {%> class="active" <% }%>><a href="sinaWeiboAuthorizeStatus.do">新浪微博</a></li>
            <li <%if("about".equals(flag)) {%> class="active" <% }%>><a href="#">关于</a></li>
          <!--     <li class="active"><a href="#">首页</a></li>
              <li><a href="sinaweibo.jsp">新浪微博</a></li>
              <li><a href="#contact">关于</a></li> -->
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>