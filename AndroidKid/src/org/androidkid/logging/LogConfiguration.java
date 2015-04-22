package org.androidkid.logging;

import java.net.URI;
import java.util.zip.Deflater;

import org.androidkid.config.Global;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.CompositeTriggeringPolicy;
import org.apache.logging.log4j.core.appender.rolling.DefaultRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.appender.rolling.TimeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.config.AbstractConfiguration;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Order;
import org.apache.logging.log4j.core.config.plugins.Plugin;


public class LogConfiguration {

    public static final String LOG_FILE_ROOT = Global.Settings.LOG_FILE_PATH;
    public static final String LOG_FILE_NAME = "letsrun.log";
    public static final String LOG_FILE_NAME_PATTERN = "letsrun-%d{yyyy-MM-dd}-%i.log.gz";

    public static final String LOG_FILE_ROLLING_SIZE = "250 MB";
    public static final String LOG_FILE_ROLLING_DAY = "1";

    /**
     * Just to make JVM visit this class to initialize the static parts.
     */
    public static void configure() {
        System.getProperties().put("log4j2.disable.jmx","true");
        ConfigurationFactory.setConfigurationFactory(new Log4j2ConfigurationFactory());
    }

    @Plugin(category = "ConfigurationFactory", name = "Log4j2ConfigurationFactory")
    @Order(0)
    public static class Log4j2ConfigurationFactory extends ConfigurationFactory {

        @Override
        protected String[] getSupportedTypes() {
            return null;
        }

        @Override
        public Configuration getConfiguration(ConfigurationSource source) {
            return new Log4j2Configuration();
        }

        @Override
        public Configuration getConfiguration(String name, URI configLocation) {
            return new Log4j2Configuration();
        }

    }

    private static class Log4j2Configuration extends AbstractConfiguration {

        public Log4j2Configuration() {
            super(ConfigurationSource.NULL_SOURCE);
            setName("app-log4j2");
            getRootLogger().setLevel(Level.DEBUG);
        }

        @Override
        protected void doConfigure() {
            configureLogCat("app-log-logcat-appender", Level.DEBUG);
            configureRollingFile("app-log-file-appender", Level.DEBUG);
        }


        private void configureLogCat(String name, Level logLevel) {
            Appender appender = LogCatAppender.createAppender(null, null, null, name);
            addAppender(appender);
            getRootLogger().addAppender(appender, logLevel, null);
        }

        private void configureRollingFile(String name, Level logLevel) {

            //policy
            final TimeBasedTriggeringPolicy timeBasedTriggeringPolicy = TimeBasedTriggeringPolicy.createPolicy(LOG_FILE_ROLLING_DAY, "true");
            final SizeBasedTriggeringPolicy sizeBasedTriggeringPolicy = SizeBasedTriggeringPolicy.createPolicy(LOG_FILE_ROLLING_SIZE);
            final CompositeTriggeringPolicy policy = CompositeTriggeringPolicy.createPolicy(timeBasedTriggeringPolicy, sizeBasedTriggeringPolicy);

            //strategy
            final DefaultRolloverStrategy strategy = DefaultRolloverStrategy.createStrategy("7", "1", null,
                    Deflater.DEFAULT_COMPRESSION + "", this);

            Appender appender = RollingFileAppender.createAppender(
                    LOG_FILE_ROOT + LOG_FILE_NAME,
                    LOG_FILE_ROOT + LOG_FILE_NAME_PATTERN,
                    "true",
                    name, null, null, null, policy, strategy, null, null, null, null, null, null);

            addAppender(appender);
            getRootLogger().addAppender(appender, logLevel, null);
        }
    }

}