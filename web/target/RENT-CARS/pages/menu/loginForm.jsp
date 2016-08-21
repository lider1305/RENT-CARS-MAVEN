<div><form method="post" action="Controller" class="login">
    <input name="page" type="hidden"  value="login"/>

    <label for="mail"><fmt:message key="login_f"/></label>
    <input type="text" name="mail" id="mail" value="name@gmail.com">

    <label for="password"><fmt:message key="password_f"/></label>
    <input type="password" name="password" id="password" value="4815162342">


    <p class="login-submit">
        <button type="submit" class="login-button" id="send"><fmt:message key="enter"/></button>
    </p>

    <a href="${pageContext.servletContext.contextPath}/Controller?page=go_to_registration"> <fmt:message key="registration"/> </a>   <a href="${pageContext.servletContext.contextPath}/Controller?page=forgot_password"> <fmt:message key="forgot_password"/> </a></p>
</form> </div>
