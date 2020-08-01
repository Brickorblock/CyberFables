package com.example.cyberfables

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.media.MediaPlayer
import android.widget.ImageView

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

    //media player to play 1 off media files in the interactive activities
    fun playSound(context: Context, sound: Int){
        val mediaPlayer = MediaPlayer.create(context, sound )
        mediaPlayer.setOnPreparedListener {
            mediaPlayer.start()
        }
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.release()
        }
    }
}