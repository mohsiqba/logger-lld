package com.logger.ooad;

import com.logger.ooad.factory.LoggerFactory;
import com.logger.ooad.message.ILogMessageHandler;
import com.logger.ooad.message.impl.DebugLogMessageHandler;
import com.logger.ooad.message.impl.ErrorLogMessageHandler;
import com.logger.ooad.message.impl.InfoLogMessageHandler;
import com.logger.ooad.message.impl.WarnLogMessageHandler;
import com.logger.ooad.models.MessageSeverity;
import com.logger.ooad.publisher.ILoggerPublisher;
import com.logger.ooad.publisher.impl.LoggerPublisher;
import com.logger.ooad.sink.ISinkHandler;
import com.logger.ooad.sink.impl.ConsoleSinkHandler;
import com.logger.ooad.sink.impl.DbSinkHandler;
import com.logger.ooad.sink.impl.FileSinkHandler;

public class LoggerMain {

    public static void main(String[] args) {

        //set COR Pattern for log level
        ILogMessageHandler warnLogMessageHandler = new WarnLogMessageHandler();
        ILogMessageHandler infoLogMessageHandler = new InfoLogMessageHandler();
        ILogMessageHandler debugLogMessageHandler = new DebugLogMessageHandler();
        ILogMessageHandler errorLogMessageHandler = new ErrorLogMessageHandler();

        errorLogMessageHandler.setNextHandler(infoLogMessageHandler);
        infoLogMessageHandler.setNextHandler(debugLogMessageHandler);
        debugLogMessageHandler.setNextHandler(warnLogMessageHandler);
        warnLogMessageHandler.setNextHandler(null);

        // set sink for each level
        // every sink is capable of handling all msg levels.
        ISinkHandler fileSinkHandler = new FileSinkHandler(errorLogMessageHandler);
        ISinkHandler consoleSinkHandler = new ConsoleSinkHandler(errorLogMessageHandler);
        ISinkHandler dbSinkHandler = new DbSinkHandler(errorLogMessageHandler);

        // define which sink should handle what msg levels.
        ILoggerPublisher publisher = new LoggerPublisher();
        publisher.add(MessageSeverity.INFO, consoleSinkHandler);
        publisher.add(MessageSeverity.ERROR, consoleSinkHandler);
        publisher.add(MessageSeverity.ERROR, fileSinkHandler);
        publisher.add(MessageSeverity.ERROR, dbSinkHandler);
        publisher.add(MessageSeverity.DEBUG, fileSinkHandler);
        publisher.add(MessageSeverity.WARN, dbSinkHandler);

        LoggerFactory loggerFactory = LoggerFactory.getInstance();
        loggerFactory.setLoggerPublisher(publisher);
        
        // ConfigUtils is set to WARN. that means all msg levels greater  or equal to WARN will be processed.
        loggerFactory.info("mohsin info"); // prints to console.
        loggerFactory.debug("mohsin debug"); // prints to file.
        loggerFactory.warn("mohsin warn"); // prints to db.
        loggerFactory.error("mohsin error"); // prints to all (console,file,db).
    }
}
