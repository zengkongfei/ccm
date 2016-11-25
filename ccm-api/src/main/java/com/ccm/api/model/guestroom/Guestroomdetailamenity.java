package com.ccm.api.model.guestroom;

import java.io.Serializable;

public class Guestroomdetailamenity implements Serializable {

    private static final long serialVersionUID = 136850315558307940L;

    /**
     * column guestroomdetailamenity.guestRoomDetailAmenityId
     */
    private String guestroomdetailamenityid;

    /**
     * column guestroomdetailamenity.roomDetailId
     */
    private String roomdetailid;

    /**
     * column guestroomdetailamenity.codeValue
     */
    private Integer codevalue;

    public Guestroomdetailamenity() {
        super();
    }

    public Guestroomdetailamenity(String guestroomdetailamenityid, String roomdetailid, Integer codevalue) {
        this.guestroomdetailamenityid = guestroomdetailamenityid;
        this.roomdetailid = roomdetailid;
        this.codevalue = codevalue;
    }

    /**
     * getter for Column guestroomdetailamenity.guestRoomDetailAmenityId
     */
    public String getGuestroomdetailamenityid() {
        return guestroomdetailamenityid;
    }

    /**
     * setter for Column guestroomdetailamenity.guestRoomDetailAmenityId
     * @param guestroomdetailamenityid
     */
    public void setGuestroomdetailamenityid(String guestroomdetailamenityid) {
        this.guestroomdetailamenityid = guestroomdetailamenityid;
    }

    /**
     * getter for Column guestroomdetailamenity.roomDetailId
     */
    public String getRoomdetailid() {
        return roomdetailid;
    }

    /**
     * setter for Column guestroomdetailamenity.roomDetailId
     * @param roomdetailid
     */
    public void setRoomdetailid(String roomdetailid) {
        this.roomdetailid = roomdetailid;
    }

    /**
     * getter for Column guestroomdetailamenity.codeValue
     */
    public Integer getCodevalue() {
        return codevalue;
    }

    /**
     * setter for Column guestroomdetailamenity.codeValue
     * @param codevalue
     */
    public void setCodevalue(Integer codevalue) {
        this.codevalue = codevalue;
    }

}