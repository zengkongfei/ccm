package com.ccm.common.pic;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ccm.api.common.exception.BizException;
import com.ccm.api.util.NumberUtil;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片处理类 功能：根据业务定制的图片处理逻辑，最小修剪后缩放到比例，或者直接匹配到比例 支持连续切割，同比例连续切割,性能更好
 */
public class ImageBean {

	/**
	 * 几种常见的图片格式
	 */
	public static String IMAGE_TYPE_GIF = "gif";// 图形交换格式

	public static String IMAGE_TYPE_JPG = "jpg";// 联合照片专家组

	public static String IMAGE_TYPE_JPEG = "jpeg";// 联合照片专家组

	public static String IMAGE_TYPE_BMP = "bmp";// 英文Bitmap（位图）的简写，它是Windows操作系统中的标准图像文件格式

	public static String IMAGE_TYPE_PNG = "png";// 可移植网络图形

	public static String IMAGE_TYPE_PSD = "psd";// Photoshop的专用格式Photoshop

	public static String MATCH_MODEL_CUT = "cut"; // 匹配模式,剪切后匹配

	public static String MATCH_MODEL_SCALE = "scale";// 缩放后最大匹配

	private int srcWidth; // 图片宽

	private int srcHeight; // 图片高

	private BufferedImage srcBufImg; // 原图buf

	private BufferedImage fitBufImg; // 中间图buf,最少剪切的适合比例图，以后的变换将在此基础上缩放

	private BufferedImage destBufImg; // 结果图片buf

	private int fitWidth = 0; // 中间图片宽

	private int fitHeight = 0; // 中间图片高

	private String matchMode = ImageBean.MATCH_MODEL_SCALE; // 匹配模式

	private String fileType; // 文件类型

	private boolean watermark; // 是否水印

	private String waterFileName; // 水印文件

	public String getWaterFileName() {
		return waterFileName;
	}

	public void setWaterFileName(String waterFileName) {
		this.waterFileName = waterFileName;
	}

	public boolean isWatermark() {
		return watermark;
	}

	public void setWatermark(boolean watermark) {
		this.watermark = watermark;
	}

	public void setMatchMode(String matchMode) {
		this.matchMode = matchMode;
	}

	/**
	 * 构造函数,读入图片原始文件
	 * 
	 * @param srcFileName
	 */
	public ImageBean(String srcFileName) {

		try {

			srcBufImg = ImageIO.read(new File(srcFileName)); // 读入文件

			srcWidth = srcBufImg.getWidth(); // 得到源图宽

			srcHeight = srcBufImg.getHeight(); // 得到源图高

			int index = srcFileName.lastIndexOf(".");

			fileType = srcFileName.substring(index + 1);

			// fitBufImg = srcBufImg;

			// fitWidth = srcWidth;

			// fitHeight = srcHeight;

		} catch (IOException e) {
			e.printStackTrace();
			throw new BizException("error.file.notExist", "文件不存在!");
		}

	}

	/**
	 * 生成图片
	 * 
	 * @param width
	 * @param height
	 */
	public ImageSize createImage(int width, int height) {

		if (width == 0) {
			width = this.srcWidth;
		} else if (height == 0) {
			height = this.srcHeight;
		} else if (this.srcHeight < height && this.srcWidth < width) {
			width = this.srcWidth;
			height = this.srcHeight;
		}

		float scaleRatio = 1;

		if (ImageBean.MATCH_MODEL_CUT.equals(this.matchMode)) {

			// 新的原图
			if (this.fitWidth == 0) {

				cutToFitBufImg(width, height);
			} else {
				float ratio = (float) width / (float) height;

				float fitRatio = (float) this.fitWidth / (float) this.fitHeight;

				// 精确切图，不再认为相差一像素不用切图。
				// float dif = ratio - fitRatio;

				if (!NumberUtil.compareFloatEqual(ratio, fitRatio)) {

					cutToFitBufImg(width, height);
				}
				// if( -0.001f < dif && dif < 0.001f ){

				// }else{

				// }
			}

			scaleRatio = getScaleRatio(this.fitWidth, this.fitHeight, width, height);

		} else {

			scaleRatio = getScaleRatio(this.srcWidth, this.srcHeight, width, height);

		}

		return scale(scaleRatio);

	}

	/**
	 * 最大保留修剪成指定的长宽比例图片对象
	 * 
	 * @param ratioWidth
	 *            比例的宽
	 * @param ratioHeight
	 *            比例的高
	 */
	private void cutToFitBufImg(int ratioWidth, int ratioHeight) {

		try {

			ImageSize imageSize = getCutSize(ratioWidth, ratioHeight);

			Image image = srcBufImg.getScaledInstance(imageSize.getSrcX(), imageSize.getSrcY(), Image.SCALE_SMOOTH);

			// 四个参数分别为图像起点坐标和宽高 即: CropImageFilter(int x,int y,int width,int
			// height)

			ImageFilter cropFilter = new CropImageFilter(imageSize.getStartX(), imageSize.getStartY(), imageSize.getFitX(), imageSize.getFitY());

			Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));

