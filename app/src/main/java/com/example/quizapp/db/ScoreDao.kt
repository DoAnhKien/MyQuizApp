package com.example.quizapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.quizapp.model.Score
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreDao {

    @Insert
    suspend fun insertAScore(score: Score)

    @Update
    suspend fun updateAScore(score: Score)

    @Delete
    suspend fun deleteAScore(score: Score)

    @Query("SELECT * FROM score_table ORDER BY score DESC")
    fun getAllScore(): LiveData<List<Score>>


}