package com.example.quizapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.quizapp.model.Question
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAQuestion(question: Question)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAQuestionA(mQuestions: List<Question>)

    @Update
    suspend fun updateAQuestion(question: Question)

    @Delete
    suspend fun deleteAQuestion(question: Question)

    @Query("SELECT * FROM question_table")
    fun getAllQuestion(): LiveData<List<Question>>


}