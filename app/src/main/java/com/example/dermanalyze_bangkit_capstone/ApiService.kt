package com.example.dermanalyze_bangkit_capstone

import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//data class Auth (
//    val access_token: String?,
//    val token_type: String?,
//    val detail: List<Detail>?
//)
//
//data class Detail2 (
//    val loc: List<String>,
//    val msg: String,
//    val type: String
//)
//{
//    "first_name": "string",
//    "last_name": "string",
//    "email": "user@example.com",
//    "password": "string"
//}
//{
//    "first_name": "string",
//    "last_name": "string",
//    "id": 0,
//    "email": "user@example.com",
//    "created_at": "2022-05-31T07:50:44.934Z"
//}

data class Register(
//    @SerializedName("id") val id: Int?,
//    @SerializedName("first_name") val first_name: String,
//    @SerializedName("last_name") val last_name: String,
//    @SerializedName("email") val email: String,
//    @SerializedName("password") val password: String,
    val first_name: String,
    val last_name: String,
    val email: String,
    val password: String,
//    @SerializedName("created_at") val created_at: String?
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

//data class LoginResponse(
//    @field:SerializedName("error")
//    val error: Boolean,
//
//    @field:SerializedName("message")
//    val message: String,
//
//    @field:SerializedName("loginResult")
//    val loginResult: LoginResult,
//)
//
//data class LoginResult(
//    @field:SerializedName("userId")
//    val userId: String,
//
//    @field:SerializedName("name")
//    val name: String,
//
//    @field:SerializedName("token")
//    val token: String
//)

//@Entity
//@Parcelize
//data class ListStory(
//    @PrimaryKey
//    @field:SerializedName("id")
//    val id: String,
//) : Parcelable



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

//    @FormUrlEncoded
//    @POST("login")
//    fun loginUser(
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): Call<LoginResponse>
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
//            .baseUrl("https://story-api.dicoding.dev/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}