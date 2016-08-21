package by.pvt.command.impl.client.order;

import by.pvt.constants.Constants;
import by.pvt.constans.ErrorMessages;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.exception.ServiceException;
import by.pvt.service.impl.CarService;
import by.pvt.util.SystemLogger;
import by.pvt.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;


public class GoToRentByTransmissionCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            CarService carService = new CarService();
            List transmissions = carService.getAllTransmissions();
            request.setAttribute(ParamSessionAndRequest.REQUEST_GET_TRANSMISSION, transmissions);
            return Constants.PAGES_RENT_BY_TRANSMISSION;
        } catch (ServiceException | SQLException e) {
            request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_DATABASE, ErrorMessages.ERROR_SQL_CONNECT);
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_POST_REQUEST);
            return Constants.PAGES_ERROR;
        }catch (IllegalArgumentException | NullPointerException e) {
            request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_404, ErrorMessages.ERROR_404);
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_404);
            return Constants.PAGES_ERROR;
        }
    }
}
