<%@ page contentType="text/html;charset=UTF-8" language="java"
         errorPage="/pages/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="I18N.message"/>
<c:choose>
    <c:when test="${client.clientID > 0}">
<div><fmt:message key="choose_language"/> <a href="${pageContext.servletContext.contextPath}/Controller?page=set_locale_ru" id="locale_ru">RU</a><a href="${pageContext.servletContext.contextPath}/Controller?page=set_locale_en" id="locale_en">| EN</a>
</div>
    </c:when>
</c:choose>