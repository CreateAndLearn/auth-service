package com.work.repository;

import com.work.entity.User;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepository extends CrudRepository<User, UUID> {

   User save(User user);


}
