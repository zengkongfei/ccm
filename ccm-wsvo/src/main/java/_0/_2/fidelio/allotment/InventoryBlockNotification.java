//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 04:23:24GMT+08:00 
//


package _0._2.fidelio.allotment;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for InventoryBlockNotification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InventoryBlockNotification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HotelReference" type="{allotment.fidelio.2.0}HotelReference" minOccurs="0"/>
 *         &lt;element name="inventoryBlockName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inventoryBlockGroupingCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="preShoulderProcureBlockCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postShoulderProcureBlockCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inventoryBlockCodes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BlockTimeSpan" type="{allotment.fidelio.2.0}BlockTimeSpan" minOccurs="0"/>
 *         &lt;element name="dropDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="AvailableDaysOfWeek" type="{allotment.fidelio.2.0}AvailableDaysOfWeek" minOccurs="0"/>
 *         &lt;element name="ArrivalDaysOfWeek" type="{allotment.fidelio.2.0}ArrivalDaysOfWeek" minOccurs="0"/>
 *         &lt;element name="RequiredDaysOfWeek" type="{allotment.fidelio.2.0}RequiredDaysOfWeek" minOccurs="0"/>
 *         &lt;element name="numberRequiredNights" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BookingDateRange" type="{allotment.fidelio.2.0}BookingDateRange" minOccurs="0"/>
 *         &lt;element name="validBookingMinOffset" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="validBookingMaxOffset" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="priceViewable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="BlockShoulders" type="{allotment.fidelio.2.0}BlockShoulders" minOccurs="0"/>
 *         &lt;element name="ProductDescriptions" type="{allotment.fidelio.2.0}ProductDescriptions" minOccurs="0"/>
 *         &lt;element name="Viewerships" type="{allotment.fidelio.2.0}Viewerships" minOccurs="0"/>
 *         &lt;element name="AssociatedProfiles" type="{allotment.fidelio.2.0}AssociatedProfiles" minOccurs="0"/>
 *         &lt;element name="InventoryBlocks" type="{allotment.fidelio.2.0}InventoryBlocks" minOccurs="0"/>
 *         &lt;element name="ratePlanCodes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mfAllotmentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mfMasterBlockCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mfMarketCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mfSourceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mfChannelCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mfBlockOriginatorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mfCutoffDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="mfCutoffDays" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="mfPackage" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mfReservationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mfShoulderBeginDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="mfShoulderEndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="mfBookingStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mfBookingId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mfGuaranteeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mfCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mfCateringFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mfBookingMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AllotmentNotes" type="{allotment.fidelio.2.0}AllotmentNotes" minOccurs="0"/>
 *         &lt;element name="Rules" type="{allotment.fidelio.2.0}Rules" minOccurs="0"/>
 *         &lt;element name="Udfs" type="{allotment.fidelio.2.0}Udfs" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="mfInactiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="inventoryBlockCodeType" type="{allotment.fidelio.2.0}inventoryBlockCodeType" />
 *       &lt;attribute name="validBookingOffsetTimeUnitType" type="{allotment.fidelio.2.0}validBookingOffsetTimeUnitType" />
 *       &lt;attribute name="inventoryBlockNotificationType" type="{allotment.fidelio.2.0}inventoryBlockNotificationType" />
 *       &lt;attribute name="inventoryBlockStatusType" type="{allotment.fidelio.2.0}inventoryBlockStatusType" />
 *       &lt;attribute name="mfBlockType" use="required" type="{allotment.fidelio.2.0}mfBlockType" />
 *       &lt;attribute name="mfBlockMessageType" use="required" type="{allotment.fidelio.2.0}mfBlockMessageType" />
 *       &lt;attribute name="mfMasterBlock" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "InventoryBlockNotification")
@XmlType(name = "InventoryBlockNotification", propOrder = {
    "hotelReference",
    "inventoryBlockName",
    "inventoryBlockGroupingCode",
    "preShoulderProcureBlockCode",
    "postShoulderProcureBlockCode",
    "inventoryBlockCodes",
    "blockTimeSpan",
    "dropDate",
    "availableDaysOfWeek",
    "arrivalDaysOfWeek",
    "requiredDaysOfWeek",
    "numberRequiredNights",
    "bookingDateRange",
    "validBookingMinOffset",
    "validBookingMaxOffset",
    "priceViewable",
    "blockShoulders",
    "productDescriptions",
    "viewerships",
    "associatedProfiles",
    "inventoryBlocks",
    "ratePlanCodes",
    "mfAllotmentId",
    "mfMasterBlockCode",
    "mfMarketCode",
    "mfSourceCode",
    "mfChannelCode",
    "mfBlockOriginatorCode",
    "mfCutoffDate",
    "mfCutoffDays",
    "mfPackage",
    "mfReservationType",
    "mfShoulderBeginDate",
    "mfShoulderEndDate",
    "mfBookingStatus",
    "mfBookingId",
    "mfGuaranteeType",
    "mfCurrencyCode",
    "mfCateringFlag",
    "mfBookingMethod",
    "allotmentNotes",
    "rules",
    "udfs"
})
public class InventoryBlockNotification {

