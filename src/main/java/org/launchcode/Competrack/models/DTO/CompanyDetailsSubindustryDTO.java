package org.launchcode.Competrack.models.DTO;

import org.launchcode.Competrack.models.CompanyDetails;
import org.launchcode.Competrack.models.Industry;
import org.launchcode.Competrack.models.Subindustry;

import javax.validation.constraints.NotNull;

public class CompanyDetailsSubindustryDTO {

    @NotNull
    public CompanyDetails companyDetails;

    @NotNull
    public Subindustry subindustry;

    public CompanyDetailsSubindustryDTO() {
    }

    public CompanyDetails getCompanyDetails() {
        return companyDetails;
    }

    public void setCompanyDetails(CompanyDetails companyDetails) {
        this.companyDetails = companyDetails;
    }

    public Subindustry getSubindustry() {
        return subindustry;
    }

    public void setSubindustry(Subindustry subindustry) {
        this.subindustry = subindustry;
    }
}
