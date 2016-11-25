//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 04:23:24GMT+08:00 
//


package _0._2.fidelio.profile;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the _0._2.fidelio.profile package. 
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

    private final static QName _CreditCard_QNAME = new QName("profile.fidelio.2.0", "CreditCard");
    private final static QName _Profile_QNAME = new QName("profile.fidelio.2.0", "Profile");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: _0._2.fidelio.profile
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Document }
     * 
     */
    public Document createDocument() {
        return new Document();
    }

    /**
     * Create an instance of {@link Comments }
     * 
     */
    public Comments createComments() {
        return new Comments();
    }

    /**
     * Create an instance of {@link ElectronicAddresses }
     * 
     */
    public ElectronicAddresses createElectronicAddresses() {
        return new ElectronicAddresses();
    }

    /**
     * Create an instance of {@link Comment }
     * 
     */
    public Comment createComment() {
        return new Comment();
    }

    /**
     * Create an instance of {@link MfNationalName }
     * 
     */
    public MfNationalName createMfNationalName() {
        return new MfNationalName();
    }

    /**
     * Create an instance of {@link SpecialRequests }
     * 
     */
    public SpecialRequests createSpecialRequests() {
        return new SpecialRequests();
    }

    /**
     * Create an instance of {@link CreditCard }
     * 
     */
    public CreditCard createCreditCard() {
        return new CreditCard();
    }

    /**
     * Create an instance of {@link ElectronicAddress }
     * 
     */
    public ElectronicAddress createElectronicAddress() {
        return new ElectronicAddress();
    }

    /**
     * Create an instance of {@link CreditLimit }
     * 
     */
    public CreditLimit createCreditLimit() {
        return new CreditLimit();
    }

    /**
     * Create an instance of {@link PostalAddress }
     * 
     */
    public PostalAddress createPostalAddress() {
        return new PostalAddress();
    }

    /**
     * Create an instance of {@link ProfileCertification }
     * 
     */
    public ProfileCertification createProfileCertification() {
        return new ProfileCertification();
    }

    /**
     * Create an instance of {@link PhoneNumbers }
     * 
     */
    public PhoneNumbers createPhoneNumbers() {
        return new PhoneNumbers();
    }

    /**
     * Create an instance of {@link PhoneNumber }
     * 
     */
    public PhoneNumber createPhoneNumber() {
        return new PhoneNumber();
    }

    /**
     * Create an instance of {@link ProfileCertifications }
     * 
     */
    public ProfileCertifications createProfileCertifications() {
        return new ProfileCertifications();
    }

    /**
     * Create an instance of {@link CreditCards }
     * 
     */
    public CreditCards createCreditCards() {
        return new CreditCards();
    }

    /**
     * Create an instance of {@link IndividualName }
     * 
     */
    public IndividualName createIndividualName() {
        return new IndividualName();
    }

    /**
     * Create an instance of {@link YieldAdjustments }
     * 
     */
    public YieldAdjustments createYieldAdjustments() {
        return new YieldAdjustments();
    }

    /**
     * Create an instance of {@link Memberships }
     * 
     */
    public Memberships createMemberships() {
        return new Memberships();
    }

    /**
     * Create an instance of {@link AdjustmentAmount }
     * 
     */
    public AdjustmentAmount createAdjustmentAmount() {
        return new AdjustmentAmount();
    }

    /**
     * Create an instance of {@link Profile }
     * 
     */
    public Profile createProfile() {
        return new Profile();
    }

    /**
     * Create an instance of {@link Adjustment }
     * 
     */
    public Adjustment createAdjustment() {
        return new Adjustment();
    }

    /**
     * Create an instance of {@link NegotiatedRate }
     * 
     */
    public NegotiatedRate createNegotiatedRate() {
        return new NegotiatedRate();
    }

    /**
     * Create an instance of {@link PostalAddresses }
     * 
     */
    public PostalAddresses createPostalAddresses() {
        return new PostalAddresses();
    }

    /**
     * Create an instance of {@link Udfs }
     * 
     */
    public Udfs createUdfs() {
        return new Udfs();
    }

    /**
     * Create an instance of {@link SpecialRequest }
     * 
     */
    public SpecialRequest createSpecialRequest() {
        return new SpecialRequest();
    }

    /**
     * Create an instance of {@link Documents }
     * 
     */
    public Documents createDocuments() {
        return new Documents();
    }

    /**
     * Create an instance of {@link Membership }
     * 
     */
    public Membership createMembership() {
        return new Membership();
    }

    /**
     * Create an instance of {@link MfNegotiatedRates }
     * 
     */
    public MfNegotiatedRates createMfNegotiatedRates() {
        return new MfNegotiatedRates();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditCard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "profile.fidelio.2.0", name = "CreditCard")
    public JAXBElement<CreditCard> createCreditCard(CreditCard value) {
        return new JAXBElement<CreditCard>(_CreditCard_QNAME, CreditCard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Profile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "profile.fidelio.2.0", name = "Profile")
    public JAXBElement<Profile> createProfile(Profile value) {
        return new JAXBElement<Profile>(_Profile_QNAME, Profile.class, null, value);
    }

}