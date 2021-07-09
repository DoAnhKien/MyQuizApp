package com.example.quizapp.di

import android.app.Application
import android.text.Annotation
import androidx.room.Room
import com.example.quizapp.db.QuestionDatabase
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
object QuestionModule {

    @Provides
    @Singleton
    fun providesQuestionDatabase(
        app: Application,
        callBack: QuestionDatabase.CallBack
    ) = Room.databaseBuilder(app, QuestionDatabase::class.java, "question.db")
        .fallbackToDestructiveMigration()
        .addCallback(callBack)
        .build()

    @Provides
    fun providesQuestionDao(db: QuestionDatabase) = db.questionDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun providesApplicationScope() = CoroutineScope(SupervisorJob())
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope