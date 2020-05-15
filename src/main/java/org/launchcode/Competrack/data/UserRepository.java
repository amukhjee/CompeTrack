package org.launchcode.Competrack.data;

import org.launchcode.Competrack.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String name);


}
