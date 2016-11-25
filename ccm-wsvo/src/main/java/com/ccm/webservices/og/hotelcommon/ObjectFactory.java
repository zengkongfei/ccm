
package com.ccm.webservices.og.hotelcommon;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.ccm.webservices.og.common.Text;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.micros.webservices.og._4_3.hotelcommon package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ParagraphURL_QNAME = new QName("http://webservices.micros.com/og/4.3/HotelCommon/", "URL");
    private final static QName _ParagraphImage_QNAME = new QName("http://webservices.micros.com/og/4.3/HotelCommon/", "Image");
    private final static QName _ParagraphText_QNAME = new QName("http://webservices.micros.com/og/4.3/HotelCommon/", "Text");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.micros.webservices.og._4_3.hotelcommon
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FacilityInfoType }
     * 
     */
    public FacilityInfoType createFacilityInfoType() {
        return new FacilityInfoType();
    }

    /**
     * Create an instance of {@link FacilityInfoType.GuestRooms }
     * 
     */
    public FacilityInfoType.GuestRooms createFacilityInfoTypeGuestRooms() {
        return new FacilityInfoType.GuestRooms();
    }

    /**
     * Create an instance of {@link AttractionsType }
     * 
     */
    public AttractionsType createAttractionsType() {
        return new AttractionsType();
    }

    /**
     * Create an instance of {@link AmenityInfo }
     * 
     */
    public AmenityInfo createAmenityInfo() {
        return new AmenityInfo();
    }

    /**
     * Create an instance of {@link MeetingRoomsType }
     * 
     */
    public MeetingRoomsType createMeetingRoomsType() {
        return new MeetingRoomsType();
    }

    /**
     * Create an instance of {@link MeetingRoomsType.MeetingRoom }
     * 
     */
    public MeetingRoomsType.MeetingRoom createMeetingRoomsTypeMeetingRoom() {
        return new MeetingRoomsType.MeetingRoom();
    }

    /**
     * Create an instance of {@link MeetingRoomsType.MeetingRoom.Codes }
     * 
     */
    public MeetingRoomsType.MeetingRoom.Codes createMeetingRoomsTypeMeetingRoomCodes() {
        return new MeetingRoomsType.MeetingRoom.Codes();
    }

    /**
     * Create an instance of {@link RestaurantsType }
     * 
     */
    public RestaurantsType createRestaurantsType() {
        return new RestaurantsType();
    }

    /**
     * Create an instance of {@link RestaurantsType.Restaurant }
     * 
     */
    public RestaurantsType.Restaurant createRestaurantsTypeRestaurant() {
        return new RestaurantsType.Restaurant();
    }

    /**
     * Create an instance of {@link RestaurantsType.Restaurant.Cuisines }
     * 
     */
    public RestaurantsType.Restaurant.Cuisines createRestaurantsTypeRestaurantCuisines() {
        return new RestaurantsType.Restaurant.Cuisines();
    }

    /**
     * Create an instance of {@link ASBRateCycle }
     * 
     */
    public ASBRateCycle createASBRateCycle() {
        return new ASBRateCycle();
    }

    /**
     * Create an instance of {@link CancelPenaltyList }
     * 
     */
    public CancelPenaltyList createCancelPenaltyList() {
        return new CancelPenaltyList();
    }

    /**
     * Create an instance of {@link SpecialRequest }
     * 
     */
    public SpecialRequest createSpecialRequest() {
        return new SpecialRequest();
    }

    /**
     * Create an instance of {@link RoomStay }
     * 
     */
    public RoomStay createRoomStay() {
        return new RoomStay();
    }

    /**
     * Create an instance of {@link Amenity }
     * 
     */
    public Amenity createAmenity() {
        return new Amenity();
    }

    /**
     * Create an instance of {@link AdditionalBedAmount }
     * 
     */
    public AdditionalBedAmount createAdditionalBedAmount() {
        return new AdditionalBedAmount();
    }

    /**
     * Create an instance of {@link Payment }
     * 
     */
    public Payment createPayment() {
        return new Payment();
    }

    /**
     * Create an instance of {@link Room }
     * 
     */
    public Room createRoom() {
        return new Room();
    }

    /**
     * Create an instance of {@link ResPayMethod }
     * 
     */
    public ResPayMethod createResPayMethod() {
        return new ResPayMethod();
    }

    /**
     * Create an instance of {@link GuestCountList }
     * 
     */
    public GuestCountList createGuestCountList() {
        return new GuestCountList();
    }

    /**
     * Create an instance of {@link InventoryItemGroupElement }
     * 
     */
    public InventoryItemGroupElement createInventoryItemGroupElement() {
        return new InventoryItemGroupElement();
    }

    /**
     * Create an instance of {@link Guarantee }
     * 
     */
    public Guarantee createGuarantee() {
        return new Guarantee();
    }

    /**
     * Create an instance of {@link TaxList }
     * 
     */
    public TaxList createTaxList() {
        return new TaxList();
    }

    /**
     * Create an instance of {@link RoomSetup }
     * 
     */
    public RoomSetup createRoomSetup() {
        return new RoomSetup();
    }

    /**
     * Create an instance of {@link RateCalculation }
     * 
     */
    public RateCalculation createRateCalculation() {
        return new RateCalculation();
    }

    /**
     * Create an instance of {@link Charge }
     * 
     */
    public Charge createCharge() {
        return new Charge();
    }

    /**
     * Create an instance of {@link VectorDistance }
     * 
     */
    public VectorDistance createVectorDistance() {
        return new VectorDistance();
    }

    /**
     * Create an instance of {@link HotelInfoList }
     * 
     */
    public HotelInfoList createHotelInfoList() {
        return new HotelInfoList();
    }

    /**
     * Create an instance of {@link GuaranteesAccepted }
     * 
     */
    public GuaranteesAccepted createGuaranteesAccepted() {
        return new GuaranteesAccepted();
    }

    /**
     * Create an instance of {@link HoldInventoryItemElement }
     * 
     */
    public HoldInventoryItemElement createHoldInventoryItemElement() {
        return new HoldInventoryItemElement();
    }

    /**
     * Create an instance of {@link Email }
     * 
     */
    public Email createEmail() {
        return new Email();
    }

    /**
     * Create an instance of {@link ContactEmailList }
     * 
     */
    public ContactEmailList createContactEmailList() {
        return new ContactEmailList();
    }

    /**
     * Create an instance of {@link InventoryItemElementList }
     * 
     */
    public InventoryItemElementList createInventoryItemElementList() {
        return new InventoryItemElementList();
    }

    /**
     * Create an instance of {@link CancelDateTime }
     * 
     */
    public CancelDateTime createCancelDateTime() {
        return new CancelDateTime();
    }

    /**
     * Create an instance of {@link SpecialRequestList }
     * 
     */
    public SpecialRequestList createSpecialRequestList() {
        return new SpecialRequestList();
    }

    /**
     * Create an instance of {@link HotelReferenceList }
     * 
     */
    public HotelReferenceList createHotelReferenceList() {
        return new HotelReferenceList();
    }

    /**
     * Create an instance of {@link ReservationComment }
     * 
     */
    public ReservationComment createReservationComment() {
        return new ReservationComment();
    }

    /**
     * Create an instance of {@link ChargeList }
     * 
     */
    public ChargeList createChargeList() {
        return new ChargeList();
    }

    /**
     * Create an instance of {@link GeoCode }
     * 
     */
    public GeoCode createGeoCode() {
        return new GeoCode();
    }

    /**
     * Create an instance of {@link OtherPaymentType }
     * 
     */
    public OtherPaymentType createOtherPaymentType() {
        return new OtherPaymentType();
    }

    /**
     * Create an instance of {@link CreditCardAuthorizationDetail }
     * 
     */
    public CreditCardAuthorizationDetail createCreditCardAuthorizationDetail() {
        return new CreditCardAuthorizationDetail();
    }

    /**
     * Create an instance of {@link GDSError }
     * 
     */
    public GDSError createGDSError() {
        return new GDSError();
    }

    /**
     * Create an instance of {@link Restriction }
     * 
     */
    public Restriction createRestriction() {
        return new Restriction();
    }

    /**
     * Create an instance of {@link ResGuestRPH }
     * 
     */
    public ResGuestRPH createResGuestRPH() {
        return new ResGuestRPH();
    }

    /**
     * Create an instance of {@link CreditCardPayment }
     * 
     */
    public CreditCardPayment createCreditCardPayment() {
        return new CreditCardPayment();
    }

    /**
     * Create an instance of {@link RoomType }
     * 
     */
    public RoomType createRoomType() {
        return new RoomType();
    }

    /**
     * Create an instance of {@link PackageElementList }
     * 
     */
    public PackageElementList createPackageElementList() {
        return new PackageElementList();
    }

    /**
     * Create an instance of {@link RoomTypeList }
     * 
     */
    public RoomTypeList createRoomTypeList() {
        return new RoomTypeList();
    }

    /**
     * Create an instance of {@link PackageDetailList }
     * 
     */
    public PackageDetailList createPackageDetailList() {
        return new PackageDetailList();
    }

    /**
     * Create an instance of {@link MinMaxRate }
     * 
     */
    public MinMaxRate createMinMaxRate() {
        return new MinMaxRate();
    }

    /**
     * Create an instance of {@link AdditionalBedAmountList }
     * 
     */
    public AdditionalBedAmountList createAdditionalBedAmountList() {
        return new AdditionalBedAmountList();
    }

    /**
     * Create an instance of {@link ItemDetail }
     * 
     */
    public ItemDetail createItemDetail() {
        return new ItemDetail();
    }

    /**
     * Create an instance of {@link AddressList }
     * 
     */
    public AddressList createAddressList() {
        return new AddressList();
    }

    /**
     * Create an instance of {@link RateList }
     * 
     */
    public RateList createRateList() {
        return new RateList();
    }

    /**
     * Create an instance of {@link GdsTotalPricingTax }
     * 
     */
    public GdsTotalPricingTax createGdsTotalPricingTax() {
        return new GdsTotalPricingTax();
    }

    /**
     * Create an instance of {@link MemberAwardInfo }
     * 
     */
    public MemberAwardInfo createMemberAwardInfo() {
        return new MemberAwardInfo();
    }

    /**
     * Create an instance of {@link Tax }
     * 
     */
    public Tax createTax() {
        return new Tax();
    }

    /**
     * Create an instance of {@link HoldItemElementList }
     * 
     */
    public HoldItemElementList createHoldItemElementList() {
        return new HoldItemElementList();
    }

    /**
     * Create an instance of {@link TimeSpan }
     * 
     */
    public TimeSpan createTimeSpan() {
        return new TimeSpan();
    }

    /**
     * Create an instance of {@link ItemElement }
     * 
     */
    public ItemElement createItemElement() {
        return new ItemElement();
    }

    /**
     * Create an instance of {@link GuaranteeAccepted }
     * 
     */
    public GuaranteeAccepted createGuaranteeAccepted() {
        return new GuaranteeAccepted();
    }

    /**
     * Create an instance of {@link AdditionalGuestAmount }
     * 
     */
    public AdditionalGuestAmount createAdditionalGuestAmount() {
        return new AdditionalGuestAmount();
    }

    /**
     * Create an instance of {@link AccompanyGuest }
     * 
     */
    public AccompanyGuest createAccompanyGuest() {
        return new AccompanyGuest();
    }

    /**
     * Create an instance of {@link Commission }
     * 
     */
    public Commission createCommission() {
        return new Commission();
    }

    /**
     * Create an instance of {@link ItemDetailList }
     * 
     */
    public ItemDetailList createItemDetailList() {
        return new ItemDetailList();
    }

    /**
     * Create an instance of {@link DailyChargeList }
     * 
     */
    public DailyChargeList createDailyChargeList() {
        return new DailyChargeList();
    }

    /**
     * Create an instance of {@link PackageCharge }
     * 
     */
    public PackageCharge createPackageCharge() {
        return new PackageCharge();
    }

    /**
     * Create an instance of {@link RoomRate }
     * 
     */
    public RoomRate createRoomRate() {
        return new RoomRate();
    }

    /**
     * Create an instance of {@link DepositRequirement }
     * 
     */
    public DepositRequirement createDepositRequirement() {
        return new DepositRequirement();
    }

    /**
     * Create an instance of {@link ScreenItemList }
     * 
     */
    public ScreenItemList createScreenItemList() {
        return new ScreenItemList();
    }

    /**
     * Create an instance of {@link PaymentsAccepted }
     * 
     */
    public PaymentsAccepted createPaymentsAccepted() {
        return new PaymentsAccepted();
    }

    /**
     * Create an instance of {@link GuaranteeDetailList }
     * 
     */
    public GuaranteeDetailList createGuaranteeDetailList() {
        return new GuaranteeDetailList();
    }

    /**
     * Create an instance of {@link ItemGroupElement }
     * 
     */
    public ItemGroupElement createItemGroupElement() {
        return new ItemGroupElement();
    }

    /**
     * Create an instance of {@link ExtendedHotelInfo }
     * 
     */
    public ExtendedHotelInfo createExtendedHotelInfo() {
        return new ExtendedHotelInfo();
    }

    /**
     * Create an instance of {@link RatePlanList }
     * 
     */
    public RatePlanList createRatePlanList() {
        return new RatePlanList();
    }

    /**
     * Create an instance of {@link StateLov }
     * 
     */
    public StateLov createStateLov() {
        return new StateLov();
    }

    /**
     * Create an instance of {@link PackageDetailCharges }
     * 
     */
    public PackageDetailCharges createPackageDetailCharges() {
        return new PackageDetailCharges();
    }

    /**
     * Create an instance of {@link RatePlanRoomTypeList }
     * 
     */
    public RatePlanRoomTypeList createRatePlanRoomTypeList() {
        return new RatePlanRoomTypeList();
    }

    /**
     * Create an instance of {@link PackageElement }
     * 
     */
    public PackageElement createPackageElement() {
        return new PackageElement();
    }

    /**
     * Create an instance of {@link StateLovList }
     * 
     */
    public StateLovList createStateLovList() {
        return new StateLovList();
    }

    /**
     * Create an instance of {@link GdsFlags }
     * 
     */
    public GdsFlags createGdsFlags() {
        return new GdsFlags();
    }

    /**
     * Create an instance of {@link Paragraph }
     * 
     */
    public Paragraph createParagraph() {
        return new Paragraph();
    }

    /**
     * Create an instance of {@link PackageCharges }
     * 
     */
    public PackageCharges createPackageCharges() {
        return new PackageCharges();
    }

    /**
     * Create an instance of {@link HotelInfo }
     * 
     */
    public HotelInfo createHotelInfo() {
        return new HotelInfo();
    }

    /**
     * Create an instance of {@link ResPayRouting }
     * 
     */
    public ResPayRouting createResPayRouting() {
        return new ResPayRouting();
    }

    /**
     * Create an instance of {@link PackageGroupElementList }
     * 
     */
    public PackageGroupElementList createPackageGroupElementList() {
        return new PackageGroupElementList();
    }

    /**
     * Create an instance of {@link ChainInfo }
     * 
     */
    public ChainInfo createChainInfo() {
        return new ChainInfo();
    }

    /**
     * Create an instance of {@link CancelTerm }
     * 
     */
    public CancelTerm createCancelTerm() {
        return new CancelTerm();
    }

    /**
     * Create an instance of {@link LovList }
     * 
     */
    public LovList createLovList() {
        return new LovList();
    }

    /**
     * Create an instance of {@link Discount }
     * 
     */
    public Discount createDiscount() {
        return new Discount();
    }

    /**
     * Create an instance of {@link AttractionInfo }
     * 
     */
    public AttractionInfo createAttractionInfo() {
        return new AttractionInfo();
    }

    /**
     * Create an instance of {@link CancelPenalty }
     * 
     */
    public CancelPenalty createCancelPenalty() {
        return new CancelPenalty();
    }

    /**
     * Create an instance of {@link GdsTotalPricing }
     * 
     */
    public GdsTotalPricing createGdsTotalPricing() {
        return new GdsTotalPricing();
    }

    /**
     * Create an instance of {@link URIList }
     * 
     */
    public URIList createURIList() {
        return new URIList();
    }

    /**
     * Create an instance of {@link RoomStatus }
     * 
     */
    public RoomStatus createRoomStatus() {
        return new RoomStatus();
    }

    /**
     * Create an instance of {@link PaymentUsed }
     * 
     */
    public PaymentUsed createPaymentUsed() {
        return new PaymentUsed();
    }

    /**
     * Create an instance of {@link ResGuestRPHList }
     * 
     */
    public ResGuestRPHList createResGuestRPHList() {
        return new ResGuestRPHList();
    }

    /**
     * Create an instance of {@link PaymentType }
     * 
     */
    public PaymentType createPaymentType() {
        return new PaymentType();
    }

    /**
     * Create an instance of {@link TimeSpanPointsList }
     * 
     */
    public TimeSpanPointsList createTimeSpanPointsList() {
        return new TimeSpanPointsList();
    }

    /**
     * Create an instance of {@link AdditionalDetail }
     * 
     */
    public AdditionalDetail createAdditionalDetail() {
        return new AdditionalDetail();
    }

    /**
     * Create an instance of {@link VectorDirection }
     * 
     */
    public VectorDirection createVectorDirection() {
        return new VectorDirection();
    }

    /**
     * Create an instance of {@link GuestCount }
     * 
     */
    public GuestCount createGuestCount() {
        return new GuestCount();
    }

    /**
     * Create an instance of {@link AdditionalGuestAmountList }
     * 
     */
    public AdditionalGuestAmountList createAdditionalGuestAmountList() {
        return new AdditionalGuestAmountList();
    }

    /**
     * Create an instance of {@link Vector }
     * 
     */
    public Vector createVector() {
        return new Vector();
    }

    /**
     * Create an instance of {@link RoomTypeInventoryList }
     * 
     */
    public RoomTypeInventoryList createRoomTypeInventoryList() {
        return new RoomTypeInventoryList();
    }

    /**
     * Create an instance of {@link Lov }
     * 
     */
    public Lov createLov() {
        return new Lov();
    }

    /**
     * Create an instance of {@link RoomRateList }
     * 
     */
    public RoomRateList createRoomRateList() {
        return new RoomRateList();
    }

    /**
     * Create an instance of {@link TimeSpanPoints }
     * 
     */
    public TimeSpanPoints createTimeSpanPoints() {
        return new TimeSpanPoints();
    }

    /**
     * Create an instance of {@link RatePlanRoomTypes }
     * 
     */
    public RatePlanRoomTypes createRatePlanRoomTypes() {
        return new RatePlanRoomTypes();
    }

    /**
     * Create an instance of {@link ReservationCommentList }
     * 
     */
    public ReservationCommentList createReservationCommentList() {
        return new ReservationCommentList();
    }

    /**
     * Create an instance of {@link ItemElementList }
     * 
     */
    public ItemElementList createItemElementList() {
        return new ItemElementList();
    }

    /**
     * Create an instance of {@link ScreenItemElement }
     * 
     */
    public ScreenItemElement createScreenItemElement() {
        return new ScreenItemElement();
    }

    /**
     * Create an instance of {@link PackageDetail }
     * 
     */
    public PackageDetail createPackageDetail() {
        return new PackageDetail();
    }

    /**
     * Create an instance of {@link InventoryItemGroupElementList }
     * 
     */
    public InventoryItemGroupElementList createInventoryItemGroupElementList() {
        return new InventoryItemGroupElementList();
    }

    /**
     * Create an instance of {@link RoomTypeInventory }
     * 
     */
    public RoomTypeInventory createRoomTypeInventory() {
        return new RoomTypeInventory();
    }

    /**
     * Create an instance of {@link InventoryItemElement }
     * 
     */
    public InventoryItemElement createInventoryItemElement() {
        return new InventoryItemElement();
    }

    /**
     * Create an instance of {@link HotelReference }
     * 
     */
    public HotelReference createHotelReference() {
        return new HotelReference();
    }

    /**
     * Create an instance of {@link PhoneList }
     * 
     */
    public PhoneList createPhoneList() {
        return new PhoneList();
    }

    /**
     * Create an instance of {@link Rate }
     * 
     */
    public Rate createRate() {
        return new Rate();
    }

    /**
     * Create an instance of {@link RatePlan }
     * 
     */
    public RatePlan createRatePlan() {
        return new RatePlan();
    }

    /**
     * Create an instance of {@link GDSResultStatus }
     * 
     */
    public GDSResultStatus createGDSResultStatus() {
        return new GDSResultStatus();
    }

    /**
     * Create an instance of {@link TransportInfo }
     * 
     */
    public TransportInfo createTransportInfo() {
        return new TransportInfo();
    }

    /**
     * Create an instance of {@link AttractionInfoList }
     * 
     */
    public AttractionInfoList createAttractionInfoList() {
        return new AttractionInfoList();
    }

    /**
     * Create an instance of {@link GeoPosition }
     * 
     */
    public GeoPosition createGeoPosition() {
        return new GeoPosition();
    }

    /**
     * Create an instance of {@link ChargesForTheDay }
     * 
     */
    public ChargesForTheDay createChargesForTheDay() {
        return new ChargesForTheDay();
    }

    /**
     * Create an instance of {@link HotelContact }
     * 
     */
    public HotelContact createHotelContact() {
        return new HotelContact();
    }

    /**
     * Create an instance of {@link GdsTotalPricingTaxList }
     * 
     */
    public GdsTotalPricingTaxList createGdsTotalPricingTaxList() {
        return new GdsTotalPricingTaxList();
    }

    /**
     * Create an instance of {@link Voucher }
     * 
     */
    public Voucher createVoucher() {
        return new Voucher();
    }

    /**
     * Create an instance of {@link ItemGroupList }
     * 
     */
    public ItemGroupList createItemGroupList() {
        return new ItemGroupList();
    }

    /**
     * Create an instance of {@link PackageGroupElement }
     * 
     */
    public PackageGroupElement createPackageGroupElement() {
        return new PackageGroupElement();
    }

    /**
     * Create an instance of {@link AdditionalDetailList }
     * 
     */
    public AdditionalDetailList createAdditionalDetailList() {
        return new AdditionalDetailList();
    }

    /**
     * Create an instance of {@link AccompanyGuestList }
     * 
     */
    public AccompanyGuestList createAccompanyGuestList() {
        return new AccompanyGuestList();
    }

    /**
     * Create an instance of {@link RoomStayList }
     * 
     */
    public RoomStayList createRoomStayList() {
        return new RoomStayList();
    }

    /**
     * Create an instance of {@link FacilityInfoType.GuestRooms.GuestRoom }
     * 
     */
    public FacilityInfoType.GuestRooms.GuestRoom createFacilityInfoTypeGuestRoomsGuestRoom() {
        return new FacilityInfoType.GuestRooms.GuestRoom();
    }

    /**
     * Create an instance of {@link AttractionsType.Attraction }
     * 
     */
    public AttractionsType.Attraction createAttractionsTypeAttraction() {
        return new AttractionsType.Attraction();
    }

    /**
     * Create an instance of {@link AmenityInfo.Amenities }
     * 
     */
    public AmenityInfo.Amenities createAmenityInfoAmenities() {
        return new AmenityInfo.Amenities();
    }

    /**
     * Create an instance of {@link MeetingRoomsType.MeetingRoom.Codes.Code }
     * 
     */
    public MeetingRoomsType.MeetingRoom.Codes.Code createMeetingRoomsTypeMeetingRoomCodesCode() {
        return new MeetingRoomsType.MeetingRoom.Codes.Code();
    }

    /**
     * Create an instance of {@link RestaurantsType.Restaurant.Cuisines.Cuisine }
     * 
     */
    public RestaurantsType.Restaurant.Cuisines.Cuisine createRestaurantsTypeRestaurantCuisinesCuisine() {
        return new RestaurantsType.Restaurant.Cuisines.Cuisine();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/og/4.3/HotelCommon/", name = "URL", scope = Paragraph.class)
    public JAXBElement<String> createParagraphURL(String value) {
        return new JAXBElement<String>(_ParagraphURL_QNAME, String.class, Paragraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/og/4.3/HotelCommon/", name = "Image", scope = Paragraph.class)
    public JAXBElement<String> createParagraphImage(String value) {
        return new JAXBElement<String>(_ParagraphImage_QNAME, String.class, Paragraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Text }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/og/4.3/HotelCommon/", name = "Text", scope = Paragraph.class)
    public JAXBElement<Text> createParagraphText(Text value) {
        return new JAXBElement<Text>(_ParagraphText_QNAME, Text.class, Paragraph.class, value);
    }

}
