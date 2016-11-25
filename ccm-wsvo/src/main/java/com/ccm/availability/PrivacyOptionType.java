
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PrivacyOptionType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PrivacyOptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="OptionType" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Promotions"/>
 *             &lt;enumeration value="MarketResearch"/>
 *             &lt;enumeration value="ThirdParties"/>
 *             &lt;enumeration value="LoyaltyProgram"/>
 *             &lt;enumeration value="Privacy"/>
 *             &lt;enumeration value="Email"/>
 *             &lt;enumeration value="Mail"/>
 *             &lt;enumeration value="Phone"/>
 *             &lt;enumeration value="SMS"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="OptionValue" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="YES"/>
 *             &lt;enumeration value="NO"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrivacyOptionType", namespace = "http://webservices.micros.com/og/4.3/Name/")
public class PrivacyOptionType {

    @XmlAttribute(name = "OptionType", required = true)
    protected String optionType;
    @XmlAttribute(name = "OptionValue", required = true)
    protected String optionValue;

    /**
     * 获取optionType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptionType() {
        return optionType;
    }

    /**
     * 设置optionType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptionType(String value) {
        this.optionType = value;
    }

    /**
     * 获取optionValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptionValue() {
        return optionValue;
    }

    /**
     * 设置optionValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptionValue(String value) {
        this.optionValue = value;
    }

}
