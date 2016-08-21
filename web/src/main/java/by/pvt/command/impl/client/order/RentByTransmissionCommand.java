package by.pvt.command.impl.client.order;

import by.pvt.command.Command;
import by.pvt.constans.ErrorMessages;
import by.pvt.constans.Message;
import by.pvt.constants.Constants;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.exception.ServiceException;
import by.pvt.service.impl.CarService;
import by.pvt.util.MessageManager;
import by.pvt.util.SystemLogger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;


public class RentByTransmissionCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            CarService cars = new CarService();
            if (request.getParameter(Constants.TRANSMISSION) != null) {
                long transmissionId = Long.parseLong(request.getParameter(Constants.TRANSMISSION));
                List allCar = cars.getCarByTransmission(transmissionId);
                request.setAttribute(ParamSessionAndRequest.REQUEST_GET_CARS_TRANSMISSION, allCar);
                return Constants.PAGES_RENT_BY_TRANSMISSION2;
            }
            request.setAttribute(ParamSessionAndRequest.REQUEST_WRONG_PARAM, MessageManager.getInstance().getValue(Message.PARAM_NO_PARAM, Locale.getDefault()));
            return Constants.PAGES_GET_CAR;
        } catch (ServiceException | SQLException e) {
            request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_DATABASE,ErrorMessages.ERROR_SQL_CONNECT);
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_POST_REQUEST);
            return Constants.PAGES_ERROR;
        }
    }
}
