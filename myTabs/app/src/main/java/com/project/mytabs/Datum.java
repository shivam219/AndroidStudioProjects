
package com.project.mytabs;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("1")
    @Expose
    private String _1;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("2")
    @Expose
    private String _2;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("3")
    @Expose
    private String _3;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("4")
    @Expose
    private String _4;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("5")
    @Expose
    private String _5;
    @SerializedName("password")
    @Expose
    private String password;

    public String get0() {
        return _0;
    }

    public void set0(String _0) {
        this._0 = _0;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String get1() {
        return _1;
    }

    public void set1(String _1) {
        this._1 = _1;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String get2() {
        return _2;
    }

    public void set2(String _2) {
        this._2 = _2;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String get3() {
        return _3;
    }

    public void set3(String _3) {
        this._3 = _3;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String get4() {
        return _4;
    }

    public void set4(String _4) {
        this._4 = _4;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String get5() {
        return _5;
    }

    public void set5(String _5) {
        this._5 = _5;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
