//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.17 at 04:00:55 下午 CST 
//


package com.ccm.oxi.ravl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the _0._2.fidelio.ravl package. 
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

    private final static QName _Ravl_QNAME = new QName("ravl.fidelio.2.0", "Ravl");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: _0._2.fidelio.ravl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TimeSpan }
     * 
     */
    public TimeSpan createTimeSpan() {
        return new TimeSpan();
    }

    /**
     * Create an instance of {@link Ravl }
     * 
     */
    public Ravl createRavl() {
        return new Ravl();
    }

    /**
     * Create an instance of {@link HotelReference }
     * 
     */
    public HotelReference createHotelReference() {
        return new HotelReference();
    }

    /**
     * Create an instance of {@link RavlDetails }
     * 
     */
    public RavlDetails createRavlDetails() {
        return new RavlDetails();
    }

    /**
     * Create an instance of {@link DaysOfWeek }
     * 
     */
    public DaysOfWeek createDaysOfWeek() {
        return new DaysOfWeek();
    }

    /**
     * Create an instance of {@link RavlDetail }
     * 
     */
    public RavlDetail createRavlDetail() {
        return new RavlDetail();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Ravl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ravl.fidelio.2.0", name = "Ravl")
    public JAXBElement<Ravl> createRavl(Ravl value) {
        return new JAXBElement<Ravl>(_Ravl_QNAME, Ravl.class, null, value);
    }

}