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
import java.sql.SQLException;
import java.util.Date;
import java.util.Locale;

public class RegisterCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            Client client = new Client();
            if (request.getParameter(Constants.CLIENT_NAME).length() != 0) {
                client.setName(request.getParameter(Constants.CLIENT_NAME));
            } else {
                request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_NULL_NAME,
                        MessageManager.getInstance().getValue(Message.PARAM_NULL_NAME, Locale.getDefault()));
                return Constants.PAGES_REGISTRATION;
            }
            if (request.getParameter(Constants.CLIENT_SURNAME).length() != 0) {
                client.setSurName(request.getParameter(Constants.CLIENT_SURNAME));
            } else {
                request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_NULL_SUR_NAME,
                        MessageManager.getInstance().getValue(Message.PARAM_NULL_SUR_NAME, Locale.getDefault()));
                return Constants.PAGES_REGISTRATION;
            }
            if (request.getParameter(Constants.CLIENT_EMAIL).length() != 0) {
                client.setEmail(request.getParameter(Constants.CLIENT_EMAIL));
            } else {
                request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_NULL_MAIL,
                        MessageManager.getInstance().getValue(Message.PARAM_NULL_EMAIL, Locale.getDefault()));
                return Constants.PAGES_REGISTRATION;
            }
            if (request.getParameter(Constants.CLIENT_PASSWORD).length() != 0) {
                client.setPassword(request.getParameter(Constants.CLIENT_PASSWORD));
            } else {
                request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_NULL_PASSWORD,
                        MessageManager.getInstance().getValue(Message.PARAM_NULL_PASSWORD, Locale.getDefault()));
                return Constants.PAGES_REGISTRATION;
            }
            if (request.getParameter(Constants.CLIENT_PHONE).length() != 0) {
                client.setPhone(request.getParameter(Constants.CLIENT_PHONE));
            } else {
                request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_NULL_PHONE,
                        MessageManager.getInstance().getValue(Message.PARAM_NULL_PHONE, Locale.getDefault()));
                return Constants.PAGES_REGISTRATION;
            }
            if (request.getParameter(Constants.CLIENT_PASSPORT).length() != 0) {
                client.setPassport(request.getParameter(Constants.CLIENT_PASSPORT));
            } else {
                request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_NULL_PASSPORT,
                        MessageManager.getInstance().getValue(Message.PARAM_NULL_PASSPORT, Locale.getDefault()));
                return Constants.PAGES_REGISTRATION;
            }
            if (request.getParameter(Constants.ISSUE_DATE).length() != 0) {
               Date startDate = DateFormatUtil.dateFormatterFromStringToDate(request.getParameter(Constants.ISSUE_DATE));
                client.setPassportIssueDate(startDate);
            } else {
                request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_NULL_DATE,
                        MessageManager.getInstance().getValue(Message.PARAM_NULL_DATE, Locale.getDefault()));
                return Constants.PAGES_REGISTRATION;
            }
            if (request.getParameter(Constants.END_DATE).length() != 0) {
                Date endDate = DateFormatUtil.dateFormatterFromStringToDate(request.getParameter(Constants.END_DATE));
                client.setPassportEndDate(endDate);
            } else {
                request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_NULL_DATE,
                        MessageManager.getInstance().getValue(Message.PARAM_NULL_DATE, Locale.getDefault()));
                return Constants.PAGES_REGISTRATION;
            }
            ClientService clientService = new ClientService();
            clientService.create(client);
            request.setAttribute(ParamSessionAndRequest.REQUEST_SUCCESS_REGISTRY,
                    MessageManager.getInstance().getValue(Message.SUCCESS_REGISTRY, Locale.getDefault()));
            return Constants.PAGES_INDEX;
        } catch (ServiceException | SQLException e) {
            request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_DATABASE, ErrorMessages.ERROR_SQL_CONNECT);
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_POST_REQUEST);
            return Constants.PAGES_ERROR;
        }
    }
}
