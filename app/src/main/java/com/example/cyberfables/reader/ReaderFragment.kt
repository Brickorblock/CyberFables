package com.example.cyberfables.reader

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.cyberfables.BookDetailFragment
import com.example.cyberfables.R
import com.example.cyberfables.SoundMaker
import com.example.cyberfables.entities.Fable
import kotlinx.android.synthetic.main.fragment_reader.view.*


class ReaderFragment : Fragment() {

    private val TAG = "ReaderFragment"

    private var soundPool: SoundPool? = null
    private lateinit var fable: Fable

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_reader, container, false)

        //grab fable from bundle args (from BookDetailFragment)
        fable = (requireArguments().get(BookDetailFragment.KEY) as Fable)

        //get soundMap
        val soundMap = getSoundPool(fable)

        // Instantiate a ViewPager2 and a PagerAdapter.
        val viewPager = root.readerPager
        viewPager.setPageTransformer(DepthPageTransformer())
        // The pager adapter, which provides the pages to the view pager widget.
        viewPager.adapter = ReaderAdapter(fable, soundMap, soundPool)
        //set the page the viewpager should show
        viewPager.setCurrentItem(fable.pageToOpenOn, false)



        // Inflate the layout for this fragment
        return root
    }

    override fun onResume() {
        super.onResume()
        //play the background music
        fable.bgMusic?.let {
            SoundMaker.playBgMusic(fable.bgMusic!!.first, fable.bgMusic!!.second)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getSoundPool(fable: Fable): HashMap<Int, Int> {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        //if theres background music increase the max streams to 2
        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()
        val soundMap = HashMap<Int,Int>()
        //load the sounds for each page
        for ((key, value) in fable.sounds) {
            soundMap[key] = soundPool!!.load(context,value,1)
        }
        return soundMap
    }

    //stops the music when the screen is locked
    override fun onStop() {
        super.onStop()
        soundPool?.release()
        soundPool = null
    }

    // handles custom pagination animation
    // source: https://developer.android.com/training/animation/screen-slide-2#depth-page
    @RequiresApi(21)
    class DepthPageTransformer : ViewPager2.PageTransformer {

        private val minScale = 0.75f

        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                when {
                    (position < -1) -> { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        alpha = 0f
                    }
                    (position <= 0) -> { // [-1,0]
                        // Use the default slide transition when moving to the left page
                        alpha = 1f
                        translationX = 0f
                        translationZ = 0f
                        scaleX = 1f
                        scaleY = 1f
                    }
                    (position <= 1) -> { // [0,1]
                        // Fade the page out.
                        alpha = 1 - position

                        // Counteract the default slide transition
                        translationX = pageWidth * -position
                        // Move it behind the left page
                        translationZ = -1f

                        // Scale the page down (between MIN_SCALE and 1)
                        val scaleFactor = (minScale + (1 - minScale) * (1 - Math.abs(position)))
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                    }
                    else -> { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        alpha = 0f
                    }
                }
            }
        }
    }
}