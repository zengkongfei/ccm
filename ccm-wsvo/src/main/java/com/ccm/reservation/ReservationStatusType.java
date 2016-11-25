
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ReservationStatusType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="ReservationStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RESERVED"/>
 *     &lt;enumeration value="PROSPECT"/>
 *     &lt;enumeration value="NOSHOW"/>
 *     &lt;enumeration value="CANCELED"/>
 *     &lt;enumeration value="INHOUSE"/>
 *     &lt;enumeration value="CHECKEDOUT"/>
 *     &lt;enumeration value="CHANGED"/>
 *     &lt;enumeration value="WAITLISTED"/>
 *     &lt;enumeration value="CHECKEDIN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ReservationStatusType", namespace = "http://webservices.micros.com/og/4.3/Reservation/")
@XmlEnum
public enum ReservationStatusType {

    RESERVED,
    PROSPECT,
    NOSHOW,
    CANCELED,
    INHOUSE,
    CHECKEDOUT,
    CHECKOUT,
    CHANGED,
    WAITLISTED,
    CHECKEDIN,
    CHECKIN;

    public String value() {
        return name();
    }

    public static ReservationStatusType fromValue(String v) {
        return valueOf(v);
    }

}
