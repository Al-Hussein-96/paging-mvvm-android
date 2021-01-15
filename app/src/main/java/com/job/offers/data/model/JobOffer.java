package com.job.offers.data.model;


import androidx.recyclerview.widget.DiffUtil;

import java.io.Serializable;

public class JobOffer implements Serializable {
    private String jobType;
    private String workingMode;
    private double addressLongitude;
    private double addressLatitude;
    private String addressDescription;
    private String city;
    private String description;
    private String phoneNumber;
    private String createdOn;
    private String company;
    private int skillId;

    public JobOffer() {
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getWorkingMode() {
        return workingMode;
    }

    public void setWorkingMode(String workingMode) {
        this.workingMode = workingMode;
    }

    public double getAddressLongitude() {
        return addressLongitude;
    }

    public void setAddressLongitude(double addressLongitude) {
        this.addressLongitude = addressLongitude;
    }

    public double getAddressLatitude() {
        return addressLatitude;
    }

    public void setAddressLatitude(double addressLatitude) {
        this.addressLatitude = addressLatitude;
    }

    public String getAddressDescription() {
        return addressDescription;
    }

    public void setAddressDescription(String addressDescription) {
        this.addressDescription = addressDescription;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public static final DiffUtil.ItemCallback<JobOffer> DIFF_CALL = new DiffUtil.ItemCallback<JobOffer>() {
        @Override
        public boolean areItemsTheSame(JobOffer oldItem, JobOffer newItem) {
            return oldItem.createdOn.equals(newItem.createdOn);
        }

        @Override
        public boolean areContentsTheSame(JobOffer oldItem, JobOffer newItem) {
            return oldItem.createdOn.equals(newItem.createdOn);
        }
    };
}
