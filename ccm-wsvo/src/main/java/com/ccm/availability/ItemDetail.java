
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ItemDetail complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ItemDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InventoryItemElements" type="{http://webservices.micros.com/og/4.3/HotelCommon/}InventoryItemElementList" minOccurs="0"/>
 *         &lt;element name="PackageDetails" type="{http://webservices.micros.com/og/4.3/HotelCommon/}PackageDetailList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemDetail", propOrder = {
    "inventoryItemElements",
    "packageDetails"
})
public class ItemDetail {

    @XmlElement(name = "InventoryItemElements")
    protected InventoryItemElementList inventoryItemElements;
    @XmlElement(name = "PackageDetails", required = true)
    protected PackageDetailList packageDetails;

    /**
     * 获取inventoryItemElements属性的值。
     * 
     * @return
     *     possible object is
     *     {@link InventoryItemElementList }
     *     
     */
    public InventoryItemElementList getInventoryItemElements() {
        return inventoryItemElements;
    }

    /**
     * 设置inventoryItemElements属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link InventoryItemElementList }
     *     
     */
    public void setInventoryItemElements(InventoryItemElementList value) {
        this.inventoryItemElements = value;
    }

    /**
     * 获取packageDetails属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PackageDetailList }
     *     
     */
    public PackageDetailList getPackageDetails() {
        return packageDetails;
    }

    /**
     * 设置packageDetails属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PackageDetailList }
     *     
     */
    public void setPackageDetails(PackageDetailList value) {
        this.packageDetails = value;
    }

}
