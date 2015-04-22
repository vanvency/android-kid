package org.androidkid.logging;

import org.apache.logging.log4j.Logger;

/**
 * Created by fanhaojun on 15/4/21.
 */
public class Log4J2Log implements Log {
    
    private final Logger log;


    public Log4J2Log(Logger log) {
        this.log = log;
    }


    public void debug(String message) {
        log.debug(message);
    }


    public void debug(String message, Throwable t) {
        log.debug(message, t);
    }


    public void error(String message) {
        log.error(message);
    }


    public void error(String message, Throwable t) {
        log.error(message, t);
    }


    public void fatal(String message) {
        log.fatal(message);
    }


    public void fatal(String message, Throwable t) {
        log.fatal(message, t);
    }


    public void info(String message) { log.info(message); }


    public void info(String message, Throwable t) {
        log.info(message, t);
    }


    public void trace(String message) {
        log.trace(message);
    }


    public void trace(String message, Throwable t) {
        log.trace(message, t);
    }


    public void warn(String message) {
        log.warn(message);
    }


    public void warn(String message, Throwable t) {
        log.warn(message, t);
    }

}
