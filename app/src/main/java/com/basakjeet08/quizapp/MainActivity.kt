package com.basakjeet08.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.basakjeet08.quizapp.databinding.ActivityMainBinding
import com.basakjeet08.quizapp.databinding.ActivityMainBinding.inflate

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            val name = binding.etName.text.toString()
            if(name.isEmpty())
                Toast.makeText(this,"Enter Your Name" , Toast.LENGTH_SHORT).show()
            else{
                val intent = Intent(this,QuizQuestions::class.java)
                intent.putExtra("user_name" , name)
                startActivity(intent)
                finish()
            }
        }
    }
}