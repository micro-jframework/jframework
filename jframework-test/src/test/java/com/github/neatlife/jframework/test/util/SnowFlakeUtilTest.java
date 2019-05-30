package com.github.neatlife.jframework.test.util;

import com.github.neatlife.jframework.fundation.util.SnowFlakeUtil;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author suxiaolin
 * @date 2019-04-11 17:11
 */
public class SnowFlakeUtilTest {

    @Test
    public void next() {
        long count = 1000;
        List<Long> idList = Lists.newArrayList();
        for (int i = 0; i < count; i++) {
            long id = SnowFlakeUtil.next();
            idList.add(id);
            System.out.println("id: " + id);
        }
        Assert.assertEquals(count, idList.stream().distinct().collect(Collectors.toList()).size());
    }
}