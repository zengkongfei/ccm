package com.ccm.api.model.channel;

import java.io.Serializable;
import java.util.Date;

public class Channelguestroom implements Serializable {

    private static final long serialVersionUID = 136850188264213658L;

    /**
     * column channelguestroom.channelGuestRoomId
     */
    private String channelguestroomId;

    /**
     * column channelguestroom.channelId
     */
    private String channelid;

    /**
     * column channelguestroom.guestRoomId
     */
    private String guestroomid;

    /**
     * column channelguestroom.channelRoomTypeCode
     */
    private String channelroomtypecode;

    /**
     * column channelguestroom.addTime
     */
    private Date addtime;

    /**
     * column channelguestroom.delTime
     */
    private Date deltime;

    /**
     * column channelguestroom.createdTime
     */
    private Date createdtime;

    /**
     * column channelguestroom.lastModifyTime
     */
    private Date lastmodifytime;

    private Long matchStatus;
    private String outerId;
    

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId;
    }

    public String getChannelguestroomId() {
        return channelguestroomId;
    }

    public void setChannelguestroomId(String channelguestroomId) {
        this.channelguestroomId = channelguestroomId;
    }


    /**
     * getter for Column channelguestroom.channelId
     */
    public String getChannelid() {
        return channelid;
    }

    /**
     * setter for Column channelguestroom.channelId
     * @param channelid
     */
    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    /**
     * getter for Column channelguestroom.guestRoomId
     */
    public String getGuestroomid() {
        return guestroomid;
    }

    /**
     * setter for Column channelguestroom.guestRoomId
     * @param guestroomid
     */
    public void setGuestroomid(String guestroomid) {
        this.guestroomid = guestroomid;
    }

    /**
     * getter for Column channelguestroom.channelRoomTypeCode
     */
    public String getChannelroomtypecode() {
        return channelroomtypecode;
    }

    /**
     * setter for Column channelguestroom.channelRoomTypeCode
     * @param channelroomtypecode
     */
    public void setChannelroomtypecode(String channelroomtypecode) {
        this.channelroomtypecode = channelroomtypecode;
    }

    /**
     * getter for Column channelguestroom.addTime
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * setter for Column channelguestroom.addTime
     * @param addtime
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * getter for Column channelguestroom.delTime
     */
    public Date getDeltime() {
        return deltime;
    }

    /**
     * setter for Column channelguestroom.delTime
     * @param deltime
     */
    public void setDeltime(Date deltime) {
        this.deltime = deltime;
    }

    /**
     * getter for Column channelguestroom.createdTime
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * setter for Column channelguestroom.createdTime
     * @param createdtime
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * getter for Column channelguestroom.lastModifyTime
     */
    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    /**
     * setter for Column channelguestroom.lastModifyTime
     * @param lastmodifytime
     */
    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    public Long getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(Long matchStatus) {
        this.matchStatus = matchStatus;
    }

}