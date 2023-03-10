
package edu.dss.softbank.domain;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import edu.dss.softbank.domain.converters.UserAttributeConverter;
import edu.dss.softbank.domain.enumerations.AccountType;
import edu.dss.softbank.domain.enumerations.FinancialFactorType;
import edu.dss.softbank.domain.enumerations.StatusType;
import edu.dss.softbank.domain.vo.Balance;

@Entity
@Table(name="clients")
public final class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @Convert(converter = UserAttributeConverter.class)
    @JoinColumn(name = "iduser", referencedColumnName = "id")
    private User user;

    @Column(name="datestart")
    private final LocalDateTime dateStart;

    private final boolean active;
    private final StatusType status;
    private final FinancialFactorType financialFactor;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Account> accounts;

    public Client() {
        this("", new Balance(0.0));
    }
    
    public Client(String name) {
        this(name, new Balance(0.0));
    }

    public Client(String name, Balance b) {
        this.name = name;
        dateStart = LocalDateTime.now();
        active = true;
        status = StatusType.normal;
        financialFactor = FinancialFactorType.medium;
        accounts = new CopyOnWriteArrayList<>();
        Account account = new Account(b);
        addAccount(account);
    }

    public String getEmail() {
        return user.getEmail();
    }

    public void addAccount(Account a) {
        accounts.add(a);
    }

    public Account getCheckingAccount() {
        Iterator<Account> iterator = accounts.iterator();
        while (iterator.hasNext()) {
            Account c = (Account) iterator.next();
            if (c.getAccountType() == AccountType.checking)
                return c;
        }
        return null; // probably not the best implementation of this...
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public Long getIduser() {
        return user.getId();
    }

    private String showAccounts() {
        String str = "";
        Iterator<Account> iterator = accounts.iterator();
        while (iterator.hasNext()) {
            str += "Account: " + iterator.next();
        }
        return str;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Client c = (Client) o;
        return (this.id == c.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.dateStart);
        hash = 67 * hash + (this.active ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.status);
        hash = 67 * hash + Objects.hashCode(this.financialFactor);
        hash = 67 * hash + Objects.hashCode(this.accounts);
        return hash;
    }

    @Override
    public String toString() {
        String str = "Client [id=" + id + ", name=" + name + ", user=" + user.getId() + ", dateStart=" + dateStart + ", active="
                + active + ", status=" + status + ", financialFactor=" + financialFactor;
                str += showAccounts() + "]";
        return str;
    }
    
}
