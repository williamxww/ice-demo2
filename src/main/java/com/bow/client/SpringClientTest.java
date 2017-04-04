package com.bow.client;

import Demo.DemoServicePrx;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author vv
 * @since 2017/4/4.
 */
public class SpringClientTest {

    @Test
    public void call() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-ice.xml");
        DemoServicePrx servicePrx = context.getBean("d1", DemoServicePrx.class);
        int a = servicePrx.calculate(1, 2);
        System.out.println(a);
    }
}
