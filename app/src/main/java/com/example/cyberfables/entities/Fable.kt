package com.example.cyberfables.entities

import android.os.Parcelable
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.cyberfables.BookDetail
import com.example.cyberfables.BookshelfFragment
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
class Fable (
    var title: String,
    var blurb: String,
    // cover should be 600 * 900
    var coverImg: Int,
    // icon should be square aspect (pref 100 * 100)
    var iconImg : Int,
    var pages: ArrayList<Int>
) : Parcelable {
    @IgnoredOnParcel
    val pageCount = pages.size

}