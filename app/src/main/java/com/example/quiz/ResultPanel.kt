package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultPanel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_panel)

        val userName: TextView = findViewById(R.id.username)
        val score: TextView = findViewById(R.id.quiz_score)
        val btnFinish: Button = findViewById(R.id.btn_finishApp)

        userName.text = intent.getStringExtra(Constants.user_name)
        val totalQuestions = intent.getIntExtra(Constants.total_questions, 0)
        val correctAns = intent.getIntExtra(Constants.correct_answer, 0)

        score.text = "$correctAns of $totalQuestions"

        btnFinish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}