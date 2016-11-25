package com.ccm.api.model.availability;

public class AvailabilityReqVo {
    private String startDate;
    private String endDate;
    private String chainCode;
    private String hotelCode;
    private Integer numberOfRooms;
    private Integer totalNumberOfGuests;
    
    
    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }
    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }
    public Integer getTotalNumberOfGuests() {
        return totalNumberOfGuests;
    }
    public void setTotalNumberOfGuests(Integer totalNumberOfGuests) {
        this.totalNumberOfGuests = totalNumberOfGuests;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
    
}
