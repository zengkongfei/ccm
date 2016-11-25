
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ReservationGuarantee complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ReservationGuarantee">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GuaranteeInfo" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *         &lt;element name="GuaranteeCreditCard" type="{http://webservices.micros.com/og/4.3/Common/}CreditCard" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="guaranteeType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReservationGuarantee", namespace = "http://webservices.micros.com/og/4.3/Reservation/", propOrder = {
    "guaranteeInfo",
    "guaranteeCreditCard"
})
public class ReservationGuarantee {

    @XmlElement(name = "GuaranteeInfo")
    protected Paragraph guaranteeInfo;
    @XmlElement(name = "GuaranteeCreditCard")
    protected CreditCard guaranteeCreditCard;
    @XmlAttribute(name = "guaranteeType", required = true)
    protected String guaranteeType;

    /**
     * 获取guaranteeInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getGuaranteeInfo() {
        return guaranteeInfo;
    }

    /**
     * 设置guaranteeInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setGuaranteeInfo(Paragraph value) {
        this.guaranteeInfo = value;
    }

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
     * 获取guaranteeType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuaranteeType() {
        return guaranteeType;
    }

    /**
     * 设置guaranteeType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuaranteeType(String value) {
        this.guaranteeType = value;
    }

}
