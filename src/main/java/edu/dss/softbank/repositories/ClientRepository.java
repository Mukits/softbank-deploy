package edu.dss.softbank.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.dss.softbank.domain.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findAll();
    Client findById(long id);
    void deleteById(long id);
    void save(Optional<Client> client);

    // quick note: the table name maps to the value object (here, it's "Client")
    @Query("SELECT name FROM Client WHERE active=1")
    Collection<Client> findAllActiveClients();
}
