
package com.ccm.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RateCodeInformationList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RateCodeInformationList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RateCodeInformation" type="{http://webservices.micros.com/og/4.3/Availability/}RateCodeInformation" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RateCodeInformationList", namespace = "http://webservices.micros.com/og/4.3/Availability/", propOrder = {
    "rateCodeInformation"
})
public class RateCodeInformationList {

    @XmlElement(name = "RateCodeInformation", required = true)
    protected List<RateCodeInformation> rateCodeInformation;

    /**
     * Gets the value of the rateCodeInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rateCodeInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRateCodeInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RateCodeInformation }
     * 
     * 
     */
    public List<RateCodeInformation> getRateCodeInformation() {
        if (rateCodeInformation == null) {
            rateCodeInformation = new ArrayList<RateCodeInformation>();
        }
        return this.rateCodeInformation;
    }

}
