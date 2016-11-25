package com.ccm.api.service.excel;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import com.ccm.api.service.base.BaseManagerTestCase;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

public class TestExportLog extends BaseManagerTestCase{

	
	@Test
	@Rollback(false)
	public void testPoi(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		for (int i = 0; i < 30000; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("abc", "95930dc94a484e5798f7716395d88a4b");
			map.put("bcd", "95930dc94a484e5798f7716395d88a4b");
			map.put("efg", "95930dc94a484e5798f7716395d88a4b");
			map.put("efg1", "abc");
			map.put("efg2", "95930dc94a484e5798f7716395d88a4b");
			map.put("efg3", "abc");
			map.put("efg4", "abc");
			map.put("hij5", new Date());
			list.add(map);
		}
		
		SXSSFWorkbook workbook = new SXSSFWorkbook();
	    Sheet sheet = workbook.createSheet("zzzz");
        
        int rowIndex = 1;
        Long start = System.currentTimeMillis();
        for (Map<String, Object> map : list) {
            Row valueRow = sheet.createRow(rowIndex);
            int i = 0;
            for (String key : map.keySet()) {
            	Cell cell = valueRow.createCell(i);
                if(CommonUtil.isNotEmpty(map.get(key))){
                	Object value = map.get(key);
                    if (value instanceof Date) {
                    	value = DateUtil.convertDateTimeToString((Date) map.get(key));
                    }
                    cell.setCellValue(value+"");
                }
                
                i++;
			}
            rowIndex++;
        }
        
        for (int i = 0; i < 8; i++) {
        	sheet.autoSizeColumn(i); //设置根据文本长度自动调整宽度
		}
        
        Long end = System.currentTimeMillis();
        System.out.println("read:"+(end-start));
		
	}
	
	
	
}
