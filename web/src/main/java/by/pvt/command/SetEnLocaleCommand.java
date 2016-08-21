package by.pvt.command;


import by.pvt.constans.ErrorMessages;
import by.pvt.constans.SiteLocale;
import by.pvt.constants.Constants;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.entity.Client;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

class SetEnLocaleCommand implements Command {
    private static Logger logger = Logger.getLogger(SetEnLocaleCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        try {
            String locale = SiteLocale.LOCALE_EN;
            Locale.setDefault(new Locale(locale));
            HttpSession session = request.getSession();
            session.setAttribute(ParamSessionAndRequest.SESSION_LOCALE, locale);
            Client client=(Client) session.getAttribute(ParamSessionAndRequest.SESSION_CLIENT);
            if(client.getRole().equalsIgnoreCase("admin")){
                return Constants.PAGES_ADMIN;
            }else{
                return Constants.PAGES_CLIENT;
            }
        } catch (Exception e) {
            logger.error(ErrorMessages.ERROR_RESPONSE + e);
            return Constants.PAGES_ERROR;
        }
    }
}
