package com.example.expiryreminderapp.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.expiryreminderapp.modal.Product
import com.example.expiryreminderapp.modal.User

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // create table sql query
    private val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")")

    private val CREATE_PRODUCT_TABLE = ("CREATE TABLE " + TABLE_PRODUCT + "("
            + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PRODUCT_NAME + " TEXT,"
            + COLUMN_PRODUCT_EXPDATE + " TEXT," + COLUMN_PRODUCT_CATEGORY + " TEXT,"
            + COLUMN_REMINDER_INTERVEL + " TEXT" +")")

    // drop table sql query
    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $TABLE_USER"
    private val DROP_PRODUCT_TABLE = "DROP TABLE IF EXISTS $TABLE_PRODUCT"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_USER_TABLE)
        db.execSQL(CREATE_PRODUCT_TABLE)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE)
        db.execSQL(DROP_PRODUCT_TABLE)

        // Create tables again
        onCreate(db)

    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */

    fun getAllProduct(): List<Product> {

        // array of columns to fetch
        val columns = arrayOf(COLUMN_PRODUCT_ID, COLUMN_PRODUCT_NAME, COLUMN_PRODUCT_EXPDATE, COLUMN_PRODUCT_CATEGORY, COLUMN_REMINDER_INTERVEL)

        // sorting orders
        val sortOrder = "$COLUMN_PRODUCT_NAME ASC"
        val productlist = ArrayList<Product>()

        val db = this.readableDatabase

        // query the user table
        val cursor= db.query(TABLE_PRODUCT, //Table to query
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder)         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val product = Product(id = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_ID)).toInt(),
                    productname = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)),
                    expirydate = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_EXPDATE)),
                    category = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_CATEGORY)),
                    reminder = cursor.getString(cursor.getColumnIndex(COLUMN_REMINDER_INTERVEL)))

                productlist.add(product)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return productlist
    }


    /**
     * This method is to create user record
     *
     * @param user
     */
    fun addUser(user: User) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.name)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_PASSWORD, user.password)

        // Inserting Row
        db.insert(TABLE_USER, null, values)
        db.close()
    }



    fun addProduct(product: Product) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_PRODUCT_NAME, product.productname)
        values.put(COLUMN_PRODUCT_EXPDATE, product.expirydate)
        values.put(COLUMN_PRODUCT_CATEGORY, product.category)
        values.put(COLUMN_REMINDER_INTERVEL, product.reminder)

        // Inserting Row
        db.insert(TABLE_PRODUCT, null, values)
        db.close()
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    fun updateUser(user: User) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.name)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_PASSWORD, user.password)

        // updating row
        db.update(TABLE_USER, values, "$COLUMN_USER_ID = ?",
            arrayOf(user.id.toString()))
        db.close()
    }

    fun updateProduct(product: Product) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_PRODUCT_NAME, product.productname)
        values.put(COLUMN_PRODUCT_EXPDATE, product.expirydate)
        values.put(COLUMN_PRODUCT_CATEGORY, product.category)
        values.put(COLUMN_REMINDER_INTERVEL, product.reminder)

        // updating row
        db.update(
            TABLE_PRODUCT, values, "$COLUMN_PRODUCT_ID = ?",
            arrayOf(product.id.toString()))
        db.close()
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    fun deleteUser(user: User) {

        val db = this.writableDatabase
        // delete user record by id
        db.delete(TABLE_USER, "$COLUMN_USER_ID = ?",
            arrayOf(user.id.toString()))
        db.close()


    }

    fun deleteProduct(product: Product) {

        val db = this.writableDatabase
        // delete user record by id
        db.delete(
            TABLE_PRODUCT, "$COLUMN_PRODUCT_ID = ?",
            arrayOf(product.id.toString()))
        db.close()


    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    fun checkUser(email: String): Boolean {

        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase

        // selection criteria
        val selection = "$COLUMN_USER_EMAIL = ?"

        // selection argument
        val selectionArgs = arrayOf(email)

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        val cursor = db.query(TABLE_USER, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null)  //The sort order


        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0) {
            return true
        }

        return false
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    fun checkUser(email: String, password: String): Boolean {

        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID)

        val db = this.readableDatabase

        // selection criteria
        val selection = "$COLUMN_USER_EMAIL = ? AND $COLUMN_USER_PASSWORD = ?"

        // selection arguments
        val selectionArgs = arrayOf(email, password)

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        val cursor = db.query(TABLE_USER, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null) //The sort order

        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0)
            return true

        return false

    }

    companion object {

        // Database Version
        private val DATABASE_VERSION = 1

        // Database Name
        private val DATABASE_NAME = "UserManager.db"

        // User table name
        private val TABLE_USER = "user"

        // User Table Columns names
        private val COLUMN_USER_ID = "user_id"
        private val COLUMN_USER_NAME = "user_name"
        private val COLUMN_USER_EMAIL = "user_email"
        private val COLUMN_USER_PASSWORD = "user_password"


        private val TABLE_PRODUCT ="product"

        private val COLUMN_PRODUCT_ID ="product_id"
        private val COLUMN_PRODUCT_NAME = "product_name"
        private val COLUMN_PRODUCT_EXPDATE ="product_expdate"
        private val COLUMN_PRODUCT_CATEGORY = "product_category"
        private val COLUMN_REMINDER_INTERVEL="reminder_intervel"
    }
}