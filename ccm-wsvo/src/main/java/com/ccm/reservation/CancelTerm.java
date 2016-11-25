
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>CancelTerm complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CancelTerm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CancelReason" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="cancelType" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}CancelTermType" />
 *       &lt;attribute name="otherCancelType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="cancelReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="cancelNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="cancelDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="cancelBy" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CancelTerm", propOrder = {
    "cancelReason"
})
public class CancelTerm {

    @XmlElement(name = "CancelReason")
    protected Paragraph cancelReason;
    @XmlAttribute(name = "cancelType", required = true)
    protected CancelTermType cancelType;
    @XmlAttribute(name = "otherCancelType")
    protected String otherCancelType;
    @XmlAttribute(name = "cancelReasonCode")
    protected String cancelReasonCode;
    @XmlAttribute(name = "cancelNumber")
    protected String cancelNumber;
    @XmlAttribute(name = "cancelDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar cancelDate;
    @XmlAttribute(name = "cancelBy")
    protected String cancelBy;

    /**
     * 获取cancelReason属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getCancelReason() {
        return cancelReason;
    }

    /**
     * 设置cancelReason属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setCancelReason(Paragraph value) {
        this.cancelReason = value;
    }

    /**
     * 获取cancelType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CancelTermType }
     *     
     */
    public CancelTermType getCancelType() {
        return cancelType;
    }

    /**
     * 设置cancelType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CancelTermType }
     *     
     */
    public void setCancelType(CancelTermType value) {
        this.cancelType = value;
    }

    /**
     * 获取otherCancelType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherCancelType() {
        return otherCancelType;
    }

    /**
     * 设置otherCancelType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherCancelType(String value) {
        this.otherCancelType = value;
    }

    /**
     * 获取cancelReasonCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelReasonCode() {
        return cancelReasonCode;
    }

    /**
     * 设置cancelReasonCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelReasonCode(String value) {
        this.cancelReasonCode = value;
    }

    /**
     * 获取cancelNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelNumber() {
        return cancelNumber;
    }

    /**
     * 设置cancelNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelNumber(String value) {
        this.cancelNumber = value;
    }

    /**
     * 获取cancelDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCancelDate() {
        return cancelDate;
    }

    /**
     * 设置cancelDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCancelDate(XMLGregorianCalendar value) {
        this.cancelDate = value;
    }

    /**
     * 获取cancelBy属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelBy() {
        return cancelBy;
    }

    /**
     * 设置cancelBy属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelBy(String value) {
        this.cancelBy = value;
    }

}
