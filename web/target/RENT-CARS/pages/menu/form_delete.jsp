
<form method="POST" action="Controller">
    <input name="page" type="hidden" value="delete_order"/>
    <td width="15%"><input type="hidden" id="orderId" name="orderId" value="${order.orderId}"/><c:out  value="${order.orderId}"/></td>
    <td width="15%"><c:out value="${order.brand}"/></td>
    <td width="10%"><c:out value="${order.model}"/></td>
    <td width="10%"><fmt:formatDate value="${order.startDate}" /></td>
    <td width="10%"><fmt:formatDate value="${order.endDate}"/></td>
    <td width="20%"><c:out value="${order.message}"/></td>
    <td width="10%"><c:out value="${order.status}"/></td>
    <td width="10%"><input type="submit" value="DELETE"/></td>
</form>
