package com.example.cyberfables.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "fable_table") class Fable(
    @PrimaryKey
    var id: Int,
    var title: String,
    var blurb: String,
    // cover should be 600 * 900
    var coverImg: Int,
    // icon should be square aspect (pref 100 * 100)
    var iconImg: Int,
    var pages: MutableList<Int>,
    var interactivePages: MutableList<Int>?,
    var interactiveFragmentsNav: MutableList<Int>?,
    var pageToOpenOn: Int = 0

) : Parcelable