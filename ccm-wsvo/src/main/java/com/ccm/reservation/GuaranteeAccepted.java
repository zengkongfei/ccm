
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>GuaranteeAccepted complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="GuaranteeAccepted">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GuaranteeCreditCard" type="{http://webservices.micros.com/og/4.3/Common/}CreditCard" minOccurs="0"/>
 *         &lt;element name="GuaranteeTravelAgent" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="GuaranteeCompany" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="agentPhone" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GuaranteeAccepted", propOrder = {
    "guaranteeCreditCard",
    "guaranteeTravelAgent",
    "guaranteeCompany"
})
public class GuaranteeAccepted {

    @XmlElement(name = "GuaranteeCreditCard")
    protected CreditCard guaranteeCreditCard;
    @XmlElement(name = "GuaranteeTravelAgent")
    protected UniqueID guaranteeTravelAgent;
    @XmlElement(name = "GuaranteeCompany")
    protected UniqueID guaranteeCompany;
    @XmlAttribute(name = "agentPhone")
    protected String agentPhone;

    /**
     * 获取guaranteeCreditCard属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CreditCard }
     *     
     */
    public CreditCard getGuaranteeCreditCard() {
        return guaranteeCreditCard;
    }

    /**
     * 设置guaranteeCreditCard属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCard }
     *     
     */
    public void setGuaranteeCreditCard(CreditCard value) {
        this.guaranteeCreditCard = value;
    }

    /**
     * 获取guaranteeTravelAgent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getGuaranteeTravelAgent() {
        return guaranteeTravelAgent;
    }

    /**
     * 设置guaranteeTravelAgent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setGuaranteeTravelAgent(UniqueID value) {
        this.guaranteeTravelAgent = value;
    }

    /**
     * 获取guaranteeCompany属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getGuaranteeCompany() {
        return guaranteeCompany;
    }

    /**
     * 设置guaranteeCompany属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setGuaranteeCompany(UniqueID value) {
        this.guaranteeCompany = value;
    }

    /**
     * 获取agentPhone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentPhone() {
        return agentPhone;
    }

    /**
     * 设置agentPhone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentPhone(String value) {
        this.agentPhone = value;
    }

}
