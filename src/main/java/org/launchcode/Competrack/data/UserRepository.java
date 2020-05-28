package org.launchcode.Competrack.data;

import org.launchcode.Competrack.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    Optional<User> findByEmail(String email);


}
