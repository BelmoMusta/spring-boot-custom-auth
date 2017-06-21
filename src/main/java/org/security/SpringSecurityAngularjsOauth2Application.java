package org.security;

import org.security.business.RouteBusiness;
import org.security.config.HasRole;
import org.security.entities.Route;
import org.security.service.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSessionListener;
import java.lang.reflect.Method;

@SpringBootApplication

public class SpringSecurityAngularjsOauth2Application implements CommandLineRunner {






    @Autowired
    RouteBusiness routeBusiness;


    @Bean
    public HttpSessionListener httpSessionListener(){
        // MySessionListener should implement javax.servlet.http.HttpSessionListener
        return new MySessionListener();}

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityAngularjsOauth2Application.class, args);


    }


    @Override
    public void run(String... strings) throws Exception {


        Class cls = UserRestService.class;

        Method[] methods = cls.getMethods();

        for (Method method : methods) {

            method.setAccessible(true);


            if (method.isAnnotationPresent(RequestMapping.class)) {

                if (method.isAnnotationPresent(HasRole.class)) {

                    RequestMapping requestMapping = method.getDeclaredAnnotation(RequestMapping.class);
                    HasRole hasRole = method.getDeclaredAnnotation(HasRole.class);


                    String role = hasRole.value();
                    String[] value = requestMapping.value();
                    for (String uri : value) {

                        Route route = new Route();

                        route.setPermission(role);
                        route.setUri(uri);
                        routeBusiness.addRoute(route);

                    }


                }

            }


        }


    }
}
