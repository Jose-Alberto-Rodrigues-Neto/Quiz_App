package com.example.quiz

object Constants {

    const val user_name: String = "Username"
    const val total_questions: String = "total_questions"
    const val correct_answer: String = "correct_answer"

    fun getQuestions(): ArrayList<Questions>{
        val questionList = ArrayList<Questions>()

        val q1 = Questions(
            1,
            "Who's the artist of this painting?",
            R.drawable.ic_picture,
            "Monet",
            "Manet",
            "Renoir",
            "Van Gogh",
            4

        )

        val q2 = Questions(
            2,
            "Who's the artist of this painting?",
            R.drawable.ic_picture2,
            "Renoir",
            "Manet",
            "Monet",
            "Van Gogh",
            2

        )

        val q3 = Questions(
            3,
            "QWho's the artist of this painting?",
            R.drawable.ic_picture3,
            "Van Gogh",
            "Monet",
            "Renoir",
            "Manet",
            3

        )

        val q4 = Questions(
            4,
            "Who's the artist of this painting?",
            R.drawable.ic_picture4,
            "Monet",
            "Renoir",
            "Manet",
            "Van Gogh",
            1

        )

        questionList.add(q1)
        questionList.add(q2)
        questionList.add(q3)
        questionList.add(q4)

        return questionList
    }

}