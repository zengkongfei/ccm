//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.14 at 01:53:08  GMT+08:00 
//


package org.opentravel.ota._2003._05;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opentravel.org/OTA/2003/05}MessageAcknowledgementType">
 *       &lt;sequence>
 *         &lt;element name="Inventories" type="{http://www.opentravel.org/OTA/2003/05}InvCountType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "inventories"
})
@XmlRootElement(name = "OTA_HotelInvCountNotifRS")
public class OTAHotelInvCountNotifRS
    extends MessageAcknowledgementType
{

    @XmlElement(name = "Inventories")
    protected InvCountType inventories;

    /**
     * Gets the value of the inventories property.
     * 
     * @return
     *     possible object is
     *     {@link InvCountType }
     *     
     */
    public InvCountType getInventories() {
        return inventories;
    }

    /**
     * Sets the value of the inventories property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvCountType }
     *     
     */
    public void setInventories(InvCountType value) {
        this.inventories = value;
    }

}
