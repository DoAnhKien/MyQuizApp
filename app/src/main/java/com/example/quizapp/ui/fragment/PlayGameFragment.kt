package com.example.quizapp.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.quizapp.R
import com.example.quizapp.aenum.QuestionStatus
import com.example.quizapp.databinding.FragmentPlayGameBinding
import com.example.quizapp.model.Question
import com.example.quizapp.until.Const
import com.example.quizapp.viewmodel.QuestionViewModel
import com.example.quizapp.viewmodel.ScoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_play_game.*
import kotlinx.android.synthetic.main.fragment_wellcome.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class PlayGameFragment : Fragment(R.layout.fragment_play_game), View.OnClickListener {

    private lateinit var mQuestions: List<Question>
    private val questionViewModel: QuestionViewModel by viewModels()
    private val scoreViewModel: ScoreViewModel by viewModels()
    private var binding: FragmentPlayGameBinding? = null
    private val TAG = "kienda"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlayGameBinding.bind(view)

        setDataForQuestion()
        observeStatus()
        setOnClickListener()
        checkTheAnswer()
    }

    private fun onRefreshData() {

    }

    private fun checkTheCorrectAnswer(correctAnswer: Int) {
        when (correctAnswer) {
            Const.OPTION_A -> {
                setDisableClick()
                setTextForScore()
                binding?.btnQuestionA?.setTextColor(Color.GREEN)
                binding?.tvExplanation?.visibility = View.VISIBLE
                Handler().postDelayed({
                    setDataForQuestion()
                }, Const.DURATION)
                setEnableClick()
                Log.d(TAG, "checkTheCorrectAnswer: A")
            }
            Const.OPTION_B -> {
                setDisableClick()
                setTextForScore()
                binding?.btnQuestionB?.setTextColor(Color.GREEN)
                binding?.tvExplanation?.visibility = View.VISIBLE
                Handler().postDelayed({
                    setDataForQuestion()
                    setTheColorWhiteForAllQuestion()
                }, Const.DURATION)
                setEnableClick()
                Log.d(TAG, "checkTheCorrectAnswer: B")
            }
            Const.OPTION_C -> {
                setDisableClick()
                setTextForScore()
                binding?.btnQuestionC?.setTextColor(Color.GREEN)
                binding?.tvExplanation?.visibility = View.VISIBLE
                Handler().postDelayed({
                    setDataForQuestion()
                    setTheColorWhiteForAllQuestion()
                }, Const.DURATION)
                setEnableClick()
                Log.d(TAG, "checkTheCorrectAnswer: C")
            }
            Const.OPTION_D -> {
                setDisableClick()
                setTextForScore()
                binding?.btnQuestionD?.setTextColor(Color.GREEN)
                binding?.tvExplanation?.visibility = View.VISIBLE
                Handler().postDelayed({
                    setDataForQuestion()
                }, Const.DURATION)
                setEnableClick()
                Log.d(TAG, "checkTheCorrectAnswer: D")
            }
        }
    }

    private fun checkFailAnswerForOptionA(failAnswer: Int, correctAnswer: Int) {
        when (correctAnswer) {
            Const.OPTION_A -> {
                binding?.btnQuestionA?.setTextColor(Color.GREEN)
                setDisableClick()
                setTextForScore()
            }
            Const.OPTION_B -> {
                binding?.btnQuestionB?.setTextColor(Color.GREEN)
                setDisableClick()
                setTextForScore()
            }
            Const.OPTION_C -> {
                binding?.btnQuestionC?.setTextColor(Color.GREEN)
                setDisableClick()
                setTextForScore()
            }
            Const.OPTION_D -> {
                binding?.btnQuestionD?.setTextColor(Color.GREEN)
                setDisableClick()
                setTextForScore()
            }
        }
    }

    private fun setTextForScore() {
        val currentQuestion = questionViewModel.getPosition() + 1
        binding?.tvCurrentQuestion?.text = "Question: $currentQuestion"
    }


    private fun checkTheAnswer() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            questionViewModel.questionEvent.collect { event ->
                when (event) {
                    is QuestionViewModel.QuestionEvent.OnResponseToLosingGame -> {
                        scoreViewModel.insertAScore(event.score)
                    }
                    is QuestionViewModel.QuestionEvent.OnResponseToTheCorrectAnswer -> {
                        checkTheCorrectAnswer(event.correctAnswer)
                    }
                    is QuestionViewModel.QuestionEvent.OnResponseToTheFailAnswer -> {
                        when (event.failAnswer) {
                            Const.OPTION_A -> {
                                binding?.btnQuestionA?.setTextColor(Color.RED)
                                checkFailAnswerForOptionA(event.failAnswer, event.correctAnswer)
                            }
                            Const.OPTION_B -> {
                                binding?.btnQuestionB?.setTextColor(Color.RED)
                                checkFailAnswerForOptionA(event.failAnswer, event.correctAnswer)
                            }
                            Const.OPTION_C -> {
                                binding?.btnQuestionC?.setTextColor(Color.RED)
                                checkFailAnswerForOptionA(event.failAnswer, event.correctAnswer)
                            }
                            Const.OPTION_D -> {
                                binding?.btnQuestionD?.setTextColor(Color.RED)
                                checkFailAnswerForOptionA(event.failAnswer, event.correctAnswer)
                            }
                        }
                    }
                    is QuestionViewModel.QuestionEvent.OnResponseCurrentScore -> {
                        binding?.tvScore?.text = "Score: " + event.score
                    }
                }
            }
        }
    }

    private fun setOnClickListener() {
        binding?.btnQuestionA?.setOnClickListener(this)
        binding?.btnQuestionB?.setOnClickListener(this)
        binding?.btnQuestionC?.setOnClickListener(this)
        binding?.btnQuestionD?.setOnClickListener(this)

    }

    private fun setDataForQuestion() {
        questionViewModel.getMQuestion().observe(requireActivity(), {
            val question: Question = questionViewModel.setDataForQuestion(it)
            binding?.tvContentQuestion?.text = question.contentQuestion
            binding?.btnQuestionA?.text = "A " + question.optionA
            binding?.btnQuestionB?.text = "B " + question.optionB
            binding?.btnQuestionC?.text = "C " + question.optionC
            binding?.btnQuestionD?.text = "D " + question.optionD
            binding?.tvExplanation?.text = question.explanation
            binding?.tvExplanation?.visibility = View.INVISIBLE
            setTheColorWhiteForAllQuestion()
        })
    }


    private fun setDisableClick(
    ) {
        binding?.btnQuestionA?.isEnabled = false
        binding?.btnQuestionB?.isEnabled = false
        binding?.btnQuestionC?.isEnabled = false
        binding?.btnQuestionD?.isEnabled = false
    }


    private fun setTheColorWhiteForAllQuestion() {
        binding?.btnQuestionA?.setTextColor(Color.WHITE)
        binding?.btnQuestionB?.setTextColor(Color.WHITE)
        binding?.btnQuestionC?.setTextColor(Color.WHITE)
        binding?.btnQuestionC?.setTextColor(Color.WHITE)
    }

    private fun setEnableClick(
    ) {
        binding?.btnQuestionA?.isEnabled = true
        binding?.btnQuestionB?.isEnabled = true
        binding?.btnQuestionC?.isEnabled = true
        binding?.btnQuestionD?.isEnabled = true
    }

    private fun observeStatus() {
        questionViewModel.getQuestionStatus().observe(requireActivity(), {
            when (it) {
                QuestionStatus.CORRECT_ANSWER -> {
                    Toast.makeText(activity, "Bạn đã trả lời đúng", Toast.LENGTH_SHORT).show()
                }
                QuestionStatus.YOU_WIN -> {
                    Toast.makeText(activity, "Bạn đã thắng", Toast.LENGTH_SHORT).show()
                }
                QuestionStatus.YOU_LOSSE -> {
                    Toast.makeText(activity, "Bạn đã thua cuộc", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

    private fun checkForOptionA() {
        questionViewModel.getMQuestion().observe(requireActivity(), {
            questionViewModel.checkForOptionA(
                it
            )
        })
    }

    private fun checkForOptionB() {
        questionViewModel.getMQuestion().observe(requireActivity(), {
            questionViewModel.checkForOptionB(
                it
            )
        })
    }

    private fun checkForOptionC() {
        questionViewModel.getMQuestion().observe(requireActivity(), {
            questionViewModel.checkForOptionC(
                it
            )
        })
    }

    private fun checkForOptionD() {
        questionViewModel.getMQuestion().observe(requireActivity(), {
            questionViewModel.checkForOptionD(
                it
            )
        })
    }

    override fun onClick(v: View?) {
        when (v) {
            btnQuestionA -> {
                setDisableClick()
                checkForOptionA()
                checkTheAnswer()
            }
            btnQuestionB -> {
                setDisableClick()
                checkForOptionB()
                checkTheAnswer()
            }
            btnQuestionC -> {
                setDisableClick()
                checkForOptionC()
                checkTheAnswer()
            }
            btnQuestionD -> {
                setDisableClick()
                checkForOptionD()
                checkTheAnswer()
            }
        }
    }


}