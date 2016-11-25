
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Phone complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Phone">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="PhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PhoneData" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="countryAccessCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="areaCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="extension" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *       &lt;attribute name="phoneType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="phoneRole" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Phone", namespace = "http://webservices.micros.com/og/4.3/Common/", propOrder = {
    "phoneNumber",
    "phoneData"
})
@XmlSeeAlso({
    NamePhone.class
})
public class Phone {

    @XmlElement(name = "PhoneNumber")
    protected String phoneNumber;
    @XmlElement(name = "PhoneData")
    protected Phone.PhoneData phoneData;
    @XmlAttribute(name = "phoneType")
    protected String phoneType;
    @XmlAttribute(name = "phoneRole")
    protected String phoneRole;

    /**
     * 获取phoneNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置phoneNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * 获取phoneData属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Phone.PhoneData }
     *     
     */
    public Phone.PhoneData getPhoneData() {
        return phoneData;
    }

    /**
     * 设置phoneData属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Phone.PhoneData }
     *     
     */
    public void setPhoneData(Phone.PhoneData value) {
        this.phoneData = value;
    }

    /**
     * 获取phoneType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneType() {
        return phoneType;
    }

    /**
     * 设置phoneType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneType(String value) {
        this.phoneType = value;
    }

    /**
     * 获取phoneRole属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneRole() {
        return phoneRole;
    }

    /**
     * 设置phoneRole属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneRole(String value) {
        this.phoneRole = value;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="countryAccessCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="areaCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="extension" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class PhoneData {

        @XmlAttribute(name = "countryAccessCode")
        protected String countryAccessCode;
        @XmlAttribute(name = "areaCode")
        protected String areaCode;
        @XmlAttribute(name = "phoneNumber")
        protected String phoneNumber;
        @XmlAttribute(name = "extension")
        protected String extension;

        /**
         * 获取countryAccessCode属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCountryAccessCode() {
            return countryAccessCode;
        }

        /**
         * 设置countryAccessCode属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCountryAccessCode(String value) {
            this.countryAccessCode = value;
        }

        /**
         * 获取areaCode属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAreaCode() {
            return areaCode;
        }

        /**
         * 设置areaCode属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAreaCode(String value) {
            this.areaCode = value;
        }

        /**
         * 获取phoneNumber属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPhoneNumber() {
            return phoneNumber;
        }

        /**
         * 设置phoneNumber属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPhoneNumber(String value) {
            this.phoneNumber = value;
        }

        /**
         * 获取extension属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getExtension() {
            return extension;
        }

        /**
         * 设置extension属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setExtension(String value) {
            this.extension = value;
        }

    }

}
