package com.example.quizapp.di

import android.app.Application
import androidx.room.Room
import com.example.quizapp.db.QuestionDatabase
import com.example.quizapp.db.ScoreDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ScoreModule {
    @Provides
    @Singleton
    fun providesScoreDatabase(
        app: Application,
        callBack: ScoreDatabase.CallBack
    ) = Room.databaseBuilder(app, ScoreDatabase::class.java, "score.db")
        .fallbackToDestructiveMigration()
        .addCallback(callBack)
        .build()

    @Provides
    fun providesScoreDao(db: ScoreDatabase) = db.scoreDao()

    @ApplicationScope1
    @Provides
    @Singleton
    fun providesApplicationScope1() = CoroutineScope(SupervisorJob())

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope1