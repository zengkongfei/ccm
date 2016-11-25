
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>GdsTotalPriceList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="GdsTotalPriceList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RateCalculation" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RateCalculation" minOccurs="0"/>
 *         &lt;element name="AdditionalBedAmounts" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AdditionalBedAmountList" minOccurs="0"/>
 *         &lt;element name="RoomQuality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BedType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GdsTotalPriceList", namespace = "http://webservices.micros.com/og/4.3/Availability/", propOrder = {
    "rateCalculation",
    "additionalBedAmounts",
    "roomQuality",
    "bedType"
})
public class GdsTotalPriceList {

    @XmlElement(name = "RateCalculation")
    protected RateCalculation rateCalculation;
    @XmlElement(name = "AdditionalBedAmounts")
    protected AdditionalBedAmountList additionalBedAmounts;
    @XmlElement(name = "RoomQuality")
    protected String roomQuality;
    @XmlElement(name = "BedType")
    protected String bedType;

    /**
     * 获取rateCalculation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RateCalculation }
     *     
     */
    public RateCalculation getRateCalculation() {
        return rateCalculation;
    }

    /**
     * 设置rateCalculation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RateCalculation }
     *     
     */
    public void setRateCalculation(RateCalculation value) {
        this.rateCalculation = value;
    }

    /**
     * 获取additionalBedAmounts属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AdditionalBedAmountList }
     *     
     */
    public AdditionalBedAmountList getAdditionalBedAmounts() {
        return additionalBedAmounts;
    }

    /**
     * 设置additionalBedAmounts属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalBedAmountList }
     *     
     */
    public void setAdditionalBedAmounts(AdditionalBedAmountList value) {
        this.additionalBedAmounts = value;
    }

    /**
     * 获取roomQuality属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomQuality() {
        return roomQuality;
    }

    /**
     * 设置roomQuality属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomQuality(String value) {
        this.roomQuality = value;
    }

    /**
     * 获取bedType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBedType() {
        return bedType;
    }

    /**
     * 设置bedType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBedType(String value) {
        this.bedType = value;
    }

}
