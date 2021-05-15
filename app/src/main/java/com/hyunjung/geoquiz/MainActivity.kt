package com.hyunjung.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var previousButton: ImageButton
    private lateinit var questionTextView: TextView

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.btn_main_true)
        falseButton = findViewById(R.id.btn_main_false)
        nextButton = findViewById(R.id.btn_main_next)
        previousButton = findViewById(R.id.btn_main_previous)
        questionTextView = findViewById(R.id.tv_main_question)

        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1)  % questionBank.size
            updateQuestion()
        }

        previousButton.setOnClickListener {
            if(currentIndex <= 0) {
                currentIndex += questionBank.size - 1
            } else {
                currentIndex--
            }
            updateQuestion()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTestResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTestResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if(userAnswer == correctAnswer) {
                R.string.correct_toast
            } else {
                R.string.incorrect_toast
            }

        val toast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP,0, 500)
        toast.show()
    }
}