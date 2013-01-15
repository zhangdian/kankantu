<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jstl/fn"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>我的tags-kankantu</title>
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
 		<jsp:param value="sinaweibo" name="flag"/>
 	</jsp:include>
 	
    <div class="container">
      <div class="row">
        <div class="span2">
         <jsp:include page="sinaweibo_left.jsp">
         	<jsp:param value="tag" name="flag"/>
         </jsp:include>
        </div><!--/span-->
			<div class="span10">
					<c:if test="${list_tag ne null }">
						<ul class="nav nav-tabs">
							<li <c:if test="${cur_tag eq \"all\" }">class="active"</c:if> ><a href="listRecommendUser.do" >ALL</a></li>
							<c:forEach var="tag" items="${list_tag}">
								<li <c:if test="${cur_tag eq tag.tagName}">class="active"</c:if> ><a href="listRecommendUser.do?tag_name=${tag.tagName}" >${tag.tagName} </a></li>
							</c:forEach>
							<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">more<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="listRecommendUser.do?tag_name=${cur_tag}">推荐用户</a></li>
									<li><a href="showFollows.do?tag_name=${cur_tag}">该组用户</a></li>
									<li><a href="#dropdown2">该组微博</a></li>
								</ul>
							</li>
						</ul>
					</c:if>
					<c:if test="${list_user ne null}">
						<ul class="thumbnails" id="list_recommend_user">
							<c:forEach var="user" items="${list_user}">
									<li class="span2">
										<div class="thumbnail">
											<a href="#"> 
												<img src="${user.profileImageURL}" alt="">
											</a>
											<div class="caption">
												<h5>${user.userName}</h5>
			      								<p>${user.followCount}粉丝</p>
			      								<c:if test="${user.follow eq false }">
			      									<p><a onclick="addFollow(${user.userId}, '${cur_tag}');" class="btn btn-primary" id="follow_${user.userId}">加关注</a></p>
			      								</c:if>
			      								<c:if test="${user.follow eq true }">
			      									<p><a onclick="deleteFollow(${user.userId}, '${cur_tag}');" class="btn" id="follow_${user.userId}">取消关注</a></p>
			      								</c:if>
	                  					</div>
	                  					</div>
									</li>
							</c:forEach>
						</ul>
					</c:if>
					<button type="button" class="btn" onclick="goon_load()" >继续加载</button>
					<hr>
					<button id="sync" name="sync" type="button" class="btn btn-primary" data-toggle="button" onclick="syncSinaWeiboTag.do" >同步Tags</button>
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
    <script src="style/js/bootstrap.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		
	});
	
	/* tag分组同步 */
	$("#sync").on("click", function () {
		window.location.href="syncSinaWeiboTag.do";
	});
	
	/* 继续加载 */
	function goon_load() {
		var request = $.ajax({
			url: "listMoreRecommendUser.do",
			type: "POST",
			dataType: "html"
		}); 
		request.done(function(msg) {
			$("#list_recommend_user").append(msg);
		});
		request.fail(function(jqXHR, textStatus) {
			$("#list_recommend_user").append(textStatus);
		});
	}
	
	/* 关注 */
	function addFollow(uid, tagName) {
		var request = $.ajax({
			url: "addFollow.do",
			type: "POST",
			data: {uid : uid, tag_name : tagName},
			dataType: "html"
		});
		request.done(function(msg) {
			$('#follow_' + uid).html("取消关注"); 
			$('#follow_' + uid).removeClass("btn-primary");
			$('#follow_' + uid).attr("onclick", "");
			$('#follow_' + uid).bind("click", function () {
				deleteFollow(uid, tagName);
			})
		});
		request.fail(function(jqXHR, textStatus) {
			alert("关注失败");
		});
	}
	
	/* 取消关注 */
	function deleteFollow(uid, tagName) {
		var request = $.ajax({
			url: "deleteFollow.do",
			type: "POST",
			data: {uid : uid, tag_name : tagName},
			dataType: "html"
		});
		request.done(function(msg) {
			$('#follow_' + uid).html("加关注"); 
			$('#follow_' + uid).addClass("btn-primary");
			$('#follow_' + uid).attr("onclick", "");
			$('#follow_' + uid).bind("click", function () {
				addFollow(uid, tagName);
			})
		});
		request.fail(function(jqXHR, textStatus) {
			alert("取消关注失败");
		});
	}
	 
	/* $('a[id^="follow_"]').html("已关注"); */
	</script>

  </body>
</html>
