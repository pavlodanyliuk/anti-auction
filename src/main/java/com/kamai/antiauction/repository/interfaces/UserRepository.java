package com.kamai.antiauction.repository.interfaces;

import com.kamai.antiauction.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
