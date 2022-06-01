package com.example.dermanalyze_bangkit_capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dermanalyze_bangkit_capstone.databinding.ActivityUpdateBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = binding.editTextTextPersonEmail
        val firstName = binding.editTextTextPersonFirstName
        val lastName = binding.editTextTextPersonLastName

        ApiConfig().getApiService().getUser().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        firstName.setText(responseBody.firstName)
                        lastName.setText(responseBody.lastName)
                        email.setText(responseBody.email)
                    }
                } else{
                    Log.d("TAG", "onResponse: gagal1: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("TAG", "onResponse: gagal2")

            }
        })

        binding.btnCancel.setOnClickListener{
            finish()
        }

        binding.btnSave.setOnClickListener{
            val userData = Update(firstName.text.toString().trim(),lastName.text.toString().trim())
            ApiConfig().getApiService().updateUser(userData).enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        finish()
                    } else {
                        Log.d("TAG", "onResponse: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("TAG", "onResponse1: ${t.message}")
                }

            })
            finish()

        }
    }
}