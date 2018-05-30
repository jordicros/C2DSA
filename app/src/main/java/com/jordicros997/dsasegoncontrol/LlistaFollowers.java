package com.jordicros997.dsasegoncontrol;

/**
 * Created by jordi on 29/05/2018.
 */

public class LlistaFollowers {
    private  String username;
    private String imgURL;

    public LlistaFollowers(String username, String imgURL) {
        this.username = username;
        this.imgURL = imgURL;
    }

    public LlistaFollowers() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
