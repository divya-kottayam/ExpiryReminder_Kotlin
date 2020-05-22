package com.example.expiryreminderapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Login_table")
class Login_Entity  {

    @PrimaryKey
    var id :Int = 0

    @ColumnInfo (name ="NAME")
    var name : String = ""

    @ColumnInfo(name ="EMAIL_ID")
    var email_id : String = ""

    @ColumnInfo (name ="PASSWORD")
    var password : String = ""


}