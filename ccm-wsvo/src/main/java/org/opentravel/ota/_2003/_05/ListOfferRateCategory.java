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
 * <p>Java class for List_OfferRateCategory.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="List_OfferRateCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Association"/>
 *     &lt;enumeration value="Business"/>
 *     &lt;enumeration value="Convention"/>
 *     &lt;enumeration value="Corporate"/>
 *     &lt;enumeration value="Employee"/>
 *     &lt;enumeration value="Government"/>
 *     &lt;enumeration value="Leisure"/>
 *     &lt;enumeration value="Negotiated"/>
 *     &lt;enumeration value="Promotional"/>
 *     &lt;enumeration value="Standard"/>
 *     &lt;enumeration value="VIP"/>
 *     &lt;enumeration value="Other_"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "List_OfferRateCategory")
@XmlEnum
public enum ListOfferRateCategory {

    @XmlEnumValue("Association")
    ASSOCIATION("Association"),
    @XmlEnumValue("Business")
    BUSINESS("Business"),
    @XmlEnumValue("Convention")
    CONVENTION("Convention"),
    @XmlEnumValue("Corporate")
    CORPORATE("Corporate"),
    @XmlEnumValue("Employee")
    EMPLOYEE("Employee"),
    @XmlEnumValue("Government")
    GOVERNMENT("Government"),
    @XmlEnumValue("Leisure")
    LEISURE("Leisure"),
    @XmlEnumValue("Negotiated")
    NEGOTIATED("Negotiated"),
    @XmlEnumValue("Promotional")
    PROMOTIONAL("Promotional"),
    @XmlEnumValue("Standard")
    STANDARD("Standard"),
    VIP("VIP"),

    /**
     * This is a string list of enumerations with an "Other_" literal to support an open enumeration list. Use the "Other_" value in combination with the @OtherType attribute to exchange a literal that is not in the list and is known to your trading partners.
     * 
     */
    @XmlEnumValue("Other_")
    OTHER("Other_");
    private final String value;

    ListOfferRateCategory(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ListOfferRateCategory fromValue(String v) {
        for (ListOfferRateCategory c: ListOfferRateCategory.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
