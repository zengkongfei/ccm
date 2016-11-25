
package com.ccm.webservices.og.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>ChargesForTheDay complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ChargesForTheDay">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RoomRateAndPackages" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ChargeList"/>
 *         &lt;element name="TaxesAndFees" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ChargeList"/>
 *       &lt;/sequence>
 *       &lt;attribute name="PostingDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="TotalCharges" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargesForTheDay", propOrder = {
    "roomRateAndPackages",
    "taxesAndFees"
})
public class ChargesForTheDay {

    @XmlElement(name = "RoomRateAndPackages", required = true)
    protected ChargeList roomRateAndPackages;
    @XmlElement(name = "TaxesAndFees", required = true)
    protected ChargeList taxesAndFees;
    @XmlAttribute(name = "PostingDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar postingDate;
    @XmlAttribute(name = "TotalCharges")
    protected Double totalCharges;

    /**
     * 获取roomRateAndPackages属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ChargeList }
     *     
     */
    public ChargeList getRoomRateAndPackages() {
        return roomRateAndPackages;
    }

    /**
     * 设置roomRateAndPackages属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeList }
     *     
     */
    public void setRoomRateAndPackages(ChargeList value) {
        this.roomRateAndPackages = value;
    }

    /**
     * 获取taxesAndFees属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ChargeList }
     *     
     */
    public ChargeList getTaxesAndFees() {
        return taxesAndFees;
    }

    /**
     * 设置taxesAndFees属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeList }
     *     
     */
    public void setTaxesAndFees(ChargeList value) {
        this.taxesAndFees = value;
    }

    /**
     * 获取postingDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPostingDate() {
        return postingDate;
    }

    /**
     * 设置postingDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPostingDate(XMLGregorianCalendar value) {
        this.postingDate = value;
    }

    /**
     * 获取totalCharges属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalCharges() {
        return totalCharges;
    }

    /**
     * 设置totalCharges属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalCharges(Double value) {
        this.totalCharges = value;
    }

}
