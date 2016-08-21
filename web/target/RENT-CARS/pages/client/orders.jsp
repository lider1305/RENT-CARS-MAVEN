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
            <input name="page" type="hidden" value="get_all_client_orders"/>
            <table>
                <tr>
                    <td width="10%"><fmt:message key="number_of_order"/></td>
                    <td width="15%"><fmt:message key="auto_brand"/></td>
                    <td width="10%"><fmt:message key="auto_model"/></td>
                    <td width="15%"><fmt:message key="order_start_date"/></td>
                    <td width="15%"><fmt:message key="order_end_date"/></td>
                    <td width="15%"><fmt:message key="other_info"/></td>
                    <td width="10%"><fmt:message key="order_status"/></td>
                    <td width="10%"><fmt:message key="order_price"/></td>
                </tr>
            </table>
            <c:forEach var="order" items="${orders}" >
                <table>


                    <tr><td colspan="8"> </td></tr>
                 <tr>
                        <td width="10%"><c:out value="${order.orderId}"/></td>
                        <td width="15%"><c:out value="${order.brand}"/></td>
                        <td width="10%"><c:out value="${order.model}"/></td>
                        <td width="15%"><fmt:formatDate value="${order.startDate}" /></td>
                        <td width="15%"><fmt:formatDate value="${order.endDate}"/></td>
                        <td width="15%"><c:out value="${order.message}"/></td>
                        <td width="10%"><c:out value="${order.status}"/></td>
                     <td width="10%"><c:out value="${order.orderAmount}"/></td>
                    </tr>
                </table>
            </c:forEach>
            <input type="submit" value="<fmt:message key="button_show"/>" />
            </form>
            <form  method="POST" action="Controller" >
                <input name="page" type="hidden" value="go_to_delete_order"/>
                <input type="submit" value="<fmt:message key="delete"/>" />
        </form>
    </div>
</inbody>
</body>
</html>
