<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/pages/menu/work.jsp" %>
       <title><fmt:message key="title_main"/></title>
</head>
<body>
<jsp:include page="/pages/menu/chooseLanguage.jsp"/>
<loc id="bg" class="login">
    <jsp:include page="/pages/menu/languageMenu.jsp"/>
</loc>
<hgroup>
    <%@ include file="/pages/menu/slogan.jsp" %>
</hgroup>
<nav>
    <%@ include file="/pages/menu/clientMenu.jsp" %>
</nav>
<section id="bg" class="overlay">
    <div>
        <%@ include file="/pages/menu/profileClientMenu.jsp" %>
    </div>
</section>
<inbody>
    <h2><fmt:message key="all_orders"/></h2>
    <div>
        <form  method="POST" action="Controller" >
            <input name="page" type="hidden" value="delete_order"/>
        <c:forEach var="order" items="${orders}" >
                <table>
                    <tr><td colspan="9"> </td></tr>
                    <tr>
                        <td width="10%"><c:out value="${order.orderId}"/></td>
                        <td width="15%"><c:out value="${order.brand}"/></td>
                        <td width="10%"><c:out value="${order.model}"/></td>
                        <td width="10%"><fmt:formatDate value="${order.startDate}" /></td>
                        <td width="10%"><fmt:formatDate value="${order.endDate}"/></td>
                        <td width="15%"><c:out value="${order.message}"/></td>
                        <td width="10%"><c:out value="${order.status}"/></td>
                        <td width="10%"><c:out value="${order.orderAmount}"/></td>
                        <td width="10%"><input type="radio" id="orderId" name="orderId" class="regular-radio big-radio"
                                               value="${order.orderId}"/></td>
                    </tr>
                </table>
            </c:forEach>
            <input type="submit" value="<fmt:message key="delete"/>" />
        </form>
    </div>
</inbody>
</body>
</html>
