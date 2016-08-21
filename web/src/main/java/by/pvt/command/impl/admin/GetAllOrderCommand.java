package by.pvt.command.impl.admin;

import by.pvt.command.Command;
import by.pvt.constans.ErrorMessages;
import by.pvt.constants.Constants;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.util.SystemLogger;

import javax.servlet.http.HttpServletRequest;


public class GetAllOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        try{
            return Constants.PAGES_ALL_ORDERS;
        }catch (IllegalArgumentException | NullPointerException e){
            request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_404, ErrorMessages.ERROR_404);
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_404);
            return Constants.PAGES_ERROR;
        }
    }
}
