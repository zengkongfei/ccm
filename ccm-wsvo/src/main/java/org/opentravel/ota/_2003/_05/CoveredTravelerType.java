//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.14 at 01:53:08  GMT+08:00 
//


package org.opentravel.ota._2003._05;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Contains identifying information as well as coverage requirements and journey information for each individual requesting coverage by the insurance plan.
 * 
 * <p>Java class for CoveredTravelerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CoveredTravelerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CoveredPerson">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.opentravel.org/OTA/2003/05}PersonNameType">
 *                 &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}BirthDateGroup"/>
 *                 &lt;attribute name="Relation" use="required" type="{http://www.opentravel.org/OTA/2003/05}StringLength1to16" />
 *                 &lt;attribute name="Age" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Email" type="{http://www.opentravel.org/OTA/2003/05}EmailType" maxOccurs="5" minOccurs="0"/>
 *         &lt;element name="Address" type="{http://www.opentravel.org/OTA/2003/05}AddressInfoType" maxOccurs="5" minOccurs="0"/>
 *         &lt;element name="Telephone" maxOccurs="5" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}TelephoneInfoGroup"/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="CitizenCountryName" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}CitizenCountryNameGroup"/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Document" type="{http://www.opentravel.org/OTA/2003/05}DocumentType" maxOccurs="5" minOccurs="0"/>
 *         &lt;element name="EmergencyContact" type="{http://www.opentravel.org/OTA/2003/05}ContactPersonType" maxOccurs="5" minOccurs="0"/>
 *         &lt;element name="Beneficiary" maxOccurs="5" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Name">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;extension base="{http://www.opentravel.org/OTA/2003/05}PersonNameType">
 *                           &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}BirthDateGroup"/>
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Address" type="{http://www.opentravel.org/OTA/2003/05}AddressInfoType" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}ID_OptionalGroup"/>
 *                 &lt;attribute name="Relation" type="{http://www.opentravel.org/OTA/2003/05}StringLength1to16" />
 *                 &lt;attribute name="BenefitPercent" type="{http://www.opentravel.org/OTA/2003/05}Percentage" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="IndCoverageReqs" type="{http://www.opentravel.org/OTA/2003/05}IndCoverageReqsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RPH" type="{http://www.opentravel.org/OTA/2003/05}RPH_Type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoveredTravelerType", propOrder = {
    "coveredPerson",
    "email",
    "address",
    "telephone",
    "citizenCountryName",
    "document",
    "emergencyContact",
    "beneficiary",
    "indCoverageReqs"
})
public class CoveredTravelerType {

    @XmlElement(name = "CoveredPerson", required = true)
    protected CoveredTravelerType.CoveredPerson coveredPerson;
    @XmlElement(name = "Email")
    protected List<EmailType> email;
    @XmlElement(name = "Address")
    protected List<AddressInfoType> address;
    @XmlElement(name = "Telephone")
    protected List<CoveredTravelerType.Telephone> telephone;
    @XmlElement(name = "CitizenCountryName")
    protected CoveredTravelerType.CitizenCountryName citizenCountryName;
    @XmlElement(name = "Document")
    protected List<DocumentType> document;
    @XmlElement(name = "EmergencyContact")
    protected List<ContactPersonType> emergencyContact;
    @XmlElement(name = "Beneficiary")
    protected List<CoveredTravelerType.Beneficiary> beneficiary;
    @XmlElement(name = "IndCoverageReqs")
    protected IndCoverageReqsType indCoverageReqs;
    @XmlAttribute(name = "RPH")
    protected String rph;

    /**
     * Gets the value of the coveredPerson property.
     * 
     * @return
     *     possible object is
     *     {@link CoveredTravelerType.CoveredPerson }
     *     
     */
    public CoveredTravelerType.CoveredPerson getCoveredPerson() {
        return coveredPerson;
    }

    /**
     * Sets the value of the coveredPerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoveredTravelerType.CoveredPerson }
     *     
     */
    public void setCoveredPerson(CoveredTravelerType.CoveredPerson value) {
        this.coveredPerson = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the email property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmailType }
     * 
     * 
     */
    public List<EmailType> getEmail() {
        if (email == null) {
            email = new ArrayList<EmailType>();
        }
        return this.email;
    }

    /**
     * Gets the value of the address property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the address property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddressInfoType }
     * 
     * 
     */
    public List<AddressInfoType> getAddress() {
        if (address == null) {
            address = new ArrayList<AddressInfoType>();
        }
        return this.address;
    }

    /**
     * Gets the value of the telephone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the telephone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTelephone().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CoveredTravelerType.Telephone }
     * 
     * 
     */
    public List<CoveredTravelerType.Telephone> getTelephone() {
        if (telephone == null) {
            telephone = new ArrayList<CoveredTravelerType.Telephone>();
        }
        return this.telephone;
    }

    /**
     * Gets the value of the citizenCountryName property.
     * 
     * @return
     *     possible object is
     *     {@link CoveredTravelerType.CitizenCountryName }
     *     
     */
    public CoveredTravelerType.CitizenCountryName getCitizenCountryName() {
        return citizenCountryName;
    }

    /**
     * Sets the value of the citizenCountryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoveredTravelerType.CitizenCountryName }
     *     
     */
    public void setCitizenCountryName(CoveredTravelerType.CitizenCountryName value) {
        this.citizenCountryName = value;
    }

    /**
     * Gets the value of the document property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the document property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentType }
     * 
     * 
     */
    public List<DocumentType> getDocument() {
        if (document == null) {
            document = new ArrayList<DocumentType>();
        }
        return this.document;
    }

    /**
     * Gets the value of the emergencyContact property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the emergencyContact property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmergencyContact().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContactPersonType }
     * 
     * 
     */
    public List<ContactPersonType> getEmergencyContact() {
        if (emergencyContact == null) {
            emergencyContact = new ArrayList<ContactPersonType>();
        }
        return this.emergencyContact;
    }

    /**
     * Gets the value of the beneficiary property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the beneficiary property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBeneficiary().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CoveredTravelerType.Beneficiary }
     * 
     * 
     */
    public List<CoveredTravelerType.Beneficiary> getBeneficiary() {
        if (beneficiary == null) {
            beneficiary = new ArrayList<CoveredTravelerType.Beneficiary>();
        }
        return this.beneficiary;
    }

    /**
     * Gets the value of the indCoverageReqs property.
     * 
     * @return
     *     possible object is
     *     {@link IndCoverageReqsType }
     *     
     */
    public IndCoverageReqsType getIndCoverageReqs() {
        return indCoverageReqs;
    }

    /**
     * Sets the value of the indCoverageReqs property.
     * 
     * @param value
     *     allowed object is
     *     {@link IndCoverageReqsType }
     *     
     */
    public void setIndCoverageReqs(IndCoverageReqsType value) {
        this.indCoverageReqs = value;
    }

    /**
     * Gets the value of the rph property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRPH() {
        return rph;
    }

    /**
     * Sets the value of the rph property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRPH(String value) {
        this.rph = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Name">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;extension base="{http://www.opentravel.org/OTA/2003/05}PersonNameType">
     *                 &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}BirthDateGroup"/>
     *               &lt;/extension>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Address" type="{http://www.opentravel.org/OTA/2003/05}AddressInfoType" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}ID_OptionalGroup"/>
     *       &lt;attribute name="Relation" type="{http://www.opentravel.org/OTA/2003/05}StringLength1to16" />
     *       &lt;attribute name="BenefitPercent" type="{http://www.opentravel.org/OTA/2003/05}Percentage" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "name",
        "address"
    })
    public static class Beneficiary {

        @XmlElement(name = "Name", required = true)
        protected CoveredTravelerType.Beneficiary.Name name;
        @XmlElement(name = "Address")
        protected AddressInfoType address;
        @XmlAttribute(name = "Relation")
        protected String relation;
        @XmlAttribute(name = "BenefitPercent")
        protected BigDecimal benefitPercent;
        @XmlAttribute(name = "ID")
        protected String id;

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link CoveredTravelerType.Beneficiary.Name }
         *     
         */
        public CoveredTravelerType.Beneficiary.Name getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link CoveredTravelerType.Beneficiary.Name }
         *     
         */
        public void setName(CoveredTravelerType.Beneficiary.Name value) {
            this.name = value;
        }

        /**
         * Gets the value of the address property.
         * 
         * @return
         *     possible object is
         *     {@link AddressInfoType }
         *     
         */
        public AddressInfoType getAddress() {
            return address;
        }

        /**
         * Sets the value of the address property.
         * 
         * @param value
         *     allowed object is
         *     {@link AddressInfoType }
         *     
         */
        public void setAddress(AddressInfoType value) {
            this.address = value;
        }

        /**
         * Gets the value of the relation property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRelation() {
            return relation;
        }

        /**
         * Sets the value of the relation property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRelation(String value) {
            this.relation = value;
        }

        /**
         * Gets the value of the benefitPercent property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getBenefitPercent() {
            return benefitPercent;
        }

        /**
         * Sets the value of the benefitPercent property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setBenefitPercent(BigDecimal value) {
            this.benefitPercent = value;
        }

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getID() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setID(String value) {
            this.id = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;extension base="{http://www.opentravel.org/OTA/2003/05}PersonNameType">
         *       &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}BirthDateGroup"/>
         *     &lt;/extension>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Name
            extends PersonNameType
        {

            @XmlAttribute(name = "BirthDate")
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar birthDate;

            /**
             * Gets the value of the birthDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getBirthDate() {
                return birthDate;
            }

            /**
             * Sets the value of the birthDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setBirthDate(XMLGregorianCalendar value) {
                this.birthDate = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}CitizenCountryNameGroup"/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class CitizenCountryName {

        @XmlAttribute(name = "DefaultInd")
        protected Boolean defaultInd;
        @XmlAttribute(name = "Code")
        protected String code;

        /**
         * Gets the value of the defaultInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isDefaultInd() {
            return defaultInd;
        }

        /**
         * Sets the value of the defaultInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setDefaultInd(Boolean value) {
            this.defaultInd = value;
        }

        /**
         * Gets the value of the code property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCode() {
            return code;
        }

        /**
         * Sets the value of the code property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCode(String value) {
            this.code = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.opentravel.org/OTA/2003/05}PersonNameType">
     *       &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}BirthDateGroup"/>
     *       &lt;attribute name="Relation" use="required" type="{http://www.opentravel.org/OTA/2003/05}StringLength1to16" />
     *       &lt;attribute name="Age" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class CoveredPerson
        extends PersonNameType
    {

        @XmlAttribute(name = "Relation", required = true)
        protected String relation;
        @XmlAttribute(name = "Age")
        protected BigInteger age;
        @XmlAttribute(name = "BirthDate")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar birthDate;

        /**
         * Gets the value of the relation property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRelation() {
            return relation;
        }

        /**
         * Sets the value of the relation property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRelation(String value) {
            this.relation = value;
        }

        /**
         * Gets the value of the age property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getAge() {
            return age;
        }

        /**
         * Sets the value of the age property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setAge(BigInteger value) {
            this.age = value;
        }

        /**
         * Gets the value of the birthDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getBirthDate() {
            return birthDate;
        }

        /**
         * Sets the value of the birthDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setBirthDate(XMLGregorianCalendar value) {
            this.birthDate = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}TelephoneInfoGroup"/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Telephone {

        @XmlAttribute(name = "RPH")
        protected String rph;
        @XmlAttribute(name = "ShareSynchInd")
        protected String shareSynchInd;
        @XmlAttribute(name = "ShareMarketInd")
        protected String shareMarketInd;
        @XmlAttribute(name = "PhoneLocationType")
        protected String phoneLocationType;
        @XmlAttribute(name = "PhoneTechType")
        protected String phoneTechType;
        @XmlAttribute(name = "PhoneUseType")
        protected String phoneUseType;
        @XmlAttribute(name = "CountryAccessCode")
        protected String countryAccessCode;
        @XmlAttribute(name = "AreaCityCode")
        protected String areaCityCode;
        @XmlAttribute(name = "PhoneNumber", required = true)
        protected String phoneNumber;
        @XmlAttribute(name = "Extension")
        protected String extension;
        @XmlAttribute(name = "PIN")
        protected String pin;
        @XmlAttribute(name = "Remark")
        protected String remark;
        @XmlAttribute(name = "FormattedInd")
        protected Boolean formattedInd;
        @XmlAttribute(name = "DefaultInd")
        protected Boolean defaultInd;

        /**
         * Gets the value of the rph property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRPH() {
            return rph;
        }

        /**
         * Sets the value of the rph property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRPH(String value) {
            this.rph = value;
        }

        /**
         * Gets the value of the shareSynchInd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShareSynchInd() {
            return shareSynchInd;
        }

        /**
         * Sets the value of the shareSynchInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShareSynchInd(String value) {
            this.shareSynchInd = value;
        }

        /**
         * Gets the value of the shareMarketInd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShareMarketInd() {
            return shareMarketInd;
        }

        /**
         * Sets the value of the shareMarketInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShareMarketInd(String value) {
            this.shareMarketInd = value;
        }

        /**
         * Gets the value of the phoneLocationType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPhoneLocationType() {
            return phoneLocationType;
        }

        /**
         * Sets the value of the phoneLocationType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPhoneLocationType(String value) {
            this.phoneLocationType = value;
        }

        /**
         * Gets the value of the phoneTechType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPhoneTechType() {
            return phoneTechType;
        }

        /**
         * Sets the value of the phoneTechType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPhoneTechType(String value) {
            this.phoneTechType = value;
        }

        /**
         * Gets the value of the phoneUseType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPhoneUseType() {
            return phoneUseType;
        }

        /**
         * Sets the value of the phoneUseType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPhoneUseType(String value) {
            this.phoneUseType = value;
        }

        /**
         * Gets the value of the countryAccessCode property.
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
         * Sets the value of the countryAccessCode property.
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
         * Gets the value of the areaCityCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAreaCityCode() {
            return areaCityCode;
        }

        /**
         * Sets the value of the areaCityCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAreaCityCode(String value) {
            this.areaCityCode = value;
        }

        /**
         * Gets the value of the phoneNumber property.
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
         * Sets the value of the phoneNumber property.
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
         * Gets the value of the extension property.
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
         * Sets the value of the extension property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setExtension(String value) {
            this.extension = value;
        }

        /**
         * Gets the value of the pin property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPIN() {
            return pin;
        }

        /**
         * Sets the value of the pin property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPIN(String value) {
            this.pin = value;
        }

        /**
         * Gets the value of the remark property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRemark() {
            return remark;
        }

        /**
         * Sets the value of the remark property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRemark(String value) {
            this.remark = value;
        }

        /**
         * Gets the value of the formattedInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isFormattedInd() {
            return formattedInd;
        }

        /**
         * Sets the value of the formattedInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setFormattedInd(Boolean value) {
            this.formattedInd = value;
        }

        /**
         * Gets the value of the defaultInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isDefaultInd() {
            return defaultInd;
        }

        /**
         * Sets the value of the defaultInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setDefaultInd(Boolean value) {
            this.defaultInd = value;
        }

    }

}