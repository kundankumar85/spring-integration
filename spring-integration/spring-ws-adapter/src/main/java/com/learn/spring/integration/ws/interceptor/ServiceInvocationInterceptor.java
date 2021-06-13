package com.learn.spring.integration.ws.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.context.MessageContext;

public class ServiceInvocationInterceptor implements org.springframework.ws.client.support.interceptor.ClientInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
        logger.info("request handled");
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
        logger.info("response handled");
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
        logger.info("error handled");
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Exception e) throws WebServiceClientException {
        logger.info("Completed....");
    }
}
