/**
 * 
 */
package com.ccm.api.service.system.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.opentravel.ota._2003._05.AddressesType;
import org.opentravel.ota._2003._05.AddressesType.Address;
import org.opentravel.ota._2003._05.ContactInfoRootType;
import org.opentravel.ota._2003._05.ContactInfosType;
import org.opentravel.ota._2003._05.CountryNameType;
import org.opentravel.ota._2003._05.EmailsType;
import org.opentravel.ota._2003._05.EmailsType.Email;
import org.opentravel.ota._2003._05.FacilityInfoType;
import org.opentravel.ota._2003._05.FacilityInfoType.GuestRooms;
import org.opentravel.ota._2003._05.FacilityInfoType.GuestRooms.GuestRoom;
import org.opentravel.ota._2003._05.FacilityInfoType.GuestRooms.GuestRoom.TypeRoom;
import org.opentravel.ota._2003._05.FeaturesType;
import org.opentravel.ota._2003._05.FeaturesType.Feature;
import org.opentravel.ota._2003._05.HotelInfoType;
import org.opentravel.ota._2003._05.HotelInfoType.HotelName;
import org.opentravel.ota._2003._05.HotelInfoType.Position;
import org.opentravel.ota._2003._05.HotelRatePlanType.BookingRules;
import org.opentravel.ota._2003._05.HotelRatePlanType.Rates;
import org.opentravel.ota._2003._05.HotelRatePlanType.Rates.Rate;
import org.opentravel.ota._2003._05.MultimediaDescriptionType;
import org.opentravel.ota._2003._05.MultimediaDescriptionsType;
import org.opentravel.ota._2003._05.OTAHotelDescriptiveContentNotifRQ;
import org.opentravel.ota._2003._05.OTAHotelDescriptiveContentNotifRQ.HotelDescriptiveContents;
import org.opentravel.ota._2003._05.OTAHotelDescriptiveContentNotifRQ.HotelDescriptiveContents.HotelDescriptiveContent;
import org.opentravel.ota._2003._05.OTAHotelRatePlanNotifRQ;
import org.opentravel.ota._2003._05.OTAHotelRatePlanNotifRQ.RatePlans;
import org.opentravel.ota._2003._05.OTAHotelRatePlanNotifRQ.RatePlans.RatePlan;
import org.opentravel.ota._2003._05.ObjectFactory;
import org.opentravel.ota._2003._05.ParagraphType;
import org.opentravel.ota._2003._05.PhonesType;
import org.opentravel.ota._2003._05.SellableProductsType;
import org.opentravel.ota._2003._05.StateProvType;
import org.opentravel.ota._2003._05.TPAExtensionsType;
import org.opentravel.ota._2003._05.TextDescriptionType.Description;
import org.opentravel.ota._2003._05.TextItemsType;
import org.opentravel.ota._2003._05.TextItemsType.TextItem;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import com.ccm.api.common.exception.BizException;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.constant.ChannelGoodsStatus;
import com.ccm.api.model.constant.CharacterSet;
import com.ccm.api.model.constant.DictName;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.ratePlan.GuaranteePolicy;
import com.ccm.api.model.ratePlan.SoldableCondition;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.ota.HotelRatePlanManager;
import com.ccm.api.service.system.AbstractPush2ChannelProtocolService;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.XMLUtil;

/**
 * @author Jenny
 * 
 */
@Service("push2ChannelProtocolOTAService")
public class Push2ChannelProtocolOTAServiceImpl extends AbstractPush2ChannelProtocolService {

	private Log log = LogFactory.getLog(Push2ChannelProtocolOTAServiceImpl.class);

