package org.zerone.databasesample

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DATABASE_NAME = "dbApp"
const val TABLE_NAME = "tbApp"
const val TABLE_CONTENT_NAME = "Name"
const val TABLE_CONTENT_AGE = "Age"

class DataBaseHelper(var context:Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var createTable = "CREATE TABLE $TABLE_NAME($TABLE_CONTENT_NAME VARCHAR(256), $TABLE_CONTENT_AGE INTEGER);"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    // CRUD Sample

    fun insertData(name: String, age: Int){
        val dbs = this.writableDatabase
        val cv = ContentValues()
        cv.put(TABLE_CONTENT_NAME, name)
        cv.put(TABLE_CONTENT_AGE, age)
        dbs.insert(TABLE_NAME, null, cv)
        dbs.close()
    }

    fun readData(): MutableList<UserObjRV>{
        var listOfUser: MutableList<UserObjRV> = ArrayList()

        val dbs = this.readableDatabase
        var readQuery = "SELECT * FROM $TABLE_NAME"
        val result = dbs.rawQuery(readQuery, null)

        if(result.moveToFirst()){
            do{
                val temp = UserObjRV()
                temp.userName = result.getString(result.getColumnIndex(TABLE_CONTENT_NAME))
                temp.userAge = result.getInt(result.getColumnIndex(TABLE_CONTENT_AGE))
                listOfUser.add(temp)
            } while (result.moveToNext())
        }
        dbs.close()
        result.close()

        return listOfUser
    }

    fun updateData(db: SQLiteDatabase?, name: String, age: Int){
        var updateQuery = "UPDATE $TABLE_NAME SET $TABLE_CONTENT_AGE = $age  WHERE $TABLE_CONTENT_NAME = $name;"
        db?.execSQL(updateQuery)
    }

    fun deleteData(db: SQLiteDatabase?, name: String){
        var deleteQuery = "DELETE FROM $TABLE_NAME WHERE $TABLE_CONTENT_NAME = $name;"
        db?.execSQL(deleteQuery)
    }

}