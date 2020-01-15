package nl.alirezaa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountModel {
    private int user_id;
    private String email;
    private String password;
    private String accountType;
    private String token;

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

    @JsonProperty("_token")
    public String getToken() {
        return token;
    }

    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }
}
