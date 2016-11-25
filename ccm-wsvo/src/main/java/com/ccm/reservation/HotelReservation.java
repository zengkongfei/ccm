package com.ccm.reservation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * HotelReservation complex type的 Java 类。
 * 
 * <p>
 * 以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="HotelReservation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UniqueIDList" type="{http://webservices.micros.com/og/4.3/Common/}UniqueIDList" minOccurs="0"/>
 *         &lt;element name="RoomStays" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RoomStayList" minOccurs="0"/>
 *         &lt;element name="ResGuests" type="{http://webservices.micros.com/og/4.3/Reservation/}ResGuestList" minOccurs="0"/>
 *         &lt;element name="WrittenConfInst" type="{http://webservices.micros.com/og/4.3/Reservation/}WrittenConfInst" minOccurs="0"/>
 *         &lt;element name="ReservationHistory" type="{http://webservices.micros.com/og/4.3/Common/}History" minOccurs="0"/>
 *         &lt;element name="UserDefinedValues" type="{http://webservices.micros.com/og/4.3/Common/}UserDefinedValueList" minOccurs="0"/>
 *         &lt;element name="Invoice" type="{http://webservices.micros.com/og/4.3/Reservation/}BillHeader" minOccurs="0"/>
 *         &lt;element name="Preferences" type="{http://webservices.micros.com/og/4.3/Name/}PreferenceList" minOccurs="0"/>
 *         &lt;element name="Alerts" type="{http://webservices.micros.com/og/4.3/Reservation/}Alert" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PayRoutings" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ResPayRouting" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PayMethods" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ResPayMethod" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AccompanyGuests" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AccompanyGuestList" minOccurs="0"/>
 *         &lt;element name="ECertificate" type="{http://webservices.micros.com/og/4.3/Membership/}ECertificate" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://webservices.micros.com/og/4.3/Common/}RecordAdministratorAttributes"/>
 *       &lt;attribute name="reservationAction" type="{http://webservices.micros.com/og/4.3/Reservation/}ReservationActionType" />
 *       &lt;attribute name="reservationStatus" type="{http://webservices.micros.com/og/4.3/Reservation/}ReservationStatusType" />
 *       &lt;attribute name="marketSegment" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="sourceCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="originCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="authorizer" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="compRoutingFlag" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="compRoutingAuthorizer" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="onBehalfFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="redemReservationFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="walkIn" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="noPost" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="remoteCo" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="group" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="attachPreferenceProfile" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="keyExpirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="totalCreditCardSurcharges" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="DoNotMoveRoom" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="OptIn" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="OptInComplete" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HotelReservation", namespace = "http://webservices.micros.com/og/4.3/Reservation/", propOrder = { "uniqueIDList", "roomStays", "resGuests", "writtenConfInst", "reservationHistory", "userDefinedValues", "invoice", "preferences", "alerts", "payRoutings", "payMethods", "accompanyGuests", "eCertificate" })
@XmlSeeAlso({ com.ccm.reservation.HotelReservationList.HotelReservation.class })
public class HotelReservation {

