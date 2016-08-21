package by.pvt.filters;

import by.pvt.constants.Constants;
import by.pvt.entity.Client;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public  class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();

        Client sessionClient = (Client) session.getAttribute("client");
        if (sessionClient != null ) {
            chain.doFilter(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.PAGES_INDEX);
            dispatcher.forward(httpRequest, httpResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
