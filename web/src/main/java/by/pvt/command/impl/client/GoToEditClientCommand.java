package by.pvt.command.impl.client;

import by.pvt.constants.Constants;
import by.pvt.constans.ErrorMessages;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.util.SystemLogger;
import by.pvt.command.Command;

import javax.servlet.http.HttpServletRequest;


public class GoToEditClientCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            return Constants.PAGES_EDIT_CLIENT;
        } catch (IllegalArgumentException | NullPointerException e) {
            request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_404, ErrorMessages.ERROR_404);
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_404);
            return Constants.PAGES_ERROR;
        }
    }
}
