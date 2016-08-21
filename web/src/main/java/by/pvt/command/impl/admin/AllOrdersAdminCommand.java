package by.pvt.command.impl.admin;


import by.pvt.command.Command;
import by.pvt.constans.ErrorMessages;
import by.pvt.constants.Constants;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.exception.ServiceException;
import by.pvt.service.impl.OrderService;
import by.pvt.util.SystemLogger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class AllOrdersAdminCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        try {
            OrderService order = new OrderService();
            List orders = order.getAll();
            request.setAttribute(ParamSessionAndRequest.REQUEST_ALL_ORDERS_ADMIN, orders);
            return Constants.PAGES_ALL_ORDERS;
        } catch (SQLException | ServiceException e) {
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_SQL_CONNECT);
            request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_DATABASE, ErrorMessages.ERROR_RESPONSE);
            return Constants.PAGES_ERROR;
        }

    }
}
