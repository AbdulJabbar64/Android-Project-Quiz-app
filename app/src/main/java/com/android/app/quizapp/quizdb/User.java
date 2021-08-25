package com.android.app.quizapp.quizdb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User{

    @PrimaryKey
    @NonNull
    private String email;
    private String password;
    private String name;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;



    public User(String email, String password, byte[] image, String name) {
        this.email = email;
        this.password = password;
        this.image = image;
        this.name = name;
    }
//    public User(String email, String password,String name){
//        this.email = email;
//        this.password = password;
//        this.name = name;
//    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public byte[] getImage() {
        return image;
    }
    public String getName() {
        return name;
    }
}
