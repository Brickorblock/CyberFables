package com.example.cyberfables.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//represents one question of the Fable1 minigame
@Parcelize
class PasswordQuestion (
    val password: String,
    //true = good pw; false = bad pw
    val answer: Boolean,
    val hasSymbols: Boolean,
    val hasNumbers: Boolean,
    val hasUpper: Boolean,
    val hasLower: Boolean,
    val hardToGuess: Boolean
): Parcelable