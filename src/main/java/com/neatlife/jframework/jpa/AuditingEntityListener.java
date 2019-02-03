package com.neatlife.jframework.jpa;

import com.neatlife.jframework.po.AbstractEntity;
import com.neatlife.jframework.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 * @author suxiaolin
 */
@Configuration
@Slf4j
public class AuditingEntityListener {

    @PrePersist
    public void touchCreated(AbstractEntity target) {
        target.setCreatedAt(DateUtil.currentSecond());
        target.setUpdatedAt(DateUtil.currentSecond());
    }

    @PreUpdate
    public void touchUpdate(AbstractEntity target) {
        target.setUpdatedAt(DateUtil.currentSecond());
    }

    @PreRemove
    public void touchDeleted(AbstractEntity target) {
        target.setDeletedAt(DateUtil.currentSecond());
    }

}

