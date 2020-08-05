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

    fun Pause(){
        soundPool.autoPause()
    }

    fun Resume(){
        soundPool.autoResume()
    }

    fun StopBgMusic() {
        //if there is bgmusic playing, stop it
        if (bgMusic != 0){
            soundPool.stop(streamMap[bgMusic]!!)
            soundMap.remove(bgMusic)
            bgMusic = 0
        }
    }

    fun Release(){
        soundPool.release()
    }

    fun playBgMusic(soundRes: Int, vol: Float = 0.07F){
        //if the bg music isnt being played
        if (bgMusic != soundRes){
            //save it and play it
            bgMusic = soundRes
            playSound(bgMusic, -1, vol, 2)
        }
    }

    //media player to play 1 off media files in the interactive activities
    fun playSound(soundRes: Int, loop: Int = 0, volume: Float = 1F, priority: Int = 1) {
        //if soundmap has the sound, play it
        if (soundMap.containsKey(soundRes)) {
            playSoundNoLoad(soundRes, loop, volume, priority)
        }
        //if it doesnt
        else {
            //load it and save the sound in the hashmap
            soundMap[soundRes] = soundPool.load(soundContext,soundRes,1)
            //wait for it to finish loading, then play it
            soundPool.setOnLoadCompleteListener { soundPool, sampleId, status ->
                playSoundNoLoad(soundRes, loop, volume, priority)
            }
        }
    }

    private fun playSoundNoLoad( soundRes: Int, loop: Int, volume: Float, priority: Int) {
        val soundID = soundMap[soundRes]!!
        val streamID = soundPool.play(soundID, volume, volume, priority, loop, 1F)
        //store the stream ID for playback control
        streamMap.put(soundRes, streamID)
    }

}