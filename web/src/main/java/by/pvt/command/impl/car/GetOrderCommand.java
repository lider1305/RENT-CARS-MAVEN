package by.pvt.command.impl.car;

import by.pvt.command.Command;
import by.pvt.constans.ErrorMessages;
import by.pvt.constans.Message;
import by.pvt.constants.Constants;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.entity.Client;
import by.pvt.entity.Order;
import by.pvt.exception.ServiceException;
import by.pvt.service.impl.CarService;
import by.pvt.service.impl.OrderService;
import by.pvt.util.DateFormatUtil;
import by.pvt.util.MessageManager;
import by.pvt.util.SystemLogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class GetOrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            Order order = new Order();
            HttpSession session = request.getSession();
            Client sessionClient = (Client) session.getAttribute(ParamSessionAndRequest.SESSION_CLIENT);
            int id = (int) sessionClient.getClientID();
            if (request.getParameter(Constants.CAR_ID_FOR_ORDER) != null) {
                int carId = Integer.parseInt(request.getParameter(Constants.CAR_ID_FOR_ORDER));
                order.setClientID(id);
                Date today = new Date();
                if (request.getParameter(Constants.ISSUE_DATE).length() == 0) {
                    request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_NULL_DATE,
                            MessageManager.getInstance().getValue(Message.PARAM_NULL_DATE, Locale.getDefault()));
                    return Constants.PAGES_GET_CAR;
                }
                if (request.getParameter(Constants.ISSUE_DATE).length() == 0) {
                    request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_NULL_DATE,
                            MessageManager.getInstance().getValue(Message.PARAM_NULL_DATE, Locale.getDefault()));
                    return Constants.PAGES_GET_CAR;
                }
                CarService carService = new CarService();
                long start = DateFormatUtil.dateFormatterFromString(request.getParameter(Constants.ISSUE_DATE));
                long end = DateFormatUtil.dateFormatterFromString(request.getParameter(Constants.END_DATE));
                if(start <today.getTime()){
                    request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_WRONG_DATE,
                            MessageManager.getInstance().getValue(Message.PARAM_WRONG_DATE, Locale.getDefault()));
                    return Constants.PAGES_GET_CAR;
                }
                if(end < start*24*60*60*1000){
                    request.setAttribute(ParamSessionAndRequest.REQUEST_EXCEPTION_WRONG_DATE_END,
                            MessageManager.getInstance().getValue(Message.PARAM_WRONG_DATE_END, Locale.getDefault()));
                    return Constants.PAGES_GET_CAR;
                }
                List rentCar = carService.getAllRentCarForDate(start,end);
                for (Object aRentCar : rentCar) {
                    if (carId == (int) aRentCar) {
                        request.setAttribute(ParamSessionAndRequest.REQUEST_CAR_IS_RENT, MessageManager.getInstance().getValue(Message.PARAM_CAR_IS_RENT, Locale.getDefault()));
                        return Constants.PAGES_GET_CAR;
                    }
                }
                order.setCarId(carId);
                order.setStartDate(DateFormatUtil.dateFormatterFromStringToDate(request.getParameter(Constants.ISSUE_DATE)));
                order.setEndDate(DateFormatUtil.dateFormatterFromStringToDate(request.getParameter(Constants.END_DATE)));
                order.setMessage(request.getParameter(Constants.MESSAGE_FOR_ORDER));
                OrderService orderService = new OrderService();
                orderService.create(order);
                request.setAttribute(ParamSessionAndRequest.REQUEST_SUCCESS_MESSAGE, MessageManager.getInstance().getValue(Message.SUCCESS_ORDER, Locale.getDefault()));
                return Constants.PAGES_SUCCESS;
            }
            request.setAttribute(ParamSessionAndRequest.REQUEST_WRONG_PARAM, MessageManager.getInstance().getValue(Message.PARAM_NO_CHOOSEN, Locale.getDefault()));
            return Constants.PAGES_GET_CAR;

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