	@XmlElement(name = "UniqueIDList")
	protected UniqueIDList uniqueIDList;
	@XmlElement(name = "RoomStays", required = true)
	protected RoomStayList roomStays;
	@XmlElement(name = "ResGuests", required = true)
	protected ResGuestList resGuests;
	@XmlElement(name = "WrittenConfInst")
	protected WrittenConfInst writtenConfInst;
	@XmlElement(name = "ReservationHistory")
	protected History reservationHistory;
	@XmlElement(name = "UserDefinedValues")
	protected UserDefinedValueList userDefinedValues;
	@XmlElement(name = "Invoice")
	protected BillHeader invoice;
	@XmlElement(name = "Preferences")
	protected PreferenceList preferences;
	@XmlElement(name = "Alerts")
	protected List<Alert> alerts;
	@XmlElement(name = "PayRoutings")
	protected List<ResPayRouting> payRoutings;
	@XmlElement(name = "PayMethods")
	protected List<ResPayMethod> payMethods;
	@XmlElement(name = "AccompanyGuests")
	protected AccompanyGuestList accompanyGuests;
	@XmlElement(name = "ECertificate")
	protected ECertificate eCertificate;
	@XmlAttribute(name = "reservationAction")
	protected ReservationActionType reservationAction;
	@XmlAttribute(name = "reservationStatus")
	protected ReservationStatusType reservationStatus;
	@XmlAttribute(name = "marketSegment")
	protected String marketSegment;
	@XmlAttribute(name = "sourceCode")
	protected String sourceCode;
	@XmlAttribute(name = "originCode")
	protected String originCode;
	@XmlAttribute(name = "authorizer")
	protected String authorizer;
	@XmlAttribute(name = "compRoutingFlag")
	protected String compRoutingFlag;
	@XmlAttribute(name = "compRoutingAuthorizer")
	protected String compRoutingAuthorizer;
	@XmlAttribute(name = "onBehalfFlag")
	protected Boolean onBehalfFlag;
	@XmlAttribute(name = "redemReservationFlag")
	protected Boolean redemReservationFlag;
	@XmlAttribute(name = "walkIn")
	protected Boolean walkIn;
	@XmlAttribute(name = "noPost")
	protected Boolean noPost;
	@XmlAttribute(name = "remoteCo")
	protected Boolean remoteCo;
	@XmlAttribute(name = "group")
	protected String group;
	@XmlAttribute(name = "attachPreferenceProfile")
	protected Boolean attachPreferenceProfile;
	@XmlAttribute(name = "keyExpirationDate")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar keyExpirationDate;
	@XmlAttribute(name = "totalCreditCardSurcharges")
	protected Double totalCreditCardSurcharges;
	@XmlAttribute(name = "DoNotMoveRoom")
	protected Boolean doNotMoveRoom;
	@XmlAttribute(name = "OptIn")
	protected Boolean optIn;
	@XmlAttribute(name = "OptInComplete")
	protected Boolean optInComplete;
	@XmlAttribute(name = "insertUser")
	protected String insertUser;
	@XmlAttribute(name = "insertDate")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar insertDate;
	@XmlAttribute(name = "updateUser")
	protected String updateUser;
	@XmlAttribute(name = "updateDate")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar updateDate;
	@XmlAttribute(name = "inactiveDate")
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar inactiveDate;

	/**
	 * 获取uniqueIDList属性的值。
	 * 
	 * @return possible object is {@link UniqueIDList }
	 * 
	 */
	public UniqueIDList getUniqueIDList() {
		return uniqueIDList;
	}

	/**
	 * 设置uniqueIDList属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link UniqueIDList }
	 * 
	 */
	public void setUniqueIDList(UniqueIDList value) {
		this.uniqueIDList = value;
	}

	/**
	 * 获取roomStays属性的值。
	 * 
	 * @return possible object is {@link RoomStayList }
	 * 
	 */
	public RoomStayList getRoomStays() {
		return roomStays;
	}

	/**
	 * 设置roomStays属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link RoomStayList }
	 * 
	 */
	public void setRoomStays(RoomStayList value) {
		this.roomStays = value;
	}

	/**
	 * 获取resGuests属性的值。
	 * 
	 * @return possible object is {@link ResGuestList }
	 * 
	 */
	public ResGuestList getResGuests() {
		return resGuests;
	}

	/**
	 * 设置resGuests属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link ResGuestList }
	 * 
	 */
	public void setResGuests(ResGuestList value) {
		this.resGuests = value;
	}

	/**
	 * 获取writtenConfInst属性的值。
	 * 
	 * @return possible object is {@link WrittenConfInst }
	 * 
	 */
	public WrittenConfInst getWrittenConfInst() {
		return writtenConfInst;
	}

	/**
	 * 设置writtenConfInst属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link WrittenConfInst }
	 * 
	 */
	public void setWrittenConfInst(WrittenConfInst value) {
		this.writtenConfInst = value;
	}

	/**
	 * 获取reservationHistory属性的值。
	 * 
	 * @return possible object is {@link History }
	 * 
	 */
	public History getReservationHistory() {
		return reservationHistory;
	}

	/**
	 * 设置reservationHistory属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link History }
	 * 
	 */
	public void setReservationHistory(History value) {
		this.reservationHistory = value;
	}

	/**
	 * 获取userDefinedValues属性的值。
	 * 
	 * @return possible object is {@link UserDefinedValueList }
	 * 
	 */
	public UserDefinedValueList getUserDefinedValues() {
		return userDefinedValues;
	}

	/**
	 * 设置userDefinedValues属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link UserDefinedValueList }
	 * 
	 */
	public void setUserDefinedValues(UserDefinedValueList value) {
		this.userDefinedValues = value;
	}

