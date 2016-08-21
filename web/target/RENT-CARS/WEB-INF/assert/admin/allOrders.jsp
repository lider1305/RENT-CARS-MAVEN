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
    <%@ include file="/pages/menu/menuAdmin.jsp" %>
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
            <input name="page" type="hidden" value="get_all_orders"/>
            <table>
                <tr>
                    <td width="15%"><fmt:message key="client_id"/></td>
                    <td width="15%"><fmt:message key="client_id_call"/></td>
                    <td width="10%"><fmt:message key="car_id"/></td>
                    <td width="15%"><fmt:message key="order_start_date"/></td>
                    <td width="15%"><fmt:message key="order_end_date"/></td>
                    <td width="20%"><fmt:message key="other_info"/></td>
                    <td width="10%"><fmt:message key="order_status"/></td>
                </tr>
            </table>
            <c:forEach var="orderDTO" items="${ordersAdmin}" >
                <table>


                    <tr><td colspan="7"> </td></tr>
                    <tr>
                        <td width="15%"><c:out value="${orderDTO.orderId}"/></td>
                        <td width="15%"><c:out value="${orderDTO.clientID}"/></td>
                        <td width="10%"><c:out value="${orderDTO.carId}"/></td>
                        <td width="15%"><fmt:formatDate value="${orderDTO.startDate}" /></td>
                        <td width="15%"><fmt:formatDate value="${orderDTO.endDate}"/></td>
                        <td width="20%"><c:out value="${orderDTO.message}"/></td>
                        <td width="10%"><c:out value="${orderDTO.orderStatus}"/></td>
                    </tr>
                </table>
            </c:forEach>
            <input type="submit" value="<fmt:message key="button_show"/>" />
        </form>
    </div>
</inbody>
</body>
</html>
