package com.hyancy.eco_recicla_reto_1_grupo_7.models;

public class User {
    private final String ID;
    private String user;
    private String email;
    private String password;

    public User(String ID, String user, String email, String password) {
        this.ID = ID;
        this.user = user;
        this.email = email;
        this.password = password;
    }

    public String getID() {
        return ID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
