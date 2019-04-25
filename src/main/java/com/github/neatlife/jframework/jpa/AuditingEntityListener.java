package com.github.neatlife.jframework.jpa;

import com.github.neatlife.jframework.model.BaseEntity;
import com.github.neatlife.jframework.util.DateUtil;
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
    public void touchCreated(BaseEntity target) {
        target.setCreatedAt(DateUtil.currentSecond());
        target.setUpdatedAt(DateUtil.currentSecond());
    }

    @PreUpdate
    public void touchUpdate(BaseEntity target) {
        target.setUpdatedAt(DateUtil.currentSecond());
    }

    @PreRemove
    public void touchDeleted(BaseEntity target) {
        target.setDeletedAt(DateUtil.currentSecond());
    }

}

