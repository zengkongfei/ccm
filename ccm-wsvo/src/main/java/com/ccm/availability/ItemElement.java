
package com.ccm.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ItemElement complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ItemElement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Package" type="{http://webservices.micros.com/og/4.3/HotelCommon/}PackageElement" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="InventoryItem" type="{http://webservices.micros.com/og/4.3/HotelCommon/}InventoryItemElement"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemElement", propOrder = {
    "_package",
    "inventoryItem"
})
public class ItemElement {

    @XmlElement(name = "Package")
    protected List<PackageElement> _package;
    @XmlElement(name = "InventoryItem", required = true)
    protected InventoryItemElement inventoryItem;

    /**
     * Gets the value of the package property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the package property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageElement }
     * 
     * 
     */
    public List<PackageElement> getPackage() {
        if (_package == null) {
            _package = new ArrayList<PackageElement>();
        }
        return this._package;
    }

    /**
     * 获取inventoryItem属性的值。
     * 
     * @return
     *     possible object is
     *     {@link InventoryItemElement }
     *     
     */
    public InventoryItemElement getInventoryItem() {
        return inventoryItem;
    }

    /**
     * 设置inventoryItem属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link InventoryItemElement }
     *     
     */
    public void setInventoryItem(InventoryItemElement value) {
        this.inventoryItem = value;
    }

}
