package com.bow.server;

import com.bow.service.DemoServiceImpl;
import com.bow.service.PrinterImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vv
 * @since 2017/4/3.
 */
public class DemoServer extends Ice.Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoServer.class);

    @Override
    public int run(String[] args) {
        if (args.length > 0) {
            LOGGER.error(appName() + ": too many arguments");
            return 1;
        }

        // assemble adapter
        Ice.ObjectAdapter adapter = communicator().createObjectAdapter("DemoAdapter");

        // create identity & servant
        Ice.Object printerServant = new PrinterImpl();
        adapter.add(printerServant, communicator().stringToIdentity("SimplePrinter"));

        Ice.Object demoServant = new DemoServiceImpl();
        adapter.add(demoServant, communicator().stringToIdentity("DemoService"));

        // activate
        adapter.activate();
        communicator().waitForShutdown();
        return 0;
    }

    public static void main(String[] args) {
        DemoServer app = new DemoServer();
        int status = app.main("Server", args, "demoServer.cfg");
        LOGGER.info("exit with status: " + status);
        System.exit(status);
    }
}
