package by.pvt.command.impl.admin;

import by.pvt.command.Command;
import by.pvt.constans.ErrorMessages;
import by.pvt.constants.Constants;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.exception.ServiceException;
import by.pvt.service.impl.ClientService;
import by.pvt.util.SystemLogger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;


public class GetAllUsersCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            ClientService user =new ClientService();
            List users = user.getAll();
            request.setAttribute(ParamSessionAndRequest.REQUEST_GET_ALL_USERS,users);
            return Constants.PAGES_ALL_USERS;
        } catch (ServiceException | SQLException e) {
            request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_DATABASE, ErrorMessages.ERROR_SQL_CONNECT);
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_POST_REQUEST);
            return Constants.PAGES_ERROR;
        }

    }
}
