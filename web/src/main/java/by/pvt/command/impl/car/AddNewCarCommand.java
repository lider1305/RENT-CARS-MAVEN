package by.pvt.command.impl.car;

import by.pvt.command.Command;
import by.pvt.constans.ErrorMessages;
import by.pvt.constans.Message;
import by.pvt.constants.Constants;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.entity.Car;
import by.pvt.exception.ServiceException;
import by.pvt.service.impl.CarService;
import by.pvt.util.MessageManager;
import by.pvt.util.SystemLogger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Locale;

import static java.lang.Integer.valueOf;


public class AddNewCarCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            Car newCar = new Car();
            newCar.setBrandId(valueOf(request.getParameter(Constants.AUTO_BRAND)));
            if (request.getParameter(Constants.AUTO_MODEL).length() != 0) {
                newCar.setModel(request.getParameter(Constants.AUTO_MODEL));
            } else {
                request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_NULL_MODEL,
                        MessageManager.getInstance().getValue(Message.PARAM_NULL_MODEL, Locale.getDefault()));
                return Constants.PAGES_ADD_CAR;
            }
            newCar.setBodyTypeId(valueOf(request.getParameter(Constants.AUTO_BODY_TYPE)));
            newCar.setEngineTypeId(valueOf(request.getParameter(Constants.AUTO_ENGINE_TYPE)));
            newCar.setTransmissionTypeId(valueOf(request.getParameter(Constants.AUTO_TRANSMISSION_TYPE)));
            newCar.setYearOfManufacture(valueOf(request.getParameter(Constants.AUTO_YEAR_MANUFACTURE)));
            newCar.setAmount(valueOf(request.getParameter(Constants.AUTO_AMOUNT_PER_DAY)));

            CarService carService = new CarService();
            carService.create(newCar);
            request.setAttribute(ParamSessionAndRequest.REQUEST_SUCCESS_ADD_NEW_CAR,
                    MessageManager.getInstance().getValue(Message.SUCCESS_ADD_NEW_CAR, Locale.getDefault()));
            return Constants.PAGES_ADD_CAR;
        } catch (ServiceException | SQLException e) {
            request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_DATABASE, ErrorMessages.ERROR_SQL_CONNECT);
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_POST_REQUEST);
            return Constants.PAGES_ERROR;
        }
    }
}
