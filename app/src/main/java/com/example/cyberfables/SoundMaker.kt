package com.example.cyberfables

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Build
import androidx.annotation.RequiresApi

object SoundMaker {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    var soundPool: SoundPool = initSoundPool()
    private var soundMap: HashMap<Int, Int> = hashMapOf()
    private var soundContext: Context? = null
    private var bgMusic: Int = 0
    private var streamMap: HashMap<Int, Int> = hashMapOf()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun initSoundPool(): SoundPool {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        return SoundPool.Builder()
            .setMaxStreams(2)
            .setAudioAttributes(audioAttributes)
            .build()
    }


    fun giveContext(context: Context){
        //set context
        soundContext =  context

    }

    //media player to play 1 off media files in the interactive activities
    fun playSound(soundRes: Int, loop: Int = 0, volume: Float = 1F, priority: Int = 1) {
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
            val soundID = soundPool!!.load(soundContext,soundRes,1)
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