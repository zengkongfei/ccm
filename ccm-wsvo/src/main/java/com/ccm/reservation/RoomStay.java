
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RoomStay complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RoomStay">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RatePlans" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RatePlanList" minOccurs="0"/>
 *         &lt;element name="RoomTypes" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RoomTypeList" minOccurs="0"/>
 *         &lt;element name="RoomRates" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RoomRateList" minOccurs="0"/>
 *         &lt;element name="GuestCounts" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GuestCountList" minOccurs="0"/>
 *         &lt;element name="TimeSpan" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan" minOccurs="0"/>
 *         &lt;element name="Guarantee" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Guarantee" minOccurs="0"/>
 *         &lt;element name="Payment" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Payment" minOccurs="0"/>
 *         &lt;element name="CreditCardDeposit" type="{http://webservices.micros.com/og/4.3/HotelCommon/}CreditCardPayment" minOccurs="0"/>
 *         &lt;element name="CancelPenalties" type="{http://webservices.micros.com/og/4.3/HotelCommon/}CancelPenaltyList" minOccurs="0"/>
 *         &lt;element name="CancelTerm" type="{http://webservices.micros.com/og/4.3/HotelCommon/}CancelTerm" minOccurs="0"/>
 *         &lt;element name="HotelReference" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference" minOccurs="0"/>
 *         &lt;element name="HotelContact" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelContact" minOccurs="0"/>
 *         &lt;element name="Total" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="ResGuestRPHs" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ResGuestRPHList" minOccurs="0"/>
 *         &lt;element name="Comments" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ReservationCommentList" minOccurs="0"/>
 *         &lt;element name="SpecialRequests" type="{http://webservices.micros.com/og/4.3/HotelCommon/}SpecialRequestList" minOccurs="0"/>
 *         &lt;element name="Packages" type="{http://webservices.micros.com/og/4.3/HotelCommon/}PackageElementList" minOccurs="0"/>
 *         &lt;element name="HotelExtendedInformation" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ExtendedHotelInfo" minOccurs="0"/>
 *         &lt;element name="DailyChargePoints" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpanPointsList" minOccurs="0"/>
 *         &lt;element name="MemberAwardInfo" type="{http://webservices.micros.com/og/4.3/HotelCommon/}MemberAwardInfo" minOccurs="0"/>
 *         &lt;element name="ExpectedCharges" type="{http://webservices.micros.com/og/4.3/HotelCommon/}DailyChargeList" minOccurs="0"/>
 *         &lt;element name="GdsTotalPricing" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GdsTotalPricing" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="isAlternate" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomStay", propOrder = {
    "ratePlans",
    "roomTypes",
    "roomRates",
    "guestCounts",
    "timeSpan",
    "guarantee",
    "payment",
    "creditCardDeposit",
    "cancelPenalties",
    "cancelTerm",
    "hotelReference",
    "hotelContact",
    "total",
    "resGuestRPHs",
    "comments",
    "specialRequests",
    "packages",
    "hotelExtendedInformation",
    "dailyChargePoints",
    "memberAwardInfo",
    "expectedCharges",
    "gdsTotalPricing"
})
public class RoomStay {