    @XmlElement(name = "HotelReference")
    protected HotelReference hotelReference;
    protected String inventoryBlockName;
    @XmlElement(required = true)
    protected String inventoryBlockGroupingCode;
    protected String preShoulderProcureBlockCode;
    protected String postShoulderProcureBlockCode;
    protected List<String> inventoryBlockCodes;
    @XmlElement(name = "BlockTimeSpan")
    protected BlockTimeSpan blockTimeSpan;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dropDate;
    @XmlElement(name = "AvailableDaysOfWeek")
    protected AvailableDaysOfWeek availableDaysOfWeek;
    @XmlElement(name = "ArrivalDaysOfWeek")
    protected ArrivalDaysOfWeek arrivalDaysOfWeek;
    @XmlElement(name = "RequiredDaysOfWeek")
    protected RequiredDaysOfWeek requiredDaysOfWeek;
    protected Integer numberRequiredNights;
    @XmlElement(name = "BookingDateRange")
    protected BookingDateRange bookingDateRange;
    protected Integer validBookingMinOffset;
    protected Integer validBookingMaxOffset;
    protected Boolean priceViewable;
    @XmlElement(name = "BlockShoulders")
    protected BlockShoulders blockShoulders;
    @XmlElement(name = "ProductDescriptions")
    protected ProductDescriptions productDescriptions;
    @XmlElement(name = "Viewerships")
    protected Viewerships viewerships;
    @XmlElement(name = "AssociatedProfiles")
    protected AssociatedProfiles associatedProfiles;
    @XmlElement(name = "InventoryBlocks")
    protected InventoryBlocks inventoryBlocks;
    protected List<String> ratePlanCodes;
    protected String mfAllotmentId;
    protected String mfMasterBlockCode;
    protected String mfMarketCode;
    protected String mfSourceCode;
    protected String mfChannelCode;
    protected String mfBlockOriginatorCode;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar mfCutoffDate;
    protected Integer mfCutoffDays;
    protected List<String> mfPackage;
    protected String mfReservationType;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar mfShoulderBeginDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar mfShoulderEndDate;
    protected String mfBookingStatus;
    protected String mfBookingId;
    protected String mfGuaranteeType;
    protected String mfCurrencyCode;
    protected String mfCateringFlag;
    protected String mfBookingMethod;
    @XmlElement(name = "AllotmentNotes")
    protected AllotmentNotes allotmentNotes;
    @XmlElement(name = "Rules")
    protected Rules rules;
    @XmlElement(name = "Udfs")
    protected Udfs udfs;
    @XmlAttribute
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar mfInactiveDate;
    @XmlAttribute
    protected InventoryBlockCodeType inventoryBlockCodeType;
    @XmlAttribute
    protected ValidBookingOffsetTimeUnitType validBookingOffsetTimeUnitType;
    @XmlAttribute
    protected InventoryBlockNotificationType inventoryBlockNotificationType;
    @XmlAttribute
    protected InventoryBlockStatusType inventoryBlockStatusType;
    @XmlAttribute(required = true)
    protected MfBlockType mfBlockType;
    @XmlAttribute(required = true)
    protected MfBlockMessageType mfBlockMessageType;
    @XmlAttribute
    protected Boolean mfMasterBlock;

    /**
     * Gets the value of the hotelReference property.
     * 
     * @return
     *     possible object is
     *     {@link HotelReference }
     *     
     */
    public HotelReference getHotelReference() {
        return hotelReference;
    }

    /**
     * Sets the value of the hotelReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReference }
     *     
     */
    public void setHotelReference(HotelReference value) {
        this.hotelReference = value;
    }

