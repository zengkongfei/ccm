package com.ccm.api.service.dataImport.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.channel.ChannelgoodsDao;
import com.ccm.api.dao.channel.RateplanDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.roomType.RoomTypeDao;
import com.ccm.api.model.channel.ChannelGoods;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.common.vo.DictCodeChannelCodeNameVO;
import com.ccm.api.model.constant.DictName;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.ProjectConfigConstant;
import com.ccm.api.model.enume.ChannelCodeEnum;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.ratePlan.RatePlanI18n;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.common.PictureManager;
import com.ccm.api.service.dataImport.ExcelDataImportManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.roomType.RoomTypeManager;
import com.ccm.api.service.taobaoAPI2.TaobaoApiManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.PropertiesUtil;

/**
 * 酒店,房型,房价 ,房型&房价 Excel 导入数据到数据库
 * @author edwin
 */
@Service("hotelContentTemplateImport")
public class HotelContentTemplateImportImpl implements ExcelDataImportManager{
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private HotelDao hotelDao;
	@Autowired
	private RoomTypeDao roomTypeDao;
	@Autowired
	private RateplanDao ratePlanDao;
	@Autowired
	private ChannelgoodsDao channelGoodsDao;
	
	@Autowired
	private HotelManager hotelManager;
	@Autowired
	RoomTypeManager roomTypeManager ;
	@Autowired
	private ChannelgoodsManager channelgoodsManager;
	@Autowired
	private ChannelManager channelManager;
	@Autowired
    private RatePlanManager ratePlanManager;
	@Autowired
	private DictCodeManager dictCodeManager;
	@Autowired
	private PictureManager pictureManager;
	@Autowired
	private TaobaoApiManager taobaoApiManager;

	@Override
	public Map<String,Object> getExcelData(String filePath,String[] sheetNames) throws IOException {
		//创建文件对象
		File file = new File(filePath);
		//先校验是否为excel文件
		String result = this.validateIsExcel(file);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		//如果文件校验不成功
		if(!SUCCESS.equals(result)){
			resultMap.put("result", result);
			return resultMap;
		}

		//创建流对象和工作簿
		InputStream is = new FileInputStream(file);
		try {
			Workbook workbook = WorkbookFactory.create(is);
			//循环校验
			for (int j = 0; j < sheetNames.length; j++) {
				boolean notFound = true; //默认标示为没有找到
				for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
					Sheet sheet = workbook.getSheetAt(i);
					String sheet_StrName = this.getUpperStr(sheet.getSheetName());
						
					//尋找匹配的sheet頁
					if(sheetNames[j].equals(sheet_StrName)){
						notFound = false;	
					}
				}
				//如果沒有找到sheet頁
				if(notFound){
					resultMap.put("result", SHEET_NOT_FOUND);
					resultMap.put("errKey", sheetNames[j]);
					return resultMap;
				}
			}
				
			//查找sheet页并且获取数据
			for (int j = 0; j < sheetNames.length; j++) {
				for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
					Sheet sheet = workbook.getSheetAt(i);
					String sheet_StrName = this.getUpperStr(sheet.getSheetName());
						
					//尋找匹配的sheet頁
					if(sheetNames[j].equals(sheet_StrName)){
						//如果讀取酒店sheet頁
						if(SHEET_HOTEL_NAME.equals(sheetNames[j])){
							Map<String,Object> hotelMap = this.getHotelSheetData(sheet);
							//如果获取数据不成功
							if(!SUCCESS.equals(hotelMap.get("result")+""))
								return hotelMap;
							resultMap.putAll(hotelMap);
						//如果讀取房型sheet頁
						}else if(SHEET_ROOMTYPE_NAME.equals(sheetNames[j])){
							Map<String,Object> roomTypeMap = this.getRoomTypeSheetData(sheet);
							//如果获取数据不成功
							if(!SUCCESS.equals(roomTypeMap.get("result")+""))
								return roomTypeMap;
							resultMap.putAll(roomTypeMap);
						//如果讀取房價sheet頁
						}else if(SHEET_RATEPLAN_NAME.equals(sheetNames[j])){
							Map<String,Object> ratePlanMap = this.getRatePlanSheetData(sheet);
							//如果获取数据不成功
							if(!SUCCESS.equals(ratePlanMap.get("result")+""))
								return ratePlanMap;
							resultMap.putAll(ratePlanMap);
						//如果讀取產品sheet頁
						}else if(SHEET_PRODUCT_NAME.equals(sheetNames[j])){
							Map<String,Object> productMap = this.getProductSheetData(sheet);
							//如果获取数据不成功
							if(!SUCCESS.equals(productMap.get("result")+""))
								return productMap;
							resultMap.putAll(productMap);
						}
					}
				}
			}
			
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			log.info(CommonUtil.getExceptionMsg(e));
		}

