package by.pvt.command.impl.client.order;

import by.pvt.command.Command;
import by.pvt.constans.Message;
import by.pvt.constants.Constants;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.exception.ServiceException;
import by.pvt.service.impl.CarService;
import by.pvt.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;


public class RentByBrandCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) {
        try {
            CarService cars = new CarService();
            int carId = Integer.parseInt(request.getParameter(Constants.BRAND_ID));

            List allCar = cars.getCarByBrand(carId);
            if(allCar.size() == 0){
                request.setAttribute(ParamSessionAndRequest.REQUEST_MESSAGE_NULL_BRAND, MessageManager.getInstance().getValue(Message.PARAM_NULL_BRAND, Locale.getDefault()));
                return Constants.PAGES_GET_CAR;
            }else {
                request.setAttribute(ParamSessionAndRequest.REQUEST_GET_CARS_BRANDS, allCar);
                return Constants.PAGES_RENT_BY_BRAND2;
            }

        } catch (ServiceException | SQLException e) {
            e.printStackTrace();
            return Constants.PAGES_ERROR;
        }
    }
}
