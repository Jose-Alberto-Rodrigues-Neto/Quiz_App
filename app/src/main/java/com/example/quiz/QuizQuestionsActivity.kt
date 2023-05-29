package com.example.quiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var playerScore: Int = 0

    private var positionStage: Int = 1
    private var positionQ: ArrayList<Questions>? = null
    private var positionOptionSelected: Int = 0
    private var playerUserName: String? = null

    private var progressBar: ProgressBar? = null
    private var txProgressBar: TextView? = null
    private var txQuestion: TextView? = null
    private var image: ImageView? = null
    private var op1: TextView? = null
    private var op2: TextView? = null
    private var op3: TextView? = null
    private var op4: TextView? = null
    private var btnSubmit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        playerUserName = intent.getStringExtra(Constants.user_name)

        progressBar = findViewById(R.id.progressBar)
        txProgressBar = findViewById(R.id.text_progressBar)
        txQuestion = findViewById(R.id.question1)
        image = findViewById(R.id.q_image)

        op1 = findViewById(R.id.op_1)
        op1?.setOnClickListener(this)

        op2 = findViewById(R.id.op_2)
        op2?.setOnClickListener(this)

        op3 = findViewById(R.id.op_3)
        op3?.setOnClickListener(this)

        op4 = findViewById(R.id.op_4)
        op4?.setOnClickListener(this)

        btnSubmit = findViewById(R.id.btn_submit)
        btnSubmit?.setOnClickListener(this)

        positionQ = Constants.getQuestions()

        setQuestion()
        defaultOptionsView()
    }

    private fun setQuestion() {

        val stageQuestion: Questions = positionQ!![positionStage - 1]
        defaultOptionsView()

        btnSubmit?.text = if ((positionStage) == positionQ!!.size) "FINISH" else "SUBMIT"

        progressBar?.progress = positionStage
        txProgressBar?.text = "${positionStage} / ${progressBar?.max}"
        txQuestion?.text = stageQuestion.quest
        image?.setImageResource(stageQuestion.image)
        op1?.text = stageQuestion.option1
        op2?.text = stageQuestion.option2
        op3?.text = stageQuestion.option3
        op4?.text = stageQuestion.option4


    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        op1?.let { options.add(0, it) }
        op2?.let { options.add(1, it) }
        op3?.let { options.add(2, it) }
        op4?.let { options.add(3, it) }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        positionOptionSelected = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.op_1 -> {
                op1?.let {
                    selectedOptionView(it, 1)
                }
            }

            R.id.op_2 -> {
                op2?.let {
                    selectedOptionView(it, 2)
                }
            }

            R.id.op_3 -> {
                op3?.let { selectedOptionView(it, 3) }
            }

            R.id.op_4 -> {
                op4?.let { selectedOptionView(it, 4) }
            }

            R.id.btn_submit -> {
                if(positionOptionSelected == 0){
                    positionStage++
                    when{
                        positionStage<=positionQ!!.size ->{
                            setQuestion()
                        }else ->{
                            val intent= Intent(this, ResultPanel::class.java)
                            intent.putExtra(Constants.user_name, playerUserName)
                            intent.putExtra(Constants.correct_answer, playerScore)
                            intent.putExtra(Constants.total_questions, positionQ?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else{
                    val question = positionQ?.get(positionStage-1)
                    if(question!!.correctRes != positionOptionSelected){
                        ansView(positionOptionSelected, R.drawable.wrong_option_border_bg)
                    }else{
                        playerScore++
                    }
                    ansView(question.correctRes, R.drawable.correct_option_border_bg)

                    if(positionStage == positionQ!!.size){
                        btnSubmit?.text = "FINISH"
                    }else{
                        btnSubmit?.text = "NEXT"
                    }

                    positionOptionSelected = 0


                }

            }
        }
    }

    private fun ansView(answer: Int, drawbView: Int) {
        when (answer) {
            1 -> {
                op1?.background = ContextCompat.getDrawable(
                    this,
                    drawbView
                )
            }

            2 -> {
                op2?.background = ContextCompat.getDrawable(
                    this,
                    drawbView
                )
            }

            3 -> {
                op3?.background = ContextCompat.getDrawable(
                    this,
                    drawbView
                )
            }

            4 -> {
                op4?.background = ContextCompat.getDrawable(
                    this,
                    drawbView
                )
            }
        }
    }
}