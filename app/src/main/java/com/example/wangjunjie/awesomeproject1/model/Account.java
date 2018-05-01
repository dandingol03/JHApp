package com.example.wangjunjie.awesomeproject1.model;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author lxb
 * 账户
 * Created by lion on 2017/12/7.
 */

public class Account implements Parcelable {

    private int id;

    private String name;
    private String username;

    private String realm;
    private String email;
    private String password;
    private Boolean emailVerified;
    private String status;

    private String created;
    private String lastUpdated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
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

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.username);
        dest.writeString(this.created);
        dest.writeString(this.lastUpdated);
        dest.writeString(this.realm);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeValue(this.emailVerified);
        dest.writeString(this.status);
    }

    public Account() {
    }

    protected Account(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.username = in.readString();
        this.created = in.readString();
        this.lastUpdated = in.readString();
        this.realm = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.emailVerified = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.status = in.readString();
    }

    public static final Parcelable.Creator<Account> CREATOR = new Parcelable.Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel source) {
            return new Account(source);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };
}
