package com.ccm.mc.webapp.action.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.model.common.Picture;
import com.ccm.api.model.constant.ProjectConfigConstant;
import com.ccm.api.model.constant.YesNo;
import com.ccm.api.model.enume.PictureType;
import com.ccm.api.service.common.PictureManager;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.RequestUtil;

/**
 * 图片上传类
 */
public class PictureUploadAction extends BaseAction {

	private static final long serialVersionUID = -9208910183310010569L;

	private File file;

	private String fileContentType;

	private String fileFileName;

	private String name;

	private String bizType; // 业务类型

	private String bizId; // 业务对象ID

	private String bizDesc; // 对象名称描述

	private String uploadType; // 上传方式 1 swf上传 2 普通上传

	/** 批量保存使用 **/

	private String[] picIds; // 图片ID

	private String[] cateIds; // 图片分类

	private String[] picNames; // 图片名称

	/** 单个保存使用 **/
	private String picId; // 图片id

	private String cateId; // 图片分类

	private String picName; // 图片名称

	/** 默认图片 **/
	private String defaultFlag; // 默认图片

	private String pictureUrlContext; // 上下文路径

	private String from; // 标识 批量编辑页面的来源,有可能是新上传后,或者,全量编辑

	private List<Picture> pictureList;// 图片列表

	String authFlag;// 商家认证状态, for bizType=1时候使用

	private PictureManager pictureManager;// 图片信息

	/****************************************************************/

