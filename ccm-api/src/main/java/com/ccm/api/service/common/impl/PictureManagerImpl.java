/**
 * 
 */
package com.ccm.api.service.common.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.perf4j.aop.Profiled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.ccm.api.dao.common.PictureDao;
import com.ccm.api.model.base.criteria.PictureCriteria;
import com.ccm.api.model.base.vo.PictureVO;
import com.ccm.api.model.common.Picture;
import com.ccm.api.model.constant.ProjectConfigConstant;
import com.ccm.api.model.enume.PictureType;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.common.PictureManager;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.DownloadPic;

@Service("pictureManager")
public class PictureManagerImpl extends GenericManagerImpl<Picture, String> implements PictureManager {

	private transient static Logger log = Logger.getLogger(PictureManagerImpl.class);

	private PictureDao pictureDao;

	@Autowired
	private Properties projectProperties;

	@Autowired
	public PictureManagerImpl(PictureDao pictureDao) {
		super(pictureDao);
		this.pictureDao = pictureDao;
	}

	/*************************************************************/

	/**
	 * 根据业务类型(酒店，房型）,业务ID，列出具体业务对象关联的的原始图片（未切图）
	 */
	public List<Picture> getBizPictureList(String bizType, String bizId) {

		PictureCriteria pictureCriteria = new PictureCriteria();

		pictureCriteria.setBizType(bizType);

		pictureCriteria.setBizId(bizId);

		pictureCriteria.setSrcFlag("1");

		return pictureDao.searchPicture(pictureCriteria);
	}

	/**
	 * 根据业务ID,业务类型获取默认图片
	 */
	public Picture queryDefaultPicture(String bizId, String bizType) {
		List<Picture> pDefault = pictureDao.queryDefaultPicture(bizId, bizType);
		if (pDefault != null && pDefault.size() > 0) {
			return pDefault.get(0);
		}
		return null;
	}

	/**
	 * 查询系统中存在但渠道上未上传的图片
	 */
	public List<Picture> getUploadChannelPicture(String bizId, String bizType, String channelCode) {
		return pictureDao.getUploadChannelPicture(bizId, bizType, channelCode);
	}

	/**
	 * 构建上传图片信息（例如：路径，文件名称等）
	 */
	public Picture createPicture(String bizId, String bizType, String fileFileName) throws Exception {
		String picAttrName = "";

		String cateNo = "";

		// 1 酒店图片,2 房型图片,3 商家图片
		picAttrName = PictureType.findValue(bizType).getFilepath();
		cateNo = PictureType.findValue(bizType).getValue();

		String sysFileId = UUID.randomUUID().toString().replace("-", "");

		// 获取扩展名
		int start = fileFileName.lastIndexOf("/");
		int end = fileFileName.lastIndexOf(".");

		String fileType;
		if (start < end && end < fileFileName.length()) {
			fileType = fileFileName.substring(end + 1);
		} else {
			fileType = "jpg";
		}

		String picSysName = sysFileId + "." + fileType;

		String url = "/" + picAttrName + "/" + picSysName;

		Picture picture = new Picture();
		picture.setBizId(bizId);
		picture.setBizType(bizType);
		picture.setFileFileName(fileFileName);
		picture.setPicSysName(picSysName);
		picture.setUrl(url);
		picture.setCateId(cateNo);
		return picture;
	}

	/**
	 * 保存图片上传信息（例如：路径，文件名称等）
	 */
	public Picture uploadBizPicture(Picture picture) {

		PictureCriteria pictureCriteria = new PictureCriteria();

		pictureCriteria.setBizType(picture.getBizType());

		pictureCriteria.setBizId(picture.getBizId());

		pictureCriteria.setSrcFlag("1");

		pictureCriteria.setDefaultFlag("1");

		List<Picture> picList = pictureDao.searchPicture(pictureCriteria);

		picture.setCreatedTime(DateUtil.currentDateTime());

		picture.setCutFlag("0");

		if (picList != null && picList.size() > 0) {
			picture.setDefaultFlag("0");
		} else {
			picture.setDefaultFlag("1");
		}

		picture.setSrcFlag("1");

		return pictureDao.save(picture);

	}

