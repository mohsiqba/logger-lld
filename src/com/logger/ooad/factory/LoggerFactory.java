package com.logger.ooad.factory;


import com.logger.ooad.models.LogMessage;
import com.logger.ooad.models.MessageSeverity;
import com.logger.ooad.publisher.ILoggerPublisher;

public class LoggerFactory {
    ILoggerPublisher loggerPublisher;
    private static final LoggerFactory INSTANCE = new LoggerFactory();

    private LoggerFactory() {
    }

    public static LoggerFactory getInstance() {
        return INSTANCE;
    }

    public void info(String message) {
        loggerPublisher.publish(new LogMessage(MessageSeverity.INFO, message));
    }

    public void warn(String message) {
        loggerPublisher.publish(new LogMessage(MessageSeverity.WARN, message));
    }

    public void debug(String message) {
        loggerPublisher.publish(new LogMessage(MessageSeverity.DEBUG, message));
    }

    public void error(String message) {
        loggerPublisher.publish(new LogMessage(MessageSeverity.ERROR, message));
    }

    public void setLoggerPublisher(ILoggerPublisher loggerPublisher) {
        this.loggerPublisher = loggerPublisher;
    }
}
