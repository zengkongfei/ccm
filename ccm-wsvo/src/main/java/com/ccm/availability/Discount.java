
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Discount complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Discount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DiscountAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DiscountType" type="{http://webservices.micros.com/og/4.3/HotelCommon/}DiscountType"/>
 *         &lt;element name="DiscountReason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Discount", propOrder = {
    "discountAmount",
    "discountType",
    "discountReason"
})
public class Discount {

    @XmlElement(name = "DiscountAmount")
    protected double discountAmount;
    @XmlElement(name = "DiscountType", required = true)
    protected DiscountType discountType;
    @XmlElement(name = "DiscountReason", required = true)
    protected String discountReason;

    /**
     * 获取discountAmount属性的值。
     * 
     */
    public double getDiscountAmount() {
        return discountAmount;
    }

    /**
     * 设置discountAmount属性的值。
     * 
     */
    public void setDiscountAmount(double value) {
        this.discountAmount = value;
    }

    /**
     * 获取discountType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DiscountType }
     *     
     */
    public DiscountType getDiscountType() {
        return discountType;
    }

    /**
     * 设置discountType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountType }
     *     
     */
    public void setDiscountType(DiscountType value) {
        this.discountType = value;
    }

    /**
     * 获取discountReason属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountReason() {
        return discountReason;
    }

    /**
     * 设置discountReason属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountReason(String value) {
        this.discountReason = value;
    }

}
