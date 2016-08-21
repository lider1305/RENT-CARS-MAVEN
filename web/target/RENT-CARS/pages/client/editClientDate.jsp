<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <fmt:message key="title_main"/>
    <%@ include file="/pages/menu/work.jsp" %>
</head>
<loc id="bg" class="login">
    <jsp:include page="/pages/menu/languageMenu.jsp"/>
</loc>
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
    <div>
        <form method="POST" action="Controller">
            <input name="page" type="hidden" value="edit_client"/>
            <fmt:message key="context_edit_data"/><br/><br/>
            <fmt:message key="client_name"/>:
            <input type="text" name="name" value="${client.name}" size="20"/>
            <fmt:message key="client_sur_name"/>:
            <input type="text" name="surname" value="${client.surName}" size="20"/>
            <fmt:message key="client_email"/>:
            <input type="text" name="mail" value="${client.email}" size="20"/>
            <fmt:message key="client_password"/>:
            <input type="text" name="password" value="${client.password}" size="20"/>
            <fmt:message key="client_phone"/>:
            <input type="text" name="phone" value="${client.phone}" size="20"/>
            <fmt:message key="client_passport"/>:
            <input type="text" name="passport" value="${client.passport}" size="20"/>
            <fmt:message key="client_passport_start"/>:
            ${exception_null_date}
            <input type="text" name="startDate" class="tcal" value=""/>
            <fmt:message key="client_passport_end"/>:
            ${exception_null_date}
            <input type="text" name="endDate" class="tcal" value=""/>
            <input type="submit" value="<fmt:message key="change_data"/>"/>
        </form>
    </div>
</inbody>
</body>
</html>
