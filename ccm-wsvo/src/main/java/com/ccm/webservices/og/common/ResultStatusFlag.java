
package com.ccm.webservices.og.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ResultStatusFlag的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="ResultStatusFlag">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FAIL"/>
 *     &lt;enumeration value="SUCCESS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ResultStatusFlag")
@XmlEnum
public enum ResultStatusFlag {

    FAIL,
    SUCCESS;

    public String value() {
        return name();
    }

    public static ResultStatusFlag fromValue(String v) {
        return valueOf(v);
    }

}
