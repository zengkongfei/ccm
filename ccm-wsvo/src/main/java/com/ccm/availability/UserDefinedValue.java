
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>UserDefinedValue complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="UserDefinedValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="CharacterValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DateValue" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="NumericValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/choice>
 *       &lt;attribute name="valueName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserDefinedValue", namespace = "http://webservices.micros.com/og/4.3/Common/", propOrder = {
    "characterValue",
    "dateValue",
    "numericValue"
})
public class UserDefinedValue {

    @XmlElement(name = "CharacterValue")
    protected String characterValue;
    @XmlElement(name = "DateValue")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateValue;
    @XmlElement(name = "NumericValue")
    protected Float numericValue;
    @XmlAttribute(name = "valueName")
    protected String valueName;

    /**
     * 获取characterValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharacterValue() {
        return characterValue;
    }

    /**
     * 设置characterValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharacterValue(String value) {
        this.characterValue = value;
    }

    /**
     * 获取dateValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateValue() {
        return dateValue;
    }

    /**
     * 设置dateValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateValue(XMLGregorianCalendar value) {
        this.dateValue = value;
    }

    /**
     * 获取numericValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getNumericValue() {
        return numericValue;
    }

    /**
     * 设置numericValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setNumericValue(Float value) {
        this.numericValue = value;
    }

    /**
     * 获取valueName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueName() {
        return valueName;
    }

    /**
     * 设置valueName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueName(String value) {
        this.valueName = value;
    }

}
