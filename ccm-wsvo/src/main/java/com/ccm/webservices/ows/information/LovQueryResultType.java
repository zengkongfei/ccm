
package com.ccm.webservices.ows.information;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>LovQueryResultType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="LovQueryResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="LovValue" type="{http://webservices.micros.com/ows/5.1/Information.wsdl}LovValueType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="qualifierType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="qualifierValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="secondaryQualifierType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="secondaryQualifierValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="tertiaryQualifierType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="tertiaryQualifierValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LovQueryResultType", propOrder = {
    "lovValue"
})
public class LovQueryResultType {

    @XmlElement(name = "LovValue")
    protected List<LovValueType> lovValue;
    @XmlAttribute(name = "qualifierType")
    protected String qualifierType;
    @XmlAttribute(name = "qualifierValue")
    protected String qualifierValue;
    @XmlAttribute(name = "secondaryQualifierType")
    protected String secondaryQualifierType;
    @XmlAttribute(name = "secondaryQualifierValue")
    protected String secondaryQualifierValue;
    @XmlAttribute(name = "tertiaryQualifierType")
    protected String tertiaryQualifierType;
    @XmlAttribute(name = "tertiaryQualifierValue")
    protected String tertiaryQualifierValue;

    /**
     * Gets the value of the lovValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lovValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLovValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LovValueType }
     * 
     * 
     */
    public List<LovValueType> getLovValue() {
        if (lovValue == null) {
            lovValue = new ArrayList<LovValueType>();
        }
        return this.lovValue;
    }

    /**
     * 获取qualifierType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualifierType() {
        return qualifierType;
    }

    /**
     * 设置qualifierType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualifierType(String value) {
        this.qualifierType = value;
    }

    /**
     * 获取qualifierValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualifierValue() {
        return qualifierValue;
    }

    /**
     * 设置qualifierValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualifierValue(String value) {
        this.qualifierValue = value;
    }

    /**
     * 获取secondaryQualifierType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondaryQualifierType() {
        return secondaryQualifierType;
    }

    /**
     * 设置secondaryQualifierType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondaryQualifierType(String value) {
        this.secondaryQualifierType = value;
    }

    /**
     * 获取secondaryQualifierValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondaryQualifierValue() {
        return secondaryQualifierValue;
    }

    /**
     * 设置secondaryQualifierValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondaryQualifierValue(String value) {
        this.secondaryQualifierValue = value;
    }

    /**
     * 获取tertiaryQualifierType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTertiaryQualifierType() {
        return tertiaryQualifierType;
    }

    /**
     * 设置tertiaryQualifierType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTertiaryQualifierType(String value) {
        this.tertiaryQualifierType = value;
    }

    /**
     * 获取tertiaryQualifierValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTertiaryQualifierValue() {
        return tertiaryQualifierValue;
    }

    /**
     * 设置tertiaryQualifierValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTertiaryQualifierValue(String value) {
        this.tertiaryQualifierValue = value;
    }

}
