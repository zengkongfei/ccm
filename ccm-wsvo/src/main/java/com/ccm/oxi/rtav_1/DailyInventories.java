//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.17 at 04:02:21 下午 CST 
//


package com.ccm.oxi.rtav_1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DailyInventories complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DailyInventories">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DailyInventory" type="{rtav.fidelio.2.0}DailyInventory" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DailyInventories", propOrder = {
    "dailyInventory"
})
public class DailyInventories {

    @XmlElement(name = "DailyInventory", required = true)
    protected List<DailyInventory> dailyInventory;

    /**
     * Gets the value of the dailyInventory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dailyInventory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDailyInventory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DailyInventory }
     * 
     * 
     */
    public List<DailyInventory> getDailyInventory() {
        if (dailyInventory == null) {
            dailyInventory = new ArrayList<DailyInventory>();
        }
        return this.dailyInventory;
    }

}
