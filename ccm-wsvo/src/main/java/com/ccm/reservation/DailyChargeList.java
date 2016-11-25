
package com.ccm.reservation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DailyChargeList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="DailyChargeList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ChargesForPostingDate" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ChargesForTheDay" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="TotalRoomRateAndPackages" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="TotalTaxesAndFees" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="TaxInclusive" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="decimals" type="{http://www.w3.org/2001/XMLSchema}short" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DailyChargeList", propOrder = {
    "chargesForPostingDate"
})
public class DailyChargeList {

    @XmlElement(name = "ChargesForPostingDate", required = true)
    protected List<ChargesForTheDay> chargesForPostingDate;
    @XmlAttribute(name = "TotalRoomRateAndPackages")
    protected Double totalRoomRateAndPackages;
    @XmlAttribute(name = "TotalTaxesAndFees")
    protected Double totalTaxesAndFees;
    @XmlAttribute(name = "TaxInclusive")
    protected Boolean taxInclusive;
    @XmlAttribute(name = "decimals")
    protected Short decimals;

    /**
     * Gets the value of the chargesForPostingDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chargesForPostingDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChargesForPostingDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChargesForTheDay }
     * 
     * 
     */
    public List<ChargesForTheDay> getChargesForPostingDate() {
        if (chargesForPostingDate == null) {
            chargesForPostingDate = new ArrayList<ChargesForTheDay>();
        }
        return this.chargesForPostingDate;
    }

    /**
     * 获取totalRoomRateAndPackages属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalRoomRateAndPackages() {
        return totalRoomRateAndPackages;
    }

    /**
     * 设置totalRoomRateAndPackages属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalRoomRateAndPackages(Double value) {
        this.totalRoomRateAndPackages = value;
    }

    /**
     * 获取totalTaxesAndFees属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalTaxesAndFees() {
        return totalTaxesAndFees;
    }

    /**
     * 设置totalTaxesAndFees属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalTaxesAndFees(Double value) {
        this.totalTaxesAndFees = value;
    }

    /**
     * 获取taxInclusive属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTaxInclusive() {
        return taxInclusive;
    }

    /**
     * 设置taxInclusive属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTaxInclusive(Boolean value) {
        this.taxInclusive = value;
    }

    /**
     * 获取decimals属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getDecimals() {
        return decimals;
    }

    /**
     * 设置decimals属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setDecimals(Short value) {
        this.decimals = value;
    }

}
