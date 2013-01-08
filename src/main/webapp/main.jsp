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
    <link href="style/css/bootstrap-responsive.css" rel="stylesheet">
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
       	<jsp:param value="main" name="flag"/>
 	</jsp:include>

    <div class="container">
      <div class="row">
        <div class="span4">
         
        </div><!--/span-->
        <div class="span8">
			<!-- 首页
			 <div class="bs-docs-example">
              <div id="myCarousel" class="carousel slide">
                <div class="carousel-inner">
                  <div class="item active">
                    <img src="images/2.jpg" alt="">
                    <div class="carousel-caption">
                      <h4>图片1</h4>
                      <p>图片1</p>
   	 				</div>
                  </div>
                  <div class="item">
                    <img src="images/2.jpg" alt="">
                    <div class="carousel-caption">   
                 	  	<h4>图片2</h4>
                      	<p>图片2</p>
                    </div>
                  </div>
                </div>
				 <a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>
              </div>
            </div> -->
            <div class="bs-docs-example">
              <div id="myCarousel" class="carousel slide">
                <div class="carousel-inner">
                  <div class="item active">
                    <img src="images/1.jpg" alt="">
                    <div class="carousel-caption">
                      <h4>图片1</h4>
                      <p>图片1</p>
                    </div>
                  </div>
                  <div class="item">
                    <img src="images/2.jpg" alt="">
                    <div class="carousel-caption">
                      <h4>图片2</h4>
                      <p>图片2</p>
                    </div>
                  </div>
                  <div class="item">
                    <img src="images/3.jpg" alt="">
                    <div class="carousel-caption">
                      <h4>图片3</h4>
                      <p>图片3</p>
                    </div>
                  </div>
                </div>
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
              </div>
            </div>
		</div>
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
    <script src="style/js/bootstrap.min.js"></script>
  </body>
</html>
