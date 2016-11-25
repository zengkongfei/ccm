
package com.ccm.webservices.og.hotelcommon;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import com.ccm.webservices.og.common.Text;


/**
 * <p>Paragraph complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Paragraph">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="99">
 *         &lt;element name="Text" type="{http://webservices.micros.com/og/4.3/Common/}Text"/>
 *         &lt;element name="Image" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Paragraph", propOrder = {
    "textOrImageOrURL"
})
@XmlSeeAlso({
    SpecialRequest.class,
    ReservationComment.class
})
public class Paragraph {

    @XmlElementRefs({
        @XmlElementRef(name = "Image", namespace = "http://webservices.micros.com/og/4.3/HotelCommon/", type = JAXBElement.class),
        @XmlElementRef(name = "URL", namespace = "http://webservices.micros.com/og/4.3/HotelCommon/", type = JAXBElement.class),
        @XmlElementRef(name = "Text", namespace = "http://webservices.micros.com/og/4.3/HotelCommon/", type = JAXBElement.class)
    })
    protected List<JAXBElement<?>> textOrImageOrURL;

    /**
     * Gets the value of the textOrImageOrURL property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the textOrImageOrURL property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTextOrImageOrURL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Text }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getTextOrImageOrURL() {
        if (textOrImageOrURL == null) {
            textOrImageOrURL = new ArrayList<JAXBElement<?>>();
        }
        return this.textOrImageOrURL;
    }

}
