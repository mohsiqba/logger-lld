package com.logger.ooad.message;

import com.logger.ooad.models.LogMessage;

/**
 * ERROR > INFO > DEBUG > WARN
 */
public interface ILogMessageHandler {
    void setNextHandler(ILogMessageHandler logMessageHandler);

    void flushMessage(String sinkMessage, LogMessage logMessage);
}
