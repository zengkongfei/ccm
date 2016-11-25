
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Profile complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Profile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProfileIDs" type="{http://webservices.micros.com/og/4.3/Common/}UniqueIDList" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="Customer" type="{http://webservices.micros.com/og/4.3/Name/}Customer"/>
 *           &lt;element name="Company" type="{http://webservices.micros.com/og/4.3/Name/}Company"/>
 *         &lt;/choice>
 *         &lt;element name="CreditCards" type="{http://webservices.micros.com/og/4.3/Name/}NameCreditCardList" minOccurs="0"/>
 *         &lt;element name="Addresses" type="{http://webservices.micros.com/og/4.3/Name/}NameAddressList" minOccurs="0"/>
 *         &lt;element name="Blacklist" type="{http://webservices.micros.com/og/4.3/Name/}BlackList" minOccurs="0"/>
 *         &lt;element name="Phones" type="{http://webservices.micros.com/og/4.3/Name/}NamePhoneList" minOccurs="0"/>
 *         &lt;element name="EMails" type="{http://webservices.micros.com/og/4.3/Name/}NameEmailList" minOccurs="0"/>
 *         &lt;element name="Preferences" type="{http://webservices.micros.com/og/4.3/Name/}PreferenceList" minOccurs="0"/>
 *         &lt;element name="Memberships" type="{http://webservices.micros.com/og/4.3/Name/}NameMembershipList" minOccurs="0"/>
 *         &lt;element name="NegotiatedRates" type="{http://webservices.micros.com/og/4.3/Name/}NegotiatedRateList" minOccurs="0"/>
 *         &lt;element name="Comments" type="{http://webservices.micros.com/og/4.3/Name/}CommentList" minOccurs="0"/>
 *         &lt;element name="UserDefinedValues" type="{http://webservices.micros.com/og/4.3/Common/}UserDefinedValueList" minOccurs="0"/>
 *         &lt;element name="Privacy" type="{http://webservices.micros.com/og/4.3/Name/}PrivacyList" minOccurs="0"/>
 *         &lt;element name="UserGroup" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="groupType" use="required" type="{http://webservices.micros.com/og/4.3/Name/}UserGroupType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="StayHistory" type="{http://webservices.micros.com/og/4.3/Name/}StayHistoryData" minOccurs="0"/>
 *         &lt;element name="Id" type="{http://webservices.micros.com/og/4.3/Common/}GovernmentID" minOccurs="0"/>
 *         &lt;element name="KeyWord" type="{http://webservices.micros.com/og/4.3/Name/}KeyWordList" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://webservices.micros.com/og/4.3/Common/}RecordAdministratorAttributes"/>
 *       &lt;attribute name="nameType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="protected" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="languageCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="nationality" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="vipCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="taxExempt" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Profile", namespace = "http://webservices.micros.com/og/4.3/Name/", propOrder = {
    "profileIDs",
    "customer",
    "company",
    "creditCards",
    "addresses",
    "blacklist",
    "phones",
    "eMails",
    "preferences",
    "memberships",
    "negotiatedRates",
    "comments",
    "userDefinedValues",
    "privacy",
    "userGroup",
    "stayHistory",
    "id",
    "keyWord"
})
public class Profile {

    @XmlElement(name = "ProfileIDs")
    protected UniqueIDList profileIDs;
    @XmlElement(name = "Customer")
    protected Customer customer;
    @XmlElement(name = "Company")
    protected Company company;
    @XmlElement(name = "CreditCards")
    protected NameCreditCardList creditCards;
    @XmlElement(name = "Addresses")
    protected NameAddressList addresses;
    @XmlElement(name = "Blacklist")
    protected BlackList blacklist;
    @XmlElement(name = "Phones")
    protected NamePhoneList phones;
    @XmlElement(name = "EMails")
    protected NameEmailList eMails;
    @XmlElement(name = "Preferences")
    protected PreferenceList preferences;
    @XmlElement(name = "Memberships")
    protected NameMembershipList memberships;
    @XmlElement(name = "NegotiatedRates")
    protected NegotiatedRateList negotiatedRates;
    @XmlElement(name = "Comments")
    protected CommentList comments;
    @XmlElement(name = "UserDefinedValues")
    protected UserDefinedValueList userDefinedValues;
    @XmlElement(name = "Privacy")
    protected PrivacyList privacy;
    @XmlElement(name = "UserGroup")
    protected Profile.UserGroup userGroup;
    @XmlElement(name = "StayHistory")
    protected StayHistoryData stayHistory;
    @XmlElement(name = "Id")
    protected GovernmentID id;
    @XmlElement(name = "KeyWord")
    protected KeyWordList keyWord;
    @XmlAttribute(name = "nameType")
    protected String nameType;
    @XmlAttribute(name = "protected")
    protected Boolean _protected;
    @XmlAttribute(name = "languageCode")
    protected String languageCode;
    @XmlAttribute(name = "nationality")
    protected String nationality;
    @XmlAttribute(name = "vipCode")
    protected String vipCode;
    @XmlAttribute(name = "taxExempt")
    protected Boolean taxExempt;
    @XmlAttribute(name = "insertUser")
    protected String insertUser;
    @XmlAttribute(name = "insertDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar insertDate;
    @XmlAttribute(name = "updateUser")
    protected String updateUser;
    @XmlAttribute(name = "updateDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateDate;
    @XmlAttribute(name = "inactiveDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar inactiveDate;

    /**
     * 获取profileIDs属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueIDList }
     *     
     */
    public UniqueIDList getProfileIDs() {
        return profileIDs;
    }

    /**
     * 设置profileIDs属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueIDList }
     *     
     */
    public void setProfileIDs(UniqueIDList value) {
        this.profileIDs = value;
    }

    /**
     * 获取customer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Customer }
     *     
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * 设置customer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Customer }
     *     
     */
    public void setCustomer(Customer value) {
        this.customer = value;
    }

    /**
     * 获取company属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Company }
     *     
     */
    public Company getCompany() {
        return company;
    }

    /**
     * 设置company属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Company }
     *     
     */
    public void setCompany(Company value) {
        this.company = value;
    }

    /**
     * 获取creditCards属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NameCreditCardList }
     *     
     */
    public NameCreditCardList getCreditCards() {
        return creditCards;
    }

    /**
     * 设置creditCards属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NameCreditCardList }
     *     
     */
    public void setCreditCards(NameCreditCardList value) {
        this.creditCards = value;
    }

    /**
     * 获取addresses属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NameAddressList }
     *     
     */
    public NameAddressList getAddresses() {
        return addresses;
    }

    /**
     * 设置addresses属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NameAddressList }
     *     
     */
    public void setAddresses(NameAddressList value) {
        this.addresses = value;
    }

    /**
     * 获取blacklist属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BlackList }
     *     
     */
    public BlackList getBlacklist() {
        return blacklist;
    }

    /**
     * 设置blacklist属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BlackList }
     *     
     */
    public void setBlacklist(BlackList value) {
        this.blacklist = value;
    }

    /**
     * 获取phones属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NamePhoneList }
     *     
     */
    public NamePhoneList getPhones() {
        return phones;
    }

    /**
     * 设置phones属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NamePhoneList }
     *     
     */
    public void setPhones(NamePhoneList value) {
        this.phones = value;
    }

    /**
     * 获取eMails属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NameEmailList }
     *     
     */
    public NameEmailList getEMails() {
        return eMails;
    }

    /**
     * 设置eMails属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NameEmailList }
     *     
     */
    public void setEMails(NameEmailList value) {
        this.eMails = value;
    }

    /**
     * 获取preferences属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PreferenceList }
     *     
     */
    public PreferenceList getPreferences() {
        return preferences;
    }

    /**
     * 设置preferences属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PreferenceList }
     *     
     */
    public void setPreferences(PreferenceList value) {
        this.preferences = value;
    }

    /**
     * 获取memberships属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NameMembershipList }
     *     
     */
    public NameMembershipList getMemberships() {
        return memberships;
    }

    /**
     * 设置memberships属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NameMembershipList }
     *     
     */
    public void setMemberships(NameMembershipList value) {
        this.memberships = value;
    }

    /**
     * 获取negotiatedRates属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NegotiatedRateList }
     *     
     */
    public NegotiatedRateList getNegotiatedRates() {
        return negotiatedRates;
    }

    /**
     * 设置negotiatedRates属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NegotiatedRateList }
     *     
     */
    public void setNegotiatedRates(NegotiatedRateList value) {
        this.negotiatedRates = value;
    }

    /**
     * 获取comments属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CommentList }
     *     
     */
    public CommentList getComments() {
        return comments;
    }

    /**
     * 设置comments属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CommentList }
     *     
     */
    public void setComments(CommentList value) {
        this.comments = value;
    }

    /**
     * 获取userDefinedValues属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UserDefinedValueList }
     *     
     */
    public UserDefinedValueList getUserDefinedValues() {
        return userDefinedValues;
    }

    /**
     * 设置userDefinedValues属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UserDefinedValueList }
     *     
     */
    public void setUserDefinedValues(UserDefinedValueList value) {
        this.userDefinedValues = value;
    }

    /**
     * 获取privacy属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PrivacyList }
     *     
     */
    public PrivacyList getPrivacy() {
        return privacy;
    }

    /**
     * 设置privacy属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PrivacyList }
     *     
     */
    public void setPrivacy(PrivacyList value) {
        this.privacy = value;
    }

    /**
     * 获取userGroup属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Profile.UserGroup }
     *     
     */
    public Profile.UserGroup getUserGroup() {
        return userGroup;
    }

    /**
     * 设置userGroup属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Profile.UserGroup }
     *     
     */
    public void setUserGroup(Profile.UserGroup value) {
        this.userGroup = value;
    }

    /**
     * 获取stayHistory属性的值。
     * 
     * @return
     *     possible object is
     *     {@link StayHistoryData }
     *     
     */
    public StayHistoryData getStayHistory() {
        return stayHistory;
    }

    /**
     * 设置stayHistory属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link StayHistoryData }
     *     
     */
    public void setStayHistory(StayHistoryData value) {
        this.stayHistory = value;
    }

    /**
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GovernmentID }
     *     
     */
    public GovernmentID getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GovernmentID }
     *     
     */
    public void setId(GovernmentID value) {
        this.id = value;
    }

    /**
     * 获取keyWord属性的值。
     * 
     * @return
     *     possible object is
     *     {@link KeyWordList }
     *     
     */
    public KeyWordList getKeyWord() {
        return keyWord;
    }

    /**
     * 设置keyWord属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link KeyWordList }
     *     
     */
    public void setKeyWord(KeyWordList value) {
        this.keyWord = value;
    }

    /**
     * 获取nameType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameType() {
        return nameType;
    }

    /**
     * 设置nameType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameType(String value) {
        this.nameType = value;
    }

    /**
     * 获取protected属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isProtected() {
        return _protected;
    }

    /**
     * 设置protected属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setProtected(Boolean value) {
        this._protected = value;
    }

    /**
     * 获取languageCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * 设置languageCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageCode(String value) {
        this.languageCode = value;
    }

    /**
     * 获取nationality属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * 设置nationality属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationality(String value) {
        this.nationality = value;
    }

    /**
     * 获取vipCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVipCode() {
        return vipCode;
    }

    /**
     * 设置vipCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVipCode(String value) {
        this.vipCode = value;
    }

    /**
     * 获取taxExempt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTaxExempt() {
        return taxExempt;
    }

    /**
     * 设置taxExempt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTaxExempt(Boolean value) {
        this.taxExempt = value;
    }

    /**
     * 获取insertUser属性的值。
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
     * 设置insertUser属性的值。
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
     * 获取insertDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInsertDate() {
        return insertDate;
    }

    /**
     * 设置insertDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInsertDate(XMLGregorianCalendar value) {
        this.insertDate = value;
    }

    /**
     * 获取updateUser属性的值。
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
     * 设置updateUser属性的值。
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
     * 获取updateDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置updateDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateDate(XMLGregorianCalendar value) {
        this.updateDate = value;
    }

    /**
     * 获取inactiveDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInactiveDate() {
        return inactiveDate;
    }

    /**
     * 设置inactiveDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInactiveDate(XMLGregorianCalendar value) {
        this.inactiveDate = value;
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
     *       &lt;attribute name="groupType" use="required" type="{http://webservices.micros.com/og/4.3/Name/}UserGroupType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class UserGroup {

        @XmlAttribute(name = "groupType", required = true)
        protected UserGroupType groupType;

        /**
         * 获取groupType属性的值。
         * 
         * @return
         *     possible object is
         *     {@link UserGroupType }
         *     
         */
        public UserGroupType getGroupType() {
            return groupType;
        }

        /**
         * 设置groupType属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link UserGroupType }
         *     
         */
        public void setGroupType(UserGroupType value) {
            this.groupType = value;
        }

    }

}
