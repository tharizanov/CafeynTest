package com.example.cafeyntest

import android.app.Application
import com.example.cafeyntest.storage.DatabaseManager
import com.example.cafeyntest.storage.ImagePersistenceManager

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DatabaseManager.initDatabase(this)
        ImagePersistenceManager.initFilesDirectory(this)
    }

}