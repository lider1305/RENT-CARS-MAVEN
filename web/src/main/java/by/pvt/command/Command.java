package by.pvt.command;

import javax.servlet.http.HttpServletRequest;

/**
 * перенаправляет на другой  запрос
 */
public interface Command {
    String execute(HttpServletRequest request);
}

