package com.neatlife.jframework.jpa;

import com.neatlife.jframework.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.lang.reflect.Field;

/**
 * @author suxiaolin
 */
@Configuration
@Slf4j
public class AuditingEntityListener {

    private final static String CREATED_AT_COLUMN = "createdAt";
    private final static String UPDATED_AT_COLUMN = "updatedAt";
    private final static String DELETED_AT_COLUMN = "deletedAt";

    @PrePersist
    public void touchCreated(Object target) {
        setFieldValue(target, DateUtil.currentSecond(), CREATED_AT_COLUMN);
        setFieldValue(target, DateUtil.currentSecond(), UPDATED_AT_COLUMN);
    }

    @PreUpdate
    public void touchUpdate(Object target) {
        setFieldValue(target, DateUtil.currentSecond(), UPDATED_AT_COLUMN);
    }

    @PreRemove
    public void touchDeleted(Object target) {
        setFieldValue(target, DateUtil.currentSecond(), DELETED_AT_COLUMN);
    }

    private void setFieldValue(Object target, Object value, String column) {
        try {
            Field field = target.getClass().getDeclaredField(column);
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            field.set(target, value);
            field.setAccessible(accessible);
        } catch (NoSuchFieldException e) {
            log.warn("class: {}, {}字段不存在, e: {}", target.getClass(), column, e);
        } catch (IllegalAccessException e) {
            log.warn("class: {}, {}字段设置值时异常, e: {}, value: {}", target.getClass(), column, e, value);
        }
    }

}

