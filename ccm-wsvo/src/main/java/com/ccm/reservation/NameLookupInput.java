
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>NameLookupInput complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="NameLookupInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="Membership" type="{http://webservices.micros.com/og/4.3/Name/}NameLookupCriteriaMembership"/>
 *         &lt;element name="CreditCard" type="{http://webservices.micros.com/og/4.3/Name/}NameLookupCriteriaCreditCard"/>
 *         &lt;element name="EmailAddress" type="{http://webservices.micros.com/og/4.3/Name/}NameLookupCriteriaEmailAddress"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NameLookupInput", namespace = "http://webservices.micros.com/og/4.3/Name/", propOrder = {
    "membership",
    "creditCard",
    "emailAddress"
})
public class NameLookupInput {

    @XmlElement(name = "Membership")
    protected NameLookupCriteriaMembership membership;
    @XmlElement(name = "CreditCard")
    protected NameLookupCriteriaCreditCard creditCard;
    @XmlElement(name = "EmailAddress")
    protected NameLookupCriteriaEmailAddress emailAddress;

    /**
     * 获取membership属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NameLookupCriteriaMembership }
     *     
     */
    public NameLookupCriteriaMembership getMembership() {
        return membership;
    }

    /**
     * 设置membership属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NameLookupCriteriaMembership }
     *     
     */
    public void setMembership(NameLookupCriteriaMembership value) {
        this.membership = value;
    }

    /**
     * 获取creditCard属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NameLookupCriteriaCreditCard }
     *     
     */
    public NameLookupCriteriaCreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * 设置creditCard属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NameLookupCriteriaCreditCard }
     *     
     */
    public void setCreditCard(NameLookupCriteriaCreditCard value) {
        this.creditCard = value;
    }

    /**
     * 获取emailAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NameLookupCriteriaEmailAddress }
     *     
     */
    public NameLookupCriteriaEmailAddress getEmailAddress() {
        return emailAddress;
    }

    /**
     * 设置emailAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NameLookupCriteriaEmailAddress }
     *     
     */
    public void setEmailAddress(NameLookupCriteriaEmailAddress value) {
        this.emailAddress = value;
    }

}
