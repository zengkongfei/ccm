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
 * <p>Java class for PricingSourceType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PricingSourceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Published"/>
 *     &lt;enumeration value="Private"/>
 *     &lt;enumeration value="Both"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PricingSourceType")
@XmlEnum
public enum PricingSourceType {


    /**
     * Published fare.
     * 
     */
    @XmlEnumValue("Published")
    PUBLISHED("Published"),

    /**
     * Private fare.
     * 
     */
    @XmlEnumValue("Private")
    PRIVATE("Private"),

    /**
     * Fare is both public and private.
     * 
     */
    @XmlEnumValue("Both")
    BOTH("Both");
    private final String value;

    PricingSourceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PricingSourceType fromValue(String v) {
        for (PricingSourceType c: PricingSourceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}