package com.ccm.api.service.system.pushData;

import java.util.Date;
import java.util.List;

public class FidelloAvailabilityStatus extends BaseInfo{
    private String userAgent;
    private String echoToken;
    private Date timeStamp;
    private List<Propertie> propertieList;
    
    public String getUserAgent() {
        return userAgent;
    }
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
    public String getEchoToken() {
        return echoToken;
    }
    public void setEchoToken(String echoToken) {
        this.echoToken = echoToken;
    }
    public Date getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
    public List<Propertie> getPropertieList() {
        return propertieList;
    }
    public void setPropertieList(List<Propertie> propertieList) {
        this.propertieList = propertieList;
    }
}
