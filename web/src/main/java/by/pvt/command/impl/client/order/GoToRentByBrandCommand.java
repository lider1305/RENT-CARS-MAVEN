package by.pvt.command.impl.client.order;

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


public class GoToRentByBrandCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) {
        try {
            CarService carService = new CarService();
            List brand = carService.getAllBrands();
            request.setAttribute(ParamSessionAndRequest.REQUEST_GET_BRANDS, brand);
            return Constants.PAGES_RENT_BY_BRAND;
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
