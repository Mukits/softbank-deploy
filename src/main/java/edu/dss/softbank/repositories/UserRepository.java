package edu.dss.softbank.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.dss.softbank.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findById(long id);
    User findByEmail(String email);
    void deleteById(long id);
}
