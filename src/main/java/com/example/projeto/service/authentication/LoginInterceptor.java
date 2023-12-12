package com.example.projeto.service.authentication;

import jakarta.servlet.http.*;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import com.example.projeto.service.CookieService;

@Component
public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        try{
            if(CookieService.getCookie(request, "userId") != null){
                return true;
            }
        }
        catch(Exception error) {
            response.sendRedirect("/login");
        }
        response.sendRedirect("/login");
        return false;
    }

}
