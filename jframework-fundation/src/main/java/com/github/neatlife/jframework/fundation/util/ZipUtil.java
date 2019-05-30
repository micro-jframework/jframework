package com.github.neatlife.jframework.fundation.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * @author suxiaolin
 * @date 2019-03-30 17:27
 */
@Slf4j
public class ZipUtil {

    private ZipUtil() {
    }

    /**
     * 递归压缩文件夹
     *
     * @param srcRootDir 压缩文件夹根目录的子路径
     * @param file       当前递归压缩的文件或目录对象
     * @param zos        压缩文件存储对象
     * @throws \Exception
     */
    private static void zip(String srcRootDir, File file, ZipOutputStream zos) throws Exception {
        if (file == null) {
            return;
        }

        // 如果是文件，则直接压缩该文件
        if (file.isFile()) {
            int count;
            int bufferLen = 1024;
            byte[] data = new byte[bufferLen];

            // 获取文件相对于压缩文件夹根目录的子路径
            String subPath = file.getAbsolutePath();
            int index = subPath.indexOf(srcRootDir);
            if (index != -1) {
                subPath = subPath.substring(srcRootDir.length() + File.separator.length());
            }
            ZipEntry entry = new ZipEntry(subPath);
            zos.putNextEntry(entry);
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
                while ((count = bis.read(data, 0, bufferLen)) != -1) {
                    zos.write(data, 0, count);
                }
            }
            zos.closeEntry();
        }
        // 如果是目录，则压缩整个目录
        else {
            // 压缩目录中的文件或子目录
            File[] childFileList = file.listFiles();
            if (!ObjectUtils.isEmpty(childFileList)) {
                for (File childFile : childFileList) {
                    zip(srcRootDir, childFile, zos);
                }
            }
        }
    }

    /**
     * 对文件或文件目录进行压缩
     *
     * @param srcPath     要压缩的源文件路径。如果压缩一个文件，则为该文件的全路径；如果压缩一个目录，则为该目录的顶层目录路径
     * @param zipPath     压缩文件保存的路径。注意：zipPath不能是srcPath路径下的子文件夹
     * @param zipFileName 压缩文件名
     * @throws \Exception
     */
    public static void zip(String srcPath, String zipPath, String zipFileName) throws Exception {
        srcPath = new File(srcPath).getAbsolutePath();
        zipPath = new File(zipPath).getAbsolutePath();

        ZipOutputStream zos = null;
        CheckedOutputStream cos = null;
        FileOutputStream fos = null;
        try {
            File srcFile = new File(srcPath);

            // 判断压缩文件保存的路径是否为源文件路径的子文件夹，如果是，则抛出异常（防止无限递归压缩的发生）
            if (srcFile.isDirectory() && zipPath.contains(srcPath)) {
                throw new Exception("压缩文件保存的路径是否为源文件路径的子文件夹");
            }

            // 判断压缩文件保存的路径是否存在，如果不存在，则创建目录
            File zipDir = new File(zipPath);
            if (!zipDir.exists() || !zipDir.isDirectory()) {
                zipDir.mkdirs();
            }

            // 创建压缩文件保存的文件对象
            String zipFilePath = zipPath + File.separator + zipFileName;
            File zipFile = new File(zipFilePath);

            fos = new FileOutputStream(zipFile);
            cos = new CheckedOutputStream(fos, new CRC32());
            zos = new ZipOutputStream(cos);

            // 如果只是压缩一个文件，则需要截取该文件的父目录
            String srcRootDir = srcPath;
            if (srcFile.isFile()) {
                int index = srcPath.lastIndexOf(File.separator);
                if (index != -1) {
                    srcRootDir = srcPath.substring(0, index);
                }
            }
            // 调用递归压缩方法进行目录或文件压缩
            zip(srcRootDir, srcFile, zos);
            zos.flush();
        } finally {
            try {
                if (zos != null) {
                    zos.close();
                }
                if (cos != null) {
                    cos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                log.error("e message: {}", e.getMessage());
            }
        }
    }

    /**
     * 解压缩zip包
     *
     * @param zipFilePath        zip文件的全路径
     * @param unzipFilePath      解压后的文件保存的路径
     * @param includeZipFileName 解压后的文件保存的路径是否包含压缩文件的文件名。true-包含；false-不包含
     */
    @SuppressWarnings({"unchecked", "resource"})
    public static void unzip(String zipFilePath, String unzipFilePath, boolean includeZipFileName) throws Exception {
        File zipFile = new File(zipFilePath);
        // 如果解压后的文件保存路径包含压缩文件的文件名，则追加该文件名到解压路径
        if (includeZipFileName) {
            String fileName = zipFile.getName();
            if (StringUtils.isNotEmpty(fileName)) {
                fileName = fileName.substring(0, fileName.lastIndexOf('.'));
            }
            unzipFilePath = unzipFilePath + File.separator + fileName;
        }
        // 创建解压缩文件保存的路径
        File unzipFileDir = new File(unzipFilePath);
        if (!unzipFileDir.exists() || !unzipFileDir.isDirectory()) {
            unzipFileDir.mkdirs();
        }

        // 开始解压
        ZipEntry entry;
        String entryFilePath;
        String entryDirPath;
        File entryFile;
        File entryDir;
        int index = 0;
        int count = 0;
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        BufferedInputStream bis;
        try (ZipFile zip = new ZipFile(zipFile)) {
            Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zip.entries();
            // 循环对压缩包里的每一个文件进行解压
            while (entries.hasMoreElements()) {
                entry = entries.nextElement();
                // 构建压缩包中一个文件解压后保存的文件全路径
                entryFilePath = unzipFilePath + File.separator + entry.getName();
                // 构建解压后保存的文件夹路径
                index = entryFilePath.lastIndexOf(File.separator);
                if (index != -1) {
                    entryDirPath = entryFilePath.substring(0, index);
                } else {
                    entryDirPath = "";
                }
                entryDir = new File(entryDirPath);
                // 如果文件夹路径不存在，则创建文件夹
                if (!entryDir.exists() || !entryDir.isDirectory()) {
                    entryDir.mkdirs();
                }

                // 创建解压文件
                entryFile = new File(entryFilePath);

                // 写入文件
                try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(entryFile))) {
                    bis = new BufferedInputStream(zip.getInputStream(entry));
                    while ((count = bis.read(buffer, 0, bufferSize)) != -1) {
                        bos.write(buffer, 0, count);
                    }
                    bos.flush();
                }
            }
        }
    }

}
