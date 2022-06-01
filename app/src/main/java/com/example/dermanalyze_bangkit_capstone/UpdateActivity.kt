package com.example.dermanalyze_bangkit_capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

        val loginPreference = LoginPreference(this)
        val token = loginPreference.getToken()
        val tokenauth = "Bearer $token"


        binding.btnCancel.setOnClickListener{
            finish()
        }

        binding.btnSave.setOnClickListener{
            getUsersData(tokenauth)
        }
    }

    private fun getUsersData(token: String) {
        val fn = binding.etFirstnameupdate.text.toString().trim()
        val ln = binding.etLastnameupdate.text.toString().trim()
        val newDataUser = UpdateUsers(fn, ln)

        val client = ApiConfig().getApiService().putUsers(token, newDataUser)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            )
            {
//                    showLoading(false)
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    finish()
                } else {
                    Toast.makeText(this@UpdateActivity,response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                    showLoading(false)
                Toast.makeText(this@UpdateActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}