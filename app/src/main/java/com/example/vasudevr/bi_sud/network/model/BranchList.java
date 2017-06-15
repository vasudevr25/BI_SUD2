package com.example.vasudevr.bi_sud.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vasudevr on 6/15/2017.
 */

public class BranchList {

    @SerializedName("BranchId")
    @Expose
    private Integer branchId;
    @SerializedName("BranchName")
    @Expose
    private String branchName;
    @SerializedName("ChannelId")
    @Expose
    private Integer channelId;
    @SerializedName("CityId")
    @Expose
    private Integer cityId;

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
