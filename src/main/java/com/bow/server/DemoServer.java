package com.bow.server;

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

        // create identity & servant
        Ice.Identity id = communicator().stringToIdentity("SimplePrinter");
        LOGGER.info("Ice.Identity id =" + communicator().identityToString(id));
        LOGGER.info("+++");
        Ice.Object servant = new PrinterImpl();

        // assemble with adapter
        Ice.ObjectAdapter adapter = communicator().createObjectAdapter("DemoAdapter");
        adapter.add(servant, id);
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
