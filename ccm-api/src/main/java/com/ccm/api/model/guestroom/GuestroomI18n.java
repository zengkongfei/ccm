package com.ccm.api.model.guestroom;

import java.io.Serializable;
public class GuestroomI18n implements Serializable {

    private static final long serialVersionUID = 136850511947265968L;

    /**
     * column guestroom_i18n.guestRoomMId
     */
    private String guestroommId;

    /**
     * column guestroom_i18n.guestRoomId
     */
    private String guestroomid;

    /**
     * column guestroom_i18n.language
     */
    private String language;

    /**
     * column guestroom_i18n.roomTypeName
     */
    private String roomtypename;

    /**
     * column guestroom_i18n.guestRoomDesc
     */
    private String guestroomdesc;

    /**
     * column guestroom_i18n.guide
     */
    private String guide;
    
    
    
    public String getGuestroommId() {
        return guestroommId;
    }

    public void setGuestroommId(String guestroommId) {
        this.guestroommId = guestroommId;
    }

    /**
     * getter for Column guestroom_i18n.guestRoomId
     */
    public String getGuestroomid() {
        return guestroomid;
    }

    /**
     * setter for Column guestroom_i18n.guestRoomId
     * @param guestroomid
     */
    public void setGuestroomid(String guestroomid) {
        this.guestroomid = guestroomid;
    }

    /**
     * getter for Column guestroom_i18n.language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * setter for Column guestroom_i18n.language
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * getter for Column guestroom_i18n.roomTypeName
     */
    public String getRoomtypename() {
        return roomtypename;
    }

    /**
     * setter for Column guestroom_i18n.roomTypeName
     * @param roomtypename
     */
    public void setRoomtypename(String roomtypename) {
        this.roomtypename = roomtypename;
    }

    /**
     * getter for Column guestroom_i18n.guestRoomDesc
     */
    public String getGuestroomdesc() {
        return guestroomdesc;
    }

    /**
     * setter for Column guestroom_i18n.guestRoomDesc
     * @param guestroomdesc
     */
    public void setGuestroomdesc(String guestroomdesc) {
        this.guestroomdesc = guestroomdesc;
    }

    /**
     * getter for Column guestroom_i18n.guide
     */
    public String getGuide() {
        return guide;
    }

    /**
     * setter for Column guestroom_i18n.guide
     * @param guide
     */
    public void setGuide(String guide) {
        this.guide = guide;
    }

}