package com.example.quizapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.quizapp.db.ScoreDao
import com.example.quizapp.model.Score
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class ScoreViewModel @ViewModelInject constructor(
    private val scoreDao: ScoreDao
) : ViewModel() {

    private val scoreEventChanel = Channel<ScoreEvent>()
    val scoreEvent = scoreEventChanel.receiveAsFlow()

    val mScores = scoreDao.getAllScore()

    fun onScoreSWiped(score: Score) = viewModelScope.launch(Dispatchers.Main) {
        scoreDao.deleteAScore(score)
        scoreEventChanel.send(ScoreEvent.ShowUndoDeleteTask(score))
    }

    fun insertAScore(score: Score) = viewModelScope.launch(Dispatchers.Main) {
        scoreDao.insertAScore(score)
    }


    fun undoDeleteClick(score: Score) = viewModelScope.launch(Dispatchers.Main) {
        scoreDao.insertAScore(score)
    }


    sealed class ScoreEvent {
        data class ShowUndoDeleteTask(val score: Score) : ScoreEvent()
        data class ShowTaskSavedConfimationMessage(val message: String) : ScoreEvent()
    }

}