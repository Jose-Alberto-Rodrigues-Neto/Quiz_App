package com.example.quiz

data class Questions(
    val id: Int,
    val quest: String,
    val image: Int,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correctRes: Int
)
