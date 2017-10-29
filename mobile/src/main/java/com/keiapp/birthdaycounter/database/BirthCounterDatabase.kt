package com.keiapp.birthdaycounter.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by murakamikei on 2017/09/20.
 */
class BirthCounterDatabase
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

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

    override fun onConfigure(db: SQLiteDatabase?) {
        super.onConfigure(db)
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
    }

    private fun getCreateSQL() : String? {
        return null
    }
}