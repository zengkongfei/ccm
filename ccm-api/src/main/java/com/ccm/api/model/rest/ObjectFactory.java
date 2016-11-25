//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.26 at 10:34:21   CST 
//


package com.ccm.api.model.rest;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccm.api.model.rest package. 
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

    private final static QName _PaymentType_QNAME = new QName("", "PaymentType");
    private final static QName _Status_QNAME = new QName("", "status");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccm.api.model.rest
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Guarantee }
     * 
     */
    public Guarantee createGuarantee() {
        return new Guarantee();
    }

    /**
     * Create an instance of {@link CancelPolicy }
     * 
     */
    public CancelPolicy createCancelPolicy() {
        return new CancelPolicy();
    }

    /**
     * Create an instance of {@link RatePlan }
     * 
     */
    public RatePlan createRatePlan() {
        return new RatePlan();
    }

    /**
     * Create an instance of {@link RatePlan.Name }
     * 
     */
    public RatePlan.Name createRatePlanName() {
        return new RatePlan.Name();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "PaymentType")
    public JAXBElement<Short> createPaymentType(Short value) {
        return new JAXBElement<Short>(_PaymentType_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "status")
    public JAXBElement<Short> createStatus(Short value) {
        return new JAXBElement<Short>(_Status_QNAME, Short.class, null, value);
    }

}