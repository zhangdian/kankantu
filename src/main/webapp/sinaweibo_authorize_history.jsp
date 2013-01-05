<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jstl/fn"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>授权历史-kankantu</title>
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
         <jsp:include page="sinaweibo_left.jsp"></jsp:include>
        </div><!--/span-->
			<div class="span9">
				<table class="table table-bordered">
					<%-- <caption>您的历史授权记录</caption> --%>
					<thead>
						<tr>
							<th>时间戳</th>
							<th>token</th>
							<th>微博用户名称</th>
						</tr>
					</thead>
					<c:if test="${list ne null}">
						<tbody>
							<c:forEach var="item" items="${list}">
								<tr>
									<td>${item.date}</td>
									<td>${item.token}</td>
									<td>${item.userName}</td>
								</tr>
							</c:forEach>
						</tbody>
					</c:if>
				</table>
			</div>
			<!--/span-->
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
