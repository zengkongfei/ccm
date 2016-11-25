
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HotelReference" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference"/>
 *         &lt;element name="ConfirmationNumber" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="LegNumber" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="ResvNameId" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="MembershipID" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "hotelReference",
    "confirmationNumber",
    "legNumber",
    "resvNameId",
    "membershipID"
})
@XmlRootElement(name = "FetchAvailableOffersRequest", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
public class FetchAvailableOffersRequest {

    @XmlElement(name = "HotelReference", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", required = true)
    protected HotelReference hotelReference;
    @XmlElement(name = "ConfirmationNumber", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
    protected UniqueID confirmationNumber;
    @XmlElement(name = "LegNumber", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
    protected UniqueID legNumber;
    @XmlElement(name = "ResvNameId", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
    protected UniqueID resvNameId;
    @XmlElement(name = "MembershipID", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
    protected UniqueID membershipID;

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
     * 获取confirmationNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getConfirmationNumber() {
        return confirmationNumber;
    }

    /**
     * 设置confirmationNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setConfirmationNumber(UniqueID value) {
        this.confirmationNumber = value;
    }

    /**
     * 获取legNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getLegNumber() {
        return legNumber;
    }

    /**
     * 设置legNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setLegNumber(UniqueID value) {
        this.legNumber = value;
    }

    /**
     * 获取resvNameId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getResvNameId() {
        return resvNameId;
    }

    /**
     * 设置resvNameId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setResvNameId(UniqueID value) {
        this.resvNameId = value;
    }

    /**
     * 获取membershipID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getMembershipID() {
        return membershipID;
    }

    /**
     * 设置membershipID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setMembershipID(UniqueID value) {
        this.membershipID = value;
    }

}