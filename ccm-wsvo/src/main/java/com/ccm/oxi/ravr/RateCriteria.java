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
 * <p>Java class for rateCriteria.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="rateCriteria">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RATECLASS"/>
 *     &lt;enumeration value="RATECATEGORY"/>
 *     &lt;enumeration value="RATECODE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "rateCriteria")
@XmlEnum
public enum RateCriteria {

    RATECLASS,
    RATECATEGORY,
    RATECODE;

    public String value() {
        return name();
    }

    public static RateCriteria fromValue(String v) {
        return valueOf(v);
    }

}