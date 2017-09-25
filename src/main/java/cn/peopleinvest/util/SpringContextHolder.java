package cn.peopleinvest.util;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;
    private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);


    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }


    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }


    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }


    public static void clearHolder() {
        logger.debug("���SpringContextHolder�е�ApplicationContext:"
                + applicationContext);
        applicationContext = null;
    }


    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {

    }


    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }


    private static void assertContextInjected() {
        Validate.validState(applicationContext == null, "applicationContextΪ�գ�����applicationContext.xml����SpringApplicationContext");
    }

}

