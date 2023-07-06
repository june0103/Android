package com.test.android73_ex00
import android.content.ContentValues
import android.content.Context

class DAO {

    companion object {
        // Create : 저장
        fun insertData(context: Context, data: StudentClass) {
//            // autoincrement가 있는 컬럼은 제외하고 나머지만 지정
//            val sql = """insert into TestTable
//                | (textData, intData, doubleData, dateData)
//                | values(?,?,?,?)
//            """.trimMargin()
//
//            // ? 설정할 값을 배열에 담아준다(문자열 배열)
//            val arg1 = arrayOf(data.textData, data.intData, data.doubleData, data.dateData)
//
//            // 데이터베이스 오픈
//            val sqliteDatabase = DBHelper(context)
//            // 쿼리실행 ( 쿼리문, ?에 셋팅할 값 배열)
//            sqliteDatabase.writableDatabase.execSQL(sql, arg1)
//            // 데이터베이스 닫기
//            sqliteDatabase.close()

            // 컬럼 이름과 데이터를 설정하는 객체
            val contentValues = ContentValues()
            // 컬럼이름, 값을 지정
            contentValues.put("name", data.name)
            contentValues.put("age", data.age)
            contentValues.put("korean", data.korean)

            val dbHelper = DBHelper(context)
            // 데이터를 저장할 테이블의 이름, null값을 어ㄸ허게 처리할 것인지, 저장할 데이터를 가지고 잇는 객체
            dbHelper.writableDatabase.insert("StudentTable", null, contentValues)
            dbHelper.close()

        }

        // Read Condition : 조건에 맞는 행 하나를 가져온다.
        fun selectData(context: Context, idx: Int): StudentClass {
//            // 쿼리문
//            val sql = "select * from TestTable where idx=?"
//            // ? 에 들어갈 값
//            val arg1 = arrayOf(idx.toString())
//
//            // 데이터베이스 오픈
//            val dbHelper = DBHelper(context)
//            // 쿼리실행
//            val cursor = dbHelper.writableDatabase.rawQuery(sql,arg1)

            val dbHelper = DBHelper(context)
            // 첫 번째 : 테이블명
            // 두 번째 : 가지고 오고자 하는 컬럼 이름 목록. null을 넣어주면 모두 가져온다.
            // 세 번째 : 특정 행을 선택하기 위한 조건절
            // 네 번째 : 세 번째에 들어가는 조건절의 ? 에 셋팅될 값 배열
            // 다섯 번째 : Group by의 기준 컬럼
            // 여섯 번째 : Having절에 들어갈 조건절
            // 일곱 번째 : Having절의 ?에 셋팅될 값 배열
            val selection = "idx = ?"
            val args = arrayOf(idx.toString())
            val cursor = dbHelper.writableDatabase.query(
                "StudentTable",
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
            val idx2 = cursor.getColumnIndex("name")
            val idx3 = cursor.getColumnIndex("age")
            val idx4 = cursor.getColumnIndex("korean")

            // 데이터를 가져온다
            val idx = cursor.getInt(idx1)
            val name = cursor.getString(idx2)
            val age = cursor.getInt(idx3)
            val korean = cursor.getInt(idx4)

            val studentClass = StudentClass(idx, name, age, korean)

            dbHelper.close()
            return studentClass
        }


        // Read All : 모든 행을 가져온다
        fun selectAllData(context: Context): MutableList<StudentClass> {
//            // 모든 행을 가져오는 쿼리문을 작성
//            val sql = "select * from TestTable"
//
//            // 데이터베이스 오픈
//            val dbHelper = DBHelper(context)
//            // 쿼리 실행
//            val cursor = dbHelper.writableDatabase.rawQuery(sql,null)


            val dbHelper = DBHelper(context)
            // 첫 번째 : 테이블명
            // 두 번째 : 가지고 오고자 하는 컬럼 이름 목록. null을 넣어주면 모두 가져온다.
            // 세 번째 : 특정 행을 선택하기 위한 조건절
            // 네 번째 : 세 번째에 들어가는 조건절의 ? 에 셋팅될 값 배열
            // 다섯 번째 : Group by의 기준 컬럼
            // 여섯 번째 : Having절에 들어갈 조건절
            // 일곱 번째 : Having절의 ?에 셋팅될 값 배열
            val cursor =
                dbHelper.writableDatabase.query("StudentTable", null, null, null, null, null, null)

            // cursor 객체는 쿼리문에 맞는 행에 접근할 수 있는 객체가 된다.
            // 처음에는 아무 행도 가르키고 있지 않다
            // moveTONext 메서드를 호출하면 다음 행에 접근
            // 이때 접근할 행이 있다면 true, 없다면 false를 반환

            val dataList = mutableListOf<StudentClass>()

            while (cursor.moveToNext()) {
                // 컬럼의 이름을 지정하여 컬럼의 순서값을 가져온다
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("name")
                val idx3 = cursor.getColumnIndex("age")
                val idx4 = cursor.getColumnIndex("korean")

                // 데이터를 가져온다
                val idx = cursor.getInt(idx1)
                val name = cursor.getString(idx2)
                val age = cursor.getInt(idx3)
                val korean = cursor.getInt(idx4)

                val studentClass = StudentClass(idx, name, age, korean)
                dataList.add(studentClass)
            }

            dbHelper.close()

            return dataList
        }


        // Update : 조건에 맞는 행의 컬럼의 값을 수정한다.
        fun updateData(context: Context, obj: StudentClass) {

//            // 쿼리문
//            // idx가 ? 인 행의 textData, intData, doubleData, dateData 컬럼의 값을 변경
//            val sql = """update TestTable
//                | set textData = ?, intData = ?, doubleData = ?, dateData = ?
//                | where idx = ?
//            """.trimMargin()
//
//            // ? 에 들거갈 값
//            val args = arrayOf(obj.textData, obj.intData, obj.doubleData, obj.dateData, obj.idx)
//            // 쿼리실행
//            val dbHelper = DBHelper(context)
//            dbHelper.writableDatabase.execSQL(sql, args)
//            dbHelper.close()

            // 컬럼과 값을 지정하는 ContentValues 생성
            val cv = ContentValues()
            cv.put("name", obj.name)
            cv.put("age", obj.age)
            cv.put("korean", obj.korean)
            // 조건절
            val condition = "idx = ?"
            // ? 에 들어갈 값
            val args = arrayOf(obj.idx.toString())
            // 수정
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.update("StudentTable", cv, condition, args)
            dbHelper.close()
        }


        // Delete : 조건 맞는 행을 삭제한다.
        fun deleteData(context: Context, idx: Int) {
//            // 쿼리문
//            // TestTable에서 idx가 ? 인 행을 삭제
//            val sql = "delete from TestTable where idx = ?"
//            // ? 에 들어갈 값
//            val args = arrayOf(idx)
//            // 쿼리 실행
//            val dbHelper = DBHelper(context)
//            dbHelper.writableDatabase.execSQL(sql, args)
//            dbHelper.close()

            // 조건절
            val condition = "idx = ?"
            // ? 에 들어갈 값
            val args = arrayOf(idx.toString())
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.delete("StudentTable", condition, args)
            dbHelper.close()
        }
    }
}
