package com.example.quizapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quizapp.R
import com.example.quizapp.di.ApplicationScope
import com.example.quizapp.model.Question
import com.example.quizapp.model.Score
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Score::class], version = 3, exportSchema = false)
abstract class ScoreDatabase : RoomDatabase() {
    abstract fun scoreDao(): ScoreDao

    class CallBack @Inject constructor(
        private val scoreDatabase: Provider<ScoreDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val scoreDao = scoreDatabase.get().scoreDao()
            applicationScope.launch {
                scoreDao.insertAScore(Score(9))
                scoreDao.insertAScore(Score(5))
                scoreDao.insertAScore(Score(6))
                scoreDao.insertAScore(Score(7))
            }
        }
    }
}