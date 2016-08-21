<%@ page contentType="text/html;charset=UTF-8" language="java"
         errorPage="/pages/error.jsp"%>
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
   <h2><fmt:message key="list_of_all_cars"/></h2>
    <div>
        <form  method="POST" action="Controller" >
            <input name="page" type="hidden" value="all_car"/>
            <table>

                <tr>
                    <td width="15%"><fmt:message key="auto_brand"/></td>
                    <td width="15%"><fmt:message key="auto_model"/></td>
                    <td width="10%"><fmt:message key="auto_body_type"/></td>
                    <td width="20%"><fmt:message key="auto_engine_type"/></td>
                    <td width="20%"><fmt:message key="auto_transmission"/></td>
                    <td width="10%"><fmt:message key="auto_year"/></td>
                    <td width="10%"><fmt:message key="auto_amount"/></td>
                </tr>
            </table>
            <c:forEach var="car" items="${cars}" >
            <table>
                <tr><td colspan="7"> </td></tr>
                <tr>
                    <td width="15%"><c:out value="${car.brand}"/></td>
                    <td width="15%"><c:out value="${car.model}"/></td>
                    <td width="10%"><c:out value="${car.bodyType}"/></td>
                    <td width="20%"><c:out value="${car.engineType}"/></td>
                    <td width="20%"><c:out value="${car.transmissionType}"/></td>
                    <td width="10%"><c:out value="${car.yearOfManufacture}"/></td>
                    <td width="10%"><c:out value="${car.amount} y.e."/></td>
                </tr>
                </table>
            </c:forEach>
        <input type="submit" value="<fmt:message key="button_show"/>" />
        </form>
    </div>
</inbody>
</body>
</html>