	/**
	 * 获取系统上传图片的目录(只获取图片目录）
	 */
	public String getUploadPath() {

		// 文件上传目录
		return projectProperties.getProperty(ProjectConfigConstant.pictureUploadPath);

	}

	/**
	 * 获取系统上传图片的目录（先获取图片目录，再判断目录是否存在，若不存在则新建）
	 */
	public String getUploadPath(String bizType) {

		// 文件上传目录
		String pictureUploadPath = projectProperties.getProperty(ProjectConfigConstant.pictureUploadPath);

		String picAttrName = PictureType.findValue(bizType).getFilepath();

		File dirPath = new File(pictureUploadPath + "/" + picAttrName + "/");
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

		return pictureUploadPath;
	}

	/**
	 * 从渠道上下载图片到系统中
	 */
	public void saveChannelPic(String channelPic, String bizId, String bizType, String channelId) throws Exception {

		if (!StringUtils.hasText(channelPic)) {
			return;
		}

		String[] cPic = channelPic.split(",");

		for (String fileFileName : cPic) {

			Picture picture = createPicture(bizId, bizType, fileFileName);

			String uploadDir = getUploadPath(bizType);

			String fileAllPath = uploadDir + picture.getUrl();

			DownloadPic.saveUrlFile(fileFileName, fileAllPath);

			picture.setChannelCode(channelId);
			picture = uploadBizPicture(picture);
		}

	}

	/**
	 * 随机设置一张图片为默认图片
	 */
	public void setOnePictureAsDefault(String bizType, String bizId) {

		PictureCriteria pictureCriteria = new PictureCriteria();

		pictureCriteria.setBizType(bizType);

		pictureCriteria.setBizId(bizId);

		pictureCriteria.setSrcFlag("1");

		pictureCriteria.setDefaultFlag("0");

		List<Picture> picList = pictureDao.searchPicture(pictureCriteria);

		if (picList == null || picList.isEmpty()) {
			return;
		}

		Picture picture = picList.get(0);

		pictureDao.updatePictureSetDefaultFlag(picture.getPicId());
	}

	public void batchDelete(String bizId, String bizType) {
		List<Picture> pList = pictureDao.getPictureByBizTypeBizId(bizType, bizId);

		for (Picture picture : pList) {

			// 删除图片物理文件
			String pictureUploadPath = getUploadPath();

			// 删除原图
			File delFilePath = new File(pictureUploadPath + picture.getUrl());

			if (delFilePath.exists()) {
				delFilePath.delete();
			}

			// 删除数据库
			remove(picture.getPicId());
		}
	}

	/*************************************************************/

	/**
	 * {@inheritDoc}
	 */
	public void updateBizPicture(String picId, String cateId, String picName) {

		Picture picture = pictureDao.get(picId);

		picture.setPicName(picName);

		picture.setCateId(cateId);

		pictureDao.save(picture);

	}

	/**
	 * {@inheritDoc}
	 */
	public void setPictureAsDefault(String picId, String bizType, String bizId) {
		Picture picture = pictureDao.get(picId);

		picture.setDefaultFlag("1");

		pictureDao.save(picture);

		pictureDao.updateOtherToNotDefault(picId, bizType, bizId);
	}

	@Override
	public List<Picture> getPictureOfHotel(String hotelId) {

		Long fullStart = System.currentTimeMillis();

		List<Picture> picList = pictureDao.getPictureOfHotelAndRooomType(hotelId);

		Long fullEnd = System.currentTimeMillis();
		log.info("getPictureOfRoomType need " + (fullEnd - fullStart) + "ms " + "hotelId:" + hotelId);

		return picList;
	}

	@Profiled
	@Override
	public List<Picture> getPictureOfRoomType(String roomTypeId) {

		Long fullStart = System.currentTimeMillis();

		List<Picture> pictureList = pictureDao.getPictureByBizTypeBizId(PictureType.ROOM_TYPE.getName(), roomTypeId);

		Long fullEnd = System.currentTimeMillis();
		log.info("getPictureOfRoomType need " + (fullEnd - fullStart) + "ms " + "roomTypeId:" + roomTypeId);

		return pictureList;
	}

