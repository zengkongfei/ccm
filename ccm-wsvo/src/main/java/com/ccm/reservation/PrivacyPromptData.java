
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>PrivacyPromptData complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PrivacyPromptData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PrivacyPromptFrequency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastPrivacyPromptDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="NextPrivacyPromptDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrivacyPromptData", namespace = "http://webservices.micros.com/og/4.3/Name/", propOrder = {
    "privacyPromptFrequency",
    "lastPrivacyPromptDate",
    "nextPrivacyPromptDate"
})
public class PrivacyPromptData {

    @XmlElement(name = "PrivacyPromptFrequency")
    protected String privacyPromptFrequency;
    @XmlElement(name = "LastPrivacyPromptDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastPrivacyPromptDate;
    @XmlElement(name = "NextPrivacyPromptDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar nextPrivacyPromptDate;

    /**
     * 获取privacyPromptFrequency属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivacyPromptFrequency() {
        return privacyPromptFrequency;
    }

    /**
     * 设置privacyPromptFrequency属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivacyPromptFrequency(String value) {
        this.privacyPromptFrequency = value;
    }

    /**
     * 获取lastPrivacyPromptDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastPrivacyPromptDate() {
        return lastPrivacyPromptDate;
    }

    /**
     * 设置lastPrivacyPromptDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastPrivacyPromptDate(XMLGregorianCalendar value) {
        this.lastPrivacyPromptDate = value;
    }

    /**
     * 获取nextPrivacyPromptDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNextPrivacyPromptDate() {
        return nextPrivacyPromptDate;
    }

    /**
     * 设置nextPrivacyPromptDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNextPrivacyPromptDate(XMLGregorianCalendar value) {
        this.nextPrivacyPromptDate = value;
    }

}
