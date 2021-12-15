package com.example.cafeyntest.network

import android.util.Log
import com.example.cafeyntest.BuildConfig
import com.example.cafeyntest.domains.network.PhotosResponse
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import java.io.InputStreamReader
import java.io.IOException
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ApiRequestRepository {

    private val gson = GsonBuilder().create()

    suspend fun requestItems(): RequestResult<PhotosResponse> {
        return withContext(Dispatchers.IO) {
            requestItemsBlocking()
        }
    }

    private fun requestItemsBlocking(): RequestResult<PhotosResponse> {
        val urlConnection: HttpURLConnection

        try {
            urlConnection = (URL(BuildConfig.API_URL).openConnection() as? HttpURLConnection)
                ?: return RequestResult.Error(Exception("Null connection"))
        } catch (e: IOException) {
            return RequestResult.Error(Exception("Open connection error: ${e.message}"))
        }

        val reader: Reader

        urlConnection.run {
            requestMethod = "GET"
            setRequestProperty("Content-Type", "application/json; utf-8")
            doInput = true
            connectTimeout = BuildConfig.NET_REQUEST_TIMEOUT_MILLIS

            try {
                connect()

                responseCode.let { code ->
                    if (code in 200..399) {
                        reader = InputStreamReader(inputStream)
                        Log.e("RESPONSE", reader.readText())
                    } else {
                        return RequestResult.Error(Exception("Bad response code: $code"))
                    }
                }
            } catch (e: IOException) {
                return RequestResult.Error(Exception("Connection error: ${e.message}"))
            }
        }

        return try {
            RequestResult.Success(gson.fromJson(reader, PhotosResponse::class.java))
        } catch (e: JsonParseException) {
            RequestResult.Error(Exception("JSON parse error: ${e.message}"))
        } finally {
            reader.close()
        }
    }

}