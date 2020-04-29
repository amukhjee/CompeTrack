package org.launchcode.Competrack.data;

import org.launchcode.Competrack.models.CompanyDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDetailsRepository extends CrudRepository<CompanyDetails, Integer> {
}
