package com.basakjeet08.quizapp

//This Data Class contains the Blueprint for all the Questions
data class Questions(
    val question: String,               //Question
    val image: Int,                     //Flag Image
    val optionOne: String,              //Option 1 for the TextView
    val optionTwo: String,              //Option 2 for the TextView
    val optionThree: String,            //Option 3 for the TextView
    val optionFour: String,             //Option four For the TextView
    val correctAnswer: Int              //Correct Option among the 4 TextView
)