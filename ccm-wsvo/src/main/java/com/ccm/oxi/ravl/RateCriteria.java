//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.17 at 04:00:55 下午 CST 
//


package com.ccm.oxi.ravl;

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
