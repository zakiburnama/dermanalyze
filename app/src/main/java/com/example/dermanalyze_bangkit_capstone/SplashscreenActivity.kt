package com.example.dermanalyze_bangkit_capstone

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager

class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

//        val loginPreference = LoginPreference(this)
//        val isLogin = loginPreference.getIsLogin()
//        val intent: Intent = if (isLogin) {
//            Intent(this, MainActivity::class.java)
//        }else {
//            Intent(this, LoginActivity::class.java)
//        }

        val intent = Intent(this, MainActivity::class.java)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        }, delayScreen)
    }

    companion object {
        private const val delayScreen: Long = 2000
    }
}