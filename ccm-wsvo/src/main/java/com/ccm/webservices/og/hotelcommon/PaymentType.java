
package com.ccm.webservices.og.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccm.webservices.og.common.CreditCard;


/**
 * <p>PaymentType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PaymentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="PaymentCard" type="{http://webservices.micros.com/og/4.3/Common/}CreditCard" minOccurs="0"/>
 *           &lt;element name="PaymentVoucher" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Voucher" minOccurs="0"/>
 *           &lt;element name="OtherPayment" type="{http://webservices.micros.com/og/4.3/HotelCommon/}OtherPaymentType" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentType", propOrder = {
    "paymentCard",
    "paymentVoucher",
    "otherPayment"
})
public class PaymentType {

    @XmlElement(name = "PaymentCard")
    protected CreditCard paymentCard;
    @XmlElement(name = "PaymentVoucher")
    protected Voucher paymentVoucher;
    @XmlElement(name = "OtherPayment")
    protected OtherPaymentType otherPayment;

    /**
     * 获取paymentCard属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CreditCard }
     *     
     */
    public CreditCard getPaymentCard() {
        return paymentCard;
    }

    /**
     * 设置paymentCard属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCard }
     *     
     */
    public void setPaymentCard(CreditCard value) {
        this.paymentCard = value;
    }

    /**
     * 获取paymentVoucher属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Voucher }
     *     
     */
    public Voucher getPaymentVoucher() {
        return paymentVoucher;
    }

    /**
     * 设置paymentVoucher属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Voucher }
     *     
     */
    public void setPaymentVoucher(Voucher value) {
        this.paymentVoucher = value;
    }

    /**
     * 获取otherPayment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OtherPaymentType }
     *     
     */
    public OtherPaymentType getOtherPayment() {
        return otherPayment;
    }

    /**
     * 设置otherPayment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OtherPaymentType }
     *     
     */
    public void setOtherPayment(OtherPaymentType value) {
        this.otherPayment = value;
    }

}
