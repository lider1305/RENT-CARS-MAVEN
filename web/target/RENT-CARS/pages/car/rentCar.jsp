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
<loc id="bg" class="login">
    <jsp:include page="/pages/menu/languageMenu.jsp"/>
</loc>
<hgroup>
    <%@ include file="/pages/menu/slogan.jsp" %>
</hgroup>
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
    <c:choose>
        <c:when test="${client.clientID > 0}">
    <h2><fmt:message key="get_car_to_rent"/></h2>
    <h2><fmt:message key="choose_car"/>:<br/></h2>
            <h3>${message_null_brand}</h3>
            <h3>${message_wrong_param}</h3>
            <h3>${exception_null_date}</h3>
            <h3>${car_is_rent_on_this_date}</h3>
            <h3>${exception_wrong_date}</h3>
            <h3>${exception_wrong_date_end}</h3>
    <table width="20%">
        <tr>
            <form method="POST" action="Controller">
                <input name="page" type="hidden" value="go_to_rent_all_cars"/>
                <h3><input type="submit" value="<fmt:message key="available"/>"/></h3>
            </form>
        </tr>
        <tr>
            <form method="POST" action="Controller">
                <input name="page" type="hidden" value="go_to_rent_car_by_brand"/>
                <h3><input type="submit" value="<fmt:message key="by_brand"/>"/></h3>
            </form>
        </tr>
        <tr>
            <form method="POST" action="Controller">
                <input name="page" type="hidden" value="go_to_rent_car_by_transmission"/>
                <h3><input type="submit" value="<fmt:message key="by_transmission"/>"/></h3>
            </form>
        </tr>
    </table>
    </c:when>
    <c:when test="${client == null}">
        <h2><fmt:message key="message_for_make_order"/></h2>
    </c:when>
</c:choose>
</inbody>
</body>
</html>
