package com.ccm.mc.webapp.action.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.UUID;

import com.ccm.api.model.constant.ProjectConfigConstant;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;

/**
 * 图片上传类
 */
public class CKEditorPictureUploadAction extends BaseAction {

	private static final long serialVersionUID = -9208910183310010569L;

	private File upload;

	private String uploadFileName;

	/**
	 * ckeditor 上传图片
	 */
	public void uploadCKEditorPicture() {

		// 按月份建目录，以免在一个目录下有太多的文件
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		String subPath = "/ckeditor/" + df.format(DateUtil.currentDateTime()) + "/";
		String uploadDir = projectProperties.getProperty(ProjectConfigConstant.pictureUploadPath) + subPath;
		File dirPath = new File(uploadDir);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

		StringBuffer result = new StringBuffer(300);
		result.append("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(");
		String funNum = getRequest().getParameter("CKEditorFuncNum");
		String url = "";
		String message = null;
		result.append(funNum);

		// 上传文件保存
		if (upload != null) {
			InputStream stream = null;
			OutputStream bos = null;
			try {
				String picType = "";
				if (uploadFileName != null && uploadFileName.lastIndexOf(".") > -1) {
					picType = uploadFileName.substring(uploadFileName.lastIndexOf("."));
				}

				int bytesRead;
				byte[] buffer = new byte[8192];

				String picFileName = UUID.randomUUID().toString() + picType;

				stream = new FileInputStream(upload);
				bos = new FileOutputStream(uploadDir + picFileName);
				while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
					bos.write(buffer, 0, bytesRead);
				}
				url = propertyConfigurer.getProperty("pictureUrlContext") + subPath + picFileName;

			} catch (Exception e) {
				log.error(e);
				message = "上传失败！";
			} finally {
				try {
					bos.close();
					stream.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}

		result.append(", '").append(url);
		if (message != null) {
			result.append("', '").append(message);
		}
		result.append("');</script>");

		try {
			getResponse().getWriter().write(result.toString());
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUpload(String upload) {
	}

	public File getUpload() {
		return upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

}
