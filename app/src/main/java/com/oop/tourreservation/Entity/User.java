package com.oop.tourreservation.Entity;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "user", indices = {@Index(value = {"username"}, unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String username;
    public String password;
    public String realName;

    // constructor for user
    public User(String username, String password, String realName) {
        this.id = 0;
        this.username = username;
        this.password = password;
        this.realName = realName;
    }

    @Override
    public String toString() {
        return username + ", " + password + ", " + realName + ", " + id;
    }
}
