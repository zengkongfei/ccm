
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EncryptedSwipe complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EncryptedSwipe">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TrackIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SwiperType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SwiperID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MaskedPAN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Track1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Track2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="KeySerialNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncryptedSwipe", namespace = "http://webservices.micros.com/og/4.3/Common/", propOrder = {
    "trackIndicator",
    "swiperType",
    "swiperID",
    "maskedPAN",
    "track1",
    "track2",
    "keySerialNumber"
})
public class EncryptedSwipe {

    @XmlElement(name = "TrackIndicator", required = true)
    protected String trackIndicator;
    @XmlElement(name = "SwiperType", required = true)
    protected String swiperType;
    @XmlElement(name = "SwiperID")
    protected String swiperID;
    @XmlElement(name = "MaskedPAN", required = true)
    protected String maskedPAN;
    @XmlElement(name = "Track1")
    protected String track1;
    @XmlElement(name = "Track2")
    protected String track2;
    @XmlElement(name = "KeySerialNumber", required = true)
    protected String keySerialNumber;

    /**
     * 获取trackIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackIndicator() {
        return trackIndicator;
    }

    /**
     * 设置trackIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackIndicator(String value) {
        this.trackIndicator = value;
    }

    /**
     * 获取swiperType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwiperType() {
        return swiperType;
    }

    /**
     * 设置swiperType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwiperType(String value) {
        this.swiperType = value;
    }

    /**
     * 获取swiperID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwiperID() {
        return swiperID;
    }

    /**
     * 设置swiperID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwiperID(String value) {
        this.swiperID = value;
    }

    /**
     * 获取maskedPAN属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaskedPAN() {
        return maskedPAN;
    }

    /**
     * 设置maskedPAN属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaskedPAN(String value) {
        this.maskedPAN = value;
    }

    /**
     * 获取track1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrack1() {
        return track1;
    }

    /**
     * 设置track1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrack1(String value) {
        this.track1 = value;
    }

    /**
     * 获取track2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrack2() {
        return track2;
    }

    /**
     * 设置track2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrack2(String value) {
        this.track2 = value;
    }

    /**
     * 获取keySerialNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeySerialNumber() {
        return keySerialNumber;
    }

    /**
     * 设置keySerialNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeySerialNumber(String value) {
        this.keySerialNumber = value;
    }

}
