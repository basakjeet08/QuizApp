package com.basakjeet08.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.basakjeet08.quizapp.databinding.ActivityMainBinding
import com.basakjeet08.quizapp.databinding.ActivityMainBinding.inflate

class MainActivity : AppCompatActivity() {

    //ViewBinding binding variable for all the Views in @activity_Main.xml
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        //Button Set On Click Listener which checks that the User Entered A name and then it Goes to the next Screen
        binding.btnStart.setOnClickListener {
            val name = binding.etName.text.toString()

            //Checking if the User Gave a name or Not
            if(name.isEmpty())
                Toast.makeText(this,getString(R.string.please_enter_your_name) , Toast.LENGTH_SHORT).show()
            else{
                val intent = Intent(this,QuizQuestions::class.java)

                //Passing the Name of the user to the next Activity
                intent.putExtra(Constants.USER_NAME , name)

                //Starting the next Activity
                startActivity(intent)

                //Finishing the Current Activity so that it wont be shown if the User hits back
                finish()
            }
        }
    }
}