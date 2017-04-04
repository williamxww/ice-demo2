package com.bow.server;

import com.bow.service.PrinterImpl;

/**
 * @author vv
 * @since 2017/4/3.
 */
public class DemoServer extends Ice.Application {
    @Override
    public int run(String[] args) {
        if (args.length > 0) {
            System.err.println(appName() + ": too many arguments");
            return 1;
        }

        Ice.ObjectAdapter adapter = communicator().createObjectAdapter("DemoAdapter");
        Ice.Properties properties = communicator().getProperties();
        Ice.Identity id = communicator().stringToIdentity(properties.getProperty("Identity"));
        System.out.println("Ice.Identity id =" + id.name);
        Ice.Object object = new PrinterImpl();
        adapter.add(object, communicator().stringToIdentity("SimplePrinter"));
        adapter.activate();
        communicator().waitForShutdown();
        return 0;
    }

    public static void main(String[] args) {
        DemoServer app = new DemoServer();
        int status = app.main("Server", args, "printerServer.cfg");
        System.exit(status);
    }
}
