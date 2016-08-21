<%@ page contentType="text/html;charset=UTF-8" language="java"
         errorPage="/pages/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <%@ include file="/pages/menu/work.jsp" %>
    <title><fmt:message key="title_main"/></title>
</head>
<body>
<%@ include file="/pages/menu/slogan.jsp" %>
<nav>
    <%@ include file="/pages/menu/mainMenu.jsp" %>
</nav>
<section id="bg" class="overlay">
    <c:choose>
        <c:when test="${client.clientID > 0}">
            <%@ include file="/pages/menu/menuClientPersonal.jsp" %>
        </c:when>
        <c:when test="${client == null}">
            <%@ include file="/pages/menu/loginForm.jsp" %>
        </c:when>
    </c:choose>
</section>
<inbody>
    <h2><fmt:message key="hello"/></h2>
    <h2>${error}</h2>
    <h2>${reg}</h2>
    <h2>${session_close}</h2>
</inbody>
</body>
</html>
