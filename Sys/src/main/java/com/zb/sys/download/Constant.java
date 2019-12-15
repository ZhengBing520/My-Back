package com.zb.sys.download;

/**
 * Created by bzheng on 2019/12/5.
 * 常量类
 */
public interface Constant {

    /**
     * 文件目录
     */
    interface FilesDirectory {

        /**
         * 文件路径
         */
        String FILE_PATH = "/var/temp/";

        /**
         * 网站访问前缀
         */
        String WEBSITE_PREFIX = "/files";

        /**
         * 图片目录
         */
        String PICTURES = "pictures/";

        /**
         * 档案目录
         */
        String RECORD = "record/";

        /**
         * 下载目录
         */
        String DOWNLOAD = "download/";
    }

    /**
     * Redis命名空间
     */
    interface RedisNamespace {

        /**
         * 图片验证码
         */
        String REDIS_IMG_CODE = "com.zb.monitor.img_code:";

        /**
         * 任务自增长id
         */
        String REDIS_INCREMENT_TASK_ID = "com.zb.increment.taskId:";

        /**
         * 任务记录id
         */
        String REDIS_TASK_ID = "com.zb.download.taskId:";
    }
    }
