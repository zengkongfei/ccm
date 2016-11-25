
package com.ccm.availability;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Restriction complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Restriction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="restrictionType" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RestrictionType" />
 *       &lt;attribute name="numberOfDays" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="roomType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rateCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Restriction")
public class Restriction {

    @XmlAttribute(name = "restrictionType", required = true)
    protected RestrictionType restrictionType;
    @XmlAttribute(name = "numberOfDays")
    protected BigInteger numberOfDays;
    @XmlAttribute(name = "roomType")
    protected String roomType;
    @XmlAttribute(name = "rateCode")
    protected String rateCode;

    /**
     * 获取restrictionType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RestrictionType }
     *     
     */
    public RestrictionType getRestrictionType() {
        return restrictionType;
    }

    /**
     * 设置restrictionType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RestrictionType }
     *     
     */
    public void setRestrictionType(RestrictionType value) {
        this.restrictionType = value;
    }

    /**
     * 获取numberOfDays属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumberOfDays() {
        return numberOfDays;
    }

    /**
     * 设置numberOfDays属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumberOfDays(BigInteger value) {
        this.numberOfDays = value;
    }

    /**
     * 获取roomType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * 设置roomType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomType(String value) {
        this.roomType = value;
    }

    /**
     * 获取rateCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateCode() {
        return rateCode;
    }

    /**
     * 设置rateCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateCode(String value) {
        this.rateCode = value;
    }

}
