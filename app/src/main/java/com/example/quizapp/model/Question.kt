package com.example.quizapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "question_table")
@Parcelize
data class Question(
    val levelQuestion: Int,
    val contentQuestion: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    val result: Int,
    val explanation: String,
    @PrimaryKey(autoGenerate = true) val questionId: Int = 0
) : Parcelable {
}