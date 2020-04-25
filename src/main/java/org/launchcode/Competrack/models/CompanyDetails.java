package org.launchcode.Competrack.models;

public class CompanyDetails {
    public String name;
    public String industry;
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }



    public CompanyDetails(String name, String industry) {
        this.name = name;
        this.industry=industry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
