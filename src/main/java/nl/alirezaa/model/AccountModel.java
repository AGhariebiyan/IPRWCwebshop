package nl.alirezaa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.security.auth.Subject;
import java.security.Principal;

public class AccountModel implements Principal {
    private int user_id;
    private String email;
    private String password;
    private String accountType;
    private String jwttoken;

    public AccountModel() {}

    public AccountModel(String email, String password, String accountType) {
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }

    @JsonProperty("user_id")
    public int getUser_id() {
        return user_id;
    }

    @JsonProperty("_user_id")
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("_email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("_password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("accountType")
    public String getAccountType() {
        return accountType;
    }

    @JsonProperty("_accountType")
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @JsonProperty("jwttoken")
    public String getJwttoken() {
        return jwttoken;
    }

    @JsonProperty("_jwttoken")
    public void setJwttoken(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
