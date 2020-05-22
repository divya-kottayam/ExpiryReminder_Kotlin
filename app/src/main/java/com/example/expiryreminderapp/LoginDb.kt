package com.example.expiryreminderapp

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.RoomDatabase

@Database (entities = [(Login_Entity::class)], version = 1)
abstract class LoginDb : RoomDatabase() {

abstract fun LoginDAO() : Login_DAO
}