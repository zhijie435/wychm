package com.jyd.utils.pic.watermark;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Created by wuzhijie on 2015/3/12 0012.
 */
public class PicWater {

    /**
     * 图片水印
     *
     * @param pressImg
     * @param targetImg
     * @param x
     * @param y
     */
    public final static void pressImage(String pressImg, String targetImg,
                                        int x, int y) throws IOException {
        //目标文件
        File _file = new File(targetImg);
        Image src = null;
        try {
            src = ImageIO.read(_file);
        } catch (IOException e) {
            throw new IOException("目标文件不存在...");
        }
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        g.drawImage(src, 0, 0, width, height, null);

        //水印文件
        File _felecia = new File(pressImg);
        Image soc_bio = null;
        try {
            soc_bio = ImageIO.read(_felecia);
        } catch (IOException e) {
            throw new IOException("水印文件不存在...");
        }
        int wide_bias = soc_bio.getWidth(null);
        int height_bias = soc_bio.getHeight(null);
        FileOutputStream out = null;
        try {
            g.drawImage(soc_bio, (width - wide_bias - x),
                    (height - height_bias - y), wide_bias, height_bias, null);
            out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
        } finally {
            g.dispose();
            out.close();
        }
    }

    public static void main(String[] args) {
        //偏移200像素
        try {
            PicWater.pressImage("e:/test/pic/logo.jpg", "e:/test/pic/tech.jpeg", 300, 20);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
