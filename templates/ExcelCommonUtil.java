

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;


import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


public class ExcelCommonUtil {


    // workBook
    private XSSFWorkbook workbook;
    // Sheet
    private XSSFSheet sheet;

    // Sheet名称
    private String sheetName = "sheet1";
    // 当前行
    private int rowIndex = 0;
    // 当前列
    private int colIndex = 0;
    // 行数据
    private XSSFRow row;
    // 列数据
    private XSSFCell cell;
    // 最大列数
    private int maxCol;


    public ExcelCommonUtil(String sheetName) {
        this.sheetName = sheetName;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
    }

    public ExcelCommonUtil() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
    }

    /**
     * 根据配置获取样式
     *
     * @param fontBold
     * @param fontSize
     * @param horizontalAlignment
     * @param verticalAlignment
     * @param border
     * @return
     */
    public XSSFCellStyle getStyle(boolean fontBold, int fontSize, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, boolean border) {
        XSSFCellStyle styleTitle = workbook.createCellStyle();
        styleTitle.setFont(getFront(fontBold, fontSize));
        styleTitle.setAlignment(horizontalAlignment);
        styleTitle.setVerticalAlignment(verticalAlignment);
        if (border) {
            // 设置边框
            styleTitle.setBorderBottom(BorderStyle.THIN);
            styleTitle.setBorderTop(BorderStyle.THIN);
            styleTitle.setBorderLeft(BorderStyle.THIN);
            styleTitle.setBorderRight(BorderStyle.THIN);
            styleTitle.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, XSSFColor.toXSSFColor(new XSSFColor(Color.BLACK)));
            styleTitle.setBorderColor(XSSFCellBorder.BorderSide.TOP, XSSFColor.toXSSFColor(new XSSFColor(Color.BLACK)));
            styleTitle.setBorderColor(XSSFCellBorder.BorderSide.LEFT, XSSFColor.toXSSFColor(new XSSFColor(Color.BLACK)));
            styleTitle.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, XSSFColor.toXSSFColor(new XSSFColor(Color.BLACK)));

        }
        return styleTitle;
    }

    /**
     * 获取字体
     *
     * @param bold
     * @param size
     * @return
     */
    public XSSFFont getFront(boolean bold, int size) {
        XSSFFont fontHead = workbook.createFont();
        fontHead.setBold(bold);
        fontHead.setFontHeightInPoints((short) size);
        return fontHead;
    }

    /**
     * 返回默认的大标题样式
     *
     * @return
     */
    public XSSFCellStyle getDefaultBigTitleStyle() {
        return getStyle(true, 20, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
    }

    /**
     * 获取默认的描述的样式
     *
     * @return
     */
    public XSSFCellStyle getDefaultDescStyle() {
        return getStyle(false, 15, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);
    }

    /**
     * 获取默认的标题样式
     *
     * @return
     */
    public XSSFCellStyle getDefaultHeadStyle() {
        return getStyle(true, 15, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, true);
    }


    /**
     * 获取默认的内容样式
     *
     * @return
     */
    public XSSFCellStyle getDefaultContentStyle() {
        return getStyle(false, 14, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, true);
    }

    /**
     * 添加
     *
     * @param content
     * @param rowCount
     * @param colCount
     * @param fontBold
     * @param fontSize
     * @param horizontalAlignment
     * @param verticalAlignment
     * @param border
     */
    public void addCell(String content, int rowCount, int colCount, boolean fontBold, int fontSize, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, boolean border, XSSFCellStyle style) {
        int currentRowIndex = rowIndex;
        int currentColIndex = colIndex;
        initRow();
        cell = row.createCell(colIndex);
        addCol();
        cell.setCellValue(content);
        if (style == null) {
            style = getStyle(fontBold, fontSize, horizontalAlignment, verticalAlignment, border);
        }
        cell.setCellStyle(style);
        if (rowCount > 0 && colCount > 0) {
            CellRangeAddress region = new CellRangeAddress(currentRowIndex, currentRowIndex + rowCount, currentColIndex, currentColIndex + colCount);
            addRow(rowCount);
            addCol(colCount);
            sheet.addMergedRegion(region);
        }
        // 调整列宽
        adjustWidth(content,currentColIndex);
    }

    /**
     * 添加
     *
     * @param content
     * @param rowCount
     * @param colCount
     */
    public void addCell(String content, int rowCount, int colCount, XSSFCellStyle style) {
        addCell(content, rowCount, colCount, false, 0, null, null, false, style);
    }


    /**
     * 添加默认的表头
     *
     * @param content
     */
    public void addDefaultHeadCell(String content) {
        addCell(content, 0, 0, false, 0, null, null, false, getDefaultHeadStyle());
    }

    /**
     * 添加默认的表头
     *
     * @param contents
     */
    public void addDefaultHeadCells(String [] contents) {
        for (int i = 0; i < contents.length; i++) {
            addCell(contents[i], 0, 0, false, 0, null, null, false, getDefaultHeadStyle());
        }
        newLine();
    } /**
     * 添加默认的表头
     *
     * @param contents
     */
    public void addDefaultContentCells(String [] contents) {
        for (int i = 0; i < contents.length; i++) {
            addCell(contents[i], 0, 0, false, 0, null, null, false, getDefaultContentStyle());
        }
        newLine();
    }

    /**
     * 添加默认的表头
     *
     * @param content
     */
    public void addDefaultBigTitleCell(String content, int rowCount, int colCount) {
        addCell(content, rowCount, colCount, getDefaultBigTitleStyle());
        newLine();
    }

    /**
     * 添加默认的内容
     *
     * @param content
     */
    public void addDefaultContentCell(String content) {
        addCell(content, 0, 0, false, 0, null, null, false, getDefaultContentStyle());
    }

    /**
     * 添加默认的描述
     *
     * @param content
     */
    public void addDefaultDescCell(String content, int rowCount, int colCount) {
        addCell(content, rowCount, colCount, false, 0, null, null, false, getDefaultDescStyle());
         newLine();
    }

    /**
     * 换行
     */
    public void breakRow(int rowCount, int colCount) {
        addDefaultDescCell("", rowCount, colCount);
        colIndex = 0;
        row = sheet.createRow(rowIndex);
    }
    /**
     * 换行
     */
    public void newLine() {
        addRow();
        colIndex = 0;
        row = sheet.createRow(rowIndex);
    }

    /**
     * 默认换行
     * 以最多的列数
     */
    public void breakRow() {
        addDefaultDescCell("", 1, maxCol);
        colIndex = 0;
        row = sheet.createRow(rowIndex);
    }


    /**
     * 初始化行
     */
    public void initRow() {
        if (row != null) {
            return;
        }
        row = sheet.createRow(rowIndex);
        addRow();
        colIndex = 0;
    }


    /**
     * 保存文件
     *
     * @param file
     * @return
     */
    File saveFile(File file) {
        // 保存文件
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(file);
            workbook.write(fout);
            fout.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 添加行
     *
     * @param row
     */
    private void addRow(int row) {
        rowIndex += row;
    }

    /**
     * 添加行
     */
    private void addRow() {
        addRow(1);
    }

    /**
     * 添加列
     *
     * @param col
     */
    private void addCol(int col) {
        colIndex += col;
        if (colIndex>maxCol){
            maxCol = colIndex;
        }
    }

    /**
     * 添加列
     */
    private void addCol() {
        addCol(1);
    }


    /**
     * 计算内容的宽度
     * @param str
     * @return
     */
    public void adjustWidth(String str,int index){
        if (str == null) {
            return;
        }
        int length = str.length();
        int currentWidth  = (int) ((length * 2.2) * 256);
        int width  = sheet.getColumnWidth(index);
        if (currentWidth>width){
            sheet.setColumnWidth(index, currentWidth);
        }
    }

    /**
     * 返回工作簿
     * @return
     */
    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

}
