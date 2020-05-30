package org.launchcode.Competrack.data;

import org.launchcode.Competrack.models.Industry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryRepository extends CrudRepository<Industry, Integer> {
}
