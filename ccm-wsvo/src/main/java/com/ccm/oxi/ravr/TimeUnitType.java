//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.30 at 01:55:28
//


package com.ccm.oxi.ravr;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for timeUnitType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="timeUnitType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NA"/>
 *     &lt;enumeration value="YEAR"/>
 *     &lt;enumeration value="MONTH"/>
 *     &lt;enumeration value="WEEK"/>
 *     &lt;enumeration value="DAY"/>
 *     &lt;enumeration value="HOUR"/>
 *     &lt;enumeration value="MINUTE"/>
 *     &lt;enumeration value="SECOND"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "timeUnitType")
@XmlEnum
public enum TimeUnitType {

    NA,
    YEAR,
    MONTH,
    WEEK,
    DAY,
    HOUR,
    MINUTE,
    SECOND;

    public String value() {
        return name();
    }

    public static TimeUnitType fromValue(String v) {
        return valueOf(v);
    }

}