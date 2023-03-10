package edu.dss.softbank.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.dss.softbank.domain.Client;
import edu.dss.softbank.domain.vo.Balance;
import edu.dss.softbank.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void saveClient(Client client) {
       clientRepository.save(client);
    }

    @Override
    public void updateClient(Client c) {
        Optional<Client> client = clientRepository.findById(c.getId());
        try {
            clientRepository.save(client);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }

    /* Testing SpotBugs features - this code is vulnerable to attacks */
    /* Solution:
     * import org.apache.commons.codec.binary.Hex;
        prinvate String generateSecretToken() {
            SecureRandom secRandom = new SecureRandom();

            byte[] result = new byte[32];
            secRandom.nextBytes(result);
            return Hex.encodeHexString(result);
        }
     */
    private String generateSecretToken() {
        Random r = new Random();
        return Long.toHexString(r.nextLong());
    }

    /*@Override
    public Client findClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }*/

    @Override
    public Client findClientById(long id) {
        return clientRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        clientRepository.deleteById(id);
    }

    private Client mapToClientDto(Client c){
        Client client = new Client(c.getName(), new Balance(c.getCheckingAccount().getFinalBalance()));
        client.setUser(c.getUser());
        client.setId(c.getId());
        return client;
    }

    @Override
    public List<Client> findAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map((client) -> mapToClientDto(client))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Client> findAllActiveClients() {
        return clientRepository.findAllActiveClients();
    }


}
