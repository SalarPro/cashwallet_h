package com.honar.walletcash

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.DatabaseErrorHandler
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


class MyDatabase(context: Context?) : SQLiteOpenHelper(context, "information.db", null, 1) {


    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL(
            "CREATE TABLE cash (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, description_ TEXT,date_ DATETIME ,cost INTEGER,tag INTEGER)"
        )


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


    fun addNewCost(name: String, description: String, datee: Date, cost: Int,tag : Int) {

        try {
            var date = android.text.format.DateFormat.format("yyyy-MM-dd HH:MM:s", datee)
            writableDatabase.execSQL(
                "INSERT INTO cash (name,description_,date_,cost,tag) VALUES ('$name','$description','$date', $cost , $tag ) "
            )
        } catch (ex: SQLiteException) {
            Log.d("errorrrr", ex.message)
        }
    }

    @SuppressLint("Recycle", "SimpleDateFormat")
    fun getAllData() {
        try {
            itemData.clear()
            val curser = readableDatabase.rawQuery("select * from cash", null)
            if (curser.moveToFirst()) {

                itemData.clear()
                do {

                    itemData.add(Cash(id = curser.getInt(0),
                        name = curser.getString(1),
                        description = curser.getString(2),
                        date = SimpleDateFormat("yyyy-MM-dd HH:MM:s").parse(curser.getString(3)),
                        cash = curser.getInt(4),
                        tag = curser.getInt(5)))
                } while (curser.moveToNext())
                itemData.reverse()
            }

        } catch (ex: SQLiteException) {
            Log.d("errorrrr", ex.message)
        }

    }


    fun clearData(){
        writableDatabase.execSQL("DELETE FROM cash")
    }

    fun deleteItem(id: Int) {
        try {
            writableDatabase.execSQL(
                "DELETE FROM cash WHERE id = $id"
            )
        } catch (ex: SQLiteException) {
            Log.d("errorrrr", ex.message)
        }
    }

}