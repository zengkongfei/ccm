
package com.ccm.reservation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Collection of KeyWord
 * 
 * <p>KeyWordList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="KeyWordList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KeyWord" type="{http://webservices.micros.com/og/4.3/Name/}KeyWord" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KeyWordList", namespace = "http://webservices.micros.com/og/4.3/Name/", propOrder = {
    "keyWord"
})
public class KeyWordList {

    @XmlElement(name = "KeyWord")
    protected List<KeyWord> keyWord;

    /**
     * Gets the value of the keyWord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keyWord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeyWord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KeyWord }
     * 
     * 
     */
    public List<KeyWord> getKeyWord() {
        if (keyWord == null) {
            keyWord = new ArrayList<KeyWord>();
        }
        return this.keyWord;
    }

}
