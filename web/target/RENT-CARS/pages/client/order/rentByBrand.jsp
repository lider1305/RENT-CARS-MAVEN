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
    <h2><fmt:message key="get_car_to_rent"/></h2>
    ${message_null_brand}
       <c:choose>
        <c:when test="${client.clientID > 0}">
            <div>
                <form method="POST" action="Controller">
                    <input name="page" type="hidden" value="rent_car_by_brand"/>
                    <br/>

                    <c:forEach var="brand" items="${brands}">
                        <table>
                            <tr>
                                <td width="20%"><c:out
                                        value="${brand.brand}"/></td>

                                <td><input type="radio" id="brandId" name="brandId" class="regular-radio big-radio"
                                           value="${brand.brandId}"/></td>
                                <td width="75%"></td>
                            </tr>
                        </table>
                    </c:forEach><br/>
                    <input type="submit" value="<fmt:message key="next"/>"/>
                </form>
            </div>
        </c:when>
        <c:when test="${client == null}">
            <h2><fmt:message key="message_for_make_order"/></h2>
        </c:when>
    </c:choose>

</inbody>
</body>
</html>