	@Resource
	private HotelRatePlanManager hotelRatePlanManager;
	@Resource
	private DictCodeManager dictCodeManager;
	@Resource
	private VelocityEngine velocityEngine;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccm.api.service.system.AbstractPush2ChannelProtocolService#buildRateOnOff
	 * ()
	 */
	@Override
	public Object buildRateOnOff(String chainCode, String hotelCode, String ratePlanCode, String status) {
		// RatePlans
		RatePlans ratePlans = new RatePlans();
		ratePlans.setChainCode(chainCode);
		ratePlans.setHotelCode(hotelCode);
		// RatePlan
		RatePlan ratePlan = new RatePlan();
		ratePlan.setRatePlanCode(ratePlanCode);
		// status
		if (ChannelGoodsStatus.OFF.equals(status)) {
			ratePlan.setRatePlanStatusType("Deactivated");
		} else {
			ratePlan.setRatePlanStatusType("Active");
		}
		ratePlans.getRatePlan().add(ratePlan);
		// OTA_HotelRatePlanNotifRQ
		OTAHotelRatePlanNotifRQ otaHotelRatePlanNotifRQ = new OTAHotelRatePlanNotifRQ();
		otaHotelRatePlanNotifRQ.setRatePlans(ratePlans);
		return otaHotelRatePlanNotifRQ;
	}

	@Override
	public Object buildHotelMsg(HotelVO hvo) throws Exception {

		// HotelDescriptiveContent
		HotelDescriptiveContent hotelDescriptiveContent = new HotelDescriptiveContent();
		hotelDescriptiveContent.setChainCode(hvo.getChainCode());
		hotelDescriptiveContent.setHotelCode(hvo.getHotelCode());
		hotelDescriptiveContent.setLanguageCode(hvo.getLanguageCode());
		// HotelName
		HotelName hotelName = new HotelName();
		hotelName.setValue(hvo.getHotelName());
		// Position
		Position position = new Position();
		position.setLatitude(hvo.getLatitude());
		position.setLongitude(hvo.getLongitude());
		// HotelInfo
		HotelInfoType hotelInfo = new HotelInfoType();
		hotelInfo.setHotelName(hotelName);
		hotelInfo.setPosition(position);
		hotelDescriptiveContent.setHotelInfo(hotelInfo);
		// Address
		Address address = new Address();
		address.getAddressLine().add(hvo.getAddress());
		address.setCityName(hvo.getCityName());
		address.setPostalCode(hvo.getPostCode());//
		StateProvType stateProv = new StateProvType();
		stateProv.setStateCode(String.valueOf(hvo.getCity()));
		address.setStateProv(stateProv);//
		CountryNameType countryName = new CountryNameType();
		countryName.setValue(hvo.getCountryCode());// ISO3166
		address.setCountryName(countryName);//
		// Addresses
		AddressesType addressesType = new AddressesType();
		addressesType.getAddress().add(address);
		// ContactInfo
		ContactInfoRootType contactInfoRootType = new ContactInfoRootType();
		contactInfoRootType.setAddresses(addressesType);
		// Phones
		PhonesType phonesType = new PhonesType();
		// 电话
		PhonesType.Phone phone = new PhonesType.Phone();
		phone.setPhoneNumber(hvo.getTelephone());
		phone.setPhoneTechType("1");
		phonesType.getPhone().add(phone);
		// 传真
		PhonesType.Phone phoneFax = new PhonesType.Phone();
		phoneFax.setPhoneNumber(hvo.getFax());
		phoneFax.setPhoneTechType("3");
		phonesType.getPhone().add(phoneFax);
		contactInfoRootType.setPhones(phonesType);
		// Emails
		EmailsType emailsType = new EmailsType();
		// 多个email
		if (StringUtils.hasText(hvo.getEmail())) {
			String emailArr[] = hvo.getEmail().split(";");
			for (String emailV : emailArr) {
				Email email = new Email();
				email.setValue(emailV);
				emailsType.getEmail().add(email);
			}
		}
		contactInfoRootType.setEmails(emailsType);
		// ContactInfos
		ContactInfosType contactInfos = new ContactInfosType();
		contactInfos.getContactInfo().add(contactInfoRootType);
		hotelDescriptiveContent.setContactInfos(contactInfos);
		// TPA_Extensions
		String sellerXml = "<TPA_Extension ChannelCode=\"TAOBAO\"><Item Key=\"seller\" Value=\"" + hvo.getTbShopName() + "\"></Item></TPA_Extension>";
		Element element = XMLUtil.parse(XMLUtil.getXmlDocument(sellerXml)).getDocumentElement();
		TPAExtensionsType TPAExtensionsType = new TPAExtensionsType();
		TPAExtensionsType.getAny().add(element);
		hotelDescriptiveContent.setTPAExtensions(TPAExtensionsType);
		// HotelDescriptiveContents
		HotelDescriptiveContents hotelDescriptiveContents = new HotelDescriptiveContents();
		hotelDescriptiveContents.getHotelDescriptiveContent().add(hotelDescriptiveContent);
		// OTA_HotelDescriptiveContentNotifRQ
		OTAHotelDescriptiveContentNotifRQ otaHotelRQ = new OTAHotelDescriptiveContentNotifRQ();
		otaHotelRQ.setHotelDescriptiveContents(hotelDescriptiveContents);
		return otaHotelRQ;

	}

