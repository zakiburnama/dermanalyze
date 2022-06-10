package com.example.dermanalyze_bangkit_capstone

import android.os.AsyncTask
import com.google.android.gms.maps.GoogleMap
import java.io.IOException


class FetchData : AsyncTask<Any?, String?, String?>() {
    var googleNearByPlacesData: String? = null
    var googleMap: GoogleMap? = null
    var url: String? = null
//    override fun doInBackground(vararg `object`: Any): String? {
//    }

    override fun doInBackground(vararg p0: Any?): String? {
        try {
            googleMap = p0.get(0) as GoogleMap?
            url = p0.get(1) as String?
            val downloadUrl = DownloadUrl()
            googleNearByPlacesData = downloadUrl.retireveUrl(url)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return googleNearByPlacesData
    }

}