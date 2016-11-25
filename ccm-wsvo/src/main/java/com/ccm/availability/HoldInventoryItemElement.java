
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>HoldInventoryItemElement complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="HoldInventoryItemElement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ItemCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ItemName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Quantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ItemStartDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ItemEndDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ItemHoldID" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HoldInventoryItemElement", propOrder = {
    "itemCode",
    "itemName",
    "quantity",
    "itemStartDate",
    "itemEndDate",
    "itemHoldID"
})
public class HoldInventoryItemElement {

    @XmlElement(name = "ItemCode", required = true)
    protected String itemCode;
    @XmlElement(name = "ItemName", required = true)
    protected String itemName;
    @XmlElement(name = "Quantity")
    protected Integer quantity;
    @XmlElement(name = "ItemStartDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar itemStartDate;
    @XmlElement(name = "ItemEndDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar itemEndDate;
    @XmlElement(name = "ItemHoldID", required = true)
    protected UniqueID itemHoldID;

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
     * 获取itemStartDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getItemStartDate() {
        return itemStartDate;
    }

    /**
     * 设置itemStartDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setItemStartDate(XMLGregorianCalendar value) {
        this.itemStartDate = value;
    }

    /**
     * 获取itemEndDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getItemEndDate() {
        return itemEndDate;
    }

    /**
     * 设置itemEndDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setItemEndDate(XMLGregorianCalendar value) {
        this.itemEndDate = value;
    }

    /**
     * 获取itemHoldID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getItemHoldID() {
        return itemHoldID;
    }

    /**
     * 设置itemHoldID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setItemHoldID(UniqueID value) {
        this.itemHoldID = value;
    }

}
