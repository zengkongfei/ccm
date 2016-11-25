
package com.ccm.webservices.og.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Payment complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Payment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="PaymentsAccepted" type="{http://webservices.micros.com/og/4.3/HotelCommon/}PaymentsAccepted" minOccurs="0"/>
 *         &lt;element name="PaymentUsed" type="{http://webservices.micros.com/og/4.3/HotelCommon/}PaymentUsed" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Payment", propOrder = {
    "paymentsAccepted",
    "paymentUsed"
})
public class Payment {

    @XmlElement(name = "PaymentsAccepted")
    protected PaymentsAccepted paymentsAccepted;
    @XmlElement(name = "PaymentUsed")
    protected PaymentUsed paymentUsed;

    /**
     * 获取paymentsAccepted属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PaymentsAccepted }
     *     
     */
    public PaymentsAccepted getPaymentsAccepted() {
        return paymentsAccepted;
    }

    /**
     * 设置paymentsAccepted属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentsAccepted }
     *     
     */
    public void setPaymentsAccepted(PaymentsAccepted value) {
        this.paymentsAccepted = value;
    }

    /**
     * 获取paymentUsed属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PaymentUsed }
     *     
     */
    public PaymentUsed getPaymentUsed() {
        return paymentUsed;
    }

    /**
     * 设置paymentUsed属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentUsed }
     *     
     */
    public void setPaymentUsed(PaymentUsed value) {
        this.paymentUsed = value;
    }

}
