
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Guarantee complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Guarantee">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GuaranteesAccepted" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GuaranteesAccepted" minOccurs="0"/>
 *         &lt;element name="Deadline" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="GuaranteeDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="guaranteeType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="mandatoryDeposit" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="cancellationRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="creditCardRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="paymentType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="requirementCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Guarantee", propOrder = {
    "guaranteesAccepted",
    "deadline",
    "guaranteeDescription"
})
public class Guarantee {

    @XmlElement(name = "GuaranteesAccepted")
    protected GuaranteesAccepted guaranteesAccepted;
    @XmlElement(name = "Deadline")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar deadline;
    @XmlElement(name = "GuaranteeDescription")
    protected Paragraph guaranteeDescription;
    @XmlAttribute(name = "guaranteeType")
    protected String guaranteeType;
    @XmlAttribute(name = "mandatoryDeposit")
    protected Boolean mandatoryDeposit;
    @XmlAttribute(name = "cancellationRequired")
    protected Boolean cancellationRequired;
    @XmlAttribute(name = "creditCardRequired")
    protected Boolean creditCardRequired;
    @XmlAttribute(name = "paymentType")
    protected String paymentType;
    @XmlAttribute(name = "requirementCode")
    protected String requirementCode;

    /**
     * 获取guaranteesAccepted属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GuaranteesAccepted }
     *     
     */
    public GuaranteesAccepted getGuaranteesAccepted() {
        return guaranteesAccepted;
    }

    /**
     * 设置guaranteesAccepted属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GuaranteesAccepted }
     *     
     */
    public void setGuaranteesAccepted(GuaranteesAccepted value) {
        this.guaranteesAccepted = value;
    }

    /**
     * 获取deadline属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeadline() {
        return deadline;
    }

    /**
     * 设置deadline属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeadline(XMLGregorianCalendar value) {
        this.deadline = value;
    }

    /**
     * 获取guaranteeDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getGuaranteeDescription() {
        return guaranteeDescription;
    }

    /**
     * 设置guaranteeDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setGuaranteeDescription(Paragraph value) {
        this.guaranteeDescription = value;
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

    /**
     * 获取mandatoryDeposit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMandatoryDeposit() {
        return mandatoryDeposit;
    }

    /**
     * 设置mandatoryDeposit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMandatoryDeposit(Boolean value) {
        this.mandatoryDeposit = value;
    }

    /**
     * 获取cancellationRequired属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCancellationRequired() {
        return cancellationRequired;
    }

    /**
     * 设置cancellationRequired属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCancellationRequired(Boolean value) {
        this.cancellationRequired = value;
    }

    /**
     * 获取creditCardRequired属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCreditCardRequired() {
        return creditCardRequired;
    }

    /**
     * 设置creditCardRequired属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCreditCardRequired(Boolean value) {
        this.creditCardRequired = value;
    }

    /**
     * 获取paymentType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * 设置paymentType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentType(String value) {
        this.paymentType = value;
    }

    /**
     * 获取requirementCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequirementCode() {
        return requirementCode;
    }

    /**
     * 设置requirementCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequirementCode(String value) {
        this.requirementCode = value;
    }

}
