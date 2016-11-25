
package com.ccm.webservices.og.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ccm.webservices.og.common.Amount;
import com.ccm.webservices.og.common.CreditCard;


/**
 * <p>CreditCardAuthorizationDetail complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CreditCardAuthorizationDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CreditCard" type="{http://webservices.micros.com/og/4.3/Common/}CreditCard"/>
 *         &lt;element name="ApprovalAmount" type="{http://webservices.micros.com/og/4.3/Common/}Amount"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ApprovalCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SettlementDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditCardAuthorizationDetail", propOrder = {
    "creditCard",
    "approvalAmount"
})
public class CreditCardAuthorizationDetail {

    @XmlElement(name = "CreditCard", required = true)
    protected CreditCard creditCard;
    @XmlElement(name = "ApprovalAmount", required = true)
    protected Amount approvalAmount;
    @XmlAttribute(name = "ApprovalCode", required = true)
    protected String approvalCode;
    @XmlAttribute(name = "SettlementDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar settlementDate;

    /**
     * 获取creditCard属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CreditCard }
     *     
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * 设置creditCard属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCard }
     *     
     */
    public void setCreditCard(CreditCard value) {
        this.creditCard = value;
    }

    /**
     * 获取approvalAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getApprovalAmount() {
        return approvalAmount;
    }

    /**
     * 设置approvalAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setApprovalAmount(Amount value) {
        this.approvalAmount = value;
    }

    /**
     * 获取approvalCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApprovalCode() {
        return approvalCode;
    }

    /**
     * 设置approvalCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApprovalCode(String value) {
        this.approvalCode = value;
    }

    /**
     * 获取settlementDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSettlementDate() {
        return settlementDate;
    }

    /**
     * 设置settlementDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSettlementDate(XMLGregorianCalendar value) {
        this.settlementDate = value;
    }

}
