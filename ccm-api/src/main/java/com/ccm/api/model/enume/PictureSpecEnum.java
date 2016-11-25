/**
 * 
 */
package com.ccm.api.model.enume;

/**
 * @author Jenny Liao
 *
 */
public enum PictureSpecEnum {

	WEBCUT_215X215("webcut_215x215"),
	WEBCUT_150X150("webcut_150x150"),
	WEBCUT_100X100("webcut_100x100"),
	WEBCUT_50X50("webcut_50x50"),
	WEBCUT_35X35("webcut_35x35"),
	
	IPHONECUT_550X670 ("iphonecut_550x670"),
	IPHONECUT_115X86 ("iphonecut_115x86"),
	
	IPHONESCALE_960X640 ("iphonescale_960x640"),
	IPHONESCALE_600X450 ("iphonescale_600x450"),
	
	WEBSCALE_360X270 ("webscale_360x270"),
	WEBSCALE_480X360 ("webscale_480x360"),
	
	WEBSCALE_540X360 ("webscale_540x360"),
	WEBCUT_60X40("webcut_60x40"),
	WEBCUT_240X160("webcut_240x160"),
	
	PHONE_CUT_150x100 ("PHONE_CUT_150x100"), //640*960规格手机小缩略图
	PHONE_CUT_113x75 ("PHONE_CUT_113x75"), //480*800规格手机小缩略图
	PHONE_CUT_75x50 ("PHONE_CUT_75x50"), //320*480规格手机小缩略图
	PHONESCALE_440 ("PHONESCALE_440"), // 宽度大于 640 规则手机专辑 
	PHONESCALE_280 ("PHONESCALE_280"),//640*960规格手机专辑列表页
	PHONESCALE_210 ("PHONESCALE_210"),//480*800规格手机专辑列表页
	PHONESCALE_140 ("PHONESCALE_140"),//320*480规格手机专辑列表页
	PHONESCALE_640x426 ("PHONESCALE_640x426"),//640*960规格手机详情页
	PHONESCALE_480x320_1 ("PHONESCALE_480x320_1"),//480*800规格手机详情页
	PHONESCALE_320x213 ("PHONESCALE_320x213"),//320*480规格手机详情页
	PHONESCALE_960x640 ("PHONESCALE_960x640"),//640*960规格手机幻灯模式
	PHONESCALE_720x480 ("PHONESCALE_720x480"),//480*800规格手机幻灯模式
	PHONESCALE_480x320_2 ("PHONESCALE_480x320_2");//320*480规格手机幻灯模式
	
	PictureSpecEnum(String name) {
		this.name = name;
	}
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
