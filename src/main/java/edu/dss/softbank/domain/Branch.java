
package edu.dss.softbank.domain;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author stout
 */
public final class Branch {
    private Long id;
    private final CopyOnWriteArrayList<Client> clients;  //TO DO: refactor to safe/secure list
    
    public Branch(Long id) {
        clients = new CopyOnWriteArrayList<>();
    }
    
    public void addClient(Client c) {
        clients.add(c);
    }
    
    public boolean isClient(Client c) {
        for (Client c1 : clients) {
            if (c1.hashCode() == c.hashCode())
                return true;
        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public List<Client> getClients() {
        return clients;
    }

    public int getNumberOfClients() {
        return clients.size();
    }
    
}
