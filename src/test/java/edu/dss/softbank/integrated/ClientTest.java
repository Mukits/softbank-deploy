package edu.dss.softbank.integrated;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import edu.dss.softbank.domain.Account;
import edu.dss.softbank.domain.Client;
import edu.dss.softbank.domain.enumerations.AccountType;
import edu.dss.softbank.domain.vo.Balance;

public class ClientTest {

    @Test
    public void itCreatesClientWithEmptyName() {
        Client client = new Client();
        assertEquals(client.getName(), "");
    }

    @ParameterizedTest
    @ValueSource(strings = { "Ja_ck", "0Jane", "Michael000001" })    
    public void itCreatesClientWithName(String name) {
        Client client = new Client(name);
        assertEquals(client.getName(), name);
    }

    @Test
    public void itCreatesClientWithBasicCheckingAccount() {
        Client client = new Client();
        assertNotNull(client);
        Account checking = client.getCheckingAccount();
        assertNotNull(checking);
        assertEquals(checking.getFinalBalance(), 0.0);
        assertEquals(checking.getAccountType(), AccountType.checking);
    }

    @Test
    public void itCreatesAccountForClient() {
        Client c1 = new Client("John Smith");
        assertNotNull(c1);
        Account a1 = new Account(new Balance(100.0));
        assertNotNull(a1);
        assertEquals(100, a1.getFinalBalance());
    }
}