    /**
     * Gets the value of the inventoryBlockName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInventoryBlockName() {
        return inventoryBlockName;
    }

    /**
     * Sets the value of the inventoryBlockName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInventoryBlockName(String value) {
        this.inventoryBlockName = value;
    }

    /**
     * Gets the value of the inventoryBlockGroupingCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInventoryBlockGroupingCode() {
        return inventoryBlockGroupingCode;
    }

    /**
     * Sets the value of the inventoryBlockGroupingCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInventoryBlockGroupingCode(String value) {
        this.inventoryBlockGroupingCode = value;
    }

    /**
     * Gets the value of the preShoulderProcureBlockCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreShoulderProcureBlockCode() {
        return preShoulderProcureBlockCode;
    }

    /**
     * Sets the value of the preShoulderProcureBlockCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreShoulderProcureBlockCode(String value) {
        this.preShoulderProcureBlockCode = value;
    }

    /**
     * Gets the value of the postShoulderProcureBlockCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostShoulderProcureBlockCode() {
        return postShoulderProcureBlockCode;
    }

    /**
     * Sets the value of the postShoulderProcureBlockCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostShoulderProcureBlockCode(String value) {
        this.postShoulderProcureBlockCode = value;
    }

    /**
     * Gets the value of the inventoryBlockCodes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inventoryBlockCodes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInventoryBlockCodes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getInventoryBlockCodes() {
        if (inventoryBlockCodes == null) {
            inventoryBlockCodes = new ArrayList<String>();
        }
        return this.inventoryBlockCodes;
    }

    /**
     * Gets the value of the blockTimeSpan property.
     * 
     * @return
     *     possible object is
     *     {@link BlockTimeSpan }
     *     
     */
    public BlockTimeSpan getBlockTimeSpan() {
        return blockTimeSpan;
    }

    /**
     * Sets the value of the blockTimeSpan property.
     * 
     * @param value
     *     allowed object is
     *     {@link BlockTimeSpan }
     *     
     */
    public void setBlockTimeSpan(BlockTimeSpan value) {
        this.blockTimeSpan = value;
    }

    /**
     * Gets the value of the dropDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDropDate() {
        return dropDate;
    }

    /**
     * Sets the value of the dropDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDropDate(XMLGregorianCalendar value) {
        this.dropDate = value;
    }

    /**
     * Gets the value of the availableDaysOfWeek property.
     * 
     * @return
     *     possible object is
     *     {@link AvailableDaysOfWeek }
     *     
     */
    public AvailableDaysOfWeek getAvailableDaysOfWeek() {
        return availableDaysOfWeek;
    }

    /**
     * Sets the value of the availableDaysOfWeek property.
     * 
     * @param value
     *     allowed object is
     *     {@link AvailableDaysOfWeek }
     *     
     */
    public void setAvailableDaysOfWeek(AvailableDaysOfWeek value) {
        this.availableDaysOfWeek = value;
    }

    /**
     * Gets the value of the arrivalDaysOfWeek property.
     * 
     * @return
     *     possible object is
     *     {@link ArrivalDaysOfWeek }
     *     
     */
    public ArrivalDaysOfWeek getArrivalDaysOfWeek() {
        return arrivalDaysOfWeek;
    }

    /**
     * Sets the value of the arrivalDaysOfWeek property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrivalDaysOfWeek }
     *     
     */
    public void setArrivalDaysOfWeek(ArrivalDaysOfWeek value) {
        this.arrivalDaysOfWeek = value;
    }

    /**
     * Gets the value of the requiredDaysOfWeek property.
     * 
     * @return
     *     possible object is
     *     {@link RequiredDaysOfWeek }
     *     
     */
    public RequiredDaysOfWeek getRequiredDaysOfWeek() {
        return requiredDaysOfWeek;
    }

    /**
     * Sets the value of the requiredDaysOfWeek property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequiredDaysOfWeek }
     *     
     */
    public void setRequiredDaysOfWeek(RequiredDaysOfWeek value) {
        this.requiredDaysOfWeek = value;
    }

    /**
     * Gets the value of the numberRequiredNights property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberRequiredNights() {
        return numberRequiredNights;
    }

    /**
     * Sets the value of the numberRequiredNights property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberRequiredNights(Integer value) {
        this.numberRequiredNights = value;
    }

    /**
     * Gets the value of the bookingDateRange property.
     * 
     * @return
     *     possible object is
     *     {@link BookingDateRange }
     *     
     */
    public BookingDateRange getBookingDateRange() {
        return bookingDateRange;
    }