//		//如果是 excel2003
//		if(this.isExcel(fileName, this.EXCEL_2003)){
//			workbook = new HSSFWorkbook(is);
//		//如果是excel2007
//		}else if(this.isExcel(fileName, this.EXCEL_2007)){
//			workbook = new XSSFWorkbook(is);
//		}

		resultMap.put("result", SUCCESS);
		return resultMap;
	}

	/**
	 * 旅店data
	 */
	private String HOTEL_DATA = "hotelData";
	/**
	 * 房型data
	 */
	private String ROOMTYPE_DATA = "roomTypeData";
	/**
	 * 房价data
	 */
	private String RATEPLAN_DATA = "ratePlanData";
	/**
	 * 产品data
	 */
	private String PRODUCT_DATA = "productData";

	/**
	 * 酒店-创建
	 */
	private String HOTEL_ACTION_NEW = "NEW";
	/**
	 * 酒店-停用
	 */
	private String HOTEL_ACTION_INACTIVE = "INACTIVE";
	
	/**
	 * 国内(文档定义为0,excel模板定义为Y)
	 */
	private Integer COUNTRY_INLAND = 0; 
	
	/**
	 * 国外(文档定义为1,excel模板定义为N)
	 */
	private Integer COUNTRY_FOREIGN= 1;
	

	/**
	 * 校验旅店sheet页的数据是否符合规范,如果校验通过则返回Map数据
	 * @param hotelSheet 酒店sheet页对象
	 * @return
	 */
	private Map<String,Object> getHotelSheetData(Sheet hotelSheet){
		
		//調用方法獲取真實的sheet頁
		//hotelSheet = getRealSheet(hotelSheet,"A3");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//如果hotelSheet页中无数据
		if(hotelSheet.getLastRowNum() == 0){
			resultMap.put("result", SHEET_EMPTY);
			resultMap.put("errKey", hotelSheet.getSheetName());
			return resultMap;
		}else if(hotelSheet.getLastRowNum() < 2 ){
			resultMap.put("result", SHEET_ERROR); //提示需要填寫兩行表頭,第三行開始填写数据
			resultMap.put("errKey", 2);
			return resultMap;
		}

		//键集合
		String[] keys = {"actionType","hidHotelId","hotelCode","crsHotelCode","hotelChineseName","hotelUsedName","domestic","country",
				"province","city","district","business","address","longitude","latitude","positionType","telephone","extend","email"};  
		//保存酒店操作列表
		List<Map<String,Object>> hotelList = new ArrayList<Map<String,Object>>();
		//循环校验数据行
		for (int i = 2 ;i <= hotelSheet.getLastRowNum() ; i++) {
			//数据行
			Row dataRow = hotelSheet.getRow(i);
			//获取每一行操作类型
			Object actType = this.getCellValue(dataRow.getCell(0)) ;
			
			//如果actionType为空
			resultMap = this.validateNull(actType,"Action Type",i+1);
			if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
				continue;
			}
			String actionType = this.getUpperStr(actType + "");
			
			//校验是否在酒店actionType之内
			resultMap = 
				this.validateInRange(actType,this.HOTEL_ACTION,"Action Type",i+1);
			if(BEYOND_RANGE_INNER.equals(resultMap.get("result")))
				return resultMap;
			
			//获取酒店code及酒店对象列表(一个code对应一家酒店)
			String hotelCode = this.getCellValue(dataRow.getCell(2)) + "";
			resultMap = this.validateNull(hotelCode,"Switch Hotel Code",i+1);
			if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
				return resultMap;
			}

			//创建酒店Map,存储一个酒店操作对象
			List<Hotel> hotels = hotelDao.getHotelByHotelCode(hotelCode);
			Map<String,Object> hotelMap = new HashMap<String, Object>();
			hotelMap.put("actionType", actionType);
			
			//如果酒店操作类型为新增
			if(this.HOTEL_ACTION_NEW.equals(actionType)){
				//如果酒店对象已存在
				if(hotels != null && hotels.size() > 0){
					//格式: 关键值重复_重复的表头名_重复的值
					resultMap.put("result", KEY_VALUE_EXISTS);
					resultMap.put("errKey", "Switch Hotel Code");
					resultMap.put("errValue", hotelCode);
					return resultMap;
				}
				//循环读取Cell值
				for (int j = 1; j < keys.length ; j++){
					hotelMap.put(keys[j], this.getCellValue(dataRow.getCell(j)));
				}
				
				//如果酒店代码为空
				resultMap = this.validateNull(hotelCode,"Switch Hotel Code",i+1);
				if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
					return resultMap;
				}
				//如果酒店名称
				resultMap = this.validateNull(hotelMap.get("hotelChineseName"),"Hotel Chinese Name",i+1);
				if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
					return resultMap;
				}
				//如果酒店地址为空
				resultMap = this.validateNull(hotelMap.get("address"),"Address",i+1);
				if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
					return resultMap;
				}
				//如果国家为空
				resultMap = this.validateNull(hotelMap.get("country"),"Country",i+1);
				if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
					return resultMap;
				}
				//如果邮件地址为空
				resultMap = this.validateNull(hotelMap.get("email"),"Email",i+1);
				if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
					return resultMap;
				}

				//校验长度
				resultMap = this.validateLength(hotelMap.get("hotelChineseName"), 60, "Hotel Chinese Name", i+1);
				if(LENGTH_OVERRANGE.equals(resultMap.get("result"))){
					return resultMap;
				}
				resultMap = this.validateLength(hotelMap.get("hotelUsedName"), 60, "Hotel Used Name", i+1);
				if(LENGTH_OVERRANGE.equals(resultMap.get("result"))){
					return resultMap;
				}
				resultMap = this.validateLength(hotelMap.get("business"), 20, "Business", i+1);
				if(LENGTH_OVERRANGE.equals(resultMap.get("result"))){
					return resultMap;
				}
				resultMap = this.validateLength(hotelMap.get("address"), 120, "Address", i+1);
				if(LENGTH_OVERRANGE.equals(resultMap.get("result"))){
					return resultMap;
				}
				resultMap = this.validateLength(hotelMap.get("longitude"), 10, "Longitude", i+1);
				if(LENGTH_OVERRANGE.equals(resultMap.get("result"))){
					return resultMap;
				}
				resultMap = this.validateLength(hotelMap.get("latitude"), 10, "Latitude", i+1);
				if(LENGTH_OVERRANGE.equals(resultMap.get("result"))){
					return resultMap;
				}
				resultMap = this.validateLength(hotelMap.get("telephone"), 30, "Telephone", i+1);
				if(LENGTH_OVERRANGE.equals(resultMap.get("result"))){
					return resultMap;
				}
				resultMap = this.validateLength(hotelMap.get("email"), 40, "Email", i+1);
				if(LENGTH_OVERRANGE.equals(resultMap.get("result"))){
					return resultMap;
				}
				
				//校验是否在坐标类型positionType之内
				resultMap = 
					this.validateInRange(hotelMap.get("positionType"), this.POSITION_TYPE,"Position_type",i+1);
				if(BEYOND_RANGE_INNER.equals(resultMap.get("result")))
					return resultMap;
				
				//先校验填写的是否国内校验
				resultMap = 
					this.validateInRange(hotelMap.get("domestic"), this.HOTEL_DOMESTIC, "Demostic", i+1);
				if(BEYOND_RANGE_INNER.equals(resultMap.get("result")))
					return resultMap;
				
				//解析Demostic(0:国内 1:国内  Y:国外 N:国内)
				if(COUNTRY_INLAND == this.getIntValue(hotelMap.get("domestic"))){
					hotelMap.put("domestic", Boolean.FALSE);
					hotelMap.put("country", "China");
					
					//校验省份-城市-地区是否匹配
					resultMap = this.validateProCityDist(
							hotelMap.get("province")+"",hotelMap.get("city")+"",hotelMap.get("district")+"");
					if(PRO_CITY_DIST_NOMATCH.equals(resultMap.get("result"))){
						resultMap.put("errRow", i+1);
						return resultMap;
					}					
					
				}else if(COUNTRY_FOREIGN == this.getIntValue(hotelMap.get("domestic"))){
					hotelMap.put("domestic", Boolean.TRUE);
				}
				
				//校验邮箱
				if(!this.validateEmail(hotelMap.get("email").toString())){
					resultMap.put("result", VALUE_FORMAT_ERROR);
					resultMap.put("errKey", "Email");
					resultMap.put("errValue", hotelMap.get("email"));
					resultMap.put("pattern", "用户名@域名(域名中包含.) 如:zhangsan@shijinet.com.cn");
					resultMap.put("errRow", (i+1));
					return resultMap;
				}
			//如果酒店操作类型为停用
			}else if(this.HOTEL_ACTION_INACTIVE.equals(actionType)){
				//如果酒店对象不存在
				if(hotels == null || hotels.size() == 0){
					//格式: 表中对象不存在_重复的表头名_重复的值
					resultMap.put("result", OBJ_NOT_EXISTS );
					resultMap.put("errKey", "Switch Hotel Code");
					resultMap.put("errValue", hotelCode);
					return resultMap;
				}
				hotelMap.put("hotelCode", hotelCode);
			}
			hotelList.add(hotelMap);
		}
		
		resultMap.put("result", SUCCESS);
		resultMap.put(this.HOTEL_DATA, hotelList);
		return resultMap;
	}
	
	/**
	 * 房型-创建
	 */
	private String ROOMTYPE_ACTION_NEW = "NEW";
	
	/**
	 * 房型-修改
	 */
	private String ROOMTYPE_ACTION_MODIFY = "MODIFY";
	
	/**
	 * 房间面积
	 */
	private String ROOM_SIZE_A = "A";
	private String ROOM_SIZE_B = "B";
	private String ROOM_SIZE_C = "C";
	private String ROOM_SIZE_D = "D";

	/**
	 * 校验房型sheet页的数据是否符合规范
	 * @param roomTypeSheet 获取房型sheet页的数据
	 * @return
	 */
	private Map<String,Object> getRoomTypeSheetData(Sheet roomTypeSheet){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		//如果roomTypeSheet页中无数据
		if(roomTypeSheet.getLastRowNum() == 0){
			resultMap.put("result", SHEET_EMPTY);
			resultMap.put("errKey", roomTypeSheet.getSheetName());
			return resultMap;
			
		}else if(roomTypeSheet.getLastRowNum() < 2 ){
			resultMap.put("result", SHEET_ERROR); //提示需要填寫兩行表頭,第三行開始填写数据
			resultMap.put("errKey", 2);
			return resultMap;
		}

		//键集合
		String[] keys = {"actionType","hotelCode","crsHotelCode","roomTypeCode","hidRoomTypeId",
				"roomTypeChineseName","maxOccupancy","size","floor","bedType","bedSize",
				"internet","amenities","guide","extend"};  
		//保存房型操作列表
		List<Map<String,Object>> roomTypeList = new ArrayList<Map<String,Object>>();
		//获取房型以及该渠道下的所有设施
		List<DictCodeChannelCodeNameVO> dictList = 
			dictCodeManager.searchCodeByChannelDictName(DictName.ROOM_AMENITY, ChannelCodeEnum.TAOBAO.getName());
		
		//循环校验数据行
		for (int i = 2 ;i <= roomTypeSheet.getLastRowNum() ; i++) {
			//数据行
			Row dataRow = roomTypeSheet.getRow(i);
			//获取每一行操作类型
			String actionType = this.getUpperStr(this.getCellValue(dataRow.getCell(0)) + "");
			
			//如果actionType为空
			resultMap = this.validateNull(actionType,"Action Type",i+1);
			if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
				continue;
			}
			
			//范围之内的校验
			resultMap = 
				this.validateInRange(this.getCellValue(dataRow.getCell(0)), this.ROOMTYPE_ACTION,"Action Type",i+1);
			if(BEYOND_RANGE_INNER.equals(resultMap.get("result")))
				return resultMap;
			//获取酒店code
			String hotelCode = this.getCellValue(dataRow.getCell(1)) + "";
			//如果hotelCode为空
			resultMap = this.validateNull(hotelCode,"Switch Hotel Code",i+1);
			if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
				return resultMap;
			}

			//获取酒店组
			List<Hotel> hotels = hotelDao.getHotelByHotelCode(hotelCode);
			if(hotels == null || hotels.size() == 0){
				//格式: 值不存在_字段名_值
				resultMap.put("result", OBJ_NOT_EXISTS );
				resultMap.put("errKey", "Switch Hotel Code");
				resultMap.put("errValue", hotelCode);
				return resultMap;
			}
			
			//获取房型code
			String roomTypeCode = this.getCellValue(dataRow.getCell(3)) + "";
			//如果roomTypeCode为空
			resultMap = this.validateNull(roomTypeCode,"Switch Room Type ID",i+1);
			if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
				return resultMap;
			}
			
			//获取房型VO
			RoomTypeVO roomTypeVo = roomTypeDao.getRoomTypeByHotelCode(roomTypeCode, hotelCode, LanguageCode.MAIN_LANGUAGE_CODE);
			
			//创建房型Map,存储一个房型操作对象
			Map<String,Object> roomTypeMap = new HashMap<String, Object>();
			roomTypeMap.put("actionType", actionType);

			//如果房型操作类型为新增/修改
			if(this.ROOMTYPE_ACTION_NEW.equals(actionType)
					||this.ROOMTYPE_ACTION_MODIFY.equals(actionType)){
				//如果房型对象已存在
				if(roomTypeVo != null ){
					//如果是新增操作
					if(this.ROOMTYPE_ACTION_NEW.equals(actionType)){
						//格式: 关键值重复_重复的表头名_重复的值
						resultMap.put("result", KEY_VALUE_EXISTS );
						resultMap.put("errKey", "Hotel Room Type ID");
						resultMap.put("errValue", roomTypeCode);
						return resultMap;
					}
					roomTypeMap.put("roomTypeId", roomTypeVo.getRoomTypeId());
					roomTypeMap.put("roomTypeMid", roomTypeVo.getRoomTypeMId());

				//如果房型对象为空,则不能进行修改
				}else if(roomTypeVo == null 
						&& this.ROOMTYPE_ACTION_MODIFY.equals(actionType)){
					//格式: 不存在
					resultMap.put("result", OBJ_NOT_EXISTS );
					resultMap.put("errKey", "Hotel Room Type ID");
					resultMap.put("errValue", roomTypeCode);
					return resultMap;
				}
				
				//如果进行数据新增,需要上传宝贝
				if(this.ROOMTYPE_ACTION_NEW.equals(actionType)){					
					//先清空图片列表
					pictureList.clear();
					String path = this.getPictureFormerPath();
					//目录在配置文件中未配置
					if(PICTURE_FORMETPATH_NOTCONFIG.equals(path)){
						//格式: 不存在
						resultMap.put("result", PICTURE_FORMETPATH_NOTCONFIG );
						return resultMap;
					//如果没有上传zip文件
					}else if(PICTURE_ZIP_NOTUPLOAD.equals(path)){
						//格式: 不存在
						resultMap.put("result", PICTURE_ZIP_NOTUPLOAD );
						resultMap.put("errKey", PropertiesUtil.getProperty(ProjectConfigConstant.pictureFormerPath));
						return resultMap;
					}
					
					//获取房型图片
					this.setPictureList(path, hotelCode);
					
					//校验图片是否查找的到
					resultMap = this.validatePicture(hotelCode, roomTypeCode, i+1);
					if(!SUCCESS.equals(resultMap.get("result")))
						return resultMap;
					
					roomTypeMap.put("pictureName", resultMap.get("pictureName"));
					roomTypeMap.put("picturePath", resultMap.get("picturePath"));
				}				
				
				//获取hotelId
				for (Hotel hotel : hotels) {
					roomTypeMap.put("hotelId", hotel.getHotelId());
					String hotelName = hotelDao.getHotelI18nChainByHotelId(hotel.getHotelId()).getHotelName();
					roomTypeMap.put("hotelName", hotelName);
					
				}
				//循环读取Cell值(不读取设施)
				for (int j = 1; j < keys.length ; j++){
					roomTypeMap.put(keys[j], this.getCellValue(dataRow.getCell(j)));
				}

				//校验非空
				resultMap = this.validateNull(roomTypeMap.get("roomTypeChineseName"),"RoomType Chinese Name",i+1);
				if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
					return resultMap;
				}
				resultMap = this.validateNull(roomTypeMap.get("size"),"Size",i+1);
				if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
					return resultMap;
				}
				resultMap = this.validateNull(roomTypeMap.get("guide"),"Guide",i+1);
				if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
					return resultMap;
				}
				resultMap = this.validateNull(roomTypeMap.get("extend"),"Extend",i+1);
				if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
					return resultMap;
				}
				
				//校验长度
				resultMap = this.validateLength(roomTypeMap.get("roomTypeChineseName"), 30, "RoomType Chinese Name", i+1);
				if(LENGTH_OVERRANGE.equals(resultMap.get("result"))){
					return resultMap;
				}
				resultMap = this.validateLength(roomTypeMap.get("floor"), 30, "Floor", i+1);
				if(LENGTH_OVERRANGE.equals(resultMap.get("result"))){
					return resultMap;
				}
				resultMap = this.validateLength(roomTypeMap.get("bedType"), 30, "Bed Type", i+1);
				if(LENGTH_OVERRANGE.equals(resultMap.get("result"))){
					return resultMap;
				}
				resultMap = this.validateLength(roomTypeMap.get("bedSize"), 30, "Bed Size", i+1);
				if(LENGTH_OVERRANGE.equals(resultMap.get("result"))){
					return resultMap;
				}
				
				//校验面积填写
				String size = roomTypeMap.get("size")+"";
				//如果填写的不是A|B|C|D
				if((this.ROOM_SIZE_A+","+this.ROOM_SIZE_B+","+this.ROOM_SIZE_C+","+this.ROOM_SIZE_D)
						.indexOf(size.toUpperCase())>=0&&size.indexOf(",")<0){
					roomTypeMap.put("size", size.toUpperCase());
				}else{
					try {
						roomTypeMap.put("size", this.getIntValue(size));
					} catch (NumberFormatException e){
						log.info(CommonUtil.getExceptionMsg(e));
						
						resultMap.put("result", VALUE_FORMAT_ERROR);
						resultMap.put("errKey", "Size");
						resultMap.put("errRow", i+1);
						resultMap.put("errValue", size);
						resultMap.put("pattern", "\\n可选值:A,B,C,D.分别代表： A:15平米以下,B:16-30平米,C:31-50平米,D:50平米以上 .\\n也可以自己定义,比如:40");
						return resultMap;
					}
				}
				//读取设施信息,
				String amenities = roomTypeMap.get("amenities") + "";
				
				//如果填写了房型设施
				if(StringUtils.isNotBlank(amenities)){
					resultMap = this.validteAmenities(amenities,dictList,"In room amenities",i+1);
					if(VALUE_NOT_INSYSTEM.equals(resultMap.get("result")))
						return resultMap;
					
					//设置设施列表
					roomTypeMap.put("amenitiesList",resultMap.get("amennitList"));
				}
				
				//范围之内的校验
				resultMap = 
					this.validateInRange(roomTypeMap.get("internet"), this.ROOMTYPE_INTERNET,"Internet",i+1);
				if(BEYOND_RANGE_INNER.equals(resultMap.get("result")))
					return resultMap;
				
				roomTypeList.add(roomTypeMap);
			}
		}
		
		resultMap.put("result", SUCCESS);
		resultMap.put(this.ROOMTYPE_DATA, roomTypeList);
		return resultMap;
	}
	
	
	/**
	 * 房价-创建
	 */
	private String RATEPLAN_ACTION_NEW = "NEW";
	/**
	 * 房价-修改
	 */
	private String RATEPLAN_ACTION_MODIFY = "MODIFY";
	
	/**
	 * 校验旅店sheet页的数据是否符合规范
	 * @param hotelSheet
	 * @return
	 */
	private Map<String,Object> getRatePlanSheetData(Sheet ratePlanSheet){
		Map<String,Object> resultMap = new HashMap<String,Object>();

		//調用方法獲取真實的sheet頁
		//ratePlanSheet = getRealSheet(ratePlanSheet,"A3");
		//如果ratePlanSheet页中无数据
		if(ratePlanSheet.getLastRowNum() == 0){
			resultMap.put("result", SHEET_EMPTY);
			resultMap.put("errKey", ratePlanSheet.getSheetName());
			return resultMap;
			
		}else if(ratePlanSheet.getLastRowNum() < 2 ){
			resultMap.put("result", SHEET_ERROR); //提示需要填寫兩行表頭,第三行開始填寫數據
			resultMap.put("errKey", 2);
			return resultMap;
		}
		//键集合
		String[] keys = {"actionType","hotelCode","crsHotelCode","ratePlanCode","crsRatePlanChineseName","paymentType",
				"breakfast","extraBreakfast","extraBreakfastCharge","taxAmount","taxPercentage","svcFeeAmount",
				"svcFeePercent","otherFee","min_adv_days","max_adv_days","startTime","endTime","cancelPolicy","extend",
				"status","ratePlanEnglishName"};  
		
		//保存房价操作列表
		List<Map<String,Object>> ratePlanList = new ArrayList<Map<String,Object>>();
		//循环校验数据行
		for (int i = 2 ;i <= ratePlanSheet.getLastRowNum() ; i++) {
			//数据行
			Row dataRow = ratePlanSheet.getRow(i);
			//获取每一行操作类型
			String actionType = this.getUpperStr(this.getCellValue(dataRow.getCell(0)) + "");
			resultMap = this.validateNull(actionType,"Action Type",i+1);
			if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
				continue;
			}
			
			//范围之内的校验
			resultMap = 
				this.validateInRange(this.getCellValue(dataRow.getCell(0)), this.RATEPLAN_ACTION,"Action Type",i+1);
			if(BEYOND_RANGE_INNER.equals(resultMap.get("result")))
				return resultMap;
			//获取房价hotelCode以及房价对象
			String hotelCode = this.getCellValue(dataRow.getCell(1)) + "";
			resultMap = this.validateNull(hotelCode,"Switch Hotel Code",i+1);
			if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
				return resultMap;
			}
			
			//获取酒店组
			List<Hotel> hotels = hotelDao.getHotelByHotelCode(hotelCode);
			if(hotels == null || hotels.size() == 0){
				//格式: 值不存在_字段名_值
				resultMap.put("result", OBJ_NOT_EXISTS );
				resultMap.put("errKey", "Switch Hotel Code");
				resultMap.put("errValue", hotelCode);
				return resultMap;
			}
			String rateplanCode = this.getCellValue(dataRow.getCell(3)) + "";
			resultMap = this.validateNull(rateplanCode,"CRS Rate Plan Code",i+1);
			if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
				return resultMap;
			}

			Rateplan rateplan = ratePlanDao.getRateplanByRateplanCode(rateplanCode, hotelCode);
			
			//创建房价Map,存储一个房价操作对象
			Map<String,Object> rateplanMap = new HashMap<String, Object>();
			rateplanMap.put("actionType", actionType);
			
			//如果房型操作类型为新增/修改
			if(this.RATEPLAN_ACTION_NEW.equals(actionType)
					||this.RATEPLAN_ACTION_MODIFY.equals(actionType)){
				
				//如果房价对象已存在,进行修改
				if(rateplan != null ){
					if(this.RATEPLAN_ACTION_NEW.equals(actionType)){
						//格式: 关键值重复_重复的表头名_重复的值
						resultMap.put("result", KEY_VALUE_EXISTS );
						resultMap.put("errKey", "CRS Rate Plan Code");
						resultMap.put("errValue", rateplanCode);
						return resultMap;
					}
					rateplanMap.put("rateplanId", rateplan.getRatePlanId());
					
					RatePlanI18n rateplanI18n = ratePlanDao.getRatePlanI18nById(rateplan.getRatePlanId());
					rateplanMap.put("rateplanMid", rateplanI18n.getRatePlanMId());
				//如果房价对象为空,则不能进行修改
				}else if(rateplan == null
						&&this.RATEPLAN_ACTION_MODIFY.equals(actionType)){
					//格式: 不存在
					resultMap.put("result", OBJ_NOT_EXISTS );
					resultMap.put("errKey", "CRS Rate Plan Code");
					resultMap.put("errValue", rateplanCode);
					return resultMap;
				}
				//获取hotelId
				for (Hotel hotel : hotels) {
					rateplanMap.put("hotelId", hotel.getHotelId());
				}
				//循环读取Cell值
				for (int j = 1; j < keys.length; j++){
					rateplanMap.put(keys[j], this.getCellValue(dataRow.getCell(j)));
				}
				
				//校验非空
				resultMap = this.validateNull(rateplanMap.get("crsRatePlanChineseName"),"CRS RatePlan Chinese Name",i+1);
				if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
					return resultMap;
				}
				resultMap = this.validateNull(rateplanMap.get("paymentType"),"Payment Type",i+1);
				if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
					return resultMap;
				}
				resultMap = this.validateNull(rateplanMap.get("status"),"Status",i+1);
				if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
					return resultMap;
				}
