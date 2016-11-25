
package com.ccm.webservices.og.name;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ccm.webservices.og.common.Gender;
import com.ccm.webservices.og.common.GovernmentIDList;
import com.ccm.webservices.og.common.PersonName;


/**
 * <p>Customer complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Customer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PersonName" type="{http://webservices.micros.com/og/4.3/Common/}PersonName"/>
 *         &lt;element name="NativeName" type="{http://webservices.micros.com/og/4.3/Name/}NativeName" minOccurs="0"/>
 *         &lt;element name="BusinessTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GovernmentIDList" type="{http://webservices.micros.com/og/4.3/Common/}GovernmentIDList" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="gender" type="{http://webservices.micros.com/og/4.3/Common/}Gender" />
 *       &lt;attribute name="birthDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Customer", propOrder = {
    "personName",
    "nativeName",
    "businessTitle",
    "governmentIDList"
})
public class Customer {

    @XmlElement(name = "PersonName", required = true)
    protected PersonName personName;
    @XmlElement(name = "NativeName")
    protected NativeName nativeName;
    @XmlElement(name = "BusinessTitle")
    protected String businessTitle;
    @XmlElement(name = "GovernmentIDList")
    protected GovernmentIDList governmentIDList;
    @XmlAttribute(name = "gender")
    protected Gender gender;
    @XmlAttribute(name = "birthDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthDate;

    /**
     * 获取personName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PersonName }
     *     
     */
    public PersonName getPersonName() {
        return personName;
    }

    /**
     * 设置personName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PersonName }
     *     
     */
    public void setPersonName(PersonName value) {
        this.personName = value;
    }

    /**
     * 获取nativeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NativeName }
     *     
     */
    public NativeName getNativeName() {
        return nativeName;
    }

    /**
     * 设置nativeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NativeName }
     *     
     */
    public void setNativeName(NativeName value) {
        this.nativeName = value;
    }

    /**
     * 获取businessTitle属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessTitle() {
        return businessTitle;
    }

    /**
     * 设置businessTitle属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessTitle(String value) {
        this.businessTitle = value;
    }

    /**
     * 获取governmentIDList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GovernmentIDList }
     *     
     */
    public GovernmentIDList getGovernmentIDList() {
        return governmentIDList;
    }

    /**
     * 设置governmentIDList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GovernmentIDList }
     *     
     */
    public void setGovernmentIDList(GovernmentIDList value) {
        this.governmentIDList = value;
    }

    /**
     * 获取gender属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Gender }
     *     
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * 设置gender属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Gender }
     *     
     */
    public void setGender(Gender value) {
        this.gender = value;
    }

    /**
     * 获取birthDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthDate() {
        return birthDate;
    }

    /**
     * 设置birthDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthDate(XMLGregorianCalendar value) {
        this.birthDate = value;
    }

}