    /**
     * Sets the value of the bookingDateRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link BookingDateRange }
     *     
     */
    public void setBookingDateRange(BookingDateRange value) {
        this.bookingDateRange = value;
    }

    /**
     * Gets the value of the validBookingMinOffset property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getValidBookingMinOffset() {
        return validBookingMinOffset;
    }

    /**
     * Sets the value of the validBookingMinOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setValidBookingMinOffset(Integer value) {
        this.validBookingMinOffset = value;
    }

    /**
     * Gets the value of the validBookingMaxOffset property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getValidBookingMaxOffset() {
        return validBookingMaxOffset;
    }

    /**
     * Sets the value of the validBookingMaxOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setValidBookingMaxOffset(Integer value) {
        this.validBookingMaxOffset = value;
    }

    /**
     * Gets the value of the priceViewable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPriceViewable() {
        return priceViewable;
    }

    /**
     * Sets the value of the priceViewable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPriceViewable(Boolean value) {
        this.priceViewable = value;
    }

    /**
     * Gets the value of the blockShoulders property.
     * 
     * @return
     *     possible object is
     *     {@link BlockShoulders }
     *     
     */
    public BlockShoulders getBlockShoulders() {
        return blockShoulders;
    }

    /**
     * Sets the value of the blockShoulders property.
     * 
     * @param value
     *     allowed object is
     *     {@link BlockShoulders }
     *     
     */
    public void setBlockShoulders(BlockShoulders value) {
        this.blockShoulders = value;
    }

    /**
     * Gets the value of the productDescriptions property.
     * 
     * @return
     *     possible object is
     *     {@link ProductDescriptions }
     *     
     */
    public ProductDescriptions getProductDescriptions() {
        return productDescriptions;
    }

    /**
     * Sets the value of the productDescriptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductDescriptions }
     *     
     */
    public void setProductDescriptions(ProductDescriptions value) {
        this.productDescriptions = value;
    }

    /**
     * Gets the value of the viewerships property.
     * 
     * @return
     *     possible object is
     *     {@link Viewerships }
     *     
     */
    public Viewerships getViewerships() {
        return viewerships;
    }

    /**
     * Sets the value of the viewerships property.
     * 
     * @param value
     *     allowed object is
     *     {@link Viewerships }
     *     
     */
    public void setViewerships(Viewerships value) {
        this.viewerships = value;
    }

    /**
     * Gets the value of the associatedProfiles property.
     * 
     * @return
     *     possible object is
     *     {@link AssociatedProfiles }
     *     
     */
    public AssociatedProfiles getAssociatedProfiles() {
        return associatedProfiles;
    }

    /**
     * Sets the value of the associatedProfiles property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssociatedProfiles }
     *     
     */
    public void setAssociatedProfiles(AssociatedProfiles value) {
        this.associatedProfiles = value;
    }

    /**
     * Gets the value of the inventoryBlocks property.
     * 
     * @return
     *     possible object is
     *     {@link InventoryBlocks }
     *     
     */
    public InventoryBlocks getInventoryBlocks() {
        return inventoryBlocks;
    }

    /**
     * Sets the value of the inventoryBlocks property.
     * 
     * @param value
     *     allowed object is
     *     {@link InventoryBlocks }
     *     
     */
    public void setInventoryBlocks(InventoryBlocks value) {
        this.inventoryBlocks = value;
    }

