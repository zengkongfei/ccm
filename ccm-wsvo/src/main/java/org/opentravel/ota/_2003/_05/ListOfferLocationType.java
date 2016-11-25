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
 * <p>Java class for List_OfferLocationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="List_OfferLocationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Origin"/>
 *     &lt;enumeration value="Destination"/>
 *     &lt;enumeration value="SingleLocation"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "List_OfferLocationType")
@XmlEnum
public enum ListOfferLocationType {


    /**
     * Trip mode origin or starting location.
     * 
     */
    @XmlEnumValue("Origin")
    ORIGIN("Origin"),

    /**
     * Trip mode ending or destination location.
     * 
     */
    @XmlEnumValue("Destination")
    DESTINATION("Destination"),

    /**
     * Trip mode start and end locations are the same.
     * 
     */
    @XmlEnumValue("SingleLocation")
    SINGLE_LOCATION("SingleLocation");
    private final String value;

    ListOfferLocationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ListOfferLocationType fromValue(String v) {
        for (ListOfferLocationType c: ListOfferLocationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}