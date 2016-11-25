
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RegionalSearchCodeType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="RegionalSearchCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CITY"/>
 *     &lt;enumeration value="CHAIN"/>
 *     &lt;enumeration value="CRO"/>
 *     &lt;enumeration value="REGION"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RegionalSearchCodeType")
@XmlEnum
public enum RegionalSearchCodeType {

    CITY,
    CHAIN,
    CRO,
    REGION,
    OTHER;

    public String value() {
        return name();
    }

    public static RegionalSearchCodeType fromValue(String v) {
        return valueOf(v);
    }

}
