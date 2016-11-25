
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Preference complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Preference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PreferenceDescription" type="{http://webservices.micros.com/og/4.3/Common/}DescriptiveText" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://webservices.micros.com/og/4.3/Common/}RecordAdministratorAttributes"/>
 *       &lt;attribute name="resortCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="preferenceType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="otherPreferenceType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="preferenceValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Preference", namespace = "http://webservices.micros.com/og/4.3/Name/", propOrder = {
    "preferenceDescription"
})
public class Preference {

    @XmlElement(name = "PreferenceDescription")
    protected DescriptiveText preferenceDescription;
    @XmlAttribute(name = "resortCode")
    protected String resortCode;
    @XmlAttribute(name = "preferenceType")
    protected String preferenceType;
    @XmlAttribute(name = "otherPreferenceType")
    protected String otherPreferenceType;
    @XmlAttribute(name = "preferenceValue")
    protected String preferenceValue;
    @XmlAttribute(name = "insertUser")
    protected String insertUser;
    @XmlAttribute(name = "insertDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar insertDate;
    @XmlAttribute(name = "updateUser")
    protected String updateUser;
    @XmlAttribute(name = "updateDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateDate;
    @XmlAttribute(name = "inactiveDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar inactiveDate;

    /**
     * 获取preferenceDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DescriptiveText }
     *     
     */
    public DescriptiveText getPreferenceDescription() {
        return preferenceDescription;
    }

    /**
     * 设置preferenceDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DescriptiveText }
     *     
     */
    public void setPreferenceDescription(DescriptiveText value) {
        this.preferenceDescription = value;
    }

    /**
     * 获取resortCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResortCode() {
        return resortCode;
    }

    /**
     * 设置resortCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResortCode(String value) {
        this.resortCode = value;
    }

    /**
     * 获取preferenceType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferenceType() {
        return preferenceType;
    }

    /**
     * 设置preferenceType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferenceType(String value) {
        this.preferenceType = value;
    }

    /**
     * 获取otherPreferenceType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherPreferenceType() {
        return otherPreferenceType;
    }

    /**
     * 设置otherPreferenceType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherPreferenceType(String value) {
        this.otherPreferenceType = value;
    }

    /**
     * 获取preferenceValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferenceValue() {
        return preferenceValue;
    }

    /**
     * 设置preferenceValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferenceValue(String value) {
        this.preferenceValue = value;
    }

    /**
     * 获取insertUser属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsertUser() {
        return insertUser;
    }

    /**
     * 设置insertUser属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsertUser(String value) {
        this.insertUser = value;
    }

    /**
     * 获取insertDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInsertDate() {
        return insertDate;
    }

    /**
     * 设置insertDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInsertDate(XMLGregorianCalendar value) {
        this.insertDate = value;
    }

    /**
     * 获取updateUser属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置updateUser属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateUser(String value) {
        this.updateUser = value;
    }

    /**
     * 获取updateDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置updateDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateDate(XMLGregorianCalendar value) {
        this.updateDate = value;
    }

    /**
     * 获取inactiveDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInactiveDate() {
        return inactiveDate;
    }

    /**
     * 设置inactiveDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInactiveDate(XMLGregorianCalendar value) {
        this.inactiveDate = value;
    }

}
