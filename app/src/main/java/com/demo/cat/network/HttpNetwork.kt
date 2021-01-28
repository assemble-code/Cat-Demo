package com.demo.cat.network

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import com.demo.cat.model.ResponseBaseClass
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


object HttpNetwork {


    fun getData(requestUrl: String): ResponseBaseClass {
        val url = URL(requestUrl)
        val urlConnection = url.openConnection() as HttpURLConnection
        return try {
            val inStream: InputStream = BufferedInputStream(urlConnection.inputStream)
            ResponseBaseClass(urlConnection.responseCode, readStream(inStream))
        } catch (exception: Exception) {
            ResponseBaseClass(urlConnection.responseCode, null) //return black in case of exception
        } finally {
            urlConnection.disconnect()
        }
    }

    private fun readStream(inStream: InputStream): String {
        val stringBuilder = StringBuilder()
        val reader = BufferedReader(InputStreamReader(inStream))
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        return stringBuilder.toString()
    }

    /**
     * check if the device has active internet connection
     * **/
     fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}