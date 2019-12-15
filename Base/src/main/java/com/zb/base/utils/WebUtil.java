package com.zb.base.utils;

import com.zb.base.exception.BaseRuntimeException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @version 1.0
 * @desc
 * @date 2019/4/9
 */
public class WebUtil {

    public static void setDownloadHeaders(HttpServletRequest request, HttpServletResponse response, String fileName, long length) {
        final String userAgent = request.getHeader("User-Agent");
        try {
            //兼容多种浏览器，防止文件名乱码
            boolean isIE = StringUtils.contains(userAgent, "MSIE");//IE
            boolean isIE11 = StringUtils.contains(userAgent, "like Gecko");// IE11
            boolean isMozilla = StringUtils.contains(userAgent, "Mozilla");// google,火狐浏览器
            String finalFileName = null;
            if ( isIE || isIE11) {
                finalFileName = URLEncoder.encode(fileName, "UTF8");
            } else if (isMozilla) {
                finalFileName = new String(fileName.getBytes(), "ISO8859-1");
            } else {
                finalFileName = URLEncoder.encode(fileName, "UTF8"); // 其他浏览器
            }
            //修正 Excel在“xxx.xlsx”中发现不可读取的内容。是否恢复此工作薄的内容？如果信任此工作簿的来源，请点击"是"
            response.setHeader("Content-Length", String.valueOf(length));
            response.setHeader("Content-Type","text/plain");
            //这里设置一下让浏览器弹出下载提示框，而不是直接在浏览器中打开
            response.setHeader(
                    "Content-Disposition",
                    "attachment; filename=\"" + finalFileName + "\"");
        } catch (UnsupportedEncodingException e) {
            throw BaseRuntimeException.getException(e);
        }
    }
}
