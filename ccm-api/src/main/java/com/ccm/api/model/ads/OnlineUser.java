package com.ccm.api.model.ads;

public class OnlineUser {
    
    private String onlineUserId;
    private String chainCode;
    private String userName;
    private String userId;
    private boolean isPush;
    
    public String getOnlineUserId() {
        return onlineUserId;
    }
    public void setOnlineUserId(String onlineUserId) {
        this.onlineUserId = onlineUserId;
    }
    public String getChainCode() {
        return chainCode;
    }
    public void setChainCode(String chainCode) {
        this.chainCode = chainCode.trim();
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName != null ? userName.trim() : null;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId.trim();
    }
    public boolean isPush() {
        return isPush;
    }
    public void setPush(boolean isPush) {
        this.isPush = isPush;
    }
}
