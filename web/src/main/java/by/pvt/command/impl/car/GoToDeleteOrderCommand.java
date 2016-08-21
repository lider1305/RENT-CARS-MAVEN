package by.pvt.command.impl.car;

import by.pvt.command.Command;
import by.pvt.constans.ErrorMessages;
import by.pvt.constants.Constants;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.util.SystemLogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GoToDeleteOrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            session.getAttribute(ParamSessionAndRequest.SESSION_ORDERS);
            return Constants.PAGES_DELETE_ORDER;
        } catch (IllegalArgumentException | NullPointerException e){
            request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_404, ErrorMessages.ERROR_404);
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_404);
            return Constants.PAGES_ERROR;
        }
    }
}
