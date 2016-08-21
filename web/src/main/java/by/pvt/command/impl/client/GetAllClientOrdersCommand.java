package by.pvt.command.impl.client;

import by.pvt.command.Command;
import by.pvt.constans.ErrorMessages;
import by.pvt.constants.Constants;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.entity.Client;
import by.pvt.exception.ServiceException;
import by.pvt.service.impl.OrderService;
import by.pvt.util.SystemLogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;


public class GetAllClientOrdersCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        OrderService orderService = new OrderService();
        HttpSession session = request.getSession();
        try {
            Client sessionClient = (Client) session.getAttribute(ParamSessionAndRequest.SESSION_CLIENT);
            int id = (int) sessionClient.getClientID();
            List orders = orderService.getClientOrders(id);
            session.setAttribute(ParamSessionAndRequest.SESSION_ORDERS, orders);
            return Constants.PAGES_GET_ALL_ORDERS;
        } catch (ServiceException | SQLException e) {
            request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_DATABASE,ErrorMessages.ERROR_SQL_CONNECT);
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_POST_REQUEST);
            return Constants.PAGES_ERROR;
        }
    }
}
