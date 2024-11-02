package com.logger.ooad.sink.impl;

import com.logger.ooad.message.ILogMessageHandler;
import com.logger.ooad.models.LogMessage;
import com.logger.ooad.sink.ISinkHandler;

public class FileSinkHandler implements ISinkHandler {
    private ILogMessageHandler logMessageHandler;

    @Override
    public void writeMessage(LogMessage message) {
        logMessageHandler.flushMessage("[file]", message);
    }

    public FileSinkHandler(ILogMessageHandler logMessageHandler) {
        this.logMessageHandler = logMessageHandler;
    }
}
