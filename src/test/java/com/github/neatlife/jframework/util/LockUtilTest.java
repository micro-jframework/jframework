package com.github.neatlife.jframework.util;

import com.github.neatlife.jframework.JframeworkApplicationTests;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author suxiaolin
 * @date 2019-04-25 13:35
 */
public class LockUtilTest extends JframeworkApplicationTests {

    @Test
    public void tryGetDistributedLock() {
        boolean lock = LockUtil.tryGetDistributedLock("lock_key_1", "1", 60);
        boolean release = LockUtil.releaseDistributedLock("lock_key_1", "1");
        Assert.assertTrue(lock);
        Assert.assertTrue(release);
    }
}