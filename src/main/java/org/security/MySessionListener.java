package org.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Calendar;

/**
 * Created by admin on 21/06/2017.
 */
public class MySessionListener implements HttpSessionListener {


    private static final Logger logger = LoggerFactory.getLogger(MySessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setMaxInactiveInterval(20);


        logger.info("session created at {}", Calendar.getInstance().getTime());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {


        logger.info("session destroyed at {}", Calendar.getInstance().getTime());


    }
}
