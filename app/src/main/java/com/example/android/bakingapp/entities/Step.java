package com.example.android.bakingapp.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Step implements Serializable {
    @SerializedName("id")
    private final int id;
    @SerializedName("shortDescription")
    private final String shortDescription;
    @SerializedName("description")
    private final String description;
    @SerializedName("videoURL")
    private final String videoURL;
    @SerializedName("thumbnailURL")
    private final String thumbnailURL;

    public Step(int id, String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }
}
