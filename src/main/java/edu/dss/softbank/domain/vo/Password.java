package edu.dss.softbank.domain.vo;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/***
 * Class to handle passwords securely.
 * It stores a hash for the password using SHA-512.
 * Link: https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
 */

public final class Password {
    private String password;
    private static Logger logger = LogManager.getLogger(Password.class);

    public Password() {}

    public Password(String p) {
        Validate.notNull(p);
        checkInvariants(p);
    }

    private void checkInvariants(String pwd) {
        Validate.notNull(pwd);
        int sz = pwd.length();
        Validate.inclusiveBetween(Properties.PWD_MIN_SIZE, Properties.PWD_MAX_SIZE, sz, 
                                  "The value must be between %d and %d", 
                                  Properties.PWD_MIN_SIZE, Properties.PWD_MAX_SIZE);
        //String p = DigestUtils.sha3_256Hex(pwd.toString()); // from apache commons - set on maven as dependency
        password = pwd;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password that = (Password) o;
        return password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return password.hashCode();
    }

    @Override
    public String toString() {
        return password;
    }

}
