package by.pvt.util;


import by.pvt.constans.Settings;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private static MessageManager instance;

    private MessageManager() {
    }

    public static synchronized MessageManager getInstance() {
        if (instance == null) {
            instance = new MessageManager();
        }
        return instance;
    }

    public String getValue(String key, Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Settings.RESOURCE_WEB, locale);
        return resourceBundle.getString(key);
    }
}
