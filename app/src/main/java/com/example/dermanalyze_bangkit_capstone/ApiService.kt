package com.example.dermanalyze_bangkit_capstone

import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
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

data class LogoutResponse(
    @field:SerializedName("email")
    val email: String?,

    @field:SerializedName("logout_at")
    val logout_at: String?
)

data class Detail(
    @field:SerializedName("loc")
    val loc: List<String>,

    @field:SerializedName("msg")
    val msg: String,

    @field:SerializedName("type")
    val type: String
)

data class PredictResponse(
    @field:SerializedName("id") val id: Int?,
    @field:SerializedName("photo_url") val photo_url: String,
    @field:SerializedName("pred_results") val pred_results: String,
    @field:SerializedName("created_at") val created_at: String,
    @field:SerializedName("owner_id") val owner_id: Int,

    @field:SerializedName("owner") val owner: Owner?,

    @field:SerializedName("detail") val detail: List<Detail>?
)

data class Owner(
    @field:SerializedName("id") val id: Int?,
    @field:SerializedName("email") val email: String
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

    @Multipart
    @POST("predict")
    fun uploadImage(
        @Header("Authorization") authorization: String,
        @Part file: MultipartBody.Part
    ): Call<PredictResponse>

    @GET("history")
    fun getHistory(
        @Header("Authorization") authorization: String,
    ): Call<ArrayList<PredictResponse>>

//    @FormUrlEncoded
    @POST("logout")
    fun logout(
        @Header("Authorization") authorization: String
    ): Call<LogoutResponse>

}

class ApiConfig {
    fun getApiService(): ApiService {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
//            .baseUrl("https://dermanalyze-api-dev.herokuapp.com/")
            .baseUrl("http://35.219.67.156/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}