/**
 * 
 */
package com.ccm.api.dao.common;

import java.util.List;
import java.util.Map;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.base.criteria.PictureCriteria;
import com.ccm.api.model.base.vo.PictureVO;
import com.ccm.api.model.common.Picture;

public interface PictureDao extends GenericDao<Picture, String> {

	/*************************************************************/

	/**
	 * 查询图片
	 * 
	 * @param pictureCriteria
	 *            图片查询bean
	 * @return
	 */
	public List<Picture> searchPicture(PictureCriteria pictureCriteria);

	/**
	 * 获取默认图片
	 * 
	 * @param bizId
	 * @param bizType
	 * @return
	 */
	public List<Picture> queryDefaultPicture(String bizId, String bizType);

	/**
	 * 获取系统上传的图片未发布到渠道
	 * 
	 * @param bizId
	 * @param bizType
	 * @param channelCode
	 * @return
	 */
	public List<Picture> getUploadChannelPicture(String bizId, String bizType, String channelCode);

	/*************************************************************/

	/**
	 * 更新某一业务对象图片除picId指定的图片以外其他图片为非默认
	 * 
	 * @param picId
	 * @param bizType
	 * @param bizId
	 */
	public void updateOtherToNotDefault(String picId, String bizType, String bizId);

	/**
	 * 获得某特定物品的所有图片
	 * 
	 * @param hotelId
	 * @return
	 */
	public List<Picture> getPictureByBizTypeBizId(String bizType, String bizId);

	/**
	 * 获取未处理过的图片
	 * 
	 * @return
	 */
	public List<Picture> getNeedCutPictures();

	/**
	 * 根据房型ID获取默认图片
	 * 
	 * @param bizId
	 * @return
	 */
	public Picture getDefaultPic(String bizId);

	/**
	 * 
	 * @param roomType
	 * @param roomTypeId
	 * @return
	 */
	public Integer getPictureCountByBizTypeBizId(String roomType, String roomTypeId);

	public List<Map<String, String>> getPictureNameAndUrl(Map<String, String> paramMap);

	/**
	 * 
	 * @param roomType
	 * @param roomTypeIdList
	 * @return
	 */
	public List<Picture> getPictureByBizTypeBizIdList(String roomType, List<String> roomTypeIdList);

	public Map<String, String> getDefPicture(Map<String, String> paramMap);

	/**
	 * 
	 * @param bizType
	 * @param bizId
	 * @param specName
	 * @param pictureUrlContext
	 * @return
	 */
	List<PictureVO> getDefPicture(String bizType, List<String> bizIdList, String specName, String pictureUrlContext);

	/**
	 * 通用图片查询
	 * 
	 * @param picture
	 * @return
	 */
	public List<Picture> queryPicture(Picture picture);

	/**
	 * 删除当前图片的切图纪录
	 * 
	 * @param picture
	 */
	public void delCurrentFileCut(Picture picture);

	/**
	 * 得到当前酒店的所有照片，包括酒店照片和房型照片
	 * 
	 * @param hotelId
	 * @return
	 */
	public List<Picture> getPictureOfHotelAndRooomType(String hotelId);

	void updatePictureByDefaultFlag(String bizType, String bizId);

	void updatePictureSetDefaultFlag(String picId);

	void updatePictureBizIdToPId(String pId, String bizId);

	void deletePictureByBizId(String bizId);
}
