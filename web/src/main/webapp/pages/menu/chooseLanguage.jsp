<c:choose>
    <c:when test="${locale == null}">
        <fmt:setLocale value="ru"/>
    </c:when>
    <c:when test="${locale != null}">
        <fmt:setLocale value="${locale}"/>
    </c:when>
</c:choose>
<fmt:setBundle basename="I18N.message"/>

