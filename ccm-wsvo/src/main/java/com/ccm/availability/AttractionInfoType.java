
package com.ccm.availability;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AttractionInfoType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="AttractionInfoType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DIRECTIONS"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AttractionInfoType")
@XmlEnum
public enum AttractionInfoType {

    DIRECTIONS,
    OTHER;

    public String value() {
        return name();
    }

    public static AttractionInfoType fromValue(String v) {
        return valueOf(v);
    }

}
