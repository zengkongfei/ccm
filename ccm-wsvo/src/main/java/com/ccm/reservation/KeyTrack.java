
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>KeyTrack complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="KeyTrack">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Key1Track" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Key2Track" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Key3Track" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Key4Track" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KeyTrack", namespace = "http://webservices.micros.com/og/4.3/Common/")
public class KeyTrack {

    @XmlAttribute(name = "Key1Track")
    protected String key1Track;
    @XmlAttribute(name = "Key2Track")
    protected String key2Track;
    @XmlAttribute(name = "Key3Track")
    protected String key3Track;
    @XmlAttribute(name = "Key4Track")
    protected String key4Track;

    /**
     * 获取key1Track属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey1Track() {
        return key1Track;
    }

    /**
     * 设置key1Track属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey1Track(String value) {
        this.key1Track = value;
    }

    /**
     * 获取key2Track属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey2Track() {
        return key2Track;
    }

    /**
     * 设置key2Track属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey2Track(String value) {
        this.key2Track = value;
    }

    /**
     * 获取key3Track属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey3Track() {
        return key3Track;
    }

    /**
     * 设置key3Track属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey3Track(String value) {
        this.key3Track = value;
    }

    /**
     * 获取key4Track属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey4Track() {
        return key4Track;
    }

    /**
     * 设置key4Track属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey4Track(String value) {
        this.key4Track = value;
    }

}
