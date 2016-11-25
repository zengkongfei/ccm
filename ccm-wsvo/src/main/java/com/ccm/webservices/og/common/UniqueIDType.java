
package com.ccm.webservices.og.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>UniqueIDType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="UniqueIDType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="EXTERNAL"/>
 *     &lt;enumeration value="INTERNAL"/>
 *     &lt;enumeration value="CANCELLATIONEXTERNAL"/>
 *     &lt;enumeration value="CANCELLATIONINTERNAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UniqueIDType")
@XmlEnum
public enum UniqueIDType {

    EXTERNAL,
    INTERNAL,
    CANCELLATIONEXTERNAL,
    CANCELLATIONINTERNAL;

    public String value() {
        return name();
    }

    public static UniqueIDType fromValue(String v) {
        return valueOf(v);
    }

}