//				resultMap = this.validateNull(rateplanMap.get("breakfast")+"","Breakfast",i+1);
//				if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
//					return resultMap;
//				}
				
				//范围之内的校验
				resultMap = 
					this.validateInRange(rateplanMap.get("breakfast"), this.RATEPLAN_BREAKFAST,"Breakfast",i+1);
				if(BEYOND_RANGE_INNER.equals(resultMap.get("result")))
					return resultMap;
				
				resultMap = 
					this.validateInRange(rateplanMap.get("paymentType"), this.RATEPLAN_PAYMENT, "Payment Type", i+1);
				if(BEYOND_RANGE_INNER.equals(resultMap.get("result")))
					return resultMap;
				
				
				int maxMoney = 99;
				int maxBreak = 99;
				
				//校验数量填写
				resultMap = this.validateAmount(rateplanMap.get("extraBreakfast"),maxBreak, "Extra Breakfast", i+1);
				if(SUCCESS.equals(resultMap.get("result"))){
					rateplanMap.put("extraBreakfast", resultMap.get("value"));
				}else{
					return resultMap;
				}
				resultMap = this.validateAmount(rateplanMap.get("extraBreakfastCharge"),maxMoney, "Extra Breakfast Charge", i+1);
				if(SUCCESS.equals(resultMap.get("result"))){
					rateplanMap.put("extraBreakfastCharge", resultMap.get("value"));
				}else{
					return resultMap;
				}
				
				//校验金额填写
				resultMap = this.validateAmount(rateplanMap.get("taxAmount"),maxMoney, "Tax Amount", i+1);
				if(SUCCESS.equals(resultMap.get("result"))){
					if(resultMap.get("value")!=null
							&&Integer.parseInt(resultMap.get("value")+"")>0){
						rateplanMap.put("taxAmount", resultMap.get("value"));
					}else{
						rateplanMap.put("taxAmount",null);
					}
				}else{
					return resultMap;
				}
				//校验百分比填写
				resultMap = this.validatePercent(rateplanMap.get("taxPercentage"),100, "Tax Percentage", i+1);
				if(SUCCESS.equals(resultMap.get("result"))){
					if(resultMap.get("value")!=null
							&&Integer.parseInt(resultMap.get("value")+"")>0){
						rateplanMap.put("taxPercentage", resultMap.get("value"));
					}else{
						rateplanMap.put("taxPercentage",null);
					}
				}else{
					return resultMap;
				}
				
				//如果同时填写了值
				if(rateplanMap.get("taxAmount")!=null
						&&rateplanMap.get("taxPercentage")!=null){
					resultMap.put("result", CONCURRENT_HAVE_VALUE);
					resultMap.put("errKey1", "Tax Amount");
					resultMap.put("errKey2", "Tax Percentage");
					resultMap.put("errRow", i+1);
					return resultMap;
				}
					
				resultMap = this.validateAmount(rateplanMap.get("svcFeeAmount"),maxMoney, "Svc Fee Amount", i+1);
				if(SUCCESS.equals(resultMap.get("result"))){
					if(resultMap.get("value")!=null
							&&Integer.parseInt(resultMap.get("value")+"")>0){
						rateplanMap.put("svcFeeAmount", resultMap.get("value"));
					}else{
						rateplanMap.put("svcFeeAmount",null);
					}
				}else{
					return resultMap;
				}
				
				resultMap = this.validatePercent(rateplanMap.get("svcFeePercent"),100, "Svc Fee Percent", i+1);
				if(SUCCESS.equals(resultMap.get("result"))){
					if(resultMap.get("value")!=null
							&&Integer.parseInt(resultMap.get("value")+"")>0){
						rateplanMap.put("svcFeePercent", resultMap.get("value"));
					}else{
						rateplanMap.put("svcFeePercent",null);
					}
				}else{
					return resultMap;
				}
				//如果同时填写了值
				if(rateplanMap.get("svcFeeAmount")!=null
						&&rateplanMap.get("svcFeePercent")!=null){
					resultMap.put("result", CONCURRENT_HAVE_VALUE);
					resultMap.put("errKey1", "Svc Fee Amount");
					resultMap.put("errKey2", "Svc Fee Percent");
					resultMap.put("errRow", i+1);
					return resultMap;
				}
				
				//校验开始时间和结束时间
				Map<String,Object> dateMap = this.parseDateByObj(rateplanMap.get("startTime"));
				if(!SUCCESS.equals(dateMap.get("result"))){
					resultMap.put("result", VALUE_FORMAT_ERROR);
					resultMap.put("errKey", "Check In Time");
					resultMap.put("errValue", rateplanMap.get("startTime"));
					resultMap.put("pattern", "yyyy-MM-DD HH:mi:ss");
					resultMap.put("errRow", (i+1));
					return resultMap;
				}
				rateplanMap.put("startTime", dateMap.get("date"));
				
				dateMap = this.parseDateByObj(rateplanMap.get("endTime"));
				if(!SUCCESS.equals(dateMap.get("result"))){
					resultMap.put("result", VALUE_FORMAT_ERROR);
					resultMap.put("errKey", "Check Out Time");
					resultMap.put("errValue", rateplanMap.get("endTime"));
					resultMap.put("pattern", "yyyy-MM-DD HH:mi:ss");
					resultMap.put("errRow", (i+1));
					return resultMap;
				}
				rateplanMap.put("endTime", dateMap.get("date"));
				//取消规则如果为空
				if(CommonUtil.isEmpty(rateplanMap.get("cancelPolicy"))){
					resultMap.put("result", CELL_VALUE_NULL);
					resultMap.put("errKey", "Cancel Policy");
					resultMap.put("errRow", i+1);
					return resultMap;
				}
				
				//获取取消规则map对象
				resultMap = this.getCancelPolicy(rateplanMap.get("cancelPolicy")+"");
				if(SUCCESS.equals(resultMap.get("result"))){
					rateplanMap.put("cancelPolicyList", resultMap.get("cancelPolicyList"));
					rateplanMap.put("cancelType", resultMap.get("cancelType"));
				}else{
					resultMap.put("errKey", "Cancel Policy");
					resultMap.put("errRow", i+1);
					return resultMap;
				}
				
				ratePlanList.add(rateplanMap);
			}
		}
		
		resultMap.put("result", SUCCESS);
		resultMap.put(this.RATEPLAN_DATA, ratePlanList);
		return resultMap;
	}
	
	/**
	 * 产品-创建
	 */
	private String PRODUCT_ACTION_NEW = "NEW";
	
	/**
	 * 产品-修改
	 */
	private String PRODUCT_ACTION_MODIFY = "MODIFY";
	
	/**
	 * 产品-删除
	 */
	private String PRODUCT_ACTION_DELETE = "DELETE";

	/**
	 * 校验产品sheet页的数据是否符合规范
	 * @param productSheet 产品sheet页对象
	 * @return
	 */
	private Map<String,Object> getProductSheetData(Sheet productSheet){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//調用方法獲取真實的sheet頁
		//productSheet = getRealSheet(productSheet,"A2");
		//如果productSheet页中无数据
		if(productSheet.getLastRowNum() == 0){
			resultMap.put("result", SHEET_EMPTY);
			resultMap.put("errKey", productSheet.getSheetName());
			return resultMap;
		}else if(productSheet.getLastRowNum() < 1 ){
			resultMap.put("result", SHEET_ERROR); //提示需要填寫1行表頭,第2行開始填写数据
			resultMap.put("errKey", 1);
			return resultMap;
		}
		
		//保存產品列表
		List<Map<String,Object>> productList = new ArrayList<Map<String,Object>>();
		//键集合
		String[] keys = {"actionType","hotelCode","crsHotelCode","roomTypeCode","roomTypeName","ratePlanCode"
				,"ratePlanName"};  
		
		//循环校验数据行
		for (int i = 1 ;i <= productSheet.getLastRowNum() ; i++) {
			//数据行
			Row dataRow = productSheet.getRow(i);
			//获取每一行操作类型
			String actionType = this.getUpperStr(this.getCellValue(dataRow.getCell(0)) + "");
			//如果actionType为空
			if(StringUtils.isBlank(actionType)){
				continue;
			}
			
			//范围之内的校验
			resultMap = 
				this.validateInRange(this.getCellValue(dataRow.getCell(0)), this.PRODUCT_ACTION,"Action Type",i+1);
			if(BEYOND_RANGE_INNER.equals(resultMap.get("result")))
				return resultMap;
			
			//获取房价hotelCode以及房价对象
			String hotelCode = this.getCellValue(dataRow.getCell(1)) + "";
			//如果hotelCode为空
			if(StringUtils.isBlank(hotelCode)){
				//格式: 值不存在_字段名_值
				resultMap.put("result", CELL_VALUE_NULL);
				resultMap.put("errKey", "Switch Hotel Code");
				resultMap.put("errRow", i+1);
				return resultMap;
			}
			
			//获取酒店组
			List<Hotel> hotels = hotelDao.getHotelByHotelCode(hotelCode);
			if(hotels == null || hotels.size() == 0){
				//格式: 值不存在_字段名_值
				resultMap.put("result", OBJ_NOT_EXISTS );
				resultMap.put("errKey", "Hotel Code");
				resultMap.put("errValue", hotelCode);
				return resultMap;
			}
			
			String hotelId = "";	
			//获取hotelId
			for (Hotel hotel : hotels) {
				hotelId = hotel.getHotelId();
			}
			
			//获取房价code以及房型code
			String roomTypeCode = this.getCellValue(dataRow.getCell(3)) + "";
			String rateplanCode = this.getCellValue(dataRow.getCell(5)) + "";
			
			//如果rateplanCode为空
			if(StringUtils.isBlank(rateplanCode)){
				//格式: 值不存在_字段名_值
				resultMap.put("result", CELL_VALUE_NULL);
				resultMap.put("errKey", "CRS Rate Plan Code");
				resultMap.put("errRow", i+1);
				return resultMap;
			//如果roomTypeCode为空
			}else if(StringUtils.isBlank(roomTypeCode)){
				//格式: 值不存在_字段名_值
				resultMap.put("result", CELL_VALUE_NULL);
				resultMap.put("errKey", "CRS Room Type ID");
				resultMap.put("errRow", i+1);
				return resultMap;
			}

			//创建产品Map,存储一个房型操作对象
			Map<String,Object> productlMap = new HashMap<String, Object>();
			productlMap.put("actionType", actionType);
			
			//获取两个对象
			RoomType roomType = roomTypeDao.getRoomTypeByHotelCode(roomTypeCode, hotelCode);
			Rateplan rateplan = ratePlanDao.getRateplanByRateplanCode(rateplanCode, hotelCode);
			//如果不存在该房型
			if(roomType == null){
				//格式: 关键值不存在_表头名_值
				resultMap.put("result", OBJ_NOT_EXISTS );
				resultMap.put("errKey", "Room Type Code");
				resultMap.put("errValue", roomTypeCode);
				return resultMap;
			//如果不存在该房价
			}else if(rateplan == null){
				//格式: 关键值不存在_表头名_值
				resultMap.put("result", OBJ_NOT_EXISTS );
				resultMap.put("errKey", "Rate Plan Code");
				resultMap.put("errValue", rateplanCode);
				return resultMap;
			}

			//如果产品操作类型为新增/修改
			if(this.PRODUCT_ACTION_NEW.equals(actionType)
					||this.PRODUCT_ACTION_MODIFY.equals(actionType)){
				
				//如果进行新增,判断是否已存在该产品
				if(this.PRODUCT_ACTION_NEW.equals(actionType)){
					ChannelGoods cg = new ChannelGoods();
					cg.setRoomTypeId(roomType.getRoomTypeId());
					cg.setRateplanid(rateplan.getRatePlanId());
					//如果存在该产品
					if (!channelgoodsManager.checkGoods(cg)) {
						//格式: 关键值重复_重复的表头名_重复的值
						resultMap.put("result", KEY_VALUE_EXISTS );
						resultMap.put("errKey", "PRODUCT");
						resultMap.put("errValue", "rateplanCode="+rateplanCode+"并且"+"roomTypeCode="+roomTypeCode);
						return resultMap;
					}
				//如果进行修改
				}else if(this.PRODUCT_ACTION_MODIFY.equals(actionType)){
					//如果进行修改
					if(this.PRODUCT_ACTION_MODIFY.equals(actionType)){
						//集团代码为酒店代码的前三位
						String chainCode = hotelCode.substring(0,3);
						//设置查询参数
						Map<String,String> params = new HashMap<String, String>();
						params.put("chainCode", chainCode);
						params.put("hotelCode", hotelCode);
						params.put("roomTypeCode", roomTypeCode);
						params.put("ratePlanCode", rateplanCode);
						
						//获取产品相应的信息
						List<ChannelGoods> channelGoodsList =  channelGoodsDao.getChannelgoodsByCodeMap(params);
						
						if(channelGoodsList == null || channelGoodsList.size() == 0){
							//格式: 关键值不存在_表头名_值
							resultMap.put("result", OBJ_NOT_EXISTS );
							resultMap.put("errKey", "PRODUCT");
							resultMap.put("errValue", "rateplanCode="+rateplanCode+"并且"+"roomTypeCode="+roomTypeCode);
							return resultMap;
						}
						//获取channelGoodsId
						for (ChannelGoods cg2 : channelGoodsList) {
							productlMap.put("channelGoodsId", cg2.getChannelGoodsId());
						}
					}
				}

				//保存三个项的ID
				productlMap.put("hotelId", hotelId);
				productlMap.put("roomTypeId", roomType.getRoomTypeId());
				productlMap.put("ratePlanId", rateplan.getRatePlanId());

				//循环读取Cell值
				for (int j = 1; j < keys.length; j++){
					productlMap.put(keys[j], this.getCellValue(dataRow.getCell(j)));
				}
				
				//校验非空
				resultMap = this.validateNull(productlMap.get("roomTypeName"),"RoomType Chinese Name",i+1);
				if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
					return resultMap;
				}
				resultMap = this.validateNull(productlMap.get("ratePlanName"),"CRS RatePlan Chinese Name",i+1);
				if(CELL_VALUE_NULL.equals(resultMap.get("result"))){
					return resultMap;
				}
				
			//如果产品操作类型为删除
			}else if(this.PRODUCT_ACTION_DELETE.equals(actionType)){
				
				//集团代码为酒店代码的前三位
				String chainCode = hotelCode.substring(0,3);
				//设置查询参数
				Map<String,String> params = new HashMap<String, String>();
				params.put("chainCode", chainCode);
				params.put("hotelCode", hotelCode);
				params.put("roomTypeCode", roomTypeCode);
				params.put("ratePlanCode", rateplanCode);
				
				//获取产品相应的信息
				List<ChannelGoods> channelGoodsList =  channelGoodsDao.getChannelgoodsByCodeMap(params);
				
				if(channelGoodsList == null || channelGoodsList.size() == 0){
					//格式: 关键值不存在_表头名_值
					resultMap.put("result", OBJ_NOT_EXISTS );
					resultMap.put("errKey", "PRODUCT");
					resultMap.put("errValue", "rateplanCode="+rateplanCode+"并且"+"roomTypeCode="+roomTypeCode);
					return resultMap;
				}
				//获取channelGoodsId
				for (ChannelGoods cg2 : channelGoodsList) {
					productlMap.put("channelGoodsId", cg2.getChannelGoodsId());
				}
				productlMap.put("hotelId", hotelId);
				
			}
			
			productList.add(productlMap);
		}
		
		resultMap.put("result", SUCCESS);
		resultMap.put(this.PRODUCT_DATA, productList);
		return resultMap;
	}
	
	@Override
	public String validateIsExcel(File file)  {
		//判断文件是否存在
		if(file == null || !file.exists()) 
			return FILE_NOTEXIST;
		//获取文件名
		String fileName = file.getName();
		//如果既不是excel2003也不是excel2007+
		if(!this.isExcel(fileName, this.EXCEL_2003)
				&&!this.isExcel(fileName, this.EXCEL_2007)){
			return IS_NOTEXCEL;
		}
		//如果文件大于20M,提醒用户可分为两个excel
		if(file.length() > FILE_MAXSIZE)
			return BEYOND_MAXSIZE;
	
		return SUCCESS; 
	}
	
	/**
	 * excel2003
	 */
	private String EXCEL_2003 = "EXCEL2003";
	
	/**
	 * excel2007
	 */
	private String EXCEL_2007 = "EXCEL2007";
	
	/**
	 * 判断是否是excel
	 * @param fileName 文件名
	 * @param version 版本
	 * @return
	 */
	private boolean isExcel(String fileName,String version){
		//如果文件名为空
		if(StringUtils.isBlank(fileName)){
			return false;
		}
		//判断是否为excel2003
		if(this.EXCEL_2003.equals(version)&&
				fileName.matches("^.+\\.(?i)(xls)$")){
			return true;
			
		//判断是否为excel2007
		}else if(this.EXCEL_2007.equals(version)&&
				fileName.matches("^.+\\.(?i)(xlsx)$")){
			return true;
		}
		return false;
	}

	/**
	 * 校验邮箱地址是否有效,真实
	 * @param email 邮箱地址
	 * @return
	 */
	private boolean validateEmail(String email){
		//校验邮箱的有效性
		if (email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
			return true;
		}
		
		//此处校验 邮箱真实性，是否能连接上邮箱服务器 (需要导入 IsEMail.jar)
//		IsEMailResult result = IsEMail.is_email_verbose(email, true);
//		switch (result.getState()) {
//		   	case OK:
//		       	return true;
//		   	default:
//		    	return false;
//		}
		return false;
	}
	
	/**
	 * 获取单元格的值
	 * @param cell excel单元格对象
	 * @return
	 */
	private Object getCellValue(Cell cell){
		//如果cell对象为空或者为空白
		if(cell == null 
				|| cell.getCellType() == Cell.CELL_TYPE_BLANK){
			return "";
			
		//如果是字符串类型
		}else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
			return cell.getStringCellValue();
			
		//如果是数值类型
		}else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
			//如果是日期类型的值
			if(DateUtil.isCellDateFormatted(cell)){
				return cell.getDateCellValue();
			}
			return cell.getNumericCellValue();
		//如果是布尔型
		}else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){
			return cell.getBooleanCellValue();
			
		//如果是公式
		}else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){
			return cell.getCellFormula();
			
		//格式错误
		}else if(cell.getCellType() == Cell.CELL_TYPE_ERROR){
			return "error";
		}

		return "";
	}
	
	/**
	 * 获取字符串的大写格式
	 * @param oldStr 原有的字符串
	 * @return
	 */
	private String getUpperStr(String oldStr){
		if(StringUtils.isBlank(oldStr))
			return "";
		return oldStr.replaceAll(" ", "").toUpperCase();
	}
	
	/**
	 * 根据double字符串获取Integer值
	 * @param numberStr
	 * @return
	 */
	private Integer getIntValue(Object numberStr) throws NumberFormatException{
		//如果对象不为空
		if(numberStr!=null&&StringUtils.isNotBlank(numberStr+"")){
			return (int)Double.parseDouble(numberStr+"");
		}
		return null;
	}
	
	/**
	 * 获取日期对象
	 * @param dateObj 日期对象
	 * @return 转换成功获得日期，如果转换失败，则获取错误信息
	 */
	private Map<String,Object> parseDateByObj(Object dateObj){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		Date date = null;
		String result = SUCCESS;
		
		//如果开始时间不为空
		if(CommonUtil.isNotEmpty(dateObj)){
			try {
				if(dateObj instanceof Date){
					date = (Date) dateObj;
				}else if(dateObj instanceof String){
					date = com.ccm.api.util.DateUtil.convertStringToDate(dateObj+"");
				}else if(dateObj instanceof Long){
					date = new Date((Long) dateObj);
				}
			} catch (ParseException e) {
				result = FAILURE;
			}
		}
		
		resultMap.put("result", result);
		resultMap.put("date", date);
		return resultMap;
	}
	
	/**
	 * 判断 国家为中国时,下属 省份-城市-地区 匹配问题
	 * @param province 省份
	 * @param city 城市
	 * @param district 地区
	 * @param rowNum 行数
	 * @return
	 */
	private Map<String,Object> validateProCityDist(String province,String city,String district){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String result = PRO_CITY_DIST_NOMATCH;
		String errText = "";
		
		//省份不能为空
		if(CommonUtil.isEmpty(province)){
			errText = "省份编码必须输入,请检查";
		//城市不能为空
		}else if(CommonUtil.isEmpty(city)){
			errText = "城市编码必须输入,请检查";
		//判断省份长度
		}else if(province.length()<6){
			errText = "省份编码长度不能低于6位,请检查";
		//判断城市编码长度
		}else if(city.length()<6){
			errText = "城市编码长度不能低于6位,请检查";
		//判断地区是否已经填写了	
		}else if(CommonUtil.isNotEmpty(district)
				&&district.length()<6){
			errText = "地区编码长度不能低于6位,请检查";
		//开始判断
		}else if(CommonUtil.isNotEmpty(district)
				&&district.length()>=6){
			
			//省份的和城市的前两位相同,城市和地区的前4位相同
			if(!province.substring(0, 2).equals(city.substring(0, 2))
					||!city.substring(0, 4).equals(district.substring(0, 4))){
				errText = "省份-城市-地区不匹配,请检查 . \\n提示:省份和城市的前2位编码相同,城市和地区的前4位编码相同";
			}else{
				result = SUCCESS;
			}
		}else if(CommonUtil.isEmpty(district)){
			//省份的和城市的前两位相同
			if(!province.substring(0, 2).equals(city.substring(0, 2))){
				errText = "省份-城市不匹配,请检查 . \\n提示:省份和城市的前2位编码相同";
			}else{
				result = SUCCESS;
			}
		}
		
		resultMap.put("result", result);
		resultMap.put("errText", errText);
		
		return resultMap;
	}
	
	/**
	 * 验证数据的长度
	 * @param objStr object类型的字符串
	 * @param maxLength 最大长度
	 * @return
	 */
	private Map<String,Object> validateLength(Object objStr,int maxLength,String columnName,int rowNum){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String result = SUCCESS;  //超过长度
		String str = CommonUtil.isEmpty(objStr)?"":objStr.toString();
		
		//长度超过范围
		if(str.length()>maxLength){
			result = LENGTH_OVERRANGE;
			resultMap.put("errKey", columnName);
			resultMap.put("errRow", rowNum);
			resultMap.put("errValue", objStr);
			resultMap.put("maxLength", maxLength);
		}
		
		resultMap.put("result", result);
		return resultMap;
	}
	
	/**
	 * 校验房型设施
	 * @param amenities 房型设施字符串,中间以逗号隔开
	 * @param dictList 设施列表
	 * @param columnName 字段名称
	 * @param rowNum 行号
	 * @return
	 */
	private Map<String,Object> validteAmenities(
			String amenities,List<DictCodeChannelCodeNameVO> dictList,String columnName,int rowNum){
		//将填写的设施拆分
		String[] amenArry = amenities.split(",");
		List<String> amennitList = new ArrayList<String>();
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String result = SUCCESS;
		String amenName = "";
		
		//循环校验并且存储
		for (int j = 0; j < amenArry.length; j++) {
			//标示 未找到
			boolean flag = false;
			for (DictCodeChannelCodeNameVO dictChannel : dictList) {
				//如果存在该值,如： CATV/有线电视 ,只匹配编号
				if(StringUtils.isNotBlank(amenArry[j])
						&&amenArry[j].toLowerCase().indexOf(dictChannel.getCodeName().toLowerCase())>=0){
					amennitList.add(dictChannel.getCodeNo());
					flag = true;
					continue;
				}
			}
			//如果未找到设施
			if(!flag){
				//不在系统中
				result = VALUE_NOT_INSYSTEM;
				amenName = amenArry[j];
				break;
			}
		}
		
		//如果不在系统中
		if(VALUE_NOT_INSYSTEM.equals(result)){
			resultMap.put("errKey", columnName);
			resultMap.put("errRow", rowNum);
			resultMap.put("errValue", amenName);
		}else{
			resultMap.put("amennitList", amennitList);
		}
		resultMap.put("result", result);
		return resultMap;
	}
	
	/**
	 * 校验百分比是否正确
	 * @param objPercent 百分比字符串
	 * @param multiple 可能需要乘以100
	 * @param columnName 列名
	 * @param rowNum 行数
	 * @return
	 */
	private Map<String,Object> validatePercent(Object objPercent,int multiple,String columnName,int rowNum){
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String result = SUCCESS;  //默认校验成功
		if(objPercent!=null&&StringUtils.isNotBlank(objPercent+"")){
			String percent = objPercent.toString().replace("%", "");
			
			try {
				int num = (int)(Double.parseDouble(percent)*multiple);
				//数字超过范围
				if(num>100||num<0){
					result = PERCENT_OVERRANGE;
					resultMap.put("errKey", columnName);
					resultMap.put("errRow", rowNum);
				}else{
					resultMap.put("value", num);
				}
			} catch (NumberFormatException e) {
				result = NUMBER_FORMAT_ERROR;
				resultMap.put("errKey", columnName);
				resultMap.put("errRow", rowNum);
				resultMap.put("errValue", objPercent);
				resultMap.put("pattern", "0%-100%之间");
			}
		}

		resultMap.put("result", result);
		return resultMap;
	}
	
	/**
	 * 校验数量和金额是否正确
	 * @param objAmount 百分比字符串
	 * @param maxAmout 金额/数量最大
	 * @param columnName 列名
	 * @param rowNum 行数
	 * @return
	 */
	private Map<String,Object> validateAmount(Object objAmount,int maxAmout,String columnName,int rowNum){
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String result = SUCCESS;  //默认校验成功
		if(objAmount!=null&&StringUtils.isNotBlank(objAmount+"")){
			try {
				int num = this.getIntValue(objAmount);
				//数字超过范围
				if(num>maxAmout||num<0){
					result = NUMBER_OVERRANGE;
					resultMap.put("errKey", columnName);
					resultMap.put("errRow", rowNum);
					resultMap.put("errValue", objAmount);
					resultMap.put("minNum", 0);
					resultMap.put("maxNum", maxAmout);
				}else{
					resultMap.put("value", num);
				}
			} catch (NumberFormatException e) {
				result = NUMBER_FORMAT_ERROR;
				resultMap.put("errKey", columnName);
				resultMap.put("errRow", rowNum);
				resultMap.put("errValue", objAmount);
				resultMap.put("pattern", "0-"+maxAmout+"之间");
			}
		}

		resultMap.put("result", result);
		return resultMap;
	}
	
	/**
	 * 校验必输项
	 * @return
	 */
	private Map<String,Object> validateNull(Object obj,String columnName,int rowNum){
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String result = SUCCESS;  //默认校验成功
		if(CommonUtil.isEmpty(obj)){
			//格式: 值不存在_字段名_值
			result = CELL_VALUE_NULL;
			resultMap.put("errKey", columnName);
			resultMap.put("errRow", rowNum);
		}
		resultMap.put("result", result);
		return resultMap;
	}
	
	private String ALLOW_ANY_RETURN = "1"; //任意退
	private String ALLOW_NOT_RETURN = "2"; //不允许退
	private String ACCORD_DATE_RETURN = "3"; //按照天数退
	private String ACCORD_TIME_RETURN = "4"; //按照时间退
	/**
	 * 根据取消规则字符串获取 取消规则列表
	 * @param cancelStr json字符串
	 * @return
	 * @throws ParseException 
	 */
	private Map<String,Object> getCancelPolicy(String cancelStr) {
		
//		1表示任意退;2表示不能退;3表示X日阶梯退；4表示X小时阶梯退      
//		1.任意退
//		2.不能退
//		3.在yyyy-MM-DD前取消收取Y%手续费(100>Y>=0)
//		4.在X小时前取消收取Y%手续费(100>Y>=0)

		String result = SUCCESS;
		Map<String,Object> cancelPolicyMap = new HashMap<String, Object>();
		List<CancelPolicyVO> cancelPolicyList = null;

		//如果字符串值不为空
		if(StringUtils.isNotBlank(cancelStr)){
			String anyReg = "(1.){0,1}任意退[\\s\\p{Zs}]*";
			String notReg = "(2.){0,1}不能退[\\s\\p{Zs}]*";
			
			//如果是任意退
			if(cancelStr.matches(anyReg)){
				cancelPolicyMap.put("cancelType", this.ALLOW_ANY_RETURN);
			//不允许退
			}else if(cancelStr.matches(notReg)){
				cancelPolicyMap.put("cancelType", this.ALLOW_NOT_RETURN);
				
			}else{
				String dateReg = this.getDateRegString();
				String dayCancel = "^([,\\s，]*[\u4E00-\u9FA5]*"+dateReg+"[\u4E00-\u9FA5]+(\\d|[1-9]\\d)(\\.\\d+)?%[\u4E00-\u9FA5]+[,\\s，]*)+"; //按天数退的表达式
				String hourCancel = "([,\\s，]*[\u4E00-\u9FA5]*[1-9]\\d*小时[\u4E00-\u9FA5]+(\\d|[1-9]\\d)(\\.\\d+)?%[\u4E00-\u9FA5]+[,\\s，]*)+"; //按小时退的表达式
				
				String returnFlag = "0";
				//如果是天数取消规则
				if(cancelStr.matches(dayCancel)){
					returnFlag = this.ACCORD_DATE_RETURN;
					cancelPolicyMap.put("cancelType", this.ACCORD_DATE_RETURN);
				//如果是小时取消规则
				}else if(cancelStr.matches(hourCancel)){
					returnFlag = this.ACCORD_TIME_RETURN; 
					cancelPolicyMap.put("cancelType", this.ACCORD_TIME_RETURN);
				}else{
					cancelPolicyMap.put("result", VALUE_FORMAT_ERROR);
					cancelPolicyMap.put("errValue", cancelStr);
					cancelPolicyMap.put("pattern", "\\n4种:1.任意退  ,2.不能退  ,3.在yyyy-MM-DD前取消收取Y%手续费(100>Y>=0),\\n"+
							"4.在入住前X小时取消收取Y%手续费(100>Y>=0) .如果为阶梯退,中间用逗号(,)隔开," +
							"\\n如:在入住前24小时取消收取50%手续费,在入住前12小时取消收取80%手续费");
					return cancelPolicyMap;
				}
				
				//剔除所有非数字，并且得到一连串数字,中间用逗号隔开
				String nums = cancelStr.replace("年", "-").replace("月", "-").replace("/", "-").replace("\\", "-");
				//此处可以换中文空格,竖线,百分号,全角空格,全角逗号,逗号
				nums = nums.replaceAll("([\u4E00-\u9FA5]|%)", ",").replaceAll("[,\\s\\p{Zs}，]+", ","); 
				
				if(nums.startsWith(",")){
					nums = nums.substring(1);
				}
				if(nums.endsWith(",")){
					nums = nums.substring(0,nums.length()-1);
				}
				
				if(this.ACCORD_DATE_RETURN.equals(returnFlag)){
					//循环遍历
					try {
						cancelPolicyList = new ArrayList<CancelPolicyVO>();
						String[] numArry = nums.split(",");
						String dateStr = "";
						try {
							for (int i = 0; i < numArry.length; i=i+2) {
								dateStr = numArry[i];
								Date imDate = com.ccm.api.util.DateUtil.convertStringToDate(numArry[i]);
								
								if(com.ccm.api.util.DateUtil.getCleanDate(imDate).compareTo(
										com.ccm.api.util.DateUtil.currentDate())!=1){
									cancelPolicyMap.put("result", DATE_NOT_LOSENOW);
									cancelPolicyMap.put("errValue", numArry[i]);
									return cancelPolicyMap;
								}
							}
						} catch (ParseException e) {
							cancelPolicyMap.put("result", VALUE_FORMAT_ERROR);
							cancelPolicyMap.put("errValue", dateStr);
							cancelPolicyMap.put("pattern", "yyyy-MM-DD");
							return cancelPolicyMap;
						}

						for (int i = 0; i < numArry.length; i=i+2) {
							CancelPolicyVO cpv= new CancelPolicyVO();
							cpv.setIsNonRefundable(false);
							cpv.setCancelType(3);
							cpv.setPercent(1.0);
							cpv.setAbsoluteDeadline(com.ccm.api.util.DateUtil.convertStringToDate(numArry[i]));
							cpv.setPercent(Double.parseDouble(numArry[i+1])); //百分比
							cancelPolicyList.add(cpv);
						}
					} catch (Exception e) {
						cancelPolicyMap.put("result", VALUE_FORMAT_ERROR);
						cancelPolicyMap.put("errValue", cancelStr);
						cancelPolicyMap.put("pattern", "\\n4种: 1.任意退  ,2.不能退  ,3.在yyyy-MM-DD前取消收取Y%手续费(100>Y>=0),\\n"+
								"4.在入住前X小时取消收取Y%手续费(100>Y>=0) .如果为阶梯退,中间用逗号(,)隔开, " +
								"\\n如:在2014-03-20前取消收取50%手续费,在2014-03-21前取消收取80%手续费"); 
						return cancelPolicyMap;
					}
					
				//按小时退
				}else if(this.ACCORD_TIME_RETURN.equals(returnFlag)){
					//循环遍历
					try {
						cancelPolicyList = new ArrayList<CancelPolicyVO>();
						String[] numArry = nums.split(",");
						for (int i = 0; i < numArry.length; i=i+2) {
							CancelPolicyVO cpv= new CancelPolicyVO();
							cpv.setIsNonRefundable(false);
							cpv.setCancelType(4);
							cpv.setOffsetDropTime(Integer.parseInt(numArry[i]));  //转换
							cpv.setPercent(Double.parseDouble(numArry[i+1]));     //百分比
							cancelPolicyList.add(cpv);
						}
					} catch (Exception e) {
						cancelPolicyMap.put("result", VALUE_FORMAT_ERROR);
						cancelPolicyMap.put("errValue", cancelStr);
						cancelPolicyMap.put("pattern", "\\n4种:1.任意退  ,2.不能退  ,3.在yyyy-MM-DD前取消收取Y%手续费(100>Y>=0),\\n"+
								"4.在入住前X小时取消收取Y%手续费(100>Y>=0) .如果为阶梯退,中间用逗号(,)隔开," +
								"\\n如:在入住前24小时取消收取50%手续费,在入住前12小时取消收取80%手续费");
						return cancelPolicyMap;
					}
				}
			}
		}
		cancelPolicyMap.put("result", result);
		cancelPolicyMap.put("cancelPolicyList", cancelPolicyList);
		return cancelPolicyMap;
	}

	private String HOTEL_ACTION = "HOTEL_ACTION";  //酒店action
	private String RANGE_HOTEL_ACTION = "(,NEW,INACTIVE,)"; //酒店操作类型组
	private String TIP_HOTEL_ACTION = "酒店操作类型 填写提示>> New:添加, Inactive:停用";
	
	private String ROOMTYPE_ACTION = "ROOMTYPE_ACTION";   //房型action
	private String RANGE_ROOMTYPE_ACTION = "(,NEW,MODIFY,)"; //房型操作类型组
	private String TIP_ROOMTYPE_ACTION = "房型操作类型 填写提示>> New:添加, Modify:修改";
	
	private String RATEPLAN_ACTION = "RATEPLAN_ACTION";  //房价action
	private String RANGE_RATEPLAN_ACTION = "(,NEW,MODIFY,)"; //房价操作类型组
	private String TIP_RATEPLAN_ACTION = "房价操作类型 填写提示>> New:添加, Modify:修改";
	
	private String PRODUCT_ACTION = "PRODUCT_ACTION";  //产品action
	private String RANGE_PRODUCT_ACTION = "(,NEW,MODIFY,DELETE,)";  //产品组
	private String TIP_PRODUCT_ACTION = "产品操作类型 填写提示>> New:添加, Modify:修改, Delete:删除";
	
	private String POSITION_TYPE = "POSITION_TYPE";  //坐标类型
	private String RANGE_POSITION_TYPE = "(,G,B,A,M,L,)"; // 坐标类型,G-Google,B-百度, A-高德, M-Mapbar,L-灵图
	private String TIP_POSITION_TYPE = "坐标类型 填写提示>> G:Google,B:百度, A:高德, M:Mapbar, L:灵图";
 	
	private String ROOMTYPE_INTERNET = "ROOMTYPE_INTERNET";
	private String RANGE_ROOMTYPE_INTERNET = "(,A,B,C,D,)";  //宽带服务 A:无宽带 B:免费宽带 C:收费宽带 D:部分收费宽带
	private String TIP_ROOMTYPE_INTERNET = "宽带服务 填写提示>> A:无宽带, B:免费宽带, C:收费宽带, D:部分收费宽带";
	
	private String RATEPLAN_BREAKFAST = "RANGE_BREAKFAST"; //早餐填写项
	private Integer BREAKFAST_MIN = 0;
	private Integer BREAKFAST_MAX = 99;
	private String TIP_RATEPLAN_BREAKFAST = "额外服务-早餐数量 填写提示>> 0:不含早餐, 1:含单早, 2:含双早, 3-99:含N早";
	
	private String HOTEL_DOMESTIC = "HOTEL_DOMESTIC";  // 是否国内酒店。0:国内;1:国外
	private String TIP_HOTEL_DOMESTIC = "是否国内酒店  填写提示>> 0:国内, 1:国外";
	
	private String RATEPLAN_PAYMENT = "RATEPLAN_PAYMENT";
	private String TIP_RATEPLAN_PAYMENT = "房价-支付方式 填写提示>> 1:预付, 5:前台面付 , 6:支付宝后付费";
	
	/**
	 * 判断填写的值是否在范围之类
	 * @param value 匹配的值
	 * @param rangeType 数组匹配类型
	 * @return
	 */
	private Map<String,Object> validateInRange(Object value,String rangeType,String columnName,int rowNum){
		String result = SUCCESS;
		String tip = "";
		Map<String, Object> rangeMap = new HashMap<String, Object>();
		
		//如果是酒店操作类型
		if(this.HOTEL_ACTION.equals(rangeType)
				&&this.RANGE_HOTEL_ACTION.indexOf(this.getUpperStr(","+value+","))<0){
			result = BEYOND_RANGE_INNER;
			tip = this.TIP_HOTEL_ACTION;
		//如果是房型操作类型 
		}else if(this.ROOMTYPE_ACTION.equals(rangeType)
				&&this.RANGE_ROOMTYPE_ACTION.indexOf(this.getUpperStr(","+value+","))<0){
			result = BEYOND_RANGE_INNER;
			tip = this.TIP_ROOMTYPE_ACTION;
		//如果是房价操作类型
		}else if(this.RATEPLAN_ACTION.equals(rangeType)
				&&this.RANGE_RATEPLAN_ACTION.indexOf(this.getUpperStr(","+value+","))<0){
			result = BEYOND_RANGE_INNER;
			tip = this.TIP_RATEPLAN_ACTION;
		//如果是产品操作类型
		}else if(this.PRODUCT_ACTION.equals(rangeType)
				&&this.RANGE_PRODUCT_ACTION.indexOf(this.getUpperStr(","+value+","))<0){
			result = BEYOND_RANGE_INNER;
			tip = this.TIP_PRODUCT_ACTION;
		//如果是坐标类型
		}else if(this.POSITION_TYPE.equals(rangeType)
				&&this.RANGE_POSITION_TYPE.indexOf(this.getUpperStr(","+value+","))<0){
			result = BEYOND_RANGE_INNER;
			tip = this.TIP_POSITION_TYPE;
		//如果是宽带服务
		}else if(this.ROOMTYPE_INTERNET.equals(rangeType)
				&&this.RANGE_ROOMTYPE_INTERNET.indexOf(this.getUpperStr(","+value+","))<0){
			result = BEYOND_RANGE_INNER;
			tip = this.TIP_ROOMTYPE_INTERNET;
		//如果是早餐服务
		}else if(this.RATEPLAN_BREAKFAST.equals(rangeType)
				&&CommonUtil.isNotEmpty(value)){
			try {
				int breakFast = (int)Double.parseDouble(value+""); 
				value = breakFast;
				if(breakFast>this.BREAKFAST_MAX||breakFast<this.BREAKFAST_MIN){
					result = BEYOND_RANGE_INNER;
					tip = this.TIP_RATEPLAN_BREAKFAST;
				}
			} catch (NumberFormatException e) {
				log.info(CommonUtil.getExceptionMsg(e));
				result = BEYOND_RANGE_INNER;
				tip = this.TIP_RATEPLAN_BREAKFAST;
			}
		//是否国内酒店	
		}else if(this.HOTEL_DOMESTIC.equals(rangeType)){
			try {
				int domestic = (int)Double.parseDouble(value+""); 
				value = domestic;
				if(domestic>1||domestic<0){
					result = BEYOND_RANGE_INNER;
					tip = this.TIP_HOTEL_DOMESTIC;
				}
			} catch (NumberFormatException e) {
				log.info(CommonUtil.getExceptionMsg(e));
				result = BEYOND_RANGE_INNER;
				tip = this.TIP_HOTEL_DOMESTIC;
			}
			
		//房价支付方式判断
		}else if(this.RATEPLAN_PAYMENT.equals(rangeType)){
			try {
				int payment = (int)Double.parseDouble(value+""); 
				value = payment;
				if(payment!=1&&payment!=5&&payment!=6){
					result = BEYOND_RANGE_INNER;
					tip = this.TIP_RATEPLAN_PAYMENT;
				}
			} catch (NumberFormatException e) {
				log.info(CommonUtil.getExceptionMsg(e));
				result = BEYOND_RANGE_INNER;
				tip = this.TIP_RATEPLAN_PAYMENT;
			}
		}
		//如果不在范围内
		if(BEYOND_RANGE_INNER.equals(result)){
			rangeMap.put("errKey", columnName);
			rangeMap.put("errValue", value);
			rangeMap.put("errRow", rowNum);
			rangeMap.put("tip", tip);
		}
		rangeMap.put("result", result);
		return rangeMap;
	}
	
	/**
	 * 校验图片
	 * @param hotelCode 酒店code
	 * @param roomTypeCode 房型 code
	 * @return
	 */
	private Map<String,Object> validatePicture(String hotelCode,String roomTypeCode,int rowNum){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String result = SUCCESS;
		String picturePath = "";
		String pictureName = "";

		if(pictureList.size()==0){
			result = PICTURE_NOT_FOUND;
		}else {
			//标示未找到
			boolean flag = false;
			for (int i = 0; i < pictureList.size(); i++) {
				Map<String,String> map = pictureList.get(i);
				picturePath = map.get("picturePath");
				pictureName = map.get("pictureName");
				String picN = pictureName.substring(0,map.get("pictureName").lastIndexOf("."));
				
				if(picN.toUpperCase().equals((hotelCode+"_"+roomTypeCode).toUpperCase())){
					flag = true;
					//校验文件大小
					if(new File(map.get("picturePath")).length()>PICTURE_MAXSIZE){
						result = PICTURE_BEYOND_SIZE;
					}
					break;
				}		
			}			
			if(!flag){
				result = PICTURE_NOT_FOUND;
			}
		}
		
		//如果成功
		if(SUCCESS.equals(result)){
			resultMap.put("pictureName", pictureName);
			resultMap.put("picturePath", picturePath);
			
		//图片过大
		}else if(PICTURE_BEYOND_SIZE.equals(result)){
			resultMap.put("errValue", pictureName);
			resultMap.put("errRow", rowNum);
			
		//未找到
		}else if(PICTURE_NOT_FOUND.equals(result)){
			resultMap.put("errValue", hotelCode+"_"+roomTypeCode);
			resultMap.put("errRow", rowNum);
		}
		resultMap.put("result", result);
		return resultMap;
	}


	//保存文件目录
	List<Map<String,String>> pictureList = new ArrayList<Map<String,String>>();
	
	/** 
	 * 根据酒店code码获取相应的图片列表
	 * @param formetFolderPath 主目录
	 * @param code 
	 */
	private void setPictureList(String formetFolderPath,String hotelCode){
		File file = new File(formetFolderPath);
		File[] files = file.listFiles();
	
		if(files!=null&&files.length>0){
			for (int i = 0; i < files.length; i++) {
				 //如果是文件目录
				if(files[i].isDirectory()){
					this.setPictureList(files[i].getPath(),hotelCode);//递归调用
				
				//开始匹配 并且校验是否为图片文件
				}else if(files[i].getName().toUpperCase().startsWith(hotelCode.toUpperCase())
						&&this.isPictureFile(files[i].getName())){
					//创建map保存文件名和文件路径
					Map<String,String> fileMap = new HashMap<String, String>();
					fileMap.put("pictureName", files[i].getName());
					fileMap.put("picturePath", files[i].getPath());
					pictureList.add(fileMap);
				}
			}
		}
	}
	
	/**
	 * 制定一个文件夹解压下面的所有压缩文件
	 * @param formetFolderPath
	 * @throws Exception
	 */
	private void unZipFolder(String formetFolderPath) throws Exception{
		File file = new File(formetFolderPath);
		File[] files = file.listFiles();
		
		if(files!=null&&files.length>0){
			for (int i = 0; i < files.length; i++) {
				  //如果是文件目录
				  if(files[i].isDirectory()){
					  this.unZipFolder(files[i].getPath());
				  }else if(files[i].getName().toLowerCase().endsWith(".zip")){
					  this.unZip(files[i], files[i].getParentFile().getPath());
				  }
			}
		}
	}
	
	/**
	 * 解压文件到指定的目录
	 * @param zf
	 * @param unzipDirectory
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void unZip(File zf, String unzipDirectory) throws Exception {
		
		  // 创建zip文件对象
		  ZipFile zipFile = new ZipFile(zf);
		  // 创建zip文件解压目录
		  File unzipFile = new File(unzipDirectory + "/"
				  + zf.getName().substring(0, zf.getName().lastIndexOf(".")));
		  
		  if (!unzipFile.exists()){
			  unzipFile.mkdir();
		  }
			
		  // 得到zip文件条目枚举对象
		  Enumeration zipEnum = zipFile.entries();
		  // 定义输入输出流对象
		  InputStream input = null;
		  OutputStream output = null;
		 
		  while (zipEnum.hasMoreElements()) {
			   // 得到当前条目
			   ZipEntry entry = (ZipEntry) zipEnum.nextElement();
			   String entryName = new String(entry.getName().getBytes("ISO8859_1"));
		
			   // 若当前条目为目录则创建
			   if (entry.isDirectory()){
				   new File(unzipDirectory + "/" + entryName).mkdir();
			   }else { 
				   	// 若当前条目为文件则解压到相应目录
				   	input = zipFile.getInputStream(entry);
				   	output = new FileOutputStream(new File(unzipFile.getPath() + "/" + entryName));
				   	byte[] buffer = new byte[1024 * 8];
				   	int readLen = 0;
				   	while ((readLen = input.read(buffer, 0, 1024 * 8)) != -1){
				   		output.write(buffer, 0, readLen);
				   	}
				   	input.close();
			   		output.flush();
			   		output.close();
			  }
		 }
	}
	
	/**
	 * 是否为图片文件
	 * @param fileName 图片文件名
	 * @return
	 */
	private boolean isPictureFile(String fileName){
		String name = fileName.toLowerCase();
		//如果是图片的常有格式
		if(name.endsWith(".jpg")||name.endsWith(".jpeg")
				||name.endsWith(".gif")||name.endsWith(".png")){
			return true;
		}
		return false;
	}

	/**
	 * 获取图片原有的存储目录
	 * @return
	 */
	private String getPictureFormerPath(){
		// 文件原有的存储目录
		String pictureFormerPath = PropertiesUtil.getProperty(ProjectConfigConstant.pictureFormerPath);
		//如果读取的配置为空
		if(CommonUtil.isEmpty(pictureFormerPath)){
			return PICTURE_FORMETPATH_NOTCONFIG;
		}
		//查找目录,没有找到就创建
		File dirPath = new File(pictureFormerPath + "/");
		if (!dirPath.exists()) {
			dirPath.mkdirs(); 
			return PICTURE_ZIP_NOTUPLOAD;
		}
		
		//如果没有找到一个文件
		if(dirPath!=null&&dirPath.listFiles().length==0){
			return PICTURE_ZIP_NOTUPLOAD;
		}
		try {
			//解压目录下的所有.zip文件
			this.unZipFolder(pictureFormerPath);
		} catch (Exception e) {}	
		return pictureFormerPath;
	}
	
	/**
	 * 校验日期的正则表达式
	 * @return
	 */
	private String getDateRegString(){
		String dateReg = "((\\d{2}(([02468][048])|([13579][26]))[\\-\\s\\/\u5e74]?((((0?[13578])|(1[02]))"+
			"[\\-\\s\\/\u5e74]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\s\\/\u5e74]?((0?[1-9])"+
			"|([1-2][0-9])|(30)))|(0?2[\\-\\s\\/\u5e74]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])"+
			"|([13579][01345789]))[\\-\\s\\/\u5e74]?((((0?[13578])|(1[02]))[\\-\\s\\/\u6708]?((0?[1-9])|([1-2]"+
			"[0-9])|(3[01])))|(((0?[469])|(11))[\\-\\s\\/\u6708]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\s\\/\u6708]?"+
			"((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		return dateReg;
	}
}
