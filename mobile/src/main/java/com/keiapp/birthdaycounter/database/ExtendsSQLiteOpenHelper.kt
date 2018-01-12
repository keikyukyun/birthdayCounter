package com.keiapp.birthdaycounter.database

import android.content.Context
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteDatabase.CursorFactory
import net.sqlcipher.database.SQLiteException
import net.sqlcipher.database.SQLiteOpenHelper

class ExtendsSQLiteOpenHelper
(context : Context?, name : String?, factory : CursorFactory?, version : Int)
    : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(database: SQLiteDatabase?) {
        try {
            database!!.execSQL(getCreateSQL())
        } catch (e : SQLiteException) {

        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    private fun getCreateSQL() : String? {
        return null
    }
}