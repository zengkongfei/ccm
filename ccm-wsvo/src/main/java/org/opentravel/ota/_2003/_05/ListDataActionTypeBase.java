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
 * <p>Java class for List_DataActionType_Base.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="List_DataActionType_Base">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Create/Add"/>
 *     &lt;enumeration value="Read/Query"/>
 *     &lt;enumeration value="Update/Modify"/>
 *     &lt;enumeration value="Cancel"/>
 *     &lt;enumeration value="Delete"/>
 *     &lt;enumeration value="Replace"/>
 *     &lt;enumeration value="Other_"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "List_DataActionType_Base")
@XmlEnum
public enum ListDataActionTypeBase {


    /**
     * Add specified data whether the data already exists or not.
     * 
     */
    @XmlEnumValue("Create/Add")
    CREATE_ADD("Create/Add"),
    @XmlEnumValue("Read/Query")
    READ_QUERY("Read/Query"),

    /**
     * Add specified data if it does not exist or update data where it does exist.	
     * 
     */
    @XmlEnumValue("Update/Modify")
    UPDATE_MODIFY("Update/Modify"),

    /**
     * Cancel an existing item.
     * 
     */
    @XmlEnumValue("Cancel")
    CANCEL("Cancel"),

    /**
     * Remove specified data.
     * 
     */
    @XmlEnumValue("Delete")
    DELETE("Delete"),

    /**
     * Overlay existing data with specified data.
     * 
     */
    @XmlEnumValue("Replace")
    REPLACE("Replace"),
    @XmlEnumValue("Other_")
    OTHER("Other_");
    private final String value;

    ListDataActionTypeBase(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ListDataActionTypeBase fromValue(String v) {
        for (ListDataActionTypeBase c: ListDataActionTypeBase.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
