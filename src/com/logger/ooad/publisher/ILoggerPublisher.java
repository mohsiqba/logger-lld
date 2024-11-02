package com.logger.ooad.publisher;

import com.logger.ooad.models.LogMessage;
import com.logger.ooad.models.MessageSeverity;
import com.logger.ooad.sink.ISinkHandler;

/**
 * SUBJECT : OBSERVER
 */
public interface ILoggerPublisher {

    void add(MessageSeverity severity, ISinkHandler subscriber);

    void remove(MessageSeverity severity, ISinkHandler subscriber);

    void publish(LogMessage message);
}
