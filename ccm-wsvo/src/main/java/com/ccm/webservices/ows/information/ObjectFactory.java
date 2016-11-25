
package com.ccm.webservices.ows.information;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.micros.webservices.ows._5_1.information package. 
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

    private final static QName _LovQueryTypeLovQueryQualifier_QNAME = new QName("http://webservices.micros.com/ows/5.1/Information.wsdl", "LovQueryQualifier");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.micros.webservices.ows._5_1.information
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HotelInformationResponse }
     * 
     */
    public HotelInformationResponse createHotelInformationResponse() {
        return new HotelInformationResponse();
    }

    /**
     * Create an instance of {@link HotelInformationRequest }
     * 
     */
    public HotelInformationRequest createHotelInformationRequest() {
        return new HotelInformationRequest();
    }

    /**
     * Create an instance of {@link PackageItemsRequest }
     * 
     */
    public PackageItemsRequest createPackageItemsRequest() {
        return new PackageItemsRequest();
    }

    /**
     * Create an instance of {@link LovResponse }
     * 
     */
    public LovResponse createLovResponse() {
        return new LovResponse();
    }

    /**
     * Create an instance of {@link LovQueryResultType }
     * 
     */
    public LovQueryResultType createLovQueryResultType() {
        return new LovQueryResultType();
    }

    /**
     * Create an instance of {@link ScreenItemsResponse }
     * 
     */
    public ScreenItemsResponse createScreenItemsResponse() {
        return new ScreenItemsResponse();
    }

    /**
     * Create an instance of {@link ScreenItemsRequest }
     * 
     */
    public ScreenItemsRequest createScreenItemsRequest() {
        return new ScreenItemsRequest();
    }

    /**
     * Create an instance of {@link ChainInformationRequest }
     * 
     */
    public ChainInformationRequest createChainInformationRequest() {
        return new ChainInformationRequest();
    }

    /**
     * Create an instance of {@link HotelInformationResponse.HotelInformation }
     * 
     */
    public HotelInformationResponse.HotelInformation createHotelInformationResponseHotelInformation() {
        return new HotelInformationResponse.HotelInformation();
    }

    /**
     * Create an instance of {@link RateResponse }
     * 
     */
    public RateResponse createRateResponse() {
        return new RateResponse();
    }

    /**
     * Create an instance of {@link CurrencyConverterResponse }
     * 
     */
    public CurrencyConverterResponse createCurrencyConverterResponse() {
        return new CurrencyConverterResponse();
    }

    /**
     * Create an instance of {@link CurrencyConverterRequest }
     * 
     */
    public CurrencyConverterRequest createCurrencyConverterRequest() {
        return new CurrencyConverterRequest();
    }

    /**
     * Create an instance of {@link AwardsSchedulesRequest }
     * 
     */
    public AwardsSchedulesRequest createAwardsSchedulesRequest() {
        return new AwardsSchedulesRequest();
    }

    /**
     * Create an instance of {@link LovRequest }
     * 
     */
    public LovRequest createLovRequest() {
        return new LovRequest();
    }

    /**
     * Create an instance of {@link LovQueryType }
     * 
     */
    public LovQueryType createLovQueryType() {
        return new LovQueryType();
    }

    /**
     * Create an instance of {@link LovQueryType2 }
     * 
     */
    public LovQueryType2 createLovQueryType2() {
        return new LovQueryType2();
    }

    /**
     * Create an instance of {@link AwardsSchedulesResponse }
     * 
     */
    public AwardsSchedulesResponse createAwardsSchedulesResponse() {
        return new AwardsSchedulesResponse();
    }

    /**
     * Create an instance of {@link ChainInformationResponse }
     * 
     */
    public ChainInformationResponse createChainInformationResponse() {
        return new ChainInformationResponse();
    }

    /**
     * Create an instance of {@link PackageItemsResponse }
     * 
     */
    public PackageItemsResponse createPackageItemsResponse() {
        return new PackageItemsResponse();
    }

    /**
     * Create an instance of {@link RateRequest }
     * 
     */
    public RateRequest createRateRequest() {
        return new RateRequest();
    }

    /**
     * Create an instance of {@link RateQueryType }
     * 
     */
    public RateQueryType createRateQueryType() {
        return new RateQueryType();
    }

    /**
     * Create an instance of {@link LovQueryQualifierType }
     * 
     */
    public LovQueryQualifierType createLovQueryQualifierType() {
        return new LovQueryQualifierType();
    }

    /**
     * Create an instance of {@link TimeSpanType }
     * 
     */
    public TimeSpanType createTimeSpanType() {
        return new TimeSpanType();
    }

    /**
     * Create an instance of {@link LovValueType }
     * 
     */
    public LovValueType createLovValueType() {
        return new LovValueType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LovQueryQualifierType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.micros.com/ows/5.1/Information.wsdl", name = "LovQueryQualifier", scope = LovQueryType.class)
    public JAXBElement<LovQueryQualifierType> createLovQueryTypeLovQueryQualifier(LovQueryQualifierType value) {
        return new JAXBElement<LovQueryQualifierType>(_LovQueryTypeLovQueryQualifier_QNAME, LovQueryQualifierType.class, LovQueryType.class, value);
    }

}
