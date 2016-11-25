
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>FeeTaxType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="FeeTaxType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="EXCLUSIVE"/>
 *     &lt;enumeration value="INCLUSIVE"/>
 *     &lt;enumeration value="CUMULATIVE"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FeeTaxType")
@XmlEnum
public enum FeeTaxType {

    EXCLUSIVE,
    INCLUSIVE,
    CUMULATIVE,
    OTHER;

    public String value() {
        return name();
    }

    public static FeeTaxType fromValue(String v) {
        return valueOf(v);
    }

}
