package com.neatlife.jframework.po;

import lombok.Data;

/**
 * @author suxiaolin
 */
@Data
public abstract class AbstractEntity {

    private Integer createdAt;

    private Integer updatedAt;

    private Integer deletedAt;

}

