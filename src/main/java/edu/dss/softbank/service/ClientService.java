package edu.dss.softbank.service;

import java.util.Collection;
import java.util.List;

import edu.dss.softbank.domain.Client;

public interface ClientService {
    void saveClient(Client client);
    void updateClient(Client client);

    //Client findClientByEmail(String email);
    Client findClientById(long id);
    void deleteById(long id);
    List<Client> findAllClients();
    Collection<Client> findAllActiveClients();
}
