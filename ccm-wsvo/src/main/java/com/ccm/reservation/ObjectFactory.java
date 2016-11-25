
package com.ccm.reservation;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccm.reservation package. 
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

    private final static QName _Profile_QNAME = new QName("http://webservices.micros.com/og/4.3/Name/", "Profile");
    private final static QName _OGHeader_QNAME = new QName("http://webservices.micros.com/og/4.3/Core/", "OGHeader");
    private final static QName _ParagraphURL_QNAME = new QName("http://webservices.micros.com/og/4.3/HotelCommon/", "URL");
    private final static QName _ParagraphImage_QNAME = new QName("http://webservices.micros.com/og/4.3/HotelCommon/", "Image");
    private final static QName _ParagraphText_QNAME = new QName("http://webservices.micros.com/og/4.3/HotelCommon/", "Text");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccm.reservation
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FutureBookingSummaryRequest }
     * 
     */
    public FutureBookingSummaryRequest createFutureBookingSummaryRequest() {
        return new FutureBookingSummaryRequest();
    }

    /**
     * Create an instance of {@link Phone }
     * 
     */
    public Phone createPhone() {
        return new Phone();
    }

    /**
     * Create an instance of {@link Profile }
     * 
     */
    public Profile createProfile() {
        return new Profile();
    }

    /**
     * Create an instance of {@link HotelReservationList }
     * 
     */
    public HotelReservationList createHotelReservationList() {
        return new HotelReservationList();
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
     * Create an instance of {@link OGHeader }
     * 
     */
    public OGHeader createOGHeader() {
        return new OGHeader();
    }

    /**
     * Create an instance of {@link OGHeader.Authentication }
     * 
     */
    public OGHeader.Authentication createOGHeaderAuthentication() {
        return new OGHeader.Authentication();
    }

    /**
     * Create an instance of {@link SystemID }
     * 
     */
    public SystemID createSystemID() {
        return new SystemID();
    }

    /**
     * Create an instance of {@link EndPoint }
     * 
     */
    public EndPoint createEndPoint() {
        return new EndPoint();
    }

    /**
     * Create an instance of {@link EndPointList }
     * 
     */
    public EndPointList createEndPointList() {
        return new EndPointList();
    }

    /**
     * Create an instance of {@link GovernmentIDList }
     * 
     */
    public GovernmentIDList createGovernmentIDList() {
        return new GovernmentIDList();
    }

    /**
     * Create an instance of {@link PersonName }
     * 
     */
    public PersonName createPersonName() {
        return new PersonName();
    }

    /**
     * Create an instance of {@link Membership }
     * 
     */
    public Membership createMembership() {
        return new Membership();
    }

    /**
     * Create an instance of {@link UniqueID }
     * 
     */
    public UniqueID createUniqueID() {
        return new UniqueID();
    }

    /**
     * Create an instance of {@link CreditCard }
     * 
     */
    public CreditCard createCreditCard() {
        return new CreditCard();
    }

    /**
     * Create an instance of {@link UserDefinedValueList }
     * 
     */
    public UserDefinedValueList createUserDefinedValueList() {
        return new UserDefinedValueList();
    }

    /**
     * Create an instance of {@link ClaimCommentsList }
     * 
     */
    public ClaimCommentsList createClaimCommentsList() {
        return new ClaimCommentsList();
    }

    /**
     * Create an instance of {@link ClaimComment }
     * 
     */
    public ClaimComment createClaimComment() {
        return new ClaimComment();
    }

    /**
     * Create an instance of {@link UserDefinedValue }
     * 
     */
    public UserDefinedValue createUserDefinedValue() {
        return new UserDefinedValue();
    }

    /**
     * Create an instance of {@link History }
     * 
     */
    public History createHistory() {
        return new History();
    }

    /**
     * Create an instance of {@link UniqueIDList }
     * 
     */
    public UniqueIDList createUniqueIDList() {
        return new UniqueIDList();
    }

    /**
     * Create an instance of {@link ClaimsList }
     * 
     */
    public ClaimsList createClaimsList() {
        return new ClaimsList();
    }

    /**
     * Create an instance of {@link EncryptedSwipe }
     * 
     */
    public EncryptedSwipe createEncryptedSwipe() {
        return new EncryptedSwipe();
    }

    /**
     * Create an instance of {@link Amount }
     * 
     */
    public Amount createAmount() {
        return new Amount();
    }

    /**
     * Create an instance of {@link Claim }
     * 
     */
    public Claim createClaim() {
        return new Claim();
    }

    /**
     * Create an instance of {@link DescriptiveText }
     * 
     */
    public DescriptiveText createDescriptiveText() {
        return new DescriptiveText();
    }

    /**
     * Create an instance of {@link AwardPointsToExpireList }
     * 
     */
    public AwardPointsToExpireList createAwardPointsToExpireList() {
        return new AwardPointsToExpireList();
    }

    /**
     * Create an instance of {@link GovernmentID }
     * 
     */
    public GovernmentID createGovernmentID() {
        return new GovernmentID();
    }

    /**
     * Create an instance of {@link Text }
     * 
     */
    public Text createText() {
        return new Text();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link IDPair }
     * 
     */
    public IDPair createIDPair() {
        return new IDPair();
    }

    /**
     * Create an instance of {@link KeyTrack }
     * 
     */
    public KeyTrack createKeyTrack() {
        return new KeyTrack();
    }

    /**
     * Create an instance of {@link IDPairList }
     * 
     */
    public IDPairList createIDPairList() {
        return new IDPairList();
    }

    /**
     * Create an instance of {@link ResultStatus }
     * 
     */
    public ResultStatus createResultStatus() {
        return new ResultStatus();
    }

    /**
     * Create an instance of {@link AwardPointsToExpire }
     * 
     */
    public AwardPointsToExpire createAwardPointsToExpire() {
        return new AwardPointsToExpire();
    }

    /**
     * Create an instance of {@link GuestProfile }
     * 
     */
    public GuestProfile createGuestProfile() {
        return new GuestProfile();
    }

    /**
     * Create an instance of {@link TextList }
     * 
     */
    public TextList createTextList() {
        return new TextList();
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
     * Create an instance of {@link FetchMemberTierWizardResponse }
     * 
     */
    public FetchMemberTierWizardResponse createFetchMemberTierWizardResponse() {
        return new FetchMemberTierWizardResponse();
    }

    /**
     * Create an instance of {@link TierWizard }
     * 
     */
    public TierWizard createTierWizard() {
        return new TierWizard();
    }

    /**
     * Create an instance of {@link FetchBenefitsRequest }
     * 
     */
    public FetchBenefitsRequest createFetchBenefitsRequest() {
        return new FetchBenefitsRequest();
    }

    /**
     * Create an instance of {@link FetchMemberTierWizardRequest }
     * 
     */
    public FetchMemberTierWizardRequest createFetchMemberTierWizardRequest() {
        return new FetchMemberTierWizardRequest();
    }

    /**
     * Create an instance of {@link FetchBenefitsResponse }
     * 
     */
    public FetchBenefitsResponse createFetchBenefitsResponse() {
        return new FetchBenefitsResponse();
    }

    /**
     * Create an instance of {@link Benefit }
     * 
     */
    public Benefit createBenefit() {
        return new Benefit();
    }

    /**
     * Create an instance of {@link AddBenefitResponse }
     * 
     */
    public AddBenefitResponse createAddBenefitResponse() {
        return new AddBenefitResponse();
    }

    /**
     * Create an instance of {@link AddBenefitRequest }
     * 
     */
    public AddBenefitRequest createAddBenefitRequest() {
        return new AddBenefitRequest();
    }

    /**
     * Create an instance of {@link MembershipTransaction }
     * 
     */
    public MembershipTransaction createMembershipTransaction() {
        return new MembershipTransaction();
    }

    /**
     * Create an instance of {@link PromotionSubscription }
     * 
     */
    public PromotionSubscription createPromotionSubscription() {
        return new PromotionSubscription();
    }

    /**
     * Create an instance of {@link UpgradeAward }
     * 
     */
    public UpgradeAward createUpgradeAward() {
        return new UpgradeAward();
    }

    /**
     * Create an instance of {@link Promotion }
     * 
     */
    public Promotion createPromotion() {
        return new Promotion();
    }

    /**
     * Create an instance of {@link FavoriteGuest }
     * 
     */
    public FavoriteGuest createFavoriteGuest() {
        return new FavoriteGuest();
    }

    /**
     * Create an instance of {@link Statement }
     * 
     */
    public Statement createStatement() {
        return new Statement();
    }

    /**
     * Create an instance of {@link RuleSchedule }
     * 
     */
    public RuleSchedule createRuleSchedule() {
        return new RuleSchedule();
    }

    /**
     * Create an instance of {@link StatementReferenceList }
     * 
     */
    public StatementReferenceList createStatementReferenceList() {
        return new StatementReferenceList();
    }

    /**
     * Create an instance of {@link PointsBreakupList }
     * 
     */
    public PointsBreakupList createPointsBreakupList() {
        return new PointsBreakupList();
    }

    /**
     * Create an instance of {@link ProfilePromotionList }
     * 
     */
    public ProfilePromotionList createProfilePromotionList() {
        return new ProfilePromotionList();
    }

    /**
     * Create an instance of {@link PromotionSubscriptionList }
     * 
     */
    public PromotionSubscriptionList createPromotionSubscriptionList() {
        return new PromotionSubscriptionList();
    }

    /**
     * Create an instance of {@link PointsChange }
     * 
     */
    public PointsChange createPointsChange() {
        return new PointsChange();
    }

    /**
     * Create an instance of {@link PointsBreakup }
     * 
     */
    public PointsBreakup createPointsBreakup() {
        return new PointsBreakup();
    }

    /**
     * Create an instance of {@link ProductAward }
     * 
     */
    public ProductAward createProductAward() {
        return new ProductAward();
    }

    /**
     * Create an instance of {@link StatementReference }
     * 
     */
    public StatementReference createStatementReference() {
        return new StatementReference();
    }

    /**
     * Create an instance of {@link ProfilePromotion }
     * 
     */
    public ProfilePromotion createProfilePromotion() {
        return new ProfilePromotion();
    }

    /**
     * Create an instance of {@link ECertificateList }
     * 
     */
    public ECertificateList createECertificateList() {
        return new ECertificateList();
    }

    /**
     * Create an instance of {@link AwardInformationList }
     * 
     */
    public AwardInformationList createAwardInformationList() {
        return new AwardInformationList();
    }

    /**
     * Create an instance of {@link PointsSchedule }
     * 
     */
    public PointsSchedule createPointsSchedule() {
        return new PointsSchedule();
    }

    /**
     * Create an instance of {@link MemberPointsList }
     * 
     */
    public MemberPointsList createMemberPointsList() {
        return new MemberPointsList();
    }

    /**
     * Create an instance of {@link MembershipTransactionList }
     * 
     */
    public MembershipTransactionList createMembershipTransactionList() {
        return new MembershipTransactionList();
    }

    /**
     * Create an instance of {@link AwardInformation }
     * 
     */
    public AwardInformation createAwardInformation() {
        return new AwardInformation();
    }

    /**
     * Create an instance of {@link Points }
     * 
     */
    public Points createPoints() {
        return new Points();
    }

    /**
     * Create an instance of {@link UpgradeAwardList }
     * 
     */
    public UpgradeAwardList createUpgradeAwardList() {
        return new UpgradeAwardList();
    }

    /**
     * Create an instance of {@link ECertificate }
     * 
     */
    public ECertificate createECertificate() {
        return new ECertificate();
    }

    /**
     * Create an instance of {@link RateAward }
     * 
     */
    public RateAward createRateAward() {
        return new RateAward();
    }

    /**
     * Create an instance of {@link FavoriteGuestList }
     * 
     */
    public FavoriteGuestList createFavoriteGuestList() {
        return new FavoriteGuestList();
    }

    /**
     * Create an instance of {@link RateAwardList }
     * 
     */
    public RateAwardList createRateAwardList() {
        return new RateAwardList();
    }

    /**
     * Create an instance of {@link Tsc }
     * 
     */
    public Tsc createTsc() {
        return new Tsc();
    }

    /**
     * Create an instance of {@link PromotionDetails }
     * 
     */
    public PromotionDetails createPromotionDetails() {
        return new PromotionDetails();
    }

    /**
     * Create an instance of {@link PointsChangeList }
     * 
     */
    public PointsChangeList createPointsChangeList() {
        return new PointsChangeList();
    }

    /**
     * Create an instance of {@link ProductAwardList }
     * 
     */
    public ProductAwardList createProductAwardList() {
        return new ProductAwardList();
    }

    /**
     * Create an instance of {@link Award }
     * 
     */
    public Award createAward() {
        return new Award();
    }

    /**
     * Create an instance of {@link EmailConfirmationResponse }
     * 
     */
    public EmailConfirmationResponse createEmailConfirmationResponse() {
        return new EmailConfirmationResponse();
    }

    /**
     * Create an instance of {@link FutureBookingSummaryResponse }
     * 
     */
    public FutureBookingSummaryResponse createFutureBookingSummaryResponse() {
        return new FutureBookingSummaryResponse();
    }

    /**
     * Create an instance of {@link IgnoreBookingRequest }
     * 
     */
    public IgnoreBookingRequest createIgnoreBookingRequest() {
        return new IgnoreBookingRequest();
    }

    /**
     * Create an instance of {@link BookHoldItemsResponse }
     * 
     */
    public BookHoldItemsResponse createBookHoldItemsResponse() {
        return new BookHoldItemsResponse();
    }

    /**
     * Create an instance of {@link ReleaseRoomResponse }
     * 
     */
    public ReleaseRoomResponse createReleaseRoomResponse() {
        return new ReleaseRoomResponse();
    }

    /**
     * Create an instance of {@link DeletePackagesResponse }
     * 
     */
    public DeletePackagesResponse createDeletePackagesResponse() {
        return new DeletePackagesResponse();
    }

    /**
     * Create an instance of {@link FetchHoldItemsResponse }
     * 
     */
    public FetchHoldItemsResponse createFetchHoldItemsResponse() {
        return new FetchHoldItemsResponse();
    }

    /**
     * Create an instance of {@link ModifyBookingRequest }
     * 
     */
    public ModifyBookingRequest createModifyBookingRequest() {
        return new ModifyBookingRequest();
    }

    /**
     * Create an instance of {@link com.ccm.reservation.HotelReservation }
     * 
     */
    public com.ccm.reservation.HotelReservation createHotelReservation() {
        return new com.ccm.reservation.HotelReservation();
    }

    /**
     * Create an instance of {@link ExternalReference }
     * 
     */
    public ExternalReference createExternalReference() {
        return new ExternalReference();
    }

    /**
     * Create an instance of {@link ModifyItemHoldResponse }
     * 
     */
    public ModifyItemHoldResponse createModifyItemHoldResponse() {
        return new ModifyItemHoldResponse();
    }

    /**
     * Create an instance of {@link AssignRoomRequest }
     * 
     */
    public AssignRoomRequest createAssignRoomRequest() {
        return new AssignRoomRequest();
    }

    /**
     * Create an instance of {@link FetchBookingRequest }
     * 
     */
    public FetchBookingRequest createFetchBookingRequest() {
        return new FetchBookingRequest();
    }

    /**
     * Create an instance of {@link UpgradeReservationRequest }
     * 
     */
    public UpgradeReservationRequest createUpgradeReservationRequest() {
        return new UpgradeReservationRequest();
    }

    /**
     * Create an instance of {@link UpgradeRoom }
     * 
     */
    public UpgradeRoom createUpgradeRoom() {
        return new UpgradeRoom();
    }

    /**
     * Create an instance of {@link SetDailyPointsResponse }
     * 
     */
    public SetDailyPointsResponse createSetDailyPointsResponse() {
        return new SetDailyPointsResponse();
    }

    /**
     * Create an instance of {@link CancelBookingResponse }
     * 
     */
    public CancelBookingResponse createCancelBookingResponse() {
        return new CancelBookingResponse();
    }

    /**
     * Create an instance of {@link FetchBookedInventoryItemsRequest }
     * 
     */
    public FetchBookedInventoryItemsRequest createFetchBookedInventoryItemsRequest() {
        return new FetchBookedInventoryItemsRequest();
    }

    /**
     * Create an instance of {@link CreateBookingResponse }
     * 
     */
    public CreateBookingResponse createCreateBookingResponse() {
        return new CreateBookingResponse();
    }

    /**
     * Create an instance of {@link FetchBookedInventoryItemsResponse }
     * 
     */
    public FetchBookedInventoryItemsResponse createFetchBookedInventoryItemsResponse() {
        return new FetchBookedInventoryItemsResponse();
    }

    /**
     * Create an instance of {@link FetchBookedPackagesRequest }
     * 
     */
    public FetchBookedPackagesRequest createFetchBookedPackagesRequest() {
        return new FetchBookedPackagesRequest();
    }

    /**
     * Create an instance of {@link DeletePackagesRequest }
     * 
     */
    public DeletePackagesRequest createDeletePackagesRequest() {
        return new DeletePackagesRequest();
    }

    /**
     * Create an instance of {@link FetchAvailableOffersResponse }
     * 
     */
    public FetchAvailableOffersResponse createFetchAvailableOffersResponse() {
        return new FetchAvailableOffersResponse();
    }

    /**
     * Create an instance of {@link UpsellRoomList }
     * 
     */
    public UpsellRoomList createUpsellRoomList() {
        return new UpsellRoomList();
    }

    /**
     * Create an instance of {@link CreateBookingRequest }
     * 
     */
    public CreateBookingRequest createCreateBookingRequest() {
        return new CreateBookingRequest();
    }

    /**
     * Create an instance of {@link ReleaseRoomRequest }
     * 
     */
    public ReleaseRoomRequest createReleaseRoomRequest() {
        return new ReleaseRoomRequest();
    }

    /**
     * Create an instance of {@link DeleteAccompanyGuestRequest }
     * 
     */
    public DeleteAccompanyGuestRequest createDeleteAccompanyGuestRequest() {
        return new DeleteAccompanyGuestRequest();
    }

    /**
     * Create an instance of {@link FetchHoldItemsRequest }
     * 
     */
    public FetchHoldItemsRequest createFetchHoldItemsRequest() {
        return new FetchHoldItemsRequest();
    }

    /**
     * Create an instance of {@link ConfirmBookingResponse }
     * 
     */
    public ConfirmBookingResponse createConfirmBookingResponse() {
        return new ConfirmBookingResponse();
    }

    /**
     * Create an instance of {@link FetchRoomUpgradesResponse }
     * 
     */
    public FetchRoomUpgradesResponse createFetchRoomUpgradesResponse() {
        return new FetchRoomUpgradesResponse();
    }

    /**
     * Create an instance of {@link UpgradeRoomList }
     * 
     */
    public UpgradeRoomList createUpgradeRoomList() {
        return new UpgradeRoomList();
    }

    /**
     * Create an instance of {@link FetchBookingResponse }
     * 
     */
    public FetchBookingResponse createFetchBookingResponse() {
        return new FetchBookingResponse();
    }

    /**
     * Create an instance of {@link AddAccompanyGuestRequest }
     * 
     */
    public AddAccompanyGuestRequest createAddAccompanyGuestRequest() {
        return new AddAccompanyGuestRequest();
    }

    /**
     * Create an instance of {@link FetchSummaryResponse }
     * 
     */
    public FetchSummaryResponse createFetchSummaryResponse() {
        return new FetchSummaryResponse();
    }

    /**
     * Create an instance of {@link CancelBookingRequest }
     * 
     */
    public CancelBookingRequest createCancelBookingRequest() {
        return new CancelBookingRequest();
    }

    /**
     * Create an instance of {@link CreateItemHoldRequest }
     * 
     */
    public CreateItemHoldRequest createCreateItemHoldRequest() {
        return new CreateItemHoldRequest();
    }

    /**
     * Create an instance of {@link UpsellReservationResponse }
     * 
     */
    public UpsellReservationResponse createUpsellReservationResponse() {
        return new UpsellReservationResponse();
    }

    /**
     * Create an instance of {@link UpdatePackagesResponse }
     * 
     */
    public UpdatePackagesResponse createUpdatePackagesResponse() {
        return new UpdatePackagesResponse();
    }

    /**
     * Create an instance of {@link ClearItemHoldRequest }
     * 
     */
    public ClearItemHoldRequest createClearItemHoldRequest() {
        return new ClearItemHoldRequest();
    }

    /**
     * Create an instance of {@link ClearItemHoldResponse }
     * 
     */
    public ClearItemHoldResponse createClearItemHoldResponse() {
        return new ClearItemHoldResponse();
    }

    /**
     * Create an instance of {@link ConfirmBookingRequest }
     * 
     */
    public ConfirmBookingRequest createConfirmBookingRequest() {
        return new ConfirmBookingRequest();
    }

    /**
     * Create an instance of {@link AddAccompanyGuestResponse }
     * 
     */
    public AddAccompanyGuestResponse createAddAccompanyGuestResponse() {
        return new AddAccompanyGuestResponse();
    }

    /**
     * Create an instance of {@link UpdateInventoryItemRequest }
     * 
     */
    public UpdateInventoryItemRequest createUpdateInventoryItemRequest() {
        return new UpdateInventoryItemRequest();
    }

    /**
     * Create an instance of {@link CreateItemHoldResponse }
     * 
     */
    public CreateItemHoldResponse createCreateItemHoldResponse() {
        return new CreateItemHoldResponse();
    }

    /**
     * Create an instance of {@link UpdateInventoryItemResponse }
     * 
     */
    public UpdateInventoryItemResponse createUpdateInventoryItemResponse() {
        return new UpdateInventoryItemResponse();
    }

    /**
     * Create an instance of {@link FetchSummaryRequest }
     * 
     */
    public FetchSummaryRequest createFetchSummaryRequest() {
        return new FetchSummaryRequest();
    }

    /**
     * Create an instance of {@link DeleteInventoryItemRequest }
     * 
     */
    public DeleteInventoryItemRequest createDeleteInventoryItemRequest() {
        return new DeleteInventoryItemRequest();
    }

    /**
     * Create an instance of {@link FetchBookedPackagesResponse }
     * 
     */
    public FetchBookedPackagesResponse createFetchBookedPackagesResponse() {
        return new FetchBookedPackagesResponse();
    }

    /**
     * Create an instance of {@link IgnoreBookingResponse }
     * 
     */
    public IgnoreBookingResponse createIgnoreBookingResponse() {
        return new IgnoreBookingResponse();
    }

    /**
     * Create an instance of {@link ModifyItemHoldRequest }
     * 
     */
    public ModifyItemHoldRequest createModifyItemHoldRequest() {
        return new ModifyItemHoldRequest();
    }

    /**
     * Create an instance of {@link FutureBookingSummaryRequest.QueryDateRange }
     * 
     */
    public FutureBookingSummaryRequest.QueryDateRange createFutureBookingSummaryRequestQueryDateRange() {
        return new FutureBookingSummaryRequest.QueryDateRange();
    }

    /**
     * Create an instance of {@link FetchBookingFilters }
     * 
     */
    public FetchBookingFilters createFetchBookingFilters() {
        return new FetchBookingFilters();
    }

    /**
     * Create an instance of {@link FetchRoomUpgradesRequest }
     * 
     */
    public FetchRoomUpgradesRequest createFetchRoomUpgradesRequest() {
        return new FetchRoomUpgradesRequest();
    }

    /**
     * Create an instance of {@link DeleteAccompanyGuestResponse }
     * 
     */
    public DeleteAccompanyGuestResponse createDeleteAccompanyGuestResponse() {
        return new DeleteAccompanyGuestResponse();
    }

    /**
     * Create an instance of {@link BookHoldItemsRequest }
     * 
     */
    public BookHoldItemsRequest createBookHoldItemsRequest() {
        return new BookHoldItemsRequest();
    }

    /**
     * Create an instance of {@link AssignRoomResponse }
     * 
     */
    public AssignRoomResponse createAssignRoomResponse() {
        return new AssignRoomResponse();
    }

    /**
     * Create an instance of {@link FetchBookingForPointUpdateRequest }
     * 
     */
    public FetchBookingForPointUpdateRequest createFetchBookingForPointUpdateRequest() {
        return new FetchBookingForPointUpdateRequest();
    }

    /**
     * Create an instance of {@link EmailConfirmationRequest }
     * 
     */
    public EmailConfirmationRequest createEmailConfirmationRequest() {
        return new EmailConfirmationRequest();
    }

    /**
     * Create an instance of {@link DeleteInventoryItemResponse }
     * 
     */
    public DeleteInventoryItemResponse createDeleteInventoryItemResponse() {
        return new DeleteInventoryItemResponse();
    }

    /**
     * Create an instance of {@link FetchAvailableOffersRequest }
     * 
     */
    public FetchAvailableOffersRequest createFetchAvailableOffersRequest() {
        return new FetchAvailableOffersRequest();
    }

    /**
     * Create an instance of {@link UpgradeReservationResponse }
     * 
     */
    public UpgradeReservationResponse createUpgradeReservationResponse() {
        return new UpgradeReservationResponse();
    }

    /**
     * Create an instance of {@link UpdatePackagesRequest }
     * 
     */
    public UpdatePackagesRequest createUpdatePackagesRequest() {
        return new UpdatePackagesRequest();
    }

    /**
     * Create an instance of {@link ModifyBookingResponse }
     * 
     */
    public ModifyBookingResponse createModifyBookingResponse() {
        return new ModifyBookingResponse();
    }

    /**
     * Create an instance of {@link UpsellReservationRequest }
     * 
     */
    public UpsellReservationRequest createUpsellReservationRequest() {
        return new UpsellReservationRequest();
    }

    /**
     * Create an instance of {@link FetchBookingForPointUpdateResponse }
     * 
     */
    public FetchBookingForPointUpdateResponse createFetchBookingForPointUpdateResponse() {
        return new FetchBookingForPointUpdateResponse();
    }

    /**
     * Create an instance of {@link SetDailyPointsRequest }
     * 
     */
    public SetDailyPointsRequest createSetDailyPointsRequest() {
        return new SetDailyPointsRequest();
    }

    /**
     * Create an instance of {@link CreditCardSurcharge }
     * 
     */
    public CreditCardSurcharge createCreditCardSurcharge() {
        return new CreditCardSurcharge();
    }

    /**
     * Create an instance of {@link BillHeader }
     * 
     */
    public BillHeader createBillHeader() {
        return new BillHeader();
    }

    /**
     * Create an instance of {@link BillItem }
     * 
     */
    public BillItem createBillItem() {
        return new BillItem();
    }

    /**
     * Create an instance of {@link ResGuestList }
     * 
     */
    public ResGuestList createResGuestList() {
        return new ResGuestList();
    }

    /**
     * Create an instance of {@link UpsellRoom }
     * 
     */
    public UpsellRoom createUpsellRoom() {
        return new UpsellRoom();
    }

    /**
     * Create an instance of {@link ResGuest }
     * 
     */
    public ResGuest createResGuest() {
        return new ResGuest();
    }

    /**
     * Create an instance of {@link ReservationGuarantee }
     * 
     */
    public ReservationGuarantee createReservationGuarantee() {
        return new ReservationGuarantee();
    }

    /**
     * Create an instance of {@link WrittenConfInst }
     * 
     */
    public WrittenConfInst createWrittenConfInst() {
        return new WrittenConfInst();
    }

    /**
     * Create an instance of {@link BillTax }
     * 
     */
    public BillTax createBillTax() {
        return new BillTax();
    }

    /**
     * Create an instance of {@link Alert }
     * 
     */
    public Alert createAlert() {
        return new Alert();
    }

    /**
     * Create an instance of {@link NameCreditCard }
     * 
     */
    public NameCreditCard createNameCreditCard() {
        return new NameCreditCard();
    }

    /**
     * Create an instance of {@link ProfileList }
     * 
     */
    public ProfileList createProfileList() {
        return new ProfileList();
    }

    /**
     * Create an instance of {@link NameAddress }
     * 
     */
    public NameAddress createNameAddress() {
        return new NameAddress();
    }

    /**
     * Create an instance of {@link NameAddressList }
     * 
     */
    public NameAddressList createNameAddressList() {
        return new NameAddressList();
    }

    /**
     * Create an instance of {@link NamePhoneList }
     * 
     */
    public NamePhoneList createNamePhoneList() {
        return new NamePhoneList();
    }

    /**
     * Create an instance of {@link KeyWord }
     * 
     */
    public KeyWord createKeyWord() {
        return new KeyWord();
    }

    /**
     * Create an instance of {@link NameIdNameAddressList }
     * 
     */
    public NameIdNameAddressList createNameIdNameAddressList() {
        return new NameIdNameAddressList();
    }

    /**
     * Create an instance of {@link NameLookupCriteriaMembership }
     * 
     */
    public NameLookupCriteriaMembership createNameLookupCriteriaMembership() {
        return new NameLookupCriteriaMembership();
    }

    /**
     * Create an instance of {@link NegotiatedRateList }
     * 
     */
    public NegotiatedRateList createNegotiatedRateList() {
        return new NegotiatedRateList();
    }

    /**
     * Create an instance of {@link NamePhone }
     * 
     */
    public NamePhone createNamePhone() {
        return new NamePhone();
    }

    /**
     * Create an instance of {@link NativeName }
     * 
     */
    public NativeName createNativeName() {
        return new NativeName();
    }

    /**
     * Create an instance of {@link com.ccm.reservation.UserGroup }
     * 
     */
    public com.ccm.reservation.UserGroup createUserGroup() {
        return new com.ccm.reservation.UserGroup();
    }

    /**
     * Create an instance of {@link PreferenceList }
     * 
     */
    public PreferenceList createPreferenceList() {
        return new PreferenceList();
    }

    /**
     * Create an instance of {@link PrivacyPromptData }
     * 
     */
    public PrivacyPromptData createPrivacyPromptData() {
        return new PrivacyPromptData();
    }

    /**
     * Create an instance of {@link NameLookupCriteriaEmailAddress }
     * 
     */
    public NameLookupCriteriaEmailAddress createNameLookupCriteriaEmailAddress() {
        return new NameLookupCriteriaEmailAddress();
    }

    /**
     * Create an instance of {@link Company }
     * 
     */
    public Company createCompany() {
        return new Company();
    }

    /**
     * Create an instance of {@link NameMembership }
     * 
     */
    public NameMembership createNameMembership() {
        return new NameMembership();
    }

    /**
     * Create an instance of {@link NameCreditCardList }
     * 
     */
    public NameCreditCardList createNameCreditCardList() {
        return new NameCreditCardList();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link NegotiatedRate }
     * 
     */
    public NegotiatedRate createNegotiatedRate() {
        return new NegotiatedRate();
    }

    /**
     * Create an instance of {@link NameEmail }
     * 
     */
    public NameEmail createNameEmail() {
        return new NameEmail();
    }

    /**
     * Create an instance of {@link KeyWordList }
     * 
     */
    public KeyWordList createKeyWordList() {
        return new KeyWordList();
    }

    /**
     * Create an instance of {@link PrivacyOptionType }
     * 
     */
    public PrivacyOptionType createPrivacyOptionType() {
        return new PrivacyOptionType();
    }

    /**
     * Create an instance of {@link Comment }
     * 
     */
    public Comment createComment() {
        return new Comment();
    }

    /**
     * Create an instance of {@link NameLookupInput }
     * 
     */
    public NameLookupInput createNameLookupInput() {
        return new NameLookupInput();
    }

    /**
     * Create an instance of {@link NameEmailList }
     * 
     */
    public NameEmailList createNameEmailList() {
        return new NameEmailList();
    }

    /**
     * Create an instance of {@link NameIdNameAddress }
     * 
     */
    public NameIdNameAddress createNameIdNameAddress() {
        return new NameIdNameAddress();
    }

    /**
     * Create an instance of {@link NameMembershipList }
     * 
     */
    public NameMembershipList createNameMembershipList() {
        return new NameMembershipList();
    }

    /**
     * Create an instance of {@link BlackList }
     * 
     */
    public BlackList createBlackList() {
        return new BlackList();
    }

    /**
     * Create an instance of {@link CommentList }
     * 
     */
    public CommentList createCommentList() {
        return new CommentList();
    }

    /**
     * Create an instance of {@link StayHistoryData }
     * 
     */
    public StayHistoryData createStayHistoryData() {
        return new StayHistoryData();
    }

    /**
     * Create an instance of {@link PrivacyList }
     * 
     */
    public PrivacyList createPrivacyList() {
        return new PrivacyList();
    }

    /**
     * Create an instance of {@link NameLookupCriteriaCreditCard }
     * 
     */
    public NameLookupCriteriaCreditCard createNameLookupCriteriaCreditCard() {
        return new NameLookupCriteriaCreditCard();
    }

    /**
     * Create an instance of {@link Preference }
     * 
     */
    public Preference createPreference() {
        return new Preference();
    }

    /**
     * Create an instance of {@link Phone.PhoneData }
     * 
     */
    public Phone.PhoneData createPhonePhoneData() {
        return new Phone.PhoneData();
    }

    /**
     * Create an instance of {@link Profile.UserGroup }
     * 
     */
    public Profile.UserGroup createProfileUserGroup() {
        return new Profile.UserGroup();
    }

    /**
     * Create an instance of {@link HotelReservationList.HotelReservation }
     * 
     */
    public HotelReservationList.HotelReservation createHotelReservationListHotelReservation() {
        return new HotelReservationList.HotelReservation();
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
     * Create an instance of {@link OGHeader.Authentication.UserCredentials }
     * 
     */
    public OGHeader.Authentication.UserCredentials createOGHeaderAuthenticationUserCredentials() {
        return new OGHeader.Authentication.UserCredentials();
    }

    /**
     * Create an instance of {@link OGHeader.Authentication.Licence }
     * 
     */
    public OGHeader.Authentication.Licence createOGHeaderAuthenticationLicence() {
        return new OGHeader.Authentication.Licence();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Profile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/og/4.3/Name/", name = "Profile")
    public JAXBElement<Profile> createProfile(Profile value) {
        return new JAXBElement<Profile>(_Profile_QNAME, Profile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OGHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/og/4.3/Core/", name = "OGHeader")
    public JAXBElement<OGHeader> createOGHeader(OGHeader value) {
        return new JAXBElement<OGHeader>(_OGHeader_QNAME, OGHeader.class, null, value);
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
