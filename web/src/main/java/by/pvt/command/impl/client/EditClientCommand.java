package by.pvt.command.impl.client;

import by.pvt.command.Command;
import by.pvt.constans.ErrorMessages;
import by.pvt.constans.Message;
import by.pvt.constants.Constants;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.entity.Client;
import by.pvt.exception.ServiceException;
import by.pvt.service.impl.ClientService;
import by.pvt.util.DateFormatUtil;
import by.pvt.util.MessageManager;
import by.pvt.util.SystemLogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Date;
import java.util.Locale;

/**
 * Метод для изменения данных аккаунта клиента
 */

public class EditClientCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        ClientService clientService = new ClientService();
        try {
            HttpSession session = request.getSession();
            Client sessionClient = (Client) session.getAttribute(ParamSessionAndRequest.SESSION_CLIENT);
            long id = sessionClient.getClientID();
            //формируем объект клиента
            Client client = (Client) clientService.getId(id);
            client.setName(request.getParameter(Constants.CLIENT_NAME));
            client.setSurName(request.getParameter(Constants.CLIENT_SURNAME));
            client.setEmail(request.getParameter(Constants.CLIENT_EMAIL));
            client.setPassword(request.getParameter(Constants.CLIENT_PASSWORD));
            client.setPhone(request.getParameter(Constants.CLIENT_PHONE));
            client.setPassport(request.getParameter(Constants.CLIENT_PASSPORT));
            if (request.getParameter(Constants.ISSUE_DATE).length() != 0) {
                Date startDate = DateFormatUtil.dateFormatterFromStringToDate(request.getParameter(Constants.ISSUE_DATE));
                client.setPassportIssueDate(startDate);
            } else {
                request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_NULL_DATE,
                        MessageManager.getInstance().getValue(Message.PARAM_NULL_DATE, Locale.getDefault()));
                return Constants.PAGES_EDIT_CLIENT;
            }
            if (request.getParameter(Constants.END_DATE).length() != 0) {
                Date endDate = DateFormatUtil.dateFormatterFromStringToDate(request.getParameter(Constants.END_DATE));
                client.setPassportEndDate(endDate);
            } else {
                request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_NULL_DATE,
                        MessageManager.getInstance().getValue(Message.PARAM_NULL_DATE, Locale.getDefault()));
                return Constants.PAGES_EDIT_CLIENT;
            }
            clientService.update(client);

            //заносим новые параметры в сессию
            Client newClient = clientService.enterToSystem(sessionClient.getEmail(), sessionClient.getPassword());
            session.setAttribute(ParamSessionAndRequest.SESSION_CLIENT, newClient);
            return Constants.PAGES_CLIENT;
        } catch (ServiceException | SQLException e) {
            request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_DATABASE,ErrorMessages.ERROR_SQL_CONNECT);
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_POST_REQUEST);
            return Constants.PAGES_ERROR;
        }
    }
}
