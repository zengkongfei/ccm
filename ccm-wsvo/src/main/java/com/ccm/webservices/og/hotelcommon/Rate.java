
package com.ccm.webservices.og.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ccm.webservices.og.common.Amount;


/**
 * <p>Rate complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Rate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Base" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="AdditionalGuestAmounts" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AdditionalGuestAmountList" minOccurs="0"/>
 *         &lt;element name="AdditionalBedAmounts" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AdditionalBedAmountList" minOccurs="0"/>
 *         &lt;element name="GdsTotalPricingTaxes" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GdsTotalPricingTaxList" minOccurs="0"/>
 *         &lt;element name="Points" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="expirationDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="rateOccurrence" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RateOccurrenceType" />
 *       &lt;attribute name="otherRateOccurrence" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rateChangeIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Rate", propOrder = {
    "base",
    "additionalGuestAmounts",
    "additionalBedAmounts",
    "gdsTotalPricingTaxes",
    "points"
})
public class Rate {

    @XmlElement(name = "Base")
    protected Amount base;
    @XmlElement(name = "AdditionalGuestAmounts")
    protected AdditionalGuestAmountList additionalGuestAmounts;
    @XmlElement(name = "AdditionalBedAmounts")
    protected AdditionalBedAmountList additionalBedAmounts;
    @XmlElement(name = "GdsTotalPricingTaxes")
    protected GdsTotalPricingTaxList gdsTotalPricingTaxes;
    @XmlElement(name = "Points")
    protected Double points;
    @XmlAttribute(name = "effectiveDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar effectiveDate;
    @XmlAttribute(name = "expirationDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expirationDate;
    @XmlAttribute(name = "rateOccurrence")
    protected RateOccurrenceType rateOccurrence;
    @XmlAttribute(name = "otherRateOccurrence")
    protected String otherRateOccurrence;
    @XmlAttribute(name = "rateChangeIndicator")
    protected Boolean rateChangeIndicator;

    /**
     * 获取base属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getBase() {
        return base;
    }

    /**
     * 设置base属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setBase(Amount value) {
        this.base = value;
    }

    /**
     * 获取additionalGuestAmounts属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AdditionalGuestAmountList }
     *     
     */
    public AdditionalGuestAmountList getAdditionalGuestAmounts() {
        return additionalGuestAmounts;
    }

    /**
     * 设置additionalGuestAmounts属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalGuestAmountList }
     *     
     */
    public void setAdditionalGuestAmounts(AdditionalGuestAmountList value) {
        this.additionalGuestAmounts = value;
    }

    /**
     * 获取additionalBedAmounts属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AdditionalBedAmountList }
     *     
     */
    public AdditionalBedAmountList getAdditionalBedAmounts() {
        return additionalBedAmounts;
    }

    /**
     * 设置additionalBedAmounts属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalBedAmountList }
     *     
     */
    public void setAdditionalBedAmounts(AdditionalBedAmountList value) {
        this.additionalBedAmounts = value;
    }

    /**
     * 获取gdsTotalPricingTaxes属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GdsTotalPricingTaxList }
     *     
     */
    public GdsTotalPricingTaxList getGdsTotalPricingTaxes() {
        return gdsTotalPricingTaxes;
    }

    /**
     * 设置gdsTotalPricingTaxes属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GdsTotalPricingTaxList }
     *     
     */
    public void setGdsTotalPricingTaxes(GdsTotalPricingTaxList value) {
        this.gdsTotalPricingTaxes = value;
    }

    /**
     * 获取points属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPoints() {
        return points;
    }

    /**
     * 设置points属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPoints(Double value) {
        this.points = value;
    }

    /**
     * 获取effectiveDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * 设置effectiveDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEffectiveDate(XMLGregorianCalendar value) {
        this.effectiveDate = value;
    }

    /**
     * 获取expirationDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpirationDate() {
        return expirationDate;
    }

    /**
     * 设置expirationDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpirationDate(XMLGregorianCalendar value) {
        this.expirationDate = value;
    }

    /**
     * 获取rateOccurrence属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RateOccurrenceType }
     *     
     */
    public RateOccurrenceType getRateOccurrence() {
        return rateOccurrence;
    }

    /**
     * 设置rateOccurrence属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RateOccurrenceType }
     *     
     */
    public void setRateOccurrence(RateOccurrenceType value) {
        this.rateOccurrence = value;
    }

    /**
     * 获取otherRateOccurrence属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherRateOccurrence() {
        return otherRateOccurrence;
    }

    /**
     * 设置otherRateOccurrence属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherRateOccurrence(String value) {
        this.otherRateOccurrence = value;
    }

    /**
     * 获取rateChangeIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRateChangeIndicator() {
        return rateChangeIndicator;
    }

    /**
     * 设置rateChangeIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRateChangeIndicator(Boolean value) {
        this.rateChangeIndicator = value;
    }

}
