package com.learn.spring.integration.jmx.adapter.filter;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("maxItemFilter")
public class MaxItemFilter {
    private static int MAX_THRESHOLD = 10;
    public boolean checkThreshold(Message<?> orderId) {
        if (Objects.nonNull(orderId.getPayload())) {
            int orderVal = (Integer) orderId.getPayload();
            if(orderVal > MAX_THRESHOLD) {
                return true;
            }
        }
        return false;
    }
}
