package com.brokendroidproductions.favebeer;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rory on 31/07/2015.
 */
public class Labels {

    @SerializedName("medium")
    public String image_url;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


}
