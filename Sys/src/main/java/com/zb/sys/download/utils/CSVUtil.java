package com.zb.sys.download.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by bzheng on 2019/12/5.
 */
public class CSVUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVUtil.class);

    // 分隔符 CSV文件用逗号做为分隔符
    private static final String SEPARATOR = ",";

    // 替换符 -- 用一个特殊点不常用的就行，防止用户输入的就是替换符
    private static final String REPLACE = "__!";

    public static void main(String[] args) {
//        Object[] objects = {1,2,3,4,8,7,9,6,5,11,12};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            stringBuilder.append("q");
        }

        List objects = Arrays.asList("你好", "dddddcd,vvvvk，路径", true, stringBuilder.toString(), "love");
        ArrayList<List> list = new ArrayList<>();
        list.add(objects);
        list.add(objects);
        list.add(objects);
        String filePath = PathUtil.getPath() + "csvFile.csv";

        boolean Flag = createCsvFile(list, new File(filePath), true, true);
//        if (Flag == true) {
//            System.out.print("CSV文件创建成功！");
//        } else {
//            System.out.print("CSV文件创建失败！");
//        }

        List<List> lists = readCsv(filePath);
        // 转成excel
        XSSFWorkbook xssfWorkbook = ExcelUtil.exportExcel_2007(lists, null);
        String targetPath =  PathUtil.getPath() + "csvFile.xlsx";
        boolean b = ExcelProcessUtil.exportFile(xssfWorkbook, targetPath, null);
        System.out.println("导出excel成功 ? " + b);
        /*lists.forEach(data -> {
            data.forEach(System.out::println);
        });*/
    }

    /**
     * 写入CSV文件
     *
     * @param rows   数据
     * @param file   CSV文件
     * @param append 是否追加 -> true:追加到下一行。else：直接覆盖已存在的内容
     * @param replace 是否将逗号替换成其他符号 {@link CSVUtil##REPLACE} --> 用于导出数据时，不至于将一列转成多列
     * @return
     */
    public static boolean createCsvFile(List<List> rows, File file, boolean append, boolean replace) {
        BufferedWriter fileOutputStream = null;
        //标记写入是否成功；
        boolean flag = true;
        try {
            // 检查文件
            checkFile(file);
            //实例化文件输出流
            fileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), "GB2312"), 1024);
            //遍历输出每行
            Iterator<List> ite = rows.iterator();
            while (ite.hasNext()) {
                List rowData = ite.next();
                for (int i = 0; i < rowData.size(); i++) {
                    Object obj = rowData.get(i);   //当前字段
                    //格式化数据
                    String field = formatField(obj, replace);
                    //拼接所有字段为一行数据
                    if (i < rowData.size() - 1) {
                        //不是最后一个元素
                        fileOutputStream.write(field + SEPARATOR);
                    } else {
                        //最后一个元素
                        fileOutputStream.write(field);
                    }
                }
                fileOutputStream.newLine();     //换行，创建一个新行；
            }
            fileOutputStream.flush();
        } catch (Exception e) {
            flag = false;
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return flag;
    }

    /**
     * 检查文件 -- 没有就创建
     * @param file
     */
    private static void checkFile(File file) throws IOException {
        //如果父目录不存在，创建父目录
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        //如果该文件不存在，生成文件
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * 将对象转成string
     *
     * @param obj
     * @param replace 是否将逗号替换成其他符号 {@link CSVUtil##REPLACE} --> 用于导出数据时，不至于将一列转成多列
     * @return
     */
    private static String formatField(Object obj, boolean replace) {
        String field;
        if (Objects.isNull(obj)) {
            field = " ";//null时给一个空格占位
        } else if (String.class.isAssignableFrom(obj.getClass()))     //如果是字符串
        {
            // excel中字符的长度不能超过32767
            if (obj.toString().length() > SpreadsheetVersion.EXCEL2007.getMaxTextLength()) {
                obj = " ";
            }
            field = (String) obj;
        } else if (Number.class.isAssignableFrom(obj.getClass()))   //如果是浮点型
        {
            NumberFormat formatter = NumberFormat.getNumberInstance();
            formatter.setMaximumFractionDigits(10);
            field = formatter.format(obj);   //格式化浮点数，使浮点数不以科学计数法输出
        } else if (Date.class.isAssignableFrom(obj.getClass()))   //如果是日期类型
        {
            //格式化日期数据
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            field = sdf.format(obj);
        } else if (Boolean.class.isAssignableFrom(obj.getClass())) {
            field = ((Boolean) obj).toString();
        } else if (Calendar.class.isAssignableFrom(obj.getClass())) {
            field = ((Calendar) obj).toString();
        } else if (RichTextString.class.isAssignableFrom(obj.getClass())) {
            field = ((RichTextString) obj).toString();
        } else {
            field = " ";
        }
        if (replace) {
            field = field.replaceAll(SEPARATOR, REPLACE);
        }

        return field;
    }

    /**
     * 将CSV文件导出
     *
     * @param filePath
     * @return
     */
    public static List<List> readCsv(String filePath) {
        if (!StringUtils.endsWith(filePath, ".csv")) {
            throw new IllegalArgumentException("导出文件不是CSV");
        }
        List<List> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GB2312"))) {
            // 读取直到最后一行
            String line = "";
            while ((line = br.readLine()) != null) {
                // 把一行数据分割成多个字段
                StringTokenizer st = new StringTokenizer(line, ",");
                List row = new ArrayList();
                while (st.hasMoreTokens()) {
                    String token = st.nextToken();
                    // 转换
                    row.add(StringUtils.isNotBlank(token) ? token.replaceAll(REPLACE, SEPARATOR) : token);
                }
                list.add(row);
            }

        } catch (IOException e) {
            // 捕获BufferedReader对象关闭时的异常
            LOGGER.error(e.getMessage(), e);
        }

        return list;
    }
}
