package com.neatlife.jframework.po;

import lombok.Data;

/**
 * @author suxiaolin
 */
@Data
public abstract class BasePo {

    private Long createdAt;

    private Long updatedAt;

    private Long deletedAt;

}

