package com.example.cyberfables

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Build
import androidx.annotation.RequiresApi

object SoundMaker {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    var soundPool: SoundPool = initSoundPool()
    var soundMap: HashMap<Int, Int> = hashMapOf()
    var context: Context? = null


    //media player to play 1 off media files in the interactive activities
    fun playSound(fragContext: Context, soundRes: Int) {

        if(context == null) context = (fragContext as MainActivity).applicationContext

        //if soundmap has the sound, play it
        if (soundMap.containsKey(soundRes)) playSoundNoLoad(soundMap[soundRes]!!)

        //if it doesnt, load it then play it
        else playSoundOnLoad(soundRes)
    }


    private fun playSoundOnLoad(soundRes: Int) {
        soundMap[soundRes] = soundPool!!.load(context,soundRes,1)
        soundPool?.setOnLoadCompleteListener { soundPool, sampleId, status ->
            playSoundNoLoad(soundMap[soundRes]!!)
        }
    }

    private fun playSoundNoLoad( soundID: Int) {
        soundPool.play(soundID, 1F, 1F, 1, 0, 1F)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun initSoundPool(): SoundPool {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        return SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()
    }
}