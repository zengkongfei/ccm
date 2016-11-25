//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.14 at 01:53:08  GMT+08:00 
//


package org.opentravel.ota._2003._05;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * Use: This is a string list of enumerations with an "Other_" literal to support an open enumeration list. Use the "Other_" enumeration in combination with the Code Extension fields to exchange a value that is not in the list and is known to your trading partners.
 * 
 * <p>Java class for List_ChargeUnit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="List_ChargeUnit">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.opentravel.org/OTA/2003/05>List_ChargeUnit_Base">
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "List_ChargeUnit", propOrder = {
    "value"
})
@XmlSeeAlso({
    org.opentravel.ota._2003._05.CodeListFeeType.Qualifiers.ChargeUnit.Unit.class,
    org.opentravel.ota._2003._05.CodeListFeeType.Taxes.Qualifiers.ChargeUnit.Unit.class
})
public class ListChargeUnit {

    @XmlValue
    protected ListChargeUnitBase value;

    /**
     * Source: Charge Type (CHG) OpenTravel codelist.
     * 
     * @return
     *     possible object is
     *     {@link ListChargeUnitBase }
     *     
     */
    public ListChargeUnitBase getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListChargeUnitBase }
     *     
     */
    public void setValue(ListChargeUnitBase value) {
        this.value = value;
    }

}
