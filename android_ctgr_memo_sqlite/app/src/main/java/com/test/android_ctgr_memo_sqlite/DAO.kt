package com.test.android_ctgr_memo_sqlite

import android.content.ContentValues
import android.content.Context


class DAO {

    companion object {
        // 비밀번호 설정
        fun setPw(context: Context, pw: String){
            val value  = ContentValues()

            value.put("pw", pw)
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.insert("PasswordTable", null, value)
        }

        // 비밀번호 가져오기
        fun getPw(context: Context): String? {
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.query(
                "PasswordTable",
                arrayOf("pw"),
                null,
                null,
                null,
                null,
                null
            )

            // 비밀번호가 없을시 null값을 받기 때문에 null값 허용
            val pw : String?
            if (cursor.moveToFirst()) {
                val idx = cursor.getColumnIndex("pw")
                pw = cursor.getString(idx)
            } else {
                pw = null
            }
            dbHelper.close()

            return pw
        }

        // 카테고리 생성
        fun insertCategory(context: Context, title: String) {
            val contentValues = ContentValues()
            contentValues.put("title", title)

            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.insert("CategoryTable", null, contentValues)
            dbHelper.close()
        }

        // 조건에 맞는 카테고리 하나를 가져온다.
        fun selectCtgr(context: Context, idx: Int): CtgrClass {
            val dbHelper = DBHelper(context)
            val selection = "idx = ?"
            val args = arrayOf(idx.toString())
            val cursor = dbHelper.writableDatabase.query(
                "CategoryTable",
                null,
                selection,
                args,
                null,
                null,
                null
            )
            cursor.moveToNext()

            // 컬럼의 이름을 지정하여 컬럼의 순서값을 가져온다
            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("title")

            // 데이터를 가져온다
            val idx = cursor.getInt(idx1)
            val title = cursor.getString(idx2)
            val ctgrClass = CtgrClass(idx, title)

            dbHelper.close()
            return ctgrClass
        }

        // 카테고리 모든 행을 가져온다 (idx 내림차순)
        fun selectAllCtgr(context: Context): MutableList<CtgrClass> {

            val dbHelper = DBHelper(context)
            val query = "SELECT * FROM CategoryTable ORDER BY idx DESC"
            val cursor = dbHelper.writableDatabase.rawQuery(query, null)

            val dataList = mutableListOf<CtgrClass>()

            while (cursor.moveToNext()) {
                // 컬럼의 이름을 지정하여 컬럼의 순서값을 가져온다
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("title")

                // 데이터를 가져온다
                val idx = cursor.getInt(idx1)
                val title = cursor.getString(idx2)

                val ctgrClass = CtgrClass(idx, title)
                dataList.add(ctgrClass)
            }
            dbHelper.close()
            return dataList
        }

        // 카테고리 수정
        fun updateCtgr(context: Context, obj: CtgrClass) {
            // 컬럼과 값을 지정하는 ContentValues 생성
            val cv = ContentValues()
            cv.put("title", obj.title)
            // 조건절
            val condition = "idx = ?"
            // ? 에 들어갈 값
            val args = arrayOf(obj.idx.toString())
            // 수정
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.update("CategoryTable", cv, condition, args)
            dbHelper.close()
        }

        // 카테고리 삭제
        fun deleteCtgr(context: Context, idx: Int) {
            val args = arrayOf(idx.toString())
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.delete("MemoTable", "categoryIdx = ?", args)
            dbHelper.writableDatabase.delete("CategoryTable", "idx = ?", args)
            dbHelper.close()
        }

        // 메모 저장
        fun insertMemo(context: Context, data: MemoClass) {
            // 컬럼 이름과 데이터를 설정하는 객체
            val contentValues = ContentValues()
            // 컬럼이름, 값을 지정
            contentValues.put("title", data.title)
            contentValues.put("content", data.content)
            contentValues.put("memodate", data.memodate)
            contentValues.put("categoryIdx", data.categoryIdx)

            val dbHelper = DBHelper(context)
            // 데이터를 저장할 테이블의 이름, null값을 어ㄸ허게 처리할 것인지, 저장할 데이터를 가지고 잇는 객체
            dbHelper.writableDatabase.insert("MemoTable", null, contentValues)
            dbHelper.close()
        }

        // 조건에 맞는 메모 하나를 가져온다.
        fun selectData(context: Context, idx: Int): MemoClass {
            val dbHelper = DBHelper(context)
            val selection = "idx = ?"
            val args = arrayOf(idx.toString())
            val cursor = dbHelper.writableDatabase.query(
                "MemoTable",
                null,
                selection,
                args,
                null,
                null,
                null
            )

            cursor.moveToNext()
            // 컬럼의 이름을 지정하여 컬럼의 순서값을 가져온다
            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("title")
            val idx3 = cursor.getColumnIndex("content")
            val idx4 = cursor.getColumnIndex("memodate")
            val idx5 = cursor.getColumnIndex("categoryIdx")

            // 데이터를 가져온다
            val idx = cursor.getInt(idx1)
            val title = cursor.getString(idx2)
            val content = cursor.getString(idx3)
            val memodate = cursor.getString(idx4)
            val categoryIdx = cursor.getInt(idx5)
            val memoClass = MemoClass(idx, title, content, memodate, categoryIdx)

            dbHelper.close()
            return memoClass
        }


        // 카테고리에 해당하는 모든 메모를 가져온다
        fun selectAllData(context: Context,categoryIdx : Int): MutableList<MemoClass> {
            val dbHelper = DBHelper(context)
            // 조건절
            val condition = "categoryIdx = ?"
            // ? 에 들어갈 값
            val args = arrayOf(categoryIdx.toString())
            val cursor =
                dbHelper.writableDatabase.query("MemoTable", null, condition, args, null, null, "idx DESC")

            val dataList = mutableListOf<MemoClass>()
            while (cursor.moveToNext()) {
                // 컬럼의 이름을 지정하여 컬럼의 순서값을 가져온다
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("title")
                val idx3 = cursor.getColumnIndex("content")
                val idx4 = cursor.getColumnIndex("memodate")
                val idx5 = cursor.getColumnIndex("categoryIdx")

                // 데이터를 가져온다
                val idx = cursor.getInt(idx1)
                val title = cursor.getString(idx2)
                val content = cursor.getString(idx3)
                val memodate = cursor.getString(idx4)
                val categoryIdx = cursor.getInt(idx5)

                val memoClass = MemoClass(idx, title, content, memodate, categoryIdx)
                dataList.add(memoClass)
            }

            dbHelper.close()

            return dataList
        }


        // 메모 수정
        fun updateData(context: Context, obj: MemoClass) {
            // 컬럼과 값을 지정하는 ContentValues 생성
            val cv = ContentValues()
            cv.put("title", obj.title)
            cv.put("content", obj.content)
            cv.put("memodate", obj.memodate)
            cv.put("categoryIdx", obj.categoryIdx)
            // 조건절
            val condition = "idx = ?"
            // ? 에 들어갈 값
            val args = arrayOf(obj.idx.toString())
            // 수정
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.update("MemoTable", cv, condition, args)
            dbHelper.close()
        }


        // 메모 삭제
        fun deleteData(context: Context, idx: Int) {
            // 조건절
            val condition = "idx = ?"
            // ? 에 들어갈 값
            val args = arrayOf(idx.toString())
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.delete("MemoTable", condition, args)
            dbHelper.close()
        }
    }
}
