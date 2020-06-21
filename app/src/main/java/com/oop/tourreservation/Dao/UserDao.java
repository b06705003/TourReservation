package com.oop.tourreservation.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.oop.tourreservation.Entity.User;

import java.util.List;

@Dao
public interface UserDao { // get access to User table

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM user")
    List<User> getAllUsers();
    
    @Query("SELECT * FROM user WHERE username = :username LIMIT 1")
    User getUserByUsername(String username);

    @Query("SELECT * FROM user WHERE id = :userId LIMIT 1")
    User getUser(int userId);
}
