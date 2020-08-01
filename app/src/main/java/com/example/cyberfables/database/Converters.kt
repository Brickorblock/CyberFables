package com.example.cyberfables.database

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromStringtoList(stringListInt: String): List<Int> {
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
    fun fromListtoString(intList: List<Int>): String {
        return intList.joinToString(separator = ",")
    }

    @TypeConverter
    fun fromHashMaptoString(hashMap: HashMap<Int,Int>): String{
        var keys = ArrayList(hashMap.keys)
        var values = ArrayList(hashMap.values)
        var stringKeys = keys.joinToString(separator = ",")
        var stringValues = values.joinToString(separator = ",")
        return "$stringKeys|$stringValues"
    }

    @TypeConverter
    fun fromStringtoHashMap(hashString: String): HashMap<Int,Int>{
        val array = hashString.split("|").map { it }
        val arrayKeys = array[0].split(",").map {it}
        val arrayValues = array[1].split(",").map {it}
        val map = HashMap<Int,Int>()
        var i = 0
        for(s in arrayKeys) {
            if (s.isNotEmpty()) {
                map.put(s.toInt(), arrayValues[i].toInt())
            }
            i++
        }
        return map
    }

}