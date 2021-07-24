package com.example.quizapp.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.aenum.QuestionStatus
import com.example.quizapp.db.QuestionDao
import com.example.quizapp.model.Question
import com.example.quizapp.model.Score
import com.example.quizapp.until.Const
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class QuestionViewModel @ViewModelInject constructor(
    private val questionDao: QuestionDao,
) : ViewModel() {

    private val mQuestions = questionDao.getAllQuestion()
    private var score: Int = 0
    private var position: Int = 0
    private var questionStatus: MutableLiveData<QuestionStatus> = MutableLiveData<QuestionStatus>()
    private val questionEventChannel = Channel<QuestionEvent>()
    val questionEvent = questionEventChannel.receiveAsFlow()

    fun insertTheQuestion(mQuestions: List<Question>) = viewModelScope.launch(Dispatchers.Main) {
        questionDao.insertAQuestionA(mQuestions)
    }

    fun insertTheQuestion(question: Question) = viewModelScope.launch(Dispatchers.Main) {
        questionDao.insertAQuestion(question)
    }

    fun getMQuestion(): LiveData<List<Question>> = mQuestions

    fun getQuestionStatus(): MutableLiveData<QuestionStatus> = questionStatus

    fun getScore(): Int = score

    fun getPosition(): Int = position


    fun checkForOptionA(
        mQuestions: List<Question>,
    ) = viewModelScope.launch(Dispatchers.Main) {

        val result: Int = mQuestions[position].result
        if (Const.OPTION_A === result) {
            questionStatus.postValue(QuestionStatus.CORRECT_ANSWER)
            questionEventChannel.send(QuestionEvent.OnResponseToTheCorrectAnswer(Const.OPTION_A))
            score++
            questionEventChannel.send(QuestionEvent.OnResponseCurrentScore(score))
            if (position == mQuestions.size - 1) {
                questionStatus.postValue(QuestionStatus.YOU_WIN)
            } else {
                position++
            }
        } else {
            val myScore: Score = Score(score)
            questionEventChannel.send(QuestionEvent.OnResponseToLosingGame(myScore))
            questionEventChannel.send(
                QuestionEvent.OnResponseToTheFailAnswer(
                    Const.OPTION_A,
                    result
                )
            )
            questionStatus.postValue(QuestionStatus.YOU_LOSSE)
        }

    }

    fun checkForOptionB(
        mQuestions: List<Question>,
    ) = viewModelScope.launch(Dispatchers.Main) {

        try {
            val result: Int = mQuestions[position].result
            if (Const.OPTION_B === result) {
                questionStatus.postValue(QuestionStatus.CORRECT_ANSWER)
                questionEventChannel.send(QuestionEvent.OnResponseToTheCorrectAnswer(Const.OPTION_B))
                score++
                questionEventChannel.send(QuestionEvent.OnResponseCurrentScore(score))
                if (position == mQuestions.size - 1) {
                    questionStatus.postValue(QuestionStatus.YOU_WIN)
                } else {
                    position++
                }
            } else {
                val myScore: Score = Score(score)
                questionEventChannel.send(QuestionEvent.OnResponseToLosingGame(myScore))
                questionEventChannel.send(
                    QuestionEvent.OnResponseToTheFailAnswer(
                        Const.OPTION_B,
                        result
                    )
                )
                questionStatus.postValue(QuestionStatus.YOU_LOSSE)
            }
        } catch (e: Exception) {
            Log.d("kienda", "checkForOptionB: $e")
        }

    }


    fun checkForOptionC(
        mQuestions: List<Question>,
    ) = viewModelScope.launch(Dispatchers.Main) {
        val result: Int = mQuestions[position].result
        if (Const.OPTION_C === result) {
            questionStatus.postValue(QuestionStatus.CORRECT_ANSWER)
            questionEventChannel.send(QuestionEvent.OnResponseToTheCorrectAnswer(Const.OPTION_C))
            score++
            questionEventChannel.send(QuestionEvent.OnResponseCurrentScore(score))
            if (position == mQuestions.size - 1) {
                questionStatus.postValue(QuestionStatus.YOU_WIN)
            } else {
                position++
            }
        } else {
            val myScore: Score = Score(score)
            questionEventChannel.send(QuestionEvent.OnResponseToLosingGame(myScore))
            questionEventChannel.send(
                QuestionEvent.OnResponseToTheFailAnswer(
                    Const.OPTION_C,
                    result
                )
            )
            questionStatus.postValue(QuestionStatus.YOU_LOSSE)
        }
    }

    fun checkForOptionD(
        mQuestions: List<Question>,
    ) = viewModelScope.launch(Dispatchers.Main) {

        val result: Int = mQuestions[position].result
        if (Const.OPTION_D === result) {
            questionStatus.postValue(QuestionStatus.CORRECT_ANSWER)
            questionEventChannel.send(QuestionEvent.OnResponseToTheCorrectAnswer(Const.OPTION_D))
            score++
            questionEventChannel.send(QuestionEvent.OnResponseCurrentScore(score))
            if (position == mQuestions.size - 1) {
                questionStatus.postValue(QuestionStatus.YOU_WIN)
            } else {
                position++
            }
        } else {
            val myScore: Score = Score(score)
            questionEventChannel.send(QuestionEvent.OnResponseToLosingGame(myScore))
            questionEventChannel.send(
                QuestionEvent.OnResponseToTheFailAnswer(
                    Const.OPTION_D,
                    result
                )
            )
            questionStatus.postValue(QuestionStatus.YOU_LOSSE)
        }

    }


    fun setDataForQuestion(
        mQuestions: List<Question>,
    ): Question {
        return mQuestions[position]
    }

    fun resetData() = viewModelScope.launch(Dispatchers.Main) {
        position = 0
        score = 0
    }


    fun insertAQuestion(question: Question) = viewModelScope.launch(Dispatchers.Main) {
        questionDao.insertAQuestion(question)
    }

    fun updateAQuestion(question: Question) = viewModelScope.launch(Dispatchers.Main) {
        questionDao.updateAQuestion(question)
    }


    sealed class QuestionEvent {
        data class OnResponseToLosingGame(val score: Score) : QuestionEvent()
        data class OnResponseToTheCorrectAnswer(val correctAnswer: Int) : QuestionEvent()
        data class OnResponseToTheFailAnswer(val failAnswer: Int, val correctAnswer: Int) :
            QuestionEvent()

        data class OnResponseCurrentScore(val score: Int) : QuestionEvent()
    }
}