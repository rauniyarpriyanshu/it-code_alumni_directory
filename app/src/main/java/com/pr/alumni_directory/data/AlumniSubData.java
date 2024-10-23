package com.pr.alumni_directory.data;

import java.io.Serializable;

public class AlumniSubData implements Serializable {

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNameOfBusiness() {
        return nameOfBusiness;
    }

    public void setNameOfBusiness(String nameOfBusiness) {
        this.nameOfBusiness = nameOfBusiness;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }


    public AlumniSubData(String userId, String nameOfBusiness, String businessAddress, String businessCity, String businessState, String businessZip, String businessPhone, String businessEmail, String businessWebsite, String businessDescription, String businessCategory, String alumniDiscount, String alumniDiscountInfo, String businessLogoLink) {
        this.userId = userId;
        this.nameOfBusiness = nameOfBusiness;
        this.businessAddress = businessAddress;
        this.businessCity = businessCity;
        this.businessState = businessState;
        this.businessZip = businessZip;
        this.businessPhone = businessPhone;
        this.businessEmail = businessEmail;
        this.businessWebsite = businessWebsite;
        this.businessDescription = businessDescription;
        this.businessCategory = businessCategory;
        this.alumniDiscount = alumniDiscount;
        this.alumniDiscountInfo = alumniDiscountInfo;
        this.businessLogoLink = businessLogoLink;
    }

    private String userId;
    private String nameOfBusiness;
    private String businessAddress;
    private String businessCity;
    private String businessState;

    private String businessZip;
    private String businessPhone;

    private String businessEmail;
    private String businessWebsite;
    private String businessDescription;
    private String businessCategory;
    private String alumniDiscount;
    private String alumniDiscountInfo;
    private String businessLogoLink;



    public String getBusinessState() {
        return businessState;
    }

    public void setBusinessState(String businessState) {
        this.businessState = businessState;
    }

    public String getBusinessZip() {
        return businessZip;
    }

    public void setBusinessZip(String businessZip) {
        this.businessZip = businessZip;
    }

    public String getBusinessCity() {
        return businessCity;
    }

    public void setBusinessCity(String businessCity) {
        this.businessCity = businessCity;
    }

    public String getAlumniDiscount() {
        return alumniDiscount;
    }

    public void setAlumniDiscount(String alumniDiscount) {
        this.alumniDiscount = alumniDiscount;
    }

    public String getAlumniDiscountInfo() {
        return alumniDiscountInfo;
    }

    public void setAlumniDiscountInfo(String alumniDiscountInfo) {
        this.alumniDiscountInfo = alumniDiscountInfo;
    }

    public String getBusinessLogoLink() {
        return businessLogoLink;
    }

    public void setBusinessLogoLink(String businessLogoLink) {
        this.businessLogoLink = businessLogoLink;
    }

    public String getBusinessWebsite() {
        return businessWebsite;
    }

    public void setBusinessWebsite(String businessWebsite) {
        this.businessWebsite = businessWebsite;
    }

    public String getBusinessDescription() {
        return businessDescription;
    }

    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }



}
