package org.security.interceptor;


import org.security.business.RouteBusiness;
import org.security.business.TokenBusiness;
import org.security.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by admin on 20/06/2017.
 */
@Component
public class MustaHandlerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    TokenBusiness tokenBusiness;
    @Autowired
    RouteBusiness routeBusiness;
   // @Value("${token.route.uri}")
    private String  tokenRouteUri;

    private static final Logger logger = LoggerFactory.getLogger(MustaHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {


        String uri = request.getRequestURI();

        HttpSession session = request.getSession();


        String token = request.getParameter("token");
        logger.info("TOKEN FROM PARAMS {}", token);


        if (token == null || token.trim().equals(""))
            token = (String) session.getAttribute("TOKEN");
        logger.info("TOKEN FROM ATTRIBUTS {}", token);


        session.setAttribute("TOKEN", token);
        logger.info("TOKEN FROM ATTRIBUTS AFTER ALTERING{}", token);


        logger.info("URI {}", request.getRequestURI());
        logger.info("TOKEN {}", token);

        User user = tokenBusiness.getUser(token);


        if (user == null) {
            response.sendRedirect("/api/not_found/");
            return false;
        }


        String userRole = user.getRole().getRole().trim();
        logger.info("userRole {}", userRole);

        String permissionForThisUri = routeBusiness.getRole(uri).trim();

        logger.info("permissionForThisUri {}", permissionForThisUri);

        if (userRole.equals(permissionForThisUri) || permissionForThisUri.trim().isEmpty()) {
            return true;
        }


        logger.info("arrived here  {}", 90);

        return false;


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object object, ModelAndView model)
            throws Exception {
        System.out.println("_________________________________________");
        System.out.println("In postHandle request processing "
                + "completed by @RestController");
        System.out.println("_________________________________________");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object object, Exception arg3)
            throws Exception {
        System.out.println("________________________________________");
        System.out.println("In afterCompletion Request Completed");
        System.out.println("________________________________________");
    }


}





