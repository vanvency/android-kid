package org.androidkid.logging;

import org.androidkid.config.Global;
import org.apache.logging.log4j.LogManager;


/**
 * Created by fanhaojun on 15/4/20.
 */
public class LogFactory {

    static {

        if(Global.Settings.FILE_LOG_ENABLED)
            LogConfiguration.configure();
    }

    public static Log getLogger(Class<?> clz) {
        if(Global.Settings.FILE_LOG_ENABLED)
            return new Log4J2Log(LogManager.getLogger(clz));
        else
            return new NativeLog(clz.getName());
    }
}
