package com.example.cyberfables.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cyberfables.entities.Fable




@Dao
interface FableDao {
    //TODO make return query LiveData<>
    @Query("SELECT * from fable_table ORDER BY id ASC")
    fun getAllFables(): List<Fable>

    //TODO make @insert queries suspend fun and add coroutines as well as a repository and viewmodel
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFables(fables: List<Fable>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(fable: Fable)

    @Query("SELECT * FROM fable_table WHERE id = :id")
    fun getFable(id: Int): Fable
}