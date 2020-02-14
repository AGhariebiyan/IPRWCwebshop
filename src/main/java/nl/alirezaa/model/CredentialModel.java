package nl.alirezaa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CredentialModel {
    private String email;
    private String password;

    public CredentialModel() {}

    public CredentialModel(String email, String password) {
        this.email = email;
        this.password = password;
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
}
