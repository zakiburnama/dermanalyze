package com.example.dermanalyze_bangkit_capstone

import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DownloadUrl {
    @Throws(IOException::class)
    fun retireveUrl(url: String?): String {
        var urlData = ""
        var httpURLConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        try {
            val getUrl = URL(url)
            httpURLConnection = getUrl.openConnection() as HttpURLConnection
            httpURLConnection.connect()
            inputStream = httpURLConnection.getInputStream()
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val sb = StringBuffer()
            var line: String? = ""
            while (bufferedReader.readLine().also { line = it } != null) {
                sb.append(line)
            }
            urlData = sb.toString()
            bufferedReader.close()
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
        } finally {
            inputStream?.close()
            httpURLConnection?.disconnect()
        }
        return urlData
    }
}