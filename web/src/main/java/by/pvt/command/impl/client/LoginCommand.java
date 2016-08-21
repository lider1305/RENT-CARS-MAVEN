package by.pvt.command.impl.client;

import by.pvt.command.Command;
import by.pvt.constans.ErrorMessages;
import by.pvt.constans.Message;
import by.pvt.constants.Constants;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.entity.Client;
import by.pvt.exception.ServiceException;
import by.pvt.service.impl.ClientService;
import by.pvt.util.DateManager;
import by.pvt.util.MessageManager;
import by.pvt.util.SystemLogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Locale;

public class LoginCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) {
        try {
            ClientService clientService = new ClientService();
            String mail = request.getParameter(Constants.CLIENT_EMAIL);
            String password = request.getParameter(Constants.CLIENT_PASSWORD);
            Client client = clientService.enterToSystem(mail, password);
            HttpSession session = request.getSession();

            //вывод даты на страницу клиента в нужном формате
            DateManager.writeDate(client, session);

            if (client.getRole().equalsIgnoreCase("admin") && mail.equalsIgnoreCase(client.getEmail()) && password.equalsIgnoreCase(client.getPassword())) {
                session.setAttribute(ParamSessionAndRequest.SESSION_CLIENT, client);
                return Constants.PAGES_ADMIN;
            }
            if (client.getRole().equalsIgnoreCase("user") && mail.equalsIgnoreCase(client.getEmail()) && password.equalsIgnoreCase(client.getPassword())) {
                session.setAttribute(ParamSessionAndRequest.SESSION_CLIENT, client);
                return Constants.PAGES_CLIENT;
            } else {
                SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.WRONG_PASSWORD_OR_LOGIN);
                request.setAttribute(ParamSessionAndRequest.REQUEST_ERROR, MessageManager.getInstance().getValue(Message.ERROR_LOGIN, Locale.getDefault()));
                return Constants.PAGES_INDEX;
            }
        } catch (ServiceException | SQLException e) {
            request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_DATABASE, ErrorMessages.ERROR_SQL_CONNECT);
            SystemLogger.getInstance().setLogger(getClass(),ErrorMessages.ERROR_POST_REQUEST);
            return Constants.PAGES_ERROR;
        }
    }
}
