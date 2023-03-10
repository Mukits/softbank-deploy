package edu.dss.softbank.integrated;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.dss.softbank.domain.Branch;
import edu.dss.softbank.domain.Client;
import edu.dss.softbank.domain.SoftBank;
import edu.dss.softbank.domain.vo.Balance;

public class BankTest {
    
    @Test
    public void itCreatesBranchOnStartup() {
        SoftBank bank = new SoftBank();
        assertNotNull(bank);
        assertEquals(1, bank.getNumberOfBranches());
        Branch b = bank.getBranch((long) 0);
        assertNotNull(b);
    }

    @Test
    public void itConsolidatesAllMoney() {
        SoftBank bank = new SoftBank();
        assertNotNull(bank);
        assertEquals(1, bank.getNumberOfBranches());

        Client c1 = new Client("John Smith", new Balance(100.0));
        assertNotNull(c1);
        bank.addClient((long) 0, c1);
        assertEquals(1, bank.getNumberOfClients());

        Client c2 = new Client("John Smith", new Balance(200.0));
        assertNotNull(c2);
        bank.addClient((long) 0, c2);
        assertEquals(2, bank.getNumberOfClients());

        Client c3 = new Client("John Smith", new Balance(500.0));
        assertNotNull(c3);
        bank.addClient((long) 0, c3); // 0 = first branch
        assertEquals(3, bank.getNumberOfClients());

        assertEquals(bank.getNumberOfBranches(), 1);
        double v = bank.consolidate((long) 0);
        assertEquals(800.00, v);
    }
}