	public Picture getDefaultPic(String roomTypeId) {
		return pictureDao.getDefaultPic(roomTypeId);
	}

	@Override
	public List<Map<String, String>> getPictureNameAndUrl(String bizType, String bizId, String specName) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("bizType", bizType);
		paramMap.put("bizId", bizId);
		paramMap.put("specName", specName);
		String pictureUrlContext = projectProperties.getProperty("pictureUrlContext");
		paramMap.put("pictureUrlContext", pictureUrlContext);
		List<Map<String, String>> priList = pictureDao.getPictureNameAndUrl(paramMap);
		return priList;
	}

	@Profiled
	@Override
	public Map<String, List<Picture>> getPictureOfRoomType(List<String> roomTypeIdList) {

		Map<String, List<Picture>> pictureMap = new HashMap<String, List<Picture>>();

		List<Picture> pictureList = pictureDao.getPictureByBizTypeBizIdList(PictureType.ROOM_TYPE.getName(), roomTypeIdList);

		for (Picture p : pictureList) {
			List<Picture> pictures = pictureMap.get(p.getBizId());
			if (pictures == null) {
				pictures = new ArrayList<Picture>();
				pictureMap.put(p.getBizId(), pictures);
			}
			pictures.add(p);
		}

		return pictureMap;
	}

	@Override
	public Map<String, String> getDefPicture(String bizType, String bizId, String specName) {

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("bizType", bizType);
		paramMap.put("bizId", bizId);
		paramMap.put("specName", specName);
		String pictureUrlContext = projectProperties.getProperty("pictureUrlContext");
		paramMap.put("pictureUrlContext", pictureUrlContext);
		Map<String, String> map = pictureDao.getDefPicture(paramMap);

		return map;
	}

	@Override
	public List<PictureVO> getDefPicture(String bizType, List<String> bizIdList, String specName) {
		String pictureUrlContext = projectProperties.getProperty("pictureUrlContext");
		List<PictureVO> voList = pictureDao.getDefPicture(bizType, bizIdList, specName, pictureUrlContext);
		return voList;
	}

	@Override
	public PictureVO getRoomTypeDefaultPciture(String roomTypeId, String specName) {
		String pictureUrlContext = projectProperties.getProperty("pictureUrlContext");
		List<String> roomTypeIdList = new ArrayList<String>();
		roomTypeIdList.add(roomTypeId);
		List<PictureVO> list = pictureDao.getDefPicture(PictureType.ROOM_TYPE.getName(), roomTypeIdList, specName, pictureUrlContext);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Picture> getHotelPictureOfHotelId(String hotelId) {

		Long fullStart = System.currentTimeMillis();

		List<Picture> pList = pictureDao.getPictureByBizTypeBizId(PictureType.HOTEL.getName(), hotelId);

		Long fullEnd = System.currentTimeMillis();
		log.info("getHotelPictureOfHotelId need " + (fullEnd - fullStart) + "ms " + "hotelId:" + hotelId);

		return pList;
	}

	@Override
	public List<Picture> queryPicture(Picture picture) {

		return pictureDao.queryPicture(picture);
	}

	@Override
	public Picture getPictureByPicSysNameAndSpecNameAndBizId(String picSysName, String specName, String bizId) {
		PictureCriteria pictureCriteria = new PictureCriteria();

		pictureCriteria.setPicSysName(picSysName);

		pictureCriteria.setBizId(bizId);

		pictureCriteria.setSpecName(specName);

		// pictureCriteria.setDefaultFlag("1");
		List<Picture> list = pictureDao.searchPicture(pictureCriteria);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updatePictureBizIdToPId(String pId, String bizId) {
		pictureDao.updatePictureBizIdToPId(pId, bizId);
	}

	@Override
	public void deletePictureByBizId(String bizId) {
		pictureDao.deletePictureByBizId(bizId);
	}

}
