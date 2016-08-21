<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  errorPage="/pages/error.jsp"%>
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
    <h2>${new_car}</h2>
    <h2><fmt:message key="all_orders"/></h2>
    <h2>${exception_null_model}</h2>
    <div>
        <form  method="POST" action="Controller" >
            <input name="page" type="hidden" value="add_new_car"/>
            <table>
                <tr>
                    <td width="15%"><fmt:message key="auto_brand"/></td>
                    <td width="20%"><fmt:message key="auto_model"/></td>
                    <td width="10%"><fmt:message key="auto_body_type"/></td>
                    <td width="15%"><fmt:message key="auto_engine_type"/></td>
                    <td width="20%"><fmt:message key="auto_transmission"/></td>
                    <td width="10%"><fmt:message key="auto_year"/></td>
                    <td width="10%"><fmt:message key="auto_amount"/></td>
                </tr>
            </table>
            <table>


                    <tr><td colspan="7"> </td></tr>
                    <tr>
                        <td width="15%"><select name="brand" class="input">
                            <option value="1">ACURA</option>
                            <option value="2">AUDI</option>
                            <option value="3">BMW</option>
                            <option value="4">DODGE</option>
                            <option value="5">FORD</option>
                            <option value="6">HONDA</option>
                            <option value="7">INFINITY</option>
                            <option value="8">LEXUS</option>
                            <option value="9">MAZDA</option>
                            <option value="10">MERCEDES</option>
                            <option value="11">NISSAN</option>
                            <option value="12">OPEL</option>
                            <option value="13">PORSCHE</option>
                            <option value="14">SKODA</option>
                            <option value="15">TOYOTA</option>
                        </select></td>
                        <td width="20%"> <input type="text" name="model"  value="" size="10"/></td>
                        <td width="10%"><select name="body_type" class="input">
                            <option value="1">СЕДАН</option>
                            <option value="2">УНИВЕРСАЛ</option>
                            <option value="3">МИНИВЭН</option>
                            <option value="4">ПАРКЕТНИК</option>
                            <option value="5">ДЖИП</option>
                            <option value="6">КУПЭ</option>
                            <option value="7">КАБРИОЛЕТ</option>
                        </select></td>
                        <td width="15%"><select name="engine_type" class="input">
                            <option value="1">БЕНЗИН</option>
                            <option value="2">ДИЗЕЛЬ</option>
                            <option value="3">ГИБРИД</option>
                        </select></td>
                        <td width="20%"><select name="transmission_type" class="input">
                            <option value="1">МЕХАНИКА</option>
                            <option value="2">АВТОМАТ</option>
                        </select></td>
                        <td width="10%"><select name="year" class="input" >
                            <option value="2016">2016</option>
                            <option value="2015">2015</option>
                            <option value="2014">2014</option>
                            <option value="2013">2013</option>
                            <option value="2012">2012</option>
                            <option value="2011">2011</option>
                            <option value="2010">2010</option>
                            <option value="2009">2009</option>
                            <option value="2008">2008</option>
                            <option value="2007">2007</option>
                            <option value="2006">2006</option>
                        </select></td>
                        <td width="10%"><input type="number" name="amount" class="input" min="0" max="100" step="1" value="5" size="10"/></td>
                    </tr>
                </table>
            <input type="submit" value="<fmt:message key="button_add_car"/>" />
        </form>
    </div>
</inbody>
</body>
</html>
