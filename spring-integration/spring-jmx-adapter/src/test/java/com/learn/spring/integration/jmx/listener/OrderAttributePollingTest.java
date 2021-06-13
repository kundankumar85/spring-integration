package com.learn.spring.integration.jmx.listener;

import com.learn.spring.integration.jmx.adapter.mbean.OrderMBean;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;

import static junit.framework.Assert.assertNotNull;

public class OrderAttributePollingTest {
    private static ClassPathXmlApplicationContext context = null;


    @BeforeClass
    public static void setUp() {
        context = new ClassPathXmlApplicationContext("spring-integration-jmx-config.xml");
    }


    @AfterClass
    public static void destroy() {
        context.stop();
    }

    @Test
    public void testJmxNotification() throws InterruptedException {
        OrderMBean orderMBean = context.getBean("orderMBean", OrderMBean.class);
        orderMBean.incrementOrder();
//        Thread.sleep(2000);
        for (int i=1; i<=20;i++) {
           /* QueueChannel processedOrder = context.getBean("processedOrders", QueueChannel.class);
            Message<String> processedMsg = (Message<String>) processedOrder.receive();
            if(processedMsg!=null)
                System.out.println(processedMsg.getPayload());*/
            orderMBean.incrementOrder();
            Thread.sleep(1000);

        }

    }

}

