<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib prefix="spring" uri="http://www.springframework.org/tags"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%><%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%><%@page import="com.guanxin.common.CommonConstants"%>
<%@page import="com.guanxin.common.persistence.Page"%>
<%@page import="com.guanxin.common.*"%> 
<%@page import="com.guanxin.spark.entity.*"%>
<%@page import="com.guanxin.common.utils.*"%>
<%@page import="java.util.*"%>
<%@page import="java.util.Map.*"%>
<%@page import="com.guanxin.spark.constants.*"%>
<fmt:setBundle basename="messages" var="nativeMessages" /><%-- commonErrorMessage只能通过fmt获取国际化,不能通过RequestUtils.getNativeResource()获取，因为进入这个页面的有可能没经过spring mvc的servlet --%><fmt:message key="common.error.message" bundle="${nativeMessages}" var="commonErrorMessage"/><fmt:message key="login.timeout.message" bundle="${nativeMessages}" var="loginTimeoutMessage"/><%
	String resourceDomain = CommonConstants.RESOURCE_DOMAIN;
	String guanxinDomain = CommonConstants.GUANXIN_DOMAIN;
	String commonErrorMessage = (String) pageContext.getAttribute("commonErrorMessage");
	String loginTimeoutMessage = (String) pageContext.getAttribute("loginTimeoutMessage");
	String csrfToken = (String) request.getAttribute("csrfToken");%><script type="text/javascript">
 
var newResourceDomain='<%=resourceDomain%>';
if(!window.resourceDomain&&newResourceDomain&&newResourceDomain!='null'){
	window.resourceDomain=newResourceDomain;
}
var newGuanxinDomain='<%=guanxinDomain%>';
if(!window.guanxinDomain&&newGuanxinDomain&&newGuanxinDomain!='null'){
	window.guanxinDomain=newGuanxinDomain;
}
var newCommonErrorMessage='<%=commonErrorMessage%>';
if(!window.commonErrorMessage&&newCommonErrorMessage&&newCommonErrorMessage!='null'){
	window.commonErrorMessage=newCommonErrorMessage;
}
var newLoginTimeoutMessage='<%=loginTimeoutMessage%>';
if(!window.loginTimeoutMessage&&newLoginTimeoutMessage&&newLoginTimeoutMessage!='null'){
	window.loginTimeoutMessage=newLoginTimeoutMessage;
}
var newCsrfToken='<%=csrfToken%>';
if(!window.csrfToken&&newCsrfToken&&newCsrfToken!='null'){
	window.csrfToken=newCsrfToken;
}
</script>  
<meta name="applicable-device"content="pc,mobile">