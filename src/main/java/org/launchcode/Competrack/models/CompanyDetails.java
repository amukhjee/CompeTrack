package org.launchcode.Competrack.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class CompanyDetails {

    @Id
    @GeneratedValue
    private int id;

    @Size(min=2, max=30, message = "Name must be between 2 to 30 characters long")
    @NotBlank(message = "Name can not be blank")
    public String name;

    @Size(min=2, max=20, message = "Industry must be between 2 to 20 characters long")
    @NotBlank(message="Industry can not be left blank.")
    public String industry;


    @Size(max=100, message="subindustry size is too long")
    public String subindustry;


    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }



    public CompanyDetails(String name, String industry, String subindustry) {
        this.name = name;
        this.industry=industry;
        this.subindustry=subindustry;
    }

    public CompanyDetails(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getSubindustry() {
        return subindustry;
    }

    public void setSubindustry(String subindustry) {
        this.subindustry = subindustry;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyDetails that = (CompanyDetails) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
