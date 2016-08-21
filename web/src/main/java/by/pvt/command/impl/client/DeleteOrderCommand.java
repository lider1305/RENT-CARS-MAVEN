package by.pvt.command.impl.client;

import by.pvt.command.Command;
import by.pvt.constans.ErrorMessages;
import by.pvt.constants.Constants;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.exception.ServiceException;
import by.pvt.service.impl.OrderService;
import by.pvt.util.SystemLogger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


public class DeleteOrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            long orderId = Long.parseLong(request.getParameter(Constants.ORDER_ID));
            OrderService orderService = new OrderService();
            orderService.delete(orderId);
            return Constants.PAGES_GET_ALL_ORDERS;
        } catch (ServiceException | SQLException e) {
            request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_DATABASE, ErrorMessages.ERROR_SQL_CONNECT);
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_POST_REQUEST);
            return Constants.PAGES_ERROR;
        }
    }
}
