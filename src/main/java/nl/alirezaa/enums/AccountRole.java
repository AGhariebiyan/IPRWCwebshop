package nl.alirezaa.enums;

public enum AccountRole {
    // Dit roept de constructor aan.
    ADMIN("admin"),
    CUSTOMER("customer");

    private final String role;

    AccountRole(String role) {
        this.role = role;
    }

    public String getrole() {
        return role;
    }
}
