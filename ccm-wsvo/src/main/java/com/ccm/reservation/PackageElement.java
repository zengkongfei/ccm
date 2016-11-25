
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
 * <p>PackageElement complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PackageElement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Amount" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://webservices.micros.com/og/4.3/Common/}DescriptiveText" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ShortDescription" type="{http://webservices.micros.com/og/4.3/Common/}DescriptiveText" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PackageDetails" type="{http://webservices.micros.com/og/4.3/HotelCommon/}PackageDetailCharges" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="packageCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="calculationRule" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="postingRhythm" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="quantity" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="includedInRate" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="addRateSeprateLine" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="addRateCombinedLine" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="startTime" type="{http://www.w3.org/2001/XMLSchema}time" />
 *       &lt;attribute name="endTime" type="{http://www.w3.org/2001/XMLSchema}time" />
 *       &lt;attribute name="sellSeparate" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="totalDepositAmount" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageElement", propOrder = {
    "amount",
    "description",
    "shortDescription",
    "packageDetails",
    "startDate",
    "endDate"
})
public class PackageElement {

    @XmlElement(name = "Amount")
    protected Amount amount;
    @XmlElement(name = "Description")
    protected List<DescriptiveText> description;
    @XmlElement(name = "ShortDescription")
    protected List<DescriptiveText> shortDescription;
    @XmlElement(name = "PackageDetails")
    protected List<PackageDetailCharges> packageDetails;
    @XmlElement(name = "StartDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "EndDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;
    @XmlAttribute(name = "packageCode", required = true)
    protected String packageCode;
    @XmlAttribute(name = "calculationRule")
    protected String calculationRule;
    @XmlAttribute(name = "postingRhythm")
    protected String postingRhythm;
    @XmlAttribute(name = "quantity")
    protected Integer quantity;
    @XmlAttribute(name = "includedInRate")
    protected Boolean includedInRate;
    @XmlAttribute(name = "addRateSeprateLine")
    protected Boolean addRateSeprateLine;
    @XmlAttribute(name = "addRateCombinedLine")
    protected Boolean addRateCombinedLine;
    @XmlAttribute(name = "startTime")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar startTime;
    @XmlAttribute(name = "endTime")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar endTime;
    @XmlAttribute(name = "sellSeparate")
    protected Boolean sellSeparate;
    @XmlAttribute(name = "totalDepositAmount")
    protected Double totalDepositAmount;

    /**
     * 获取amount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * 设置amount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setAmount(Amount value) {
        this.amount = value;
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
     * Gets the value of the shortDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shortDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShortDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptiveText }
     * 
     * 
     */
    public List<DescriptiveText> getShortDescription() {
        if (shortDescription == null) {
            shortDescription = new ArrayList<DescriptiveText>();
        }
        return this.shortDescription;
    }

    /**
     * Gets the value of the packageDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packageDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackageDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageDetailCharges }
     * 
     * 
     */
    public List<PackageDetailCharges> getPackageDetails() {
        if (packageDetails == null) {
            packageDetails = new ArrayList<PackageDetailCharges>();
        }
        return this.packageDetails;
    }

    /**
     * 获取startDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * 设置startDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * 获取endDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * 设置endDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * 获取packageCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageCode() {
        return packageCode;
    }

    /**
     * 设置packageCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageCode(String value) {
        this.packageCode = value;
    }

    /**
     * 获取calculationRule属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalculationRule() {
        return calculationRule;
    }

    /**
     * 设置calculationRule属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalculationRule(String value) {
        this.calculationRule = value;
    }

    /**
     * 获取postingRhythm属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostingRhythm() {
        return postingRhythm;
    }

    /**
     * 设置postingRhythm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostingRhythm(String value) {
        this.postingRhythm = value;
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
     * 获取includedInRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludedInRate() {
        return includedInRate;
    }

    /**
     * 设置includedInRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludedInRate(Boolean value) {
        this.includedInRate = value;
    }

    /**
     * 获取addRateSeprateLine属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAddRateSeprateLine() {
        return addRateSeprateLine;
    }

    /**
     * 设置addRateSeprateLine属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAddRateSeprateLine(Boolean value) {
        this.addRateSeprateLine = value;
    }

    /**
     * 获取addRateCombinedLine属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAddRateCombinedLine() {
        return addRateCombinedLine;
    }

    /**
     * 设置addRateCombinedLine属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAddRateCombinedLine(Boolean value) {
        this.addRateCombinedLine = value;
    }

    /**
     * 获取startTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * 设置startTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartTime(XMLGregorianCalendar value) {
        this.startTime = value;
    }

    /**
     * 获取endTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * 设置endTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
    }

    /**
     * 获取sellSeparate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSellSeparate() {
        return sellSeparate;
    }

    /**
     * 设置sellSeparate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSellSeparate(Boolean value) {
        this.sellSeparate = value;
    }

    /**
     * 获取totalDepositAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalDepositAmount() {
        return totalDepositAmount;
    }

    /**
     * 设置totalDepositAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalDepositAmount(Double value) {
        this.totalDepositAmount = value;
    }

}