	@Override
	public Object buildRoomMsg(String chainCode, RoomTypeVO rtvo, String channelId) {
		// HotelDescriptiveContent
		HotelDescriptiveContent hotelDescriptiveContent = new HotelDescriptiveContent();
		hotelDescriptiveContent.setChainCode(chainCode);
		hotelDescriptiveContent.setHotelCode(rtvo.getHotelCode());
		hotelDescriptiveContent.setLanguageCode(rtvo.getLanguage());
		// GuestRoom
		GuestRoom guestRoom = new GuestRoom();
		guestRoom.setCode(rtvo.getRoomTypeCode());
		if (rtvo.getMaxOccupancy() != null) {
			guestRoom.setMaxOccupancy(BigInteger.valueOf(rtvo.getMaxOccupancy()));
		}
		guestRoom.setRoomTypeName(rtvo.getRoomTypeName());
		// DescriptiveText
		guestRoom.setDescriptiveText(CommonUtil.filterHtml(rtvo.getDescription()));
		TypeRoom typeRoom=new TypeRoom();
		typeRoom.setSizeMeasurement(rtvo.getArea());
		guestRoom.getTypeRoom().add(typeRoom);
		// 床型
		ObjectFactory of = new ObjectFactory();
		Description description = of.createTextDescriptionTypeDescription();
		if (rtvo.getBedTypeCode() != null) {
			Map<String, String> dc = dictCodeManager.searchCodeMapByChannelHotel(DictName.BEDTYPE, channelId, null, true);
			description.setValue(dc.get(String.valueOf(rtvo.getBedTypeCode())));
		}
		TextItem textItem = of.createTextItemsTypeTextItem();
		textItem.getDescription().add(description);
		TextItemsType textItemsType = of.createTextItemsType();
		textItemsType.getTextItem().add(textItem);
		MultimediaDescriptionType multimediaDescriptionType = of.createMultimediaDescriptionType();
		multimediaDescriptionType.setTextItems(textItemsType);
		MultimediaDescriptionsType multimediaDescriptionsType = of.createMultimediaDescriptionsType();
		multimediaDescriptionsType.getMultimediaDescription().add(multimediaDescriptionType);
		Feature feature = new Feature();
		feature.setCodeDetail("BED");
		if (rtvo.getBed_size() != null) {
			feature.setUnitOfMeasure("Meters");
			feature.setUnitOfMeasureQuantity(new BigDecimal(rtvo.getBed_size()));
		}
		feature.setMultimediaDescriptions(multimediaDescriptionsType);
		FeaturesType featuresType = new FeaturesType();
		featuresType.getFeature().add(feature);
		guestRoom.setFeatures(featuresType);
		// GuestRooms
		GuestRooms guestRooms = new GuestRooms();
		guestRooms.getGuestRoom().add(guestRoom);
		// FacilityInfo
		FacilityInfoType facilityInfoType = new FacilityInfoType();
		facilityInfoType.setGuestRooms(guestRooms);
		hotelDescriptiveContent.setFacilityInfo(facilityInfoType);
		// HotelDescriptiveContents
		HotelDescriptiveContents hotelDescriptiveContents = new HotelDescriptiveContents();
		hotelDescriptiveContents.getHotelDescriptiveContent().add(hotelDescriptiveContent);
		// OTA_HotelDescriptiveContentNotifRQ
		OTAHotelDescriptiveContentNotifRQ otaHotelRQ = new OTAHotelDescriptiveContentNotifRQ();
		otaHotelRQ.setHotelDescriptiveContents(hotelDescriptiveContents);
		return otaHotelRQ;
	}

