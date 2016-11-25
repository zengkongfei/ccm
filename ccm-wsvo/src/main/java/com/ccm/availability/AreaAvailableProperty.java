
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AreaAvailableProperty complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AreaAvailableProperty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HotelReference" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference"/>
 *         &lt;element name="PropertyStatus" type="{http://webservices.micros.com/og/4.3/Availability/}PropertyStatusCode"/>
 *         &lt;element name="PropertyErrorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MinMaxRate" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://webservices.micros.com/og/4.3/HotelCommon/}MinMaxRate">
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="RateCodes" type="{http://webservices.micros.com/og/4.3/Availability/}RateCodeInformationList" minOccurs="0"/>
 *         &lt;element name="RateCalculationMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AreaAvailableProperty", namespace = "http://webservices.micros.com/og/4.3/Availability/", propOrder = {
    "hotelReference",
    "propertyStatus",
    "propertyErrorCode",
    "minMaxRate",
    "rateCodes",
    "rateCalculationMethod"
})
public class AreaAvailableProperty {

    @XmlElement(name = "HotelReference", required = true)
    protected HotelReference hotelReference;
    @XmlElement(name = "PropertyStatus", required = true)
    protected PropertyStatusCode propertyStatus;
    @XmlElement(name = "PropertyErrorCode")
    protected String propertyErrorCode;
    @XmlElement(name = "MinMaxRate")
    protected AreaAvailableProperty.MinMaxRate minMaxRate;
    @XmlElement(name = "RateCodes")
    protected RateCodeInformationList rateCodes;
    @XmlElement(name = "RateCalculationMethod")
    protected String rateCalculationMethod;

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
     * 获取propertyStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PropertyStatusCode }
     *     
     */
    public PropertyStatusCode getPropertyStatus() {
        return propertyStatus;
    }

    /**
     * 设置propertyStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyStatusCode }
     *     
     */
    public void setPropertyStatus(PropertyStatusCode value) {
        this.propertyStatus = value;
    }

    /**
     * 获取propertyErrorCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyErrorCode() {
        return propertyErrorCode;
    }

    /**
     * 设置propertyErrorCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyErrorCode(String value) {
        this.propertyErrorCode = value;
    }

    /**
     * 获取minMaxRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AreaAvailableProperty.MinMaxRate }
     *     
     */
    public AreaAvailableProperty.MinMaxRate getMinMaxRate() {
        return minMaxRate;
    }

    /**
     * 设置minMaxRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AreaAvailableProperty.MinMaxRate }
     *     
     */
    public void setMinMaxRate(AreaAvailableProperty.MinMaxRate value) {
        this.minMaxRate = value;
    }

    /**
     * 获取rateCodes属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RateCodeInformationList }
     *     
     */
    public RateCodeInformationList getRateCodes() {
        return rateCodes;
    }

    /**
     * 设置rateCodes属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RateCodeInformationList }
     *     
     */
    public void setRateCodes(RateCodeInformationList value) {
        this.rateCodes = value;
    }

    /**
     * 获取rateCalculationMethod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateCalculationMethod() {
        return rateCalculationMethod;
    }

    /**
     * 设置rateCalculationMethod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateCalculationMethod(String value) {
        this.rateCalculationMethod = value;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://webservices.micros.com/og/4.3/HotelCommon/}MinMaxRate">
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class MinMaxRate
        extends com.ccm.availability.MinMaxRate
    {


    }

}
