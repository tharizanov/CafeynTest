package com.example.cafeyntest.storage

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Patterns
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception

object ImagePersistenceManager {

    private const val DIR_NAME = "images"

    private lateinit var filesDir: File

    fun initFilesDirectory(context: Context) {
        filesDir = ContextWrapper(context.applicationContext).getDir(DIR_NAME, Context.MODE_PRIVATE)
    }

    fun loadImage(source: String): Bitmap? {
        val inputFile = File(filesDir, getImageName(source))
        if (!inputFile.exists())
            return null

        var fis: FileInputStream? = null
        return try {
            fis = FileInputStream(inputFile)
            BitmapFactory.decodeStream(fis)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            try {
                fis?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun saveImage(source: String, bitmap: Bitmap): Boolean {
        val outputFile = File(filesDir, getImageName(source))
        if (outputFile.exists())
            return true

        var fos: FileOutputStream? = null
        return try {
            fos = FileOutputStream(outputFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            try {
                fos?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun getImageName(source: String): String {
        if (!Patterns.WEB_URL.matcher(source).matches()) {
            return source
        }

        source.split('/').let {
            return if (it.size < 2)
                source
            else
                "${it[it.size-2]}_${it[it.size-1]}"
        }
    }

}