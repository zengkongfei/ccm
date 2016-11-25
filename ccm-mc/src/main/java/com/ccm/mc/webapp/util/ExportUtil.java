package com.ccm.mc.webapp.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.util.StringUtils;

import com.ccm.api.common.Column;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

public class ExportUtil {

	/**
	 * 以年月日时分秒毫秒+4位随机数的格式来创建一个文件名，不带扩展名
	 * 
	 * @return 文件名
	 */
	public static String createFileName(String prefix) {

		StringBuffer sb = new StringBuffer();
		if (StringUtils.hasText(prefix)) {
			sb.append(prefix + "-");
		}

		Date date = DateUtil.currentDateTime();
		// 获取年月日时分秒
		sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(date));
		// 毫秒
		String milli = String.valueOf(date.getTime() % 1000);
		while (milli.length() < 3) {
			milli = "0" + milli;
		}
		sb.append(milli);
		// 四位随机数
		String rondom = String.valueOf(new Random().nextInt(10000));
		while (rondom.length() < 4) {
			rondom = "0" + rondom;
		}
		sb.append(rondom);
		return sb.toString();
	}

	public static HSSFWorkbook createExcel(String sheetName, String[] colName, List<String[]> values) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet(sheetName);

		// 创建表头
		Row headerRow = sheet.createRow(0);
		int i = 0;
		int rowIndex = 1;

		for (String name : colName) {
			Cell cell = headerRow.createCell(i++);
			cell.setCellValue(name);
		}
		for (String[] objs : values) {
			Row valueRow = sheet.createRow(rowIndex);
			for (i = 0; i < objs.length; i++) {
				Cell cell = valueRow.createCell(i);
				cell.setCellValue(objs[i]);
			}
			rowIndex++;

		}
		//自动调节宽度
        for (int j = 0; j < colName.length; j++) {
        	sheet.autoSizeColumn(j); //设置根据文本长度自动调整宽度
		}
		return workbook;
	}
	
	public static SXSSFWorkbook createExcel2(String sheetName, String[] colName, List<String[]> values) throws Exception {
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		Sheet sheet = workbook.createSheet(sheetName);

		// 创建表头
		Row headerRow = sheet.createRow(0);
		int i = 0;
		int rowIndex = 1;

		for (String name : colName) {
			Cell cell = headerRow.createCell(i++);
			cell.setCellValue(name);
		}
		for (String[] objs : values) {
			Row valueRow = sheet.createRow(rowIndex);
			for (i = 0; i < objs.length; i++) {
				Cell cell = valueRow.createCell(i);
				cell.setCellValue(objs[i]);
			}
			rowIndex++;

		}
		
		return workbook;
    }
	/**
	 * 
	 * @param sheetName
	 * @param colName
	 * @param values
	 * @param tail 尾部增加的内容
	 * @return
	 * @throws Exception
	 */
	public static SXSSFWorkbook createExcelTail(String sheetName, String[] colName, List<String[]> values,
											String[] tail) throws Exception {
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		Sheet sheet = workbook.createSheet(sheetName);
		
		int i = 0;
		int rowIndex = 1;
		
		//创建表头
		Row headerRow = sheet.createRow(0);
		for (String name : colName) {
			Cell cell = headerRow.createCell(i++);
			cell.setCellValue(name);
		}
		//添加内容
		for (String[] objs : values) {
			Row valueRow = sheet.createRow(rowIndex);
			for (i = 0; i < objs.length; i++) {
				Cell cell = valueRow.createCell(i);
				cell.setCellValue(objs[i]);
			}
			rowIndex++;
		}
		
		//添加尾部
		for (String t : tail) {
			Row valueRow = sheet.createRow(rowIndex);
			for (i = 0; i < tail.length; i++) {
				Cell cell = valueRow.createCell(i);
				cell.setCellValue(tail[i]);
			}
		}
		
		return workbook;
    }
	
	public static SXSSFWorkbook createExcel_2007(String sheetName,List values) throws Exception {
	    SXSSFWorkbook workbook = new SXSSFWorkbook();
	    Sheet sheet = workbook.createSheet(sheetName);

        //创建表头
        Row headerRow = sheet.createRow(0);
        int i = 0;
        int rowIndex = 1;

        Object object = CommonUtil.isNotEmpty(values) ? values.get(0) : new Object();
        Field[] fd = object.getClass().getDeclaredFields();
        
        List<Field> exportField = new ArrayList<Field>();
        for (Field f : fd) {
            Column c = f.getAnnotation(Column.class);
            if(c!=null && StringUtils.hasText(c.title())){
            	Cell cell = headerRow.createCell(i++);
                cell.setCellValue(c.title());
                exportField.add(f);
            }
        }
        
        for (Object obj : values) {
            Class<? extends Object> clazz = obj.getClass();
            Row valueRow = sheet.createRow(rowIndex);
            for (i = 0; i < exportField.size(); i++) {
                Field field = exportField.get(i);
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object value = getMethod.invoke(obj);
                Cell cell = valueRow.createCell(i);
                if(CommonUtil.isNotEmpty(value)){
                    if (field.getType() == Date.class) {
                        value = DateUtil.convertDateTimeToString((Date) value);
                    }
                    cell.setCellValue(value+"");
                }
            }
            rowIndex++;
        }
        //自动调节宽度
        for (int j = 0; j < exportField.size(); j++) {
        	sheet.autoSizeColumn(j); //设置根据文本长度自动调整宽度
		}
        
        return workbook;
    }
}
