//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.05 at 04:34:51 下午 CST 
//


package com.ccm.oxi.result;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for resultMsgType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="resultMsgType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PROFILE"/>
 *     &lt;enumeration value="ALLOTMENT"/>
 *     &lt;enumeration value="RATEHEADER"/>
 *     &lt;enumeration value="RATEDETAIL"/>
 *     &lt;enumeration value="RATEAVAIL"/>
 *     &lt;enumeration value="RATEAVLRT"/>
 *     &lt;enumeration value="PACKAGE"/>
 *     &lt;enumeration value="RATE"/>
 *     &lt;enumeration value="RESERVATION"/>
 *     &lt;enumeration value="INVENTORY"/>
 *     &lt;enumeration value="CCOMMENT"/>
 *     &lt;enumeration value="HURDLE"/>
 *     &lt;enumeration value="UDFDEFINITION"/>
 *     &lt;enumeration value="CATEVENT"/>
 *     &lt;enumeration value="FBAGENDA"/>
 *     &lt;enumeration value="ACTIVITY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "resultMsgType")
@XmlEnum
public enum ResultMsgType {

    PROFILE,
    ALLOTMENT,
    RATEHEADER,
    RATEDETAIL,
    RATEAVAIL,
    RATEAVLRT,
    PACKAGE,
    RATE,
    RESERVATION,
    INVENTORY,
    CCOMMENT,
    HURDLE,
    UDFDEFINITION,
    CATEVENT,
    FBAGENDA,
    ACTIVITY;

    public String value() {
        return name();
    }

    public static ResultMsgType fromValue(String v) {
        return valueOf(v);
    }

}
