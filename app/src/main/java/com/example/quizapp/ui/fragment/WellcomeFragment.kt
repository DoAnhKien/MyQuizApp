package com.example.quizapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentWellcomeBinding
import com.example.quizapp.until.exhaustive
import com.example.quizapp.viewmodel.QuestionViewModel
import com.example.quizapp.viewmodel.WellComeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow

class WellcomeFragment : Fragment(R.layout.fragment_wellcome) {

    private var binding: FragmentWellcomeBinding? = null
    private val viewModel: WellComeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWellcomeBinding.bind(view)


        binding?.apply {
            btnPhayGame.setOnClickListener {
                viewModel.onPlayGameClick()
            }
            btnShowListResult.setOnClickListener {
                viewModel.onShowScoreClick()
            }
        }


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.wellComeEvent.collect { event ->
                when (event) {
                    is WellComeViewModel.WellComeEvent.NavigateToPlayGameScreen -> {
                        val action =
                            WellcomeFragmentDirections.actionWellcomeFragmentToPlayGameFragment()
                        findNavController().navigate(action)
                    }
                    is WellComeViewModel.WellComeEvent.NavigateToShowScoreScreen -> {
                        val action =
                            WellcomeFragmentDirections.actionWellcomeFragmentToShowScoreFragment2()
                        findNavController().navigate(action)
                    }
                }.exhaustive
            }
        }


    }
}