package com.example.myhutool.qrcode;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.*;

/**
 * @author Baozi
 */
public class QrCodeUtilDemo {
    public static void main(String[] args) {
        // 生成指定url对应的二维码到文件，宽和高都是300像素
        // QrCodeUtil.generate("https://hutool.cn/", 300, 300, FileUtil.file("d:/qrcode.jpg"));
        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        // 设置前景色，既二维码颜色（青色）
        config.setForeColor(Color.CYAN.getRGB());
        // 设置背景色（灰色）
        config.setBackColor(Color.GRAY.getRGB());

        // 生成二维码到文件，也可以到流
        // QrCodeUtil.generate("http://hutool.cn/", config, FileUtil.file("d:/qrcode.jpg"));

        // 高纠错级别
        // config.setErrorCorrection(ErrorCorrectionLevel.H);
        // QrCodeUtil.generate("https://hutool.cn/", config, FileUtil.file("e:/qrcodeCustom.jpg"));

       QrCodeUtil.decode(FileUtil.file("D:/qrcode.jpg"));
        // System.out.println(decode);
    }
}
