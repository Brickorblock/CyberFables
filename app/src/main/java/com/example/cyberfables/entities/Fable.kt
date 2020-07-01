package com.example.cyberfables.entities

import android.widget.ImageView

class Fable(
    var title: String,
    var blurb: String,
    // cover should be 600 * 900
    var coverImg: Int,
    // icon should be square aspect (pref 100 * 100)
    var iconImg : Int,
    var pages: ArrayList<Int>
) {
    val pageCount = pages.size
}