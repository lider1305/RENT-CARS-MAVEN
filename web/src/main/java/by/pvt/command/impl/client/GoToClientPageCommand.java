package by.pvt.command.impl.client;

import by.pvt.command.Command;
import by.pvt.constans.ErrorMessages;
import by.pvt.constants.Constants;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.entity.Client;
import by.pvt.util.SystemLogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class GoToClientPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Client client = (Client) session.getAttribute(ParamSessionAndRequest.SESSION_CLIENT);
            if (client.getRole().equalsIgnoreCase("admin")) {
                return Constants.PAGES_ADMIN;
            }
            return Constants.PAGES_CLIENT;
        } catch (IllegalArgumentException | NullPointerException e) {
            request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_404, ErrorMessages.ERROR_404);
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_404);
            return Constants.PAGES_ERROR;
        }
    }
}
