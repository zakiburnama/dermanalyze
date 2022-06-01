package com.example.dermanalyze_bangkit_capstone

import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


data class Register(
    val first_name: String,
    val last_name: String,
    val email: String,
    val password: String
)

data class UpdateUsers(
    val first_name: String,
    val last_name: String
)

data class RegisterResponse(
    @field:SerializedName("id") val id: Int?,
    @field:SerializedName("first_name") val first_name: String,
    @field:SerializedName("last_name") val last_name: String,
    @field:SerializedName("email") val email: String,
    @field:SerializedName("password") val password: String,
    @field:SerializedName("created_at") val created_at: String?,

    @field:SerializedName("detail")
    val detail: List<Detail>?
)


data class LoginResponse(
    @field:SerializedName("access_token")
    val access_token: String?,

    @field:SerializedName("token_type")
    val token_type: String?,

    @field:SerializedName("detail")
    val detail: List<Detail>?
)

data class Detail(
    @field:SerializedName("loc")
    val loc: List<String>,

    @field:SerializedName("msg")
    val msg: String,

    @field:SerializedName("type")
    val type: String
)

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("users")
    fun register(@Body userData: Register): Call<RegisterResponse>

    @GET("users")
    fun getUsers(
        @Header("Authorization") authorization: String,
    ): Call<RegisterResponse>

    @PUT("users")
    fun putUsers(
        @Header("Authorization") authorization: String,
        @Body userData: UpdateUsers
    ): Call<RegisterResponse>

}


class ApiConfig {
    fun getApiService(): ApiService {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dermanalyze-api-dev.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}