	/**
	 * 图片上传方法
	 */
	public String execute() {

		String errorMsg = null;

		// 文件名
		Map<String, String> resultMap = new HashMap<String, String>();

		String swfErrorCode = "";

		try {
			if (errorMsg == null) {

				resultMap.put("errorCode", "0");
				resultMap.put("name", name);

				// retrieve the file data
				InputStream stream = new FileInputStream(file);

				int maxSize = 512000;
				if (bizType.equals(PictureType.HOTEL.getName()) && stream.available() > maxSize) {
					swfErrorCode = "-8";
					errorMsg = "上传图片大小超过!" + maxSize + "byte";
					resultMap.put("errorCode", "99");
					resultMap.put("errorMsg", errorMsg);
				} else {

					// 1 酒店图片,2 房型图片,3 商家图片
					Picture picture = pictureManager.createPicture(bizId, bizType, fileFileName);
					String faPtha = pictureManager.getUploadPath(bizType);

					// write the file to the file specified
					OutputStream bos = new FileOutputStream(faPtha + picture.getUrl());
					int bytesRead;

					byte[] buffer = new byte[8192];

					while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
						bos.write(buffer, 0, bytesRead);
					}

					bos.close();
					stream.close();

					picture = pictureManager.uploadBizPicture(picture);

					addPictureToSession(picture);

					// 原始文件名
					resultMap.put("fileFileName", fileFileName);

					// 图片名称
					resultMap.put("picSysName", picture.getPicSysName());

					resultMap.put("picId", picture.getPicId());
					resultMap.put("url", picture.getUrl());

					swfErrorCode = "1";

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			swfErrorCode = "-1";
			errorMsg = "上传图片系统错误!";
			resultMap.put("errorCode", "99");
			resultMap.put("errorMsg", errorMsg);
		}

		try {

			if ("normal".equals(uploadType)) {

				this.getResponse().getWriter().print(RequestUtil.escape(JSONObject.toJSONString(resultMap)));

			} else {

				this.getResponse().getWriter().print(swfErrorCode);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 保存新上传图片到session
	 * 
	 * @param picture
	 */
	@SuppressWarnings("unchecked")
	public void addPictureToSession(Picture picture) {
		HttpSession session = this.getSession();
		System.out.println("sET:" + session.getId());
		List<Picture> newUploadPicList = new ArrayList<Picture>();
		if (session.getAttribute("newUploadPictures") != null) {
			newUploadPicList = (List<Picture>) session.getAttribute("newUploadPictures");

		}
		newUploadPicList.add(picture);
		session.setAttribute("newUploadPictures", newUploadPicList);
	}

	/**
	 * 通过业务ID,业务类型从系统中查询图片
	 * 
	 * @return
	 */
	public String picGet() {
		if (StringUtils.hasText(bizId)) {
			pictureList = pictureManager.getBizPictureList(bizType, bizId);
			pictureUrlContext = projectProperties.getProperty(ProjectConfigConstant.pictureUrlContext);
		}
		return "pictList";
	}

	/**
	 * 单个图片删除
	 * 
	 * @return
	 */
	public String ajaxDelete() {
		// 文件名

		if (picId != null) {
			Map<String, String> resultMap = new HashMap<String, String>();
			try {

				// 删除图片物理文件
				Picture picture = pictureManager.get(picId);

				String pictureUploadPath = pictureManager.getUploadPath(); // this.getProjectConfig().getPicCutConfig();

				// 删除原图
				File delFilePath = new File(pictureUploadPath + picture.getUrl());

				if (delFilePath.exists()) {
					delFilePath.delete();
				}

				// 删除数据库
				pictureManager.remove(picId);

				// 当删除的图片是默认图片时，随机设置一张已存在图片为默认图片
				if (picture.getDefaultFlag().equals(YesNo.YES)) {
					pictureManager.setOnePictureAsDefault(picture.getBizType(), picture.getBizId());
				}

				resultMap.put("errorCode", "0");

			} catch (Exception e) {

				resultMap.put("errorCode", "99");
				resultMap.put("errorMsg", "系统错误!");
			}
			try {

				this.getResponse().getWriter().print(RequestUtil.escape(JSONObject.toJSONString(resultMap)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/****************************************************************/

	// 图片列表-原始图片列表
	public String pictureList() {

		pictureList = pictureManager.getBizPictureList(bizType, bizId);

		if ("1".equals(bizType)) {

		}

		pictureUrlContext = projectProperties.getProperty(ProjectConfigConstant.pictureUrlContext);

		return "picList";
	}

	// 进入批量图片上传
	public String normalUpload() {

		return "normalUpload";
	}

	// 进入批量图片上传
	public String batchUpload() {

		return "batchUpload";
	}

	// 图片信息批量编辑-批量上传图片后或者用户点击批量编辑时候
	public String batchEdit() {

		String bizId = this.getRequest().getParameter("bizId");
		String bizType = this.getRequest().getParameter("bizType");

		pictureUrlContext = projectProperties.getProperty(ProjectConfigConstant.pictureUrlContext);

		if ("upload".equals(from)) {
			pictureList = getPictureToSession();
		} else {
			pictureList = pictureManager.getBizPictureList(bizType, bizId);
		}

		return "batchEdit";
	}

	// 图片信息批量编辑-批量上传图片后或者用户点击批量编辑时候
	public String batchSave() {

		if (picIds != null) {

			for (int i = 0; i < picIds.length; i++) {
				String picId = picIds[i];

				String picName = picNames[i];
				String cateId = null;
				if (cateIds != null) {
					cateId = cateIds[i];
				}

				pictureManager.updateBizPicture(picId, cateId, picName);

			}

		}
		if (defaultFlag != null && !"".equals(defaultFlag)) {
			pictureManager.setPictureAsDefault(defaultFlag, bizType, bizId);

		}

		removePictureToSession();
		return "toPicList";
	}

	// 图片信息批量编辑-批量上传图片后或者用户点击批量编辑时候
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String ajaxSave() {
		// 文件名

		if (picId != null) {
			Map resultMap = new HashMap();
			try {
				pictureManager.updateBizPicture(picId, cateId, picName);
				resultMap.put("errorCode", "0");
				resultMap.put("cateId", cateId);
				resultMap.put("picName", picName);
			} catch (Exception e) {

				resultMap.put("errorCode", "99");
				resultMap.put("errorMsg", "系统错误!");
			}
			try {

				System.out.println(JSONObject.toJSONString(resultMap));

				this.getResponse().getWriter().print(JSONObject.toJSONString(resultMap));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 图片管理-设置为默认
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String ajaxSetDefault() {
		// 文件名

		if (picId != null) {
			Map resultMap = new HashMap();
			try {
				pictureManager.setPictureAsDefault(picId, bizType, bizId);
				resultMap.put("errorCode", "0");

			} catch (Exception e) {

				resultMap.put("errorCode", "99");
				resultMap.put("errorMsg", "系统错误!");
			}
			try {

				System.out.println(JSONObject.toJSONString(resultMap));

				this.getResponse().getWriter().print(RequestUtil.escape(JSONObject.toJSONString(resultMap)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 保存新上传图片到session
	@SuppressWarnings("unchecked")
	public List<Picture> getPictureToSession() {

		HttpSession session = this.getSession();
		System.out.println("GET:" + session.getId());
		return (List<Picture>) session.getAttribute("newUploadPictures");

	}

	// 清除session
	public void removePictureToSession() {

		HttpSession session = this.getSession();
		session.removeAttribute("newUploadPictures");
	}

	public String getUploadType() {
		return uploadType;
	}

	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}

	public String getBizDesc() {
		return bizDesc;
	}

	public void setBizDesc(String bizDesc) {
		this.bizDesc = bizDesc;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPictureUrlContext() {
		return pictureUrlContext;
	}

	public void setPictureUrlContext(String pictureUrlContext) {
		this.pictureUrlContext = pictureUrlContext;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public File getFile() {
		return file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String[] getPicIds() {
		return picIds;
	}

	public void setPicIds(String[] picIds) {
		this.picIds = picIds;
	}

	public String[] getCateIds() {
		return cateIds;
	}

	public void setCateIds(String[] cateIds) {
		this.cateIds = cateIds;
	}

	public String[] getPicNames() {
		return picNames;
	}

	public void setPicNames(String[] picNames) {
		this.picNames = picNames;
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public String getCateId() {
		return cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public PictureManager getPictureManager() {
		return pictureManager;
	}

	public void setPictureList(List<Picture> pictureList) {
		this.pictureList = pictureList;
	}

	public List<Picture> getPictureList() {
		return pictureList;
	}

	public String getAuthFlag() {
		return authFlag;
	}

	public void setAuthFlag(String authFlag) {
		this.authFlag = authFlag;
	}

	public void setPictureManager(PictureManager pictureManager) {
		this.pictureManager = pictureManager;
	}
}
