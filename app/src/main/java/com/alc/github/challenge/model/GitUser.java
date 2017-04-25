package com.alc.github.challenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Gino Osahon on 20/04/2017.
 */
public class GitUser implements Parcelable{

    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    @SerializedName("url")
    @Expose
    private String url;

        public GitUser(String login, int id, String avatarUrl, String url){
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.url = url;
    }

    public GitUser(Parcel in){
        login = in.readString();
        id = in.readInt();
        avatarUrl = in.readString();
        url = in.readString();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(login);
        parcel.writeInt(id);
        parcel.writeString(url);
        parcel.writeString(avatarUrl);
    }

    public static final Parcelable.Creator<GitUser> CREATOR = new Parcelable.Creator<GitUser>(){
        @Override
        public GitUser createFromParcel(Parcel parcel) {
            return new GitUser(parcel);
        }

        @Override
        public GitUser[] newArray(int size) {
            return new GitUser[size];
        }
    };
}
