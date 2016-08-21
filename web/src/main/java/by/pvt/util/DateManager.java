package by.pvt.util;

import by.pvt.constans.ConstantsValues;
import by.pvt.entity.Client;

import javax.servlet.http.HttpSession;

/**
 * Created by user1 on 03.08.2016.
 */
public class DateManager {
    /**
     * метод возврашает дату в строчном представлении
     *
     * @param client  объект Клиента
     * @param session Объект Сессии
     */
    public static void writeDate(Client client, HttpSession session) {
        String strDate = DateFormatUtil.dateFormatterFromDateToString(client.getPassportIssueDate());
        String endDate = DateFormatUtil.dateFormatterFromDateToString(client.getPassportEndDate());
        session.setAttribute(ConstantsValues.START_DATE, strDate);
        session.setAttribute(ConstantsValues.END_DATE, endDate);
    }
}
