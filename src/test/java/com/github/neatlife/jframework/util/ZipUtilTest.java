package com.github.neatlife.jframework.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * @author suxiaolin
 * @date 2019-03-30 17:31
 */
public class ZipUtilTest {

    @Test
    public void zip() {
        String root = "src/main";
        // 压缩后文件存储路径
        String zipPath = root + "/zip";
        // 压缩源文件（如果是文件, 则为全路径 /groovy.png）
        String sourcePath = root + "/resources";
        // 压缩后文件名
        String zipFileName = "zip.zip";
        try {
            ZipUtil.zip(sourcePath, zipPath, zipFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String zipFilePath = zipPath + File.separator + zipFileName;
        Assert.assertTrue(new File(zipFilePath).exists());

        // 解压源文件路径
        // 解压后文件存储路径
        String unzipPath = root + "/unzip";
        try {
            ZipUtil.unzip(zipFilePath, unzipPath, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}