package by.pvt.command;


import by.pvt.constans.ErrorMessages;
import by.pvt.constants.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

class GoToContactCommand implements Command {
    private static Logger logger = Logger.getLogger(GoToContactCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        try {
            return Constants.PAGES_CONTACT;
        } catch (Exception e) {
            logger.error(ErrorMessages.ERROR_RESPONSE + e);
            return Constants.PAGES_ERROR;
        }
    }
}
