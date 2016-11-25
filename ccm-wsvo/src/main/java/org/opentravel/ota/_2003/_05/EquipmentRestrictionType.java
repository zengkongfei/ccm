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
 * <p>Java class for EquipmentRestrictionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EquipmentRestrictionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OneWayOnly"/>
 *     &lt;enumeration value="RoundTripOnly"/>
 *     &lt;enumeration value="AnyReservation"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EquipmentRestrictionType")
@XmlEnum
public enum EquipmentRestrictionType {

    @XmlEnumValue("OneWayOnly")
    ONE_WAY_ONLY("OneWayOnly"),
    @XmlEnumValue("RoundTripOnly")
    ROUND_TRIP_ONLY("RoundTripOnly"),
    @XmlEnumValue("AnyReservation")
    ANY_RESERVATION("AnyReservation");
    private final String value;

    EquipmentRestrictionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EquipmentRestrictionType fromValue(String v) {
        for (EquipmentRestrictionType c: EquipmentRestrictionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
