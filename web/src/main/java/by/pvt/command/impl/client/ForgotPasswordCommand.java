package by.pvt.command.impl.client;

import by.pvt.command.Command;
import by.pvt.constans.ErrorMessages;
import by.pvt.constants.Constants;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.exception.ServiceException;
import by.pvt.service.impl.ClientService;
import by.pvt.util.SystemLogger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


public class ForgotPasswordCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) {
        ClientService clientService = new ClientService();
        try {
            String mail = request.getParameter(Constants.CLIENT_EMAIL);
            String password = clientService.forgotPassword(mail);
            request.setAttribute(ParamSessionAndRequest.REQUEST_PASSWORD, password);
            return Constants.PAGES_FORGOT_PASSWORD;
        } catch (ServiceException | SQLException e) {
            request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_DATABASE, ErrorMessages.ERROR_SQL_CONNECT);
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_POST_REQUEST);
            return Constants.PAGES_ERROR;
        }
    }
}
