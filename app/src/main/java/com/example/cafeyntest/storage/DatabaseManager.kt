package com.example.cafeyntest.storage

import android.content.Context
import androidx.room.Room
import com.example.cafeyntest.domains.network.ResponseItem

object DatabaseManager {

    private const val DB_NAME = "photo_items_db"

    private lateinit var db: AppDatabase

    fun initDatabase(context: Context) {
        db = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME).build()
    }

    suspend fun getAllItems(): List<PhotoItem> = db.userDao().getAll()

    suspend fun storeItem(item: ResponseItem) {
        db.userDao().insertAll(PhotoItem(item.id, item.albumId, item.title, item.url, item.thumbnailUrl))
    }

    suspend fun storeItems(items: List<ResponseItem>) {
        if (items.isEmpty())
            return

        ArrayList<PhotoItem>(items.size).run {
            for (item in items) {
                add(PhotoItem(item.id, item.albumId, item.title, item.url, item.thumbnailUrl))
            }
            db.userDao().insertAll(*toTypedArray())
        }
    }

}