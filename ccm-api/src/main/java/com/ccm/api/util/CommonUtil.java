/**
 * 
 */
package com.ccm.api.util;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.model.constant.OXIConstant;
import com.ccm.api.model.constant.ProjectConfigConstant;
import com.ccm.api.model.constant.Sex;
import com.ccm.api.model.roomType.vo.RoomTypeVO;

/**
 * @author Jenny
 * 
 */
public final class CommonUtil {

	public static List<String[]> transList = new ArrayList<String[]>();
	static {
		transList.add(new String[] { "大床", "KingBed" });
		transList.add(new String[] { "双床", "TwinBed" });
		transList.add(new String[] { "高楼层", "High Floor" });
		transList.add(new String[] { "低楼层", "Low Floor" });
		transList.add(new String[] { "安静房间", "Quite Room" });
		transList.add(new String[] { "不吸烟", "Non Smoking" });
		transList.add(new String[] { "吸烟", "Smoking" });
		transList.add(new String[] { "晚到店", "Later Arrival" });
		transList.add(new String[] { "单人床", "Single Bed" });
	}

	/**
	 * 根据定义的中英将参数替换成英文
	 * 
	 * @param str
	 * @return
	 */
	public static String transStr(String str) {
		if (str != null && !"".equals(str)) {
			for (String[] arr : transList) {
				str = str.replaceAll(arr[0], arr[1]);
			}
		}
		return str;
	}

	/**
	 * 产生2,3,6随机数
	 * 
	 * @param n
	 *            :表示几位
	 * @return
	 */
	public static int createRandomNum(int n) {
		Random rand = new Random();

		if (n == 3) {
			int ro = rand.nextInt(999);
			while (ro < 100)
				ro += ro * 10;
			return ro;
		} else if (n == 4) {
			int ro = rand.nextInt(9999);
			while (ro < 1000)
				ro += ro * 10;
			return ro;

		} else {
			// 默认是六位随机数
			int ro = rand.nextInt(999999);
			while (ro < 100000)
				ro += ro * 10;
			return ro;
		}
	}

