
package com.ccm.webservices.og.hotelcommon;

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

import com.ccm.webservices.og.common.DescriptiveText;


/**
 * <p>FacilityInfoType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="FacilityInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GuestRooms" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="GuestRoom" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="RoomDescription" type="{http://webservices.micros.com/og/4.3/Common/}DescriptiveText" minOccurs="0"/>
 *                             &lt;element name="AmenityInfo" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AmenityInfo" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="code" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="maxOccupancy" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="totalRooms" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Restaurants" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RestaurantsType" minOccurs="0"/>
 *         &lt;element name="MeetingRooms" type="{http://webservices.micros.com/og/4.3/HotelCommon/}MeetingRoomsType" minOccurs="0"/>
 *         &lt;element name="Attractions" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AttractionsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="dateOpened" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="dateRennovated" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FacilityInfoType", propOrder = {
    "guestRooms",
    "restaurants",
    "meetingRooms",
    "attractions"
})
public class FacilityInfoType {

    @XmlElement(name = "GuestRooms")
    protected FacilityInfoType.GuestRooms guestRooms;
    @XmlElement(name = "Restaurants")
    protected RestaurantsType restaurants;
    @XmlElement(name = "MeetingRooms")
    protected MeetingRoomsType meetingRooms;
    @XmlElement(name = "Attractions")
    protected AttractionsType attractions;
    @XmlAttribute(name = "dateOpened")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateOpened;
    @XmlAttribute(name = "dateRennovated")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateRennovated;

    /**
     * 获取guestRooms属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FacilityInfoType.GuestRooms }
     *     
     */
    public FacilityInfoType.GuestRooms getGuestRooms() {
        return guestRooms;
    }

    /**
     * 设置guestRooms属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FacilityInfoType.GuestRooms }
     *     
     */
    public void setGuestRooms(FacilityInfoType.GuestRooms value) {
        this.guestRooms = value;
    }

    /**
     * 获取restaurants属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RestaurantsType }
     *     
     */
    public RestaurantsType getRestaurants() {
        return restaurants;
    }

    /**
     * 设置restaurants属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RestaurantsType }
     *     
     */
    public void setRestaurants(RestaurantsType value) {
        this.restaurants = value;
    }

    /**
     * 获取meetingRooms属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MeetingRoomsType }
     *     
     */
    public MeetingRoomsType getMeetingRooms() {
        return meetingRooms;
    }

    /**
     * 设置meetingRooms属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MeetingRoomsType }
     *     
     */
    public void setMeetingRooms(MeetingRoomsType value) {
        this.meetingRooms = value;
    }

    /**
     * 获取attractions属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AttractionsType }
     *     
     */
    public AttractionsType getAttractions() {
        return attractions;
    }

    /**
     * 设置attractions属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AttractionsType }
     *     
     */
    public void setAttractions(AttractionsType value) {
        this.attractions = value;
    }

    /**
     * 获取dateOpened属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOpened() {
        return dateOpened;
    }

    /**
     * 设置dateOpened属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOpened(XMLGregorianCalendar value) {
        this.dateOpened = value;
    }

    /**
     * 获取dateRennovated属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateRennovated() {
        return dateRennovated;
    }

    /**
     * 设置dateRennovated属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateRennovated(XMLGregorianCalendar value) {
        this.dateRennovated = value;
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
     *       &lt;sequence>
     *         &lt;element name="GuestRoom" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="RoomDescription" type="{http://webservices.micros.com/og/4.3/Common/}DescriptiveText" minOccurs="0"/>
     *                   &lt;element name="AmenityInfo" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AmenityInfo" minOccurs="0"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="code" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="maxOccupancy" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="totalRooms" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "guestRoom"
    })
    public static class GuestRooms {

        @XmlElement(name = "GuestRoom", required = true)
        protected List<FacilityInfoType.GuestRooms.GuestRoom> guestRoom;
        @XmlAttribute(name = "totalRooms")
        protected String totalRooms;

        /**
         * Gets the value of the guestRoom property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the guestRoom property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getGuestRoom().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link FacilityInfoType.GuestRooms.GuestRoom }
         * 
         * 
         */
        public List<FacilityInfoType.GuestRooms.GuestRoom> getGuestRoom() {
            if (guestRoom == null) {
                guestRoom = new ArrayList<FacilityInfoType.GuestRooms.GuestRoom>();
            }
            return this.guestRoom;
        }

        /**
         * 获取totalRooms属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTotalRooms() {
            return totalRooms;
        }

        /**
         * 设置totalRooms属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTotalRooms(String value) {
            this.totalRooms = value;
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
         *       &lt;sequence>
         *         &lt;element name="RoomDescription" type="{http://webservices.micros.com/og/4.3/Common/}DescriptiveText" minOccurs="0"/>
         *         &lt;element name="AmenityInfo" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AmenityInfo" minOccurs="0"/>
         *       &lt;/sequence>
         *       &lt;attribute name="code" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="maxOccupancy" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "roomDescription",
            "amenityInfo"
        })
        public static class GuestRoom {

            @XmlElement(name = "RoomDescription")
            protected DescriptiveText roomDescription;
            @XmlElement(name = "AmenityInfo")
            protected AmenityInfo amenityInfo;
            @XmlAttribute(name = "code")
            protected String code;
            @XmlAttribute(name = "maxOccupancy")
            protected BigInteger maxOccupancy;

            /**
             * 获取roomDescription属性的值。
             * 
             * @return
             *     possible object is
             *     {@link DescriptiveText }
             *     
             */
            public DescriptiveText getRoomDescription() {
                return roomDescription;
            }

            /**
             * 设置roomDescription属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link DescriptiveText }
             *     
             */
            public void setRoomDescription(DescriptiveText value) {
                this.roomDescription = value;
            }

            /**
             * 获取amenityInfo属性的值。
             * 
             * @return
             *     possible object is
             *     {@link AmenityInfo }
             *     
             */
            public AmenityInfo getAmenityInfo() {
                return amenityInfo;
            }

            /**
             * 设置amenityInfo属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link AmenityInfo }
             *     
             */
            public void setAmenityInfo(AmenityInfo value) {
                this.amenityInfo = value;
            }

            /**
             * 获取code属性的值。
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
             * 设置code属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCode(String value) {
                this.code = value;
            }

            /**
             * 获取maxOccupancy属性的值。
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getMaxOccupancy() {
                return maxOccupancy;
            }

            /**
             * 设置maxOccupancy属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setMaxOccupancy(BigInteger value) {
                this.maxOccupancy = value;
            }

        }

    }

}
