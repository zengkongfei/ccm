
package com.ccm.webservices.ows.information;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ccm.webservices.og.hotelcommon.GDSResultStatus;
import com.ccm.webservices.og.hotelcommon.InventoryItemGroupElementList;
import com.ccm.webservices.og.hotelcommon.PackageElementList;
import com.ccm.webservices.og.hotelcommon.PackageGroupElementList;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GDSResultStatus"/>
 *         &lt;element name="PackageGroups" type="{http://webservices.micros.com/og/4.3/HotelCommon/}PackageGroupElementList" minOccurs="0"/>
 *         &lt;element name="Packages" type="{http://webservices.micros.com/og/4.3/HotelCommon/}PackageElementList" minOccurs="0"/>
 *         &lt;element name="Items" type="{http://webservices.micros.com/og/4.3/HotelCommon/}InventoryItemGroupElementList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "result",
    "packageGroups",
    "packages",
    "items"
})
@XmlRootElement(name = "PackageItemsResponse")
public class PackageItemsResponse {

    @XmlElement(name = "Result", required = true)
    protected GDSResultStatus result;
    @XmlElement(name = "PackageGroups")
    protected PackageGroupElementList packageGroups;
    @XmlElement(name = "Packages")
    protected PackageElementList packages;
    @XmlElement(name = "Items")
    protected InventoryItemGroupElementList items;

    /**
     * 获取result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GDSResultStatus }
     *     
     */
    public GDSResultStatus getResult() {
        return result;
    }

    /**
     * 设置result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GDSResultStatus }
     *     
     */
    public void setResult(GDSResultStatus value) {
        this.result = value;
    }

    /**
     * 获取packageGroups属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PackageGroupElementList }
     *     
     */
    public PackageGroupElementList getPackageGroups() {
        return packageGroups;
    }

    /**
     * 设置packageGroups属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PackageGroupElementList }
     *     
     */
    public void setPackageGroups(PackageGroupElementList value) {
        this.packageGroups = value;
    }

    /**
     * 获取packages属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PackageElementList }
     *     
     */
    public PackageElementList getPackages() {
        return packages;
    }

    /**
     * 设置packages属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PackageElementList }
     *     
     */
    public void setPackages(PackageElementList value) {
        this.packages = value;
    }

    /**
     * 获取items属性的值。
     * 
     * @return
     *     possible object is
     *     {@link InventoryItemGroupElementList }
     *     
     */
    public InventoryItemGroupElementList getItems() {
        return items;
    }

    /**
     * 设置items属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link InventoryItemGroupElementList }
     *     
     */
    public void setItems(InventoryItemGroupElementList value) {
        this.items = value;
    }

}
