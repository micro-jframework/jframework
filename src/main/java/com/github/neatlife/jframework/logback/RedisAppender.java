package com.github.neatlife.jframework.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.github.neatlife.jframework.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.util.ObjectUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

import java.util.Arrays;
import java.util.Iterator;

@Slf4j
public class RedisAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private static Boolean enableNoRepeat = false;
    private static Integer repeatInterval = 600;
    JedisPool pool;
    /**
     * keep this for redis compatibility for now
     */
    JSONEventLayout jsonlayout;
    Layout<ILoggingEvent> layout;
    /**
     * logger configurable options
     */
    String host = "localhost";
    int port = Protocol.DEFAULT_PORT;
    String key = null;
    int timeout = Protocol.DEFAULT_TIMEOUT;
    String password = null;
    int database = Protocol.DEFAULT_DATABASE;

    public RedisAppender() {
        jsonlayout = new JSONEventLayout();
    }

    public static void setEnableNoRepeat(Boolean enableNoRepeat) {
        RedisAppender.enableNoRepeat = enableNoRepeat;
    }

    public static void setRepeatInterval(Integer repeatInterval) {
        RedisAppender.repeatInterval = repeatInterval;
    }

    @Override
    protected void append(ILoggingEvent event) {
        Jedis client = pool.getResource();
        try {

            String json = layout == null ? jsonlayout.doLayout(event) : layout.doLayout(event);
            String logMd5 = Md5Util.getMD5String(json);
            if (enableNoRepeat && ObjectUtils.isEmpty(client.get(logMd5))) {
                client.rpush(key, json);
                client.setex(logMd5, repeatInterval, logMd5);
            }
        } catch (Exception e) {
            log.error("e message: {}", e.getMessage());
            pool.returnBrokenResource(client);
            client = null;
        } finally {
            if (client != null) {
                pool.returnResource(client);
            }
        }
    }

    @Deprecated
    public String getSource() {
        return jsonlayout.getSource();
    }

    @Deprecated
    public void setSource(String source) {
        jsonlayout.setSource(source);
    }

    @Deprecated
    public String getSourceHost() {
        return jsonlayout.getSourceHost();
    }

    @Deprecated
    public void setSourceHost(String sourceHost) {
        jsonlayout.setSourceHost(sourceHost);
    }

    @Deprecated
    public String getSourcePath() {
        return jsonlayout.getSourcePath();
    }

    @Deprecated
    public void setSourcePath(String sourcePath) {
        jsonlayout.setSourcePath(sourcePath);
    }

    @Deprecated
    public String getTags() {
        if (jsonlayout.getTags() != null) {
            Iterator<String> i = jsonlayout.getTags().iterator();
            StringBuilder sb = new StringBuilder();
            while (i.hasNext()) {
                sb.append(i.next());
                if (i.hasNext()) {
                    sb.append(',');
                }
            }
            return sb.toString();
        }
        return null;
    }

    @Deprecated
    public void setTags(String tags) {
        if (tags != null) {
            String[] atags = tags.split(",");
            jsonlayout.setTags(Arrays.asList(atags));
        }
    }

    @Deprecated
    public String getType() {
        return jsonlayout.getType();
    }

    @Deprecated
    public void setType(String type) {
        jsonlayout.setType(type);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    @Deprecated
    public boolean getMdc() {
        return jsonlayout.getProperties();
    }

    @Deprecated
    public void setMdc(boolean flag) {
        jsonlayout.setProperties(flag);
    }

    @Deprecated
    public boolean getLocation() {
        return jsonlayout.getLocationInfo();
    }

    @Deprecated
    public void setLocation(boolean flag) {
        jsonlayout.setLocationInfo(flag);
    }

    @Deprecated
    public int getCallerStackIndex() {
        return jsonlayout.getCallerStackIdx();
    }

    @Deprecated
    public void setCallerStackIndex(int index) {
        jsonlayout.setCallerStackIdx(index);
    }

    @Deprecated
    public void addAdditionalField(AdditionalField p) {
        jsonlayout.addAdditionalField(p);
    }

    public Layout<ILoggingEvent> getLayout() {
        return layout;
    }

    public void setLayout(Layout<ILoggingEvent> layout) {
        this.layout = layout;
    }

    @Override
    public void start() {
        super.start();
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, host, port, timeout, password, database);
    }

    @Override
    public void stop() {
        super.stop();
        pool.destroy();
    }

}
