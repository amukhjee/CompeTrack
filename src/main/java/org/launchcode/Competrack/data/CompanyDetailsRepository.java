package org.launchcode.Competrack.data;

import org.launchcode.Competrack.models.CompanyDetails;
import org.launchcode.Competrack.models.Industry;
import org.launchcode.Competrack.models.NameSorter;
import org.launchcode.Competrack.models.Subindustry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CompanyDetailsRepository extends CrudRepository<CompanyDetails, Integer> {

}



