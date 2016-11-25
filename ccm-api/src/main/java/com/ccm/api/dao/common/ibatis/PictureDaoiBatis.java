/**
 * 
 */
package com.ccm.api.dao.common.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.common.PictureDao;
import com.ccm.api.model.base.criteria.PictureCriteria;
import com.ccm.api.model.base.vo.PictureVO;
import com.ccm.api.model.common.Picture;
import com.ccm.api.model.enume.PictureSpecEnum;
import com.ccm.api.model.enume.PictureType;

@Repository("pictrueDao")
public class PictureDaoiBatis extends GenericDaoiBatis<Picture, String> implements PictureDao {

	/**
	 * Constructor that sets the entity to User.class.
	 */
	public PictureDaoiBatis() {
		super(Picture.class);
	}

	/*************************************************************/

	@SuppressWarnings({ "unchecked" })
	/**
	 * 查询图片
	 */
	public List<Picture> searchPicture(PictureCriteria pictureCriteria) {

		return getSqlMapClientTemplate().queryForList("searchPicture", pictureCriteria);
	}

	/**
	 * 获取默认图片
	 */
	@SuppressWarnings("unchecked")
	public List<Picture> queryDefaultPicture(String bizId, String bizType) {
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("bizType", bizType);
		paraMap.put("bizId", bizId);
		return getSqlMapClientTemplate().queryForList("getDefaultPicture", paraMap);
	}

	/**
	 * 获取系统上传的图片未发布到渠道
	 */
	@SuppressWarnings("unchecked")
	public List<Picture> getUploadChannelPicture(String bizId, String bizType, String channelCode) {
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("bizType", bizType);
		paraMap.put("bizId", bizId);
		paraMap.put("channelCode", channelCode);
		return getSqlMapClientTemplate().queryForList("getUploadChannelPicture", paraMap);
	}

	/*************************************************************/

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateOtherToNotDefault(String picId, String bizType, String bizId) {
		Map paraMap = new HashMap();
		paraMap.put("picId", picId);
		paraMap.put("bizType", bizType);
		paraMap.put("bizId", bizId);

		getSqlMapClientTemplate().update("updateOtherToNotDefault", paraMap);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Picture> getPictureOfHotelAndRooomType(String hotelId) {
		return (List<Picture>) getSqlMapClientTemplate().queryForList("getPictureOfHotelAndRooomType", hotelId);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Picture> getPictureByBizTypeBizId(String bizType, String bizId) {

		Map paraMap = new HashMap();
		paraMap.put("bizType", bizType);
		paraMap.put("bizId", bizId);

		return (List<Picture>) getSqlMapClientTemplate().queryForList("searchPicture", paraMap);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Picture> getNeedCutPictures() {
		Map paraMap = new HashMap();
		paraMap.put("cutFlag", "0");
		paraMap.put("srcFlag", "1");

		// return (List<Picture>)
		// getSqlMapClientTemplate().queryForList("searchPicture", paraMap);

		return (List<Picture>) getSqlMapClientTemplate().queryForList("searchPictureForCut", paraMap, 0, 50);

	}

	@SuppressWarnings("unchecked")
	public Picture getDefaultPic(String bizId) {
		PictureCriteria pc = new PictureCriteria();
		pc.setBizType(PictureType.ROOM_TYPE.getName());
		pc.setSrcFlag("0");
		pc.setSpecName(PictureSpecEnum.WEBCUT_60X40.getName());
		pc.setBizId(bizId);
		pc.setDefaultFlag("1");
		List<Picture> picList = getSqlMapClientTemplate().queryForList("searchPicture", pc);
		if (picList != null && picList.size() > 0) {
			return picList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Integer getPictureCountByBizTypeBizId(String bizType, String bizId) {
		Map paraMap = new HashMap();
		paraMap.put("bizType", bizType);
		paraMap.put("bizId", bizId);
		return (Integer) getSqlMapClientTemplate().queryForObject("getPictureCountByBizTypeBizId", paraMap);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Picture> getPictureByBizTypeBizIdList(String bizType, List<String> bizIdList) {
		Map paraMap = new HashMap();
		paraMap.put("bizType", bizType);
		paraMap.put("bizIdList", bizIdList);
		return getSqlMapClientTemplate().queryForList("getPictureByBizTypeBizIdList", paraMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getPictureNameAndUrl(Map<String, String> paramMap) {
		List<Map<String, String>> picList = getSqlMapClientTemplate().queryForList("getPictureNameAndUrl", paramMap);
		return picList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getDefPicture(Map<String, String> paramMap) {
		List<Map<String, String>> list = getSqlMapClientTemplate().queryForList("getDefPicture", paramMap);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PictureVO> getDefPicture(String bizType, List<String> bizIdList, String specName, String pictureUrlContext) {

		if (bizIdList == null || bizIdList.size() == 0) {
			return new ArrayList<PictureVO>();
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bizType", bizType);
		paramMap.put("bizIdList", bizIdList);
		paramMap.put("specName", specName);

		paramMap.put("pictureUrlContext", pictureUrlContext);

		List<PictureVO> voList = getSqlMapClientTemplate().queryForList("getDefPicturelist", paramMap);

		return voList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Picture> queryPicture(Picture picture) {
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("bizType", picture.getBizType());
		paraMap.put("bizId", picture.getBizId());
		paraMap.put("specName", picture.getSpecName());
		paraMap.put("picSysName", picture.getPicSysName());
		return getSqlMapClientTemplate().queryForList("queryPicture", paraMap);
	}

	@Override
	public void delCurrentFileCut(Picture picture) {
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("bizType", picture.getBizType());
		paraMap.put("bizId", picture.getBizId());
		paraMap.put("picSysName", picture.getPicSysName());

		getSqlMapClientTemplate().delete("delCurrentFileCut", paraMap);
	}

	@Override
	public void updatePictureByDefaultFlag(String bizType, String bizId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("bizType", bizType);
		map.put("String", bizId);
		getSqlMapClientTemplate().update("updateDefaultFlag", map);
	}

	@Override
	public void updatePictureSetDefaultFlag(String picId) {
		getSqlMapClientTemplate().update("updatePictureSetDefaultFlag", picId);
	}
	
	@Override
	public void updatePictureBizIdToPId(String pId,String bizId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("pId", pId);
		params.put("bizId", bizId);
		getSqlMapClientTemplate().update("updatePictureBizIdToPId", params);
	}
	
	@Override
	public void deletePictureByBizId(String bizId){
		getSqlMapClientTemplate().delete("deletePictureByBizId", bizId);
	}

}
