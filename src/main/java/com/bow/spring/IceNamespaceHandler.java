package com.bow.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author vv
 * @since 2017/4/4.
 */
public class IceNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("reference", new ReferenceBeanDefinitionParser());
    }
}
