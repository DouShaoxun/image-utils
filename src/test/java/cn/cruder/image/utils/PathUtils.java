package cn.cruder.image.utils;

import java.io.File;

/**
 * @Author: cruder
 * @Date: 2022/03/03/10:13
 */
public class PathUtils {
    public static String imageFilePath(String name) {
        return new File("src/main/resources/image/" + name).getAbsolutePath();
    }
}
