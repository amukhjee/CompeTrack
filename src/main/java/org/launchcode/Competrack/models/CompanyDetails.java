package org.launchcode.Competrack.models;
import org.launchcode.Competrack.annotation.URLValidation;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class CompanyDetails extends AbstractEntity{


    private String industry;

    public void setSubindustry(String subindustry) {
        this.subindustry = subindustry;
    }

    private String subindustry;

    private String revenue;

    private String earnings;

    @URLValidation(message = "Please provide valid URL")
    private String url;

    @NotBlank(message="Address can not be left blank.")
    @NotNull
    private String address;


    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSubindustry() {
        return subindustry;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getEarnings() {
        return earnings;
    }

    public void setEarnings(String earnings) {
        this.earnings = earnings;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CompanyDetails(String industry, String url, String address, String subindustry, String revenue, String earnings ) {
        super();
        this.industry=industry;
        this.address=address;
        this.url=url;
        this.subindustry =subindustry;
        this.revenue=revenue;
        this.earnings=earnings;

    }

    public CompanyDetails(){}

}
