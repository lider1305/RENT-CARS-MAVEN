package by.pvt.controller;

import by.pvt.command.Command;
import by.pvt.command.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performAction(request, response);
    }

    private void performAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = request.getParameter("page");

        if (page != null) {
            Command command = CommandFactory.getCommand(page);
            String nextPage = command.execute(request);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
            requestDispatcher.forward(request, response);
        } else {
            throw new IllegalArgumentException("Не верный параметр запроса, проверьте данные");

        }
    }
}
