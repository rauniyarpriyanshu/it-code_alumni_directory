package com.pr.alumni_directory.data;

import java.io.Serializable;

public class AlumniData implements Serializable {

    private String userId;
    private String firstName;

    public AlumniData(String userId, String firstName, String lastName, String email, String name, String address, String jobTitle, AlumniSubData subData) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.name = name;
        this.address = address;
        this.jobTitle = jobTitle;
        this.subData = subData;
    }

    private String lastName;
    private String email;
    private String name;
    private String address;
    private String jobTitle;
    public AlumniSubData subData;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public AlumniSubData getSubData() {
        return subData;
    }

    public void setSubData(AlumniSubData subData) {
        this.subData = subData;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }


    @Override
    public String toString() {
        return "{ALUMNI DATA: id="+getUserId()
                +", name="+getName()+ ", email="+getName()+
                ", address="+getAddress()+", jobTitle="+getJobTitle()+", ALUMNI SUB_INFO: category="+getSubData().getBusinessCategory()+"}";
    }

}
