
package com.ccm.availability;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>UserGroupType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="UserGroupType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TAM"/>
 *     &lt;enumeration value="BOOKER"/>
 *     &lt;enumeration value="BOOKER2"/>
 *     &lt;enumeration value="COMPANY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UserGroupType", namespace = "http://webservices.micros.com/og/4.3/Name/")
@XmlEnum
public enum UserGroupType {

    TAM("TAM"),
    BOOKER("BOOKER"),
    @XmlEnumValue("BOOKER2")
    BOOKER_2("BOOKER2"),
    COMPANY("COMPANY");
    private final String value;

    UserGroupType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UserGroupType fromValue(String v) {
        for (UserGroupType c: UserGroupType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
