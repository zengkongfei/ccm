//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.14 at 01:53:08  GMT+08:00 
//


package org.opentravel.ota._2003._05;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SeatDirectionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SeatDirectionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Facing"/>
 *     &lt;enumeration value="Back"/>
 *     &lt;enumeration value="Airline"/>
 *     &lt;enumeration value="Lateral"/>
 *     &lt;enumeration value="Unknown"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SeatDirectionType")
@XmlEnum
public enum SeatDirectionType {

    @XmlEnumValue("Facing")
    FACING("Facing"),
    @XmlEnumValue("Back")
    BACK("Back"),
    @XmlEnumValue("Airline")
    AIRLINE("Airline"),
    @XmlEnumValue("Lateral")
    LATERAL("Lateral"),
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown");
    private final String value;

    SeatDirectionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SeatDirectionType fromValue(String v) {
        for (SeatDirectionType c: SeatDirectionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}