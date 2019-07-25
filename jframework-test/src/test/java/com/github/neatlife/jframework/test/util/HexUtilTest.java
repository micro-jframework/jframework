package com.github.neatlife.jframework.test.util;

import com.github.neatlife.jframework.fundation.util.HexUtil;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class HexUtilTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testHex2Bytes() throws Exception {
        Assert.assertArrayEquals(new byte[]{-81, 27}, HexUtil.hex2Bytes("AF1B"));

        thrown.expect(Exception.class);
        HexUtil.hex2Bytes("AF1");
    }

    @Test
    public void testBytes2Hex() {
        Assert.assertEquals("af1b", HexUtil.bytes2Hex(new byte[]{-81, 27}));
        Assert.assertEquals("af1b",
                HexUtil.bytes2Hex(new byte[]{-81, 27}, true));
        Assert.assertEquals("AF1B",
                HexUtil.bytes2Hex(new byte[]{-81, 27}, false));
    }
}
