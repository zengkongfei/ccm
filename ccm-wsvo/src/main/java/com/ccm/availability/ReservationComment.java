
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ReservationComment complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ReservationComment">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph">
 *       &lt;attribute name="commentOriginatorCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="guestViewable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReservationComment")
public class ReservationComment
    extends Paragraph
{

    @XmlAttribute(name = "commentOriginatorCode")
    protected String commentOriginatorCode;
    @XmlAttribute(name = "guestViewable")
    protected Boolean guestViewable;

    /**
     * 获取commentOriginatorCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommentOriginatorCode() {
        return commentOriginatorCode;
    }

    /**
     * 设置commentOriginatorCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommentOriginatorCode(String value) {
        this.commentOriginatorCode = value;
    }

    /**
     * 获取guestViewable属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGuestViewable() {
        return guestViewable;
    }

    /**
     * 设置guestViewable属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGuestViewable(Boolean value) {
        this.guestViewable = value;
    }

}