    @XmlElement(name = "RatePlans", required = true)
    protected RatePlanList ratePlans;
    @XmlElement(name = "RoomTypes", required = true)
    protected RoomTypeList roomTypes;
    @XmlElement(name = "RoomRates", required = true)
    protected RoomRateList roomRates;
    @XmlElement(name = "GuestCounts", required = true)
    protected GuestCountList guestCounts;
    @XmlElement(name = "TimeSpan", required = true)
    protected TimeSpan timeSpan;
    @XmlElement(name = "Guarantee", required = true)
    protected Guarantee guarantee;
    @XmlElement(name = "Payment")
    protected Payment payment;
    @XmlElement(name = "CreditCardDeposit")
    protected CreditCardPayment creditCardDeposit;
    @XmlElement(name = "CancelPenalties")
    protected CancelPenaltyList cancelPenalties;
    @XmlElement(name = "CancelTerm")
    protected CancelTerm cancelTerm;
    @XmlElement(name = "HotelReference", required = true)
    protected HotelReference hotelReference;
    @XmlElement(name = "HotelContact")
    protected HotelContact hotelContact;
    @XmlElement(name = "Total")
    protected Amount total;
    @XmlElement(name = "ResGuestRPHs")
    protected ResGuestRPHList resGuestRPHs;
    @XmlElement(name = "Comments", required = true)
    protected ReservationCommentList comments;
    @XmlElement(name = "SpecialRequests")
    protected SpecialRequestList specialRequests;
    @XmlElement(name = "Packages")
    protected PackageElementList packages;
    @XmlElement(name = "HotelExtendedInformation")
    protected ExtendedHotelInfo hotelExtendedInformation;
    @XmlElement(name = "DailyChargePoints")
    protected TimeSpanPointsList dailyChargePoints;
    @XmlElement(name = "MemberAwardInfo")
    protected MemberAwardInfo memberAwardInfo;
    @XmlElement(name = "ExpectedCharges")
    protected DailyChargeList expectedCharges;
    @XmlElement(name = "GdsTotalPricing")
    protected GdsTotalPricing gdsTotalPricing;
    @XmlAttribute(name = "isAlternate")
    protected Boolean isAlternate;

    /**
     * 获取ratePlans属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RatePlanList }
     *     
     */
    public RatePlanList getRatePlans() {
        return ratePlans;
    }

    /**
     * 设置ratePlans属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RatePlanList }
     *     
     */
    public void setRatePlans(RatePlanList value) {
        this.ratePlans = value;
    }

    /**
     * 获取roomTypes属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RoomTypeList }
     *     
     */
    public RoomTypeList getRoomTypes() {
        return roomTypes;
    }

    /**
     * 设置roomTypes属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RoomTypeList }
     *     
     */
    public void setRoomTypes(RoomTypeList value) {
        this.roomTypes = value;
    }

    /**
     * 获取roomRates属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RoomRateList }
     *     
     */
    public RoomRateList getRoomRates() {
        return roomRates;
    }

    /**
     * 设置roomRates属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RoomRateList }
     *     
     */
    public void setRoomRates(RoomRateList value) {
        this.roomRates = value;
    }

    /**
     * 获取guestCounts属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GuestCountList }
     *     
     */
    public GuestCountList getGuestCounts() {
        return guestCounts;
    }

    /**
     * 设置guestCounts属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GuestCountList }
     *     
     */
    public void setGuestCounts(GuestCountList value) {
        this.guestCounts = value;
    }

    /**
     * 获取timeSpan属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TimeSpan }
     *     
     */
    public TimeSpan getTimeSpan() {
        return timeSpan;
    }

    /**
     * 设置timeSpan属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpan }
     *     
     */
    public void setTimeSpan(TimeSpan value) {
        this.timeSpan = value;
    }

    /**
     * 获取guarantee属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Guarantee }
     *     
     */
    public Guarantee getGuarantee() {
        return guarantee;
    }

    /**
     * 设置guarantee属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Guarantee }
     *     
     */
    public void setGuarantee(Guarantee value) {
        this.guarantee = value;
    }

    /**
     * 获取payment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Payment }
     *     
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * 设置payment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Payment }
     *     
     */
    public void setPayment(Payment value) {
        this.payment = value;
    }

    /**
     * 获取creditCardDeposit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CreditCardPayment }
     *     
     */
    public CreditCardPayment getCreditCardDeposit() {
        return creditCardDeposit;
    }

    /**
     * 设置creditCardDeposit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCardPayment }
     *     
     */
    public void setCreditCardDeposit(CreditCardPayment value) {
        this.creditCardDeposit = value;
    }

    /**
     * 获取cancelPenalties属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CancelPenaltyList }
     *     
     */
    public CancelPenaltyList getCancelPenalties() {
        return cancelPenalties;
    }

    /**
     * 设置cancelPenalties属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CancelPenaltyList }
     *     
     */
    public void setCancelPenalties(CancelPenaltyList value) {
        this.cancelPenalties = value;
    }

    /**
     * 获取cancelTerm属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CancelTerm }
     *     
     */
    public CancelTerm getCancelTerm() {
        return cancelTerm;
    }

    /**
     * 设置cancelTerm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CancelTerm }
     *     
     */
    public void setCancelTerm(CancelTerm value) {
        this.cancelTerm = value;
    }

