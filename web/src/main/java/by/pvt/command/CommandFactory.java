package by.pvt.command;

import by.pvt.command.impl.admin.*;
import by.pvt.command.impl.car.*;
import by.pvt.command.impl.client.*;
import by.pvt.command.impl.client.order.*;
import by.pvt.constans.ErrorMessages;
import by.pvt.constants.Commands;

public class CommandFactory {
    public static Command getCommand(String page) {
        Commands currentCommand = Commands.valueOf(page.toUpperCase());
        switch (currentCommand) {
            case REGISTRY:
                return new RegisterCommand();
            case LOGIN:
                return new LoginCommand();
            case FORGOT:
                return new ForgotPasswordCommand();
            case EDIT_CLIENT:
                return new EditClientCommand();
            case LOGOUT:
                return new LogoutCommand();
            case GO_TO_REGISTRATION:
                return new GoToRegistrationCommand();
            case FORGOT_PASSWORD:
                return new GoToForgotPasswordCommand();
            case GET_ALL_CARS:
                return new GoToGetAllCarsCommand();
            case GET_CAR:
                return new GoToGetCarCommand();
            case CONTACTS:
                return new GoToContactCommand();
            case ALL_CAR:
                return new AllCarsCommand();
            case GO_TO_ORDERS:
                return new GoToAllOrdersCommand();
            case LIST_OF_ALL_CARS:
                return new GetListOfCarsCommand();
            case DELETE_ORDER:
                return new DeleteOrderCommand();
            case CAR:
                return new GetOrderCommand();
            case GO_TO_EDIT_CLIENT:
                return new GoToEditClientCommand();
            case GO_TO_DELETE_ORDER:
                return new GoToDeleteOrderCommand();
            case GO_TO_CLIENT_PAGE:
                return new GoToClientPageCommand();
            case GET_ALL_CLIENT_ORDERS:
                return new GetAllClientOrdersCommand();
            case SET_LOCALE_RU:
                return new SetRuLocaleCommand();
            case SET_LOCALE_EN:
                return new SetEnLocaleCommand();
            case GO_TO_ADMIN_PAGE:
                return new GoToAdminCommand();
            case GO_GET_ALL_ORDERS:
                return new GetAllOrderCommand();
            case GO_GET_ALL_USERS:
                return new GoToGetAllUsersCommand();
            case GO_ADD_NEW_CAR:
                return new GoToAddNewCarCommand();
            case GET_ALL_ORDERS:
                return new AllOrdersAdminCommand();
            case GET_ALL_USERS:
                return new GetAllUsersCommand();
            case ADD_NEW_CAR:
                return new AddNewCarCommand();
            //фильтры
            case GO_TO_RENT_ALL_CARS:
                return new GoToRentAllCarsCommand();
            case GO_TO_RENT_CAR_BY_BRAND:
                return new GoToRentByBrandCommand();
            case GO_TO_RENT_CAR_BY_TRANSMISSION:
                return new GoToRentByTransmissionCommand();
            case RENT_CAR_BY_TRANSMISSION:
                return new RentByTransmissionCommand();
            case GO_TO_RENT_CAR_BY_PRICE:
                return new GoToRentByPriceCommand();
            case RENT_CAR_BY_BRAND:
                return new RentByBrandCommand();

            default:
                throw new IllegalArgumentException(ErrorMessages.ILLEGAL_ARGUMENT);
        }
    }
}
