
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AdditionalBedAmount complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AdditionalBedAmount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Amount" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="extraBedType" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AdditionalBedType" />
 *       &lt;attribute name="otherExtraBedType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="quantity" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdditionalBedAmount", propOrder = {
    "amount"
})
public class AdditionalBedAmount {

    @XmlElement(name = "Amount")
    protected Amount amount;
    @XmlAttribute(name = "extraBedType", required = true)
    protected AdditionalBedType extraBedType;
    @XmlAttribute(name = "otherExtraBedType")
    protected String otherExtraBedType;
    @XmlAttribute(name = "quantity")
    protected Integer quantity;

    /**
     * 获取amount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * 设置amount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setAmount(Amount value) {
        this.amount = value;
    }

    /**
     * 获取extraBedType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AdditionalBedType }
     *     
     */
    public AdditionalBedType getExtraBedType() {
        return extraBedType;
    }

    /**
     * 设置extraBedType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalBedType }
     *     
     */
    public void setExtraBedType(AdditionalBedType value) {
        this.extraBedType = value;
    }

    /**
     * 获取otherExtraBedType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherExtraBedType() {
        return otherExtraBedType;
    }

    /**
     * 设置otherExtraBedType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherExtraBedType(String value) {
        this.otherExtraBedType = value;
    }

    /**
     * 获取quantity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 设置quantity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuantity(Integer value) {
        this.quantity = value;
    }

}
