package com.brokendroidproductions.favebeer;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rory on 31/07/2015.
 */
public class Data {

    @SerializedName("name")
    private String name;

    public Labels labels;

    public Labels getLabels() {
        return labels;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
