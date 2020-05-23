package com.example.expiryreminderapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface Login_DAO {

    @Insert
    fun Logindata(data : Login_Entity)

    @Delete
    fun delete(user: Login_Entity)
}