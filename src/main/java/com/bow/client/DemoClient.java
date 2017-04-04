package com.bow.client;

/**
 * @author vv
 * @since 2017/4/3.
 */
public class DemoClient extends Ice.Application {
    @Override
    public int run(String[] args) {
        Ice.ObjectPrx base = communicator().stringToProxy("SimplePrinter");
        Demo.PrinterPrx printer = Demo.PrinterPrxHelper.checkedCast(base);
        if (printer == null)
            throw new Error("Invalid proxy");
        for (int i = 0; i < 10; i++) {
            printer.printString("hello " + i);
        }
        return 0;
    }

    public static void main(String[] args) {
        DemoClient app = new DemoClient();
        System.exit(app.main("client", args, "demoClient.cfg"));
    }
}
