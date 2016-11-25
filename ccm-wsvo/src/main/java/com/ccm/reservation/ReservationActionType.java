
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ReservationActionType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="ReservationActionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ADD"/>
 *     &lt;enumeration value="EDIT"/>
 *     &lt;enumeration value="CANCEL"/>
 *     &lt;enumeration value="CHECKIN"/>
 *     &lt;enumeration value="CHECKOUT"/>
 *     &lt;enumeration value="NOSHOW"/>
 *     &lt;enumeration value="REINSTATE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ReservationActionType", namespace = "http://webservices.micros.com/og/4.3/Reservation/")
@XmlEnum
public enum ReservationActionType {

    ADD,
    EDIT,
    CANCEL,
    CHECKIN,
    CHECKOUT,
    NOSHOW,
    REINSTATE;

    public String value() {
        return name();
    }

    public static ReservationActionType fromValue(String v) {
        return valueOf(v);
    }

}