	/**
	 * 获取invoice属性的值。
	 * 
	 * @return possible object is {@link BillHeader }
	 * 
	 */
	public BillHeader getInvoice() {
		return invoice;
	}

	/**
	 * 设置invoice属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link BillHeader }
	 * 
	 */
	public void setInvoice(BillHeader value) {
		this.invoice = value;
	}

	/**
	 * 获取preferences属性的值。
	 * 
	 * @return possible object is {@link PreferenceList }
	 * 
	 */
	public PreferenceList getPreferences() {
		return preferences;
	}

	/**
	 * 设置preferences属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link PreferenceList }
	 * 
	 */
	public void setPreferences(PreferenceList value) {
		this.preferences = value;
	}

	/**
	 * Gets the value of the alerts property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the alerts property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getAlerts().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Alert }
	 * 
	 * 
	 */
	public List<Alert> getAlerts() {
		if (alerts == null) {
			alerts = new ArrayList<Alert>();
		}
		return this.alerts;
	}

	/**
	 * Gets the value of the payRoutings property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the payRoutings property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getPayRoutings().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link ResPayRouting }
	 * 
	 * 
	 */
	public List<ResPayRouting> getPayRoutings() {
		if (payRoutings == null) {
			payRoutings = new ArrayList<ResPayRouting>();
		}
		return this.payRoutings;
	}

	/**
	 * Gets the value of the payMethods property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the payMethods property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getPayMethods().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link ResPayMethod }
	 * 
	 * 
	 */
	public List<ResPayMethod> getPayMethods() {
		if (payMethods == null) {
			payMethods = new ArrayList<ResPayMethod>();
		}
		return this.payMethods;
	}

	/**
	 * 获取accompanyGuests属性的值。
	 * 
	 * @return possible object is {@link AccompanyGuestList }
	 * 
	 */
	public AccompanyGuestList getAccompanyGuests() {
		return accompanyGuests;
	}

	/**
	 * 设置accompanyGuests属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link AccompanyGuestList }
	 * 
	 */
	public void setAccompanyGuests(AccompanyGuestList value) {
		this.accompanyGuests = value;
	}

	/**
	 * 获取eCertificate属性的值。
	 * 
	 * @return possible object is {@link ECertificate }
	 * 
	 */
	public ECertificate getECertificate() {
		return eCertificate;
	}

	/**
	 * 设置eCertificate属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link ECertificate }
	 * 
	 */
	public void setECertificate(ECertificate value) {
		this.eCertificate = value;
	}

	/**
	 * 获取reservationAction属性的值。
	 * 
	 * @return possible object is {@link ReservationActionType }
	 * 
	 */
	public ReservationActionType getReservationAction() {
		return reservationAction;
	}

	/**
	 * 设置reservationAction属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link ReservationActionType }
	 * 
	 */
	public void setReservationAction(ReservationActionType value) {
		this.reservationAction = value;
	}

	/**
	 * 获取reservationStatus属性的值。
	 * 
	 * @return possible object is {@link ReservationStatusType }
	 * 
	 */
	public ReservationStatusType getReservationStatus() {
		return reservationStatus;
	}

	/**
	 * 设置reservationStatus属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link ReservationStatusType }
	 * 
	 */
	public void setReservationStatus(ReservationStatusType value) {
		this.reservationStatus = value;
	}

