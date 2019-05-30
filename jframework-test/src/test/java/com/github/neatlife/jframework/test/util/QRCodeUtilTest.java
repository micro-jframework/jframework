package com.github.neatlife.jframework.test.util;

import com.github.neatlife.jframework.fundation.util.QRCodeUtil;
import org.junit.Test;

import java.io.File;

/**
 * @author suxiaolin
 * @date 2019-03-25 23:24
 */
public class QRCodeUtilTest {

    @Test
    public void createQRCode() throws Exception {
        QRCodeUtil.createQRCode("https://bing.com", "src/main/resources/1.png", 300, 300);
    }

    @Test
    public void testPath() {
        System.out.println(new File("src/main/resources/1.png").toPath());
    }

    @Test
    public void read() throws Exception {
        String content = QRCodeUtil.read("src/main/resources/1.png");
        System.out.println(content);
    }
}