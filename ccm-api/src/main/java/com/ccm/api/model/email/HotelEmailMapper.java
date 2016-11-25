package com.ccm.api.model.email;

public class HotelEmailMapper {
    
    
    private String hotelEmailMapperId;
    private String chainCode;
    private String hotelCode;
    private String email;
    private String description;
    public String getHotelEmailMapperId() {
        return hotelEmailMapperId;
    }
    public void setHotelEmailMapperId(String hotelEmailMapperId) {
        this.hotelEmailMapperId = hotelEmailMapperId;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
