package com.bow.service;

import Demo._PrinterDisp;
import Ice.Current;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vv
 * @since 2017/4/3.
 */
public class PrinterImpl  extends _PrinterDisp {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrinterImpl.class);
    @Override
    public void printString(String s, Current __current) {
        LOGGER.info("receive" + s);
    }
}
