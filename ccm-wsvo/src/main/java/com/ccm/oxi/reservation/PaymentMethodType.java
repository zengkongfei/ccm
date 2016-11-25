//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.10 at 03:58:32 下午 CST 
//


package com.ccm.oxi.reservation;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for paymentMethodType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="paymentMethodType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NA"/>
 *     &lt;enumeration value="CASH"/>
 *     &lt;enumeration value="CREDITCARD"/>
 *     &lt;enumeration value="DEBITCARD"/>
 *     &lt;enumeration value="VOUCHER"/>
 *     &lt;enumeration value="CHECK"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "paymentMethodType")
@XmlEnum
public enum PaymentMethodType {

    NA,
    CASH,
    CREDITCARD,
    DEBITCARD,
    VOUCHER,
    CHECK,
    OTHER;

    public String value() {
        return name();
    }

    public static PaymentMethodType fromValue(String v) {
        return valueOf(v);
    }

}
