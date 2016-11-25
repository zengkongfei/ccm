
package com.ccm.webservices.og.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BlackListFlag的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="BlackListFlag">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="REMOVE"/>
 *     &lt;enumeration value="SET"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BlackListFlag")
@XmlEnum
public enum BlackListFlag {

    REMOVE,
    SET;

    public String value() {
        return name();
    }

    public static BlackListFlag fromValue(String v) {
        return valueOf(v);
    }

}
