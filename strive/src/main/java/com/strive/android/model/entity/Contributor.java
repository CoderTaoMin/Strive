package com.strive.android.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 清风徐来 on 2017/6/27
 * Contributor entity
 */
public class Contributor {
    private String login;
    private Integer contributions;
    @SerializedName("avatar_url")
    private String avatar;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getContributions() {
        return contributions;
    }

    public void setContributions(Integer contributions) {
        this.contributions = contributions;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
