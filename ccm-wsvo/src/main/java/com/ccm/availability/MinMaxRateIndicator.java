
package com.ccm.availability;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MinMaxRateIndicator的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="MinMaxRateIndicator">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MINIMUM"/>
 *     &lt;enumeration value="MAXIMUM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MinMaxRateIndicator", namespace = "http://webservices.micros.com/og/4.3/Availability/")
@XmlEnum
public enum MinMaxRateIndicator {

    MINIMUM,
    MAXIMUM;

    public String value() {
        return name();
    }

    public static MinMaxRateIndicator fromValue(String v) {
        return valueOf(v);
    }

}
