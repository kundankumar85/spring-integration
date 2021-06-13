package com.learn.spring.integration.jmx.adapter.mbean;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@ManagedResource
public class OrderMBean {
    private final AtomicInteger orders = new AtomicInteger();

    @ManagedAttribute
    public int getOrders() {
        return this.orders.get();
    }

    @ManagedOperation
    public void incrementOrder() {
        orders.incrementAndGet();
    }

    @ManagedOperation
    public void resetOrders() {
        this.orders.set(0);
    }
}
