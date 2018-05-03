package com.example.wangjunjie.awesomeproject1.api.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AccessToken implements Parcelable {
    private String id;
    private int userId;
    private Account user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.userId);
        dest.writeParcelable(this.user, flags);
    }

    public AccessToken() {
    }

    protected AccessToken(Parcel in) {
        this.id = in.readString();
        this.userId = in.readInt();
        this.user = in.readParcelable(Account.class.getClassLoader());
    }

    public static final Parcelable.Creator<AccessToken> CREATOR = new Parcelable.Creator<AccessToken>() {
        @Override
        public AccessToken createFromParcel(Parcel source) {
            return new AccessToken(source);
        }

        @Override
        public AccessToken[] newArray(int size) {
            return new AccessToken[size];
        }
    };

    @Override
    public String toString() {
        return "AccessToken{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", user=" + user +
                '}';
    }
}
