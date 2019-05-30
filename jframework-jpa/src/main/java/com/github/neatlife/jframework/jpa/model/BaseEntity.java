package com.github.neatlife.jframework.jpa.model;

import lombok.Data;

/**
 * @author suxiaolin
 */
@Data
public abstract class BaseEntity {

    private Long createdAt;

    private Long updatedAt;

    private Long deletedAt;

}