	/**
	 * 获取marketSegment属性的值。
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMarketSegment() {
		return marketSegment;
	}

	/**
	 * 设置marketSegment属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMarketSegment(String value) {
		this.marketSegment = value;
	}

	/**
	 * 获取sourceCode属性的值。
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSourceCode() {
		return sourceCode;
	}

	/**
	 * 设置sourceCode属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSourceCode(String value) {
		this.sourceCode = value;
	}

	/**
	 * 获取originCode属性的值。
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOriginCode() {
		return originCode;
	}

	/**
	 * 设置originCode属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOriginCode(String value) {
		this.originCode = value;
	}

	/**
	 * 获取authorizer属性的值。
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAuthorizer() {
		return authorizer;
	}

	/**
	 * 设置authorizer属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAuthorizer(String value) {
		this.authorizer = value;
	}

	/**
	 * 获取compRoutingFlag属性的值。
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCompRoutingFlag() {
		return compRoutingFlag;
	}

	/**
	 * 设置compRoutingFlag属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCompRoutingFlag(String value) {
		this.compRoutingFlag = value;
	}

	/**
	 * 获取compRoutingAuthorizer属性的值。
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCompRoutingAuthorizer() {
		return compRoutingAuthorizer;
	}

	/**
	 * 设置compRoutingAuthorizer属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCompRoutingAuthorizer(String value) {
		this.compRoutingAuthorizer = value;
	}

	/**
	 * 获取onBehalfFlag属性的值。
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isOnBehalfFlag() {
		return onBehalfFlag;
	}

	/**
	 * 设置onBehalfFlag属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setOnBehalfFlag(Boolean value) {
		this.onBehalfFlag = value;
	}

	/**
	 * 获取redemReservationFlag属性的值。
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isRedemReservationFlag() {
		return redemReservationFlag;
	}

	/**
	 * 设置redemReservationFlag属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setRedemReservationFlag(Boolean value) {
		this.redemReservationFlag = value;
	}

	/**
	 * 获取walkIn属性的值。
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isWalkIn() {
		return walkIn;
	}

	/**
	 * 设置walkIn属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setWalkIn(Boolean value) {
		this.walkIn = value;
	}

	/**
	 * 获取noPost属性的值。
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isNoPost() {
		return noPost;
	}

	/**
	 * 设置noPost属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setNoPost(Boolean value) {
		this.noPost = value;
	}

	/**
	 * 获取remoteCo属性的值。
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isRemoteCo() {
		return remoteCo;
	}

	/**
	 * 设置remoteCo属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setRemoteCo(Boolean value) {
		this.remoteCo = value;
	}

	/**
	 * 获取group属性的值。
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * 设置group属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGroup(String value) {
		this.group = value;
	}

	/**
	 * 获取attachPreferenceProfile属性的值。
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isAttachPreferenceProfile() {
		return attachPreferenceProfile;
	}

	/**
	 * 设置attachPreferenceProfile属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setAttachPreferenceProfile(Boolean value) {
		this.attachPreferenceProfile = value;
	}

	/**
	 * 获取keyExpirationDate属性的值。
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getKeyExpirationDate() {
		return keyExpirationDate;
	}

	/**
	 * 设置keyExpirationDate属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setKeyExpirationDate(XMLGregorianCalendar value) {
		this.keyExpirationDate = value;
	}

	/**
	 * 获取totalCreditCardSurcharges属性的值。
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getTotalCreditCardSurcharges() {
		return totalCreditCardSurcharges;
	}

	/**
	 * 设置totalCreditCardSurcharges属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link Double }
	 * 
	 */
	public void setTotalCreditCardSurcharges(Double value) {
		this.totalCreditCardSurcharges = value;
	}

	/**
	 * 获取doNotMoveRoom属性的值。
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isDoNotMoveRoom() {
		return doNotMoveRoom;
	}

	/**
	 * 设置doNotMoveRoom属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setDoNotMoveRoom(Boolean value) {
		this.doNotMoveRoom = value;
	}

	/**
	 * 获取optIn属性的值。
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isOptIn() {
		return optIn;
	}

	/**
	 * 设置optIn属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setOptIn(Boolean value) {
		this.optIn = value;
	}

	/**
	 * 获取optInComplete属性的值。
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isOptInComplete() {
		return optInComplete;
	}

	/**
	 * 设置optInComplete属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setOptInComplete(Boolean value) {
		this.optInComplete = value;
	}

	/**
	 * 获取insertUser属性的值。
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInsertUser() {
		return insertUser;
	}

	/**
	 * 设置insertUser属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInsertUser(String value) {
		this.insertUser = value;
	}

	/**
	 * 获取insertDate属性的值。
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getInsertDate() {
		return insertDate;
	}

	/**
	 * 设置insertDate属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setInsertDate(XMLGregorianCalendar value) {
		this.insertDate = value;
	}

	/**
	 * 获取updateUser属性的值。
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * 设置updateUser属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUpdateUser(String value) {
		this.updateUser = value;
	}

	/**
	 * 获取updateDate属性的值。
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getUpdateDate() {
		return updateDate;
	}

	/**
	 * 设置updateDate属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setUpdateDate(XMLGregorianCalendar value) {
		this.updateDate = value;
	}

	/**
	 * 获取inactiveDate属性的值。
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getInactiveDate() {
		return inactiveDate;
	}

	/**
	 * 设置inactiveDate属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setInactiveDate(XMLGregorianCalendar value) {
		this.inactiveDate = value;
	}

}
