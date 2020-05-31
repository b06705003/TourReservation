package com.oop.tourreservation;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "user", indices = {@Index(value = {"username"}, unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String username;

    public User(String username) {
        this.id = 0;
        this.username = username;
    }
}
