package com.example.wallpaper.Models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private  String id;

    @SerializedName("username")
    private String username;

    @SerializedName("profile_image")
    private ProfileImages profileImages = new ProfileImages();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ProfileImages getProfileImages() {
        return profileImages;
    }

    public void setProfileImages(ProfileImages profileImages) {
        this.profileImages = profileImages;
    }
}
