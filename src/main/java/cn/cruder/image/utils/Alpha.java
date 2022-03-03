package cn.cruder.image.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 将白色背景图片转换成透明图片
 *
 * @Author: cruder
 * @Date: 2022/03/03/9:10
 */
public class Alpha {

    /**
     * @param imgSrc    原图片地址
     * @param imgTarget 新图片地址
     * @return 转换结果ture or false
     */
    public static boolean transferAlpha2File(String imgSrc, String imgTarget) throws IOException {
        File file = new File(imgSrc);
        InputStream is = null;
        boolean result = false;
        try {
            // 如果是MultipartFile类型，那么自身也有转换成流的方法：is = file.getInputStream();
            is = new FileInputStream(file);
            BufferedImage bi = ImageIO.read(is);
            Image image = (Image) bi;
            ImageIcon imageIcon = new ImageIcon(image);
            BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
            g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
            int alpha = 0;
            for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
                for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
                    int rgb = bufferedImage.getRGB(j2, j1);
                    int R = (rgb & 0xff0000) >> 16;
                    int G = (rgb & 0xff00) >> 8;
                    int B = (rgb & 0xff);
                    if (((255 - R) < 30) && ((255 - G) < 30) && ((255 - B) < 30)) {
                        rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
                    }
                    bufferedImage.setRGB(j2, j1, rgb);
                }
            }
            g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
            // 直接输出文件
            result = ImageIO.write(bufferedImage, "png", new File(imgTarget));
            // 直接输出文件
            //result = ImageIO.write(bufferedImage, "jpg", new File(imgTarget));
        } catch (Exception e) {
            result = false;
            throw e;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
        return result;
    }

}