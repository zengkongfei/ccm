//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.10 at 03:58:32 下午 CST 
//


package com.ccm.oxi.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Describe complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Describe">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resortName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="insertUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updateUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roomCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rateCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="marketCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="guarantee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="membership" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="membershipLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="discountReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Describe", propOrder = {
    "resortName",
    "insertUser",
    "updateUser",
    "roomCategory",
    "rateCode",
    "marketCode",
    "guarantee",
    "membership",
    "membershipLevel",
    "company",
    "discountReason"
})
public class Describe {

    protected String resortName;
    protected String insertUser;
    protected String updateUser;
    protected String roomCategory;
    protected String rateCode;
    protected String marketCode;
    protected String guarantee;
    protected String membership;
    protected String membershipLevel;
    protected String company;
    protected String discountReason;

    /**
     * Gets the value of the resortName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResortName() {
        return resortName;
    }

    /**
     * Sets the value of the resortName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResortName(String value) {
        this.resortName = value;
    }

    /**
     * Gets the value of the insertUser property.
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
     * Sets the value of the insertUser property.
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
     * Gets the value of the updateUser property.
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
     * Sets the value of the updateUser property.
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
     * Gets the value of the roomCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomCategory() {
        return roomCategory;
    }

    /**
     * Sets the value of the roomCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomCategory(String value) {
        this.roomCategory = value;
    }

    /**
     * Gets the value of the rateCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateCode() {
        return rateCode;
    }

    /**
     * Sets the value of the rateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateCode(String value) {
        this.rateCode = value;
    }

    /**
     * Gets the value of the marketCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketCode() {
        return marketCode;
    }

    /**
     * Sets the value of the marketCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketCode(String value) {
        this.marketCode = value;
    }

    /**
     * Gets the value of the guarantee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantee() {
        return guarantee;
    }

    /**
     * Sets the value of the guarantee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantee(String value) {
        this.guarantee = value;
    }

    /**
     * Gets the value of the membership property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMembership() {
        return membership;
    }

    /**
     * Sets the value of the membership property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMembership(String value) {
        this.membership = value;
    }

    /**
     * Gets the value of the membershipLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMembershipLevel() {
        return membershipLevel;
    }

    /**
     * Sets the value of the membershipLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMembershipLevel(String value) {
        this.membershipLevel = value;
    }

    /**
     * Gets the value of the company property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the value of the company property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompany(String value) {
        this.company = value;
    }

    /**
     * Gets the value of the discountReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountReason() {
        return discountReason;
    }

    /**
     * Sets the value of the discountReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountReason(String value) {
        this.discountReason = value;
    }

}