    /**
     * 获取hotelReference属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelReference }
     *     
     */
    public HotelReference getHotelReference() {
        return hotelReference;
    }

    /**
     * 设置hotelReference属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReference }
     *     
     */
    public void setHotelReference(HotelReference value) {
        this.hotelReference = value;
    }

    /**
     * 获取hotelContact属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelContact }
     *     
     */
    public HotelContact getHotelContact() {
        return hotelContact;
    }

    /**
     * 设置hotelContact属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelContact }
     *     
     */
    public void setHotelContact(HotelContact value) {
        this.hotelContact = value;
    }

    /**
     * 获取total属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotal() {
        return total;
    }

    /**
     * 设置total属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotal(Amount value) {
        this.total = value;
    }

    /**
     * 获取resGuestRPHs属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ResGuestRPHList }
     *     
     */
    public ResGuestRPHList getResGuestRPHs() {
        return resGuestRPHs;
    }

    /**
     * 设置resGuestRPHs属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ResGuestRPHList }
     *     
     */
    public void setResGuestRPHs(ResGuestRPHList value) {
        this.resGuestRPHs = value;
    }

    /**
     * 获取comments属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ReservationCommentList }
     *     
     */
    public ReservationCommentList getComments() {
        return comments;
    }

    /**
     * 设置comments属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ReservationCommentList }
     *     
     */
    public void setComments(ReservationCommentList value) {
        this.comments = value;
    }

    /**
     * 获取specialRequests属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SpecialRequestList }
     *     
     */
    public SpecialRequestList getSpecialRequests() {
        return specialRequests;
    }

    /**
     * 设置specialRequests属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SpecialRequestList }
     *     
     */
    public void setSpecialRequests(SpecialRequestList value) {
        this.specialRequests = value;
    }

    /**
     * 获取packages属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PackageElementList }
     *     
     */
    public PackageElementList getPackages() {
        return packages;
    }

    /**
     * 设置packages属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PackageElementList }
     *     
     */
    public void setPackages(PackageElementList value) {
        this.packages = value;
    }

    /**
     * 获取hotelExtendedInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ExtendedHotelInfo }
     *     
     */
    public ExtendedHotelInfo getHotelExtendedInformation() {
        return hotelExtendedInformation;
    }

    /**
     * 设置hotelExtendedInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ExtendedHotelInfo }
     *     
     */
    public void setHotelExtendedInformation(ExtendedHotelInfo value) {
        this.hotelExtendedInformation = value;
    }

    /**
     * 获取dailyChargePoints属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TimeSpanPointsList }
     *     
     */
    public TimeSpanPointsList getDailyChargePoints() {
        return dailyChargePoints;
    }

    /**
     * 设置dailyChargePoints属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpanPointsList }
     *     
     */
    public void setDailyChargePoints(TimeSpanPointsList value) {
        this.dailyChargePoints = value;
    }

    /**
     * 获取memberAwardInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MemberAwardInfo }
     *     
     */
    public MemberAwardInfo getMemberAwardInfo() {
        return memberAwardInfo;
    }

    /**
     * 设置memberAwardInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MemberAwardInfo }
     *     
     */
    public void setMemberAwardInfo(MemberAwardInfo value) {
        this.memberAwardInfo = value;
    }

    /**
     * 获取expectedCharges属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DailyChargeList }
     *     
     */
    public DailyChargeList getExpectedCharges() {
        return expectedCharges;
    }

    /**
     * 设置expectedCharges属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DailyChargeList }
     *     
     */
    public void setExpectedCharges(DailyChargeList value) {
        this.expectedCharges = value;
    }

    /**
     * 获取gdsTotalPricing属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GdsTotalPricing }
     *     
     */
    public GdsTotalPricing getGdsTotalPricing() {
        return gdsTotalPricing;
    }

    /**
     * 设置gdsTotalPricing属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GdsTotalPricing }
     *     
     */
    public void setGdsTotalPricing(GdsTotalPricing value) {
        this.gdsTotalPricing = value;
    }

    /**
     * 获取isAlternate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsAlternate() {
        return isAlternate;
    }

    /**
     * 设置isAlternate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsAlternate(Boolean value) {
        this.isAlternate = value;
    }

}
