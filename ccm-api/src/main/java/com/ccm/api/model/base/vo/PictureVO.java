/**
 * 
 */
package com.ccm.api.model.base.vo;

/**
 * @author Jenny Liao
 * 
 */
public class PictureVO {

    private String roomTypeId;
    private String url;
    private String title;

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the roomTypeId
     */
    public String getRoomTypeId() {
        return roomTypeId;
    }

    /**
     * @param roomTypeId
     *            the roomTypeId to set
     */
    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

}
