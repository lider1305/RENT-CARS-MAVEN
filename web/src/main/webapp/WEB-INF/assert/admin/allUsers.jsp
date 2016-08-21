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
    <h2><fmt:message key="all_users"/></h2>
    <div>
        <form  method="POST" action="Controller" >
            <input name="page" type="hidden" value="get_all_users"/>
            <table>
                <tr>
                    <td width="5%"><fmt:message key="client_id"/></td>
                    <td width="15%"><fmt:message key="client_name"/></td>
                    <td width="10%"><fmt:message key="client_sur_name"/></td>
                    <td width="20%"><fmt:message key="client_email"/></td>
                    <td width="15%"><fmt:message key="client_phone"/></td>
                    <td width="15%"><fmt:message key="client_passport"/></td>
                    <td width="10%"><fmt:message key="client_passport_start"/></td>
                    <td width="10%"><fmt:message key="client_passport_end"/></td>
                </tr>
            </table>
            <c:forEach var="client" items="${users}" >
                <table>


                    <tr><td colspan="7"> </td></tr>
                    <tr>
                        <td width="5%"><c:out value="${client.clientID}"/></td>
                        <td width="15%"><c:out value="${client.name}"/></td>
                        <td width="10%"><c:out value="${client.surName}"/></td>
                        <td width="20%"><c:out value="${client.email}" /></td>
                        <td width="15%"><c:out value="${client.phone}"/></td>
                        <td width="15%"><c:out value="${client.passport}"/></td>
                        <td width="10%"><fmt:formatDate value="${client.passportIssueDate}"/></td>
                        <td width="10%"><fmt:formatDate value="${client.passportEndDate}"/></td>
                    </tr>
                </table>
            </c:forEach>
            <input type="submit" value="<fmt:message key="button_show"/>" />
        </form>
    </div>
</inbody>
</body>
</html>
