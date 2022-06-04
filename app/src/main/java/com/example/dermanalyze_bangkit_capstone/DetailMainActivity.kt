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

        val title = intent.getStringExtra(EXTRA_TITLE)
        val readmore = intent.getStringExtra(EXTRA_READ)
        val photo = intent.getIntExtra(EXTRA_PHOTO, 0)

        Log.i("TAG", "#### $title")
        Log.i("TAG", "#### $photo")


        binding.avatar.setImageResource(photo)
        binding.textView3.text = title
        binding.textView4.text = readmore



    }

    companion object {
        const val EXTRA_USER = "extra_user"
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_READ = "extra_read"
    }
}