package org.launchcode.Competrack.data;

import org.launchcode.Competrack.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer > {
}
