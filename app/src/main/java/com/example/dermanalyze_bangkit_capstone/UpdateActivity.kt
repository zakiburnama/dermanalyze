package com.example.dermanalyze_bangkit_capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dermanalyze_bangkit_capstone.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCancel.setOnClickListener{
            finish()
        }

        binding.btnSave.setOnClickListener{
            finish()
        }
    }
}