
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>NameIdNameAddress complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="NameIdNameAddress">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="ProfileId" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID"/>
 *         &lt;element name="Name" type="{http://webservices.micros.com/og/4.3/Name/}NativeName"/>
 *         &lt;element name="Address" type="{http://webservices.micros.com/og/4.3/Name/}NameAddress" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NameIdNameAddress", namespace = "http://webservices.micros.com/og/4.3/Name/", propOrder = {

})
public class NameIdNameAddress {

    @XmlElement(name = "ProfileId", required = true)
    protected UniqueID profileId;
    @XmlElement(name = "Name", required = true)
    protected NativeName name;
    @XmlElement(name = "Address")
    protected NameAddress address;

    /**
     * 获取profileId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getProfileId() {
        return profileId;
    }

    /**
     * 设置profileId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setProfileId(UniqueID value) {
        this.profileId = value;
    }

    /**
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NativeName }
     *     
     */
    public NativeName getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NativeName }
     *     
     */
    public void setName(NativeName value) {
        this.name = value;
    }

    /**
     * 获取address属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NameAddress }
     *     
     */
    public NameAddress getAddress() {
        return address;
    }

    /**
     * 设置address属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NameAddress }
     *     
     */
    public void setAddress(NameAddress value) {
        this.address = value;
    }

}
