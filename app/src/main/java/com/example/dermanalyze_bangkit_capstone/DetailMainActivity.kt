package com.example.dermanalyze_bangkit_capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dermanalyze_bangkit_capstone.databinding.ActivityDetailMainBinding

class DetailMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val x = intent.getSerializableExtra(EXTRA_USER) as Articles
//
//        val y = x.titleArticles.toString()
//
//        Log.i("TAG", "#### y")

//        binding.textView3.text = y



    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}