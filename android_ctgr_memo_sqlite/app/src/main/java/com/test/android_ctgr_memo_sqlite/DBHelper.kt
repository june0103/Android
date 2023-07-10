package com.test.android_ctgr_memo_sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context:Context) : SQLiteOpenHelper(context, "CtgrMemo.db",null,1) {

    override fun onCreate(sqliteDatabase: SQLiteDatabase?) {

        // 비밀번호 테이블 생성 쿼리
        val pwSql = """create table PasswordTable (
                pw text not null)
        """.trimIndent()

        // 카테고리 테이블 생성 쿼리
        val categorySql = """create table CategoryTable 
            (idx integer primary key autoincrement,
             title text not null)
        """.trimIndent()

        // 메모 테이블 생성 쿼리
        // 카테고리테이블의 idx를 참조하는 외래키로 categoryIdx 설정
        val memosql = """create table MemoTable
            (idx integer primary key autoincrement,
            title text not null,
            content text not null,
            memodate date not null,
            categoryIdx integer,
            foreign key (categoryIdx) references CategoryTable(idx))
        """.trimIndent()

        // 쿼리문 수행
        sqliteDatabase?.execSQL(pwSql)
        sqliteDatabase?.execSQL(categorySql)
        sqliteDatabase?.execSQL(memosql)
    }

    override fun onUpgrade(sqliteDatabase: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}