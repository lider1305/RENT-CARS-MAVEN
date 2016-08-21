<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/pages/js/tcal.js"></script>
<html>
<head>
    <%@ include file="/pages/menu/work.jsp" %>
    <title><fmt:message key="title_main"/></title>
</head>
<body>
<hgroup>
    <%@ include file="/pages/menu/slogan.jsp" %>
</hgroup>
<nav>
    <%@ include file="/pages/menu/mainMenu.jsp" %>
</nav>
<section id="bg" class="overlay">
    <c:choose>
        <c:when test="${client.clientID > 0}">
            <%@ include file="/pages/menu/menuClientPersonal.jsp" %>
        </c:when>
        <c:when test="${client == null}">
            <%@ include file="/pages/menu/loginForm.jsp" %>
        </c:when>
    </c:choose>
</section>
<inbody>
    <div>
        <form method="POST" action="Controller" onsubmit="return validate();"  >
            <input name="page" type="hidden" value="registry"/>
            Введите ваши данные:<br/><br/>
            <fmt:message key="client_name"/>: *<br/>
            ${exception_null_name}
            <input type="text" name="fullName" id="fullName" value="" size="20"/>
            <fmt:message key="client_sur_name"/>: *<br/>
            ${exception_null_surname}
            <input type="text" name="surname" id="surname" value="" size="20"/>
            <fmt:message key="client_email"/>: *<br/>
            ${exception_null_mail}
            <input type="text" name="mail" id="mail" value="" size="20"/>
            <fmt:message key="client_password"/>: *<br/>
            ${exception_null_password}
            <input type="password" name="password" id="password" value="" size="20"/>
            <fmt:message key="client_phone"/>: *<br/>
            ${exception_null_phone}
            <input type="text" name="phone" id="phone" value="" size="20"/>
            <fmt:message key="client_passport"/>: *<br/>
            ${exception_null_passport}
            <input type="text" name="passport" id="passport" value="" size="20"/>
            <fmt:message key="client_passport_start"/>: *<br/>
            ${exception_null_date}
            <input type="text" name="startDate" id="startDate" class="tcal" value=""/>
            <fmt:message key="client_passport_end"/>: *<br/>
            ${exception_null_date}
            <input type="text" name="endDate" id="endDate" class="tcal" value=""/>
            <input type="submit"  value="<fmt:message key="button_registration"/>"/>
        </form>
    </div>
    <script>
        function validate() {
            var fullName = document.getElementById("fullName");
            var surname = document.getElementById("surname");
            var mail = document.getElementById("mail");
            var phone = document.getElementById("phone");
            var passport = document.getElementById("passport");
            var startDate = document.getElementById("startDate");
            var endDate = document.getElementById("endDate");

            if(!fullName.value) {
                fullName.style.border = "2px solid red";
                alert("Вы не ввели имя");
                return false;
            }

            if(!surname.value) {
                surname.style.border = "2px solid red";
                alert("Вы не ввели фамилию");
                return false;
            }

            if(!mail.value) {
                mail.style.border = "2px solid red";
                alert("Вы не ввели почту");
                return false;
            }
            if(!phone.value) {
                phone.style.border = "2px solid red";
                alert("Вы не ввели телефонный номер");
                return false;
            }if(!passport.value) {
                passport.style.border = "2px solid red";
                alert("Вы не ввели серию паспорта");
                return false;
            }if(!startDate.value) {
                startDate.style.border = "2px solid red";
                alert("Вы не ввели дату выдачи");
                return false;
            }if(!endDate.value) {
                endDate.style.border = "2px solid red";
                alert("Вы не ввели дату окончания");
                return false;
            }
            return true;
        }
    </script>
</inbody>
</body>
</html>


