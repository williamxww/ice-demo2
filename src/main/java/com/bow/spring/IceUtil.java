package com.bow.spring;

import Ice.Communicator;
import Ice.InitializationData;
import Ice.Util;

/**
 * @author vv
 * @since 2017/4/4.
 */
public class IceUtil {

    private static final String CONFIG_FILE = "demoClient.cfg";

    private static Communicator communicator;

    private IceUtil() {

    }

    public static Communicator getCommunicator() {
        if (communicator == null) {
            synchronized (IceUtil.class) {
                if (communicator == null) {
                    InitializationData initData = new InitializationData();
                    initData.properties = Util.createProperties();
                    initData.properties.load(CONFIG_FILE);
                    communicator = Util.initialize(initData);
                }
            }
        }
        return communicator;
    }

}
