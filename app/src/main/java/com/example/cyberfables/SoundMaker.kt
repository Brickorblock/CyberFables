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
    private var context: Context? = null

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


    //media player to play 1 off media files in the interactive activities
    fun playSound(fragContext: Context, soundRes: Int, loop: Int = 0, volume: Float = 1F, priority: Int = 1) {
        //if context is null, set context to fragContext
        context = context ?: (fragContext as MainActivity).applicationContext
        //play the sound
        player(soundRes,loop, volume, priority)
    }

    private fun player(soundRes: Int, loop: Int, volume: Float, priority: Int){
        //if soundmap has the sound, play it
        if (soundMap.containsKey(soundRes)) {
            playSoundNoLoad(soundMap[soundRes]!!, loop, volume, priority)
        }
        //if it doesnt
        else {
            //load it
            val soundID = soundPool!!.load(context,soundRes,1)
            //save it in the hashmap
            soundMap[soundRes] = soundID
            //wait for it to finish loading, then play it
            soundPool?.setOnLoadCompleteListener { soundPool, sampleId, status ->
                playSoundNoLoad(soundID, loop, volume, priority)
            }
        }
    }


    private fun playSoundNoLoad( soundID: Int, loop: Int, volume: Float, priority: Int){
        soundPool.play(soundID, volume, volume, priority, loop, 1F)
    }

}