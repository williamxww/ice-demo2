package com.bow.client;

import Demo.DemoServicePrx;
import Demo.DemoServicePrxHelper;
import Demo.PrinterPrx;
import Demo.PrinterPrxHelper;
import org.junit.Before;
import org.junit.Test;

/**
 * @author vv
 * @since 2017/4/4.
 */
public class DemoClientTest {

    private Ice.Communicator communicator;

    private DemoServicePrx demoService;

    private PrinterPrx printer;

    @Before
    public void setup() {
        try {
            String[] initParams = new String[] { "--Ice.Default.Locator=IceGrid/Locator:tcp -h 127.0.0.1 -p 4061" };
            communicator = Ice.Util.initialize(initParams);

            // 通过注册中心获取地址信息
            Ice.ObjectPrx proxy = communicator.stringToProxy("DemoService");
            demoService = DemoServicePrxHelper.checkedCast(proxy);

            Ice.ObjectPrx base = communicator.stringToProxy("SimplePrinter");
            printer = PrinterPrxHelper.checkedCast(base);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void demoService() {
        demoService.say("vv");
        int a = demoService.calculate(1, 1);
        System.out.println(a);
    }

    @Test
    public void printer(){
        printer.printString("vv");
    }
}
