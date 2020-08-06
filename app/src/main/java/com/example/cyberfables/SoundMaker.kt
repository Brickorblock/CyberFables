package com.example.cyberfables

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi

object SoundMaker {
    var soundPool: SoundPool = initSoundPool()
    private var soundMap: HashMap<Int, Int> = hashMapOf()
    private var soundContext: Context? = null
    private var bgMusic: MediaPlayer? = initMediaPlayer()

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

    fun initMediaPlayer(): MediaPlayer? {
        val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
        }
        return mediaPlayer
    }


    fun giveContext(context: Context){
        //set context
        soundContext =  context

    }

    fun Pause(){
        soundPool.release()
        bgMusic!!.pause()
    }

    fun Resume(){
        soundPool = initSoundPool()
        soundMap.clear()
        bgMusic!!.start()
    }

    fun StopBgMusic() {
        //if there is bgmusic playing, stop it
        if (bgMusic!!.isPlaying){
            bgMusic!!.reset()
        }
    }

    fun Release(){
        soundPool.release()
        soundMap.clear()
        bgMusic?.release()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun playBgMusic(soundRes: Int, vol: Float = 0.07F){
        val uri = "android.resource://com.example.cyberfables/"
        if (!bgMusic!!.isPlaying) {
            bgMusic!!.apply {
                setDataSource(soundContext!!, Uri.parse(uri + soundRes))
                isLooping = true
                setVolume(vol, vol)
                prepareAsync() // might take long! (for buffering, etc)
            }

            bgMusic!!.setOnPreparedListener {
                it.start()
            }
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
        soundPool.play(soundID, volume, volume, priority, loop, 1F)
        //store the stream ID for playback control
    }

}