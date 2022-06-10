package com.example.dermanalyze_bangkit_capstone

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService2 {
    @GET("nearbysearch/json")
    fun getLocationHospital(
        @Query("location") location: String,
        @Query("radius") radius: Int,
        @Query("type") type: String,
        @Query("key") key: String,
    ): Call<Response>


//    @GET("nearbysearch/json?keyword=cruise&radius=1500&location=-33.8670522%2C151.1957362&type=restaurant&key=AIzaSyAjvdFMaMA_LCSxPf4NjrTToej5VYd4sK8")
//    fun getLocationHospital(): Call<Response>

//    @GET("nearbysearch/json?&key=AIzaSyAjvdFMaMA_LCSxPf4NjrTToej5VYd4sK8")
//    fun getLocationHospital(
//        @Query("type") type: String,
//        @Query("radius") radius: Int,
//        @Query("location") location: String,
//    ): Call<Response>
}

class ApiConfig2 {
    fun getApiService2(): ApiService2 {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
//            .baseUrl("https://dermanalyze-api-dev.herokuapp.com/")
//            .baseUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/")
            .baseUrl("https://maps.googleapis.com/maps/api/place/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService2::class.java)
    }
}