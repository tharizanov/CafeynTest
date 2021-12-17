package com.example.cafeyntest.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhotoItemDao {

    @Query("SELECT * FROM photoitem")
    suspend fun getAll(): List<PhotoItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg items: PhotoItem)

}