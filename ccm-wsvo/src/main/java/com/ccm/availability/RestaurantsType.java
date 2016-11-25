
package com.ccm.availability;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Provides detailed information regarding restaurants.
 * 
 * <p>RestaurantsType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RestaurantsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Restaurant" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RestaurantDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" maxOccurs="5" minOccurs="0"/>
 *                   &lt;element name="RelativePosition" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Vector" minOccurs="0"/>
 *                   &lt;element name="Cuisines" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Cuisine" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="Code" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="Description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="RestaurantContacts" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AddressList" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="RestaurantName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="MaxSeatingCapacity" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="MaxSingleParty" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="OfferBreakfast" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="OfferLunch" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="OfferDinner" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="OfferBrunch" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RestaurantsType", propOrder = {
    "restaurant"
})
public class RestaurantsType {

    @XmlElement(name = "Restaurant")
    protected List<RestaurantsType.Restaurant> restaurant;

    /**
     * Gets the value of the restaurant property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the restaurant property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRestaurant().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RestaurantsType.Restaurant }
     * 
     * 
     */
    public List<RestaurantsType.Restaurant> getRestaurant() {
        if (restaurant == null) {
            restaurant = new ArrayList<RestaurantsType.Restaurant>();
        }
        return this.restaurant;
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
     *         &lt;element name="RestaurantDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" maxOccurs="5" minOccurs="0"/>
     *         &lt;element name="RelativePosition" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Vector" minOccurs="0"/>
     *         &lt;element name="Cuisines" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Cuisine" maxOccurs="unbounded">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="Code" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="Description" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="RestaurantContacts" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AddressList" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="RestaurantName" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="MaxSeatingCapacity" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
     *       &lt;attribute name="MaxSingleParty" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
     *       &lt;attribute name="OfferBreakfast" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="OfferLunch" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="OfferDinner" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="OfferBrunch" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "restaurantDescription",
        "relativePosition",
        "cuisines",
        "restaurantContacts"
    })
    public static class Restaurant {

        @XmlElement(name = "RestaurantDescription")
        protected List<Paragraph> restaurantDescription;
        @XmlElement(name = "RelativePosition")
        protected Vector relativePosition;
        @XmlElement(name = "Cuisines")
        protected RestaurantsType.Restaurant.Cuisines cuisines;
        @XmlElement(name = "RestaurantContacts")
        protected AddressList restaurantContacts;
        @XmlAttribute(name = "RestaurantName")
        protected String restaurantName;
        @XmlAttribute(name = "MaxSeatingCapacity")
        @XmlSchemaType(name = "nonNegativeInteger")
        protected BigInteger maxSeatingCapacity;
        @XmlAttribute(name = "MaxSingleParty")
        @XmlSchemaType(name = "nonNegativeInteger")
        protected BigInteger maxSingleParty;
        @XmlAttribute(name = "OfferBreakfast")
        protected Boolean offerBreakfast;
        @XmlAttribute(name = "OfferLunch")
        protected Boolean offerLunch;
        @XmlAttribute(name = "OfferDinner")
        protected Boolean offerDinner;
        @XmlAttribute(name = "OfferBrunch")
        protected Boolean offerBrunch;

        /**
         * Gets the value of the restaurantDescription property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the restaurantDescription property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRestaurantDescription().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Paragraph }
         * 
         * 
         */
        public List<Paragraph> getRestaurantDescription() {
            if (restaurantDescription == null) {
                restaurantDescription = new ArrayList<Paragraph>();
            }
            return this.restaurantDescription;
        }

        /**
         * 获取relativePosition属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Vector }
         *     
         */
        public Vector getRelativePosition() {
            return relativePosition;
        }

        /**
         * 设置relativePosition属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Vector }
         *     
         */
        public void setRelativePosition(Vector value) {
            this.relativePosition = value;
        }

        /**
         * 获取cuisines属性的值。
         * 
         * @return
         *     possible object is
         *     {@link RestaurantsType.Restaurant.Cuisines }
         *     
         */
        public RestaurantsType.Restaurant.Cuisines getCuisines() {
            return cuisines;
        }

        /**
         * 设置cuisines属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link RestaurantsType.Restaurant.Cuisines }
         *     
         */
        public void setCuisines(RestaurantsType.Restaurant.Cuisines value) {
            this.cuisines = value;
        }

        /**
         * 获取restaurantContacts属性的值。
         * 
         * @return
         *     possible object is
         *     {@link AddressList }
         *     
         */
        public AddressList getRestaurantContacts() {
            return restaurantContacts;
        }

        /**
         * 设置restaurantContacts属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link AddressList }
         *     
         */
        public void setRestaurantContacts(AddressList value) {
            this.restaurantContacts = value;
        }

        /**
         * 获取restaurantName属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRestaurantName() {
            return restaurantName;
        }

        /**
         * 设置restaurantName属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRestaurantName(String value) {
            this.restaurantName = value;
        }

        /**
         * 获取maxSeatingCapacity属性的值。
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getMaxSeatingCapacity() {
            return maxSeatingCapacity;
        }

        /**
         * 设置maxSeatingCapacity属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setMaxSeatingCapacity(BigInteger value) {
            this.maxSeatingCapacity = value;
        }

        /**
         * 获取maxSingleParty属性的值。
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getMaxSingleParty() {
            return maxSingleParty;
        }

        /**
         * 设置maxSingleParty属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setMaxSingleParty(BigInteger value) {
            this.maxSingleParty = value;
        }

        /**
         * 获取offerBreakfast属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isOfferBreakfast() {
            return offerBreakfast;
        }

        /**
         * 设置offerBreakfast属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setOfferBreakfast(Boolean value) {
            this.offerBreakfast = value;
        }

        /**
         * 获取offerLunch属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isOfferLunch() {
            return offerLunch;
        }

        /**
         * 设置offerLunch属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setOfferLunch(Boolean value) {
            this.offerLunch = value;
        }

        /**
         * 获取offerDinner属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isOfferDinner() {
            return offerDinner;
        }

        /**
         * 设置offerDinner属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setOfferDinner(Boolean value) {
            this.offerDinner = value;
        }

        /**
         * 获取offerBrunch属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isOfferBrunch() {
            return offerBrunch;
        }

        /**
         * 设置offerBrunch属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setOfferBrunch(Boolean value) {
            this.offerBrunch = value;
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
         *         &lt;element name="Cuisine" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="Code" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="Description" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
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
            "cuisine"
        })
        public static class Cuisines {

            @XmlElement(name = "Cuisine", required = true)
            protected List<RestaurantsType.Restaurant.Cuisines.Cuisine> cuisine;

            /**
             * Gets the value of the cuisine property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the cuisine property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getCuisine().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link RestaurantsType.Restaurant.Cuisines.Cuisine }
             * 
             * 
             */
            public List<RestaurantsType.Restaurant.Cuisines.Cuisine> getCuisine() {
                if (cuisine == null) {
                    cuisine = new ArrayList<RestaurantsType.Restaurant.Cuisines.Cuisine>();
                }
                return this.cuisine;
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
             *       &lt;attribute name="Code" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="Description" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Cuisine {

                @XmlAttribute(name = "Code")
                protected String code;
                @XmlAttribute(name = "Description")
                protected String description;

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
                 * 获取description属性的值。
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDescription() {
                    return description;
                }

                /**
                 * 设置description属性的值。
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDescription(String value) {
                    this.description = value;
                }

            }

        }

    }

}
