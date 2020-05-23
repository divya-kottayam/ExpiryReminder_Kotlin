package com.example.expiryreminderapp

import android.content.Context
import androidx.room.*

@Database (entities = [(Login_Entity::class)], version = 1, exportSchema = false)
abstract class LoginDb : RoomDatabase() {

    abstract val LoginDAO: Login_DAO

    companion object {

        @Volatile
        private var INSTANCE: LoginDb? = null

        fun getInstance(context: Context): LoginDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LoginDb::class.java,
                        "ExpiryDB"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}