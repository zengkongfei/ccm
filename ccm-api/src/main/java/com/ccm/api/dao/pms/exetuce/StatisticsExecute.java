package com.ccm.api.dao.pms.exetuce;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.ccm.api.dao.pms.bean.StatisticsBean;
import com.ccm.api.dao.pms.form.StatisticsForm;
import com.ccm.api.dao.pms.utils.DateUtils;
import com.ccm.api.dao.pms.utils.SystemData;

public class StatisticsExecute {
	private StatisticsBean statisticsBean;
	private static DecimalFormat df = new DecimalFormat("0.00"); 
	
	public StatisticsExecute(){
		statisticsBean = new StatisticsBean();
	}
	
	public String getStatisticsData(StatisticsForm statisticsForm, SystemData systemData){
		int type = statisticsForm.getType();
		String fromDate = statisticsForm.getFromDate();
		String toDate = statisticsForm.getTodate();
		String hotelid = systemData.getHotelid();
		try{
			List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
			if(statisticsForm.getHotelid().isEmpty()){
				dataList = statisticsBean.getStatisticsData(type, fromDate, toDate, systemData.getHotelidSqlList(), true);
			}else{
				dataList = statisticsBean.getStatisticsData(type, fromDate, toDate, statisticsForm.getHotelid(), false);
			}
			int roomSum = statisticsBean.getMaxRoomNum(systemData.getHotelidSqlList());
			
			Map<String, Object> dataMap = new HashMap<String, Object>();
			if(type == 3){
				dataMap.put("statData", calculateDataByYear(dataList, roomSum, type, fromDate));
			}else{
				dataMap.put("statData", calculateDataByDay(dataList, roomSum, type, fromDate));
			}
			dataMap.put("result", "T");
			return JSON.toJSONStringWithDateFormat(dataMap, "yyyy-MM-dd");
		}catch(Exception e){
			e.printStackTrace();
			return "{\"result\":\"F\"}";
		}
//		return "";
	}
	private List<Map<String, Object>> calculateDataByYear(List<Map<String, Object>> dataList,int roomSum, int type , String fromDate) throws ParseException{
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		int typeDaySum = getTypeDays(type, fromDate) + 1;//月份从1月开始算起，先加1
		String year = fromDate.substring(0, 5);
		int month = 1;
		while(typeDaySum != month){
			boolean has = false;
			for(Map<String, Object> map : dataList){
				int dbMonth = Integer.parseInt(map.get("dt").toString());
				if(dbMonth == month){
					int rommSum = roomSum * getMonthMaxDays(year, month+"");
					int depRoomSum = map.get("rsum")==null ? 0 : Integer.parseInt(map.get("rsum").toString());
					//Double percentage = rommSum==0 ? 0.00d : depRoomSum/rommSum;
					map.put("totalRoom", rommSum);
					map.put("percentage", df.format(Double.valueOf(depRoomSum)/rommSum * 100));
					mapList.add(map);
					has = true;
					break;
				}
			}
			if(!has){
				int rommSum = roomSum * getMonthMaxDays(year, month+"");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dt", month);
				map.put("totalRoom", rommSum);
				map.put("rsum", "0");
				map.put("percentage", 0.00);
				map.put("rateSum", "0.00");
				mapList.add(map);
			}
			month++;
		}
		return mapList;
	}
	
	private List<Map<String, Object>> calculateDataByDay(List<Map<String, Object>> dataList,int roomSum, int type , String fromDate) throws ParseException{
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		Date fdt = DateUtils.parse(fromDate, "yyyy-MM-dd");
		int typeDaySum = getTypeDays(type, fromDate);
		
		int day = 0;
		while(typeDaySum != day){
			boolean has = false;
			for(Map<String, Object> map : dataList){
				Date dt = (Date) map.get("dt");
				if(DateUtils.isSameDay(fdt, dt)){
					dataList.remove(map);
					int rommSum = roomSum;
					int depRoomSum = map.get("rsum")==null ? 0 : Integer.parseInt(map.get("rsum").toString());
					String percentage = "0.00";
					if(rommSum!=0){
						percentage = df.format(Double.valueOf(depRoomSum)/rommSum * 100);
					}
					map.put("totalRoom", rommSum);
					map.put("percentage", percentage);
					if(map.get("rateSum") == null){
						map.put("rateSum", "0.00");
					}
					mapList.add(map);
					has = true;
					break;
				}
			}
			if(!has){
				int rommSum = roomSum;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dt", fdt);
				map.put("totalRoom", rommSum);
				map.put("rsum", "0");
				map.put("percentage", "0.00");
				map.put("rateSum", "0.00");
				mapList.add(map);
			}
			fdt = DateUtils.add(fdt, DateUtils.DAY, 1);
			day++;
		}
		return mapList;
	}
	
	private int getMonthMaxDays(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dd = sdf.parse(date);
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	private int getMonthMaxDays(String year, String month) throws ParseException{
		String date = "";
		if(month.length()==1){
			date = year + "-0" + month + "-01";
		}else{
			date = year + "-" + month + "-01";
		}
		return getMonthMaxDays(date);
	}
	private int getTypeDays(int type, String date) throws ParseException{
		switch(type){
		case 0://天
			return 1;
		case 1://周
			return 7;
		case 2://月
			return getMonthMaxDays(date);
		case 3://年
			return 12;
		default:
			return 0;
		}
	}
	
}
