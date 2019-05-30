package com.github.neatlife.jframework.fundation.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author suxiaolin
 * @date 2019-03-29 09:02
 */
@Slf4j
public class Md5Util {
    /**
     * 获得字符串的md5值
     *
     * @param str 待加密的字符串
     * @return md5加密后的字符串
     */
    public static String getMD5String(String str) {
        byte[] bytes = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            bytes = md5.digest(str.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            log.error("e message: {}", e.getMessage());
        }
        return HexUtil.bytes2Hex(bytes);

    }


    /**
     * 获得字符串的md5大写值
     *
     * @param str 待加密的字符串
     * @return md5加密后的大写字符串
     */
    public static String getMD5UpperString(String str) {
        return getMD5String(str).toUpperCase();
    }

    /**
     * 获得文件的md5值
     *
     * @param file 文件对象
     * @return 文件的md5
     */
    public static String getFileMD5String(File file) {
        String ret = "";
        FileInputStream in = null;
        FileChannel ch = null;
        try {
            in = new FileInputStream(file);
            ch = in.getChannel();
            ByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
                    file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            ret = HexUtil.bytes2Hex(md5.digest());
        } catch (IOException | NoSuchAlgorithmException e) {
            log.error("e message: {}", e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("e message: {}", e.getMessage());
                }
            }
            if (ch != null) {
                try {
                    ch.close();
                } catch (IOException e) {
                    log.error("e message: {}", e.getMessage());
                }
            }
        }
        return ret;
    }

    /**
     * 获得文件md5值大写字符串
     *
     * @param file 文件对象
     * @return 文件md5大写字符串
     */
    public static String getFileMD5UpperString(File file) {
        return getFileMD5String(file).toUpperCase();
    }

    /**
     * 校验文件的md5值
     *
     * @param file 目标文件
     * @param md5  基准md5
     * @return 校验结果
     */
    public static boolean checkFileMD5(File file, String md5) {
        return getFileMD5String(file).equalsIgnoreCase(md5);
    }

    /**
     * 校验字符串的md5值
     *
     * @param str 目标字符串
     * @param md5 基准md5
     * @return 校验结果
     */
    public static boolean checkMD5(String str, String md5) {
        return getMD5String(str).equalsIgnoreCase(md5);
    }

    /**
     * 获得加盐md5，算法过程是原字符串md5后连接加盐字符串后再进行md5
     *
     * @param str  待加密的字符串
     * @param salt 盐
     * @return 加盐md5
     */
    public static String getMD5AndSalt(String str, String salt) {
        return getMD5String(getMD5String(str).concat(salt));
    }
}
