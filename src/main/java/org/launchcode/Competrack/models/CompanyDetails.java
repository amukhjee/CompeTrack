package org.launchcode.Competrack.models;

import org.launchcode.Competrack.annotation.URLValidation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
public class CompanyDetails extends AbstractEntity{

    @Size(min=2, max=20, message = "Industry must be between 2 to 20 characters long")
    @NotBlank(message="Industry can not be left blank.")
    @ManyToOne
    public Industry industry;

    @ManyToMany
    private List<Subindustry> subindustries;

    public List<Subindustry> getSubindustries() {
        return subindustries;
    }

    public void setSubindustries(List<Subindustry> subindustries) {
        this.subindustries = subindustries;
    }

    @URLValidation(message = "Please provide valid URL")
    public String url;

    @NotBlank(message="Address can not be left blank.")
    @NotNull
     public String address;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CompanyDetails(Industry industry, String url, String address, List<Subindustry> subindustry) {
        super();
        this.industry=industry;
        this.address=address;
        this.url=url;
        this.subindustries=subindustry;
    }

    public CompanyDetails(){}

}
