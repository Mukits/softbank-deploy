
package edu.dss.softbank.domain;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import edu.dss.softbank.domain.converters.BalanceAttributeConverter;
import edu.dss.softbank.domain.enumerations.AccountType;
import edu.dss.softbank.domain.vo.Balance;

@Entity
public final class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final AccountType accountType;

    @Column(name="balance", columnDefinition="double")
    @Convert(converter = BalanceAttributeConverter.class)
    private final Balance balance;
    
    public Account() {
        balance = new Balance();
        accountType = AccountType.checking;
    }

    public Account(Balance b, AccountType type) {
        balance = b;
        accountType = type;
    }

    public Account(Balance b) {
        this(b, AccountType.checking);
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getFinalBalance() {
        return balance.getBalance();
    }
    
}
