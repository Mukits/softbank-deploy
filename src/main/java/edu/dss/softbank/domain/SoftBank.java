
package edu.dss.softbank.domain;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import edu.dss.softbank.domain.enumerations.AccountType;

/**
 * Main class for Softbank.
 * 
 * For info on CopyOnWriteArrayList see https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CopyOnWriteArrayList.html
 */
public final class SoftBank {
    private Long id;
    private final String name;
    private List<Branch> branches; // thread safe list
    private List<Tracker> trackers;  // TO DO: change this for a safe/secure list
    
    public SoftBank() {
        name = "SoftBank/Main";
        branches = new CopyOnWriteArrayList<>();
        addBranch((long) 1);
        trackers = new CopyOnWriteArrayList<>();
    }

    @Override
    public String toString() {
        return "SoftBank{" + "id=" + id + ", name=" + name + '}';
    }
    
    public void addBranch(Long id) {
        Branch b = new Branch(id);
        branches.add(b);
    }

    public Branch getBranch(Long id) {
        return branches.get(id.intValue());
    }

    public void addTracker(Client c, StockOption s) {
        Tracker t = new Tracker(c, s);
        trackers.add(t);
    }
    
    public int getNumberOfTrackers() {
        return trackers.size();
    }

    public int getNumberOfBranches() {
        return branches.size();
    }

    public void addClient(Long branchId, Client c) {
        Branch b = getBranch(branchId);
        b.addClient(c);
    }

    public int getNumberOfClients() {
        int n = 0;
        Iterator<Branch> iterator = branches.iterator();
        while (iterator.hasNext()) {
            Branch b = (Branch) iterator.next();
            n += b.getNumberOfClients();
        }
        return n;
    }

    public double consolidate(Long branchId) {
        Branch b = getBranch(branchId);
        Iterator<Client> iterator = b.getClients().iterator();
        double v = 0.0;
        while (iterator.hasNext()) {
            Client c = (Client) iterator.next();
            Account a = c.getCheckingAccount();
            v += a.getFinalBalance();
        }
        return v;
    }

}
