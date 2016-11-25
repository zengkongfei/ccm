
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>FetchBookingFilters complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="FetchBookingFilters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CreationDate" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan" minOccurs="0"/>
 *         &lt;element name="ContractId" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="Membership" type="{http://webservices.micros.com/og/4.3/Common/}Membership" minOccurs="0"/>
 *         &lt;element name="HotelReference" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference" minOccurs="0"/>
 *         &lt;element name="ConfirmationNumber" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="ResvNameId" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="KeyTrack" type="{http://webservices.micros.com/og/4.3/Common/}KeyTrack" minOccurs="0"/>
 *         &lt;element name="ExternalSystemNumber" type="{http://webservices.micros.com/og/4.3/Reservation/}ExternalReference" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="GetList" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="RoomNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ReservationStatus" type="{http://webservices.micros.com/og/4.3/Reservation/}ReservationStatusType" />
 *       &lt;attribute name="ReservationDisposition" type="{http://webservices.micros.com/og/4.3/Reservation/}ReservationDispositionType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FetchBookingFilters", namespace = "http://webservices.micros.com/og/4.3/Reservation/", propOrder = {
    "creationDate",
    "contractId",
    "membership",
    "hotelReference",
    "confirmationNumber",
    "resvNameId",
    "keyTrack",
    "externalSystemNumber"
})
public class FetchBookingFilters {

    @XmlElement(name = "CreationDate")
    protected TimeSpan creationDate;
    @XmlElement(name = "ContractId")
    protected UniqueID contractId;
    @XmlElement(name = "Membership")
    protected Membership membership;
    @XmlElement(name = "HotelReference")
    protected HotelReference hotelReference;
    @XmlElement(name = "ConfirmationNumber")
    protected UniqueID confirmationNumber;
    @XmlElement(name = "ResvNameId")
    protected UniqueID resvNameId;
    @XmlElement(name = "KeyTrack")
    protected KeyTrack keyTrack;
    @XmlElement(name = "ExternalSystemNumber")
    protected ExternalReference externalSystemNumber;
    @XmlAttribute(name = "GetList")
    protected Boolean getList;
    @XmlAttribute(name = "RoomNumber")
    protected String roomNumber;
    @XmlAttribute(name = "ReservationStatus")
    protected ReservationStatusType reservationStatus;
    @XmlAttribute(name = "ReservationDisposition")
    protected ReservationDispositionType reservationDisposition;

    /**
     * 获取creationDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TimeSpan }
     *     
     */
    public TimeSpan getCreationDate() {
        return creationDate;
    }

    /**
     * 设置creationDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpan }
     *     
     */
    public void setCreationDate(TimeSpan value) {
        this.creationDate = value;
    }

    /**
     * 获取contractId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getContractId() {
        return contractId;
    }

    /**
     * 设置contractId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setContractId(UniqueID value) {
        this.contractId = value;
    }

    /**
     * 获取membership属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Membership }
     *     
     */
    public Membership getMembership() {
        return membership;
    }

    /**
     * 设置membership属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Membership }
     *     
     */
    public void setMembership(Membership value) {
        this.membership = value;
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
     * 获取keyTrack属性的值。
     * 
     * @return
     *     possible object is
     *     {@link KeyTrack }
     *     
     */
    public KeyTrack getKeyTrack() {
        return keyTrack;
    }

    /**
     * 设置keyTrack属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link KeyTrack }
     *     
     */
    public void setKeyTrack(KeyTrack value) {
        this.keyTrack = value;
    }

    /**
     * 获取externalSystemNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ExternalReference }
     *     
     */
    public ExternalReference getExternalSystemNumber() {
        return externalSystemNumber;
    }

    /**
     * 设置externalSystemNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ExternalReference }
     *     
     */
    public void setExternalSystemNumber(ExternalReference value) {
        this.externalSystemNumber = value;
    }

    /**
     * 获取getList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGetList() {
        return getList;
    }

    /**
     * 设置getList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGetList(Boolean value) {
        this.getList = value;
    }

    /**
     * 获取roomNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * 设置roomNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomNumber(String value) {
        this.roomNumber = value;
    }

    /**
     * 获取reservationStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ReservationStatusType }
     *     
     */
    public ReservationStatusType getReservationStatus() {
        return reservationStatus;
    }

    /**
     * 设置reservationStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ReservationStatusType }
     *     
     */
    public void setReservationStatus(ReservationStatusType value) {
        this.reservationStatus = value;
    }

    /**
     * 获取reservationDisposition属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ReservationDispositionType }
     *     
     */
    public ReservationDispositionType getReservationDisposition() {
        return reservationDisposition;
    }

    /**
     * 设置reservationDisposition属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ReservationDispositionType }
     *     
     */
    public void setReservationDisposition(ReservationDispositionType value) {
        this.reservationDisposition = value;
    }

}
