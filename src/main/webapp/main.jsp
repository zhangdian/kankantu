<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>看看图</title>
<link rel="stylesheet" href="style/style.css" />
</head>
<body>
<a href="/openOathPage.do">授权</a><br>

<form method="post" action="/getToken.do">
	输入code<input type="text" name="code" id="code" /><br>
	<input type="submit" value="获取token" /><br>
</form>

token：<input type="text" name="token" id="token" value="${token}" /><br>

<form method="post" action="/repostWeibo.do">
输入use_id<input type="text" name="user_id" id="user_id" /><br>
<input type="submit" value="转发他的5条微博" /><br></form>
</body>
</html>
