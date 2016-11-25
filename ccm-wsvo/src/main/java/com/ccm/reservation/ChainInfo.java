
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Basic contact information for Hotel Property.
 * 
 * <p>ChainInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ChainInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Addresses" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AddressList" minOccurs="0"/>
 *         &lt;element name="ContactEmails" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ContactEmailList" minOccurs="0"/>
 *         &lt;element name="ContactPhones" type="{http://webservices.micros.com/og/4.3/HotelCommon/}PhoneList" minOccurs="0"/>
 *         &lt;element name="MarketingText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LoyaltyProgram" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BookingConditions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AcceptFrequenFlyerCard" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChainInfo", propOrder = {
    "addresses",
    "contactEmails",
    "contactPhones",
    "marketingText",
    "loyaltyProgram",
    "bookingConditions",
    "acceptFrequenFlyerCard"
})
public class ChainInfo {

    @XmlElement(name = "Addresses")
    protected AddressList addresses;
    @XmlElement(name = "ContactEmails")
    protected ContactEmailList contactEmails;
    @XmlElement(name = "ContactPhones")
    protected PhoneList contactPhones;
    @XmlElement(name = "MarketingText", required = true)
    protected String marketingText;
    @XmlElement(name = "LoyaltyProgram", required = true)
    protected String loyaltyProgram;
    @XmlElement(name = "BookingConditions", required = true)
    protected String bookingConditions;
    @XmlElement(name = "AcceptFrequenFlyerCard", required = true)
    protected String acceptFrequenFlyerCard;

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
     * 获取marketingText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketingText() {
        return marketingText;
    }

    /**
     * 设置marketingText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketingText(String value) {
        this.marketingText = value;
    }

    /**
     * 获取loyaltyProgram属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoyaltyProgram() {
        return loyaltyProgram;
    }

    /**
     * 设置loyaltyProgram属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoyaltyProgram(String value) {
        this.loyaltyProgram = value;
    }

    /**
     * 获取bookingConditions属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingConditions() {
        return bookingConditions;
    }

    /**
     * 设置bookingConditions属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingConditions(String value) {
        this.bookingConditions = value;
    }

    /**
     * 获取acceptFrequenFlyerCard属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptFrequenFlyerCard() {
        return acceptFrequenFlyerCard;
    }

    /**
     * 设置acceptFrequenFlyerCard属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptFrequenFlyerCard(String value) {
        this.acceptFrequenFlyerCard = value;
    }

}
