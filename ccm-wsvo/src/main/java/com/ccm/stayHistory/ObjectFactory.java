
package com.ccm.stayHistory;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccm.stayHistory package. 
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

    private final static QName _StayInfo_QNAME = new QName("http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayInfo/", "StayInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccm.stayHistory
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StayRequestResponse }
     * 
     */
    public StayRequestResponse createStayRequestResponse() {
        return new StayRequestResponse();
    }

    /**
     * Create an instance of {@link StayRequest }
     * 
     */
    public StayRequest createStayRequest() {
        return new StayRequest();
    }

    /**
     * Create an instance of {@link ErrorInfoType }
     * 
     */
    public ErrorInfoType createErrorInfoType() {
        return new ErrorInfoType();
    }

    /**
     * Create an instance of {@link HotelReferenceListType }
     * 
     */
    public HotelReferenceListType createHotelReferenceListType() {
        return new HotelReferenceListType();
    }

    /**
     * Create an instance of {@link ConfirmationIDListType }
     * 
     */
    public ConfirmationIDListType createConfirmationIDListType() {
        return new ConfirmationIDListType();
    }

    /**
     * Create an instance of {@link ArrayOfHotelReference }
     * 
     */
    public ArrayOfHotelReference createArrayOfHotelReference() {
        return new ArrayOfHotelReference();
    }

    /**
     * Create an instance of {@link StayRequestResponse.StayRequestResult }
     * 
     */
    public StayRequestResponse.StayRequestResult createStayRequestResponseStayRequestResult() {
        return new StayRequestResponse.StayRequestResult();
    }

    /**
     * Create an instance of {@link StayRequest.StayHistoryRequest }
     * 
     */
    public StayRequest.StayHistoryRequest createStayRequestStayHistoryRequest() {
        return new StayRequest.StayHistoryRequest();
    }

    /**
     * Create an instance of {@link StayHistoryQueryType }
     * 
     */
    public StayHistoryQueryType createStayHistoryQueryType() {
        return new StayHistoryQueryType();
    }

    /**
     * Create an instance of {@link ADSInfoType }
     * 
     */
    public ADSInfoType createADSInfoType() {
        return new ADSInfoType();
    }

    /**
     * Create an instance of {@link ArrayOfStayInfoDetail }
     * 
     */
    public ArrayOfStayInfoDetail createArrayOfStayInfoDetail() {
        return new ArrayOfStayInfoDetail();
    }

    /**
     * Create an instance of {@link StayInfoDetail }
     * 
     */
    public StayInfoDetail createStayInfoDetail() {
        return new StayInfoDetail();
    }

    /**
     * Create an instance of {@link TimeSpanType }
     * 
     */
    public TimeSpanType createTimeSpanType() {
        return new TimeSpanType();
    }

    /**
     * Create an instance of {@link ErrorInfoType.ErrorInfo }
     * 
     */
    public ErrorInfoType.ErrorInfo createErrorInfoTypeErrorInfo() {
        return new ErrorInfoType.ErrorInfo();
    }

    /**
     * Create an instance of {@link HotelReferenceListType.HotelReference }
     * 
     */
    public HotelReferenceListType.HotelReference createHotelReferenceListTypeHotelReference() {
        return new HotelReferenceListType.HotelReference();
    }

    /**
     * Create an instance of {@link ConfirmationIDListType.ConfirmationID }
     * 
     */
    public ConfirmationIDListType.ConfirmationID createConfirmationIDListTypeConfirmationID() {
        return new ConfirmationIDListType.ConfirmationID();
    }

    /**
     * Create an instance of {@link ArrayOfHotelReference.HotelReference }
     * 
     */
    public ArrayOfHotelReference.HotelReference createArrayOfHotelReferenceHotelReference() {
        return new ArrayOfHotelReference.HotelReference();
    }

    /**
     * Create an instance of {@link StayRequestResponse.StayRequestResult.Result }
     * 
     */
    public StayRequestResponse.StayRequestResult.Result createStayRequestResponseStayRequestResultResult() {
        return new StayRequestResponse.StayRequestResult.Result();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfStayInfoDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayInfo/", name = "StayInfo")
    public JAXBElement<ArrayOfStayInfoDetail> createStayInfo(ArrayOfStayInfoDetail value) {
        return new JAXBElement<ArrayOfStayInfoDetail>(_StayInfo_QNAME, ArrayOfStayInfoDetail.class, null, value);
    }

}
