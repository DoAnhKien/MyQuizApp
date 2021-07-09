package com.example.quizapp.ui.fragment

import android.os.Bundle
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
import com.example.quizapp.viewmodel.QuestionViewModel
import com.example.quizapp.viewmodel.ScoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.util.*

@AndroidEntryPoint
class PlayGameFragment : Fragment(R.layout.fragment_play_game) {

    private lateinit var mQuestions: List<Question>
    private val viewModel: QuestionViewModel by viewModels()
    private val scoreViewModel: ScoreViewModel by viewModels()
    private var binding: FragmentPlayGameBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlayGameBinding.bind(view)
        viewModel.mQuestions.observe(requireActivity(), {
            Log.d("kienda","Questions size: ${it.size}")
        })

        observeStatus()
        mQuestions = ArrayList<Question>()
        viewModel.mQuestions.observe(requireActivity(), {
            mQuestions = it
            viewModel.setDataForQuestion(
                binding!!.btnQuestionA,
                binding!!.btnQuestionB,
                binding!!.btnQuestionC,
                binding!!.btnQuestionD,
                mQuestions,
                binding!!.tvScore,
                binding!!.tvCurrentQuestion,
                binding!!.tvContentQuestion
            )
            binding!!.btnQuestionA.setOnClickListener {
                viewModel.checkForOptionA(
                    binding!!.btnQuestionA,
                    binding!!.btnQuestionB,
                    binding!!.btnQuestionC,
                    binding!!.btnQuestionD,
                    mQuestions,
                    binding!!.tvExplanation,
                    binding!!.tvCurrentQuestion,
                    binding!!.tvContentQuestion,
                    binding!!.tvScore
                )
            }
            binding!!.btnQuestionB.setOnClickListener {
                viewModel.checkForOptionB(
                    binding!!.btnQuestionA,
                    binding!!.btnQuestionB,
                    binding!!.btnQuestionC,
                    binding!!.btnQuestionD,
                    mQuestions,
                    binding!!.tvExplanation,
                    binding!!.tvCurrentQuestion,
                    binding!!.tvContentQuestion,
                    binding!!.tvScore
                )
            }
            binding!!.btnQuestionC.setOnClickListener {
                viewModel.checkForOptionC(
                    binding!!.btnQuestionA,
                    binding!!.btnQuestionB,
                    binding!!.btnQuestionC,
                    binding!!.btnQuestionD,
                    mQuestions,
                    binding!!.tvExplanation,
                    binding!!.tvCurrentQuestion,
                    binding!!.tvContentQuestion,
                    binding!!.tvScore
                )
            }
            binding!!.btnQuestionD.setOnClickListener {
                viewModel.checkForOptionD(
                    binding!!.btnQuestionA,
                    binding!!.btnQuestionB,
                    binding!!.btnQuestionC,
                    binding!!.btnQuestionD,
                    mQuestions,
                    binding!!.tvExplanation,
                    binding!!.tvCurrentQuestion,
                    binding!!.tvContentQuestion,
                    binding!!.tvScore
                )
            }
        })

        binding!!.refresh.setOnRefreshListener {
            viewModel.setEnableClick(
                binding!!.btnQuestionA,
                binding!!.btnQuestionB,
                binding!!.btnQuestionC,
                binding!!.btnQuestionD
            )
            viewModel.resetData()
            viewModel.mQuestions.observe(requireActivity(), {
                mQuestions = it
                viewModel.setDataForQuestion(
                    binding!!.btnQuestionA,
                    binding!!.btnQuestionB,
                    binding!!.btnQuestionC,
                    binding!!.btnQuestionD,
                    mQuestions,
                    binding!!.tvScore,
                    binding!!.tvCurrentQuestion,
                    binding!!.tvContentQuestion
                )
            })
            binding!!.refresh.isRefreshing = false
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.questionEvent.collect { event ->
                when (event) {
                    is QuestionViewModel.QuestionEvent.OnResponseToLosingGame -> {
                        scoreViewModel.insertAScore(event.score)
                    }
                }
            }
        }
    }

    private fun observeStatus() {
        viewModel.questionStatus.observe(requireActivity(), {
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


}