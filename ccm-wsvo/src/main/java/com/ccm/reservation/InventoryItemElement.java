
package com.ccm.reservation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>InventoryItemElement complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="InventoryItemElement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ItemCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ItemName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Description" type="{http://webservices.micros.com/og/4.3/Common/}DescriptiveText" maxOccurs="unbounded"/>
 *         &lt;element name="SellControl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SellSeparate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Quantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Available" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ItemGroup" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ItemGroupElement"/>
 *       &lt;/sequence>
 *       &lt;attribute name="isPackage" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="isChild" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ItemDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InventoryItemElement", propOrder = {
    "itemCode",
    "itemName",
    "description",
    "sellControl",
    "sellSeparate",
    "quantity",
    "available",
    "itemGroup"
})
public class InventoryItemElement {

    @XmlElement(name = "ItemCode", required = true)
    protected String itemCode;
    @XmlElement(name = "ItemName", required = true)
    protected String itemName;
    @XmlElement(name = "Description", required = true)
    protected List<DescriptiveText> description;
    @XmlElement(name = "SellControl", required = true)
    protected String sellControl;
    @XmlElement(name = "SellSeparate", required = true)
    protected String sellSeparate;
    @XmlElement(name = "Quantity")
    protected Integer quantity;
    @XmlElement(name = "Available")
    protected Integer available;
    @XmlElement(name = "ItemGroup", required = true)
    protected ItemGroupElement itemGroup;
    @XmlAttribute(name = "isPackage")
    protected Boolean isPackage;
    @XmlAttribute(name = "isChild")
    protected Boolean isChild;
    @XmlAttribute(name = "ItemDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar itemDate;

    /**
     * 获取itemCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 设置itemCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemCode(String value) {
        this.itemCode = value;
    }

    /**
     * 获取itemName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置itemName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemName(String value) {
        this.itemName = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the description property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptiveText }
     * 
     * 
     */
    public List<DescriptiveText> getDescription() {
        if (description == null) {
            description = new ArrayList<DescriptiveText>();
        }
        return this.description;
    }

    /**
     * 获取sellControl属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellControl() {
        return sellControl;
    }

    /**
     * 设置sellControl属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellControl(String value) {
        this.sellControl = value;
    }

    /**
     * 获取sellSeparate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellSeparate() {
        return sellSeparate;
    }

    /**
     * 设置sellSeparate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellSeparate(String value) {
        this.sellSeparate = value;
    }

    /**
     * 获取quantity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 设置quantity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuantity(Integer value) {
        this.quantity = value;
    }

    /**
     * 获取available属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAvailable() {
        return available;
    }

    /**
     * 设置available属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAvailable(Integer value) {
        this.available = value;
    }

    /**
     * 获取itemGroup属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ItemGroupElement }
     *     
     */
    public ItemGroupElement getItemGroup() {
        return itemGroup;
    }

    /**
     * 设置itemGroup属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ItemGroupElement }
     *     
     */
    public void setItemGroup(ItemGroupElement value) {
        this.itemGroup = value;
    }

    /**
     * 获取isPackage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsPackage() {
        return isPackage;
    }

    /**
     * 设置isPackage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPackage(Boolean value) {
        this.isPackage = value;
    }

    /**
     * 获取isChild属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsChild() {
        return isChild;
    }

    /**
     * 设置isChild属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsChild(Boolean value) {
        this.isChild = value;
    }

    /**
     * 获取itemDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getItemDate() {
        return itemDate;
    }

    /**
     * 设置itemDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setItemDate(XMLGregorianCalendar value) {
        this.itemDate = value;
    }

}
