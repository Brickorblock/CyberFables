package com.example.cyberfables.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//this class stores the data associated with one instance of the Fable1 minigame
@Parcelize
class PasswordMinigame (
    var questions: MutableList<PasswordQuestion>,
    var score: Int = 0,
    var lives: Int = 3,
    // tracks if the user got the previous question correct
    var userCorrect: Boolean = false,
    var totalSize: Int = questions.size
) : Parcelable