
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Promotion complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Promotion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Dates" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IssueType" type="{http://webservices.micros.com/og/4.3/Membership/}PromotionIssueType" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://webservices.micros.com/og/4.3/Membership/}PromotionSubscriptionStatusType" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Promotion", namespace = "http://webservices.micros.com/og/4.3/Membership/", propOrder = {

})
public class Promotion {

    @XmlElement(name = "Code", required = true)
    protected String code;
    @XmlElement(name = "Dates")
    protected TimeSpan dates;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "IssueType")
    protected PromotionIssueType issueType;
    @XmlElement(name = "Status")
    protected PromotionSubscriptionStatusType status;

    /**
     * 获取code属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置code属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * 获取dates属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TimeSpan }
     *     
     */
    public TimeSpan getDates() {
        return dates;
    }

    /**
     * 设置dates属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpan }
     *     
     */
    public void setDates(TimeSpan value) {
        this.dates = value;
    }

    /**
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取issueType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PromotionIssueType }
     *     
     */
    public PromotionIssueType getIssueType() {
        return issueType;
    }

    /**
     * 设置issueType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PromotionIssueType }
     *     
     */
    public void setIssueType(PromotionIssueType value) {
        this.issueType = value;
    }

    /**
     * 获取status属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PromotionSubscriptionStatusType }
     *     
     */
    public PromotionSubscriptionStatusType getStatus() {
        return status;
    }

    /**
     * 设置status属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PromotionSubscriptionStatusType }
     *     
     */
    public void setStatus(PromotionSubscriptionStatusType value) {
        this.status = value;
    }

}
