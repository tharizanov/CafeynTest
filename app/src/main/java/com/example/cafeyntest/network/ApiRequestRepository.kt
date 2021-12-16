package com.example.cafeyntest.network

import com.example.cafeyntest.BuildConfig
import com.example.cafeyntest.domains.network.ResponseItem
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import java.io.IOException
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ApiRequestRepository {

    private val gson = GsonBuilder().create()

    suspend fun requestItems(): RequestResult<ArrayList<ResponseItem>> {
        return withContext(Dispatchers.IO) {
            requestItemsBlocking()
        }
    }

    private fun requestItemsBlocking(): RequestResult<ArrayList<ResponseItem>> {
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
                    } else {
                        return RequestResult.Error(Exception("Bad response code: $code"))
                    }
                }
            } catch (e: IOException) {
                return RequestResult.Error(Exception("Connection error: ${e.message}"))
            }
        }

        val listType = TypeToken.getParameterized(ArrayList::class.java, ResponseItem::class.java).type

        return try {
            RequestResult.Success(gson.fromJson(reader, listType))
        } catch (e: JsonParseException) {
            RequestResult.Error(Exception("JSON parse error: ${e.message}"))
        } finally {
            reader.close()
        }
    }

}