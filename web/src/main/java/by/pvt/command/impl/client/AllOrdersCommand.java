package by.pvt.command.impl.client;

import by.pvt.command.Command;
import by.pvt.constans.ErrorMessages;
import by.pvt.constants.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class AllOrdersCommand implements Command {
    private static Logger logger = Logger.getLogger(AllOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        try {
            return Constants.PAGES_GET_ALL_ORDERS;
        } catch (Exception e) {
            logger.error(ErrorMessages.ERROR_RESPONSE + e);
            return Constants.PAGES_ERROR;
        }
    }
}