	@Override
	public Object buildRateMsg(RatePlanVO rpvo, String status, String language, String channelId) throws Exception {
		// TPAExtensions
		GuaranteePolicy gp = new GuaranteePolicy();
		Rate rate = new Rate();
		TPAExtensionsType tpaExtensionsType = hotelRatePlanManager.buildTPAExtensions(rpvo.getRp().getRatePlanCode(), rpvo.getRp().getRatePlanId(), channelId, language, rate, gp);
		if (gp == null || !StringUtils.hasText(gp.getGuaranteeCode())) {
			log.info("guaranteePaymentIsNull");
			throw new BizException("没有可用的担保规则！");
		}
		// OTA_HotelRatePlanNotifRQ
		OTAHotelRatePlanNotifRQ otaHotelRatePlanNotifRQ = new OTAHotelRatePlanNotifRQ();
		otaHotelRatePlanNotifRQ.setTPAExtensions(tpaExtensionsType);

		// rate
		rate.setStart(DateUtil.getDate(rpvo.getRp().getEffectiveDate()));
		rate.setEnd(DateUtil.getDate(rpvo.getRp().getExpireDate()));

		// RatePlans
		RatePlans ratePlans = new RatePlans();
		ratePlans.setChainCode(rpvo.getChainCode());
		ratePlans.setHotelCode(rpvo.getRp().getHotelCode());
		// RatePlan
		RatePlan ratePlan = new RatePlan();
		ratePlan.setRatePlanCode(rpvo.getRp().getRatePlanCode());
		// status
		if (ChannelGoodsStatus.OFF.equals(status)) {
			ratePlan.setRatePlanStatusType("Deactivated");
		} else {
			ratePlan.setRatePlanStatusType("Active");
		}

		List<SoldableCondition> scList = rpvo.getRp().getSCList();
		SellableProductsType sellableProducts = new SellableProductsType();
		BookingRules bookingRules = hotelRatePlanManager.buildBookingRules(scList, sellableProducts, rate);
		ratePlan.setSellableProducts(sellableProducts);
		ratePlan.setBookingRules(bookingRules);

		String ratePlanName = "";
		if (rpvo.getRpi18n() != null) {
			ratePlanName = rpvo.getRpi18n().getRatePlanName();
		}
		ParagraphType paragraphType = hotelRatePlanManager.buildDescription(ratePlanName, language);
		ratePlan.getDescription().add(paragraphType);

		Rates rates = new Rates();
		rates.getRate().add(rate);
		ratePlan.setRates(rates);
		ratePlans.getRatePlan().add(ratePlan);
		otaHotelRatePlanNotifRQ.setRatePlans(ratePlans);

		return otaHotelRatePlanNotifRQ;
	}

	/**
	 * 组装产品全量消息xml
	 */
	@SuppressWarnings("deprecation")
	@Override
	public String buildHotelProductMsg(Map<String, Object> map) {
		String tmpUrl = "pushXmlTemplate/FIDELIO_HotelProductNotifRQ.xml";
		String xml = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, tmpUrl, CharacterSet.defaultCode, map);
		return xml;
	}
}
