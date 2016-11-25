
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Basic contact information for Hotel Property.
 * 
 * <p>HotelContact complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="HotelContact">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Addresses" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AddressList" minOccurs="0"/>
 *         &lt;element name="ContactEmails" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ContactEmailList" minOccurs="0"/>
 *         &lt;element name="ContactPhones" type="{http://webservices.micros.com/og/4.3/HotelCommon/}PhoneList" minOccurs="0"/>
 *         &lt;element name="HotelInformation" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelInfoList" minOccurs="0"/>
 *         &lt;element name="URIs" type="{http://webservices.micros.com/og/4.3/HotelCommon/}URIList" minOccurs="0"/>
 *         &lt;element name="Vector" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Vector" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HotelContact", propOrder = {
    "addresses",
    "contactEmails",
    "contactPhones",
    "hotelInformation",
    "urIs",
    "vector"
})
public class HotelContact {

    @XmlElement(name = "Addresses")
    protected AddressList addresses;
    @XmlElement(name = "ContactEmails")
    protected ContactEmailList contactEmails;
    @XmlElement(name = "ContactPhones")
    protected PhoneList contactPhones;
    @XmlElement(name = "HotelInformation")
    protected HotelInfoList hotelInformation;
    @XmlElement(name = "URIs")
    protected URIList urIs;
    @XmlElement(name = "Vector")
    protected Vector vector;

    /**
     * 获取addresses属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AddressList }
     *     
     */
    public AddressList getAddresses() {
        return addresses;
    }

    /**
     * 设置addresses属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AddressList }
     *     
     */
    public void setAddresses(AddressList value) {
        this.addresses = value;
    }

    /**
     * 获取contactEmails属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ContactEmailList }
     *     
     */
    public ContactEmailList getContactEmails() {
        return contactEmails;
    }

    /**
     * 设置contactEmails属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ContactEmailList }
     *     
     */
    public void setContactEmails(ContactEmailList value) {
        this.contactEmails = value;
    }

    /**
     * 获取contactPhones属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PhoneList }
     *     
     */
    public PhoneList getContactPhones() {
        return contactPhones;
    }

    /**
     * 设置contactPhones属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PhoneList }
     *     
     */
    public void setContactPhones(PhoneList value) {
        this.contactPhones = value;
    }

    /**
     * 获取hotelInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelInfoList }
     *     
     */
    public HotelInfoList getHotelInformation() {
        return hotelInformation;
    }

    /**
     * 设置hotelInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelInfoList }
     *     
     */
    public void setHotelInformation(HotelInfoList value) {
        this.hotelInformation = value;
    }

    /**
     * 获取urIs属性的值。
     * 
     * @return
     *     possible object is
     *     {@link URIList }
     *     
     */
    public URIList getURIs() {
        return urIs;
    }

    /**
     * 设置urIs属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link URIList }
     *     
     */
    public void setURIs(URIList value) {
        this.urIs = value;
    }

    /**
     * 获取vector属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Vector }
     *     
     */
    public Vector getVector() {
        return vector;
    }

    /**
     * 设置vector属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Vector }
     *     
     */
    public void setVector(Vector value) {
        this.vector = value;
    }

}
