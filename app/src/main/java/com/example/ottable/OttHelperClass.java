package com.example.ottable;

public class OttHelperClass {

    String ott_username, ott_pass, ott_type;

    public OttHelperClass() {

    }

    public OttHelperClass(String ott_username, String ott_pass, String ott_type) {
        this.ott_username = ott_username;
        this.ott_pass = ott_pass;
        this.ott_type = ott_type;
    }

    public String getOtt_username() {
        return ott_username;
    }

    public void setOtt_username(String ott_username) {
        this.ott_username = ott_username;
    }

    public String getOtt_pass() {
        return ott_pass;
    }

    public void setOtt_pass(String ott_pass) {
        this.ott_pass = ott_pass;
    }

    public String getOtt_type() {
        return ott_type;
    }

    public void setOtt_type(String ott_type) {
        this.ott_type = ott_type;
    }
}
