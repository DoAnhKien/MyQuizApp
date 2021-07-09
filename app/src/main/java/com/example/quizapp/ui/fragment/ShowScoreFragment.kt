package com.example.quizapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.adapter.ScoreAdapter
import com.example.quizapp.callback.MyScoreClicked
import com.example.quizapp.databinding.FragmentShowScoreBinding
import com.example.quizapp.model.Score
import com.example.quizapp.until.exhaustive
import com.example.quizapp.viewmodel.ScoreViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShowScoreFragment : Fragment(R.layout.fragment_show_score), MyScoreClicked {

    private val viewModel: ScoreViewModel by viewModels()
    private val TAG = "ShowScoreFragment"
    private lateinit var searchView: SearchView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentShowScoreBinding.bind(view)

        val scoreAdapter = ScoreAdapter(this)

        binding.apply {
            rvShowAllScore.apply {
                adapter = scoreAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

            ItemTouchHelper(object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val score = scoreAdapter.currentList[viewHolder.adapterPosition]
                    viewModel.onScoreSWiped(score)
                }

            }).attachToRecyclerView(rvShowAllScore)
        }

        viewModel.mScores.observe(requireActivity(), {
            scoreAdapter.submitList(it)
            Log.d(TAG, "Score size: ${it.size}")
        })


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.scoreEvent.collect { event ->
                when (event) {
                    is ScoreViewModel.ScoreEvent.ShowUndoDeleteTask -> {
                        Snackbar.make(requireView(), "Task deleted", Snackbar.LENGTH_LONG)
                            .setAction("UNDO") {
                                viewModel.undoDeleteClick(event.score)
                            }.show()
                    }
                    is ScoreViewModel.ScoreEvent.ShowTaskSavedConfimationMessage -> {

                    }
                }.exhaustive
            }
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_show_score, menu)

        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView

    }

    override fun onMyScoreClick(score: Score) {

    }

    override fun onMyScoreOnLongClick(score: Score) {

    }

}