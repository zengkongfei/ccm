
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
 * <p>RoomRate complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RoomRate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Rates" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RateList" minOccurs="0"/>
 *         &lt;element name="InvBlockDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *         &lt;element name="Total" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="GDSTotalPricing" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GdsTotalPricing" minOccurs="0"/>
 *         &lt;element name="TotalPoints" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="Packages" type="{http://webservices.micros.com/og/4.3/HotelCommon/}PackageElementList" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="roomTypeCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ratePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="expirationDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="suppressRate" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="redemRate" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="hasPackage" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="pointsChangeIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomRate", propOrder = {
    "rates",
    "invBlockDescription",
    "total",
    "gdsTotalPricing",
    "totalPoints",
    "packages"
})
public class RoomRate {

    @XmlElement(name = "Rates")
    protected RateList rates;
    @XmlElement(name = "InvBlockDescription")
    protected Paragraph invBlockDescription;
    @XmlElement(name = "Total")
    protected Amount total;
    @XmlElement(name = "GDSTotalPricing")
    protected GdsTotalPricing gdsTotalPricing;
    @XmlElement(name = "TotalPoints")
    protected Double totalPoints;
    @XmlElement(name = "Packages")
    protected PackageElementList packages;
    @XmlAttribute(name = "roomTypeCode", required = true)
    protected String roomTypeCode;
    @XmlAttribute(name = "ratePlanCode")
    protected String ratePlanCode;
    @XmlAttribute(name = "effectiveDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar effectiveDate;
    @XmlAttribute(name = "expirationDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expirationDate;
    @XmlAttribute(name = "suppressRate")
    protected Boolean suppressRate;
    @XmlAttribute(name = "redemRate")
    protected Boolean redemRate;
    @XmlAttribute(name = "hasPackage")
    protected Boolean hasPackage;
    @XmlAttribute(name = "pointsChangeIndicator")
    protected Boolean pointsChangeIndicator;

    /**
     * 获取rates属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RateList }
     *     
     */
    public RateList getRates() {
        return rates;
    }

    /**
     * 设置rates属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RateList }
     *     
     */
    public void setRates(RateList value) {
        this.rates = value;
    }

    /**
     * 获取invBlockDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getInvBlockDescription() {
        return invBlockDescription;
    }

    /**
     * 设置invBlockDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setInvBlockDescription(Paragraph value) {
        this.invBlockDescription = value;
    }

    /**
     * 获取total属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotal() {
        return total;
    }

    /**
     * 设置total属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotal(Amount value) {
        this.total = value;
    }

    /**
     * 获取gdsTotalPricing属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GdsTotalPricing }
     *     
     */
    public GdsTotalPricing getGDSTotalPricing() {
        return gdsTotalPricing;
    }

    /**
     * 设置gdsTotalPricing属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GdsTotalPricing }
     *     
     */
    public void setGDSTotalPricing(GdsTotalPricing value) {
        this.gdsTotalPricing = value;
    }

    /**
     * 获取totalPoints属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalPoints() {
        return totalPoints;
    }

    /**
     * 设置totalPoints属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalPoints(Double value) {
        this.totalPoints = value;
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
     * 获取roomTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    /**
     * 设置roomTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomTypeCode(String value) {
        this.roomTypeCode = value;
    }

    /**
     * 获取ratePlanCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatePlanCode() {
        return ratePlanCode;
    }

    /**
     * 设置ratePlanCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatePlanCode(String value) {
        this.ratePlanCode = value;
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
     * 获取suppressRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSuppressRate() {
        return suppressRate;
    }

    /**
     * 设置suppressRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSuppressRate(Boolean value) {
        this.suppressRate = value;
    }

    /**
     * 获取redemRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRedemRate() {
        return redemRate;
    }

    /**
     * 设置redemRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRedemRate(Boolean value) {
        this.redemRate = value;
    }

    /**
     * 获取hasPackage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasPackage() {
        return hasPackage;
    }

    /**
     * 设置hasPackage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasPackage(Boolean value) {
        this.hasPackage = value;
    }

    /**
     * 获取pointsChangeIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPointsChangeIndicator() {
        return pointsChangeIndicator;
    }

    /**
     * 设置pointsChangeIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPointsChangeIndicator(Boolean value) {
        this.pointsChangeIndicator = value;
    }

}
