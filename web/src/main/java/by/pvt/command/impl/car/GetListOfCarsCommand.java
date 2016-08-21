package by.pvt.command.impl.car;

import by.pvt.command.Command;
import by.pvt.constans.ErrorMessages;
import by.pvt.constants.Constants;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.exception.ServiceException;
import by.pvt.service.impl.CarService;
import by.pvt.util.SystemLogger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;


public class GetListOfCarsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            CarService cars = new CarService();
            List allCar = cars.getAll();
            request.setAttribute(ParamSessionAndRequest.REQUEST_GET_CARS, allCar);
            return Constants.PAGES_RENT_BY_ALL_CARS;
        } catch (ServiceException | SQLException e) {
            request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_DATABASE, ErrorMessages.ERROR_SQL_CONNECT);
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_POST_REQUEST);
            return Constants.PAGES_ERROR;
        }
    }
}
