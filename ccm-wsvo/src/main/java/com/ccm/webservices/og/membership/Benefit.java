
package com.ccm.webservices.og.membership;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Benefit complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Benefit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BenefitCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProcessingMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BenefitActive" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ProgramBeginDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ProgramEndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ProgramDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProgramActive" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Benefit", propOrder = {
    "benefitCode",
    "processingMessage",
    "benefitActive",
    "programBeginDate",
    "programEndDate",
    "programDescription",
    "programActive"
})
public class Benefit {

    @XmlElement(name = "BenefitCode", required = true)
    protected String benefitCode;
    @XmlElement(name = "ProcessingMessage")
    protected String processingMessage;
    @XmlElement(name = "BenefitActive")
    protected Boolean benefitActive;
    @XmlElement(name = "ProgramBeginDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar programBeginDate;
    @XmlElement(name = "ProgramEndDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar programEndDate;
    @XmlElement(name = "ProgramDescription")
    protected String programDescription;
    @XmlElement(name = "ProgramActive")
    protected Boolean programActive;

    /**
     * 获取benefitCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBenefitCode() {
        return benefitCode;
    }

    /**
     * 设置benefitCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBenefitCode(String value) {
        this.benefitCode = value;
    }

    /**
     * 获取processingMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessingMessage() {
        return processingMessage;
    }

    /**
     * 设置processingMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessingMessage(String value) {
        this.processingMessage = value;
    }

    /**
     * 获取benefitActive属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBenefitActive() {
        return benefitActive;
    }

    /**
     * 设置benefitActive属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBenefitActive(Boolean value) {
        this.benefitActive = value;
    }

    /**
     * 获取programBeginDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProgramBeginDate() {
        return programBeginDate;
    }

    /**
     * 设置programBeginDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProgramBeginDate(XMLGregorianCalendar value) {
        this.programBeginDate = value;
    }

    /**
     * 获取programEndDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProgramEndDate() {
        return programEndDate;
    }

    /**
     * 设置programEndDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProgramEndDate(XMLGregorianCalendar value) {
        this.programEndDate = value;
    }

    /**
     * 获取programDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramDescription() {
        return programDescription;
    }

    /**
     * 设置programDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramDescription(String value) {
        this.programDescription = value;
    }

    /**
     * 获取programActive属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isProgramActive() {
        return programActive;
    }

    /**
     * 设置programActive属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setProgramActive(Boolean value) {
        this.programActive = value;
    }

}
