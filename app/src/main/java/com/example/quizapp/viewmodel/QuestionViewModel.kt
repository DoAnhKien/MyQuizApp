package com.example.quizapp.viewmodel

import android.graphics.Color
import android.widget.Button
import android.widget.TextView
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.aenum.QuestionStatus
import com.example.quizapp.db.QuestionDao
import com.example.quizapp.model.Question
import com.example.quizapp.model.Score
import com.example.quizapp.until.Const
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class QuestionViewModel @ViewModelInject constructor(
    private val questionDao: QuestionDao,
) : ViewModel() {

    private val mQuestions = questionDao.getAllQuestion()
    private var score: Int = 0
    private var position: Int = 0
    private var questionStatus: MutableLiveData<QuestionStatus> = MutableLiveData<QuestionStatus>()
    private val questionEventChannel = Channel<QuestionEvent>()
    val questionEvent = questionEventChannel.receiveAsFlow()


    fun checkForOptionA(
        btnOptionA: Button,
        btnOptionB: Button,
        btnOptionC: Button,
        btnOptionD: Button,
        mQuestions: List<Question>,
        tvExplaination: TextView,
        tvCurrentQuestion: TextView,
        tvContentQuestion: TextView,
        tvScore: TextView
    ) = viewModelScope.launch {
        val result: Int = mQuestions[position].result
        if (Const.OPTION_A === result) {
            questionStatus.postValue(QuestionStatus.CORRECT_ANSWER)
            score++
            tvScore.text = score.toString()
            if (position == mQuestions.size - 1) {
                questionStatus.postValue(QuestionStatus.YOU_WIN)
            } else {
                position++
                setDataForQuestion(
                    btnOptionA,
                    btnOptionB,
                    btnOptionC,
                    btnOptionD,
                    mQuestions,
                    tvScore,
                    tvCurrentQuestion,
                    tvContentQuestion
                )
            }
        } else {
            val myScore: Score = Score(score)
            questionEventChannel.send(QuestionEvent.OnResponseToLosingGame(myScore))
            setDisableClick(btnOptionA, btnOptionB, btnOptionC, btnOptionD)
            btnOptionA.setTextColor(Color.RED)
            questionStatus.postValue(QuestionStatus.YOU_LOSSE)
            when (result) {
                Const.OPTION_B -> {
                    tvExplaination.visibility = TextView.VISIBLE
                    btnOptionB.setTextColor(Color.GREEN)
                    tvExplaination.text = "Đáp án B " + mQuestions[position].explanation
                }
                Const.OPTION_C -> {
                    tvExplaination.visibility = TextView.VISIBLE
                    btnOptionC.setTextColor(Color.GREEN)
                    tvExplaination.text = "Đáp án C " + mQuestions[position].explanation
                }
                Const.OPTION_D -> {
                    tvExplaination.visibility = TextView.VISIBLE
                    btnOptionD.setTextColor(Color.GREEN)
                    tvExplaination.text = "Đáp án D " + mQuestions[position].explanation
                }
            }
        }
    }

    fun checkForOptionB(
        btnOptionA: Button,
        btnOptionB: Button,
        btnOptionC: Button,
        btnOptionD: Button,
        mQuestions: List<Question>,
        tvExplaination: TextView,
        tvCurrentQuestion: TextView,
        tvContentQuestion: TextView,
        tvScore: TextView
    ) = viewModelScope.launch {
        val result: Int = mQuestions[position].result
        if (Const.OPTION_B === result) {
            questionStatus.postValue(QuestionStatus.CORRECT_ANSWER)
            score++
            tvScore.text = score.toString()
            if (position == mQuestions.size - 1) {
                questionStatus.postValue(QuestionStatus.YOU_WIN)
            } else {
                position++
                setDataForQuestion(
                    btnOptionA,
                    btnOptionB,
                    btnOptionC,
                    btnOptionD,
                    mQuestions,
                    tvScore,
                    tvCurrentQuestion,
                    tvContentQuestion
                )
            }
        } else {
            val myScore: Score = Score(score)
            questionEventChannel.send(QuestionEvent.OnResponseToLosingGame(myScore))
            setDisableClick(btnOptionA, btnOptionB, btnOptionC, btnOptionD)
            btnOptionB.setTextColor(Color.RED)
            questionStatus.postValue(QuestionStatus.YOU_LOSSE)
            when (result) {
                Const.OPTION_A -> {
                    tvExplaination.visibility = TextView.VISIBLE
                    btnOptionA.setTextColor(Color.GREEN)
                    tvExplaination.text = "Đáp án B " + mQuestions[position].explanation
                }
                Const.OPTION_C -> {
                    tvExplaination.visibility = TextView.VISIBLE
                    btnOptionC.setTextColor(Color.GREEN)
                    tvExplaination.text = "Đáp án C " + mQuestions[position].explanation
                }
                Const.OPTION_D -> {
                    tvExplaination.visibility = TextView.VISIBLE
                    btnOptionD.setTextColor(Color.GREEN)
                    tvExplaination.text = "Đáp án D " + mQuestions[position].explanation
                }
            }
        }
    }

    fun checkForOptionC(
        btnOptionA: Button,
        btnOptionB: Button,
        btnOptionC: Button,
        btnOptionD: Button,
        mQuestions: List<Question>,
        tvExplaination: TextView,
        tvCurrentQuestion: TextView,
        tvContentQuestion: TextView,
        tvScore: TextView
    ) = viewModelScope.launch {
        val result: Int = mQuestions[position].result
        if (Const.OPTION_C === result) {
            questionStatus.postValue(QuestionStatus.CORRECT_ANSWER)
            score++
            tvScore.text = score.toString()
            if (position == mQuestions.size - 1) {
                questionStatus.postValue(QuestionStatus.YOU_WIN)
            } else {
                position++
                setDataForQuestion(
                    btnOptionA,
                    btnOptionB,
                    btnOptionC,
                    btnOptionD,
                    mQuestions,
                    tvScore,
                    tvCurrentQuestion,
                    tvContentQuestion
                )
            }
        } else {
            setDisableClick(btnOptionA, btnOptionB, btnOptionC, btnOptionD)
            btnOptionC.setTextColor(Color.RED)
            questionStatus.postValue(QuestionStatus.YOU_LOSSE)
            when (result) {
                Const.OPTION_A -> {
                    tvExplaination.visibility = TextView.VISIBLE
                    btnOptionA.setTextColor(Color.GREEN)
                    tvExplaination.text = "Đáp án B " + mQuestions[position].explanation
                }
                Const.OPTION_B -> {
                    tvExplaination.visibility = TextView.VISIBLE
                    btnOptionB.setTextColor(Color.GREEN)
                    tvExplaination.text = "Đáp án C " + mQuestions[position].explanation
                }
                Const.OPTION_D -> {
                    tvExplaination.visibility = TextView.VISIBLE
                    btnOptionD.setTextColor(Color.GREEN)
                    tvExplaination.text = "Đáp án D " + mQuestions[position].explanation
                }
            }
        }
    }


    fun checkForOptionD(
        btnOptionA: Button,
        btnOptionB: Button,
        btnOptionC: Button,
        btnOptionD: Button,
        mQuestions: List<Question>,
        tvExplaination: TextView,
        tvCurrentQuestion: TextView,
        tvContentQuestion: TextView,
        tvScore: TextView
    ) = viewModelScope.launch {
        val result: Int = mQuestions[position].result
        if (Const.OPTION_D === result) {
            questionStatus.postValue(QuestionStatus.CORRECT_ANSWER)
            score++
            tvScore.text = score.toString()
            if (position == mQuestions.size - 1) {
                questionStatus.postValue(QuestionStatus.YOU_WIN)
            } else {
                position++
                setDataForQuestion(
                    btnOptionA,
                    btnOptionB,
                    btnOptionC,
                    btnOptionD,
                    mQuestions,
                    tvScore,
                    tvCurrentQuestion,
                    tvContentQuestion
                )
            }
        } else {
            val myScore: Score = Score(score)
            questionEventChannel.send(QuestionEvent.OnResponseToLosingGame(myScore))
            setDisableClick(btnOptionA, btnOptionB, btnOptionC, btnOptionD)
            btnOptionD.setTextColor(Color.RED)
            questionStatus.postValue(QuestionStatus.YOU_LOSSE)
            when (result) {
                Const.OPTION_B -> {
                    tvExplaination.visibility = TextView.VISIBLE
                    btnOptionB.setTextColor(Color.GREEN)
                    tvExplaination.text = "Đáp án B " + mQuestions[position].explanation
                }
                Const.OPTION_C -> {
                    tvExplaination.visibility = TextView.VISIBLE
                    btnOptionC.setTextColor(Color.GREEN)
                    tvExplaination.text = "Đáp án C " + mQuestions[position].explanation
                }
                Const.OPTION_A -> {
                    tvExplaination.visibility = TextView.VISIBLE
                    btnOptionA.setTextColor(Color.GREEN)
                    tvExplaination.text = "Đáp án A " + mQuestions[position].explanation
                }
            }
        }
    }


    fun setDataForQuestion(
        btnOptionA: Button,
        btnOptionB: Button,
        btnOptionC: Button,
        btnOptionD: Button,
        mQuestions: List<Question>,
        tvScore: TextView,
        tvCurrentQuestions: TextView,
        tvContentQuestion: TextView
    ) = viewModelScope.launch {
        val currentQuestion = mQuestions[position]
        btnOptionA.text = "A " + currentQuestion.optionA
        btnOptionB.text = "B " + currentQuestion.optionB
        btnOptionC.text = "C " + currentQuestion.optionC
        btnOptionD.text = "D " + currentQuestion.optionD
        val numberQuestion = position + 1
        when (currentQuestion.levelQuestion) {
            Const.EASY_DIFFICULTY -> {
                tvCurrentQuestions.text =
                    "Bạn đã trả lời: " + numberQuestion + "/" + mQuestions.size + " Câu hỏi dễ"
                tvScore.text = "Điểm của bạn là: $score"
                tvContentQuestion.text = currentQuestion.contentQuestion
            }
            Const.MEIUM_DIFFICULTY -> {
                tvCurrentQuestions.text =
                    "Bạn đã trả lời: " + numberQuestion + "/" + mQuestions.size + " Câu hỏi vừa"
                tvScore.text = "Điểm của bạn là: $score"
                tvContentQuestion.text = currentQuestion.contentQuestion
            }
            Const.HARD_DIFFICULTY -> {
                tvCurrentQuestions.text =
                    "Bạn đã trả lời: " + numberQuestion + "/" + mQuestions.size + " Câu hỏi khó"
                tvScore.text = "Điểm của bạn là: $score"
                tvContentQuestion.text = currentQuestion.contentQuestion
            }
        }
    }

    private fun setDisableClick(
        btnOptionA: Button,
        btnOptionB: Button,
        btnOptionC: Button,
        btnOptionD: Button
    ) = viewModelScope.launch {
        btnOptionA.isEnabled = false
        btnOptionB.isEnabled = false
        btnOptionC.isEnabled = false
        btnOptionD.isEnabled = false
    }

    fun setEnableClick(
        btnOptionA: Button,
        btnOptionB: Button,
        btnOptionC: Button,
        btnOptionD: Button
    ) = viewModelScope.launch {
        btnOptionA.setEnabled(true)
        btnOptionB.setEnabled(true)
        btnOptionC.setEnabled(true)
        btnOptionD.setEnabled(true)
        btnOptionA.setTextColor(Color.WHITE)
        btnOptionB.setTextColor(Color.WHITE)
        btnOptionC.setTextColor(Color.WHITE)
        btnOptionD.setTextColor(Color.WHITE)
    }

    fun resetData() = viewModelScope.launch {
        position = 0
        score = 0
    }


    fun insertAQuestion(question: Question) = viewModelScope.launch {
        questionDao.insertAQuestion(question)
    }

    fun updateAQuestion(question: Question) = viewModelScope.launch {
        questionDao.updateAQuestion(question)
    }


    sealed class QuestionEvent {
        data class OnResponseToLosingGame(val score: Score) : QuestionEvent()
    }
}