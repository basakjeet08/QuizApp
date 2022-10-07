package com.basakjeet08.quizapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.basakjeet08.quizapp.databinding.ActivityQuizQuestionsBinding
import com.basakjeet08.quizapp.databinding.ActivityQuizQuestionsBinding.inflate

class QuizQuestions : AppCompatActivity() {
    private val questionList = Constants.getQuestions()
    private lateinit var binding : ActivityQuizQuestionsBinding
    private var currentQuestion = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        setQuestion()

        binding.btnSubmit.setOnClickListener {
            currentQuestion++
            setQuestion()
        }

    }



    private fun setQuestion(){
        val ques = questionList[currentQuestion]

        with(ques){
            binding.tvQuestion.text = question
            binding.ivFlag.setImageResource(image)
            binding.tvOptionOne.text = optionOne
            binding.tvOptionTwo.text = optionTwo
            binding.tvOptionThree.text = optionThree
            binding.tvOptionFour.text = optionFour
        }
        binding.progressBar.progress = currentQuestion
        binding.tvProgress.text = "$currentQuestion/${binding.progressBar.max}"
    }
}