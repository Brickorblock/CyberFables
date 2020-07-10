package com.example.cyberfables.database

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromString(stringListInt: String): List<Int> {
        val array = stringListInt.split(",").map { it }
        val intList = mutableListOf<Int>()
        for(s in array) {
            if (s.isNotEmpty()) {
                intList.add(s.toInt())
            }
        }
        return intList
    }

    @TypeConverter
    fun toString(intList: List<Int>): String {
        return intList.joinToString(separator = ",")
    }
}