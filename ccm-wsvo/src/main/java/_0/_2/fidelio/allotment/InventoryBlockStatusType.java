//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 04:23:24GMT+08:00 
//


package _0._2.fidelio.allotment;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inventoryBlockStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="inventoryBlockStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="INITIAL"/>
 *     &lt;enumeration value="ACTIVE"/>
 *     &lt;enumeration value="DEACTIVATED"/>
 *     &lt;enumeration value="RELEASE"/>
 *     &lt;enumeration value="UNLOCK"/>
 *     &lt;enumeration value="CANCEL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "inventoryBlockStatusType")
@XmlEnum
public enum InventoryBlockStatusType {

    INITIAL,
    ACTIVE,
    DEACTIVATED,
    RELEASE,
    UNLOCK,
    CANCEL;

    public String value() {
        return name();
    }

    public static InventoryBlockStatusType fromValue(String v) {
        return valueOf(v);
    }

}
