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

    //This variable is an ArrayList which takes all the Questions from Constants Object
    private val questionList = Constants.getQuestions()

    //Binging variable is used For ViewBinding .... It contains all the View Ids of activity_quiz_questions
    private lateinit var binding : ActivityQuizQuestionsBinding

    //Current Question Variable is used to keep Track of which Question is currently Going on
    private var currentQuestion = 0

    //User Answer Keeps track of which option User Selected at last before Hitting the Submit Button
    private var userAnswer = 0

    //Score is Used to keep a track of Total Score Scored by user
    private var score = 0

    // It contains the User_Name / Name of the user which he provided at the Main Activity
    private lateinit var name:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        //It takes the Intent extra Data which is the name of the user from the Last Activity
        name = intent.getStringExtra(Constants.USER_NAME).toString()

        // setQuestion is called to set the first Question to the Screen for user To see and the later Questions are iterated by the hitting of Submit Button
        setQuestion()

        //setOnClick listener for all the Various Option TextViews
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

        //setOnClick Listener for the Submit Button is given below
        binding.btnSubmit.setOnClickListener {

            //Checking if the Button was showing Submit when the user hit it so it will show the Corrected Options and color them
            if(binding.btnSubmit.text.toString() == "Submit"){
                showCorrectedAnswer()
                userAnswer = 0
                binding.btnSubmit.text = getString(R.string.next)
            }

            //If the Button was showing Next then it will go to the next Question
            else {

                //If the next Button is hit after the Last Question then the user will be shown the result Activity
                if(currentQuestion == questionList.size-1){
                    val intent = Intent(this,Result::class.java)

                    //Passing the name and the Score of the User to the later Activity
                    intent.putExtra(Constants.SCORE,score.toString())
                    intent.putExtra(Constants.USER_NAME,name)
                    intent.putExtra(Constants.MAX_QUESTIONS , questionList.size.toString())

                    //Starting the Next Activity and finishing this one so that the user wont get it back by hitting Back
                    startActivity(intent)
                    finish()
                }

                //If the Text at the Button is Next then it will change back to Submit and the currentQuestion will increment and the Question will Be Set
                else{
                    binding.btnSubmit.text = getString(R.string.submit)
                    currentQuestion++
                    setQuestion()
                }
            }
        }
    }

    //Setting the Questions (Changing the text of the Question ,Option and the Flag to the next Question)
    private fun setQuestion(){

        //Calling the setTextViewDefaults function to make all the textField colors and style to default one for the next Question
        setTextViewDefaults()

        //Changing the TextViews and all the necessary thing to the new Question
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

        //Setting the String to TextView using the string Xml file format
        binding.tvProgress.text = getString(R.string.progress , currentQuestion.toString(),binding.progressBar.max.toString())
    }

    //This Function reverses all the changes in textViews like the background color,border and the textStyle
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

    //This function sets the chosen textView background border and textStyle so we can under
    private fun setTextViewSelected(tv:TextView){
        tv.setBackgroundResource(R.drawable.selected_option_border_bg)
        tv.setTypeface(null,Typeface.BOLD)
    }

    //This Function sets the Correct option to Green and if we choose wrong option then if will set the Wrong Option to Red
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

        //To check if the givenAnswer by user is correct
        if(userAnswer == questionList[currentQuestion].correctAnswer)
            score++
    }
}