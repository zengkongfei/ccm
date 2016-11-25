
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AdditionalBedType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="AdditionalBedType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ADULT"/>
 *     &lt;enumeration value="CHILD"/>
 *     &lt;enumeration value="CRIB"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AdditionalBedType")
@XmlEnum
public enum AdditionalBedType {

    ADULT,
    CHILD,
    CRIB,
    OTHER;

    public String value() {
        return name();
    }

    public static AdditionalBedType fromValue(String v) {
        return valueOf(v);
    }

}
