package com.ccm.web.servlet.tags;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccm.api.AppConfig;
import com.ccm.api.model.base.Address;
import com.ccm.api.model.base.vo.PictureVO;
import com.ccm.api.model.enume.PictureSpecEnum;
import com.ccm.api.model.enume.PictureType;
import com.ccm.api.service.common.PictureManager;
import com.ccm.api.util.CacheUtil;
import com.ccm.api.util.CurrencyConverter;
import com.ccm.web.servlet.ApplicationContextStaticHolder;

public class CustomFunctionsTag {

	private static final Log log = LogFactory.getLog(CustomFunctionsTag.class);

	public static String starCssStar(String hotelStar) {
		// SELECT * FROM dictcode WHERE dictId = 11
		// codeId codeLabel codeNo dictId
		// 1101 五星 1101 11
		// 1102 四星 1102 11
		// 1103 三星 1103 11
		// 1104 二星及以下 1104 11
		// 1105 经济连锁 1105 11
		// 1106 特色客栈 1106 11
		// 1107 青年旅舍 1107 11
		// 1108 家庭旅馆 1108 11
		// 1109 准五星/豪华 1109 11
		// 1110 准四星/高档 1110 11
		// 1111 准三星/舒适 1111 11
		if ("1101".equals(hotelStar))
			return "star5";
		if ("1102".equals(hotelStar))
			return "star4";
		if ("1103".equals(hotelStar))
			return "star3";
		if ("1104".equals(hotelStar))
			return "star2";
		if ("1105".equals(hotelStar))
			return "jjls1";
		if ("1106".equals(hotelStar))
			return "tskz2";
		if ("1107".equals(hotelStar))
			return "qnls3";
		if ("1108".equals(hotelStar))
			return "jtlg4";
		if ("1109".equals(hotelStar))
			return "wing5";
		if ("1110".equals(hotelStar))
			return "wing4";
		if ("1111".equals(hotelStar))
			return "wing3";
		return null;
	}

	public static String starName(String hotelStar) {
		// 1101 五星 star5
		// 1102 四星 star4
		// 1103 三星 star3
		// 1104 二星及以下 star2
		// 1105 经济连锁 jjls1
		// 1106 特色客栈 tskz2
		// 1107 青年旅舍 qnls3
		// 1108 家庭旅馆 jtlg4
		if ("1101".equals(hotelStar))
			return "五星";
		if ("1102".equals(hotelStar))
			return "四星";
		if ("1103".equals(hotelStar))
			return "三星";
		if ("1104".equals(hotelStar))
			return "二星及以下";
		if ("1105".equals(hotelStar))
			return "经济连锁";
		if ("1106".equals(hotelStar))
			return "特色客栈";
		if ("1107".equals(hotelStar))
			return "青年旅舍";
		if ("1108".equals(hotelStar))
			return "家庭旅馆";
		if ("1109".equals(hotelStar))
			return "准五星/豪华";
		if ("1110".equals(hotelStar))
			return "准四星/高档";
		if ("1111".equals(hotelStar))
			return "准三星/舒适";
		return null;
	}

	public static String hotelListUrl(String cityPinyin) {
		if (AppConfig.isDev()) {
			return "/front/hotel_list?cityName=" + cityPinyin;
		} else {
			return AppConfig.getB2cBaseUrl() + "list_" + cityPinyin;
		}
	}

	public static String fullAddress(Address address) {

		Long fullStart = System.currentTimeMillis();

		Boolean hit = true;
		String key = "address" + address.toString();
		String addr = (String) CacheUtil.get(key);
		if (addr == null) {
			hit = false;
			StringBuilder sb = new StringBuilder();
			if (address != null) {
				sb.append(address.getAddress());
			}
			addr = sb.toString();
			CacheUtil.put(key, addr);
		}

		Long fullEnd = System.currentTimeMillis();
		log.info("fullAddress" + " hit " + hit + " need " + (fullEnd - fullStart) + "ms " + " fullAddress:" + key);

		return addr;
	}

	public static String roomDefPicUrl(String roomUuid) {

		Long fullStart = System.currentTimeMillis();

		Boolean hit = true;
		String key = "roomDefPicUrl_" + roomUuid;
		String url = (String) CacheUtil.get(key);
		if (url == null) {
			hit = false;
			Map<String, String> map = ApplicationContextStaticHolder.getBean(PictureManager.class).getDefPicture(PictureType.ROOM_TYPE.getName(), roomUuid, PictureSpecEnum.WEBCUT_240X160.getName());
			url = map != null ? map.get("url") : "";
			CacheUtil.put(key, url);
		}

		Long fullEnd = System.currentTimeMillis();
		log.info("roomDefPicUrl " + " hit " + hit + " need " + (fullEnd - fullStart) + "ms " + " cityId:" + roomUuid);

		return url;
	}

	public static String partialText(String text, Integer length) {
		if (StringUtils.isNotEmpty(text) && length != null && length.intValue() > 0) {
			return subStringCN(text, length);
		} else {
			return "";
		}
	}

	// TODO:move it to String Utils or other Util class
	private static String subStringCN(final String str, final int maxLength) {
		if (str == null) {
			return str;
		}
		String suffix = "...";
		int suffixLen = suffix.length();

		final StringBuffer sbuffer = new StringBuffer();
		final char[] chr = str.trim().toCharArray();
		int len = 0;
		for (int i = 0; i < chr.length; i++) {

			if (chr[i] >= 0xa1) {
				len += 2;
			} else {
				len++;
			}
		}

		if (len <= maxLength) {
			return str;
		}

		len = 0;
		for (int i = 0; i < chr.length; i++) {

			if (chr[i] >= 0xa1) {
				len += 2;
				if (len + suffixLen > maxLength) {
					break;
				} else {
					sbuffer.append(chr[i]);
				}
			} else {
				len++;
				if (len + suffixLen > maxLength) {
					break;
				} else {
					sbuffer.append(chr[i]);
				}
			}
		}
		sbuffer.append(suffix);
		return sbuffer.toString();
	}

	public static String[] split(String content) {
		if (StringUtils.isEmpty(content)) {
			return null;
		}
		String[] items = content.split("[\\s+,，　、]+");
		List<String> list = new ArrayList<String>();
		for (String item : items) {
			if (StringUtils.isNotBlank(item)) {
				list.add(item);
			}
		}
		return list.toArray(new String[0]);
	}

	public static String priceToString(Double price) {
		if (price != null) {
			return CurrencyConverter.priceToString(price);
		} else {
			return null;
		}
	}

	public static PictureVO getRoomTypeDefaultPicture(String roomTypeId, String pictureSpec) {
		PictureManager pictureManager = ApplicationContextStaticHolder.getBean(PictureManager.class);
		return pictureManager.getRoomTypeDefaultPciture(roomTypeId, pictureSpec);
	}

}
