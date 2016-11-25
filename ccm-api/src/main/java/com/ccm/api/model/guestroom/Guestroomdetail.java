package com.ccm.api.model.guestroom;

import java.io.Serializable;
import java.util.Date;

public class Guestroomdetail implements Serializable {

    private static final long serialVersionUID = 136850315395658579L;

    /**
     * column guestroomdetail.roomDetailId
     */
    private String roomdetailid;

    /**
     * column guestroomdetail.guestRoomId
     */
    private String guestroomid;

    /**
     * column guestroomdetail.hall
     */
    private String hall;

    /**
     * column guestroomdetail.floor
     */
    private Integer floor;

    /**
     * column guestroomdetail.roomNo
     */
    private String roomno;

    /**
     * column guestroomdetail.bedCount
     */
    private Integer bedcount;

    /**
     * column guestroomdetail.roomStatus
     */
    private Integer roomstatus;

    /**
     * column guestroomdetail.roomSize
     */
    private Float roomsize;

    /**
     * column guestroomdetail.createdTime
     */
    private Date createdtime;

    /**
     * column guestroomdetail.lastModifyTime
     */
    private Date lastmodifytime;

    public Guestroomdetail() {
        super();
    }

    public Guestroomdetail(String roomdetailid, String guestroomid, String hall, Integer floor, String roomno, Integer bedcount, Integer roomstatus, Float roomsize, Date createdtime, Date lastmodifytime) {
        this.roomdetailid = roomdetailid;
        this.guestroomid = guestroomid;
        this.hall = hall;
        this.floor = floor;
        this.roomno = roomno;
        this.bedcount = bedcount;
        this.roomstatus = roomstatus;
        this.roomsize = roomsize;
        this.createdtime = createdtime;
        this.lastmodifytime = lastmodifytime;
    }

    /**
     * getter for Column guestroomdetail.roomDetailId
     */
    public String getRoomdetailid() {
        return roomdetailid;
    }

    /**
     * setter for Column guestroomdetail.roomDetailId
     * @param roomdetailid
     */
    public void setRoomdetailid(String roomdetailid) {
        this.roomdetailid = roomdetailid;
    }

    /**
     * getter for Column guestroomdetail.guestRoomId
     */
    public String getGuestroomid() {
        return guestroomid;
    }

    /**
     * setter for Column guestroomdetail.guestRoomId
     * @param guestroomid
     */
    public void setGuestroomid(String guestroomid) {
        this.guestroomid = guestroomid;
    }

    /**
     * getter for Column guestroomdetail.hall
     */
    public String getHall() {
        return hall;
    }

    /**
     * setter for Column guestroomdetail.hall
     * @param hall
     */
    public void setHall(String hall) {
        this.hall = hall;
    }

    /**
     * getter for Column guestroomdetail.floor
     */
    public Integer getFloor() {
        return floor;
    }

    /**
     * setter for Column guestroomdetail.floor
     * @param floor
     */
    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    /**
     * getter for Column guestroomdetail.roomNo
     */
    public String getRoomno() {
        return roomno;
    }

    /**
     * setter for Column guestroomdetail.roomNo
     * @param roomno
     */
    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    /**
     * getter for Column guestroomdetail.bedCount
     */
    public Integer getBedcount() {
        return bedcount;
    }

    /**
     * setter for Column guestroomdetail.bedCount
     * @param bedcount
     */
    public void setBedcount(Integer bedcount) {
        this.bedcount = bedcount;
    }

    /**
     * getter for Column guestroomdetail.roomStatus
     */
    public Integer getRoomstatus() {
        return roomstatus;
    }

    /**
     * setter for Column guestroomdetail.roomStatus
     * @param roomstatus
     */
    public void setRoomstatus(Integer roomstatus) {
        this.roomstatus = roomstatus;
    }

    /**
     * getter for Column guestroomdetail.roomSize
     */
    public Float getRoomsize() {
        return roomsize;
    }

    /**
     * setter for Column guestroomdetail.roomSize
     * @param roomsize
     */
    public void setRoomsize(Float roomsize) {
        this.roomsize = roomsize;
    }

    /**
     * getter for Column guestroomdetail.createdTime
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * setter for Column guestroomdetail.createdTime
     * @param createdtime
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * getter for Column guestroomdetail.lastModifyTime
     */
    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    /**
     * setter for Column guestroomdetail.lastModifyTime
     * @param lastmodifytime
     */
    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

}