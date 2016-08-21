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
    <h2><fmt:message key="hello_user"/> ${client.name} ${client.surName}</h2>
    <p></p>
    <div>
        <table width="100%" border="1" class="table">
            <tr>
                <td><fmt:message key="client_name"/></td>
                <td><fmt:message key="client_sur_name"/></td>
                <td><fmt:message key="client_email"/></td>
                <td><fmt:message key="client_phone"/></td>
                <td><fmt:message key="client_passport"/></td>
                <td><fmt:message key="client_passport_start"/></td>
                <td><fmt:message key="client_passport_end"/></td>
            </tr>
            <tr>
                <td>${client.name}</td>
                <td>${client.surName}</td>
                <td>${client.email}</td>
                <td>${client.phone}</td>
                <td>${client.passport}</td>
                <td>${strDate}</td>
                <td>${endDate}</td>
            </tr>
        </table>
    </div>
</inbody>
</body>
</html>
