package com.chema.tienda.relojes.interceptores;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class InterceptorAdmin implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String passAdmin="";

        if (request.getParameter("pass-login-admin") != null ) {
            passAdmin = request.getParameter("pass-login-admin");
            if (passAdmin.equals("123")) {
                // Guardo en una variable de sesion que el user actual es un admin
                request.getSession().setAttribute("admin", "ok");
            }
        }

        //System.out.println("Hola desde el interceptor");
        if(request.getRequestURI().contains("/admin/")){
            // MIro si la variable de sesion admin existe y vale ok,
            // eso quiere decir que el user introdujo correctamente
            // el pass en el form de login_admin

            if ( !
                    ( request.getSession().getAttribute("admin") != null
                            && request.getSession().getAttribute("admin").equals("ok") )
            ) {

                // Si en sesion no esta este admin ok, redirigo al form de login
                response.sendRedirect("../loginAdmin");
                return false;
            }

        }

        return true;

    }


}
