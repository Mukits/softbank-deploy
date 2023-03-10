package edu.dss.softbank.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import edu.dss.softbank.domain.converters.PasswordAttributeConverter;
import edu.dss.softbank.domain.vo.Password;

@Entity
@Table(name="users")
public final class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToOne(mappedBy = "user") // references the attribute/property 'user' at class Client
    private Client client;

    // email is the username of the client in the system
    @Column(nullable=false, unique=true)
    private String email;

    @NotEmpty(message = "Password is mandatory")
    @Column(name="password", nullable=false)
    @Convert(converter = PasswordAttributeConverter.class)
    private Password password;

    @Column(name="lastlogin")
    private LocalDateTime lastLogin;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();

    public User() {
        email = "";
        password = new Password();
        lastLogin = LocalDateTime.now();
    }

    public User(Long id) {
      this.id = id;
    }

    public User(String email) {
        this.email = email;
        password = new Password();
        lastLogin = LocalDateTime.now();
    }

    public User(Password p) {
        email = "";
        password = p;
        lastLogin = LocalDateTime.now();
    }
   
    public User(Password p, int plusDays) {
        email = "";
        password = p;
        lastLogin = LocalDateTime.now();
        lastLogin.plusDays(plusDays);
  }

    @Override
    public String toString() {
      return String.format("User[email=%s, lastLogin='%s']", email, lastLogin);
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getPasswordAsString() {
      return password.getPassword();
    }

    public Password getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password.setPassword(password);
    }

    public void setPassword(Password password) {
      this.password = password;
    }

    public String getLastLogin() {
      DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
      return lastLogin.format(format);
    }

    public void setLastLogin(LocalDateTime lastLogin) {
      this.lastLogin = lastLogin;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }
    
    public void setRoles(List<Role> asList) {
      this.roles = asList;
    }

    public Collection<Role> getRoles() {
      return roles;
    }

}
