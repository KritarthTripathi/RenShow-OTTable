package com.example.ottable;

public class UserHelperClass {
    String name, username, phnom, upi_id, password;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String username, String phnom, String upi_id, String password) {
        this.name = name;
        this.username = username;
        this.phnom = phnom;
        this.upi_id = upi_id;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhnom() {
        return phnom;
    }

    public void setPhnom(String phnom) {
        this.phnom = phnom;
    }

    public String getUpi_id() {
        return upi_id;
    }

    public void setUpi_id(String upi_id) {
        this.upi_id = upi_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