    /**
     * Gets the value of the ratePlanCodes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ratePlanCodes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRatePlanCodes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRatePlanCodes() {
        if (ratePlanCodes == null) {
            ratePlanCodes = new ArrayList<String>();
        }
        return this.ratePlanCodes;
    }

    /**
     * Gets the value of the mfAllotmentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMfAllotmentId() {
        return mfAllotmentId;
    }

    /**
     * Sets the value of the mfAllotmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMfAllotmentId(String value) {
        this.mfAllotmentId = value;
    }

    /**
     * Gets the value of the mfMasterBlockCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMfMasterBlockCode() {
        return mfMasterBlockCode;
    }

    /**
     * Sets the value of the mfMasterBlockCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMfMasterBlockCode(String value) {
        this.mfMasterBlockCode = value;
    }

    /**
     * Gets the value of the mfMarketCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMfMarketCode() {
        return mfMarketCode;
    }

    /**
     * Sets the value of the mfMarketCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMfMarketCode(String value) {
        this.mfMarketCode = value;
    }

    /**
     * Gets the value of the mfSourceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMfSourceCode() {
        return mfSourceCode;
    }

    /**
     * Sets the value of the mfSourceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMfSourceCode(String value) {
        this.mfSourceCode = value;
    }

    /**
     * Gets the value of the mfChannelCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMfChannelCode() {
        return mfChannelCode;
    }

    /**
     * Sets the value of the mfChannelCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMfChannelCode(String value) {
        this.mfChannelCode = value;
    }

    /**
     * Gets the value of the mfBlockOriginatorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMfBlockOriginatorCode() {
        return mfBlockOriginatorCode;
    }

    /**
     * Sets the value of the mfBlockOriginatorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMfBlockOriginatorCode(String value) {
        this.mfBlockOriginatorCode = value;
    }

    /**
     * Gets the value of the mfCutoffDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMfCutoffDate() {
        return mfCutoffDate;
    }

    /**
     * Sets the value of the mfCutoffDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMfCutoffDate(XMLGregorianCalendar value) {
        this.mfCutoffDate = value;
    }

    /**
     * Gets the value of the mfCutoffDays property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMfCutoffDays() {
        return mfCutoffDays;
    }

    /**
     * Sets the value of the mfCutoffDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMfCutoffDays(Integer value) {
        this.mfCutoffDays = value;
    }

    /**
     * Gets the value of the mfPackage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mfPackage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMfPackage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMfPackage() {
        if (mfPackage == null) {
            mfPackage = new ArrayList<String>();
        }
        return this.mfPackage;
    }

    /**
     * Gets the value of the mfReservationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMfReservationType() {
        return mfReservationType;
    }

    /**
     * Sets the value of the mfReservationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMfReservationType(String value) {
        this.mfReservationType = value;
    }

    /**
     * Gets the value of the mfShoulderBeginDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMfShoulderBeginDate() {
        return mfShoulderBeginDate;
    }

    /**
     * Sets the value of the mfShoulderBeginDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMfShoulderBeginDate(XMLGregorianCalendar value) {
        this.mfShoulderBeginDate = value;
    }

    /**
     * Gets the value of the mfShoulderEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMfShoulderEndDate() {
        return mfShoulderEndDate;
    }

    /**
     * Sets the value of the mfShoulderEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMfShoulderEndDate(XMLGregorianCalendar value) {
        this.mfShoulderEndDate = value;
    }

    /**
     * Gets the value of the mfBookingStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMfBookingStatus() {
        return mfBookingStatus;
    }

    /**
     * Sets the value of the mfBookingStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMfBookingStatus(String value) {
        this.mfBookingStatus = value;
    }

    /**
     * Gets the value of the mfBookingId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMfBookingId() {
        return mfBookingId;
    }

    /**
     * Sets the value of the mfBookingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMfBookingId(String value) {
        this.mfBookingId = value;
    }

    /**
     * Gets the value of the mfGuaranteeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMfGuaranteeType() {
        return mfGuaranteeType;
    }

    /**
     * Sets the value of the mfGuaranteeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMfGuaranteeType(String value) {
        this.mfGuaranteeType = value;
    }

    /**
     * Gets the value of the mfCurrencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMfCurrencyCode() {
        return mfCurrencyCode;
    }

    /**
     * Sets the value of the mfCurrencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMfCurrencyCode(String value) {
        this.mfCurrencyCode = value;
    }

    /**
     * Gets the value of the mfCateringFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMfCateringFlag() {
        return mfCateringFlag;
    }

    /**
     * Sets the value of the mfCateringFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMfCateringFlag(String value) {
        this.mfCateringFlag = value;
    }

    /**
     * Gets the value of the mfBookingMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMfBookingMethod() {
        return mfBookingMethod;
    }

    /**
     * Sets the value of the mfBookingMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMfBookingMethod(String value) {
        this.mfBookingMethod = value;
    }

    /**
     * Gets the value of the allotmentNotes property.
     * 
     * @return
     *     possible object is
     *     {@link AllotmentNotes }
     *     
     */
    public AllotmentNotes getAllotmentNotes() {
        return allotmentNotes;
    }

    /**
     * Sets the value of the allotmentNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link AllotmentNotes }
     *     
     */
    public void setAllotmentNotes(AllotmentNotes value) {
        this.allotmentNotes = value;
    }

    /**
     * Gets the value of the rules property.
     * 
     * @return
     *     possible object is
     *     {@link Rules }
     *     
     */
    public Rules getRules() {
        return rules;
    }

