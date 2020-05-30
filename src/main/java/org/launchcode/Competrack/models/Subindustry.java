package org.launchcode.Competrack.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subindustry extends AbstractEntity{
    @ManyToMany
    private List<CompanyDetails> jobs= new ArrayList<>();

    public Subindustry(List<CompanyDetails> jobs) {
        super();
        this.jobs = jobs;
    }

    public Subindustry() {
    }

    public List<CompanyDetails> getJobs() {
        return jobs;
    }

    public void setJobs(List<CompanyDetails> jobs) {
        this.jobs = jobs;
    }
}
