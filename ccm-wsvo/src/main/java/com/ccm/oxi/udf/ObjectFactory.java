//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.10 at 03:58:32 下午 CST 
//


package com.ccm.oxi.udf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the _0._2.fidelio.udf package. 
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

    private final static QName _Udf_QNAME = new QName("udf.fidelio.2.0", "Udf");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: _0._2.fidelio.udf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Udf }
     * 
     */
    public Udf createUdf() {
        return new Udf();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Udf }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "udf.fidelio.2.0", name = "Udf")
    public JAXBElement<Udf> createUdf(Udf value) {
        return new JAXBElement<Udf>(_Udf_QNAME, Udf.class, null, value);
    }

}
