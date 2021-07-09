package com.example.quizapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "score_table")
@Parcelize
data class Score(
    val score: Int,
    @PrimaryKey(autoGenerate = true) val scoreId: Int = 0
) : Parcelable {
}