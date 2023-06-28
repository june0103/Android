package com.test.android_memo

class DataClass {
    companion object{
        // 카테고리를 key값으로 사용하고 vlue는 메모객체
        val ctgrMap : MutableMap<String, MutableList<MemoClass>> = mutableMapOf()
    }
}

data class MemoClass(var name : String, var content : String)