//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 04:23:24GMT+08:00 
//


package _0._2.fidelio.udfdefinition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AllUdfDefinitions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AllUdfDefinitions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UdfDefinitions" type="{udfdefinition.fidelio.2.0}UdfDefinitions"/>
 *         &lt;element name="ProfileUdfDefinitions" type="{udfdefinition.fidelio.2.0}ProfileUdfDefinitions"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AllUdfDefinitions", propOrder = {
    "udfDefinitions",
    "profileUdfDefinitions"
})
public class AllUdfDefinitions {

    @XmlElement(name = "UdfDefinitions", required = true)
    protected UdfDefinitions udfDefinitions;
    @XmlElement(name = "ProfileUdfDefinitions", required = true)
    protected ProfileUdfDefinitions profileUdfDefinitions;

    /**
     * Gets the value of the udfDefinitions property.
     * 
     * @return
     *     possible object is
     *     {@link UdfDefinitions }
     *     
     */
    public UdfDefinitions getUdfDefinitions() {
        return udfDefinitions;
    }

    /**
     * Sets the value of the udfDefinitions property.
     * 
     * @param value
     *     allowed object is
     *     {@link UdfDefinitions }
     *     
     */
    public void setUdfDefinitions(UdfDefinitions value) {
        this.udfDefinitions = value;
    }

    /**
     * Gets the value of the profileUdfDefinitions property.
     * 
     * @return
     *     possible object is
     *     {@link ProfileUdfDefinitions }
     *     
     */
    public ProfileUdfDefinitions getProfileUdfDefinitions() {
        return profileUdfDefinitions;
    }

    /**
     * Sets the value of the profileUdfDefinitions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfileUdfDefinitions }
     *     
     */
    public void setProfileUdfDefinitions(ProfileUdfDefinitions value) {
        this.profileUdfDefinitions = value;
    }

}
