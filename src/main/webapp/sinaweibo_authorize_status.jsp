<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jstl/fn"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>主页-kankantu</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="style/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
    </style>
    <link href="style/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="style/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="style/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="style/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="style/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="style/ico/favicon.png">
  </head>

  <body>

 	<jsp:include page="head.jsp">
 		<jsp:param value="sinaweibo" name="flag"/>
 	</jsp:include>
 	
    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
         <jsp:include page="sinaweibo_left.jsp">
         	<jsp:param value="authorize_status" name="flag"/>
         </jsp:include>
        </div><!--/span-->
        <div class="span9">
          <div class="hero-unit">
            <h2>新浪微博</h2>
            <p>授权到新浪微博后，我们可以二次定制你的以及你所关注的人的新浪微博内容，定向关注收藏微博内容，还可以定制自动转发的功能！</p>
            <p><a class="btn btn-primary btn-large" href="/openOathPage.do">授权去咯 &raquo;</a></p>
          </div>
          <div class="row-fluid">
            <div class="span12">
              
              <c:if test="${token ne null}">
              	<h4>授权状态：<span class="label">授权有效</span></h4>
              	<h4>微博名称：<span class="label">${token.userName}</span></h4>
              	<h4>token：<span class="label">${token.token }</span></h4>
              	<h4>失效时间：<span class="label">${hour}小时${minute}分钟${second}秒&nbsp;后过期</span></h4>
              </c:if>
              <c:if test="${token eq null}">
              	<h4>授权状态：<span class="label label-important">未授权或授权超时</span></h4>
              </c:if>
              <p><a class="btn" href="listSinaWeiboAuthorize.do">查看详细授权信息 &raquo;</a></p>
            </div><!--/span-->
          </div><!--/row-->
        </div><!--/span-->
      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy; kankantu 2012</p>
      </footer>

    </div><!--/.fluid-container-->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="style/js/jquery.js"></script>
    <script src="style/js/bootstrap-transition.js"></script>
    <script src="style/js/bootstrap-alert.js"></script>
    <script src="style/js/bootstrap-modal.js"></script>
    <script src="style/js/bootstrap-dropdown.js"></script>
    <script src="style/js/bootstrap-scrollspy.js"></script>
    <script src="style/js/bootstrap-tab.js"></script>
    <script src="style/js/bootstrap-tooltip.js"></script>
    <script src="style/js/bootstrap-popover.js"></script>
    <script src="style/js/bootstrap-button.js"></script>
    <script src="style/js/bootstrap-collapse.js"></script>
    <script src="style/js/bootstrap-carousel.js"></script>
    <script src="style/js/bootstrap-typeahead.js"></script>

  </body>
</html>
