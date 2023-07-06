package com.test.android_memo_sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context:Context) : SQLiteOpenHelper(context, "memo.db",null,1) {

    override fun onCreate(sqliteDatabase: SQLiteDatabase?) {

        val sql = """create table MemoTable
            (idx integer primary key autoincrement,
            title text not null,
            content text not null,
            memodate date not null)
        """.trimIndent()

        // 쿼리문 수행
        sqliteDatabase?.execSQL(sql)
    }

    override fun onUpgrade(sqliteDatabase: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}