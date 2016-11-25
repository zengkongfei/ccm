
package com.ccm.webservices.og.hotelcommon;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PropertyStatusCodeType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="PropertyStatusCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OPEN"/>
 *     &lt;enumeration value="CLOSED"/>
 *     &lt;enumeration value="ALTERNATE"/>
 *     &lt;enumeration value="REQUESTED"/>
 *     &lt;enumeration value="UNKNOWN"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PropertyStatusCodeType")
@XmlEnum
public enum PropertyStatusCodeType {

    OPEN,
    CLOSED,
    ALTERNATE,
    REQUESTED,
    UNKNOWN,
    OTHER;

    public String value() {
        return name();
    }

    public static PropertyStatusCodeType fromValue(String v) {
        return valueOf(v);
    }

}
