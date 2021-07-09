package com.example.quizapp.callback

import com.example.quizapp.model.Score

interface MyScoreClicked {
    fun onMyScoreClick(score: Score)
    fun onMyScoreOnLongClick(score: Score)
}