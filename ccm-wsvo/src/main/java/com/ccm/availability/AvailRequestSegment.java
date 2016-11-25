
package com.ccm.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AvailRequestSegment complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AvailRequestSegment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StayDateRange" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan" minOccurs="0"/>
 *         &lt;element name="RateRange" type="{http://webservices.micros.com/og/4.3/HotelCommon/}MinMaxRate" minOccurs="0"/>
 *         &lt;element name="RatePlanCandidates" type="{http://webservices.micros.com/og/4.3/Availability/}RatePlanCandidateList" minOccurs="0"/>
 *         &lt;element name="RoomStayCandidates" type="{http://webservices.micros.com/og/4.3/Availability/}RoomStayCandidateList" minOccurs="0"/>
 *         &lt;element name="HotelSearchCriteria" type="{http://webservices.micros.com/og/4.3/Availability/}HotelSearchCriteria" minOccurs="0"/>
 *         &lt;element name="ChildAges" type="{http://webservices.micros.com/og/4.3/Availability/}ChildAgeList" minOccurs="0"/>
 *         &lt;element name="GdsTotalPrice" type="{http://webservices.micros.com/og/4.3/Availability/}GdsTotalPriceList" minOccurs="0"/>
 *         &lt;element name="AlternateDates" type="{http://webservices.micros.com/og/4.3/Availability/}AlternateDateList" maxOccurs="5" minOccurs="0"/>
 *         &lt;element name="PointsDateRange" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan" minOccurs="0"/>
 *         &lt;element name="ECertificate" type="{http://webservices.micros.com/og/4.3/Membership/}ECertificate" minOccurs="0"/>
 *         &lt;element name="ASBRateCycle" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ASBRateCycle" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="availReqType" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AvailRequestType" />
 *       &lt;attribute name="moreDataEchoToken" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="numberOfRooms" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="roomOccupancy" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="totalNumberOfGuests" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="numberOfChildren" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="childBucket1" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="childBucket2" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="childBucket3" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="membershipId" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="membershipType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="membershipLevel" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="pointsBelow" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="pointsAbove" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AvailRequestSegment", namespace = "http://webservices.micros.com/og/4.3/Availability/", propOrder = {
    "stayDateRange",
    "rateRange",
    "ratePlanCandidates",
    "roomStayCandidates",
    "hotelSearchCriteria",
    "childAges",
    "gdsTotalPrice",
    "alternateDates",
    "pointsDateRange",
    "eCertificate",
    "asbRateCycle"
})
public class AvailRequestSegment {

    @XmlElement(name = "StayDateRange")
    protected TimeSpan stayDateRange;
    @XmlElement(name = "RateRange")
    protected MinMaxRate rateRange;
    @XmlElement(name = "RatePlanCandidates")
    protected RatePlanCandidateList ratePlanCandidates;
    @XmlElement(name = "RoomStayCandidates")
    protected RoomStayCandidateList roomStayCandidates;
    @XmlElement(name = "HotelSearchCriteria")
    protected HotelSearchCriteria hotelSearchCriteria;
    @XmlElement(name = "ChildAges")
    protected ChildAgeList childAges;
    @XmlElement(name = "GdsTotalPrice")
    protected GdsTotalPriceList gdsTotalPrice;
    @XmlElement(name = "AlternateDates")
    protected List<AlternateDateList> alternateDates;
    @XmlElement(name = "PointsDateRange")
    protected TimeSpan pointsDateRange;
    @XmlElement(name = "ECertificate")
    protected ECertificate eCertificate;
    @XmlElement(name = "ASBRateCycle")
    protected ASBRateCycle asbRateCycle;
    @XmlAttribute(name = "availReqType", required = true)
    protected AvailRequestType availReqType;
    @XmlAttribute(name = "moreDataEchoToken")
    protected String moreDataEchoToken;
    @XmlAttribute(name = "numberOfRooms")
    protected Integer numberOfRooms;
    @XmlAttribute(name = "roomOccupancy")
    protected Integer roomOccupancy;
    @XmlAttribute(name = "totalNumberOfGuests")
    protected Integer totalNumberOfGuests;
    @XmlAttribute(name = "numberOfChildren")
    protected Integer numberOfChildren;
    @XmlAttribute(name = "childBucket1")
    protected Integer childBucket1;
    @XmlAttribute(name = "childBucket2")
    protected Integer childBucket2;
    @XmlAttribute(name = "childBucket3")
    protected Integer childBucket3;
    @XmlAttribute(name = "membershipId")
    protected Long membershipId;
    @XmlAttribute(name = "membershipType")
    protected String membershipType;
    @XmlAttribute(name = "membershipLevel")
    protected String membershipLevel;
    @XmlAttribute(name = "pointsBelow")
    protected Long pointsBelow;
    @XmlAttribute(name = "pointsAbove")
    protected Long pointsAbove;

