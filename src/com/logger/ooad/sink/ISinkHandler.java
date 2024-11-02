package com.logger.ooad.sink;

import com.logger.ooad.models.LogMessage;

public interface ISinkHandler {
    void writeMessage(LogMessage message);
}
