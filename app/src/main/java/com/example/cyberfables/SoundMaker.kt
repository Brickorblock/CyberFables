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
    fun playSound(fragContext: Context, soundRes: Int, loop: Int = 0) {
        //if context is null, set context
        if(context == null) context = (fragContext as MainActivity).applicationContext
        //play the sound
        player(soundRes,loop)
    }

    private fun player(soundRes: Int, loop: Int){
        //if soundmap has the sound, play it
        if (soundMap.containsKey(soundRes)) {
            playSoundNoLoad(soundMap[soundRes]!!, loop)
        }
        //if it doesnt
        else {
            //load it
            val soundID = soundPool!!.load(context,soundRes,1)
            //save it in the hashmap
            soundMap[soundRes] = soundID
            //wait for it to finish loading, then play it
            soundPool?.setOnLoadCompleteListener { soundPool, sampleId, status ->
                playSoundNoLoad(soundID, loop)
            }
        }
    }


    private fun playSoundNoLoad( soundID: Int, loop: Int){
        soundPool.play(soundID, 1F, 1F, 1, loop, 1F)
    }

}