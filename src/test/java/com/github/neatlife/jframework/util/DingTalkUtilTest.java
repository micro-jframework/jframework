package com.github.neatlife.jframework.util;

import com.github.neatlife.jframework.JframeworkApplicationTests;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author suxiaolin
 * @date 2019-04-13 8:03
 */
public class DingTalkUtilTest extends JframeworkApplicationTests {

    @Test
    public void send() {
        boolean success = DingTalkUtil.send("test content", "test title", Stream.of("13288888888").collect(Collectors.toSet()));
        Assert.assertTrue(success);
    }
}