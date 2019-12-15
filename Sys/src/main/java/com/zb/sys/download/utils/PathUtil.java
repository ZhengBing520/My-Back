package com.zb.sys.download.utils;

import com.zb.sys.download.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.io.File;

/**
 * Created by bzheng on 2019/12/5.
 */
//@Component
public class PathUtil {

    private static String property = System.getProperty("user.dir");

    private static Logger logger = LoggerFactory.getLogger(PathUtil.class);

//    @Value("${file.tempDirectory}")
    private static String tempDirectory = Constant.FilesDirectory.FILE_PATH;
    /**
     * 前提：上传图片不用临时路径
     * 如果是windows系统，需要把盘写上（E:），否则图片上传不成功,Linux不需要
     * @return
     */
    public static String getPath() {
        String path = tempDirectory;
        String[] split = property.split(":");
        if (!ObjectUtils.isEmpty(split) && split.length > 1) { // 判断是否为windows
            String s = split[0];
            path = s + ":" + path;
        }
        return path;
    }

    /**
     *
     * @param subcatalog
     */
    public static void pathExists(String subcatalog) {
        String path = getPath();
        // 判断目录是否存在，不存在就创建
        File file = new File(path + subcatalog);
        if (!file.exists() && !file.isDirectory()) {
            // 创建目录
            try {
                file.mkdirs();
                logger.info("创建服务器上传文件的临时目录成功，路径：" + path + subcatalog);
            } catch (Exception e) {
                logger.info("创建服务器上传文件的临时目录失败：" + e.getMessage(), e);
            }
        }
    }

}