package com.example.cafeyntest.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PhotoItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): PhotoItemDao

}