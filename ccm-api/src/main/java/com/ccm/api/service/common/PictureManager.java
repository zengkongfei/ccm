/**
 * 
 */
package com.ccm.api.service.common;

import java.util.List;
import java.util.Map;

import com.ccm.api.model.base.vo.PictureVO;
import com.ccm.api.model.common.Picture;
import com.ccm.api.service.base.GenericManager;

public interface PictureManager extends GenericManager<Picture, String> {

	/**
	 * 根据业务类型(酒店，房型）,业务ID，列出具体业务对象关联的的原始图片（未切图）
	 * 
	 * @param bizType
	 *            业务类型
	 * @param bizId
	 *            业务ID
	 * @return
	 */
	public List<Picture> getBizPictureList(String bizType, String bizId);

	/**
	 * 根据业务ID,业务类型获取默认图片
	 * 
	 * @param bizId
	 * @param bizType
	 * @return
	 */
	public Picture queryDefaultPicture(String bizId, String bizType);

	/**
	 * 查询系统中存在但渠道上未上传的图片
	 * 
	 * @param bizId
	 * @param bizType
	 * @param channelCode
	 * @return
	 */
	public List<Picture> getUploadChannelPicture(String bizId, String bizType, String channelCode);

	/**
	 * 构建上传图片信息（例如：路径，文件名称等）
	 * 
	 * @param bizId
	 * @param bizType
	 * @param fileFileName
	 * @return
	 * @throws Exception
	 */
	public Picture createPicture(String bizId, String bizType, String fileFileName) throws Exception;

	/**
	 * 保存图片上传信息（例如：路径，文件名称等）
	 * 
	 * @param picture
	 * @return
	 */
	public Picture uploadBizPicture(Picture picture);

	/**
	 * 获取系统上传图片的目录(只获取图片目录）
	 * 
	 * @return
	 */
	public String getUploadPath();

	/**
	 * 获取系统上传图片的目录（先获取图片目录，再判断目录是否存在，若不存在则新建）
	 * 
	 * @param bizType
	 * @return
	 */
	public String getUploadPath(String bizType);

	/**
	 * 从渠道上下载图片到系统中
	 * 
	 * @param channelPic
	 * @param bizId
	 * @param bizType
	 * @param channelId
	 * @throws Exception
	 */
	public void saveChannelPic(String channelPic, String bizId, String bizType, String channelId) throws Exception;

	/**
	 * 随机设置一张图片为默认图片
	 * 
	 * @param bizType
	 * @param bizId
	 */
	public void setOnePictureAsDefault(String bizType, String bizId);

	public void batchDelete(String bizId, String bizType);

	/*************************************************************/

	/**
	 * 根据图片id更新图片的基本信息（类别,图片业务名称)
	 * 
	 * @param picId
	 * @param cateId
	 * @param picName
	 */
	public void updateBizPicture(String picId, String cateId, String picName);

	/**
	 * 设置图片为默认图片,同时将其他图片未非默认
	 * 
	 * @param picId
	 * @param bizType
	 * @param bizId
	 */
	public void setPictureAsDefault(String picId, String bizType, String bizId);

	/**
	 * 获取酒店+所有房型图片
	 * 
	 * @return
	 */
	public List<Picture> getPictureOfHotel(String hotelId);

	/**
	 * 获取酒店图片
	 * 
	 * @return
	 */
	public List<Picture> getHotelPictureOfHotelId(String hotelId);

	/**
	 * 
	 * @return
	 */
	public List<Picture> getPictureOfRoomType(String roomTypeId);

	public Picture getDefaultPic(String roomTypeId);

	public List<Map<String, String>> getPictureNameAndUrl(String bizType, String bizId, String specName);

	public Map<String, String> getDefPicture(String bizType, String bizId, String specName);

	/**
	 * 
	 * @param roomTypeIdList
	 * @return
	 */
	public Map<String, List<Picture>> getPictureOfRoomType(List<String> roomTypeIdList);

	public List<PictureVO> getDefPicture(String bizType, List<String> bizIdList, String specName);

	public PictureVO getRoomTypeDefaultPciture(String roomTypeId, String specName);

	/**
	 * 通用图片查询
	 * 
	 * @param picture
	 * @return
	 */
	public List<Picture> queryPicture(Picture picture);

	/**
	 * 根据
	 * 
	 * @param picSysName
	 * @param specName
	 * @param bizId
	 * @return
	 */
	public Picture getPictureByPicSysNameAndSpecNameAndBizId(String picSysName, String specName, String bizId);
	
	/**
	 * 修改图片表中的bizId为对应的主键ID
	 * @param pId
	 * @param bizId
	 */
	void updatePictureBizIdToPId(String pId, String bizId);
	
	/**
	 * 通过bizId删除对应的图片记录
	 * @param bizId
	 */
	void deletePictureByBizId(String bizId);

}