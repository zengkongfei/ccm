
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QueryDateRange" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan">
 *                 &lt;attribute name="dateType" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;enumeration value="BOOKED_DATE"/>
 *                       &lt;enumeration value="ARRIVAL_DATE"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreditCardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AdditionalFilters" type="{http://webservices.micros.com/og/4.3/Reservation/}FetchBookingFilters"/>
 *         &lt;element name="EncryptedCreditCardSwipe" type="{http://webservices.micros.com/og/4.3/Common/}EncryptedSwipe"/>
 *         &lt;choice>
 *           &lt;element name="NameID" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID"/>
 *           &lt;element name="CorporateID" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID"/>
 *           &lt;element name="TravelAgentID" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "queryDateRange",
    "lastName",
    "firstName",
    "creditCardNumber",
    "additionalFilters",
    "encryptedCreditCardSwipe",
    "nameID",
    "corporateID",
    "travelAgentID"
})
@XmlRootElement(name = "FutureBookingSummaryRequest", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
public class FutureBookingSummaryRequest {

    @XmlElement(name = "QueryDateRange", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
    protected FutureBookingSummaryRequest.QueryDateRange queryDateRange;
    @XmlElement(name = "LastName", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
    protected String lastName;
    @XmlElement(name = "FirstName", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
    protected String firstName;
    @XmlElement(name = "CreditCardNumber", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
    protected String creditCardNumber;
    @XmlElement(name = "AdditionalFilters", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", required = true)
    protected FetchBookingFilters additionalFilters;
    @XmlElement(name = "EncryptedCreditCardSwipe", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", required = true)
    protected EncryptedSwipe encryptedCreditCardSwipe;
    @XmlElement(name = "NameID", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
    protected UniqueID nameID;
    @XmlElement(name = "CorporateID", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
    protected UniqueID corporateID;
    @XmlElement(name = "TravelAgentID", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
    protected UniqueID travelAgentID;

    /**
     * 获取queryDateRange属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FutureBookingSummaryRequest.QueryDateRange }
     *     
     */
    public FutureBookingSummaryRequest.QueryDateRange getQueryDateRange() {
        return queryDateRange;
    }

    /**
     * 设置queryDateRange属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FutureBookingSummaryRequest.QueryDateRange }
     *     
     */
    public void setQueryDateRange(FutureBookingSummaryRequest.QueryDateRange value) {
        this.queryDateRange = value;
    }

    /**
     * 获取lastName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 设置lastName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * 获取firstName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 设置firstName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * 获取creditCardNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * 设置creditCardNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardNumber(String value) {
        this.creditCardNumber = value;
    }

    /**
     * 获取additionalFilters属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FetchBookingFilters }
     *     
     */
    public FetchBookingFilters getAdditionalFilters() {
        return additionalFilters;
    }

    /**
     * 设置additionalFilters属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FetchBookingFilters }
     *     
     */
    public void setAdditionalFilters(FetchBookingFilters value) {
        this.additionalFilters = value;
    }

    /**
     * 获取encryptedCreditCardSwipe属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EncryptedSwipe }
     *     
     */
    public EncryptedSwipe getEncryptedCreditCardSwipe() {
        return encryptedCreditCardSwipe;
    }

    /**
     * 设置encryptedCreditCardSwipe属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptedSwipe }
     *     
     */
    public void setEncryptedCreditCardSwipe(EncryptedSwipe value) {
        this.encryptedCreditCardSwipe = value;
    }

    /**
     * 获取nameID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getNameID() {
        return nameID;
    }

    /**
     * 设置nameID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setNameID(UniqueID value) {
        this.nameID = value;
    }

    /**
     * 获取corporateID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getCorporateID() {
        return corporateID;
    }

    /**
     * 设置corporateID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setCorporateID(UniqueID value) {
        this.corporateID = value;
    }

    /**
     * 获取travelAgentID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getTravelAgentID() {
        return travelAgentID;
    }

    /**
     * 设置travelAgentID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setTravelAgentID(UniqueID value) {
        this.travelAgentID = value;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan">
     *       &lt;attribute name="dateType" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;enumeration value="BOOKED_DATE"/>
     *             &lt;enumeration value="ARRIVAL_DATE"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class QueryDateRange
        extends TimeSpan
    {

        @XmlAttribute(name = "dateType", required = true)
        protected String dateType;

        /**
         * 获取dateType属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDateType() {
            return dateType;
        }

        /**
         * 设置dateType属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDateType(String value) {
            this.dateType = value;
        }

    }

}