			if (fitBufImg != null) {
				fitBufImg.flush();
				fitBufImg = null;
			}

			fitBufImg = new BufferedImage(imageSize.getFitX(), imageSize.getFitY(), BufferedImage.TYPE_INT_RGB);

			this.fitWidth = imageSize.getFitX();
			this.fitHeight = imageSize.getFitY();

			Graphics g = fitBufImg.getGraphics();

			g.drawImage(img, 0, 0, imageSize.getFitX(), imageSize.getFitY(), null); // 绘制切割后的图

			g.dispose();

			if (image != null) {

				image.flush();

				image = null;

			}

			if (img != null) {
				img.flush();
				img = null;
			}

			// 输出为文件
			// ImageIO.write(tag, "JPEG", new File(result));

		} catch (Exception e) {

			e.printStackTrace();
			throw new BizException("error.file.cutError", "切图错误!");
		}

	}

	/**
	 * 自动匹配图片尺寸
	 * 
	 * @param imageSize
	 * @return
	 */
	private ImageSize getCutSize(int toWidth, int toHeight) {

		ImageSize imageSize = new ImageSize(this.srcWidth, this.srcHeight, toWidth, toHeight);

		int x = toWidth;

		int y = toHeight;

		int xx = this.srcWidth;

		int yy = this.srcHeight;

		float toRatio = (float) x / (float) y;

		float fromRatio = (float) xx / (float) yy;

		int startX = 0;

		int startY = 0;

		if (fromRatio > toRatio) {

			float xx1 = (float) x * (float) yy / (float) y;

			startX = (int) ((xx - xx1) / 2);

			xx = (int) xx1;

		} else {

			float yy1 = (float) xx * (float) y / (float) x;

			startY = (int) ((yy - yy1) / 2);

			yy = (int) yy1;

		}

		imageSize.setFitX(xx);

		imageSize.setFitY(yy);

		imageSize.setStartX(startX);

		imageSize.setStartY(startY);

		return imageSize;
	}

	/**
	 * 计算缩放比例
	 * 
	 * @param imageSize
	 * @return
	 */
	private float getScaleRatio(int fromWidth, int fromHeight, int toWidth, int toHeight) {

		float fromRatio = (float) fromWidth / (float) fromHeight;

		float toRatio = (float) toWidth / (float) toHeight;

		float ratio = 1;

		if (fromRatio > toRatio) {

			ratio = (float) toWidth / (float) fromWidth;

		} else {

			ratio = (float) toHeight / (float) fromHeight;

		}

		return ratio;
	}

	/**
	 * 缩放图像（按比例缩放）
	 * 
	 * @param ratio
	 *            缩放比例
	 */
	private ImageSize scale(float ratio) {

		int width = 0;

		int height = 0;

		Image image = null;

		if (ImageBean.MATCH_MODEL_CUT.equals(this.matchMode)) {

			width = (int) (this.fitWidth * ratio);

			height = (int) (this.fitHeight * ratio);

			image = fitBufImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);

		} else {
			width = (int) (this.srcWidth * ratio);

			height = (int) (this.srcHeight * ratio);

			image = srcBufImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		}

		destBufImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 水印
		if (this.watermark) {

			Graphics2D g = destBufImg.createGraphics();

			g.drawImage(image, 0, 0, width, height, null);

			Image src_biao = null;
			try {
				src_biao = ImageIO.read(new File(waterFileName));

				int wideth_biao = src_biao.getWidth(null);

				int height_biao = src_biao.getHeight(null);

				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1f));

				g.drawImage(src_biao, (width - wideth_biao - 10), (height - height_biao - 10), wideth_biao, height_biao, null);

				g.dispose();
			} catch (IOException e) {

				e.printStackTrace();
				throw new BizException("error.file.waterMarkError", "水印图片错误!");
			}

		} else {
			Graphics g = destBufImg.getGraphics();

			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
		}

		if (image != null) {
			image.flush();
			image = null;
		}

		return new ImageSize(width, height);

	}

	/**
	 * 参考代码
	 */
	/*
	 * public static void reduceImg(String imgsrc, String imgdist, int
	 * widthdist, int heightdist) { try { File srcfile = new File(imgsrc); if
	 * (!srcfile.exists()) { return; } Image src =
	 * javax.imageio.ImageIO.read(srcfile);
	 * 
	 * BufferedImage tag= new BufferedImage((int) widthdist, (int) heightdist,
	 * BufferedImage.TYPE_INT_RGB);
	 * 
	 * tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,
	 * Image.SCALE_SMOOTH), 0, 0, null);
	 * tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,
	 * Image.SCALE_AREA_AVERAGING), 0, 0, null);
	 * 
	 * FileOutputStream out = new FileOutputStream(imgdist); JPEGImageEncoder
	 * encoder = JPEGCodec.createJPEGEncoder(out); encoder.encode(tag);
	 * out.close();
	 * 
	 * } catch (IOException ex) { ex.printStackTrace(); } }
	 */

	/**
	 * 写入文件
	 * 
	 * @param fullFileName
	 */
	public void writeImageFile(String fullFileName) {
		try {

			// 输出为文件
			// ImageIO.write(destBufImg, this.fileType, new File(fullFileName));

			float softenFactor = 0.05f;
			float[] softenArray = { 0, softenFactor, 0, softenFactor, 1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
			Kernel kernel = new Kernel(3, 3, softenArray);
			ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
			destBufImg = cOp.filter(destBufImg, null);

			// Write the jpeg to a file.
			FileOutputStream out = new FileOutputStream(new File(fullFileName));

			// Encodes image as a JPEG data stream
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(destBufImg);

			param.setQuality(0.95f, true);

			encoder.setJPEGEncodeParam(param);
			encoder.encode(destBufImg);

			if (destBufImg != null) {
				destBufImg.flush();
				destBufImg = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 清理分配资源，内存
	 */
	public void clear() {

		if (srcBufImg != null) {
			srcBufImg.flush();
			srcBufImg = null;

		}

		if (fitBufImg != null) {
			fitBufImg.flush();
			fitBufImg = null;

		}

		if (destBufImg != null) {
			destBufImg.flush();
			destBufImg = null;

		}

	}

	// fileName,usedName ,fileType,

	public static void main(String[] args) {
		// File cutPicFile = new File("d:/4fcf2283e4b0ef5f78ffd340.jpg");
		// String fileType = "jpg";
		//
		// String cutPicDir = "d:/4fcf2283e4b0ef5f78ffd340/";
		// if (cutPicFile.exists()) {
		//
		// ImageBean imageBean = new
		// ImageBean("d:/4fcf2283e4b0ef5f78ffd340.jpg");
		//
		// String picConfigJson =
		// "[{matchMode:'cut',size:[{name:'webcut_240x160',width:240,height:160, watermark:false},{name:'webcut_120x80',width:120,height:80, watermark:false},{name:'webcut_60x40',width:60,height:40, watermark:false},{name:'phone_cut_150x100',width:150,height:100, watermark:false},{name:'phone_cut_113x75',width:113,height:75, watermark:false},{name:'phone_cut_75x50',width:75,height:50, watermark:false}]},{matchMode:'scale',size:[{name:'webscale_540x360',width:540,height:360,watermark:true},{name:'phonescale_280',width:280,height:0,watermark:false},{name:'phonescale_210',width:210,height:0,watermark:false},{name:'phonescale_140',width:140,height:0,watermark:false}, {name:'phonescale_640x426',width:640,height:426,watermark:false},{name:'phonescale_480x320_1',width:480,height:320,watermark:false},{name:'phonescale_320x213',width:320,height:213,watermark:false},{name:'phonescale_960x640',width:960,height:640,watermark:true},{name:'phonescale_720x480',width:720,height:480,watermark:true},{name:'phonescale_480x320_2',width:480,height:320,watermark:true}]}]";
		//
		// JSONArray configArray = JSONArray.fromObject(picConfigJson);
		//
		// for (int i = 0; i < configArray.size(); i++) {
		//
		// JSONObject configObject = (JSONObject) configArray.get(i);
		//
		// String matchMode = configObject.getString("matchMode");
		//
		// imageBean.setMatchMode(matchMode);
		//
		// JSONArray filesArray = configObject.getJSONArray("size");
		//
		// for (int j = 0; j < filesArray.size(); j++) {
		//
		// JSONObject singleFileObject = (JSONObject) filesArray.get(j);
		//
		// String cateName = singleFileObject.getString("name");
		//
		// String cutFileName = cateName + "." + fileType;
		//
		// String resultFullFilePath = cutPicDir + cutFileName;
		//
		// int width = singleFileObject.getInt("width");
		//
		// int height = singleFileObject.getInt("height");
		//
		// boolean watermark = singleFileObject.getBoolean("watermark");
		//
		// imageBean.setWatermark(watermark);
		// // imageBean.setWatermark(false);
		//
		// if (watermark) {
		//
		// String watermarkFilePath =
		// "E:/ccm/ccm-b2b/src/main/webapp/init/watermark.png";
		// imageBean.setWaterFileName(watermarkFilePath);
		// }
		//
		// ImageSize retImgSize = imageBean.createImage(width, height);
		//
		// imageBean.writeImageFile(resultFullFilePath);
		//
		// }
		// }
		//
		// imageBean.clear();
		//
		// }
	}

}
