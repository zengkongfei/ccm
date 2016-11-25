
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>TierWizard complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="TierWizard">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SequenceNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MessageCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TranslatedMessage" type="{http://webservices.micros.com/og/4.3/Common/}Text"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TierWizard", namespace = "http://webservices.micros.com/og/4.3/Membership/", propOrder = {
    "sequenceNumber",
    "messageCode",
    "translatedMessage"
})
public class TierWizard {

    @XmlElement(name = "SequenceNumber")
    protected int sequenceNumber;
    @XmlElement(name = "MessageCode", required = true)
    protected String messageCode;
    @XmlElement(name = "TranslatedMessage", required = true)
    protected Text translatedMessage;

    /**
     * 获取sequenceNumber属性的值。
     * 
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * 设置sequenceNumber属性的值。
     * 
     */
    public void setSequenceNumber(int value) {
        this.sequenceNumber = value;
    }

    /**
     * 获取messageCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageCode() {
        return messageCode;
    }

    /**
     * 设置messageCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageCode(String value) {
        this.messageCode = value;
    }

    /**
     * 获取translatedMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Text }
     *     
     */
    public Text getTranslatedMessage() {
        return translatedMessage;
    }

    /**
     * 设置translatedMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Text }
     *     
     */
    public void setTranslatedMessage(Text value) {
        this.translatedMessage = value;
    }

}
