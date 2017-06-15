package com.example.vasudevr.bi_sud.network.model;

/**
 * Created by vasudevr on 6/15/2017.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductList {

    @SerializedName("CategoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("CompanyId")
    @Expose
    private Integer companyId;
    @SerializedName("CurrentStatus")
    @Expose
    private Object currentStatus;
    @SerializedName("EndDate")
    @Expose
    private Object endDate;
    @SerializedName("EntryStartBy")
    @Expose
    private Object entryStartBy;
    @SerializedName("EntryStartDate")
    @Expose
    private Object entryStartDate;
    @SerializedName("IsPension")
    @Expose
    private Boolean isPension;
    @SerializedName("Islive")
    @Expose
    private Boolean islive;
    @SerializedName("LastEditBy")
    @Expose
    private Object lastEditBy;
    @SerializedName("LastEditDate")
    @Expose
    private Object lastEditDate;
    @SerializedName("Platform")
    @Expose
    private String platform;
    @SerializedName("ProductId")
    @Expose
    private Integer productId;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("ProductUIN")
    @Expose
    private String productUIN;
    @SerializedName("StartDate")
    @Expose
    private Object startDate;
    @SerializedName("StatusFlag")
    @Expose
    private Boolean statusFlag;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Object getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Object currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

    public Object getEntryStartBy() {
        return entryStartBy;
    }

    public void setEntryStartBy(Object entryStartBy) {
        this.entryStartBy = entryStartBy;
    }

    public Object getEntryStartDate() {
        return entryStartDate;
    }

    public void setEntryStartDate(Object entryStartDate) {
        this.entryStartDate = entryStartDate;
    }

    public Boolean getIsPension() {
        return isPension;
    }

    public void setIsPension(Boolean isPension) {
        this.isPension = isPension;
    }

    public Boolean getIslive() {
        return islive;
    }

    public void setIslive(Boolean islive) {
        this.islive = islive;
    }

    public Object getLastEditBy() {
        return lastEditBy;
    }

    public void setLastEditBy(Object lastEditBy) {
        this.lastEditBy = lastEditBy;
    }

    public Object getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Object lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUIN() {
        return productUIN;
    }

    public void setProductUIN(String productUIN) {
        this.productUIN = productUIN;
    }

    public Object getStartDate() {
        return startDate;
    }

    public void setStartDate(Object startDate) {
        this.startDate = startDate;
    }

    public Boolean getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Boolean statusFlag) {
        this.statusFlag = statusFlag;
    }

}