package com.logger.ooad.publisher.impl;

import com.logger.ooad.models.LogMessage;
import com.logger.ooad.models.MessageSeverity;
import com.logger.ooad.publisher.ILoggerPublisher;
import com.logger.ooad.sink.ISinkHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SUBJECT : OBSERVER DP
 */
public class LoggerPublisher implements ILoggerPublisher {

    Map<MessageSeverity, List<ISinkHandler>> subscriberMap = new HashMap<>();

    @Override
    public void add(MessageSeverity severity, ISinkHandler subscriber) {
        this.subscriberMap.putIfAbsent(severity, new ArrayList<>());
        this.subscriberMap.get(severity).add(subscriber);
    }

    @Override
    public void remove(MessageSeverity severity, ISinkHandler subscriber) {
        this.subscriberMap.get(severity).remove(subscriber);
    }

    @Override
    public void publish(LogMessage message) {
        for (ISinkHandler subscriber : subscriberMap.get(message.getSeverity())) {
            subscriber.writeMessage(message);
        }
    }

}
