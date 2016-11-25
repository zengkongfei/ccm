
package com.ccm.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>InventoryItemGroupElementList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="InventoryItemGroupElementList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InventoryItemGroup" type="{http://webservices.micros.com/og/4.3/HotelCommon/}InventoryItemGroupElement" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InventoryItemGroupElementList", propOrder = {
    "inventoryItemGroup"
})
public class InventoryItemGroupElementList {

    @XmlElement(name = "InventoryItemGroup")
    protected List<InventoryItemGroupElement> inventoryItemGroup;

    /**
     * Gets the value of the inventoryItemGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inventoryItemGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInventoryItemGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InventoryItemGroupElement }
     * 
     * 
     */
    public List<InventoryItemGroupElement> getInventoryItemGroup() {
        if (inventoryItemGroup == null) {
            inventoryItemGroup = new ArrayList<InventoryItemGroupElement>();
        }
        return this.inventoryItemGroup;
    }

}
