package com.ccm.api.model.guestroom;

import java.io.Serializable;

public class Guestroomamenity implements Serializable {

    private static final long serialVersionUID = 136850315101152713L;

    /**
     * column guestroomamenity.guestRoomAmenityId
     */
    private String guestroomamenityId;

    /**
     * column guestroomamenity.guestRoomId
     */
    private String guestroomid;

    /**
     * column guestroomamenity.codeValue
     */
    private String codeNo;

    private Boolean delFlag;


    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getCodeNo() {
        return codeNo;
    }

    public void setCodeNo(String codeNo) {
        this.codeNo = codeNo;
    }


    public String getGuestroomamenityId() {
        return guestroomamenityId;
    }

    public void setGuestroomamenityId(String guestroomamenityId) {
        this.guestroomamenityId = guestroomamenityId;
    }

    /**
     * getter for Column guestroomamenity.guestRoomId
     */
    public String getGuestroomid() {
        return guestroomid;
    }

    /**
     * setter for Column guestroomamenity.guestRoomId
     * @param guestroomid
     */
    public void setGuestroomid(String guestroomid) {
        this.guestroomid = guestroomid;
    }


}