    /**
     * Sets the value of the rules property.
     * 
     * @param value
     *     allowed object is
     *     {@link Rules }
     *     
     */
    public void setRules(Rules value) {
        this.rules = value;
    }

    /**
     * Gets the value of the udfs property.
     * 
     * @return
     *     possible object is
     *     {@link Udfs }
     *     
     */
    public Udfs getUdfs() {
        return udfs;
    }

    /**
     * Sets the value of the udfs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Udfs }
     *     
     */
    public void setUdfs(Udfs value) {
        this.udfs = value;
    }

    /**
     * Gets the value of the mfInactiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMfInactiveDate() {
        return mfInactiveDate;
    }

    /**
     * Sets the value of the mfInactiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMfInactiveDate(XMLGregorianCalendar value) {
        this.mfInactiveDate = value;
    }

    /**
     * Gets the value of the inventoryBlockCodeType property.
     * 
     * @return
     *     possible object is
     *     {@link InventoryBlockCodeType }
     *     
     */
    public InventoryBlockCodeType getInventoryBlockCodeType() {
        return inventoryBlockCodeType;
    }

    /**
     * Sets the value of the inventoryBlockCodeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link InventoryBlockCodeType }
     *     
     */
    public void setInventoryBlockCodeType(InventoryBlockCodeType value) {
        this.inventoryBlockCodeType = value;
    }

    /**
     * Gets the value of the validBookingOffsetTimeUnitType property.
     * 
     * @return
     *     possible object is
     *     {@link ValidBookingOffsetTimeUnitType }
     *     
     */
    public ValidBookingOffsetTimeUnitType getValidBookingOffsetTimeUnitType() {
        return validBookingOffsetTimeUnitType;
    }

    /**
     * Sets the value of the validBookingOffsetTimeUnitType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidBookingOffsetTimeUnitType }
     *     
     */
    public void setValidBookingOffsetTimeUnitType(ValidBookingOffsetTimeUnitType value) {
        this.validBookingOffsetTimeUnitType = value;
    }

    /**
     * Gets the value of the inventoryBlockNotificationType property.
     * 
     * @return
     *     possible object is
     *     {@link InventoryBlockNotificationType }
     *     
     */
    public InventoryBlockNotificationType getInventoryBlockNotificationType() {
        return inventoryBlockNotificationType;
    }

    /**
     * Sets the value of the inventoryBlockNotificationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link InventoryBlockNotificationType }
     *     
     */
    public void setInventoryBlockNotificationType(InventoryBlockNotificationType value) {
        this.inventoryBlockNotificationType = value;
    }

    /**
     * Gets the value of the inventoryBlockStatusType property.
     * 
     * @return
     *     possible object is
     *     {@link InventoryBlockStatusType }
     *     
     */
    public InventoryBlockStatusType getInventoryBlockStatusType() {
        return inventoryBlockStatusType;
    }

    /**
     * Sets the value of the inventoryBlockStatusType property.
     * 
     * @param value
     *     allowed object is
     *     {@link InventoryBlockStatusType }
     *     
     */
    public void setInventoryBlockStatusType(InventoryBlockStatusType value) {
        this.inventoryBlockStatusType = value;
    }

    /**
     * Gets the value of the mfBlockType property.
     * 
     * @return
     *     possible object is
     *     {@link MfBlockType }
     *     
     */
    public MfBlockType getMfBlockType() {
        return mfBlockType;
    }

    /**
     * Sets the value of the mfBlockType property.
     * 
     * @param value
     *     allowed object is
     *     {@link MfBlockType }
     *     
     */
    public void setMfBlockType(MfBlockType value) {
        this.mfBlockType = value;
    }

    /**
     * Gets the value of the mfBlockMessageType property.
     * 
     * @return
     *     possible object is
     *     {@link MfBlockMessageType }
     *     
     */
    public MfBlockMessageType getMfBlockMessageType() {
        return mfBlockMessageType;
    }

    /**
     * Sets the value of the mfBlockMessageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link MfBlockMessageType }
     *     
     */
    public void setMfBlockMessageType(MfBlockMessageType value) {
        this.mfBlockMessageType = value;
    }

    /**
     * Gets the value of the mfMasterBlock property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMfMasterBlock() {
        return mfMasterBlock;
    }

    /**
     * Sets the value of the mfMasterBlock property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMfMasterBlock(Boolean value) {
        this.mfMasterBlock = value;
    }

}