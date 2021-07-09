package com.example.quizapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.callback.MyScoreClicked
import com.example.quizapp.databinding.ItemScoreBinding
import com.example.quizapp.model.Score

class ScoreAdapter(private val onMyScoreClicked: MyScoreClicked) :
    ListAdapter<Score, ScoreAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemScoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentScore = getItem(position)
        holder.bind(currentScore)
    }

    inner class ViewHolder(private val binding: ItemScoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val score = getItem(position)
                        onMyScoreClicked.onMyScoreClick(score)
                    }
                }
                root.setOnLongClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val score = getItem(position)
                        onMyScoreClicked.onMyScoreOnLongClick(score)
                    }
                    true
                }
            }
        }


        fun bind(mScore: Score) {
            binding.apply {
                tvCurrentScore.text = mScore.score.toString()
            }
        }
    }


    class DiffCallback : DiffUtil.ItemCallback<Score>() {
        override fun areItemsTheSame(oldItem: Score, newItem: Score): Boolean {
            return oldItem.scoreId == newItem.scoreId
        }

        override fun areContentsTheSame(oldItem: Score, newItem: Score): Boolean {
            return oldItem.scoreId == newItem.scoreId
        }
    }


}