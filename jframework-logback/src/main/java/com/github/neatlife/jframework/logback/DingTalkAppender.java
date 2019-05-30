package com.github.neatlife.jframework.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.github.neatlife.jframework.fundation.util.DingTalkUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author suxiaolin
 * @date 2019-04-13 11:37
 */
public class DingTalkAppender  extends UnsynchronizedAppenderBase<ILoggingEvent> {
    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd:HHmmssSSS");
    private static final String FORMAT_MESSAGE = "time: %s\nthreadName: %s\nlevel: %s\nlogger: %s\nclassName: %s.%s\nlineNumber: %d\nexception: %s:%s";

    private String to;

    @Override
    public void append(ILoggingEvent event) {
        if (event.getLevel() != Level.ERROR) {
            return;
        }

        String msg = transformStackTrace(event);
        DingTalkUtil.send(msg, event.getLoggerName(), Stream.of(to).collect(Collectors.toSet()));
    }

    private String transformStackTrace(ILoggingEvent event) {
        String exception = "";
        IThrowableProxy throwableProxy =  event.getThrowableProxy();
        if (throwableProxy != null) {
            exception = ThrowableProxyUtil.asString(throwableProxy);
        }

        StackTraceElement[] callerData = event.getCallerData();
        StackTraceElement stackTraceElement = callerData[0];

        String time = DEFAULT_DATE_FORMAT.format(new Date(event.getTimeStamp()));
        String threadName = event.getThreadName();
        String level = event.getLevel().toString();
        String logger = event.getLoggerName();
        String msg = event.getFormattedMessage();
        String className = stackTraceElement.getClassName();
        String method = stackTraceElement.getMethodName();
        int lineNumber = stackTraceElement.getLineNumber();

        return String.format(FORMAT_MESSAGE, time, threadName, level, logger, className, method, lineNumber, exception, msg);
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
