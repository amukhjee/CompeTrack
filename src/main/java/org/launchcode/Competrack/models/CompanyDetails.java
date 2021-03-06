package org.launchcode.Competrack.models;

import org.launchcode.Competrack.annotation.URLValidation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CompanyDetails extends AbstractEntity{




    private String industry;

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSubindustry() {
        return subindustry;
    }

    public void setSubindustry(String subindustry) {
        this.subindustry = subindustry;
    }

    private String subindustry;


    @URLValidation(message = "Please provide valid URL")
    private String url;

    @NotBlank(message="Address can not be left blank.")
    @NotNull
    private String address;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CompanyDetails(String industry, String url, String address, String subindustry) {
        super();
        this.industry=industry;
        this.address=address;
        this.url=url;
        this.subindustry =subindustry;
    }

    public CompanyDetails(){}

}
