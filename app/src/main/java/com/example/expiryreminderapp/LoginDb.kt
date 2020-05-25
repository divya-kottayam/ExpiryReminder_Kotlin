package com.example.expiryreminderapp

import android.content.Context
import androidx.room.*


@Database (entities = [(Login_Entity::class)], version = 1, exportSchema = false)
abstract class LoginDb : RoomDatabase() {

    abstract val LoginDAO: Login_DAO


}