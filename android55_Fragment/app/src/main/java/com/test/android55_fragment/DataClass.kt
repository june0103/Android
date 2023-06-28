package com.test.android55_fragment

class DataClass {
    companion object{

        val studentList = mutableListOf<StudentClass>()

    }

}
data class StudentClass(var name: String, var age : Int, var korean: Int)