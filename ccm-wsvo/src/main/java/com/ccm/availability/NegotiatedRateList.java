
package com.ccm.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Collection of NegotiatedRate
 * 
 * <p>NegotiatedRateList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="NegotiatedRateList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NegotiatedRate" type="{http://webservices.micros.com/og/4.3/Name/}NegotiatedRate" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NegotiatedRateList", namespace = "http://webservices.micros.com/og/4.3/Name/", propOrder = {
    "negotiatedRate"
})
public class NegotiatedRateList {

    @XmlElement(name = "NegotiatedRate")
    protected List<NegotiatedRate> negotiatedRate;

    /**
     * Gets the value of the negotiatedRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the negotiatedRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNegotiatedRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NegotiatedRate }
     * 
     * 
     */
    public List<NegotiatedRate> getNegotiatedRate() {
        if (negotiatedRate == null) {
            negotiatedRate = new ArrayList<NegotiatedRate>();
        }
        return this.negotiatedRate;
    }

}