    /**
     * 获取stayDateRange属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TimeSpan }
     *     
     */
    public TimeSpan getStayDateRange() {
        return stayDateRange;
    }

    /**
     * 设置stayDateRange属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpan }
     *     
     */
    public void setStayDateRange(TimeSpan value) {
        this.stayDateRange = value;
    }

    /**
     * 获取rateRange属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MinMaxRate }
     *     
     */
    public MinMaxRate getRateRange() {
        return rateRange;
    }

    /**
     * 设置rateRange属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MinMaxRate }
     *     
     */
    public void setRateRange(MinMaxRate value) {
        this.rateRange = value;
    }

    /**
     * 获取ratePlanCandidates属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RatePlanCandidateList }
     *     
     */
    public RatePlanCandidateList getRatePlanCandidates() {
        return ratePlanCandidates;
    }

    /**
     * 设置ratePlanCandidates属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RatePlanCandidateList }
     *     
     */
    public void setRatePlanCandidates(RatePlanCandidateList value) {
        this.ratePlanCandidates = value;
    }

    /**
     * 获取roomStayCandidates属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RoomStayCandidateList }
     *     
     */
    public RoomStayCandidateList getRoomStayCandidates() {
        return roomStayCandidates;
    }

    /**
     * 设置roomStayCandidates属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RoomStayCandidateList }
     *     
     */
    public void setRoomStayCandidates(RoomStayCandidateList value) {
        this.roomStayCandidates = value;
    }

    /**
     * 获取hotelSearchCriteria属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelSearchCriteria }
     *     
     */
    public HotelSearchCriteria getHotelSearchCriteria() {
        return hotelSearchCriteria;
    }

    /**
     * 设置hotelSearchCriteria属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelSearchCriteria }
     *     
     */
    public void setHotelSearchCriteria(HotelSearchCriteria value) {
        this.hotelSearchCriteria = value;
    }

    /**
     * 获取childAges属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ChildAgeList }
     *     
     */
    public ChildAgeList getChildAges() {
        return childAges;
    }

    /**
     * 设置childAges属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ChildAgeList }
     *     
     */
    public void setChildAges(ChildAgeList value) {
        this.childAges = value;
    }

    /**
     * 获取gdsTotalPrice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GdsTotalPriceList }
     *     
     */
    public GdsTotalPriceList getGdsTotalPrice() {
        return gdsTotalPrice;
    }

    /**
     * 设置gdsTotalPrice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GdsTotalPriceList }
     *     
     */
    public void setGdsTotalPrice(GdsTotalPriceList value) {
        this.gdsTotalPrice = value;
    }

    /**
     * Gets the value of the alternateDates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alternateDates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlternateDates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AlternateDateList }
     * 
     * 
     */
    public List<AlternateDateList> getAlternateDates() {
        if (alternateDates == null) {
            alternateDates = new ArrayList<AlternateDateList>();
        }
        return this.alternateDates;
    }

    /**
     * 获取pointsDateRange属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TimeSpan }
     *     
     */
    public TimeSpan getPointsDateRange() {
        return pointsDateRange;
    }

