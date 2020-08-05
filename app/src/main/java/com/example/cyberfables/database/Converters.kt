package com.example.cyberfables.database

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromStringToList(stringListInt: String): List<Int> {
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
    fun fromListToString(intList: List<Int>): String {
        return intList.joinToString(separator = ",")
    }

    @TypeConverter
    fun fromHashMapToString(hashMap: HashMap<Int,Int>): String{
        val keys = ArrayList(hashMap.keys)
        val values = ArrayList(hashMap.values)
        val stringKeys = keys.joinToString(separator = ",")
        val stringValues = values.joinToString(separator = ",")
        return "$stringKeys|$stringValues"
    }

    @TypeConverter
    fun fromStringToHashMap(hashString: String): HashMap<Int,Int>{
        val array = hashString.split("|").map { it }
        val arrayKeys = array[0].split(",").map {it}
        val arrayValues = array[1].split(",").map {it}
        val map = HashMap<Int,Int>()
        for((i, s) in arrayKeys.withIndex()) {
            if (s.isNotEmpty()) {
                map[s.toInt()] = arrayValues[i].toInt()
            }
        }
        return map
    }

    @TypeConverter
    fun fromStringToPair(pairString: String): Pair<Int,Float>{
        val array = pairString.split("|").map { it }
        val pair = Pair(array[0].toInt(), array[1].toFloat())
        return pair
    }

    @TypeConverter
    fun fromPairtoString(pairIntFloat: Pair<Int, Float>): String {
        return "${pairIntFloat.first}|${pairIntFloat.second}"
    }



}