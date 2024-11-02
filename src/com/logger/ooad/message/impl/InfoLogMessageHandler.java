package com.logger.ooad.message.impl;

import com.logger.ooad.message.ILogMessageHandler;
import com.logger.ooad.models.LogMessage;
import com.logger.ooad.models.MessageSeverity;

import static com.logger.ooad.utils.ConfigUtils.LOGGER_LEVEL;

public class InfoLogMessageHandler implements ILogMessageHandler {

    private ILogMessageHandler logMessageHandler;

    @Override
    public void setNextHandler(ILogMessageHandler logMessageHandler) {
        this.logMessageHandler = logMessageHandler;
    }

    @Override
    public void flushMessage(String sinkMessage, LogMessage logMessage) {
        if (logMessage.getSeverity().getSeverity() >= LOGGER_LEVEL && logMessage.getSeverity() == MessageSeverity.INFO && MessageSeverity.INFO.getSeverity() >= LOGGER_LEVEL) {
            System.out.println(sinkMessage + "[" + logMessage.getSeverity() + "]::" + logMessage.getMessage());
        }
        if (logMessageHandler != null) {
            logMessageHandler.flushMessage(sinkMessage, logMessage);
        }
    }
}
