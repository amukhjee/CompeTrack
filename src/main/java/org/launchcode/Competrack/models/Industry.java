package org.launchcode.Competrack.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Industry extends AbstractEntity {


    @OneToMany
    @JoinColumn
    private List<CompanyDetails> companyDetails= new ArrayList<>();

    public List<CompanyDetails> getCompanyDetails() {
        return companyDetails;
    }

    public Industry(List<CompanyDetails> companyDetails) {
        super();
        this.companyDetails = companyDetails;
    }

    public Industry() {
    }


    public void setCompanyDetails(List<CompanyDetails> companyDetails) {
        this.companyDetails = companyDetails;
    }




}
