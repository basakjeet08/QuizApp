package com.basakjeet08.quizapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.basakjeet08.quizapp.databinding.ActivityResultBinding
import com.basakjeet08.quizapp.databinding.ActivityResultBinding.inflate

class Result : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("user_name").toString()
        val score = intent.getStringExtra("score").toString()

        binding.tvName.text = name
        binding.tvResult.text = "You Scored $score/10"
    }
}