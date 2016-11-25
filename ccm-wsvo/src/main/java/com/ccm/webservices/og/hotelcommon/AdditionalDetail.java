
package com.ccm.webservices.og.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AdditionalDetail complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AdditionalDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AdditionalDetailDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="detailType" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AdditionalDetailType" />
 *       &lt;attribute name="otherDetailType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdditionalDetail", propOrder = {
    "additionalDetailDescription"
})
public class AdditionalDetail {

    @XmlElement(name = "AdditionalDetailDescription")
    protected Paragraph additionalDetailDescription;
    @XmlAttribute(name = "detailType", required = true)
    protected AdditionalDetailType detailType;
    @XmlAttribute(name = "otherDetailType")
    protected String otherDetailType;

    /**
     * 获取additionalDetailDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getAdditionalDetailDescription() {
        return additionalDetailDescription;
    }

    /**
     * 设置additionalDetailDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setAdditionalDetailDescription(Paragraph value) {
        this.additionalDetailDescription = value;
    }

    /**
     * 获取detailType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AdditionalDetailType }
     *     
     */
    public AdditionalDetailType getDetailType() {
        return detailType;
    }

    /**
     * 设置detailType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalDetailType }
     *     
     */
    public void setDetailType(AdditionalDetailType value) {
        this.detailType = value;
    }

    /**
     * 获取otherDetailType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherDetailType() {
        return otherDetailType;
    }

    /**
     * 设置otherDetailType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherDetailType(String value) {
        this.otherDetailType = value;
    }

}
