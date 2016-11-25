
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ReservationDispositionType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="ReservationDispositionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="DUEIN"/>
 *     &lt;enumeration value="INHOUSE"/>
 *     &lt;enumeration value="DUEOUT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ReservationDispositionType", namespace = "http://webservices.micros.com/og/4.3/Reservation/")
@XmlEnum
public enum ReservationDispositionType {

    NONE,
    DUEIN,
    INHOUSE,
    DUEOUT;

    public String value() {
        return name();
    }

    public static ReservationDispositionType fromValue(String v) {
        return valueOf(v);
    }

}
