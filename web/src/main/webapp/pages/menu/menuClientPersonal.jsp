<c:choose>
    <c:when test="${client.role == 'ADMIN'}">
        <ul id="menu1" class="client-menu">
            <li><a href="${pageContext.servletContext.contextPath}/Controller?page=go_to_admin_page" id="admin"> <span class="wrap"> <span class="link"><fmt:message key="personal_page"/></span></span> </a></li>
        </ul>
    </c:when>
    <c:when test="${client.role == 'USER'}">
        <ul id="menu1" class="client-menu">
            <li><a href="${pageContext.servletContext.contextPath}/Controller?page=go_to_client_page" id="rewrite"> <span class="wrap"> <span class="link"><fmt:message key="personal_page"/></span></span> </a></li>
        </ul>
    </c:when>
</c:choose>



