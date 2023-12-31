package com.example.sqlitedemofour

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseCreation(context: Context): SQLiteOpenHelper(
    context, DATABASE_NAME, null, VERSION
) {
    companion object{
        private const val DATABASE_NAME = "database_name"
        private const val VERSION = 1
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE four(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, number TEXT, password TEXT)"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS four")
        onCreate(db)
    }
    fun insertIntoTable(name: String, email: String, number: String, password: String): Boolean{
        val sqLiteDatabase: SQLiteDatabase = this.writableDatabase
        val contentValues: ContentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("email", email)
        contentValues.put("number", number)
        contentValues.put("password", password)

        val long: Long = sqLiteDatabase.insert("four", null, contentValues)
        sqLiteDatabase.close()

        return long > 0
    }

    fun retrieveData(email: String, password: String): Boolean{
        val sqLiteDatabase: SQLiteDatabase = this.readableDatabase
        val cursor: Cursor = sqLiteDatabase.rawQuery(
            "SELECT * FROM four WHERE email = ? AND  password = ?",
            arrayOf(email, password)
        )
        val isLoginSuccessful = cursor.moveToFirst()
        sqLiteDatabase.close()

        return isLoginSuccessful
    }
    fun getLoggedUserDetails(email: String): ArrayList<ContainerClass>{
        val arrayList = ArrayList<ContainerClass>()
        val sqLiteDatabase: SQLiteDatabase = this.readableDatabase
        val cursor: Cursor = sqLiteDatabase.rawQuery(
            "SELECT * FROM four WHERE email = ?",
            arrayOf(email)
        )
        if (cursor.moveToFirst()){
            val name = cursor.getString(1)
            val email2 = cursor.getString(2)
            val number = cursor.getString(3)

            arrayList.add(ContainerClass(name, email2, number))
        }
        return arrayList
    }
}