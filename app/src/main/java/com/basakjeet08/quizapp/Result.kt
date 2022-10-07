package com.basakjeet08.quizapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.basakjeet08.quizapp.databinding.ActivityResultBinding
import com.basakjeet08.quizapp.databinding.ActivityResultBinding.inflate

class Result : AppCompatActivity() {

    //Binding variable for the Views in activity_result xml file
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        //Getting the User name and the score from the previous activity
        val name = intent.getStringExtra(Constants.USER_NAME).toString()
        val score = intent.getStringExtra(Constants.SCORE).toString()
        val maxQuestion = intent.getStringExtra(Constants.MAX_QUESTIONS).toString()

        //Setting the name and the score to the TextView in the current activity
        binding.tvName.text = name
        binding.tvResult.text = getString(R.string.progress , score , maxQuestion)

        //Button Finish setOnClick listener to finish the activity and end the App
        binding.btnFinish.setOnClickListener { finish() }
    }
}