package by.pvt.filters;


import by.pvt.constans.ErrorMessages;
import by.pvt.constants.Commands;
import by.pvt.constants.Constants;
import by.pvt.constants.ParamSessionAndRequest;
import by.pvt.entity.Client;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {

   @Override
   public void init(FilterConfig filterConfig) throws ServletException {
   }

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       HttpServletRequest httpRequest = (HttpServletRequest) request;
       HttpServletResponse httpResponse = (HttpServletResponse) response;
       HttpSession session = httpRequest.getSession();

       try {
           String page = request.getParameter("page");
           Commands currentCommand = Commands.valueOf(page.toUpperCase());
           Client clientSession = (Client) session.getAttribute("client");
           if(currentCommand== Commands.LOGIN){
               chain.doFilter(request,response);
           }else if(currentCommand== Commands.REGISTRY){
               chain.doFilter(request,response);
           }else if (currentCommand== Commands.GO_TO_REGISTRATION){
               chain.doFilter(request,response);
           }else if (currentCommand== Commands.FORGOT){
               chain.doFilter(request,response);
           }
           else if (currentCommand== Commands.FORGOT_PASSWORD){
               chain.doFilter(request,response);
           }
           else if (currentCommand== Commands.CONTACTS){
               chain.doFilter(request,response);
           }else if (currentCommand== Commands.GET_ALL_CARS){
               chain.doFilter(request,response);
           }else if (currentCommand== Commands.GET_CAR){
               chain.doFilter(request,response);
           }else if (currentCommand== Commands.ALL_CAR){
               chain.doFilter(request,response);
           }
           else {
               try {
                   if (clientSession.getClientID() > 0) {
                       chain.doFilter(request, response);
                   }
               }catch (NullPointerException e){
                   session.invalidate();
                   request.setAttribute(ParamSessionAndRequest.REQUEST_SESSION_CLOSE, ErrorMessages.SESSION_CLOSE);
                   RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.PAGES_INDEX);
                   dispatcher.forward(httpRequest, httpResponse);
               }
           }
       } catch (IllegalArgumentException |NullPointerException e) {
           chain.doFilter(request,response);
       }
   }

   @Override
   public void destroy() {

   }
}
