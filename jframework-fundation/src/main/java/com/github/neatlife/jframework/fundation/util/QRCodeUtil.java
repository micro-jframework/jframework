package com.github.neatlife.jframework.fundation.util;

import com.google.common.collect.Maps;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 二维码工具类
 *
 * @author suxiaolin
 * @date 2019-03-25 23:22
 */
public class QRCodeUtil {
    /**
     * 默认二维码宽度
     */
    private static final int WIDTH = 300;

    /**
     * 默认二维码高度
     */
    private static final int HEIGHT = 300;

    /**
     * 默认二维码文件格式
     */
    private static final String FORMAT = "png";

    /**
     * 二维码参数
     */
    private static final Map<EncodeHintType, Object> hints = new HashMap();

    static {
        // 字符编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 容错等级 L、M、Q、H 其中 L 为最低, H 为最高
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 二维码与图片边距
        hints.put(EncodeHintType.MARGIN, 2);
    }

    /**
     * 返回一个 BufferedImage 对象
     *
     * @param content 二维码内容
     * @param width   宽
     * @param height  高
     */
    public static BufferedImage toBufferedImage(String content, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    /**
     * 将二维码图片输出到一个流中
     *
     * @param content 二维码内容
     * @param stream  输出流
     * @param width   宽
     * @param height  高
     */
    public static void writeToStream(String content, OutputStream stream, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToStream(bitMatrix, FORMAT, stream);
    }

    /**
     * 生成二维码图片文件
     *
     * @param content 二维码内容
     * @param path    文件保存路径
     * @param width   宽
     * @param height  高
     */
    public static void createQRCode(String content, String path, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        //toPath() 方法由 jdk1.7 及以上提供
        MatrixToImageWriter.writeToPath(bitMatrix, FORMAT, new File(path).toPath());
    }


    /**
     * 读取二维码图片
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static String read(String path) throws Exception {
        File file = new File(path);
        BufferedImage image;
        image = ImageIO.read(file);
        if (Objects.isNull(image)) {
            throw new RuntimeException("无法读取源文件");
        }
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        @SuppressWarnings("rawtypes")
        EnumMap<DecodeHintType, String> hints = Maps.newEnumMap(DecodeHintType.class);
        //解码设置编码方式为：utf-8
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
        result = new MultiFormatReader().decode(bitmap, hints);
        return result.getText();
    }
}