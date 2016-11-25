package com.ccm.api.service.system.pushData;

import java.util.Date;
import java.util.List;

public class Propertie {
        private String chainCode;
        private String hotelCode;
        private String roomsAvailable;
        private String startDate;
        private String channelCode;
        private List<RoomTypeSegment> roomTypeSegments;
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
        public String getRoomsAvailable() {
            return roomsAvailable;
        }
        public void setRoomsAvailable(String roomsAvailable) {
            this.roomsAvailable = roomsAvailable;
        }
        public String getChannelCode() {
            return channelCode;
        }
        public void setChannelCode(String channelCode) {
            this.channelCode = channelCode;
        }
        public List<RoomTypeSegment> getRoomTypeSegments() {
            return roomTypeSegments;
        }
        public void setRoomTypeSegments(List<RoomTypeSegment> roomTypeSegments) {
            this.roomTypeSegments = roomTypeSegments;
        }
        public String getStartDate() {
            return startDate;
        }
        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }
}
