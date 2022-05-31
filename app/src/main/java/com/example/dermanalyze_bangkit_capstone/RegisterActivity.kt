package com.example.dermanalyze_bangkit_capstone

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.example.dermanalyze_bangkit_capstone.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        val valueregis = Register(
//            id = null,
            first_name = "budi",
            last_name = "badu",
            email = "budi@gmail.com",
            password = "budi123",
//            created_at = null,
        )

        binding.btnRegiter.setOnClickListener {


            Log.i("TAG", "##### btn ditekan")

            val client = ApiConfig().getApiService().register(valueregis)
            client.enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
//                    showLoading(false)
                    Log.i("TAG", "##### sukses")

                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody != null) {

//                        val token = responseBody.access_token
//                        val token = responseBody.access_token

                        val x = responseBody.first_name


                        Log.i("TAG", "##### $x")
//                        Log.i("TAG", "##### $token")
//                        Log.i("TAG", "##### $token")
//                        val isLogin = true
//
//                        saveUser(token, isLogin)

//                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this@LoginActivity).toBundle())
//                        finishAfterTransition()


                    } else {
                        Toast.makeText(this@RegisterActivity,response.message(),Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                    showLoading(false)
                    Log.i("TAG", "##### gagal $t")
                    Toast.makeText(this@RegisterActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })


//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
        }



        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
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