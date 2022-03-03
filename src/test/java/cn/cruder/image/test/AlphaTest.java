package cn.cruder.image.test;

import cn.cruder.image.utils.Alpha;
import cn.cruder.image.utils.PathUtils;

import java.io.File;
import java.io.IOException;

/**
 * @Author: cruder
 * @Date: 2022/03/03/10:12
 */
public class AlphaTest {
    public static void main(String[] args) throws IOException {
        String imgSrc = PathUtils.imageFilePath("source" + File.separatorChar + "White.png");
        String imgTarget = PathUtils.imageFilePath("target" + File.separatorChar + System.currentTimeMillis() + ".png");
        Alpha.transferAlpha2File(imgSrc, imgTarget);
        System.out.println(imgTarget);
    }
}
