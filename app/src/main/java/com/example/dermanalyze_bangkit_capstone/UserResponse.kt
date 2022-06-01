package com.example.dermanalyze_bangkit_capstone

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("last_name")
	val lastName: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("first_name")
	val firstName: String,

	@field:SerializedName("email")
	val email: String
)