	// 计算两个时间点之间秒钟差
	public static long calInterSec(String src) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			Date d1 = df.parse(src);
			Date d2 = DateUtil.currentDateTime();
			long diff = (d2.getTime() - d1.getTime()) / 1000;
			return diff;
		} catch (Exception e) {
			return 0l;
		}
	}

	// 计算两个时间点之间毫秒差
	public static long calInterMill(Date date) {

		try {
			Calendar now = Calendar.getInstance();
			now.setTime(date);
			now.add(Calendar.HOUR_OF_DAY, 2);
			Date start = DateUtil.currentDateTime();
			long diff = (now.getTimeInMillis() - start.getTime()) / 1000;
			return diff;
		} catch (Exception e) {
			return 0l;
		}
	}

	/**
	 * 产生UUIDD
	 * 
	 * @return
	 */
	public static String generatePrimaryKey() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 获取字符的所占字节长度;中文占两个字符，其它是一个
	 * 
	 * @param str
	 * @return
	 */
	public static int getStringByteLength(String str) {
		if (StringUtils.hasText(str)) {
			try {
				return str.getBytes("GBK").length;
			} catch (UnsupportedEncodingException e) {
				return 0;
			}
		}
		return 0;
	}

	/** 获取关键异常信息 */
	public static String getExceptionMsg(Exception e, String... strs) {
		if (e == null || strs == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(e.getMessage()).append("\n").append(e.getClass()).append("\n");
		for (StackTraceElement elem : e.getStackTrace()) {
			for (String string : strs) {
				if (elem.toString().indexOf(string) > 0) {
					sb.append(elem).append("\n");
				}
			}
		}
		return sb.toString();
	}

	public static String join(Object[] array, String delimiter) {
		if (array == null) {
			throw new IllegalArgumentException();
		}
		if (array.length == 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for (Object item : array) {
			builder.append(item.toString() + delimiter);
		}
		builder.delete(builder.length() - delimiter.length(), builder.length());
		return builder.toString();
	}

	/**
	 * 将集合中的内容以delimiter为间隔拼接字符串
	 * 
	 * @param list
	 * @param delimiter
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String join(Collection list, String delimiter) {
		if (list == null) {
			throw new IllegalArgumentException();
		}
		return join(list.toArray(), delimiter);
	}
	/**
	 * 将集合中的内容以逗号为间隔拼接字符串
	 * 
	 * @param list
	 * @param delimiter
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String join(Collection list) {
		return join(list,",");
	}
	/**
	 * 提供字符串，集合，数组，map等常见对象判空处理
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof String) {
			String str = (String) obj;
			return "".equals(str.trim());
		}
		if (obj instanceof Number) {
			Number num = (Number) obj;
			return num.byteValue() == 0;
		}
		if (obj instanceof Collection) {
			Collection col = (Collection) obj;
			return col.isEmpty();
		}
		if (obj instanceof Map) {
			Map map = (Map) obj;
			return map.isEmpty();
		}
		if (obj.getClass().getSimpleName().endsWith("[]")) {
			List<Object> list = Arrays.asList(obj);
			Object[] objs = (Object[]) list.get(0);
			return objs.length == 0;
		}
		return false;
	}

	/**
	 * 提供字符串，集合，数组，map等常见对象判空处理
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	/**
	 * @return String
	 */
	public static synchronized String generateSequenceNo() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
		return dateFormat.format(Calendar.getInstance().getTime());
	}

	/**
	 * 判断是否存在中文字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean validateIsGBK(String str) {
		if (CommonUtil.isEmpty(str))
			return false;
		// 是否存在中文字符的标示
		boolean isGBK = false;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			// 如果为中文字符
			if (c > +256) {
				isGBK = true;
				break;
			}
		}
		return isGBK;
	}

	public static String getSex(String nameTitle) {
		String sex = Sex.UNKNOWN;
		if (OXIConstant.nameTitle.equals(nameTitle)) {
			sex = Sex.MALE;
		} else if ("MS.".equals(nameTitle)) {
			sex = Sex.FEMALE;
		}
		return sex;
	}

	/**
	 * 过滤字符串中所有的HTML标签
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static String filterHtml(String htmlStr) {
		if (CommonUtil.isEmpty(htmlStr))
			return "";
		// 去掉所有html元素,
		String str = htmlStr.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "");

		return str;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	/**根据指定大小切分成多个list*/
	public static List<List> splitList(List list, int unit) {
		if (CommonUtil.isEmpty(list)) {
			return new ArrayList<List>();
		}
		if (unit == 0) {
			return list;
		}
		int totalCount = list.size();
		int threadCount = (totalCount - 1) / unit + 1;
		List<List> objList = new ArrayList<List>();
		for (int i = 0; i < threadCount; i++) {
			final int beginIndex = i * unit;
			final int endIndex = (i + 1) * unit > totalCount ? totalCount : (i + 1) * unit;
			List pcList = list.subList(beginIndex, endIndex);
			objList.add(pcList);
		}
		return objList;
	}

	public static Long oddEven(boolean odd, Long result) {
		// 奇数，自增2.若result为偶数增1变为奇数后再自增2
		if (odd) {
			if (isOdd(result)) {
				result = result + 2;
			} else {
				result = result + 3;
			}
		}
		// 偶数，自增2.若result为奇数增1变为偶数后再自增2
		else {
			if (isOdd(result)) {
				result = result + 3;
			} else {
				result = result + 2;
			}
		}
		return result;
	}

	/**
	 * 判断是否是奇数
	 * 
	 * @param num
	 * @return
	 */
	private static boolean isOdd(Long num) {
		if (num % 2 == 0) {
			return false;
		} else {
			return true;
		}
	}
	/**深度复制*/
	public static final Object objectCopy(Object oldObj) {  
        Object newObj = null;  
        ByteArrayOutputStream bo = null;
        ObjectOutputStream oo = null;
        ByteArrayInputStream bi = null;
        ObjectInputStream oi= null;
        try {  
        	bo = new ByteArrayOutputStream();  
            oo = new ObjectOutputStream(bo);  
            oo.writeObject(oldObj);//源对象  
            bi = new ByteArrayInputStream(bo.toByteArray());  
            oi= new ObjectInputStream(bi);  
            newObj = oi.readObject();//目标对象  
        } catch (Exception e) {  
            e.printStackTrace();
        }finally{
        	try {
        		if(oi != null)
				oi.close();
        		if(bi != null)
				bi.close();
        		if(oo != null)
	            oo.close();
        		if(bo != null)
	            bo.close();
	            bo = null;
	            oo = null;
	            bi = null;
	            oi = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        return newObj;  
    }
	
	
	/**
	  * 效验  str,有匹配到sql关键字返回true
	  * @param str
	  * @return
	  */
	public static boolean sqlValidate(String str) {
		str = str.toLowerCase();// 统一转为小写
		String badStr = "delete|update|drop|exec|truncate|insert";// 过滤掉的sql关键字
		String[] badStrs = badStr.split("\\|");
		for (int i = 0; i < badStrs.length; i++) {
			if (str.indexOf(badStrs[i]) >= 0) {
				return true;
			}
		}
		return false;
	}
	
	
	public static String getCronExpressionFromDB(long time){
		if(time>=28*24*60*60){
			return "0 0 0 1/28 * ? ";
		}
		long minute = time/60;
		
		if(minute<60){
			if(minute==0){
				return "0/"+time+" "+"*"+" "+"* * * ?";
			}
			long second =time%60;
			if(second==0){
				return "0"+" "+"0/"+minute+" "+"* * * ?";
			}
			//分秒
			return "0/"+second+" "+"0/"+minute+" "+"* * * ?";
		}else{
			//时分秒
			long hour = time/(60*60);
			if(hour<24){
				if(time%(60*60)==0){
					return "0 0 "+"0/"+hour+" * * ?";
				}
				if(time%(60)==0){
					long second = (time - hour * 3600 )/60;
					return "0 "+"0/"+second+" "+"0/"+hour+" * ?";
				}
				long second = (time - hour * 3600 )%60;
				minute = minute%60;
				if(minute==0){
					if(second==0){
						return "0 "+"0"+" "+"1/"+hour+" * * ?";
					}
					return "0/"+second+" "+"0"+" "+"0/"+hour+" * * ?";
				}
				
				return "0/"+second+" "+"0/"+minute+" "+"0/"+hour+" * * ?";
			}else{
				long day = time/(60*60*24);
				
				if(time%(60*60*24)==0){
					return "0"+" "+"0"+" "+"0"+" "+"0/"+day+"* ?";
				}
				//每天一次
				if(day>31){
					return "0 0 0 0 1/1 ? ";
				}else{
					hour = (time/(60*60))%24;
					if(hour==0){
						long second = (time - hour * 3600*24 )%60;
						if(second==0){
							minute = minute%60;
							return "0 "+"0/"+minute+" 0 "+"1/"+day+" * ? ";
						}else{
							minute = minute%60;
							if(minute!=0){
								return "0/"+second+" "+"0/"+minute+" * "+"1/"+day+" * ?";
							}
						}
						return "0/"+second+" 0 0 "+"1/"+day+" * ? ";
					}
					long second = (time - hour * 3600*24 )%60;
					minute = minute%60;
					
					return "0/"+second+" "+"0/"+minute+" "+"0/"+hour+" "+"1/"+day+" * ?";
				}
			}
		} 
	}

	public static boolean getIsProduction(){
		if(ProjectConfigConstant.isProduction.equalsIgnoreCase("0")){
			return false;
		}
		if(ProjectConfigConstant.isProduction.equalsIgnoreCase("1")){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取对象属性名称的get方法值
	 * @param Object
	 * @param fieldName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object invokeGetMethodValue(Object obj, String fieldName) {
		try {
			Class clazz = obj.getClass();
			PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
			Method getMethod = pd.getReadMethod();// 获得get方法

			if (pd != null) {
				Object o = getMethod.invoke(obj);// 执行get方法返回一个Object
				return o;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * object 中属性名=属性值组成的md5
	 * 
	 * @param str 属性名数组
	 * @return
	 */
	public static String objectMd5(String str,Object obj) throws Exception{
		if(!StringUtils.hasText(str)){
			return null;
		}
		JSONObject json = JSONObject.parseObject(str);
		Set<String> keyset = json.keySet();
		List<String> list = new ArrayList<String>();
		list.addAll(keyset);
		Collections.sort(list);
		String result ="";
		for(String key:list){
			Boolean bool = json.getBoolean(key);
			if(bool){
				result= result + key+ "="+ CommonUtil.invokeGetMethodValue(obj, key);
			}
		}
		return MD5Util.encode(result);
	}
	
	
	/**
	 * 修改对象中的属性值
	 * 
	 * @param str
	 *            属性名数组
	 * @return
	 */
	public static Object changeObject(JSONObject json, Object obj)throws Exception {
		if (json == null) {
			return obj;
		}
		Field[] f = obj.getClass().getDeclaredFields();
		for (int i = 0; i < f.length; i++) {
			Field ft = f[i];
			if (json.containsKey(ft.getName())) {
				Boolean bool = json.getBoolean(ft.getName());
				if(!bool){
					ft.setAccessible(true);
					ft.set(obj, null);
					continue;
				}
			}
		}
		return obj;
	}
	
	/**
	 * 生成几位随机码
	 * @param length
	 * @return
	 */
	public static String getRoundCode(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString(); 
	}
	
	public static int getInt(Object obj) {
		return obj != null ? Integer.parseInt(obj.toString()) : 0;
	}
	
	/**
	 * url 默认添加http:// 的头
	 * @param url
	 * @return
	 */
	public static String getHttpReq(String url){
		if(!StringUtils.hasText(url)){
			return url;
		}
		if(url.indexOf("http://")<0 && url.indexOf("https://")<0){
			url = "http://"+url;
		}
		return url;
	}
	
	
	public static void main(String[] args) {
		String str="{'bedTypeName':'true','description':'true','maxOccupancy':'true','roomTypeCode':'true','roomTypeName':'true'}";
		String md5 = "";
		try {
			md5 = objectMd5(str, new RoomTypeVO());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(md5);
	}
	
}
