package com.brioal.commonjava;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        ExcelCommonUtil excelCommonUtil = new ExcelCommonUtil("2019发明人奖获奖项目清单");
        excelCommonUtil.addDefaultBigTitleCell("导出时间:2019-12-08",2,10);
        excelCommonUtil.breakRow();
        excelCommonUtil.addDefaultDescCell("今天是星期一",1,10);
        excelCommonUtil.breakRow(1,10);
        excelCommonUtil.addDefaultHeadCell("A啊啊啊啊啊啊啊啊啊啊啊");
        excelCommonUtil.addDefaultHeadCell("Bgjhgjgj");
        excelCommonUtil.addDefaultHeadCell("C");
        excelCommonUtil.addDefaultHeadCell("D");
        excelCommonUtil.addDefaultHeadCell("E");
        excelCommonUtil.addDefaultHeadCell("F");
        excelCommonUtil.addDefaultHeadCell("啊啊啊啊啊啊啊啊啊啊啊啊啊G");
        excelCommonUtil.breakRow(1,1);
        excelCommonUtil.addDefaultContentCell("1");
        excelCommonUtil.addDefaultContentCell("2");
        excelCommonUtil.addDefaultContentCell("3");
        excelCommonUtil.addDefaultContentCell("4");
        excelCommonUtil.addDefaultContentCell("5");
        excelCommonUtil.addDefaultContentCell("6");

        File targetFile = new File("1.xlsx");
        targetFile = excelCommonUtil.saveFile(targetFile);


    }
}
