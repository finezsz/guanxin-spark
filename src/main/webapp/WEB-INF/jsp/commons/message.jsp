<%@page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>错啦</title>
<%@include file="commons.jsp"%> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--这个页面体积要大于1024字节,否则在有些浏览器可能无法显示--><%
	String errors = (String) request.getAttribute("errors");
	if (StringUtils.isBlank(errors)) {
		errors = commonErrorMessage;
	}
	String requestType = request.getHeader("x-requested-with");
	if (!(requestType != null && "XMLHttpRequest".equals(requestType))) {// 非ajax请求才有3秒后跳转到首页
%><!-- 这个页面ajax请求和普通请求共用，通过 XMLHttpRequest区分是ajax请求还是普通浏览器请求，如果是ajax请求不要自动跳转到首页的这段js代码 -->
<script type="text/javascript">
	var i = 6;
	setInterval(function() {
		if (i < 1) {
			window.location = "<%=guanxinDomain%>/";
			return;
		}
		document.getElementById("reciprocal").innerHTML=i--;
	}, 1000);
</script><%
	}
%><style type="text/css">
body {
    background-color: #ffffff;
    color: #404040;
    font: 12px/1.5 arial,宋体,sans-serif;
    word-break: break-all;
    word-wrap: break-word;
}
em {
    color: #ff6000;
    font-family: arial,tahoma;
    padding: 0 3px;
}
a { 
    text-decoration: none;
    background-color: #FFF2F2;
} 
</style>
</head>
<body>
<div
	style="width: 650px;text-align:center;background-color: #FFF2F2; bspark: 1px solid #FF8080; padding:22px 18px; font-size: 12px; 
	font: 12px/1.5 arial, 宋体, sans-serif; word-wrap: break-word; word-break: break-all; margin:60px auto;">
	<!-- 如果是ajax请求则只要提取runTimeErrorMessage这个id对应元素下的文本，然后通过$$.showTip展示 ,详见guanxin.utils.js内的 parseAndShowError函数-->
	<span id="runTimeErrorMessage">
		<%=errors%>
	</span> 
	<span style="margin-left:8px;margin-right:6px;"> 
		<em id="reciprocal">6</em>秒后自动跳转到首页
	</span>
	<span>
		<b><a style="color: #ff6000;" href="<%=guanxinDomain%>/">立即跳转</a></b>
	</span>
<%  
if(exception!=null){
	exception.printStackTrace();
}
%>
</div>
</body>
</html>