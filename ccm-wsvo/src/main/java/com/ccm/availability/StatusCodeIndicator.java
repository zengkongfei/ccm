
package com.ccm.availability;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>StatusCodeIndicator的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="StatusCodeIndicator">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="INVALID"/>
 *     &lt;enumeration value="RESTRICTED"/>
 *     &lt;enumeration value="AVAILABLE"/>
 *     &lt;enumeration value="UNAVAILABLE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StatusCodeIndicator", namespace = "http://webservices.micros.com/og/4.3/Availability/")
@XmlEnum
public enum StatusCodeIndicator {

    INVALID,
    RESTRICTED,
    AVAILABLE,
    UNAVAILABLE;

    public String value() {
        return name();
    }

    public static StatusCodeIndicator fromValue(String v) {
        return valueOf(v);
    }

}
