package org.launchcode.Competrack.data;

import org.launchcode.Competrack.models.Subindustry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubindustryRepository extends CrudRepository<Subindustry, Integer> {
}