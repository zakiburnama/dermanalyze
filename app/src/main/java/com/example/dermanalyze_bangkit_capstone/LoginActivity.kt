package com.example.dermanalyze_bangkit_capstone

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.example.dermanalyze_bangkit_capstone.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()


        binding.btnLogin.setOnClickListener {
//            val email = binding.etEmail.text.toString().trim()
//            val password = binding.etPassword.text.toString().trim()

//            val email = "coba1111@gmail.com"
//            val password = "coba123"

            val email = "budi@gmail.com"
            val password = "budi123"

            Log.i("TAG", "##### $email")
            Log.i("TAG", "##### $password")

            val client = ApiConfig().getApiService().login(email, password)
//            val client = ApiConfig().getApiService().loginUser(email, password)
            client.enqueue(object : Callback<LoginResponse> {
//            client.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
//                    showLoading(false)

                    Log.i("TAG", "##### sukses")

                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody != null) {

//                        val token = responseBody.access_token
                        val token = responseBody.access_token

                        Log.i("TAG", "##### $token")
                        Log.i("TAG", "##### $token")
                        Log.i("TAG", "##### $token")
//                        val isLogin = true
//
//                        saveUser(token, isLogin)

//                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this@LoginActivity).toBundle())
//                        finishAfterTransition()


                    } else {
                        Toast.makeText(this@LoginActivity, response.message(), Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    showLoading(false)
                    Log.i("TAG", "##### gagal $t")
                    Toast.makeText(this@LoginActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })



//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()

        }

        binding.tvRegiter.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}