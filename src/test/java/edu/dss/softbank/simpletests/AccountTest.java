package edu.dss.softbank.simpletests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.dss.softbank.domain.Account;
import edu.dss.softbank.domain.enumerations.AccountType;
import edu.dss.softbank.domain.vo.Balance;

public class AccountTest {

    @Test
    public void itStartsZeroedAndChecking() {
        Account a = new Account();
        assertNotNull(a);
        assertEquals(a.getFinalBalance(), 0.0);
        assertEquals(a.getAccountType(), AccountType.checking);
    }

    @Test
    public void itWorksWithValidAccountsPosNeg() {
        Account a = new Account(new Balance(100.0));
        assertNotNull(a);
        assertEquals(a.getFinalBalance(), 100);
    }

}