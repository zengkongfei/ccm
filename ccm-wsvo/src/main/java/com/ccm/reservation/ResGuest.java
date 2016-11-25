
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>ResGuest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ResGuest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Profiles" type="{http://webservices.micros.com/og/4.3/Name/}ProfileList" minOccurs="0"/>
 *         &lt;element name="SpecialRequests" type="{http://webservices.micros.com/og/4.3/HotelCommon/}SpecialRequestList" minOccurs="0"/>
 *         &lt;element name="Comments" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ReservationCommentList" minOccurs="0"/>
 *         &lt;element name="ArrivalTransport" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TransportInfo" minOccurs="0"/>
 *         &lt;element name="DepartureTransport" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TransportInfo" minOccurs="0"/>
 *         &lt;element name="GuestCounts" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GuestCountList" minOccurs="0"/>
 *         &lt;element name="InHouseTimeSpan" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="resGuestRPH" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ageQualifyingCode" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AgeQualifyingCode" />
 *       &lt;attribute name="otherAgeQualifyingCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="arrivalTime" type="{http://www.w3.org/2001/XMLSchema}time" />
 *       &lt;attribute name="departureTime" type="{http://www.w3.org/2001/XMLSchema}time" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResGuest", namespace = "http://webservices.micros.com/og/4.3/Reservation/", propOrder = {
    "profiles",
    "specialRequests",
    "comments",
    "arrivalTransport",
    "departureTransport",
    "guestCounts",
    "inHouseTimeSpan"
})
public class ResGuest {

    @XmlElement(name = "Profiles", required = true)
    protected ProfileList profiles;
    @XmlElement(name = "SpecialRequests")
    protected SpecialRequestList specialRequests;
    @XmlElement(name = "Comments")
    protected ReservationCommentList comments;
    @XmlElement(name = "ArrivalTransport")
    protected TransportInfo arrivalTransport;
    @XmlElement(name = "DepartureTransport")
    protected TransportInfo departureTransport;
    @XmlElement(name = "GuestCounts")
    protected GuestCountList guestCounts;
    @XmlElement(name = "InHouseTimeSpan")
    protected TimeSpan inHouseTimeSpan;
    @XmlAttribute(name = "resGuestRPH", required = true)
    protected int resGuestRPH;
    @XmlAttribute(name = "ageQualifyingCode")
    protected AgeQualifyingCode ageQualifyingCode;
    @XmlAttribute(name = "otherAgeQualifyingCode")
    protected String otherAgeQualifyingCode;
    @XmlAttribute(name = "arrivalTime")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar arrivalTime;
    @XmlAttribute(name = "departureTime")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar departureTime;

    /**
     * 获取profiles属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ProfileList }
     *     
     */
    public ProfileList getProfiles() {
        return profiles;
    }

    /**
     * 设置profiles属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ProfileList }
     *     
     */
    public void setProfiles(ProfileList value) {
        this.profiles = value;
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
     * 获取arrivalTransport属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TransportInfo }
     *     
     */
    public TransportInfo getArrivalTransport() {
        return arrivalTransport;
    }

    /**
     * 设置arrivalTransport属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TransportInfo }
     *     
     */
    public void setArrivalTransport(TransportInfo value) {
        this.arrivalTransport = value;
    }

    /**
     * 获取departureTransport属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TransportInfo }
     *     
     */
    public TransportInfo getDepartureTransport() {
        return departureTransport;
    }

    /**
     * 设置departureTransport属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TransportInfo }
     *     
     */
    public void setDepartureTransport(TransportInfo value) {
        this.departureTransport = value;
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
     * 获取inHouseTimeSpan属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TimeSpan }
     *     
     */
    public TimeSpan getInHouseTimeSpan() {
        return inHouseTimeSpan;
    }

    /**
     * 设置inHouseTimeSpan属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpan }
     *     
     */
    public void setInHouseTimeSpan(TimeSpan value) {
        this.inHouseTimeSpan = value;
    }

    /**
     * 获取resGuestRPH属性的值。
     * 
     */
    public int getResGuestRPH() {
        return resGuestRPH;
    }

    /**
     * 设置resGuestRPH属性的值。
     * 
     */
    public void setResGuestRPH(int value) {
        this.resGuestRPH = value;
    }

    /**
     * 获取ageQualifyingCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AgeQualifyingCode }
     *     
     */
    public AgeQualifyingCode getAgeQualifyingCode() {
        return ageQualifyingCode;
    }

    /**
     * 设置ageQualifyingCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AgeQualifyingCode }
     *     
     */
    public void setAgeQualifyingCode(AgeQualifyingCode value) {
        this.ageQualifyingCode = value;
    }

    /**
     * 获取otherAgeQualifyingCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherAgeQualifyingCode() {
        return otherAgeQualifyingCode;
    }

    /**
     * 设置otherAgeQualifyingCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherAgeQualifyingCode(String value) {
        this.otherAgeQualifyingCode = value;
    }

    /**
     * 获取arrivalTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getArrivalTime() {
        return arrivalTime;
    }

    /**
     * 设置arrivalTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setArrivalTime(XMLGregorianCalendar value) {
        this.arrivalTime = value;
    }

    /**
     * 获取departureTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDepartureTime() {
        return departureTime;
    }

    /**
     * 设置departureTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDepartureTime(XMLGregorianCalendar value) {
        this.departureTime = value;
    }

}
