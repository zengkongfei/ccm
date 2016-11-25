package com.ccm.api.model.ads;

public class ChainannelRateIdMapper {
    
    private String chainannelrateidmapperId;
    private String chainCode;
    private String hotelCode;
    private String roomType;
    private String rateCode;
    private String chainannelProductCode;
    
    public String getChainannelrateidmapperId() {
        return chainannelrateidmapperId;
    }
    public void setChainannelrateidmapperId(String chainannelrateidmapperId) {
        this.chainannelrateidmapperId = chainannelrateidmapperId;
    }
    public String getChainCode() {
        return chainCode;
    }
    public void setChainCode(String chainCode) {
        this.chainCode = chainCode;
    }
    public String getHotelCode() {
        return hotelCode;
    }
    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }
    public String getRoomType() {
        return roomType;
    }
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    
    public String getRateCode() {
        return rateCode;
    }
    public void setRateCode(String rateCode) {
        this.rateCode = rateCode;
    }
    public String getChainannelProductCode() {
        return chainannelProductCode;
    }
    public void setChainannelProductCode(String chainannelProductCode) {
        this.chainannelProductCode = chainannelProductCode;
    }
    @Override
    public String toString() {
        return "[chainannelrateidmapperId:"+chainannelrateidmapperId+"," +
        		" chainCode:"+chainCode+", hotelCode:"+hotelCode+", roomType:"+roomType+", rateCode:"+rateCode+
        		", chainannelProductCode:"+chainannelProductCode+"]";
    }
}
