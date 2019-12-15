package com.zb.sys.download.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * Created by bzheng on 2019/12/7.
 * excel加工工具类：
 * 1.将ExcelUtil中获取的Workbook生成文件，还可以加密
 */
public class ExcelProcessUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelProcessUtil.class);
    /**
     * 导出至目标路径
     * @param workBook 工作簿对象
     * @param targetPath 目标路径
     * @param password 文件密码，为empty则认为不设置
     * @return
     */
    public static boolean exportFile(Workbook workBook, String targetPath, String password) {
        if (Objects.isNull(workBook)) {
            LOGGER.error("workBook 对象为null");
            return false;
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(targetPath)) {
            // 写到目标文件
            workBook.write(fileOutputStream);
            if (StringUtils.isNotBlank(password)) {
                // 设置加密
                confirmPassword(targetPath,password);
            }
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                workBook.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        return false;
    }

    /**
     * 对文件加密
     *
     * @param targetPath 文件路径
     * @param password 密码
     */
    public static void confirmPassword(String targetPath, String password) {
        // 创建POIFS文件系统 加密文件
        try (POIFSFileSystem fs = new POIFSFileSystem()) {
            EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile);
            // EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile, CipherAlgorithm.aes192, HashAlgorithm.sha384, -1, -1, null);
            Encryptor enc = info.getEncryptor();
            // 设置密码,格式：自定义
            enc.confirmPassword(password);
            // Read in an existing OOXML file and write to encrypted output stream
            // don't forget to close the output stream otherwise the padding bytes aren't added
            try (OPCPackage opc = OPCPackage.open(new File(targetPath), PackageAccess.READ_WRITE);
                 OutputStream os = enc.getDataStream(fs)) {
                opc.save(os);
            }
            // Write out the encrypted version
            try (FileOutputStream fos = new FileOutputStream(targetPath)) {
                fs.writeFilesystem(fos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
