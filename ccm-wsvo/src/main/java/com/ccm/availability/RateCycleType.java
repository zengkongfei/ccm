
package com.ccm.availability;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RateCycleType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="RateCycleType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MONTHLY"/>
 *     &lt;enumeration value="WEEKLY"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RateCycleType")
@XmlEnum
public enum RateCycleType {

    MONTHLY,
    WEEKLY,
    OTHER;

    public String value() {
        return name();
    }

    public static RateCycleType fromValue(String v) {
        return valueOf(v);
    }

}
