package com.bow.service;

import Demo.Request;
import Demo.Response;
import Demo._DemoServiceDisp;
import Ice.Current;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vv
 * @since 2017/4/4.
 */
public class DemoServiceImpl extends _DemoServiceDisp {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Override
    public void say(String s, Current __current) {
        LOGGER.info("receive " + s);
    }

    @Override
    public int calculate(int a, int b, Current __current) {
        return (a + b) * 2;
    }

    @Override
    public Response call(Request request, Current __current) {
        LOGGER.info("receive request, id " + request.requestId + " body " + request.body);
        Response response = new Response();
        response.responseId = request.requestId;
        response.body = " get it ";
        return response;
    }
}
