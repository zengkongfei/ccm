
package com.ccm.webservices.og.membership;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AwardType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="AwardType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RATE"/>
 *     &lt;enumeration value="PRODUCT"/>
 *     &lt;enumeration value="UPGRADE"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AwardType")
@XmlEnum
public enum AwardType {

    RATE,
    PRODUCT,
    UPGRADE,
    OTHER;

    public String value() {
        return name();
    }

    public static AwardType fromValue(String v) {
        return valueOf(v);
    }

}
