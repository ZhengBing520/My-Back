package com.zb.sys.download.utils;

import com.zb.sys.download.Constant;
import com.zb.sys.utils.function.MultiFunction;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ExcelUtil {

//    private static CellStyle defaultCellStyle;

    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    // 最大数据条数
    private static int maxSize = 10000;

    /**
     * 将值填充到单元格中
     *
     * @param cell
     * @param val
     */
    public static void inputValue(Cell cell, Object val) {
        if (val == null) {
            cell.setCellValue("");
            return;
        }
        Class clazz = val.getClass();
        if (String.class.isAssignableFrom(clazz)) {
            String value = val.toString();
            // 字符的长度不能超过32767
            if(value.length() > SpreadsheetVersion.EXCEL2007.getMaxTextLength()){
                value = "";
            }
            cell.setCellValue((String) val);
        } else if (Number.class.isAssignableFrom(clazz)) {
            //为了避免数字可能超过Double最大值,使用String表示数字
            cell.setCellValue(val.toString());
        } else if (Date.class.isAssignableFrom(clazz)) {
            //为了避免日期类型转换过去后显示为数字,需要设置单元格格式
            /*Workbook workbook= cell.getRow().getSheet().getWorkbook();
            cell.setCellStyle(getDateCellStyle(workbook));
            cell.setCellValue((Date)val);*/
            // 装成string
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d HH:mm:ss");
            cell.setCellValue(sdf.format((Date) val));
        } else if (Boolean.class.isAssignableFrom(clazz)) {
            cell.setCellValue((Boolean) val);
        } else if (Calendar.class.isAssignableFrom(clazz)) {
            cell.setCellValue((Calendar) val);
        } else if (RichTextString.class.isAssignableFrom(clazz)) {
            cell.setCellValue((RichTextString) val);
        }
    }

    /*public static CellStyle getDateCellStyle(Workbook workbook) {
        if (Objects.isNull(defaultCellStyle)) {
            DataFormat dataFormat= workbook.createDataFormat();
            CellStyle cellStyle=workbook.createCellStyle();
            cellStyle.setDataFormat(dataFormat.getFormat("yyyy/m/d"));
            defaultCellStyle = cellStyle;
        }
        return defaultCellStyle;
    }*/
    public static Object readCell(Cell cell) {
        return readCell(cell, null, null);
    }

    @SuppressWarnings("unchecked")
    public static Object readCell(Cell cell, Class clazz, String head) {
        if (cell == null) {
            return null;
        }
        CellType cellType = cell.getCellType();
        Object val = null;
        switch (cellType) {
            case BLANK: {
                return null;
            }
            case BOOLEAN: {
                val = cell.getBooleanCellValue();
                break;
            }
            case NUMERIC: {
                if (DateUtil.isCellDateFormatted(cell)) {

                    val = cell.getDateCellValue();
                } else {
                    val = cell.getNumericCellValue();
                }
                break;
            }
            case STRING: {
                val = cell.getStringCellValue();
                break;
            }
            case FORMULA: {
                try {
                    val = cell.getStringCellValue();
                } catch (IllegalStateException e) {
                    val = cell.getNumericCellValue();
                }
                break;
            }
            default: {
                val = cell.toString();
            }
        }
        if (Objects.isNull(clazz) || Objects.isNull(head)) {
            return val;
        }
        // 判断类型
        if (!clazz.isAssignableFrom(val.getClass())) {
            // excel会将整数转成double类型
            if (Number.class.isAssignableFrom(clazz) && !Double.class.isAssignableFrom(val.getClass())) {
                // 异常信息也可以提取出来，让方法传给前端展示
                throw new IllegalArgumentException("导入模板中的【" + head + "】内容有误");
            }

        }
        return val;
    }

    /**
     * 追加到excel(.xlsx) // 不建议用此方法，可以查看：{@link com.zb.sys.download.utils.CSVUtil#createCsvFile(List, File, boolean,boolean)}
     *
     * @param dataList 数据集合
     * @return
     */
    public static boolean writeExcel_2007(File file, List<List> dataList, BiConsumer<Cell, Object> cellBiConsumer) {

        try (InputStream inp = new FileInputStream(file)) {
            // 不报错：Zip bomb detected! The file would exceed the max. ratio of compressed file size to the size of the expanded data.
            ZipSecureFile.setMinInflateRatio(-1);
//            Workbook wb = WorkbookFactory.create(inp);
            Workbook wb = new XSSFWorkbook(inp);
            if (1 > wb.getNumberOfSheets()) {
                return false;
            }
            Sheet sheet = wb.getSheetAt(0);
            // 获取最后一行
//            int endRow = sheet.getLastRowNum(); // 此方法返回的0有两种：1.文件为empty（新建的文件）；2.文件有一行
            int endRow = sheet.getPhysicalNumberOfRows();// 此方法返回物理行
            writeSheet(sheet, endRow + 1, 1, null, dataList);
            // Write the output to a file
            try (OutputStream fileOut = new FileOutputStream(file)) {
                //            fileOut.flush();
                wb.write(fileOut);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return false;
        }

        return true;
    }

    /**
     * 导出excel(.xlsx)
     *
     * @param dataList 数据集合
     * @return
     */
    public static XSSFWorkbook exportExcel_2007(List<List> dataList, BiConsumer<Cell, Object> cellBiConsumer) {
        XSSFWorkbook workBook = new XSSFWorkbook();
        Sheet sheet = workBook.createSheet();
        writeSheet(sheet, 1, 1, cellBiConsumer, dataList);
        return workBook;
    }

    /**
     * 导出excel(.xlsx)
     *
     * @param dataList 数据集合
     * @return
     */
    public static XSSFWorkbook exportExcel_2007(List<List> dataList, BiConsumer<Cell, Object> cellBiConsumer, Map<String, Integer> map) {
        XSSFWorkbook workBook = new XSSFWorkbook();
        Sheet sheet = workBook.createSheet();
        writeSheet(sheet, 1, 1, cellBiConsumer, dataList, map);
        return workBook;
    }

    /**
     * 导出excel(.xls)
     *
     * @param dataList 数据集合
     * @return
     */
    public static HSSFWorkbook exportExcel_2003(List<List> dataList, BiConsumer<Cell, Object> cellBiConsumer) {
        HSSFWorkbook workBook = new HSSFWorkbook();
        int mus = 65535;// 一个sheet最大行数
        Sheet sheet = workBook.createSheet();
        writeSheet(sheet, 1, 1, cellBiConsumer, dataList);
        return workBook;
    }

    /**
     * 重写覆盖excel,会用新生成的excel覆盖掉之前的
     * 原理:
     * 1、获取path指定的文件,读取成java对象
     * 2、生成临时的excel文件
     * 3、将整合的数据写入临时excel
     * 4、删除path文件同时将临时excel文件改名成path
     *
     * @param path           覆盖重写的文件path
     * @param sheetIndex     path的sheet编号,从1开始
     * @param beginRowIndex  sheet开始的行号,从1开始
     * @param beginColIndex  sheet开始的列号,从1开始
     * @param cellBiConsumer 读取cell值的方法
     * @param dataList       数据集合
     */
    public static void overWriteExcel(final Path path, final int sheetIndex, final int beginRowIndex, final int beginColIndex, BiConsumer<Cell, Object> cellBiConsumer, List<List> dataList) {
        if (dataList == null || dataList.size() == 0) {
            return;
        }
        try {
            String tempName = path.subpath(0, path.getNameCount() - 2) + File.pathSeparator + UUID.randomUUID().toString();
            Path tempPath = Paths.get(tempName);
            writeExcel(path, tempPath, sheetIndex, beginRowIndex, beginColIndex, cellBiConsumer, dataList);
            Files.deleteIfExists(path);
            boolean res = tempPath.toFile().renameTo(path.toFile());
            if (!res) {
                throw new IllegalArgumentException("[ExcelUtil.overWriteExcel],Rename Failed!");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }


    /**
     * 将源excel的数据经过加工写入到目标excel中
     *
     * @param sourcePath     源excel
     * @param targetPath     目标excel
     * @param sheetIndex     path的sheet编号,从1开始
     * @param beginRowIndex  sheet开始的行号,从1开始
     * @param beginColIndex  sheet开始的列号,从1开始
     * @param cellBiConsumer 读取cell值的方法
     * @param dataList       数据集合
     */
    public static void writeExcel(final Path sourcePath, final Path targetPath, final int sheetIndex, final int beginRowIndex, final int beginColIndex, BiConsumer<Cell, Object> cellBiConsumer, List<List> dataList) {
        try (InputStream is = Files.newInputStream(sourcePath);
             OutputStream os = Files.newOutputStream(targetPath)) {
            Workbook workbook = WorkbookFactory.create(is);
            if (sheetIndex > workbook.getNumberOfSheets()) {
                return;
            }
            Sheet sheet = workbook.getSheetAt(sheetIndex - 1);
            writeSheet(sheet, beginRowIndex, beginColIndex, cellBiConsumer, dataList);
            workbook.write(os);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }


    /**
     * 写入数据到sheet
     * 会进行覆盖操作
     *
     * @param sheet          操作的sheet
     * @param beginRowIndex  sheet开始的行号,从1开始
     * @param beginColIndex  sheet开始的列号,从1开始
     * @param cellBiConsumer 读取cell值的方法
     * @param dataList       数据集合
     */
    public static void writeSheet(Sheet sheet, final int beginRowIndex, final int beginColIndex, BiConsumer<Cell, Object> cellBiConsumer, List<List> dataList) {
        writeSheet(sheet, beginRowIndex, beginColIndex, cellBiConsumer, dataList, null);
    }

    /**
     * 写入数据到sheet
     * 会进行覆盖操作
     *
     * @param sheet          操作的sheet
     * @param beginRowIndex  sheet开始的行号,从1开始
     * @param beginColIndex  sheet开始的列号,从1开始
     * @param cellBiConsumer 读取cell值的方法
     * @param dataList       数据集合
     */
    public static void writeSheet(Sheet sheet, final int beginRowIndex, final int beginColIndex, BiConsumer<Cell, Object> cellBiConsumer, List<List> dataList, Map<String, Integer> map) {
        if (dataList == null || dataList.size() == 0) {
            return;
        }
        for (int i = 0; i <= dataList.size() - 1; i++) {
            List data = dataList.get(i);
            int rowIndex = i + beginRowIndex - 1;
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            for (int j = 0; j <= data.size() - 1; j++) {
                int colIndex = j + beginColIndex - 1;
                Object val = data.get(j);
                Cell cell = row.getCell(colIndex);
                if (cell == null) {
                    cell = row.createCell(colIndex);
                }
                if (0 == i) {
                    // 设置列宽
                    setColumnWidth(sheet, colIndex, val, map);
                    // 设计居中
                    getCellStyle(cell);

                }
                if (cellBiConsumer == null) {
                    inputValue(cell, val);
                } else {
                    cellBiConsumer.accept(cell, val);
                }
            }
        }
    }

    /**
     * 设置列宽
     *
     * @param sheet
     * @param colIndex 列序号
     * @param val      值
     * @param map      特殊字段对应长度
     */
    private static void setColumnWidth(Sheet sheet, int colIndex, Object val, Map<String, Integer> map) {
        // 获取头部信息长度
        // 判断是否为特殊处理字段
        int width = 0;
        width = getWidth(val, map);
        width = 0 == width ? String.valueOf(val).length() * 2 : width;
        sheet.setColumnWidth(colIndex, 256 * (width + 1));
    }

    private static int getWidth(Object val, Map<String, Integer> map) {
        if (CollectionUtils.isEmpty(map)) {
            // 获取默认的Map
            map = SpecialField.map;
        }
        Integer length = map.get(val.toString().toUpperCase());
        if (Objects.nonNull(length)) {
            return length;
        }
//        Objects.equals()
        return 0;
    }

    private static Cell getCellStyle(Cell cell) {
        Workbook workbook = cell.getRow().getSheet().getWorkbook();

        Font font = workbook.createFont();
//        font.setColor(Font.COLOR_RED);
        font.setFontName("宋体");
//        font.setFontHeightInPoints((short) 11);
        font.setBold(true);//粗体显示

        CellStyle cellStyle = workbook.createCellStyle();
        // 设置居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        // 设置颜色填充
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFont(font);

        // 设置边框:
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框
//
        cell.setCellStyle(cellStyle);
        return cell;
    }

    /**
     * 根绝开始行，开始列，结束列。获取excel中的数据
     * 数据格式为clazzArr指定
     * 结束行 为指定的列 为空
     *
     * @param sheet         操作的sheet
     * @param beginRowIndex sheet开始的行号,从1开始
     * @param beginColIndex sheet开始的列号,从1开始
     * @param endColIndex   sheet结束的列号,从1开始
     * @param rowFunction   判断结束行 方法
     * @param cellFunction  读取单元格数据 方法
     * @return
     */
    public static List<List> readExcel(final Sheet sheet, final int beginRowIndex, final int beginColIndex, final int endColIndex,
                                       final Function<Row, Boolean> rowFunction, final Function<Cell, Object> cellFunction) {
        return readExcel(null, sheet, beginRowIndex, beginColIndex, endColIndex, rowFunction, cellFunction);
    }

    /**
     * 根绝开始行，开始列，结束列。获取excel中的数据
     * 数据格式为clazzArr指定
     * 结束行 为指定的列 为空
     *
     * @param heads         头部信息，验证模板正确性
     * @param sheet         操作的sheet
     * @param beginRowIndex sheet开始的行号,从1开始
     * @param beginColIndex sheet开始的列号,从1开始
     * @param endColIndex   sheet结束的列号,从1开始
     * @param rowFunction   判断结束行 方法
     * @param cellFunction  读取单元格数据 方法
     * @return
     */
    public static List<List> readExcel(String[] heads, final Sheet sheet, final int beginRowIndex, final int beginColIndex, final int endColIndex,
                                       final Function<Row, Boolean> rowFunction, final Function<Cell, Object> cellFunction) {
        // 验证头部信息
        if (Objects.nonNull(heads) && heads.length > 0) {
            // 获取第一行数据
            Row row = sheet.getRow(0);
            for (int i = 0; i < heads.length; i++) {
                Cell cell = row.getCell(i);
                Object val = readCell(cell);
                if (!Objects.equals(heads[i], val)) {
                    logger.info("不同的字段名，模板字段名：" + val + ";需求字段名：" + heads[i]);
                    throw new IllegalArgumentException("请导入正确的模板");
                }
            }
        }

        List<List> returnList = new ArrayList<>();
        int startRow = beginRowIndex - 1;
        int endRow = sheet.getLastRowNum();
        // 检查阈值 -> 数据量不能操作某个值，如果是限制文件大小，就设置上传文件大小的限制
        checkData(endRow, sheet, beginColIndex - 1, endColIndex - 1, maxSize, beginRowIndex);

        for (int i = startRow; i <= endRow; i++) {
            Row row = sheet.getRow(i);
            boolean isContinue = rowFunction.apply(row);
            if (!isContinue) {
                break;
            }
            List<Object> objList = new ArrayList<>();
            // 标记一列是否为空
            boolean flag = false;
            for (int j = beginColIndex - 1; j <= endColIndex - 1; j++) {
                Cell cell = row.getCell(j);
                Object val;
                if (cellFunction == null) {
                    val = readCell(cell);
                } else {
                    val = cellFunction.apply(cell);
                }
                objList.add(val);
                if (Objects.nonNull(val) && StringUtils.isNotBlank(val.toString())) {
                    flag = true;
                }
            }
            if (!flag) {
                // 表示这一列没有数据，一列没有数据则不往下读了
                return returnList;
            }
            returnList.add(objList);
        }
        return returnList;
    }

    /**
     * 检查数据量是否达到阈值
     *
     * @param endRow     最后一行数据的角标
     * @param sheet      文件
     * @param startIndex 列开始角标
     * @param endIndex   列结束角标
     * @param maxSize    最大行数
     * @param offset     偏移量
     */
    private static void checkData(int endRow, Sheet sheet, int startIndex, int endIndex, int maxSize, int offset) {
        // 能支持excel最大的行数 和 实际行数比较
        if (maxSize + offset > endRow + 1) {
            return;
        }
        // 判断最后一行(maxSize - 1)的数据是否为null或者为空字符，是则通过
        Row row = sheet.getRow(maxSize - 1);
        for (int j = startIndex; j <= endIndex; j++) {
            Cell cell = row.getCell(j);
            Object value = readCell(cell);
            if (Objects.nonNull(value) && StringUtils.isNotBlank(value.toString())) {
                // 有值就抛异常
                throw new IllegalArgumentException("导入数量超出最大限制：" + maxSize);
            }
        }


    }

    /**
     * 根绝开始行，开始列，结束列。获取excel中的数据
     * 数据格式为clazzArr指定
     * 结束行 为指定的列 为空
     *
     * @param heads         头部信息，验证模板正确性
     * @param clazzs        数据类型class，如：Double.class、String.class...
     * @param sheet         操作的sheet
     * @param beginRowIndex sheet开始的行号,从1开始
     * @param beginColIndex sheet开始的列号,从1开始
     * @param endColIndex   sheet结束的列号,从1开始
     * @param rowFunction   判断结束行 方法
     * @param cellFunction  读取单元格数据 方法
     * @return
     */
    public static List<List> readExcel(String[] heads, Class[] clazzs, final Sheet sheet, final int beginRowIndex, final int beginColIndex, final int endColIndex,
                                       final Function<Row, Boolean> rowFunction, final MultiFunction<Cell, Class, String, Object> cellFunction) {
        return readExcel(heads, clazzs, sheet, beginRowIndex, beginColIndex, endColIndex, maxSize, rowFunction, cellFunction);
    }

    /**
     * 根绝开始行，开始列，结束列。获取excel中的数据
     * 数据格式为clazzArr指定
     * 结束行 为指定的列 为空
     *
     * @param heads         头部信息，验证模板正确性
     * @param clazzs        数据类型class，如：Double.class、String.class...
     * @param sheet         操作的sheet
     * @param beginRowIndex sheet开始的行号,从1开始
     * @param beginColIndex sheet开始的列号,从1开始
     * @param endColIndex   sheet结束的列号,从1开始
     * @param maxSize       最大导入数据量
     * @param rowFunction   判断结束行 方法
     * @param cellFunction  读取单元格数据 方法
     * @return
     */
    public static List<List> readExcel(String[] heads, Class[] clazzs, final Sheet sheet, final int beginRowIndex, final int beginColIndex, final int endColIndex, final int maxSize,
                                       final Function<Row, Boolean> rowFunction, final MultiFunction<Cell, Class, String, Object> cellFunction) {
        // 验证头部信息
        boolean flag2 = false;
        if (Objects.nonNull(heads) && heads.length > 0) {
            // 获取表头
            Row row;
            if (beginRowIndex > 3) {
                // 说明有最上一行的类，读取下面的表头
                row = sheet.getRow(1);
            } else {
                row = sheet.getRow(0);
            }

            if (Objects.isNull(row)) {
                throw new IllegalArgumentException("请导入正确的模板");
            }

            for (int i = 0; i < heads.length; i++) {
                // 获取列的数据
                Cell cell = row.getCell(i + beginColIndex - 1);
                Object val = readCell(cell);
                if (!Objects.equals(heads[i], val)) {
                    flag2 = true;
                    logger.info("不同的字段名，模板字段名：" + val + ";需求字段名：" + heads[i]);
//                    throw new IllegalArgumentException("请导入正确的模板");
                }
            }
        }
        if (flag2) {
            throw new IllegalArgumentException("请导入正确的模板");
        }

        List<List> returnList = new ArrayList<>();
        int startRow = beginRowIndex - 1;
        int endRow = sheet.getLastRowNum();
        // 检查阈值 -> 数据量不能超过某个值，如果是限制文件大小，就设置上传文件大小的限制
        checkData(endRow, sheet, beginColIndex - 1, endColIndex - 1, maxSize, beginRowIndex);
        for (int i = startRow; i <= endRow; i++) {
            Row row = sheet.getRow(i);
            boolean isContinue = rowFunction.apply(row);
            if (!isContinue) {
                break;
            }
            List<Object> objList = new ArrayList<>();
            // 标记一列是否为空
            boolean flag = false;
            for (int j = beginColIndex - 1; j <= endColIndex - 1; j++) {
                Cell cell = row.getCell(j);
                Object val;
                // clazzs 和 heads 的角标
                int k;
                if (1 != beginColIndex) {
                    // 不等于1 说明第一列可能是一个不需要的值（如：例）
                    k = j - 1;
                } else {
                    k = j;
                }

                if (cellFunction == null) {
                    val = readCell(cell, clazzs[k], heads[k]);
                } else {
                    val = cellFunction.apply(cell, clazzs[k], heads[k]);
                }
                objList.add(val);
                if (Objects.nonNull(val) && StringUtils.isNotBlank(val.toString())) {
                    flag = true;
                }
            }
            if (!flag) {
                // 表示这一列没有数据，一列没有数据则不往下读了
                return returnList;
            }
            returnList.add(objList);
        }
        return returnList;
    }

    public static List<List> readExcel(final InputStream is, final int sheetIndex, final int beginRowIndex, final int beginColIndex, final int endColIndex,
                                       final Function<Row, Boolean> rowFunction, final Function<Cell, Object> cellFunction) {
        return readExcel(null, is, sheetIndex, beginRowIndex, beginColIndex, endColIndex, rowFunction, cellFunction);
    }

    public static List<List> readExcel(final String[] heads, final InputStream is, final int sheetIndex, final int beginRowIndex, final int beginColIndex, final int endColIndex,
                                       final Function<Row, Boolean> rowFunction, final Function<Cell, Object> cellFunction) {
        try {
            Workbook workbook = WorkbookFactory.create(is);
            if (sheetIndex > workbook.getNumberOfSheets()) {
                return new ArrayList<>();
            }
            Sheet sheet = workbook.getSheetAt(sheetIndex - 1);
            return readExcel(heads, sheet, beginRowIndex, beginColIndex, endColIndex, rowFunction, cellFunction);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<List> readExcel(final String[] heads, final Class[] clazzs, final InputStream is, final int sheetIndex, final int beginRowIndex, final int beginColIndex, final int endColIndex,
                                       final Function<Row, Boolean> rowFunction, final MultiFunction<Cell, Class, String, Object> cellFunction) {
        return readExcel(heads, clazzs, is, sheetIndex, beginRowIndex, beginColIndex, endColIndex, maxSize, rowFunction, cellFunction);
    }

    public static List<List> readExcel(final String[] heads, final Class[] clazzs, final InputStream is, final int sheetIndex, final int beginRowIndex, final int beginColIndex, final int endColIndex, final int maxSize,
                                       final Function<Row, Boolean> rowFunction, final MultiFunction<Cell, Class, String, Object> cellFunction) {
        try {
            Workbook workbook = WorkbookFactory.create(is);
            if (sheetIndex > workbook.getNumberOfSheets()) {
                return new ArrayList<>();
            }
            Sheet sheet = workbook.getSheetAt(sheetIndex - 1);
            return readExcel(heads, clazzs, sheet, beginRowIndex, beginColIndex, endColIndex, maxSize, rowFunction, cellFunction);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Map<String, Object>> readExcel(final Sheet sheet, final int beginRowIndex, final int beginColIndex, final int endColIndex,
                                                      final String[] fieldNameArr, final Function<Row, Boolean> rowFunction, final Function<Cell, Object> cellFunction) {
        List<List> dataList = readExcel(sheet, beginRowIndex, beginColIndex, endColIndex, rowFunction, cellFunction);
        return parseToJsonArrayData(dataList, fieldNameArr);
    }

    public static List<Map<String, Object>> readExcel(final InputStream is, final int sheetIndex, final int beginRowIndex, final int beginColIndex, final int endColIndex,
                                                      final String[] fieldNameArr, final Function<Row, Boolean> rowFunction, final Function<Cell, Object> cellFunction) {
        return readExcel(is, sheetIndex, beginRowIndex, beginColIndex, endColIndex, fieldNameArr, null, rowFunction, cellFunction);
    }

    public static List<Map<String, Object>> readExcel(final InputStream is, final int sheetIndex, final int beginRowIndex, final int beginColIndex, final int endColIndex,
                                                      final String[] fieldNameArr, final String[] heads, final Function<Row, Boolean> rowFunction, final Function<Cell, Object> cellFunction) {
        List<List> dataList = readExcel(heads, is, sheetIndex, beginRowIndex, beginColIndex, endColIndex, rowFunction, cellFunction);
        return parseToJsonArrayData(dataList, fieldNameArr);
    }

    public static List<Map<String, Object>> readExcel(final InputStream is, final int sheetIndex, final int beginRowIndex, final int beginColIndex, final int endColIndex,
                                                      final String[] fieldNameArr, final String[] heads, Class[] clazzs, final Function<Row, Boolean> rowFunction, final MultiFunction<Cell, Class, String, Object> cellFunction) {
        return readExcel(is, sheetIndex, beginRowIndex, beginColIndex, endColIndex, maxSize, fieldNameArr, heads, clazzs, rowFunction, cellFunction);
    }

    public static List<Map<String, Object>> readExcel(final InputStream is, final int sheetIndex, final int beginRowIndex, final int beginColIndex, final int endColIndex, final int maxSize,
                                                      final String[] fieldNameArr, final String[] heads, Class[] clazzs, final Function<Row, Boolean> rowFunction, final MultiFunction<Cell, Class, String, Object> cellFunction) {
        List<List> dataList = readExcel(heads, clazzs, is, sheetIndex, beginRowIndex, beginColIndex, endColIndex, maxSize, rowFunction, cellFunction);
        return parseToJsonArrayData(dataList, fieldNameArr);
    }

    private static List<Map<String, Object>> parseToJsonArrayData(List<List> dataList, final String[] fieldNameArr) {
        List<Map<String, Object>> collect = dataList.stream().map(data -> {
            Map<String, Object> jsonObject = new LinkedHashMap<>();
            for (int i = 0; i <= data.size() - 1; i++) {
                jsonObject.put(fieldNameArr[i], data.get(i));
            }
            return jsonObject;
        }).collect(Collectors.toList());
        dataList = null;
        return collect;
    }

    /**
     * 导出大数据量excel
     *
     * @param dataList 数据集合
     * @return
     */
    public static Workbook exportBigExcel(List<List> dataList) {
        Workbook workBook = new SXSSFWorkbook();
        Sheet sheet = workBook.createSheet();
        CellStyle cellStyle = workBook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        for (int i = 0; i <= dataList.size() - 1; i++) {
            Row row = sheet.createRow(i);
            List innerDataList = dataList.get(i);
            for (int j = 0; j <= innerDataList.size() - 1; j++) {
                Cell curCell = row.createCell(j);
                inputValue(curCell, innerDataList.get(j));
            }
        }
        return workBook;
    }


   /* public static void main(String[] args) {
        List<List> dataList= Arrays.asList(
                Arrays.asList("a","b","c","d","a","b","c","d"),
                Arrays.asList("a","b","c","d","a","b","c","d","1","3"),
                Arrays.asList("a","b","c","d","a","b","c","d","1","3"),
                Arrays.asList("a","b","c","d","a","b","c","d","1","3")
        );
        overWriteExcel(Paths.get("F:\\360MoveData\\Users\\Administrator\\Desktop\\车辆信息.xls"),1,1,1,null,dataList);
        Map<String, Integer> map2 = SpecialField.getMap("VIN", 17, "用户名", 22);
        map2.forEach((k,v) -> {
            System.out.println("key : " + k + "; val : " + v);
        });
        String format = sdf.format(new Date());
        System.out.println(format);
    }*/

    public static class SpecialField {

        /**
         * 字段定长
         */
        public static Map<String, Integer> map = new HashMap<>();

        static {
            // 比较的时候转成大写 str.toUpperCase()
            map.put("VIN", 17);
            map.put("用户名", 30);
            map.put("手机号码", 11);
            map.put("邮箱", 30);
            map.put("工程车型", 11);
            map.put("创建时间", 20);
            map.put("更新时间", 20);
            map.put("创建人", 20);
            map.put("更新人", 20);
            map.put("平台名称", 10);
        }

        /**
         * 一个key，一个val
         * 列：Map<String, Integer> map2 = SpecialField.getMap("VIN", 17, "用户名", 22);
         *
         * @param fields
         * @return
         */
        public static Map<String, Integer> getMap(Object... fields) {
            Map<String, Integer> fieldMap = new HashMap<>();
            for (int i = 0; i < fields.length; i = i + 2) {
                fieldMap.put(fields[i].toString(), Integer.valueOf(fields[i + 1].toString()));
            }
            return fieldMap;
        }

    }

    public static void main(String[] args) {
        File file = new File(PathUtil.getPath() + Constant.FilesDirectory.DOWNLOAD + "test.xlsx");

        try (InputStream inp = new FileInputStream(file)) {
            Workbook wb = WorkbookFactory.create(inp);
            if (1 > wb.getNumberOfSheets()) {
                return;
            }
            Sheet sheet = wb.getSheetAt(0);
            // 获取最后一行
            int endRow = sheet.getLastRowNum() + 1;
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            System.out.println(endRow + " - " + physicalNumberOfRows);
            // Write the output to a file

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
