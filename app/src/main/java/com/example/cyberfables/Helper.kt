package com.example.cyberfables

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Build
import android.util.Log
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.example.cyberfables.entities.Fable
import kotlinx.coroutines.withContext

class Helper {


    // this is used to handle custom hitboxes (touch zones) on a static image -
    // we overlay the image with a hitbox map containing different colours, then we determine
    // what hitbox was clicked by getting its colour
    // credit: https://stackoverflow.com/questions/16670774/clickable-area-of-image
    fun getHitboxColour(hitboxesImg: ImageView, x: Int, y: Int): Int {

        var bitmap =
            Bitmap.createBitmap(hitboxesImg.width, hitboxesImg.height, Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bitmap)
        hitboxesImg.draw(canvas)

        var pixel: Int
        try {
            pixel = bitmap.getPixel(x, y)
        } catch (e: IllegalArgumentException) {
            // sometimes on emulator, a tap can be registered out-of-bounds from the emu screen
            // this would never happen on a real device
            pixel = -1
        }

        return pixel
    }
}