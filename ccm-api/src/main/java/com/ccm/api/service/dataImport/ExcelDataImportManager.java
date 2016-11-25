package com.ccm.api.service.dataImport;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * 
 * @author edwin
 * 将excel文件中的数据导入到数据库中
 */
public interface ExcelDataImportManager {

	/**
	 * 设置文件最大为20M
	 */
	final static long FILE_MAXSIZE = 20 * 1024 * 1024 ; 
	
	/**
	 * 文件不存在
	 */
	final static String FILE_NOTEXIST = "FILENOTEXIST";
	
	/**
	 * 不是excel文件
	 */
	final static String IS_NOTEXCEL = "ISNOTEXCEL";
	
	/**
	 * 超出文件最大限制
	 */
	final static String BEYOND_MAXSIZE = "BEYOND_MAX_SIZE";
	
	/**
	 * 无数据
	 */
	final static String NO_DATA = "NODATA";
	
	/**
	 * 验证成功
	 */
	final static String SUCCESS = "SUCCESS";
	
	/**
	 * 验证失败
	 */
	final static String FAILURE = "FAILURE";
	
	/**
	 * excel中数据为空
	 */
	final static String EXCEL_EMPTY = "EXCELEMPTY";
	
	/**
	 * sheet中数据为空
	 */
	final static String SHEET_EMPTY = "SHEET_EMPTY";
	
	/**
	 * sheet中格式錯誤
	 */
	final static String SHEET_ERROR = "SHEET_ERROR";
	
	/**
	 * 单元格数据为空
	 */
	final static String CELL_NULL = "CELLNULL";
	
	/**
	 * 数据格式错误
	 */
	final static String NUMBER_FORMAT_ERROR = "FORMATERROR";
	
	/**
	 * 同时有值
	 */
	final static String CONCURRENT_HAVE_VALUE = "CONCURRENT_HAVE_VALUE";
	
	/**
	 * sheet頁找不到
	 */
	final static String SHEET_NOT_FOUND = "SHEET_NOT_FOUND";
	
	/**
	 * sheet页中無数据
	 */
	final static String SHEET_NODATA = "SHEET_NODATA";
	
	/**
	 * 值不能为空
	 */
	final static String CELL_NOT_EMPTY = "CELL_NOT_EMPTY";
	
	/**
	 * 关键值已存在
	 */
	final static String KEY_VALUE_EXISTS = "KEY_VALUE_EXISTS";
	
	/**
	 * 操作对象不存在
	 */
	final static String OBJ_NOT_EXISTS = "OBJ_NOT_EXISTS";
	
	/**
	 * 操作对象重复
	 */
	final static String	OBJ_HAS_REPEAT = "OBJ_HAS_REPEAT";
	
	/**
	 * 单元格为空
	 */
	final static String CELL_VALUE_NULL = "CELL_VALUE_NULL";
	
	/**
	 * 值不存在与系统中
	 */
	final static String VALUE_NOT_INSYSTEM = "VALUE_NOT_INSYSTEM";
	
	/**
	 * 不符合格式(如日期格式为yyyy-MM-DD)
	 */
	final static String VALUE_FORMAT_ERROR = "VALUE_FORMAT_ERROR";
	
	/**
	 * 不能小于当前日期
	 */
	final static String DATE_NOT_LOSENOW = "DATE_NOT_LOSENOW";
	
	/**
	 * 超出范围
	 */
	final static String BEYOND_RANGE_INNER = "BEYOND_RANGE_INNER";
	
	/**
	 * 多个值不匹配(如 国家-省份-城市-地区)
	 */
	final static String VALUE_NOT_MATCH = "VALUE_NOT_MATCH";
	
	/**
	 * 字符串长度超出范围
	 */
	final static String LENGTH_OVERRANGE = "LENGTH_OVERRANGE";
	
	/**
	 * 百分比超出范围
	 */
	final static String PERCENT_OVERRANGE = "PERCENT_OVERRANGE";
	
	/**
	 * 数值超出范围
	 */
	final static String NUMBER_OVERRANGE = "NUMBER_OVERRANGE";
	
	/**
	 * 省份-城市-地区不匹配
	 */
	final static String PRO_CITY_DIST_NOMATCH = "PRO_CITY_DIST_NOMATCH";
	
	/**
	 * 图片原有存储目录地址未配置
	 */
	final static String PICTURE_FORMETPATH_NOTCONFIG ="PICTURE_FORMETPATH_NOTCONFIG";
	
	/**
	 * 没有上传图片zip包
	 */
	final static String PICTURE_ZIP_NOTUPLOAD ="PICTURE_ZIP_NOTUPLOAD";
	
	
	/**
	 * 图片文件找不到(根据一定的规则)
	 */ 
	final static String PICTURE_NOT_FOUND = "PICTURE_NOT_FOUND";
	
	/**
	 * 超过图片文件大小
	 */
	final static String PICTURE_BEYOND_SIZE = "PICTURE_BEYOND_SIZE";
	
	/**
	 * 设置文件最大为500K
	 */
	final static long PICTURE_MAXSIZE = 500 * 1024 ; 
	
	/**
	 * 图片保存失败
	 */
	final static String PICTURE_SAVE_FAILURE = "PICTURE_SAVE_FAILURE";
	
	/**
	 * 发布到淘宝失败
	 */
	final static String TAOBAO_PUBLISH_FAILURE = "taobaoFailure";
	
	/**
	 * sheet页的名称
	 */
	public static String SHEET_HOTEL_NAME = "HOTELDISTRIBUTE";
	public static String SHEET_ROOMTYPE_NAME = "ROOMTYPEDISTRIBUTE";
	public static String SHEET_RATEPLAN_NAME = "RATEPLANDISTRIBUTE";
	public static String SHEET_PRODUCT_NAME = "PRODUCTDISTRIBUTE";
	
	
	/**
	 * 校验excel文件是否存在，文件大小,文件格式
	 * @param file 文件对象
	 * @return
	 */
	String validateIsExcel(File file) ;
	
	
	/**
	 * 校验excel文件中某個sheet頁的数据是否符合规范(非空,规则),校验成功后返回
	 * @param filePath excel文件路径
	 * @param sheetNames sheet名數組
	 * @return
	 */
	Map<String,Object> getExcelData(String filePath,String[] sheetNames) throws IOException ;
	
}
