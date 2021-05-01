package com.simpo.tracker.frame;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

@Component
public class InitFrameListener implements ApplicationContextAware, ServletContextAware,
        InitializingBean, ApplicationListener<ContextRefreshedEvent> {

    private static Logger logger = Logger.getLogger(InitFrameListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent cre) {
        // TODO Auto-generated method stub
        System.out.println("1111---"+cre.toString());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        //System.out.println("2222---");
    }

    @Override
    public void setServletContext(ServletContext sc) {
        // TODO Auto-generated method stub
        //System.out.println("3333---"+sc.toString());
    }

    @Override
    public void setApplicationContext(ApplicationContext ac)
            throws BeansException {
        // TODO Auto-generated method stub

    }

}
