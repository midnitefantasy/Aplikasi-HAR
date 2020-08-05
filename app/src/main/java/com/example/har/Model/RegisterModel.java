package com.example.har.Model;


public class RegisterModel {
    private String nama, email, password, jk;
    private int umur, tb, bb, isSuccess;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public int getTb() {
        return tb;
    }

    public void setTb(int tb) {
        this.tb = tb;
    }

    public int getBb() {
        return bb;
    }

    public void setBb(int bb) {
        this.bb = bb;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public RegisterModel (String nama, String email, String password, int umur, String jk, int tb, int bb) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.jk = jk;
        this.tb = tb;
        this.bb = bb;
        this.isSuccess = isSuccess;

    }

}
