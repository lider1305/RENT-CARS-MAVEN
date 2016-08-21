<%@ page contentType="text/html;charset=UTF-8" language="java"
         errorPage="/pages/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <%@ include file="/pages/menu/work.jsp" %>
    <title><fmt:message key="title_main"/></title>
</head>
<body>
<loc id="bg" class="login">
    <jsp:include page="/pages/menu/languageMenu.jsp"/>
</loc>
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
    <h2><fmt:message key="get_car_to_rent"/></h2>
    <c:choose>
        <c:when test="${client.clientID > 0}">
            <div>
                <form method="POST" action="Controller">
                    <input name="page" type="hidden" value="car"/>
                    <br/>
                    <a class="button" href="${pageContext.servletContext.contextPath}/Controller?page=list_of_all_cars" id="allCar">
                        <h2 class="button">
                            <fmt:message key="choose_car"/>:</h2></a>
                    <c:forEach var="car" items="${cars}">
                        <table width="800px">
                            <tr>
                                <td width="90%"><c:out
                                        value="${car.brand} : ${car.model} : ${car.bodyType} : ${car.engineType} : ${car.transmissionType}
                     : Год выпуска ${car.yearOfManufacture} : стоимость  ${car.amount} "/></td>

                                <td><input type="radio" id="carId" name="carId" class="regular-radio big-radio"
                                           value="${car.carId}"/></td>

                            </tr>
                        </table>
                    </c:forEach><br/>
                    <fmt:message key="date_start_of_rent"/>:
                    <input type="text" name="startDate" class="tcal" value=""/>
                    <fmt:message key="date_end_of_rent"/>:
                    <input type="text" name="endDate" class="tcal" value=""/>
                    <fmt:message key="other_info"/>:
                    <input type="text" name="message_for_order" value="Необходим навигатор" size="20"/>
                    <input type="submit" value="<fmt:message key="make_order"/>"/>
                </form>
            </div>
        </c:when>
        <c:when test="${client == null}">
            <h2><fmt:message key="message_for_make_order"/></h2>
        </c:when>
    </c:choose>

</inbody>
</body>
</html>