    /**
     * 设置pointsDateRange属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpan }
     *     
     */
    public void setPointsDateRange(TimeSpan value) {
        this.pointsDateRange = value;
    }

    /**
     * 获取eCertificate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ECertificate }
     *     
     */
    public ECertificate getECertificate() {
        return eCertificate;
    }

    /**
     * 设置eCertificate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ECertificate }
     *     
     */
    public void setECertificate(ECertificate value) {
        this.eCertificate = value;
    }

    /**
     * 获取asbRateCycle属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ASBRateCycle }
     *     
     */
    public ASBRateCycle getASBRateCycle() {
        return asbRateCycle;
    }

    /**
     * 设置asbRateCycle属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ASBRateCycle }
     *     
     */
    public void setASBRateCycle(ASBRateCycle value) {
        this.asbRateCycle = value;
    }

    /**
     * 获取availReqType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AvailRequestType }
     *     
     */
    public AvailRequestType getAvailReqType() {
        return availReqType;
    }

    /**
     * 设置availReqType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AvailRequestType }
     *     
     */
    public void setAvailReqType(AvailRequestType value) {
        this.availReqType = value;
    }

    /**
     * 获取moreDataEchoToken属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoreDataEchoToken() {
        return moreDataEchoToken;
    }

    /**
     * 设置moreDataEchoToken属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoreDataEchoToken(String value) {
        this.moreDataEchoToken = value;
    }

    /**
     * 获取numberOfRooms属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * 设置numberOfRooms属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfRooms(Integer value) {
        this.numberOfRooms = value;
    }

    /**
     * 获取roomOccupancy属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRoomOccupancy() {
        return roomOccupancy;
    }

    /**
     * 设置roomOccupancy属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRoomOccupancy(Integer value) {
        this.roomOccupancy = value;
    }

    /**
     * 获取totalNumberOfGuests属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalNumberOfGuests() {
        return totalNumberOfGuests;
    }

    /**
     * 设置totalNumberOfGuests属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalNumberOfGuests(Integer value) {
        this.totalNumberOfGuests = value;
    }

    /**
     * 获取numberOfChildren属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    /**
     * 设置numberOfChildren属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfChildren(Integer value) {
        this.numberOfChildren = value;
    }

    /**
     * 获取childBucket1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getChildBucket1() {
        return childBucket1;
    }

    /**
     * 设置childBucket1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChildBucket1(Integer value) {
        this.childBucket1 = value;
    }

    /**
     * 获取childBucket2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getChildBucket2() {
        return childBucket2;
    }

    /**
     * 设置childBucket2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChildBucket2(Integer value) {
        this.childBucket2 = value;
    }

    /**
     * 获取childBucket3属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getChildBucket3() {
        return childBucket3;
    }

    /**
     * 设置childBucket3属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChildBucket3(Integer value) {
        this.childBucket3 = value;
    }

    /**
     * 获取membershipId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMembershipId() {
        return membershipId;
    }

    /**
     * 设置membershipId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMembershipId(Long value) {
        this.membershipId = value;
    }

    /**
     * 获取membershipType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMembershipType() {
        return membershipType;
    }

    /**
     * 设置membershipType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMembershipType(String value) {
        this.membershipType = value;
    }

    /**
     * 获取membershipLevel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMembershipLevel() {
        return membershipLevel;
    }

    /**
     * 设置membershipLevel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMembershipLevel(String value) {
        this.membershipLevel = value;
    }

    /**
     * 获取pointsBelow属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPointsBelow() {
        return pointsBelow;
    }

    /**
     * 设置pointsBelow属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPointsBelow(Long value) {
        this.pointsBelow = value;
    }

    /**
     * 获取pointsAbove属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPointsAbove() {
        return pointsAbove;
    }

    /**
     * 设置pointsAbove属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPointsAbove(Long value) {
        this.pointsAbove = value;
    }

}
