package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class IdProvider {

    Logger logger = Logger.getLogger(IdProvider.class);

    public String provideId(Book book) {
        return this.hashCode() + "_" + book.hashCode();
    }

//    private void initIdProvider() {
//        logger.info("Initializing IdProvider");
//    }
//
//    private void destroyIdProvider() {
//        logger.info("Destroying IdProvider");
//    }
//
//    private void defaultInit() {
//        logger.info("Default INIT");
//    }
//
//    private void defaultDestroy() {
//        logger.info("Default DESTROY");
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        logger.info("Initializing IdProvider afterPropertiesSet");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        logger.info("Initializing IdProvider destroy");
//    }
//
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        logger.info("postProcessBeforeInitialization invoked by bean: " + beanName);
//        return null;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        logger.info("postProcessAfterInitialization invoked by bean: " + beanName);
//        return null;
//    }

}
