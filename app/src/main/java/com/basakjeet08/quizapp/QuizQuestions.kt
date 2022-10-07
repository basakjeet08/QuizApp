package com.basakjeet08.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.basakjeet08.quizapp.databinding.ActivityQuizQuestionsBinding
import com.basakjeet08.quizapp.databinding.ActivityQuizQuestionsBinding.inflate

class QuizQuestions : AppCompatActivity() {
    private val questionList = Constants.getQuestions()
    private lateinit var binding : ActivityQuizQuestionsBinding
    private var currentQuestion = 0
    private var userAnswer = 0
    private var score = 0
    private lateinit var name:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        setQuestion()
        name = intent.getStringExtra("user_name").toString()



        binding.tvOptionOne.setOnClickListener {
            userAnswer = 1
            setTextViewDefaults()
            setTextViewSelected(binding.tvOptionOne)
        }
        binding.tvOptionTwo.setOnClickListener {
            userAnswer = 2
            setTextViewDefaults()
            setTextViewSelected(binding.tvOptionTwo)
        }
        binding.tvOptionThree.setOnClickListener {
            userAnswer = 3
            setTextViewDefaults()
            setTextViewSelected(binding.tvOptionThree)
        }
        binding.tvOptionFour.setOnClickListener {
            userAnswer = 4
            setTextViewDefaults()
            setTextViewSelected(binding.tvOptionFour)
        }









        binding.btnSubmit.setOnClickListener {
            if(binding.btnSubmit.text.toString() == "Submit"){
                showCorrectedAnswer()
                userAnswer = 0
                binding.btnSubmit.text = "Next"
            }
            else {
                if(currentQuestion == questionList.size-1){
                    val intent = Intent(this,Result::class.java)
                    intent.putExtra("score",score.toString())
                    intent.putExtra("user_name",name)
                    startActivity(intent)
                    finish()
                }
                else{
                    binding.btnSubmit.text = "Submit"
                    currentQuestion++
                    setQuestion()
                }

            }
        }

    }



    private fun setQuestion(){
        setTextViewDefaults()
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

    private fun setTextViewDefaults(){
        with(binding){
            tvOptionOne.setBackgroundResource(R.drawable.default_option_border_bg)
            tvOptionTwo.setBackgroundResource(R.drawable.default_option_border_bg)
            tvOptionThree.setBackgroundResource(R.drawable.default_option_border_bg)
            tvOptionFour.setBackgroundResource(R.drawable.default_option_border_bg)


            tvOptionOne.setTypeface(null, Typeface.NORMAL)
            tvOptionTwo.setTypeface(null, Typeface.NORMAL)
            tvOptionThree.setTypeface(null, Typeface.NORMAL)
            tvOptionFour.setTypeface(null, Typeface.NORMAL)
        }
    }
    private fun setTextViewSelected(tv:TextView){
        tv.setBackgroundResource(R.drawable.selected_option_border_bg)
        tv.setTypeface(null,Typeface.BOLD)
    }

    private fun showCorrectedAnswer(){
        when(userAnswer){
            1 -> binding.tvOptionOne.setBackgroundColor(Color.parseColor("#B82525"))
            2 -> binding.tvOptionTwo.setBackgroundColor(Color.parseColor("#B82525"))
            3 -> binding.tvOptionThree.setBackgroundColor(Color.parseColor("#B82525"))
            4 -> binding.tvOptionFour.setBackgroundColor(Color.parseColor("#B82525"))
        }
        when(questionList[currentQuestion].correctAnswer){
            1 -> binding.tvOptionOne.setBackgroundColor(Color.parseColor("#47914A"))
            2 -> binding.tvOptionTwo.setBackgroundColor(Color.parseColor("#47914A"))
            3 -> binding.tvOptionThree.setBackgroundColor(Color.parseColor("#47914A"))
            4 -> binding.tvOptionFour.setBackgroundColor(Color.parseColor("#47914A"))
        }
        if(userAnswer == questionList[currentQuestion].correctAnswer)
            score++
    }

}