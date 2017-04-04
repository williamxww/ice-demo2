package com.bow.spring;

import Ice.Communicator;
import Ice.ObjectPrx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.security.InvalidParameterException;

/**
 * @author vv
 * @since 2017/4/4.
 */
public class ReferenceBean extends ReferenceConfig implements FactoryBean, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceBean.class);

    private ObjectPrx proxy;

    @Override
    public Object getObject() throws Exception {
        Class helperClazz = getHelper();
        Method cast = helperClazz.getMethod("checkedCast", ObjectPrx.class);
        return cast.invoke(null, proxy);
    }

    @Override
    public Class<?> getObjectType() {
        return getInterface();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Communicator communicator = IceUtil.getCommunicator();
        this.proxy = communicator.stringToProxy(this.getIdentity());
    }

    private Class<?> getHelper() {
        String interfaceName = getInterfaceName();
        String fullName = interfaceName + "PrxHelper";
        Class clazz;
        try {
            clazz = Class.forName(fullName);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("please check reference type", e);
        }
        return clazz;
    }

    private Class<?> getInterface() {
        String interfaceName = getInterfaceName();
        String fullName = interfaceName + "Prx";
        Class clazz;
        try {
            clazz = Class.forName(fullName);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("please check reference type", e);
        }
        return clazz;
    }

    private String getInterfaceName() {
        String type = getType();
        if (StringUtils.isEmpty(type)) {
            throw new InvalidParameterException("<ice:reference type=\"must not be empty\" />");
        }
        String[] tokens = StringUtils.tokenizeToStringArray(type, ICE_SEPERATOR);
        String interfaceName = StringUtils.arrayToDelimitedString(tokens, DOT);
        return interfaceName;
    }

    public static void main(String[] args) {
        String type = "::Demo::DemoService";
        String[] tokens = StringUtils.tokenizeToStringArray(type, "::");
        for (String s : tokens) {
            System.out.println(s);
        }
        System.out.println();
    }
}
